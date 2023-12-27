<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#consultTable th {background-color: #eee;}
</style>

	<div>
		<table class="table table-bordered wordbreak" id="consultTable" style="margin-top: 10px;">
			<colgroup>
				<col style="width:5%;"/>
				<col style="width:55%;"/>
				<col style="width:10%;"/>
				<col style="width:10%;"/>
				<col style="width:20%;"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>상담내용</th>
					<th>상담자 아이디</th>
					<th>상담자</th>
					<th>상담시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userConsultList}" var="item" varStatus="status">
					<tr>
						<td class="text-center">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
						<td class="text-center">${item.cnstDesc}</td>
						<td class="text-center">${item.cnstId}</td>
						<td class="text-center">${item.cnstUser}</td>
						<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.regDttm}"/></td>
													
					</tr>
				</c:forEach>
				<c:if test="${empty userConsultList}">
					<tr>
						<td colspan="4">상담내역이 없습니다. </td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<meditag:paging pageInfo="${pageInfo}" funcName="listConsult"/>
	 </div>
	 <div style="margin-top:2%">
		<textarea name="cnstDesc"  id="cnstDesc" style="height:60px;width:99%" class="form-control input-sm"></textarea>
		<div style="float:right;margin-top:10px; margin-right: 15px;">
			<a href="javascript:consultAdd()" class="btn btn-primary btn-sm">상담등록</a>
		</div>
	</div>
	 
<script type="text/javascript">
	//	상담 내용 저장
	function consultAdd() {
		var cnstDesc 	=	document.all["cnstDesc"].value;
	  	if(isEmpty($("#cnstDesc").val())) {
	  		alert("상담내용을 입력하세요.");
			$("#cnstDesc").focus();
			return;
		}

	  	var userNo = '${vo.userNo}';
		$.getJSON( cUrl("/mng/user/userInfo/addConsult"),
		   { "userNo":userNo,"cnstDesc":cnstDesc},			// params
		   function(result) {
			   alert(result.message);
			   listConsult();
		   }
		);
	}


</script>
