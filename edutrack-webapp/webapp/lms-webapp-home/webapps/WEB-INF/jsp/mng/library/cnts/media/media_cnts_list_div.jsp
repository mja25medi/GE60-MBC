<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibMediaCntsList" value="${clibMediaCntsList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

							<ul class="list-group wordbreak">
							<c:forEach var="item" items="${clibMediaCntsList}">
								<li class="list-group-item">
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
										<c:if test="${item.uldStsCd eq 'complete' }">
										<a href="javascript:preview('${item.cntsCd}','${item.uldStsCd}')" class="btn btn-xs btn-default"><spring:message code="button.preview"/></a>
										<a href="javascript:contentsShare('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="common.title.share"/></a>
										</c:if>

									</span>
									<div class="small">
										<spring:message code="common.title.regdate"/> : <meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/> |
										<spring:message code="common.title.stats"/> : <c:if test="${item.uldStsCd eq 'upload'}"><spring:message code="library.title.contents.stats.upload"/></c:if><c:if test="${item.uldStsCd eq 'complete'}"><spring:message code="library.title.contents.stats.channel"/></c:if>
										<c:if test="${item.uldStsCd eq 'complete'}"><i class="fa fa-check-circle fa-1x"></i></c:if>
										<c:if test="${item.uldStsCd eq 'upload'}"><i class="fa fa-refresh fa-spin fa-1x"></i></c:if>
										| <spring:message code="common.title.tag"/> : ${item.cntsTag }
									</div>
									<div style="clear:both"></div>
								</li>
							</c:forEach>
							<c:if test="${empty clibMediaCntsList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
							</c:if>
							</ul>

							<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
