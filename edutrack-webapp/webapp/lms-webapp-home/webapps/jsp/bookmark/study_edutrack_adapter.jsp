<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<html>
<head>
<title></title>
<meditag:js	src="/app/js/Context.js" />
<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
<meditag:js	src="/js/jquery/jquery.form.js" />
<meditag:js	src="/js/jquery/jquery.json-2.2.min.js"	/>
<meditag:js	src="/js/common_conf.js" />
<meditag:js	src="/js/common_util.js" />
<meditag:js src="/js/scorm/edutrack_adapter.js"/>
<script type="text/javascript">
	function initEdutrackAPIAdapter() {
		parent.edutrackAPI = new EdutrackAPIAdapter();
	}
</script>
</head>
<body onLoad="initEdutrackAPIAdapter()">
</body>
</html>