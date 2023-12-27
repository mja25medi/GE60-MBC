<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="assignmentSendVO" value="${vo.assignmentSendVO}" />
<c:set var="pageInfo" value="${pageInfo}"/>
				<div class="row">
					<div class="col-md-12">
						<div id="viewAsmt">
							<table class="table table-bordered wordbreak">
								<caption class="sr-only"><spring:message code="lecture.title.assignment.manage"/></caption>
								<colgroup>
									<col style="width:18%"/>
									<col style="width:32%"/>
									<col style="width:18%"/>
									<col style="width:32%"/>
								</colgroup>
								<tr>
									<th scope="row" ><spring:message code="lecture.title.assignment.name"/></th>
									<td colspan="3" class="wordbreak">
										${assignmentVO.asmtTitle}
									</td>
								</tr>
								<tr>
								<c:choose>
									<c:when test="${assignmentVO.asmtTypeCd eq 'ON'}">
									<th scope="row" ><spring:message code="lecture.title.assignment.type"/></th>
									<td >
										<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}"><img src="${img_base}/basic/icon/ico_online.gif" alt="Online" /></c:if>
										<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}"><img src="${img_base}/basic/icon/ico_offline.gif" alt="offline" /></c:if>
									</td>
									<th scope="row" ><spring:message code="lecture.title.assignment.seltype"/></th>
									<td >
										<meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" />
									</td>
									</c:when>
									<c:otherwise>
									<th scope="row" ><spring:message code="lecture.title.assignment.type"/></th>
									<td colspan="3">
										<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}"><img src="${img_base}/basic/icon/ico_online.gif" alt="Online" /></c:if>
										<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}"><img src="${img_base}/basic/icon/ico_offline.gif" alt="offline" /></c:if>
									</td>
									</c:otherwise>
								</c:choose>
								</tr>
								<tr>
									<th scope="row" ><spring:message code="lecture.title.assignment.duration"/></th>
									<td colspan="3" class="wordbreak">
										${assignmentVO.asmtStartDttm} ${assignmentVO.asmtStartHour}:${assignmentVO.asmtStartMin} ~ ${assignmentVO.asmtEndDttm} ${assignmentVO.asmtEndHour}:${assignmentVO.asmtEndMin}
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.assignment.delaydate"/></th>
									<td>${assignmentVO.extSendDttm} ${assignmentVO.extSendHour}:${assignmentVO.extSendMin}</td>
									<th scope="row" ><spring:message code="lecture.title.assignment.send.cnt"/></th>
									<td>
										${assignmentVO.asmtLimitCnt} <spring:message code="common.title.times.postfix"/>
									</td>
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
								<tr>
									<th scope="row" ><spring:message code="lecture.title.assignment.regyn"/></th>
									<td colspan="3" class="wordbreak">
										<c:set var="regYn" value="${assignmentVO.regYn}"/>
										<c:if test="${empty assignmentVO.regYn}"><c:set var="regYn" value="N"/></c:if>
										<meditag:codename code="${regYn}" category="REG_YN" />
									</td>
								</tr>
							</table>
							<div class="text-right">
				               	<a href="javascript:viewEditForm()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
				               	<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/></a>
				               </div>
						</div>

						<div id="editAsmt" style="display:none;">
							<form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
							<!-- <input type="hidden" name="curPage"/> -->
							<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
							<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd}"/>
							<input type="hidden" name="asmtSn" id="asmtSn" value="${vo.asmtSn}"/>
							<input type="hidden" name="sendCnt" value="${vo.sendCnt}" />
							<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />

							<input type="hidden" name="assignmentSendVO.crsCreCd" value="${assignmentSendVO.crsCreCd}" />
							<input type="hidden" name="assignmentSendVO.asmtSn" value="${assignmentSendVO.asmtSn}" />
							<input type="hidden" name="assignmentSendVO.score" value="${assignmentSendVO.score}" />
							<input type="hidden" name="assignmentSendVO.stdNo" value="${assignmentSendVO.stdNo}" />
							<input type="hidden" name="assignmentSendVO.sendCnt" value="${assignmentSendVO.sendCnt}" />
							<input type="hidden" name="assignmentVO.userNm" value="${assignmentVO.userNm}" />
							<input type="hidden" name="assignmentVO.declsNo" value="${assignmentVO.declsNo}" />
							<input type="hidden" name="asmtSvcCd" value="${vo.asmtSvcCd}" id="asmtSvcCd"/>
							<%-- <input type="hidden" name="assignmentSendVO.userNoObj" value="${assignmentSendVO.userNoObj}" id="userNoObj"/> --%>
							<input type="hidden" name="userNoObj" />
							<input type="hidden" name="strStdNo" />
							<input type="hidden" name="strScore" />
							<!-- <input type="hidden" name="listScale" /> -->
							<input type="hidden" name="listScale" value="${listScale}"/>
							<input type="hidden" name="tab" />

							<table class="table table-bordered wordbreak">
								<caption class="sr-only"><spring:message code="lecture.title.assignment.manage"/></caption>
								<colgroup>
									<col style="width:17%"/>
									<col style="width:35%"/>
									<col style="width:15%"/>
									<col style="width:33%"/>
								</colgroup>
								<tr>
								<c:choose>
									<c:when test="${assignmentVO.asmtTypeCd eq 'ON'}">
									<th scope="row" ><spring:message code="lecture.title.assignment.type"/></th>
									<td>
										<input type="hidden" name="asmtTypeCd" value="${vo.asmtTypeCd}" />
										<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}"><img src="${img_base}/basic/icon/ico_online.gif" alt="Online" /></c:if>
										<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}"><img src="${img_base}/basic/icon/ico_offline.gif" alt="Offline" /></c:if>
									</td>
									<th scope="row" ><spring:message code="lecture.title.assignment.seltype"/></th>
									<td>
										<input type="hidden" name="asmtSelectTypeCd" value="${vo.asmtSelectTypeCd}"  />
										<meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" />
									</td>
									</c:when>
									<c:otherwise>
									<th scope="row" ><spring:message code="lecture.title.assignment.type"/></th>
									<td colspan="3">
										<input type="hidden" name="asmtTypeCd" value="${vo.asmtTypeCd}"  />
										<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}"><img src="${img_base}/basic/icon/ico_online.gif" alt="Online" /></c:if>
										<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}"><img src="${img_base}/basic/icon/ico_offline.gif" alt="Offline" /></c:if>
									</td>
									</c:otherwise>
								</c:choose>
								</tr>
								<tr>
									<th scope="row" ><spring:message code="lecture.title.assignment.name"/></th>
									<td colspan="3">
										<input type="text" dispName="<spring:message code="lecture.title.assignment.name"/>" maxlength="100" isNull="N" lenCheck="100" name="assignmentVO.asmtTitle" onfocus="this.select()" id="asmtTitle" class="form-control input-sm"/>
									</td>
								</tr>
								<tr>
									<th scope="row" ><spring:message code="lecture.title.assignment.duration"/></th>
									<td colspan="3">
										<div class="input-group" style="float:left;width:128px;">
											<input type="text" dispName="<spring:message code="lecture.title.assignment.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="asmtStartDttm" value="${vo.asmtStartDttm}" id="asmtStartDttm" class="inputDate form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('asmtStartDttm')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
										<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.starthour"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtStartHour" value="${vo.asmtStartHour}" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
										<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.startmin"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtStartMin" value="${vo.asmtStartMin}" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>

										<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
										<div class="input-group" style="float:left;width:128px;">
											<input type="text" dispName="<spring:message code="lecture.title.assignment.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="asmtEndDttm" value="${vo.asmtEndDttm}" id="asmtEndDttm" class="inputDate form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('asmtEndDttm')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
										<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.endhour"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtEndHour" value="${vo.asmtEndHour}" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
										<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.endmin"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtEndMin" value="${vo.asmtEndMin}" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
										<meditag:datepicker name1="asmtStartDttm" name2="asmtEndDttm" />
									</td>
								</tr>
								<tr>
									<th scope="row" ><spring:message code="lecture.title.assignment.delaydate"/></th>
									<td>
										<div class="input-group" style="float:left;width:128px;">
											<input type="text" dispName="<spring:message code="lecture.title.assignment.delaydate"/>" maxlength="50" isNull="N" lenCheck="50" name="extSendDttm" value="${vo.extSendDttm}" id="extSendDttm" class="inputDate form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('extSendDttm')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
										<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.delayhour"/>" maxlength="2" isNull="N" lenCheck="2" name="extSendHour" value="${vo.extSendHour}" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
										<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.delaymin"/>" maxlength="2" isNull="N" lenCheck="2" name="extSendMin" value="${vo.extSendMin}" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
										<meditag:datepicker name1="extSendDttm" />
									</td>
									<th scope="row" ><spring:message code="lecture.title.assignment.send.cnt"/></th>
									<td style="text-align: left;">
										<input type="text" style="float:left;width:50px;text-align:right;" dispName="<spring:message code="lecture.title.assignment.send.cnt"/>" maxlength="3" isNull="N" lenCheck="3" name="asmtLimitCnt" value="${vo.asmtLimitCnt}" onfocus="this.select()" id="asmtLimitCnt" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkNumber(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.times.postfix"/></span>
									</td>
								</tr>
								<tr >
									<th scope="row"><label for="uploadify"><spring:message code="common.title.atachfile"/></label></th>
									<td colspan="3">
										<div class="upload">
											<div class="upload_inbox">
												<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
												<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
												<div id="atchprogress" class="progress">
								    				<div class="progress-bar progress-bar-success"></div>
												</div>
											</div>
											<div id="atchfiles" class="multi_inbox"></div>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row" ><spring:message code="common.title.cnts"/></th>
									<td colspan="3">
										<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
										-
										</c:if>
										<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
										<textarea style="height:100px" dispName="<spring:message code="common.title.cnts"/>" isNull="N" name="asmtCts" id="asmtCts" class="form-control input-sm">${vo.asmtCts}</textarea>
										</c:if>
									</td>
								</tr>
								<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
								<tr >
									<th scope="row" style="width:20%"><spring:message code="lecture.title.assignment.regyn"/></th>
									<td colspan="3">
										<select name="assignmentVO.regYn" id="regYn" class="form-control input-sm">
											<c:forEach items="${regYnList}" var="item">
												<option value="${item.codeCd}">${item.codeNm}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								</c:if>
								<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
								<input type="hidden" name="regYn" value="${vo.regYn}" />
								</c:if>
							</table>
							</form>
							<div class="text-right">
								<a href="javascript:editAssignment()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
								<a href="javascript:delAssignment()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
								<a href="javascript:hideEditForm()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul class="nav nav-tabs" style="margin-top:10px;">
							<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
							<li><a href="<c:url value="/lec/assignment/editFormAssignmentQstnMain?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}&tab=0"/>"><spring:message code="lecture.title.assignment.manage.question.tab"/></a></li>
							</c:if>
							<li><a href="<c:url value="/lec/assignment/editFormAssignmentRateMain?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}"/>"><spring:message code="lecture.title.assignment.manage.rate.tab"/></a></li>
							<li class="active"><a href="#"><spring:message code="lecture.title.assignment.manage.rslt.tab"/></a></li>
						</ul>
					</div>
				</div>

				<div class="row" style="margin-top:5px; margin-bottom:20px;">
					<div class="col-md-12">
						<div class="flot-chart">
							<div class="flot-chart-content" id="flot-line-chart-multi"></div>
						</div>
					</div>
				</div>

			<meditag:js src="/tpl/bootstrap/bower_components/flot/excanvas.min.js"/>
			<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.js"/>
			<meditag:js src="/tpl/bootstrap/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"/>

