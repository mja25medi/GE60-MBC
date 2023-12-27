<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="assignmentSendVO" value="${vo.assignmentSendVO}" />
<c:set var="assignmentSendListVO" value="${assignmentSendListVO}" />
<c:set var="pageInfo" value="${pageInfo}"/>
                <div class="learn_top m_column_row">
                    <div class="left_title">
                        <h3><spring:message code="lecture.title.assignment.manage"/></h3>
                        <button type="button" class="list" onclick="goList()"><spring:message code="button.list"/></button>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_top">
                        <h4>과제정보</h4>
                    </div>
                    <div class="table_list">
			 			<form id="assignmentForm" name="assignmentForm" onsubmit="return false">
							<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
							<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd}"/>
							<input type="hidden" name="asmtSn" id="asmtSn" value="${vo.asmtSn}"/>
							<input type="hidden" name="sendCnt" value="${vo.sendCnt}" />
							<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
							<input type="hidden" name="score" value="${assignmentSendVO.score}" />
							<input type="hidden" name="stdNo" value="${assignmentSendVO.stdNo}" />
							<input type="hidden" name="userNm" value="${vo.userNm}" />
							<input type="hidden" name="declsNo" value="${vo.declsNo}" />
							<input type="hidden" name="rateYn" id="rateYn" value="N" />
							<input type="hidden" name="userNoObj" id="userNoObj"/>
							<input type="hidden" name="strStdNo" />
							<input type="hidden" name="strScore" />
							<input type="hidden" name="listScale" value="${vo.listScale}"/>
							<input type="hidden" name="tab" />
							<input type="hidden" name="asmtSelectTypeCd" value="${vo.asmtSelectTypeCd}" />
							<input type="hidden" name="asmtTypeCd" value="${vo.asmtTypeCd}" />
							<input type="hidden" name="asmtTitle" value="${vo.asmtTitle}" id="asmtTitle"/>
							<input type="hidden" name="asmtStartDttm" value="${vo.asmtStartDttm}" id="asmtStartDttm" />
							<input type="hidden" name="asmtStartHour" value="${vo.asmtStartHour}" />
							<input type="hidden" name="asmtStartMin" value="${vo.asmtStartMin}" />
							<input type="hidden" name="asmtEndDttm" value="${vo.asmtEndDttm}" id="asmtEndDttm"/>
							<input type="hidden" name="asmtEndHour" value="${vo.asmtEndHour}" />
							<input type="hidden" name="asmtEndMin" value="${vo.asmtEndMin}" />
							<input type="hidden" name="extSendDttm" value="${vo.extSendDttm}" id="extSendDttm"/>
							<input type="hidden" name="extSendHour" value="${vo.extSendHour}" />
							<input type="hidden" name="extSendMin" value="${vo.extSendMin}" />
							<input type="hidden" name="asmtLimitCnt" value="${vo.asmtLimitCnt}" id="asmtLimitCnt"/>
							<input type="hidden" name="asmtCts" value="${vo.asmtCts}" id="asmtCts"/>
							<input type="hidden" name="regYn" value="${vo.regYn}" id="regYn"/>
							<input type="hidden" name="asmtSvcCd" value="${vo.asmtSvcCd}" id="asmtSvcCd"/>
	                        <ul class="list">
	                            <li class="head"><label><spring:message code="lecture.title.assignment.name"/></label></li>
	                            <li>${assignmentVO.asmtTitle}</li>
	                        </ul>
	                        <ul class="list">
	                            <li class="head"><label>과제유형</label></li>
	                            <li>
	                            <c:if test="${assignmentVO.asmtTypeCd eq 'ON'}"><label class="btn3 sm solid fcGreen">온라인</label></c:if>
								<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}"><label class="btn3 sm solid fcViolet">오프라인</label></c:if>
	                            <!-- <label class="btn3 sm solid fcRed">혼합</label> -->
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
                            <li>
                                <ul class="add_file">
                                	<c:forEach var="file" items="${assignmentVO.attachFiles}">
                                		<li>${file.lecDownloadTag}</li>
                                	</c:forEach>


                                </ul>
                            </li>
                        </ul>
	                        <ul class="list">
	                            <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
	                            <li>${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}</li>
	                        </ul>
	                        <ul class="list">
	                            <li class="head"><label><spring:message code="lecture.title.assignment.regyn"/></label></li>
	                            <li>
	                            <c:set var="regYn" value="${assignmentVO.regYn}"/>
								<c:if test="${empty assignmentVO.regYn}"><c:set var="regYn" value="N"/></c:if>
									<meditag:codename code="${regYn}" category="REG_YN" />
	                            </li>
	                        </ul>
	                    </form>
                    </div>
                </div>
                <ul class="class_tab">
	                <c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
						<li><a href="<c:url value="/lec/assignment/editFormAssignmentQstnMain?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}&amp;tab=0"/>"><spring:message code="lecture.title.assignment.manage.question.tab"/></a></li>
					</c:if>
					<li class="active"><a href="#"><spring:message code="lecture.title.assignment.manage.rate.tab"/></a></li>
					<%-- <li><a href="<c:url value="/lec/assignment/editFormAssignmentRsltMain?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}"/>"><spring:message code="lecture.title.assignment.manage.rslt.tab"/></a></li> --%>
                </ul>
                <div class="segment">
                    <div class="board_top">
                        <div class="page_btn flex-none">
                        	<c:if test="${MSG_SMS eq 'Y' }">
								<button type="button" class="btn" onclick="messageForm('SMS')"><spring:message code="button.sms"/></button>
							</c:if>
							<c:if test="${MSG_EMAIL eq 'Y' }">
								<button type="button" class="btn" onclick="messageForm('EMAIL');"><spring:message code="button.email"/></button>
							</c:if>
							<c:if test="${MSG_NOTE eq 'Y' }">
							<button type="button" class="btn" onclick="messageForm('MSG');"><spring:message code="button.note"/></button>
							</c:if>
                        </div>
                        <form name="StdSearch" id="StdSearch" onsubmit="return false;" class="form-inline">		
                            <fieldset class="form-row">
                            <legend class="blind">게시판 검색</legend>
                            <input type="text" name="searchValue"  id="searchValue"  class="form-control" placeholder="<spring:message code="user.title.userinfo.name"/>" value="${assignmentVO.userNm}"/>
                            <button type="submit" class="btn type2" onclick="listStudent(1)">검색</button>
                            <select name="listScale" id="listScale" onchange="changelistScale()" class="form-select type-num" title="페이지당 리스트수를 선택하세요.">
								<option value="20" <c:if test="${assignmentForm.listScale == 20}">selected</c:if>>20</option>
								<option value="40" <c:if test="${assignmentForm.listScale == 40}">selected</c:if>>40</option>
								<option value="60" <c:if test="${assignmentForm.listScale == 60}">selected</c:if>>60</option>
								<option value="80" <c:if test="${assignmentForm.listScale == 80}">selected</c:if>>80</option>
								<option value="100" <c:if test="${assignmentForm.listScale == 100}">selected</c:if>>100</option>
								<option value="200" <c:if test="${assignmentForm.listScale == 200}">selected</c:if>>200</option>
							</select>
                            </fieldset>
                        </form>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>평가관리 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="4%"><span class="custom-input onlychk"><input type="checkbox" name="stdChkAll" id="stdChkAll" style="border:0" onclick="checkAll()"/><label for="stdChkAll"></label></span></th>
                                    <th scope="col" width="8%"><spring:message code="common.title.no"/></th>
                                    <th scope="col"><spring:message code="user.title.userinfo.name"/></th>
                                    <th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
                                    <th scope="col"><spring:message code="lecture.title.assignment.sendyn"/></th>
                                    <th scope="col"><spring:message code="lecture.title.assignment.send.lastdate"/></th>
                                    <th scope="col"><spring:message code="lecture.title.assignment.score"/></th>
                                    <th scope="col">모사율</th>
                                    <th scope="col"><spring:message code="lecture.title.assignment.rateyn"/></th>
                                    <th scope="col"><spring:message code="lecture.title.assignment.rateresult"/></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="item" items="${assignmentSendListVO}" varStatus="status">
	                                <tr>
	                                    <td scope="row" data-label="선택">
	                                    	<span class="custom-input onlychk">
	                                    	<input type="hidden" name="sel" value="${item.userNo}"/>
	                                    	<input type="checkbox" name="selStd" id="selStd${status.index }" value="${item.stdNo}"/><label for="selStd${status.index }"></label>
                                    	</span></td>
	                                    <td scope="row" data-label="<spring:message code="common.title.no"/>">${fn:length(assignmentSendListVO) - status.index}</td>
	                                    <td class="title"  style="text-align: center;" data-label="<spring:message code="user.title.userinfo.name"/>">${item.userNm}</td>
	                                    <td class="title" style="text-align: center; data-label="<spring:message code="user.title.userinfo.userid"/>">${item.userId}</td>
	                                    <td data-label="<spring:message code="lecture.title.assignment.sendyn"/>">
	                                    	<c:choose>
												<c:when test="${item.sendCnt > 0}">
													<spring:message code="lecture.title.assignment.sendy"/>
												</c:when>
												<c:otherwise>
													<spring:message code="lecture.title.assignment.sendn"/>
												</c:otherwise>
											</c:choose>
	                                    </td>
	                                    <td data-label="<spring:message code="lecture.title.assignment.send.lastdate"/>"><meditag:dateformat type="1" delimeter="." property="${item.modDttm}"/></td>
	                                    <td data-label="<spring:message code="lecture.title.assignment.score"/>">${item.score} <spring:message code="common.title.score"/></td>
	                                    <td data-label="모사율">
                                    		<%--completeStatus 로 완료 여부 확인, 완료 시 링크 삽입/표시 --%>
					                        <!-- </div> -->
                       						<c:if test="${item.completeStatus eq 'Y'}">
												<a href="http://183.111.234.121:8082/ckplus/copykiller.jsp?uri=${item.copyRatioUri}&property_id=4" target="_blank">${item.dispTotalCopyRatio}</a> 
											</c:if>
	                                    </td>
	                                    <td data-label="<spring:message code="lecture.title.assignment.rateyn"/>">
	                                    	<c:choose>
												<c:when test="${item.rateYn eq 'Y' }">
													<spring:message code="lecture.title.assignment.rateyn_y"/>
												</c:when>
												<c:otherwise>
													<spring:message code="lecture.title.assignment.rateyn_n"/>
												</c:otherwise>
											</c:choose>
	                                    </td>
	                                    <td data-label="<spring:message code="lecture.title.assignment.rateresult"/>">
	                                    	<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
												<div class="input-group" style="width:120px;">
													<input type="text" name="selScore" style="text-align:center; width:60px;" class="form-control input-sm inputNumber" onkeyup="isChkMaxNumber(this,'100')"/>
													<button class="btn3 type1 sm" onclick='addScore("${status.index}")' style="cursor:pointer"><spring:message code="button.add"/></button>
												</div>
											</c:if>
											<c:if test="${item.sendCnt > 0 && assignmentVO.asmtTypeCd ne 'OFF'}">
												<button onclick="javascript:editCodeRate('${item.stdNo}')" class="btn3 type1 sm"><spring:message code="button.rate"/></button>
											</c:if>
	                                    </td>
                                	</tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
				<%-- </td>
			</tr>
		</c:forEach> --%>
				<!-- </tbody>
			</table> -->
                </div>
                   
				<c:if test="${assignmentVO.regYn eq 'Y' && 	assignmentVO.asmtTypeCd ne 'OFF'}">
					<div class="txt-right mt20">
						<a href="javascript:resetSend()" class="btn3 type1 mt4 mb4"><spring:message code="button.reset.send"/></a>
					</div>
				</c:if>
				<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
					<div class="txt-right mt20">
						<a href="javascript:addScoreAll()" class="btn3 type1 mt4 mb4"><spring:message code="button.add.score"/></a>
					</div>
				</c:if>
               	<div class="board_pager">
                   	<meditag:paging pageInfo="${pageInfo}" funcName="listStudent" name="lect"/>
               	</div>
			<!-- </div> -->
						

