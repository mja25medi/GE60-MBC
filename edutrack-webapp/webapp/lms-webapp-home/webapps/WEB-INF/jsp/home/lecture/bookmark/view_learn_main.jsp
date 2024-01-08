<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

<c:url var="imageBase" value="/img/"/>
<c:url var="imageTpl" value="/tpl/003/img"/>

				<div class="learn_top">
                    <div class="left_title">
                        <h3>학습목차</h3>
                        <button type="button" class="list" onclick="listBookmarkMain()">목록</button>
                    </div>
                    <ul class="right_state">
                        <li class="step01">학습 미진행</li>
                        <li class="step02">학습 진행중</li>
                        <li class="step03">학습완료</li>
                    </ul>
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
                    	<c:forEach items="${bookmarkList }" var="contentsItem" varStatus="status">
                    		
                    		<!-- 순차/랜덤 학습 변수 -->
							<c:set var="btnDisabled" value=""/>
                    		<c:if test="${status.count ne '0' and beforePrgr < 100 and sbjVO.studyMthd eq 'SE' and contentsCnt > 0}">
                    			<c:set var="btnDisabled" value="disabled"/>	
                    		</c:if>
                    		
                    		<c:if test="${contentsItem.unitLvl eq 1 }">
		                        <div class="item
		                            <c:choose>
			                        	<c:when test="${contentsItem.prgrRatio eq 0}">step01</c:when>
			                        	<c:when test="${contentsItem.prgrRatio eq 100}">step03</c:when>
			                        	<c:otherwise>step02</c:otherwise>
		                        	</c:choose>">

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
			                                	강사: ${contentsItem.teacherNm } /
			                                </c:if>
			                                <c:if test="${not empty contentsItem.classStartTime }">
			                                	 강의 시간: <meditag:dateformat type="1" delimeter="." property="${contentsItem.classStartTime}"/>
			                                	<meditag:dateformat type="7" delimeter="." property="${contentsItem.classStartTime}"/> ~ 
			                                	<meditag:dateformat type="7" delimeter="." property="${contentsItem.classEndTime}"/>
			                                	 
			                                </c:if>
			                                진도율:<strong class="fcBlue"> ${contentsItem.prgrRatio}%</strong>
		                                </div>
		                            </div>
		                            <div class="button_group">
		                           
