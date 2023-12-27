<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
	            <table class="tbl" id="studentTable" style="width:100%">
	                <colgroup>
	                    <col width="20%">
	                    <col width="auto">
	                    <col width="30%">
	                </colgroup>
	                <thead>
	                    <tr>
	                    	<th>회차명</th>
		                    <th>수강 과정명</th>
		                    <th>자격증 신청</th>
	                	</tr>
	                </thead>
	                <tbody>
	                	<c:if test="${not empty stuList}">
						<c:forEach items="${stuList}" var="item">
							<jsp:useBean id="now" class="java.util.Date" />
							<fmt:parseDate value="${item.scoreOpenStartDttm }" pattern="yyyyMMddHHmmss" var="startDate" />
							<fmt:parseDate value="${item.scoreOpenEndDttm }" pattern="yyyyMMddHHmmss" var="endDate" />
							<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss" var="nowDate" />             <%-- 오늘날짜 --%>
							<fmt:formatDate value="${startDate}" pattern="yyyyMMddHHmmss" var="openDate"/>       
							<fmt:formatDate value="${endDate}" pattern="yyyyMMddHHmmss" var="closeDate"/>        
							<c:set var="isOpen" value="${openDate < nowDate && closeDate > nowDate}" />
	                    	<tr>
	                        	<td class="txt-center">${item.crsNm }</td>
	                        	<td class="txt-center">${item.crsCreNm }</td>
	                       		<td class="txt-center">
								<c:if test="${isOpen == true}">
									<c:choose>
										<c:when test="${item.certPassYn eq 'Y' && empty item.certSts }">
											<a href="javascript:certAplcStudent('${item.stdNo }', '${item.crsCreCd}')" class="btn solid dark">자격증 신청 </a>
										</c:when>
										<c:when test="${item.certPassYn eq 'Y'  && item.certSts eq 'I' }">
											승인 대기 중
												<a href="javascript:cancelCertStudent('${item.stdNo }', '${item.crsCreCd }')" class="btn solid dark">신청 취소</a>
                                        		<div class="mt8">
                                        			신청일 : <meditag:dateformat type="8" delimeter="." property="${item.certAplcDttm }"/> 
                                        		</div>
										</c:when>
										<c:when test="${item.certPassYn eq 'Y' && item.certSts eq 'S'}">
											승인 완료
												<a href="javascript:printCerti('${item.crsCreCd }','${item.stdNo }')" class="btn solid dark">자격증 출력</a>
										</c:when>
										<c:when test="${item.certPassYn eq 'Y'  && item.certSts eq 'N' }">
											자격증 신청 취소
										</c:when>
										<c:when test="${item.certPassYn eq 'Y'  && item.certSts eq 'F' }">
											자격증 승인 취소 사유 : ${item.certFailReason}
										</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${isOpen == false}">
									자격증 신청 기간이 아닙니다.
								</c:if>
	                        </td>
	                    </tr>
	                    </c:forEach>
	                    </c:if>
	                    <c:if test="${empty stuList}">
	                    	 <tr>
	                    	 	<td colspan="3">
	                    			해당 회차에 수강한 과정이 없습니다. 
	                    		</td>
	                    	</tr>
	                    </c:if>
	                </tbody>
	            </table>
	
</body>
