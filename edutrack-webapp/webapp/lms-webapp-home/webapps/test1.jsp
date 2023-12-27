<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>



<!--dddㅇ  -->




<!DOCTYPE html>
<html lang="ko">
	<head>
	    <meta charset="utf-8">
	    <title>스마트인재개발</title>
	    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	    <meta name="description" content="페이지 설명">
	    <link rel="apple-touch-icon-precomposed" sizes="57x57" href="/tpl/003/img/common/apple-touch-icon-57x57.png">
	    <link rel="icon" type="image/png" href="/tpl/003/img/common/favicon-16x16.png" sizes="16x16">
	    <link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
	    <link rel="stylesheet" href="/tpl/003/css/webfonts.css">
	    <link rel="stylesheet" href="/tpl/003/css/layout.css">    
	    <link rel="stylesheet" href="/tpl/003/css/effect_slick.css">
	    <link rel="stylesheet" href="/tpl/003/css/sub.css"><!-- sub 페이지에서 사용 -->
	
    	<script src="/app/js/Context.js"></script>
	    <script defer src="/js/common.js"></script>
	    <script defer src="/tpl/003/js/common_function.js"></script>
	    <script defer src="/tpl/003/js/common_util.js"></script>
	    <script src="/tpl/003/js/common_conf.js"></script>
	    <script src="/tpl/003/jquery/jquery-3.2.1.min.js"></script>
	    <script async src="/tpl/003/jquery/slick.min.js"></script>
	    <script defer src="/tpl/003/js/func.min.js"></script>
	    <script defer src="/tpl/003/js/common.js"></script>
	    <script defer src="/tpl/003/js/main.js"></script>
	    <script src="/tpl/003/jquery/jquery.cycle2.js"></script>

	    <script src="/js/nuguya/nice.nuguya.oivs.crypto.js"></script>
		<script src="/js/nuguya/nice.nuguya.oivs.msg.js"></script>
		<script src="/js/nuguya/nice.nuguya.oivs.util.js"></script>

    
 	   <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
       <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

		<script type="text/JavaScript" src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>
		<script type="text/JavaScript" src="/js/jquery/jquery.ui.touch-punch.min.js"></script>
	
		<link rel="stylesheet" href="/tpl/002/css/summernote.min.css" />	
		<!-- include summernote css/js-->
		<link rel="stylesheet" href="/libs/summernote/summernote.css" type="text/css" />
		<link rel="stylesheet" href="/css/summernote_custom.css" type="text/css" />
		<script type="text/JavaScript" src="/libs/summernote/summernote.js"></script>
		<script type="text/JavaScript" src="/libs/summernote/lang/summernote-ko-KR.js"></script>
		<script type="text/JavaScript" src="/libs/summernote/lang/summernote-ja-JP.js"></script>
		<script type="text/JavaScript" src="/js/common_summernote.js"></script>
	
	
    
		<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"></script>
		<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"></script>
		<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.fileupload.js"></script>
		<script type="text/JavaScript" src="/js/common_fileupload.js"></script>
	
	<script type="text/JavaScript" src="/tpl/003/js/modaldialog2.js"></script>
	<script type="text/JavaScript" src="/js/jquery/jquery.form.js"></script>
    <script defer src="/tpl/003/js/sub.js"></script>
	    
		
	
	</head>
	<div id="skipNav">
	    <a href="#content">본문으로 바로가기</a>
	    <a href="#gnb">주메뉴 바로가기</a>
	</div>

