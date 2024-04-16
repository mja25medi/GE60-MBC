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
										<li><span class="current">전략과제</span></li>
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
								<li class="active"><a href="#">전략과제</a></li>
								<li><a href="#">연혁</a></li>
								<li><a href="#">조직도</a></li>
								<li><a href="#">오시는 길</a></li>
								<li><a href="#">강사지원</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">전략과제</h3>

							
						<dl class="guide_info">
							<div class="learning_card">
								<dt class="txt-center mb20 lg"><span class="lg">진로 · 취업 지원</span></dt>
								<dd>
									<ol class="curriculum type3">
										<li class="step">
											<div class="circle type1">
												<b aria-hidden="true"></b>
												<span class="cnt">
													<p><strong>국비지원교육사업</strong></p>
													<ul class="list-dot">
														<li>IT, AI, 빅데이터, 메타버스 등 4차산업혁명 기술 교육</li>
														<li>오프라인 교육 센터 운영 및 온라인 교육 서비스의 운영</li>
														<li>청년들의 진로 설정 및 직업 선택에 실질적인 기여 도모</li>
													</ul>
												</span>
											</div>
										</li>
										<li class="step">
											<div class="circle type2">
												<b aria-hidden="true"></b>
												<span class="cnt">
													<p><strong>AI 플랫폼 운영사업</strong></p>
													<ul class="list-dot">
														<li>생성 AI에 관한 교육, 커뮤니티 플랫폼 운영</li>
														<li>AI 역량 향상에 기여하는 수준 높은 교육 프로그램 구현</li>
														<li>강의와 실무를 결합한 교육으로 교육의 실질적 효과 추구</li>
													</ul>
												</span>
											</div>
										</li>
									</ol>
								</dd>

								<dt class="txt-center mt50 mb20"><span class="lg">창업지원</span></dt>
								<dd>
									<ol class="curriculum type3">
										<li class="step">
											<div class="circle type1 gradient3">
												<b aria-hidden="true"></b>
												<span class="cnt">
													
													<p><strong>액셀러레이팅 사업</strong></p>
													<ul class="list-dot">
														<li>Company Builder 형태의 체계적인 보육 시스템 운영</li>
														<li>Seed 단계의 스타트업에 대한 투자 및 보육을 통한 성장지원</li>
														<li>투썬캠퍼스 자체 보육 공간의 효율적인 운영<br></li>
													</ul>
												</span>
											</div>
										</li>
										<li class="step">
											<div class="circle type2 gradient4">
												<b aria-hidden="true"></b>
												<span class="cnt">
													<p><strong>창업보육사업</strong></p>
													<ul class="list-dot">
														<li>정부창업지원사업 참여를 통한 스타트업 육성</li>
														<li>스타트업의 성장을 유도하는 다양한 보육 시스템 운영</li>
														<li>스타트업의 보육을 통한 우량 기업 발굴 및 투자 실현</li>
													</ul>
												</span>
											</div>
										</li>
									</ol>
								</dd>

							</div>			
							
						</dl>								
						
					</div>
					<!-- //content -->
				</div>
				<!-- //contentWrap -->
			</main>

			<%@ include file="../inc/hrd_footer.jsp" %>
		</div>
	</body>
</html>
