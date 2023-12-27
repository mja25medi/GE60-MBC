<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="examVO" value="${vo}" />
<c:set var="examStareVO" value="${vo.examStareVO}" />

<c:set var="defaultTab" value="0"/>
<c:if test="${examVO.examTypeCd eq 'OFF'}"><c:set var="defaultTab" value="1"/></c:if>
<c:if test="${examVO.regYn eq 'Y'}"><c:set var="defaultTab" value="1"/></c:if>


<br/>
<table summary="<spring:message code="lecture.title.exam.manage"/>" class="table table-bordered wordbreak">
	<tr height="35">
		<th class="top" scope="row" style="width:20%"><spring:message code="lecture.title.exam.examtype"/></th>
		<td class="top" style="width:30%">
			<meditag:codename code="${examVO.examTypeCd}" category="EXAM_TYPE_CD"/>
		</td>
		<th class="top" scope="row" style="width:20%"><spring:message code="lecture.title.exam.ansrtype"/></th>
		<td class="top" style="width:30%">
			<meditag:codename code="${examVO.examStareTypeCd}" category="EXAM_STARE_TYPE_CD"/>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.name"/></th>
		<td colspan="3">
			${examVO.examTitle}
		</td>
	</tr>
<c:if test="${examVO.examTypeCd eq 'ON'}">
	<c:if test="${examVO.examStareTypeCd eq 'S'}">
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.answer.ratio"/></th>
		<td style="width:30%">
			${examVO.stareCritPrgrRatio} % <spring:message code="common.title.over"/>
		</td>
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.limitcnt"/></th>
		<td style="width:30%">
			${examVO.stareLimitCnt} <spring:message code="common.title.times.postfix"/>
		</td>
	</tr>
	</c:if>
	<c:if test="${examVO.examStareTypeCd eq 'R'}">
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.duration"/></th>
		<td colspan="3">
			<meditag:dateformat type="1" delimeter="." property="${examVO.examStartDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}"/>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.result.date"/></th>
		<td>
			<meditag:dateformat type="1" delimeter="." property="${examVO.rsltCfrmDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.rsltCfrmDttm}"/>
		</td>
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.limitcnt"/></th>
		<td style="width:30%">
			${examVO.stareLimitCnt} <spring:message code="common.title.times.postfix"/>
		</td>
	</tr>
	</c:if>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.timelimit"/></th>
		<td>
			<c:choose>
				<c:when test="${examVO.examStareTm > 0}">
					${examVO.examStareTm} <spring:message code="common.title.min"/>
				</c:when>
				<c:otherwise>
					<spring:message code="common.title.status.useyn_n"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%">시험 출제 문항수/배점</th>
		<td>
			<c:if test="${not empty examVO.selCnt && examVO.selCnt > 0 }">
				선택형  : ${examVO.selCnt }개 / ${examVO.selPnt } 점
			</c:if>
			<c:if test="${not empty examVO.shortCnt && examVO.shortCnt > 0 }">
				<br>단답형  : ${examVO.shortCnt }개 / ${examVO.shortPnt } 점
			</c:if>
			<c:if test="${not empty examVO.desCnt && examVO.desCnt > 0 }">
				<br>서술형 : ${examVO.desCnt }개 / ${examVO.desPnt } 점
			</c:if>
		</td>
	</tr>
</c:if>
<c:if test="${examVO.examTypeCd ne 'ON'}">
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.duration"/></th>
		<td colspan="3">
			<meditag:dateformat type="1" delimeter="." property="${examVO.examStartDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}"/>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.result.date"/></th>
		<td colspan="3">
			<meditag:dateformat type="1" delimeter="." property="${examVO.rsltCfrmDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.rsltCfrmDttm}"/>
		</td>
	</tr>
</c:if>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.regyn"/></th>
		<td colspan="3">
			<c:set var="regYn" value="${examVO.regYn}"/>
			<c:if test="${empty examVO.regYn}"><c:set var="regYn" value="N"/></c:if>
			<meditag:codename code="${regYn}" category="REG_YN"/>
		</td>
	</tr>
	<tr>
		<th scope="row"><spring:message code="lecture.title.exam.desc"/></th>
		<td colspan="3" class="wordbreak">
			${fn:replace(examVO.examCts,crlf,"<br/>")}
		</td>
	</tr>
</table>
<div class="text-right" style="margin-top:10px;">
	<button class="btn btn-default btn-sm" onclick="goList()" ><spring:message code="button.list"/></button>
</div>
<ul class="nav nav-tabs" id="tabMenuExam">
	<c:if test="${examVO.examTypeCd eq 'ON'}" >
	<li><a href="javascript:examQstnForm()"><spring:message code="lecture.title.exam.manage.question.tab"/></a></li>
	</c:if>
	<li class="active"><a href="#"><spring:message code="lecture.title.exam.manage.result.tab"/></a></li>
	<li><a href="javascript:examRsltForm()"><spring:message code="lecture.title.exam.manage.status.tab"/></a></li>
