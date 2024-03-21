<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="assignmentListVO" value="${assignmentListVO}"/>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="cntChk" value="0"/>
<c:set var="startNo" value="0"/>
<c:set var="avgScore" value="0"/>

                <div class="learn_top">
                    <div class="left_title">
                        <h3><spring:message code="lecture.title.assignment.desc"/></h3>
                    </div>
                </div>
                <c:forEach var="item" items="${assignmentListVO}" varStatus="status">
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
 							<c:if test="${item.asmtTypeCd eq 'ON'}">
                               	<label class="online">온라인</label>
                            </c:if>
                            <c:if test="${item.asmtTypeCd eq 'OFF'}">
                              	<label class="offline">오프라인</label>
                            </c:if>
                            <input type="hidden" id="selectAsmtSn" value="${item.asmtSn}"/>                       
                             <h4>${item.asmtTitle }</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                            <c:choose>
									<c:when test="${item.asmtStareTypeCd eq 'S'}"> 
										<li><strong><spring:message code="lecture.title.assignment.send.ratio"/></strong><spring:message code="lecture.message.exam.send.ratio.over" arguments="${item.sendCritPrgrRatio}"/></li>	
									</c:when>
									<c:otherwise>
										<li><strong><spring:message code="lecture.title.assignment.duration"/></strong>${item.asmtStartDttm}~${item.asmtEndDttm}</li>	
									</c:otherwise>
							</c:choose>
                                <c:if test="${item.asmtTypeCd eq 'ON'}">
                                <li>
                                  	 	<strong><spring:message code="lecture.title.assignment.seltype"/></strong>
                                  	 	<meditag:codename code="${item.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" />
                                </li>
                                </c:if>
                                <li><strong><spring:message code="lecture.title.assignment.sendyn"/></strong>
                                	<c:if test="${item.sendCnt > 0}"><spring:message code="lecture.title.assignment.sendy"/></c:if>
									<c:if test="${item.sendCnt eq 0}"><spring:message code="lecture.title.assignment.sendn"/></c:if>
                                </li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="basic" onclick="asmtInfo('${item.asmtSn}' )">과제정보</button>
								<c:choose>
									<c:when test="${item.asmtTypeCd eq 'OFF' && item.score > 0}"> <!-- 오프라인 과제, 채점 완료된 경우--> 
										<button type="button" class="secondary" onclick="addAsmtSend('${item.asmtSn}')"><spring:message code="button.view.result"/></button>
									</c:when>
									<c:when test="${item.asmtTypeCd eq 'OFF' && item.score <= 0}"> <!-- 오프라인 과제, 채점 완료되지 않은 경우--> 
									</c:when>
									<c:when test="${item.asmtTypeCd eq 'ON' && (item.score > 0 || item.sendCnt >= item.asmtLimitCnt || item.connYn eq 'NN')}"> <!-- 채점이 완료 or 제한 제출 횟수를 넘음 or 제출기간 이후 일때--> 
										<button type="button" class="secondary" onclick="addAsmtSend('${item.asmtSn}')"><spring:message code="button.view.result"/></button>
									</c:when>
									<c:when test="${item.asmtTypeCd eq 'ON' && item.connYn eq 'NO'}"> <!-- 제출기간전 일때--> 
										<button type="button" class="primary" onclick="alert('과제 제출 기간이 아닙니다.')"><spring:message code="button.send.asmt"/></button>
									</c:when>
									<c:when test="${item.asmtTypeCd eq 'ON' && item.sendCritPrgrRatio > item.stdRatio}"> <!-- 온라인 과제이며 응시 가능 진도율보다 작을 때--> 
										<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.asmt.send.alert.answer.ratio" arguments="${item.sendCritPrgrRatio}"/>')"><spring:message code="button.send.asmt"/></button>
									</c:when>
									<c:otherwise><!-- 온라인 과제이며 과제 제출 가능--> 
										<button type="button" class="primary" onclick="crsSvcTypeCheck('${item.asmtSn}')"><spring:message code="button.send.asmt"/></button>
									</c:otherwise>
								</c:choose>                               
                             </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
				<c:if test="${empty assignmentListVO}">
				<div class="segment">
					<div class="course_list test_custom">
						<div class="item">
							<spring:message code="lecture.message.assignment.nodata"/>
						</div>
					</div>
				</div>
				</c:if>                
 

				<form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
				<input type="hidden" name="crsCreCd" />
				<input type="hidden" name="asmtSn" id="asmtSn"/>
				</form>

<script type="text/javascript">
	var ItemVO1 = {};
	var modalBox = null;
	var otpModalBox = null;
	
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		otpModalBox = new $M.ModalDialog({
			"modalid" : "otpModalBox"
		});
	});

	function listAssignment(){
		var sbjCd = $("#sbjCd option:selected").val();
		var url = "/lec/assignment/stdAssignmentMain?sbjCd="+sbjCd;
		$(location).attr('href',url);
	}

	function asmtInfo(asmtSn){
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/viewAssignmentPop"/>"
			+ "?asmtSn="+asmtSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(900, 550);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.desc"/>");
		modalBox.show();
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	function addAsmtSend(asmtSn){
		document.location.href =  "/lec/assignment/addSendMain?asmtSn="+asmtSn;
	}
	
	function addSend(){
		pop_motp_close();
		var asmtSn = $("#selectAsmtSn").val();
		document.location.href =  "/lec/assignment/addSendMain?asmtSn="+asmtSn;
	}
	
	
	function crsSvcTypeCheck(asmtSn){
		$.getJSON( 
			"/home/course/selectCrsSvcTypeCheck",{ "crsCreCd":"${vo.crsCreCd}"},			
			function(data) {
				if(data.crsSvcType == 'R' && data.crsOperMthd == 'ON') {
					exceptIdCheck(asmtSn);
				} else {
					document.location.href =  "/lec/assignment/addSendMain?asmtSn="+asmtSn;
				}
			}
		);
	}

	function exceptIdCheck(asmtSn){
		ItemVO1.returnMethod = 'addSend';
		
		$.getJSON( 
			"/home/user/selectExceptionIdCheck",{ },			
			function(data) {
				if(data.idCheck == 'Y') {
					document.location.href =  "/lec/assignment/addSendMain?asmtSn="+asmtSn;
				} else {
					 pop_motp(asmtSn);
				}
			}
		);
	}


	function pop_motp(asmtSn){
		var evalCd = "03";
		var url = generateUrl("/mng/etc/HrdApi/viewMotp",{ "evalCd": evalCd ,"asmtSn":asmtSn,"stdNo":"${vo.stdNo}","crsCreCd":"${vo.crsCreCd}"});
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

	
</script>
