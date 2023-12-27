<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils"%><%@
page import="egovframework.edutrack.comm.service.SysMenuMemService"%><%@
page import="egovframework.edutrack.modules.system.menu.service.SysMenuVO"%><%@
page import="egovframework.edutrack.comm.util.web.UserBroker"%><%@
page import="egovframework.edutrack.comm.util.web.StringUtil"%><%@
page import="egovframework.edutrack.modules.course.createcourse.service.UserCourseVO"%><%@
page import="egovframework.edutrack.modules.course.createcourse.service.CreateCourseService"%><%@
page import="egovframework.edutrack.comm.util.web.DateTimeUtil"%><%@
page import="java.util.List"%><%@
page import="java.util.Hashtable"%><%@
page import="java.util.Map"%><%@
include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
	String authGrpCd = UserBroker.getClassUserType(request);
	String userType = UserBroker.getUserType(request);

	if("".equals(authGrpCd)) authGrpCd = "GUEST";
	SysMenuMemService service = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	List<SysMenuVO> menuList = service.getLecMenuList(authGrpCd);
	request.setAttribute("menuList",menuList);

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
				<h2>
				<spring:message code="lecture.title.classroom"/>
				<button type="button" id="menu_btn" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-align-justify"></i></button>
				</h2>
				<div class="btn-group" style="width:100%;margin-bottom:10px;">
					<button type="button" class="btn btn-default btn-sm btn-block dropdown-toggle" data-toggle="dropdown">
						<spring:message code="course.title.course.shortcut" /> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
					<c:set var="creCrsCnt" value="0"/>
					<c:forEach var="item" items="${mycreCrsList}">
						<c:if test="${item.connectYn eq 'Y' || item.connectYn eq 'C'}">
							<c:set var="creCrsCnt" value="${creCrsCnt + 1}"/>
						<li><a href="<c:url value="/lec/main/goLecture?crsCreCd=${item.crsCreCd}&stdNo=${item.stdNo}" />">${item.crsCreNm} [${item.creYear}/${item.creTerm}]</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${creCrsCnt == 0}">
						<c:choose>
							<c:when test="${fn:contains(userType, 'TEACHER') == true || fn:contains(userType, 'TUTOR')}">
								<li><a href="#"><spring:message code="teacher.message.course.ing.nodata"/></a></li>
							</c:when>
							<c:otherwise><li><a href="#"><spring:message code="student.message.course.ing.nodata"/></a></li></c:otherwise>
						</c:choose>
					</c:if>
					</ul>
				</div>

				<c:forEach items="${menuList}" var="item">
					<c:forEach items="${item.subList}" var="item1">
						<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item1.menuCd}"/></c:if>
						<c:forEach items="${item1.subList}" var="item2">
							<c:if test="${item2.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item1.menuCd}"/></c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>
				<ul class="nav nav-pills nav-stacked" id="left-menu" style="margin-bottom:10px;">
				<c:forEach items="${menuList}" var="item">
					<c:set var="menuName1" value="${item.menuNm}"/>
					<c:forEach var="lang" items="${item.menuLangList}">
						<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
					</c:forEach>
					<c:if test="${CRSTYPE eq 'S'}">
						<c:if test="${item.optnCd1 eq 'Y'}">
				<li <c:if test="${item.menuCd eq MENUCODE}">class="active"</c:if>><a href="<c:url value="/lec/main/goMenuPage?mcd=${item.menuCd}" />">${menuName1}</a></li>
						</c:if>
					</c:if>
					<c:if test="${CRSTYPE eq 'R'}">
						<c:choose>
							<c:when test="${MOECOURSE eq 'Y'}">
								<c:if test="${item.optnCd5 eq 'Y'}">
				<li <c:if test="${item.menuCd eq MENUCODE}">class="active"</c:if>><a href="<c:url value="/lec/main/goMenuPage?mcd=${item.menuCd}" />">${menuName1}</a></li>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${(CRSMTHD eq 'ON' && item.optnCd1 eq 'Y') || (CRSMTHD eq 'OF' && item.optnCd2 eq 'Y') || (CRSMTHD eq 'BL' && item.optnCd3 eq 'Y')}">
				<li <c:if test="${item.menuCd eq MENUCODE}">class="active"</c:if>><a href="<c:url value="/lec/main/goMenuPage?mcd=${item.menuCd}" />">${menuName1}</a></li>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				</ul>
				<a href="<c:url value="/lec/main/logoutLecture" />" class="btn btn-sm btn-default btn-block"><i class="glyphicon glyphicon-log-out"></i> <spring:message code="lecture.title.classroom.out"/></a>
