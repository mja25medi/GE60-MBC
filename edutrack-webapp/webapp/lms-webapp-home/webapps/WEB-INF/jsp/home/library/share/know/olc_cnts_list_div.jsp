<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibShareOlcCntsList" value="${clibShareOlcCntsList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

							<ul class="list-group">
							<c:forEach var="item" items="${clibShareOlcCntsList}">
								<li class="list-group-item wordbreak">
									<c:if test="${not empty item.thumbFileSn && item.thumbFileSn > 0}">
									<div style="width:140px;float:left;">
										<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" style="width: 130px;" alt="${item.cntsNm}"/>
									</div>
									</c:if>
									<font size="4.5">${item.cntsNm}</font>
									<span class="pull-right" style="text-align: center;">
										<a href="javascript:previewOlc('${item.userNo}', '${item.cntsCd}', '${item.cntsNm} }')" class="btn btn-xs btn-default"><spring:message code="button.view"/></a>
									</span>

									<div class="small">
										<spring:message code="common.title.regdate"/> : <meditag:dateformat type="8" delimeter="." property="${item.regDttm}"/> |
										<spring:message code="common.title.writer.name"/> : ${item.regNm } |
										<spring:message code="common.title.tag"/> : ${item.cntsTag } |
										<spring:message code="library.title.contents.ccl"/> : <meditag:orgcodename code="${item.cclCd}" category="CCL_CD" orgCd="${USER_ORGCD }"/>
									</div>
									<div style="clear:both"></div>
								</li>
							</c:forEach>
							<c:if test="${empty clibShareOlcCntsList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
							</c:if>
							</ul>

							<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>