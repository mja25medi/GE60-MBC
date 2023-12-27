<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="vo" value="${vo}" />
<c:set var="examStareListVO" value="${examStareListVO}" />
<c:set var="pageInfo" value="${pageInfo}"/>

		<table summary="<spring:message code="lecture.title.exam.rate.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:30px;"/>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<c:if test="${vo.examTypeCd eq 'ON'}"><col style="width:auto;"/></c:if>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<c:if test="${vo.examTypeCd eq 'ON'}"><col style="width:auto;"/></c:if>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:150px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="text-center"><input type="checkbox" name="stdChkAll" id="stdChkAll" style="border:0" title="Select All" onclick="checkAll();"/></th>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<c:if test="${vo.examTypeCd eq 'ON'}"><th scope="col"><spring:message code="lecture.title.exam.agreeyn"/></th></c:if>
					<th scope="col"><spring:message code="lecture.title.exam.stareyn"/></th>
					<c:if test="${vo.examTypeCd eq 'ON'}"><th scope="col"><spring:message code="lecture.title.exam.staredate"/></th></c:if>
					<th scope="col"><spring:message code="lecture.title.exam.score"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.rateyn"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.rate"/>/<spring:message code="lecture.title.exam.result"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${examStareListVO}" varStatus="status">
				<tr>
					<td class="text-center"><input type="checkbox" name="selStd" value="${item.stdNo}"></td>
					<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
					<td>${item.userNm}</td>
					<td>${item.userId}</td>
					<c:if test="${vo.examTypeCd eq 'ON'}">
					<td class="text-center">
						<c:choose>
							<c:when test="${not empty item.agreeYn}">
								<spring:message code="lecture.title.exam.agreey"/>
							</c:when>
							<c:when test="${empty item.agreeYn}">
								<spring:message code="lecture.title.exam.agreen"/>
							</c:when>
						</c:choose>
					</td>
					</c:if>
					<td class="text-center">
						<c:choose>
							<c:when test="${item.stareCnt > 0 }">
								<c:choose>
									<c:when test="${empty item.endDttm}">
										<c:choose>
											<c:when test="${item.examTypeCd eq 'ON' }">
												<font color=\"blue\"><spring:message code="lecture.title.exam.starey"/>(<spring:message code="lecture.title.exam.stare.nocomplete"/>)</font>
											</c:when>
											<c:otherwise>
												<font color=\"blue\"><spring:message code="lecture.title.exam.starey"/></font>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${item.examTypeCd eq 'ON' }">
												<font color=\"blue\"><spring:message code="lecture.title.exam.starey"/>(<spring:message code="lecture.title.exam.stare.complete"/>)</font>
											</c:when>
											<c:otherwise>
												<font color=\"blue\"><spring:message code="lecture.title.exam.starey"/></font>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<font color=\"blud\"><spring:message code="lecture.title.exam.staren"/></font>
							</c:otherwise>
						</c:choose>
					</td>
					<c:if test="${vo.examTypeCd eq 'ON'}">
						<td class="text-center">
							<c:choose>
								<c:when test="${not empty item.endDttm }">
									<meditag:dateformat type="8" delimeter="." property="${item.endDttm}"/>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>
						</td>
					</c:if>
					<td class="text-right">
						<c:choose>
							<c:when test="${item.stareCnt > 0 }">
								<fmt:formatNumber value="${item.totGetScore}" pattern="#.#" minFractionDigits="1"/> <spring:message code="common.title.score"/>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td class="text-center">
						<c:choose>
							<c:when test="${item.stareCnt > 0 }">
								<c:if test="${item.rateYn eq 'Y'}"><spring:message code="lecture.title.exam.rateyn_y"/></c:if>
								<c:if test="${item.rateYn ne 'Y'}"><spring:message code="lecture.title.exam.rateyn_n"/></c:if>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td class="text-center">
						<!-- 온라인 시험의 경우, 제출 또는 임시 저장하여 수강생 답안 데이터가 있는 경우 평가 가능 -->
						<c:choose>
							<c:when test="${vo.examTypeCd eq 'OFF'}">
								<div class="input-group" style="width:120px;">
									<input type='text' name='score' onkeyup="isChkMaxNumber(this,'100')"  onblur="isChkScore(this,100);" style='text-align:right;size=7' maxlength="6" class="form-control input-sm inputNumber">
									<span class="input-group-addon" onclick="addScore(${status.index})" style="cursor:pointer"><spring:message code="button.add"/></span>
								</div>
							</c:when>
							<c:when test="${item.stareCnt > 0 && not empty item.endDttm && not empty item.stareAnss}">
								<a href="javascript:editRate('${item.stdNo}')" class="btn btn-info btn-sm"><spring:message code="button.rate"/></a>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty examStareListVO}">
				<tr>
					<td colspan="9"><spring:message code="lecture.message.exam.student.nodata"/></td>
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