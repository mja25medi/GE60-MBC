<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="courseVO" value="${vo}"/>


	<form id="courseForm" name="courseForm" onsubmit="return false" action="/mng/course/course">
	<input type="hidden" name="creCrsCnt" id="creCrsCnt" value="${courseVO.creCrsCnt }" />
	<input type="hidden" name="autoMakeYn" value="Y"/>
	<input type="hidden" name="crsCd" value="${courseVO.crsCd }"/>
	<input type="hidden" name="eduTeam" id="eduTeam" value="${courseVO.eduTeam }"/>
	<c:if test="${gubun eq 'A'}">
	<input type="hidden" name="eduTm" id="eduTm"/>
	<input type="hidden" name="oflnEduTm" id="oflnEduTm"/>
	</c:if>
	<table summary="<spring:message code="course.title.course.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="crsNm"><spring:message code="course.title.course.name"/></label></th>
			<td colspan="3">
				<div class="input-group" style="width:100%">
					<input type="text" dispName="<spring:message code="course.title.course.name"/>" maxlength="100" isNull="N" name="crsNm" class="form-control input-sm" id="crsNm" value="${courseVO.crsNm }" onchange="changeCrsNm()" onblur="chkChange()"/>
					<input type="hidden" name="crsNmOrigin" id="crsNmOrigin" value="${courseVO.crsNm }">
					<span class="input-group-btn">
						<button class="btn btn-default btn-sm" type="button" onclick="checkCrsNm()"><spring:message code="button.duplicate.check"/></button>
					</span>
					<c:if test="${gubun eq 'A'}"><input type="hidden"  name="chkDup" id="chkDup"  value="N"/></c:if>
					<c:if test="${gubun eq 'E'}"><input type="hidden"  name="chkDup" id="chkDup"  value="Y"/></c:if>
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.category"/></th>
			<td>
				<div class="input-group">
					<input type="hidden" name="crsCtgrCd" id="writeCrsCtgrCd" value="${courseVO.crsCtgrCd }"/>
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
    						<li>*<spring:message code="library.message.ctgr.nodata"/></li>
    						</c:if>
  						</ul>
  					</div>
  					<input type="text" dispName="<spring:message code="course.title.course.category"/>" maxlength="100" isNull="N" name="crsCtgrNm" value="${courseVO.crsCtgrNm }" readonly="true" style="background-color:#ffffff;" class="form-control input-sm" id="writeCrsCtgrNm"/>
  					<span class="input-group-btn">
						<button class="btn btn-default btn-sm" type="button" onclick="addCtgr()"><spring:message code="board.title.faq.category.write"/></button>
					</span>
				</div>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" id="useYnY" <c:if test="${courseVO.useYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" id="useYnN" <c:if test="${courseVO.useYn ne 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/></label>
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
					<c:when test="${courseVO.crsOperMthd eq 'ON'}">
				<input type="text" style="width:50px;text-align:right;float:left;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" dataType="number" name="eduTm" value="${courseVO.eduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="onlnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span>
				<input type="hidden" name="oflnEduTm"/>
					</c:when>
					<c:when test="${courseVO.crsOperMthd eq 'OF'}">
				<input type="text" style="width:50px;text-align:right;float:left;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" dataType="number" name="oflnEduTm" value="${courseVO.oflnEduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="oflnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span>
				<input type="hidden" name="eduTm"/>
					</c:when>
					<c:otherwise>
				<span style="float:left;line-height:30px;"><spring:message code="course.title.createcourse.cyber"/> : </span>
				<input type="text" style="width:50px;float:left;text-align:right;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" maxValue="100" dataType="number" name="eduTm" value="${courseVO.eduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="onlnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span>

				<span style="float:left;margin-left:20px;line-height:30px;"><spring:message code="course.title.createcourse.offline"/> : </span>
				<input type="text" style="width:50px;float:left;text-align:right;" dispName="<spring:message code="course.title.createcourse.edutime"/>" maxlength="3" isNull="Y" lenCheck="3" maxValue="100" dataType="number" name="oflnEduTm" value="${courseVO.oflnEduTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="oflnEduTm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.time"/></span>
					</c:otherwise>
				</c:choose>
				</c:if>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="eduNop"><spring:message code="course.title.course.stdcnt"/></label></th>
			<td>
				<%-- <input type="text" style="width:60px;text-align:right;" dispName="<spring:message code="course.title.course.stdcnt"/>" maxlength="10" isNull="N" name="eduNop" value="${courseVO.eduNop }" class="inputNumber form-control input-sm" id="eduNop" onkeyup="isChkNumber(this)"/> --%>
				<div style="float:left">
				<select name="nopLimitYn" id="nopLimitYn" style="width:120px;" onChange="chgNopLimitYn();" class="form-control input-sm">
				<c:forEach var="item" items="${nopLimitList}">
					<c:set var="selected" value=""/>
					<c:if test="${courseVO.nopLimitYn eq  item.codeCd}">
						<c:set var="selected" value="selected"/>
					</c:if>
					<c:set var="codeName" value="${item.codeNm}"/>
					<c:forEach var="lang" items="${item.codeLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY}"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<option value="${item.codeCd}" ${selected}>${codeName}</option>
				</c:forEach>
				</select>
				</div>
				<div id="enrlNopArea" style="float:left:display:hidden;">
					<input type="text" style="width:60px;float:left;text-align:right;" dispName="<spring:message code="course.title.course.stdcnt"/>" maxlength="10"  isNull="Y" name="eduNop" value="${courseVO.eduNop }" class="inputNumber form-control input-sm" id="eduNop" onkeyup="isChkNumber(this)"/>
					<span style="float:left;line-height:30px;padding-left:5px;"><spring:message code="common.title.cnt.user"/></span>
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.edumthd"/></th>
			<td>
				<c:if test="${gubun eq 'A'}">
				<select name="crsOperMthd" id="crsOperMthd" class="form-control input-sm">
					<c:forEach var="item" items="${crsOperMthdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${courseVO.crsOperMthd eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<select name="crsOperMthd" id="crsOperMthd" class="form-control input-sm">
					<c:forEach var="item" items="${crsOperMthdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${courseVO.crsOperMthd eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
			</td>
			<th scope="row"><label for="cpltScore"><spring:message code="course.title.course.completescore"/></label></th>
			<td>
				<input type="text" style="width:60px;float:left;text-align:right;" dispName="<spring:message code="course.title.course.completescore"/>" maxlength="3" maxValue="100" isNull="Y" name="cpltScore" value="${courseVO.cpltScore }" class="inputNumber form-control input-sm" id="cpltScore" onkeyup="isChkMaxNumber(this,100)"/>
				<span style="float:left;line-height:30px;padding-left:5px;"><spring:message code="common.title.score"/></span>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.enrollconfirm"/></th>
			<td>
				<select name="enrlCertMthd" id="enrlCertMthd" class="form-control input-sm">
					<c:forEach var="item" items="${enrlCertMthdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.enrlCertMthd eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.certificate"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="certIssueYn" value="Y" id="certIssueYnY" <c:if test="${courseVO.certIssueYn eq 'Y'}">checked</c:if>/> <spring:message code="course.title.course.certificate.person"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="certIssueYn" value="N" id="certIssueYnN" <c:if test="${courseVO.certIssueYn ne 'Y'}">checked</c:if>/> <spring:message code="course.title.course.certificate.admin"/></label>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.completehandle"/></th>
			<td>
				<select name="cpltHandlType" id="cpltHandlType" class="form-control input-sm">
					<c:forEach var="item" items="${cpltHandlTypeList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.cpltHandlType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.opermthd"/></th>
			<td>
				<c:choose>
					<c:when test="${gubun eq 'E'}">
						<input type="hidden" name="crsOperType" value="${courseVO.crsOperType}"/>
						<select name="crsOperTypeEdit" id="crsOperTypeEdit" class="form-control input-sm">
							<c:forEach var="item" items="${crsOperTypeEditList}" varStatus="status">
							<option value="${item.codeCd}" <c:if test="${courseVO.crsOperType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
							</c:forEach>				
						</select>
					</c:when>
					<c:otherwise>
						<select name="crsOperType" id="crsOperType" class="form-control input-sm" onChange="chkHandlType()">
							<c:forEach var="item" items="${crsOperTypeEditList}" varStatus="status">
							<option value="${item.codeCd}" <c:if test="${courseVO.crsOperType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
							</c:forEach>				
						</select>
					</c:otherwise>
				</c:choose>


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

		<c:if test="${gubun eq 'E'}">
			<c:if test="${courseVO.crsOperMthd eq 'OF' || courseVO.crsOperMthd eq 'BL' }">
		<tr>
			<th scope="row"><label for="oflnEduPlace"><spring:message code="course.title.course.place"/></label></th>
			<td colspan="3">
				<input type="text" style="width:96%;" dispName="<spring:message code="course.title.course.target"/>" maxlength="50" isNull="Y" name="oflnEduPlace" value="${courseVO.oflnEduPlace}" class="form-control input-sm" id="oflnEduPlace"/>
			</td>
		</tr>
			</c:if>
		</c:if>

		<tr >
			<th scope="row"><label for="eduTarget"><spring:message code="course.title.course.target"/></label></th>
			<td>
				<input type="text" style="width:96%;" dispName="<spring:message code="course.title.course.target"/>" maxlength="50" isNull="Y" name="eduTarget" value="${courseVO.eduTarget}" class="form-control input-sm" id="eduTarget"/>
			</td>
			<th scope="row"><label for="eduPrice"><spring:message code="course.title.course.edufee"/></label></th>
			<td>
				<div class="input-group">
				<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
					<input type="text" style="width:120px;text-align:right;" dispName="<spring:message code="course.title.course.edufee"/>" maxlength="10" isNull="Y" name="eduPrice" value="${courseVO.eduPrice}" class="inputNumber form-control input-sm" id="eduPrice" onkeyup="isChkNumber(this)"/>
				<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="crsDesc"><spring:message code="course.title.course.desc"/></label></th>
			<td colspan="3">
				<textarea name="crsDesc" lenCheck="1000" dispName="<spring:message code="course.title.course.desc"/>" style="height:80px" id="crsDesc" class="form-control input-sm">${courseVO.crsDesc}</textarea>
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="eduPrps"><spring:message code="course.title.course.goal"/></label></th>
			<td colspan="3">
				<textarea name="eduPrps" lenCheck="1000" dispName="<spring:message code="course.title.course.goal"/>" style="height:80px" id="eduPrps" class="form-control input-sm">${courseVO.eduPrps}</textarea>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="eduPrps"><spring:message code="course.title.course.scoreratio"/> (%)</label></th>
			<td colspan="3">
				<ul style="list-style-type:none;padding:0px;width:100%">
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="prgrRatio"><spring:message code="course.title.course.ratio.progress"/></label></span>
							<input type="text" dispName="<spring:message code="course.title.course.ratio.progress"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="prgrRatio" value="${courseVO.prgrRatio}" class="inputNumber form-control input-sm" id="prgrRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="examRatio"><spring:message code="course.title.course.ratio.exam"/></label></span>
							<input type="text" dispName="<spring:message code="course.title.course.ratio.exam"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="examRatio" value="${courseVO.examRatio}" class="inputNumber form-control input-sm" id="examRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="asmtRatio"><spring:message code="course.title.course.ratio.asmt"/></label></span>
							<input type="text" dispName="<spring:message code="course.title.course.ratio.asmt"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="asmtRatio" value="${courseVO.asmtRatio}" class="inputNumber form-control input-sm" id="asmtRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="forumRatio"><spring:message code="course.title.course.ratio.forum"/></label></span>
							<input type="text" dispName="<spring:message code="course.title.course.ratio.forum"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="forumRatio" value="${courseVO.forumRatio}" class="inputNumber form-control input-sm" id="forumRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="joinRatio"><spring:message code="course.title.course.ratio.join"/></label></span>
							<input type="text" dispName="<spring:message code="course.title.course.ratio.join"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="joinRatio" value="${courseVO.joinRatio}" class="inputNumber form-control input-sm" id="joinRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="etcRatio"><spring:message code="course.title.course.ratio.etc"/></label></span>
							<input type="text" dispName="<spring:message code="course.title.course.ratio.etc"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="etcRatio" value="${courseVO.etcRatio}" class="inputNumber form-control input-sm" id="etcRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="attdRatio"><spring:message code="course.title.course.ratio.attend"/></label></span>
							<input type="text" dispName="<spring:message code="course.title.course.ratio.attend"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="attdRatio" class="inputNumber form-control input-sm" id="attdRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="etcRatio"><spring:message code="course.title.course.ratio.total"/></label></span>
							<input type="text" name="totRatio"  id="totRatio" class="inputNumber form-control input-sm" style="text-align:right;width:50px;background-color:#fff" readonly="readonly"/>
						</div>
					</li>
				</ul>
				<%-- <p><spring:message code="course.message.course.alert.ratio"/></p> --%>
			</td>
		</tr>
	</table>
	<div style="width:100%;">
		<div style="float:left; height:40px; line-height:30px;">
			<b><spring:message code="course.message.course.alert.ratio"/></b>
		</div>
	</div>
	<div class="text-right" style="margin-bottom:20px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCourse()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCourse()"><spring:message code="button.add"/></button>
			<button class="btn btn-info btn-sm" onclick="crsMngForm('${courseVO.crsCd}')"><spring:message code="button.manage"/></button>
			<button class="btn btn-warning btn-sm" onclick="delCourse()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="closeWriteArea()"><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
		
<script type="text/javascript">

	//팝업박스
	//var reshTpltPopBox = PopupBox("", "", "", "width=450,height=360,style=normal,bgcolor=,modal=yes,resizable=no,move=yes,titlebar=yes,position=absolute,top=50,left=200","set2");

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		var gubun = "${gubun}";

		if(gubun == 'E'){
			$('#crsOperTypeEdit').attr('disabled','true');
		}
		chgNopLimitYn();
		sumTotal();
		changeOperMthd();
	});

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
		if(!ratioCheck()) return;
		if(!selectCheck()) return;
		if(parseInt($("#cpltScore").val(),10) > 100) {
			alert("<spring:message code="course.message.course.alert.cpltscore"/>");
			return;
		}
		$('#courseForm').find('input[name=cmd]').val(cmd);
		$('#courseForm').attr("action","/mng/course/course/"+ cmd);
		$('#courseForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
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
	 * 비율 설정 체크
	 */
	function ratioCheck() {
		var prgrRatio = parseInt(nvlNumber($("#prgrRatio").val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio").val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio").val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio").val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio").val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio").val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio").val()),10);

		var totRatio = prgrRatio + attdRatio + examRatio + forumRatio + asmtRatio + joinRatio + etcRatio;

		if(totRatio != 100) {
			alert('<spring:message code="course.message.course.alert.ratio"/>');
			return false;
		}
		return true;

	}

	function selectCheck() {
		<c:if test="${gubun eq 'A'}">
		var crsOperMthd = $("#crsOperMthd option:selected").val();
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

	function changeCrsNm(){
		$("#chkDup").val("N");
	}
	/*
	* 교육방법 변경시 시간입력 변경
	*/
	function changeOperMthd(){
		var mthd = $("#crsOperMthd").val();
		if("ON" == mthd){
			$("#crsOperMthdOn").show();
			$("#crsOperMthdOf").hide();
			$("#crsOperMthdBl").hide();
			$("#oflnEduPlaceOnOf").hide();

			$("#eduTm_temp").val("");
			$("#oflnEduTm_temp").val("");
			$("#eduTm_tempBl").val("");
			$("#oflnEduTm_tempBl").val("");
			$("#oflnEduPlace").val("");
			$("#attdRatio").attr("disabled", true);
			$("#prgrRatio").attr("disabled", false);
		} else if("OF" == mthd){
			$("#crsOperMthdOn").hide();
			$("#crsOperMthdOf").show();
			$("#crsOperMthdBl").hide();
			$("#oflnEduPlaceOnOf").show();

			$("#eduTm_temp").val("");
			$("#oflnEduTm_temp").val("");
			$("#eduTm_tempBl").val("");
			$("#oflnEduTm_tempBl").val("");
			$("#oflnEduPlace").val("");
			$("#attdRatio").attr("disabled", false);
			$("#prgrRatio").attr("disabled", true);
		}else if("BL" == mthd){
			$("#crsOperMthdOn").hide();
			$("#crsOperMthdOf").hide();
			$("#crsOperMthdBl").show();
			$("#oflnEduPlaceOnOf").show();

			$("#eduTm_temp").val("");
			$("#oflnEduTm_temp").val("");
			$("#eduTm_tempBl").val("");
			$("#oflnEduTm_tempBl").val("");
			$("#oflnEduPlace").val("");
			$("#attdRatio").attr("disabled", false);
			$("#prgrRatio").attr("disabled", false);
		}

	}

	function chkOperType(){

		if($("#cpltHandlType").val() == "MA" && $("#crsOperType").val() == "S" ){
			alert('<spring:message code="course.open.message.course.opertypechk"/>');
			$("#cpltHandlType").val("AT");
		}
		<c:if test="${gubun eq 'E'}">
		if($("#cpltHandlType").val() == "MA" && $("#crsOperTypeEdit").val() == "S" ){
			alert('<spring:message code="course.open.message.course.opertypechk"/>');
			$("#cpltHandlType").val("AT");
		}
		</c:if>
	}

	function chkHandlType(){
		if($("#cpltHandlType").val() == "MA" && $("#crsOperType").val() == "S" ){
			alert('<spring:message code="course.open.message.course.opertypechk"/>');
			$("#cpltHandlType").val("AT");
		}
		<c:if test="${gubun eq 'E'}">
		if($("#cpltHandlType").val() == "MA" && $("#crsOperTypeEdit").val() == "S" ){
			alert('<spring:message code="course.open.message.course.opertypechk"/>');
			$("#cpltHandlType").val("AT");
		}
		</c:if>
	}

	//인원제한 able <-> disable
	function chgNopLimitYn(){
		var nopLimitYn = $("#nopLimitYn option:selected").val();
		if(nopLimitYn == "Y"){
			$('#enrlNopArea').show();
		}else{
			$('#enrlNopArea').hide();
		}
	}

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
		parent.crsEditForm("${courseVO.crsCd}");
	</c:if>
	}

	function sumTotal(){
		var prgrRatio = parseInt(nvlNumber($("#prgrRatio").val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio").val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio").val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio").val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio").val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio").val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio").val()),10);

		var totRatio = prgrRatio + attdRatio + examRatio + forumRatio + asmtRatio + joinRatio + etcRatio;

		$("#totRatio").val(totRatio);

	}


</script>
