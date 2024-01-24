<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form id="contentsForm" name="contentsForm" onsubmit="return false">
	
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }" />
	<input type="hidden" name="unitCd" />
	<table summary="<spring:message code="course.title.subject.category.manage"/>" style="width:100%;height:100%">
		<tr>
			<td colspan="2" style="padding:5px;">
				<select name="parUnitCd" onChange="listContentsSub()" id="parUnitCd" class="form-control input-sm">
					<option value=''><spring:message code="course.title.category.root"/></option>
				<c:if test="${not empty cntsList}">
				<c:forEach var="item" items="${cntsList}">
					<option value='${item.unitCd }'>${item.unitNm }</option>
				</c:forEach>
				</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:65%;padding:5px;" valign="top">
				<select name="selUnitCd" id="selUnitCd" multiple style="height:220px" class="form-control input-sm"></select>
			</td>
			<td style="width:35%;padding:5px;" valign="top">
				<a href="javascript:moveUp()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.up"/></a>
				<a href="javascript:moveDown()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.down"/> </a>
				<a href="javascript:sortContents()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.add"/> </a>
			</td>
		</tr>
	</table>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		listContentsSub();
	});


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.contentsForm)) return;
		$('#contentsForm').attr("action","/mng/course/createCourse/subject/"+cmd);
		$('#contentsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		var sbjCd = '${vo.sbjCd}';
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.sbjCntsReload(sbjCd);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 컨텐츠 목록 조회
	 */
	function listContentsSub() {
		var f = document.contentsForm;
		var crsCreCd = '${vo.crsCreCd}'
		var sbjCd = '${vo.sbjCd}';
		var parUnitCd = f.parUnitCd[f.parUnitCd.selectedIndex].value;
		var selectBox = document.getElementById("selUnitCd");
		var len = selectBox.length;
		for(var i=len ; i >= 0; i--) {
			selectBox.options[0] = null;
		}
		$.getJSON( cUrl("/mng/course/createCourse/subject/listCreCntsSub"),		// url
				{ 
				  "parUnitCd" : parUnitCd,
				  "sbjCd"	  : sbjCd,
				  "crsCreCd"  : crsCreCd
				},			// params
				listContentsSubCallback				// callback function
			);
	}
	/**
	 * 과목 분류 목록 조회 Callback
	 */
	function listContentsSubCallback(ProcessResultListDTO) {
		var selectBox = document.getElementById("selUnitCd");
		var len = selectBox.length;
		var contentsList = ProcessResultListDTO.returnList;
		for (var i=0; i<contentsList.length; i++) {
			var item = contentsList[i];
			new_option = new Option(item.unitNm, item.unitCd);
			selectBox.options.add(new_option);
		}
	}

	/**
	 * 과목 분류 순서 변경
	 */
	function sortContents() {
		var f = document.contentsForm;
		f["unitCd"].value = "";
		var selectBox = document.getElementById("selUnitCd");
		var len = selectBox.length;
		if(selectBox.length == 0) {
			alert('<spring:message code="course.message.category.alert.sort.nodata"/>');
		} else {
			var contentsList = "";
			for(var i=0; i < len; i++) {
				if(i==0) {
					contentsList = selectBox.options[i].value;
				} else {
					contentsList = contentsList+'|'+selectBox.options[i].value;
				}
			}
			f["unitCd"].value = contentsList;
			process("sortCreCnts");	// cmd
		}
	}


	function moveUp() {
		moveOption('up');
	}

	function moveDown() {
		moveOption('down');
	}

	function moveOption(moveType) {
		var selectBox = document.getElementById("selUnitCd");
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
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
</script>
