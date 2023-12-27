<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="teacherListVO" value="${teacherListVO}"/>

		<table summary="<spring:message code="course.title.teacher.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px; display: none"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:70px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="text-center" style="display: none"><input type="checkbox" name="chkAllTeacher" id="chkAllTeacher" value="N" style="border:0" title="check all" onclick="checkAllTeacher()"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody id="sortable-teacher">
				<c:forEach items="${teacherListVO}" var="item" varStatus="status">
				<tr id="${item.userNo}">
					<td class="text-center" style="display: none"><input type='checkbox' name='selTeacher' value='${item.userNo}' style='border:0'></td>
					<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.userNm}</td>
					<td><a href="javascript:userInfo('${item.userNo}')">${item.userId}</a></td>
					<td>${item.mobileNo}</td>
					<td>${item.email}</td>
					<td class="text-center">
						<button class="btn btn-warning btn-sm" onclick="javascript:creCrsTchDelete('${item.userNo}','TEACHER');"><spring:message code="button.delete"/></button>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty teacherListVO}">
				<tr>
					<td colspan="7"><spring:message code="course.message.teacher.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#sortable-teacher').sortable({
					handle : 'i.glyphicon-move',
			    	stop : function(event, ui) {
			    		var sortString = "";
			    		$("#sortable-teacher").children('tr').each(function(){
			    			sortString += "|"+$(this).attr("id");
			    		});
			    		sortString = sortString.substring(1);
			    		teacherSort(sortString);
			    	}
				});
				$( "#sortable-teacher" ).disableSelection();
			});
		</script>

