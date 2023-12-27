<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
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
      								<c:if test="${item.shareDivCd eq 'KNOW' }">[<spring:message code="olc.title.knowledge.share"/>]</c:if>
									<c:if test="${item.shareDivCd eq 'CNTS' }">[<spring:message code="olc.title.contents.share"/>]</c:if>
									${item.cntsNm}

									<span class="pull-right" style="text-align: center;">
										<a href="javascript:previewOlc('${item.userNo}', '${item.cntsCd}', '${item.cntsNm} }')" class="btn btn-xs btn-default"><spring:message code="button.preview"/></a>
										<a href="javascript:delOlcShare('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="button.delete"/></a>
									</span>

									<div class="small">
										<spring:message code="common.title.regdate"/> : <meditag:dateformat type="8" delimeter="." property="${item.regDttm}"/> |
										<spring:message code="common.title.applicant.name"/> : <a href="#_none" onclick="userInfo('${item.regNo}')">${item.regNm }</a> |
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