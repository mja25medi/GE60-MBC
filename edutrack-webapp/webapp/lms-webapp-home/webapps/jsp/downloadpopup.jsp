<%@ page contentType="text/html; charset=euc-kr"%>
<%
request.setCharacterEncoding("utf-8");
String path=request.getParameter("path");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script type="text/javascript">
<!--

	window.open("<%=path%>" , "aaa" , "location=no");
	self.close();
//-->
</SCRIPT>
</HEAD>

<BODY>

</BODY>
</HTML>