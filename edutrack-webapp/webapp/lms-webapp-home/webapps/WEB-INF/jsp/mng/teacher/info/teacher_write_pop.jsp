<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}" />
<c:set var="usrUserInfoVO" value="${vo}"/>
<c:set var="tchInfoVO" value="${tchInfoVO}"/>
	<ul class="nav nav-tabs" id="tabMenu">
  		<li><a href="<c:url value="/mng/user/userInfo/viewUserPop?userNo=${usrUserInfoVO.userNo}"/>"><spring:message code="user.title.userinfo.user.info"/></a></li>
  		<li class="active"><a href="#none"><spring:message code="user.title.userinfo.teacher.into"/></a></li>
	</ul>

	<form id="userInfoForm" name="userInfoForm" onsubmit="return false">
	<input type="hidden" name="idCheck" id="idCheck" value="N" />
	<input type="hidden" name="userNo" value="${vo.userNo }" />
	<input type="hidden" name="orgCd" value="${vo.orgCd }"  />
	<input type="hidden" name="tchInfoVO.userNo" value="${usrUserInfoVO.userNo}" />

	<table summary="<spring:message code="teacher.title.teacherinfo.teacher.manage"/>" class="table table-bordered wordbreak" style="margin-top:30px;">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row"><span style="color:red;">* </span><label for="tchCtgrCd"><spring:message code="teacher.title.teacherinfo.tchctgrcd"/></label></th>
			<td class="top">
				<select name="tchInfoVO.tchCtgrCd" id="tchInfoVO.tchCtgrCd" class="form-control input-sm">
					<c:forEach var="item" items="${tchCtgrCdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.tchCtgrCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
			<th class="top" scope="row"><span style="color:red;">* </span><label for="tchDivCd"><spring:message code="teacher.title.teacherinfo.tchdivcd"/></label></th>
			<td class="top">
				<select name="tchInfoVO.tchDivCd" id="tchInfoVO.tchDivCd" class="form-control input-sm">
					<c:forEach var="item" items="${tchDivCdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.tchDivCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="crer"><spring:message code="teacher.title.teacherinfo.crer"/></label></th>
			<td colspan="3">
				<textarea name="tchInfoVO.crer" lenCheck="1000" dispName="<spring:message code="teacher.title.teacherinfo.crer"/>" isNull="N" style="height:80px;" id="crer" class="form-control input-sm" >${tchInfoVO.crer }</textarea> 
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="major"><spring:message code="teacher.title.teacherinfo.major"/></label></th>
			<td colspan="3">
				<textarea name="tchInfoVO.major" lenCheck="1000" dispName="<spring:message code="teacher.title.teacherinfo.major"/>" isNull="N" style="height:80px" id="major" class="form-control input-sm">${tchInfoVO.major }</textarea>
			</td>
		</tr>
	</table>

	<div class="text-right">
		<a href="javascript:addTeacher()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>	
	<br/>
	<form name="Search" id="Search" onsubmit="return false">
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.school"/></h5>
		</div>
		<div style="float:right">
			<button class="btn btn-primary btn-sm" onclick="addSchool()" ><spring:message code="button.write.teacher.school"/></button>
		</div>
	</div>
	<div id="schoolList" style="width:100%;float:left">
		<table summary="<spring:message code="teacher.title.teacherinfo.school"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:100px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:50px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/> </th>
					<th scope="col"><spring:message code="teacher.title.teacherinfo.school.graddiv"/></th>
					<th scope="col"><spring:message code="teacher.title.teacherinfo.school.schlnm"/></th>
					<th scope="col"><spring:message code="teacher.title.teacherinfo.school.schlsubj"/></th>
					<th scope="col"><spring:message code="teacher.title.teacherinfo.school.locatnm"/></th>
					<th scope="col"><spring:message code="teacher.title.teacherinfo.school.regdttm"/></th>
					<th scope="col"><spring:message code="teacher.title.teacherinfo.school.entrgraddt"/></th>
					<th scope="col"><spring:message code="common.title.edit"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="7"><spring:message code="teacher.message.school.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.writing"/></h5>
		</div>
		<div style="float:right">
			<button class="btn btn-primary btn-sm" onclick="addWriter()" ><spring:message code="button.write.teacher.writing"/></button>
		</div>
	</div>
	<div id="writeList" style="width:100%;float:left;">
		<table summary="<spring:message code="teacher.title.teacherinfo.writing"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:70px">
				<col style="width:100px;">
				<col style="width:auto">
				<col style="width:100px;">
				<col style="width:130px;">
				<col style="width:100px">
				<col style="width:50px">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="7"><spring:message code="teacher.message.writing.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	var originOrgCd = '${usrUserInfoVO.orgCd}';

	// 페이지 초기화
	$(document).ready(function() {
		listSchool();
	    listWriting();
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(validate(document.userInfoForm) ==false) return ;
		$('#userInfoForm').attr("action" ,"/mng/tch/info/" + cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			parent.listPageing();
			//parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	function addTeacher(){
		process("add");	// cmd
	}


	function listSchool() {
		$('#schoolList').load(
			cUrl("/mng/tch/schs/listSchool"),
			{ "userNo": "${usrUserInfoVO.userNo}" }
		);
	}

	function listWriting() {
		$('#writeList').load(
			cUrl("/mng/tch/writing/listWrite"),
			{ "userNo": "${usrUserInfoVO.userNo}"}
		);
	}

	function addSchool(){
		<c:if test="${gubun eq 'A'}">
			alert("<spring:message code="teacher.message.teacherinfo.school.empty"/>");
			return;
		</c:if>
		document.location = "/mng/tch/schs/addFormSchoolPop?userNo=${usrUserInfoVO.userNo}";
	}

	function addWriter(){
		<c:if test="${gubun eq 'A'}">
			alert("<spring:message code="teacher.message.teacherinfo.writing.empty"/>");
			return;
		</c:if>
		document.location = "/mng/tch/writing/addFormWritePop?userNo=${usrUserInfoVO.userNo}";
	}

	function editSchool(schsSn){
		document.location = "/mng/tch/schs/editFormSchoolPop?userNo=${usrUserInfoVO.userNo}${AMPERSAND}schsSn="+schsSn;
	}

	function editWriter(lecWritingsSn){
		document.location = "/mng/tch/writing/editFormWritePop?userNo=${usrUserInfoVO.userNo}${AMPERSAND}lecWritingsSn="+lecWritingsSn;
	}

</script>
