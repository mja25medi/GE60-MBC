<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

				<ul class="list-group">
					<c:forEach var="item" items="${tchList}" varStatus="status">
					<li class="list-group-item">
						<h4 class="list-group-item-heading">
							<a href="javascript:setTch('${item.declsNo}','${item.userNo}','${item.userNm}');">
								${item.userNm}
							</a>
							<span class="pull-right">
								<a href="#" onclick="javascript:editTeacher('${item.userNo}','${item.declsNo}');" class="btn btn-xs btn-primary"><spring:message code="button.edit"/></a>
							</span>
						</h4>
						<p class="list-group-item-text">
						 	<spring:message code="course.title.decls"/> : ${item.declsNo} |
							<spring:message code="course.title.course.edutime"/> : ${item.lecTm}
						</p>
					</li>
					</c:forEach>
					<c:if test="${empty tchList}">
					<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
					</c:if>
				</ul>