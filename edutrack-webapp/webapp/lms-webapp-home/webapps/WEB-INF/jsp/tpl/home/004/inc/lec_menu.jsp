<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils"%><%@
page import="egovframework.edutrack.comm.service.SysMenuMemService"%><%@
page import="egovframework.edutrack.modules.system.menu.service.SysMenuVO"%><%@
page import="egovframework.edutrack.comm.util.web.UserBroker"%><%@
page import="egovframework.edutrack.comm.util.web.StringUtil"%><%@
page import="egovframework.edutrack.modules.course.createcourse.service.UserCourseVO"%><%@
page import="egovframework.edutrack.modules.course.createcourse.service.CreateCourseService"%><%@
page import="egovframework.edutrack.modules.log.message.service.LogMsgLogService"%><%@
page import="egovframework.edutrack.modules.log.message.service.LogMsgLogVO"%><%@
page import="egovframework.edutrack.comm.util.web.DateTimeUtil"%><%@
page import="java.util.List"%><%@
page import="java.util.Hashtable"%><%@
page import="java.util.Map"%><%@
include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
	String authGrpCd = UserBroker.getClassUserType(request);
	String userType = UserBroker.getUserType(request);
	request.setAttribute("authGrpCd",authGrpCd);
	request.setAttribute("userType",userType);
	
	if("".equals(authGrpCd)) authGrpCd = "GUEST";
	SysMenuMemService service = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	List<SysMenuVO> menuList = service.getLecMenuList(authGrpCd);
	request.setAttribute("menuList",menuList);

	//-- 안읽은 쪽지수
	LogMsgLogService messageService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(LogMsgLogService.class);
	LogMsgLogVO messageVO = new LogMsgLogVO();
	messageVO.setRegNo(UserBroker.getUserNo(request));
	int msgCnt = messageService.msgCount(messageVO);
	request.setAttribute("msgCnt", msgCnt);
	
	
	
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
	request.setAttribute("mycreCrsList", resultList);
%>
<div class="lnb <c:if test="${authGrpCd eq 'TCH'}">prof_mode</c:if>">
    <div class="inner">
        <div class="burger_box">
            <a href="#" class="menu-icon js-menu_toggle closed">
                <span class="menu-icon_box">
                    <span class="menu-icon_line menu-icon_line--1"></span>
                    <span class="menu-icon_line menu-icon_line--2"></span>
                    <span class="menu-icon_line menu-icon_line--3"></span>
                </span>
            </a>
        </div>
        <div class="profile">
            <p class="name"><strong>${USERNAME }</strong>(${USERID })님</p>
            <div class="meta">강의실 입장을 환영합니다.</div>
        </div>
        <div class="mo_util">
		    <select class="form-select" id="crsCreCd" onchange="changeCre();">
		        <c:forEach items="${mycreCrsList}" var="creitem" varStatus="creStatus">
                <option value="${creitem.crsCreCd}" <c:if test="${creitem.crsCreCd eq CRSCRECD}">selected</c:if>>${creitem.crsCreNm}</option>
                </c:forEach>
		    </select>
            <div class="button-box">
 		        <button type="button" class="active" onclick="goMenuPage('/home/main/goMenuPage?mcd=MC00000000');"><img src="/tpl/003/img/class/icon_util_home.svg" alt="icon" /><span class="sr-only">홈</span></button>
		        <button type="button" onclick="viewRecvMsgPop('','');"><img src="/tpl/003/img/class/icon_util_msg.svg" alt="icon" /><span class="sr-only">쪽지</span><label class="count">${msgCnt}</label></button>
		        <button type="button" onclick="goMenuPage('/home/user/logout');"><img src="/tpl/003/img/class/icon_util_out.svg" alt="icon" /><span class="sr-only">나가기</span></button>
            </div>
        </div>
	            <c:forEach items="${menuList}" var="item">
	            	<c:if test="${item.menuCd eq MENUCODE}"><c:set var="chkedmenu1" value="${item.menuCd}"/></c:if>
	            	<c:if test="${not empty item.subList}">
						<c:forEach items="${item.subList}" var="item1">
							<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu1" value="${item1.menuCd}"/></c:if>
							<c:if test="${not empty item1.subList}">
								<c:forEach items="${item1.subList}" var="item2">
									<c:if test="${item2.menuCd eq MENUCODE}"><c:set var="chkedmenu1" value="${item2.menuCd}"/></c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
        <ul class="navList">
	            	<c:forEach items="${menuList}" var="item" varStatus="mstatus">
							<c:set var="menuName1" value="${item.menuNm}"/>
							<c:forEach var="lang" items="${item.menuLangList}">
								<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
							</c:forEach>
							<c:set var="linkUrl" value="#" />	  
							<c:if test="${not empty item.menuUrl}">
								<c:url var="linkUrl" value="/lec/main/goMenuPage?mcd=${item.menuCd}" />
							</c:if>
							<c:if test="${ empty item.subList}">
			            		<li <c:if test="${chkedmenu1 eq item.menuCd }">class="on"</c:if>><a href="${linkUrl }"><img src="/tpl/003/img/class/icon_${item.leftMenuImg}.svg" alt="icon" />${menuName1 }</a></li>
			            	</c:if>
			            	<c:if test="${not empty item.subList}">
			            		<li><a href="#0"><img src="/tpl/003/img/class/icon_${item.leftMenuImg}.svg" alt="icon" />${menuName1 }<i class="xi-angle-down-min" aria-hidden="true"></i></a>
					                <ul class="sub">
										<c:forEach items="${item.subList}" var="item1">
											<c:set var="menuName2" value="${item1.menuNm}"/>
											<c:forEach var="lang2" items="${item1.menuLangList}">
												<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
											</c:forEach>
											<c:set var="linkUrl2" value="#" />
											<c:if test="${not empty item1.menuUrl}">
												<c:url var="linkUrl2" value="/lec/main/goMenuPage?mcd=${item1.menuCd}" />
											</c:if>
											<li <c:if test="${chkedmenu1 eq item1.menuCd }">class="current"</c:if>><a href="${linkUrl2}">${menuName2}</a></li>
										</c:forEach>					                
					                </ul>
					            </li>
			            	</c:if>	
			         </c:forEach>	
        </ul>           
    </div>
</div>

