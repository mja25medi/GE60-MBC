<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div id="displayArea">
				
				
					<div class="col-md">
						<div class="panel panel-default">
						<div class="panel-heading">
						<div class="panel-body" style="padding:0px;">
						
							<div class="input-group" style="float:left">
								<select id="ctgrCd" name="ctgrCd" class="form-control" style="width: 600px;" onchange="subList()" class="form-control input-sm">
									<option value = ""><spring:message code="course.title.exambank.select.category"/> </option>
								</select>
							</div>						
							<div class="pull-right" >
								<button class="btn btn-primary btn-sm" id="writeBtn" style="float: right;margin-right: 5px;" onclick="editFormCategory()"><spring:message code="course.title.exambank.category.edit"/></button>
								<button class="btn btn-primary btn-sm" id="writeBtn" style="float: right;margin-right: 5px;" onclick="addFormCategory()"><spring:message code="course.title.exambank.category.wirte"/></button>
							</div>
						</div>
						</div>
						</div>
					</div>		
					
							
					<div class="col-md-5 col-xs-18">
						<div class="panel panel-default">
							<div class="panel-heading">
								<spring:message code="course.title.exambank.subcategory.list"/>
								<div class="pull-right" >
									<button class="btn btn-primary btn-sm" id="writeBtn" style="float: right; margin-right: 5px; margin-top:-4px;" onclick="subAddFormCategory()"><spring:message code="course.title.exambank.subcategory.wirte"/></button>
								</div>
							</div>
							<div class="panel-body" style="padding:0px;">
								<div id="ctgrList" style="width:100%; height:600px; overflow:auto; background-color:white; padding:5px; line-height:20px; margin-top:5px;">
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-7 col-xs-18">
						<form name="Search" onsubmit="return false" class="form-inline">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span id="contentsTitle"><spring:message code="course.title.exambank.category.manage"/></span>
								<div style="clear: both;"></div>
							</div>
							<div class="panel-body" id="contentsList" style="width:100%;height:600px; padding:5px;line-height:20px;">
									<ul class="list-group" style="display : none">
										<li class="list-group-item"></li>
									</ul>
							</div>
						</div>
						</form>
					</div>
				</div>
				<div id="workArea" style="dispaly:none" class="col-md-12">

				</div>
			</div>
		</div>
	</div>
</section>
<c:if test="${not empty ALERT_MESSAGE}">
	<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
	<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
	<script type="text/javascript">
		$(document).ready(function() {
			alert('${flashMsg}');
		});
	</script>
