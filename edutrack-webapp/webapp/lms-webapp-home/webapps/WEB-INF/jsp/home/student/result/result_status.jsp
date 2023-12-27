<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:class_head>

<mhtml:class_body>
				<mhtml:class_location />

				<div class="row">
					<div class="col-md-4">
						<div class="well">
							<ul style="list-style-type:none;padding-left:20px;">
								<c:if test="${createCourseVO.prgrRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.prgr"/> :</strong>${createCourseVO.prgrRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.attdRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.attd"/> :</strong>${createCourseVO.attdRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.examRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.exam"/> :</strong>${createCourseVO.examRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.asmtRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.asmt"/> :</strong>${createCourseVO.asmtRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.forumRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.forum"/> :</strong>${createCourseVO.forumRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.prjtRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.project"/> :</strong>${createCourseVO.prjtRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.joinRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.join"/> :</strong>${createCourseVO.joinRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.etcRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.etc"/> :</strong>${createCourseVO.etcRatio}%</li>
								</c:if>
							</ul>
						</div>
					</div>
					<div class="col-md-8">
						<div class="well">
							<ul style="list-style-type:none;padding-left:20px;">
								<li><spring:message code="student.message.result.msg1"/></li>
								<li><spring:message code="student.message.result.msg2"/></li>
								<li><spring:message code="student.message.result.msg3"/></li>
								<li><spring:message code="student.message.result.msg5"/></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="row" style="margin-bottom:10px;">
					<div class="col-md-12">
						<ul class="nav nav-tabs">
							<li><a href="<c:url value="/lec/eduResult/listResultMain"/>"><spring:message code="student.title.result.manage.score.tab"/></a></li>
							<li class="active"><a href="#"><spring:message code="student.title.result.manage.status.tab"/></a></li>
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
	var Item = new Object();
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

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
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

</script>
</mhtml:class_body>
</mhtml:class_html>
