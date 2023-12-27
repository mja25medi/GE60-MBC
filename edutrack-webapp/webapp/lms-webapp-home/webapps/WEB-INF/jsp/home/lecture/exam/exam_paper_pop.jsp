<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="examStareVO" value="${vo}"/>

                <div class="modal_cont">
				<c:if test="${examVO.stareTmUseYn eq 'Y' }">
                    <div class="test_count">
                        <div class="total">
                            <div class="time"><strong><spring:message code="lecture.title.exam.time.total"/></strong>${examVO.examStareTm} <spring:message code="common.title.min"/></div>
                            <div class="remain"><i class="xi-alarm-o"></i><spring:message code="lecture.title.exam.time.remaining"/> : <strong id="leftTimeZone"></strong></div>
                        </div>
                    </div>
				</c:if>
                <span class="disc"><i class="xi-error"></i>응시 후 반드시 제출하기 버튼을 누르셔야 합니다.</span>
		<form id="examForm" name="examForm" onsubmit="return false" method="post">
			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
			<input type="hidden" name="examSn" value="${vo.examSn}" />
			<input type="hidden" name="stdNo" value="${vo.stdNo}" />
			<input type="hidden" name="getScores" value="${vo.getScores}" />
			<input type="hidden" name="stareTm" value="${vo.stareTm}"/>
			<input type="hidden" name="totGetScore" value="${vo.totGetScore}" />
			<input type="hidden" name="stareAnss" value="${vo.stareAnss}" />
			<input type="hidden" name="stareCnt" value="${vo.stareCnt}" />
 			<c:forEach items="${questionList}" var="item" varStatus="status">
   
                    <div class="list_view_box">
                        <div class="list_title">
                            <div class="type"><span class="num">${status.count}.</span><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" /> 문항</div>
                            <div class="score"><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" /> : ${item.qstnScore}<spring:message code="common.title.score"/></div>
                        </div>
                        <div class="list_item">
                            <div class="quest">${item.title}</div>
                            <c:if test="${not empty item.qstnCts}">
	                        <div class="exam_area">${item.qstnCts}</div>
	                        </c:if>
                            
						<c:if test="${item.qstnType eq 'J'}">	<!-- 서술형 -->
							<div class="form-row">
       							<textarea name="ans_${status.count}" id="ans_${status.count}" rows="5"  class="form-control"  placeholder="답을 입력하세요"  onpaste="return false;" oncopy="return false;"></textarea>
       						</div>
        				</c:if>
        				<c:if test="${item.qstnType eq 'D'}">	<!--  단답형 -->
	                        <div class="form-inline">
	                                <input class="form-control" type="text" name="ans_${status.count}" id="ans_${status.count}" placeholder="답을 입력하세요" onpaste="return false;" oncopy="return false;">
<!-- 	                                <input class="form-control" type="text" name="name" id="nameInput" placeholder="2번 답">
	                                <input class="form-control" type="text" name="name" id="nameInput" placeholder="3번 답">
 -->	                                
	                        </div>
         				</c:if>	
						<c:if test="${item.qstnType eq 'K'}">	<!-- 선택형 -->
                            <ol class="field">
							<c:if test="${not empty item.empl1}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_01" name="ans_${status.count}" class="hidden" value="1"><label for="ans_${status.count}_01">${item.empl1}</label></li>
							</c:if>
							<c:if test="${not empty item.empl2}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_02" name="ans_${status.count}" class="hidden" value="2"><label for="ans_${status.count}_02">${item.empl2}</label></li>
							</c:if>
							<c:if test="${not empty item.empl3}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_03" name="ans_${status.count}" class="hidden" value="3"><label for="ans_${status.count}_03">${item.empl3}</label></li>
							</c:if>
							<c:if test="${not empty item.empl4}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_04" name="ans_${status.count}" class="hidden" value="4"><label for="ans_${status.count}_04">${item.empl4}</label></li>
							</c:if>
							<c:if test="${not empty item.empl5}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_05" name="ans_${status.count}" class="hidden" value="5"><label for="ans_${status.count}_05">${item.empl5}</label></li>
							</c:if>
                            </ol>
						</c:if>
          				<c:if test="${item.qstnType eq 'S'}">	<!-- 진위형 -->
                            <div class="checkImg">
                                <input id="ans_${status.count}_01" type="radio" name="ans_${status.count}" value="O">
                                <label class="imgChk true" for="ans_${status.count}_01"></label>
                                <input id="ans_${status.count}_02" type="radio" name="ans_${status.count}" value="X">
                                <label class="imgChk false" for="ans_${status.count}_02"></label>
                            </div>

        				</c:if>                            

                        </div>
                    </div>
            </c:forEach>        
 		</form>
 
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn" onclick="submitPaper('N');"><spring:message code="button.add.temporary"/></button>
                    <button type="button" class="btn type2" onclick="submitPaper('Y');"><spring:message code="button.send.asmt"/></button>
                </div>



