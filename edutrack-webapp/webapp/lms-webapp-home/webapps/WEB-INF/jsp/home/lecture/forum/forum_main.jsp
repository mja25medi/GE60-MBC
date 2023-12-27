<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="forumList" value="${forumList}"/>
<c:set var="subjectList" value="${subjectList}"/>
<c:set var="forumVO" value="${vo}"/>
<c:set var="forumCntChk" value="0"/>
<c:set var="forumStartNo" value="0"/>
<c:set var="avgScore" value="0"/>

				<div class="row">
					<div class="col-lg-12">
						<form id="forumForm" name="forumForm" onsubmit="return false" >
						<input type="hidden" name="sbjCd" value="${vo.sbjCd}" />
						<ul class="list-group" style="padding-top: 10px;">
							<c:forEach var="item" items="${forumList}" varStatus="status">
							<li class="list-group-item" style="clear: both;">
								<div class="media wordbreak" style="min-height:55px;">
									<div class="media-body" style="float: left;width: 90%;">
										<a href="javascript: forumInfo('${item.forumSn}' )">
											<h4 class="media-heading" title="${item.forumTitle }">
												<%-- ${fn:substring(item.forumTitle,0, 30)}<c:if test="${fn:length(item.forumTitle) > 30 }">...</c:if> --%>
												${status.count}. ${item.forumTitle }
											</h4>
										</a>
										<ul class="list-inline">
											<li class="mr20"><spring:message code="lecture.title.forum.duration"/> : <meditag:dateformat type="8" delimeter="." property="${item.forumStartDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${item.forumEndDttm}"/></li>
											<li class="mr20"><spring:message code="lecture.title.forum.atclcnt"/> : ${item.atclCnt}</li>
											<li>
												<spring:message code="lecture.title.forum.period.after.write"/> :
												<c:if test="${item.periodAfterWriteYn eq 'Y'}">
													<spring:message code="lecture.title.forum.writeY"/>
												</c:if>
												<c:if test="${item.periodAfterWriteYn ne 'Y'}">
													<spring:message code="lecture.title.forum.writeN"/>
												</c:if>
											</li>
										</ul>
									</div>
									<div style="padding-left: 10px;display: table-cell;vertical-align: middle;float: right;">
										<a href="<c:url value="/lec/forum/joinForumMain"/>?forumSn=${item.forumSn}" class="btn btn-primary btn-sm"><spring:message code="button.enter"/></a>
									</div>
								</div>
								<div style="clear: both;"></div>
								</li>
							</c:forEach>
							<c:if test="${empty forumList}">
							<li class="list-group-item"><spring:message code="lecture.message.forum.nodata"/></li>
							</c:if>
						</ul>
						</form>
					</div>
				</div>
				<div class="row" style="margin-top:5px; margin-bottom:20px;">
					<div class="col-md-6 col-sm-6">
						<div class="flot-chart">
							<div class="flot-chart-content" id="flot-bar-chart-multi"></div>
						</div>
					</div>

					<div class="col-md-6 col-sm-6 text-right">
						<div class="flot-chart">
							<div class="flot-chart-content text-left" id="flot-line-chart-multi"></div>
						</div>
					</div>
				</div>

				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.orderBars.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.categories.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/excanvas.min.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"/>

<c:set var="myScoreCnt" value="0"/>
<c:if test="${myScore == 0 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone00}"/></c:if>
<c:if test="${myScore == 10 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone10}"/></c:if>
<c:if test="${myScore == 20 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone20}"/></c:if>
<c:if test="${myScore == 30 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone30}"/></c:if>
<c:if test="${myScore == 40 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone40}"/></c:if>
<c:if test="${myScore == 50 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone50}"/></c:if>
<c:if test="${myScore == 60 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone60}"/></c:if>
<c:if test="${myScore == 70 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone70}"/></c:if>
<c:if test="${myScore == 80 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone80}"/></c:if>
<c:if test="${myScore == 90 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone90}"/></c:if>
<c:if test="${myScore == 100 }"><c:set var="myScoreCnt" value="${forumStatusVO.scoreZone100}"/></c:if>
<style type="text/css">
#flot-line-chart-multi:after {
    content: '(<spring:message code="common.title.cnt.user"/>)';
    position: absolute;
    top: -4%;
    left: -10px;
    font-weight: 600;
    float: left;
}
#flot-bar-chart-multi:after {
	content: '(<spring:message code="lecture.title.forum.no"/>)';
    position: absolute;
    top: -4%;
    left: -10px;
    font-weight: 600;
    float: left;
}
<c:choose>
	<c:when test="${fn:length(forumList) >= 10 }">
		.flot-chart{height: 500px;}
		.flot-chart-content{height: 500px;}
	</c:when>
	<c:when test="${fn:length(forumList) >= 5 }">
		.flot-chart-content{height: 400px;}
	</c:when>
	<c:otherwise>
		.flot-chart-content{height: 300px;}
	</c:otherwise>
