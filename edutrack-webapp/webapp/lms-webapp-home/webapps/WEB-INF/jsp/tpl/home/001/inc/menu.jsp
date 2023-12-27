<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils"%><%@
page import="egovframework.edutrack.comm.service.SysMenuMemService"%><%@
page import="egovframework.edutrack.modules.org.menu.service.OrgMenuVO"%><%@
page import="egovframework.edutrack.comm.util.web.UserBroker"%><%@
page import="java.util.List"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
String authGrpCd = UserBroker.getUserType(request);
String orgCd = UserBroker.getUserOrgCd(request);
if("".equals(authGrpCd)) authGrpCd = "GUEST";
SysMenuMemService service = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
OrgMenuVO menuVO = service.getOrgHomeMenuList(orgCd, authGrpCd);
request.setAttribute("menuVO",menuVO);
%>
				<c:set var="chkedmenu" value=""/>
				<c:forEach items="${menuVO.subList}" var="item" varStatus="status">
					<c:if test="${item.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
					<c:forEach items="${item.subList}" var="item1">
						<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
						<c:forEach items="${item1.subList}" var="item2">
							<c:if test="${item2.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>
				<c:forEach items="${menuVO.subList}" var="item">
					<c:if test="${item.menuCd eq chkedmenu}">
						<c:set var="menuName" value="${item.menuNm}"/>
						<c:forEach var="lang" items="${item.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName" value="${lang.menuNm}"/></c:if>
						</c:forEach>
				<h2 class="hidden-xs">${menuName}</h2>
				<ul class="nav nav-pills nav-stacked hidden-xs">
					<c:forEach items="${item.subList}" var="item1">
						<c:set var="menuName2" value="${item1.menuNm}"/>
						<c:forEach var="lang2" items="${item1.menuLangList}">
							<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
						</c:forEach>

						<c:choose>
							<c:when test="${item1.menuCd eq 'HM04002000' }">
								<c:if test="${MBR_APLC_USE eq 'Y' }">
					<li <c:if test="${item1.menuCd eq MENUCODE }">class="active"</c:if>><a href="<c:url value="/home/main/goMenuPage?mcd=${item1.menuCd}"/>">${menuName2}</a></li>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${item1.menuViewYn eq 'Y'}">
					<li <c:if test="${item1.menuCd eq MENUCODE }">class="active"</c:if>><a href="<c:url value="/home/main/goMenuPage?mcd=${item1.menuCd}"/>">${menuName2}</a></li>
								</c:if>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</ul>
					</c:if>
				</c:forEach>
