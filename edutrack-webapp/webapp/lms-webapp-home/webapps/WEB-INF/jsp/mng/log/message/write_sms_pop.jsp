<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="transVO" value="${vo.logMsgTransLogVO}"/>
	<form action="/mng/log/message/addMessage" onsubmit="return false" name="messageForm" id="messageForm">
    <input type="hidden" name="msgDivCd" value="SMS" />
    <input type="hidden" name="logMsgTransLogVO.userNoList" value="${transVO.userNoList}"/>
    <input type="hidden" name="logMsgTransLogVO.stdNoList" value="${transVO.stdNoList}"/>
    <input type="hidden" name="logMsgTransLogVO.etcMbrSnList" value="${transVO.etcMbrSnList}"/>
    <input type="hidden" name="logMsgTransLogVO.entrySnList" value="${transVO.entrySnList}"/>
    <input type="hidden" name="sendNm" value="${vo.sendNm}"/>
    <table class="table table-striped table-bordered">
    	<colgroup>
    		<col width="18%"/>
    		<col width="30%"/>
    		<col width="18%"/>
    		<col width="auto"/>
    	</colgroup>
    	<tr>
    		<th class="top" scope="row"><spring:message code="log.title.msg.receiver.cnt"/></th>
    		<td class="top" >${fn:length(targetList)}</td>
    		<th class="top" scope="row"><spring:message code="log.title.msg.sender.number"/></th>
    		<td class="top">
    			<input type="text" style="width:100" dispName="<spring:message code="log.title.msg.sender.number"/>"  maxlength="14" isNull="N" lenCheck="14" name="sendAddr" id="txtSendAddr" class="form-control input-sm" value="${vo.sendAddr}" readonly="readonly"/>
    		</td>
    	</tr>
    	<tr>
    		<th scope="row" ><spring:message code="log.title.msg.send.time"/></th>
    		<td >
				<label><input type="radio" name="sendType" onclick="toggleSendType()" value="N" style="padding-left: 5px" checked="checked"><spring:message code="log.title.msg.sned.now"/></label>
				<%-- <label style="margin-left:10px;"><input type="radio" name="sendType" onclick="toggleSendType()" value="R" style="padding-left: 5px"><spring:message code="log.title.msg.send.time"/></label> --%>
			</td>
			<td colspan="2">
				<div id="sendControl">
					<div class="input-group" style="float:left;width:140px;">
						<input type="text"  maxlength="10" dispName="<spring:message code="common.title.day"/>" isNull="Y" name="rsrvDate" id="rsrvDate" class="form-control input-sm"/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('rsrvDate')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
						<meditag:datepicker name1="rsrvDate"/>
					</div>
					<div style="float:left">
						<input type="text" style="width:40px" dispName="<spring:message code="common.title.hour"/>" maxlength="2" isNull="Y" lenCheck="2"  name="rsrvHour" id="rsrvHour" onkeyup="checkHour()" class="form-control input-sm"/>
					</div>
					<div style="float:left">
						<input type="text" style="width:40px" dispName="<spring:message code="common.title.min"/>" maxlength="2" isNull="Y" lenCheck="2" name="rsrvMin" id="rsrvMin" onkeyup="checkMinute()" class="form-control input-sm"/>
					</div>
				</div>
    		</td>
    	</tr>
    	<tr>
    		<th scope="row" ><spring:message code="common.title.cnts"/></th>
    		<td valign="top" style="padding-top:5px">
				<textarea name="cts" style="width:100%;height:60px" isNull="N" id="sendMsg" class="form-control input-sm"></textarea>
    			<div class="buttonwrapper" style="padding: 6px; width: 96%" ><div class="buttonalign" style="float: right" >
					<button class="btn btn-primary btn-sm" id="btnSend"><spring:message code="button.msg.send"/></button>
					<button class="btn btn-default btn-sm" id="btnClose"><spring:message code="button.close"/></button>
				</div></div>
			</td>
    		<td colspan="2" valign="top" style="padding-top:5px">
    			<table style="width: 100%" class="table table-bordered wordbreak">
					<tr>
						<th scope='col'><spring:message code="user.title.userinfo.name"/></th>
						<th scope='col'><spring:message code="user.title.userinfo.phoneno"/></th>
						<th scope='col'><spring:message code="log.title.msg.reject.receive"/></th>
					</tr>
					<c:forEach items="${targetList}" var="item">
					<tr>
						<td>${item.recvNm}</td>
						<td>${item.recvAddr}</td>
						<td><c:if test="${item.recvYn eq 'N'}">O</c:if></td>
					</tr>
					</c:forEach>
				</table>
    		</td>
    	</tr>
	</table>
	</form>
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		// 전송 이벤트 바인딩
		$('#btnSend').bind('click keydown', function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();
				if($('#txtSendAddr').value="") {
					alert("발신 번호를 입력하세요.");
					$('#txtSendAddr').focus();
					return;
				}
				if($('#sendMsg').value="") {
					alert("전송 메시지를 입력하세요.");
					$('#sendMsg').focus();
					return;
				}
				// 예약일 경우 예약시간 정합성 체크
				if( $(':input:radio[name=sendType]:checked').val() == 'R' ) {
					if($('#rsrvDate').val() == "") {
						alert("발송예약일자를 입력하세요.");
						$('#rsrvDate').focus();
						return;
					}
					if(checkHour() == false || $('#rsrvHour').val() == ""
						|| checkMinute() == false || $('#rsrvHour').val() == "") {
						alert("전송시간을 입력하세요.");
						$('#rsrvHour').focus();
						return;
					}
					
					if(checkHour() == false || $('#rsrvHour').val() == ""
						|| checkMinute() == false || $('#rsrvHour').val() == "") {
						alert("전송시간을 입력하세요.");
						$('#rsrvHour').focus();
						return;
					}
					
				}
				$('#messageForm').find('input[name=cmd]').val('addMessage');
				
				
				//if(!validate(document.messageForm)) return;
				
				$('#messageForm').ajaxSubmit(
					function(resultDTO) {
						if(resultDTO.result >= 0) {
							alert('SMS 발송을 등록하였습니다.');
							window.close();
						} else {
							alert('SMS 발송을 등록하지 못했습니다.');
						}
					}
				);
			}
		});

		$('#btnClose').bind('click', function(event) {
			event.preventDefault();
			parent.modalBoxClose();
		});

		$("#sendMsg").trigger('keydown');
		toggleSendType();
	});

	function toggleSendType() {
		if( $(':input:radio[name=sendType]:checked').val() == 'N' ) {
			$("#sendControl").hide();
		} else {
			$("#sendControl").show();
		}
	}

	function checkHour(){
		var value = $('#rsrvHour').val();
		if((value > 23) || (value < 0)){
			alert("입력 가능한 시간(시)이 아닙니다.");
			$('#rsrvHour').val('').focus();
			return false;
		}
		return true;
	}

	function checkMinute(){
		var value = $('#rsrvMin').val();
		if((value > 59) || (value < 0)){
			alert("입력 가능한 시간(분)이 아닙니다.");
			$('#rsrvMin').val('').focus();
			return false;
		}
		return true;
	}

	var oldText = "";
	var oldCount;

	function checkLen() {
		var temp;
		var memocount = 0;
		var textLen = 90;
		objByte = document.getElementById("byte");
		memocount = jsByteLength($("#sendMsg").val());
		objByte.innerHTML = memocount + "/" + textLen + "Byte";

		if ( memocount > 90 ){
			alert("내용은 90Byte까지만 쓰실 수 있습니다.");
			$("#sendMsg").val(oldText);
			objByte.innerHTML = oldCount + "/" + textLen + "Byte";
			return;
		} else {
			oldText = $("#sendMsg").val();
			oldCount = memocount;
		}
		return memocount;
	}
</script>
