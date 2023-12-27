<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="examVO" value="${vo}" />
<c:set var="examQuestionList" value="${examQuestionListVO}"/>
				<div id="content">
	                <div class="learn_top m_column_row">
	                    <div class="left_title">
	                        <h3>시험채점</h3>
	                        <button type="button" class="list" onclick="goList()">목록</button>
	                    </div>
	                </div>
					<div class="segment">
						<div class="board_top">
							<h4>시험정보</h4>
						</div>
						<div class="table_list">
							<ul class="list">
								<li class="head"><label>시험유형</label></li>
								<li><c:if test="${examVO.examTypeCd eq 'ON'}"><label class="btn3 sm solid fcGreen">온라인</label></c:if> 
									<c:if test="${examVO.examTypeCd eq 'OFF'}"><label class="btn3 sm solid fcViolet">오프라인</label></c:if>
								</li>
								<li class="head"><label>응시유형</label></li>
								<li><meditag:codename code="${examVO.examStareTypeCd}" category="EXAM_STARE_TYPE_CD" /></li>
							</ul>
							<ul class="list">
								<li class="head"><label>시험제목</label></li>
								<li>${examVO.examTitle}</li>
							</ul>
							<c:if test="${examVO.examTypeCd eq 'ON'}">
								<ul class="list">
									<c:if test="${examVO.semiExamYn eq 'Y'}">
										<li class="head"><label>응시 기준 강의</label></li>
										<li>${examVO.stareLecCount} 강 수강 후</li>
									</c:if>
									<c:if test="${examVO.semiExamYn eq 'N'}">
										<li class="head"><label>응시가능진도율</label></li>
										<li>${examVO.stareCritPrgrRatio}% <spring:message code="common.title.over" /></li>
									</c:if>
									<li class="head"><label>응시제한횟수</label></li>
									<li>${examVO.stareLimitCnt}<spring:message code="common.title.times.postfix" /></li>
								</ul>
								<ul class="list">
									<li class="head"><label>시간제한</label></li>
									<c:choose>
										<c:when test="${examVO.examStareTm > 0}">
											<li>${examVO.examStareTm}<spring:message code="common.title.min" /></li>
										</c:when>
										<c:otherwise>
											<li>&nbsp;<spring:message code="lecture.message.exam.timelimit.nouse" /></li>
										</c:otherwise>
									</c:choose>
								</ul>
								<ul class="list">
									<li class="head"><label>시험출제문항수/배점</label></li>
									<li><c:if test="${not empty examVO.selCnt && examVO.selCnt > 0 }">
												선택형  : ${examVO.selCnt }개 / ${examVO.selPnt } 점</c:if> 
										<c:if test="${not empty examVO.shortCnt && examVO.shortCnt > 0 }">
											<br>단답형  : ${examVO.shortCnt }개 / ${examVO.shortPnt } 점</c:if> 
										<c:if test="${not empty examVO.desCnt && examVO.desCnt > 0 }">
											<br>서술형 : ${examVO.desCnt }개 / ${examVO.desPnt } 점</c:if>
									</li>
								</ul>
								<c:if test="${examVO.examStareTypeCd eq 'R'}">
									<ul class="list">
										<li class="head"><label>시험기간</label></li>
										<li>
											<meditag:dateformat type="1" delimeter="." property="${examVO.examStartDttm}" />
											<meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}" /> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}" /> 
											<meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}" />
									 	</li>
									</ul>
									<ul class="list">
										<li class="head"><label>결과확인일</label></li>
										<li><meditag:dateformat type="1" delimeter="." property="${examVO.rsltCfrmDttm}" />
											<meditag:dateformat type="7" delimeter="." property="${examVO.rsltCfrmDttm}" />
										</li>
									</ul>
								</c:if>
							</c:if>
							<c:if test="${examVO.examTypeCd eq 'OFF'}">
								<li class="head"><label>결과확인일</label></li>
								<li><meditag:dateformat type="1" delimeter="."	property="${examVO.examStartDttm}" />  
									<meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}" /> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}" /> 
									<meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}" />
								</li>
							</c:if>
							<ul class="list">
								<li class="head"><label>공개여부</label></li>
								<li><c:set var="regYn" value="${examVO.regYn}" /> 
								<c:if test="${empty examVO.regYn}">
									<c:set var="regYn" value="N" />
								</c:if> <meditag:codename code="${regYn}" category="REG_YN" />
								</li>
							</ul>
							<ul class="list">
								<li class="head"><label>시험설명</label></li>
								<li>${fn:replace(examVO.examCts,crlf,"<br/>")}</li>
							</ul>
						</div>
					</div>
					<ul class="class_tab">
                        <li class="active"><a href="#">문제관리</a></li>
                        <li><a href="<c:url value="/lec/exam/editFormExamMain?examSn=${examVO.examSn}&amp;crsCreCd=${examVO.crsCreCd}&gubun=R"/>">평가관리</a></li>
                    </ul>
                    
                    <form name="SearchQstn" id="SearchQstn" >
                <div class="segment">
                    <div class="board_top">
					 <c:if test="${examVO.regYn eq 'N' || empty examVO.regYn}">
                        <div class="page_btn">
                            <button type="button" class="btn" onclick="questionWrite('J');"><meditag:codename code="J" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.qstn"/></button>
                            <button type="button" class="btn" onclick="questionWrite('K');"><meditag:codename code="K" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.qstn"/></button>
                            <button type="button" class="btn" onclick="questionWrite('S');"><meditag:codename code="S" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.qstn"/></button>
                            <button type="button" class="btn" onclick="questionWrite('D');"><meditag:codename code="D" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.qstn"/></button>
                            <button type="button" class="btn type4" onclick="qstnBankWrite();"><spring:message code="button.view.exambank"/></button>
                            <button type="button" class="btn type1" onclick="examRegist();"><spring:message code="button.ok.regist"/></button>
                        </div>
                        	</c:if>
					 	<c:if test="${examVO.regYn eq 'Y'}">
                        <div class="page_btn">
                            <button type="button" class="btn type4" onclick="previewQstn();"><spring:message code="lecture.title.exam.question"/> <spring:message code="button.question.preview"/></button>
                            <button type="button" class="btn type1" onclick="examRegistCancel();"><spring:message code="button.cancel.regist"/></button>
                        </div>
                        	</c:if>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>문제관리 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col">시험문제</th>
                                    <th scope="col" width="15%">문제유형</th>
                                    <th scope="col" width="15%">관리</th>
                                </tr>
                            </thead>
                            <tbody>
                           		<c:set var="qstnCnt" value="0" />
								<c:set var="lastQstnNo" value="0"/>
								<c:forEach var="item" items="${examQuestionList}" varStatus="status">
									<c:choose>
										<c:when test="${qstnCnt < item.qstnNo }">
											<c:set var="qstnCnt" value="${item.qstnNo}" />
                               				 <tr>
			                                    <td scope="row" data-label="번호">${item.qstnNo}</td>
			                                    <%-- <td class="title" data-label="시험문제">${item.title}</td> --%>
			                                    <td class="title" data-label="시험문제"><a href="javascript:viewQstn('${item.examQstnSn}');" >${item.title}</a></td>
			                                    <td data-label="문제유형"><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE"/></td>
			                                    <td data-label="관리">
				                                    <c:if test="${examVO.regYn eq 'N' || empty examVO.regYn}">
				                                   <button type="button" onclick="questionEdit('${item.examQstnSn}')" class="btn btn-info btn-sm"><spring:message code="button.edit"/></button>
				                                    </c:if>
				                                    <c:if test="${examVO.regYn eq 'Y'}">-</c:if>
			                         		    </td>
									
                             				 </tr>	
                             				 </c:when>
                             				
                             				 	<c:otherwise>
								<tr>
									 <td class="title" data-label="시험문제">${item.title}</td>
									 <td data-label="문제유형"><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE"/></td>
									   <td data-label="관리">
	                                    <c:if test="${examVO.regYn eq 'N' || empty examVO.regYn}">
	                                    <button class="btn type3" onclick="questionEdit('${item.examQstnSn}');">수정</button>
	                                    </c:if>
	                                    <c:if test="${examVO.regYn eq 'Y'}">-</c:if>
	                         		    </td>
								</tr>
										</c:otherwise>
                             				  </c:choose>
                             				 	<c:if test="${status.last }"><c:set var="lastQstnNo" value="${item.qstnNo}"/></c:if>
                             				 </c:forEach>		
                             				 <c:if test="${empty examQuestionList}">
									<tr>
										<td colspan="4"><spring:message code="lecture.message.exam.question.nodata"/></td>
									</tr>
								</c:if>
                            </tbody>
                        </table>
                        <input type="hidden" id="lastQstnNo" name="lastQstnNo" value="${lastQstnNo }"/>
                    </div>
                </div>
                </form>
            </div>
				
