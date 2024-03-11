<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="imageBase" value="/img/"/>
<c:url var="imageTpl" value="/tpl/003/img"/>

<div class="learn_top">
                    <div class="left_title">
                        <h3>학습목차</h3>
                        <button type="button" class="list" onclick="listBookmarkMain()">목록</button>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <c:if test="${sbjVO.sbjType eq 'OFF'}"><label class="offline">오프라인</label></c:if>
				            <c:if test="${sbjVO.sbjType eq 'ON'}"><label class="online">온라인</label></c:if>
				            <c:if test="${sbjVO.sbjType eq 'BL'}"><label class="mixing">혼합</label></c:if>
                            <h4>${sbjVO.sbjNm }</h4>
                        </div>
                        
                     	<c:if test="${sbjVO.sbjType eq 'ON' }"><span class="location">온라인</span></c:if>
						<c:if test="${sbjVO.sbjType eq 'OFF' }">
							<c:choose>
								<c:when test='${createCourseVO.oflnEduPlace eq null || createCourseVO.oflnEduPlace eq ""}'></c:when>
								<c:otherwise><span class="location">${createCourseVO.oflnEduPlace}</span></c:otherwise>
							</c:choose>
						</c:if>    
									
                    </div>
                    <div class="course_list">
                    	<c:forEach items="${bookmarkList }" var="contentsItem">
                    		<c:if test="${contentsItem.unitLvl eq 1 }">
		                        <div class="item step02">
		                            <label class="chasi">${contentsItem.unitOdr }차시</label>
		                            <div class="title">
		                                <h5><span>${contentsItem.unitNm }</span>		                                	
		                                	<c:choose>
				                            	<c:when test="${contentsItem.cntsTypeCd eq 'CODING_L' and empty contentsItem.zoomUrl}">
				                            		<img src="${imageTpl}/class/icon_course_code.svg" alt="icon" />
				                            	</c:when>
				                            	<c:when test="${contentsItem.cntsTypeCd eq 'CODING_T' and empty contentsItem.zoomUrl}">
				                            		<img src="${imageTpl}/class/icon_course_code.svg" alt="icon" />
				                            	</c:when>
				                            	<c:when test="${contentsItem.cntsTypeCd eq 'CODING_L' or contentsItem.cntsTypeCd eq 'CODING_T' and not empty contentsItem.zoomUrl}">
				                            		<img src="${imageTpl}/class/icon_course_code.svg" alt="icon" />		                            	
				                            	</c:when>
	 			                            	<c:when test="${contentsItem.cntsTypeCd eq 'META'}">
				                            		<img src="${imageTpl}/class/icon_course_video.svg" alt="icon">
				                            	</c:when>
												<c:otherwise>
													<img src="${imageTpl}/class/icon_course_play.svg" alt="icon" />		      
												</c:otherwise>
											</c:choose>
										</h5>
		                                <div class="disc">
			                                <c:if test="${not empty contentsItem.teacherNm }">
			                                	강사: ${contentsItem.teacherNm }
			                                </c:if>
			                                <c:if test="${not empty contentsItem.classStartTime }">
			                                	/ 강의 시간: <meditag:dateformat type="1" delimeter="." property="${contentsItem.classStartTime}"/>
			                                	<meditag:dateformat type="7" delimeter="." property="${contentsItem.classStartTime}"/> ~ 
			                                	<meditag:dateformat type="7" delimeter="." property="${contentsItem.classEndTime}"/>
			                                </c:if>
		                                </div>
		                            </div>
		                            <div class="button_group">
			                            <c:if test="${not empty contentsItem.atchFilePath}">
			                                <button type="button" class="basic data" onclick="fileDownload('${contentsItem.atchFilePath}')">학습자료 </button>     
			                            </c:if>
			                            
			                            <c:if test="${contentsItem.cntsTypeCd eq 'CODING_L' or contentsItem.cntsTypeCd eq 'CODING_T' or contentsItem.cntsTypeCd eq 'META'}">
				                            <c:if test="${not empty contentsItem.unitFilePath}">
				                                <button type="button" class="basic" onclick="previewShareCnts('${contentsItem.unitFilePath}')">학습영상</button>     
				                            </c:if>
				                        </c:if>  
	
			                            <c:choose>
			                            	<c:when test="${contentsItem.cntsTypeCd eq 'CODING_L'}">
			                            		<c:if test="${not empty contentsItem.zoomUrl}">
			                            			<button type="button" class="gcolor" onclick="window.open('${contentsItem.zoomUrl}');">Zoom 입장</button>			                            	
			                            		</c:if>
			                            		<c:choose>
				                            		<c:when test="${sbjVO.sbjType eq 'OFF'}"> <!-- 오프라인일때 -->
				                            			<c:choose>
				                            				<c:when test="${nowTime > contentsItem.classEndTime}"><!-- 강의시간 이후 일때 -->
				                            					<button type="button" class="primary disabled">강의종료</button>
				                            				</c:when>
				                            				<c:otherwise>
					                            				<button type="button" class="primary" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','','Y')">강의하기</button>
				                            				</c:otherwise>
				                            			</c:choose>
				                            		</c:when>
				                            		<c:otherwise> <!-- 온라인일때 -->
				                            			<c:choose>
				                            				<c:when test="${nowTime < contentsItem.classStartTime}"><!-- 강의시간 이전 일때 -->
				                            					<button type="button" class="primary" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','','Y')">강의보기</button>
				                            				</c:when>
				                            				<c:otherwise>
					                            				<c:choose>
					                            					<c:when test="${nowDate > createCourseVO.enrlStartDttm }"> <!-- 수강기간 이후일 경우 -->
					                            						<button type="button" class="primary" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','','Y')">강의보기</button>
					                            					</c:when>
					                            					<c:otherwise>
					                            						<button type="button" class="primary" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','','Y')">강의하기</button>
					                            					</c:otherwise>
					                            				</c:choose>					                            				
				                            				</c:otherwise>
				                            			</c:choose>                     			
				                            		</c:otherwise>
				                            	</c:choose>
			                            	</c:when>
			                            	<c:when test="${contentsItem.cntsTypeCd eq 'CODING_T'}">
	                            				<c:choose>
		                            				<c:when test="${nowTime > contentsItem.classEndTime}"><!-- 강의시간 이후 일때 -->
		                            					<button type="button" class="primary disabled">강의종료</button>
		                            				</c:when>
		                            				<c:otherwise>
			                            				<button type="button" class="primary" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','${contentsItem.asmtSn}')">강의하기</button>
		                            				</c:otherwise>
		                            			</c:choose>  
			                            		<c:if test="${not empty contentsItem.zoomUrl}">
			                            			<button type="button" class="primary" onclick="window.open('${contentsItem.zoomUrl}');">Zoom 입장</button>		                            	
			                            		</c:if>
			                            	</c:when>
 			                            	<c:when test="${contentsItem.cntsTypeCd eq 'META'}">
			                            	 	<c:choose>
 			                            			<c:when test="${sbjVO.sbjType eq 'OFF' }">
 			                            				<c:if test="${nowTime > contentsItem.classEndTime}">
 			                            					<button type="button" class="primary disabled" >강의종료</button>
 			                            				</c:if>
 			                            			</c:when>
 			                            			<c:otherwise>
 			                            				<button type="button" class="primary" onclick="window.open('${contentsItem.unitFilePath}?sceneId=${contentsItem.sceneId}&avatarUrl=${productDomain}${userVo.avatar}&displayName=${userVo.userNm}');">강의하기</button> 			                            			
 			                            			</c:otherwise>
 			                            		</c:choose>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<c:if test="${not empty contentsItem.unitFilePath || contentsItem.unitType eq 'C'}"> <!-- 콘텐츠 연결이 있을때만 버튼 노출 || 페이지 방식일 경우 버튼 노출 -->  
				                            		<c:choose>
	 			                            			<c:when test="${sbjVO.sbjType eq 'OFF' }">
	 			                            				<c:choose>
				                            					<c:when test="${nowTime > contentsItem.classEndTime}"> <!-- 강의시간 이후일 경우 -->
				                            						<button type="button" class="primary disabled" >강의종료</button>
				                            					</c:when>
				                            					<c:otherwise>
				                            						<button type="button" class="primary" title="강의하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}')">강의하기</button>
				                            					</c:otherwise>
				                            				</c:choose>
	 			                            			</c:when>
	 			                            			<c:when test="${sbjVO.sbjType eq 'ON' }">
			                            					<c:choose>
				                            					<c:when test="${nowDate > createCourseVO.enrlEndDttm }"> <!-- 수강기간 이후일 경우 -->
				                            						<button type="button" class="primary disabled" >강의종료</button>
				                            					</c:when>
				                            					<c:otherwise>
				                            						<button type="button" class="primary" title="강의하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}')">강의하기</button>
				                            					</c:otherwise>
				                            				</c:choose>
	 			                            			</c:when>
	 			                            			<c:otherwise><button type="button" class="primary" title="강의하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}')">강의하기</button></c:otherwise>
		 			                         		</c:choose>
							                    </c:if>
			                            	</c:otherwise>
			                            </c:choose>	
		                            </div>
		                        </div>
	                        </c:if>
                        </c:forEach>
                    </div>
                </div>
	<style>
		.disabled {
		    pointer-events: none;
		    cursor: not-allowed;
		    opacity: .65;
		}
	</style>                  
       
