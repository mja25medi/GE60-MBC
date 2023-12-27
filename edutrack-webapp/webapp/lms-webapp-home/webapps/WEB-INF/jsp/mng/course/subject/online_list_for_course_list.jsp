<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="onlineSubjectList" value="${onlineSubjectList}"/>
<c:set var="onlineSubjectVO" value="${vo}" />

				<table summary="<spring:message code="course.title.subject.category.manage"/>" class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:30px"/>
						<col style="width:auto"/>
						<col style="width:auto"/>
						<!-- <col style="width:auto"/> -->
					</colgroup>
					<thead>
					<tr>
						<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
						<th scope="col"><spring:message code="course.title.subject.name"/></th>
						<th scope="col"><spring:message code="course.title.subject.category"/></th>
						<!-- <th scope="col">환급구분</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${onlineSubjectList}" var="item" varStatus="status">
						<tr>
							<td><input type='checkbox' name='sel' id='sel_${status.index}' value='${item.sbjCd}' style='border:0'></td>
							<td>${item.sbjNm}</td>
							<td>${item.sbjCtgrNm}</td>
							<%-- 
							<td>
								<c:choose>
									<c:when test="${item.refundYn eq 'Y' }">
										환급
									</c:when>
									<c:otherwise>
										비환급
									</c:otherwise>
								</c:choose>
							</td>
							 --%>
						</tr>
					</c:forEach>
					<c:if test="${empty onlineSubjectList}">
					<tr>
						<td colspan="8"><spring:message code="course.message.subject.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>

			<div class="text-right" style="margin-bottom:20px;">
				<button class="btn btn-primary btn-sm" onclick="addCreateCourse()"><spring:message code="common.title.select"/></button>
			</div>
			
<script type="text/javascript">
function checkAll() {
    var state=$('input[name=chkAll]:checked').size();
    if(state==1){
   		$("input[name='sel']").prop({checked:true});
    }else{
    	$("input[name='sel']").prop({checked:false});
    }
}

function addCreateCourse(){
	$.ajax({
		url : '/mng/course/createCourse/addCreateCourse'
		,data : {
			'crsCd' : parent.$("#crsCd").val()
			,'deptCd' : parent.$("#deptCd").val()
			,'creTypeCd' : parent.$("#creTypeCd").val()
			,'sbjCd' : $("input[name='sel']:checked").stringValues()
		}
		,success : function(resultVO) {
			alert(resultVO.message);
			parent.listCreateCourse();
			parent.modalBoxClose();
		}
		,error : function(request,status,error) {
			alert("과정 개설에 실패하였습니다.");
		}
	});
}
</script>