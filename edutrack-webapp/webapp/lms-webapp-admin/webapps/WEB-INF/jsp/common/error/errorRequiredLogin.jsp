<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<%
String errorCode = exception.getMessage();
try {
	errorCode = exception.getMessage().split(":")[1].replaceAll(" ", "");
} catch (Exception e) {}
request.setAttribute("errorCode", errorCode);
%>
<html lang="">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="description" content="Mediopia Tech Edutrack Global" />
	<meta name="author"	content="Mediopia Tech" />
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="No-Cache" />
	<title>LMS Management System</title>

	<!-- Bootstrap Core	CSS	-->
	<link rel="stylesheet" href="/libs/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css" type="text/css" />
	<!-- MetisMenu CSS -->
	<link rel="stylesheet" href="/libs/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css" type="text/css" />
	<!-- Custom	CSS	-->
	<link rel="stylesheet" href="/libs/bootstrap/dist/css/sb-admin-2.css" type="text/css" />
	<!-- Custom	Fonts -->
	<link rel="stylesheet" href="/libs/bootstrap/bower_components/font-awesome/css/font-awesome.min.css" type="text/css" />
	<!-- Jquery Ui CSS -->
	<link rel="stylesheet" href="/css/jquery-ui-1.11.0.custom/jquery-ui.css" type="text/css" />
	<!-- Edutrack CSS -->
	<link rel="stylesheet" href="/css/edutrack.css" type="text/css" />

	<!-- HTML5 Shim	and	Respond.js IE8 support of HTML5	elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if	lt IE 9]>
		<script	src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	<script type="text/JavaScript" src="/app/js/Context.js"></script>
	<script type="text/JavaScript" src="/js/common.js"></script>
	<script type="text/JavaScript" src="/js/common_conf.js"></script>
	<script type="text/JavaScript" src="/js/common_util.js"></script>
	<script type="text/JavaScript" src="/js/common_function.js"></script>

	<!-- jQuery	-->
	<script type="text/JavaScript" src="/libs/bootstrap/bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/JavaScript" src="/libs/bootstrap/bower_components/jquery/dist/jquery.form.js"></script>
	<script type="text/JavaScript" src="/libs/bootstrap/bower_components/jquery/dist/jquery-ui.min.js"></script>
	<script type="text/JavaScript" src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>
</head>

<body  >
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" style="padding:5px 15px 5px 15px;">
                    	Error!
                    </div>
                    <div class="panel-body wordbreak">
                    	<h4><p>
						<%
							try {
						%>		
							<spring:message code="${errorCode}"/><br/><br/>
						<%
							} catch (Exception e) {
						%>
							${errorCode}<br/><br/>
						<%
							}
						%>                    	
                    	</p></h3>
                    	<button class="btn btn-lg btn-success btn-block" id="btnOk"><spring:message code="button.close"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
		$(document).ready(function(){
			$("#btnOk").bind("click", eventBacktohome);
			function eventBacktohome(event) {
				window.close();
			}
		});	
	</script>	
</body>
</html>