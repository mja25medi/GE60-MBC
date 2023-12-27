<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="panel panel-default">
					<div class="panel-heading">
						조회건수 : ${apiLogList.size() } 건
					</div>
					<div class="panel-body" style="padding:0px;">
						<div id="listApiLog" style="width:100%; overflow:auto; background-color:white; padding:5px; line-height:20px; margin-top:5px;">
							<table class="table table-bordered wordbreak">
								<caption class="sr-only">조회리스트</caption>
								<colgroup>
									<col style="width:10%" />
									<col style="width:30%" />
									<col style="width:10%" />
									<col style="width:10%" />
									<col style="width:25%" />
									<col style="width:15%" />
								</colgroup>
								<thead>
										<tr>
											<th scope="col">순서</th>
											<th scope="col">메세지</th>
											<th scope="col">성공수</th>
											<th scope="col">실패수</th>
											<th scope="col">전송일</th>
											<th scope="col">전송시간</th>
										</tr>
									</thead>
								<tbody>
									<c:choose>
										<c:when test="${empty apiLogList}">
											<tr>
												<td colspan="6"><spring:message code="common.message.nodata"/></td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${apiLogList}" var="item" varStatus="status">
												<tr>
													<td class="text-center">${status.count }</td>
													<td class="text-center">${item.syncResultMsg}</td>
													<td class="text-center">${item.syncSuccessCnt}</td>
													<td class="text-center">${item.syncFailCnt}</td>
													<td class="text-center">
														<fmt:parseDate var="sendDate" value="${item.syncDate}" pattern="yyyyMMddHHmmss" />
														<fmt:formatDate value="${sendDate}" pattern="yyyy.MM.dd(E) HH:mm:ss"/>
													</td>
													<td class="text-center">${item.syncTime/1000}초</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>

<script type="text/javascript">
	$(document).ready(function() {

	});

</script>
