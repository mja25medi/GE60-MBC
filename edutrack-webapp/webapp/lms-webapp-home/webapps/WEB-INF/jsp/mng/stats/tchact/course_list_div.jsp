<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="itemList" value="${tchActStatusDetailList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
		<table style="width:100%" class="table table-bordered wordbreak" id="crsCreList">
			<colgroup>
				<col style="width:30px"/>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:120px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="text-center"><input type="checkbox" name="chkAll" id="chkAll" value="N"  onchange="viewChart('DAY');"/></th>
					<th><spring:message code="common.title.no"/></th>
					<th><spring:message code="course.title.course.name"/></th>
					<th><spring:message code="common.title.year"/></th>
					<th><spring:message code="course.title.createcourse.creterm"/></th>
					<th><spring:message code="log.title.tchact.connect"/></th>
					<th><spring:message code="log.title.tchact.board"/></th>
					<th><spring:message code="log.title.tchact.lectureelement"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${itemList}" var="item" varStatus="status">
				<tr>
					<td class="text-center">
						<input type="checkbox" name="sel" value="${item.crsCreCd}"  onchange="viewChart('DAY');"  <c:if test="${status.count <= 3 }">checked</c:if> />
						<input type="hidden" name="crsCreNm" value="${item.crsCreNm}" />
					</td>
					<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
					<td>${item.crsCreNm}</td>
					<td class="text-center">${item.creYear}</td>
					<td class="text-center">${item.creTerm}</td>
					<td class="text-right">${item.classConnCnt}</td>
					<td class="text-right">${item.bbsAtclCnt}/${item.bbsCmntCnt}</td>
					<td class="text-right">${item.asmtCnt}/${item.examCnt}/${item.forumCnt}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty itemList}">
				<tr>
					<td colspan="7"><spring:message code="common.message.nodata"/> </td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>

<script type="text/javascript">
$(document).ready(function() {
	//전체선택 /전체취소
	$('#chkAll').click(function(){
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$(document).find("#crsCreList input[name='sel']").prop({checked:true});
	    }else{
	    	$(document).find("#crsCreList input[name='sel']").prop({checked:false});
	    }
	});

	viewChart('DAY');
});
</script>