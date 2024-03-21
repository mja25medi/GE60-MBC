<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>


<!-- <c:set var="gubun" value="${gubun}"/>  -->
<c:set var="assignmentVO" value="${vo}" />
	<br/>
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
	<input type="hidden" name="sendCnt" value="${vo.sendCnt }" />
	</form>
	<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.name"/></th>
				<td colspan="3" class="wordbreak">
					${vo.asmtTitle}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.type"/></th>
				<td><meditag:codename code="${vo.asmtTypeCd}" category="ASMT_TYPE_CD"/></td>
				<th scope="row"><spring:message code="lecture.title.assignment.seltype"/></th>
				<td><meditag:codename code="${vo.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.duration"/></th>
				<td colspan="3">
				<c:choose>
					<c:when test="${vo.asmtStareTypeCd eq 'S'}">
						-
					</c:when>
					<c:otherwise>
						${vo.asmtStartDttm} ${vo.asmtStartHour}:${vo.asmtStartMin} ~ ${vo.asmtEndDttm} ${vo.asmtEndHour}:${vo.asmtEndMin}
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.delaydate"/></th>
				<td>
					<c:choose>
					<c:when test="${vo.asmtStareTypeCd eq 'S'}">
						-
					</c:when>
					<c:otherwise>
							${vo.extSendDttm} ${vo.extSendHour}:${vo.extSendMin}
					</c:otherwise>
				</c:choose>
				</td>
				<th scope="row"><spring:message code="lecture.title.assignment.send.cnt"/></th>
				<td>${vo.asmtLimitCnt} <spring:message code="common.title.times.postfix"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="common.title.atachfile"/></th>
				<td colspan="3" class="wordbreak">
					<c:forEach var="file" items="${vo.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="common.title.cnts"/></th>
				<td colspan="3" class="wordbreak">${fn:replace(vo.asmtCts,crlf,"<br/>")}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.regyn"/></th>
				<td colspan="3" class="wordbreak"><meditag:codename code="${vo.regYn}" category="REG_YN"/></td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
		<c:if test="${vo.regYn ne 'Y'}">
		<a href="javascript:registerAsmt();" class="btn btn-info btn-sm"><spring:message code="button.ok.regist"/></a>
		</c:if>
		<c:if test="${vo.regYn eq 'Y'}">
		<a href="javascript:registerCancelAsmt();" class="btn btn-warning btn-sm"><spring:message code="button.cancel.regist"/></a>
		</c:if>
		<a href="javascript:goList();" class="btn btn-default btn-sm"><spring:message code="button.list"/></a>
	</div>
	<ul class="nav nav-tabs" id="tabMenuExam">
		<li class="active"><a href="#"><spring:message code="lecture.title.assignment.manage.question.tab"/></a></li>
		<li><a href="javascript:rateAsmtForm()"><spring:message code="lecture.title.assignment.manage.rate.tab"/></a></li>
		<li><a href="javascript:rsltAsmtForm()"><spring:message code="lecture.title.assignment.manage.rslt.tab"/></a></li>
	</ul>
	<div style="border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
		<form name="SearchSub" id="SearchSub" action="javascript:listSub()">
		<div class="row">
			<div class="col-lg-12">
				<div class="text-right">
					<c:if test="${vo.asmtSvcCd eq 'CODE'}">
						<a href="javascript:writeAIQstn();" class="btn btn-primary btn-sm">AI<spring:message code="button.write.question"/></a>
					</c:if>
					<a href="javascript:writeQstn();" class="btn btn-primary btn-sm"><spring:message code="button.write.question"/></a>
				</div>
			</div>
		</div>
		<div id="questionList" style="margin-top:5px;">
			<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:50px;"/>
					<col style="width:auto"/>
					<col style="width:50px;"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.question"/></th>
						<th scope="col"><spring:message code="common.title.manage"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="3"><spring:message code="lecture.message.assignment.question.nodata"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
	</div>

