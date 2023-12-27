<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>
<mhtml:frm_body cssTag="background: #ffffff">
    <form id="crsTchForm" name="crsTchForm" onsubmit="return false">
	<input type="hidden" name="crsCd" value="${vo.crsCd }" />
	<input type="hidden" name="userNo" value="${vo.userNo }" />
	<table title="<spring:message code="course.title.teacher.manage"/>" style="width:100%" class="table_none">
    	<tr>
        	<td style="padding:5px 5px 5px 5px" width="65%" valign="top">
            	<select name="UserNo" id="userNo" multiple style="width:220px;height:260px" class="form-control input-sm">
            	<c:forEach items="${teacherList}" var="item">
					<option value="${item.userNo}">${item.userNm}</option>
				</c:forEach>
    			</select>
			</td>
			<td style="padding:5px 5px 5px 5px" width="35%" valign="top">
    			<button class="btn btn-default btn-sm btn-block" onclick="moveUp()" ><spring:message code="button.up"/></button>
    			<button class="btn btn-default btn-sm btn-block" onclick="moveDown()" ><spring:message code="button.down"/></button>
    			<button class="btn btn-default btn-sm btn-block" onclick="sortTeacher()" ><spring:message code="button.add"/></button>
        	</td>
    	</tr>
	</table>
    <input type="submit" value="submit" style="display:none" />
    </form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {

	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.crsTchForm)) return;
		$('#crsTchForm').attr("action","/mng/course/teacher/" + cmd);
		$('#crsTchForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listTeacher();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 코드 정렬
	 */
	function sortTeacher() {
		var f = document.crsTchForm;
		f["crsTchVO.userNo"].value = "";

		var selectBox = document.getElementById("userNo");
		var len = selectBox.length;

		if(selectBox.length == 0) {
			alert('<spring:message code="course.message.teacher.sort.nodata"/>');
		} else {

			var userList = "";
			for(var i=0; i < len; i++) {
				if(i==0) {
					userList = selectBox.options[i].value;
				} else {
					userList = userList+'|'+selectBox.options[i].value;
				}
			}
			f["crsTchVO.userNo"].value = userList;

			process('sortTeacher');
		}
	}


	function moveUp() {
		moveOption('up');
	}

	function moveDown() {
		moveOption('down');
	}

	function moveOption(moveType) {
		var selectBox = document.getElementById("userNo");
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