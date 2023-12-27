<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibOlcCntsList" value="${clibOlcCntsList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

							<ul class="list-group">
							<c:forEach var="item" items="${clibOlcCntsList}">
								<li class="list-group-item wordbreak">
									<c:if test="${not empty item.thumbFileSn && item.thumbFileSn > 0}">
									<div style="width:140px;float:left;">
										<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" style="width: 130px;" alt="${item.cntsNm}"/>
									</div>
									</c:if>
									<c:if test="${item.useYn eq 'Y'}"><i class="glyphicon glyphicon-ok-circle" style="color:skyblue"></i></c:if>
      								<c:if test="${item.useYn ne 'Y'}"><i class="glyphicon glyphicon-ban-circle" style="color:orange"></i></c:if>
      								${item.cntsNm}&nbsp;
									<a href="javascript:cntsEdit('${item.cntsCd}')" class="">
										<%-- <spring:message code="button.edit"/> --%>
										<i class="fa fa-cog"></i>
									</a>
									<span class="pull-right" style="text-align: center;">
										<a href="javascript:pageManage('${item.cntsCd}','${item.cntsNm}')" class="btn btn-xs btn-default"><spring:message code="library.title.contents.olc.page.manage"/></a>
										<c:if test="${item.cntsCnt > 0}">
										<a href="javascript:preview('${item.userNo}', '${item.cntsCd}', '${item.cntsNm} }')" class="btn btn-xs btn-default"><spring:message code="button.preview"/></a>
										<a href="javascript:contentsShare('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="common.title.share"/></a>
										</c:if>
									</span>
									<div class="small">
										<spring:message code="common.title.regdate"/> : <meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/>
										| <spring:message code="common.title.tag"/> : ${item.cntsTag }
									</div>
									<div style="clear:both"></div>
								</li>
							</c:forEach>
							<c:if test="${empty clibOlcCntsList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
							</c:if>
							</ul>

							<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>