<%-- 			                                <div role="progressbar" class="w40">   
			                                    <div class="circletype" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0" style="--value:${contentsItem.prgrRatio } "></div> ${contentsItem.prgrRatio }%
			                                </div> --%>
			                            <c:if test="${not empty contentsItem.atchFilePath}">
			                                <button type="button" class="basic data" onclick="fileDownload('${contentsItem.atchFilePath}')">학습자료</button>     
			                            </c:if>
			                            
			                            <c:if test="${contentsItem.cntsTypeCd eq 'CODING_T'}">
				                            <c:if test="${not empty contentsItem.unitFilePath}">
				                                <button type="button" class="basic" onclick="previewShareCnts('${contentsItem.unitFilePath}')">학습영상</button>     
				                            </c:if>
				                        </c:if>    
			                            
	                            		<c:if test="${not empty contentsItem.zoomUrl}">
	                            			<button type="button" class="gcolor" onclick="window.open('${contentsItem.zoomUrl}');">Zoom 입장</button>			                            	
	                            		</c:if>
	
			                            <c:choose>
			                            	<c:when test="${contentsItem.cntsTypeCd eq 'CODING_L'}"> <!-- 코딩강의 -->
				                            	<c:choose>
				                            		<c:when test="${sbjVO.sbjType eq 'OFF'}"> <!-- 오프라인일때 -->
				                            			<c:choose>
				                            				<c:when test="${nowTime > contentsItem.classStartTime and nowTime < contentsItem.classEndTime}"><!-- 강의시간내일때 -->
						                            			<button type="button" class="primary ${btnDisabled}" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">학습하기</button>			                            						                            				
				                            				</c:when>
				                            				<c:when test="${nowTime > contentsItem.classEndTime}"><!-- 강의시간 이후 일때 -->
				                            					<button type="button" class="primary ${btnDisabled}" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','','Y')">복습하기</button>
				                            				</c:when>
				                            				<c:otherwise><!-- 강의시간이 아닐때 -->
				                            					<c:set var="btnDisabled" value="disabled"/>	
					                            				<button type="button" class="primary ${btnDisabled}">학습하기</button>
				                            				</c:otherwise>
				                            			</c:choose>
				                            		</c:when>
				                            		<c:otherwise> <!-- 온라인일때 -->
					                            		<button type="button" class="primary" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">학습하기</button>				                            			
				                            		</c:otherwise>
				                            	</c:choose>
			                            	</c:when>
			                            	<c:when test="${contentsItem.cntsTypeCd eq 'CODING_T'}">
			                            		 <c:choose>
 			                            			<c:when test="${nowTime > contentsItem.classStartTime and nowTime < contentsItem.classEndTime}"> <!-- 강의시간내 -->
														<button type="button" class="primary ${btnDisabled}" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','${contentsItem.asmtSn}')">학습하기</button>
 			                            			</c:when>
 			                            			<c:when test="${nowTime < contentsItem.classStartTime}"> <!-- 강의시간 이전 -->
 			                            			</c:when>
 			                            			<c:otherwise>
 			                            				<c:set var="btnDisabled" value="disabled"/>	
 			                            				<button type="button" class="primary ${btnDisabled}" >학습종료</button>
 			                            			</c:otherwise>
 			                            		</c:choose>			                            		
			                            	</c:when>
 			                            	<c:when test="${contentsItem.cntsTypeCd eq 'META'}">
 			                            		<c:choose>
 			                            			<c:when test="${nowTime > contentsItem.classStartTime and nowTime < contentsItem.classEndTime}">
 			                            				<c:if test="${empty userVo.avatar}">
 			                            					<button type="button" class="primary ${btnDisabled}" onclick="editAvatar();">학습하기</button>
 			                            				</c:if>
 			                            				<c:if test="${not empty userVo.avatar}">
 			                            					<button type="button" class="primary ${btnDisabled}" onclick="goMeta('${contentsItem.unitFilePath}','${contentsItem.sceneId}','${productDomain}','${userVo.avatar}','${userVo.userNm}','${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.roomId}');">학습하기</button>
 			                            				</c:if>
 			                            			</c:when>
 			                            			<c:otherwise>
 			                            				<c:set var="btnDisabled" value="disabled"/>	
 			                            				<button type="button" class="primary ${btnDisabled}" >학습종료</button>
 			                            			</c:otherwise>
 			                            		</c:choose>
			                            	</c:when>
											<c:otherwise>
					                            	<%-- 랜덤학습 미적용으로 주석처리 2023.12.19
					                            	<c:choose> 
					                            	<c:when test="${contentsCnt > 0 && beforePrgr < 100 }"> <!-- 이전차시 진도율 100% 미달일 경우 disabled -->
					                            		<button type="button" class="primary disabled" title="학습하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">학습하기1</button>
					                            	</c:when>
					                            	<c:otherwise>
					                            		
					                            		<c:choose>
					                            			<c:when test="${nowDate < createCourseVO.enrlStartDttm }">	<!-- 수강기간 이전일 경우 disabled -->
					                            				<button type="button" class="primary disabled" title="학습하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">학습하기2</button>
					                            			</c:when>
					                            			<c:otherwise>
					                            				<c:choose>
					                            					<c:when test="${nowDate < createCourseVO.enrlStartDttm }"> <!-- 수강기간 이후일 경우 -->
					                            						<button type="button" class="default" title="복습하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">복습하기</button>
					                            					</c:when>
					                            					<c:otherwise>
					                            						<button type="button" class="primary ${btnDisabled}" title="학습하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">학습하기</button>
					                            					</c:otherwise>
					                            				</c:choose>
					                            			</c:otherwise>
					                            		</c:choose> 
					                            		--%>
					                            		<c:if test="${not empty contentsItem.unitFilePath}"> <!-- 콘텐츠 연결이 있을때만 버튼 노출 --> 
						                            		<c:choose>
						                            			<c:when test="${nowDate < createCourseVO.enrlStartDttm }">	<!-- 수강기간 이전일 경우 disabled -->
						                            				<button type="button" class="primary disabled" title="학습하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">학습하기</button>
						                            			</c:when>
						                            			<c:otherwise>
						                            				<c:choose>
						                            					<c:when test="${nowDate > createCourseVO.enrlEndDttm }"> <!-- 수강기간 이후일 경우 -->
						                            						<button type="button" class="primary" title="복습하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}','','Y')">복습하기</button>
						                            					</c:when>
						                            					<c:otherwise>
						                            						<button type="button" class="primary ${btnDisabled}" title="학습하기" onclick="checkDayLimit('${contentsItem.sbjCd}','${contentsItem.unitCd}','${contentsItem.prgrRatio}','${contentsItem.cntsTypeCd}')">학습하기</button>
						                            					</c:otherwise>
						                            				</c:choose>
						                            			</c:otherwise>
						                            		</c:choose>
					                            		</c:if>
												
											</c:otherwise>			                            	
			                            </c:choose>
			                            
		                            </div>
		                        </div>
		                        <c:set var="contentsCnt" value="${contentsCnt+1 }"/>
			                    <c:set var="beforePrgr" value="${contentsItem.prgrRatio }"/>
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
	
	
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1",
			"nomargin"	: true
		});
	});	
	
	
	function editAvatar() {
		
		if(confirm("입장을 위해서는 아바타 생성이 필요합니다.\n편집 페이지로 이동하시겠습니까?")){
		
			var addContent = "<iframe id='avatartForm' name='avatartForm' width='100%' height='100%' "
				+ "frameborder='0' scrolling='no' src='<c:url value="/home/user/editFormUserAvatar"/>'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(900, 560);
			modalBox.setTitle("아바타 편집");
			modalBox.show();
		}
	}
	
	
	function goMeta(unitFilePath,sceneId,productDomain,avatar,userNm,sbjCd,unitCd,roomId){
		
		//alert(unitFilePath);
		//alert(roomId);
		//https://room.xrcloud.app:4000/ZQZYC4B/메타버스-메타버스?public=6a8457ab-710e-4717-a112-6e1043d4892b
		//https://room.xrcloud.app:4000/A8dKWB3/빌리버-테스트?public=ada2446d-110f-42c7-bc25-7c9c6566ea56
   		fetch('https://api.xrcloud.app/api/rooms/'+roomId+'?userId=${vo.stdNo}_${vo.crsCreCd}_'+sbjCd+'_'+unitCd, {
   			headers: {'X-XRCLOUD-API-KEY' : '${xrcloud_api_key}'}
   		})
   		.then(resp => resp.json())
   		.then(json => {
   			var obj = JSON.stringify(json);
   			var data = JSON.parse(obj);
   			console.log(data);
   			var roomUrl = data.roomUrl.private.guest;
   			
   			if(roomUrl == "") {
   				alert("api 연동에 실패했습니다.");
   			} else {
   				var domain = window.location.protocol+'//'+window.location.hostname;
   				var url = roomUrl+'&avatarUrl='+domain+avatar+'&displayName='+userNm;
   				window.open(url);
   			}
   			
   		})
   		.catch(error => {
		    alert('api 연동에 실패했습니다.');
		})
		
	}
	
	function listBookmarkMain(){
		document.location.href =  "/lec/bookmark/listBookmarkMain";
	}
	
	//코딩강의
	function viewCodingA() {
		document.location.href =  "/lec/bookmark/viewCodingLessons";
	}
	
	//코딩실습
	function viewCodingB(asmtSn) {
		var crsCreCd = "${createCourseVO.crsCreCd}";
		window.open("/lec/bookmark/viewCodingPracticePop?asmtSn="+asmtSn+"&crsCreCd="+crsCreCd);
	}
	
	function viewContents(sbjCd, unitCd, review) {
		var deviceType = "PC";
		if(isMobile()){
			deviceType = "MOBILE";
		}
		
		if(sbjCd == '' || sbjCd == undefined) sbjCd = ItemVO1.sbjCd;
		if(unitCd == '' || unitCd == undefined) unitCd = ItemVO1.unitCd;
		var url = cUrl("/lec/bookmark/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+unitCd+"&deviceType="+deviceType+"&browserType="+browserType+"&review="+review;
		var winOption = "fullscreen=yes,left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=no,resizable=yes";
		var contentsWin = window.open(url, "contentsWin");
		if(contentsWin != null){
			contentsWin.focus();
		}
		pop_motp_close();
	}
	
	function checkDayLimit(sbjCd, unitCd, prgrRatio, cntsTypeCd, asmtSn , review) {

		// 코딩강의 / 코딩실습일경우
		if(cntsTypeCd == 'CODING_L' || cntsTypeCd == 'CODING_T') {
			var deviceType = "PC";
			if(isMobile()){
				deviceType = "MOBILE";
			}
			
			if(sbjCd == '' || sbjCd == undefined) sbjCd = ItemVO1.sbjCd;
			if(unitCd == '' || unitCd == undefined) unitCd = ItemVO1.unitCd;
			if(cntsTypeCd == 'CODING_T'){
				var url = cUrl("/lec/bookmark/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+unitCd+"&deviceType="+deviceType+"&browserType="+browserType + "&asmtSn="+asmtSn;
			} else {
				var url = cUrl("/lec/bookmark/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+unitCd+"&deviceType="+deviceType+"&browserType="+browserType + "&review="+review;
			}
			var winOption = "fullscreen=yes,left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=no,resizable=yes";
			var contentsWin = window.open(url, "contentsWin");
			if(contentsWin != null){
				contentsWin.focus();
			}
		}
		else if(prgrRatio == 100){	
			viewContents(sbjCd, unitCd);
		}else{
			$.ajax({
				url : '/lec/bookmark/getTotalBookmarkInfo'
				,data : {
					'stdNo' : "${STUDENTNO}"
					,'sbjCd' : sbjCd
					,'unitCd' : unitCd
					,'crsCreCd' : "${createCourseVO.crsCreCd}"
				}
				,success : function(resultVO) {
					ItemVO1.sbjCd = sbjCd;
					ItemVO1.unitCd = unitCd;
					if(resultVO.returnValueCheck == "000"){
						pop_motp('00',sbjCd, unitCd)
					}else if(resultVO.returnValueCheck == "001"){
						pop_motp('01',sbjCd, unitCd)
					}else if(resultVO.returnValueCheck == "002"){
						viewContents(sbjCd, unitCd, review);
					}else if(resultVO.returnValueCheck == "003"){
						alert("일일 차시 제한을 초과하였습니다.");
					}else{
						alert("진행단계 평가 응시 후 수강 가능합니다.");
					}
				}
				,error : function(request,status,error) {	}
			});
		}
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