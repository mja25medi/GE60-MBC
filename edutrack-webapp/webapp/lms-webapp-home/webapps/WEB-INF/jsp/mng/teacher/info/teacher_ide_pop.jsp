<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="teacherForm" name="teacherForm" onsubmit="return false" method="post">
    <input type="hidden" id="userNo" name="userNo"/>
    <input type="hidden" id="ideUrl" name="ideUrl"/>
	<div>
			<table summary="<spring:message code="board.title.bbs.atcl.list"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:50px;"/>
					<col style="width:70px"/>
					<col style="width:90px"/>
					<col style="width:200px;"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col">이름</th>
						<th scope="col">아이디</th>
						<th scope="col">IDE</th>
					</tr>
				</thead>
				<tbody id="tbodyList">
					<c:forEach items="${userInfoList}" var="item" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${item.userNm}</td>
						<td>${item.userId }</td>
						<td>
						<select id="ideUrl" name="ideUrls" class="form-control input-sm" onchange="urlCheck(this)">
							<c:if test="${not empty item.ideUrl }">
								<option value="${item.ideUrl}">${item.ideUrl }</option>
								<c:forEach items="${ideList}" var="ideList" varStatus="status">
									<option value="${ideList.ideUrl}">${ideList.ideUrl }</option>
								</c:forEach>
							</c:if>
							<c:if test="${ empty item.ideUrl }">
								<option value="">IDE를 선택하세요.</option>
								<c:forEach items="${ideList}" var="ideList" varStatus="status">
									<option value="${ideList.ideUrl}">${ideList.ideUrl }</option>
								</c:forEach>
							</c:if>
						</select>
						<input type="hidden" name="userNos" value="${item.userNo} " >
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty userInfoList}">
						<tr>
							<td colspan="8"><spring:message code="common.message.nodata"/></td>
						</tr>
					</c:if>
				</tbody>
		</table>
		<div class="text-right">
			<a href="javascript:editStudent()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
		</div>
	</div>
	</form>

<script type="text/javascript">

function editStudent(){
	var f = document.teacherForm;

	process("editTeacherUrl");
}

function urlCheck(obj){
	
	var vals = [];
	var x = 0;

	$('select[name=ideUrls]').each(function(i){
		vals[i] = $(this).val();
	});

 	$(vals).each(function(i){
 		if(vals[i] == obj.value){x++;}
 		if( x > 1 ){
 			alert("IDE는 중복 선택 하실 수 없습니다.");
 			$(obj).find("option:eq(0)").prop("selected","true");
 			return false;
 		}
	});

}

function process(cmd) {

	//if(validate(document.studentForm) == false) return ;

	$('#teacherForm').attr("action" , "/mng/tch/info/" + cmd );
	$('#teacherForm').ajaxSubmit(processCallback);
}

/**
* 처리 결과 표시 콜백
*/
function processCallback(resultDTO) {
	alert(resultDTO.message);
	if(resultDTO.result >= 0) {
		parent.listPageing('1');
		parent.modalBoxClose();
		// 정상 처리
	} else {
		// 비정상 처리
	}
}


</script>
</mhtml:frm_body>
</mhtml:mng_html>