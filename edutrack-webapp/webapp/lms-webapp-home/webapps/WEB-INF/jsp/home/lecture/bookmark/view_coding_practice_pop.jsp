<%@ page pageEncoding="utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/tpl/home/003/decorators/pop_lec_decorator.jsp" %>
<c:set var="assignmentSubList" value="${assignmentSubList}"/>
<c:set var="returnAssignmentVO" value="${returnAssignmentVO}"/>
<c:set var="studentVO" value="${studentVO}"/>

<c:set var="bookmarkVO" value="${bookmarkVO}"/>
<c:set var="credit" value="credit"/>
<c:set var="cmiMode" value="normal"/>

<c:if test="${empty STUDENTNO}">
	<c:set var="credit" value="no-credit"/>
	<c:set var="cmiMode" value="browse"/>
</c:if>
	<iframe id='edutrackAPIFrame' name='edutrackAPIFrame'' style="height:0;width:0;border:0;border:none;visibility:hidden;;" src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" onload="onloadApiFrame()"></iframe>
    <div class="coding_wrap">
        <div class="coding_header">
            <h1>${returnAssignmentVO.asmtTitle }</h1>
            <div class="right_util">
            	<c:if test="${sbjVO.sbjType ne 'ON'}">
               		<div class="request">코딩 요청중입니다. 잠시만 기다려 주세요.<strong><i class="xi-alarm-clock-o" aria-hidden="true"></i>00:00</strong></div>
                	<button type="button" class="btn type9">도움요청</button>
                </c:if>
                <button type="button" onclick="onunloadFunction();" class="btn">닫기</button>
            </div>
        </div>
        <div class="panel-container coding-test">
            <div class="panel-left">
                <div class="test_field">
                    <button type="button" class="indent_btn"></button>
                    <ul class="left_number">
                    	<c:forEach items="${assignmentSubList}" var="asList" varStatus="status">
                        	<li <c:if test="${asList.sendCts ne null }" >class="active" </c:if>><a href="javascript:selectAsmtSub(${asList.asmtSubSn })">${status.count}번 문제</a></li>
                        </c:forEach>
                    </ul>
                    <script>
                       $("ul.left_number li").unbind('click').bind('click', function(e) {
                           $("ul.left_number li").removeClass('on');
                           $(this).addClass("on");
                       });
                   </script>
                    <div class="right_content">
                        <div id="asmtSub" class="cont">
                            <label class="h_title">문제 설명</label>
                            <div id="asmtSubDetail" class="mb20"></div>
                            <label class="h_title">제한사항</label>
                            <div id="asmtConstList" class="mb20"></div>
                        </div>
                        <div class="answer">
                            <label for="contTextarea" class="h_title">답안입력</label>
                            <form id="sendForm">
                            
                            </form>
                            <div class="txt-right mt10 mb10">
                                <button id="submitAsmt" type="button" class="btn gray2" onclick="chatGPT()" style="display: none;">제출하기</button>
                                <button id="resultPop" type="button" class="btn gray2" style="display: none;">채점결과</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="splitter"></div>
            <div class="panel-right2">
                <div class="panel-top">
                    <iframe src="${studentVO.ideUrl }" style="height: 100%;"></iframe>
                </div>
                
            </div>
        </div>
    </div>
    <script>
    
	
	
	var result = "";
	var seekTime = "0";
	var prgrRatio = "100";
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
				alert("<spring:message code="lecture.message.bookmark.alert.isnot.initialized.edutrackadaptor"/>");
			}
		} else {
			alert("<spring:message code="lecture.message.bookmark.alert.isnot.called.edutrackadaptor"/>");
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
		
	}
	
	function onloadApiFrame() {
		initAPI();
		initEdutrackAPI();
	}
	
	window.onunload = onunloadFunction;
	window.onbeforeunload = onunloadFunction;

	function onunloadFunction() {
		
		var firstSeekTime = parseInt("${bookmarkVO.critTm}")*60;
		var learnSeekTime = parseInt(edutrackAPI.GetSeekTime());
		var seekTime;
		
		
		if(firstSeekTime <= learnSeekTime){
			seekTime = "0";
		} else {
			seekTime = firstSeekTime;
		}
		
		edutrackAPI.SetValue("prgrRatio", "100");
		edutrackAPI.SetSeekTime(seekTime); //--seek 시간 설정
		result = edutrackAPI.Terminate("");
		opener.reload();
		self.close();
	}
	
    
    var modalBox = null;

    $(document).ready(function() {
    	selectAsmtSub("${assignmentSubList[0].asmtSubSn}")
    	$("ul.left_number li:first").addClass('on');
    	
    	modalBox = new $M.ModalDialog({
    		"modalid" : "modal1",
    		"nomargin"	: true
    	});
    });	
    
     $(".panel-left").resizable({
         handleSelector: ".splitter",
         resizeHeight: false
     });
     
     function selectAsmtSub(asmtSubSn) {
    	 $("#sendForm").empty();
    	 $.getJSON(cUrl("/lec/assignment/getAsmtSub"), 	// url
    				{ 
    				  "asmtSubSn" : asmtSubSn,
    				  "asmtSn" : '${returnAssignmentVO.asmtSn}'
    				}, function(data) { 
    					var asmtSubVO = data.returnVO;
    					var ascVOList = asmtSubVO.asmtSubConstVOList;
    					var constList = "";
    					$("#asmtSubDetail").html(asmtSubVO.asmtCts);
    					for(var i=0; i<ascVOList.length; i++) {
    						constList += "<p name='constNm' id='const_"+i+"'>" + ascVOList[i].constCts +"<br>"
    					}
    					$("#asmtConstList").html(constList);
    					
    					$("#sendForm").append("<div class='form-row'><textarea class='form-control' rows='10' name='sendCts' id='sendCts'>"+asmtSubVO.sendCts+"</textarea></div>");
    					$("#sendForm").append("<input type='hidden' name='sendTitle'  value='"+asmtSubVO.asmtTitle+"_${studentVO.stdNo}'/>");
    					$("#sendForm").append("<input type='hidden' name='asmtSubSn' id='asmtSubSn'  value='"+asmtSubVO.asmtSubSn+"'/>");
    					$("#sendForm").append("<input type='hidden' name='stdNo'  value='${studentVO.stdNo}'/>");
    					$("#resultPop").attr("onclick", "codingTestResult("+asmtSubSn+")");
    					if(asmtSubVO.sendCts != '') {
    						$("#resultPop").show();
    						$("#submitAsmt").hide();
    					} else {
    						$("#resultPop").hide();
    						$("#submitAsmt").show();
    					}
    					
    				}
    			);
     }
         
     function addAsmtSend(){
    	 var asmtSn = "${returnAssignmentVO.asmtSn}"
    	 var asmtSubSn = $("#asmtSubSn").val();
    	 $('#sendForm')
    	 .attr("action","/lec/assignment/addSendCodeAsmt?asmtSn="+asmtSn+"&asmtSubSn="+asmtSubSn);
    	 $('#sendForm').ajaxSubmit(processCallback);
 	}
     
     /**
 	 * 처리 결과 표시 콜백
 	 */
 	function processCallback(resultDTO) {
 		
 		if(resultDTO.result >= 0) {
 			// 정상 처리
 			alert("제출되었습니다.")
 		} else {
 			// 비정상 처리
 			alert(resultDTO.message);
 		}
 	}
 	
 	//chatGPT 채점
	function chatGPT() {
	      const api_key = "${gptKey}"  // <- API KEY 입력
	      var asmtSubDetail = $('#asmtSubDetail').text();
	      var sendCts = $('#sendCts').val();

	      var constStr = "- 테스트케이스 내용 : ";
	      
	      var clist = new Array();
	      $("p[name=constNm]").each(function(index, item){
	   	   		clist.push("테스트케이스"+(index+1)+": "+ $(item).text());
	      });
	      console.log(clist);
	      for(var i=0; i<clist.length; i++) {
	    	  constStr += clist[i];
	      }
	      
	      if(isEmpty(asmtSubDetail)){
	    	  alert('오류가 발생했습니다. 관리자에게 문의하십시오.')
	    	  return false;
	      }
	      if(isEmpty(sendCts)){
	    	  alert('답안을 입력하십시오.')
	    	  return false;
	      }
	      const keywords = "- 문제 : "+ asmtSubDetail
      					 + constStr
      					 + "- 학생이 제출한 코드 : "+ sendCts
      					 + " '문제'를 읽고 '테스트케이스의 내용'을 기준으로 '학생이 제출한 코드'가 '테스트케이스의 각각의 내용'에 부합하는지 채점해주세요."
      					 + " 테스트케이스의 내용에는 각각 번호가 매겨져있습니다. 번호별로 판단해서 학생이 제출한 코드가 테스트케이스의 내용 중 하나의 요소에 부합할 때마다 '테스트케이스+번호: 성공'이라고 출력해주세요."
      					 + "번호별로 판단해서 테스트케이스의 내용 중 하나의 요소에 부합하지 않을 때마다 '테스트케이스+번호: 실패'라고 출력해주세요."
      					 + "테스트케이스의 판단결과의 이유를 한 줄로 짧게 50자 이내로 설명해주세요."
	      console.log(keywords);
	      
	   	  //로딩 팝업
		  $('body').css("overflow", "hidden");
	 	  $("#loading_result").css('display','flex').hide().fadeIn();
	    
	     const messages = [
	        { role: 'system', content: 'You are a helpful assistant.' },
	        { role: 'user', content: keywords },
	      ]

	      const data = {
	        model: '${gptVer}',
	        temperature: 0.5,
	        n: 1,
	        messages: messages,
	      }
			
	      $.ajax({
	        url: "${gptUrl}",
	        method: 'POST',
	        headers: {
	          Authorization: "Bearer " + api_key,
	          'Content-Type': 'application/json',
	        },
	        data: JSON.stringify(data),
	      }).then(function (response) {
	    	  $("#loading_result").hide();
	    	  $("#sendForm").append("<textarea class='form-control' rows='10' id='gptResult' name='gptResult' style='display: none;'/></textarea>");
	    	  document.getElementById('gptResult').innerHTML = response.choices[0].message.content;
	    	  addAsmtSend();
	      });
	    }
	
	function isEmpty(str){
		if(typeof str == "undefined" || str == null || str == "")
			return true;
		else
			return false ;
	}
	
	//채점결과 팝업 
 	function codingTestResult(asmtSubSn) {
		var crsCreCd = '${returnAssignmentVO.crsCreCd}';
		var asmtSn = ${returnAssignmentVO.asmtSn};
		var stdNo = '${studentVO.stdNo}';
		var addContent  = "<iframe id='codingTestResultFrame' name='codingTestResultFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/bookmark/codingTestResultPop"/>"
			+ "?crsCreCd="+crsCreCd+"&asmtSn="+asmtSn+"&stdNo="+stdNo+"&asmtSubSn="+asmtSubSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("채점 결과");
		modalBox.show();
	}
 	
 	function modalBoxClose() {
 		modalBox.clear();
 		modalBox.close();
 	}
    </script>

<!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
<!-- <button class="modal__btn" onclick="codingTestResult()" style="width: 100%;">채점결과 팝업</button> -->

<!-- 로딩 popup -->
<div id="loading_result" class="modal_popup_wrap">
	<div class="modal_popup modal_medium">
		<div id="loading" class="multi_inbox" style="padding:15px; text-align: center;">
		    <div data-aos="fade-right" data-aos-anchor=".info-element" id="load-ing" style="height: 160px;"></div>
		    <script>
		        var animData = {
		            container: document.getElementById('load-ing'),
		            animType: 'svg',
		            loop: true,
		            prerender: false,
		            autoplay: true,
		            path: '/img/Animation_load.json'
		
		        };
		        var anim = bodymovin.loadAnimation(animData);
		        window.onresize = anim.resize.bind(anim);
		    </script>
		    <b>채점 중...</b>
		</div>
	</div>
</div>
