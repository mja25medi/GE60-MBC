<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="codeForm" id="codeForm" onsubmit="return false">
	<input type="hidden" name="codeCtgrCd" value="${vo.codeCtgrCd}"/>
	<input type="hidden" name="codeCd" value="${vo.codeCd}"/>
	<table title="<spring:message code="system.title.code.sort"/>" style="width:100%">
		<tr>
			<td style="width:65%;padding:5px;" valign="top">
				<select name="selectCodeCd" id="selectCodeCd" multiple style="width:100%;height:250px" title="<spring:message code="system.title.code.selectbox"/>" class="form-control input-sm">
				<c:if test="${not empty codeList}">
					<c:forEach var="item" items="${codeList}">
					<option value='${item.codeCd}'>${item.codeNm}</option>
					</c:forEach>
				</c:if>
				</select>
			</td>
			<td style="width:35%;padding:5px;" valign="top">
				<a class="btn btn-default btn-sm" href="javascript:moveUp()" style="display:block"><spring:message code="button.up"/></a>
				<a class="btn btn-default btn-sm" href="javascript:moveDown()" style="display:block"><spring:message code="button.down"/></a>
				<a class="btn btn-default btn-sm" href="javascript:sortCode()" style="display:block"><spring:message code="button.add"/></a>
			</td>
		</tr>
	</table>
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {

	});


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#codeForm').attr("action",cmd);
		$('#codeForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listCode();
			parent.ctgrPopBox.close();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 코드 정렬
	 */
	function sortCode() {
		var f = document.codeForm;
		f["codeCd"].value = "";

		var selectBox = document.getElementById("selectCodeCd");
		var len = selectBox.length;

		if(selectBox.length == 0) {
			alert('<spring:message code="system.message.code.impossible.sort"/>');
		} else {

			var codeCdList = "";
			for(var i=0; i < len; i++) {
				if(i==0) {
					codeCdList = selectBox.options[i].value;
				} else {
					codeCdList = codeCdList+'|'+selectBox.options[i].value;
				}
			}
			f["codeCd"].value = codeCdList;

			process('/adm/system/code/sortCode.do');
		}
	}


	function moveUp() {
		moveOption('up');
	}

	function moveDown() {
		moveOption('down');
	}

	function moveOption(moveType) {
		var selectBox = document.getElementById("selectCodeCd");
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
