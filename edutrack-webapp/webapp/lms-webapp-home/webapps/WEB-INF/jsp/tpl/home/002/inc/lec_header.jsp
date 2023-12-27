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
	SysMenuMemService service = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	List<SysMenuVO> menuList = service.getLecMenuList(authGrpCd);
	request.setAttribute("menuList",menuList);

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
	List<UserCourseVO> crsList = null;
	List<UserCourseVO> creList = null;
	CreateCourseService createCourseService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(CreateCourseService.class);
	String tarYear = StringUtil.nvl(request.getParameter("tarYear"),DateTimeUtil.getYear());
	Map<String, Object> userInfo = new Hashtable<String, Object>();
	//-- 학습중인과정, 준비중인 과정 목록 가져오기
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user type : "+userType);
		userInfo.put("userNo", UserBroker.getUserNo(request));
	if(userType.contains("TEACHER") || userType.contains("TUTOR") || userType.contains("TCH")) {
		//-- 강사나 투터의 경우 운영중인 과정
		crsList = createCourseService.myCrsListForTeacher(userInfo).getReturnList();
		
		userInfo.put("crsCd", UserBroker.getCrsCd(request));
		creList = createCourseService.myCreListForTeacher(userInfo).getReturnList();
	} else {
		//-- 학습중인 과정
		crsList = createCourseService.myCrsListForStudent(userInfo).getReturnList();

		userInfo.put("crsCd", UserBroker.getCrsCd(request));
		creList = createCourseService.myCreListForStudent(userInfo).getReturnList();
	}
	request.setAttribute("creList", creList);
	request.setAttribute("crsList", crsList);
	
	
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
			<div class="inner-box">
                <a href="#0" class="menu-btn">
                    <span class="barTop"></span>
                    <span class="barMid"></span>
                    <span class="barBot"></span>
                </a>
                <h1><a href="/lec/main/goMenuPage?mcd=ML01000000"><strong>나의</strong> 강의실</a></h1>
                <ul class="util">
                    <li class="select-control">
                        <select class="form-select" id="crsCd" onchange="changeCrs();">
                            <c:forEach items="${crsList}" var="crsitem" varStatus="crsStatus">
                            	<option value="${crsitem.crsCd}" <c:if test="${crsitem.crsCd eq CRSCD}">selected</c:if>>${crsitem.crsYear}년도 ${crsitem.crsTerm}기</option>
                            </c:forEach>
                        </select>
                        <select class="form-select" id="crsCreCd" onchange="changeCre();">
                            <c:forEach items="${creList}" var="creitem" varStatus="creStatus">
                            	<option value="${creitem.crsCreCd}" <c:if test="${creitem.crsCreCd eq CRSCRECD}">selected</c:if>>${creitem.crsCreNm}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <div class="util-link">
                        	 <%-- <a href="#" onclick="javascript:window.open('/pdf/${pdfName}.pdf','','width=532, height=710, resizable=no, status=no, scrollbars=no, location=no')" class="userguide"><i class="xi-paper-o"></i><span>${manualName}</span></a> --%>
                            <a href="/home/main/goMenuPage?mcd=MC00000000" class="home"><i class="xi-home-o"></i><span>홈</span></a>
                            <a href="javascript:viewRecvMsgPop('','');" class="alrim"><i class="xi-bell-o"></i><span>쪽지</span><label class="count">${msgCnt}</label></a>
                            <a href="/home/user/logout" class="logout"><i class="xi-log-out"></i><span>로그아웃</span></a>
                        </div>
                    </li>                                       
                </ul>
            </div>
            
            <c:set var="chkedmenu" value=""/>
			<c:forEach items="${menuList}" var="item">
				<c:if test="${item.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
					<c:forEach items="${item.subList}" var="item1">
						<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
						<c:forEach items="${item1.subList}" var="item2">
							<c:if test="${item2.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
						</c:forEach>
					</c:forEach>
			</c:forEach>
			
            <div class="location-wrap">
                <div class="ui menu">
                	<c:forEach items="${menuList}" var="item" varStatus="mstatus">
                		<c:set var="menuName1" value="${item.menuNm}"/>
						<c:forEach var="lang" items="${item.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
						</c:forEach>
						<c:set var="linkUrl" value="#" />
						<c:if test="${not empty item.menuUrl}">
							<c:url var="linkUrl" value="/lec/main/goMenuPage?mcd=${item.menuCd}" />
						</c:if>
						<a class="item <c:if test="${chkedmenu eq item.menuCd }">active</c:if>" href="${linkUrl}">${menuName1}</a>
                	</c:forEach>
               	</div>
            </div>
            

		<script type="text/javascript">
		var modalBox = null;
		$(document).ready(function() {
			modalBox = new $M.ModalDialog({
				"modalid" : "modal1",
				"nomargin" : false //m_large
			});
		});
			$(function() {
				//컨텐츠 상단 이동
				var offset = 300,
				offset_opacity = 1200,
				scroll_top_duration = 300,
				$back_to_top = $('#toTop');

				$back_to_top.on('click', function(event){
				event.preventDefault();
					$('body,html').animate({scrollTop: 0 ,}, scroll_top_duration);
				});

				//REPONSIVE 레이아웃 위치 이동
				$(window).resize(function() {
				var ww = $(window).width();
				if (ww > 1200){
					$("#mv_shift").insertAfter("#cd_shift");
					}
				else {
					$("#mv_shift").insertBefore("#cd_shift");
					}
				});
				$(window).load(function() {
				var ww = $(window).width();
				if (ww > 1200){
					$("#mv_shift").insertAfter("#cd_shift");
					}
				else {
					$("#mv_shift").insertBefore("#cd_shift");
					}
				});
			
				$("#crsCreCd").change(function() {
					
				});

			});
				
			function changeCrs() {
				var searchValue = $("#courseSearchValue").val();
				document.location.href = "/lec/main/goMenuPage?mcd=ML01000000"+"${AMPERSAND}subParam=crsCd=" + $('#crsCd').val() + "@$crsCreCd=CHANGE";
			}
				
			function changeCre() {
				var searchValue = $("#courseSearchValue").val();
				document.location.href = "/lec/main/goMenuPage?mcd=ML01000000"+"${AMPERSAND}subParam=crsCd=" + $('#crsCd').val() + "@$crsCreCd=" +$("#crsCreCd").val();
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