</ul>
<div style=";border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
	<form name="StdSearch" id="StdSearch" onsubmit="return false">
	<div class="row" style="margin-bottom:5px;">
		<div class="col-lg-12">
			<%-- <div class="input-group" style="float:left">
				<select name="declsNo" id="declsNo" onchange="listStudent(1)" class="form-control input-sm">
					<option value=""><spring:message code="course.title.decls.all"/></option>
					<c:forEach var="decls" items="${creCrsDeclsList}">
					<option value="${decls.declsNo}">${decls.declsNo}</option>
					</c:forEach>
				</select>
			</div> --%>
			<div class="input-group" style="float:left">
				<select name="stareYn" id="stareYn" onchange="listStudent(1)" class="form-control input-sm">
					<option value="A"><spring:message code="common.title.all"/></option>
					<option value="Y"><spring:message code="lecture.title.exam.starey"/></option>
					<option value="N"><spring:message code="lecture.title.exam.staren"/></option>
				</select>
			</div>
			<div class="input-group" style="float:left;width:140px;">
				<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" placeholder="<spring:message code="user.title.userinfo.name"/>"/>
				<span class="input-group-addon" onclick="javascript:listStudent(1)" style="cursor:pointer" >
					<i class="fa fa-search"></i>
				</span>
			</div>
			<div style="float:right;margin-left:5px;">
				<select name="listScale" id="listScale" onchange="listStudent(1)" class="form-control input-sm">
					<option value="10">10</option>
					<option value="20" selected="selected">20</option>
					<option value="40">40</option>
					<option value="60">60</option>
					<option value="80">80</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
			</div>
			<div style="float:right;">
				<c:if test="${MSG_SMS eq 'Y' }">
				<button class="btn btn-info btn-sm" onclick="messageForm('SMS');" ><spring:message code="button.sms"/></button>
				</c:if>
				<c:if test="${MSG_EMAIL eq 'Y' }">
				<button class="btn btn-info btn-sm" onclick="messageForm('EMAIL');" ><spring:message code="button.email"/></button>
				</c:if>
				<c:if test="${MSG_NOTE eq 'Y' }">
				<button class="btn btn-info btn-sm" onclick="messageForm('MSG');" ><spring:message code="button.note"/></button>
				</c:if>
				<c:if test="${examVO.regYn eq 'Y' && examVO.examTypeCd eq 'ON'}">
				<button class="btn btn-primary btn-sm" onclick="scoreComplete();" ><spring:message code="button.eval.exam"/></button>
				<button class="btn btn-primary btn-sm" onclick="resetStare();" ><spring:message code="button.reset.exam"/></button>
						<button class="btn btn-primary btn-sm" onclick="editRateDan();">단답,서술형 평가</button>
					</c:if>
				<c:if test="${examVO.examTypeCd eq 'OFF'}">
				<button class="btn btn-primary btn-sm" onclick="addScoreAll();" ><spring:message code="button.save.score"/></button>
				</c:if>
			</div>
		</div>
	</div>
	<div id="studentList">
		<table summary="<spring:message code="lecture.title.exam.rate.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:30px;"/>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<c:if test="${vo.examTypeCd eq 'ON'}"><col style="width:auto;"/></c:if>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<c:if test="${examVO.examTypeCd eq 'ON'}"><col style="width:auto;"/></c:if>
				<col style="width:80px;"/>
				<col style="width:80px;"/>
				<col style="width:80px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<c:if test="${vo.examTypeCd eq 'ON'}"><th scope="col"><spring:message code="lecture.title.exam.agreeyn"/></th></c:if>
					<th scope="col"><spring:message code="lecture.title.exam.stareyn"/></th>
					<c:if test="${examVO.examTypeCd eq 'ON'}"><th scope="col"><spring:message code="lecture.title.exam.staredate"/></th></c:if>
					<th scope="col"><spring:message code="lecture.title.exam.score"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.rateyn"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.rate"/>/<spring:message code="lecture.title.exam.result"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="9"><spring:message code="lecture.message.exam.student.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
</div>
<form id="examForm" name="examForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<input type="hidden" name="qstnScores" value="${vo.qstnScores }" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn }" />
	<input type="hidden" name="stareCnt" value="${vo.stareCnt }"/>
	<input type="hidden" name="userNoObj" id="userNoObj" value="${examStareVO.userNoObj }"/>
