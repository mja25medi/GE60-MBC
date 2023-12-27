<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="userDeptInfoList" value="${userDeptInfoList}"/>

				<div class="callout callout-warning" style="">
					<span style="color:red;font-weight:bold;">* </span>&nbsp;과정이 개설된 기업은 삭제할 수 없습니다.
				</div>
				<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:60px"/>
						<col style="width:150px"/>
						<col style="width:150px"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
							<th scope="col">법인명</th>
							<th scope="col">코드명</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userDeptInfoList}" var="item" varStatus="status">
						<tr>
							<td class="text-center"><input type='checkbox' name='sel' id='sel_${status.index}' value='${item.deptCd}' style='border:0' <c:if test="${item.deleteYn eq 'N' }">disabled</c:if>></td>
							<td>${item.deptNm}</td>
							<td>${item.deptCd}</td>
						</tr>
						</c:forEach>
						<c:if test="${empty userDeptInfoList}">
						<tr>
							<td colspan="9"><spring:message code="common.message.nodata"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>

				<div class="text-right" style="margin-bottom:20px;">
					<button class="btn btn-primary btn-sm" onclick="selectDept()"><spring:message code="common.title.select"/></button>
				</div>
				
				
<script type="text/javascript">

$(document).ready(function() {
	var deptArray = parent.$("#deptCds").val().split(",");
	$("input[name='sel']").each(function(idx) {
		if(deptArray.includes(this.value)){
			$(this).prop('checked', true);
		}
	});
});

function checkAll() {
    var state=$('input[name=chkAll]:checked').size();
    if(state==1){
   		$("input[name='sel']").prop({checked:true});
    }else{
    	$("input[name='sel']").prop({checked:false});
    }
}

function selectDept(){
	parent.$("#deptCds").val($("input[name='sel']:checked").stringValues());
	
	var deptNms = "";
	$("input[name='sel']:checked").each(function(idx) {
		deptNms += $($(this).parent().siblings()[0]).text();
		if((idx+1)!=$("input[name='sel']:checked").length){
			deptNms += ", ";
		}
	});
	parent.$("#deptNms").val(deptNms);
	parent.modalBoxClose();
}
</script>