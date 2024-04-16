<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
	<%@ include file="../inc/home_common.jsp" %>
	<link rel="stylesheet" href="../css/main.css" /><!-- main 페이지에서 사용 -->
	<body>
		<div id="wrap">
			<%@ include file="../inc/hrd_header.jsp" %>

			<main class="main">
				<div id="content">
					<!-- mainvisual -->
					<div class="mainvisual">
						<!-- 슬라이드 이동버튼 추가_240408 -->
						<div
							class="cycle-slideshow"
							data-cycle-slides="> .slide"
							data-cycle-speed="1000"
							data-cycle-timeout="4000"
							data-cycle-pause-on-hover="false"
							data-cycle-prev="#cycle-prev" 
    						data-cycle-next="#cycle-next" 
							data-cycle-stop=".btnStop"
							data-cycle-next=".btnPlay"
							data-cycle-pager="#adv-custom-pager"
							data-cycle-pager-template="<a href='#'></a>" 
							>
							<a href="https://www.naver.com/" target="_blank" class="slide vm01" style="background-image: url(../img/new_main/v_img01.png), url(../img/new_main/v_img01-bg.png)">
								<div class="txt-box">
									<p>
										<span class="hash"># 나 빼고 모두 탑승증!</span>
										<span class="hash"># 국비지원</span>
									</p>
									<p class="tit">커리어 초고속 점프 UP!<br>
										투썬캠퍼스에 합류하세요!
									</p>
									<p class="content">
										성장에 필요한 모든 것을 투썬이 지원합니다.
									</p>
									<!-- <a href="#" class="r_btn">자세히 보기 <i class="icon"></i></a> -->
								</div>
							</a>
							
							<a href="#" target="_blank" class="slide vm03" style="background-image: url(../img/new_main/v_img02.png), url(../img/new_main/v_img02-bg.png)">
								<div class="txt-box">
									<p>										
										<span class="hash"># 국비지원</span>
										<span class="hash"># 대한민국 국민 누구나!</span>
									</p>
									<p class="tit fcWhite">내일배움카드로 수강료가 0원!<br>
										열정만 가지고 오세요!</p>
									<p class="content fcWhite">
										독보적인 실전 중심 투썬 강의!<br />
										직무역량과 취업 준비까지 투썬캠퍼스에서 한번에 끝내세요!
									</p>
									<!-- <a href="#" class="r_btn">자세히 보기 <i class="icon"></i></a> -->
								</div>
							</a>

							<a href="#" target="_blank" class="slide vm02" style="background-image: url(../img/new_main/v_img03.png), url(../img/new_main/v_img03-bg.png)">
								<div class="txt-box">
									<p>										
										<span class="hash"># 내일배움카드</span>
										<span class="hash"># 1:1밀착관리</span>
									</p>
									<p class="tit">찾아 헤매던 몰입도 높은<br>
										국비지원 교육 여기!</p>
									<p class="content">
										탄탄한 기본기 & 프로젝트 경험을 갖춘 실무자로 <br />
										성장하려면 투썬캠퍼스를 선택하세요.
									</p>
									<!-- <a href="#" class="r_btn">자세히 보기 <i class="icon"></i></a> -->
								</div>
							</a>
						</div>

						<!-- 이동버튼 추가_240408 -->
						<div id="cycle-prev" class="cycle-arrows cycle-prev"></div>
    					<div id="cycle-next" class="cycle-arrows cycle-next"></div>
						<!-- //이동버튼 추가_240408 -->

						<div class="container">

							<div class="cont-box">
								<div class="pagerWrap">
									<span id="adv-custom-pager" title="롤링배너 버튼"></span>
									<button class="btnStop" data-cycle-cmd="pause">슬라이드 멈춤</button>
									<button class="btnPlay blind" data-cycle-cmd="resume">슬라이드 다시 시작</button>
								</div>
								<script type="text/javascript">
									//bigSlide button
									$(".pagerWrap .btnStop").click(function () {
										$(this).addClass("blind");
										$(".pagerWrap .btnPlay").removeClass("blind");
									});
									$(".pagerWrap .btnPlay").click(function () {
										$(this).addClass("blind");
										$(".pagerWrap .btnStop").removeClass("blind");
									});
								</script>
							</div>
						</div>

						<!-- 로그인 -->
						<div class="login_wrap">
							<div class="con">
								<div class="tit-header">
									<p class="tit">회원로그인</p>
									<!-- <div class="tit_img"></div> 태그 삭제_240408 -->
								</div>
								<div class="login_form">
									<div class="login_input">
										<span class="form-floating">
											<input type="text" id="loginInputId" class="form-control" placeholder="아이디를 입력해주세요." />
										</span>
										<span class="form-floating">
											<input type="password" id="loginInputPw" class="form-control" placeholder="비밀번호를 입력해주세요." />
										</span>
									</div>
									<div class="login_bottom">
										<div class="custom-input idchk_save"><input type="checkbox" id="loginChkIdSave" /><label for="loginChkIdSave">아이디 저장</label></div>
										
									</div>
									<div class="entry_btn">
										<button type="submit" title="로그인">로그인</button>
									</div>

									<!-- 태그구조수정 및 위치이동함_240408 -->
									<div class="login_foot">
										<a href="#" class="btn"><span>ID/PW 찾기</span></a>	
										<a href="#" class="btn"><span>회원가입</span></a>
									</div>
									<!-- //태그구조수정 및 위치이동함_240408 -->
								</div>
							</div>
						</div>
						<!-- //로그인 -->

					</div>
				</div>
				<!-- //mainvisual -->



				<!-- 퀵메뉴 추가_240408 -->
				<nav class="quick">
					<a href="" class="btn">
						<i class="icon-png-memopaper" aria-hidden="true"></i>수강안내
					</a>
					<a href="" class="btn">
						<i class="icon-png-papercheck" aria-hidden="true"></i>수강신청​
					</a>
					<a href="" class="btn">
						<i class="icon-png-speaker" aria-hidden="true"></i>공지사항​
					</a>
					<a href="" class="btn">
						<i class="icon-png-talktalk" aria-hidden="true"></i>과정상담신청​
					</a>
					<a href="" class="btn">
						<i class="icon-png-instagram" aria-hidden="true"></i>인스타그램​
					</a>
					<a href="" class="btn">
						<i class="icon-png-kakaotalk" aria-hidden="true"></i>1:1 카톡상담
					</a>
				</nav>
				<!-- //퀵메뉴 추가_240408 -->



				<!-- 신규 교육과정 -->
				<div class="section section01">
					<div class="container">
						<div class="con_area">
							<div class="slider_info">
								<span class="state">NEW</span>
								<h2>신규 교육과정</h2>
								<div class="desc">투썬의 신규 강의를 만나보세요!</div>
							</div>
							<ul class="slider_list">
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/newClass_01.0png" alt="[필수교육] 의료기관 및 요양기 관 종사자 CS 교육" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[필수교육] 의료기관 및 요양기 관 종사자 CS 교육</p>
												<p class="des2">의료기관, 요양기관 종사자가 알아야 할 고객만족 서비스의 개념과 실천</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>기업직업훈련카드</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/newClass_02.png" alt="[맨투맨 직무] 고객만족을 위한 병원안내 가이드" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[맨투맨 직무] 고객만족을 위한 병원안내 가이드</p>
												<p class="des2">본 과정은 의료기관에서 이루어지는 서비스에 대해 학습하는 과정입니다.</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>평생교육바우처</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/newClass_03.png" alt="[커뮤니케이션 클리닉] 진단툴로 풀어보는 소통의 혈액형" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[커뮤니케이션 클리닉] 진단툴로 풀어보는 소통의 혈액형</p>
												<p class="des2">
													본 과정은 교류분석을 기반으로 하여 학술적이고 이론적인 내용보다는 학습자가 쉽게 접근하고 이해할 수 있도록 실제 응용 가능한 커뮤니케이션 방법을
													설명하였고, 이고그램 진단프로그램 등을 활용하여 자신의 리더십 스타일 및 직원의 유형을 파악하여 그에 맞게 직원을 이끌어가는 방법을 파악할 수 있다.
												</p>
												<div class="card_labels">
													<span>비환급</span>
													<span>기업직업훈련카드</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/newClass_04.png" alt="X와 MZ의 비즈니스 커뮤니케이션과 리더십" />
												</div>
											</div>
											<div class="des">
												<p class="des1">X와 MZ의 비즈니스 커뮤니케 이션과 리더십</p>
												<p class="des2">
													MZ세대와 X세대의 커뮤니케이션 부재와 공감 부재가 기업의 업무 향상 저해 요소로 부각되고 있으며, 이에따라 각 세대별 입장을 알아보고 효과적인 비즈니스
													커뮤니케이션 방안을 소개합니다.
												</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>기업직업훈련카드</span>
													<span>비환급</span>
													<span>기업직업훈련카드</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/newClass_05.png" alt="의료종사자 직무필수핵심 의료법" />
												</div>
											</div>
											<div class="des">
												<p class="des1">의료종사자 직무필수핵심 의료법</p>
												<p class="des2">
													의료법 전반에 대한 중요 핵심 지식을 선택형 시뮬레이션 플립러닝 방식으로 배우가 선택해야 하는 답변에 따라 제공되는 스토리로 학습의 재미요소를
													가미하여 강의를 구성 실무에 적용할 수 있는 핵심 내용을 중심으로 강의를 구성했으며 시뮬레이션 형식 강의가 끝난다음 애니메이션 방식으로 플립러닝
													방식의 학습 설계를 도입해 핵심 내용에 대한 집중도를 높일 수 있도록 구성
												</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>기업직업훈련카드</span>
													<span>기업직업훈련카드</span>
													<span>기업직업훈련카드</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/contents/thumb_03.png" alt="[D코스] 인공지능 All-in-One 실무캠프" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[D코스] 인공지능 All-in-One 실무캠프</p>
												<p class="des2">가장 트렌디한 인공지능 실무 기술로 구성된 부트캠프로 3개 과정 중 1개 과정을 선택해 수강합니다.</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>기업직업훈련카드</span>
												</div>
											</div>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- 교육과정 없음 -->
				<div class="section section01">
					<div class="container">
						<div class="con_area">
							<div class="slider_info">
								<span class="state">Tag</span>
								<h2>00 교육과정</h2>
								<div class="desc">투썬의 00강의를 만나보세요!</div>
							</div>
							<div class="no-list">과정 준비중입니다.</div>
						</div>
					</div>
				</div>
				<!-- 5대 법정 의무교육 -->
				<div class="section section01">
					<div class="container">
						<div class="con_area">
							<div class="slider_info">
								<span class="state">필수교육</span>
								<h2>5대 법정 의무교육</h2>
								<div class="desc">고용인 5인 이상 사업장이라면 잊지 말고 한번에 교육받으세요!</div>
							</div>
							<ul class="slider_list">
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/esClass_01.png" alt="[필수교육] 산업안전보건교육" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[필수교육] 산업안전보건교육</p>
												<p class="des2">산업안전보건법 제 31조 의거 매분기 3-6시간 이상 교육 실시</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>평생교육바우처</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img
														src="../img/new_main/esClass_02.png"
														alt="[필수교육] 직장 내 성희롱
													예방교육" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[필수교육] 직장 내 성희롱 예방교육</p>
												<p class="des2">남녀고용평등법 제 13조 의거 연간 1회 1시간 이상 교육 실시</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>평생교육바우처</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/esClass_03.png" alt="[필수교육] 개인정보보호교육" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[필수교육] 개인정보보호교육</p>
												<p class="des2">개인정보보호법 제 28조 의거 연간 1회 1시간 이상 교육 실시</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>평생교육바우처</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/esClass_04.png" alt="[필수교육] 직장 내 장애인 인식개선교육" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[필수교육] 직장 내 장애인 인식개선교육</p>
												<p class="des2">
													본 교육과정은 「장애인고용촉진 및 직업재활법」 제5조의2에 의한 "직장 내 장애인 인식개선교육"으로서 장애인 인식개선을 돕기 위해 제작된
													법정의무교육과정입니다.
												</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>평생교육바우처</span>
												</div>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#0">
										<div class="card_item">
											<div class="image_area">
												<div class="img_area">
													<img src="../img/new_main/esClass_05.png" alt="[필수교육] 퇴직연금교육" />
												</div>
											</div>
											<div class="des">
												<p class="des1">[필수교육] 퇴직연금교육</p>
												<p class="des2">퇴직급여 보장법 제 32조 의거 연간 1회 1시간 이상 교육 실시</p>
												<div class="card_labels">
													<span>사업주훈련</span>
													<span>평생교육바우처</span>
												</div>
											</div>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>

				<!-- 펼쳐보기 버튼 사용안함_240408
				<div class="open_moreClass container">
					<button type="button" class="moreClass_btn"><span>펼쳐보기</span></button>
				</div>
				-->

				<!-- Best 강의-->
				<div class="section section02">
					<div class="container">
						<div class="title_info">
							<span class="state">HOT</span>
							<h2>BEST 추천 강의</h2>
							<div class="desc">투썬의 대표 강의들을 만나보세요!</div>
						</div>
						<ul class="best_list">
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_01.png" alt="일잘러 필수스킬 모음" />
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_02.png" alt="마케팅 바이블의 정석! 한번에 끝내는 디지털 마케팅" />
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_03.png" alt="하이브리드&멀티클라우드 연동과 활용" />
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_04.png" alt="8시간만에 끝내는 PPT 필수 스킬 모음" />
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_05.png" alt="의료 질 향상을 위한 의료정보관리" />
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_06.png" alt="chatGPT 바로써먹기" />
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_07.png" alt="직장인 회계 첫걸음" />
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="img_area">
											<img src="../img/new_main/bestClass_08.png" alt="상세페이지 절대공식" />
										</div>
									</div>
								</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- <script> 사용안함_240408
					$(function () {
						$(".moreClass_btn").click(function () {
							$(".section02").slideToggle(300);
						});
					});
				</script> -->

				<!-- 수강후기-->
				<div class="section section03">
					<div class="container">
						<div class="title_header">
							<div class="title_info">
								<h2>
									교육생 후기로 확인되는 <br />
									투썬의 특별함!
								</h2>
							</div>
							<div class="btn_area">
								<a href="#" class="r_btn">자세히 보기 <i class="icon"></i></a>
							</div>
						</div>
						<!-- 240215수정 -->
						<ul class="news_slider_list">
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="stuImg"><img src="../img/new_main/re_user_01.png" alt="후기 수료생 대체이미지01" /></div>
										<div class="desc">
											<p class="stuName">최*혁 수료생</p>
											<p class="review_bold">역시는 역시네요... <br />너무 잘듣고있습니다.</p>
											<p class="review_nomal">
												강의 앞 파트에서 궁금한 점이 생겨도 꾹 참고 순서대로 수강하 다보면, 자연스럽게 해결이 되는 흐름이 인상적입니다! 잘 정제된 수업 정리 노트(+선배의 쪽집게
												강의)를 입수한 것 같아요.
											</p>
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="stuImg"><img src="../img/new_main/re_user_02.png" alt="후기 수료생 대체이미지02" /></div>
										<div class="desc">
											<p class="stuName">김*솔 수료생</p>
											<p class="review_bold">역시는 역시네요... <br />너무 잘듣고있습니다.</p>
											<p class="review_nomal">
												강의 앞 파트에서 궁금한 점이 생겨도 꾹 참고 순서대로 수강하 다보면, 자연스럽게 해결이 되는 흐름이 인상적입니다! 잘 정제된 수업 정리 노트(+선배의 쪽집게
												강의)를 입수한 것 같아요.
											</p>
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="stuImg"><img src="../img/new_main/re_user_03.png" alt="후기 수료생 대체이미지03" /></div>
										<div class="desc">
											<p class="stuName">김*우 수료생</p>
											<p class="review_bold">역시는 역시네요... <br />너무 잘듣고있습니다.</p>
											<p class="review_nomal">
												강의 앞 파트에서 궁금한 점이 생겨도 꾹 참고 순서대로 수강하 다보면, 자연스럽게 해결이 되는 흐름이 인상적입니다! 잘 정제된 수업 정리 노트(+선배의 쪽집게
												강의)를 입수한 것 같아요.
											</p>
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="stuImg"><img src="../img/new_main/re_user_04.png" alt="후기 수료생 대체이미지04" /></div>
										<div class="desc">
											<p class="stuName">이*준 수료생</p>
											<p class="review_bold">역시는 역시네요... <br />너무 잘듣고있습니다.</p>
											<p class="review_nomal">
												강의 앞 파트에서 궁금한 점이 생겨도 꾹 참고 순서대로 수강하 다보면, 자연스럽게 해결이 되는 흐름이 인상적입니다! 잘 정제된 수업 정리 노트(+선배의 쪽집게
												강의)를 입수한 것 같아요.
											</p>
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#0">
									<div class="card_item">
										<div class="stuImg"><img src="../img/new_main/re_user_05.png" alt="후기 수료생 대체이미지05" /></div>
										<div class="desc">
											<p class="stuName">한*혁 수료생</p>
											<p class="review_bold">역시는 역시네요... <br />너무 잘듣고있습니다.</p>
											<p class="review_nomal">
												강의 앞 파트에서 궁금한 점이 생겨도 꾹 참고 순서대로 수강하 다보면, 자연스럽게 해결이 되는 흐름이 인상적입니다! 잘 정제된 수업 정리 노트(+선배의 쪽집게
												강의)를 입수한 것 같아요.
											</p>
										</div>
									</div>
								</a>
							</li>
						</ul>
						<!-- //240215수정 -->
					</div>
				</div>

				<!-- 공지사항 및 상담안내 -->
				<section class="section section04">
					<div class="container">
						<div class="left_area">
							<div class="listTab">
								<ul>
									<li class="select">
										<a href="#tab1">공지사항</a>
									</li>
									<li>
										<a href="#tab2">자료실</a>
									</li>
									<li>
										<a href="#tab3">Q&A</a>
									</li>
									<li>
										<a href="#tab4">FAQ</a>
									</li>
								</ul>
							</div>
							<div id="tab1" class="tab_content" style="display: block">
								<ul class="list_line" style="display: none">
									<li>
										<a href="#">
											<div class="tit">
												<p>비나리 컴퓨터 우리는 여우비 나비잠 별하 책방 나비잠 여우별 감사합니다 미쁘다 아슬라 별빛 산들림 컴퓨터 소록소록 나비잠 아리아 그루잠 미쁘다 예그리나</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
									<li>
										<a href="#">
											<div class="tit">
												<p>비나리 컴퓨터 우리는 여우비</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
								</ul>
								<!-- 등록된 내용이 없을때  style="display: none" 을 빼주세요.-->
								<div class="no_list">등록된 내용이 없습니다.</div>
								<!-- //등록된 내용이 없을때 -->
							</div>
							<div id="tab2" class="tab_content">
								<ul class="list_line">
									<li>
										<a href="#">
											<div class="tit">
												<p>비나리 컴퓨터 우리는 여우비</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
									<li>
										<a href="#">
											<div class="tit">
												<p>비나리 컴퓨터 우리는 여우비</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
								</ul>
							</div>
							<div id="tab3" class="tab_content">
								<ul class="list_line">
									<li>
										<a href="#">
											<div class="tit">
												<p>비나리 컴퓨터 우리는 여우비 나비잠 별하 책방 나비잠 여우별 감사합니다 미쁘다 아슬라 별빛</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
									<li>
										<a href="#">
											<div class="tit">
												<p>나비잠 별하 책방 나비잠 여우별</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
								</ul>
							</div>
							<div id="tab4" class="tab_content">
								<ul class="list_line">
									<li>
										<a href="#">
											<div class="tit">
												<p>나비잠 별하 책방 나비잠 여우별</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
									<li>
										<a href="#">
											<div class="tit">
												<p>나비잠 별하 책방 나비잠 여우별</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
									<li>
										<a href="#">
											<div class="tit">
												<p>나비잠 별하 책방 나비잠 여우별</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
									<li>
										<a href="#">
											<div class="tit">
												<p>나비잠 별하 책방 나비잠 여우별</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
									<li>
										<a href="#">
											<div class="tit">
												<p>나비잠 별하 책방 나비잠 여우별</p>
												<i class="xi-new"></i>
											</div>
											<div class="date">2024-01-10</div>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="right_area">
							<p class="head">교육 상담시간</p>
							<p class="tel">031-706-8400</p>
							<ul>
								<li>평일 9:00 ~ 18:00 / 점심시간 12:00 ~ 13:00</li>
								<li>주말, 공휴일 휴무</li>
								<!-- <li>FAX : 000-111-2222</li> -->
							</ul>
							<a href="#" class="full_btn">원격지원</a>
						</div>
					</div>
				</section>

				<!-- 관공서 추가_240408-->
				<div class="foot_banner">
					<div class="container">
						<a href="https://www.hrd.go.kr" target="_blank"><img src="../img/common/go_banner01.png" alt="직업훈련포털 새창으로 열림"></a>
							
						<a href="https://www.moel.go.kr/index.do" target="_blank"><img src="../img/common/go_banner02.png" alt="고용노동부 새창으로 열림"></a>

						<a href="https://www.keis.or.kr/main/index.do" target="_blank"><img src="../img/common/go_banner03.png" alt="한국고용정보원 새창으로 열림"></a>

						<a href="https://www.rndjob.or.kr/" target="_blank"><img src="../img/common/go_banner04.png" alt="이공계인력중개센터 새창으로 열림"></a>

						<a href="https://www.work.go.kr/seekWantedMain.do" target="_blank"><img src="../img/common/go_banner05.png" alt="워크넷 새창으로 열림"></a>

						<a href="https://www.worldjob.or.kr/new_index.do" target="_blank"><img src="../img/common/go_banner06.png" alt="월드잡 새창으로 열림"></a>

					</div>
				</div>
				<!-- //관공서 추가_240408-->


			</main>
			<%@ include file="../inc/hrd_footer.jsp" %>
		</div>
	</body>
</html>
