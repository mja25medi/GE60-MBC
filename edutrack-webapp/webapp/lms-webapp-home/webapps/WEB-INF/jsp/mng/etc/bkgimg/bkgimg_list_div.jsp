<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="itemList" value="${bkgImgList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

				<div class="row">
					<c:forEach var="item" items="${itemList}" varStatus="status">
					<div class="col-sm-6 col-md-3">
 						<div class="thumbnail" style="height:300px;">
 							<img src="/app/file/view2/${item.mainImgFileSn}" style="width:100%;height:220px;"/>
 							<div class="caption">
 								<h5 class="wordbreak">
      								<c:if test="${item.useYn eq 'Y'}"><i class="glyphicon glyphicon-ok-circle" style="color:skyblue"></i></c:if>
      								<c:if test="${item.useYn ne 'Y'}"><i class="glyphicon glyphicon-ban-circle" style="color:orange"></i></c:if>
      								${fn:substring(item.bkgrImgNm,0, 20)}<c:if test="${fn:length(item.bkgrImgNm) > 20 }">...</c:if>
      							</h5>
 								<p>
 									<a href="#none" onclick="preview('${item.mainImgFileSn}')" title="preview"><i class="glyphicon glyphicon-eye-open"></i></a>
 									<a href="javascript:bkgImgEdit('${item.bkgrImgSn}')" title="edit"><i class="fa fa-cog"></i></a>
 								</p>
 							</div>
 						</div>
					</div>
					</c:forEach>
					<c:if test="${empty itemList}">
					<div class="col-md-12">
						<div class="well">
							<<spring:message code="common.message.nodata"/>
						</div>
					</div>
					</c:if>
				</div>
				<meditag:paging pageInfo="${pageInfo}" funcName="bkgImgList"/>
