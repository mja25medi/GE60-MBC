<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<spring:message code="course.title.exambank.manage"/>
							<div class="pull-right">
								<a href="<c:url value="/mng/main/goMenuPage"/>?mcd=${MENUCODE}" class="btn btn-default btn-xs"><spring:message code="button.list"/></a>
							</div>
						</div>
						<div class="panel-body">
							<div class="row" id="CategoryList">
								<div class="col-lg-12">
									<form id="examQbankForm" name="examQbankForm" onsubmit="return false">
									<div class="well well-sm">
										<div class="input-group" style="float:left;width:50%">
											<select id="ctgrCd" name="selCtgrCd" style="form-control" onchange="listQuestion('1')" class="form-control input-sm">
												<option value = ""><spring:message code="course.title.exambank.select.category"/> </option>
											</select>
										</div>
										<div class="text-right">
											<a href="javascript:addFormCategory()" class="btn btn-primary btn-sm"><spring:message code="button.write.category"/> </a>
											<a href="javascript:editFormCategory()" class="btn btn-primary btn-sm"><spring:message code="button.edit.category"/> </a>
											<a href="javascript:deleteCategory()" class="btn btn-warning btn-sm"><spring:message code="button.delete.category"/> </a>
										</div>
									</div>
								</div>
							</div>
							<div id="CategoryWrite" style="display:none" class="row">
								<div class="col-lg-12">
									<input type="hidden" name="ctgrCd" value="${vo.ctgrCd }"/>
									<input type="hidden" name="sbjCd" value="${vo.sbjCd }"/>

									<div class="well well-sm">
										<div class="input-group" style="float:left;width:50%">
											<input type="text" dispName="<spring:message code="course.title.exambank.category"/>" maxlength="50" isNull="N"  name="ctgrNm" value="${vo.ctgrNm }" class="form-control input-sm"/>
										</div>
										<div class="text-right">
											<a href="javascript:addCategory()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
											<a href="javascript:WriteCategoryCancel()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/> </a>
										</div>
									</div>
								</div>
							</div>
							</form>
							<div class="row">
								<div class="col-lg-12">
									<div class="text-right">
										<a href="javascript:writeQuestion('J')" class="btn btn-primary btn-sm"><meditag:codename code="J" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestion('K')" class="btn btn-primary btn-sm"><meditag:codename code="K" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestion('S')" class="btn btn-primary btn-sm"><meditag:codename code="S" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestion('D')" class="btn btn-primary btn-sm"><meditag:codename code="D" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestionExcel()" class="btn btn-info btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
									</div>
									<div id="qstnList" style="margin-top:5px;">
										<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:50px;">
												<col style="width:auto;">
												<col style="width:80px;">
												<col style="width:50px;">
											</colgroup>
											<thead>
												<tr>
													<th scope="col"><spring:message code="common.title.no"/></th>
													<th scope="col"><spring:message code="lecture.title.exam.question"/></th>
													<th scope="col"><spring:message code="lecture.title.exam.question.type"/></th>
													<th scope="col"><spring:message code="common.title.manage"/></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td colspan="4"><spring:message code="lecture.message.exam.question.nodata"/></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	var ItemDTO = new Object();
	var modalBox = null;
	//---- 페이지 초기화 함수
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		ItemDTO.sbjCd = "${vo.sbjCd }";
		listCategory();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listCategory() {
		$.getJSON( cUrl("/mng/course/examQbank/listCategory"),		// url
			{ 
			  sbjCd : ItemDTO.sbjCd
			},			// params
			listCategoryCallback				// callback function
		);
	}

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listCategoryCallback(ProcessResultListDTO) {
		$("#ctgrCd option").remove();
		$("#ctgrCd").append("<option value=''><spring:message code="course.title.exambank.select.category"/></option>");
		var itemList = ProcessResultListDTO.returnList;
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];
			var selected = "";
			if(ItemDTO.ctgrCd == item.ctgrCd) selected = "selected='selected'";
			$("#ctgrCd").append("<option value='"+item.ctgrCd+"' "+selected+">"+item.ctgrNm+"</option>");
		}
		listQuestion('1');
	}

	/**
	 * 항목 추가 화면 켄슬
	 */
	function WriteCategoryCancel() {
		$("#CategoryList").show();
		$("#CategoryWrite").hide();
	}

	/**
	 * 항목 등록 화면 호출
	 */
	function addFormCategory() {
		$("#CategoryList").hide();
		$("#CategoryWrite").show();

		$("#ctgrCd").val("");
		listQuestion('1');

		var f = document.examQbankForm;
		f["ctgrCd"].value = "";
		f["ctgrNm"].value = "";
	}

	/**
	 * 항목 수정 화면 호출
	 */
	function editFormCategory() {
		var ctgrCd = $("#ctgrCd option:selected").val();
		if(ctgrCd == "") {
			alert("<spring:message code="course.message.exambank.category.alert.select.category.edit"/>");
			return;
		}
		var listObj = document.getElementById("CategoryList");
		listObj.style.display = "none";
		var WriteObj = document.getElementById("CategoryWrite");
		WriteObj.style.display = "block";
		$.getJSON( cUrl("/mng/course/examQbank/viewCategory"),		// url
			{
			  sbjCd : ItemDTO.sbjCd,
			  ctgrCd : ctgrCd
			},			// params
			editFormCategoryCallback				// callback function
		);
	}

	/**
	 * 항목 수정 화면 호출 Callback
	 */
	function editFormCategoryCallback(ProcessResultDTO) {
		var item = ProcessResultDTO.returnVO;
		var f = document.examQbankForm;
		f["ctgrCd"].value = item.ctgrCd;
		f["ctgrNm"].value = item.ctgrNm;
	}

	/**
	 * 항목 등록
	 */
	function addCategory() {
		var f = document.examQbankForm;
		if(jsByteLength(f["ctgrNm"].value) <= 0) {
			alert("<spring:message code="course.message.exambank.insert.category.name"/>");
			return;
		}
		if(f["ctgrCd"].value != "") {
			processCategory("editCategory");
		} else {
			processCategory("addCategory");
		}
	}

	/**
	 * 항목 삭제
	 */
	function deleteCategory() {
		var ctgrCd = $("#ctgrCd option:selected").val();
		var f = document.examQbankForm;
		if(ctgrCd == "") {
			alert("<spring:message code="course.message.exambank.select.category"/>");
			return;
		}
		if(confirm('<spring:message code="course.message.exambank.confirm.delete.category"/>')) {
			ItemDTO.ctgrCd = "";
			f["selCtgrCd"].value = ctgrCd;
			processCategory("deleteCategory");
		}
	}

	/**
	 * 카테고리 서브밋 처리
	 */
	function processCategory(cmd) {
		//if(!validate(document.examQbankForm)) return;
		// --- 실행 함수에서 벨리데이트 확인 할것.
		
		$('#examQbankForm').attr("action","/mng/course/examQbank/" + cmd);
		if(cmd != "deleteCategory") {
			$('#examQbankForm').ajaxSubmit(processCategoryCallback);
		} else {
			$('#examQbankForm').ajaxSubmit(processCategoryDeleteCallback);
		}
	}

	/**
	 * 카테고리 처리 결과 표시 콜백
	 */
	function processCategoryCallback(resultDTO) {
		alert(resultDTO.message);
		var dto = resultDTO.returnVO;
		ItemDTO.ctgrCd = dto.ctgrCd;
		if(resultDTO.result >= 0) {
			// 정상 처리
			WriteCategoryCancel();
			listCategory();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 카테고리 처리 결과 표시 콜백
	 */
	function processCategoryDeleteCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			WriteCategoryCancel();
			listCategory();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 문제 리스틀 가져온다.
	 */
	function listQuestion(page) {
		$(".workArea").hide();
		$("#qstnListArea").show();
		var ctgrCd = $("#ctgrCd > option:selected").val();
		$("#qstnList").load(
			cUrl("/mng/course/examQbank/listQuestion"),
			{
				"sbjCd" : ItemDTO.sbjCd,
				"ctgrCd" : ctgrCd,
				"curPage" : page
			}
		);
	}

	/**
	 * 시험 문제 등록
	 */
	function writeQuestion(type) {
		var ctgrCd = $("#ctgrCd > option:selected").val();
		if(isEmpty(ctgrCd)) {
			alert("<spring:message code="lecture.messgae.exam.question.alert.select.category"/>");
			return;
		}
		var addContent  = "<iframe id='addQuestionFrame' name='addQuestionFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/examQbank/addFormQuestionPop"/>"
			+ "?sbjCd="+ItemDTO.sbjCd+"&amp;ctgrCd="+ctgrCd+"&amp;qstnType="+type+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 750);
		modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		modalBox.show();
	}

	/**
	 * 시험 문제 수정
	 */
	function editQuestion(qstnSn) {
		var ctgrCd = $("#ctgrCd > option:selected").val();
		var addContent  = "<iframe id='addQuestionFrame' name='addQuestionFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/examQbank/editQuestionPop"/>"
			+ "?sbjCd="+ItemDTO.sbjCd+"&amp;ctgrCd="+ctgrCd+"&amp;qstnSn="+qstnSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 750);
		modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		modalBox.show();
	}
	/**
	 * 시험 문제 미리보기
	 */
	function examBankPreview(sbjCd, ctgrCd, qstnSn){
		var addContent  = "<iframe id='examBankPaperFrame' name='examBankPaperFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/examQbank/previewPaperPop"/>"
			+ "?sbjCd="+sbjCd+"&amp;ctgrCd="+ctgrCd+"&amp;qstnSn="+qstnSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1100, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question"/> <spring:message code="button.question.preview"/>");
		parent.modalBox.show();
	}

	function writeQuestionExcel(){
		var ctgrCd = $("#ctgrCd > option:selected").val();
		if(isEmpty(ctgrCd)) {
			alert("<spring:message code="lecture.messgae.exam.question.alert.select.category"/>");
			return;
		}
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/examQbank/addQuestionExcelPop"/>"
			+ "?sbjCd="+ItemDTO.sbjCd+"&amp;ctgrCd="+ctgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		modalBox.show();
	}

</script>
