<%@page import="egovframework.edutrack.modules.course.createcourse.service.CreateCourseService"%>
<%@page import="egovframework.edutrack.modules.course.createcourse.service.UserCourseVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils"%><%@
page import="egovframework.edutrack.comm.service.SysMenuMemService"%><%@
page import="egovframework.edutrack.modules.system.config.service.SysCfgService"%><%@
page import="egovframework.edutrack.modules.org.menu.service.OrgMenuVO"%><%@
page import="egovframework.edutrack.modules.log.message.service.LogMsgLogService"%><%@
page import="egovframework.edutrack.modules.log.message.service.LogMsgLogVO"%><%@
page import="egovframework.edutrack.comm.util.web.UserBroker"%><%@
page import="egovframework.edutrack.comm.service.SysCodeMemService"%><%@
page import="egovframework.edutrack.modules.system.code.service.SysCodeVO"%><%@
page import="egovframework.edutrack.modules.system.code.service.SysCodeLangVO"%><%@
page import="egovframework.edutrack.modules.course.createcourse.service.CreateCourseService"%><%@
page import="egovframework.edutrack.modules.course.createcourse.service.UserCourseVO"%><%@
page import="egovframework.edutrack.comm.util.web.DateTimeUtil"%><%@
page import="java.util.List"%><%@
page import="egovframework.edutrack.comm.util.web.StringUtil"%><%@
page import="java.util.Hashtable"%><%@
page import="java.util.Map"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<%
	String authGrpCd = UserBroker.getUserType(request);
	String orgCd = UserBroker.getUserOrgCd(request);
	if("".equals(authGrpCd)) authGrpCd = "GUEST";
	SysMenuMemService service = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	OrgMenuVO menuVO = service.getOrgHomeMenuList(orgCd, authGrpCd);
	request.setAttribute("menuVO",menuVO);

	SysCfgService configService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysCfgService.class);
	String LOGINFORMMCD = configService.getValue("MENUNO","LOGIN");
	request.setAttribute("LOGINFORMMCD",LOGINFORMMCD);

	//-- 안읽은 쪽지수
	LogMsgLogService messageService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(LogMsgLogService.class);
	LogMsgLogVO messageVO = new LogMsgLogVO();
	messageVO.setRegNo(UserBroker.getUserNo(request));
	int msgCnt = messageService.msgCount(messageVO);
	request.setAttribute("msgCnt", msgCnt);


	String joininMcd = configService.getValue("MENUNO", "JOININ");
	request.setAttribute("joininMcd", joininMcd);

	String loginMcd = configService.getValue("MENUNO", "LOGIN");
	request.setAttribute("loginMcd", loginMcd);

	String findidMcd = configService.getValue("MENUNO", "FINDIDPW");
	request.setAttribute("findidMcd", findidMcd);

	String crsSearchMcd = configService.getValue("MENUNO", "CRSSEARCH");
	request.setAttribute("crsSearchMcd", crsSearchMcd);

	String searchFullMcd = configService.getValue("MENUNO", "SEARCHFULL");
	request.setAttribute("searchFullMcd", searchFullMcd);

	String messageBoxMcd = configService.getValue("MENUNO", "MESSAGEBOX");
	request.setAttribute("messageBoxMcd", messageBoxMcd);

	String editMyinfoMcd = configService.getValue("MENUNO", "EDITMYINFO");
	request.setAttribute("editMyinfoMcd", editMyinfoMcd);

	String siteMapMcd = configService.getValue("MENUNO", "SITEMAP");
	request.setAttribute("siteMapMcd", siteMapMcd);

	SysCodeMemService codeService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysCodeMemService.class);
	List<SysCodeVO> langList = codeService.getSysCodeList("LANG_CD");
	for(SysCodeVO codeVO : langList) {
		for(SysCodeLangVO codeLangVO : codeVO.getCodeLangList()) {
			if(LOCALEKEY.equals(codeLangVO.getLangCd())) codeVO.setCodeNm(codeLangVO.getCodeNm());
		}
	}
	request.setAttribute("langList",langList);

	String userType = UserBroker.getUserType(request);
	List<UserCourseVO> resultList = null;
	CreateCourseService createCourseService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(CreateCourseService.class);
	String tarYear = StringUtil.nvl(request.getParameter("tarYear"),DateTimeUtil.getYear());
	Map<String, Object> userInfo = new Hashtable<String, Object>();
	//-- 학습중인과정, 준비중인 과정 목록 가져오기
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user type : "+userType);
	if(userType.contains("TEACHER") || userType.contains("TUTOR")) {
		//-- 강사나 투터의 경우 운영중인 과정
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("creYear", tarYear);
		userInfo.put("crsCreNm", "");
		userInfo.put("enrlSts", "");
		resultList = createCourseService.listCreateCourseForTeacher(userInfo).getReturnList();
	} else {
		//-- 학습중인 과정
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("creYear", "");
		userInfo.put("enrlSts", "ing");
		resultList = createCourseService.listCreateCourseForStudent(userInfo).getReturnList();
	}
	request.setAttribute("userType", userType);
	request.setAttribute("creCrsList", resultList);
	
	//다중 접속 체크 여부
		String multiConn = (String)request.getSession().getAttribute("MULTICON_STATE");
		if (multiConn == null) multiConn = "";
		if (multiConn.equals("LOGOUT")) {
			request.getSession().setAttribute("MULTICON_STATE", "");
		}

		if (multiConn.equals("LOGOUT")) {
			%>
			<script type="text/javascript">
			alert("로그아웃 되었습니다.");
			</script>
			<%
		}
