<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

    <div class="modal_cont">
        <div class="tstyle mb30">
            <ul class="dbody">                       
                <li>
                    <div class="row">
                        <label for="facCd" class="form-label col-sm-2">시설명</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <select id="facCd" class="form-control w50">
                                    <option value="${facInfo.facCd }" selected>${facInfo.facNm }</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="resDt" class="form-label col-sm-2">날짜</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input type="date" class="form-control w50" name="resDt" id="resDt" onchange="resetEnable()">
                                <!-- <input class="datepicker hasDatepicker form-control" name="resDt" id="resDt" onchange="resetEnable()" readonly style="background-color: white;" autocomplete="off"> -->
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="a3" class="form-label col-sm-2">시간</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <select name="resStm" id="resStm" onchange="resetEnable()" class="form-control w40" >
                                    <c:forEach var="i" begin="00" end="23">
										<c:forEach var="j" begin="0" end="1">
									    	<option value="<fmt:formatNumber pattern="00" value="${i}"/><fmt:formatNumber pattern="00" value="${j*30}"/>00"/>
												${i}:<fmt:formatNumber pattern="00" value="${j*30}"/>
											</option>                                                                             
									    </c:forEach>
									</c:forEach>
                                </select>
                                <select name="resEtm" id="resEtm" onchange="resetEnable()" class="form-control w40" >
                                    <c:forEach var="i" begin="00" end="23">
									    <c:forEach var="j" begin="0" end="1">
											<option value="<fmt:formatNumber pattern="00" value="${i}"/><fmt:formatNumber pattern="00" value="${j*30}"/>00"/>
												${i}:<fmt:formatNumber pattern="00" value="${j*30}"/>
											</option>                                                                            
									    </c:forEach>
									</c:forEach>
                                </select>
                                <button id="timeChkBtn" type="button" class="btn solid dark ml5" onclick="chkResTime()">조회</button>
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="eventDesc" class="form-label col-sm-2">사용목적</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <textarea class="form-control" rows="5" id="eventDesc"></textarea>
                            </div>
                        </div>
                    </div>
                </li>   
                <li>
                    <div class="row">
                        <label for="attCnt" class="form-label col-sm-2">사용인원</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                            	<input class="form-control w50" type="number" min="1" max="${facInfo.facCap}" name="attCnt" id="attCnt" >
                               <%--  <select name="attCnt" id="attCnt" class="form-control w50">
                                    <c:forEach var="n" begin="10" end="${facInfo.facCap}" step="10">
                                    	<option value="${n}">${n}</option>
                                    </c:forEach>
                                </select> --%>
                            </div>
                        </div>
                    </div>
                </li>                        
            </ul>
            
        </div>
      
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2" onclick="parent.modalBoxClose()">취소</button>
        <button type="button" class="btn type4" onclick="facReservation()">확인</button>
    </div>


<script type="text/javascript">
	var maxAttCnt = ${facInfo.facCap};
	
	$(document).ready(function() {
		var enabled = false;
		var today = new Date();
		today.setDate(today.getDate());
		$.datetimepicker.setLocale('kr');
		$('#resDt').datetimepicker({
			defaultDate: parent.targetDt,
			timepicker:false,
			format: "Y.m.d",
			onShow: function (ct) {
                this.setOptions({
	              	minDate: today
                });
			}
		});

		$("#attCnt").val(0);
	});
	
	function chkResTime() {
		var facCd = $("#facCd").val();
		var resDt = $("#resDt").val();
		var resStm = $("#resStm").val();
		var resEtm = $("#resEtm").val();
		
		//사용기간
		var resDt = $("#resDt").val();
		if(resDt==null || resDt=="") {
			$("#resDt").focus();
			alert("대관일을 입력해 주세요.");
			return;
		}
		//사용시작시간
		var resStm = $("#resStm").val();
		if(resStm==null || resStm=="") {
			$("#resStm").focus();
			alert("사용시작시간을 입력해 주세요.");
			return;
		}
		//사용종료시간
		var resEtm = $("#resEtm").val();
		if(resEtm==null || resEtm=="") {
			$("#resEtm").focus();
			alert("사용종료시간을 입력해 주세요.");
			return;
		}
		
		// 사용시작-종료시간 비교
		var resStart = parseInt(resStm); 
		var resEnd = parseInt(resEtm);
		
		if(resEnd <= resStart) {
			$("#resEtm").focus();
			alert("종료 시간은 시작 시간 이후여야 합니다.");
			return;
		}
		
		var t = new Date();
		var y = t.getFullYear(); // 년도
		var m = t.getMonth() + 1;  // 월
		var d = t.getDate();  // 날짜
		var today = y + '-' + m + '-' + d; // 오늘날짜
		
		if(resDt < today){
			$("#resDt").focus();
			alert("시설 예약은 오늘 이후의 날짜만 가능합니다.");
			return;
		}
		
		$.post(cUrl("/home/org/fac/chkResTime"),{
			"facCd" : facCd,
			"resDt" : resDt,
			"resStm" : resStm,
			"resEtm" : resEtm
		}, function(data) {
			if(data.result > 0) {
				enabled = true;
				$("#timeChkBtn").hide();
				
			}
			alert(data.message);
		});
	}
	
	function resetEnable() {
		enabled = false;
		$("#timeChkBtn").show();
	}
	
	function facReservation() {
		//시설선택
		var facCd = $("#facCd").val();
		if(facCd==null || facCd=="") {
			$("#facCd").focus();
			alert("시설을 선택해 주세요.");
			return;
		}
		//사용기간
		var resDt = $("#resDt").val();
		if(resDt==null || resDt=="") {
			$("#resDt").focus();
			alert("대관일을 입력해 주세요.");
			return;
		}
		//사용시작시간
		var resStm = $("#resStm").val();
		if(resStm==null || resStm=="") {
			$("#resStm").focus();
			alert("사용시작시간을 입력해 주세요.");
			return;
		}
		//사용종료시간
		var resEtm = $("#resEtm").val();
		if(resEtm==null || resEtm=="") {
			$("#resEtm").focus();
			alert("사용종료시간을 입력해 주세요.");
			return;
		}
		
		//행사내용
		var eventDesc = $("#eventDesc").val();
		if(eventDesc==null || eventDesc=="") {
			$("#eventDesc").focus();
			alert("행사내용을 입력해 주세요.");
			return;
		}		
		
		var attCnt = $("#attCnt").val();
		if(attCnt==null || attCnt=="") {
			$("#attCnt").focus();
			alert("참석 인원을 입력해주세요.");
			return;
		} else {
			//숫자인지 검사
			attCnt = parseInt(attCnt);
			maxAttCnt = parseInt(maxAttCnt);
			if(attCnt==0) {
				$("#attCnt").focus();
				alert("참석 인원을 입력해주세요.");
				return;
			}
			if(maxAttCnt<attCnt) {
				$("#attCnt").focus();
				alert("최대 수용인원을 초과하였습니다. 최대 수용인원 ["+maxAttCnt+"]");
				$("#attCnt").val(maxAttCnt);
				return;
			}
		}
		
		if(!enabled) {
			alert("예약이 가능한 시간대인지 확인 해주세요.");
			return;
		}
		
		$.post(cUrl("/home/org/fac/addRes"),{
			"facCd" : facCd,
			"resDt" : resDt,
			"resStm" : resStm,
			"resEtm" : resEtm,
			"eventDesc" : eventDesc,
			"attCnt" : attCnt
		}, function(data) {
			if(data.result > 0) {
				alert(data.message);
				parent.modalBoxClose();
				document.location.reload();
			} else {
				alert(data.message);
			}
		});
	}
</script>
