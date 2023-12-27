<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
		<section class="content">
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon bg-orange"><i class="fa fa-users"></i></span>
						<div class="info-box-content">
							<span class="info-box-text">Total Users</span>
							<span class="info-box-number">${losvo.totalUserCnt}</span>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon bg-yellow"><i class="fa fa-child"></i></span>
						<div class="info-box-content">
							<span class="info-box-text">Visits Today</span>
							<span class="info-box-number">${losvo.todayConnectCnt}</span>
						</div>
					</div>
				</div>		
				
					<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon bg-aqua"><i class="fa fa-bar-chart"></i></span>
						<div class="info-box-content">
							<span class="info-box-text">Today Login Count</span>
							<span class="info-box-number">${losvo.todayLoginCnt}</span>
						</div>
					</div>
				</div>
				
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon bg-green"><i class="fa fa-comments-o"></i></span>
						<div class="info-box-content">
							<span class="info-box-text">New Articles</span>
							<span class="info-box-number">${losvo.newAtclCnt}</span>
						</div>
					</div>
				</div>
			
						
				
				<div class="col-md-12 col-xs-12">
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">Connect Log</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						<div class="box-body">
							<div class="pull-right">
								<div class="input-group" style="float:left;width:130px;margin-top: -3px;">
									<input type="text" name="startDt" id="startDt" value="${nowDate}" class="form-control input-sm"/>
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDt')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<div class="input-group" style="float:left;width:130px;margin-left:10px; margin-right: 10px;margin-top: -3px;">
									<input type="text" name="endDt" id="endDt" value="${nowDate}" class="form-control input-sm"/>
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDt')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<meditag:datepicker name1="startDt" name2="endDt" />
								<a href="javascript:viewChart('HOUR')" class="btn btn-xs btn-default"><spring:message code="common.title.period.hour"/></a>
								<a href="javascript:viewChart('DAY')" class="btn btn-xs btn-default"><spring:message code="common.title.period.day"/></a>
								<a href="javascript:viewChart('WEEK')" class="btn btn-xs btn-default"><spring:message code="common.title.period.week"/></a>
								<a href="javascript:viewChart('MONTH')" class="btn btn-xs btn-default"><spring:message code="common.title.period.month"/></a>
							</div>
							<div class="clearfix"></div>							
							<div class="flot-chart" id="chartArea">
								<div class="flot-chart-content" id="flot-line-chart-multi"></div>
							</div>
						</div>
					</div>
					
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title"></h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						
						<div class="box-body">
							<form name="statsKnowForm" id="statsKnowForm" onsubmit="return false" action="/mng/stats/conn/listConn">
							<input type="hidden" name="cmd" />
							<input type="hidden" name="dateType" value="${vo.dateType}"/>
							<input type="hidden" name="viewType" id="viewType" value="HOUR">
							<div class="pull-right">
								<div class="input-group" style="float:left;width:130px;margin-top: -3px;">
									<input type="text" name="startDt" id="startDt2" value="${nowDate}" class="form-control input-sm"/>
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDt2')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<div class="input-group" style="float:left;width:130px;margin-left:10px; margin-right: 10px;margin-top: -3px;">
									<input type="text" name="endDt" id="endDt2" value="${nowDate}" class="form-control input-sm"/>
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDt2')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<meditag:datepicker name1="startDt2" name2="endDt2" />
								<a href="javascript:listStatus('HOUR')" class="btn btn-xs btn-default"><spring:message code="common.title.period.hour"/></a>
								<a href="javascript:listStatus('DAY')" class="btn btn-xs btn-default"><spring:message code="common.title.period.day"/></a>
								<a href="javascript:listStatus('WEEK')" class="btn btn-xs btn-default"><spring:message code="common.title.period.week"/></a>
								<a href="javascript:listStatus('MONTH')" class="btn btn-xs btn-default"><spring:message code="common.title.period.month"/></a>
							</div>
							</form>
							<div class="clearfix"></div>		
							<div class="flot-chart">
								<div class="flot-chart-content" id="flot-bar-chart" style="height:300px"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<meditag:js src="/tpl/libs/admin-lte/plugins/flot/excanvas.min.js"/>
		<meditag:js src="/tpl/libs/admin-lte/plugins/flot/jquery.flot.js"/>
		<meditag:js src="/tpl/libs/admin-lte/plugins/flot/jquery.flot.pie.js"/>
		<meditag:js src="/tpl/libs/admin-lte/plugins/flot/jquery.flot.resize.js"/>
		<meditag:js src="/tpl/libs/admin-lte/plugins/flot/jquery.flot.time.js"/>
		<meditag:js src="/tpl/libs/admin-lte/plugins/flot/jquery.flot.tooltip.min.js"/>

		<script type="text/javascript">
			var connects = [];
			var logins = [];
			var modalBox = null;
		
			$(document).ready(function() {
				modalBox = new $M.ModalDialog({
					"modalid" : "modal1"
				});
				
				viewChart("HOUR");
				listStatus("HOUR");
			});
		
			function modalBoxClose() {
				modalBox.clear();
				modalBox.close();
			}
		
		    function peopleFormatter(v, axis) {
		        return v.toFixed(axis.tickDecimals) + "명";
		    }
		
			function viewChart(type) {
				var startDt = $("#startDt").val();
				var endDt = $("#endDt").val();
				$("#chartArea").load(
						cUrl("/mng/main/connectLogChart"),
						{	
							"mcd":"MS00000000",
							"type":type,
							"startDt":startDt,
							"endDt":endDt
						}
				);
			}
			
			function listStatus(type) {
				//if(!validate(document.statsKnowForm)) return;
				$("#viewType").val(type);
				
			    var f = document.statsKnowForm;
				$('#statsKnowForm').ajaxSubmit(processListStatusCallback);
			}
			
			function processListStatusCallback(resultListVO) {
				var list = resultListVO.returnList;
				var typeCd = $("#viewType").val();
			
				var itemGroup = new Object();
			
				var totCnt = 0;
				var ticksString = "[";
				var barDataString = "[{label:'View',data:[";
				for (var i=0; i<list.length; i++) {
					var item = list[i];
					totCnt = totCnt + item.connCnt;
					if(i>0) {
						ticksString += ",";
						barDataString += ",";
					}
					barDataString += "["+i+","+item.connCnt+"]";
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
			}
		</script>
