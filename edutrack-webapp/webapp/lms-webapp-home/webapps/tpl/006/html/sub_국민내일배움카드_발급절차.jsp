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
										<li>국민내일배움카드​</li>
										<li><span class="current">발급절차​</span></li>
									</ul>
								</nav>
							</div>
							<h4 class="title_h1">국민내일배움카드​</h4>
						</div>
						<!-- //h1_area -->

						<div class="subMenu">
							<ul class="menu">
								<li><a href="#">내일배움카드란?</a></li>
								<li><a href="#">발급조건​</a></li>
								<li class="active"><a href="#">발급절차​</a></li>
								<li><a href="#">수강생 유의사항</a></li>
								<li><a href="#">모사답안 처리기준</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">발급절차​</h3>

						<div class="apply_process type2 mt0">
							
							<ul class="top">
								<li>
									<div class="step">STEP. 01</div>
									<div class="ctt">
										<strong>국민내일배움카드 발급신청(온라인/오프라인)</strong>
										<ul class="list-dot">
											<li>
												<b>온라인 신청 : </b>HRD-Net 홈페이지 로그인 후, 메인의 ‘발급신청’ 아이콘을 통해 국민내일배움카드 신청
											</li>
											<li>
												<b>오프라인 신청 : </b>방문전 해당 고용센터로 문의후 방문신청
											</li>
										</ul>
									</div>
								</li>
								<li>
									<div class="step">STEP. 02</div>
									<div class="ctt">
										<strong>국민내일배움카드 발급</strong>
										<ul class="list-dot">
											<li>발급관련 진행절차 등은 신청중인 고용센터로 문의</li>
											<li>
												<b>대표전화 : </b> 1350
											</li>
										</ul>
									</div>
								</li>
								<li>
									<div class="step">STEP. 03</div>
									<div class="ctt">
										<strong>훈련과정 검색 및 수강신청</strong>
										<ul class="list-dot">
											<li>바른법률HR 홈페이지 회원가입 후 로그인</li>
											<li>훈련절차 및 훈련비용 등 확인 후 수강신청</li>
											<li>원하는 과정 선택 [ 교육과정 바로가기 ] <!-- <a href="/course/course_list.jsp" target="_blank" class="job_link">[ 교육과정 바로가기 <i class="xi-external-link" aria-hidden="true"></i> ]</a> --> - 동시 수강은 3과정으로 제한 </li>
										</ul>
									</div>
								</li>
								<li>
									<div class="step">STEP. 04</div>
									<div class="ctt">
										<strong>훈련과정 수강</strong>
										<ul class="list-dot">
											<li>총 훈련 비용중 본인부담 금액만 결제</li>
											<li>수료 필수 - 중도포기에 따른 패널티 있음</li>
										</ul>
									</div>
								</li>
							</ul>
						</div>


						<dl class="guide_info mt30">
							<div>
								<dt>
									중도포기에 따른 패널티 안내
								</dt>
								<dd>
									<table class="tbl w100">
										<colgroup>
											<col >
											<col class="w30">
										</colgroup>
										<thead>
											<tr>
												<th>구분</th>
												<th>차감액</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="txt-left">1. 질병·사고, 훈련기관 사정, 천재지변 등 불가피한 사유 없이 중도에 훈련 수강을 그만둔 경우</td>
												<td class="txt-left">1회 20만원<br>2회 50만원<br>3회 이상 100만원</td>
											</tr>
											<tr>
												<td class="txt-left">2. 거짓이나 그 밖의 부정한 방법으로 출결체크하여 제36조제1항제3호에 따라 제적된 경우</td>
												<td class="txt-left">전액</td>
											</tr> 
											<tr>
												<td class="txt-left">3. 제4조의 지원대상에 해당하지 않거나 제5조의 지원제외 대상에 해당함에도 불구하고 거짓이나 그 밖의 부정한 방법으로 계좌를 발급받거나, 지원받은 경우</td>
												<td class="txt-left">전액</td>
											</tr>                             
										</tbody>
									</table>
								</dd>
							</div>

							<div>
								<dt>
									수강생 유의사항
								</dt>
								<dd>
									<ul class="list-dot">
										<li>훈련세부일정에 따라 본인이 직접 훈련을 수강하여야하며, 대리 또는 허위 수강등을 하는 경우에는 노동부로부터 훈련비용의 지원을 받을 수 없습니다.</li>
										<li>평가에는 반드시 참여해야 하며, 지방노동관서의 장으로부터 지정 받은 훈련기간이 종료하기 전까지 모든 평가에 참여한 수료생 중 수료기준에 도달한 경우에 한하여 노동부로부터 훈련비용의 지원을 받을 수 있습니다.</li>
										<li>학습보고서, 평가지 등의 대리 또는 허위 작성 기타 부정한 방법으로 수료하여 훈련비용의 지원을 받거나 받고자 하는 경우, 향후 1년간 고용보험상의 직업능력개발훈련 비용지원을 받을 수 없게 될 수도 있습니다.</li>
									</ul>
								</dd>
							</div>
						</dl>

						<div class="btns mt30">
							<a href="http://www.hrd.go.kr/" target="_blank"  title="새창으로 열림"class="btn type4">근로자카드 발급신청 바로가기</a>
						</div>

						<div class="w90 bcWhite mra mla p4 txt-center lg">
							근로자카드 발급문의 <b class="fcBlue">(국번없이) 1350</b>
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
