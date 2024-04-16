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
										<li><span class="current">오시는 길</span></li>
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
								<li><a href="#">조직도</a></li>
								<li class="active"><a href="#">오시는 길</a></li>
								<li><a href="#">강사지원</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">오시는 길</h3>

						<div class="info_map">
							<div class="tab_cont">
								<div class="vc_row on">
									<!-- <div class="page_tit">
										<h1>오시는 길</h1>
									</div> -->
									<div class="map_content">
										<!-- <div class="map_view">지도</div> -->
										<!-- * 카카오맵 - 지도퍼가기 -->
										<!-- 1. 지도 노드 -->
										<div id="daumRoughmapContainer1712630748261" class="root_daum_roughmap root_daum_roughmap_landing"></div>

										<!--
											2. 설치 스크립트
											* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
										-->
										<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

										<!-- 3. 실행 스크립트 -->
										<script charset="UTF-8">
											new daum.roughmap.Lander({
												"timestamp" : "1712630748261",
												"key" : "2iueq"
											}).render();
										</script>
									</div>									

									<!-- <hr class="solid mt30 mb30"> -->
									<dl class="mt30">
										<dt>도로명</dt>
										<dd>경기도 성남시 분당구 판교역로 221, 투썬캠퍼스(삼평동, 투썬월드빌딩)</dd>
									</dl>

									<dl>
										<dt>지번</dt>
										<dd>경기도 성남시 분당구 삼평동 672-1(삼평동, 투썬월드빌딩)</dd>
									</dl>
		
									<dl>
										<dt>전화</dt>
										<dd>031-706-8400</dd>
									</dl>
									<dl>
										<dt>팩스</dt>
										<dd>031-729-9798</dd>
									</dl>
									<dl>
										<dt>이메일</dt>
										<dd>campus@twosun.com</dd>
									</dl>
									<dl>
										<dt>지하철</dt>
										<dd>신분당선 판교역 (판교테크노밸리) 4번 출구 도보 14분</dd>
									</dl>
									<dl>
										<dt>버스</dt>
										<dd>375, 380 메디포스트 (07520 네오위즈.NHN방면) 하차 도보 3분</dd>
									</dl>

								</div>
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