<script type="text/javascript">
	var ItemDTO = new Object();
	var atchFiles; // 첨부파일 목록

	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listStudent(1);
			}
		});

		ItemDTO.curPage = 1;

		var data = [{
			label: "Number of Students",
			data : [
<c:forEach var="item" items="${statusList}" varStatus="status">
				[${item.keyCode}, ${item.keyValue}] <c:if test="${!status.last}">,</c:if>
</c:forEach>
			],
			color: ["green"]
		}];
		var position = 'right';
		var options = {
			series: {
	            lines: {
	                show: true
	            },
	            points : {
	            	show : true
	            }
	        },
	        xaxis: {
	        	axisLabelUseCanvas: true,
	         	axisLabelPadding: 1
	        },
	        yaxis: {
                axisLabelUseCanvas: true,
                tickSize: 1,
                tickDecimals: 0
	        },
	        legend: {
	            position: 'ne'
	        },
	        grid: {
	            hoverable: true //IMPORTANT! this is needed for tooltip to work
	        },
	        tooltip: true,
	        tooltipOpts: {
	        	content: "Score : %x , Students : %y",
	            onHover: function(flotItem, $tooltipEl) {
	                // console.log(flotItem, $tooltipEl);
	            }
	        }
		};
		var plot = $.plot($("#flot-line-chart-multi"), data, options);

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"files" 			: $.parseJSON('${assignmentVO.attachFilesJson}'),
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'ASMT',
												'organization' : "${USER_ORGCD}",
												'type'		: 'file' }
						}
					});

	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

    function peopleFormatter(v, axis) {
        return v.toFixed(axis.tickDecimals) + "명";
    }

	/**
	 * 수정 화면 토글
	 */
	function viewEditForm() {
		$("#viewAsmt").hide();
		$("#editAsmt").show();
	}

	/**
	 * 수정 화면 토글
	 */
	function hideEditForm() {
		$("#viewAsmt").show();
		$("#editAsmt").hide();
	}

	//-- 과제 목록
	function goList() {
		document.location.href = cUrl("/lec/assignment/tchAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}


	function delAssignment() {
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		var f = document.assignmentForm;
		var sendCnt = parseInt(f["sendCnt"].value,10);
		if(sendCnt > 0){
			if(confirm("<spring:message code="lecture.message.assignment.confirm.delete2"/>")){
				process("deleteAssignment");
			}
		}else{
			if(confirm("<spring:message code="lecture.message.assignment.confirm.delete1"/>")){
				process("deleteAssignment");
			}
		}
	}

	/**
	 * 과제 정보 수정
	 */
	function editAssignment() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var asmtType = '${assignmentVO.asmtTypeCd}';
		var f = document.assignmentForm;

		if(isEmpty(f["asmtTitle"].value)) {
			alert("<spring:message code="lecture.message.assignment.alert.input.name"/>");
			f["asmtTitle"].focus();
			return;
		}

		var sendCnt;
		<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
		sendCnt = ${assignmentVO.sendCnt};
		if(sendCnt > 0 && $("#regYn").val() == "N"){
			alert("<spring:message code="lecture.message.assignment.alert.cancelregist"/>");
			return;
		}
		</c:if>

		var asmtStartDttm = chgDate(f["asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["asmtEndDttm"].value);
		var extSendDttm = chgDate(f["extSendDttm"].value);

		if(!dateCheck(asmtEndDttm, extSendDttm)) {
			alert("<spring:message code="lecture.message.assignment.alert.delaydate"/>");
			return;
		}
		if(validate(document.assignmentForm) ==false) return ;
		if(validateTime()==false) return;

		var extSend = $('#extSendDttm').val().replace(/[.]/g, "")+f["extSendHour"].value+f["extSendMin"].value;
		var asmtEnd = $('#asmtEndDttm').val().replace(/[.]/g, "")+f["asmtEndHour"].value+f["asmtEndMin"].value;

		if(asmtEnd > extSend){
			alert("<spring:message code="lecture.message.assignment.alert.delaydate"/>");
			return;
		}

		process("editAssignment");	// cmd
	}


	/*입력한 시간의 유효성을 체크한다.
	*  폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
	*/
	function validateTime(){

		var f = document.assignmentForm;

		var asmtStartHour = chgDate(f["asmtStartHour"].value);  //과제 시작일 시''
		var asmtStartMin = chgDate(f["asmtStartMin"].value);   //과제 시작일 분''

		var asmtEndHour = chgDate(f["asmtEndHour"].value);  //과제 종료일 시''
		var asmtEndMin = chgDate(f["asmtEndMin"].value);   //과제 종료일 분''

		var extSendHour = chgDate(f["extSendHour"].value);  //과제 제출일 시''
		var extSendMin = chgDate(f["extSendMin"].value);   //과제 제출일 분''

		if(asmtStartHour==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.starthour"/>" );
			f["asmtStartHour"].focus();
			return false;
		}
		else if(asmtStartMin==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.startmin"/>" );
			f["asmtStartMin"].focus();
			return false;
		}
		else if(asmtEndHour==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.endhour"/>" );
			f["asmtEndHour"].focus();
			return false;
		}
		else if(asmtEndMin==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.endmin"/>" );
			f["asmtEndMin"].focus();
			return false;
		}
		else if(extSendHour==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.delayhour"/>" );
			f["extSendHour"].focus();
			return false;
		}
		else if(extSendMin==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.delaymin"/>" );
			f["extSendMin"].focus();
			return false;
		}

		if(asmtStartHour>24 || asmtStartHour>24  || extSendHour>24){
			alert("<spring:message code="lecture.message.assignment.alert.validate.hour"/>");
			return false;
		}

		if(asmtStartMin>59 || asmtEndMin>59  || extSendMin>59){
			alert("<spring:message code="lecture.message.assignment.alert.validate.min"/>");
			return false;
		}

		return true;
	}

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

	//날짜 사이의 간격 구하기
	function getGapDate() {
		var f = document.f;
		var from = f.serviceStart.value;
		var to = f.serviceEnd.value;
		if(from != "" && to != "") {
			var days = jsGetDays(chgDate(from),chgDate(to));

			f.serviceDay.value = days;

		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.assignmentForm)) return;
		$('#assignmentForm').attr("action","/lec/assignment/"+cmd);
		document.assignmentForm.submit();
	}

	/**
	 * 과제 등록 완료
	 */
	function assignmentRegist() {
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		process("addRegistAssignment");	// cmd
	}

	/**
	 * 과제 등록 완료 취소
	 */
	function assignmentRegistCancel() {
		if(parseInt(document.assignmentForm["sendCnt"].value,10) > 0) {
			alert("<spring:message code="lecture.message.assignment.alert.cancelregist"/>");
			return;
		}
		document.assignmentForm["regYn"].value = "N";
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		process("editAssignment");	// cmd
	}

</script>
