<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>


							<div class="flot-chart-content" id="flot-line-chart-multi" style="height:300px;"></div>

<c:set var="weekTitle" />
<script type="text/javascript">
$(document).ready(function() {


	<c:if test="${TYPE eq 'HOUR'}">
	connects = [
	      	<c:forEach var="item" items="${connectLogList}" varStatus="status">
   			    [${item.codeNm},${item.connCnt}]<c:if test="${!status.last}">,</c:if>
	      	</c:forEach>
      	           ];
	logins = [
	   		<c:forEach var="item" items="${loginLogList}" varStatus="status">
	   			[${item.codeNm},${item.loginCnt}]<c:if test="${!status.last}">,</c:if>
	   		</c:forEach>
      	         ];
	doPlotHour('right');
	</c:if>

	<c:if test="${TYPE eq 'DAY'}">
	connects = [
        	<c:forEach var="item" items="${connectLogList}" varStatus="status">
			    [${item.codeNm},${item.connCnt}]<c:if test="${!status.last}">,</c:if>
        	</c:forEach>
        	           ];
  	logins = [
     		<c:forEach var="item" items="${loginLogList}" varStatus="status">
     			[${item.codeNm},${item.loginCnt}]<c:if test="${!status.last}">,</c:if>
     		</c:forEach>
        	         ];
	doPlotDay('right');
	</c:if>

	<c:if test="${TYPE eq 'WEEK'}">
	connects = [
	      	<c:forEach var="item" items="${connectLogList}" varStatus="status">
		      	<c:choose>
		      	<c:when test="${item.codeNm eq 'SUN'}"><c:set var="weekTitle"  value="1"/></c:when>
		      	<c:when test="${item.codeNm eq 'MON'}"><c:set var="weekTitle"  value="2"/></c:when>
		      	<c:when test="${item.codeNm eq 'TUE'}"><c:set var="weekTitle"  value="3"/></c:when>
		      	<c:when test="${item.codeNm eq 'WED'}"><c:set var="weekTitle"  value="4"/></c:when>
		      	<c:when test="${item.codeNm eq 'THU'}"><c:set var="weekTitle"  value="5"/></c:when>
		      	<c:when test="${item.codeNm eq 'FRI'}"><c:set var="weekTitle"  value="6"/></c:when>
		      	<c:when test="${item.codeNm eq 'SAT'}"><c:set var="weekTitle"  value="7"/></c:when>
		      	</c:choose>
			    ["${weekTitle}",${item.connCnt}]<c:if test="${!status.last}">,</c:if>
	      	</c:forEach>
      	           ];
	logins = [
	   		<c:forEach var="item" items="${loginLogList}" varStatus="status">
		   		<c:choose>
		      	<c:when test="${item.codeNm eq 'SUN'}"><c:set var="weekTitle"  value="1"/></c:when>
		      	<c:when test="${item.codeNm eq 'MON'}"><c:set var="weekTitle"  value="2"/></c:when>
		      	<c:when test="${item.codeNm eq 'TUE'}"><c:set var="weekTitle"  value="3"/></c:when>
		      	<c:when test="${item.codeNm eq 'WED'}"><c:set var="weekTitle"  value="4"/></c:when>
		      	<c:when test="${item.codeNm eq 'THU'}"><c:set var="weekTitle"  value="5"/></c:when>
		      	<c:when test="${item.codeNm eq 'FRI'}"><c:set var="weekTitle"  value="6"/></c:when>
		      	<c:when test="${item.codeNm eq 'SAT'}"><c:set var="weekTitle"  value="7"/></c:when>
		      	</c:choose>
	   			["${weekTitle}",${item.loginCnt}]<c:if test="${!status.last}">,</c:if>
	   		</c:forEach>
      	         ];
	doPlotWeek('right');
	</c:if>

	<c:if test="${TYPE eq 'MONTH'}">
	connects = [
        	<c:forEach var="item" items="${connectLogList}" varStatus="status">
			    [${item.codeNm},${item.connCnt}]<c:if test="${!status.last}">,</c:if>
        	</c:forEach>
        	           ];
  	logins = [
     		<c:forEach var="item" items="${loginLogList}" varStatus="status">
     			[${item.codeNm},${item.loginCnt}]<c:if test="${!status.last}">,</c:if>
     		</c:forEach>
        	         ];
	doPlotMonth('right');
	</c:if>
});


