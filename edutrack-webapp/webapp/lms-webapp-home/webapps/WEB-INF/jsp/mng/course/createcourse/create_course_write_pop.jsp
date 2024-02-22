<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="courseVO" value="${courseVO}"/>
<c:set var="createCourseVO" value="${vo}"/>
	<form name="Input_0" id="Input_0" onsubmit="return false">
	<div style="height:15px;"></div>
	<c:set var="creTerm" value="${maxTerm + creId}"/>
	<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:17%"/>
			<col style="width:33%"/>
			<col style="width:17%"/>
			<col style="width:33%"/>
		</colgroup>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="crsCreNm_0"><spring:message code="course.title.course.name"/></label></th>
			<td>
				<select name="crsCreNm_0" id="crsCreNm_0" class="form-control input-sm" onchange="selectBoxChange(this.value);">
					<option value="">과정선택</option>
					<c:forEach items="${courseList}" var="courseList">
						<option value="${courseList.crsCd}" 
								value2="${courseList.crsNm}" 
								value3="${courseList.creCrsCnt + 1}" 
								value4="${courseList.crsOperMthd}" 
								value5="${courseList.eduPrice }"
								value6="${courseList.eduTm}"
								value7="${courseList.cpltScore}">${courseList.crsNm}</option>
					</c:forEach>
				</select>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="creTerm"><spring:message code="course.title.createcourse.creterm"/></label></th>
			<td>
				<div class="input-group" style="float:left;">
					<input type="text" style="width:50px;" maxlength="50" name="creTerm_0" id="creTerm_0" class="form-control input-sm" value="" readonly/>
				</div>
			</td>
		</tr>
		
		<tr id="qrInput" style="display: none;">
			<th scope="row"><label for="QR">QR 등록</label></th>
			<td colspan="3">
				<a href="javascript:uploderclick('QRuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
				<input type="file" name="QRuploader" id="QRuploader" title="QRuploader" multiple style="display:none" accept="image/*"  "/><%-- 첨부파일 버튼 --%>
				<div class="upload">
					<div class="upload_inbox">
						<div id="QRprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="QRfiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.duration.aplc"/></th>
			<td colspan="4">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcStartDttm_0" id="enrlAplcStartDttm_0" class="inputDate form-control input-sm" autocomplete="off" />
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcStartDttm_0')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcEndDttm_0" id="enrlAplcEndDttm_0" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcEndDttm_0')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlAplcStartDttm_0" name2="enrlAplcEndDttm_0" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span>
				<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'ON' }"><spring:message code="course.title.createcourse.method.online"/></c:if>
				<c:if test="${courseVO.crsOperMthd eq 'OF' }"><spring:message code="course.title.createcourse.method.offline"/></c:if>
				<spring:message code="course.title.createcourse.duration.edu"/>
			</th>
			<td colspan="4">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlStartDttm_0" id="enrlStartDttm_0" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm_0')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlEndDttm_0" id="enrlEndDttm_0" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm_0')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlStartDttm_0" name2="enrlEndDttm_0" />
			</td>
			<c:if test="${courseVO.crsOperType eq 'S'}">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.eduday"/></th>
			<td>
				<input type="hidden" name="enrlEndDttm_0" id="enrlEndDttm_0"/>
				<input type="hidden" name="enrlStartDttm_0" id="enrlStartDttm_0"/>
				<div style="float: left;width: 20%;">
				<input type="text" style="text-align:right;width:50px;float:left;" maxlength="3" name="enrlDaycnt_0" id="enrlDaycnt_0" class="inputNumber form-control input-sm" />
				<span style="float:left;line-height:30px;padding-left:5px"><spring:message code="common.title.day"/></span>
				</div>
				<div style="float: left;width: 80%;">
					<p style="margin: 0px;"><spring:message code="course.message.course.alert.enrldaycnt"/></p>
				</div>
			</td>
			</c:if>
		</tr>
		
		<c:if test="${courseVO.crsOperMthd eq 'BL'}">
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.duration.offedu"/></th>
			<td colspan="3">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="oflnStartDttm_0" id="oflnStartDttm_0" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('oflnStartDttm_0')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="oflnEndDttm_0" id="oflnEndDttm_0" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('oflnEndDttm_0')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="oflnStartDttm_0" name2="oflnEndDttm_0" />
			</td>
		</tr>
		</c:if>
		<c:if test="${courseVO.crsOperMthd eq 'ON' || courseVO.crsOperMthd eq 'BL'}">
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.endday.audit"/></th>
			<td colspan="3">
				<c:if test="${courseVO.crsOperType eq 'R'}">
				<div style="float:left">
					<div class="input-group" style="float:left;width:128px;">
						<input type="text" maxlength="10" name="auditEndDttm_0" id="auditEndDttm_0" class="inputDate form-control input-sm"/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('auditEndDttm_0')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="auditEndDttm_0" />
				</div>
				<div style="float:left;margin-left:10px;">
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('0','1')" ><spring:message code="button.month.1"/></button>
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('0','3')" ><spring:message code="button.month.3"/></button>
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('0','6')" ><spring:message code="button.month.6"/></button>
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('0','12')" ><spring:message code="button.month.12"/></button>
				</div>
				</c:if>
				<c:if test="${courseVO.crsOperType eq 'S'}">
					 <input type="hidden" name="auditEndDttm_0" id="auditEndDttm_0"/>
					 <span class='red'> - </span>
				</c:if>
			</td>
		</tr>
		</c:if>
		
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="scoreOpenDttm"><spring:message code="course.title.createterm.score.date"/></label></th>
			<td>
				<div style="float:left">
					<div class="input-group" style="float:left;width:128px;">
						<input type="text" maxlength="10" name="scoreOpenDttm_0" id="scoreOpenDttm_0" class="inputDate form-control input-sm" autocomplete="off"/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreOpenDttm_0')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="scoreOpenDttm_0" />
				</div>
			</td>
				<th scope="row" ><span style="color:red;">* </span><label for="eduPrice"><spring:message code="course.title.course.edufee"/></label></th>
			<td>
				<div class="input-group">
				<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
					<input type="text" style="width:120px;text-align:right;" maxlength="10" name="eduPrice_0" id="eduPrice_0"  class="inputNumber form-control input-sm"  value="${courseVO.eduPrice }" onkeyup="isChkNumber(this)"/>
				<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
				</div>
			</td>
		</tr>
		
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="cpltScore"><spring:message code="course.title.course.completescore"/></label></th>
			<td>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" maxValue="100" name="cpltScore_0" id="cpltScore_0" class="inputNumber form-control input-sm" value="${courseVO.cpltScore}" onkeyup="isChkMaxNumber(this,100)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.score"/></span>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.edutime"/></th>
			<td colspan="3">
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="onlnEduTm_0" id="onlnEduTm_0" class="inputNumber form-control input-sm" value="${courseVO.eduTm}" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
			</td>
			
			<th scope="row" style="display: none"><span style="color:red;">* </span><spring:message code="course.title.createcourse.decls.cnt"/></th>
			<td style="display: none">
				<input type="text" style="width:40px;text-align:right;" maxlength="3" name="declsCnt_0" id="declsCnt_0" value="1" class="inputNumber form-control input-sm" onkeyup="isChkNumber(this)"/>
			</td>
			
			
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span>과정구분</th>
			<td>
				<select name="creTypeCd_0" id="creTypeCd_0" class="form-control input-sm" style="float:left;width: 70%;margin-right: 5px;" onchange="offEduPlace()" readonly>
					<option value="">과정유형을 선택하세요</option>
					<option value="ON">온라인</option>
					<option value="OF">오프라인</option>
					<option value="BL">혼합</option>						
				</select>
			</td>
			<th scope="row">강의실</th>
			<td>
				<select name="oflnEduPlace_0" id="oflnEduPlace_0" class="form-control input-sm" style="display: none;" >
					<option value="">장소를 선택하세요.</option>
					<c:forEach var="roomList" items="${lecRoomList}">
						<option value="${roomList.addr1}${roomList.addr2}">${roomList.addr1}${roomList.addr2}</option>
					</c:forEach>
				</select>
			</td>
		
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="dayStudyLimit_0">진도제한</label></th>
			<td colspan="3">
				<p style="margin: 5px 5px;float: left;">하루 최대</p>
				<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="dayStudyLimit_0" value="${createCourseVO.dayStudyLimit }" class="form-control input-sm" id="dayStudyLimit_0" style="float: left;width: 20%"  onkeyup="isChkNumber(this)"/>
				<p style="margin: 5px 5px;float: left;">차시까지만 수강 가능</p>
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="eduPrps"><spring:message code="course.title.course.scoreratio"/> (%)</label></th>
			<td colspan="3">
				<ul id="ratioList" style="list-style-type:none;padding:0px;width:100%">
					<%-- <li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="prgrRatio_0"><spring:message code="course.title.course.ratio.progress"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="prgrRatio_0" id="prgrRatio_0" class="inputNumber form-control input-sm" value="${courseVO.prgrRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('0');" />
						</div>
					</li> --%>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="semiExamRatio_0">진행단계</label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="semiExamRatio_0" id="semiExamRatio_0" class="inputNumber form-control input-sm" value="${courseVO.semiExamRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('0');" />
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="examRatio_0"><spring:message code="course.title.course.ratio.exam"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="examRatio_0" id="examRatio_0" class="inputNumber form-control input-sm" value="${courseVO.examRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('0');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="asmtRatio_0"><spring:message code="course.title.course.ratio.asmt"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="asmtRatio_0" id="asmtRatio_0" class="inputNumber form-control input-sm" value="${courseVO.asmtRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('0');"/>
							&nbsp&nbsp&nbsp<button id="addEtc" style="display: none;"><spring:message code="button.plus"/></button>
						</div>
					</li>
					<%-- <li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="forumRatio_0"><spring:message code="course.title.course.ratio.forum"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="forumRatio_0" id="forumRatio_0" class="inputNumber form-control input-sm" value="${courseVO.forumRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('0');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="joinRatio_0"><spring:message code="course.title.course.ratio.join"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="joinRatio_0" id="joinRatio_0" class="inputNumber form-control input-sm" value="${courseVO.joinRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('0');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="etcRatio_0"><spring:message code="course.title.course.ratio.etc"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="etcRatio_0" id="etcRatio_0" class="inputNumber form-control input-sm" value="${courseVO.etcRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="attdRatio_0"><label for="attdRatio"><spring:message code="course.title.course.ratio.attend"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="attdRatio_0" id="attdRatio_0" class="inputNumber form-control input-sm" value="${courseVO.attdRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('0');" <c:if test="${courseVO.crsOperMthd eq 'ON'}">disabled="disabled"</c:if>/>
						</div>
					</li> --%>
					<li style="float:left;margin-right:30px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="totRatio_0"><spring:message code="course.title.course.ratio.total"/></label></span>
							<input type="text" name="totRatio_0" id="totRatio_0" class="inputNumber form-control input-sm" style="text-align:right;width:50px;background-color:#fff;" readonly="readonly"/>
						</div>
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="course.title.createcourse.info"/></th>
			<td colspan="3">
				<textarea style="height:50px" dispName="<spring:message code="course.title.createcourse.info"/>"  lenCheck="2000" name="creDesc_0" class="form-control input-sm" id="creDesc_0">${vo.creDesc }</textarea>
			</td>
		</tr>
	</table>
	<div style="width:100%;">
		<div style="float:left; height:40px; line-height:30px;">
			<b><spring:message code="course.message.course.alert.ratio"/></b>
		</div>
	</div>
	</form>

	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary" onclick="addCreateCourse()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary" onclick="editCreateCourse()"><spring:message code="button.add"/></button>
			<button class="btn btn-warning" onclick="delCreateCourse()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>

	<form id="createCourseFrom" name="createCourseFrom" onsubmit="return false">
	<input type="hidden" name="crsCd" id="crsCd" value=""/>
	<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd }"/>
	<input type="hidden" name="crsCreNm" id="crsCreNm" value="${vo.crsCreNm }"/>
	<input type="hidden" name="creYear" id="creYear" value="${vo.creYear }"/>
	<input type="hidden" name="creTerm" id="creTerm" value="${vo.creTerm }"/>
	<input type="hidden" name="enrlAplcStartDttm" id="enrlAplcStartDttm" value="${vo.enrlAplcStartDttm }"/>
	<input type="hidden" name="enrlAplcEndDttm" id="enrlAplcEndDttm" value="${vo.enrlAplcEndDttm }"/>
	<input type="hidden" name="enrlStartDttm" id="enrlStartDttm" value="${vo.enrlStartDttm }"/>
	<input type="hidden" name="enrlEndDttm" id="enrlEndDttm" value="${vo.enrlEndDttm }"/>
	<input type="hidden" name="enrlDaycnt" id="enrlDaycnt" value="${vo.enrlDaycnt }"/>
	<input type="hidden" name="oflnStartDttm" id="oflnStartDttm" value="${vo.oflnStartDttm }"/>
	<input type="hidden" name="oflnEndDttm" id="oflnEndDttm" value="${vo.oflnEndDttm }"/>
	<input type="hidden" name="auditEndDttm" id="auditEndDttm" value="${vo.auditEndDttm }"/>
	<input type="hidden" name="cpltScore" id="cpltScore" value="${vo.cpltScore }"/>
	<input type="hidden" name="nopLimitYn" id="nopLimitYn" value="${vo.nopLimitYn }"/>
	<input type="hidden" name="enrlNop" id="enrlNop" value="${vo.enrlNop }"/>
	<input type="hidden" name="declsCnt" id="declsCnt" value="${vo.declsCnt }"/>
	<input type="hidden" name="eduPrice" id="eduPrice" value="${vo.eduPrice }"/>
	<input type="hidden" name="prgrRatio" id="prgrRatio" value="${vo.prgrRatio }"/>
	<input type="hidden" name="attdRatio" id="attdRatio" value="${vo.attdRatio }"/>
	<input type="hidden" name="examRatio" id="examRatio" value="${vo.examRatio }"/>
	<input type="hidden" name="semiExamRatio" id="semiExamRatio" value="${vo.semiExamRatio }"/>
	<input type="hidden" name="forumRatio" id="forumRatio" value="${vo.forumRatio }"/>
	<input type="hidden" name="asmtRatio" id="asmtRatio" value="${vo.asmtRatio }"/>
	<input type="hidden" name="joinRatio" id="joinRatio" value="${vo.joinRatio }"/>
	<input type="hidden" name="etcRatio" id="etcRatio"/>
	<input type="hidden" name="etcRatio2" id="etcRatio2"/>
	<input type="hidden" name="etcRatio3" id="etcRatio3"/>
	<input type="hidden" name="etcRatio4" id="etcRatio4"/>
	<input type="hidden" name="etcRatio5" id="etcRatio5"/>
	<input type="hidden" name="dayStudyLimit" id="dayStudyLimit" value="${vo.dayStudyLimit }"/>
	<input type="hidden" name="etcNm" id="etcNm" value="${vo.etcNm }"/>
	<input type="hidden" name="etcNm2" id="etcNm2" value="${vo.etcNm2 }"/>
	<input type="hidden" name="etcNm3" id="etcNm3" value="${vo.etcNm3 }"/>
	<input type="hidden" name="etcNm4" id="etcNm4" value="${vo.etcNm4 }"/>
	<input type="hidden" name="etcNm5" id="etcNm5" value="${vo.etcNm5 }"/>
	<input type="hidden" name="onlnEduTm" id="onlnEduTm" value="${vo.onlnEduTm }"/>
	<input type="hidden" name="oflnEduTm" id="oflnEduTm" value="${vo.oflnEduTm }"/>
	<input type="hidden" name="oflnEduPlace" id="oflnEduPlace" value="${vo.oflnEduPlace }"/>
	<input type="hidden" name="scoreOpenDttm" id="scoreOpenDttm" value="${vo.scoreOpenDttm }"/>
	<input type="hidden" name="creTypeCd" id="creTypeCd" value="${vo.creTypeCd}"/>
	<input type="hidden" name="creDesc" id="creDesc" value="${vo.creDesc}"/>
	<input type="hidden" name="qrFileSn" id="qrFileSn" />
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.fileupload.js"></script>
<script type="text/JavaScript" src="/js/common_fileupload.js"></script>	
<script type="text/javascript">
	var creCnt = ${creCnt};
	var runCnt = 0;
	var endCnt = 0;

	var qrFile;
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
		for(i=1; i <= ${creCnt}; i++) {
			chgNopLimitYn(i);
			chgCreTerm(i);
			sumTotal(i);
		}
		
		qrFile = new $M.JqueryFileUpload({
			"varName"			: "qrFile",
			"files" 			: $.parseJSON('${vo.qrFileJson}'),
			"uploaderId"		: "QRuploader",
			"fileListId"		: "QRfiles",
			"progressId"		: "QRprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"infoUse"		: true,
			"uploadSetting"		: {
				'formData'		: { 'repository': 'CRS_CRE_CRS_QR',
	                                'organization' : "${USER_ORGCD}",
									'type'		: 'QR' }
			}
		});
	});

	function uploderclick(str) {
		$("#"+str).click();
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#createCourseFrom').attr("action","/mng/course/createCourse/"+cmd);
		$('#createCourseFrom').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		endCnt++;
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			parent.location.reload();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}


	/**
	 * 과정 개설 등록
	 */
	function addCreateCourse() {

		var error = 0;
		
  		for(i=0; i <= ${creCnt}; i++) {
			if(!validationCheck(i)) {
				error++;
				break;
			}
			if(!chkDuplicate(i)) {
				error++;
				break;
			}
		}

		if(!chkDuplicateMulti()){
			error++;
		}

  		if(error == 0) {
			setValue(runCnt);
			//첨부파일
			process("addCreateCourse");	// cmd
		}
	}

	//인원제한 able <-> disable
	function chgNopLimitYn(str){
		var nopLimitYn = $("#nopLimitYn_"+str+" option:selected").val();
		if(nopLimitYn == "Y"){
			$('#enrlNopArea_'+str).show();
		}else{
			$('#enrlNopArea_'+str).hide();
		}
	}

	function chgCreTerm(str) {
		var creTerm = $("#creTerm_"+str).val();
		if(creTerm.length == 1) creTerm = "00"+creTerm;
		else if(creTerm.length == 2) creTerm = "0"+creTerm;
		$("#creTerm_"+str).val(creTerm);
	}

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

	//날짜 사이의 간격 구하기
	function getGapDate() {
		var f = document.f;
		var from = f.serviceStart.value;
		var to = f.serviceEnd.value;
		if(from != "" && to != "") {
			var days = jsGetDays(chgDate(from),chgDate(to));

			f.serviceDay.value = days;

		}
	}

	function validationCheck(str) {

 		if($("#crsCreNm_"+str+" option:selected").val() == '') {		
			alert('과정을 선택하세요.');
			$("#crsCreNm_"+str).focus();
			return false;
		}
		
		if($("#enrlAplcStartDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.aplc"/>');
			$("#enrlAplcStartDttm_"+str).focus();
			return false;
		}
		if($("#enrlAplcEndDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.aplc"/>');
			$("#enrlAplcEndDttm_"+str).focus();
			return false;
		}

		if('${courseVO.crsOperType}' == 'R'){
			if($("#auditEndDttm_"+str).val() == '') {
				alert('<spring:message code="course.message.createcourse.input.Audit"/>');
				$("#auditEndDttm_"+str).focus();
				return false;
			}
		}

		if($("#enrlStartDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edu"/>');
			$("#enrlStartDttm_"+str).focus();
			return false;
		}
		
		var enrlStartDate = $("#enrlStartDttm_"+str).val();
		var enrlEendDate = $("#enrlEndDttm_"+str).val();
		var endDate = $("#enrlAplcEndDttm_"+str).val();
		
		var enrlStartArray = enrlStartDate.split('.');
		var enrlEndArray = enrlEendDate.split('.');
		var endArray = endDate.split('.');

		var enrl_start_date = new Date(enrlStartArray[0], enrlStartArray[1]-1, enrlStartArray[2]);
		var enrl_end_date = new Date(enrlEndArray[0], enrlEndArray[1]-1, enrlEndArray[2]);
		var end_date = new Date(endArray[0], endArray[1]-1, endArray[2]);
		
		if(end_date > enrl_start_date) {
			alert("교육기간은 교육신청기간 종료날짜 이후에만 가능합니다.");
			$("#enrlStartDttm_"+str).focus();
			return false;
		}
				
		if($("#enrlEndDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edu"/>');
			$("#enrlEndDttm_"+str).focus();
			return false;
		}
		
		if($("#scoreOpenDttm_"+str).val() == '') {
			alert('성적 열람 시작일을 입력하세요.');
			$("#scoreOpenDttm_"+str).focus();
			return false;
		}
		
		if($("#cpltScore_"+str).val() == '') {
			alert('수료점수를 입력하세요.');
			$("#cpltScore_"+str).focus();
			return false;
		}

		if($("#onlnEduTm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edutm"/>');
			$("#onlnEduTm_"+str).focus();
			return false;
		}
		
		if($("#creTypeCd_"+str).val() == 'OF' || $("#creTypeCd_"+str).val() == 'BL') {
			if($("#oflnEduPlace_"+str).val() == '') {
				alert('<spring:message code="course.message.createcourse.input.duration.eduplace"/>');
				$("#oflnEduPlace_"+str).focus();
				return false;
			}
		}

		<c:if test="${courseVO.crsOperMthd eq 'BL'}">
		if($("#oflnStartDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.offedu"/>');
			$("#oflnStartDttm_"+str).focus();
			return false;
		}
		if($("#oflnEndDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.offedu"/>');
			$("#oflnEndDttm_"+str).focus();
			return false;
		}
		if($("#oflnEduTm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edutm"/>');
			$("#oflnEduTm_"+str).focus();
			return false;
		}
		if($("#oflnEduPlace_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.eduplace"/>');
			$("#oflnEduPlace_"+str).focus();
			return false;
		}
		</c:if>
		<c:if test="${courseVO.crsOperType eq 'S'}">
		if($("#enrlDaycnt_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.enrldaycnt"/>');
			$("#enrlDaycnt_"+str).focus();
			return false;
		}
		</c:if>

		var cpltScore = parseInt(nvlNumber($("#cpltScore_"+str).val()),10);
		if(cpltScore == '') {
			alert('<spring:message code="course.message.createcourse.input.completescore"/>');
			$("#cpltScore_"+str).focus();
			return false;
		}
		if(cpltScore > 100 || cpltScore < 0) {
			alert('<spring:message code="course.message.createcourse.alert.completescore"/>');
			$("#cpltScore_"+str).focus();
			return;
		}

		var nopLimitYn = $("#nopLimitYn_"+str+" option:selected").val();
		if(nopLimitYn == "Y"){
			if($("#enrlNop_"+str).val() == '') {
				alert('<spring:message code="course.message.createcourse.input.limitnum"/>');
				$("#enrlNop_"+str).focus();
				return false;
			}
		}
		if($("#declsCnt_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.declscnt"/>');
			$("#declsCnt_"+str).focus();
			return false;
		}
/* 		if($("#eduPrice_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.edufee"/>');
			$("#eduPrice_"+str).focus();
			return false;
		} */

		if($("#dayStudyLimit_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.daystudylimit"/>');
			$("#dayStudyLimit_"+str).focus();
			return false;
		}
 
 		var prgrRatio = parseInt(nvlNumber($("#prgrRatio_"+str).val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio_"+str).val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio_"+str).val()),10);
		var semiExamRatio = parseInt(nvlNumber($("#semiExamRatio_"+str).val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio_"+str).val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio_"+str).val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio_"+str).val()),10);
		
		var etcRatio = parseInt(nvlNumber($("#etcRatio_"+str).val()),10);
		var etcRatio2 = parseInt(nvlNumber($("#etcRatio2_"+str).val()),10);
		var etcRatio3 = parseInt(nvlNumber($("#etcRatio3_"+str).val()),10);
		var etcRatio4 = parseInt(nvlNumber($("#etcRatio4_"+str).val()),10);
		var etcRatio5 = parseInt(nvlNumber($("#etcRatio5_"+str).val()),10);
				
		var totRatio = prgrRatio + attdRatio + examRatio + semiExamRatio + forumRatio + asmtRatio + joinRatio + etcRatio + etcRatio2 + etcRatio3 + etcRatio4 + etcRatio5;
		
		if(totRatio != 100) {
			alert('<spring:message code="course.message.createcourse.alert.totalscore"/>');
			return false;
		}
		
		return true;
	}

	function setValue(str) {
		$("#crsCreNm").val($("#crsCreNm_"+str+" option:selected").attr("value2"));
		$("#creYear").val($("#creYear_"+str).val());
		$("#creTerm").val($("#creTerm_"+str).val());
		$("#enrlAplcStartDttm").val($("#enrlAplcStartDttm_"+str).val());
		$("#enrlAplcEndDttm").val($("#enrlAplcEndDttm_"+str).val());
		$("#enrlStartDttm").val($("#enrlStartDttm_"+str).val());
		$("#enrlEndDttm").val($("#enrlEndDttm_"+str).val());
		$("#enrlDaycnt").val($("#enrlDaycnt_"+str).val());
		<c:if test="${courseVO.crsOperMthd eq 'BL'}">
		$("#oflnStartDttm").val($("#oflnStartDttm_"+str).val());
		$("#oflnEndDttm").val($("#oflnEndDttm_"+str).val());
		</c:if>
		<c:if test="${courseVO.crsOperMthd eq 'ON' || courseVO.crsOperMthd eq 'BL'}">
		$("#auditEndDttm").val($("#auditEndDttm_"+str).val());
		</c:if>
		<c:if test="${courseVO.crsOperMthd eq 'OF'}">
		$("#auditEndDttm").val("");
		</c:if>
		$("#cpltScore").val($("#cpltScore_"+str).val());
		$("#nopLimitYn").val($("#nopLimitYn_"+str+" option:selected").val());
		if($("#nopLimitYn_"+str+" option:selected").val() == "Y") {
			$("#enrlNop").val($("#enrlNop_"+str).val());
		} else {
			$("#enrlNop").val("0");
		}
		$("#nopLimitYn").val($("#nopLimitYn_"+str+" option:selected").val());
		$("#declsCnt").val($("#declsCnt_"+str).val());
		$("#eduPrice").val($("#eduPrice_"+str).val());
		$("#prgrRatio").val($("#prgrRatio_"+str).val());
		$("#attdRatio").val($("#attdRatio_"+str).val());
		$("#examRatio").val($("#examRatio_"+str).val());
		$("#semiExamRatio").val($("#semiExamRatio_"+str).val());
		$("#forumRatio").val($("#forumRatio_"+str).val());
		$("#asmtRatio").val($("#asmtRatio_"+str).val());
		$("#joinRatio").val($("#joinRatio_"+str).val());
		$("#etcRatio").val($("#etcRatio_"+str).val());
		$("#etcRatio2").val($("#etcRatio2_"+str).val());
		$("#etcRatio3").val($("#etcRatio3_"+str).val());
		$("#etcRatio4").val($("#etcRatio4_"+str).val());
		$("#etcRatio5").val($("#etcRatio5_"+str).val());
		$("#etcNm").val($("#etcNm_"+str).val());
		$("#etcNm2").val($("#etcNm2_"+str).val());
		$("#etcNm3").val($("#etcNm3_"+str).val());
		$("#etcNm4").val($("#etcNm4_"+str).val());
		$("#etcNm5").val($("#etcNm5_"+str).val());
		$("#dayStudyLimit").val($("#dayStudyLimit_"+str).val());
		$("#onlnEduTm").val($("#onlnEduTm_"+str).val());
		$("#oflnEduTm").val($("#oflnEduTm_"+str).val());
		$("#oflnEduPlace").val($("#oflnEduPlace_"+str).val());
		$("#scoreOpenDttm").val($("#scoreOpenDttm_"+str).val());
		$("#creTypeCd").val($("#creTypeCd_"+str).val());
		$("#creDesc").val($("#creDesc_"+str).val());
		var _qrFile = qrFile.getFileSnAll();
		$("#qrFileSn").val(_qrFile);
	}

	function setAuditDttm(str, gep) {
		var endDttm = $("#enrlEndDttm_"+str).val().replace(".","").replace(".","");
		if(isEmpty(endDttm)) {
			alert("<spring:message code="course.message.createcourse.input.duration.enddate"/>");
			return;
		}
		var auditDttm = addDateFormatStr(getIntervalMonth(endDttm, gep, 1));
		$("#auditEndDttm_"+str).val(auditDttm);
	}

	function sumTotal(creCnt){
		var prgrRatio = parseInt(nvlNumber($("#prgrRatio_"+creCnt).val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio_"+creCnt).val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio_"+creCnt).val()),10);
		var semiExamRatio = parseInt(nvlNumber($("#semiExamRatio_"+creCnt).val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio_"+creCnt).val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio_"+creCnt).val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio_"+creCnt).val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio_"+creCnt).val()),10);
		var etcRatio2 = parseInt(nvlNumber($("#etcRatio2_"+creCnt).val()),10);
		var etcRatio3 = parseInt(nvlNumber($("#etcRatio3_"+creCnt).val()),10);
		var etcRatio4 = parseInt(nvlNumber($("#etcRatio4_"+creCnt).val()),10);
		var etcRatio5 = parseInt(nvlNumber($("#etcRatio5_"+creCnt).val()),10);

		var totRatio = prgrRatio + attdRatio + semiExamRatio + examRatio + forumRatio + asmtRatio + joinRatio + etcRatio + etcRatio2 + etcRatio3 + etcRatio4 + etcRatio5;

		$("#totRatio_"+creCnt).val(totRatio);

	}

	function chkDuplicate(str){
		var chkCrsCreNm = "";
		var chkCreYear= "";
		var chkCreTerm= "";
		var arCrsCreNm = "";
		var arCreYear= "";
		var arCreTerm= "";
		var dupCrsCreNm = false;
		var dupCreYear= false;
		var dupCreTerm= false;

<c:forEach items="${createCourseList}" var="item" varStatus="status">
	<c:if test="${status.count != 1}">
		chkCrsCreNm += "|";
		chkCreYear += "|";
		chkCreTerm += "|";
	</c:if>
		chkCrsCreNm += "${item.crsCreNm}";
		chkCreYear += "${item.creYear}";
		chkCreTerm += "${item.creTerm}";
</c:forEach>

		arCrsCreNm = chkCrsCreNm.split("|");
		arCreYear = chkCreYear.split("|");
		arCreTerm = chkCreTerm.split("|");


		for(var i=0; i < arCrsCreNm.length; i++){
			if($("#crsCreNm_"+str).val() == arCrsCreNm[i] ){
				dupCrsCreNm = true;
			}
			if($("#creYear_"+str).val() == arCreYear[i] ){
				dupCreYear = true;
			}
			if($("#creTerm_"+str).val() == arCreTerm[i] ){
				dupCreTerm = true;
			}
		}
		if(dupCrsCreNm == true && dupCreYear == true && dupCreTerm == true){
			alert('<spring:message code="course.message.createcourse.validation1"/>');
			return false;
		}

		return true;
	}

	function chkDuplicateMulti(){
		var dupCrsCreNm = false;
		var dupCreYear= false;
		var dupCreTerm= false;
		for(i=1; i <= ${creCnt}; i++) {
			for(j=1; j <= ${creCnt}; j++) {
				if(i != j){
					if($("#crsCreNm_"+i).val() == $("#crsCreNm_"+j).val()){
						dupCrsCreNm = true;
					}
					if($("#creYear_"+i).val() == $("#creYear_"+j).val()){
						dupCreYear = true;
					}
					if($("#creTerm_"+i).val() == $("#creTerm_"+j).val()){
						dupCreTerm = true;

					}
				}
			}
		}
		if(dupCrsCreNm == true && dupCreYear == true && dupCreTerm == true){
			alert('<spring:message code="course.message.createcourse.validation2"/>');
			return false;
		}
		return true;
	}
	
	var selectBoxChange = function(value) {
		$("#crsCd").val(value);
		$("#creTerm_0").val($("#crsCreNm_0 > option:selected").attr("value3"));
		$("#creTypeCd_0").val($("#crsCreNm_0 > option:selected").attr("value4"));
		$("#eduPrice_0").val($("#crsCreNm_0 > option:selected").attr("value5"));
		$("#onlnEduTm_0").val($("#crsCreNm_0 > option:selected").attr("value6"));
		$("#cpltScore_0").val($("#crsCreNm_0 > option:selected").attr("value7"));
		//오프라인 강의실 박스
	    var operType = $("#creTypeCd_0").val();
	    if (operType == 'OF' || operType == 'BL') {
	      $('#oflnEduPlace_0').show();
	      $('#qrInput').show();
	      $("#addEtc").show();
	    } else {
	      $('#oflnEduPlace_0').hide();
	      $('#qrInput').hide();
	      $("#addEtc").hide();
	    }
		
	}
	
	//기타 성적 input
	var etcNum = 1;
	$('#addEtc').click(function() {
		if(etcNum == 1) {
			var str ="";
			str += '<li style="float:left;margin-right:20px;height:35px;">';
			str += '<div class="input-group">';
			str += '<input type="text" style="font-align:right;width:60px;" maxlength="200" name="etcNm_0" id="etcNm_0" class="form-control input-sm" value="기타1"/>'
			str += '<input type="text" style="font-align:right;width:50px;" maxlength="3" name="etcRatio_0" id="etcRatio_0" class="inputNumber form-control input-sm" value="" onkeyup="isChkMaxNumber(this,100);sumTotal(0);"/>'
			str += '<a class="etcRemove" href="#">(삭제)</a>'
			str += '</div>';
			str += '</li>';
			$('#ratioList').append(str);
			etcNum++;
		} else if(etcNum < 6) {
			var str ="";
			str += '<li style="float:left;margin-right:20px;height:35px;">';
			str += '<div class="input-group">';
			str += '<input type="text" style="font-align:right;width:60px;" maxlength="200" name="etcNm'+etcNum+'_0" id="etcNm'+etcNum+'_0" class="inputNumber form-control input-sm" value="기타'+etcNum+'"/>'
			str += '<input type="text" style="font-align:right;width:50px;" maxlength="3" name="etcRatio'+etcNum+'_0" id="etcRatio'+etcNum+'_0" class="inputNumber form-control input-sm" value="" onkeyup="isChkMaxNumber(this,100);sumTotal(0);"/>'
			str += '<a class="etcRemove" href="#">(삭제)</a>'
			str += '</div>';
			str += '</li>';
			$('#ratioList').append(str);
			etcNum++;
		} else {
			alert("최대 5개까지 가능합니다.")
		}
		$('.etcRemove').on('click', function () {
			$(this).prev().prev().prev().remove();
			$(this).prev().prev().remove(); 
	        $(this).prev().remove(); 
	        $(this).remove ();
	        if(etcNum > 1){
	        etcNum--;
	        }
	        sumTotal(0);
	    });
	})
	

</script>