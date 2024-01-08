<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
       <main class="main">
            <div id="content">
            <c:if test="${not empty orgInfo.sceneId and not empty orgInfo.roomUrl}">	
                <!-- mv_banner -->
                
                <c:choose>
                	<c:when test="${empty usrUserInfoVO }">
                		<div class="mv_banner">
		                    <div class="ban">
		                        <a href="javascript:alert('로그인이 필요한 서비스입니다.');">
		                            <i class="xi-message big"></i>
		                            <i class="xi-full-moon colorA"></i>
		                            <i class="xi-full-moon colorB"></i>
		                            <i class="xi-full-moon colorC"></i>
		                            <i class="xi-full-moon colorD"></i>
		                            <strong><span>메타버스</span><span>캠퍼스</span></strong>
		                        </a>
		                    </div>
		                </div>	
                	</c:when>
                	<c:otherwise>
                		<c:choose>
		                	<c:when test="${empty usrUserInfoVO.avatar}">
		                		<div class="mv_banner">
				                    <div class="ban">
				                        <a href="javascript:editAvatar();">
				                            <i class="xi-message big"></i>
				                            <i class="xi-full-moon colorA"></i>
				                            <i class="xi-full-moon colorB"></i>
				                            <i class="xi-full-moon colorC"></i>
				                            <i class="xi-full-moon colorD"></i>
				                            <strong><span>메타버스</span><span>캠퍼스</span></strong>
				                        </a>
				                    </div>
				                </div>
		                	</c:when>
		                	<c:otherwise>
		                		<div class="mv_banner">
				                    <div class="ban">
				                        <a href="javascript:goMeta('${orgInfo.roomUrl}','${usrUserInfoVO.avatar}','${usrUserInfoVO.userNm}');">
				                            <i class="xi-message big"></i>
				                            <i class="xi-full-moon colorA"></i>
				                            <i class="xi-full-moon colorB"></i>
				                            <i class="xi-full-moon colorC"></i>
				                            <i class="xi-full-moon colorD"></i>
				                            <strong><span>메타버스</span><span>캠퍼스</span></strong>
				                        </a>
				                    </div>
				                </div>
		                	</c:otherwise>
		                </c:choose>
                	</c:otherwise>
                </c:choose>
                
            </c:if>
                <!-- mainvisual -->
                <div class="mainvisual">
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
                <!-- countdown -->
                <c:if test="${cntCourse ne 0}">
	                <div class="countdown">
	                    <div class="container">
	                        <span class="info"><strong>${cntCourseDate}</strong> ${cntCourseVO.crsCreNm} 모집마감까지</span> 
	                        
	                        <div class="time_area">
	                            <span><i class="xi-alarm-o" aria-hidden="true"></i></span>
	                            <span class="time" id="time_d">일</span>
	                            <span class="time" id="time_tms"> : : :</span>
	                            <span>남았어요 !</span>
	                        </div>
	                    </div>
	                </div>
                </c:if>
                <!-- //countdown -->
            </div>
            <!-- //mainvisual -->
           
            <!-- 전액무료 국비과정-->
            <div class="section section01" >
                <div class="container">                        
                   
                    <div class="con_area">
                        <div class="slider_info">
                            <h2>전액무료! 국비과정</h2>                            
                            <a href="#" class="more"><span class="sr_only">더보기</span><i class="xi-angle-right" aria-hidden="true"></i></a>
                            <div class="desc">4~6개월동안 빅데이터, AI, IoT, 풀스텍코딩에 특화된 실무교육과정으로 전액 무료로 수강할 수 있는 과정입니다.</div>
                            
                            <div class="sort">
          							<button type="button" onclick="courseEnrollList('')" class="active">전체</button>
                       			 <c:forEach var="item" items="${courseCategoryList}" varStatus="status">
	        							<c:if test="${item.crsCtgrLvl == 1 && item.useYn == 'Y'}">
	        								<button type="button" onclick="courseEnrollList('${item.crsCtgrCd}')">${item.crsCtgrNm}</button>
	        						</c:if>
	        					</c:forEach>
                            </div>
                        </div>
                         <div id="courseEnrollListArea"></div>       
                    </div>
                </div>                   
            </div>    

            <!-- Best 강의-->     
            <div class="section section03">
          		${bastPageVO.pageCts }
            </div>
			
			<!-- 이벤트 -->     
            <section class="section section05">
                <div class="container">
                    <div class="popzone">
                        <ul class="pop-latest">
                         <c:forEach var="event" items="${eventList}">
							<li><a href="${event.eventUrl}" target="${event.eventTrgt}"><img src="/app/file/view2/${event.eventImgSn}" class="thumb"  alt="${event.eventDesc}"></a></li>
                            </c:forEach>
                        </ul>                   
                    </div>
                </div>
            </section>

            <!-- 수강후기-->
            <section class="section section04 bgGray">
            	${reviewPageVO.pageCts }
            </section>

            <!-- NEWS -->     
            <div class="section section06">
            	${newsPageVO.pageCts }
            </div>

        </main>
        
         <!-- 팝업 보기 -->
    	<c:if test="${popupCnt > 0 }">
	        <div class="popup-wrap on">
	            <div class="inner-box">
	                <ul id="slides-main">
	                <c:forEach items="${popupNoticeList}" var="item" varStatus="status">
	                    <li>
	                        <iframe id="iframe1" src="/home/brd/popup/indexReadPop2?popupSn=${item.popupSn}"></iframe>
	                    </li>
                    </c:forEach>
	                </ul>
	            </div>
	            <div class="popup-overlay"></div>
	        </div>
        </c:if>
        <div class="popup-btn-box">
            <a href="#0" class="popup-close"><i class="xi-close-min"></i></a>
            <a href="#0" class="popup-open"><i class="xi-library-books-o"></i><label class="ui blue small circular label">${popupCnt}</label></a>
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
	
	/**
     * 아바타 편집
     */
    function editAvatar() {
    	var addContent = "<iframe id='avatartForm' name='avatartForm' width='100%' height='100%' "
    		+ "frameborder='0' scrolling='no' src='<c:url value="/home/user/editFormUserAvatar"/>'/>";
    	modalBox.clear();
    	modalBox.addContents(addContent);
    	modalBox.resize(900, 680);
    	modalBox.setTitle("아바타 편집");
    	modalBox.show();
    }
    function goMeta(roomUrl,avatar,userNm){
    	var domain = window.location.protocol+'//'+window.location.hostname;
    	var url = roomUrl+'&avatarUrl='+domain+avatar+'&displayName='+userNm;
    	window.open(url);
    }
   
	
</script>
	