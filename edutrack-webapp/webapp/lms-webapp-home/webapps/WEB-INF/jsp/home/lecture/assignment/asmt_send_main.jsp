<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="assignmentVO" value="${vo}"/>
<c:set var="assignmentSendVO" value="${assignmentSendVO}"/>
<c:set var="assignmentSubListVO" value="${assignmentSubListVO}"/>
<c:set var="sendYn" value="N" />

                <div class="learn_top">
                    <div class="left_title">
                        <h3><spring:message code="lecture.title.assignment.desc"/></h3>
                    <button type="button" class="list" onclick="listAsmtMain()">목록</button>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_top">
                        <h4><spring:message code="lecture.title.assignment.desc"/></h4>
                    </div>
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.name"/></label></li>
                            <li>${assignmentVO.asmtTitle}</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.type"/></label></li>
                            <li>
                            	<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
									<label class="btn3 sm solid fcGreen">온라인</label>
								</c:if>
								<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
									<label class="btn3 sm solid fcViolet">오프라인</label>
								</c:if>
                            </li>
                            <c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
                            <li class="head"><label><spring:message code="lecture.title.assignment.seltype"/></label></li>
                            <li><meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></li>
                            </c:if>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.duration"/></label></li>
                            <li>${assignmentVO.asmtStartDttm} ${assignmentVO.asmtStartHour}:${assignmentVO.asmtStartMin} ~ ${assignmentVO.asmtEndDttm} ${assignmentVO.asmtEndHour}:${assignmentVO.asmtEndMin}</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.delaydate"/></label></li>
                            <li>${assignmentVO.extSendDttm} ${assignmentVO.extSendHour}:${assignmentVO.extSendMin}</li>
                            <c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
                            <li class="head"><label><spring:message code="lecture.title.assignment.send.cnt"/></label></li>
                            <li>${assignmentVO.asmtLimitCnt} <spring:message code="common.title.times.postfix"/></li>
                            </c:if>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="common.title.atachfile"/></label></li>
                            <li><c:forEach var="file" items="${assignmentVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach></li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
                            <li>${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}</li>
                        </ul>
                    </div>
                </div>
                <c:if test="${assignmentVO.asmtSelectTypeCd eq 'R'}">
	                <div class="segment">
	                    <div class="board_top">
	                        <h4><spring:message code="lecture.title.assignment.question"/></h4>
	                    </div>
	                    <c:if test="${empty resultVO4}">
		                    <div class="table_list">
		                        <ul class="list">
		                            <li><spring:message code="lecture.message.assignment.question.nodata"/></li>
		                        </ul>
		                    </div>
						</c:if>
						<c:if test="${not empty resultVO4}">
		                    <div class="table_list">
		                        <ul class="list">
		                            <li class="head"><label><spring:message code="lecture.title.assignment.question.title"/></label></li>
		                            <li>${resultVO4.asmtTitle}</li>
		                        </ul>
		                        <ul class="list">
		                            <li class="head"><label><spring:message code="common.title.atachfile"/></label></li>
		                            <li><c:forEach var="file" items="${resultVO4.attachFiles}"><p>${file.downloadTag}</p></c:forEach></li>
		                        </ul>
		                        <ul class="list">
		                            <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
		                            <li>${fn:replace(resultVO4.asmtCts,crlf,"<br/>")}</li>
		                        </ul>
		                    </div>
	                    </c:if>
	                </div>
                </c:if>
				<c:if test="${assignmentVO.asmtSelectTypeCd eq 'S'}">
					<c:if test="${assignmentSendVO.sendCnt > 0}">
		                <div class="segment">
		                    <div class="board_top">
		                        <h4><spring:message code="lecture.title.assignment.question"/></h4>
		                    </div>
			                    <div class="table_list">
			                    	<c:if test="${not empty subVO}">
			                        <ul class="list">
			                            <li class="head"><label><spring:message code="lecture.title.assignment.question.title"/></label></li>
			                            <li>${subVO.asmtTitle}</li>
			                        </ul>
			                        <ul class="list">
			                            <li class="head"><label><spring:message code="common.title.atachfile"/></label></li>
			                            <li><c:forEach var="file" items="${subVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach></li>
			                        </ul>
			                        <ul class="list">
			                            <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
			                            <li>${fn:replace(subVO.asmtCts,crlf,"<br/>")}</li>
			                        </ul>
			                        </c:if>
			                    </div>
		                </div>
					</c:if>
					<c:if test="${assignmentSendVO.sendCnt <= 0}">
		                <div class="segment">
		                    <div class="board_top">
		                        <h4><spring:message code="lecture.title.assignment.question"/></h4>
		                    </div>
			                    <table class="tstyle list">
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col class="w2 m_hidden">
                            <col class="w8 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" >선택</th>
                                <th scope="col" ><spring:message code="lecture.title.assignment.question.title"/></th>
                                <th scope="col" ><spring:message code="common.title.cnts"/></th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">
							<c:if test="${empty assignmentSubListVO}">
								<tr>
									<td colspan="3" class="rnone">
										 <font color="red"><spring:message code="lecture.message.assignment.question.nodata"/></font>
									</td>
								</tr>
						    </c:if>
							<c:forEach items="${assignmentSubListVO}" var="assignmentVO1" varStatus="status">
								<input type="hidden" name="asmtSn" value="${assignmentVO1.asmtSn}"/>
								<input type="hidden" name="asmtTitle" value="${assignmentVO1.asmtTitle}"/>
								<%-- <input type="hidden" name="asmtCts" value='${assignmentVO1.asmtCts}'/> --%>
								<tr>
									<td class="text-center">
										<span class="custom-input">
											<input type="radio" name="sel" id="sel${status.count}" value="${assignmentVO1.asmtSubSn}" onfocus='on_check()'/><label for="sel${status.count}"></label>
										</span>
									</td>
									<td class="wordbreak">${assignmentVO1.asmtTitle}</td>
									<td class="text-center">
										<a href="javascript:readSub('${assignmentVO1.asmtSn}','${assignmentVO1.asmtSubSn}')" class="btn btn-info btn-sm"><spring:message code="button.view"/></a>
									</td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
		                </div>
					</c:if>
				</c:if>	
				<%-- <c:if test="${not empty resultVO4 || not empty assignmentSubListVO || not empty subVO}"> --%>
				<c:if test="${assignmentSendVO.sendCnt > 0}">
	                <div class="segment">
	                    <div class="board_top">
	                        <h4><spring:message code="lecture.title.assignment.send.info"/></h4>
	                    </div>
						<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
		                    <div class="table_list">
		                        <ul class="list">
		                            <li class="head"><label><spring:message code="common.title.title"/></label></li>
		                            <li>${assignmentSendVO.sendTitle}</li>
		                        </ul>
		                        <ul class="list">
		                            <li class="head"><label><spring:message code="lecture.title.assignment.send.lastdate"/></label></li>
		                            <li><meditag:dateformat type="8" property="${assignmentSendVO.modDttm}" delimeter="."/></li>
		                            <li class="head"><label><spring:message code="lecture.title.assignment.send.cnt"/></label></li>
		                            <li>${assignmentSendVO.sendCnt} <spring:message code="common.title.times.postfix"/></li>
		                        </ul>
		                        <ul class="list">
		                            <li class="head"><label><spring:message code="lecture.title.assignment.send.file"/></label></li>
		                            <li><c:forEach var="file" items="${assignmentSendVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach></li>
		                        </ul>
		                        <ul class="list">
		                            <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
		                            <li>${fn:replace(assignmentSendVO.sendCts,crlf,"<br/>")}</li>
		                        </ul>
		                        <c:if test="${assignmentSendVO.rateYn eq 'Y' }">
									<c:if test="${assignmentVO.rsltYn eq 'Y' }">
										<ul class="list">
				                            <li class="head"><label><spring:message code="lecture.title.assignment.send.comment"/></label></li>
				                            <li>${fn:replace(assignmentSendVO.atchCts,crlf,"<br/>")}</li>
				                        </ul>
										<ul class="list">
				                            <li class="head"><label><spring:message code="lecture.title.assignment.score"/></label></li>
				                            <li><fmt:formatNumber value="${assignmentSendVO.score}" pattern="#.#" minFractionDigits="1"/> <spring:message code="common.title.score"/></li>
				                        </ul>
									</c:if>
								</c:if>
		                    </div>
		                    
	                    </c:if>
						<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
		                    <div class="table_list">
		                        <ul class="list">
		                            <li class="head"><label>채점일자</label></li>
		                            <li><meditag:dateformat type="8" property="${assignmentSendVO.modDttm}" delimeter="."/></li>
		                        </ul>
								<c:if test="${assignmentSendVO.rateYn eq 'Y' }">
									<c:if test="${assignmentVO.rsltYn eq 'Y' }">
										<ul class="list">
				                            <li class="head"><label><spring:message code="lecture.title.assignment.score"/></label></li>
				                            <li><fmt:formatNumber value="${assignmentSendVO.score}" pattern="#.#" minFractionDigits="1"/> <spring:message code="common.title.score"/></li>
				                        </ul>
									</c:if>
								</c:if>		                        
		                    </div>						
						</c:if>
	                </div>
				
				
				</c:if>
				<%-- </c:if> --%>
				<%-- 제출기한이 아니거나 제출횟수를 초과한다면 제출폼은 dispaly:none --%>
				<c:if test="${assignmentVO.sendCanYn eq 'Y' && isSend}">
					<c:set var="sendYn" value="Y" />
					<c:if test="${not empty resultVO4 || not empty assignmentSubListVO || not empty subVO}">
						<c:if test="${assignmentSendVO.score <= 0 }">
                
			                <div class="segment">
			                    <div class="board_top">
			                        <h4><spring:message code="lecture.title.assignment.send.info"/></h4>
			                    </div>
								<form id="assignmentForm" name="assignmentForm" onsubmit="return false" method="post">
								<input type="hidden" name="curPage" value="${assignmentSendVO.curPage}" />
								<input type="hidden" name="crsCreCd" value="${assignmentSendVO.crsCreCd}" />
								<input type="hidden" name="asmtSn" value="${assignmentSendVO.asmtSn}" />
								<input type="hidden" name="sendCnt" value="${assignmentSendVO.sendCnt}" />
								<input type="hidden" name="asmtSubSn" value="${assignmentSendVO.asmtSubSn}" />
								<input type="hidden" name="attachFileSns" value="${assignmentSendVO.attachFileSns}" />
								<input type="hidden" name="asmtSn" />
								<input type="hidden" name="asmtTitle" />
			                    <div class="tstyle">
			                        <ul class="dbody">
			                            <li>
			                                <div class="row">
			                                    <label for="sendTitle" class="form-label col-sm-2"><spring:message code="common.title.title"/></label>
			                                    <div class="col-sm-10">
			                                        <div class="form-row">
			                                        	<input type="text" dispName="<spring:message code="common.title.title"/>" maxlength="100" isNull="N" lenCheck="100"  name="sendTitle" id="sendTitle" onfocus="this.select()" class="form-control"/>
			                                        </div>             
			                                    </div>
			                                </div>
			                            </li>
			                            <li>
			                                <div class="row">
			                                    <span class="form-label col-sm-2"><spring:message code="lecture.title.assignment.send.file"/></span>
			                                    <div class="col-sm-10 attach_area">
			                                        <div class="upload">
														<div class="upload_inbox">
															<a href="javascript:uploderclick('atchuploader');" class="btn3 solid fcBlue sm"><spring:message code="button.select.file"/></a>
															<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" />
															<div id="atchprogress" class="progress">
											    				<div class="progress-bar progress-bar-success"></div>
															</div>
														</div>
														<div id="atchfiles" class="attach_list multi_inbox"></div>
													</div>                                           
			                                    </div> 
			                                </div>
			                            </li>
			                            <li>
			                                <div class="row">
			                                    <label for="tx_canvas_text" class="form-label col-sm-2"><spring:message code="common.title.cnts"/></label>
			                                    <div class="col-sm-10">
			                                        <div class="form-row">
			                                        	<textarea name="sendCts" id="tx_canvas_text" rows="10"  dispName="<spring:message code="common.title.cnts"/>" isNull="N" class="form-control" onpaste="return false;" oncopy="return false;"></textarea>
												   <%-- <nested:textarea name="sendCts" id="tx_canvas_text"  dispName="<spring:message code="common.title.cnts"/>" isNull="N" class="form-control input-sm" style="height:120px"/> --%>
			                                        </div>
			                                    </div>
			                                </div>
			                            </li>                            
			                        </ul>
			                    </div>
			                    </form>
			                    <div class="btns mt30">
									<%-- 온라인이며 제출기한이 아니거나 제출횟수를 초과한다면 제출하기 버튼은 dispaly:none --%>
									<c:if test="${assignmentVO.asmtTypeCd eq 'ON' && assignmentVO.sendCanYn eq 'Y' && isSend}">
										<c:if test="${assignmentVO.asmtSelectTypeCd eq 'R'}">
											<c:if test="${assignmentSendVO.score <= 0 }">
											<button type="button" class="btn gray2" onclick="updateStudentSend(${assignmentSendVO.sendCnt},${assignmentVO.asmtLimitCnt})"><spring:message code="button.send.asmt"/></button>
											</c:if>
										</c:if>
										<c:if test="${not empty resultVO4 || not empty assignmentSubListVO || not empty subVO}">
											<c:if test="${assignmentSendVO.score <= 0 }">
												<c:if test="${assignmentVO.asmtSelectTypeCd eq 'S'}">
													<c:if test="${assignmentSendVO.sendCnt == 0}">
													<button type="button" class="btn gray2" onclick="addStudentSend()"><spring:message code="button.send.asmt"/></button>
													</c:if>
													<c:if test="${assignmentSendVO.sendCnt > 0}">
													<button type="button" class="btn gray2" onclick="updateStudentSend(${assignmentSendVO.sendCnt},${assignmentVO.asmtLimitCnt})"><spring:message code="button.send.asmt"/></button>
													</c:if>
												</c:if>
											</c:if>
										</c:if>
									</c:if>
			                        <button type="button" class="btn type5" onclick="goList()"><spring:message code="button.cancel"/></button>
			                    </div>
			                </div>
						</c:if>
					</c:if>
				</c:if>




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
		atchFiles = new $M.JqueryFileUpload({
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
					});
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
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
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
			if(atchFiles.getFileSnAll() == ""){
				alert("<spring:message code="lecture.message.assignment.send.alert.nofile"/>");
				return;
			}
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
			$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
			$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
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
		modalBox.resize(700, 400);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.desc"/>");
		modalBox.show();
	}
	
	function listAsmtMain(){
		document.location.href =  "/lec/assignment/stdAssignmentMain";
	}
</script>
