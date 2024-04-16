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
										<li><span class="current">강사지원</span></li>
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
								<li><a href="#">오시는 길</a></li>
								<li class="active"><a href="#">강사지원</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">강사지원</h3>

						<div class="info_story">
						
							<div class="story01">
								<dl>
									<dt><h4>투썬캠퍼스 원격평생교육원과 함께하실 강사님을 모집합니다.</h4></dt>
									<dd>
									<p>
										디지털 인재 양성의 비전을 공유하며 투썬캠퍼스와 함께 성장해 나갈 강사님을 모집합니다. <br>
									자신의 강의와 커리어에 자부심이 있는 성실한 강사님들의 많은 관심과 지원 바랍니다.<br>
										투썬캠퍼스는 항상 문이 열려 있습니다. </p></dd>
								</dl>
							</div>
							
						</div>

						<dl class="guide_info mt50">							
							
							<div>
								<dt>
									스킬
								</dt>
								<dd>
									<ul class="list-type flex-wrap gap2">
										<li><span class="icon_type"><strong class="">Python</strong></span></li>
										<li><span class="icon_type"><strong class="">JAVA SQL</strong></span></li>
										<li><span class="icon_type"><strong class="">머신러닝</strong></span></li>
										<li><span class="icon_type"><strong class="">딥러닝</strong></span></li>
										<li><span class="icon_type"><strong class="">데이터베이스</strong></span></li>
										<li><span class="icon_type"><strong class="">클라우드</strong></span></li>
										<li><span class="icon_type"><strong class="">서버</strong></span></li>
										<li><span class="icon_type"><strong class="">가상화(AWS, Docker, 쿠버네티스 등)</strong></span></li>
										<li><span class="icon_type"><strong class="">정보보안</strong></span></li>
										<li><span class="icon_type"><strong class="">실무 프로젝트</strong></span></li>
										<li><span class="icon_type"><strong class="">...</strong></span></li>
									</ul>
								</dd>
							</div>

							<div>
								<dt>
									자격요건
								</dt>
								<dd>
									<ul class="list-dot mb10">
										<li>전, 현직 전문학원 강사, 전직 교사 등 관련분야 종사자​</li>
										<li>NCS 확인강사​
											<ul class="info_list">
												<li>정보기술개발, 정보기술전략계획, 인공지능, 정보기술운영, 정보보호 등 관련분야 NCS 직종점수 보유자​</li>
												<li>관련분야 실무 또는 강의 경력 3년 이상 보유자​</li>
											</ul>
										</li>
										<li>컴퓨터 시스템, 공학계열 등의 관련 전공 학사 이상 학력​</li>
										<li>최신 기술 동향에 대한 이해가 높으신 분</li>
									</ul>
								</dd>								
							</div>

							<div>
								<dt>
									우대사항
								</dt>
								<dd>
									<ul class="list-dot">
										<li>온라인강의 유경험자​</li>
										<li>관련분야 상위등급 전문자격증 소지자​​</li>
										<li>관련분야 저자​​</li>
										<li>관련분야 박사학위 소지자​​</li>
										<li>직업훈련교강사 경력 보유자, 직업능력개발훈련교사 자격, 정보처리기사 등의 직무 관련 자격증 보유자, KDT(K-디지털트레이닝), KDC(K-디지털기초역량훈련), 산대특(산업구조변화대응 특화훈련) 등의 경험자 우대​</li>
									</ul>
								</dd>
							</div>

							<div>
								<dt>
									강사혜택
								</dt>
								<dd>
									<ul class="list-dot">
										<li>강사 브랜드 강화/ 인지도 상승/ 광고, 보도자료 등을 통한 마케팅 제공</li>
									</ul>
								</dd>
							</div>

						</dl>

						<!-- 안쓴다함_240411 -->
						<!--
						<div id="apply" class="recruit_form">
							<div class="page_tit">
								<h4>입사지원서 작성</h4>
							</div>
							<div class="tstyle mb10">
								<ul class="dbody">
									
									<li>
										<div class="row">
											<label for="nameInput" class="form-label col-sm-2">이름<i class="icon_star" aria-hidden="true"></i></label>
											<div class="col-sm-10">
												<div class="form-row">
													<input class="form-control w50" type="text" name="name" id="nameInput" value=""  placeholder="이름을 입력하세요">
												</div>
											</div>
										</div>
									</li>  
									<li>
										<div class="row">
											<label for="selectPhone1" class="form-label col-sm-2"><span>연락처</span><i class="icon_star" aria-hidden="true"></i></label>
											<div class="col-sm-10">
												<div class="form-inline">
												<select class="form-select w20" id="a1">
													<option value="-">선택</option>
													<option value="-" selected>02</option>
												</select> 
												<input class="form-control" type="text" name="title" value="" id="a2" title="전화번호 두번째자리" placeholder="">
												<input class="form-control" type="text" name="title" id="a3" value="" title="전화번호 세번째자리" placeholder="">                            
												</div>
											</div>
										</div>
									</li>
									<li>
										<div class="row">
											<label for="selectPhone1" class="form-label col-sm-2"><span>휴대폰 번호</span><i class="icon_star" aria-hidden="true"></i></label>
											<div class="col-sm-10">
												<div class="form-inline">
												<select class="form-select w20" id="selectPhone1">
													<option value="-">선택</option>
													<option value="-" selected>010</option>
												</select> 
												<input class="form-control" type="text" name="title" value="" id="inputPhone2" title="휴대폰 번호 두번째자리" placeholder="">
												<input class="form-control" type="text" name="title" id="inputPhone3" value="" title="휴대폰 번호 세번째자리" placeholder="">                            
												</div>
											</div>
										</div>
									</li>
									<li>
										<div class="row">
											<label for="inputEmail1" class="form-label col-sm-2"><span>이메일</span><i class="icon_star" aria-hidden="true"></i></label>
											<div class="col-sm-10">
												<div class="form-inline">
													<input class="form-control mr5" type="text" name="name" value="" id="inputEmail1">
													<span class="mr5">@</span>
													<input class="form-control mr5" type="text" name="name" id="inputEmail2" value=""  title="이메일 주소 뒷자리" placeholder="">
													<select class="form-select" id="selectEmail2">
														<option value="-">직접입력</option>
														<option value="-">naver.com</option>
														<option value="-">daum.net</option>
													</select>
												</div>
											</div>
										</div>
									</li> 
									<li>
										<div class="row">
											<label for="jobCareer" class="form-label col-sm-2"><span>지원분야</span><i class="icon_star" aria-hidden="true"></i></label>
											<div class="col-sm-10">
												<label class="form-row">
													<textarea class="form-control " rows="10" value=""  placeholder="강의 지원 분야 및 관심 분야를 간단하게 입력해 주세요."></textarea>
												</label>
											</div>
										</div>
									</li> 
									<li>
										<div class="row">
											<label for="jobCareer" class="form-label col-sm-2"><span>이력서</span><i class="icon_star" aria-hidden="true"></i></label>
											<div class="col-sm-10 attach_area">
												<input type="file" class="input_attach sr-only" id="attchFile" multiple><label for="attchFile" class="btn gray2">파일 찾기</label> 
												<div class="attach_list">
													<div class='attach_name'>홍길동_이력서.hwp<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
													<div class='attach_name'>홍길동_포트폴리오.pdf<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
													<div class='attach_name'>홍길동_강의경력증명.zip<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
												</div>
												<small class="note mt10">※ 100MB미만의 한글, 워드, 엑셀, 파워포인트, 알집 압축 파일만 첨부가능
												</small> 
											</div> 
										</div>
									</li>
								</ul>								
							</div>

							<div class="box terms txt_small">
								<ul class="item">
									<li>
										<b>개인정보 수집 및 이용 동의</b><br>
										귀하는 개인정보 수집 및 이용에 대한 동의를 거부하실 수 있으나, 동의를 거부하실 경우 강사모집이 불가합니다. 위 내용을 충분히 이해하였으며, 개인정보 수집 및 이용에 동의합니다.
									</li>									
									<li class="mt20">
										<span class="custom-input">
											<input type="checkbox" name="agree1" id="agree1Y">
											<label for="agree1Y" class="fweb">동의합니다.</label>
										</span>  
									</li>
								</ul>                                 
							</div>

							<div class="btns mt30">
								<button type="button" class="btn type4">강사 지원하기</button>
							</div>
						</div>	
						-->
						<!-- //안쓴다함_240411 -->
						
						

						<!--  -->
						<div class="recruit_process type2 mt30">
							<div class="page_tit">
								<h5>전형절차</h5>
							</div> 
							<div class="step">
								<ul class="top gap3">
									<li>
										<div class="step">STEP. 01</div>
										<div class="ctt">
											<strong>강사지원 (이메일 지원)</strong>
										</div>
									</li>
									<li>
										<div class="step">STEP. 02</div>
										<div class="ctt">
											<strong>추가 서류 접수 및 내부 검토 후 개별 연락</strong>
										</div>
									</li>
									<li class="last">
										<div class="step">STEP. 03</div>
										<div class="ctt">
											<strong>담당자 인터뷰 및 조건 협의</strong>
										</div>
									</li>
									<li class="last">
										<div class="step">STEP. 04</div>
										<div class="ctt">
											<strong>강사 선정</strong>
										</div>
									</li>
								</ul>
							</div>
							<div class="caution bgimg">

								<div>
									<b>메일양식</b><br>

									<div>
										<i class="xi-check-circle-o" aria-hidden="true"></i> 지원분야: (강의 지원 분야 및 관심 분야를 간단히 입력해 주세요.)
									</div>
									<div>
										<i class="xi-check-circle-o" aria-hidden="true"></i> 성명
									</div>
									<div>
										<i class="xi-check-circle-o" aria-hidden="true"></i> 연락처
									</div>
									<div>
										<i class="xi-check-circle-o" aria-hidden="true"></i> 이메일
									</div>
									<div>
										<i class="xi-check-circle-o" aria-hidden="true"></i> 내용
									</div>
									<div>
										<i class="xi-check-circle-o" aria-hidden="true"></i> 기타 별첨서류 - 간단한 이력서, 포트폴리오(샘플강의) 등
									</div>
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