<form id="examForm" name="examForm" onsubmit="return false" method="post">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<input type="hidden" name="qstnScores" value="${vo.qstnScores }" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn }" />
	<input type="hidden" name="qstnNos" value="${vo.qstnNos }" />
	<input type="hidden" name="stareCnt" value="${vo.stareCnt }"/>
</form>

<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

        //전체선택 /전체취소
		$('#stdChkAll').click(function(){
		    var state=$('input[name=stdChkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#StdSearch input[name='selStd']").attr({checked:true});
		    }else{
		    	$(document).find("#StdSearch input[name='selStd']").attr({checked:false});
		    }
		});

		setScore();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function goList() {
		document.location.href = cUrl("/lec/exam/examMain")+"?crsCreCd=${examVO.crsCreCd}";
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examForm').attr("action","/lec/exam/"+cmd);
		document.examForm.submit();
	}



	/**
	 * 시험 등록 완료
	 */
	function examRegist() {
		process("addRegistExam");	// cmd
	}

	/**
	 * 시험 등록 완료 취소
	 */
	function examRegistCancel() {
		process("cancelRegistExam");	// cmd
	}

	/**
	 * 시험 문제 등록 폼 호출
	 */
	function questionWrite(qstnType) {
		if('${examVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.exam.question.alert.write"/>");
			return;
		}
		var addContent  = "<iframe id='examWriteFrame' name='examWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/addFormQuestionPop"/>"
			+ "?examSn=${examVO.examSn}&amp;crsCreCd=${examVO.crsCreCd}&amp;qstnType="+qstnType+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1450, 720);
		modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		modalBox.show();
	}

	/**
	 * 시험 문제 수정 폼 호출
	 */
	function questionEdit(examQstnSn) {
		if('${examVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.exam.question.alert.edit"/>");
			return;
		}
		var addContent  = "<iframe id='examWriteFrame' name='examWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/editQuestionPop"/>"
			+ "?examSn=${examVO.examSn}&amp;crsCreCd=${examVO.crsCreCd}&amp;examQstnSn="+examQstnSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1450, 720);
		modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		modalBox.show();
	}

	/**
	 * 문제 은행 보기 폼
	 */
	function qstnBankWrite() {
		if('${examVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.exam.question.alert.write"/>");
			return;
		}

		var addContent  = "<iframe id='examBankFrame' name='examBankFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/editQbankPop"/>"
			+ "?examSn=${examVO.examSn}&semiExamYn=${examVO.semiExamYn}&crsCreCd=${vo.crsCreCd}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1300, 700);
		modalBox.setTitle("<spring:message code="lecture.title.exam.qstnbank"/>");
		modalBox.show();
	}

	/**
	 * 입력한 시간의 유효성을 체크한다.
	 * 폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
	 */
	function validateTime(){

		var f = document.examForm;

		if(f["examStartDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.startdate"/>");
			return false;
		}

		if(f["examEndDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.enddate"/>");
			return false;
		}

		if(f["rsltCfrmDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.resultdate"/>");
			return false;
		}

		var asmtStartHour = f["examStartHour"].value;  //과제 시작일 시''
		var asmtStartMin = f["examStartMin"].value;   //과제 시작일 분''

		var asmtEndHour = f["examEndHour"].value;  //과제 종료일 시''
		var asmtEndMin = f["examEndMin"].value;   //과제 종료일 분''

		var extSendHour = f["rsltCfrmHour"].value; //결과확인일 시
		var extSendMin = f["rsltCfrmMin"].value;   //결과확인일 분

		if(asmtStartHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.starthour"/>" );
			f["examStartHour"].focus();
			return false;
		}
		if(asmtStartMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.startmin"/>" );
			f["examStartMin"].focus();
			return false;
		}
		if(asmtEndHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.endhour"/>" );
			f["examEndHour"].focus();
			return false;
		}
		if(asmtEndMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.endmin"/>" );
			f["examEndMin"].focus();
			return false;
		}
		if(extSendHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.resulthour"/>" );
			f["rsltCfrmHour"].focus();
			return false;
		}
		if(extSendMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.resultmin"/>" );
			f["rsltCfrmMin"].focus();
			return false;
		}

		if(asmtStartHour>24 || asmtStartHour>24  || extSendHour>24){
			alert("<spring:message code="lecture.message.exam.alert.validate.hour"/>");
			return false;
		}

		if(asmtStartMin>59 || asmtEndMin>59  || extSendMin>59){
			alert("<spring:message code="lecture.message.exam.alert.validate.min"/>");
			return false;
		}

		return true;
	}

	function setScoreChk(){
		var Obj = document.getElementsByName("score");
		if(Obj.length ==0){
			alert("<spring:message code="lecture.message.exam.alert.setscore.nodata"/>");
			return;
		}
		setScore();
	}

	function setScore() {
		var Obj = document.getElementsByName("score");
		var AvgScore = Math.round(100 / Obj.length);
		var Tot_AvgScore = 0;
		//-- 스코어 입력란이 있을 경우

		if(Obj.length > 0) {
			for(var i=0; i < Obj.length; i++) {
				Obj[i].value = AvgScore;
				Tot_AvgScore += AvgScore;
			}
			Obj[Obj.length-1].value = Number(Obj[Obj.length-1].value) + Number(100-Tot_AvgScore);

			//-- 첫번째 배점 칸에 값이 없을 경우에만 자동 배점 처리함.
			if(Obj[0].value == "0.0") {
			}
		}
	}

	function previewQstn(){
		var addContent  = "<iframe id='examPaperFrame' name='examPaperFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/previewPaperPop"/>"
				+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		parent.modalBox.resize(1450, 600);
		modalBox.setTitle("<spring:message code="lecture.title.exam.question"/> <spring:message code="button.question.preview"/>");
		modalBox.show();
	}

	function setSortQstn(){
		var addContent  = "<iframe id='examPaperFrame' name='examPaperFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/sortQuestionPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1100, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question"/> <spring:message code="button.sort"/>");
		parent.modalBox.show();
	}


	function viewQstn(examQstnSn){
		var addContent  = "<iframe id='examWriteFrame' name='examWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/viewQstnPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}&amp;examQstnSn="+examQstnSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1450, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question"/> <spring:message code="button.view"/>");
		parent.modalBox.show();
	}

</script>
