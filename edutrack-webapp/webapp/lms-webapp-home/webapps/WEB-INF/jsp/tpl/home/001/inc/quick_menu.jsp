<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils"%><%@
page import="egovframework.edutrack.modules.system.config.service.SysCfgService"%><%@
page import="egovframework.edutrack.modules.system.config.service.SysCfgVO"%><%@
page import="egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO"%><%@
page import="java.util.List"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
	SysCfgCtgrVO ccdto = new SysCfgCtgrVO();
	ccdto.setCfgCtgrCd("MENUNO");
	SysCfgService configService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysCfgService.class);
	List<SysCfgVO> configList = configService.listConfig(ccdto).getReturnList();
	String loginMenuCode = "";
	String mypageMenuCode = "";
	String historyMenuCode = "";
	String noticeMenuCode = "";
	String searchMenuCode = "";
	for(SysCfgVO cfgVO : configList) {
		if("LOGIN".equals(cfgVO.getCfgCd())) loginMenuCode = cfgVO.getCfgVal();
		if("MYPAGE".equals(cfgVO.getCfgCd())) mypageMenuCode = cfgVO.getCfgVal();
		if("HISTORY".equals(cfgVO.getCfgCd())) historyMenuCode = cfgVO.getCfgVal();
		if("NOTICE".equals(cfgVO.getCfgCd())) noticeMenuCode = cfgVO.getCfgVal();
		if("CRSSEARCH".equals(cfgVO.getCfgCd())) searchMenuCode = cfgVO.getCfgVal();
	}
	request.setAttribute("loginMenuCode", loginMenuCode);
	request.setAttribute("mypageMenuCode", mypageMenuCode);
	request.setAttribute("historyMenuCode", historyMenuCode);
	request.setAttribute("noticeMenuCode", noticeMenuCode);
	request.setAttribute("searchMenuCode", searchMenuCode);
%>
<c:if test="${empty sessionScope.USERNO}">
<%-- 	<c:url var="mypageMenu" value="/home/main/goMenuPage?mcd=${loginMenuCode}&amp;goMcd=${mypageMenuCode}"/> --%>
<%-- 	<c:url var="historyMenu" value="/home/main/goMenuPage?mcd=${loginMenuCode}&amp;goMcd=${historyMenuCode}"/> --%>
	<c:url var="mypageMenu" value="/home/main/indexMain"/>
	<c:url var="historyMenu" value="/home/main/indexMain"/>
</c:if>
<c:if test="${not empty sessionScope.USERNO}">
	<c:url var="mypageMenu" value="/home/main/goMenuPage?mcd=${mypageMenuCode}"/>
	<c:url var="historyMenu" value="/home/main/goMenuPage?mcd=${historyMenuCode}"/>
</c:if>
		<div id="sidemenu">
			<div class="serviceType">
				<dl>
					<dt><spring:message code="common.title.quick.menu"/></dt>
					<dd class="icon01"><a href="<c:url value="/home/main/goMenuPage?mcd=${searchMenuCode}"/>"><span><spring:message code="common.title.quick.enroll"/></span><br/><spring:message code="common.title.quick.enroll"/></a></dd>
					<dd class="icon02"><a href="${mypageMenu}"><span><spring:message code="common.title.quick.mypage"/></span><br/><spring:message code="common.title.quick.mypage"/></a></dd>
					<dd class="icon03"><a href="${historyMenu}"><span><spring:message code="common.title.quick.certi"/></span><br/><spring:message code="common.title.quick.certi"/></a></dd>
					<dd class="icon04"><a href="<c:url value="/home/main/goMenuPage?mcd=${noticeMenuCode}"/>"><span><spring:message code="common.title.quick.notice"/></span><br/><spring:message code="common.title.quick.notice"/></a></dd>
					<dd><a href="#" id="toTop"><img src="/tpl/001/img/home/top_btn.png" alt="Top" /></a></dd>
				</dl>
			</div>
		</div>
