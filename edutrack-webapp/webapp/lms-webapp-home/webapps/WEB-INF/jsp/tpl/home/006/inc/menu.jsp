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
String pageCd = request.getParameter("pageCd");
request.setAttribute("menuVO",menuVO);
request.setAttribute("pageCd",pageCd);
%>
				<c:set var="chkedmenu" value=""/>
				<c:set var="threeDepth" value=""/>
				
				<c:forEach items="${menuVO.subList}" var="item" varStatus="status">
					<c:if test="${item.menuCd eq MENUCODE}">
						<c:set var="chkedmenu" value="${item.menuCd}"/>
					</c:if>
					<c:forEach items="${item.subList}" var="item1">
						<c:if test="${item1.menuCd eq MENUCODE}">
							<c:set var="chkedmenu" value="${item.menuCd}"/>
						</c:if>
						<c:set var="item2" value="${item1.subList}"/>
						<c:forEach items="${item1.subList}" var="item2">
							<c:set var="threeDepth" value="${item1.subList }"/>
							<c:if test="${item2.menuCd eq MENUCODE}">
								<c:set var="chkedmenu" value="${item.menuCd}"/>
							</c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>
				<c:forEach items="${menuVO.subList}" var="item">
					<c:if test="${item.menuCd eq chkedmenu}">
						<c:set var="menuName" value="${item.menuNm}"/>
						<c:forEach var="lang" items="${item.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName" value="${lang.menuNm}"/></c:if>
						</c:forEach>
	                    <div class="subMenu">
	                        <ul class="menu">
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
	                    </div>
					</c:if>
				</c:forEach>
				
                <h3 class="subMenu_title">${MENUNAME }</h3>
                
                <!-- 3dpethMenu -->
               	<ul class="tabs mb20 four_list" id="threeDepthMenu" style="display:none">
               		<c:forEach var="subList" items="${threeDepth}">
               			<c:if test="${subList.parMenuCd eq MENUCODE }">
               				<script type="text/javascript">
               					$('#threeDepthMenu').show();
               				</script>
               				<c:set var="menuName" value="${subList.menuNm}"/>
							<c:forEach var="lang3" items="${subList.menuLangList}">
								<c:if test="${LOCALEKEY eq lang3.langCd}"><c:set var="menuName" value="${lang3.menuNm}"/></c:if>
							</c:forEach>
							<li <c:if test="${subList.optnCtgrCd2 eq pageCd}">class="active"</c:if>><a href="${subList.menuUrl}" title="${menuName }">${menuName }</a></li>
	                	</c:if>
	                </c:forEach>
               	</ul>