</c:if>	
<script type="text/javascript">
var ItemDTO = new Object();

	$(document).ready(function() {
		$("#contentsTitle").html("<spring:message code="course.title.exambank.category.manage"/>");
		listCategory();
	});

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
	분류 관리 리스트를 가져온다
	*/
		function listCategoryCallback(ProcessResultListDTO) {
			ItemDTO.ctgrCd = $("#ctgrCd option:selected").val();
			if(ItemDTO.ctgrCd == ''  && '${sessionScope.SearchVO.ExamQbankMain.searchCd}' != ''){
				ItemDTO.ctgrCd = '${sessionScope.SearchVO.ExamQbankMain.searchCd}';
			}
			$("#ctgrCd option").remove();
			$("#ctgrCd").append("<option value=''><spring:message code="course.title.exambank.select.category"/></option>");
			var itemList = ProcessResultListDTO.returnList;
			for (var i=0; i<itemList.length; i++) {
				var item = itemList[i];
				var selected = "";
				if(ItemDTO.ctgrCd == item.ctgrCd){
					selected = "selected='selected'";
				}
				
				$("#ctgrCd").append("<option value='"+item.ctgrCd+"' "+selected+">"+item.ctgrNm+"</option>");
			}
			subList();
		}
		
	/**
	 *상위 과정 분류 등록 폼
	 */
	function addFormCategory() {
	 	$("#contentsList").empty();
		$("#contentsList").load(cUrl("/mng/course/examQbank/addFormCategory"));
	}

	/**
	 * 상위 과정 분류 수정 폼
	 */
	function editFormCategory() {
		var ctgrCd = $("#ctgrCd option:selected").val();
		if(ctgrCd == "") {
			alert("<spring:message code="course.message.exambank.category.alert.select.category.edit"/>");
			return;
		}
		$("#contentsList").load(cUrl("/mng/course/examQbank/editFormCategory"), { "ctgrCd": ctgrCd});		
	}
	
	
	/**
	 *상위 과정 분류 등록
	 */
	function addCategory() {
		var f = document.examQbankForm;
		if(jsByteLength(f["ctgrNm"].value) <= 0) {
			alert("<spring:message code="course.message.exambank.insert.category.name"/>");
			return;
		}
		processCategory("addCategory");
	}	

	/**
	 *상위 과정 분류 수정
	 */
	function editCategory() {
		var f = document.examQbankForm;
		if($("#ctgrCd").val() != "") {
			processCategory("editCategory");
		}
	}	
	
	/**
	 * 상위 분류 항목 삭제
	 */
	function deleteCategory() {
		var ctgrCd = $("#ctgrCd").val();
		var f = document.examQbankForm;
		if(ctgrCd == "") {
			alert("<spring:message code="course.message.exambank.select.category"/>");
			return;
		}
		
		
		if(confirm('<spring:message code="course.message.exambank.confirm.delete.category"/>') && confirm('항목에 등록된 하위 분류, 모든 시험 문제가 같이 삭제됩니다.\n\n정말로 삭제하시겠습니까?')) {
			processCategory("deleteCategory");
			$("#contentsList").empty();
		}
	}
	
	/**
	 * 하위 분류 항목 삭제
	 */
	function subDeleteCategory() {
		var ctgrCd = $("#ctgrCd").val();
		var f = document.examQbankForm;
		if(ctgrCd == "") {
			alert("<spring:message code="course.message.exambank.select.category"/>");
			return;
		}
		if(confirm('삭제 하시겠습니까')) {
			processCategory("deleteCategory");
			$("#contentsList").empty();
		}
	}
	/**
	 *과정 분류 등록 폼
	 */
	function subAddFormCategory() {
		var ctgrCd = $("#ctgrCd option:selected").val();
		if(ctgrCd == "") {
//			alert("<spring:message code="course.message.exambank.category.alert.select.category.edit"/>");
			alert("상위 분류 목록을 선택해 주십시요");
			return;
		}
	 	$("#contentsList").empty();
		$("#contentsList").load(cUrl("/mng/course/examQbank/subAddFormCategory"), { "ctgrCd": ctgrCd});
	}	

	/**
	 *과정 분류 등록, 수정
	 */
	function subAddCategory() {
		var f = document.examQbankForm;
		if(jsByteLength(f["ctgrNm"].value) <= 0) {
			alert("<spring:message code="course.message.exambank.insert.category.name"/>");
			return;
		}
			processCategory("subAddCategory");
	}	

	/**
	 *과정 분류 수정 폼
	 */
	function subEditFormCategory(ctgrCd) {
		if(ctgrCd == "") {
//			alert("<spring:message code="course.message.exambank.category.alert.select.category.edit"/>");
			alert("상위 분류 목록을 선택해 주십시요");
			return;
		}
		$("#contentsList").empty();
		$("#contentsList").load(cUrl("/mng/course/examQbank/subEditFormCategory"), { "ctgrCd": ctgrCd});			
		
		
	}	
	
	/**
	 *과정 분류 등록, 수정
	 */
	function subEditCategory() {
		var f = document.examQbankForm;
		if(jsByteLength(f["ctgrNm"].value) <= 0) {
			alert("<spring:message code="course.message.exambank.insert.category.name"/>");
			return;
		}
			processCategory("subEditCategory");
	}	
	/**
	 *창 닫기
	 */
	function closeWriteArea() {
		$("#contentsList").empty();
	}
	
	/**
	 * 하위 분류 리스트를 가져온다.
	 */
	function subList() {
		var parCtgrCd = $("#ctgrCd option:selected").val();
		$("#ctgrList").empty();
		$("#ctgrList").load(cUrl("/mng/course/examQbank/subList"), { "parCtgrCd": parCtgrCd});	
	}	
	
	/**
	 * 서브밋 처리
	 */
	function processCategory(cmd) {
		if(!validate(document.examQbankForm)) return;
		
		$('#examQbankForm').attr("action","/mng/course/examQbank/" + cmd);
		$('#examQbankForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		var dto = resultDTO.returnVO;
		if(resultDTO.result >= 0) {
			// 정상 처리
			listCategory();
			closeWriteArea();
		} else {
			// 비정상 처리
		}
	}	
	
</script>