</c:choose>
</style>
<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	var scores = [];

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		var data = [{
			label: "Number of Students",
			data : [
				[0, ${forumStatusVO.scoreZone00}], [10,${forumStatusVO.scoreZone10}], [20,${forumStatusVO.scoreZone20}],
            	[30,${forumStatusVO.scoreZone30}], [40,${forumStatusVO.scoreZone40}], [50,${forumStatusVO.scoreZone50}],
            	[60,${forumStatusVO.scoreZone60}], [70,${forumStatusVO.scoreZone70}], [80,${forumStatusVO.scoreZone80}],
            	[90,${forumStatusVO.scoreZone90}], [100,${forumStatusVO.scoreZone100}]
			],
			lines: { show:true },
			points: { show:true }
		},{
			label: "Myscore",
			data: [[${myScore},${myScoreCnt}]],
			bars: { show: true, align: "center" }
		}];
		var position = 'right';
		var options = {
	        xaxes: [{
	        	tickDecimals: 0,
				tickSize: 10,
	        }],
	        yaxes: [{
	        	tickDecimals: 0,
	            min: 0
	        }, {
	            alignTicksWithAxis: position == "right" ? 1 : null,
	            position: position
	        }],
	        legend: {
	            position: 'ne'
	        },
	        grid: {
	            hoverable: true //IMPORTANT! this is needed for tooltip to work
	        },
	        tooltip: true,
	        tooltipOpts: {
	            content: "Number of students : %y",
	            onHover: function(flotItem, $tooltipEl) {
	                // console.log(flotItem, $tooltipEl);
	            }
	        }
		};
		var plot = $.plot($("#flot-line-chart-multi"), data, options);

		var ticksString = "[";
		var avgData = "[";
		var myData = "[";
<c:forEach var="item" items="${forumList}" varStatus="status">
	<c:if test="${forumCntChk eq '0'}">
		<c:set var="forumCntChk" value="1"/>
	</c:if>
	<c:if test="${forumCntChk eq '1'}">
		<c:set var="forumStartNo" value="${status.count}"/>
	</c:if>
	<c:if test="${status.count ne '1'}">
		ticksString += ",";
		avgData += ",";
		myData += ",";
	</c:if>
	<c:set var="avgScore" value="0"/>
	<c:set var="myScore" value="0"/>
	<c:if test="${item.rsltYn eq 'Y' }">
		<c:set var="avgScore" value="${item.avgScore}"/>
		<c:set var="myScore" value="${item.myScore}"/>
	</c:if>
		avgData += "[${avgScore},${status.count}]";
		myData += "[${myScore},${status.count}]";
		ticksString += "[${status.count},'${status.count}']";
	<c:set var="forumCntChk" value="${forumCntChk + 1}"/>
</c:forEach>
<c:if test="${empty forumList}">
		avgData += "[0,0]";
		myData += "[0,0]";
		ticksString += "[0,'0']";
</c:if>
		avgData += "]";
		myData += "]";
		ticksString += "]";

		//console.log("ticksString : " + ticksString);
		//console.log("avgData : " + avgData);
		//console.log("myData : " + myData);
		var barDataString = "[ {label:'Score of Students',data:"+avgData+", bars: {order: 2} }, {label:'Myscore',data:"+myData+" , bars: {order: 1} }  ]";
		//var ticksString = "[[1,'1'],[2,'2'],[3,'3'],[4,'4'],[5,'5']]";
		var X_ticksString = "[[0,'0'],[10,'10'],[20,'20'],[30,'30'],[40,'40'],[50,'50'],[60,'60'],[70,'70'],[80,'80'],[90,'90'],[100,'100']]";
		var barOptions = {
	        series: {
	            bars: {
	                show: true,
	                barWidth: 0.3,
                    horizontal: true
	            }
	        },
	        yaxis: {
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 3,
                autoscaleMargin: .10,
	        	ticks: eval(ticksString),
	            min : 0,
	            max : ${forumCntChk }
	        },
	        xaxis: {
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 10,
                ticks: eval(X_ticksString),
    			tickSize: 10,
                tickDecimals: 1,
                min : 0,
                max : 100
	        },
	        grid: {
	            hoverable: true
	        },
	        legend: {
	        	position: 'ne',
                alignTicksWithAxis: position == "right" ? 1 : null
	            //show: false
	        },
	        tooltip: true,
	        tooltipOpts: {
	        	content: "%y : %x <spring:message code="common.title.score"/>"
	        }
	    };

		$.plot($("#flot-bar-chart-multi"), eval(barDataString), barOptions);
	});

	function forumInfo(forumSn){
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/lec/forum/viewForumPop"/>"
			+ "?forumSn="+forumSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("<spring:message code="lecture.title.forum.info"/>");
		modalBox.show();
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
</script>
