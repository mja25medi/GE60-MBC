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
				<div class="profile">
	                <!-- <img src="/tpl/002/img/male.png">-->
	                <p class="profile_name">${USERNAME } 님</p> 
	                <!-- <p class="profile_date">최근접속 2021/12/14 10:12:59</p> -->
	            </div>
	            
	            <c:forEach items="${menuList}" var="item">
					<c:forEach items="${item.subList}" var="item1">
						<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu1" value="${item1.menuCd}"/></c:if>
						<c:forEach items="${item1.subList}" var="item2">
							<c:if test="${item2.menuCd eq MENUCODE}"><c:set var="chkedmenu1" value="${item1.menuCd}"/></c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>
	            <ul class="clearfix">
	            	<c:forEach items="${menuList}" var="item" varStatus="mstatus">
	            		<c:if test="${item.menuCd eq ROOTMENUCODE}">
							<c:set var="menuName1" value="${item.menuNm}"/>
							<c:forEach var="lang" items="${item.menuLangList}">
								<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
							</c:forEach>
							<c:set var="linkUrl" value="#" />
							<c:if test="${not empty item.subList}">
								<c:forEach items="${item.subList}" var="item1">
									<c:set var="menuName2" value="${item1.menuNm}"/>
									<c:forEach var="lang2" items="${item1.menuLangList}">
										<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
									</c:forEach>
									<c:set var="linkUrl2" value="#" />
									<c:if test="${not empty item1.menuUrl}">
										<c:url var="linkUrl2" value="/lec/main/goMenuPage?mcd=${item1.menuCd}" />
									</c:if>
									<c:choose>
										<c:when test="${empty item1.subList}">
											<li <c:if test="${chkedmenu1 eq item1.menuCd }">class="open"</c:if>><a href="${linkUrl2}"><span>${menuName2}</span></a></li>
										</c:when>
										<c:otherwise>
											<li class="sub-menu <c:if test="${chkedmenu1 eq item1.menuCd }">open</c:if>">
						                    	<a class="sub-menu" href="#0"><span>${menuName2}</span></a>                    
						                    	<ul>
						                    		<c:forEach items="${item1.subList}" var="item2">
														<c:set var="menuName3" value="${item2.menuNm}"/>
														<c:forEach var="lang3" items="${item2.menuLangList}">
															<c:if test="${LOCALEKEY eq lang3.langCd}"><c:set var="menuName3" value="${lang3.menuNm}"/></c:if>
														</c:forEach>
														<c:set var="linkUrl3" value="#" />
														<c:if test="${not empty item2.menuUrl}">
															<c:url var="linkUrl3" value="/lec/main/goMenuPage?mcd=${item2.menuCd}" />
														</c:if>
														<li><a href="${linkUrl3}"><span <c:if test="${MENUCODE eq item2.menuCd }">style="color: black;"</c:if>>${menuName3}</span></a></li>
													</c:forEach>
						                    	</ul>
	               					 		</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
						</c:if>
					</c:forEach>
	            </ul> 
