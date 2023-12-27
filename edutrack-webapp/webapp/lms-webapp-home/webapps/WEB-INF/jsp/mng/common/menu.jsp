<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="egovframework.edutrack.comm.service.SysMenuMemService"%>
<%@ page import="egovframework.edutrack.modules.system.menu.service.SysMenuVO"%>
<%@ page import="java.util.List"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%
	String authGrpCd = UserBroker.getMngType(request);
	if("".equals(authGrpCd)) authGrpCd = "GUEST";
	SysMenuMemService service = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	List<SysMenuVO> menuList = service.getMngMenuList(authGrpCd);
	request.setAttribute("menuList",menuList);
%>
			<c:url var="img_base" value="/img" />
			<c:set var="chkedmenu1" value=""/>
			<c:set var="chkedmenu2" value=""/>
			<c:forEach items="${menuDTO.subList}" var="item">
				<c:forEach items="${item.subList}" var="item1">
					<c:if test="${item1.menuCd eq MENUCODE || fn:length(menuDTO.subList) == 1}"><c:set var="chkedmenu1" value="${item.menuCd}"/></c:if>
					<c:forEach items="${item1.subList}" var="item2">
						<c:if test="${item2.menuCd eq MENUCODE}">
							<c:set var="chkedmenu1" value="${item.menuCd}"/>
							<c:set var="chkedmenu2" value="${item1.menuCd}"/>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:forEach>
			<ul id="leftMenu">
				<c:forEach items="${menuList}" var="item">
					<c:set var="menuName1" value="${item.menuNm}"/>
					<c:forEach var="lang" items="${item.menuLangList}">
						<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
					</c:forEach>
				<li class="main">
					<a href="#none" onclick="<c:if test="${not empty item.subList}">togleMenu('${item.menuCd}', '1');</c:if><c:if test="${not empty item.menuUrl}">goMenu('${item.menuCd}');</c:if>">
						<font style="<c:if test="${item.menuCd eq MENUCODE}">color:#0AA0C5;</c:if><c:if test="${item.menuCd ne MENUCODE}">color:white;</c:if>font-weight:bold">${menuName1}</font>
					</a>
					<c:if test="${not empty item.subList}">
					<ul class="submenu1" style="display:<c:if test="${chkedmenu1 ne item.menuCd}">none</c:if><c:if test="${chkedmenu1 eq item.menuCd}">block</c:if>" id="${item.menuCd}">
						<c:forEach items="${item.subList}" var="item1">
							<c:set var="menuName2" value="${item1.menuNm}"/>
							<c:forEach var="lang2" items="${item1.menuLangList}">
								<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
							</c:forEach>
						<li class="sub">
							<a href="#none" onclick="<c:if test="${not empty item1.subList}">togleMenu('${item1.menuCd}', '2');</c:if><c:if test="${not empty item1.menuUrl}">goMenu('${item1.menuCd}');</c:if>">
								<font style="<c:if test="${item1.menuCd eq MENUCODE}">color:yellow;font-weight:bold</c:if><c:if test="${item1.menuCd ne MENUCODE}">color:white</c:if>">${menuName2}</font>
							</a>
							<c:if test="${not empty item1.subList}">
							<ul class="submenu2" style="display:<c:if test="${chkedmenu2 ne item1.menuCd}">none</c:if><c:if test="${chkedmenu2 eq item1.menuCd}">block</c:if>" id="${item1.menuCd}">
								<c:forEach items="${item1.subList}" var="item2">
									<c:set var="menuName3" value="${item2.menuNm}"/>
									<c:forEach var="lang3" items="${item2.menuLangList}">
										<c:if test="${LOCALEKEY eq lang3.langCd}"><c:set var="menuName3" value="${lang3.menuNm}"/></c:if>
									</c:forEach>
								<li class="sub01">
									<c:if test="${not empty item2.menuUrl}"><a href="#none" onclick="goMenu('${item2.menuCd}');"></c:if>
										<font style="<c:if test="${item2.menuCd eq MENUCODE}">color:orange;font-weight:bold</c:if><c:if test="${item2.menuCd ne MENUCODE}">color:white</c:if>">${menuName3}</font>
									<c:if test="${not empty item2.menuUrl}"></a></c:if>
								</li>
								</c:forEach>
							</ul>
							</c:if>
						</li>
					</c:forEach>
					</ul>
					</c:if>
				</li>
				</c:forEach>
			</ul>


			<script type="text/javascript">
				function togleMenu(menu, lvl) {
					//$("#"+menu).slideToggle('show');
					//$(".submenu"+lvl).hide();
					//$("#"+menu).show();
					$(".submenu"+lvl).slideUp();
					$("#"+menu).slideDown();
				}

				function goMenu(menuCode) {
					document.location.href = generateUrl("/mng/main/goMenuPage", {"mcd":menuCode}) 
				}

				function showMenu() {
					$("#menuShowButton").hide();
					$("#leftMenu").show();
					$("#menuHideButton").show();
					$(".contents").attr("style","margin-left:200px;");
					setCookie("leftMenuOpenYn", "show", 1);
				}

				function hideMenu() {
					$("#leftMenu").hide();
					$("#menuHideButton").hide();
					$("#menuShowButton").show();
					$(".contents").attr("style","margin-left:0px;");
					setCookie("leftMenuOpenYn", "hide", 1);
				}


				/** 페이지 초기화 */
				$(document).ready(function(){
					var leftMenuOpenYn = GetCookie("leftMenuOpenYn");
					if(leftMenuOpenYn == 'hide') hideMenu();
					else showMenu();
				});

			</script>