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
										<li><span class="current">발급조건​</span></li>
									</ul>
								</nav>
							</div>
							<h4 class="title_h1">국민내일배움카드​</h4>
						</div>
						<!-- //h1_area -->

						<div class="subMenu">
							<ul class="menu">
								<li><a href="#">내일배움카드란?</a></li>
								<li class="active"><a href="#">발급조건​</a></li>
								<li><a href="#">발급절차​</a></li>
								<li><a href="#">수강생 유의사항</a></li>
								<li><a href="#">모사답안 처리기준</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">발급조건​</h3>

						<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
						<div class="com11 bcLight">
							<h4>카드 발급조건이 있나요?</h4>
							<p class="subMenu_title txt-center">지원대상을 확인하고 증빙서류만 지참하면 OK!</p>

							<div class="com22 mt30 mb30">
								<div>
									<i class="material-symbols-outlined bcTeal2">factory</i>
									중소기업 재직자
								</div>
								<div>
									<i class="material-symbols-outlined bcBlue2">castle</i>
									만 45세 이상 대기업 재직자
								</div>
								<div>
									<i class="material-symbols-outlined bcPurple2" aria-hidden="true">storefront</i>
									고용보험료 체납액 없는 자영업자
								</div>
								<div>
									<i class="material-symbols-outlined bcOrange">house</i>
									비정규직 육아휴직자
								</div>
							</div>

							<div class="w90 bcWhite mra mla p4 txt-center lg">
								내일배움카드 발급대상확인 <br class="mobile-elem"><b class="fcBlue">국번없이 1350</b>
							</div>

						</div>


						<dl class="guide_info mt30">
							<div>
								<dt>지원대상자</dt>
								<dd>
									<b>다음 요건 중 어느 하나에 해당하는 <span class="fcBlue">고용보험 가입 근로자</span> (근로자 카드 신청일자 기준)</b><br>	
									<ul class="list-dot">
										<li>우선지원 대상기업에 재직중인 근로자</li>
										<li>기간제 근로계약을 체결한 기간제 근로자</li>
										<li>1주 동안의 소정 근로시간이 36시간 미만인 근로자</li>
										<li>(사업 내 모든 근로자를 대상으로 근로시간을 단축한 근로자는 제외)</li>
										<li>파견근로자보호 등에 관한 법률에 따른 파견근로자</li>
										<li>일용근로자 (근로자 카드 신청일 이전 60일 이내에 1개월 동일용근로내역이 있는 자)</li>
										<li>고용보험료 체납액이 없는 자영업자</li>
										<li>근로자 카드를 신청한 일로부터 180일 이내에 이직 예정인 자</li>
										<li>경영상의 이유로 90일 이상 무급휴직, 휴업중인 자</li>
										<li>대규모 기업에 재직중인 45세 이상인 자</li>
										<li>근로자카드 신청일로부터 고용보험 가입기간이 3년 이상이고,</li>
										<li>그 기간 동안에 사업주 및 근로자 개인 지원 훈련과정 수강이력이 없는 자</li>
										<li>육아휴직자</li>
										<li>일학습병행제 훈련에 참여중인 근로자</li>
										<li>고용위기지역 근로자</li>
										<li>고용보험미가입 근로자</li>
									</ul>
								</dd>
							</div>

							<div>
								<dt>증빙서류 확인하기</dt>
								<dd>
									<ul class="list-dot">
										<li>근로계약서( 근로계약 기간(시간) )<br>
											[ 사업주(사업장) 도장, 근로자의 성명 및 도장(사인), 주민등록번호 등 기재확인 ]</li>
										<li>'이직 예정자' 는 근로계약서 및 해고예고 통지서 (또는 이직예정확인서)</li>
										<li>'무급 휴직, 휴업자'는 휴직원, 노동위원회의 무급휴업 심의, 의결 통보서 등</li>
										<li>'육아휴직자'는 육아휴직확인서</li>
										<li>'일학습병행훈련에 참여한 근로자'는 훈련에 참여한 사실을 증빙할 수 있는 서류</li>

									</ul>
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