<script type="text/javascript">

	var ItemDTO = new Object();
	var atchFiles; // 첨부파일 목록

	var modalBox = null;

	$(document).ready(function() {
		$(".sub_title_2.ohddien").text("과제관리");
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

        //전체선택 /전체취소
		$('#stdChkAll').click(function(){
		    var state=$('input[name=stdChkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#StdForm input[name='selStd']").prop({checked:true});
		    }else{
		    	$(document).find("#StdForm input[name='selStd']").prop({checked:false});
		    }
		});

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

	function changelistScale() {
		listStudent(1);
		var listScale = '${listScale}';
		$("#listScale").val(listScale).attr("selected", "selected");

	}
	/**
	
	 * 학습자 목록 조회
	 */
	function listStudent(page){
		ItemDTO.curPage = page;
		var searchValue = $("#searchValue").val();
		var listScale = $("#listScale > option:selected").val();
		document.location.href = cUrl("/lec/assignment/editFormAssignmentRateMain")+"?crsCreCd=${assignmentVO.crsCreCd}${AMPERSAND}asmtSn=${assignmentVO.asmtSn}${AMPERSAND}curPage="+page+"${AMPERSAND}listScale="+listScale+"${AMPERSAND}userNm="+searchValue+"${AMPERSAND}";
	}

	/**
	 * 학습자 평가
	 */
	function editRate(stdNo) {
		var addContent  = "<iframe id='addRateFrame' name='addRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/editFormRatePop"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}&amp;stdNo="+stdNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1500, 900);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.rate"/>");
		parent.modalBox.show();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.assignmentForm)) return;
		$('#assignmentForm').attr("action","/lec/assignment/"+cmd);
		//document.assignmentForm.submit();
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
 	* 처리 결과 표시 콜백
 	*/
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			location.reload();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 개별 성적 저장
	 */
	function addScore(no) {
		if("N" == "${regYn}"){
			alert('<spring:message code="lecture.message.common.alert.regyn_n"/>');
			return;
		}
		var ScoreObj = document.getElementsByName("selScore");
		var StdObj = document.getElementsByName("selStd");
		
		if(ScoreObj[no].value > 100){
			alert("<spring:message code="lecture.message.common.alert.input.score100"/>");
			ScoreObj[no].value = "";
			ScoreObj[no].focus();
			return;
		}
		if(ScoreObj[no].value ==""){
			alert('점수를 입력하세요');
			ScoreObj[no].focus();
			return;
		}
		
		var f = document.assignmentForm;
		f["crsCreCd"].value = '${assignmentVO.crsCreCd}';
		f["asmtSn"].value = '${assignmentVO.asmtSn}';
		f["score"].value = ScoreObj[no].value;
		f["stdNo"].value = StdObj[no].value;
		f["sendCnt"].value = 1;
		f["rateYn"].value = "Y";
		process("addSendScore");

	}

	function addScoreAll() {
		if("N" == "${regYn}"){
			alert('<spring:message code="lecture.message.common.alert.regyn_n"/>');
			return;
		}
		var ScoreObj = document.getElementsByName("selScore");
		var StdNoObj = document.getElementsByName("selStd");
		var strStdNo = "";
		var strScore = "";

		var overScore = 0;

		for(var i=0; i < ScoreObj.length; i++) {
			//-- 값이 들어온것만 잡는다.
			if(!isEmpty(ScoreObj[i].value)) {
				strStdNo = strStdNo+"|"+StdNoObj[i].value;
				strScore = strScore+"|"+ScoreObj[i].value;
				if(ScoreObj[i].value > 100){
					overScore++;
				}
			}
		}
		strStdNo = strStdNo.substring(1);
		strScore = strScore.substring(1);

		if(strStdNo == ""){
			alert("<spring:message code="lecture.message.common.alert.input.score"/>");
			return;
		}

		if(overScore > 0){
			alert("<spring:message code="lecture.message.common.alert.input.score100"/>");
			return;
		}
		
		var f = document.assignmentForm;
		f["crsCreCd"].value = '${assignmentVO.crsCreCd}';
		f["asmtSn"].value = '${assignmentVO.asmtSn}';
		f["sendCnt"].value = 1;
		f["strStdNo"].value = strStdNo;
		f["strScore"].value = strScore;
		f["rateYn"].value = "Y";
		process("addSendScoreAll");

	}

	/**
	 * 재제출 설정
	 */
	function resetSend() {
		var userList = "";
		var chkLength = "";
		$("input[name='selStd']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
		         userList += this.value;
		         chkLength ++
			}
		});	

		if(userList == ""){
			alert("<spring:message code="lecture.message.assignment.rate.alert.resend.selectuser"/>");
		}else{
			if(confirm('<spring:message code="lecture.message.assignment.rate.alert.resend.confirm" arguments="'+chkLength+'"/>')){
				var f = document.assignmentForm;
				f["userNoObj"].value = userList;
				process("removeSend");	// cmd
			}
		}
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
		process("editRegistAssignment");	// cmd
	}

	/**
	* 메세지 전송 popup
	*/
	function messageForm(msgDivCd) {
		var userList = "";
		if($("input[name='selStd']").length > 1) { 
			$("input[name='selStd']").each(function(i){
				if(this.checked) {
					if(i > 0) userList += ",";
			         userList += this.previousElementSibling.value;
				}
			});	
		} else if($("input[name='selStd']").length == 1) {
			  userList += $("input[name='selStd']").previousElementSibling.value;
		}
		
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/message/messageWritePop"/>"
			+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 650);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}
	
	//전체선택 /전체취소
	function checkAll() {
	    var state=$('input[name=stdChkAll]:checked').size();
	    if(state==1){
	   		$(document).find("input[name='selStd']").prop({checked:true});
	    }else{
	    	$(document).find("input[name='selStd']").prop({checked:false});
	    }
	}
	   
	/**
	 * 학습자 평가(코딩학습의 경우)
	 */
	function editCodeRate(stdNo) {

		var addContent  = "<iframe id='addRateFrame' name='addRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='none' src='<c:url value="/lec/assignment/rateCodeAsmtFormPop"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&asmtSn=${assignmentVO.asmtSn}&stdNo="+stdNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1200, 800);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.rate"/>");
		modalBox.show();
	}
</script>
