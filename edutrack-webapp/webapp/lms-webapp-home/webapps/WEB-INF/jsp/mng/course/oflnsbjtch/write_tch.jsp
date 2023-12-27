<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="oflnSbjTchVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#fff;">
	<form id="oflnSbjTchForm" name="oflnSbjTchForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }"/>
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }"/>
	<table class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row"><spring:message code="course.title.course.teacher"/></th>
			<td class="top">
			<c:if test="${gubun eq 'A'}">
				<select name="userNo" id="userNo" class="form-control input-sm">
					<option value="">강사선택</option>
				<c:forEach var="item" items="${teacherList}">
					<option value="${item.userNo}">${item.userNm}</option>
				</c:forEach>
				</select>
			</c:if>
			<c:if test="${gubun eq 'E'}">
				${oflnSbjTchVO.userNm}
				<input type="hidden" name="userNo" value="${vo.userNo }"/>
			</c:if>
			</td>
			<th class="top" scope="row"><spring:message code="course.title.decls"/></th>
			<td class="top">
			<c:if test="${gubun eq 'A'}">
				<select name="declsNo" id="declsNo"  class="form-control input-sm">
				<c:forEach var="item" items="${declsList}">
					<option value="${item.declsNo}">${item.declsNo}</option>
				</c:forEach>
				</select>
			</c:if>
			<c:if test="${gubun eq 'E'}">
				${oflnSbjTchVO.declsNo}
				<input type="hidden" name="declsNo" value="${vo.declsNo }"/>
			</c:if>
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="course.title.teacher.edutime"/></th>
			<td>
				<input type="text" style="width:50px;text-align:right;" dispName="<spring:message code="course.title.teacher.edutime"/>" maxlength="3" dataType="number" isNull="Y" lenCheck="3" name="lecTm" value="${vo.lecTm }" onfocus="this.select()" class="inputNumber form-control input-sm" id="lecTm"/>
			</td>
			<th scope="row"><spring:message code="course.title.subject.lecture.type"/></th>
			<td>
				<select name="lecKindCd" id="lecKindCd" class="form-control input-sm">
				<c:forEach var="item" items="${lecKindList}">
					<option value="${item.codeCd}" <c:if test="${item.codeCd eq oflnSbjTchVO.lecKindCd }">selected</c:if>>${item.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</table>

	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
			<a href="#" onclick="addTeacher()" class="btn btn-sm btn-primary"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<a href="#" onclick="editTeacher()" class="btn btn-sm btn-primary"><spring:message code="button.edit"/></a>
			<a href="#" onclick="delTeacher()" class="btn btn-sm btn-warning"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="#" onclick="parent.modalBoxClose()" class="btn btn-sm btn-default"><spring:message code="button.close"/></a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	$(document).ready(function() {

	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.oflnSbjTchForm)) return;
		$('#oflnSbjTchForm').attr("action","/mng/course/ofln/sbjTch/" + cmd);
		$('#oflnSbjTchForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.addCreateFrame.subWorkFrame.listTch();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
			parent.modalBoxClose();
		}
	}

	/**
	 * 강사 등록
	 */
	function addTeacher() {
		var userNo = $("#userNo > option:selected").val();
		var declsNo = $("#declsNo > option:selected").val();
		if(userNo == '') {
			alert('강사를 선택해 주세요.');
			return;
		}
		if(declsNo == '') {
			alert('분반을 선택해 주세요.');
			return;
		}
		process("addTch");	// cmd
	}

	/**
	 * 강사 수정
	 */
	function editTeacher() {
		process("editTch");	// cmd
	}

	/**
	 * 강사 삭제
	 */
	function delTeacher() {
		var f = document.oflnSbjTchForm;
		if(confirm('강사를 삭제하려고 합니다.\n\n강사의 강의 정보가 모두 삭제됩니다.\n\n삭제 하시겠습니까?')) {
			process("removeTch");	// cmd
		} else {
			return;
		}
	}


	function changeTeacher() {
		var tchInfo = {};
		<c:forEach var="item" items="${teacherList}">
		tchInfo['${item.userNo}'] = '${item.lecfeePayCritCd}/${item.tchPosCd}';
		</c:forEach>

		var userNo = oflnSbjTchFormoflnSbjTchVO_userNo.getSelectedValue();

		var tchStr = tchInfo[userNo].split("/");

		var lecfeePayCritCd = tchStr[0];
		var tchPosCd = tchStr[1];
		//alert(lecfeePayCritCd + "::" + tchPosCd);
		if(!isEmpty(lecfeePayCritCd)) {
			$("#tchLvlCd").val(lecfeePayCritCd);
		}
		if(!isEmpty(tchPosCd)) {
			$("#tchPosCd").val(tchPosCd);
		}

	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>