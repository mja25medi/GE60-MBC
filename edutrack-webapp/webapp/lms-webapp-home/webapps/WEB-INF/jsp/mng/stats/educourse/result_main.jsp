<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="col-md-12">
						<div class="well well-sm" style="float:left;width:100%;">
							<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
							<div class="input-group" style="float:left;">
								<select name="year" id="year" class="form-control input-sm">
									<c:forEach var="year" items="${yearList}">
									<option value="${year}" <c:if test="${year eq curYear}">selected</c:if>>${year}</option>
									</c:forEach>
								</select>
							</div>
							<div class="input-group" style="width:180px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>"/>
								<span class="input-group-addon" onclick="listStatus()" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
							<div class="input-group" style="float:right">
								<div class="form-group">
									<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.download.excel"/> </a>
								</div>
							</div>
							</form>
						</div>
					</div>
					<div class="col-md-12">
						<div id="statusListArea"></div>
					</div>
				</div>
				<form action="/mng/stats/eduCourse/listExcelDownloadResult" name="eduCourseStatusFrom" id="eduCourseStatusFrom">
				<input type="hidden" name="creYear" id="creYear"/>
				</form>
			</div>
		</div>
	</div>
</section>				
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.sortKey = "CRS_NM_ASC";
		listStatus();
	});

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listStatus(1);
	}

	function listStatus() {
		var creYear = $("#year option:selected").val();
		var searchValue = $('#searchValue').val();
		$("#statusListArea")
		.load(
			cUrl("/mng/stats/eduCourse/listCourseResult"), {
			"logEduCourseStatusVO.searchValue":searchValue,
			"logEduCourseStatusVO.sortKey":ItemDTO.sortKey,
			"logEduTeamStatusVO.creYear" : creYear
		});
	}


	function excelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}

		var creYear = $("#year option:selected").val();

		// 폼에 action을 설정하고 submit시킨다.
		$("#eduCourseStatusFrom").attr('target', '_m_download_iframe');
		$("#creYear").val(creYear);
		$("#eduCourseStatusFrom").submit();
		$("#eduCourseStatusFrom").removeAttr('target');

	}

</script>


