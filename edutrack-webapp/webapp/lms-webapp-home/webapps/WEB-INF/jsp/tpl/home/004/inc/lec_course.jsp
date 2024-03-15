<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<%
	String authGrpCd = UserBroker.getClassUserType(request);
	request.setAttribute("authGrpCd", authGrpCd);
%>
                    <div class="course_info">
                        <label class="category">${courseVO.crsCtgrNm }</label>
                        <div class="item">
                        	<div class="class_row">
	                            <h2>${createCourseVO.crsCreNm }</h2>
	                              <c:if test="${authGrpCd eq 'TCH'}">
	                              	  <button type="button" class="btn type6" onclick="resHelp()" style="display: none;" id="resHelp">코딩실습 도움주기</button>
	                              </c:if>
	                              <c:if test="${authGrpCd ne 'TCH'}">
		                            <c:if test="${createCourseVO.creTypeCd eq 'OF' or createCourseVO.creTypeCd eq 'BL' }">
			                            <c:if test="${attendanceVO.enterFlag ne 'E'}">
	                                    	<button type="button" class="btn type3" onclick="enterClass()">출석하기</button>
	                                    </c:if>
	                                    <c:if test="${attendanceVO.enterFlag eq 'E'}">
		                                    <button type="button" id="quitBtn" class="btn type3" onclick="quitClass()" style="display: none;">퇴실하기</button>
		                                    <button type="button" class="btn type3" onclick="classOutingCheck()">외출/조퇴하기</button>
	                                    </c:if>
	                                </c:if>
                                </c:if>
                            </div>
                            <ul>
				                <li><span>교육기간</span>${createCourseVO.enrlStartDttm } ~ ${createCourseVO.enrlEndDttm }</li>
				                <c:if test="${createCourseVO.crsOperType eq 'R' }">
				                	<li><span>성적열람 시작일</span>${createCourseVO.scoreOpenDttm }</li>
				                </c:if>
				                <li>
					                <c:if test="${authGrpCd ne 'TCH'}">
					                <span><i class="xi-calendar-check" aria-hidden="true"></i>전체 ${createCourseVO.sbjCnt }개의 과목 중</span>
						            <c:if test="${empty createCourseVO.sbjCnts}">0</c:if><c:if test="${not empty createCourseVO.sbjCnts}">${createCourseVO.sbjCnts }</c:if>개 수강 완료
					                </c:if>
					                <c:if test="${authGrpCd eq 'TCH'}">
					                <span><i class="xi-calendar-check" aria-hidden="true"></i>전체 ${createCourseVO.sbjCnt }개의 과목</span>
					                </c:if>
				                </li>					                
                            </ul>
                        </div>
                    </div>
                    
                    <script>
                    
                    $(document).ready(function() {
                    	callRedis();
                    	setInterval(callRedis, 3000);
                    	clock();
                        setInterval(clock, 60000); 
                    });
                    
                    function classOutingCheck(){
                    	var url = '/lec/attend/classOutingCheckPop';
                    	var addContent  = "<iframe id='viewMotpFrame' name='viewMotpFrame' width='100%' height='100%' "
                    	+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
						modalBox.clear();
						modalBox.addContents(addContent);
						modalBox.resize(700, 500);
						modalBox.setTitle("외출/조퇴하기");
						modalBox.show();
					}
                    
                    function modalBoxClose() {
                    	modalBox.clear();
                    	modalBox.close();
                    }
                    
                    //입실
                    function enterClass() {
               			var crsCreCd = '${createCourseVO.crsCreCd}'
               			var url = '/lec/attend/enterClass?crsCreCd='+crsCreCd;
               			var addContent  = "<iframe id='viewEnterFrame' name='viewEnterFrame' width='100%' height='100%' "
                        	+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
    						modalBox.clear();
    						modalBox.addContents(addContent);
    						modalBox.resize(700, 500);
    						modalBox.setTitle("출석하기");
    						modalBox.show();
               			
                	}
                	function openQrReader(gubun) {
                	    if ('win16|win32|win64|windows|mac|macintel|linux|freebsd|openbsd|sunos'.indexOf(navigator.platform.toLowerCase()) >= 0) {
                	    	 alert("모바일에서 확인해주세요");
                	    }else{
                	      	 window.open("/lec/attend/qrReader?gubun="+gubun);
                	    }
                	}
                    //퇴실
                    function quitClass() {
                    	var crsCreCd = '${createCourseVO.crsCreCd}'
                   		var url = '/lec/attend/quitClass?crsCreCd='+crsCreCd;
                   		var addContent  = "<iframe id='viewEnterFrame' name='viewEnterFrame' width='100%' height='100%' "
                           	+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
       						modalBox.clear();
       						modalBox.addContents(addContent);
       						modalBox.resize(700, 500);
       						modalBox.setTitle("퇴실하기");
       						modalBox.show();
                	}
                    
                	function clock(){
                        let today = new Date();
                        let H = today.getHours();
                        let M = today.getMinutes();
                        let S = today.getSeconds();
						if(H>=18 && M>=0) {
							$('#quitBtn').show();
						}
                    }
                	
                	 function resHelp(){
                     	var url = '/lec/bookmark/helpListPop';
                     	var addContent  = "<iframe id='viewListFrame' name='viewListFrame' width='100%' height='100%' "
                     	+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
 						modalBox.clear();
 						modalBox.addContents(addContent);
 						modalBox.resize(800, 800);
 						modalBox.setTitle("페어코딩 요청확인");
 						modalBox.show();
 					}
                	 
                	//redis 도움 요청 조회
               	 	function callRedis(){
               	 		$.getJSON(cUrl("/lec/bookmark/callRedis"), 	// url
               	 				{ 
               	 				  "crsCreCd" : '${createCourseVO.crsCreCd}'
               	 				}, function(data) { 
               	 					if(data.result < 0) {
               	 						$("#resHelp").hide();
               	 					} else if (data.result > 0) {
               	 						$("#resHelp").show();
               	 					}
               	 				}
               	 			);
               	 	}
                    </script>




