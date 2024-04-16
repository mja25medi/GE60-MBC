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
										<li><span class="current">수강생 유의사항</span></li>
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
								<li class="active"><a href="#">수강생 유의사항</a></li>
								<li><a href="#">모사답안 처리기준</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">수강생 유의사항</h3>

						<div class="training_area box">
							<dl class="guide_info">
								<div>
									<dt>
										학습수료기준
									</dt>
									<dd>
										<ul class="list-dot">
											<li>학습수료기준에는 진도율, 중간(진행단계)평가, 최종평가, 과제 제출이 포함됩니다.</li>
											<li>진도율 80% 이상 충족, 중간평가(10%), 최종평가(40%), 과제(50%) 점수를 합산하여 총점 평균 60점 이상일 경우 수료됩니다.</li>
										</ul>
									</dd>
								</div>
	
								<div>
									<dt>
										평가 및 과제제출
									</dt>
									<dd>
										평가 및 과제제출은 필수이며 하나라도 충족하지 못할 경우 미수료 됩니다. 중간(진행단계)평가는 전체 진도율 50%이상부터 응시 가능하며 최종평가 및 과제제출은 진도율 80% 이상부터 응시 가능합니다.
	
										<table class="tbl w100 mt30">
											<colgroup>
												<col style="width:25%">
												<col style="width:25%">
												<col style="width:25%">
												<col style="width:25%">
											</colgroup>
											<thead>
												<tr>
													<th>구분</th>
													<th>중간(진행단계)평가</th>
													<th>최종평가</th>
													<th>과제</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><strong>응시조건</strong></td>
													<td>진도율 50% 이상</td>
													<td colspan="2">진도율 80% 이상</td>
												</tr>
												<tr>
													<td><strong>시험유형</strong></td>
													<td>객관식</td>
													<td>객관식, 주관식</td>
													<td>서술형</td>
												</tr>
												<tr>
													<td><strong>시험시간</strong></td>
													<td>60분</td>
													<td>60분</td>
													<td>-</td>
												</tr>
												<tr>
													<td><strong>총점 반영율</strong></td>
													<td>10%</td>
													<td>40%</td>
													<td>50%</td>
												</tr>
											</tbody>
										</table>
	
										<ul class="info_list mt30"> 
											<li>평가 및 과제는 단 1회만 응시 가능 (제출 후에 수정 불가)</li>
											<li>평가 응시 도중 접속이 종료될 경우 제한시간 내에 재접속하여 시험 응시 가능 (※평가 창이 닫혀있어도 평가시간은 계속 진행)</li>
											<li>과제 제출일은 최종으로 작성한 임시저장일로 기록됩니다.</li>
											<li>과제 제출 시 복사/붙여넣기 기능은 금지되어 있으니 과제 제출에 유의하시길 바랍니다.</li>
										</ul>
									</dd>
								</div>

								<div>
									<dt>
										본인인증 및 보안절차
									</dt>
									<dd>
										<ul class="list-dot">
											<li>대리수강 및 기타 부정행위 방지를 위한 본인인증절차가 있으며 1일 1회 나의강의실 접속 시에 휴대폰 또는 아이핀 본인인증을 해야만 수강 가능합니다. (* 부정한 방법으로 학습 후 환급받을 시에 1년간 환급 카드 사용이 제한될 수 있음)</li>
											<li>실제 학습 진행여부 파악을 위한 보안절차(CAPCHA)인증은 학습 진행 시 진도 8차시 단위마다 적용됩니다. 보안인증이 이루어지지 않을 경우 학습이 제한됩니다. (예시 : 1차시 수강 전, 9차시 수강 전, 17차시 수강 전 ...)</li>
										</ul>
									</dd>
								</div>

								<div>
									<dt>
										1일 학습 진도 및 최소 학습시간 적용
									</dt>
									<dd>
										<ul class="list-dot">
											<li>한 과정별 하루에 수강할 수 있는 강의는 총 8강으로 제한됩니다.</li>
											<li>수강 강좌를 각 회차별 최소 13분 이상 수강해야하며, 모든 페이지를 보셔야만 진도율이 반영되어 수강완료로 전환됩니다.</li>
										</ul>
									</dd>
								</div>

								<div>
									<dt>
										수강신청 제한
									</dt>
									<dd>
										수강 기간 내에 신청할 수 있는 과정은 최대 3개이며 타 사이트 과정도 포함됩니다.
									</dd>
								</div>

								<div>
									<dt>
										동시수강제한
									</dt>
									<dd>
										부정훈련 방지를 위해 수강 중에는 동시접속 및 동시수강은 제한됩니다.
									</dd>
								</div>

								<div>
									<dt>
										미수료, 수강포기 등 중도탈락에 대한 패널티
									</dt>
									<dd>
										기존 미수료 및 수강포기에 따른 패널티가 변경되었으며 카드발급일 기준 2019.01.15. 일자 이후 카드 발급자부터 변경된 기준에 따라 적용됩니다.

										<table class="tbl mt30">
											<colgroup>
												<col style="width:20%">
												<col style="width:40%">
												<col style="width:40%">
											</colgroup>
											<thead>
												<tr>
													<th>미수료 및 <br>수강포기횟수</th>
													<th>개정 전 <br>(근로자카드발급일 2019.1.14이전)</th>
													<th>개정 후 <br>(근로자카드발급일 2019.1.15이전)</th>
												</tr>
											</thead>
											<tbody>
												
												<tr>
													<td><strong>1회</strong></td>
													<td class="txt-left">- 지원한도액 20만원 차감 <br>- 사유 발생일로부터 60일간 카드 사용 중지</td>
													<td class="txt-left">- 지원한도액 <span class="fcRed">20만원 차감</span></td>
												</tr>
												<tr>
													<td><strong>2회</strong></td>
													<td class="txt-left">- 지원한도액 30만원 차감<br>- 사유 발생일로부터 60일간 카드 사용 중지 <br>- 패널티 발생 이후 훈련과정 등록 시, 자비 부담금 20% 추가 발생 <br>※ 해당 훈련과정 수료 시 환급</td>
													<td class="txt-left">- 지원한도액 <span class="fcRed">50만원 차감</span><br>- 사유 발생일로부터 <span class="fcRed">60일간 카드 사용 중지</span> <br>- 패널티 발생 이후 훈련과정 등록 시, 자비 부담금 20% 추가 발생 <br>※ 해당 훈련과정 수료 시 환급</td>
												</tr>
												<tr>
													<td><strong>3회</strong></td>
													<td class="txt-left">- 지원한도액 30만원 차감<br>- 사유 발생일로부터 60일간 카드 사용 중지 <br>- 패널티 발생 이후 훈련과정 등록 시, 자비부담금 20% 추가 발생 <br>※ 해당 훈련과정 수료 시 환급</td>
													<td class="txt-left">- 지원한도액 <span class="fcRed">100만원 차감</span><br>- 사유 발생일로부터 60일간 카드 사용 중지 <br>- 패널티 발생 이후 훈련과정 등록 시, 자비부담금 20% 추가 발생 <br>※ 해당 훈련과정 수료 시 환급</td>
												</tr>
											</tbody>
										</table>

										<p class="mt10">※ 미수료패널티 기준은 신규카드발급자(2019.01.15.)부터 적용됩니다.</p>
										<p>※ 기존 카드발급자가 2019.01.15. 일자 이후 재발급 받은 카드로 훈련에 참여하여 미수료 또는 수강포기 할 경우에도 기존 패널티 규정이 적용됩니다. 또한 이전에 발급 받은 카드에서 발생된 카드에서 발생한 패널티는 횟수에 포함되지 않고 1회로 적용됩니다.<br>(추가로 신규 발급 받은 카드로 훈련참여 시 패널티 자부담 미발생)</p>

									</dd>
								</div>

								<div>
									<dt>
										모사답안처리기준
									</dt>
									<dd>
										과제제출은 1회만 가능하며 모사답안프로그램을 통해 모사답안으로 확정된 경우 처리기준에 따라 미수료 처리되오니 답안 제출로 인한 불이익 없으시길 바랍니다.

										<div class="gray_box mt10">
											<b>[모사답안 기준]</b><br>
											<ol>
												<li>① 모사답안필터링 시스템에 의하여 학습자간 모사답안으로 확정된 경우</li>
												<li>② 문항별 및 전체 답안이 80% 이상 동일한 경우</li>
												<li>③ 오타, 띄어쓰기, 특수문자 등 내용이 유사하거나 동일한 경우</li>
												<li>④ 단락의 앞뒤 구성을 바꿔 동일한 내용의 답안을 제출한 경우</li>
												<li>⑤ 사고력 측정형, 사례 제시형, 현업 적용형과 같은 문제 유형의 답안이 80%이상 동일한 경우</li>
											</ol><br>
											<b>[모사답안 처리방침]</b><br>
											모사답안 기준에 따라 모사답안으로 확정된 경우, 모사답안에 관련된 학습자(제출자, 답안제공자)는 모두 0점 처리 및 미수료 처리됩니다.
											
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
