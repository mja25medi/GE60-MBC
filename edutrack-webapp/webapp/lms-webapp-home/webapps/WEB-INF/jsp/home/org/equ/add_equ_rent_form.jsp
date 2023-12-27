<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
	
<form id="rentForm" action="/home/org/equ/addRent" method="post" onsubmit="return false">
	<div class="modal_cont">
        <div class="tstyle mb30">
            <ul class="dbody">                       
                <li>
                    <div class="row">
                        <label for="equCd" class="form-label col-sm-2">장비명</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                               	<%-- <input type="hidden" id="equCd" name="equCd" value="${equInfo.equCd }" /> --%>
                                <select id="equCd" name="equCd" class="form-control w50">
                                    <option value="${equInfo.equCd }" selected>${equInfo.equNm }</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="resDt" class="form-label col-sm-2">대여기간</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input type="date" class="form-control w40" name="rentStartDttm" id="rentStartDttm" onchange="resetEnable()">
                                <input type="date" class="form-control w40" name="rentEndDttm" id="rentEndDttm" onchange="resetEnable()">
                                <button id="dttmChkBtn" type="button" class="btn solid dark ml5" onclick="chkRentDttm()">조회</button>
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="rentCnt" class="form-label col-sm-2">장비대여수</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                            	<input class="form-control w50" type="number" min="1" max="${equInfo.itemCnt}" id="rentCnt" name="rentCnt" >
                            </div>
                        </div>
                    </div>
                </li>                        
                <li>
                    <div class="row">
                        <label for="rentDesc" class="form-label col-sm-2">사용목적</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <textarea class="form-control" rows="5" id="rentDesc" name="rentDesc"></textarea>
                            </div>
                        </div>
                    </div>
                </li>   
            </ul>
            
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2" onclick="parent.modalBoxClose()">취소</button>
        <button type="button" class="btn type4" onclick="addRent()">확인</button>
    </div>
</form>

<script type="text/javascript">
	var maxCnt = ${equInfo.itemCnt};
	
	$(document).ready(function() {
		var enabled = false;
		
	});
	
	function chkRentDttm() {
		var equCd = $("#equCd").val();
		var rentStartDttm = $("#rentStartDttm").val();
		var rentEndDttm = $("#rentEndDttm").val();

		//대여시작날짜
		if(rentStartDttm==null || rentStartDttm=="") {
			$("#rentStartDttm").focus();
			alert("대여 시작일을 입력해 주세요.");
			return;
		}
		// 대여종료날짜
		var rentEndDttm = $("#rentEndDttm").val();
		if(rentEndDttm==null || rentEndDttm=="") {
			$("#rentEndDttm").focus();
			alert("대여 종료일을 입력해 주세요.");
			return;
		}
		
		var t = new Date();
		var y = t.getFullYear(); // 년도
		var m = t.getMonth() + 1;  // 월
		var d = t.getDate();  // 날짜
		var today = y + '-' + m + '-' + d; // 오늘날짜
		
		if(rentStartDttm < today){
			$("#rentStartDttm").focus();
			alert("장비 대여는 오늘 이후의 날짜만 가능합니다.");
			$("#rentStartDttm").val(today);
			return;
		}
		
		if(new Date(rentStartDttm) > new Date(rentEndDttm)) {
			alert("대여 종료 날짜가 시작 날짜보다 빠릅니다.");
			$("#rentEndDttm").val(rentStartDttm);
			return;
		}
		
		$.post(cUrl("/home/org/equ/chkEnableItemCnt"),{
			"equCd" : equCd,
			"rentStartDttm" : rentStartDttm,
			"rentEndDttm" : rentEndDttm
		}, function(data) {
			if(data.result > 0) {
				var enableCnt = data.returnVO;
				$("#rentCnt").empty();
				for(var i = 1; i <= enableCnt; i++) {
					$("#rentCnt").append(
						"<option value='" + i + "'>" + i + "</option>"
					);
				}
				alert(data.message);
				enabled = true;
				$("#dttmChkBtn").hide();
			} else {
				alert(data.message);
			}
		});
	}
	
	function resetEnable() {
		enabled = false;
		$("#dttmChkBtn").show();
	}
	
	function addRent() {
		if(!enabled) {
			alert("대여 가능 조회 먼저 해주세요.");
			return;
		}
		
		var rentCnt = $("#rentCnt").val();
		if(rentCnt==null || rentCnt=="") {
			$("#rentCnt").focus();
			alert("장비 대여수를 입력해주세요.");
			return;
		} else {
			//숫자인지 검사
			rentCnt = parseInt(rentCnt);
			maxCnt = parseInt(maxCnt);
			
			if(rentCnt==0) {
				$("#rentCnt").focus();
				alert("장비 대여수를 입력해주세요.");
				return;
			}
			if(maxCnt<rentCnt) {
				$("#rentCnt").focus();
				alert("최대 대여 가능수를 초과하였습니다. 최대 대여 가능수 ["+maxCnt+"]");
				$("#rentCnt").val(maxCnt);
				return;
			}
		}
		
		$("#rentForm").ajaxSubmit(
			function(data) {
				if(data.result > 0) {
					alert(data.message);
					parent.modalBoxClose();
					location.reload();
					parent.location.reload();
				} else {
					alert(data.message);
				}
			}
		);
			
		
	}
</script>