</form>
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listStudent(1);
			}
		}
		listStudent(1);
	});

	/**
	* resize
	*/
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	*목록 이동
	*/
	function goList() {
		document.location.href = cUrl("/mng/lecture/exam/examMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}semiExamYn=${examVO.semiExamYn}";
	}

	//-- 문항 관리
	function examQstnForm() {
		document.location.href = cUrl("/mng/lecture/exam/manageFormExamQstnMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
	}

	//-- 결과 통계
	function examRsltForm() {
		document.location.href = cUrl("/mng/lecture/exam/manageFormExamRsltMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
	}

	function listStudent(page) {
		ItemDTO.curPage = page;
		var stareYn = $("#stareYn option:selected").val();
		var userNm = $("#searchKey").val();
		var listScale = $("#listScale option:selected").val();
		var declsNo = $("#declsNo option:selected").val();
		$("#studentList")
			.load(
				cUrl("/mng/lecture/exam/listStare"),
				{
					"crsCreCd" : '${examVO.crsCreCd}',
					"examSn" : '${examVO.examSn}',
					"userNm" : userNm,
					"stareYn" : stareYn,
					"declsNo" : declsNo,
					"curPage" : page,
			  		"listScale" : listScale
				},
				function(){
					parentResize();
				}
		);
	}

	function checkAll() {
	    var state=$('input[name=stdChkAll]:checked').size();
	    if(state==1){
	    	$("#StdSearch input[name='selStd']").prop("checked",true);
	    }else{
	    	$("#StdSearch input[name='selStd']").prop("checked",false);
	    }
	}


	/**
	 * 학습자 평가
	 */
	function editRate(stdNo) {
		var addContent  = "<iframe id='addRateFrame' name='addRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/exam/editFormRatePop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}&amp;stdNo="+stdNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.rate.manage"/>");
		parent.modalBox.show();
	}

	/**
	 * 주관식/단답식 평가
	 */
	function editRateDan(stdNo) {
		var addContent  = "<iframe id='addRateFrame' name='addRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/editRateDanPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(900, 650);
		parent.modalBox.setTitle("단답,서술형 평가");
		parent.modalBox.show();
	}

	/**
	 * 시험 평가 완료
	 */
	function scoreComplete() {
		var connYn = "${examVO.connYn}";
		if(connYn == 'Y'){
			//alert('<spring:message code="lecture.message.evaluation.complete.warning"/>');
			//return;
		}
		if(confirm('<spring:message code="lecture.message.exam.rate.confirm.complete"/>')) {
			$.getJSON( cUrl("/mng/lecture/exam/editExamComplete"),		// url
					{ 
					  "crsCreCd" : '${examVO.crsCreCd}',
					  "examSn" : '${examVO.examSn}'
					},			// params
					processAddScoreCallback				// callback function
				);
		}
	}

	/**
	 * 재시험 설정
	 */
	function resetStare() {
		if(confirm('<spring:message code="lecture.message.exam.rate.confirm.reset"/>')) {
			var userList = $("#StdSearch input[name='selStd']:checked").stringValues();
			if(userList == ""){
				alert("<spring:message code="lecture.message.exam.rate.alert.reset"/>");
			}else{
				$('#userNoObj').val(userList);
				process("removeStare");	// cmd
			}
		} else return;
	}

	//성적 저장
	function addScoreAll() {
		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");

		var strStdNo = "";
		var strScore = "";

		for(var i=0; i < ScoreObj.length; i++) {
			if(!isEmpty(ScoreObj[i].value)) {
				strStdNo = strStdNo+"|"+StdNoObj[i].value;
				strScore = strScore+"|"+ScoreObj[i].value;
			}
		}
		
		strStdNo = strStdNo.substring(1);
		strScore = strScore.substring(1);
		
		if(isEmpty(strStdNo) || isEmpty(strScore)){
			alert('저장할 수강생이 없습니다.');
			return;
		}

		$.ajax({
			url : '/mng/lecture/exam/addStareScoreAll',
			data : {
				"crsCreCd" : '${examVO.crsCreCd}',
				  "examSn" : '${examVO.examSn}',
				  "stareCnt" : 1,
				  "strStdNo" : strStdNo,
				  "strScore" : strScore,
				  "rateYn" : "Y"
			}
			,method : "post"
			,success : function(data) {
				processAddScoreCallback(data);
			}
			,error : function(request, status, error) {
				alert("오류가 발생했습니다. " + error);
			}
		});
	}

	/**
	 * 개별 성적 저장
	 */
	function addScore(no) {
		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");
		var scoreVal = parseInt(isNull(ScoreObj[no].value) ? 0 : ScoreObj[no].value); 
		
		if(isNull(ScoreObj[no].value)){
			alert("빈 값을 입력할 수 없습니다.");
			ScoreObj[no].value = "";
			ScoreObj[no].focus();
			return;
		}
		
		if(parseInt(scoreVal) > 100){
			alert("<spring:message code="lecture.message.common.alert.input.score100"/>");
			ScoreObj[no].value = "";
			ScoreObj[no].focus();
			return;
		}
		$.getJSON( cUrl("/mng/lecture/exam/addStareScore"),		// url
				{ 
				  "crsCreCd" : '${examVO.crsCreCd}',
				  "examSn" : '${examVO.examSn}',
				  "stdNo" : StdNoObj[no].value,
				  "totGetScore" : parseInt(scoreVal),
				  "stareCnt" : 1,
				  "rateYn" : "Y"
				},			// params
				processAddScoreCallback				// callback function
			);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processAddScoreCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}

	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var stdNoList = $("#StdSearch input[name='selStd']:checked").stringValues();
		if(stdNoList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/log/message/addMessagePop"/>"
			+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.stdNoList="+stdNoList+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle(getMessageTitle(msgDivCd));
		parent.modalBox.show();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examForm').attr("action","/mng/lecture/exam/" + cmd);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.href = cUrl("/mng/lecture/exam/manageFormExamRateMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
		} else {
			// 비정상 처리
		}
	}
</script>
