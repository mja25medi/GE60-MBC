<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%
  if (request.getParameter("gc") != null) {
    System.gc();
    System.runFinalization();
  }

	Properties sysprop = new Properties(System.getProperties());
	String path = application.getRealPath("/")  + "sys.txt";
	OutputStream os = new FileOutputStream(path);
	PrintStream ps = new PrintStream(os);
	sysprop.list(ps);
	os.close();

%>

<HTML>
<HEAD>
<!--META http-equiv="Refresh" content="10;url=<%= request.getRequestURI() %>"-->
<STYLE TYPE="text/css">
<!--
	BODY {
		font-size : 9pt;
		font-family : 굴림;	}
	FONT {
		line-height : 16px;
		font-size : 9pt;
		font-family : 굴림;	}

	TH {
		font-size : 9pt;
		font-weight: bold;
		font-family : 굴림;	}
	TD {
		font-size : 9pt;
		font-family : 굴림;	}

	INPUT, TEXTAREA, SELECT {
		border:0x
		solid black;
		font-size : 9pt;
		font-family : 굴림;	}

	A:LINK, A:ACTIVE, A:VISITED {
		color: #003399;
		font-size: 9pt;
		text-decoration: none; }
	A:HOVER {
		text-decoration: underline;
		color: #3399ff; }
	H2 {
		font-size:11pt;
		font-family:굴림;
		text-decoration: underline; }
	SMALL {
		font-size:8pt;
		font-family:굴림; }

	.SOURCE {
		line-height : 16px;
		font-size : 12pt;
		font-family : Fixedsys;
		color: #1B057C;
	/*background-color: #1B057C;*/ }

-->
</STYLE>
</HEAD>

<body leftmargin=15 topmargin=10>
<center><p>

<table width="600" cellpadding="7" cellspacing="0" border="1" bordercolordark="WHITE" bordercolorlight="BLACK">
<tr><td>
■ 호스트 : <%= InetAddress.getLocalHost().getHostName() %>
(<%= InetAddress.getLocalHost().getHostAddress() %>)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<% SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss", Locale.KOREA); %>
■ 측정시각 : <%= formatter.format(new Date()) %>
</td></tr>
<tr><td> 
■ 시스템 정보 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="sys.txt">System properties File</a>
<table  width='100%' border="1" cellspacing="0" cellpadding="5" frame=hsides bordercolor="gray">
<!--  -->
	<TR>
		<TD>SYSTEM OS NAME(Ver) : </TD><TD><%=System.getProperty("os.name")%>&nbsp;(<%=System.getProperty("os.version")%>)</TD>
	</TR>
	<TR>
		<TD>user name : </TD><TD><%=System.getProperty("user.name")%></TD>
	</TR>
<!--  -->
	<TR>
		<TD>JAVA Version(JVM Version) : </TD>
		<TD><%=System.getProperty("java.runtime.name")%><BR>
		    <%=System.getProperty("java.vm.vendor")%><BR>
			<%=System.getProperty("java.fullversion")%>&nbsp;&nbsp;(<%=System.getProperty("java.vm.version")%>)
		</TD>
	</TR>
	<TR>
		<TD>JAVA HOME : </TD><TD><%=System.getProperty("java.home")%></TD>
	</TR>
	<TR>
		<TD>JAVA Library Path : </TD><TD><%=System.getProperty("sun.boot.library.path")%></TD>
	</TR>
<!--  -->
	<TR>
		<TD>WAS Info :</TD><TD><%=getServletConfig().getServletContext().getServerInfo()%></TD>
	</TR>
	<!--
	<TR>
		<TD>JEUS HOME : </TD><TD><%=System.getProperty("jeus.home")%></TD>
	</TR>
	<TR>
		<TD>JEUS SERVLETHOME : </TD><TD><%=System.getProperty("jeus.servlethome")%></TD>
	</TR>
	<TR>
		<TD>JEUS EJBHOME : </TD><TD><%=System.getProperty("jeus.ejbhome")%></TD>
	</TR>
	<TR>
		<TD>JEUS Baseport : </TD><TD><%=System.getProperty("jeus.baseport")%></TD>
	</TR>	
	<TR>
		<TD>JSP Engine Ver :</TD><TD><%=getServletInfo()%></TD>
	</TR>

	 -->
	
	<TR>
		<TD>System user language : </TD><TD><%=System.getProperty("user.language")%></TD>
	</TR>
	<TR>
		<TD>System user region : </TD><TD><%=System.getProperty("user.region")%></TD>
	</TR>
	<TR>
		<TD>System file encoding : </TD><TD><%=System.getProperty("file.encoding")%></TD>
	</TR>
	<TR>
		<TD>current WAS char encoding : </TD><TD><%=response.getCharacterEncoding()%></TD>
	</TR>
</TABLE>
</td></tr>
<tr><td align=center>

<%
   Runtime rt = Runtime.getRuntime();
   long free = rt.freeMemory();
   long total = rt.totalMemory();
   long usedRatio = (total - free) * 100 / total;
   long unusedRatio = free * 100 / total;
%>

<table width=100% bgcolor="lightgrey" border=1 cellpadding=6 cellspacing=0>
<tr>
<td align="center" colspan="2">전체 가상머신 메모리 (<b><%= total/1024 %> KB</b>)</td>
</tr>
<tr bgcolor=#E3E3E3>
<td align="center">사용중인 메모리 (<b><%= (total - free)/1024 %> KB</b>)</td>
<td align="center">남아있는 메모리 (<b><%= free/1024 %> KB</b>)</td>
</tr>
<tr bgcolor=#E8EEEC>
<td><hr color="#CC3366" align=left size=10 width="<%= usedRatio %>%" noshade>
(<%= usedRatio %> %)</td>
<td><hr color="#0066FF" align=left size=10 width="<%= unusedRatio %>%" noshade>
(<%= unusedRatio %> %)</td>
</tr>
</table>
<BR>
<a href="<%= request.getRequestURI() %>?gc=exec"> 가비지컬렉션 실행</a>
&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;
<a href="<%= request.getRequestURI() %>"> 다시 읽기</a>

</td></tr>
</table>

</center>
</BODY>
</HTML>