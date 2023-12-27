<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="assignmentVO" value="${vo}"/>
<c:set var="assignmentSendListVO" value="${assignmentSendListVO}"/>
<c:set var="pageInfo" value="${pageInfo}" />

			<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:30px;"/>
					<col style="width:50px;"/>
					<col style="width:auto;"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:60px">
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:200px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" name="stdChkAll" id="stdChkAll" style="border:0" onclick="checkAll()"/></th>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.sendyn"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.send.lastdate"/></th>
						<th scope="col">모사율</th>
						<th scope="col"><spring:message code="lecture.title.assignment.score"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.rateyn"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.rate"/>/<spring:message code="lecture.title.assignment.result"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${assignmentSendListVO}" varStatus="status">
					<tr>
						<td class="text-center"><input type="checkbox" name="selStd" value="${item.stdNo}" style="border:0"></td>
						<td class="text-right">${status.count}</td>
						<td>${item.userNm}</td>
						<td>${item.userId}</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${item.sendCnt > 0}">
									<font color="blue"><spring:message code="lecture.title.assignment.sendy"/></font>
								</c:when>
								<c:otherwise>
									<font color="blud"><spring:message code="lecture.title.assignment.sendn"/></font>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${not empty item.modDttm}">
									<meditag:dateformat type="1" delimeter="." property="${item.modDttm}"/>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>
						</td>
						<%--completeStatus 로 완료 여부 확인, 완료 시 링크 삽입/표시 --%>
						<td class="text-right">
							<c:if test="${item.completeStatus eq 'Y'}">
								<a href="http://183.111.234.121:8082/ckplus/copykiller.jsp?uri=${item.copyRatioUri}&property_id=4" target="_blank">${item.dispTotalCopyRatio}</a> 
							</c:if>
						</td>
						<td class="text-right">
							${item.score} <spring:message code="common.title.score"/>
						</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${item.rateYn eq 'Y'}">
									<spring:message code="lecture.title.assignment.stare.complete"/>
								</c:when>
								<c:otherwise>
									<spring:message code="lecture.title.assignment.stare.nocomplete"/>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="text-center">
							<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
								<div class="input-group" style="width:120px; margin-right: 5px;">
									<input type="text" name="score" style="text-align:right;" class="form-control input-sm inputNumber" onkeyup="isChkMaxNumber(this,'100')"  />
									<span class="input-group-addon" onclick='addScore("${status.index}")' style="cursor:pointer"><spring:message code="button.add"/></span>
								</div>
							</c:if>
							<c:if test="${item.sendCnt > 0 && assignmentVO.asmtTypeCd ne 'OFF' && assignmentVO.asmtSvcCd eq 'GEN'}">
								<a href="javascript:editRate('${item.stdNo}');" class="btn btn-primary btn-sm"><spring:message code="button.rate"/></a>
							</c:if>
							<c:if test="${assignmentVO.asmtTypeCd eq 'ON' && assignmentVO.asmtSvcCd eq 'CODE'}">
								<a href="javascript:editCodeRate('${item.stdNo}');" class="btn btn-primary btn-sm"><spring:message code="button.rate"/></a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty assignmentSendListVO}">
					<tr>
						<td colspan="9"><spring:message code="student.message.student.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<meditag:paging pageInfo="${pageInfo}" funcName="listStudent"/>

<script type="text/javascript">
	$(document).ready(function() {
		$(".inputNumber").inputNumber();
	});
</script>