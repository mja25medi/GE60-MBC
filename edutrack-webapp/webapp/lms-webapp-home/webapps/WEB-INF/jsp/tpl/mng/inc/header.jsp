<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils"%><%@
page import="egovframework.edutrack.comm.service.SysCodeMemService"%><%@
page import="egovframework.edutrack.modules.system.code.service.SysCodeVO"%><%@
page import="egovframework.edutrack.modules.system.code.service.SysCodeLangVO"%><%@
page import="egovframework.edutrack.comm.service.SysMenuMemService"%><%@
page import="egovframework.edutrack.modules.system.menu.service.SysMenuVO"%><%@
page import="egovframework.edutrack.modules.org.info.service.OrgOrgInfoService"%><%@
page import="java.util.List"%>

<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%
	SysCodeMemService service = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysCodeMemService.class);
	List<SysCodeVO> langList = service.getSysCodeList("LANG_CD");
	for(SysCodeVO sysCodeVO : langList) {
		for(SysCodeLangVO sysCodeLangVO : sysCodeVO.getCodeLangList()) {
			if(LOCALEKEY.equals(sysCodeLangVO.getLangCd())) sysCodeVO.setCodeNm(sysCodeLangVO.getCodeNm());
		}
	}
	request.setAttribute("langList",langList);

	String authGrpCd = UserBroker.getMngType(request);
	if("".equals(authGrpCd)) authGrpCd = "GUEST";
	SysMenuMemService menuService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	List<SysMenuVO> sysMenuList = menuService.getMngMenuList(authGrpCd);
	request.setAttribute("sysMenuList",sysMenuList);
	
	String hrdApiUseCheck = Constants.HRD_API_USE_YN;
	request.setAttribute("hrdApiUseCheck",hrdApiUseCheck);
	
