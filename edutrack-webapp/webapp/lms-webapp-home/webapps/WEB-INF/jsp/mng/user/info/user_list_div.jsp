<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	.text-center a {color: black;}
</style>

		<form name="Search" id="Search" onsubmit="return false;" class="form-inline" >
				<div style="margin-top:20px;">
					<select name="searchKey" id="searchKey" class="form-control input-sm" style="width:100px;float:left;margin-left:10px;margin-bottom:15px">
						<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
						<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
					</select>
					<div class="input-group" style="width:65%;float:left;margin-left:5px;">
						<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
						<span class="input-group-addon" onclick="listUserPageing(1)" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
						
				<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:50px"/>
						<col style="width:50px"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">아이디</th>
							<th scope="col">이름</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userInfoList}" var="item" varStatus="status">
							<tr>
								<td class="text-center">${item.userId}</td>
								<td class="text-center"><a href="javascript:selectUser('${item.userNo}','${item.orgCd}')">${item.userNm}</a></td>
							</tr>
						</c:forEach>
						<c:if test="${empty userInfoList}">
							<tr>
								<td><spring:message code="user.message.userinfo.nodata"/></td>
							</tr>
						</c:if>
					</tbody>
				</table>
		</form>
		
		<div id="paginator_box1">
			<div class="paginator" id="paginator1"></div>
			<div class="paginator_pages">${pageInfo.totalPageCount} pages</div>
			<script type="text/javascript">
			   page = new Paginator('paginator1',${pageInfo.totalPageCount},${pageInfo.pageSize},${pageInfo.currentPageNo},'javascript:listUserPageing');
			</script>
		</div>
