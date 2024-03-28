<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />

<c:set var="bookmarkVO" value="${bookmarkVO}"/>
<c:set var="credit" value="credit"/>
<c:set var="cmiMode" value="normal"/>

<c:if test="${empty STUDENTNO}">
	<c:set var="credit" value="no-credit"/>
	<c:set var="cmiMode" value="browse"/>
</c:if>

<c:if test="${bookmarkVO.todayStudyYn ne 'Y' }">
<script type="text/javascript">
	alert("<spring:message code="lecture.message.contents.alert.onedaystudy"/>");
	self.close();
</script>
</c:if>

<head>
	<base target="_self"></base>
	<meta http-equiv="Content-Type"	content="text/html;	charset=UTF-8">
	<meta http-equiv="Expires" content="-1">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="No-Cache">

	<title>Contents View Page</title>

	<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
	<meditag:js	src="/tpl/${COLOR_TPL}/js/jquery/jquery.form.js" />
	<meditag:js	src="/js/scorm/bookmark.js" />
	<!-- <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/video.css">  -->
	
	<script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/func.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/common.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/class.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/iframeResizer.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/sidebar.min.js"></script>
    <script src="/js/common.js"></script>
	<script src="/js/jquery/jquery.form.js"></script>
	<script src="/tpl/${COLOR_TPL}/js/sidebar.min.js"></script>
	
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/fonts/xeicon.min.css"> 

    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/board.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/contents.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_layout.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_content.css">
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/sidebar.min.css"> 

	<!--[if	IE 6]>
	<script	type="text/javascript" src="/uniedu/js/DD_belatedPNG_0.0.8a.js"	charset="UTF-8"></script>
	<script	type="text/javascript">DD_belatedPNG.fix('img');</script>
	<![endif]-->
	<!--[if	IE 6]>
	<meditag:css href="css/home/ie6.css" />
	<![endif]-->
	<!--[if	IE 7]>
	<meditag:css href="css/home/ie7.css" />
	<![endif]-->
	<style type="text/css">
		#lecQnaForm {padding-bottom: 30px;}
		.vod_header .top_inner .title {padding-left: 308px;}
	</style>

	<script type="text/javascript" defer="defer">

		$(document).ready(function() {
			resizeWin();
			listQstn();
			
			/********** wide-inbox sidebar **********/
			$('.inbox-inquiry').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
			.sidebar('attach events', '.inquiry-button', 'toggle')

			$('.inbox-lecture').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
			.sidebar('attach events', '.lecture-button', 'toggle')
			
			$('.inbox-exam').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
			.sidebar('attach events', '.exam-button', 'toggle')
	
			$('.inbox-report').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
			.sidebar('attach events', '.report-button', 'toggle')
	
		});
		
		$(document).on("click","#qnaSave",function(){
			$('#lecQnaForm').toggle();
			$('#qnaBtns').toggle();
			$('#qnaTitle').focus();
		})

		<c:if test="${review ne 'Y'}">

		<c:if test="${bookmarkVO.prgrChkType ne 'SCORM'}">
		var result;
		var seekTime = 0;
		var prgrRatio = 0;
		var viewTime = 0;
		//var edutrackAPI = window.dialogArguments.edutrackAPI;
		//var edutrackAPI = opener.edutrackAPI;
		//var edutrackAPI = edutrackAPIFrame.edutrackAPI;
		//var edutrackAPI = $("#edutrackAPIFrame").contentWindow.document.edutrackAPI;
		var edutrackAPI = null;

		function initEdutrackAPI() {
			if(edutrackAPI != null) {
				var api = edutrackAPI;
				api.SetStdNo("${STUDENTNO}");
				api.SetSbjCd("${bookmarkVO.sbjCd}");
				api.SetUnitCd("${bookmarkVO.unitCd}");
				api.SetRegNo("${USERNO}");
				api.SetBookmarkType("${bookmarkVO.prgrChkType}");
				api.SetTotalPageCnt("${bookmarkVO.totalPage}");
				api.SetShowTime("${bookmarkVO.critTm}");
				api.SetCredit("${credit}");
				try {
					result = api.Initialize("");
					seekTime = api.GetSeekTime();
					prgrRatio = api.GetValue("prgrRatio");
				} catch (e) {
					alert("<spring:message code="lecture.message.bookmark.alert.isnot.initialized.edutrackadaptor"/> 1");
				}
			} else {
				alert("<spring:message code="lecture.message.bookmark.alert.isnot.called.edutrackadaptor"/> 2");
			}
		}
		
 		function setSeekTime(time) {
			edutrackAPI.SetSeekTime(time);
		}

 
		function getSeekTime() {
			return seekTime;
		}

		function getPrgrRatio() {
			return prgrRatio;
		}

		function initAPI() {}

		function evtOnUnload() {
			result = edutrackAPI.Terminate("");
		}
		</c:if>

		function onloadApiFrame() {
			initAPI();
			initEdutrackAPI();
			var interval = setInterval(function() {
				viewTime++;	
			}, 1000);
			
		}

		window.onunload = onunloadFunction();
		window.onbeforeunload = onunloadFunction();
		
		function onunloadFunction() {
 			edutrackAPI.SetSeekTime(viewTime);
			evtOnUnload();
			window.close();
			opener.location.reload();
		}

		function onunloadForm() {
 			edutrackAPI.SetSeekTime(viewTime);
			evtOnUnload();
			opener.location.reload();
		}
		
		</c:if>
		<c:if test="${review eq 'Y'}">
			function onunloadFunction() {
				window.close();
				opener.location.reload();
			}
	
			function onunloadForm() {
				opener.location.reload();
			}
		</c:if>
		
		function resizeWin() {
			window.resizeTo("${subjectVO.winWidth}", "${subjectVO.winHeight}");
		}


		function MM_findObj(n, d) { //v4.01
			var p,i,x;
			if(!d) d=document;
			if((p=n.indexOf("?"))>0&&parent.frames.length) {
				d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
		  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
		  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
		  if(!x && d.getElementById) x=d.getElementById(n); return x;
		}

		function MM_preloadImages() { //v3.0
		  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
		}

		function MM_swapImgRestore() { //v3.0
		  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		}

		function MM_swapImage() { //v3.0
		  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
		   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
		}

		function resizeIframe(width, height) {
			$("#contentsMain").css("width",width+"px");
			$("#contentsMain").css("height",height+"px");
		}

		function closePop(){
			window.close();
		}
		
		function loadForm() {
			$("#qstnWriteFormDiv").load("/lec/qna/writeQstnFromCnts");
			$("#qstnWriteFormDiv").show();
		}
	
		/* 문의하기 */		
		function unloadForm() {
			$("#qnaTitle").val("");
			$("#qnaCts").val("");
			$("#lecQnaForm").hide();
			$("#qnaBtns").hide();
		}
		
		/* 서브밋 처리 */
		function process(cmd) {
			
			var qnaSn = $("#qnaSn").val();

			$('#lecQnaForm').attr("action","/lec/qna/"+cmd);
			if(!validate(document.lecQnaForm)) return false;
			
			$('#lecQnaForm').ajaxSubmit(
				function(data) {
					if(data.result > 0 ) {						
						alert(data.message);
						listQstn();						
					} else {
						alert(data.message);
					}
				}
			);
		}
		
		/** 문의리스트 */
		function listQstn() {

		 		$.ajax({
					url : '/lec/bookmark/listLecQstn'
					,data : {
						"unitCd":"${param.unitCd}"
					}
					,success : function(result) {
						$('#contentsList').empty();
						$('#contentsList').append(result);
						$('#lecQnaForm').hide();
						$('#qnaBtns').hide();
					}
					,error : function(request,status,error) {
					}
				});
				
		}
		
		/* 글 저장 */
		function addQstn() {
			if($("#qnaTitle").val() == ""){alert("제목을 입력해 주세요."); return false;}
			if($("#qnaCts").val() == ""){alert("내용을 입력해 주세요."); return false;}
			process("writeQstn");
		}

		/* 차시 이동 */
		function goUnitCd(unitCd,type) {
			if(unitCd == ''){
				if(type == 'p'){alert("첫번째 차시입니다.")};
				if(type == 'n'){alert("마지막 차시입니다.")};
				return false;	
			}
			
			onunloadFunction();
			
			var sbjCd = '${contentsVO.sbjCd}';
			location.href = "/lec/bookmark/viewContents"+"?sbjCd="+sbjCd+"&unitCd="+unitCd+"&deviceType=${param.deviceType}&browserType=${param.browserType}";
		}
		
		function goList(url){
			onunloadFunction();
			opener.location.href = url;
			self.close();
		}
		
	</script>
</head>
<body>

<c:if test="${bookmarkVO.todayStudyYn eq 'Y' }">
    <div class="vod_wrap">
        <div class="vod_header">
            <div class="top_inner">
                <div class="title">
	                <button type="button" onclick="goUnitCd('${contentsVO.preUnitCd}','p')"><i class="xi-angle-left" aria-hidden="true"></i></button>
	                ${contentsVO.unitNm }
	                <button type="button" onclick="goUnitCd('${contentsVO.nextUnitCd}','n')"><i class="xi-angle-right" aria-hidden="true"></i></button>
                </div>
                <div class="group_btn">
                    <button type="button" class="btn-line inquiry-button">문의하기</button>
                    <button type="button" class="btn-line lecture-button">목차열기</button>
                    <button type="button" class="btn-line exam-button">시험목록열기</button>
                    <button type="button" class="btn-line report-button">과제목록열기</button>
                    <c:if test="${CLASSUSERTYPE eq 'STU' }">
                		<button onclick="onunloadFunction()" id="closeBtn" type="button" class="btn-line lecture-button">학습종료</button>
					</c:if>
                </div>
            </div>
        </div>
        <div class="vod_content">
			<iframe src="${filePath}" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="no" width="100%" height="90%" frameborder="0" style="padding:0px;"></iframe>
			<c:if test="${bookmarkVO.prgrChkType ne 'SCORM'}">
				<iframe id='edutrackAPIFrame' name='edutrackAPIFrame' width='0' height='0' frameborder='0' src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" onload="onloadApiFrame()"></iframe>
			</c:if>
            <div class="ui right wide sidebar inbox-inquiry">
                <!------------ 문의목록 ------------>
                <c:if test="${CLASSUSERTYPE eq 'STU'}">
	                <div class="board_top">
	                    <div class="page_btn">
	                        <button type="button" class="btn type4" id="qnaSave">등록</button>
	                    </div>
	                </div>
					<div class="grid-row" id="contentsList"></div> 
	                <div class="btns" id="qnaBtns" style="margin-bottom: 30px;">
	                    <button type="button" class="btn gray2" onclick="addQstn()">저장</button>
	                    <button type="button" class="btn type5" onclick="unloadForm()">취소</button>
	                </div>
                </c:if>
                <c:if test="${CLASSUSERTYPE ne 'STU'}">
	            	<div class="grid-row" id="contentsList"></div> 
	            </c:if>
            </div>
            <div class="ui right wide sidebar inbox-lecture">
                <!------------ 강의목록 ------------>
                <div class="learn_top">
                    <ul class="right_state">
                        <li class="step01">학습 미진행</li>
                        <li class="step02">학습 진행중</li>
                        <li class="step03">학습완료</li>
                    </ul>
                </div>
                <div class="course_list">
                	<c:forEach items="${bookmarkList }" var="contentsItem">
	                    <div class="item
	                 		<c:choose>
	                        	<c:when test="${contentsItem.prgrRatio eq 0}">step01</c:when>
	                        	<c:when test="${contentsItem.prgrRatio eq 100}">step03</c:when>
	                        	<c:otherwise>step02</c:otherwise>
	                       	</c:choose>">
	                        <div class="title">
	                            <h5>
	                            	<a href="javascript:void(0);" onclick="goUnitCd('${contentsItem.unitCd}','')" >	                            
		                            	<span>${contentsItem.unitNm }</span>
		                            </a>
	                            </h5>
	                            <c:if test="${CLASSUSERTYPE eq 'STU'}">
		                            <div class="prog_rate">
		                                <ul>
		                                    <li>
		                                        <span class="header">진도율</span><span class="meta">${contentsItem.prgrRatio }%</span>
		                                        <div class="progress">
		                                            <div class="bar red_type" style="width: ${contentsItem.prgrRatio }%;"></div>
		                                        </div>
		                                    </li>
		                                    <c:set var="min"><fmt:formatNumber value="${contentsItem.seekTime/60}" pattern="0"/></c:set>	                                    
		                                    <li>${min }분/${contentsItem.critTm }분</li>
		                                </ul>
		                            </div>
	                            </c:if>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
            </div>
            <div class="ui right wide sidebar inbox-exam">
                <!------------ 시험목록 ------------>
                <c:if test="${CLASSUSERTYPE eq 'STU'}">
	                <c:forEach items="${examListVO }" var="item" varStatus="status">
	                <div class="course_list" style="padding: 3%;">
	                	<div class="item step02">
							<div class="title">
		                    	<h5>   
	                  		        <a href="javascript:void(0);" onclick="goList('/lec/main/goMenuPage?mcd=MC10000022')">	                            
	                            		<span>${item.examTitle }</span>
	                            	</a>
	                            </h5>            
	                            <div class="prog_rate">
	                                <ul>
	                                    <li style="font-size:16px; display: block;">
	                                        <span class="header" style="width: 100%;">시험 날짜</span>
	                                        <span class="meta">${item.examStartDttm} ${item.examStartHour }:${item.examStartMin }~</span>
	                                    </li>
	                                    <li style="font-size:16px; display: unset;">
	                                        <span class="meta" style="margin-right: 5px;">${item.examEndDttm} ${item.examEndHour }:${item.examEndMin }</span>(${item.examStareTm}분)
	                                    </li>
	                                </ul>
	                            </div>
		                    </div> 
	                    </div>   
	                    </div>            
                	</c:forEach>        
	                <c:if test="${empty examListVO }">
	                	<div class="table_list" id="" style="border-bottom: 2px solid #222;">
							<p style="text-align: center; margin: 50px;">등록된 시험이 없습니다.</p>
						</div>
		            </c:if>
	            </c:if>
            </div>
            <div class="ui right wide sidebar inbox-report">
                <!------------과제목록 ------------>
                <c:if test="${CLASSUSERTYPE eq 'STU'}">
	                <c:forEach items="${assignmentListVO }" var="item" varStatus="status">
	                <div class="course_list" style="padding: 3%;">
	                	<div class="item step02">
							<div class="title">
		                    	<h5>   
	                  		        <a href="javascript:void(0);" onclick="goList('/lec/main/goMenuPage?mcd=MC10000025')">	                            
	                            		<span>${item.asmtTitle }</span>
	                            	</a>
	                            </h5>            
	                            <div class="prog_rate">
	                                <ul>
	                                    <li style="font-size:16px; display: unset;">
	                                        <span class="header" style="width: 100%;">과제 기간</span>
	                                        <span class="meta">${item.asmtStartDttm}~</span>
	                                    </li>
	                                    <li style="font-size:16px; display: unset;">
	                                        <span class="meta">${item.asmtEndDttm}</span>
	                                    </li>
	                                </ul>
	                            </div>
		                    </div> 
	                    </div>   
	                    </div>            
                	</c:forEach>        
	                <c:if test="${empty assignmentListVO }">
	                	<div class="table_list" id="" style="border-bottom: 2px solid #222;">
							<p style="text-align: center; margin: 50px;">등록된 과제가 없습니다.</p>
						</div>
		            </c:if>
	            </c:if>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