<body>
    
    <div id="wrap">
		







		
		
			
			
				
				
			
				
				
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
				
				
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
					
				
					
				
					
				
					
				
			
				
				
			
				
				
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
				
				
			
		
			
			
				
				
			
				
				
			
		
		
		
		
		
		
<header class="header">            
    <div class="logo_search">
        <div class="container">
            <h1 class="logo">
                <a href="/" class="link">
                    <span class="sr-only">스마트인재개발원</span>
                </a>
            </h1>
            <button id="btn_sch" class="btn_sch_open">
                <i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span><!-- 모바일 검색버튼-->
            </button> 
            <div class="sch_layer">                            
               <form class="search" name="courseSearch" id="courseSearch" onsubmit="return false;">                      
                    <label htmlFor="allKeyWord" class="title">검색</label>
                    <input type="text" id="courseSearchValue" name="allKeyWord"  maxlength="100"  placeholder="과정명 입력">
                    <button id="btnSch" class="btn_sch" onClick="topCourseSearch()">
                        <i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span>
                    </button>
                </form>
                <button type="button" class="btn_sch_close">
                    <i class="xi-close-thin" aria-hidden="true"></i><span class="sr-only">검색 닫기</span>
                </button>                            
            </div>
            <div class="gnb_util">
                <div class="member_area">
					
					
                    <div class="logout">
                        <span class="welcome"><span class="text"><strong>학습자1</strong>님</span> <em class="badge">3</em></span>
                        <ul class="nav">
                            <li><a href="/home/user/logout" class=""><span>로그아웃</span></a></li>
                            <li><a href="/home/main/goMenuPage?mcd=HM03006000" class=""><span>정보수정</span></a></li>
							
                        </ul>
                    </div>

					

                </div>
                <!-- //member_area -->
                                  
            </div>
            <!-- //gnb_util -->
        </div>
        <!-- //container -->
    </div>
    <!--//logo_search-->

    <div class="gnb_area">
        <nav class="nav container" id="gnb">
            <button type="button" class="btn_allmenu"><span class="sr-only">전체메뉴보기</span><i class="xi-bars"></i></button>
            <h2 class="pop_title">전체메뉴</h2>
            
            <ul id="head_menu" class="topmenu">
  							
								
									
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 ">
					                    <a href="#0"><span>KDP센터소개</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000041">KDP 사업소개</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000042">KDP 센터소개</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000044">인사말</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000045">찾아오시는 길</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000046">조직도</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
									
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 ">
					                    <a href="#0"><span>개발원소개</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000011">개발원 이야기</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000008">조직현황/강사진</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000009">세미콜론 매거진</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000047">인재채용</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000048">뉴스룸</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000013">오시는길</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
									
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 ">
					                    <a href="#0"><span>교육안내</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																
																
					
																
																	
																	
																		
															<li><a href="#">훈련과정</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000016">고용보험 환급제도</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000054">훈련비 환급방법</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000017">훈련 진행절차</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000018">훈련진행 유의사항</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000019">모시답안 처리기준</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
									
									
										
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 ">
					                    <a href="#0"><span>수강신청</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000023">원격훈련과정</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=HM01003000">집체훈련과정</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000024">혼합훈련과정</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
									
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 ">
					                    <a href="#0"><span>시설 • 장비 공유</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000047">시설장비 이용방법</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000037">대관/대여 신청</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000048">예약현황</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000038">예약조회 </a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000055">장비대여</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
									
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 ">
					                    <a href="#0"><span>나의강의실</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC00000051">나의학습</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000010">종료과정 성적조회</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000011">설문</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
					
																
																	
																	
																		
															<li><a href="#">과정별 학습문의</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
									
									
										
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 ">
					                    <a href="#0"><span>마이페이지</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000013">장바구니</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000014">결제내역</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
					
																
																	
																	
																		
															<li><a href="#">문의내역</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=HM03005000">받은쪽지함</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=HM03006000">개인정보수정</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
									
									
										
									
										
									
										
									
										
									
									
									
									
					                <li class="depth1 on">
					                    <a href="#0"><span>지원센터</span></a>
					                    <div class="submenu">                    
					                        <ul class="depth2">             
															
																
																
																	
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=HM02001000">공지사항</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=MC10000017">학습자료실</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=HM02006000">1:1 문의하기</a></li>
																		
																	
																
					
															
																
																
																	
																
																	
																
																	
																
																	
																
																
																
																	
																
					
																
																	
																	
																		
															<li><a href="/home/main/goMenuPage?mcd=HM02005000">FAQ</a></li>
																		
																	
																
					
															                    
					                        </ul>
					                    </div>
					                </li>
									
									
								
							
								
							
								
							            
                  
                                                                                
            </ul>
            <button type="button" class="pop_close"><i class="xi-close"></i><span class="sr-only">전체메뉴 레이어
                    닫기</span></button>
        </nav>
    </div>
    <!-- //gnb_area -->
