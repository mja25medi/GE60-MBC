<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="transVO" value="${vo.logMsgTransLogVO}"/>
	<form id="logMessageForm" name="logMessageForm" onsubmit="return false">
    <input type="hidden" name="msgDivCd" value="SMS" />
    <input type="hidden" name="logMsgTransLogVO.userNoList" value="${transVO.userNoList}"/>
    <input type="hidden" name="logMsgTransLogVO.stdNoList" value="${transVO.stdNoList}"/>
    <input type="hidden" name="logMsgTransLogVO.etcMbrSnList" value="${transVO.etcMbrSnList}"/>
    <input type="hidden" name="logMsgTransLogVO.entrySnList" value="${transVO.entrySnList}"/>
    <input type="hidden" name="sendNm" value="${vo.sendNm}"/>
    
    
    
    <div class="modal_cont">
        <div class="tstyle">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label for="titleInput" class="form-label col-sm-2">발송대상 인원수</label>
                        <div class="col-sm-10">${fn:length(targetList)}명</div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="txtSendAddr" class="form-label col-sm-2">발신번호</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input type="text" style="width:100" dispName="<spring:message code="log.title.msg.sender.number"/>" maxlength="15" isNull="N" lenCheck="14" name="sendAddr" value="${vo.sendAddr}" id="txtSendAddr" class="form-control input-sm" readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="reservChoice" class="form-label col-sm-2">예약발송</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <span class="custom-input">
                                    <input type="radio" name="sendType" id="sendType" onclick="toggleSendType()" value="N" checked="checked">
                                    <label for="sendType">즉시발송</label>
                                </span>
                            </div>
                            <%-- <div id="sendControl">
								<div class="input-group" style="float:left;width:140px;">
									<input type="text" maxlength="10" dispName="<spring:message code="common.title.day"/>" isNull="Y" name="rsrvDate" id="rsrvDate" class="form-control input-sm"/>
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
							</div> --%>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="sendMsg" class="form-label col-sm-2">내용</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <textarea class="form-control" rows="10" name="cts" id="sendMsg">${vo.cts}</textarea>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="form-row">
                        <div class="res_tbl_wrap">
                            <table>
                                <caption>수신자 목록</caption>
                                <thead>
                                    <tr>
                                        <th scope="col">이름</th>
                                        <th scope="col">전화번호</th>
                                        <th scope="col">수신거부</th>
                                    </tr>
                                </thead>
                                <tbody>
                             	  <c:forEach items="${targetList}" var="item">
									 <tr>
                                       <td scope="row" data-label="이름">${item.recvNm}</td>
                                       <td data-label="전화번호">${item.recvAddr}</td>
                                       <td data-label="수신거부">
                                       <c:if test="${item.recvYn eq 'N'}">O</c:if>
                                       <c:if test="${item.recvYn eq 'Y'}">-</c:if>
                                       </td>
                                    </tr>
									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </li>                              
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2" id="btnSend"><spring:message code="button.msg.send"/></button>
        <button type="button" class="btn" id="btnClose"><spring:message code="button.close"/></button>
    </div>
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		var f = document.systemSmsForm;
		if("${limitYn}" == "N"){
			alert("<spring:message code="log.message.msg.alert.sms.limited"/>");
			parent.userSmsPopBox.close();
		}

		// 메시지 길이 표시 이벤트
		$("#sendMsg").bind("keydown", function(event) {
			var memocount = 0;
			var maxLength = 80;
			memocount = jsByteLength($("#sendMsg").val());

			if ( memocount > 80 ){
				alert("<spring:message code="log.messgae.msg.alert.sms.cnts80"/>");
				event.preventDefault();	// 입력 무시
				return false;
			}
			$('#byte').html(memocount + "/" + maxLength + "Byte");
		});

		// 전송 이벤트 바인딩
		$('#btnSend').bind('click keydown', function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();

				if($('#txtSendAddr').value="") {
					alert("<spring:message code="log.message.msg.alert.sms.input.sender.number"/>");
					$('#txtSendAddr').focus();
					return;
				}

				if($('#sendMsg').value="") {
					alert("<spring:message code="log.message.msg.alert.input.cnts"/>");
					$('#sendMsg').focus();
					return;
				}

				// 예약일 경우 예약시간 정합성 체크
				if( $(':input:radio[name=sendType]:checked').val() == 'R' ) {

					if($('#rsrvDate').val() == "") {
						alert("<spring:message code="log.message.msg.alert.sms.input.send.date"/>");
						$('#rsrvDate').focus();
						return;
					}

					if(checkHour() == false || $('#rsrvHour').val() == ""
						|| checkMinute() == false || $('#rsrvHour').val() == "") {
						alert("<spring:message code="lgo.message.msg.alert.sms.input.send.hour"/>");
						$('#rsrvHour').focus();
						return;
					}
				}

				$('#logMessageForm').attr("action","/lec/message/addMessage");
				$('#logMessageForm').ajaxSubmit(
					function(resultDTO) {
						if(resultDTO.result >= 0) {
							alert('<spring:message code="log.message.msg.note.add.success"/>');
							parent.modalBoxClose();
						} else {
							alert('<spring:message code="log.message.msg.note.add.failed"/>');
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
			alert("<spring:message code="common.message.alert.timeformat.validation.hour"/>");
			$('#rsrvHour').val('').focus();
			return false;
		}
		return true;
	}

	function checkMinute(){
		var value = $('#rsrvMin').val();
		if((value > 59) || (value < 0)){
			alert("<spring:message code="common.message.alert.timeformat.validation.min"/>");
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
		var textLen = 80;
		objByte = document.getElementById("byte");
		memocount = jsByteLength($("#sendMsg").val());
		objByte.innerHTML = memocount + "/" + textLen + "Byte";

		if ( memocount > 80 ){
			alert("<spring:message code="log.messgae.msg.alert.sms.cnts80"/>");
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
