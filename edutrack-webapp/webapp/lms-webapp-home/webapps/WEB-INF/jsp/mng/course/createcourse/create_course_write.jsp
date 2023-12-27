<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="courseVO" value="${courseVO}"/>
<c:set var="createCourseVO" value="${vo}"/>
	<c:forEach begin="1" step="1" end="${creCnt}" var="creId" varStatus="status">
	<form name="Input_${creId}" id="Input_${creId}" onsubmit="return false">
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
			<th scope="row"><span style="color:red;">* </span><label for="crsCreNm_${creId}"><spring:message code="course.title.course.name"/></label></th>
			<td>
				<input type="text" maxlength="100" name="crsCreNm_${creId}" id="crsCreNm_${creId}" class="form-control input-sm" value="${courseVO.crsNm}">
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="creYear"><spring:message code="course.title.createcourse.creyear"/></label>/<label for="creTerm"><spring:message code="course.title.createcourse.creterm"/></label></th>
			<td>
				<div class="input-group" style="float:left;">
					<input type="text" style="width:55px;" maxlength="4" name="creYear_${creId}" id="creYear_${creId}" class="inputNumber form-control input-sm" value="${createCourseVO.creYear}" onkeyup="isChkNumber(this)"/>
				</div>
				<span style="float:left;line-height:30px;padding-left:5px;padding-right:10px;"><spring:message code="common.title.year"/></span>
				<div class="input-group" style="float:left;">
					<input type="text" style="width:50px;" maxlength="50" name="creTerm_${creId}" id="creTerm_${creId}" class="form-control input-sm" value="${creTerm}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.duration.aplc"/></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcStartDttm_${creId}" id="enrlAplcStartDttm_${creId}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcStartDttm_${creId}')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcEndDttm_${creId}" id="enrlAplcEndDttm_${creId}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcEndDttm_${creId}')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlAplcStartDttm_${creId}" name2="enrlAplcEndDttm_${creId}" />
			</td>
			<c:if test="${courseVO.crsOperType eq 'R'}">
			<th scope="row"><span style="color:red;">* </span>
				<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'ON' }"><spring:message code="course.title.createcourse.method.online"/></c:if>
				<c:if test="${courseVO.crsOperMthd eq 'OF' }"><spring:message code="course.title.createcourse.method.offline"/></c:if>
				<spring:message code="course.title.createcourse.duration.edu"/>
			</th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlStartDttm_${creId}" id="enrlStartDttm_${creId}" class="inputDate form-control input-sm""/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm_${creId}')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlEndDttm_${creId}" id="enrlEndDttm_${creId}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm_${creId}')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlStartDttm_${creId}" name2="enrlEndDttm_${creId}" />
			</td>
			</c:if>
			<c:if test="${courseVO.crsOperType eq 'S'}">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.eduday"/></th>
			<td>
				<input type="hidden" name="enrlEndDttm_${creId}" id="enrlEndDttm_${creId}"/>
				<input type="hidden" name="enrlStartDttm_${creId}" id="enrlStartDttm_${creId}"/>
				<div style="float: left;width: 20%;">
				<input type="text" style="text-align:right;width:50px;float:left;" maxlength="3" name="enrlDaycnt_${creId}" id="enrlDaycnt_${creId}" class="inputNumber form-control input-sm" />
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
					<input type="text" maxlength="10" name="oflnStartDttm_${creId}" id="oflnStartDttm_${creId}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('oflnStartDttm_${creId}')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="oflnEndDttm_${creId}" id="oflnEndDttm_${creId}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('oflnEndDttm_${creId}')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="oflnStartDttm_${creId}" name2="oflnEndDttm_${creId}" />
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
						<input type="text" maxlength="10" name="auditEndDttm_${creId}" id="auditEndDttm_${creId}" class="inputDate form-control input-sm"/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('auditEndDttm_${creId}')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="auditEndDttm_${creId}" />
				</div>
				<div style="float:left;margin-left:10px;">
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('${creId}','1')" ><spring:message code="button.month.1"/></button>
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('${creId}','3')" ><spring:message code="button.month.3"/></button>
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('${creId}','6')" ><spring:message code="button.month.6"/></button>
					<button class="btn btn-default btn-sm" onclick="setAuditDttm('${creId}','12')" ><spring:message code="button.month.12"/></button>
				</div>
				</c:if>
				<c:if test="${courseVO.crsOperType eq 'S'}">
					 <input type="hidden" name="auditEndDttm_${creId}" id="auditEndDttm_${creId}"/>
					 <span class='red'> - </span>
				</c:if>
			</td>
		</tr>
		</c:if>
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.edutime"/></th>
				<c:choose>
					<c:when test="${courseVO.crsOperMthd eq 'ON'}">
			<td colspan="3">
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="onlnEduTm_${creId}" id="onlnEduTm_${creId}" class="inputNumber form-control input-sm" value="${courseVO.eduTm}" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
				<input type="hidden" name="oflnEduTm_${creId}" id="oflnEduTm_${creId}" value=""/>
				<input type="hidden" name="oflnEduPlace_${creId}" id="oflnEduPlace_${creId}" value=""/>
			</td>
					</c:when>
					<c:when test="${courseVO.crsOperMthd eq 'OF'}">
			<td>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="oflnEduTm_${creId}" id="oflnEduTm_${creId}" class="inputNumber form-control input-sm" value="${courseVO.oflnEduTm}" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
				<input type="hidden" name="onlnEduTm_${creId}" id="onlnEduTm_${creId}" value=""/>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.place"/></th>
			<td>
				<input type="text" name="oflnEduPlace_${creId}" id="oflnEduPlace_${creId}" class="form-control input-sm" value="${courseVO.oflnEduPlace}" />
			</td>
					</c:when>
					<c:otherwise>
			<td>
				<span style="float:left;line-height:30px;"><spring:message code="course.title.createcourse.cyber"/> : </span>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="onlnEduTm_${creId}" id="onlnEduTm_${creId}" class="inputNumber form-control input-sm" value="${courseVO.eduTm}" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>

				<span style="float:left;margin-left:20px;line-height:30px;"><spring:message code="course.title.createcourse.offline"/> : </span>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="oflnEduTm_${creId}" id="oflnEduTm_${creId}" class="inputNumber form-control input-sm" value="${courseVO.oflnEduTm}" onkeyup="isChkNumber(this)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.place"/></th>
			<td>
				<input type="text" name="oflnEduPlace_${creId}" id="oflnEduPlace_${creId}" class="form-control input-sm" value="${courseVO.oflnEduPlace}" />
			</td>
					</c:otherwise>
				</c:choose>

		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="cpltScore"><spring:message code="course.title.course.completescore"/></label></th>
			<td>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" maxValue="100" name="cpltScore_${creId}" id="cpltScore_${creId}" class="inputNumber form-control input-sm" value="${courseVO.cpltScore}" onkeyup="isChkMaxNumber(this,100)"/>
				<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.score"/></span>
			</td>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.course.stdcnt"/></th>
			<td>
				<div style="float:left">
				<select name="nopLimitYn_${creId}" id="nopLimitYn_${creId}" style="width:120px;" onChange="chgNopLimitYn('${creId}');" class="form-control input-sm">
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
				<div id="enrlNopArea_${creId}" style="float:left:display:hidden;">
					<input type="text" style="float:left;width:50px;text-align:right;" maxlength="5" name="enrlNop_${creId}" id="enrlNop_${creId}" class="inputNumber form-control input-sm" value="${courseVO.eduNop}" onkeyup="isChkNumber(this)"/>
					<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.cnt.user"/></span>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.decls.cnt"/></th>
			<td>
				<input type="text" style="width:40px;text-align:right;" maxlength="3" name="declsCnt_${creId}" id="declsCnt_${creId}" value="1" class="inputNumber form-control input-sm" onkeyup="isChkNumber(this)"/>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="eduPrice"><spring:message code="course.title.course.edufee"/></label></th>
			<td>
				<div class="input-group">
				<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
					<input type="text" style="width:120px;text-align:right;" maxlength="10" name="eduPrice_${creId}" id="eduPrice_${creId}"  class="inputNumber form-control input-sm"  value="${courseVO.eduPrice }" onkeyup="isChkNumber(this)"/>
				<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
				</div>
			</td>
		</tr>

		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="eduPrps"><spring:message code="course.title.course.scoreratio"/> (%)</label></th>
			<td colspan="3">
				<ul style="list-style-type:none;padding:0px;width:100%">
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="prgrRatio_${creId}"><spring:message code="course.title.course.ratio.progress"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="prgrRatio_${creId}" id="prgrRatio_${creId}" class="inputNumber form-control input-sm" value="${courseVO.prgrRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('${creId}');" <c:if test="${courseVO.crsOperMthd eq 'OF'}">disabled="disabled"</c:if>/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="examRatio_${creId}"><spring:message code="course.title.course.ratio.exam"/></label></span>
							<input type="text" style="font-align:right;width:50px;" maxlength="3" name="examRatio_${creId}" id="examRatio_${creId}" class="inputNumber form-control input-sm" value="${courseVO.examRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('${creId}');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="asmtRatio_${creId}"><spring:message code="course.title.course.ratio.asmt"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="asmtRatio_${creId}" id="asmtRatio_${creId}" class="inputNumber form-control input-sm" value="${courseVO.asmtRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('${creId}');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="forumRatio_${creId}"><spring:message code="course.title.course.ratio.forum"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="forumRatio_${creId}" id="forumRatio_${creId}" class="inputNumber form-control input-sm" value="${courseVO.forumRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('${creId}');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="joinRatio_${creId}"><spring:message code="course.title.course.ratio.join"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="joinRatio_${creId}" id="joinRatio_${creId}" class="inputNumber form-control input-sm" value="${courseVO.joinRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('${creId}');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="etcRatio_${creId}"><spring:message code="course.title.course.ratio.etc"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="etcRatio_${creId}" id="etcRatio_${creId}" class="inputNumber form-control input-sm" value="${courseVO.etcRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('');"/>
						</div>
					</li>
					<li style="float:left;margin-right:20px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="attdRatio_${creId}"><label for="attdRatio"><spring:message code="course.title.course.ratio.attend"/></label></span>
							<input type="text" style="text-align:right;width:50px;" maxlength="3" name="attdRatio_${creId}" id="attdRatio_${creId}" class="inputNumber form-control input-sm" value="${courseVO.attdRatio}" onkeyup="isChkMaxNumber(this,100);sumTotal('${creId}');" <c:if test="${courseVO.crsOperMthd eq 'ON'}">disabled="disabled"</c:if>/>
						</div>
					</li>
					<li style="float:left;margin-right:30px;height:35px;">
						<div class="input-group">
							<span style="float:left;margin-right:5px;padding-top:5px;"><label for="totRatio_${creId}"><spring:message code="course.title.course.ratio.total"/></label></span>
							<input type="text" name="totRatio_${creId}" id="totRatio_${creId}" class="inputNumber form-control input-sm" style="text-align:right;width:50px;background-color:#fff;" readonly="readonly"/>
						</div>
					</li>
				</ul>
				<%-- <p><spring:message code="course.message.course.alert.ratio"/></p> --%>
			</td>
		</tr>
	</table>
	</form>
	<c:if test="${!status.last}">
	<hr style="border:0;border-bottom:2px dashed #ccc;background-color:#999;"/>
	</c:if>
	</c:forEach>
	<div style="width:100%;">
		<div style="float:left; height:40px; line-height:30px;">
			<b><spring:message code="course.message.course.alert.ratio"/></b>
		</div>
	</div>
	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary" onclick="addCreateCourse()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary" onclick="editCreateCourse()"><spring:message code="button.add"/></button>
			<button class="btn btn-warning" onclick="delCreateCourse()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default" onclick="closeWriteArea()"><spring:message code="button.close"/></button>
	</div>

	<form id="createCourseFrom" name="createCourseFrom" onsubmit="return false">
	<input type="hidden" name="crsCd" id="crsCd" value="${vo.crsCd }"/>
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
	<input type="hidden" name="forumRatio" id="forumRatio" value="${vo.forumRatio }"/>
	<input type="hidden" name="asmtRatio" id="asmtRatio" value="${vo.asmtRatio }"/>
	<input type="hidden" name="joinRatio" id="joinRatio" value="${vo.joinRatio }"/>
	<input type="hidden" name="etcRatio" id="etcRatio" value="${vo.etcRatio }"/>
	<input type="hidden" name="onlnEduTm" id="onlnEduTm" value="${vo.onlnEduTm }"/>
	<input type="hidden" name="oflnEduTm" id="oflnEduTm" value="${vo.oflnEduTm }"/>
	<input type="hidden" name="oflnEduPlace" id="oflnEduPlace" value="${vo.oflnEduPlace }"/>
	<input type="submit" value="submit" style="display:none" />
	</form>


<script type="text/javascript">
	var creCnt = ${creCnt};
	var runCnt = 0;
	var endCnt = 0;

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
		for(i=1; i <= ${creCnt}; i++) {
			chgNopLimitYn(i);
			chgCreTerm(i);
			sumTotal(i);
		}

	});

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
			if(endCnt >= ${creCnt})	{
				var msg = "<spring:message code="course.message.createcourse.added.confirm"/>";
				if(confirm(msg)){
					listCreateCourse('${createCourseVO.crsCd}','${createCourseVO.crsOperType }');
				} else {
					//alert("<spring:message code="course.message.createcourse.added"/>");
					parent.listCourse();
					parent.closeWriteArea();
				}
			}
		} else {
			// 비정상 처리
		}
	}


	/**
	 * 과정 개설 등록
	 */
	function addCreateCourse() {
		var error = 0;
		for(i=1; i <= ${creCnt}; i++) {
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
			for(i=1; i <= ${creCnt}; i++) {
				runCnt++;
				setValue(runCnt);
				process("addCreateCourse");	// cmd
			}
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
		if($("#crsCreNm_"+str).val() == '') {
			aleret('<spring:message code="course.message.createcourse.input.coursename"/>');
			$("#crsCreNm_"+str).focus();
			return false;
		}
		if($("#creYear_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.creyear"/>');
			$("#creYear_"+str).focus();
			return false;
		}
		if($("#creTerm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.creterm"/>');
			$("#creTerm_"+str).focus();
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

		<c:if test="${courseVO.crsOperType eq 'R'}">
		if($("#enrlStartDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edu"/>');
			$("#enrlStartDttm_"+str).focus();
			return false;
		}
		if($("#enrlEndDttm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edu"/>');
			$("#enrlEndDttm_"+str).focus();
			return false;
		}
		</c:if>
		<c:if test="${courseVO.crsOperMthd eq 'ON'}">
		if($("#onlnEduTm_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edutm"/>');
			$("#onlnEduTm_"+str).focus();
			return false;
		}
		</c:if>
		<c:if test="${courseVO.crsOperMthd eq 'OF'}">
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
		if($("#eduPrice_"+str).val() == '') {
			alert('<spring:message code="course.message.createcourse.input.edufee"/>');
			$("#eduPrice_"+str).focus();
			return false;
		}

		var prgrRatio = parseInt(nvlNumber($("#prgrRatio_"+str).val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio_"+str).val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio_"+str).val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio_"+str).val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio_"+str).val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio_"+str).val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio_"+str).val()),10);
		var totRatio = prgrRatio + attdRatio + examRatio + forumRatio + asmtRatio + joinRatio + etcRatio;
		if(totRatio != 100) {
			alert('<spring:message code="course.message.createcourse.alert.totalscore"/>');
			return false;
		}
		return true;
	}

	function setValue(str) {
		$("#crsCreNm").val($("#crsCreNm_"+str).val());
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
		$("#forumRatio").val($("#forumRatio_"+str).val());
		$("#asmtRatio").val($("#asmtRatio_"+str).val());
		$("#joinRatio").val($("#joinRatio_"+str).val());
		$("#etcRatio").val($("#etcRatio_"+str).val());
		$("#onlnEduTm").val($("#onlnEduTm_"+str).val());
		$("#oflnEduTm").val($("#oflnEduTm_"+str).val());
		$("#oflnEduPlace").val($("#oflnEduPlace_"+str).val());

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
		var forumRatio = parseInt(nvlNumber($("#forumRatio_"+creCnt).val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio_"+creCnt).val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio_"+creCnt).val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio_"+creCnt).val()),10);

		var totRatio = prgrRatio + attdRatio + examRatio + forumRatio + asmtRatio + joinRatio + etcRatio;

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

</script>