</header>


	


        <main class="main">
            <div id="contentWrap" class="container">

                <!-- content -->
                <div id="content" class="content">
	                <!-- snb -->
	            	






                    <div class="h1_area">
                        <div class="location_bar">
                            <nav class="location">
                                <ul>
                                    <li><a href="/"><i class="xi-home-o" aria-hidden="true"></i><span class="sr-only">Home</span></a></li>
                                    
                                    
                                    	<li><span class="current">지원센터 </span></li>
                                    
                                    	<li><span class="current"> 1:1 문의하기</span></li>
                                    
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">지원센터</h4>
                    </div>

	
	                 <!-- //snb -->
					




				
				
					
					
						
						
					
						
						
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
						
						
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
							
						
							
						
							
						
							
						
					
						
						
					
						
						
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
						
						
					
				
					
					
						
						
					
						
						
					
				
				
					
				
					
				
					
				
					
				
					
				
					
				
					
				
					
						
						
							
						
							
						
							
						
							
						
	                    <div class="subMenu">
	                        <ul class="menu">
	                        	
										
										
											
										
											
										
											
										
											
										
				
										
											
											
												
									<li ><a href="/home/main/goMenuPage?mcd=HM02001000">공지사항</a></li>
												
											
										
				
								
										
										
											
										
											
										
											
										
				
										
											
											
												
									<li ><a href="/home/main/goMenuPage?mcd=MC10000017">학습자료실</a></li>
												
											
										
				
								
										
										
											
										
											
										
											
										
											
										
				
										
											
											
												
									<li class="active"><a href="/home/main/goMenuPage?mcd=HM02006000">1:1 문의하기</a></li>
												
											
										
				
								
										
										
											
										
											
										
											
										
											
										
				
										
											
											
												
									<li ><a href="/home/main/goMenuPage?mcd=HM02005000">FAQ</a></li>
												
											
										
				
									
      
	                        </ul>
	                    </div>

						
						

					
				
					
				
					
				
                    <h3 class="subMenu_title">1:1 문의하기</h3>
				                    <!-- //h1_area --> 
                    <!-- //2depth--> 
                    





<form id="frm" name="frm" method="post">
	<input type="hidden" name="qnaSn" id="qnaSn" value="" />
	<input type="hidden" name="attachFileSns" id="attachFileSns" value="" />
	<input type="hidden" name="attachImageSns" id="attachImageSns" value="" />
	<input type="hidden" name="editorYn" id="editorYn" value="N" />
	<div class="detail_cont write">
		<div class="tstyle">
			<ul class="dbody">
				<li>
					<div class="row">
						<label for="titleInput" class="form-label col-sm-2">제목</label>
						<div class="col-sm-10">
							<div class="form-row">
								<input class="form-control" maxlength="100" name="qnaTitle" type="text" id="qnaTitle" title="제목" value="">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="starScoreInput" class="form-label col-sm-2">분류</label>
						<div class="col-sm-10">
							<div class="form-inline">
								<select id="qnaCtgrCd" name="qnaCtgrCd">
									
										<option value="01" >수강신청</option>
									
										<option value="02" >결제방법</option>
									
										<option value="03" >회원정보</option>
									
										<option value="04" >학습문의</option>
									
										<option value="05" >학습장애</option>
									
										<option value="06" >고용보험환급</option>
									
										<option value="07" >기타</option>
									
								</select>
							</div>
						</div>
					</div>
				</li>
				
				<li>
					<div class="row">
						<label for="contTextarea" class="form-label col-sm-2">내용</label>
						<div class="col-sm-10">
							<div class="form-row">
								<div id="summernote" style="float:left; width:100%; margin: 0 auto;"></div>
								<textarea name="qnaCts" id="contentTextArea" title="게시물내용" class="sr-only"></textarea>
							</div>
						</div>
					</div>
				</li>
 				<li>
					<div class="row">
						<span class="form-label col-sm-2">첨부파일</span>
						<div class="upload col-sm-10 attach_area">
							<div class="upload_inbox">
								<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs" style="background: #4D4D4D; color: #fff; border-color: #4D4D4D;">
									파일선택
								</a>
								<input type="file" name="atchuploader" id="atchuploader" title="첨부파일" multiple style="display:none" />
								<div id="atchprogress" class="progress">
									<div class="progress-bar progress-bar-success"></div>
								</div>
							</div>
							<div id="atchfiles" class="multi_inbox attach_list"></div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="btns mt30">
			
				<button type="button" onclick="javascript:writeQstn();" class="btn gray2">등록</button>
				<button type="button" onclick="javascript:listQstn()" class="btn type5">취소</button>
			
			
		</div>
	</div>
</form>

