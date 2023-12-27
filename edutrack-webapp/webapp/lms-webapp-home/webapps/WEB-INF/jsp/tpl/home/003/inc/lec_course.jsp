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
	                              <c:if test="${authGrpCd ne 'TCH'}">
		                            <c:if test="${createCourseVO.creTypeCd eq 'OF' or createCourseVO.creTypeCd eq 'BL' }">
			                            <div class="course_btn">
			                            	<c:if test="${attendanceVO.enterFlag ne 'E'}">
		                                    	<button type="button" class="btn type3" onclick="openQrReader('enter')">출석하기</button>
		                                    </c:if>
		                                    <c:if test="${attendanceVO.enterFlag eq 'E'}">
			                                    <button type="button" id="quitBtn" class="btn type3" onclick="openQrReader('quit')" style="display: none;">퇴실하기</button>
			                                    <button type="button" class="btn type3" onclick="classOutingCheck()">외출/조퇴하기</button>
		                                    </c:if>
		                                </div>
	                                </c:if>
                                </c:if>
                            </div>
                            <ul>
				                <li><span>교육기간</span>${createCourseVO.enrlAplcStartDttm } ~ ${createCourseVO.enrlAplcEndDttm }</li>
				                <li><span>성적열람 시작일</span>${createCourseVO.scoreOpenDttm }</li>
				                <li>
				                <c:if test="${authGrpCd ne 'TCH'}">
				                <span><i class="xi-calendar-check" aria-hidden="true"></i>전체 ${createCourseVO.sbjCnt }개의 과목 중</span>${createCourseVO.sbjCnts }개 수강 완료
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
                    	clock();
                        setInterval(clock, 60000); 
                    });
                    
                    function classOutingCheck(){
                    	var url = '/lec/attend/classOutingCheckPop';
                    	var addContent  = "<iframe id='viewMotpFrame' name='viewMotpFrame' width='100%' height='100%' "
                    	+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
						modalBox.clear();
						modalBox.addContents(addContent);
						modalBox.resize(800, 300);
						modalBox.setTitle("외출/조퇴하기");
						modalBox.show();
					}
                    
                    function modalBoxClose() {
                    	modalBox.clear();
                    	modalBox.close();
                    }
                    
                    //입실
                    /* function enterClass() {
               			var crsCreCd = '${createCourseVO.crsCreCd}'
               			$.getJSON( 
               				cUrl("/lec/attend/enterClass"), 
               				{ "crsCreCd" : crsCreCd },			// params
               				function(data) {
				 	  			if(data.result >= 0) {
				 	  				alert(data.message);
								} else {
									alert(data.message);
								}
               				}
               			);
                	} */
                	function openQrReader(gubun) {
                	    if ('win16|win32|win64|windows|mac|macintel|linux|freebsd|openbsd|sunos'.indexOf(navigator.platform.toLowerCase()) >= 0) {
                	    	 alert("모바일에서 확인해주세요");
                	    }else{
                	      	 window.open("/lec/attend/qrReader?gubun="+gubun);
                	    }
                	}
                    //퇴실
                    /* function quitClass() {
               			var crsCreCd = '${createCourseVO.crsCreCd}'
               			$.getJSON( 
               				cUrl("/lec/attend/quitClass"), 
               				{ "crsCreCd" : crsCreCd },			// params
               				function(data) {
				 	  			if(data.result >= 0) {
				 	  				alert(data.message);
								} else {
									alert(data.message);
								}
               				}
               			);
                	} */
                	function clock(){
                        let today = new Date();
                        let H = today.getHours();
                        let M = today.getMinutes();
                        let S = today.getSeconds();
						if(H>=18 && M>=0) {
							$('#quitBtn').show();
						}
                    }
                    </script>




