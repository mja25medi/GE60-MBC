<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="examVO" value="${vo}" />
<c:set var="examStareVO" value="${vo.examStareVO}" />
<c:set var="examStareListVO" value="${examStareListVO}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="chkstaren" value="" />
<script src="/js/jquery/jquery-1.11.1.min.js"></script>
<script src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>
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
											${examVO.examStartDttm} ${examVO.examStartHour }:${examVO.examStartMin } ~ ${examVO.examEndDttm} ${examVO.examEndHour }:${examVO.examEndMin }
									 	</li>
									</ul>
									<ul class="list">
										<li class="head"><label>결과확인일</label></li>
										<li>${examVO.rsltCfrmDttm} ${examVO.rsltCfrmHour }:${examVO.rsltCfrmMin }</li>
									</ul>
								</c:if>
							</c:if>
							<c:if test="${examVO.examTypeCd eq 'OFF'}">
								<ul class="list">
									<li class="head"><label>결과확인일</label></li>
									<li>${examVO.examStartDttm} ${examVO.examStartHour }:${examVO.examStartMin } ~ ${examVO.examEndDttm} ${examVO.examEndHour }:${examVO.examEndMin }</li>
								</ul>
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
				<c:if test="${examVO.examTypeCd eq 'ON' }">
                        <li><a href="<c:url value="/lec/exam/editFormExamMain?examSn=${examVO.examSn}&amp;crsCreCd=${examVO.crsCreCd}&amp;gubun=Q"/>"><spring:message code="lecture.title.exam.manage.question.tab"/></a></li>
                        </c:if>
                        <li class="active"><a href="<c:url value="/lec/exam/editFormExamMain?examSn=${examVO.examSn}&amp;crsCreCd=${examVO.crsCreCd}&amp;gubun=R"/>"><spring:message code="lecture.title.exam.manage.result.tab"/></a></li>
                  </ul>		
                  </div>
                 <div class="segment">
                    <div class="board_top">
                        <div class="page_btn flex-none">
                         <c:if test="${MSG_SMS eq 'Y' }">
							<button type="button" class="btn" onclick="messageForm('SMS')"><spring:message code="button.sms"/></button>
							</c:if>
							<c:if test="${MSG_EMAIL eq 'Y' }">
							<button type="button" class="btn" onclick="messageForm('EMAIL');" ><spring:message code="button.email"/></button>
							</c:if>
							<c:if test="${MSG_NOTE eq 'Y' }">
							<button type="button" class="btn" onclick="messageForm('MSG');"> <spring:message code="button.note"/></button>
							</c:if>
                        </div>
                        <form name="StdSearch" id="StdSearch" action="javascript:listStd(1)" class="board_search">
                           <input type="hidden" name="declsNo"/>
							<input type="hidden" name="examNm"/>
							<input type="hidden" name="listScale"/>
							<input type="hidden" name="curPage"/>
							<input type="hidden" id="userNm" name="userNm" value="${examVO.userNm}"/>
                            <fieldset class="form-row">
                            <legend class="blind">게시판 검색</legend>
                            <input type="text" class="form-control" name="searchValue" id="searchValue"  placeholder="이름" title="검색어를 입력하세요" value="">
                            <button type="submit" class="btn type2" onclick="javascript:listStd(1);">검색</button>
                            <select class="form-select type-num" name="listScale" id="listScale" onchange="listStd(1)"  title="페이지당 리스트수를 선택하세요.">
	                          	<option value="20" <c:if test="${examForm.listScale == 20}">selected</c:if>>20</option>
								<option value="40" <c:if test="${examForm.listScale == 40}">selected</c:if>>40</option>
								<option value="60" <c:if test="${examForm.listScale == 60}">selected</c:if>>60</option>
								<option value="80" <c:if test="${examForm.listScale == 80}">selected</c:if>>80</option>
								<option value="100" <c:if test="${examForm.listScale == 100}">selected</c:if>>100</option>
								<option value="200" <c:if test="${examForm.listScale == 200}">selected</c:if>>200</option>
                            </select>
                            <div class="clearfix"></div>
                            	</fieldset>
                          </form>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>평가관리 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="4%">
                                    <span class="custom-input onlychk"><input type="checkbox" name="stdChkAll" id="stdChkAll"  onclick="checkAll()"><label for="stdChkAll"></label></span>
                                    </th>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col">이름</th>
                                    <th scope="col">아이디</th>
                                    <th scope="col">응시여부</th>
                                    <c:if test="${examVO.examTypeCd eq 'ON'}"><th scope="col">응시일시</th></c:if>
                                    <th scope="col">점수</th>
                                    <th scope="col">평가여부</th>
                                    <th scope="col">평가/결과</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${examStareListVO}" varStatus="status">
                                <tr>
                                  <td scope="row" data-label="선택">
                                     	<span class="custom-input onlychk">
	                                    	<input type="hidden" name="sel" value="${item.userNo}"/>
	                                    	<input type="checkbox" name="selStd" id="selStd${status.index }" value="${item.stdNo}"/><label for="selStd${status.index }"></label>
                                    	</span></td>
                                    <td scope="row" data-label="번호">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
                                    <td class="title" data-label="이름">${item.userNm}</td>
                                    <td class="title" data-label="아이디">${item.userId}</td>
                                    <td data-label="응시여부">
										<c:if test="${item.stareCnt == 0 }">
											<spring:message code="lecture.title.exam.staren"/>
										</c:if>
										<c:if test="${item.stareCnt > 0 }">
											<c:set var="chkstaren" value="Y" />
											<spring:message code="lecture.title.exam.starey"/>
										</c:if>
									</td>
									<c:if test="${examVO.examTypeCd eq 'ON'}">
                                    <td data-label="응시일시">
                                    	<c:if test="${item.stareCnt > 0 }">
												<c:set var="dttm" value='${item.endDttm }' />
												<c:if test="${item.endDttm eq null }">
													<c:set var="dttm" value='${item.startDttm }' />
												</c:if>
												<meditag:dateformat type="1" delimeter="." property="${dttm}"/> <meditag:dateformat type="7" delimeter="." property="${dttm}"/>
											</c:if>
											<c:if test="${item.stareCnt == 0 }">-</c:if>	
                                    </td>
                                    </c:if>
                                    <td data-label="점수"><c:if test="${item.stareCnt > 0 }">
												<fmt:formatNumber value="${item.totGetScore}" pattern="#.#" minFractionDigits="1"/> <spring:message code="common.title.score"/>
											</c:if>
											<c:if test="${item.stareCnt == 0 }">-</c:if>
									</td>
                                    <td data-label="평가여부">
                                    			<c:if test="${item.stareCnt > 0 }">
												<c:if test="${item.rateYn eq 'Y'}"><spring:message code="lecture.title.exam.rateyn_y"/></c:if>
												<c:if test="${item.rateYn ne 'Y'}"><spring:message code="lecture.title.exam.rateyn_n"/></c:if>
											</c:if>
											<c:if test="${item.stareCnt == 0 }">-</c:if>
									</td>
                                    <td data-label="평가/결과">
                                    	<c:choose>
											<c:when test="${vo.examTypeCd eq 'OFF'}">
												<div class="input-group" style="width:120px;">
													<input type='text' name='score' onkeyup="isChkMaxNumber(this,'100')"  onblur="isChkScore(this,100);" style='width:80px;  text-align:right;size=7' maxlength="6" class="input-sm inputNumber">
													<button onclick="addScore(${status.index})"class="btn3 type1 sm"><spring:message code="button.add"/></button>
												</div>
											</c:when>
											<c:when test="${item.stareCnt > 0 && not empty item.endDttm && not empty item.stareAnss}">
												<button onclick="javascript:editRate('${item.stdNo}')"class="btn3 type1"><spring:message code="lecture.title.exam.rate"/></button> 
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>	
                                    </td>
                               	 </tr>
                             </c:forEach>
	                           <c:if test="${empty examStareListVO}">
	                              <tr>
	                              	<td colspan="9">
										<div class="no-list"  ><spring:message code="lecture.message.exam.student.nodata"/></div>
									</td>
	                              </tr>
								</c:if>
                           </tbody>
                        </table>
                   	 </div>
                    </div> 
             	  	<c:if test="${not empty examStareListVO }">
						<div class="txt-right mt20">
							<c:if test="${examVO.regYn eq 'Y' && examVO.examTypeCd eq 'ON'}">
									<a href="javascript:scoreComplete();" class="btn3 type1 mt4 mb4"><spring:message code="button.eval.exam"/></a>
									<a href="javascript:resetStare();" class="btn3 type1 mt4 mb4"><spring:message code="button.reset.exam"/></a>
									<a href="javascript:editRateDan();" class="btn3 type1 mt4 mb4">단답,서술형 평가</a>
							</c:if>
							<c:if test="${examVO.examTypeCd eq 'OFF'}">
								<a href="javascript:addScoreAll();" class="btn btn-primary btn-sm"><spring:message code="button.save.score"/></a>
							</c:if>				
						</div>
					</c:if>
                 <div class="board_pager">
                     <span class="inner">
                         <meditag:paging pageInfo="${pageInfo}" funcName="listStd" name="lect"/>
                     </span>
                 </div>
				<form id="examForm" name="examForm" onsubmit="return false" method="post">
					<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
					<input type="hidden" name="examSn" value="${vo.examSn }" />
					<input type="hidden" name="qstnScores" value="${vo.qstnScores }" />
					<input type="hidden" name="examQstnSn" value="${vo.examQstnSn }" />
					<input type="hidden" name="stareCnt" value="${vo.stareCnt }"/>
					<!-- <input type="hidden" name="totGetScore"/>  온라인 평가관리 오류 조치--> 
					<input type="hidden" name="stdNo"/>
					<input type="hidden" name="userNoObj" id="userNoObj" value="${examStareVO.userNoObj }"/>
					<input type="hidden" name="strStdNo" />
					<input type="hidden" name="strScore" />
				</form>
				
