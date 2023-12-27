<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="pageInfo" value="${pageInfo}"/>

				<div class="row">
					<c:forEach var="item" items="${bannerList}" varStatus="status">
					<div class="col-sm-6 col-md-3">
 						<div class="thumbnail" style="height:200px;">
 							<img src="/app/file/view2/${item.bnnrImgSn}" style="width:100%;height:110px;"/>
    						<div class="caption">
      							<h5>
      								<c:if test="${item.openYn eq 'Y'}"><i class="glyphicon glyphicon-ok-circle" style="color:skyblue"></i></c:if>
      								<c:if test="${item.openYn ne 'Y'}"><i class="glyphicon glyphicon-ban-circle" style="color:orange"></i></c:if>
      								${item.bnnrNm}
      							</h5>
      							<p>
      								<a href="javascript:bnnrEdit('${item.bnnrSn}')" ><i class="fa fa-cog"></i></a>
      							</p>
    						</div>
 						</div>
					</div>
					</c:forEach>
					<c:if test="${empty bannerList}">
					<div class="col-md-12">
						<div class="well">
							<spring:message code="common.message.nodata"/>
						</div>
					</div>
					</c:if>
				</div>
				<meditag:paging pageInfo="${pageInfo}" funcName="bannerList"/>
