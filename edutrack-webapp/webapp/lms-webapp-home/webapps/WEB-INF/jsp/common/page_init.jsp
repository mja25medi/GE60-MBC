<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%><%@
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@
	taglib prefix="meditag" uri="tag-utils" %><%@	
	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@	
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@
	page import="egovframework.edutrack.comm.util.web.UserBroker"%><%@
	page import="egovframework.edutrack.Constants"
%>
<c:set var="MENUCODE" value="${sessionScope.MENUCODE}"
/><c:set var="MENUNAME" value="${sessionScope.MENUNAME}"
/><c:set var="MENUPATH" value="${sessionScope.LOCATION}"
/><c:set var="MENUCHRGDEPT" value="${sessionScope.MENUCHRGDEPT}"
/><c:set var="MENUCHRGNAME" value="${sessionScope.MENUCHRGNAME}"
/><c:set var="MENUCHRGPHONE" value="${sessionScope.MENUCHRGPHONE}"
/><c:set var="ROOTMENUCODE" value="${sessionScope.ROOTMENUCODE}"
/><c:set var="ROOTMENUNAME" value="${sessionScope.ROOTMENUNAME}"
/><c:set var="USERNO" value="${sessionScope.USERNO}"
/><c:set var="USERNAME" value="${sessionScope.USERNAME}"
/><c:set var="USERTYPE" value="${sessionScope.USERTYPE}"
/><c:set var="MNGTYPE" value="${sessionScope.MNGTYPE}"
/><c:set var="USERID" value="${sessionScope.USERID}"
/><c:set var="CRSCD" value="${sessionScope.CRSCD}"
/><c:set var="CRSCRECD" value="${sessionScope.CRSCRECD}"
/><c:set var="STUDENTNO" value="${sessionScope.STUDENTNO}"
/><c:set var="CLASSUSERTYPE" value="${sessionScope.CLASSUSERTYPE}"
/><c:set var="CRSCRENM" value="${sessionScope.CRSCRENM}"
/><c:set var="CRSMTHD" value="${sessionScope.CRSMTHD}"
/><c:set var="CRSTYPE" value="${sessionScope.CRSTYPE}"
/><c:set var="CRSDURATION" value="${sessionScope.CRSDURATION}"
/><c:set var="ctx" value="${pageContext.request.contextPath}"
/><c:set var="VIEWAUTH" value="${sessionScope.VIEWAUTH}"
/><c:set var="CREAUTH" value="${sessionScope.CREAUTH}"/><%
String LOCALEKEY = UserBroker.getLocaleKey((HttpServletRequest)pageContext.getRequest());
String AJAXMESSAGE = Constants.AJAX_MESSAGE_USE;
request.setAttribute("LOCALEKEY", LOCALEKEY);
request.setAttribute("AJAXMESSAGE", AJAXMESSAGE);
request.setAttribute("LANG_DEFAULT", Constants.LANG_DEFAULT);
request.setAttribute("LANG_MULTIUSE", Constants.LANG_MULTIUSE);
request.setAttribute("MSG_SMS", Constants.MSG_SMS);
request.setAttribute("MSG_EMAIL", Constants.MSG_EMAIL);
request.setAttribute("MSG_NOTE", Constants.MSG_NOTE);

request.setAttribute("USER_ORGNM",UserBroker.getUserOrgNm((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("USER_DOMAINNM",UserBroker.getUserDomainNm((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("USER_ORGCD",UserBroker.getUserOrgCd((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("LAYOUT_TPL",(String)session.getAttribute("LAYOUTTPL"));
request.setAttribute("COLOR_TPL",(String)session.getAttribute("COLORTPL"));
request.setAttribute("TOP_LOGOSN",UserBroker.getUserTopLogo((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("SUB_LOGO1SN",UserBroker.getUserSubLogo1((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("SUB_LOGO1URL",UserBroker.getUserSubLogo1Url((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("SUB_LOGO2SN",UserBroker.getUserSubLogo2((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("SUB_LOGO2URL",UserBroker.getUserSubLogo2Url((HttpServletRequest)pageContext.getRequest()));
request.setAttribute("SUB_IMGSN",(String)session.getAttribute("SUBIMGSN"));
request.setAttribute("SUB_TXTIMGSN",(String)session.getAttribute("SUBTXTIMGSN"));
request.setAttribute("SUB_TXTIMGSN",(String)session.getAttribute("SUBTXTSTR"));
request.setAttribute("LEC_IMGSN",(String)session.getAttribute("LECIMGSN"));
request.setAttribute("LEC_TXTIMGSN",(String)session.getAttribute("LECTXTIMGSN"));
request.setAttribute("LEC_TXTSTR",(String)session.getAttribute("LECTXTSTR"));
request.setAttribute("WWW_FOOTER",(String)session.getAttribute("WWWFOOTER"));
request.setAttribute("MBR_APLC_USE",(String)session.getAttribute("MBRAPLCUSE"));

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