%>
	<header class="main-header">
		<!-- Logo -->
		<a href="/mindex.jsp" class="logo" target="_top">
			<!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini">M</span>
			<!-- logo for regular state and mobile devices -->
			<span class="logo-lg">
				<c:choose>
					<c:when test="${not empty ADM_LOGOSN}">
					<img src="<c:url value="/app/file/view2/${ADM_LOGOSN}"/>" style="max-width : 200px; max-height : 50px;" alt="${USER_ORGNM}"/>
					</c:when>
					<c:otherwise>
					${USER_ORGNM}
					</c:otherwise>
				</c:choose>			
			</span>
		</a>
		<nav class="navbar navbar-static-top">
			<!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>
			<!-- Navbar Right Menu -->
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<!-- Messages: style can be found in dropdown.less-->
					<!--
					<li class="dropdown messages-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-envelope-o"></i>
							<span class="label label-success">4</span>
						</a>
						<ul class="dropdown-menu">
							<li class="header">You have 4 messages</li>
						</ul>
					</li>
					-->
					<!-- Notifications: style can be found in dropdown.less -->
					<!--
					<li class="dropdown notifications-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-bell-o"></i>
							<span class="label label-warning">10</span>
						</a>
						<ul class="dropdown-menu">
							<li class="header">You have 10 notifications</li>
						</ul>
					</li>
					-->
					<c:if test="${fn:length(langList) > 1 }">
					<!-- Language: style can be found in dropdown.less -->
					<!--
          			<li class="dropdown tasks-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-flag-o"></i>
						</a>
						<ul class="dropdown-menu">
							<form name="Lang">
							<c:forEach var="lang" items="${langList}" varStatus="status">
							<li <c:if test="${!status.last}">style="border-bottom:1px solid #ddd;"</c:if>>
								<div style="padding:7px 20px;">
									<label style="margin:0px;"><input type="radio" name="lang" onclick="changeLang('${lang.codeCd}');" <c:if test="${LOCALEKEY eq lang.codeCd}">checked</c:if>> ${lang.codeNm}</label>
								</div>
							</li>
							</c:forEach>
							</form>
						</ul>
					</li>
					-->
					</c:if>
					<!-- User Account: style can be found in dropdown.less -->
					<li class="dropdown user user-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="<c:url value="/img/common/no_user.gif"/>" class="user-image" alt="User Image">
							<span class="hidden-xs">${USERNAME}</span>
						</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header">
								<img src="<c:url value="/img/common/no_user.gif"/>" class="img-circle" alt="User Image">
								<p>
									${USERNAME} - ${USERID}
									<small>- -</small>
								</p>
							</li>
							
							<li class="user-footer">
								<div class="pull-left">
									<a href="/mng/main/goMenuPage?mcd=MC00000113" class="btn btn-default btn-flat">Profile</a>
								</div>
								<div class="pull-right">
									<a href="/mng/main/logout" class="btn btn-default btn-flat">Logout</a>
								</div>
							</li>
						</ul>
					</li>					
				</ul>
			</div>
		</nav>
	</header>
	<!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="<c:url value="/img/common/no_user.gif"/>" class="img-circle" alt="${USERNAME}">
				</div>
				<div class="pull-left info">
					<p>${USERNAME}</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<c:set var="chkedmenu1" value=""/>
			<c:set var="chkedmenu2" value=""/>
			<c:set var="chkedmenu3" value=""/>
			<c:forEach items="${sysMenuList}" var="item1">
				<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu1" value="${item1.menuCd}"/></c:if>
				<c:forEach items="${item1.subList}" var="item2">
					<c:if test="${item2.menuCd eq MENUCODE}">
						<c:if test="${item2.menuCd eq MENUCODE}">
							<c:set var="chkedmenu1" value="${item1.menuCd}"/>
							<c:set var="chkedmenu2" value="${item2.menuCd}"/>
						</c:if>					
					</c:if>
					<c:forEach items="${item2.subList}" var="item3">
						<c:if test="${item3.menuCd eq MENUCODE}">
							<c:set var="chkedmenu1" value="${item1.menuCd}"/>
							<c:set var="chkedmenu2" value="${item2.menuCd}"/>
							<c:set var="chkedmenu3" value="${item3.menuCd}"/>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:forEach>			
			<ul class="sidebar-menu" id="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<c:forEach items="${sysMenuList}" var="item1">
					<c:if test="${hrdApiUseCheck eq 'Y'}">
						<c:set var="menuName1" value="${item1.menuNm}"/>
						<c:forEach var="lang" items="${item1.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
						</c:forEach>
						<c:set var="menuLink1" value="#"/>
						<c:if test="${empty item1.subList}">
							<c:url var="menuLink1" value="/mng/main/goMenuPage?mcd=${item1.menuCd}"/>
						</c:if>				
						<li class="<c:if test="${item1.menuCd eq chkedmenu1}">active</c:if> treeview">
							<a href="${menuLink1}">
								<c:if test="${not empty item1.leftMenuImg }">
								<i class="${item1.leftMenuImg}"></i>
								</c:if>
								<c:if test="${empty item1.leftMenuImg }">
								<i class="fa fa-dashboard"></i>
								</c:if>
								<span>${menuName1}</span>
								<c:if test="${not empty item1.subList}">
								<span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
								</span>
								</c:if>
							</a>
							<c:if test="${not empty item1.subList}">
								<ul class="treeview-menu">
									<c:forEach items="${item1.subList}" var="item2">
										<c:set var="menuName2" value="${item2.menuNm}"/>
										<c:forEach var="lang2" items="${item2.menuLangList}">
											<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
										</c:forEach>
										<c:set var="menuLink2" value="#"/>
										<c:if test="${empty item2.subList}">
											<c:url var="menuLink2" value="/mng/main/goMenuPage?mcd=${item2.menuCd}"/>
										</c:if>	
										<li <c:if test="${item2.menuCd eq chkedmenu2}">class="active"</c:if>>
											<a href="${menuLink2}">
											<i class="fa fa-circle-o"></i> ${menuName2}</a>
											<c:if test="${not empty item2.subList}">
												<ul class="treeview-menu">
													<c:forEach items="${item2.subList}" var="item3">
														<c:set var="menuName3" value="${item3.menuNm}"/>
														<c:forEach var="lang3" items="${item3.menuLangList}">
															<c:if test="${LOCALEKEY eq lang3.langCd}"><c:set var="menuName3" value="${lang3.menuNm}"/></c:if>
														</c:forEach>
														<c:url var="menuLink3" value="/mng/main/goMenuPage?mcd=${item3.menuCd}"/>
														<li>
															<a href="${menuLink3}">
															<i class="fa fa-circle-o"></i> ${menuName3}</a>
														</li>
													</c:forEach>
												</ul>
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</li>
					</c:if>
					<c:if test="${(hrdApiUseCheck ne 'Y') && (item1.menuCd ne 'MS12000000')}">
						<c:set var="menuName1" value="${item1.menuNm}"/>
						<c:forEach var="lang" items="${item1.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
						</c:forEach>
						<c:set var="menuLink1" value="#"/>
						<c:if test="${empty item1.subList}">
							<c:url var="menuLink1" value="/mng/main/goMenuPage?mcd=${item1.menuCd}"/>
						</c:if>				
						<li class="<c:if test="${item1.menuCd eq chkedmenu1}">active</c:if> treeview">
							<a href="${menuLink1}">
								<c:if test="${not empty item1.leftMenuImg }">
									<i class="${item1.leftMenuImg}"></i>
								</c:if>
								<c:if test="${empty item1.leftMenuImg }">
									<i class="fa fa-dashboard"></i>
								</c:if>
								<span>${menuName1}</span>
								<c:if test="${not empty item1.subList}">
									<span class="pull-right-container">
										<i class="fa fa-angle-left pull-right"></i>
									</span>
								</c:if>
							</a>
							<c:if test="${not empty item1.subList}">
								<ul class="treeview-menu">
									<c:forEach items="${item1.subList}" var="item2">
										<c:set var="menuName2" value="${item2.menuNm}"/>
										<c:forEach var="lang2" items="${item2.menuLangList}">
											<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
										</c:forEach>
										<c:set var="menuLink2" value="#"/>
										<c:if test="${empty item2.subList}">
											<c:url var="menuLink2" value="/mng/main/goMenuPage?mcd=${item2.menuCd}"/>
										</c:if>	
										<li <c:if test="${item2.menuCd eq chkedmenu2}">class="active"</c:if>>
											<a href="${menuLink2}">
											<i class="fa fa-circle-o"></i> ${menuName2}</a>
											<c:if test="${not empty item2.subList}">
												<ul class="treeview-menu">
													<c:forEach items="${item2.subList}" var="item3">
														<c:set var="menuName3" value="${item3.menuNm}"/>
														<c:forEach var="lang3" items="${item3.menuLangList}">
															<c:if test="${LOCALEKEY eq lang3.langCd}"><c:set var="menuName3" value="${lang3.menuNm}"/></c:if>
														</c:forEach>
														<c:url var="menuLink3" value="/mng/main/goMenuPage?mcd=${item3.menuCd}"/>
														<li>
															<a href="${menuLink3}">
															<i class="fa fa-circle-o"></i> ${menuName3}</a>
														</li>
													</c:forEach>
												</ul>
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</li>
					</c:if>
				</c:forEach>
			</ul>			
		</section>	
	</aside>