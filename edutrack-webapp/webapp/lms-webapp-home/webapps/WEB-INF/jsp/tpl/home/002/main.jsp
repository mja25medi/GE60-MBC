<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ page import="java.util.List"%>
<%@ page import="egovframework.edutrack.comm.util.web.StringUtil"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Map"%>
<%@ page import="egovframework.edutrack.modules.course.createcourse.service.UserCourseVO"%>
<%@ page import="egovframework.edutrack.modules.course.createcourse.service.CreateCourseService"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
           <main>
            <div id="content">
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
						<div href="${mainImg.connUrl}" target="${target}" class="slide" style="background:#eee url('<c:url value="/app/file/view2/${mainImg.bkgrFileSn}"/>') center top no-repeat;background-size : cover;">
						 	<div class="description"> 
                                <div>
                                    <small>4차 산업혁명 핵심 온라인 과정</small>
                                    <h2>데이터분석&활용전문가 양성과정</h2>
                                </div>
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

 					<!-- login -->
                    <form name="userInfoForm" id="userInfoForm" method="post" action="/home/user/login" class="form-inline login" onSubmit="return false;">
	                    <input type="hidden" name="goMcd" value="MC00000000" id="goMcd" />
						<input type="hidden" name="encryptData" id="encryptData" />
	                    <div class="login-wrap">
	                        <div class="container">
                       			<div class="login-form">
		                        	<c:choose>
	                        			<c:when test="${empty USERNO }">
			                                <fieldset>
			                                    <legend>로그인</legend>
			                                    <div class="login-box">
			                                        <div class="login-inp">
			                                            <span class="input-txt">
			                                                <input type="text" id="log_id" class="_enterBind" placeholder="아이디" name="" maxlength="20">
			                                            </span>
			                                            <span class="input-txt">
			                                                <input type="password" id="log_pwd" class="_enterBind" placeholder="비밀번호" name="" maxlength="20">
			                                            </span>
			                                        </div>
			                                        <button type="button" id="login_btn" class="login enterLogin" onclick="">로그인</button>
			                                    </div>                                   
			                                    <div class="chk-wrap">                                           
			                                        <a href="javascript:searchIdModal();">아이디 찾기</a>  
	                                        		<a href="javascript:searchPwModal();">비밀번호 찾기</a>  
			                                        <a href="/home/main/goMenuPage?mcd=HM04002000">회원가입</a>                           
			                                    </div>                                                              
			                                </fieldset>
			                                <script type="text/javascript">
				                        		$("._enterBind").bind("keydown", function(event){
				                        			if($M.Check.Event.isEnter(event)) {
				                        				doLogin();
				                        			}
				                        		});
			                                </script>                               
	                        			</c:when>
		                        		<c:otherwise>
		                        			<fieldset>
			                                    <legend>접속자 정보</legend>
			                                    <div class="login-box">
			                                        <span class="mem-txt">
			                                            <strong>${USERNAME }님</strong>
			                                            <button type="button" class="logout" onclick="location.href='/home/user/logout'">로그아웃</button>
			                                        </span>
			                                        <span class="mem-txt">
			                                            <a href="javascript:viewRecvMsgPop();">
			                                                <i class="xi-note-o"></i> 쪽지
			                                                <em class="badge">${msgCnt }</em>
			                                            </a>
			                                        </span>
			                                        <span class="class-sel">
			                                            <select class="form-select" id="crsCreCdMain" style="max-width: 22rem;">
			                                                <option value="-">과정을 선택하세요</option>
			                                                <c:forEach items="${creCrsList }" var="creItem">
			                                                	<option value="${creItem.crsCreCd }">${creItem.crsCreNm }</option>  
			                                                </c:forEach>
			                                            </select>  
			                                            <button type="button" class="study" onclick="goLectureByMain();">학습하기</button>
			                                        </span>
			                                    </div>                                   
			                                    <div class="chk-wrap">                                           
			                                        <a href="/home/main/goMenuPage?mcd=MC00000035">개인정보수정</a>  
			                                        <a href="/home/main/goMenuPage?mcd=MC00000051">강의실 입장</a>                           
			                                    </div>                                                              
			                                </fieldset>                
		                        		</c:otherwise>
		                        	</c:choose>
	                            </div>
	                            <div class="quick-banner">
	                                <ul>
	                                    <li><a href="/home/main/goMenuPage?mcd=HM02006000" class="icon01">학습상담</a></li>
	                                    <li><a href="/home/main/goMenuPage?mcd=HM02005000" class="icon02">자주하는 질문</a></li>
	                                </ul>
	                            </div> 
	                        </div> 
	                    </div>
	                </form>
                </div>
 
            </div>
            
             <!-- 메인상단 퀵메뉴 -->
            <section class="section section02 active bcDark">
                <div class="container">
                    <div class="mainquickbnr" roll="list">
                        <a href="/home/main/goMenuPage?mcd=MC00000016">
                            <i class="xi-medi-paper1" aria-hidden="true"></i>
                            <span>교육안내</span>
                        </a>
                        <a href="/home/main/goMenuPage?mcd=HM01003000">
                            <i class="xi-medi-course" aria-hidden="true"></i>
                            <span>수강신청</span>
                        </a>
                        <a href="/home/main/goMenuPage?mcd=MC00000045">
                            <i class="xi-medi-certi" aria-hidden="true"></i>
                            <span>자격증신청</span>
                        </a>
                        <a href="/home/main/goMenuPage?mcd=MC00000037">
                            <i class="xi-medi-place" aria-hidden="true"></i>
                            <span>시설/장비</span>
                        </a>
                        <a href="/home/main/goMenuPage?mcd=MC00000051">
                            <i class="xi-medi-print" aria-hidden="true"></i>
                            <span>증명서 발급</span>
                        </a>
                        <a href="/home/main/goMenuPage?mcd=MC00000030">
                            <i class="xi-medi-book" aria-hidden="true"></i>
                            <span>학습자료</span>
                        </a>
                        <a href="/home/main/goMenuPage?mcd=MC00000013">
                            <i class="xi-medi-map" aria-hidden="true"></i>
                            <span>오시는 길</span>
                        </a>
                    </div>
                        
                </div>
            </section>
            
             <!-- 공지사항-->
            <section class="section section02 bcDark">
                <div class="container">
                    <div class="left_area">
                        <div class="board_box">
                            <div class="boardList">
                                <div class="title">
                                    <h2>공지사항</h2>
                                    <a href="/home/main/goMenuPage?mcd=HM02001000" class="more"><span class="sr_only">더보기</span><i class="xi-plus" aria-hidden="true"></i></a> 
                                </div>
                                <c:choose>
                                	<c:when test="${not empty noticeList }">
                                		<ul class="board-latest">
		                                	<c:forEach items="${noticeList }" var="noticeItem">
		                                		<li><a href="/home/brd/bbs/viewAtclMain?bbsCd=${noticeItem.bbsCd }&atclSn=${noticeItem.atclSn }&mcd=HM02001000"><span class="boardTxt"><span class="notice">[공지]</span>${noticeItem.atclTitle }</span><span class="boardDate"><meditag:dateformat type="1" delimeter="." property="${noticeItem.regDttm}" /></span></a></li>
		                                	</c:forEach>
		                                </ul>        
                                	</c:when>
                                	<c:otherwise>
                                		<div class="no-list">등록된 내용이 없습니다.</div>   
                                	</c:otherwise>
                                </c:choose>
                            </div>  
                            <div class="boardList">
                                <div class="title">
                                    <h2>학습자료실</h2>
                                    <a href="/home/main/goMenuPage?mcd=MC00000030" class="more"><span class="sr_only">더보기</span><i class="xi-plus" aria-hidden="true"></i></a> 
                                </div>
                                <c:choose>
                                	<c:when test="${not empty pdsList }">
		                                <ul class="board-latest">
		                                	<c:forEach items="${pdsList }" var="pdsItem">
		                                		<li><a href="/home/brd/bbs/viewAtclMain?bbsCd=${pdsItem.bbsCd }&atclSn=${pdsItem.atclSn }&mcd=MC00000030"><span class="boardTxt">${pdsItem.atclTitle }</span><span class="boardDate"><meditag:dateformat type="1" delimeter="." property="${pdsItem.regDttm}" /></span></a></li>
		                                	</c:forEach>
		                                </ul> 
		                            </c:when>
                                	<c:otherwise>
                                		<div class="no-list">등록된 내용이 없습니다.</div>   
                                	</c:otherwise>
                                </c:choose>                          
                            </div>
                        </div>  
                    </div>
                </div>
            </section>
            
          <!-- 개설강좌 안내-->
            <div class="section section01">
                <div class="container">                        
                    
                    <div class="left_area">
                        <div class="slider_info">
                            <h2>개설강좌 안내</h2>
                            <a href="/home/main/goMenuPage?mcd=MC00000023" class="more"><span class="sr_only">더보기</span><i class="xi-plus" aria-hidden="true"></i></a> 
                        </div>
                        <c:choose>
                           	<c:when test="${not empty onSbjList}">
		                    	<ul class="slider_list">
		                            <c:forEach items="${onSbjList}" var="courseItem">
		                            	<li>
		                            		<div class="card_item">
                                  				<div class="image_area">
                                       				<a href="/home/course/subject/readCourseMain?sbjCd=${courseItem.sbjCd }&mcd=MC00000023" class="img_area" tabindex="-1">
                                            			<img src="/app/file/thumb/${courseItem.thumbFileSn }" alt="${courseItem.sbjNm }">
                                      				 </a>
                                   				</div>
                                  				<div class="des">
                                        			<p class="des1"><a href="/home/course/subject/readCourseMain?sbjCd=${courseItem.sbjCd }&mcd=MC00000023">${courseItem.sbjNm }</a></p>
			                                        <p class="des2">기간 : <span class="stress">${courseItem.eduDaycnt }일</span> / 시간 : <span class="stress">총 ${courseItem.eduTm }시간</span></p>
			                                        <p class="des3">${courseItem.sbjDesc }</p>
                                       				<div class="btn_go">
                                           				<a href="/home/course/subject/readCourseMain?sbjCd=${courseItem.sbjCd }&mcd=MC00000023" tabindex="-1"><button class="btn-custom1" tabindex="-1">과정상세보기</button></a>
                                       				</div>
                                   				</div>
                               				</div>
                        				</li>
                        			</c:forEach>
                        		  </ul>
                        		</c:when>
                        		<c:otherwise>
	                            	<div class="no-list">등록된 과정이 없습니다.</div>
	                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>                   
            </div>  
            
            <!-- 수강후기-->
            <section class="section section02 bgGray">
                <div class="container">        
                     
                    <div class="right_area">
                        <div class="review_wrap">
                            <div class="title">
                               <h2>수강후기</h2>
                               <a href="/home/main/goMenuPage?mcd=MC00000031" class="more"><span class="sr_only">더보기</span><i class="xi-plus" aria-hidden="true"></i></a> 
                            </div>   
                               <c:choose>
                               	<c:when test="${not empty reviewList }">               
		                            <ul class="review">
		                            	<c:forEach items="${reviewList }" var="reviewItem">
											<li>
			                                    <div class="review_name">
			                                    	<div class="extra"> 
				                                        <div class="icon"><img src="/tpl/002/img/main/icon_smile.png"></div>
				                                        <div class="rate">
				                                            <div class="name">${reviewItem.regNm }</div>
				                                            <div class=""><i class="rating" data-rating="${reviewItem.starScore }"></i></div>
				                                        </div>
			                                        </div>
			                                        <a href="/home/brd/bbs/viewAtclMain?bbsCd=${reviewItem.bbsCd }&atclSn=${reviewItem.atclSn }&mcd=MC00000031">
			                                            <p class="review_subject">${reviewItem.atclTitle }</p>
			                                            <p class="review_content">${reviewItem.atclCts }</p>
			                                        </a>
			                                    </div>
			                                </li>                            	
		                            	</c:forEach>
		                            </ul>
		                        </c:when>
		                        <c:otherwise>
		                        	<div class="no-list">등록된 내용이 없습니다.</div>   
		                        </c:otherwise>
		                    </c:choose>
                            <script>
                                $(".rating").starRating({
                                    readOnly: true
                                });
                            </script>                         
                        </div>
                    </div>
                </div>
            </section>

            <!-- 하단 배너 -->
             <section class="sec">
                <div class="container bnr"> 
                	 <c:forEach var="bnnr" items="${bannerList }">
                	 	 <div class="item">
                	 	 	<small><u>비학점</u></small>
	                        <h3 class="title">데이터분석&활용전문가 양성과정</h3>
	                        <p class="title">4차 산업혁명 핵심 온라인 과정</p>
	                       	<div class="button-area">
                           	 	<a href="${item.bnnrUrl }" >자세히 보기</a>
                        	</div>
	                        <img src="/app/file/view2/${bnnr.bnnrImgSn }" aria-hidden="true" alt="${bnnr.bnnrDesc }" class="bg">
                    	</div>
                	 </c:forEach>
                </div>
            </section>
            
            <button type="button" class="go_top"><i class="xi-angle-up-min"></i><span>TOP</span></button>
            
            </main>
            
            <script type="text/javascript">
            	function goLectureByMain(){
            		var crsCreCdMain = $("#crsCreCdMain").val();
            		if(crsCreCdMain != "-"){
	            		document.location.href = cUrl("/home/main/goMenuPage")+"?mcd=MC00000051&subParam=crsCreCd="+$("#crsCreCdMain").val(); 
            		}else{
	            		document.location.href = cUrl("/home/main/goMenuPage")+"?mcd=MC00000051"; 
            		}
            	}
            </script>