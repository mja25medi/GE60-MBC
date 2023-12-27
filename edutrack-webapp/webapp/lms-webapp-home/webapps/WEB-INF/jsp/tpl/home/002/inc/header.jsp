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
page import="java.util.List"%>
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
            <div class="logo_search">
                <div class="container">
                     <h1 class="logo">
                        <a href="/" class="link">
     						<c:if test="${empty TOP_LOGOSN}">${ORGNM}</c:if>
							<c:if test="${not empty TOP_LOGOSN}"><img src="/app/file/view2/${TOP_LOGOSN}" alt="${ORGNM}" /></c:if>
						</a>
                    </h1>
                    <div class="gnb_util">
                        <div class="member_area">
			 				<c:if test="${empty sessionScope.USERNO}">
							<div class="login">
                                <ul class="nav"> 
									<li><a href="<c:url value="/home/main/goMenuPage?mcd=${loginMcd}"/>"><spring:message code="common.title.link.login"/></a></li>
									<c:if test="${MBR_APLC_USE eq 'Y' }">
									<li><a href="<c:url value="/home/main/goMenuPage?mcd=${joininMcd}"/>"><spring:message code="user.title.login.joinin"/></a></li>
									</c:if>     
									<li><a href="javascript:goLoginCheck()" class=""><span>마이페이지</span></a></li>
									<li><a href="/home/main/goMenuPage?mcd=MC00000052" class=""><span>고객센터</span></a></li>
                                    <!-- <li><a href="/home/main/goMenuPage?mcd=HM02006000" class=""><span>1:1 온라인상담</span></a></li> -->
                                </ul>
                            </div>	
                            </c:if>		 				
							<c:if test="${not empty sessionScope.USERNO}">
                            <div class="logout">
                                <span class="welcome">
                               	 <span class="text"><strong>${USERNAME }</strong>님</span>
                               	 <em class="badge" style="cursor: pointer;" onclick="javascript:viewRecvMsgPop();">${msgCnt }</em>
                                </span>
                                <ul class="nav">
                                    <li><a href="<c:url value="/home/user/logout" />" class=""><span><spring:message code="common.title.link.logout"/></span></a></li>
                                    <li><a href="<c:url value="/home/main/goMenuPage?mcd=MC00000035"/>" class=""><span>마이페이지</span></a></li>
									<c:if test="${fn:length(MNGTYPE) > 1}">
									<li><a href="/mindex.jsp"><span><spring:message code="common.title.go.manage"/></span></a></li>
									</c:if>
									<li><a href="/home/main/goMenuPage?mcd=MC00000052" class=""><span>고객센터</span></a></li>
                                  <!--   <li><a href="/home/main/goMenuPage?mcd=HM02006000" class="go_ps"><span>1:1 온라인상담</span></a></li> -->
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
                    <button type="button" class="btn_allmenu"><span class="sr-only">전체메뉴보기</span><i
                            class="xi-bars"></i></button>
                    <h2 class="pop_title">전체메뉴</h2>
                    
                    <ul id="head_menu" class="topmenu">
						<c:forEach items="${menuVO.subList}" var="item" varStatus="mstatus">
							<c:if test="${item.topMenuYn eq 'Y'}">
								<c:set var="menuName1" value="${item.menuNm}"/>
								<c:set var="menuDesc1" value="${item.menuDesc}"/>
								<c:forEach var="lang" items="${item.menuLangList}">
									<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
								</c:forEach>
								<c:set var="linkUrl" value="#" />
								<c:if test="${not empty item.menuUrl}">
									<c:url var="linkUrl" value="/home/main/goMenuPage?mcd=${item.menuCd}" />
								</c:if>
								<c:if test="${not empty item.subList}">
								<li class="depth1">
									<a href="#"><span>${menuName1}</span></a>
		                           <div class="submenu">
		                                <div class="title_area">
		                                    <h3 class="title">${menuName1}</h3>
		                                    <p>${menuDesc1 }</p>
		                                </div>
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
											<li class="flexBasisAuto"><a href="${linkUrl2}">${menuName2}</a></li>
														</c:if>
													</c:otherwise>
												</c:choose>
	
											</c:forEach>

		                                </ul>
		                            </div>
								</li>
								</c:if>
								<c:if test="${empty item.subList}">
								<li class="depth1">
									<a href="${linkUrl}"><span>${menuName1}</span></a>
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

            
<script type="text/javascript">
	var modalBox = null;
	var modalBox2 = null;
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1",
			"nomargin" : false //m_large
		});
		modalBox2 = new $M.ModalDialog({
			"modalid" : "modal2",
			"nomargin" : false //m_large
		});
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	function modalBoxClose2() {
		modalBox2.clear();
		modalBox2.close();
	}
	
	function searchIdModal(){
		modalBox2.clear();
		modalBox2.addContents('<c:url value="/home/user/searchIdModalPop2"/>');
		modalBox2.setTitle("아이디 찾기");
		modalBox2.show();
	}
	
	function searchPwModal(){
		modalBox2.clear();
		modalBox2.addContents('<c:url value="/home/user/searchPwModalPop2"/>');
		modalBox2.setTitle("비밀번호 찾기");
		modalBox2.show();
	}
	
	function goLoginCheck(){
		alert("로그인이 필요한 서비스입니다.");
		document.location.href = "/home/main/goMenuPage?mcd=HM04001000";
	}
	
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
	
    function viewRecvMsgPop() {
		modalBox.clear();
		modalBox.addContents('<c:url value="/home/message/listRecvPop2"/>');
		modalBox.setTitle("받은 쪽지함");
		modalBox.show();
    }

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
            