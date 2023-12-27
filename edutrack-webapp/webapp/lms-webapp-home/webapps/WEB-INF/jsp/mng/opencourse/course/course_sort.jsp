<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff">
	<form id="openCrsForm" name="openCrsForm" onsubmit="return false" >
	<input type="hidden" name="crsCd" value="${vo.crsCd }" />
	<table summary="<spring:message code="course.title.course.manage"/>" style="width:100%;height:100%" class="table_detail">
		<tr>
			<td style="width:65%;padding:5px;" valign="top">
				<select name="crsCd" id="crsCd" multiple style="height:220px" class="form-control input-sm">
				</select>
			</td>
			<td style="width:35%;padding:5px;" valign="top">
				<a class="btn btn-default btn-sm" href="javascript:moveUp()" style="display:block;"><spring:message code="button.up"/></a>
				<a class="btn btn-default btn-sm" href="javascript:moveDown()" style="display:block"><spring:message code="button.down"/></a>
				<a class="btn btn-default btn-sm" href="javascript:sortCourse()" style="display:block"><spring:message code="button.add"/></a>
			</td>

		</tr>
	</table>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		listCourse();
	});


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(openCrsForm)) return;
		$('#openCrsForm').attr("action", "/mng/openCourse/" + cmd);
		$('#openCrsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listCourse();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과정 분류 목록 조회
	 */
	function listCourse() {
		var f = document.openCrsForm;
		var selectBox = document.getElementById("crsCd");
		var len = selectBox.length;
		for(var i=len ; i >= 0; i--) {
			selectBox.options[0] = null;
		}

		$.getJSON(
					cUrl("/mng/openCourse/listCrsJSON"),
					{  "ctgrCd":"${openCrsVO.ctgrCd}"	},
					listCourseCallback	);
	}

	/**
	 * 과정 분류 목록 조회 Callback
	 */
	function listCourseCallback(ProcessResultListDTO) {
		var selectBox = document.getElementById("crsCd");
		var len = selectBox.length;
		var categoryList = ProcessResultListDTO.returnList;
		for (var i=0; i<categoryList.length; i++) {
			var item = categoryList[i];
			new_option = new Option(item.crsNm, item.crsCd);
			selectBox.options.add(new_option);
		}
	}

	/**
	 * 과정 분류 순서 변경
	 */
	function sortCourse() {
		var f = openCrsForm;
		f["crsCd"].value = "";
		var selectBox = document.getElementById("crsCd");
		var len = selectBox.length;
		if(selectBox.length == 0) {
			alert('<spring:message code="course.message.category.alert.sort.nodata"/>');
		} else {
			var courseList = "";
			for(var i=0; i < len; i++) {
				if(i==0) {
					courseList = selectBox.options[i].value;
				} else {
					courseList = courseList+'|'+selectBox.options[i].value;
				}
			}
			f["crsCd"].value = courseList;
			process("sortCrs");	// cmd
		}
	}


	function moveUp() {
		moveOption('up');
	}

	function moveDown() {
		moveOption('down');
	}

	function moveOption(moveType) {
		var selectBox = document.getElementById("crsCd");
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
</mhtml:frm_body>
</mhtml:mng_html>