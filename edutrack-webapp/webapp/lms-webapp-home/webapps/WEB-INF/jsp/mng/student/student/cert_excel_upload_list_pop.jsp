<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
	<style type="text/css">
		.error {color:red; font-weight:bold;}
		.empty {color:blue; font-weight:bold;}
	</style>

	<form name="Validate" id="Validate" onsubmit="return false;" method="post">
		<input type="hidden" id="crsCreCd"  name="crsCreCd" value="${vo.crsCreCd }" />
		<input type="hidden" id="crsCd" name="crsCd" value="${vo.crsCd }"/>

		<div class="input-group" style="float:left;width:80%">
		</div>
		<div class="text-right" style="width:100%">
			<a href="javascript:saveCertStudent()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
		</div>
		<div class="table-container" style="height:500px; margin-top:10px; ">
			<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak" style="margin-bottom:0px; font-size: 12px; width:1070px; max-width:none;">
				<colgroup>
					<col style="min-width:10px;width:10px;"/>
					<col style="min-width:50px;width:50px;"/>
					<col style="min-width:50px;width:50px;"/>
					<col style="min-width:50px;width:50px;"/>
					<col style="min-width:20px;width:20px;"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.userid"/> </th>
						<th scope="col">점수</th>
						<th scope="col">합격여부</th>
						<th scope="col"><spring:message code="common.title.edit"/></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${studentExcelList}" varStatus="status">
					<tr>
						<td class="text-center">
							<input type="checkbox" name="chk" id="chk_${item.lineNo}" value="${item.lineNo}" <c:if test="${not empty item.errorCode }">disabled</c:if>>
							<input type="hidden" name="lineNo" id="lineNo_${item.lineNo}" value="${item.lineNo}" />
							<input type="hidden" name="errorCode" id="errorCode_${item.lineNo}" value="${item.errorCode}" />
						</td>
						<td class="text-center">
							<span class="value-view-${item.lineNo} " id="userId-view-${item.lineNo}">
								${item.userId }
							</span>
							<input type="text" name="userId" id="userId-edit-${item.lineNo}" value="${item.userId}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
						</td>
						<td class="text-center">
							<span class="value-view-${item.lineNo}" id="certScore-view-${item.lineNo}">
								${item.certScore }
							</span>
							<input type="text" name="certScore" id="certScore-edit-${item.lineNo}" value="${item.certScore}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
						</td>
						<td class="text-center">
							<span class="value-view-${item.lineNo} " id="certPassYn-view-${item.lineNo}">
								${item.certPassYn }
							</span>
							<input type="text" name="certPassYn" id="certPassYn-edit-${item.lineNo}" value="${item.certPassYn}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
						</td>
						<td class="text-center">
							<a id="editBtn_${item.lineNo}" href="javascript:editLine('${item.lineNo}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
							<a id="checkBtn_${item.lineNo}" href="javascript:checkLine('${item.lineNo}')" class="btn btn-warning btn-sm" style="display:none"><spring:message code="button.process"/> </a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty studentExcelList }">
					<tr>
						<td colspan="5"><spring:message code="user.message.userinfo.nodata"/></td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</form>
	<form id="certStudentForm" name="certStudentForm" onsubmit="return false" >
		<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd }" />
		<input type="hidden" name="lineNo" id="lineNo" value=""/>
		<input type="hidden" name="userId" id="userId" value=""/>
		<input type="hidden" name="certScore" id="certScore" value=""/>
		<input type="hidden" name="certPassYn" id="certPassYn" value=""/>
		<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		parent.modalBox.resize(1100,600);

	});

	function editLine(lineNo) {
		// unchecked and disabled checkbox
		$("#chk_"+lineNo).attr("checked", false);
		$("#chk_"+lineNo).attr("disabled", true);

		$("#editBtn_"+lineNo).hide();
		$("#checkBtn_"+lineNo).show();

		$(".value-view-"+lineNo).hide();
		$(".value-edit-"+lineNo).show();
	}

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	    	$("#Validate input[name='chk']:enabled").prop({checked:true});
	    }else{
	    	$("#Validate input[name='chk']:enabled").prop({checked:false});
	    }
	}

	function checkLine(lineNo) {
		var userId = $("#userId-edit-"+lineNo).val();
		var certScore = $("#certScore-edit-"+lineNo).val();
		var certPassYn = $("#certPassYn-edit-"+lineNo).val();

		if(isEmpty(userId)) {
			alert('아이디를 입력하세요');
			$("#userId-edit-"+lineNo).focus();
			$("#userId-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#userId-edit-"+lineNo).removeClass("validerr");
		}
		if(isEmpty(certScore)) {
			alert('점수를 입력하세요');
			$("#certScore-edit-"+lineNo).focus();
			$("#certScore-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#certScore-edit-"+lineNo).removeClass("validerr");
		}
		if(isEmpty(certPassYn)) {
			alert('합격여부를 입력하세요');
			$("#certPassYn-edit-"+lineNo).focus();
			$("#certPassYn-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#certPassYn-edit-"+lineNo).removeClass("validerr");
		}

		$("#lineNo").val(lineNo);
		$("#userId").val(userId);
		$("#certScore").val(certScore);
		$("#certPassYn").val(certPassYn);

		$("#certStudentForm").attr("action","/mng/std/student/studentUploadCheck");
		$('#certStudentForm').ajaxSubmit(checkLineCallback);
	}

	function checkLineCallback(resultDTO) {
		var lineNo = resultDTO.lineNo;
		var qstnTypeText = "";
		if(isEmpty(resultDTO.errorCode)) {
			//-- error class 삭제
			$(".value-view-"+lineNo).removeClass("error");
			//-- check box 활성화
			$("#chk_"+lineNo).attr("disabled", false);

			$("#userId-view-"+lineNo).html(resultDTO.userId);
			$("#certScore-view-"+lineNo).html(resultDTO.certScore);
			$("#certPassYn-view-"+lineNo).html(resultDTO.certPassYn);

			$("#errorCode_"+lineNo).val("");

			$("#checkBtn_"+lineNo).hide();
			$("#editBtn_"+lineNo).show();

			$(".value-edit-"+lineNo).hide();
			$(".value-view-"+lineNo).show();
		} else {
			//-- 오류가 있는 경우
			$("#errorCode_"+lineNo).val(resultDTO.errorCode);
			//-- check box 비활성화
			$("#chk_"+lineNo).attr("disabled", true);
		}
	}

	function saveCertStudent() {
		var certStudentList = $("#Validate input[name='chk']:checked").stringValues();
		if(isEmpty(certStudentList)) {
			alert("응시생을 선택해주세요");
			return;
		} else {
			$("#Validate").attr("action","/mng/std/student/addCertStudentExcel");
			$('#Validate').ajaxSubmit(saveCertStudentCallback);
		}
	}

	function saveCertStudentCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listCertStudent('1');
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
</script>