<script>

var API_1484_11 = null;
var edutrackAPI = null;
var otpModalBox = null;
var modalBox = null;
var ItemVO1 = {};


function listBookmarkMain(){
	document.location.href =  "/lec/bookmark/listBookmarkMain";
}

//코딩강의
function viewCodingA() {
	document.location.href =  "/lec/bookmark/viewCodingLessons";
}

//코딩실습
function viewCodingB() {
	document.location.href =  "/lec/bookmark/viewCodingPractice";
}

function viewContents(sbjCd, unitCd) {
	var deviceType = "PC";
	if(isMobile()){
		deviceType = "MOBILE";
	}
	
	if(sbjCd == '' || sbjCd == undefined) sbjCd = ItemVO1.sbjCd;
	if(unitCd == '' || unitCd == undefined) unitCd = ItemVO1.unitCd;
	var url = cUrl("/lec/bookmark/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+unitCd+"&deviceType="+deviceType+"&browserType="+browserType+"&review=Y";
	var winOption = "fullscreen=yes,left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=no,resizable=yes";
	var contentsWin = window.open(url, "contentsWin");
	if(contentsWin != null){
		contentsWin.focus();
	}
	pop_motp_close();
}

function checkDayLimit(sbjCd, unitCd) {	
	viewContents(sbjCd, unitCd);
}

function pop_motp(evalCd,sbjCd, unitCd){
	var url = generateUrl("/mng/etc/HrdApi/viewMotp",{ "evalCd": evalCd ,"sbjCd":sbjCd, "unitCd":unitCd, "crsCreCd":"${createCourseVO.crsCreCd}"});
	var addContent  = "<iframe id='viewMotpFrame' name='viewMotpFrame' width='100%' height='100%' "
	+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
	otpModalBox.clear();
	otpModalBox.addContents(addContent);
	otpModalBox.resize(500, 680);
	otpModalBox.setTitle("M-OTP 인증");
	otpModalBox.show();
}

function pop_motp_close(){
	otpModalBox.clear();
	otpModalBox.close();
}

function veiwMeta(id){
	
}

function listBookmarkLogPop(sbjCd, unitCd){
	var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/bookmark/listBookmarkLogPop"/>"
		+ "?sbjCd="+sbjCd+"&unitCd="+unitCd+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1400, 800);
	modalBox.setTitle("수강기록");
	modalBox.show();
}


