<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="egovframework.edutrack.comm.service.SysCodeMemService"%>
<%@page import="egovframework.edutrack.modules.system.code.service.SysCodeVO"%>
<%@page import="egovframework.edutrack.modules.system.code.service.SysCodeLangVO"%>
<%@page import="egovframework.edutrack.comm.service.SysMenuMemService"%>
<%@page import="egovframework.edutrack.modules.system.menu.service.SysMenuVO"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<%
	SysCodeMemService sysCodeService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysCodeMemService.class);
	List<SysCodeVO> langList = sysCodeService.getSysCodeList("LANG_CD");
	for(SysCodeVO sysCodeVO : langList) {
		for(SysCodeLangVO sysCodeLangVO : sysCodeVO.getCodeLangList()) {
			if(LOCALEKEY.equals(sysCodeLangVO.getLangCd())) sysCodeVO.setCodeNm(sysCodeLangVO.getCodeNm());
		}
	}
	request.setAttribute("langList",langList);

	String authGrpCd = UserBroker.getAdmType(request);
	if("".equals(authGrpCd)) authGrpCd = "GUEST";
	SysMenuMemService sysMenuService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	List<SysMenuVO> sysMenuList = sysMenuService.getAdmMenuList(authGrpCd);
	request.setAttribute("sysMenuList",sysMenuList);
%>
		<!-- Navigation	-->
		<nav class="navbar navbar-custom navbar-static-top" role="navigation" style="margin-bottom: 0;">
			<div class="navbar-header">
				<button	type="button" class="navbar-toggle"	data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand"	href="/" target="_top" style="padding:5px 15px 5px 15px;"><img src="<c:url value="/img/common/logo.jpg"/>" alt="${system_name}" /></a>
			</div>
			<!-- /.navbar-header -->
			<ul	class="nav navbar-top-links navbar-right">
				<c:if test="${fn:length(langList) > 1 }">
				<li	class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="fa fa-gear fa-fw"></i> <i class="fa fa-caret-down"></i>
					</a>
					<ul	class="dropdown-menu dropdown-lang">
					<form name="Lang">
					<c:forEach var="lang" items="${langList}" varStatus="status">
						<c:if test="${status.count > 1 }">
						<li class="divider"></li>
						</c:if>
						<li>
							<div style="padding:3px 20px 3px 20px;">
								<label><input type="radio" name="lang" onclick="changeLang('${lang.codeCd}');" <c:if test="${LOCALEKEY eq lang.codeCd}">checked</c:if>> ${lang.codeNm}</label>
							</div>
						</li>
					</c:forEach>
					</form>
					</ul>
					<!-- /.dropdown-alerts -->
				</li>
				<script type="text/javascript">
					function changeLang(lang) {
						document.location.href = cUrl("/adm/main/changeLang.do")+"?langCd="+lang;
					}
				</script>
				</c:if>
				<li	class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
					</a>
					<ul	class="dropdown-menu dropdown-user">
						<li><a href="/main/gomenuPage.do?mcd=MA04000000"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
						<li	class="divider"></li>
						<li><a href="/adm/main/logout.do"><i	class="fa fa-sign-out fa-fw"></i> Logout</a>
						</li>
					</ul>
					<!-- /.dropdown-user -->
				</li>
				<!-- /.dropdown	-->
			</ul>
			<!-- /.navbar-top-links	-->
			<!-- Side Menu Start -->
			<c:set var="chkedmenu1" value=""/>
			<c:set var="chkedmenu2" value=""/>
			<c:forEach items="${sysMenuList}" var="item">
				<c:forEach items="${item.subList}" var="item1">
					<c:if test="${item1.menuCd eq MENUCODE || fn:length(sysMenuVO.subList) == 1}"><c:set var="chkedmenu1" value="${item.menuCd}"/></c:if>
					<c:forEach items="${item1.subList}" var="item2">
						<c:if test="${item2.menuCd eq MENUCODE}">
							<c:set var="chkedmenu1" value="${item.menuCd}"/>
							<c:set var="chkedmenu2" value="${item1.menuCd}"/>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:forEach>
			<div class="navbar-default sidebar"	role="navigation">
				<div class="sidebar-nav	navbar-collapse">
					<ul class="nav"	id="side-menu">
					<c:forEach items="${sysMenuList}" var="item">
						<c:set var="menuName1" value="${item.menuNm}"/>
						<c:forEach var="lang" items="${item.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
						</c:forEach>
						<li>
							<c:if test="${empty item.subList}">
							<a href="<c:url value="/adm/main/gomenuPage.do"/>?mcd=${item.menuCd}" id="menu_${item.menuCd}"><c:if test="${not empty item.leftMenuImg }"><i class="${item.leftMenuImg}" style="font-size:1.1em"></i></c:if> ${menuName1}</a>
							</c:if>
							<c:if test="${not empty item.subList}">
							<a href="#" id="menu_${item.menuCd}"><c:if test="${not empty item.leftMenuImg }"><i class="${item.leftMenuImg}" style="font-size:1.1em"></i></c:if> ${menuName1}<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
							<c:forEach items="${item.subList}" var="item1">
								<c:set var="menuName2" value="${item1.menuNm}"/>
								<c:forEach var="lang2" items="${item1.menuLangList}">
									<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
								</c:forEach>
								<li>
								<c:if test="${empty item1.subList}">
									<a href="<c:url value="/adm/main/gomenuPage.do"/>?mcd=${item1.menuCd}" id="menu_${item1.menuCd}">${menuName2}</a>
								</c:if>
								<c:if test="${not empty item1.subList}">
									<a href="#" id="menu_${item1.menuCd}">${menuName2}<span class="fa	arrow"></span></a>
									<ul class="nav nav-third-level">
									<c:forEach items="${item1.subList}" var="item2">
										<c:set var="menuName3" value="${item2.menuNm}"/>
										<c:forEach var="lang3" items="${item2.menuLangList}">
											<c:if test="${LOCALEKEY eq lang3.langCd}"><c:set var="menuName3" value="${lang3.menuNm}"/></c:if>
										</c:forEach>
										<li >
											<a href="<c:url value="/adm/main/gomenuPage.do"/>?mcd=${item2.menuCd}" id="menu_${item2.menuCd}">${menuName3}</a>
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
				</div>
				<!-- /.sidebar-collapse	-->
			</div>
			<!-- /.navbar-static-side -->
		</nav>