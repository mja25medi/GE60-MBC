<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

            

            <div id="content">
                
                <!-- mainvisual -->
                <div class="mainvisual">
                    
                    <!-- search -->
                    <div class="sch_layer">                            
                        <form class="sch_form" name="sch_form" id="schForm">                               
                            <label for="allKeyWord" class="title">통합검색</label>
                            <input type="text" name="allKeyWord" id="allKeyWord" placeholder="강좌명을 검색하세요">
                            <button id="btnSch" class="btn_sch">
                                <i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span>
                            </button>
                        </form>
                        <button type="button" class="btn_sch_close">
                            <i class="xi-close-thin" aria-hidden="true"></i><span class="sr-only">검색 닫기</span>
                        </button>                            
                    </div>

                    <div class="cycle-slideshow"
                        data-cycle-slides="> .slide"
                        data-cycle-speed="1000"
                        data-cycle-timeout="5000"
                        data-cycle-pause-on-hover="true"
                        data-cycle-stop=".btnStop"
                        data-cycle-next=".btnPlay"
                        data-cycle-pager="#adv-custom-pager"
                        data-cycle-pager-template="<a href='#'></a>"
                    >
                        <c:forEach var="mainImg" items="${orgImgList }">
	                    <c:set var="target" value="_blank"/>
						<c:if test="${not empty mainImg.connTrgt}"><c:set var="target" value="${mainImg.connTrgt}"/></c:if>
							<div href="${mainImg.connUrl}" target="${target}" class="slide" style="background: #1F1F1F url('<c:url value="/app/file/view2/${mainImg.bkgrFileSn}"/>') center top no-repeat;">
							 	<div class="txt-box">
	                                <p class="tit">${mainImg.bkgrImgNm}</p>
	                                <p class="content">${mainImg.descImgNm}</p>
	                            </div>
							</div>
	                  </c:forEach>
                        
                    </div>
                    <div class="cont-box">
                        <div class="pagerWrap">
                            <span id="adv-custom-pager" title="롤링배너 버튼"></span>
                            <button class="btnStop" data-cycle-cmd="pause">슬라이드 멈춤</button>
                            <button class="btnPlay blind" data-cycle-cmd="resume">슬라이드 다시 시작</button>
                        </div>
                        <script type="text/javascript">
                        //bigSlide button
                        $('.pagerWrap .btnStop').click(function(){
                            $(this).addClass('blind');
                            $('.pagerWrap .btnPlay').removeClass('blind');
                        });
                        $('.pagerWrap .btnPlay').click(function(){
                            $(this).addClass('blind');
                            $('.pagerWrap .btnStop').removeClass('blind');
                        });
                        </script>   
                    </div>
                                        
                </div>
                             
            </div>
            <!-- //mainvisual -->

            <!-- 공지, 분류별강좌 -->
            <div class="section section01">
                <div class="container">

                    
                        <div class="boardList">
                            <div class="title">
                                <h2>공지사항</h2>
                                <a href="<c:url value="/home/main/goMenuPage"/>?mcd=${noticeMcd}" class="more"><span class="sr_only">더보기</span><i class="xi-plus" aria-hidden="true"></i></a> 
                            </div>
                            <ul class="board-latest">
                            	<c:forEach var="item" items="${noticeList}">
								<c:url var="noticeUrl" value="/home/main/goMenuPage?mcd=${noticeMcd}&subParam=read@$bbsCd=NOTICE@$atclSn=${item.atclSn}"/>
								<li><a href="${noticeUrl}" title="${item.atclTitle}">
									<span class="boardTxt">
										<span class="notice">[공지]</span>
											${fn:substring(item.atclTitle,0, 13)}<c:if test="${fn:length(item.atclTitle) > 15 }">...</c:if><c:if test="${item.recently eq 'NEW' }"><img src="/img/common/new.png" /></c:if>
									</span>	
									<span class="boardDate"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></span>
								</a></li>
								</c:forEach>
								<c:if test="${empty noticeList}">
								<li><span style="width:100%"><spring:message code="board.message.bbs.atcl.nodata"/></span></li>
								</c:if>
                            </ul>                            
                        </div>  
                        
                        <div class="iconList">
                            <ul class="list_group">
                                <li>
                                    <h2 class="list_tit">분류별 <br>공개강좌</h2> 
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon01.svg" alt="인문과학 아이콘">
                                        <p class="card_tit">인문과학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon02.svg" alt="사회과학 아이콘">
                                        <p class="card_tit">사회과학</p>                                       
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon03.svg" alt="공학 아이콘">
                                        <p class="card_tit">공학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon04.svg" alt="자연과학 아이콘">
                                        <p class="card_tit">자연과학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon05.svg" alt="교육학 아이콘">
                                        <p class="card_tit">교육학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon06.svg" alt="의약학 아이콘">
                                        <p class="card_tit">의약학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon07.svg" alt="예술체육 아이콘">
                                        <p class="card_tit">예술체육</p>                                        
                                    </a>
                                </li>
                            </ul>
                                                     
                        </div>
                    

                </div>
            </div>
           
            <!-- 전액무료 국비과정-->
            <div class="section section02">
                <div class="container">                        
                   
                    <div class="con_area">
                        <div class="slider_info">
                            <h2>교육과정</h2>                            
                            <a href="#" class="more"><span class="sr_only">더보기</span><i class="xi-angle-right" aria-hidden="true"></i></a>
                        
                            <div class="sort">
                                <button type="button" onclick="courseEnrollList('')" class="active">전체</button>
                            	<c:forEach var="item" items="${courseCategoryList}" varStatus="status">
	        						<c:if test="${item.crsCtgrLvl == 1 && item.useYn == 'Y'}">
	        							<button type="button" onclick="courseEnrollList('${item.crsCtgrCd}')">${item.crsCtgrNm}</button>
	        						</c:if>
	        					</c:forEach>
                            </div>
                                                             
                        </div>
                        
                        <ul class="lecture_list" id="courseEnrollListArea"></ul>                           

                    </div>
                </div>                   
            </div>    

            <!-- Best 강의-->     
            <div class="section section03">
            	${bastPageVO.pageCts }
            </div>

   

