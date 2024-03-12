<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<c:set var="gubun" value="${gubun}"/>
<c:set var="vo" value="${vo}" />


	<form id="courseForm" name="courseForm" method="post" onsubmit="return false">
	<input type="hidden" name="creCrsCnt" id="creCrsCnt" value="${vo.creCrsCnt }" />
	<input type="hidden" name="autoMakeYn" value="Y"/>
	<input type="hidden" name="crsCd" value="${vo.crsCd }"/>
	<input type="hidden" name="thumbFileSn" value="${vo.thumbFileSn }"/>
	<input type="hidden" name="attachFileSns" id="attachFileSns" value="${vo.attachFileSns}" />
	<input type="hidden" name="eduTeam" id="eduTeam" value="${vo.eduTeam }"/>
	<input type="hidden" name="nopLimitYn" id="nopLimitYn" value="${vo.nopLimitYn }"/>
	<input type="hidden" name="cpltHandlType" id="cpltHandlType" value="AT"/> <!-- 24.01.22 임종혁부장. 자동처리만 나오게 요청 -->
	<input type="hidden" name="certIssueYn" id="certIssueYn" value="Y"/> <!-- 개인 발급 가능 -->

	<c:if test="${gubun eq 'A'}">
		<input type="hidden" name="eduTm" id="eduTm"/>
		<input type="hidden" name="oflnEduTm" id="oflnEduTm"/>
	</c:if>
	<table summary="<spring:message code="course.title.course.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:25%"/>
			<col style="width:20%"/>
			<col style="width:35%"/>
		</colgroup>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="crsNm"><spring:message code="course.title.course.name"/></label></th>
			<td colspan="3">
				<div class="input-group" style="width:100%">
					<input type="text" dispName="<spring:message code="course.title.course.name"/>" maxlength="100" isNull="N" name="crsNm" class="form-control input-sm" id="crsNm" value="${vo.crsNm }" onchange="changeCrsNm()" onblur="chkChange()"/>
					<input type="hidden" name="crsNmOrigin" id="crsNmOrigin" value="${vo.crsNm }">
					<span class="input-group-btn">
						<button class="btn btn-default btn-sm" type="button" onclick="checkCrsNm()"><spring:message code="button.duplicate.check"/></button>
					</span>
					<c:if test="${gubun eq 'A'}"><input type="hidden"  name="chkDup" id="chkDup"  value="N"/></c:if>
					<c:if test="${gubun eq 'E'}"><input type="hidden"  name="chkDup" id="chkDup"  value="Y"/></c:if>
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.opermthd"/></th>
			<td>
				<c:choose>
					<c:when test="${gubun eq 'E'}">
						<%-- <input type="hidden" name="crsOperType" value="${courseVO.crsOperType}"/> --%>
						<select name="crsSvcType" id="crsSvcType" class="form-control input-sm"  onchange="selectBoxChange(this.id, this.value);">
							<c:forEach var="item" items="${crsSvcEditList}" varStatus="status">
							<option value="${item.codeCd}" <c:if test="${vo.crsSvcType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
							</c:forEach>				
						</select>
					</c:when>
					<c:otherwise>
						<select name="crsSvcType" id="crsSvcType" class="form-control input-sm" onChange="selectBoxChange(this.id, this.value);">
							<c:forEach var="item" items="${crsSvcEditList}" varStatus="status">
							<option value="${item.codeCd}" <c:if test="${vo.crsSvcType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
							</c:forEach>				
						</select>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>	
			<!--  -->
		<tr >
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.oper.type"/></th>
			<td>
				<div style="float:left">
				<c:if test="${gubun eq 'A'}">
				<select name="crsOperType" id="crsOperType" class="form-control input-sm"  onChange="selectBoxChange(this.id, this.value);">
					<c:forEach var="item" items="${crsOperTypeList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.crsOperType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<select name="crsOperType" id="crsOperType" class="form-control input-sm"  onChange="selectBoxChange(this.id, this.value);" readonly>  
					<c:forEach var="item" items="${crsOperTypeList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.crsOperType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				</div>
			</td>
			
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.crstype"/></th>
			<td>
				<div style="float:left">
				<c:if test="${gubun eq 'A'}">
				<select name="crsOperMthd" id="crsOperMthd" class="form-control input-sm"  onChange="selectBoxChange(this.id, this.value);">
					<c:forEach var="item" items="${crsOperMthdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.crsOperMthd eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<!-- 과목 (온라인,오프라인) 지정 후 과정유형이 변경되면 서비스에 결함이 발생할 수 있어 select box 를 readonly 함. (수정시..) -->
				<!-- 예를 들오 온라인 과정에 온라인 과목이 지정되어있는데  오프라인으로 변경되면 서비스 장애가 발생할 수 있음.-->
				<select name="crsOperMthd" id="crsOperMthd" class="form-control input-sm"  onChange="selectBoxChange(this.id, this.value);" readonly>  
					<c:forEach var="item" items="${crsOperMthdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.crsOperMthd eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				</div>
				<div id="enrlNopArea" style="float:left:display:hidden;">
					<span style="float:left;margin-left:10px;margin-right:5px;padding-top:5px;">교육인원</span>
					<input type="text" style="width:45px;float:left;text-align:right;" dispName="<spring:message code="course.title.course.stdcnt"/>" maxlength="10"  isNull="Y" name="eduNop" value="${vo.eduNop }" class="inputNumber form-control input-sm" id="eduNop" onkeyup="isChkNumber(this)"/>
					<span style="float:left;line-height:30px;padding-left:5px;"><spring:message code="common.title.cnt.user"/></span>
				</div>
			</td>
		</tr>	
			
		<tr id="hrdNetArea" style="display:hidden;">
			<th scope="row"><span style="color:red;">* </span><label for="simsaCode">HRD 심사코드</label></th>
			<td>
				<input type="text" dispName="심사코드" maxlength="50" isNull="Y" lenCheck="50"  name="simsaCode" value="${vo.simsaCode }" class="form-control input-sm" id="simsaCode"/>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="tracseId">HRD 훈련과정 ID</label></th>
			<td>
				<input type="text" dispName="훈련과정 ID" maxlength="50" isNull="Y" lenCheck="50"  name="tracseId" value="${vo.tracseId }" class="form-control input-sm" id="tracseId"/>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.category"/></th>
			<td>
				<div class="input-group">
					<input type="hidden" name="crsCtgrCd" id="writeCrsCtgrCd" value="${vo.crsCtgrCd }"/>
					<div class="input-group-btn btn-group">
    					<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
    						<span class="caret"></span>
  						</button>
  						<ul class="dropdown-menu" role="menu"  style="max-height:300px;overflow:auto;">
  						<c:set var="defLvl" value="0" />
						<c:set var="lineNo" value="0" />
						<c:set var="crsCtgrCd_01" value="0" />
						<c:set var="crsCtgrCd_02" value="0" />
						<c:set var="crsCtgrCd_03" value="0" />

						<c:forEach items="${courseCategoryList}" var="item">
							<c:set var="lineNo" value="${lineNo + 1}" />
    						<c:if test="${lineNo == 1}" >
								<c:set var="defLvl" value="${item.crsCtgrLvl}" />
							</c:if>
    						<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
							<c:if test="${item.subCnt > 0}">
								<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
							</c:if>
							<c:url var="blankImgPath" value="/img/framework/common/blank.gif" />

							<c:if test="${item.crsCtgrLvl == 1}">
								<c:set var="crsCtgrCd_01" value="${item.crsCtgrCd}" />
	    						<li><a href="javascript:setCrsCtgr('${item.crsCtgrCd}','${item.crsCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.crsCtgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.crsCtgrNm} </a></li>
							</c:if>

							<c:if test="${item.crsCtgrLvl == 2}">
								<c:if test="${crsCtgrCd_01 == item.parCrsCtgrCd}">
								<c:set var="crsCtgrCd_02" value="${item.crsCtgrCd}" />
	    						<li><a href="javascript:setCrsCtgr('${item.crsCtgrCd}','${item.crsCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.crsCtgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.crsCtgrNm} </a></li>
	    						</c:if>
							</c:if>

							<c:if test="${item.crsCtgrLvl == 3}">
								<c:if test="${crsCtgrCd_02 == item.parCrsCtgrCd}">
								<c:set var="crsCtgrCd_03" value="${item.crsCtgrCd}" />
	    						<li><a href="javascript:setCrsCtgr('${item.crsCtgrCd}','${item.crsCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.crsCtgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.crsCtgrNm} </a></li>
	    						</c:if>
							</c:if>
    						</c:forEach>
    						<c:if test="${empty courseForm.courseCategoryList }">
    						<!-- <li>*<spring:message code="library.message.ctgr.nodata"/></li> -->
    						</c:if>
  						</ul>
  					</div>
  					<input type="text" dispName="<spring:message code="course.title.course.category"/>" maxlength="100" isNull="N" name="crsCtgrNm" value="${vo.crsCtgrNm }" readonly="true" style="background-color:#ffffff;" class="form-control input-sm" id="writeCrsCtgrNm"/>
				</div>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" id="useYnY" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" id="useYnN" <c:if test="${vo.useYn ne 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="eduTm"><spring:message code="course.title.course.edutime"/></label></th>
			<td>
				<c:if test="${gubun eq 'A'}">

				<div id="crsOperMthdOn" style="display:">
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="eduTm_temp" id="eduTm_temp" class="inputNumber form-control input-sm" value="" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
				</div>

				<div id="crsOperMthdOf" style="display:none">
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="oflnEduTm_temp" id="oflnEduTm_temp" class="inputNumber form-control input-sm" value="" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
				</div>

				<div id="crsOperMthdBl" style="display:none">
				<span style="float:left;line-height:30px;"><spring:message code="course.title.createcourse.cyber"/> : </span>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="eduTm_tempBl" id="eduTm_tempBl" class="inputNumber form-control input-sm" value="" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>

				<span style="float:left;margin-left:20px;line-height:30px;"><spring:message code="course.title.createcourse.offline"/> : </span>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="oflnEduTm_tempBl" id="oflnEduTm_tempBl" class="inputNumber form-control input-sm" value="" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
				</div>

				</c:if>

				<c:if test="${gubun eq 'E'}">
				<c:choose>
					<c:when test="${vo.crsOperMthd eq 'ON'}">
				<input type="text" style="width:50px;text-align:right;float:left;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" dataType="number" name="eduTm" value="${vo.eduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="onlnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span>
				<input type="hidden" name="oflnEduTm"/>
					</c:when>
					<c:when test="${vo.crsOperMthd eq 'OF'}">
				<input type="text" style="width:50px;text-align:right;float:left;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" dataType="number" name="eduTm" value="${vo.eduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="onlnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span>
				<input type="hidden" name="oflnEduTm"/>
					</c:when>
					<c:otherwise>
				<span style="float:left;line-height:30px;"></span>
				<input type="text" style="width:50px;float:left;text-align:right;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" maxValue="100" dataType="number" name="eduTm" value="${vo.eduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="onlnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span>
				<input type="hidden" name="oflnEduTm"/>
				<%-- <span style="float:left;margin-left:20px;line-height:30px;"><spring:message code="course.title.createcourse.offline"/> : </span>
				<input type="text" style="width:50px;float:left;text-align:right;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" maxValue="100" dataType="number" name="oflnEduTm" value="${vo.oflnEduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="oflnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span> --%>
					</c:otherwise>
				</c:choose>
				</c:if>
			</td>
			<th scope="row"><label for="cpltScore"><spring:message code="course.title.course.completescore"/></label></th>
			<td>
				<input type="text" style="width:60px;float:left;text-align:right;" dispName="<spring:message code="course.title.course.completescore"/>" maxlength="3" maxValue="100" isNull="Y" name="cpltScore" value="${vo.cpltScore }" class="inputNumber form-control input-sm" id="cpltScore" onkeyup="isChkMaxNumber(this,100)"/>
				<span style="float:left;line-height:30px;padding-left:5px;"><spring:message code="common.title.score"/></span>
			</td>
		</tr>
		

		
		<tr >
			<%-- <th scope="row"><label for="eduTarget"><spring:message code="course.title.course.target"/></label></th>
			<td>
				<input type="text" style="width:96%;" dispName="<spring:message code="course.title.course.target"/>" maxlength="50" isNull="Y" name="eduTarget" value="${vo.eduTarget}" class="form-control input-sm" id="eduTarget"/>
			</td> --%>
			
			<th scope="row"><label for="eduPrice"><spring:message code="course.title.course.edufee"/></label></th>
			<td colspan="3">
				<div class="input-group">
				<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
					<input type="text" style="width:120px;text-align:right;" dispName="<spring:message code="course.title.course.edufee"/>" maxlength="10" isNull="Y" name="eduPrice" value="${vo.eduPrice}" class="inputNumber form-control input-sm" id="eduPrice" onkeyup="isChkNumber(this)"/>
				<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
				</div>
			</td>
		</tr>

		<c:if test="${gubun eq 'A'}">
		<tr id="oflnEduPlaceOnOf" style="display:none">
			<th scope="row"><label for="oflnEduPlace"><spring:message code="course.title.course.place"/></label></th>
			<td colspan="3">
				<input type="text" style="width:96%;" dispName="<spring:message code="course.title.course.target"/>" maxlength="50" isNull="Y" name="oflnEduPlace" class="form-control input-sm" id="oflnEduPlace"/>
			</td>
		</tr>
		</c:if>

		<%-- <c:if test="${gubun eq 'E'}">
			<c:if test="${vo.crsOperMthd eq 'OF' || vo.crsOperMthd eq 'BL' }">
		<tr>
			<th scope="row"><label for="oflnEduPlace"><spring:message code="course.title.course.place"/></label></th>
			<td colspan="3">
				<input type="text" style="width:96%;" dispName="<spring:message code="course.title.course.target"/>" maxlength="50" isNull="Y" name="oflnEduPlace" value="${vo.oflnEduPlace}" class="form-control input-sm" id="oflnEduPlace"/>
			</td>
		</tr>
			</c:if>
		</c:if> --%>
		
		<tr>
			<th scope="row"><label for="tchDesc">썸네일 이미지</label></th>
			<td colspan="3">
				<a href="javascript:uploderclick('thumbuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
				<input type="file" name="thumbuploader" id="thumbuploader" title="<spring:message code="board.title.bbs.atcl.thumb"/>" multiple style="display:none" accept="image/*"  "/><%-- 첨부파일 버튼 --%>
				<div class="upload">
					<div class="upload_inbox">
						<div id="thumbprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="thumbfiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="tchDesc">강의계획서</label></th>
			<td colspan="3">
				<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
				<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none"/><%-- 첨부파일 버튼 --%>
				<!-- The global progress bar -->
				<div id="atchprogress" class="progress">
		    		<div class="progress-bar progress-bar-success"></div>
				</div>
				<!-- The container for the uploaded files -->
				<div id="atchfiles" class="files multi_inbox"></div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="tchDesc">태그</label><br><button class="btnAdd"><spring:message code="button.plus"/></button></th>
			<td colspan="3">
				<div class="input-group">
				 <div class='addInput'>
				 	 <c:if test="${!empty preMetaTag}">
					   <c:forEach var="List" items="${preMetaTag}" varStatus="">
                            <div class="inputArea" style="display: flex; margin-top: 5px;">
                            <input type="text" style="text-align:right;width:200px;"name="metaTag" id="metaTag" class="inputNumber form-control input-sm" value="${List}"/>
                            <button type="button" class="btnRemove" onclick="delMetaTag(this)">제거</button><br>
                            </div>
                        </c:forEach>
                     </c:if>
				 </div>	
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="crsDesc"><spring:message code="course.title.course.desc"/></label></th>
			<td colspan="3">
				<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.crsDesc}</div>
				<textarea name="crsDesc" id="contentTextArea" class="sr-only"></textarea>
				<%-- <textarea name="crsDesc" lenCheck="1000" dispName="<spring:message code="course.title.course.desc"/>" style="height:80px" id="crsDesc" class="form-control input-sm">${vo.crsDesc}</textarea> --%>
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="eduPrps"><spring:message code="course.title.course.goal"/></label></th>
			<td colspan="3">
				<textarea name="eduPrps" lenCheck="1000" dispName="<spring:message code="course.title.course.goal"/>" style="height:80px" id="eduPrps" class="form-control input-sm">${vo.eduPrps}</textarea>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-bottom:20px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCourse()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCourse()"><spring:message code="button.add"/></button>
			<%-- <button class="btn btn-info btn-sm" onclick="crsMngForm('${vo.crsCd}')"><spring:message code="button.manage"/></button> --%>
			<button class="btn btn-warning btn-sm" onclick="delCourse()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="javascript:parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.fileupload.js"></script>
