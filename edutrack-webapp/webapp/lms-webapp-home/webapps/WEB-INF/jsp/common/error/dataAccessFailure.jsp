<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
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
	<title>Edutrack</title>

	<!-- Bootstrap Core	CSS	-->
	<link rel="stylesheet" href="<c:url value="/libs/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css"/>" type="text/css" />
	<!-- MetisMenu CSS -->
	<link rel="stylesheet" href="<c:url value="/libs/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css"/>" type="text/css" />
	<!-- Custom	CSS	-->
	<link rel="stylesheet" href="<c:url value="/libs/bootstrap/dist/css/sb-admin-2.css"/>" type="text/css" />
	<!-- Custom	Fonts -->
	<link rel="stylesheet" href="<c:url value="/libs/bootstrap/bower_components/font-awesome/css/font-awesome.min.css"/>" type="text/css" />
	<!-- Jquery Ui CSS -->
	<link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.11.0.custom/jquery-ui.css"/>" type="text/css" />
	<!-- Edutrack CSS -->
	<link rel="stylesheet" href="<c:url value="/css/edutrack.css"/>" type="text/css" />

	<!-- HTML5 Shim	and	Respond.js IE8 support of HTML5	elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if	lt IE 9]>
		<script	src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	<script type="text/JavaScript" src="<c:url value="/app/js/Context.js"/>"></script>
	<script type="text/JavaScript" src="<c:url value="/js/common.js"/>"></script>
	<script type="text/JavaScript" src="<c:url value="/js/common_conf.js"/>"></script>
	<script type="text/JavaScript" src="<c:url value="/js/common_util.js"/>"></script>
	<script type="text/JavaScript" src="<c:url value="/js/common_function.js"/>"></script>

	<!-- jQuery	-->
	<script type="text/JavaScript" src="<c:url value="/libs/bootstrap/bower_components/jquery/dist/jquery.min.js"/>"></script>
	<script type="text/JavaScript" src="<c:url value="/libs/bootstrap/bower_components/jquery/dist/jquery.form.js"/>"></script>
	<script type="text/JavaScript" src="<c:url value="/libs/bootstrap/bower_components/jquery/dist/jquery-ui.min.js"/>"></script>
	<script type="text/JavaScript" src="<c:url value="/js/jquery/jquery-custom/jquery.input-1.0.js"/>"></script>
	<script type="text/JavaScript">
		$(document).ready(function(){
			$("#btnOk").bind("click", eventBacktohome);
			function eventBacktohome(event) {
				top.document.location.href="/";
			}
		});	
	</script>	
	<style>
	.error_info {width:60%; margin:8% auto 0 auto; border-top:4px solid #d2d2cf; border-bottom:4px solid #d2d2cf; font-family: '나눔고딕','Nanum Gothic','맑은 고딕','Malgun Gothic','돋움',Dotum,AppleGothic,Arial,sans-serif;}
	.error_txt {width:100%; padding:40px 20px 50px 300px; background:url(/img/error_img.png) no-repeat 50px center; box-sizing:border-box;}
	.error_txt h3 {font-size:30px; letter-spacing:-1.35px; line-height:1.4; margin-bottom:20px; color:#1886d7;}
	.error_txt h3 span {font-size:16px; font-weight:400; color:#222;}
	.error_txt p {font-size:16px; line-height:1.8 color:#666;}
	.error_txt a {font-size:14px; padding:6px 35px; background:#183e75; border-radius:2px; color:#fff;}
	.error_txt a:hover {background:#40669e;}
	@media all and (max-width:1217px){
	.error_info {width:80%;}
	}
	@media all and (max-width:768px){
	.error_txt {width:100%; padding:180px 20px 20px 20px; background:url(images/error_img.png) no-repeat center 30px;}
	.error_txt h3 {font-size:24px; letter-spacing:-1.35px; line-height:1.4;}
	.error_txt p {font-size:14px;}
	.error_txt a {position:relative; left:50%; margin-left:-55px;}
	}
	</style>
</head>
<body  >
    <div class="container">
        <div class="row">
            <div class="error_info">
			<div class="error_txt">
				<h3>
<!-- 				<span>지식협력단지 지식 정보 서비스</span><br/> -->
				이용에 불편을 드려 죄송합니다.</h3>
				<!-- 안내문구 start -->
				<p>
				정상적인 홈페이지지 접속이 아니거나 세션이 종료 되었습니다. <br/>
				확인버튼을 클릭하여 다시 접속해 주세요.<br/><br/>
				</p>
				<a href="/home/main/goMenuPage?mcd=MC00000000">HOME</a>
				<!-- //안내문구 End -->		
			</div>		
		</div>
        </div>
    </div>
</body>
</html>