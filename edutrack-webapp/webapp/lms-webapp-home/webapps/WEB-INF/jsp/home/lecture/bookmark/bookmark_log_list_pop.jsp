<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>


						<div class="like-table" data-toggle="like-table">
							<div class="thead">
								<span class="th w25">과정명</span>
								<span class="th w25">회차명</span>
								<span class="th w20">학습일시</span>
								<span class="th w10">접속기기</span>
								<span class="th w10">브라우저</span>
								<!-- <span class="th w25">접속IP</span> -->
							</div>
							<ul class="tbody">
								<c:if test="${not empty unitLogList }">
									<c:forEach var="item" items="${unitLogList }">
										<li class="tr">
											<dl class="td">
												<dt class="w25">과정명</dt>
												<dd>${item.crsCreNm }</dd>
											</dl>
											<dl class="td">
												<dt class="w25">회차명</dt>
												<dd>${item.unitNm }</dd>
											</dl>
											<dl class="td">
												<dt class="w20">학습일시</dt>
												<dd>
													<fmt:parseDate var="logDate" value="${item.loginDttm}"  pattern="yyyyMMddHHmmss"/>
													<fmt:formatDate value="${logDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
												</dd>
											</dl>
											<dl class="td">
												<dt class="w10">접속기기</dt>
												<dd>${item.deviceType }</dd>
											</dl>
											<dl class="td">
												<dt class="w10">브라우저</dt>
												<dd>${item.browserType }</dd>
											</dl>
										<%-- 	<dl class="td">
												<dt class="w20">접속IP</dt>
												<dd>${item.connIp }</dd>
											</dl> --%>
										</li>
									</c:forEach>
								</c:if>
							</ul>
							<c:if test="${empty unitLogList }">
								<div class="no-list">수강 기록이 없습니다.</div>
							</c:if>
						</div>