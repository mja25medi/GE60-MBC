<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#statusTable th {background-color: #eee;}
</style>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="col-md-12">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
							<table  class="table table-bordered wordbreak" id="statusTable">
								<colgroup>
									<col style="width: 10%" />
									<col style="width: 40%" />
									<col style="width: 10%" />
									<col style="width: 40%" />
								</colgroup>
								<tr>
									<th scope="row">기수</th>
									<td>
										<select name="crsCd" id="crsCd" class="form-control input-sm" style="float:left;" onchange="listStatus();">
											<option value="">기수를 선택하세요</option>
											<c:forEach items="${courseList }" var="courseItem">
												<option value="${courseItem.crsCd }" <c:if test="${eduCourseStatusVO.crsCd eq courseItem.crsCd }">selected</c:if>>${courseItem.crsYear }년도 ${courseItem.crsTerm }기수</option>
											</c:forEach>
										</select>
									</td>
									<th scope="row">기업</th>
									<td>
										<select name="deptCd" id="deptCd" class="form-control input-sm" style="float:left;" onchange="listStatus();">
											<option value="">기업을 선택하세요</option>
											<c:forEach items="${deptList }" var="deptItem">
												<option value="${deptItem.deptCd }" <c:if test="${eduCourseStatusVO.deptCd eq deptItem.deptCd }">selected</c:if>>${deptItem.deptNm }</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th scope="row">과정</th>
									<td colspan="3">
										<select name="sbjCd" id="sbjCd" class="form-control input-sm" style="float:left;" onchange="listStatus();">
											<option value="">과정을 선택하세요</option>
											<c:forEach items="${subjectList }" var="subjectItem">
												<option value="${subjectItem.sbjCd }" <c:if test="${eduCourseStatusVO.sbjCd eq subjectItem.sbjCd }">selected</c:if>>${subjectItem.sbjNm }</option>
											</c:forEach>
										</select>
									</td>
							  </tr>
							  <tr>
								<th scope="row">개강일</th>
								<td colspan="3">
									<div class="input-group" style="float:left;">
										<input type="text" maxlength="10" name="enrlStartDttmStart" id="enrlStartDttmStart" class="inputDate form-control input-sm"/>
										<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttmStart')" style="cursor:pointer">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
									<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
									<div class="input-group" style="float:left;">
										<input type="text" maxlength="10" name="enrlStartDttmEnd" id="enrlStartDttmEnd" class="inputDate form-control input-sm"/>
										<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttmEnd')" style="cursor:pointer">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
									<meditag:datepicker name1="enrlStartDttmStart" name2="enrlStartDttmEnd" />
								</td>
							</tr>
							<tr>
								<th scope="row">종강일</th>
								<td colspan="3">
									<div class="input-group" style="float:left;">
										<input type="text" maxlength="10" name="enrlEndDttmStart" id="enrlEndDttmStart" class="inputDate form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttmStart')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
									</div>
									<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
									<div class="input-group" style="float:left;">
										<input type="text" maxlength="10" name="enrlEndDttmEnd" id="enrlEndDttmEnd" class="inputDate form-control input-sm"/>
										<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttmEnd')" style="cursor:pointer">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
									<meditag:datepicker name1="enrlEndDttmStart" name2="enrlEndDttmEnd" />
								</td>
							</tr>
							<tr>
								<th scope="row">검색조건</th>
								<td colspan="3">
									<select name="searchKey" id="searchKey" class="form-control" style="float:left;width:200px;margin-right:5px">
										<!-- <option value="all">- 전체 -</option> -->
										<option value="crsNm">기수명</option>
										<option value="deptNm">기업명</option>
										<option value="crsCreNm">과정명</option>
									</select>
									<input type="text" class="form-control" name="searchValue" id="searchValue" placeholder="" style="float:left;width:300px;margin-right:5px">
									<a class="btn btn-default btn-sm" href="javascript:listStatus();">검색 </a>
								</td>
							</tr>
						</table>
						<div class="input-group" style="float:right">
							<div class="form-group">
								<a href="javascript:excelDownload('list')" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> 리스트<spring:message code="button.download.excel"/> </a>
								<a href="javascript:excelDownload('detail')" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> 상세정보<spring:message code="button.download.excel"/> </a>
							</div>	
						</div>
					</form>
				</div>
				<div class="col-md-12">
					<div id="statusListArea"></div>
				</div>
			</div>
	
				<form name="eduCourseStatusFrom" id="eduCourseStatusFrom">
					<input type="hidden" name="crsCd"/>
					<input type="hidden" name="deptCd"/>
					<input type="hidden" name="sbjCd"/>
					<input type="hidden" name="enrlStartDttmStart"/>
					<input type="hidden" name="enrlStartDttmEnd"/>
					<input type="hidden" name="enrlEndDttmStart"/>
					<input type="hidden" name="enrlEndDttmEnd"/>
					<input type="hidden" name="searchKey"/>
					<input type="hidden" name="searchValue"/>
				</form>
				

			</div>
		</div>
	</div>
