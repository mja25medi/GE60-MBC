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
		
		
<header class="header">            
    <div class="logo_search">
        <div class="container">
            <h1 class="logo">
                <a href="#0" class="link">
                    <span class="sr-only">${USER_ORGNM }</span>
                </a>
            </h1>
         
            <div class="gnb_util">
                
                <div class="member_area">
					<c:if test="${empty sessionScope.USERNO}">
                   <div class="login">
                        <ul class="nav"> 
                            <li><a href="<c:url value="/home/main/goMenuPage?mcd=${loginMcd}"/>" class=""><span><spring:message code="common.title.link.login"/></span></a></li>
                            <c:if test="${MBR_APLC_USE eq 'Y' }">
                            <li><a href="<c:url value="/home/main/goMenuPage?mcd=${joininMcd}"/>" class=""><span><spring:message code="user.title.login.joinin"/></span></a></li>
                            </c:if>
                            
                        </ul>
                    </div>
					</c:if>
					<c:if test="${not empty sessionScope.USERNO}">
                    <div class="logout">
                        <span class="welcome"><span class="text"><strong>${USERNAME}</strong>님</span> <em class="badge">3</em></span>
                        <ul class="nav">
                            <li><a href="<c:url value="/home/user/logout" />" class=""><span><spring:message code="common.title.link.logout"/></span></a></li>
                            <li><a href="<c:url value="/home/main/goMenuPage?mcd=${editMyinfoMcd}"/>" class=""><span><spring:message code="common.title.link.myinfo"/></span></a></li>
							<c:if test="${fn:length(MNGTYPE) > 1}">
							<li><a href="/mindex.jsp" class=""><span><spring:message code="common.title.go.manage"/></span></a></li>
							</c:if>
                        </ul>
                    </div>

					</c:if>

                </div>
                <!-- //member_area -->
                                  
            </div>
            <!-- //gnb_util -->
        </div>
        <!-- //container -->
    </div>
    <!--//logo_search-->

    <div class="gnb_area">
        <nav class="nav container" id="gnb">
            <button type="button" class="btn_allmenu"><span class="sr-only">전체메뉴보기</span><i class="xi-bars"></i></button>
            <h2 class="pop_title">전체메뉴</h2>
            
            <ul id="head_menu" class="topmenu">
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
					                <li class="depth1 <c:if test="${chkedmenu eq item.menuCd}">on</c:if>">
					                    <a href="#0"><span>${menuName1}</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
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
					                    </div>
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
            <button type="button" class="pop_close"><i class="xi-close"></i><span class="sr-only">전체메뉴 레이어
                    닫기</span></button>
        </nav>
    </div>
    <!-- //gnb_area -->
</header>


	