<script type="text/javascript">
	
	var modalBox = null;

	 $(document).ready(function(){
		$('.depth1').removeClass('on');
		var date = timeFomatter("${cntCourseVO.enrlAplcEndDttm}");
		CountDownTimer(date);
		courseEnrollList('');
		
	    modalBox = new $M.ModalDialog({
       		"modalid" : "modal1",
       		"nomargin"	: true
       	});  
	    var slider = $('#slides-main').slick({
            arrows: true,
            autoplay: true,
            slidesToShow: 2,
            slidesToScroll: 1,
            responsive: [
                { 
                    breakpoint: 950,
                    settings: {
                    slidesToShow: 1
                    }
                },
                { 
                    breakpoint: 480,
                    settings: {
                    slidesToShow: 1,
                    arrows: true,
                    dots: true
                    }
                }

            ]
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
	    
	    /********** main hot news **********/
	    $('.news_slider_list').slick({
	      infinite: true,
	      arrows: true,
	      dots: false,
	      // autoplay: true,
	      autoplaySpeed: 5000,
	      slidesToShow: 4,
	      slidesToScroll: 1,
	      responsive: [
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
		function timeFomatter(date) {
			var year= date.slice(0, 4);
			var month= date.slice(4, 6);
			var day= date.slice(6, 8);
			var hours= date.slice(8, 10);
			var minutes= date.slice(10, 12);
			var seconds= date.slice(12, 14);
		return year+'/'+month+'/'+day+' '+hours+':'+minutes+':'+seconds;
		}	
		
     function CountDownTimer(dday) {
    	 var md = dday.slice(5, 10);
         var countDownDate = new Date(dday).getTime();
         var x = setInterval(function() {
             var now = new Date().getTime();
             var distance = countDownDate - now;
             var days = Math.floor(distance / (1000 * 60 * 60 * 24));
             var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
             var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
             var seconds = Math.floor((distance % (1000 * 60)) / 1000);

             function showRemaining() {
                 if (days < 0) {
                     days = 0;
                     hours = 0;
                     minutes = 0;
                     seconds = 0;
                     clearInterval(x);
                 }
          		 $('.info').find('#time_ym').html(md);
                 $('.time_area').find('#time_d').html(days+'일');
                 $('.time_area').find('#time_tms').html(hours+':'+minutes+":"+seconds);
             }
             showRemaining();
         }, 1000);
     }
    	
	function courseEnrollList(crsCtgrCd){
		$('#courseEnrollListArea').load(cUrl("/home/main/listCourseEnroll"),{'crsCtgrCd' : crsCtgrCd});
	}
	
	function topCourseSearch() {
		var searchValue = $("#searchValue").val();
		document.location.href = cUrl("/home/course/listSearchCourseFullMain")+"?mcd=${searchFullMcd}${AMPERSAND}searchValue="+searchValue;
	}

   
	
</script>	