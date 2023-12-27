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
            <div class="logo_search">
                <div class="container">
                      <h1 class="logo">
                        <a href="/" class="link">
     						<c:if test="${empty TOP_LOGOSN}">${ORGNM}</c:if>
							<c:if test="${not empty TOP_LOGOSN}"><img src="/app/file/view2/${TOP_LOGOSN}" alt="${ORGNM}" /></c:if>
						</a>
                    </h1>
                </div>
            </div>
       
            
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
            