%>
		<c:set var="chkedmenu" value=""/>
		<c:forEach items="${menuVO.subList}" var="item">
			<c:if test="${item.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
			<c:forEach items="${item.subList}" var="item1">
				<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
				<c:forEach items="${item1.subList}" var="item2">
					<c:if test="${item2.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
				</c:forEach>
			</c:forEach>
		</c:forEach>
		<div class="sr-only">
			<a href="#side-menu"><spring:message code="common.title.link.menu"/></a>
			<a href="#content"><spring:message code="common.title.link.cnts"/></a>
		</div>

		<header>
			<ul class="util">
				<c:if test="${empty sessionScope.USERNO}">
				<li><a href="<c:url value="/home/main/goMenuPage?mcd=${loginMcd}"/>"><i	class="fa fa-sign-in fa-fw"></i> <spring:message code="common.title.link.login"/></a></li>
			<c:if test="${MBR_APLC_USE eq 'Y' }">
				<li><a href="<c:url value="/home/main/goMenuPage?mcd=${joininMcd}"/>"><i class="fa fa-user fa-fw"></i> <spring:message code="user.title.login.joinin"/></a></li>
			</c:if>
				<li><a href="<c:url value="/home/main/goMenuPage?mcd=${findidMcd}"/>"><i class="fa fa-search fa-fw"></i> <spring:message code="user.title.login.searchid"/></a></li>
				</c:if>
				<c:if test="${not empty sessionScope.USERNO}">
				<li><a href="<c:url value="/home/user/logout" />"><i	class="fa fa-sign-out fa-fw"></i> <spring:message code="common.title.link.logout"/></a></li>
				<li><a href="<c:url value="/home/main/goMenuPage?mcd=${editMyinfoMcd}"/>"><i class="fa fa-user fa-fw"></i> <spring:message code="common.title.link.myinfo"/></a></li>
				<c:if test="${fn:length(MNGTYPE) > 1}">
				<li><a href="/mindex.jsp"><i class="fa fa-key fa-fw"></i> <spring:message code="common.title.go.manage"/></a></li>
				</c:if>
				</c:if>
			</ul>
			<div id="member">
				<h1>
					<a href="<c:url value="/home/main/goMenuPage?mcd=MC00000000"/>">
						<c:if test="${empty TOPLOGOSN}">${ORGNM}</c:if>
						<c:if test="${not empty TOPLOGOSN}"><img src="/app/file/view2/${TOPLOGOSN}" alt="${ORGNM}"/></c:if>
					</a>
				</h1>
				<c:if test="${empty sessionScope.USERNO}">
				<form name="userInfoForm" id="userInfoForm" method="post" action="/home/user/login" class="form-inline login" onSubmit="return false;">
					<input type="hidden" name="goMcd" value="MC00000000" id="goMcd" />
					<input type="hidden" name="encryptData" id="encryptData" />
					<div class="form-group input_box">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input type="text" id="log_id" name="userId" title="<spring:message code="user.title.login.inputid"/>" class="form-control" placeholder="User ID" autofocus />
						</div>
					</div>
					<div class="form-group input_box">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" id="log_pwd" name="userPass" autocomplete="off" title="<spring:message code="user.title.login.inputpw"/>" class="form-control enterLogin" placeholder="Password" />
						</div>
					</div>
					<a class="btn btn-default login_btn" id="login_btn" href="#none"><spring:message code="user.title.login.login"/></a>
					<label class="id_storage">
						<input name="remember" id="rememberMe" type="checkbox" value="Remember Me" style="form-control" /> <spring:message code="user.title.login.rememberme"/>
					</label>
				<c:if test="${MBR_APLC_USE eq 'Y' }">
					<a class="btn btn-default btn-sm" href="<c:url value="/home/main/goMenuPage?mcd=${joininMcd}"/>"><spring:message code="user.title.login.joinin"/></a>
				</c:if>
					<a class="btn btn-default btn-sm" href="<c:url value="/home/main/goMenuPage?mcd=${findidMcd}"/>"><spring:message code="user.title.login.searchid"/></a>
				</form>
				</c:if>
				<c:if test="${not empty sessionScope.USERNO}">
				<div class="login_info">
					<a href="<c:url value="/home/main/goMenuPage"/>?mcd=${editMyinfoMcd}" class="member" style="margin-right: 1px;"><i class="fa fa-cog"></i> </a><spring:message code="common.messgae.wellcome" arguments="${USERNAME}"/>
					<a href="<c:url value="/home/main/goMenuPage"/>?mcd=${messageBoxMcd}" ><i class="glyphicon glyphicon-envelope"></i> <spring:message code="common.message.note.cnt" arguments="${msgCnt}"/></a>
					<c:if test="${fn:length(MNGTYPE) > 1}">
					<a class="btn btn-primary btn-sm" href="/mindex.jsp"> <spring:message code="common.title.go.manage"/></a>
					</c:if>
					<div class="btn-group">
						<button type="button" class="btn btn-default btn-sm btn-block dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							<spring:message code="course.title.course.shortcut" /> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu block" role="menu" id="top-myclass-select">
							<c:set var="creCrsCnt" value="0"/>
 							<c:forEach var="item" items="${creCrsList}">
 								<c:if test="${item.connectYn eq 'Y' || item.connectYn eq 'C'}">
 									<c:set var="creCrsCnt" value="${creCrsCnt + 1}"/>
 							<li><a href="<c:url value="/lec/main/goLecture?crsCreCd=${item.crsCreCd}&amp;stdNo=${item.stdNo}" />"><span><i class="glyphicon glyphicon-play-circle"></i> ${item.crsCreNm} [${item.creYear}/${item.creTerm}]</span></a></li>
 								</c:if>
 							</c:forEach>
 							<c:if test="${creCrsCnt == 0}">
 								<c:choose>
 									<c:when test="${fn:contains(userType, 'TEACHER') == true || fn:contains(userType, 'TUTOR')}">
 										<li><a href="#"><span><spring:message code="teacher.message.course.ing.nodata"/></span></a></li>
 									</c:when>
 									<c:otherwise>
 										<li><a href="#"><span><spring:message code="student.message.course.ing.nodata"/></span></a></li>
 									</c:otherwise>
 								</c:choose>
 							</c:if>
						</ul>
					</div>
					<a class="btn btn-warning btn-sm" href="<c:url value="/home/user/logout" />"><spring:message code="common.title.link.logout"/></a>
				</div>
				</c:if>
			</div>
			<div id="gnb">
				<div class="gnb_box">
					<form class="search" name="courseSearch" id="courseSearch" onsubmit="return false;">
						<div class="search_box">
							<fieldset>
								<legend><spring:message code="common.title.search"/></legend>
								<label for="courseSearchValue" class="blind"><spring:message code="common.title.input.searchvalue"/></label>
								<input type="text" id="courseSearchValue" value="" maxlength="100" placeholder="<spring:message code="common.title.input.coursename"/>"/>
								<button id="mainSearchButton" onClick="topCourseSearch()"></button>
							</fieldset>
						</div>
					</form>
					<nav class="navbar-default">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div id="navbar" class="navbar-collapse collapse">
							<ul class="nav navbar-nav">
							<c:forEach items="${menuVO.subList}" var="item" varStatus="mstatus">
								<c:if test="${item.topMenuYn eq 'Y'}">
									<c:set var="menuName1" value="${item.menuNm}"/>
									<c:forEach var="lang" items="${item.menuLangList}">
										<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
									</c:forEach>
									<c:set var="linkUrl" value="#" />
									<c:if test="${not empty item.menuUrl}">
										<c:url var="linkUrl" value="/home/main/goMenuPage?mcd=${item.menuCd}" />
									</c:if>
									<c:if test="${not empty item.subList}">
								<li class="dropdown <c:if test="${chkedmenu eq item.menuCd}">active</c:if>">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${menuName1}</a>
									<ul class="dropdown-menu gnb-menu">
										<c:forEach items="${item.subList}" var="item1">
											<c:set var="menuName2" value="${item1.menuNm}"/>
											<c:forEach var="lang2" items="${item1.menuLangList}">
												<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
											</c:forEach>
											<c:set var="linkUrl2" value="#" />
											<c:if test="${not empty item1.menuUrl}">
												<c:url var="linkUrl2" value="/home/main/goMenuPage?mcd=${item1.menuCd}" />
											</c:if>

											<c:choose>
												<c:when test="${item1.menuCd eq 'HM04002000' }">
													<c:if test="${MBR_APLC_USE eq 'Y' }">
										<li><a href="${linkUrl2}">${menuName2}</a></li>
													</c:if>
												</c:when>
												<c:otherwise>
													<c:if test="${item1.menuViewYn eq 'Y'}">
										<li><a href="${linkUrl2}">${menuName2}</a></li>
													</c:if>
												</c:otherwise>
											</c:choose>

										</c:forEach>
									</ul>
								</li>
									</c:if>
									<c:if test="${empty item.subList}">
								<li>
									<a href="${linkUrl}">${menuName1}</a>
								</li>
									</c:if>
								</c:if>
							</c:forEach>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</header>

