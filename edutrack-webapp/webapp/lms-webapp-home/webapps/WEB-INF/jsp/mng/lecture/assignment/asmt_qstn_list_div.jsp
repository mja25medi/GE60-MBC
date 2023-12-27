<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="assignmentSibListVO" value="${assignmentSibListVO}"/>
			<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:50px;">
					<col style="width:auto">
					<col style="width:50px;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.question"/></th>
						<th scope="col"><spring:message code="common.title.manage"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${assignmentSibListVO}" varStatus="status">
					<tr>
						<td class="text-right">${status.count}</td>
						<td class="wordbreak"><a href="javascript:viewQstn('${item.asmtSubSn}');" >${item.asmtTitle}</a></td>
						<td class="text-center">
							<c:if test="${assignmentVO.regYn eq 'Y' }">-</c:if>
							<c:if test="${assignmentVO.regYn ne 'Y' }">
							<a href="javascript:editQstn('${item.asmtSubSn}');" class="btn btn-primary btn-sm"><spring:message code="button.manage"/></a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty assignmentSibListVO}">
					<tr>
						<td colspan="3"><spring:message code="lecture.message.assignment.question.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>

	<script type="text/javascript">
	/**
	 * 과제 등록 완료
	 */
	function registerAsmt() {
		<c:if test="${empty assignmentSibListVO}">
		alert("<spring:message code="lecture.message.assignment.question.nodata"/>");
		return;
		</c:if>
		process("registerAsmt");	// cmd
	}

	/**
	 * 과제 등록 완료 취소
	 */
	function registerCancelAsmt() {
		if(parseInt(document.assignmentForm["sendCnt"].value,10) > 0) {
			alert("<spring:message code="lecture.message.assignment.alert.cancelregist"/>");
			return;
		}
		process("registerCancelAsmt");	// cmd
	}	
	</script>