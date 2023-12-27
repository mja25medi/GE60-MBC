<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="assignmentVO" value="${vo}"/>
<c:set var="assignmentSendVO" value="${assignmentSendVO}"/>
<c:set var="assignmentSubListVO" value="${assignmentSubListVO}"/>
<c:set var="sendYn" value="N" />
				<div class="grid-row">
						<div class="m-box size-6">
							 <div class="sub_title"> <spring:message code="lecture.title.assignment.desc"/></div> 
									<table class="table mt20 mb20">
										<caption class="sr-only"><spring:message code="lecture.title.assignment.desc"/></caption>
										<colgroup>
											<col width="20%" />
											<col width="30%" />
											<col width="20%" />
											<col width="30%" />
										</colgroup>
										<tbody>
											<tr>
												<th scope="row"><spring:message code="lecture.title.assignment.name"/></th>
												<td colspan="3" class="wordbreak">${assignmentVO.asmtTitle}</td>
											</tr>
											<tr>
												<th scope="row"><spring:message code="lecture.title.assignment.type"/></th>
												<td>
												 	<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
														<span class="label outline fcBlue mt4 fl">온라인</span>
													</c:if>
												 	<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
														<span class="label outline fcBlue mt4 fl">오프라인</span>
													</c:if>
												</td>
												<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
													<th scope="row"><spring:message code="lecture.title.assignment.seltype"/></th>
													<td><meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></td>
												</c:if>
											</tr>
											<tr>
												<th scope="row"><spring:message code="lecture.title.assignment.duration"/></th>
												<td colspan="3">${assignmentVO.asmtStartDttm} ${assignmentVO.asmtStartHour}:${assignmentVO.asmtStartMin} ~ ${assignmentVO.asmtEndDttm} ${assignmentVO.asmtEndHour}:${assignmentVO.asmtEndMin}</td>
											</tr>
											<tr>
												<th scope="row"><spring:message code="lecture.title.assignment.delaydate"/></th>
												<td>${assignmentVO.extSendDttm} ${assignmentVO.extSendHour}:${assignmentVO.extSendMin}</td>
												<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
													<th scope="row"><spring:message code="lecture.title.assignment.send.cnt"/></th>
													<td>${assignmentVO.asmtLimitCnt} <spring:message code="common.title.times.postfix"/></td>
												</c:if>
											</tr>
											<tr>
												<th scope="row"><spring:message code="common.title.atachfile"/></th>
												<td colspan="3" class="wordbreak">
													<c:forEach var="file" items="${assignmentVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
												</td>
											</tr>
											<tr>
												<th scope="row"><spring:message code="common.title.cnts"/></th>
												<td colspan="3" class="wordbreak">${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}</td>
											</tr>
										</tbody>
									</table>
								</div>

							<c:if test="${assignmentVO.asmtSelectTypeCd eq 'R'}">
								<div class="m-box size-6">
							 		<div class="sub_title"> <spring:message code="lecture.title.assignment.question"/></div> 
									<table class="table mt20 mb20">
										<caption class="sr-only"><spring:message code="lecture.title.assignment.question"/></caption>
										<colgroup>
											<col width="20%" />
											<col width="80%" />
										</colgroup>
										<tbody>
										<c:if test="${empty resultVO4}">
							            	<tr>
												<td colspan="2" class="rnone" align="center">
													 <font color="red"><spring:message code="lecture.message.assignment.question.nodata"/></font>
												</td>
											</tr>
						            	</c:if>
						            	<c:if test="${not empty resultVO4}">
											<tr>
												<th scope="row"><spring:message code="lecture.title.assignment.question.title"/></th>
												<td colspan="3" class="wordbreak">${resultVO4.asmtTitle}</td>
											</tr>
											<tr>
												<th scope="row"><spring:message code="common.title.atachfile"/></th>
												<td colspan="3" class="wordbreak"><c:forEach var="file" items="${resultVO4.attachFiles}"><p>${file.downloadTag}</p></c:forEach> </td>
											</tr>
											<tr>
												<th scope="row"><spring:message code="common.title.cnts"/></th>
												<td colspan="3" class="wordbreak">${fn:replace(resultVO4.asmtCts,crlf,"<br/>")}</td>
											</tr>
										</c:if>
										</tbody>
									</table>
								</div>
							</c:if>

							<c:if test="${assignmentVO.asmtSelectTypeCd eq 'S'}">
								<c:if test="${assignmentSendVO.sendCnt > 0}">
								<div class="m-box size-6">
							 		<div class="sub_title"> <spring:message code="lecture.title.assignment.question"/></div> 
										<table class="table mt20 mb20">
											<caption class="sr-only"><spring:message code="lecture.title.assignment.question"/></caption>
											<colgroup>
												<col width="20%" />
												<col width="80%" />
											</colgroup>
											<tbody>
						            			<c:if test="${not empty subVO}">
													<tr>
														<th scope="row"><spring:message code="lecture.title.assignment.question.title"/></th>
														<td colspan="3" class="wordbreak">${subVO.asmtTitle}</td>
													</tr>
													<tr>
														<th scope="row"><spring:message code="common.title.atachfile"/></th>
														<td colspan="3" class="wordbreak"><c:forEach var="file" items="${subVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach> </td>
													</tr>
													<tr>
														<th scope="row"><spring:message code="common.title.cnts"/></th>
														<td colspan="3" class="wordbreak">${fn:replace(subVO.asmtCts,crlf,"<br/>")}</td>
													</tr>
												</c:if>
											</tbody>
										</table>
									</div>
								</c:if>
							</c:if>
								<c:if test="${assignmentSendVO.sendCnt > 0}">
								<div class="m-box size-6">
							 		<div class="sub_title">  <spring:message code="lecture.title.assignment.send.info"/></div> 
										<table class="table mt20 mb20">
											<caption class="sr-only"><spring:message code="lecture.title.assignment.send.info"/></caption>
											<colgroup>
												<col width="20%" />
												<col width="30%" />
												<col width="20%" />
												<col width="30%" />
											</colgroup>
											<tbody>
												<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
													<tr>
														<th scope="row"><spring:message code="common.title.title"/></th>
														<td colspan="3" class="wordbreak">${assignmentSendVO.sendTitle}</td>
													</tr>
													<tr>
														<th scope="row"><spring:message code="lecture.title.assignment.send.lastdate"/></th>
														<td><meditag:dateformat type="8" property="${assignmentSendVO.modDttm}" delimeter="."/></td>
														<th scope="row"><spring:message code="lecture.title.assignment.send.cnt"/></th>
														<td>${assignmentSendVO.sendCnt} <spring:message code="common.title.times.postfix"/></td>
													</tr>
													<tr>
														<th scope="row"><spring:message code="lecture.title.assignment.send.file"/></th>
														<td colspan="3" class="wordbreak"><c:forEach var="file" items="${assignmentSendVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach> </td>
													</tr>
													<tr>
														<th scope="row"><spring:message code="common.title.cnts"/></th>
														<td colspan="3" class="wordbreak">${fn:replace(assignmentSendVO.sendCts,crlf,"<br/>")}</td>
													</tr>
													<c:if test="${assignmentSendVO.rateYn eq 'Y' }">
														<c:if test="${assignmentVO.rsltYn eq 'Y' }">
															<tr>
																<th scope="row"><spring:message code="lecture.title.assignment.send.comment"/></th>
																<td colspan="3" class="wordbreak">${fn:replace(assignmentSendVO.atchCts,crlf,"<br/>")}</td>
															</tr>
															<tr>
																<th scope="row"><spring:message code="lecture.title.assignment.score"/></th>
																<td colspan="3" class="wordbreak"><fmt:formatNumber value="${assignmentSendVO.score}" pattern="#.#" minFractionDigits="1"/> <spring:message code="common.title.score"/></td>
															</tr>
														</c:if>
													</c:if>
												</c:if>
												<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
													<tr>
														<th scope="row">채점일자</th>
														<td><meditag:dateformat type="8" property="${assignmentSendVO.modDttm}" delimeter="."/></td>
													</tr>
													<c:if test="${assignmentSendVO.rateYn eq 'Y' }">
													<c:if test="${assignmentVO.rsltYn eq 'Y' }">
														<tr>
															<th scope="row"><spring:message code="lecture.title.assignment.score"/></th>
															<td colspan="3" class="wordbreak"><fmt:formatNumber value="${assignmentSendVO.score}" pattern="#.#" minFractionDigits="1"/> <spring:message code="common.title.score"/></td>
														</tr>
													</c:if>
													</c:if>
												</c:if>
											</tbody>
										</table>
									</div>
								</c:if>
				</div>
				<div class="txt-right mt20 mb40">
					<a href="javascript:parent.modalBoxClose()" class="btn3">닫기</a>
				</div>

	<script src="/tpl/002/js/jquery/jquery.form.js"></script>
	<script src="${CONTEXT_ROOT }/libs/admin-lte/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var modalBox = null;
	var atchFiles; // 첨부파일 목록
	var sendCanYn = '${assignmentVO.sendCanYn}';//제출 영장일
	 var doubleSubmitFlag = false;

	//라이오 체크
	var radio = document.getElementsByName("sel").length;
	var chkname = '';
	function on_check(){
		for(var i = 0; i < radio; i++) {
		   if(document.getElementsByName("sel")[i].checked) {
		   break;
	       }
	    }
	}
	//과제 문제 정보
	var asmtSn = "";
	var asmtTitle = "";
	var asmtCts = "";

	$(document).ready(function() {
		$(".sub_title_2.ohddien").text("과제제출");
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		 $("#tx_canvas_text").on("paste", function() {
	          return false;
	      });
		 
		 $("#tx_canvas_text").on("keyup", function(){
			 $("#tx_canvas_text").css("height", "auto");
			 $("#tx_canvas_text").css("height", $("#tx_canvas_text").prop('scrollHeight')+"px");
		 });

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		/* atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'ASMT_SEND',
												'organization' : "${USER_ORGCD}",
												'type'		: 'file' }
						}
					}); */
		<c:if test="${sendYn eq 'N'}">
		$("input[name=sel]").attr("disabled", true);
		</c:if>
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function uploderclick(str) {
		$("#"+str).click();
	}

	function addStudentSend() {
		//$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		var selSize = $('input[name=sel]:checked').size();
		if(sendCanYn == 'N'){
			alert("<spring:message code="lecture.message.assignment.send.alert.senddate"/>");
		}else{
			for(var i = 0; i < radio; i++) {
				if(selSize == 0){
					alert('<spring:message code="lecture.messgae.assignment.send.alert.question.select"/>');
					return;
				}
			   if(document.getElementsByName("sel")[i].checked) {
				  chkname = document.getElementsByName("sel")[i].value;
				  asmtSn = document.getElementsByName("asmtSn")[i].value;
				  asmtTitle = document.getElementsByName("asmtTitle")[i].value;
				  //asmtCts = document.getElementsByName("asmtCts")[i].value;
			  	  break;
		       }
		    }
			/* if(atchFiles.getFileSnAll() == ""){
				alert("<spring:message code="lecture.message.assignment.send.alert.nofile"/>");
				return;
			} */
			//2015.11.05 김현욱 수정함. hidden 데이터 변경시 스크립트 오류 발생하여 수정함.
			if(!validate(document.assignmentForm)) return;
			
			$('#assignmentForm')
			//.attr("action","/lec/assignment/editSend")
			.find('input[name=asmtSubSn]').val(chkname).end()
			.find('input[name=asmtSn]').val(asmtSn).end()
			.find('input[name=asmtTitle]').val(asmtTitle).end()
			process("editSend");
			//$('#assignmentForm').ajaxSubmit(processCallback);
		}
	}

	function updateStudentSend(sendCnt,asmtLimitCnt) {
		if(sendCanYn == 'N'){
			alert("<spring:message code="lecture.message.assignment.send.alert.senddate"/>");
		}else if(sendCnt >= asmtLimitCnt){
                 alert("<spring:message code="lecture.message.assignment.send.alert.sendcnt"/>");
		}else{
			//$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
			//$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
			process("editSend");
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.assignmentForm)) return;
		if(doubleSubmitFlag){ // 더블써밋 플래그가 true가 되면 '처리중' 실행
		     alert('처리중입니다.');
		     return;
	  	 } else {
	  		doubleSubmitFlag = true;
	  		$('#assignmentForm').attr("action","/lec/assignment/"+cmd);
			$('#assignmentForm').ajaxSubmit(processCallback);
	  	 }
		
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.href = cUrl("/lec/assignment/stdAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
		} else {
			// 비정상 처리
		}
		doubleSubmitFlag = false;
	}

	function goList() {
		document.location.href = cUrl("/lec/assignment/stdAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}

	/**
	 * 과제 문제 정보 보기
	 */
	function readSub(asmtSn,asmtSubSn) {
		var addContent  = "<iframe id='courseInfoFrame' name='courseInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/readSubPop"/>"
			+ "?asmtSn="+asmtSn+"&amp;asmtSubSn="+asmtSubSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(700, 550);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.desc"/>");
		modalBox.show();
	}
</script>
