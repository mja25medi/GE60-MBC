<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="itemList" value="${bannerList}"/>
	<form name="bannerForm" id="bannerForm">
    <input type="hidden" name="bnnrSns" value="${vo.bnnrSns }" id="bnnrSns"/>
    <input type="hidden" name="bnnrNm" styleId="bnnrNm" value="1111"/>
	<table title="<spring:message code="etc.title.banner.sort"/>" style="width:100%">
		<tr>
			<td style="width:65%;padding:5px;" valign="top">
				<select name="bannerSn" id="bannerSn" multiple style="width:100%;height:250px" class="form-control input-sm">
				<c:if test="${not empty itemList}">
					<c:forEach var="item" items="${itemList}">
					<option value='${item.bnnrSn}'>${item.bnnrNm}</option>
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
	<form>
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {

	});


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.bannerForm)) return;
		$('#bannerForm').attr("action", "/mng/etc/bnnr/" + cmd);
		$('#bannerForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.returnMessage);
		if(resultDTO.returnResult >= 0) {
			// 정상 처리
			parent.bannerList(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 코드 정렬
	 */
	function sortCode() {

		$("#bnnrSns").val("");
		var selectBox = document.getElementById("bannerSn");
		var len = selectBox.length;
		if(selectBox.length == 0) {
			alert('<spring:message code="system.message.code.impossible.sort"/>');
		} else {
			var itemList = "";
			for(var i=0; i < len; i++) {
				if(i==0) {
					itemList = selectBox.options[i].value;
				} else {
					itemList = itemList+'|'+selectBox.options[i].value;
				}

			}
			$("#bnnrSns").val(itemList);
			process('sort');
		}
	}


	function moveUp() {
		moveOption('up');
	}

	function moveDown() {
		moveOption('down');
	}

	function moveOption(moveType) {
		var selectBox = document.getElementById("bannerSn");
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
