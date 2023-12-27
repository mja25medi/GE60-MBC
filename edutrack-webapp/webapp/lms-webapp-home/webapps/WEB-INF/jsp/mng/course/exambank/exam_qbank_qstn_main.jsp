<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
			<div id="displayArea">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row" id="CategoryList">
									<form id="examQbankForm" name="examQbankForm" onsubmit="return false">
										<div class="input-group" style="float:left;width:20%">
											<select id="ctgrCd" name="selCtgrCd" class="form-control" onchange="subListCategory('1')" class="form-control input-sm">
												<option value = ""></option>
											</select>
										</div>
										<div class="input-group" style="float:left;width:30%">	
											<select id="parCtgrCd" name="parCtgrCd" class="form-control" onchange="listQuestion('1')" class="form-control input-sm">
												<option value = "">하위 분류</option>
											</select>
										</div>
										<div class="input-group" style="float:right;width:20%">
											<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="<spring:message code="common.title.all"/>" value="${vo.searchKey }"/>
											<span class="input-group-addon" onclick="listQuestion('1')" style="cursor:pointer">
												<i class="fa fa-search"></i>
											</span>
										</div>	
										<div class="input-group" style="float:right;width:10%">
											<select name="qstnType" id="qstnType" class="form-control" onchange="listQuestion('1')" class="form-control input-sm">
												<option value = "">문제유형</option>
												<option value="J" <c:if test="${vo.qstnType eq 'J'}">selected</c:if>><meditag:codename code="J" category="EXAM_QSTN_TYPE"/> </option>
												<option value="K" <c:if test="${vo.qstnType eq 'K'}">selected</c:if>><meditag:codename code="K" category="EXAM_QSTN_TYPE"/> </option>
												<option value="S" <c:if test="${vo.qstnType eq 'S'}">selected</c:if>><meditag:codename code="S" category="EXAM_QSTN_TYPE"/> </option>
												<option value="D" <c:if test="${vo.qstnType eq 'D'}">selected</c:if>><meditag:codename code="D" category="EXAM_QSTN_TYPE"/> </option>
											</select>
										</div>
									</form>
							</div>
							</div>
							<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<div class="text-right">
										<a href="javascript:writeQuestion('J')" class="btn btn-primary btn-sm"><meditag:codename code="J" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestion('K')" class="btn btn-primary btn-sm"><meditag:codename code="K" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestion('D')" class="btn btn-primary btn-sm"><meditag:codename code="D" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestion('S')" class="btn btn-primary btn-sm"><meditag:codename code="S" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/> </a>
										<a href="javascript:writeQuestionExcel()" class="btn btn-info btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
									</div>
									<div id="qstnList" style="margin-top:5px;">
										<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:5%"/>
												<col style="width:32%"/>
												<col style="width:12%"/>
												<col style="width:11%"/>
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
													<td colspan="6"><spring:message code="lecture.message.exam.question.nodata"/></td>
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
			<div id="workArea" style="dispaly:none" >

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
		// 셀렉트 박스 목록
		listCategory();
	
		/* if(('${examQbankQstnVO.parCtgrCd}' != "" || '${examQbankQstnVO.parCtgrCd}' != null) && ('${examQbankQstnVO.ctgrCd}' != "" || '${examQbankQstnVO.ctgrCd}' != null)) {
			subListCategory2('${examQbankQstnVO.ctgrCd}');
		}else{
			//시험문제 전체 목록
// 			listQuestion(1);
		} */
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 상위 분류 관리 목록 조회
	 */
	function listCategory() {
		$.getJSON( cUrl("/mng/course/examQbank/listCategory"),		// url
			{ 
			},			// params
			listCategoryCallback				// callback function
		);
	}

	/*
	상위 분류 관리 리스트를 가져온다
	*/
	function listCategoryCallback(ProcessResultListDTO) {
		$("#ctgrCd option").remove();
		$("#ctgrCd").append("<option value=''><spring:message code="course.title.exambank.select.category"/></option>");
		ItemDTO.ctgrCd = '${examQbankQstnVO.ctgrCd}';
		if(ItemDTO.ctgrCd == ''  &&  '${vo.ctgrCd}' != ''){
			ItemDTO.ctgrCd = '${vo.ctgrCd}';
		}
		var itemList = ProcessResultListDTO.returnList;
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];
			var selected = "";
			if(i==0 && ItemDTO.ctgrCd == '') selected = "selected='selected'";
			if(ItemDTO.ctgrCd == item.ctgrCd) selected = "selected='selected'";
			$("#ctgrCd").append("<option value='"+item.ctgrCd+"' "+selected+">"+item.ctgrNm+"</option>");
		}
		//listQuestion(1);
		subListCategory();
	}
	
	
	/**
	 * 하위 분류 목록 조회
	 */
	function subListCategory() {
		var parCtgrCd = $("#ctgrCd option:selected").val();
		var useYn = "Y";
		$.getJSON( cUrl("/mng/course/examQbank/subListCategory"),		// url
				{ 
					"parCtgrCd": parCtgrCd 
					,"useYn": useYn
				},			// params
				subListCategoryCallback
			);		
	}	
	
	function subListCategory2(parCtgrCd) {
		$.getJSON( cUrl("/mng/course/examQbank/subListCategory"),		// url
				{ 
					"parCtgrCd": parCtgrCd 
				},			// params
				subListCategoryCallback
			);		
	}

	/*
	하위 분류 관리 리스트를 가져온다
	*/
	function subListCategoryCallback(ProcessResultListDTO) {
		ItemDTO.parCtgrCd = '${examQbankQstnVO.parCtgrCd}';
		if(ItemDTO.parCtgrCd == ''  && '${vo.parCtgrCd}' != ''){
			ItemDTO.parCtgrCd = '${vo.parCtgrCd}';
		}
		$("#parCtgrCd option").remove();
		$("#parCtgrCd").append("<option value=''><spring:message code="course.title.exambank.select.subcategory"/></option>");
		var itemList = ProcessResultListDTO.returnList;
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];
			var selected = "";
			if(i==0 && ItemDTO.parCtgrCd == '') selected = "selected='selected'";
			if(ItemDTO.parCtgrCd == item.ctgrCd) selected = "selected='selected'";
			$("#parCtgrCd").append("<option value='"+item.ctgrCd+"' "+selected+">"+item.ctgrNm+"</option>");
		}
		listQuestion(1);
	}


	
	
	/**
	 * 시험 리스트를 가져온다.
	 */
	function listQuestion(page) {
		var ctgrCd = $("#parCtgrCd option:selected").val();
		var parCtgrCd = $("#ctgrCd option:selected").val();
		var qstnType = $("#qstnType option:selected").val();
		var searchKey = $("#searchKey").val();
		var useYn = "Y";
		
		$("#qstnList").empty();
		$("#qstnList").load(
				cUrl("/mng/course/examQbank/listQuestion"),
				{
					"ctgrCd" : ctgrCd,
					"parCtgrCd" : parCtgrCd,
					"qstnType"	: qstnType,
					"useYn"	: useYn,
					"searchKey" : searchKey,
					"curPage" : page
				}
			);
	}	
		
	
	
	
	
	
	
	/**
	 * 시험 문제 등록
	 */
	function writeQuestion(type) {
		var parCtgrCd = $("#parCtgrCd > option:selected").val();
		var ctgrCd = $("#ctgrCd option:selected").val();
		var searchKey = $("#searchKey").val();
		if(isEmpty(ctgrCd)) {
			alert("상위 분류를 선택해주세요");
			return;
		}		
		if(isEmpty(parCtgrCd)) {
			alert("하위 분류를 선택해주세요");
			return;
		}
		$("#workArea").empty();
		$("#workArea").load(cUrl("/mng/course/examQbank/addFormQuestion"),{"ctgrCd":ctgrCd, "parCtgrCd":parCtgrCd, "qstnType":type ,"searchKey" : searchKey});
		$("#displayArea").slideUp('slow');
		$("#workArea").show();			
			
	}

	/**
	 * 시험 문제 수정
	 */
	function editFormQuestion(qstnSn) {
		var ctgrCd = $("#parCtgrCd > option:selected").val();
		var parCtgrCd = $("#ctgrCd > option:selected").val();
		var qstnType = $("#qstnType > option:selected").val();
		var searchKey = $("#searchKey").val();
		$("#workArea").empty();
		$("#workArea").load(cUrl("/mng/course/examQbank/editFormQuestion"),{"ctgrCd":ctgrCd, "qstnSn":qstnSn, "parCtgrCd":parCtgrCd, "qstnType":qstnType ,"searchKey" : searchKey });
		$("#displayArea").hide();
		$("#workArea").show();	
	}	

	function closeWorkArea() {
		$("#workArea").empty();
		$("#displayArea").show();
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
		var parCtgrCd = $("#ctgrCd option:selected").val();
		var ctgrCd = $("#parCtgrCd option:selected").val();
		if(isEmpty(parCtgrCd)) {
			alert("상위 분류를 선택해주세요");
			return;
		}	
		if(isEmpty(ctgrCd)) {
			alert("하위 분류를 선택해 주세요.");
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
