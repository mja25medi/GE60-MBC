<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="faqAtclVO"  value="${vo}" />
<c:set var="faqList" value="${faqList}" />
<style type="text/css">
	/* 파일 영역 스타일 재선언 */
	.fileEm{
		max-width: 30%;  
		text-overflow:ellipsis; 
		overflow:hidden; 
		white-space:nowrap;
		display: inline-block;
		vertical-align: top;
	}
</style>
				<div class="post_list">
					<c:if test="${not empty faqList}">
					<div class="panel-group" id="accordion">
						<c:forEach var="item" items="${faqList}" varStatus="status">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#faqcnts_${status.count}" onclick="resizeCnts('${status.count}')">
										${item.atclTitle}
									</a>
								</h4>
							</div>
							<div id="faqcnts_${status.count}" class="panel-collapse collapse">
								<div class="panel-body faqcntsBody wordbreak" id="faqcntsBody_${status.count}">
									${item.atclCts}
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
					</c:if>
					<c:if test="${empty faqList}">
					<div class="well">
						<spring:message code="board.message.faq.nodata"/>
					</div>
					</c:if>
				</div>
					<div class="row" style="margin-bottom:20px;">
						<div class="col-lg-12">
							<meditag:paging pageInfo="${pageInfo}" funcName="list"/>
						</div>
					</div>
