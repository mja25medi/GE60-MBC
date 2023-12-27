<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="lecRoomList" value="${lecRoomList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="lecRoomVo" value="${vo}" />

	<table summary="<spring:message code="course.title.subject.category.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:110px"/>
			<col style="width:180px"/>
			<col style="width:600px"/>
			<col style="width:300px"/>
			<col style="width:100px"/>
			<col style="width:100px"/>
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><spring:message code="course.title.lecroom.contents.cnt"/></th>
			<th scope="col"><spring:message code="course.title.lecroom.contents.used"/></th>
			<th scope="col"><spring:message code="course.title.lecroom.contents.addr1"/></th>
			<th scope="col"><spring:message code="course.title.lecroom.contents.addr2"/></th>
			<th scope="col"><spring:message code="common.title.stats"/></th>
			<th scope="col"><spring:message code="course.title.lecroom.contents.del"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${lecRoomList}" var="item" varStatus="status">
		<tr>
			<td class="text-center"">${status.count}</td>
			<td class="text-center">${item.codeNm}</td>
			<td class="text-left">${item.addr1}&nbsp; <a href="javascript:lecRoomEdit('${item.lecRoomSn }');"><i class="fa fa-cog"></i></a></td>
			<td class="text-center">${item.addr2}</td>
			<td class="text-center">
				<c:if test="${item.useYn eq 'Y'}">사용가능</c:if>
				<c:if test="${item.useYn eq 'N'}">사용불가</c:if>			
			</td>
			<td class="text-center">
				<a href="javascript:delLecRoom('${item.lecRoomSn }');" class="btn btn-info btn-sm"><spring:message code="button.delete"/> </a>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty lecRoomList}">
		<tr>
			<td colspan="8"><spring:message code="course.message.lecroom.nodata"/></td>
		</tr>
		</c:if>
	</tbody>
</table>
<meditag:paging pageInfo="${pageInfo}" funcName="listLecRoom"/>

<style>
.table>tbody>tr>td {vertical-align: middle;}
</style>
									
									