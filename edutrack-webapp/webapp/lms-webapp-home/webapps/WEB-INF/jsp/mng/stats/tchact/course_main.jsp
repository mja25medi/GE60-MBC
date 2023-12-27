<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
					<div class="col-md-12 col-sm-12">
						<table style="width:100%" class="table table-striped table-bordered" style="margin-bottom:10px;">
							<colgroup>
								<col style="width:20%"/>
								<col style="width:30%"/>
								<col style="width:20%"/>
								<col style="width:30%"/>
							</colgroup>
							<tbody>
								<tr>
									<th><spring:message code="user.title.userinfo.name"/></th>
									<td>${userInfoVO.userNm}</td>
									<th><spring:message code="user.title.userinfo.userid"/></th>
									<td>${userInfoVO.userId}</td>
								</tr>
							</tbody>
						</table>
						<div class="text-right">
							<select name="listScale" id="listScale" onchange="viewStatus(1);" class="form-control input-sm">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
							<a href="<c:url value="/TchActStatusManage.do"/>?cmd=teacherMain" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
						</div>
					</div>
	
					<div class="col-md-12" style="margin-top:10px;">
						<div id="listArea"></div>
					</div>
	
					<div class="col-md-12" style="margin-top:10px;">
						<div id="listChart">
							<div class="panel panel-default">
								<div class="panel-heading">
									Connect Log
									<div style="float: right;">
									<%-- <select name="crsCreCd" id="crsCreCd" style="float:left;width:150px;margin-top: -3px;">
										<option value=""><spring:message code="common.title.all"/></option>
										<c:forEach items="${itemList}" var="item" varStatus="status">
											<option value="${item.crsCreCd}">${item.crsCreNm}</option>
										</c:forEach>
										<c:if test="${empty itemList}">
											<option></option>
										</c:if>
									</select> --%>
									<div class="input-group" style="float:left;width:130px;margin-left:10px; margin-right: 10px;margin-top: -3px;">
										<input type="text" name="connectLogDTO.startDt" id="startDt" value="${nowDate}" class="form-control input-sm"/>
										<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDt')" style="cursor:pointer">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
									<div class="input-group" style="float:left;width:130px;margin-left:10px; margin-right: 10px;margin-top: -3px;">
										<input type="text" name="connectLogDTO.endDt" id="endDt" value="${nowDate}" class="form-control input-sm"/>
										<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDt')" style="cursor:pointer">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
									<meditag:datepicker name1="startDt" name2="endDt" />
	
									<a href="javascript:viewChart('DAY')" class="btn btn-xs btn-default"><spring:message code="common.title.period.day"/></a>
									<a href="javascript:viewChart('WEEK')" class="btn btn-xs btn-default"><spring:message code="common.title.period.week"/></a>
									<a href="javascript:viewChart('MONTH')" class="btn btn-xs btn-default"><spring:message code="common.title.period.month"/></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="flot-chart" id="chartArea">
										<div class="flot-chart-content" id="flot-line-chart-multi"></div>
									</div>
									<div id="legendContainer"  style="float: right;"></div>
								</div>
							</div>
						</div>
					</div>
	
					</form>
				</div>
	
				<meditag:js src="/tpl/bootstrap/bower_components/flot/excanvas.min.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.pie.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.resize.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.time.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"/>
			</div>
		</div>
	</div>
</section>				
<style type="text/css">
#legendContainer {
    background-color: #fff;
    padding: 2px;
    margin-bottom: 8px;
    border-radius: 3px 3px 3px 3px;
    border: 1px solid #E6E6E6;
    display: inline-block;
    margin: 0 auto;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		viewStatus(1);
	});

	function viewStatus(page) {
		var listScale = $("#listScale option:selected").val();

		$("#listArea")
			.load(
				cUrl("/mng/stats/tchAct/courseList"), {
				"searchDuration" : "${vo.searchDuration}",
				"userNo" : "${vo.userNo}",
				"curPage" : page,
				"listScale":listScale
			});
	}
/*
	function viewStatistics(type){
		$("#listChart")
			.load(
				cUrl("/TchActStatusManage.do"), {
				"cmd" : "courseStatistics",
				"tchActStatusDetailDTO.searchDuration" : "${tchActStatusDetailDTO.searchDuration}",
				"tchActStatusDetailDTO.userNo" : "${tchActStatusDetailDTO.userNo}"
		});

	}
 */

	function viewChart(type) {
		var startDt = $("#startDt").val();
		var endDt = $("#endDt").val();

		var chkCrsCreCd = $(document).find("#crsCreList input[name='sel']");
		var chkCrsCreNm = $(document).find("#crsCreList input[name='crsCreNm']");
	    var crsCreCd = "";
	    var crsCreNm= "";
	    var chkCnt = 0;

	    for(var i=0; i<chkCrsCreCd.length; i++){
			if(chkCrsCreCd[i].checked){
				if(crsCreCd != ""){
					crsCreCd += ";";
					crsCreNm += ";";
				}
				crsCreCd += chkCrsCreCd[i].value;
				crsCreNm += chkCrsCreNm[i].value;
				chkCnt++

				if(chkCnt > 5 ){
					alert("<spring:message code="log.message.tchact.alert.course.selectmax"/>");
					chkCrsCreCd[i].checked = false;
					return;
			    }
			}
	    }


		$("#chartArea").load(
				cUrl("/mng/stats/tchAct/courseStatisticsList"),
				{
					"searchDuration" : "${vo.searchDuration}",
					"userNo" : "${vo.userNo}",
					"type":type,
					"startDt":startDt,
					"endDt":endDt,
					"crsCreCd":crsCreCd,
					"crsCreNm":crsCreNm
				}
		);
	}
</script>


