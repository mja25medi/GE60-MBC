<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

 				<div class="sub_title mt30">${infoTitle} 목록</div> 
				<div class="like-table" data-toggle="like-table">
					<div class="thead">
						<span class="th w8">종류</span>
						<span class="th w30">제목</span>
						<span class="th w8">제출일</span>
						<span class="th w8">점수</span>
						<span class="th w8">상세</span>
					</div>
					<ul class="tbody">
						<c:if test="${not empty dataList }">
							<c:forEach items="${dataList}" var="item" varStatus="status">
								<li class="tr">
									<dl class="td"><dt>종류</dt>
										<dd>
											<c:if test="${item.dataTypeDetail eq 'ON' }">온라인</c:if>
											<c:if test="${item.dataTypeDetail eq 'OFF' }">오프라인</c:if>
										</dd>
									</dl>
									<dl class="td txt-left"><dt>제목</dt><dd>${item.dataTitle }</dd></dl>
									<dl class="td">
										<dt>제출일</dt>
										<dd>
											<c:choose>
												<c:when test="${not empty item.dataScoreDttm}">
													<meditag:dateformat type="2" delimeter="." property="${item.dataScoreDttm }" />
												</c:when>
												<c:when test="${empty item.dataScoreDttm }">
													미제출
												</c:when>
											</c:choose>
										</dd>
									</dl>
									<dl class="td">
										<dt>점수</dt>
										<dd>${item.dataScore}점</dd>
									</dl>								
									<dl class="td">
										<dt>상세보기</dt>
										<dd>
											<c:if test="${item.dataTypeDetail eq 'ON' and not empty item.dataScoreDttm}">
												<button type="button" class="btn type1" onclick="javascript:viewDetailPop('${item.dataType}', '${item.dataSn }');">상세보기</button>
											</c:if>
										</dd>
									</dl>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<c:if test="${empty dataList }">
					<div class="no-list">등록된 정보가 없습니다.</div>
				</c:if>
