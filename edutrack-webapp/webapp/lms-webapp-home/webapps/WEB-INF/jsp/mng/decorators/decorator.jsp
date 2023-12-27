<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<!DOCTYPE html>

<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Script-Type" content="text/javascript" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <meta name="author" content="메디오피아테크" />
    <meta name="description" content="메디오피아 LMS7입니다." />
    <meta name="keywords" content="기업LMS, 학교LMS, 온라인컨텐츠개발, LINC관련 사업개발" />
    <title>LMS7n</title>

    <link rel="shortcut icon" href="/home/001/img/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/class-default.css" />
    
    
    <!-- Stylesheets -->
    <link rel="stylesheet" type="text/css" href="/home/001/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/jquery-ui-slider-pips.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/element-ui.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/footable.standalone.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/jquery.mCustomScrollbar.min.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/semantic.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/ionicons.css" />
    <link rel="stylesheet" type="text/css" href="/home/001/css/class-default.css" />  
    
    <!-- Scripts -->
    <script type="text/javascript" src="/home/001/js/jquery.min.js"></script>
    <script type="text/javascript" src="/home/001/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/home/001/js/jquery-ui-slider-pips.js"></script>
    <script type="text/javascript" src="/home/001/js/common.js"></script>
    <script type="text/javascript" src="/home/001/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="/home/001/js/classie.js"></script>
    <script type="text/javascript" src="/home/001/js/gnmenu.js"></script>
    <script type="text/javascript" src="/home/001/js/semantic.min.js"></script>
    <script type="text/javascript" src="/home/001/js/semantic-ui-calendar.min.js"></script>
    <script type="text/javascript" src="/home/001/js/footable.min.js"></script>
    <script type="text/javascript" src="/home/001/js/jquery.ui.touch-punch.min.js"></script>
    <script type="text/javascript" src="/home/001/js/iframe.js"></script>
    <script type="text/javascript" src="/home/001/js/chart.min.js"></script>
    <script type="text/javascript" src="/home/001/js/Chart.PieceLabel.min.js"></script>
    <script type="text/javascript" src="/home/001/js/slick.min.js"></script>
    <script type="text/javascript" src="/home/001/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="/home/001/js/modal.js"></script>
    <script type="text/javascript" src="/home/001/js/moment.min.js"></script>
    <script type="text/javascript" src="/home/001/js/timeline.js"></script>
    <script type="text/javascript" src="/home/001/js/jquery.dynamiclist.js"></script>
    <script type="text/javascript" src="/home/001/js/jquery.star-rating-svg.js"></script>
    

</head>
<body>
	<div id="loading_page">
		<p><i class="notched circle loading icon"></i></p>
	</div>
	<div id="wrap" class="pusher">	
        <!--  div id="container" -->
	    	<!-- 본문 content 부분 -->
	        <div class="content">
	        	MAIN context page <br/>
				<sitemesh:write property="body"/>
				
				<!-- confirm() -->
				<a href="#0" class="ui red button alert-btn" style="display: none;">선택 경고창</a>
				<div id="alert-box" class="warning"><p>선택된 부분을 정말 삭제하시겠습니까?</p>
				    <a href="#0" id="alertOk" class="ui inverted small button w80 ml20">예</a>
				    <a href="#0" id="alertNo" class="ui inverted small button w80">아니오</a>
				    <a id="close"><i class="ion-ios-close-empty"></i></a>
				</div>

				<!-- alert() -->
				<a href="#0" class="ui red button note-btn" style="display: none;">확인 경고창</a>
				<div id="note-box" class=""><p>아이디를 입력해주세요.</p><a id="close"><i class="ion-ios-close-empty"></i></a></div>
				
			</div>
		<!--  /div	-->	
	</div>	
               	
</body>
</html>	
