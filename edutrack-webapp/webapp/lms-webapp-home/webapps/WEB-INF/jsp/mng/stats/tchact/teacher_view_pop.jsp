<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form onsubmit="return false" id="userInfoForm">
	<input type="hidden" name="cmd" />
	<input type="hidden" name="idCheck" id="idCheck" value="N" />
	<input type="hidden" name="userNo" value="${vo.userNo}"/>
	<input type="submit" value="submit" style="display:none" />

	<table summary="<spring:message code="teacher.title.teacherinfo.teacher.manage"/>" class="table table-bordered wordbreak" style="margin-top:30px;">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row"><label for="userId"><spring:message code="teacher.title.teacherinfo.tchctgrcd"/></label></th>
			<td class="top">
				${vo.tchCtgrNm}
			</td>
			<th class="top" scope="row"><spring:message code="teacher.title.teacherinfo.tchdivcd"/></th>
			<td class="top">
				${vo.tchDivNm}
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="crer"><spring:message code="teacher.title.teacherinfo.crer"/></label></th>
			<td colspan="3">
				${vo.crer}
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="major"><spring:message code="teacher.title.teacherinfo.major"/></label></th>
			<td colspan="3">
				${vo.major}
			</td>
		</tr>
	</table>

	<div class="text-right">
		<a href="javascript:javascript:tchEdit('${vo.userNo}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	</form>
	<br/>
	<form name="Search" id="Search" onsubmit="return false">
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.school"/></h5>
		</div>
	</div>
	<div id="schoolList" style="width:100%;float:left">
		<table summary="<spring:message code="teacher.title.teacherinfo.school"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"><spring:message code="teacher.message.school.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.writing"/></h5>
		</div>
	</div>
	<div id="writeList" style="width:100%;float:left;">
		<table summary="<spring:message code="teacher.title.teacherinfo.writing"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"><spring:message code="teacher.message.writing.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	var originOrgCd = '${vo.orgCd}';

	// 페이지 초기화
	$(document).ready(function() {
		listSchool();
	    listWriting();
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').find('input[name=cmd]').val(cmd);
		$("#reshAnsrFrm").attr("action","/mng/stats/tchAct/"+cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			parent.listPageing();
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
			{ "userNo": "${vo.userNo}", "isView":"Y" }
		);
	}

	function listWriting() {
		$('#writeList').load(
			cUrl("/mng/tch/writing/listWrite"),
			{"userNo": "${vo.userNo}", "isView":"Y"}
		);
	}

	function tchEdit(userNo) {
		document.location = "/mng/tch/info/editPop?userNo="+userNo;
	}

</script>
