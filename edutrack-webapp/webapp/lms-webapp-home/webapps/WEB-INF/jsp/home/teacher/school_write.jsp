<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="gubun" value="${gubun}" />
<c:set var="tchSchsVO" value="${vo}"/>
<fmt:formatDate value="${now}" pattern="yyyy" var="year" />
<fmt:formatDate value="${now}" pattern="MM" var="month" />
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>

<mhtml:home_body>
		<mhtml:home_location />
		<form id="tchSchsForm" name="tchSchsForm" onsubmit="return false" >
		<input type="hidden" name="idCheck" id="idCheck" value="N" />
		<input type="hidden" name="userNo"  id="userNo" value="${vo.userNo}"/>
		<input type="hidden" name="schsSn"  id="schsSn" value="${vo.schsSn}"/>
		<input type="hidden" name="entrgradDt"  id="entrgradDt" value="${vo.entrgradDt}"/>

		<ul class="nav nav-tabs" id="tabMenu">
			<li><a href="<c:url value="/home/user/editFormUser"/>"><spring:message code="user.title.userinfo.user.info"/></a></li>
			<li class="active"><a href="#none"><spring:message code="user.title.userinfo.teacher.into"/></a></li>
		</ul>

		<div style="width:100%;">
			<div style="float:left;">
				<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.school"/></h5>
			</div>
		</div>

		<table class="table table-bordered wordbreak" style="margin-top:30px;">
			<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.teacher.manage"/></caption>
			<colgroup>
				<col width="18%"/>
				<col width="32%"/>
				<col width="18%"/>
				<col width="32%"/>
			</colgroup>
			<tr height="35">
				<th class="top" scope="row"><span style="color:red;">* </span><label for="entrDt_year"><spring:message code="teacher.title.teacherinfo.school.regdttm"/></label></th>
				<td class="top">
					<div class="input-group">
						<select name="entrDt_year" id="entrDt_year" class="form-control input-sm" style="width:100px;float:left;">
							<c:forEach var="i" begin="0" end="${year-1900}">
								<c:choose>
									<c:when test="${fn:substring(vo.entrDt,0,4) eq (year-i)}"><option selected value="${year-i}">${year-i}</option></c:when>
									<c:otherwise><option value="${year-i}">${year-i}</option></c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<select name="entrDt_mon" id="entrDt_mon" class="form-control input-sm" style="width:80px;float:left;">
							<c:forEach var="i" begin="1" step="1" end="12">
								<c:if test="${i < 10 }"><c:set var="i" value="0${i}" /></c:if>
								<c:if test="${gubun eq 'A' }">
									<c:choose>
										<c:when test="${month eq i }"><option selected value="${ i }">${ i }</option></c:when>
										<c:otherwise><option value="${ i }">${ i }</option></c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${gubun eq 'E' }">
									<c:choose>
										<c:when test="${fn:substring(vo.entrDt,5,7) eq i }"><option selected value="${ i }">${ i }</option></c:when>
										<c:otherwise><option value="${ i }">${ i }</option></c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</td>
				<th class="top" scope="row"><span style="color:red;">* </span><label for="gradDt_year"><spring:message code="teacher.title.teacherinfo.school.entrgraddt"/></label></th>
				<td class="top">
					<select name="gradDt_year" id="gradDt_year" class="form-control input-sm" style="width:100px;float:left;">
						<c:forEach var="i" begin="0" end="${year-1900}">
							<c:choose>
								<c:when test="${fn:substring(vo.gradDt,0,4) eq (year-i)}"><option selected value="${year-i}">${year-i}</option></c:when>
								<c:otherwise><option value="${year-i}">${year-i}</option></c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<select name="gradDt_mon" id="gradDt_mon" class="form-control input-sm" style="width:80px;float:left;">
						<c:forEach var="i" begin="1" step="1" end="12">
							<c:if test="${i < 10 }"><c:set var="i" value="0${i}" /></c:if>
							<c:if test="${gubun eq 'A' }">
								<c:choose>
									<c:when test="${month eq i }"><option selected value="${ i }">${ i }</option></c:when>
									<c:otherwise><option value="${ i }">${ i }</option></c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${gubun eq 'E' }">
								<c:choose>
									<c:when test="${fn:substring(vo.gradDt,5,7) eq i }"><option selected value="${ i }">${ i }</option></c:when>
									<c:otherwise><option value="${ i }">${ i }</option></c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr height="35">
				<th class="top" scope="row"><span style="color:red;">* </span><label for="schlNm"><spring:message code="teacher.title.teacherinfo.school.schlnm"/></label></th>
				<td class="top">
					<input type="text"  maxlength="50" lenCheck="50" name="schlNm" id="schlNm" class="form-control input-sm" isNull="N" dispName="<spring:message code="teacher.title.teacherinfo.school.schlnm"/>" style="width:200px;"/>
				</td>
				<th class="top" scope="row"><span style="color:red;">* </span><label for="schlSubj"><spring:message code="teacher.title.teacherinfo.school.schlsubj"/></label></th>
				<td class="top">
					<input type="text"  maxlength="50" lenCheck="50" name="schlSubj" id="schlSubj" class="form-control input-sm" isNull="N" dispName="<spring:message code="teacher.title.teacherinfo.school.schlsubj"/>" style="width:200px;"/>
				</td>
			</tr>
			<tr height="35">
				<th class="top" scope="row"><span style="color:red;">* </span><label for="locatNm"><spring:message code="teacher.title.teacherinfo.school.locatnm"/></label></th>
				<td class="top">
					<input type="text"  maxlength="50" lenCheck="50" name="locatNm" id="locatNm"  class="form-control input-sm" isNull="N" dispName="<spring:message code="teacher.title.teacherinfo.school.locatnm"/>" style="width:200px;"/>
				</td>
				<th class="top" scope="row"><span style="color:red;">* </span><spring:message code="teacher.title.teacherinfo.school.graddiv"/></th>
				<td class="top">
					<label style="font-weight: normal;"><input type="radio" name="gradDiv" id="gradDivY" value="Y" style="border:0" <c:if test="${vo.gradDiv eq 'Y' }"> checked</c:if> /> <spring:message code="teacher.title.teacherinfo.school.graddiv_y"/></label>
					<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="gradDiv" id="gradDivN" value="N" style="border:0" <c:if test="${vo.gradDiv eq 'N' }"> checked</c:if> /> <spring:message code="teacher.title.teacherinfo.school.graddiv_n"/></label>
				</td>
			</tr>
		</table>

		<div class="text-right">
			<c:if test="${gubun eq 'A'}">
			<a href="javascript:addSchool()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			</c:if>
			<c:if test="${gubun eq 'E'}">
			<a href="javascript:editSchool()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			<a href="javascript:delSchool()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
			</c:if>
			<a href="/home/tch/info/editForm" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
		</div>
		<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {

	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#tchSchsForm').attr("action" ,"/home/tch/schs/" + cmd);
		$('#tchSchsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location = "/home/tch/info/editForm";
		} else {
			// 비정상 처리
		}
	}

	function addSchool(){
		var entrDt = $("#entrDt_year").val()+$("#entrDt_mon").val();
		var gradDt = $("#gradDt_year").val()+$("#gradDt_mon").val();
		$("#entrgradDt").val(entrDt+gradDt);

		if(validate(document.tchSchsForm) ==false) return ;

		if(!$("#gradDivY").is(":checked") && !$("#gradDivN").is(":checked") ){
			alert("<spring:message code="teacher.message.teacherinfo.alert.graddiv"/>");
			return;
		}
		process("addSchool");	// cmd
	}

	function editSchool(){
		var entrDt = $("#entrDt_year").val()+$("#entrDt_mon").val();
		var gradDt = $("#gradDt_year").val()+$("#gradDt_mon").val();
		$("#entrgradDt").val(entrDt+gradDt);

		if(validate(document.tchSchsForm) ==false) return ;

		if(!$("#gradDivY").is(":checked") && !$("#gradDivN").is(":checked") ){
			alert("<spring:message code="teacher.message.teacherinfo.alert.graddiv"/>");
			return;
		}
		process("editSchool");	// cmd
	}

	function delSchool(){
		process("delSchool");	// cmd
	}

</script>
</mhtml:home_body>
</mhtml:home_html>