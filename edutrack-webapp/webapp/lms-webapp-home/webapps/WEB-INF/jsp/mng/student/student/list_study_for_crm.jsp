<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#creListTable th {background-color: #eee;}
</style>

<div style="width: 40%; float: left;">
	<table summary="수강 내역" class="table table-bordered wordbreak custom-table" style="margin-top: 10px;" id="creListTable">
		<colgroup>
			<col style="width:auto"/>
			<col style="width:30%"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">과정명</th>	
				<th scope="col">회차</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty myCreList }">
				<c:forEach var="item" items="${myCreList }" varStatus="status">
					<tr class="text-center">
						<td><a href="javascript:listContents('${item.stdNo }','${item.sbjCd }','${item.crsCreCd}');">${item.crsCreNm }</a></td>
						<td><a href="javascript:listContents('${item.stdNo }','${item.sbjCd }',${item.crsCreCd}');">${item.creTerm }회차</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty myCreList }">
				<tr>
					<td colspan="6">내역이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>
<div style="width: 58%; float: left; margin-left: 20px;" id="contentsListDiv">

</div>

<script type="text/javascript">
	//수강이력
	function listContents(stdNo, sbjCd, crsCreCd){
		var goUrl ="/mng/course/contents/listContentsForCrm";
		$("#contentsListDiv")
		.load(
			cUrl(goUrl),		
			{
				'stdNo':stdNo,
				'sbjCd':sbjCd,
				'crsCreCd':crsCreCd,
			}
		);
	}
</script>