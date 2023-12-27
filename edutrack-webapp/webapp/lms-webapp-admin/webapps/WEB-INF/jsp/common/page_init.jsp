<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>
<%@	page import="egovframework.edutrack.comm.util.web.UserBroker"%>
<%@	page import="egovframework.edutrack.Constants"%>
<c:set var="MENUCODE" value="${sessionScope.MENUCODE}"/>
<c:set var="MENUNAME" value="${sessionScope.MENUNAME}"/>
<c:set var="MENUPATH" value="${sessionScope.LOCATION}"/>
<c:set var="USERNO" value="${sessionScope.USERNO}"/>
<%
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
%>