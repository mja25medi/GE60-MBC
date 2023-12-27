<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


				<div class="row">
					<c:forEach var="item" items="${tplList}" varStatus="status">
					<div class="col-sm-6 col-md-4">
 						<div class="thumbnail" style="height:300px;">
 							<div style="width:100%;height:220px;">
 								<c:if test="${item.mainImgFileSn > 0}">
   								<a href="#none" onclick="preview('${item.mainImgFileSn}')"><img src="/app/file/view2/${item.mainImgFileSn}" style="max-width:100%;max-height:220px;"/></a>
   								</c:if>
   								<c:if test="${item.mainImgFileSn == 0}">
   								<a href="#none"><img src="/img/common/image.jpg" style="max-width:100%;max-height:220px;"/></a>
   								</c:if>
   							</div>
    						<div class="caption">
      							<h5>
      								${item.tplNm}</h5>
      							<p style="font-size:12px;color:gray;" class="text-right"><a href="javascript:tplEdit('${item.tplCd}')"><i class="glyphicon glyphicon-wrench"></i></a></p>
    						</div>
 						</div>
					</div>
					</c:forEach>
					<c:if test="${empty tplList}">
					<div class="col-md-12">
						<div class="well">
							<spring:message code="common.message.nodata"/>
						</div>
					</div>
					</c:if>
				</div>
				<meditag:paging pageInfo="${pageInfo}" funcName="pageing"/>