</section>				
<script type="text/javascript">

	var ItemDTO = new Object();
	var modalBox = null;

	$(document).ready(function() {
		ItemDTO.sortKey = "";
		listStatus();
		
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listStatus(1);
	}

	function listStatus() {
		var crsCd = $("#crsCd option:selected").val();
		var deptCd = $("#deptCd option:selected").val();
		var sbjCd = $("#sbjCd option:selected").val();
		var enrlStartDttmStart  = $('input[name=enrlStartDttmStart]').val();
		var enrlStartDttmEnd  = $('input[name=enrlStartDttmEnd]').val();
		var enrlEndDttmStart  = $('input[name=enrlEndDttmStart]').val();
		var enrlEndDttmEnd  = $('input[name=enrlEndDttmEnd]').val();
		var searchKey = $("#searchKey option:selected").val();
		var searchValue  = $("#searchValue").val();

		$("#statusListArea")
		.load(
			cUrl("/mng/stats/eduCourse/listCourseStatus"), {
			"logEduCourseStatusVO.crsCd":crsCd,
			"logEduCourseStatusVO.deptCd":deptCd,
			"logEduCourseStatusVO.sortKey":ItemDTO.sortKey,
			"logEduCourseStatusVO.sbjCd" : sbjCd,
			"logEduCourseStatusVO.enrlStartDttmStart" : enrlStartDttmStart,
			"logEduCourseStatusVO.enrlStartDttmEnd" : enrlStartDttmEnd,
			"logEduCourseStatusVO.enrlEndDttmStart" : enrlEndDttmStart,
			"logEduCourseStatusVO.enrlEndDttmEnd" : enrlEndDttmEnd,
			"logEduCourseStatusVO.searchKey" : searchKey,
			"logEduCourseStatusVO.searchValue" : searchValue
		});
	}
	
	
	function excelDownload(status) {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}

		var url = "";
		if(status == "list"){
			url = "/mng/stats/eduCourse/listExcelDownload";
		}else if(status == "detail"){
			url = "/mng/stats/eduCourse/listExcelDownloadStdResult";
		}
		
		var crsCd = $("#crsCd option:selected").val();
		var deptCd = $("#deptCd option:selected").val();
		var sbjCd = $("#sbjCd option:selected").val();
		var enrlStartDttmStart  = $('input[name=enrlStartDttmStart]').val();
		var enrlStartDttmEnd  = $('input[name=enrlStartDttmEnd]').val();
		var enrlEndDttmStart  = $('input[name=enrlEndDttmStart]').val();
		var enrlEndDttmEnd  = $('input[name=enrlEndDttmEnd]').val();
		var searchKey = $("#searchKey option:selected").val();
		var searchValue  = $("#searchValue").val();
		
		// 폼에 action을 설정하고 submit시킨다.
		$("#eduCourseStatusFrom").attr('target', '_m_download_iframe');
		$("#eduCourseStatusFrom").find('input[name=crsCd]').val(crsCd);
		$("#eduCourseStatusFrom").find('input[name=deptCd]').val(deptCd);
		$("#eduCourseStatusFrom").find('input[name=sbjCd]').val(sbjCd);
		$("#eduCourseStatusFrom").find('input[name=enrlStartDttmStart]').val(enrlStartDttmStart);
		$("#eduCourseStatusFrom").find('input[name=enrlStartDttmEnd]').val(enrlStartDttmEnd);
		$("#eduCourseStatusFrom").find('input[name=enrlEndDttmStart]').val(enrlEndDttmStart);
		$("#eduCourseStatusFrom").find('input[name=enrlEndDttmEnd]').val(enrlEndDttmEnd);
		$("#eduCourseStatusFrom").find('input[name=searchKey]').val(searchKey);
		$("#eduCourseStatusFrom").find('input[name=searchValue]').val(searchValue);
		$("#eduCourseStatusFrom").attr("action",url);
		$("#eduCourseStatusFrom").submit();
		$("#eduCourseStatusFrom").removeAttr('target');
	}
	

	function stdEduResltView(crsCreCd){
		var addContent = "<iframe id='stdEduResltFrame' name='stdEduResltFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/eduResult/listResultPop"/>"
				+ "?crsCreCd="+crsCreCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1200, 750);
		modalBox.setTitle("학습관리");
		modalBox.show();
	}
	
</script>


