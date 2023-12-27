<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="vo" value="${vo}"/>
	<form id="certStsForm" name="certStsForm" onsubmit="return false">
		<input type="hidden" name="stdNo" id="stdNo" value="${vo.stdNo }"/>
		<input type="submit" value="submit" style="display:none" />
		<div style="height:15px;"></div>
		<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-striped table-bordered">
			<colgroup>
				<col style="width:30%"/>
				<col style="width:70%"/>
			</colgroup>
			<tr>
				<th scope="row"><span style="color:red;">* </span><label for="userNm">신청자명</label></th>
				<td>
					<div class="input-group" style="float:left;">
						${vo.userNm}
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span style="color:red;">* </span><label for="certSts">진행상태</label></th>
				<td>
					<select name="certSts" id="certSts" onchange="certStsCheck()" class="form-control input-sm" style="float:left;">
							<option value="">진행상태를 선택하세요</option>
							<option value="S" <c:if test="${vo.certSts eq 'S' }"> selected="selected"</c:if>>승인완료</option>
							<option value="F" <c:if test="${vo.certSts eq 'F' }"> selected="selected"</c:if>>승인취소</option>
					</select>
				</td>
			</tr>
			<tr id="certFailReasonTr" style="display:none">
				<th scope="row"><span style="color:red;">* </span>취소사유</th>
				<td>
					<textarea id="certFailReason" name="certFailReason" style="height:80px" class="form-control input-sm" >${vo.certFailReason}</textarea>
				</td>
			</tr>
		</table>
	</form>
	<div class="text-right" style="margin-top:10px;">
		<button class="btn btn-primary" onclick="editCertSts()"><spring:message code="button.add"/></button>
		<button class="btn btn-default" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>

<script type="text/javascript">

	$(document).ready(function() {
		if('${vo.certSts}' == "F"){
			$("#certFailReasonTr").css("display", "");
		}
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#certStsForm').attr("action","/mng/std/student/"+cmd);
		$('#certStsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			parent.listCertStudent();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 자격증 신청 상태 등록
	 */
	function editCertSts() {
		var certSts = $("#certSts option:selected").val();
		if(certSts == "F" ){
			if($("#certFailReason").val().length == 0){
				alert("취소사유를 반드시 입력해야합니다.");
				$('#certFailReason').focus();
				return;
			}
		}else if (certSts == "S"){
			$("#certFailReason").val('');
		}
		process("editCertSts");	// cmd
	}
	
	function certStsCheck(){
		var certSts = $("#certSts option:selected").val();
		if(certSts == "F"){
			$("#certFailReasonTr").css("display", "");
		}else{
			$("#certFailReasonTr").css('display','none');
		}
	}
	
</script>