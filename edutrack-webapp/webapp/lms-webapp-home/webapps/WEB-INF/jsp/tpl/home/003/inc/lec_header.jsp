<%@page import="egovframework.edutrack.modules.course.createcourse.service.CreateCourseService"%>
<%@page import="egovframework.edutrack.modules.course.createcourse.service.UserCourseVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils"%><%@
page import="egovframework.edutrack.comm.service.SysMenuMemService"%><%@
page import="egovframework.edutrack.modules.system.config.service.SysCfgService"%><%@
page import="egovframework.edutrack.modules.org.menu.service.OrgMenuVO"%><%@
page import="egovframework.edutrack.modules.system.menu.service.SysMenuVO"%><%@
page import="egovframework.edutrack.modules.log.message.service.LogMsgLogService"%><%@
page import="egovframework.edutrack.modules.log.message.service.LogMsgLogVO"%><%@
page import="egovframework.edutrack.comm.util.web.UserBroker"%><%@
page import="egovframework.edutrack.comm.service.SysCodeMemService"%><%@
page import="egovframework.edutrack.modules.system.code.service.SysCodeVO"%><%@
page import="egovframework.edutrack.modules.system.code.service.SysCodeLangVO"%><%@
page import="egovframework.edutrack.comm.util.web.DateTimeUtil"%><%@
page import="egovframework.edutrack.comm.util.web.StringUtil"%><%@
page import="java.util.List"%><%@
page import="java.util.Hashtable"%><%@
page import="java.util.Map"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
	String authGrpCd = UserBroker.getClassUserType(request);
	String orgCd = UserBroker.getUserOrgCd(request);
	if("".equals(authGrpCd)) authGrpCd = "GUEST";

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
	//List<UserCourseVO> crsList = null;
	List<UserCourseVO> creList = null;
	CreateCourseService createCourseService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(CreateCourseService.class);
	String tarYear = StringUtil.nvl(request.getParameter("tarYear"),DateTimeUtil.getYear());
	Map<String, Object> userInfo = new Hashtable<String, Object>();
	//-- 학습중인과정, 준비중인 과정 목록 가져오기
	//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user type : "+userType);
		userInfo.put("userNo", UserBroker.getUserNo(request));
	if(userType.contains("TEACHER") || userType.contains("TUTOR") || userType.contains("TCH")) {
		//-- 강사나 투터의 경우 운영중인 과정
		//crsList = createCourseService.myCrsListForTeacher(userInfo).getReturnList();
		
		//userInfo.put("crsCd", UserBroker.getCrsCd(request));
		creList = createCourseService.myCreListForTeacher(userInfo).getReturnList();
	} else {
		//-- 학습중인 과정
		//crsList = createCourseService.myCrsListForStudent(userInfo).getReturnList();

		//userInfo.put("crsCd", UserBroker.getCrsCd(request));
		creList = createCourseService.myCreListForStudent(userInfo).getReturnList();
	}
	request.setAttribute("creList", creList);
	//request.setAttribute("crsList", crsList);
	
	
	String pdfName = null;
	String manualName = null;
	if(userType.contains("TEACHER") || userType.contains("TUTOR") || userType.contains("TCH")){
		pdfName = "tutorGuid";
		manualName ="튜터 가이드";
	}else{
		pdfName = "studentGuid";
		manualName = "학습자 가이드";
	}
	request.setAttribute("pdfName", pdfName);
	request.setAttribute("manualName", manualName);
%>
		<c:set var="chkedmenu" value=""/>
		<c:forEach items="${menuList}" var="item">
			<c:if test="${item.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
		</c:forEach>
		<header>
		    <h1 class="logo"><a href="/lec/main/goMenuPage?mcd=ML01000000"><span class="sr-only">스마트인재개발원 온라인강의실</span></a></h1>
		    <div class="util">
		        <select class="form-select" id="crsCreCd" onchange="goLecture();">
		        	<c:forEach items="${creList}" var="creitem" varStatus="creStatus">
                    <option value="${creitem.crsCreCd}" value2="${creitem.stdNo}" <c:if test="${creitem.crsCreCd eq CRSCRECD}">selected</c:if>>${creitem.crsCreNm}</option>
                    </c:forEach>
		        </select>
		        <button type="button" class="active" onclick="goMenuPage();"><img src="/tpl/${COLOR_TPL}/img/class/icon_util_home.svg" alt="icon" /><span class="sr-only">홈</span></button>
		        <button type="button" onclick="viewRecvMsgPop('','');"><img src="/tpl/${COLOR_TPL}/img/class/icon_util_msg.svg" alt="icon" /><span class="sr-only">쪽지</span><label class="count">${msgCnt}</label></button>
		        <c:choose>
		        	<c:when  test="${authGrpCd eq 'TCH' || 'TEACHER'}">
				        <button type="button" onclick="logout();"><img src="/tpl/${COLOR_TPL}/img/class/icon_util_out.svg" alt="icon" /><span class="sr-only">나가기</span></button>
		        	</c:when>
		        	<c:otherwise>
				        <button type="button" onclick="location.href='/home/main/goMenuPage?mcd=MC00000051';"><img src="/tpl/${COLOR_TPL}/img/class/icon_util_out.svg" alt="icon" /><span class="sr-only">나가기</span></button>
		        	</c:otherwise>
		        </c:choose>
		    </div>
		</header>
		
		
	
            

		<script type="text/javascript">
		var modalBox = null;
		$(document).ready(function() {
			modalBox = new $M.ModalDialog({
				"modalid" : "modal1",
				"nomargin" : false //m_large
			});
		});

				
			function logout() {
				document.location.href = "/home/user/logout";
			}
			function changeCrs() {
				var searchValue = $("#courseSearchValue").val();
				document.location.href = "/lec/main/goMenuPage?mcd=ML01000000"+"${AMPERSAND}subParam=crsCd=" + $('#crsCd').val() + "@$crsCreCd=CHANGE";
			}
				
			function changeCre() {
				var searchValue = $("#courseSearchValue").val();
				document.location.href = "/lec/main/goMenuPage?mcd=ML01000000"+"${AMPERSAND}subParam=crsCd=" + $('#crsCd').val() + "@$crsCreCd=" +$("#crsCreCd").val();
			}
			
			function goLecture() {
				var crsCreCd = $("#crsCreCd").val();
				var stdNo = $("#crsCreCd option:selected").attr("value2");
				location.href=cUrl("/lec/main/goLecture") + "?crsCreCd=" + crsCreCd + "&stdNo=" + stdNo ;
			}
			function goMenuPage() {
				location.href=cUrl("lec/main/lectureMain");
			}
			
			function viewRecvMsgPop(msgSn,msgTransSn) {
				var addContent  = "<iframe id='courseInfoFrame' name='courseInfoFrame' width='100%' height='100%' "
					+ "frameborder='0' scrolling='yes' src='<c:url value="/lec/message/listRecvPop"/>"
					+ "?msgSn="+msgSn+"&msgTransSn="+msgTransSn+"'/>";
				modalBox.clear();
				modalBox.addContents(addContent);
				modalBox.resize(1400, 500);
				modalBox.setTitle("받은 쪽지함");
				modalBox.show();
		    }
			
		</script>