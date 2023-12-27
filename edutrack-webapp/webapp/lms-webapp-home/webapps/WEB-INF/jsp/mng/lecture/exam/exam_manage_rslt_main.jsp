<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="examVO" value="${vo}" />
<c:set var="defaultTab" value="0"/>
<c:if test="${examVO.examTypeCd eq 'OFF'}"><c:set var="defaultTab" value="1"/></c:if>
<c:if test="${examVO.regYn eq 'Y'}"><c:set var="defaultTab" value="1"/></c:if>

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
	<li><a href="javascript:examRateForm()"><spring:message code="lecture.title.exam.manage.result.tab"/></a></li>
	<li class="active"><a href="#"><spring:message code="lecture.title.exam.manage.status.tab"/></a></li>
</ul>
<div style=";border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
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

	/**
	 * 목록 이동
	 */
	function goList() {
		document.location.href = cUrl("/mng/lecture/exam/examMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}semiExamYn=${examVO.semiExamYn}";
	}

	//-- Question Management
	function examQstnForm() {
		document.location.href = cUrl("/mng/lecture/exam/manageFormExamQstnMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
	}

	function examRateForm() {
		document.location.href = cUrl("/mng/lecture/exam/manageFormExamRateMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
	}
</script>
