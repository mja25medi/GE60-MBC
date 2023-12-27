<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="팀 등록">
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<br>
<form id="prjTeamForm" name="prjTeamForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" />
 	<input type="hidden" name="prjtSn" />
 	<input type="hidden" name="prjtTeamSn" />
	<table summary="<spring:message code="lecture.title.project.team.manage"/>" style="width:96%" class="table_dtl" align="center">
		<colgroup>
			<col style="width:40%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row"><spring:message code="lecture.title.project.team.name"/></th>
			<td class="top">
				<input type="text" style="width:250px" dispName="<spring:message code="lecture.title.project.team.name"/>" maxlength="100" isNull="N" lenCheck="100" name="prjtTeamNm" class="txt" id="prjtTeamNm"/>
			</td>
		</tr>
	</table>
	<table style="width:96%">
		<tr>
			<td>
				<meditag:buttonwrapper>
					<c:if test="${gubun eq 'A'}">
						<meditag:button value="button.add" title="button.add" func="addMbr()" />
					</c:if>
					<c:if test="${gubun eq 'E'}">
						<meditag:button value="button.edit" title="button.edit" func="editMbr()" />
					</c:if>
					<meditag:button value="button.close" title="button.close" func="parent.projectPopBox.close();" />
				</meditag:buttonwrapper>
			</td>
		</tr>
	</table>

	</form>
<script type="text/javascript">
	/**
	 * 신규팀 등록
	 */
	function addMbr() {
		process("add");
	}

	/**
	 * 팀 수정
	 */
	function editMbr() {
		process("edit");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjTeamForm').attr("action", "/mng/lecture/prjTeam/" + cmd);
		$('#prjTeamForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.subWorkFrame2.listMember();
			parent.projectPopBox.close();
		} else {
			// 비정상 처리
		}
	}

	</script>
	</mhtml:frm_body>
</mhtml:mng_html>