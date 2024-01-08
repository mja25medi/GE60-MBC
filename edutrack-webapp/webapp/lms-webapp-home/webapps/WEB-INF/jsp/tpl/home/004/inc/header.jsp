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
                    <span class="sr-only">동국대학교WISE캠퍼스 OpenCampus</span>
                </a>
            </h1>
         
            <div class="gnb_util">
                
                <div class="member_area">
                    <div class="login">
                        <ul class="nav"> 
                            <li><a href="" class=""><span>로그인</span></a></li>
                            <li><a href="" class=""><span>회원가입</span></a></li>
                            
                        </ul>
                    </div>
                    <!-- <div class="logout">
                        <span class="welcome"><span class="text"><strong>홍길동</strong>님</span> <em class="badge">3</em></span>
                        <ul class="nav">
                            <li><a href="" class=""><span>로그아웃</span></a></li>
                            <li><a href="" class=""><span>개인정보수정</span></a></li>
                        </ul>
                    </div> -->
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
                <li class="depth1">
                    <a href="#0"><span>서비스 소개</span></a>
                    <div class="submenu">                    
                        <ul class="depth2">             
                            <li><a href="#0">서비스 소개</a></li>
                        </ul>
                    </div>
                </li>                
                <li class="depth1">
                    <a href="#0"><span>과정안내/신청</span></a>
                    <div class="submenu">                       
                        <ul class="depth2">    
                            <li><a href="#0">훈련과정</a></li>         
                            <li><a href="#0">고용보험 환급제도</a></li>
                            <li><a href="#0">훈련비 환급방법</a></li>
                            <li><a href="#0">훈련 진행절차</a></li>
                            <li><a href="#0">훈련진행 유의사항</a></li>
                            <li><a href="#0">모사답안 처리기준</a></li>
                        </ul>
                    </div>
                </li>                                                     
                <li class="depth1">
                    <a href="#0"><span>나의 강의실</span></a>
                    <div class="submenu">                        
                        <ul class="depth2">
                            <li><a href="#0">나의 학습</a></li>
                            <li><a href="#0">종료과정 성적조회</a></li>
                            <li><a href="#0">수료증</a></li>
                            <li><a href="#0">설문</a></li>
                            <li><a href="#0">과정별 학습 문의</a></li>
                        </ul>
                    </div>
                </li> 
                <li class="depth1">
                    <a href="#0"><span>마이 페이지</span></a>
                    <div class="submenu">                        
                        <ul class="depth2">
                            <li><a href="#0">장바구니</a></li>
                            <li><a href="#0">결제내역</a></li>
                            <li><a href="#0">문의내역</a></li>
                            <li><a href="#0">받은쪽지</a></li>
                            <li><a href="#0">개인정보 수정</a></li>
                        </ul>
                    </div>
                </li> 
                <li class="depth1">
                    <a href="#0"><span>알림마당</span></a>
                    <div class="submenu">                        
                        <ul class="depth2">
                            <li><a href="#0">공지사항</a></li>
                            <li><a href="#0">학습자료실</a></li>
                            <li><a href="#0">1:1문의하기</a></li>
                            <li><a href="#0">FAQ</a></li>
                        </ul>
                    </div>
                </li>                                                                                  
            </ul>
            <button type="button" class="pop_close"><i class="xi-close"></i><span class="sr-only">전체메뉴 레이어
                    닫기</span></button>
        </nav>
    </div>
    <!-- //gnb_area -->
</header>
<script type="text/javascript">
function topCourseSearch() {
	var searchValue = $("#searchValue").val();
	document.location.href = cUrl("/home/course/listSearchCourseFullMain")+"?mcd=${searchFullMcd}${AMPERSAND}searchValue="+searchValue;
}
</script>

	
