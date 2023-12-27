<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="researchBankItemVO" value="${vo}" />
<c:set var="researchBankItemListVO" value="${researchBankItemListVO}" />
    <form id="researchBankForm" name="researchBankForm" onsubmit="return false" >
		<input type="hidden" name="reshSn" value="${vo.reshSn }" />
		<input type="hidden" name="reshItemSns" value="${vo.reshItemSns }" />
        <table title="<spring:message code="board.title.faq.manage"/>" style="width:100%">
            <tr>
                <td style="width:70%;padding:5px;" valign="top">

                    <select name="reshItemSn" id="reshItemSn" multiple style="height:260px" class="form-control input-sm">
           				<c:forEach var="item" items="${researchBankItemListVO}" varStatus="status">
            				<c:choose>
            					<c:when test="${fn:length(item.reshItemCts) > 12}">
            						<option value="${item.reshItemSn}">${status.count}</option>
            					</c:when>
								<c:otherwise>
									<option value="${item.reshItemSn}">${status.count}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
	    			</select>
                </td>
                <td style="width:30%;padding:5px;" valign="top">
                	<a href="javascript:moveUp()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.up" /> </a>
                	<a href="javascript:moveDown()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.down" /> </a>
                	<a href="javascript:sortAtcl()" class="btn btn-default btn-sm" style="display:block"><spring:message code="button.add" /> </a>
                </td>
            </tr>
        </table>
    <input type="submit" value="submit" style="display:none" />
    </form>

<script type="text/javascript">

function onclickTab(tab) {
	var cmd = "viewCourse";
	$("#listCourse").load(cUrl("/mng/org/research/" + cmd),{ "researchBankVO.reshSn":"${researchBankVO.reshSn}"});
}

//페이지 초기화
$(document).ready(function() {
});


/**
 * 서브밋 처리
 */
function process(cmd) {
	if(!validate(document.researchBankForm)) return;
	$('#researchBankForm').attr("action","/mng/org/research/" + cmd);
	$('#researchBankForm').ajaxSubmit(processCallback);
}

/**
 * 처리 결과 표시 콜백
 */
function processCallback(resultDTO) {
	/* alert(resultDTO.message); */

	if(resultDTO.result >= 0) {
		// 정상 처리
		parent.listQstn();
		parent.modalBoxClose();
	} else {
		// 비정상 처리
	}
}

/**
 * 코드 정렬
 */
function sortAtcl() {
	var f = document.researchBankForm;
	f["researchBankItemVO.reshItemSns"].value = "";

	var selectBox = document.getElementById("reshItemSn");
	var len = selectBox.length;

	if(selectBox.length == 0) {
		alert('<spring:message code="course.messgae.reshbank.item.alert.nosort"/>');
	} else {

		var atclSnList = "";
		for(var i=0; i < len; i++) {
			if(i==0) {
				atclSnList = selectBox.options[i].value;
			} else {
				atclSnList = atclSnList+'|'+selectBox.options[i].value;
			}
		}
		f["researchBankItemVO.reshItemSns"].value = atclSnList;
		process('sortReserchItem');
	}
}


function moveUp() {
	moveOption('up');
}

function moveDown() {
	moveOption('down');
}

function moveOption(moveType) {
	var selectBox = document.getElementById("reshItemSn");
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
