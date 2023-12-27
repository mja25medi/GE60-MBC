<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseVO" value="${vo}"/>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div id="listLayer" class="rowLayer">
					<form name="Search" onsubmit="return false" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="input-group" style="width:360px;">
							<select name="searchKey" id="searchKey" class="form-control input-sm" style="float:left;width: 50%;">
								<option value="crsNm">회차명</option>
							</select>
							<input type="text" name="searchValue" id="searchValue" style="float:left;width: 50%;" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="<spring:message code="common.title.all"/>"/>
							<span class="input-group-addon" onclick="listCourse()" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 text-right">
						<button class="btn btn-primary btn-sm" onclick="crsWriteForm('')">회차<spring:message code="button.write"/></button>
						<select name="listScale" id="listScale" onchange="listCourse(1)" class="form-control input-sm">
							<option value="10">10</option>
							<option value="20" selected="selected">20</option>
							<option value="40">40</option>
							<option value="60">60</option>
							<option value="80">80</option>
							<option value="100">100</option>
							<option value="200">200</option>
						</select>
					</div>
					<input type="submit" value="submit" style="display:none" />
					</form>
					<div class="col-md-12">
						<div id="courseList" style="margin-top:5px;">
							<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:60px"/>
									<col style="width:auto"/>
									<col style="width:auto;min-width:110px;"/>
									<col style="width:auto;min-width:110px;"/>
									<col style="width:auto;min-width:90px;"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순서</th>
										<th scope="col">회차명</th>
										<th scope="col">운영기간</th>
										<th scope="col">자격증 신청 기간</th>
										<th scope="col">개설 과정 수</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="8"><spring:message code="course.message.course.select.category"/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div id="workLayer" class="rowLayer">
					<div class="col-md-12" id="workBody">

					</div>
				</div>
			</div>
		</div>
	</div>
	
</section>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = {
			sortKey : ""
		};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		ItemDTO.sortKey = "CRS_NM_DESC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listCourse(1);
			}
		}
		listCourse(1);
		showArea('list');
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function showArea(str) {
		$(".rowLayer").hide();
		$("#"+str+"Layer").show();
	}

	function closeWriteArea(){
		$("#workBody").html("");
		showArea('list');
	}

	/**
	 * 과정 목록 조회
	 */
	function listCourse(page) {
		var searchKey = $('#searchKey').val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();

		$('#courseList')
			.load(cUrl("/mng/course/certification/listCertification"),
				{ 
				  "sortKey":ItemDTO.sortKey,
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "curPage":page,
				  "listScale":listScale
				 }
			);

	}

	/**
	 * 과정 등록 폼
	 */
	function crsWriteForm() {
		var addContent  = "<iframe id='certInfoFrame' name='certInfoFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/certification/addFormCertCoursePop"/>'>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(650, 400);
		modalBox.setTitle("회차 등록");
		modalBox.show();
	}

	/**
	 * 과정 수정 폼
	 */
	function crsEditForm(crsCd) {
		var addContent  = "<iframe id='certInfoFrame' name='certInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/certification/editFormCertCoursePop"/>"
			+ "?crsCd="+crsCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(650, 400);
		modalBox.setTitle("회차 수정");
		modalBox.show();
	}

	/**
	 * 과정 관리 폼
	 */
	function crsMngForm(crsCd) {
		var url = cUrl("/mng/course/certification/mngCourse");
		$("#workBody")
			.load(url, {
				"crsCd" : crsCd
			});
		showArea('work');
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listCourse(1);
	}
</script>
