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
										<li><span class="current">비전/미션</span></li>
									</ul>
								</nav>
							</div>
							<h4 class="title_h1">교육원 소개</h4>
						</div>
						<!-- //h1_area -->

						<div class="subMenu">
							<ul class="menu">
								<li><a href="#">투썬 Overview</a></li>
								<li class="active"><a href="#">비전/미션</a></li>
								<li><a href="#">전략과제</a></li>
								<li><a href="#">연혁</a></li>
								<li><a href="#">조직도</a></li>
								<li><a href="#">오시는 길</a></li>
								<li><a href="#">강사지원</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">비전/미션</h3>



						<div class="KDP_greeting">
							<ul>
								<li>
									<div class="card v01">
										<h5>Mission</h5>
										<p>학생 · 취준생 · 직장인 · <br>창업자의 진로 · 직업 · 창업 · 역량 <br>향상을 통해 사회에 기여한다.</p>
									</div>
								</li>
								<li>
									<div class="card v02">
										<h5>Vision</h5>
										<p>새로운 태양을 찾아 떠나는 <br>진로 & 직업 & 창업 여행의 <br>필수 동반자</p>
									</div>
								</li>
								<li>
									<div class="card v03">
										<h5>Core Value</h5>
										<p>소통 · 협력 · 창의</p>
									</div>
								</li>
								<li>
									<div class="card">
										<h5>Strategic Objectives</h5>
										<p>트렌드에 부합하는 <br>교육 및 고객 맞춤형 <br>교육 추구</p>
									</div>
								</li>
							</ul>    
						
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