<script type="text/javascript">
	var modalBox = null;
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listStd(1);
			}
		}

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		if('${examVO.examTypeCd}' == 'OFF'){
			$('.online').hide();
		}
		
		//문항 수, 배점 변화에 따른 총점 확인
		$('#selCnt, #selPnt, #shortCnt, #shortPnt, #desCnt, #desPnt').on('change keyup' ,function() {
			getExamQstnTotScore(this);
		});
		
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
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

	function goList() {
		document.location.href = cUrl("/lec/exam/examMain?crsCreCd=${examVO.crsCreCd}");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examForm').attr("action","/lec/exam/"+cmd);
		$('#examForm')[0].submit();
	}


	/*입력한 시간의 유효성을 체크한다.
	*  폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
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

	/**
	*
	*/
	function listStd(page){
		var f = document.StdSearch;
		var searchValue = $("#searchValue").val();

		//var declsNo = $("#declsNo option:selected").val();
		var listScale = $("#listScale option:selected").val();
		var befListScale = "${examVO.listScale}"
		if (listScale != befListScale ) {
			searchValue = $("#userNm").val();
		}			
		
		document.location.href = cUrl("/lec/exam/editFormExamMain")+"?gubun=R${AMPERSAND}examSn=${examVO.examSn}${AMPERSAND}curPage="+page+"${AMPERSAND}listScale="+listScale+"${AMPERSAND}userNm="+searchValue+"${AMPERSAND}crsCreCd=${examVO.crsCreCd}";
	}


	/**
	 * 시험 평가 완료
	 */
	function scoreComplete() {
		var chkstaren = "${chkstaren}";
		if(chkstaren != "Y"){
			alert('<spring:message code="lecture.message.exam.rate.nostaren"/>');
			return;
		}
		if(confirm('<spring:message code="lecture.message.exam.rate.confirm.complete"/>')) {
			process("editExamComplete");	// cmd
		}
	}

	/**
	 * 재시험 설정
	 */
	function resetStare() {
		var userList = "";
		$("input[name='selStd']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
		         userList += this.value;
			}
		});	
		//var userList = $("#StdSearch input[name='selStd']:checked").stringValues();
		if(userList == ""){
			alert("<spring:message code="lecture.message.exam.rate.alert.reset"/>");
			return;
		}
		if(confirm('<spring:message code="lecture.message.exam.rate.confirm.reset"/>')) {
			$('#examForm').find('input[name="userNoObj"]').val(userList);
			$('#examForm').find('input[name="examSn"]').val("${examVO.examSn}");
			$('#examForm').find('input[name="crsCreCd"]').val("${examVO.crsCreCd}");
			process("removeStare");	// cmd
		}
	}

	/**
	* 성적저장
	*/
	function addScoreAll() {
		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");

		var strStdNo = "";
		var strScore = "";
		var overScore = 0;

		for(var i=0; i < ScoreObj.length; i++) {
			//-- 값이 들어온것만 잡는다.
			if(!isEmpty(ScoreObj[i].value)) {
				strStdNo = strStdNo+"|"+StdNoObj[i].value;
				strScore = strScore+"|"+ScoreObj[i].value;
				if(parseInt(ScoreObj[i].value) > 100){
					overScore++;
				}
			}
		}
		strStdNo = strStdNo.substring(1);
		strScore = strScore.substring(1);

		if(isEmpty(strStdNo) || isEmpty(strScore)){
			alert('저장할 수강생이 없습니다.');
			return;
		}
		
		if(strStdNo == ""){
			alert("<spring:message code="lecture.message.common.alert.input.score"/>");
			return;
		}

		if(overScore > 0){
			alert("<spring:message code="lecture.message.common.alert.input.score100"/>");
			return;
		}

		$('#examForm').find('input[name="stareCnt"]').val(1);
		$('#examForm').find('input[name="rateYn"]').val("Y");
		$('#examForm').find('input[name=strStdNo]').val(strStdNo);
		$('#examForm').find('input[name=strScore]').val(strScore);

		process("addStareScoreAll");
		
	}

	/**
	 * 개별 성적 저장
	 */
	function addScore(no) {

		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");
		var scoreVal = parseInt(isNull(ScoreObj[no].value) ? 0 : ScoreObj[no].value);
		
		var stdNo = StdNoObj[no].value;
		if(isNull(ScoreObj[no].value)) {
			alert("<spring:message code="lecture.message.common.alert.input.score"/>");
			ScoreObj[no].focus();
			return;
		}
		if(parseInt(scoreVal) > 100){
			alert("<spring:message code="lecture.message.common.alert.input.score100"/>");
			ScoreObj[no].value = "";
			ScoreObj[no].focus();
			return;
		}
		
		$.getJSON( cUrl("/lec/exam/addStareScore"),		// url
				{ 
				  "crsCreCd" : '${examVO.crsCreCd}',
				  "examSn" : '${examVO.examSn}',
				  "stdNo" : StdNoObj[no].value,
				  "totGetScore" : parseInt(scoreVal),
				  "stareCnt" : 1,
				  "rateYn" : "Y"
				},			// params
				function(data) {
					alert(data.message);
					if(data.result > 0) {
						listStd(1);
					}
				}
			);

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

	/**
	 * 학습자 평가
	 */
	function editRate(stdNo) {
	var addContent  = "<iframe id='examRateFrame' name='examRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/editFormRatePop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&examSn=${examVO.examSn}&stdNo="+stdNo+"'/>"; 
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1400, 850);
		modalBox.setTitle("<spring:message code="lecture.title.exam.rate"/>");
		modalBox.show();
	}

	/**
	 * 학습자 평가
	 */
	function editRateDan() {
		var addContent  = "<iframe id='examRateFrame' name='examRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/editRateDanPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		parent.modalBox.resize(1430, 640);
		parent.modalBox.setTitle("단답,서술형 평가");
		modalBox.show();
	}
	
</script>