function doPlotHour(position) {

	$.plot($("#flot-line-chart-multi"), [{
        data: connects,
        color: "orange",
        label: " Connect Count"
    }, {
        data: logins,
        color: "green",
        label: " Login Count",
    }], {
        xaxes: [{
        	tickDecimals: 0,
			tickSize: 1,
            min : 0,
            max : 23
        }],
        yaxes: [{
        	tickDecimals: 0,
            min: 0
        }, {
            alignTicksWithAxis: position == "right" ? 1 : null,
            position: position
        }],
        legend: {
            position: 'sw'
        },
        grid: {
            hoverable: true //IMPORTANT! this is needed for tooltip to work
        },
        tooltip: true,
        tooltipOpts: {
            content: "%s for %x:00 was %y",
            onHover: function(flotItem, $tooltipEl) {
                // console.log(flotItem, $tooltipEl);
            }
        }

    });
}

function doPlotDay(position) {

	$.plot($("#flot-line-chart-multi"), [{
        data: connects,
        color: "orange",
        label: " Connect Count"
    }, {
        data: logins,
        color: "green",
        label: " Login Count",
    }], {
        xaxes: [{
        	tickDecimals: 0,
			tickSize: 1,
            min : 1,
            max : 31
        }],
        yaxes: [{
        	tickDecimals: 0,
            min: 0
        }, {
            alignTicksWithAxis: position == "right" ? 1 : null,
            position: position
        }],
        legend: {
            position: 'sw'
        },
        grid: {
            hoverable: true //IMPORTANT! this is needed for tooltip to work
        },
        tooltip: true,
        tooltipOpts: {
            content: "%s for %x was %y",
            onHover: function(flotItem, $tooltipEl) {
                // console.log(flotItem, $tooltipEl);
            }
        }

    });
}

function doPlotWeek(position) {
	var ticksString = "[[1,'SUN'],[2,'MON'],[3,'TUE'],[4,'WED'],[5,'THU'],[6,'FRI'],[7,'SAT']]";

	$.plot($("#flot-line-chart-multi"), [{
        data: connects,
        color: "orange",
        label: " Connect Count"
    }, {
        data: logins,
        color: "green",
        label: " Login Count",
    }], {
        xaxes: [{
        	ticks: eval(ticksString),
            min : 1,
            max : 7
        }],
        yaxes: [{
        	tickDecimals: 0,
            min: 0
        }, {
            alignTicksWithAxis: position == "right" ? 1 : null,
            position: position
        }],
        legend: {
            position: 'sw'
        },
        grid: {
            hoverable: true //IMPORTANT! this is needed for tooltip to work
        },
        tooltip: true,
        tooltipOpts: {
            content: "%s for %x was %y",
            onHover: function(flotItem, $tooltipEl) {
                // console.log(flotItem, $tooltipEl);
            }
        }

    });
}

function doPlotMonth(position) {

	$.plot($("#flot-line-chart-multi"), [{
        data: connects,
        color: "orange",
        label: " Connect Count"
    }, {
        data: logins,
        color: "green",
        label: " Login Count",
    }], {
        xaxes: [{
        	tickDecimals: 0,
			tickSize: 1,
            min : 1,
            max : 12
        }],
        yaxes: [{
        	tickDecimals: 0,
            min: 0
        }, {
            alignTicksWithAxis: position == "right" ? 1 : null,
            position: position
        }],
        legend: {
            position: 'sw'
        },
        grid: {
            hoverable: true //IMPORTANT! this is needed for tooltip to work
        },
        tooltip: true,
        tooltipOpts: {
            content: "%s for %x was %y",
            onHover: function(flotItem, $tooltipEl) {
                // console.log(flotItem, $tooltipEl);
            }
        }

    });
}

</script>