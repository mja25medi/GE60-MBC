<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

            
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
	                        <c:forEach var="mainImg" items="${orgImgList }" varStatus="status">
		                    	<c:set var="target" value="_blank"/>
								<c:if test="${not empty mainImg.connTrgt}"><c:set var="target" value="${mainImg.connTrgt}"/></c:if>
								<div href="${mainImg.connUrl}" target="${target}" class="slide vm0${status.index }" style="background-image: url('<c:url value="/app/file/view2/${mainImg.bkgrFileSn}"/>'),url(/tpl/006/img/new_main/v_img01-bg.png)">
								 	<div class="txt-box">
									<p class="tit">${mainImg.bkgrImgNm}</p>
									<p class="content">${mainImg.descImgNm}</p>
									<%-- <a href="${mainImg.connUrl}" class="r_btn">자세히 보기 <i class="icon"></i></a> --%>
									</div>
								</div>
		                  	</c:forEach>
                        
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
						
						<c:choose>
							 <c:when test="${empty USERNO }">
						 					<!-- login -->
						                    <form name="userInfoForm" id="userInfoForm" method="post" action="/home/user/login" onSubmit="return false;">
							                    <input type="hidden" name="goMcd" value="MC00000000" id="goMcd" />
												<input type="hidden" name="encryptData" id="encryptData" />
												<div class="login_wrap">
													<div class="con">
														<div class="tit-header">
															<p class="tit">회원로그인</p>
															<!-- <div class="tit_img"></div> 태그 삭제_240408 -->
														</div>
														<div class="login_form">
															<div class="login_input">
																<span class="form-floating">
																	<input type="text" id="loginInputId" class="form-control _enterBind" placeholder="아이디를 입력해주세요." name="" maxlength="20"/>
																</span>
																<span class="form-floating">
																	<input type="password" id="loginInputPw" class="form-control _enterBind" placeholder="비밀번호를 입력해주세요." name="" maxlength="20" />
																</span>
															</div>
															<div class="login_bottom">
																<div class="custom-input idchk_save"><input type="checkbox" id="loginChkIdSave" /><label for="loginChkIdSave">아이디 저장</label></div>
															</div>
															<div class="entry_btn">
																<button type="button" id="login_btn" title="로그인" class="enterLogin" onclick="">로그인</button>
															</div>
															
															<!-- 태그구조수정 및 위치이동함_240408 -->
															<div class="login_foot">
																<a href="javascript:searchIdModal();" class="btn"><span>ID/PW 찾기</span></a>
																<a href="/home/main/goMenuPage?mcd=HM04002000" class="btn"><span>회원가입</span></a>
															</div>
															<!-- //태그구조수정 및 위치이동함_240408 -->
														</div>
													</div>
												</div>
							                </form>
						 						
												<script type="text/javascript">
												$('body').delegate('#login_btn', 'click keydown', function(event) {
													if($M.Check.Event.isClickEnter(event)) {
														doLogin();
													}
												});
												$("._enterBind").bind("keydown", function(event){
														if($M.Check.Event.isEnter(event)) {
															  doLogin();
														}
													});
													
													function rememberSet() {
														// ID 저장하기
														if ($('#loginChkIdSave:checked').length > 0) {
															setCookie('loginChkIdSave', $('#login_id').val(), 1); // 하루 저장
														} else {
															setCookie('loginChkIdSave', '', 0);
														}
													}
						
													function rememberGet() {
														// 쿠키에 저장된 기본값 입력
														if (isNotNull(GetCookie('loginChkIdSave'))) {
															$('#loginInputId').val(GetCookie('loginChkIdSave'));
															$('#loginChkIdSave').attr('checked', 'checked');
														}
													}							
													function doLogin() {
														rememberSet();
														if(isEmpty($("#loginInputId").val())) {
															alert("<spring:message code="user.message.login.alert.input.userid"/>");
															$("#loginInputId").focus();
															return false;
														}
														if(isEmpty($("#loginInputPw").val())) {
															alert("<spring:message code="user.message.login.alert.input.password"/>");
															$("#loginInputPw").focus();
															return false;
														}
														var log_id   = $('#loginInputId').val();
														var log_pwd  = $('#loginInputPw').val();
						
														var goMcd = $('#goMcd').val();
														// 암호화.
														var encrypt = makeSendInfo(log_id, log_pwd);
						
														if(!isEmpty(encrypt)) {
															// 암호화에 성공했으면 평문정보가 전송되지 않도록 폼 클리어
															$('#userInfoForm').clearForm();
														}
														// 암호화 데이터를 hidden 항목에 삽입하고 submit
														$('#goMcd').val(goMcd);
														$('#encryptData').val(encrypt);
														//$("#userInfoForm").submit();
														document.userInfoForm.submit();
													}							
												</script>                               
									</c:when>
									<c:otherwise>
												<div class="login_wrap">
													<div class="con">
														<div class="tit-header">
															<p class="tit">회원 로그인</p>
															<div class="tit_img"></div>
														</div>
														<div class="login_form after_login">
															<div class="welcom"><strong>${USERNAME}</strong>님 반갑습니다.</div>
															<div class="login_bottom">
																<ul>
																	<li>
																		<a href="/home/main/listCourseMain?mcd=MC00000051" class="btn"><span>나의학습</span></a>
																	</li>
																	<li>
																		<a href="/home/main/goMenuPage?mcd=${editMyinfoMcd}" class="btn"><span>개인정보 수정</span></a>
																	</li>
																</ul>
															</div>
															<div class="entry_btn">
																<button type="button" title="로그아웃" onclick="logout()">로그아웃</button>
															</div>
														</div>
													</div>
												</div>
							</c:otherwise>
						</c:choose>

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
				<div class="section section01" id="new_course_section">
					<div class="container">
						<div class="con_area">
							<div class="slider_info">
								<span class="state">NEW</span>
								<h2>신규 교육과정</h2>
								<div class="desc">오성온의 신규 강의를 만나보세요!</div>
							</div>
							<div class="lecture_list" id="courseEnrollListArea_NEW"></div>  
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
							<div class="lecture_list" id="courseEnrollListArea_R"></div> 
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
					${bastPageVO.pageCts }
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
									오성온 평생교육원의 특별함!
								</h2>
							</div>
							<div class="btn_area">
								<a href="/home/main/goMenuPage?mcd=MC00000063" class="r_btn">자세히 보기 <i class="icon"></i></a>
							</div>
						</div>
						<ul class="news_slider_list">
                               <c:choose>
                               	<c:when test="${not empty reviewList }">               
		                            	<c:forEach items="${reviewList }" var="reviewItem" varStatus="status">
										    <c:choose>
										   		<c:when test="${status.count % 5 eq 1 }"><c:set var="userIconStyle" value="top: 1rem"/></c:when>
										   		<c:when test="${status.count % 5 eq 2 }"><c:set var="userIconStyle" value="top: 1rem; left: -0.8rem"/></c:when>
										   		<c:when test="${status.count % 5 eq 3 }"><c:set var="userIconStyle" value="top: 1.5rem"/></c:when>
										   		<c:when test="${status.count % 5 eq 4 }"><c:set var="userIconStyle" value="top: 1.5rem"/></c:when>
										   		<c:when test="${status.count % 5 eq 5 }"><c:set var="userIconStyle" value="top: 1rem; left: -0.5rem"/></c:when>
										   		<c:otherwise><c:set var="userIconStyle" value="top: 1rem"/></c:otherwise>
										   	</c:choose>
											<li>
												<a href="/home/brd/bbs/viewAtclMain?bbsCd=${reviewItem.bbsCd }&atclSn=${reviewItem.atclSn }&mcd=MC00000063">
													<div class="card_item">
														<div class="stuImg"><img src="/tpl/${COLOR_TPL}/img/new_main/re_user_0${status.count % 5 }.svg" style="${userIconStyle}" alt="후기 수료생 대체이미지01" /></div>
														<div class="desc">
															<p class="stuName">${reviewItem.regNm } 수료생</p>
															<p class="review_bold">${reviewItem.atclTitle }</p>
															<p class="review_nomal">
																${reviewItem.atclCts }
															</p>
														</div>
													</div>
												</a>
											</li>		                            	
                          	
		                            	</c:forEach>
		                        </c:when>
		                        <c:otherwise>
		                        	<div class="no-list">등록된 내용이 없습니다.</div>   
		                        </c:otherwise>
		                    </c:choose>
						</ul>
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
										<a href="#tab3">FAQ</a>
									</li>
								</ul>
							</div>
							<div id="tab1" class="tab_content" style="display: block">
								<ul class="list_line">
								<c:choose>
                                	<c:when test="${not empty noticeList }">
		                                	<c:forEach items="${noticeList }" var="noticeItem">
												<li>
													<a href="/home/brd/bbs/viewAtclMain?bbsCd=${noticeItem.bbsCd }&atclSn=${noticeItem.atclSn }&mcd=HM02001000">
														<div class="tit">
															<p>${noticeItem.atclTitle }</p>
															<i class="xi-new"></i>
														</div>
														<div class="date"><meditag:dateformat type="1" delimeter="." property="${noticeItem.regDttm}" /></div>
													</a>
												</li>
		                                	</c:forEach>
                                 	</c:when>
                                	<c:otherwise>
                                		<div class="no-list">등록된 내용이 없습니다.</div>   
                                	</c:otherwise>
                                </c:choose>
								</ul>
							</div>
							<div id="tab2" class="tab_content">
								<ul class="list_line">
                                <c:choose>
                                	<c:when test="${not empty pdsList }">
		                                	<c:forEach items="${pdsList }" var="pdsItem">
												<li>
													<a href="/home/brd/bbs/viewAtclMain?bbsCd=${pdsItem.bbsCd }&atclSn=${pdsItem.atclSn }&mcd=MC00000030">
														<div class="tit">
															<p>${pdsItem.atclTitle }</p>
															<i class="xi-new"></i>
														</div>
														<div class="date"><meditag:dateformat type="1" delimeter="." property="${pdsItem.regDttm}" /></div>
													</a>
												</li>
		                                	</c:forEach>
		                            </c:when>
                                	<c:otherwise>
                                		<div class="no-list">등록된 내용이 없습니다.</div>   
                                	</c:otherwise>
                                </c:choose>                          

								</ul>
							</div>

							<div id="tab3" class="tab_content">
								<ul class="list_line">
                               <c:choose>
                                	<c:when test="${not empty faqList }">
		                                	<c:forEach items="${faqList }" var="faqItem">
												<li>
													<a href="/home/brd/bbs/viewAtclMain?bbsCd=${faqItem.bbsCd }&atclSn=${faqItem.atclSn }&mcd=HM02005000">
														<div class="tit">
															<p>${faqItem.atclTitle }</p>
															<i class="xi-new"></i>
														</div>
														<div class="date"><meditag:dateformat type="1" delimeter="." property="${faqItem.regDttm}" /></div>
													</a>
												</li>
		                                	</c:forEach>
		                            </c:when>
                                	<c:otherwise>
                                		<div class="no-list">등록된 내용이 없습니다.</div>   
                                	</c:otherwise>
                                </c:choose>                          


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
							<a href="https://helpu.kr/oso/" target="_blank" class="full_btn">원격지원</a>
						</div>
					</div>
				</section>
				
				<!-- 관공서 추가_240408-->
				<div class="foot_banner">
					<div class="container">
						<a href="https://www.hrd.go.kr" target="_blank"><img src="/tpl/${COLOR_TPL}/img/common/go_banner01.png" alt="직업훈련포털 새창으로 열림"></a>
							
						<a href="https://www.moel.go.kr/index.do" target="_blank"><img src="/tpl/${COLOR_TPL}/img/common/go_banner02.png" alt="고용노동부 새창으로 열림"></a>

						<a href="https://www.keis.or.kr/main/index.do" target="_blank"><img src="/tpl/${COLOR_TPL}/img/common/go_banner03.png" alt="한국고용정보원 새창으로 열림"></a>

						<a href="https://www.rndjob.or.kr/" target="_blank"><img src="/tpl/${COLOR_TPL}/img/common/go_banner04.png" alt="이공계인력중개센터 새창으로 열림"></a>

						<a href="https://www.work.go.kr/seekWantedMain.do" target="_blank"><img src="/tpl/${COLOR_TPL}/img/common/go_banner05.png" alt="워크넷 새창으로 열림"></a>

						<a href="https://www.worldjob.or.kr/new_index.do" target="_blank"><img src="/tpl/${COLOR_TPL}/img/common/go_banner06.png" alt="월드잡 새창으로 열림"></a>

					</div>
				</div>
				<!-- //관공서 추가_240408-->

   