<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	var summernote;
	var data = "";
	
	var ItemVO = {};
	
	// 페이지 초기화
	$(document).ready(function() {
		ItemVO.orgCd = "ORG0000001";
		
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('[]'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'QNA_QSTN',
													'organization' : "ORG0000001",
													'type'		: 'file' }
							}
						});
		
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"QNA_QSTN",
			'organization' 		: 	"ORG0000001",
			"editorHeight"		:	"400px",
			"attachments"		:	$.parseJSON('{"image":[]}')
		});
		
	});

	function uploderclick(str) {
		$("#"+str).click();
	}


	/* 서브밋 처리 */
	function process(cmd) {
		$('#frm').attr("action","/home/brd/qna/"+cmd);
 		
		if(!validate(document.frm)) return false;
		
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("제목을 입력하세요.");
			return false;
		}
		
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);
		var _paramImages = summernote.getFileSnAll();
		var _paramFiles = atchFiles.getFileSnAll();
		
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);
		
		$('#frm').ajaxSubmit(processCallback);
	}
	
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result == 1) {
			document.location.href = "/home/brd/qna/main";
		} else {
			
		}
	}

	/* 글 저장 */
	function writeQstn() {
		process("writeQstn");
	}

	/* 글 수정 */
	function editQstn() {
		process("editQstn");
	}
	
	/** 글 상세 화면으로 이동 */
	function viewQna(){
		var qnaSn = '';
		var url = generateUrl("/home/brd/qna/viewQnaMain", {"qnaSn" : qnaSn});
		document.location.href = url;
	}
	
	/** 글 목록 화면으로 이동 */
	function listQstn(){
		var url = generateUrl("/home/brd/qna/main");
		document.location.href = url;
	}

	</script>
                       

                        
                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        





<footer class="footer">
    <button type="button" class="go_top"><i class="xi-angle-up-min"></i><span>TOP</span></button>
    <div class="container">


        <div class="footer_inner">
            <div class="left">
                <div class="footer_logo"><img src="/tpl/003/img/common/logo_w.svg"></div>
            </div>
            <div class="right">
                <ul class="sns">
                    <li class="kakao"><a href="https://pf.kakao.com/_VYlpM" target="_blank"><img src="/tpl/003/img/common/sns1.png"></a></li>
                    <li class="naver"><a href="https://blog.naver.com/smhrd_official" target="_blank"><img src="/tpl/003/img/common/sns2.png"></a></li>
                    <li class="youtube"><a href="https://www.youtube.com/channel/UCubIpLB7cA9tWIUZ26WFKPg" target="_blank"><img src="/tpl/003/img/common/sns3.png"></a></li>
                    <li class="instagram"><a href="https://www.instagram.com/smhrd0317/" target="_blank"><img src="/tpl/003/img/common/sns4.png"></a></li>
                    <li class="facebook"><a href="https://www.facebook.com/smhrd0317" target="_blank"><img src="/tpl/003/img/common/sns5.png"></a></li>
                </ul>
            </div>
        </div>




       
        <div class="address_group">
            <div class="left">
                <dl>
                    <dt>(사)스마트인재개발원</dt>
                    <dd><span class="txt_line">대표자 : 차준섭</span>
                        <span class="txt_line">개인정보책임관리자 : 반수경</span>
                        <span class="txt_line">사업자번호 : 178-82-00065</span><br>
                        <span>광주 동구본점 : </span>광주 동구 예술길 31-15 3~4, 7층<br>
                        <span>서울 서초점 : </span>서울시 서초구 동작대로 132 9층<br>
                        <span>광주 남구점 : </span>광주 남구 송암로 60 2층<br>
                        <span>광주 서구점 : </span>광주 서구 경열로 20 3,4층<br>
                        <span>순천점 : </span>전남 순천시 중앙로 260<br>
                        <span>목포점 : </span>전남 목포시 산정로212번길 13<br>
                        <span class="txt_line"><strong>대표 FAX : </strong>062-655-3510</span>
                        <span class="txt_line"><strong>대표 E-Mail : </strong>smhrd@smhrd.or.kr</span>
                    </dd>
                </dl>
            </div>
            <div class="right">
                <dl>
                    <dt>고객센터</dt>
                    <dd>전화상담 : <span>062-655-3506, 9</span><br>
                    카카오톡 : <span>@스마트인재개발원</span><br>
                    홈페이지 : <span>상담신청 메뉴 클릭</span><br>
                    &lt;상담시간&gt;<p></p>
                    <p><span>- 전화상담 : </span>09시 ~ 19시 (월~금)<br>
                    <span>- 홈페이지 : </span>09시 ~ 22시 (연중무휴)<br>
                    <span>- 카카오톡 : </span>09시 ~ 22시 (연중무휴)<br>
                    <a href="https://www.hrd.go.kr/hrdp/co/pcoco/PCOCO0100P.do?tracseId=AIG20220000366748&amp;tracseTme=6&amp;trainstCstmrId=500020018488&amp;crseTracseSe=C0061&amp;pageId=" style="color:#f4f4f4;">*표기된 취업/수료율은 HRD-Net 훈련기관<br>
                    정보의 2022년도 14개 과정 평균 취업률입니다.</a>
                    </p>
                    </dd>
                </dl>
            </div>

            
        </div>
    </div>
    
</footer>






       
    </div>
	
	
</body>

</html>