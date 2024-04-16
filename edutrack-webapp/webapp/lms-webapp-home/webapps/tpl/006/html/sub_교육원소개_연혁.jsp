<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
	<%@ include file="../inc/home_common.jsp" %>
	<link rel="stylesheet" href="../css/sub.css" /><!-- sub 페이지에서 사용 -->
	<body>
		<div id="wrap">
			<%@ include file="../inc/hrd_header.jsp" %>

			<main class="main">
				<div id="contentWrap" class="container">
					<!-- content -->
					<div id="content" class="content">
						<div class="h1_area">
							<div class="location_bar">
								<nav class="location">
									<ul>
										<li><i class="xi-home-o" aria-hidden="true"></i><span class="sr-only">Home</span></li>
										<li>교육원 소개</li>
										<li><span class="current">연혁</span></li>
									</ul>
								</nav>
							</div>
							<h4 class="title_h1">교육원 소개</h4>
						</div>
						<!-- //h1_area -->

						<div class="subMenu">
							<ul class="menu">
								<li><a href="#">투썬 Overview</a></li>
								<li><a href="#">비전/미션</a></li>
								<li><a href="#">전략과제</a></li>
								<li class="active"><a href="#">연혁</a></li>
								<li><a href="#">조직도</a></li>
								<li><a href="#">오시는 길</a></li>
								<li><a href="#">강사지원</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">투썬캠퍼스 연혁</h3>
						<div class="info_story">
								
                            <div class="history_page">
                                <div class="history">
                                    <div class="left">
                                        <p><span>TWOSUNCAMPUS</span><br>History</p>
                                    </div>
                                    <div class="info">
                                        <div class="line">
                                            <div class="bg"></div>
                                        </div>
                                        <ul>                                                
                                            <li class="">
                                                <div class="num">2023</div>
                                                    <div class="txt">                                        
                                                    <p>AI 분야 특화 교육, 아카데미, 클래스, 세미나 등을 운영하는 투썬AI스쿨 런칭</p> 
                                                    <p>(재)예술경영지원센터, 2023 예술분야 초기창업 지원사업 운영</p>    
                                                    <p>투썬캠퍼스 분당센터, 직업능력심사평가원 주관 인증평가 ‘5년 인증 우수훈련기관’ 선정</p>                                                                                               
                                                </div>
                                            </li>                                        
                                            <li class="">
                                                <div class="num">2022</div>
                                                    <div class="txt">                                        
                                                    <p>경기창조경제혁신센터, 2022 창업존 글로벌 테스트베드 운영</p>
                                                    <p>서울산업진흥원, 서울창업허브 창동 캠퍼스타운 콜라보 프로그램 운영</p>   
                                                    <p>창업진흥원, 공공기술활용 청년기술창업경진대회 운영</p>                                                                                                 
                                                </div>
                                            </li>                                        
                                            <li class="">
                                                <div class="num">2021</div>
                                                    <div class="txt">                                        
                                                    <p>한국정보보호산업협회, 2021년 정보보호 클러스터 운영</p>                                                
                                                    <p>경기창조경제혁신센터, 판교 창업존 글로벌 테스트베드 운영</p>    
                                                    <p>창업진흥원, 실전창업교육 온라인 연계 IR 데모데이 운영</p>                                                                                                
                                                </div>
                                            </li>                                        
                                            <li class="">
                                                <div class="num">2020</div>
                                                    <div class="txt">                                        
                                                    <p>한국인터넷진흥원, 2020년 정보보호 클러스터(판교 기업지원허브) 운영</p>                                                
                                                    <p>(재)예술경영지원센터, 2020 예술분야 창업기업 액셀러레이팅 지원사업 운영</p>                                                
                                                    <p>창업진흥원, 2020 비대면 청년창업 콘테스트 서면 및 발표평가 운영</p>                                                                                                   
                                                </div>
                                            </li>                                        
                                            <li class="">
                                                <div class="num">2019</div>
                                                    <div class="txt">                                        
                                                    <p>중소벤처기업부 지정 엑셀러레이터 등록 (제2019-29호)</p>                                                
                                                    <p>중소기업진흥공단 주관 인천창업사관학교 운영, 서울앱비즈니스센터 운영</p>                                                
                                                    <p>원격교육형태 평생교육시설 등록</p>                                                                                                    
                                                </div>
                                            </li>                                        
                                            <li class="">
                                                <div class="num">2018</div>
                                                    <div class="txt">                                        
                                                    <p>건국대, 연세대, 서울과기대, 중앙대 등 대학생 대상 TBA과정운영</p>                                                
                                                    <p>한국경영정보학회 주관, 2018 경영정보대상 수상 </p>                                                
                                                                                                                                                
                                                </div>
                                            </li>                                        
                                                
                                        </ul>
                                    </div>
                                </div>
                                
                            </div>

                            <script>
                                function history(){
                                    var historyInfo = document.querySelector('.history_page .history .info');
                                    var historyInfoOffTop = $(historyInfo).offset().top
                                    var infoLi = document.querySelectorAll(".history .info li")
                                    var contOffTop = 0;
                                    var contOffTopMap = {}; // 객체를 사용하여 값을 저장할 변수 생성
                            
                                    infoLi.forEach(function (cont, index){
                                        contOffTop = Math.floor($(cont).offset().top - historyInfoOffTop);
                                        contOffTopMap[index] = contOffTop;
                                    })
                            
                                    var line = document.querySelector(".history .info .line")
                                    var computedStyle = window.getComputedStyle(line);
                                    var lineHeight = parseFloat(computedStyle.getPropertyValue('line-height')) * 0.6
                                    var keys = Object.keys(contOffTopMap);
                                    var lastKey = keys[keys.length - 1];
                                    line.style.height = contOffTopMap[lastKey] - lineHeight + "px";
                                    var scrollPercentageStart;
                            
                                    if ($(window).width() > 768){
                                        scrollPercentageStart = 0.85
                                    } else{
                                        scrollPercentageStart = 0.7
                                    }
                                    console.log(scrollPercentageStart)

                                    window.addEventListener('scroll', function() {
                            
                                        var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
                                        var historyInfoHeight = historyInfo.clientHeight;
                                        var viewportHeight = window.innerHeight;
                                        var lineBg = document.querySelector(".history .info .line .bg")
                                        var scrollPercentage = (scrollTop - (historyInfoOffTop * scrollPercentageStart))  / ((historyInfoHeight * 0.95) - (viewportHeight * 0.6)) * 100;
                            
                                        lineBg.style.height = scrollPercentage + '%';
                            
                                        infoLi.forEach(function (cont, index){
                            
                                            var contPercentage =  Math.round(((contOffTopMap[index]) / ((historyInfoHeight)) * 100));
                                            if (scrollPercentage > contPercentage){
                                                cont.classList.add("active")
                                                var paragraphElement = document.querySelector('.history_page .history .left p');
                                                if(index > 1 ) {
                                                    paragraphElement.innerHTML = '<span class="year">2018 ~ 2023</span>';
                                                } else {
                                                    paragraphElement.innerHTML = '<span>TWOSUNCAMPUS</span><br>History';
                                                }
                                            } else {
                                                cont.classList.remove("active")
                                            }
                                        })
                            
                                        if(scrollPercentage < 0) {
                                            lineBg.style.height = 0;
                                        }
                                    });
                                }
                                $(document).ready(function(){
                                    history();
                                });
                            
                                $(window).on('resize', function () {
                                    history();
                                });
                            </script>

						</div>
					</div>
					<!-- //content -->
				</div>
				<!-- //contentWrap -->
			</main>

			<%@ include file="../inc/hrd_footer.jsp" %>
		</div>
	</body>
</html>