<script type="text/javascript">
	var ItemDTO = new Object();
	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {
		listQstn();
	});

	//-- 과제 목록
	function goList() {
		document.location.href = cUrl("/mng/lecture/assignment/asmtMain")+"?crsCreCd=${vo.crsCreCd}";
	}

	//-- 과제 평가
	function rateAsmtForm() {
		document.location.href = cUrl("/mng/lecture/assignment/manageRateMain")+"?crsCreCd=${vo.crsCreCd}${AMPERSAND}asmtSn=${vo.asmtSn}";
	}

	//-- Result Status
	function rsltAsmtForm() {
		document.location.href = cUrl("/mng/lecture/assignment/manageRsltMain")+"?crsCreCd=${vo.crsCreCd}${AMPERSAND}asmtSn=${vo.asmtSn}";
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#assignmentForm').attr("action","/mng/lecture/assignment/" + cmd);
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.reload();
		} else {
			// 비정상 처리
		}
	}


	/**
	* resize
	*/
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame"); //-- $('#subWorkFrame', parent);
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 과제 후보 목록 조회
	 */
	function listQstn() {
		$("#asmtListArea").show();
		$("#questionList")
			.load(
				cUrl("/mng/lecture/assignment/listQstn"),		// url
				{
				  	"crsCreCd" : '${vo.crsCreCd}',
				  	"asmtSn" : '${vo.asmtSn}',
					  	"regYn" : '${vo.regYn}'
				},			// params
				function() {
					parentResize();
				}
			);
	}

	/**
	 * 과제 문제 등록 폼 호출
	 */
	function writeQstn() {

		if('${vo.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.write"/>");
			return;
		}
		var addContent  = "<iframe id='addSubFrame' name='addSubFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/assignment/addQstnPop"/>"
			+ "?crsCreCd=${vo.crsCreCd}&amp;asmtSn=${vo.asmtSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(600, 800);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.question.manage"/>");
		parent.modalBox.show();
	}

	/**
	 * 과제 문제 수정 폼 호출
	 */
	function editQstn(asmtSubSn) {
		if('${assignmentVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.edit"/>");
			return;
		}
		var addContent  = "<iframe id='addSubFrame' name='addSubFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/assignment/editQstnPop"/>"
			+ "?crsCreCd=${vo.crsCreCd}&amp;asmtSn=${vo.asmtSn}&amp;asmtSubSn="+asmtSubSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(600, 800);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.question.manage"/>");
		parent.modalBox.show();
	}

	/**
	 * 문제 은행 보기 폼
	 */
	function qstnBankWrite() {
		if('${assignmentVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.write"/>");
			return;
		}
		var addContent  = "<iframe id='addSubFrame' name='addSubFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/assignment/addQbankForm"/>"
			+ "?crsCreCd=${vo.crsCreCd}&amp;asmtSn=${vo.asmtSn}'/>";
		parent.asmtPopBox.clear();
		parent.asmtPopBox.addContents(addContent);
		parent.asmtPopBox.resize(1000, 700);
		parent.asmtPopBox.setTitle("<spring:message code="lecture.title.assignment.qbank"/>");
		parent.asmtPopBox.show();
	}

	/**
	 * 과제 문제 미리보기
	 */
	function viewQstn(asmtSubSn) {

		var addContent  = "<iframe id='addSubFrame' name='addSubFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/assignment/viewQstnPop"/>"
			+ "?crsCreCd=${vo.crsCreCd}&amp;asmtSn=${vo.asmtSn}&amp;asmtSubSn="+asmtSubSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.question.manage"/>");
		parent.modalBox.show();
	}
	
	/**
	 * AI 과제 문제 등록 폼 호출
	 */
	function writeAIQstn() {

		if('${vo.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.write"/>");
			return;
		}
		var addContent  = "<iframe id='addSubFrame' name='addSubFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/assignment/addAIQstnPop"/>"
			+ "?crsCreCd=${vo.crsCreCd}&amp;asmtSn=${vo.asmtSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1200, 700);
		parent.modalBox.setTitle("AI<spring:message code="lecture.title.assignment.question.manage"/>");
		parent.modalBox.show();
	}

</script>
