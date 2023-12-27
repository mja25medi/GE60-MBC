<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsTchList" value="${crsTchList}"/>

		<table summary="<spring:message code="course.title.tutor.manage"/>" style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:70px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody id="sortable-tutor">
				<c:forEach items="${crsTchList}" var="item" varStatus="status">
				<tr id="${item.userNo}">
					<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.userNm}</td>
					<td>${item.userId}</td>
					<td>${item.mobileNo}</td>
					<td>${item.email}</td>
					<td>
						<button class="btn btn-warning btn-sm" onclick="javascript:delTutor('${item.userNo}');"><spring:message code="button.delete"/></button>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty crsTchList}">
				<tr>
					<td colspan="5"><spring:message code="course.message.tutor.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#sortable-tutor').sortable({
					handle : 'i.glyphicon-move',
			    	stop : function(event, ui) {
			    		var sortString = "";
			    		$("#sortable-tutor").children('tr').each(function(){
			    			sortString += "|"+$(this).attr("id");
			    		});
			    		sortString = sortString.substring(1);
			    		tutorSort(sortString);
			    	}
				});
				$( "#sortable-tutor" ).disableSelection();
				resizeForm();
			});
		</script>
