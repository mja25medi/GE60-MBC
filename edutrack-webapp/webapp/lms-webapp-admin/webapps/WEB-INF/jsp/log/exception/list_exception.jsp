<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<head>
<style type="text/css">
	table tr.record { background-color: #FFFFFF }
	table tr.stacktrace { background-color: #F4F8F8; padding-left: 15px; }
</style>
</head>
<body>
<section class="content">
<div class="box">
	<div class="box-body">
			<div class="row" id="content">
				<form name="logExceptionForm" id="logExceptionForm" onsubmit="return false" method="POST" class="form-inline" action="/adm/log/exception/listExceptionMain">
				<div class="col-md-12 col-sm-12">
					<div class="input-group" style="width:144px;float:left">
						<input type="text" name="searchFrom" id="searchFrom" maxlength="10" title="<spring:message code="log.title.exception.search.date"/> " class="form-control input-sm inputDate" value="${vo.searchFrom}"/>
						<span class="input-group-addon" onclick="_clickCalendar('searchFrom')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<div style="float:left;margin-left:5px;margin-right:5px;line-height:25px;">~</div>
					<div class="input-group" style="width:144px;float:left">
						<input type="text" name="searchTo" id="searchTo" maxlength="10" title="<spring:message code="log.title.exception.search.date"/> " class="form-control input-sm inputDate" value="${vo.searchTo}"/>
						<span class="input-group-addon" onclick="_clickCalendar('searchTo')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="searchFrom" name2="searchTo" />
					<div style="float:left;margin-left:5px;">
						<a href="#" class="btn btn-default btn-sm" onclick="setPervDttm('1')" ><spring:message code="button.month.1"/></a>
						<a href="#" class="btn btn-default btn-sm" onclick="setPervDttm('3')" ><spring:message code="button.month.3"/></a>
						<a href="#" class="btn btn-default btn-sm" onclick="setPervDttm('6')" ><spring:message code="button.month.6"/></a>
						<a href="#" class="btn btn-default btn-sm" onclick="setPervDttm('12')" ><spring:message code="button.month.12"/></a>
					</div>
					<select name="exceptionDiv" id="exceptionDiv" style="width:130px;margin-left:5px;" class="form-control input-sm">
						<option value="SERVICE" <c:if test="${vo.exceptionDiv eq 'SERVICE'}">selected</c:if>><spring:message code="log.title.exception.layer.service"/></option>
						<option value="ACTION" <c:if test="${vo.exceptionDiv eq 'ACTION'}">selected</c:if>><spring:message code="log.title.exception.layer.action"/></option>
					</select>
					<button class="btn btn-default btn-sm" id="btnSubmit"><spring:message code="button.search"/></button>
				</div>
				</form>
				<div class="col-md-12" style="margin-top:10px;">
					<div class="well well-sm">
						<div class="row">
							<div class="col-md-12">
								<ul style="list-style: none; padding-left:10px;">
									<li style="float:left;margin-right:40px;"><spring:message code="common.title.type"/> : ${(vo.exceptionDiv eq 'SERVICE') ? '<spring:message code="log.title.exception.layer.service"/> AOP' : '<spring:message code="log.title.exception.layer.action"/> Dispatch Action'}</li>
									<li style="float:left;margin-right:40px;"><spring:message code="log.title.exception.occurrence.cnt"/> : ${exceptionTotalCount}</li>
									<li style="float:left;margin-right:40px;"><spring:message code="log.title.exception.type.cnt" /> : ${fn:length(exceptionList)}</li>
									<li style="float:left;margin-right:40px;"><spring:message code="log.title.exception.report.date"/> : ${vo.originDt}</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<table title="Exception Report" style="width:100%" class="table table-striped table-bordered">
						<colgroup>
							<col style="width:80px"/>
							<col style="width:auto"/>
							<col style="width:auto"/>
							<col style="width:120px"/>
							<col style="width:60px"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><spring:message code="log.title.exception.occurrence.cnt"/></th>
								<th scope="col"><spring:message code="log.title.exception.method.name" /></th>
								<th scope="col"><spring:message code="log.title.exception.name" /></th>
								<th scope="col"><spring:message code="log.title.exception.occurrence.date" /> </th>
								<th scope="col"><button id="btnViewAll"><spring:message code="button.view" /></button></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty exceptionList}">
							<tr><td colspan="5"><spring:message code="log.message.exception.nodata" /></td></tr>
							</c:if>
							<c:forEach var="item" items="${exceptionList}" varStatus="status">
							<tr class="record" id="record${status.count}">
								<td class='ac text-right'>${item.originCnt}</td>
								<td class="wordbreak">${item.methodNm}</td>
								<td class="wordbreak">${item.exceptionNm}</td>
								<td class='ac' style="padding:0px 10px 0px 10px;"><meditag:dateformat type="0" property="${item.modDttm}"/></td>
								<td class='text-center'>
									<button id="btnView${status.count}" class="btnView btn btn-info btn-sm">∨</button>
								</td>
							</tr>
							<tr class="stacktrace" id="stacktrace${status.count}">
								<td colspan="5" style="text-align: left;padding:10px 15px 10px 15px;">${item.stackTrace}</td></tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</section>
<script type="text/javascript">
$(document).ready(function() {
	$(".inputDate").inputDate();
	$('#btnSubmit').bind("click keydown", function(event) {
		if($M.Check.Event.isClickEnter(event)) {
			event.preventDefault();
			document.logExceptionForm.submit();
		}
	});

	$('.stacktrace').hide();
	$('tr:odd').addClass('odd');
	$('tr:even').addClass('even');

	$('#btnViewAll').toggle(function() {
		$(this).text('Close');
		$('.btnView').text('∧');
		$('.stacktrace').show();
	}, function() {
		$(this).text('Open');
		$('.btnView').text('∨');
		$('.stacktrace').hide();
	});

	$('button.btnView').bind('click keydown', function() {
		if ($(this).text() == '∧') {
			$('#'+$(this).text('∨').attr('id').replace('btnView','stacktrace')).hide();
		} else {
			$('#'+$(this).text('∧').attr('id').replace('btnView','stacktrace')).show();
		}
	});

});

function setPervDttm(gep) {
	var endDttm = $("#searchTo").val().replace(".","").replace(".","");
	var prevDttm = addDateFormatStr(getIntervalMonth(endDttm, gep, 0));
	$("#searchFrom").val(prevDttm);
}
</script>
</body>
