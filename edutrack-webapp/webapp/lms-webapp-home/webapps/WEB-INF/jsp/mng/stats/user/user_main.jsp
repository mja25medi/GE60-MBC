<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
						<div class="well well-sm">
							<form name="statsUserForm" id="statsUserForm" onsubmit="return false" >
							<input type="hidden" name="dateType" value="${vo.dateType}"/>
							<div class="row">
								<div class="col-md-12 col-xs-12 mb5">
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
									<div class="btn-group">
										<button class="btn btn-default btn-sm" onclick="dateSetter('Day')" ><spring:message code="button.today"/></button>
										<button class="btn btn-default btn-sm" onclick="dateSetter('Week')" ><spring:message code="button.1week"/></button>
										<button class="btn btn-default btn-sm" onclick="dateSetter('Month')" ><spring:message code="button.1month"/></button>
										<button class="btn btn-default btn-sm" onclick="dateSetter('Year')" ><spring:message code="button.1year"/></button>
									</div>
								</div>
								<div class="col-md-6 col-xs-12 mb5">
									<div class="input-group">
										<select name="viewType" id="viewType" class="form-control input-sm">
										<c:forEach var="code" items="${codeList}">
										<c:set var="codeName" value="${code.codeNm}"/>
										<c:forEach var="codeLang" items="${code.codeLangList}">
											<c:if test="${codeLang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${codeLang.codeNm}"/></c:if>
										</c:forEach>
										<option value="${code.codeCd}">${codeName}</option>
										</c:forEach>
										</select>
										<div class="input-group-btn">
<%-- 											<button class="btn btn-default btn-sm" onclick="listStatus()"><spring:message code="button.search"/></button> --%>
											<a href="javascript:listStatus()" class="btn btn-primary btn-sm"><i class="fa fa-search fa-fw"></i><spring:message code="button.search"/></a>
										</div>
										<div class="input-group-btn">
											<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i><spring:message code="button.download.excel"/></a>
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>																
							</form>
						</div>
						<div class="row">
							<div class="col-md-7 col-sm-7">
								<div class="panel panel-default">
									<div class="panel-heading">
										<spring:message code="log.title.userlog.title.count"/>
									</div>
									<div class="panel-body" style="height:630px;">
										<div class="flot-chart">
											<div class="flot-chart-content" id="flot-bar-chart" style="height:600px;">&nbsp;</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-5 col-sm-5">
								<div class="panel panel-default">
									<div class="panel-heading">
										<spring:message code="log.title.userlog.title.ratio"/>
									</div>
									<div class="panel-body" style="height:630px;">
										<div class="flot-chart">
											<div class="flot-chart-content" id="flot-pie-chart" style="height:600px;">&nbsp;</div>
										</div>
									</div>
								</div>
							</div>
						</div>
			</div>
		</div>
	</div>
</section>
	<meditag:js src="/libs/admin-lte/plugins/flot/excanvas.min.js"/>
	<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.js"/>
	<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.pie.js"/>
	<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.resize.js"/>
	<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.time.js"/>
	<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.tooltip.min.js"/>	
<script type="text/javascript">
	$(document).ready(function() {
		$(".inputDate").inputDate();
		listStatus();
	});
	
	function listStatus() {
		//if(!validate(document.statsUserForm)) return;
	    var f = document.statsLoginForm;
		$('#statsUserForm').attr("action", "/mng/stats/conn/listUser");
		$('#statsUserForm').ajaxSubmit(processListStatusCallback);
	}
	
	function processListStatusCallback(resultListVO) {
		var list = resultListVO.returnList;
		var typeCd = $("#viewType option:selected").val();
		var itemGroup = new Object();
	
		var totCnt = 0;
		var ticksString = "[";
		var barDataString = "[{label:'User',data:[";
		for (var i=0; i<list.length; i++) {
			var item = list[i];
			totCnt = totCnt + item.userCnt;
			if(i>0) {
				ticksString += ",";
				barDataString += ",";
			}
			barDataString += "["+i+","+item.userCnt+"]";
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
		        	axisLabel: "User",
	                axisLabelUseCanvas: true,
	                axisLabelFontSizePixels: 12,
	                axisLabelFontFamily: 'Verdana, Arial',
	                axisLabelPadding: 3,
	                tickDecimals: 0
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
			var ratio = Math.round((parseInt(item.userCnt,10) / totCnt) * 100);
			if(i > 0) pieData += ",";
			pieData += "{label:'"+item.codeNm+"', data:"+ratio+"}";
		}
		pieData += "]";
	
	
		$.plot($("#flot-pie-chart"), eval(pieData), {
	        series: {
	            pie: {
	                show: true,
	                radius: 1,
	                label: {
	                    show: true,
	                    radius: 3/4,
//		                    formatter: "%p.0%, %s",
	                    background: {
	                        opacity: 0.7,
	                        color:'#000'
	                    }
	                }
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
		if(!validate(document.statsUserForm)) return;
		$('#statsUserForm').attr("action", "/mng/stats/conn/viewAutoDate");
		$('#statsUserForm').find('input[name=dateType]').val(str);
		$('#statsUserForm').ajaxSubmit(autoDateCallback);
	}
	
	function autoDateCallback(ProcessResultDTO) {
		//var Item = ProcessResultDTO.returnDto;
		var Item = ProcessResultDTO;
		$('#statsUserForm').find('input[name=startDt]').val(Item.startDt);
		$('#statsUserForm').find('input[name=endDt]').val(Item.endDt);
		listStatus();
	}
	
	function excelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml = '<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		
		// 폼에 action을 설정하고 submit시킨다.
		$("#statsUserForm").removeAttr('onsubmit');
		$("#statsUserForm").attr('target', '_m_download_iframe');
		$("#statsUserForm").attr("action", "/mng/stats/conn/userListExcelDownload");
		$("#statsUserForm").submit();
		$("#statsUserForm").removeAttr('target');
		$("#statsUserForm").attr('onsubmit', 'return false');

	}
	
</script>