<script type="text/JavaScript" src="/js/common_fileupload.js"></script>	
<script type="text/javascript">

	// 페이지 초기화
	
	var summernote;
	var thumbFile;
	var atchFiles;
	$(document).ready(function() {
		thumbFile = new $M.JqueryFileUpload({
			"varName"			: "thumbFile",
			"files" 			: $.parseJSON('${vo.thumbFileJson}'),
			"uploaderId"		: "thumbuploader",
			"fileListId"		: "thumbfiles",
			"progressId"		: "thumbprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"infoUse"		: true,
			"uploadSetting"		: {
				'formData'		: { 'repository': 'CRS_THUMB',
	                                'organization' : "${USER_ORGCD}",
									'type'		: 'thumb' }
			}
		});
		
		atchFiles = new $M.JqueryFileUpload({
			"varName"			: "atchFiles",
			"files" 			: $.parseJSON('${vo.attachFilesJson}'),
			"uploaderId"		: "atchuploader",
			"fileListId"		: "atchfiles",
			"progressId"		: "atchprogress",
			"maxcount"			: 1,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'CRS_PLAN',
									'organization' : "${USER_ORGCD}",
									'type'		: 'file' }
			}
		});
		
		$('.btnAdd').click (function () {                                        
            $('.addInput').append ( 
            	'<div class="inputArea"  style="display: flex;margin-top: 5px;"><input type="text" style="text-align:right;width:200px;"name="metaTag" id="metaTag" class="inputNumber form-control input-sm"/>\
                <button type="button" class="btnRemove">제거</button><br></div>'                    
            );    // input taf 추가                     
            $('.btnRemove').on('click', function () { 
                $(this).prev().remove();        
                $(this).next().remove();        
                $(this).remove();               
            });
        });       
		
		selectBoxChange($("#crsSvcType").attr('id'), $("#crsSvcType").val());
		selectBoxChange($("#crsOperMthd").attr('id'), $("#crsOperMthd").val());
			
		
		ItemVO.orgCd = "${USER_ORGCD}";
		ItemVO.attachImagesJson = '${vo.attachImagesJson}';
		MessageVO.alertCntsNull = "<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>";
		MessageVO.confirmDelete = "<spring:message code="system.message.page.confirm.delete"/>";
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"CRS_CRS",
			'organization' 		: 	ItemVO.orgCd,
			"editorHeight"		:	"330px",
			"attachments"		:	$.parseJSON(ItemVO.attachImagesJson)
		});
		
		
		
		
	});
	

	function uploderclick(str) {
		$("#"+str).click();
	}
	
	 $('.btnRemove').on('click', function () { 
         $(this).prev().remove();        
         $(this).next().remove();        
         $(this).remove();               
     });
	
	/**
	 * 과정명 중복체크
	 */
	function checkCrsNm() {
		var crsNm = $("#crsNm").val();
		if(crsNm == ""){
			alert('<spring:message code="course.message.createcourse.input.coursename"/>');
			return;
		}
		var ajaxUrl = cUrl("/mng/course/course/checkCrsNm")
		var ajaxData = { 
						 "crsNm" : crsNm };
		$.getJSON (
			ajaxUrl, ajaxData,
			function(data) {
				if(data.dupYn == 'Y') {
					alert('<spring:message code="course.message.course.alert.dup.coursename"/>');
					$("#chkDup").val("N");
				} else {
					alert("사용 가능한 과정명입니다.");
					$("#chkDup").val("Y");
				}
		});
		return false;
	}
	
	/**
	 * 분류 등록
	 */
	 
	var modalBox = null;
	var ItemDTO = {
			sortKey : ""
		};
	function addCtgr(){
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/category/mainPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 700);
		modalBox.setTitle("<spring:message code="board.title.faq.category.write"/>");
		modalBox.show();
	}

	function reloadForm(){
		modalBoxClose();
	<c:if test="${gubun eq 'A'}">
		parent.crsWriteForm('');
	</c:if>
	<c:if test="${gubun eq 'E'}">
		parent.crsEditForm("${vo.crsCd}");
	</c:if>
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		
		
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			parent.location.reload();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
		
		/*
		if(resultDTO.result >= 0) {
			if($('#courseForm').find('input[name=cmd]').val() == "addCourse"){
				var item = resultDTO.returnDto;
				var msg = "<spring:message code="course.message.course.add.success.confirm"/>";
				if(confirm(msg)){
					parent.listCourse(1);
					parent.crsMngForm(item.crsCd);
				} else {
					parent.listCourse(1);
					parent.closeWriteArea();
				}
			} else {
				alert(resultDTO.message);
				// 정상 처리
				parent.listCourse(1);
				parent.closeWriteArea();

			}
		} else {
			// 비정상 처리
			alert(resultDTO.message);
		}
		*/
	}
	
	function setCrsCtgr(ctgrCd, ctgrNm) {
		$('#writeCrsCtgrNm').val(ctgrNm);
		$('#writeCrsCtgrCd').val(ctgrCd);
		$('#writeCrsCtgrNm').attr("title",ctgrNm);
	}
	
	/**
	 * 과정 등록
	 */
	function addCourse() {
		if(!validate(document.courseForm)) return;
		if($("#chkDup").val() == "N"){
			alert('<spring:message code="course.message.course.alert.nodupchek"/>');
			return;
		}
		if($("#crsSvcType").val() == "R" ) {
			if($("#simsaCode").val() == ""){
				alert('HRD-NET 심사코드를 입력해 주세요.');
				return;
			}
			if($("#tracseId").val() == ""){
				alert('HRD-NET 훈련과정 ID를 입력해 주세요.');
				return;
			}
		}
		if($("#crsOperMthd").val() != "ON" ) {
			if($('#eduNop').val() == '') {
				alert('수강인원이 입력되지 않았습니다.');
				return;
			}
		}
		if("BL" == $("#crsOperMthd").val()){
			$("#eduTm").val($("#eduTm_tempBl").val());
			$("#oflnEduTm").val($("#oflnEduTm_tempBl").val());
		}else {
			$("#eduTm").val($("#eduTm_temp").val());
			$("#oflnEduTm").val($("#oflnEduTm_temp").val());
		}
		process("addCourse");	// cmd
	}
	
	/**
	 * 과정 수정
	 */
	function editCourse() {
		if($("#chkDup").val() == "N"){
			alert('<spring:message code="course.message.course.alert.nodupchek"/>');
			return;
		}
		process("editCourse");	// cmd
	}
	
	/**
	 * 과정 삭제
	 */
	function delCourse() {
		var f = document.courseForm;
		if($("#creCrsCnt").val() > 0) {
			alert("<spring:message code="course.message.course.alert.cantdelete"/>");
			return;
		}
		if(confirm('<spring:message code="course.message.course.confirm.delete"/>')) {
			//process("deleteCourse");	// cmd
			$('#courseForm').attr("action","/mng/course/course/deleteCourse");
			$('#courseForm').ajaxSubmit(processCallback);
		} else {
			return;
		}
	}
	
	function chkChange(){
		if($("#crsNmOrigin").val() != $("#crsNm").val()){
			$("#chkDup").val("N");
		}
	}

	function changeCrsNm(){
		$("#chkDup").val("N");
	}

	function checkCrsNm() {
		var crsNm = $("#crsNm").val();
		if(crsNm == ""){
			alert('<spring:message code="course.message.createcourse.input.coursename"/>');
			return;
		}
		var ajaxUrl = cUrl("/mng/course/course/checkCrsNm")
		var ajaxData = { 
						 "crsNm" : crsNm };
		$.getJSON (
			ajaxUrl, ajaxData,
			function(data) {
				if(data.dupYn == 'Y') {
					alert('<spring:message code="course.message.course.alert.dup.coursename"/>');
					$("#chkDup").val("N");
				} else {
					alert('<spring:message code="course.message.course.alert.nodup.coursename"/>');
					$("#chkDup").val("Y");
				}
		});
		return false;
	}
	
	function reloadForm(){
		modalBoxClose();
	<c:if test="${gubun eq 'A'}">
		parent.crsWriteForm('');
	</c:if>
	<c:if test="${gubun eq 'E'}">
		parent.crsEditForm("${vo.crsCd}");
	</c:if>
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		var nopLimitYn = $("#nopLimitYn option:selected").val();
		if(nopLimitYn == "Y"){
			if($("#eduNop").val() == '') {
				alert('<spring:message code="course.message.createcourse.input.limitnum"/>');
				$("#eduNop").focus();
				return false;
			}
		}

		if(!validate(document.courseForm)) return;
		if(!selectCheck()) return;
		if(parseInt($("#cpltScore").val(),10) > 100) {
			alert("<spring:message code="course.message.course.alert.cpltscore"/>");
			return;
		}
		var _thumbFile = thumbFile.getFileSnAll();
		$(':input:hidden[name=thumbFileSn]').val(_thumbFile);
		var _paramFiles = atchFiles.getFileSnAll();
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);
		
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)) {
			alert(MessageVO.alertCntsNull);
			return false;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);
		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		
		$('#courseForm').find('input[name=cmd]').val(cmd);
		$('#courseForm').attr("action","/mng/course/course/"+ cmd);
		$('#courseForm').ajaxSubmit(processCallback);
	}
	
	
	/**
	 * select validation
	 */
	function selectCheck() {
		<c:if test="${gubun eq 'A'}">
			var crsOperType = $("#crsOperType option:selected").val();
			if (crsOperType == 'S') {
			  //document.getElementById('crsOperMthd').disabled  = false;
			   $("#crsOperMthd").attr("disabled", false);
			}else{
				var crsOperMthd = $("#crsOperMthd option:selected").val();
			}
		</c:if>
		var enrlCertMthd = $("#enrlCertMthd option:selected").val();

		<c:if test="${gubun eq 'A'}">
		if(crsOperMthd == '99') {
			alert('<spring:message code="course.message.course.alert.select.opermthd"/>');
			return false;
		}
		</c:if>
		if(enrlCertMthd == '99') {
			alert('<spring:message code="course.message.course.alert.select.enrollcomfirm"/>');
			return false;
		}
		return true;
	}
	var selectBoxChange = function(id, val) {
		//교육구분
		if(id == "crsSvcType") {
			if(val =="R") { // 국비지원 
				$("#hrdNetArea").show();
			} else {
				$("#hrdNetArea").hide();
			}
		}
		

		if (id == 'crsOperType') {
	        if (val == 'S') {
	        	//removeOptions(['OF', 'BL']);
	        	 document.getElementById('crsOperMthd').disabled  = true;
	        } else {
	        	//restoreOptions();
	        	 document.getElementById('crsOperMthd').disabled  = false;
	        }
	    }
		
		//교육구분 (오프라인, 혼합과정인 경우 교육인원을 등록 폼을 보여준다. )
		if(id == "crsOperMthd") {
			if(val !="ON") { // 오프라인, 혼합과정인  
				$("#enrlNopArea").show();
				$("#nopLimitYn").val("N");
			} else {
				$("#enrlNopArea").hide();
				$("#eduNop").val("0");
				$("#nopLimitYn").val("Y");
				
			}
		}
		
	}	
	/* function removeOptions(optionValues) {
	    var selectBox = document.getElementById('crsOperMthd');
	    for (var i = 0; i < selectBox.options.length; i++) {
	        if (optionValues.includes(selectBox.options[i].value)) {
	            selectBox.remove(i);
	            i--; 
	        }
	    }
	}

	function restoreOptions() {
	    var selectBox = document.getElementById('crsOperMthd');
	    while (selectBox.options.length > 0) {
	        selectBox.remove(0);
	    }
	    
	    var crsOperMthdList = [
	        { codeCd: 'ON', codeNm: '온라인' },
	        { codeCd: 'OF', codeNm: '오프라인' },
	        { codeCd: 'BL', codeNm: '혼합교육' }
	    ];

	    crsOperMthdList.forEach(function(item) {
	        var option = document.createElement('option');
	        option.value = item.codeCd;
	        option.text = item.codeNm;
	        selectBox.add(option);
	    });
	}
 */
	
	
</script>
