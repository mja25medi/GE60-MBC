<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="teacherListVO" value="${teacherListVO}"/>

		<table summary="<spring:message code="course.title.tutor.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px; display: none;"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:70px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="text-center" style="display: none"><input type="checkbox" name="chkAllTutor" id="chkAllTutor" value="N" style="border:0" title="check all" onclick="checkAllTutor()"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody id="sortable-tutor">
				<c:forEach items="${teacherListVO}" var="item" varStatus="status">
				<tr id="${item.userNo}">
					<td class="text-center" style="display: none"><input type='checkbox' name='selTutor' value='${item.userNo}' style='border:0' title='${item.userNm}'></td>
					<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.userNm}</td>
					<td><a href="javascript:userInfo('${item.userNo}')">${item.userId}</a></td>
					<td>${item.mobileNo}</td>
					<td>${item.email}</td>
					<td class="text-center">
						<button onclick="javascript:creCrsTchDelete('${item.userNo}','TUTOR');" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></button>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty teacherListVO}">
				<tr>
					<td colspan="6"><spring:message code="course.message.tutor.nodata"/>.</td>
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
			});
		</script>