<script type="text/javascript">
	$(function() {
		<c:if test="${empty sessionScope.USERNO}">
		$('body').delegate('#login_btn', 'click keydown', function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();
				doLogin();
			}
		});

		$(".enterLogin").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				doLogin();
				//$("#login_btn").click();
			}
		}
		rememberGet();
		</c:if>

	});

	<c:if test="${empty sessionScope.USERNO}">
	function doLogin() {
		rememberSet();
		if(isEmpty($("#log_id").val())) {
			alert("<spring:message code="user.message.login.alert.input.userid"/>");
			$("#log_id").focus();
			return false;
		}
		if(isEmpty($("#log_pwd").val())) {
			alert("<spring:message code="user.message.login.alert.input.password"/>");
			$("#log_pwd").focus();
			return false;
		}
		var log_id   = $('#log_id').val();
		var log_pwd  = $('#log_pwd').val();

		var goMcd = $('#goMcd').val();
		// 암호화.
		var encrypt = makeSendInfo(log_id, log_pwd);

		if(!isEmpty(encrypt)) {
			// 암호화에 성공했으면 평문정보가 전송되지 않도록 폼 클리어
			$('#userInfoForm').clearForm();
		}
		// 암호화 데이터를 hidden 항목에 삽입하고 submit
		$('#goMcd').val(goMcd);
		$('#encryptData').val(encrypt);
		//$("#userInfoForm").submit();
		document.userInfoForm.submit();
	}

	function rememberSet() {
		// ID 저장하기
		if ($('#rememberMe:checked').length > 0) {
			setCookie('rememberMe', $('#log_id').val(), 1); // 하루 저장
		} else {
			setCookie('rememberMe', '', 0);
		}
	}

	function rememberGet() {
		// 쿠키에 저장된 기본값 입력
		if (isNotNull(GetCookie('rememberMe'))) {
			$('#log_id').val(GetCookie('rememberMe'));
			$('#rememberMe').attr('checked', 'checked');
		}
	}
	</c:if>


</script>
