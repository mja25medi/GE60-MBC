<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/board.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/contents.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_layout.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_content.css">

    
    <div class="modal_cont">
        <ul class="class_tab">
            <li class="active" id="tab_1"><a href="javascript:clickTab(1)">외출하기</a></li>
            <li id="tab_2"><a href="javascript:clickTab(2)">조퇴하기</a></li>
        </ul>
        <div class="tstyle" id="outDiv">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label for="outingTime" class="form-label col-sm-2"><span>외출시간</span></label>
                        <div class="col-sm-10">
                            <div class="form-inline">
                                <select class="form-select" id="outTime">
                                    <option value="0">9시</option>
                                    <option value="1">10시</option>
                                    <option value="2">11시</option>
                                    <option value="3">13시</option>
                                    <option value="4">14시</option>
                                    <option value="5">15시</option>
                                    <option value="6">16시</option>
                                    <option value="7">17시</option>
                                </select><span class="ruffle_sign">~</span>
                                <select class="form-select" id="inTime">
                                    <option value="0">9시</option>
                                    <option value="1">10시</option>
                                    <option value="2">11시</option>
                                    <option value="3">13시</option>
                                    <option value="4">14시</option>
                                    <option value="5">15시</option>
                                    <option value="6">16시</option>
                                    <option value="7">17시</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="tstyle" id="leftDiv" style="display: none;">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label for="outingTime" class="form-label col-sm-2"><span>조퇴시간</span></label>
                        <div class="col-sm-10">
                            <div class="form-inline">
                                <select class="form-select" id="leftTime" name="leftTime">
                                    <option value="9">9시</option>
                                    <option value="10">10시</option>
                                    <option value="11">11시</option>
                                    <option value="13">13시</option>
                                    <option value="14">14시</option>
                                    <option value="15">15시</option>
                                    <option value="16">16시</option>
                                    <option value="17">17시</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button id="saveOuting" type="button" class="btn type2" onclick="saveOuting()">등록</button>
        <button id="saveLeft" type="button" class="btn type2" onclick="saveLeft()" style="display: none">등록</button>
        <button type="button" class="btn" onclick="parent.modalBoxClose()">닫기</button>
    </div>
<script>
function clickTab(num) {
	$(".active").removeClass("active");
	$("#tab_" + num).addClass("active");
	if(num == 1) {
		$("#outDiv").show();
		$("#saveOuting").show();
		$("#saveLeft").hide();
		$("#leftDiv").hide();
	} else if (num == 2){
		$("#saveOuting").hide();
		$("#outDiv").hide();
		$("#saveLeft").show();
		$("#leftDiv").show();
	}
}

//외출
function saveOuting(){
	var outTime = $("#outTime").val();
	var inTime = $("#inTime").val();
	if(outTime > inTime) {
		alert("외출시간과 복귀시간을 확인하십시오")
		return false;
	}
	$.getJSON( 
		cUrl("/lec/attend/outClass"), 
		{ "outTime" : outTime, "inTime" : inTime},			// params
		function(data) {
  			if(data.result >= 0) {
  				alert(data.message);
			} else {
				alert(data.message);
			}
		}
	);
}

//조퇴
function saveLeft(){
	var leftTime = $("#leftTime").val();
	$.getJSON( 
		cUrl("/lec/attend/leftClass"), 
		{ "leftTime" : leftTime },			// params
		function(data) {
  			if(data.result >= 0) {
  				alert(data.message);
			} else {
				alert(data.message);
			}
		}
	);
}


</script>
