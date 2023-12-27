<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form id="subjectForm" name="subjectForm" onsubmit="return false">
	<input type="hidden" name="sbjCtgrCd" value="${vo.sbjCtgrCd }" />
	<input type="hidden" name="sbjCtgrTypeCd" value="${vo.sbjCtgrTypeCd }" />
	<table summary="<spring:message code="course.title.subject.category.manage"/>" style="width:100%;height:100%">
		<tr>
			<td colspan="2" style="padding:5px;">
				<select name="parSbjCtgrCd" onChange="listCategorySub()" id="parSbjCtgrCd" class="form-control input-sm">
					<option value=''><spring:message code="course.title.category.root"/></option>
				<c:if test="${not empty subjectCategoryList}">
				<c:forEach var="item" items="${subjectCategoryList}">
					<option value='${item.sbjCtgrCd }'>${item.sbjCtgrNm }</option>
				</c:forEach>
				</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:65%;padding:5px;" valign="top">
				<select name="selSbjCtgrCd" id="selSbjCtgrCd" multiple style="height:220px" class="form-control input-sm"></select>
			</td>
			<td style="width:35%;padding:5px;" valign="top">
				<a href="javascript:moveUp()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.up"/> </a>
				<a href="javascript:moveDown()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.down"/> </a>
				<a href="javascript:sortCategory()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.add"/> </a>
			</td>
		</tr>
	</table>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		listCategorySub();
	});


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.subjectForm)) return;
		$('#subjectForm').attr("action","/mng/course/subject/"+cmd);
		$('#subjectForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.refreshCategoryList();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과목 분류 목록 조회
	 */
	function listCategorySub() {
		var f = document.subjectForm;
		var parSbjCtgrCd = f.parSbjCtgrCd[f.parSbjCtgrCd.selectedIndex].value;
		var sbjCtgrTypeCd = f["sbjCtgrTypeCd"].value;
		var selectBox = document.getElementById("selSbjCtgrCd");
		var len = selectBox.length;
		for(var i=len ; i >= 0; i--) {
			selectBox.options[0] = null;
		}

		$.getJSON( cUrl("/mng/course/subject/listCtgrSub"),		// url
				{ 
				  "parSbjCtgrCd" : parSbjCtgrCd,
				  "sbjCtgrTypeCd" : sbjCtgrTypeCd
				},			// params
				listCategorySubCallback				// callback function
			);
	}

	/**
	 * 과목 분류 목록 조회 Callback
	 */
	function listCategorySubCallback(ProcessResultListDTO) {
		var selectBox = document.getElementById("selSbjCtgrCd");
		var len = selectBox.length;
		var categoryList = ProcessResultListDTO.returnList;
		for (var i=0; i<categoryList.length; i++) {
			var item = categoryList[i];
			new_option = new Option(item.sbjCtgrNm, item.sbjCtgrCd);
			selectBox.options.add(new_option);
		}
	}

	/**
	 * 과목 분류 순서 변경
	 */
	function sortCategory() {
		var f = document.subjectForm;
		f["sbjCtgrCd"].value = "";

		var selectBox = document.getElementById("selSbjCtgrCd");
		var len = selectBox.length;

		if(selectBox.length == 0) {
			alert('<spring:message code="course.message.category.alert.sort.nodata"/>');
		} else {

			var categoryList = "";
			for(var i=0; i < len; i++) {
				if(i==0) {
					categoryList = selectBox.options[i].value;
				} else {
					categoryList = categoryList+'|'+selectBox.options[i].value;
				}
			}
			
			f["sbjCtgrCd"].value = categoryList;
			process("sortCtgr");	// cmd
		}
	}


	function moveUp() {
		moveOption('up');
	}

	function moveDown() {
		moveOption('down');
	}

	function moveOption(moveType) {
		var selectBox = document.getElementById("selSbjCtgrCd");
		var si =  selectBox.selectedIndex;
		if(si >= 0) {
			var len = selectBox.length;
			var siValue = selectBox.options[si].value;
			var siText = selectBox.options[si].text;
			var moveYn = "Y";
			var ti = null;
			if(moveType == 'up' && si != 0) {
				ti = si-1;
			} else if(moveType == 'down' && len-1 > si) {
				ti = si+1;
			} else {
				moveYn = "N";
			}
			if(moveYn == "Y") {
				var tiValue = selectBox.options[ti].value;
				var tiText = selectBox.options[ti].text;
				//---- 위치변경
				selectBox.options[si].value = tiValue;
				selectBox.options[si].text = tiText;
				selectBox.options[ti].value = siValue;
				selectBox.options[ti].text = siText;
				selectBox.selectedIndex = ti;
			}
		}
	}
</script>
