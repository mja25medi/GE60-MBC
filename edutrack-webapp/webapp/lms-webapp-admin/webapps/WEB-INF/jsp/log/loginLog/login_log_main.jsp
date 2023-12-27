<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<body>
<section class="content">
<div class="box">
<div class="box-body">
	<div class="row" id="content">
		<div class="col-md-12">
		<div class="row" id="content">
				<div class="col-md-12 col-sm-12">
					<div class="well well-sm">
						<form onsubmit="return false" id="loginLogForm" name="loginLogForm" method="POST">
						<input type="hidden" name="dateType" />
						<table class="table_detail" style="width:100%">
							<colgroup>
								<col style="width:120px"/>
								<col style="width:300px;"/>
								<col style="width:auto"/>
								<col style="width:120px"/>
							</colgroup>
							<tbody>
							<tr>
								<th class="top"><spring:message code="org.title.orginfo.choice" /></th>
								<td colspan="3" class="top">
									<select name="orgCd" id="orgCd" class="form-control input-sm">
										<c:forEach var="item" items="${orgInfoList}">
										<option value="${item.orgCd}">${item.orgNm}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th><spring:message code="log.title.connectlog.select.date"/></th>
								<td >
									<div >
										<div class="input-group" style="float:left;width:130px;">
											<input type="text" name="startDt" id="startDt" value="${curDate}" class="inputDate form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDt')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
										<div class="input-group" style="float:left;width:130px;margin-left:10px;">
											<input type="text" name="endDt" id="endDt" value="${curDate}" class="inputDate form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDt')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
										<meditag:datepicker name1="startDt" name2="endDt" />
									</div>
								</td>
								<td colspan="2">
									<button class="btn btn-default btn-sm" onclick="dateSetter('Day')" ><spring:message code="button.today"/></button>
									<button class="btn btn-default btn-sm" onclick="dateSetter('Week')" ><spring:message code="button.1week"/></button>
									<button class="btn btn-default btn-sm" onclick="dateSetter('Month')" ><spring:message code="button.1month"/></button>
									<button class="btn btn-default btn-sm" onclick="dateSetter('Year')" ><spring:message code="button.1year"/></button>
								</td>
							</tr>
							<tr>
								<th><spring:message code="log.title.connectlog.select.type"/></th>
								<td>
									<select name="viewType" id="viewType" class="form-control input-sm"  style="width:270px;">
									<c:forEach var="code" items="${codeList}">
									<c:set var="codeName" value="${code.codeNm}"/>
									<c:forEach var="codeLang" items="${code.codeLangList}">
										<c:if test="${codeLang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${codeLang.codeNm}"/></c:if>
									</c:forEach>
									<option value="${code.codeCd}">${codeName}</option>
									</c:forEach>
									</select>
								</td>
								<td>
									<button class="btn btn-default btn-sm" onclick="listStatus()" ><spring:message code="button.search"/></button>
								</td>
								<td align="right" style="width:120px">

								</td>
							</tr>
							</tbody>
						</table>
						</form>
					</div>
				</div>
				<div class="col-md-7 col-sm-7">
					<div class="panel panel-default">
						<div class="panel-heading">
							<spring:message code="log.title.connectlog.title.count"/>
						</div>
						<div class="panel-body" style="height:630px;">
							<div class="flot-chart">
								<div class="flot-chart-content" id="flot-bar-chart"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-5 col-sm-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<spring:message code="log.title.connectlog.title.ratio"/>
						</div>
						<div class="panel-body" style="height:630px;">
							<div class="flot-chart">
								<div class="flot-chart-content" id="flot-pie-chart"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<meditag:js src="/libs/bootstrap/bower_components/flot/excanvas.min.js"/>
			<meditag:js src="/libs/bootstrap/bower_components/flot/jquery.flot.js"/>
			<meditag:js src="/libs/bootstrap/bower_components/flot/jquery.flot.pie.js"/>
			<meditag:js src="/libs/bootstrap/bower_components/flot/jquery.flot.resize.js"/>
			<meditag:js src="/libs/bootstrap/bower_components/flot/jquery.flot.time.js"/>
			<meditag:js src="/libs/bootstrap/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"/>
		</div>
		</div>
	</div>
</div>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		$(".inputDate").inputDate();
		listStatus();
	});

	function listStatus() {
		var cmd ="/adm/loginLog/list";
		 $('#loginLogForm').attr("action", cmd);
		$('#loginLogForm').ajaxSubmit(processListStatusCallback);
	}

	function processListStatusCallback(ProcessResultListVO) {
		var list = ProcessResultListVO.returnList;
		var typeCd = $("#viewType option:selected").val();
		var orgCd = $("#orgCd option:selected").val();

		var itemGroup = new Object();

		var totCnt = 0;
		var ticksString = "[";
		var barDataString = "[{label:'Connect',data:[";
		for (var i=0; i<list.length; i++) {
			var item = list[i];
			totCnt = totCnt + item.loginCnt;
			if(i>0) {
				ticksString += ",";
				barDataString += ",";
			}
			barDataString += "["+i+","+item.loginCnt+"]";
			ticksString += "["+i+",'"+item.codeNm+"']";
		}
		barDataString += "]}]";
		ticksString += "]";

	    var barOptions = {
		        series: {
		            bars: {
		            	align: "center",
		                show: true,
		                barWidth: 1
		            }
		        },
		        xaxis: {
		        	axisLabel: typeCd,
	                axisLabelUseCanvas: true,
	                axisLabelFontSizePixels: 12,
	                axisLabelFontFamily: 'Verdana, Arial',
	                axisLabelPadding: 10,
		        	ticks: eval(ticksString)
		        },
		        yaxis: {
		        	axisLabel: "Connect",
	                axisLabelUseCanvas: true,
	                axisLabelFontSizePixels: 12,
	                axisLabelFontFamily: 'Verdana, Arial',
	                axisLabelPadding: 3
		        },
		        grid: {
		            hoverable: true
		        },
		        legend: {
		            show: false
		        },
		        tooltip: true,
		        tooltipOpts: {
		            content: "%x : %y"
		        }
		    };

	    $.plot($("#flot-bar-chart"), eval(barDataString), barOptions);

		var pieData = "[";
		for (var i=0; i<list.length; i++) {
			var item = list[i];
			var ratio = Math.round((parseInt(item.loginCnt,10) / totCnt) * 100);
			if(i > 0) pieData += ",";
			pieData += "{label:'"+item.codeNm+"', data:"+ratio+"}";
		}
		pieData += "]";


		$.plot($("#flot-pie-chart"), eval(pieData), {
		        series: {
		            pie: {
		                show: true
		            }
		        },
		        grid: {
		            hoverable: true
		        },
		        tooltip: true,
		        tooltipOpts: {
		            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
		            shifts: {
		                x: 20,
		                y: 0
		            },
		            defaultTheme: false
		        }
		    });
	}

	function dateSetter(str) {
		var cmd ="/adm/loginLog/viewAutoDate";
		 $('#loginLogForm').attr("action", cmd);
		$('#loginLogForm').find('input[name=dateType]').val(str);
		$('#loginLogForm').ajaxSubmit(autoDateCallback);
	}

	function autoDateCallback(ProcessResultVO) {
		var Item = ProcessResultVO;
		$('#loginLogForm').find('input[name=startDt]').val(Item.startDt);
		$('#loginLogForm').find('input[name=endDt]').val(Item.endDt);
		listStatus();
	}
</script>
</body>