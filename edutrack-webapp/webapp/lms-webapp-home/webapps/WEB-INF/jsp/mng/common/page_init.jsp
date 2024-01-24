<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@	taglib prefix="meditag" uri="tag-utils" %>
<%@	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	page import="egovframework.edutrack.comm.util.web.UserBroker"%>
<%@	page import="egovframework.edutrack.Constants"%>
<%@	page import="egovframework.edutrack.comm.util.web.StringUtil"%>
<%@	page import="egovframework.edutrack.comm.util.web.WebUtil"%>
<c:set var="MENUCODE" value="${sessionScope.MENUCODE}"/>
<c:set var="MENUNAME" value="${sessionScope.MENUNAME}"/>
<c:set var="MENUPATH" value="${sessionScope.LOCATION}"/>
<%
	pageContext.setAttribute("crlf", "\n");

	String LOCALEKEY = UserBroker.getLocaleKey((HttpServletRequest)pageContext.getRequest());
	String AJAXMESSAGE = Constants.AJAX_MESSAGE_USE;
	request.setAttribute("LOCALEKEY", LOCALEKEY);
	request.setAttribute("AJAXMESSAGE", AJAXMESSAGE);
	request.setAttribute("LANG_DEFAULT", Constants.LANG_DEFAULT);
	request.setAttribute("LANG_MULTIUSE", Constants.LANG_MULTIUSE);
	request.setAttribute("system_name", Constants.SYSTEM_NAME);
	request.setAttribute("MSG_SMS", Constants.MSG_SMS);
	request.setAttribute("MSG_EMAIL", Constants.MSG_EMAIL);
	request.setAttribute("MSG_NOTE", Constants.MSG_NOTE);
	request.setAttribute("USER_ORGNM",UserBroker.getUserOrgNm((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("USER_DOMAINNM",UserBroker.getUserDomainNm((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("USER_ORGCD",UserBroker.getUserOrgCd((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("TOP_LOGOSN",UserBroker.getUserTopLogo((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("SUB_LOGO1SN",UserBroker.getUserSubLogo1((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("SUB_LOGO2SN",UserBroker.getUserSubLogo2((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("ADM_LOGOSN",UserBroker.getUserAdmLogo((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("ADM_FOOTER",UserBroker.getUserAdmFooter((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("USER_NO",UserBroker.getUserNo((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("USER_NAME",UserBroker.getUserName((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute("MANAGE_DOMAIN",Constants.MGR_SITE_DOMAIN);
	if(StringUtil.nvl((String)session.getAttribute("CONTEXT_ROOT")).equals("")==false)
		request.setAttribute("CONTEXT_PATH", "/"+(String)session.getAttribute("CONTEXT_ROOT"));
	
	request.setAttribute(Constants.USER_DEVICE, UserBroker.getUserDevice((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute(Constants.USER_APP_TYPE, UserBroker.getAppType((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute(Constants.USER_OS, UserBroker.getUserOS((HttpServletRequest)pageContext.getRequest()));
	request.setAttribute(Constants.USER_BROWSER, UserBroker.getUserBrowser((HttpServletRequest)pageContext.getRequest()));
	
	String browser = "";
	String userAgent = request.getHeader("User-Agent");
	if (userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
		browser = "IE";
		request.setAttribute("AMPERSAND", "&amp;");
		request.setAttribute("BROWSER", browser);
	} else if (userAgent.indexOf("Opera") > 0) {
		browser = "Opera";
		request.setAttribute("AMPERSAND", "&");
		request.setAttribute("BROWSER", browser);
	} else if (userAgent.indexOf("Firefox") > 0) {
		browser = "Firefox";
		request.setAttribute("AMPERSAND", "&");
		request.setAttribute("BROWSER", browser);
	} else if (userAgent.indexOf("Safari") > 0) {
		if (userAgent.indexOf("Chrome") > 0) {
			browser = "Chrome";
			request.setAttribute("AMPERSAND", "&");
			request.setAttribute("BROWSER", browser);
		} else {
			browser = "Safari";
			request.setAttribute("AMPERSAND", "&");
			request.setAttribute("BROWSER", browser);
		}
	}

%>