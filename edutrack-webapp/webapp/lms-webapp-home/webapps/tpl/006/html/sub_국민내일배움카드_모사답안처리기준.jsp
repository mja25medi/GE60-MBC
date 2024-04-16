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
										<li><span class="current">모사답안 처리기준</span></li>
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
								<li><a href="#">발급절차​</a></li>
								<li><a href="#">수강생 유의사항</a></li>
								<li class="active"><a href="#">모사답안 처리기준</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">모사답안 처리기준</h3>

						<div class="training_area box">
						
							<dl class="guide_info">
								<div>
									<dt>
										베낀답안이란?
									</dt>
									<dd>
										수강생의 리포트 답안 내용이 인터넷 또는 타인의 답안과 동일하거나, 일부 문구만을 수정하여 제출한 경우 확인하여 베낀처리 하는 것을 의미합니다.
									</dd>
								</div>
	
								<div>
									<dt>
										베낀답안방지안내
									</dt>
									<dd>
										<h5 class="point">자사는 베낀답안에 대해 철저히 관리하고 있습니다.</h5>
										베낀답안이 발생하여 수료할 수 없을 시 어떠한 경우도 책임지지 않습니다.
									</dd>
									<dd class="mt30">
										<h5 class="point">베낀답안 발생시</h5>
										<ul class="txt_num_list">
											<li><span class="num">1.</span>베낀답안이 발생할 경우 해당 문제 및 과제가 0점 처리됩니다.</li>
											<li><span class="num">2.</span>베낀답안이 발생하여 수료점수 미달 시 재시험 및 과제 재제출은 없습니다.</li>
											<li><span class="num">3.</span>베낀답안이 발생하여 수료점수 미달 시 미수료로 처리됩니다.</li>
										</ul>
									</dd>
									<dd class="mt30">
										<h5 class="point">베낀답안 발생시 처리기준</h5>
										<ul class="txt_num_list">
											<li><span class="num">1.</span>주관식(서술형)문제 및 과제(서술형)에 대한 띄어쓰기, 오타, 특수문자 등이 동일할 경우​</li>
											<li><span class="num">2.</span>파일 속성, 크기가 완전 일치하는 동일 파일​</li>
											<li><span class="num">3.</span>과제 내용이 완전히 동일한 경우​</li>
											<li><span class="num">4.</span>우연으로 보기에는 상식 이하의 오답이 동일하게 발견된 경우​​</li>
											<li><span class="num">5.</span>다른 사람이 했다면 완전히 같게 될 수 없는 것이 동일하게 발견되는 경우​
												(예: 수많은 도형의 위치, 선 굵기 등이 완전 일치)​</li>
										</ul>

										<p class="mt20">
											위 항목 100% 일치 했을 시 베낀답안으로 판단, 베낀답안 추출 프로그램을 통한 베낀답안 추출 진행
										</p>
									</dd>

									<dd class="mt30">
										<h5 class="point">베낀답안 예방 방법</h5>

										<div class="staff_benefit type2 mt10">
											<ul>
												<li>										
													<div class="tit">
														<strong>학습자 공지</strong>
														교육시작시 안내문을 통해 베낀기준 및 처리에 대한 내용을 학습자에게 공지합니다.
													</div>
												</li>
												<li>										
													<div class="tit">
														<strong>문제은행 구축 및 랜덤 출제</strong>
														시험 및 과제의 경우 문제은행을 구축하여 랜덤방식으로 선택되어 제공되지 때문에 동일한 시험문제 및 과제를 통한 평가를 최소화 합니다.
													</div>
												</li>
												<li>										
													<div class="tit">
														<strong>학습자 모드에서 복사 금지</strong>
														학습자가 시험응시 및 과제 제출시 웹상에서의 복사기능 제어 진행
													</div>
												</li>
												<li>										
													<div class="tit">
														<strong>교수자 상세 필터링</strong>
														교수자의 검색으로 베낀답안 확인 절차를 진행합니다.
													</div>
												</li>
											</ul>
										</div>
										





									</dd>


								</div>
							</dl>


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
