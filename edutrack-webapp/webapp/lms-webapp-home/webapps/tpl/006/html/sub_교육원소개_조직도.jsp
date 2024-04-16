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
										<li><span class="current">조직도</span></li>
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
								<li><a href="#">연혁</a></li>
								<li class="active"><a href="#">조직도</a></li>
								<li><a href="#">오시는 길</a></li>
								<li><a href="#">강사지원</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">조직도</h3>



						<div class="comp99">

							<div class="grade">
								<div class="order header2">
									<em class="main2">대표이사</em>
								</div>
							</div>
							<div class="grade">
								<div class="order">
									<em class="sub lTemp">이사</em>
								</div>
								<div class="order">
									<em class="sub rTemp">자문위원</em>
								</div>				
								<dl class="order group">
									<div class="sub">
										<dt class="main2">전산</dt>
										<dd class="sub">LMS 관리</dd>
										<dd class="sub">홈페이지 관리</dd>
									</div>
									<div class="sub">
										<dt class="main2">운영</dt>
										<dd class="sub">교육일정관리</dd>
										<dd class="sub">수강생 관리</dd>
										<dd class="sub">교육운영</dd>
										<dd class="sub">HDR 행정</dd>
									</div>
									<div class="sub">
										<dt class="main2">마케팅</dt>
										<dd class="sub">고객관리</dd>
										<dd class="sub">계약관리</dd>
										<dd class="sub">홍보관리</dd>
									</div>
									<div class="sub">
										<dt class="main2">기획 및 총무</dt>
										<dd class="sub">과정기획</dd>
										<dd class="sub">과정관리</dd>
										<dd class="sub">경영지원</dd>
									</div>						
								</dl>				
							</div>				
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
