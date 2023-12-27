<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<section class="content">
<div class="box">
<div class="box-body">
			<div class="row" id="content">
				<div class="col-md-12">
					<div class="well well-sm" style="width:100%;">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<select name="orgCd" id="orgCd" class="form-control input-sm" onchange="changeOption()">
							<c:forEach var="item" items="${orgInfoList}">
							<option value="${item.orgCd}">${item.orgNm}</option>
							</c:forEach>
						</select>
						<select name="year" id="year"  class="form-control input-sm" onchange="changeOption()">
							<option value="">연도 전체</option>
							<c:forEach var="year" items="${yearList}">
							<option value="${year}">${year}</option>
							</c:forEach>
						</select>
						<div class="input-group" style="width:200px;">
							<select name="crsCd" id="crsCd" class="form-control input-sm" onchange="listStatus()"></select>
						</div>
						<div class="input-group" style="width:300px;">
							<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="개설과정명으로 검색하세요."/>
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
					<div id="statusListArea">
					</div>
				</div>
			</div>
			<form action="/adm/eduCourseStatus/listExcelDownload" name="eduCourseStatusFrom" id="eduCourseStatusFrom">
				<input type="submit" value="submit" style="display:none" />
				<input type="hidden" name="creYear" id="creYear"/>
				<input type="hidden" name="orgCd"/>
				<input type="hidden" name="crsCd"/>
				<input type="hidden" name="searchKey"/>
				<input type="hidden" name="searchValue"/>
			</form>
			</div>
		</div>
</section>
<script type="text/javascript">

	var ItemVO = new Object();
	var searchKey = "crsCreNm";

	$(document).ready(function() {
		ItemVO.sortKey = "CRE_TERM_ASC";
		changeOption();
	});

	function setSortKey(key) {
		ItemVO.sortKey = key;
		listStatus(1);
	}

	function listStatus() {

		var creYear = $("#year option:selected").val();
		var orgCd = $("#orgCd option:selected").val();
		var crsCd = $("#crsCd option:selected").val();
		
		var searchValue = $('#searchValue').val();
		
		$("#statusListArea")
		.load(
			cUrl("/adm/eduCourseStatus/listCourseStatus"), {
			"logEduCourseStatusVO.searchKey":searchKey,
			"logEduCourseStatusVO.searchValue":searchValue,
			"logEduCourseStatusVO.sortKey":ItemVO.sortKey,
			"logEduCourseStatusVO.crsCd": crsCd,
			"logEduCourseStatusVO.orgCd": orgCd,
			"logEduTeamStatusVO.creYear" : creYear,
		});

	}
	
	function changeOption() {
		selectOrg();
		listStatus();
	}
	
	function selectOrg() {

		var orgCd = $("#orgCd option:selected").val();
		var crsYear = $("#year option:selected").val();
		
		$.getJSON(cUrl("/adm/eduCourseStatus/listCourse"), {
			"orgCd" : orgCd,
			"crsYear" : crsYear
		}, function(data) {
			var crsList = data.returnList;
			var str = '<option value="">기수 전체</option>';
			crsList.forEach(function(crs) {
				str = str + '<option value=' + crs.crsCd+ '>' + crs.crsNm + '</option>';
			});
			$("#crsCd").html(str);
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
		var orgCd = $("#orgCd option:selected").val();
		var crsCd = $("#crsCd option:selected").val();
		
		var searchValue = $('#searchValue').val();
		// 폼에 action을 설정하고 submit시킨다.
		$("#eduCourseStatusFrom").attr('target', '_m_download_iframe');
		$("#eduCourseStatusFrom").find('input[name=orgCd]').val(orgCd);
		$("#eduCourseStatusFrom").find('input[name=creYear]').val(creYear);
		$("#eduCourseStatusFrom").find('input[name=searchKey]').val(searchKey);
		$("#eduCourseStatusFrom").find('input[name=searchValue]').val(searchValue);
		$("#eduCourseStatusFrom").find('input[name=crsCd]').val(crsCd);
		$("#eduCourseStatusFrom").submit();
		$("#eduCourseStatusFrom").removeAttr('target');

	}

</script>