<script type="text/javascript">
	/* 글 저장 */
	var tmUseYn = '${examVO.stareTmUseYn}';
	var sendYn	= '';
	var timeOut = 'N';
	$(document).ready(function() {
		$("body").css("padding","0px").css("min-height","0px");
		//직접 URL 접속 막기
		//if(parent.opener == undefined){
		//	alert("<spring:message code="lecture.message.exam.stare.alert.noconnectpath"/>");
		//	document.location.href ="about:blank";
		//}
		$("#frmdvwrap").css("overflow-y","scroll").css("height","540px");
		$(".dvcontainer").css("margin-top","10px");
		if(tmUseYn=='Y')	examTimeShow("${examVO.examStareTm * 60}");

/* 		$(document).bind('contextmenu', function(){
	        alert("<spring:message code="common.message.mouse.leftclick"/>");
	        event.returnValue = false;
	        return false;
	    });

		$(document).keydown(function(event){
			if(event.keyCode == 116){
				event.keyCode = 0;
				alert("<spring:message code="common.message.noreload"/>");
				return false;
			}
	    }); */

	    $(window).bind("load resize", function() {
	    	resizeSubContentsByClass("exam-contents", 80);
	    });
	    <c:if test="${examStareVO.stareCnt > 0}" >
	    setAnswer();
	    </c:if>
	    
	    
	    /*parent.$('#examModal').on('hide.bs.modal ', function (e) {
			e.preventDefault();
	        if(confirm('시험 응시 도중 나가시겠습니까? 입력한 답으로 제출합니다.')){
		        submitPaper('Y');
	        }else{
		        //return;
	        }
	    });
	    */
	});

	//-- 시험시간을 초로 받아 남은 시간을 화면에 표시 하고 돌려 준다.
	function examTimeShow(extime) {
		var examStareTm = ${examVO.examStareTm * 60}
		leftTime = extime;
		lastTime = leftTime;
		//--- 남은 시간이 없으면... 시험전송
		if(leftTime <= 0) {
			alert("<spring:message code="lecture.message.exam.stare.alert.timeoverandsend"/>");
			timeOut = 'Y';
			submitPaper('Y');
		} else {
			var t1 = '00';
			var t2 = '00';
			if(leftTime >= 3600) {
				t1 = parseInt(leftTime / 3600);
				if (t1 == 0) t1	= '00';
				else if (t1 < 10) t1	= '0' + t1;
			} else {
				t1 = '00';
			}

			if (leftTime >= 60) {
				if (leftTime >= 3600) t2 = parseInt(parseInt(leftTime % 3600) / 60);
				else t2 = parseInt(leftTime / 60);
				if (t2 == 0) t2 = '00';
				else if (t2 < 10) t2 = '0' + t2;
			} else t2 = '00';

			var t3	= leftTime % 60;
			if (t3 == 0) t3	= '00';
			else if (t3 < 10) t3 = '0' + t3;

			var time_str = t1 + ':' + t2 + ':' + t3 ;

			leftTime	= leftTime - 1;
			$("#leftTimeZone").html(time_str);
			$("#examForm").find("input[name$=stareTm]").val(examStareTm - leftTime);
			timer_id	= setTimeout('examTimeShow(leftTime)',1000);
		}
	}

	function process(cmd) {
		var f = document.examForm;

		$('#examForm').attr("action","/lec/exam/"+cmd);
		//$('#examForm').find('input[name=examStareVO.stareAnss]').val(f["examStareVO.stareAnss"].value);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(sendYn == 'Y'){
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				// 정상 처리
				parent.document.location.reload();
				parent.modalBoxClose();
				//self.close();
			} else {
				// 비정상 처리
				parent.document.location.reload();
				parent.modalBoxClose();
			}
		}else{
			alert('<spring:message code="lecture.message.exam.alert.temporary.save"/>');
		}
	}

	function submitPaper(gubun) {
		sendYn = gubun;
		var msg = '';
		if(sendYn == 'Y'){
			if(timeOut == 'Y'){
				submitProcess(gubun);
			} else if('N'){
				msg = '<spring:message code="lecture.message.exam.stare.confirm.send"/>';
				if(confirm(msg)) {
					submitProcess(gubun);
				}
			}
		}else{
			msg = '<spring:message code="lecture.message.exam.confirm.temporary.save"/>';
			submitProcess(gubun);
		}

	}

	function submitProcess(chkGubun) {
		var f = document.examForm;
		var answers = "";
		var noCheck = 0;
		var noCheckNo = '';

		<c:forEach items="${questionList}" var="item" varStatus="status">
		if('${item.qstnType}' == 'K' || '${item.qstnType}' == 'S'){
			var ans = $("input[name$=ans_${status.count}]:checked").val();
			if(ans == undefined) {
				answers = answers+"@#";
				noCheck++;
				noCheckNo = noCheckNo+${status.count}+', ';
			} else {
				answers = answers+"@#"+ans;
			}
		}else{
			if($("#ans_${status.count}").val() == "") {
				noCheck++;
				noCheckNo = noCheckNo+${status.count}+', ';
			}
			//2015.11.04 김현욱 수정 공백제거
			answers = answers+"@#"+$.trim($("#ans_${status.count}").val());
		}
		</c:forEach>
		//문항의 맨 끝을 ','를 제거한다.
		noCheckNo = noCheckNo.substring(0,noCheckNo.lastIndexOf(','));

		answers = answers+"@#";
		f["stareAnss"].value = answers;
		if(noCheck > 0 && chkGubun == 'Y') {
			if(timeOut == 'Y'){
				process("addPaperSubmit");
			} else if('N'){
				if(confirm('<spring:message code="lecture.message.exam.confirm.question.blank" arguments="'+noCheckNo+'"/>')){
					process("addPaperSubmit");
	/* 				alert('<spring:message code="lecture.message.exam.stare.alert.resend"/>');
					return; */
				}
			}

		} else if(chkGubun == 'N'){
			process("addPaperSubmitTemp");
		} else {
			process("addPaperSubmit");
		}

	}

	function setAnswer(){
		var f = document.examForm;
		var answers = f["stareAnss"].value;
		var ans_ar = answers.split("@#");

		<c:forEach items="${questionList}" var="item" varStatus="status">
			if('${item.qstnType}' == 'K' || '${item.qstnType}' == 'S'){
				if(ans_ar["${status.count}"] != ''){
					$("input[name$=ans_${status.count}]:input[value="+ans_ar["${status.count}"]+"]").attr("checked", true);
				}
			} else {
				$("#ans_${status.count}").val(ans_ar["${status.count}"]);
			}
		</c:forEach>
	}
</script>
</body>