<script type="text/javascript">
	
	var modalBox = null;

	 $(document).ready(function(){
		$('.depth1').removeClass('on');
		// 신규 과정 조회 (최근 4주 안에 개설 과정)
		courseEnrollList("","","NEW");
		// 법정 과정 조회
		courseEnrollList("","R","");
		//$(".section02").slideToggle(300);
		
	    modalBox = new $M.ModalDialog({
       		"modalid" : "modal1",
       		"nomargin"	: true
       	});  
	    
	    /********** main popzone **********/
	    $('.pop-latest').slick({
	         infinite: true,
	         arrows: true,
	         dots: false,
	         autoplay: true,
	         autoplaySpeed: 4000,
	         fade: false,
	         slidesToShow: 1,
	         responsive: [
	             {
	               breakpoint: 1217,
	               settings: {
	                 fade: false,
	                 slidesToShow: 1
	               }
	             },
	             {
	               breakpoint: 786,
	               settings: {
	                 fade: false,
	                 slidesToShow: 1
	               }
	             },
	             {
	               breakpoint: 480,
	               settings: {
	                 slidesToShow: 1
	               }
	             }
	         ]
	     });
	    
	    $('.slider_list').slick({
	        infinite: true,
	        arrows: true,
	        dots: false,
	        // autoplay: true,
	        autoplaySpeed: 5000,
	        slidesToShow: 5,
	        slidesToScroll: 1,
	        responsive: [
	            {
	              breakpoint: 1200,
	              settings: {
	                fade: false,
	                slidesToShow: 4
	              }
	            },
	            {
	              breakpoint: 950,
	              settings: {
	                dots: true,
	                slidesToShow: 3
	              }
	            },
	            {
	              breakpoint: 620,
	              settings: {
	                dots: true,
	                slidesToShow: 2
	              }
	            },
	            {
	              breakpoint: 380,
	              settings: {
	                dots: true,
	                slidesToShow: 1
	              }
	            }
	        ]
	    });
	});

    	
	function courseEnrollList(crsCtgrCd, crsSvcType, searchMode){
		var dispMode = crsSvcType;	
		if(crsSvcType == "" && searchMode != "") {
			dispMode = searchMode;
		}
		$("#courseEnrollListArea"+"_"+dispMode).load(cUrl("/home/main/listCourseEnroll"),{'crsCtgrCd' : crsCtgrCd, 'crsSvcType' : crsSvcType, 'searchMode' : searchMode});
	}
	
	function topCourseSearch() {
		var searchValue = $("#searchValue").val();
		document.location.href = cUrl("/home/course/listSearchCourseFullMain")+"?mcd=${searchFullMcd}${AMPERSAND}searchValue="+searchValue;
	}
	function readCourse(crsCreCd,crsCd){
		if(crsCreCd == ''){
			alert("등록된 회차가 없는 과정입니다.")
		}else{
			var url = generateUrl("/home/course/readCourseCreateMain", { "mcd": 'MC00000023' ,"crsCreCd": crsCreCd, "crsCd": crsCd });
			document.location.href = url;
		}
	}
	function logout() {
		document.location.href = cUrl("/home/user/logout");
	}
</script>	