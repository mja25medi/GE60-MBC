<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="assignmentSendVO" value="${vo.assignmentSendVO}" />
	<br/>
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
	<input type="hidden" name="sendCnt" value="${vo.sendCnt }" />
	<input type="hidden" name="assignmentSendVO.userNoObj" id="userNoObj" value="${assignmentSendVO.userNoObj }"/>
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
					${assignmentVO.asmtTitle}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.type"/></th>
				<td><meditag:codename code="${assignmentVO.asmtTypeCd}" category="ASMT_TYPE_CD"/></td>
				<th scope="row"><spring:message code="lecture.title.assignment.seltype"/></th>
				<td><meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.duration"/></th>
				<td colspan="3">${assignmentVO.asmtStartDttm} ${assignmentVO.asmtStartHour}:${assignmentVO.asmtStartMin} ~ ${assignmentVO.asmtEndDttm} ${assignmentVO.asmtEndHour}:${assignmentVO.asmtEndMin}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.delaydate"/></th>
				<td>${assignmentVO.extSendDttm} ${assignmentVO.extSendHour}:${assignmentVO.extSendMin}</td>
				<th scope="row"><spring:message code="lecture.title.assignment.send.cnt"/></th>
				<td>${assignmentVO.asmtLimitCnt} <spring:message code="common.title.times.postfix"/></td>
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
				<th scope="row"><spring:message code="lecture.title.assignment.regyn"/></th>
				<td colspan="3" class="wordbreak"><meditag:codename code="${assignmentVO.regYn}" category="REG_YN"/></td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
		<%-- <c:if test="${assignmentVO.regYn ne 'Y'}">
		<a href="javascript:registerAsmt()" class="btn btn-info btn-sm"><spring:message code="button.ok.regist"/> </a>
		</c:if>
		<c:if test="${assignmentVO.regYn eq 'Y'}">
		<a href="javascript:registerCancelAsmt()" class="btn btn-warning btn-sm"><spring:message code="button.cancel.regist"/> </a>
		</c:if> --%>
		<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
	</div>
	<ul class="nav nav-tabs" id="tabMenuExam">
		<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}" >
		<li><a href="javascript:qstnAsmtForm()"><spring:message code="lecture.title.assignment.manage.question.tab"/></a></li>
		</c:if>
		<li><a href="javascript:rateAsmtForm()"><spring:message code="lecture.title.assignment.manage.rate.tab"/></a></li>
		<li class="active"><a href="#"><spring:message code="lecture.title.assignment.manage.rslt.tab"/></a></li>
	</ul>
	<div style="border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
		<div class="row" style="margin-top:5px; margin-bottom:20px;">
			<div class="col-md-12">
				<div class="flot-chart">
					<div class="flot-chart-content" id="flot-line-chart-multi"></div>
				</div>
			</div>
		</div>
	</div>

	<meditag:js src="/tpl/bootstrap/bower_components/flot/excanvas.min.js"/>
	<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.js"/>
	<meditag:js src="/tpl/bootstrap/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"/>

<script type="text/javascript">
	var ItemDTO = new Object();

	$(document).ready(function() {
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
		parentResize();
	});

	/**
	* resize
	*/
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

		//-- 과제 목록
	function goList() {
		document.location.href = cUrl("/mng/lecture/assignment/asmtMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}

	//-- Question Management
	function qstnAsmtForm() {
		document.location.href = cUrl("/mng/lecture/assignment/manageQstnMain")+"?crsCreCd=${assignmentVO.crsCreCd}${AMPERSAND}asmtSn=${assignmentVO.asmtSn}";
	}

	//-- Result Statics
	function rateAsmtForm() {
		document.location.href = cUrl("/mng/lecture/assignment/manageRateMain")+"?crsCreCd=${assignmentVO.crsCreCd}${AMPERSAND}asmtSn=${assignmentVO.asmtSn}";
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
			//listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과제 등록 완료
	 */
	function registerAsmt() {
		<c:if test="${empty assignmentSibListVO}">
		alert("<spring:message code="lecture.message.assignment.question.nodata"/>");
		return;
		</c:if>
		process("registerAsmt");	// cmd
	}

	/**
	 * 과제 등록 완료 취소
	 */
	function registerCancelAsmt() {
		if(parseInt(document.assignmentForm["sendCnt"].value,10) > 0) {
			alert("<spring:message code="lecture.message.assignment.alert.cancelregist"/>");
			return;
		}
		process("registerCancelAsmt");	// cmd
	}
</script>