function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}


function isMobile(){
	var mobileKeyWords = ['iPhone', 'iPod', 'iPad', 'BlackBerry', 'Android', 'Windows CE', 'LG', 'MOT', 'SAMSUNG', 'SonyEricsson']
		,blnMobile = false
		,nu = navigator.userAgent;
	for(var word in mobileKeyWords){
	  if (nu.match(mobileKeyWords[word]) != null){
	    blnMobile = true;
	    break;
	  }
	}
	return blnMobile;
}

function fileDownload(url) {
	// download용 iframe이 없으면 만든다.
	if ( $("#_m_download_iframe").length == 0 ) {
		iframeHtml =
			'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>' +
			'<form name="_m_download_form" id="_m_download_form" target="_m_download_iframe"></form>';
		$("body").append(iframeHtml);
	}
	//_m_download_iframe.document.location.href = url;
	// 폼에 action을 설정하고 submit시킨다.
	$("#_m_download_form").attr('action', url).submit();
}

/* 학습영상 미리보기 */
function previewShareCnts(cntsCd) {
	var url = cUrl("/home/library/clibMediaCnts/preview")+"?cntsCd="+cntsCd;
	var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=780,height=480";
	var contentsWin = window.open(url, "contentsWin", winOption);
	contentsWin.focus();
}

</script>	    