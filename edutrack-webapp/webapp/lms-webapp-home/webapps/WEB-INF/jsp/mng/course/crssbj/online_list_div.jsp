<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsOnlnSbjList" value="${crsOnlnSbjList}"/>

		<table summary="<spring:message code="course.title.subject.manage.online"/>" style="width:100%" class="table table-bordered">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:100px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<!-- <col style="width:180px"/> -->
				<col style="width:100px"/>
				<col style="width:140px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">순서</th>
					<th scope="col"><spring:message code="course.title.subject.code"/></th>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.category"/></th>
					<%-- <th scope="col"><spring:message code="course.title.subject.studymthd"/></th> --%>
					<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody id="sortable-online">
				<c:forEach items="${crsOnlnSbjList}" var="item" varStatus="status">
				<tr id="${item.sbjCd}">
					<td>${fn:length(crsOnlnSbjList) - status.index}</td>
					<td>${item.sbjCd}</td>
					<td class="wordbreak"><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.sbjNm}</td>
					<td class="wordbreak">${item.sbjCtgrNm} > ${item.sbjNm}</td>
					<%-- <td class="text-center">
						<div id="studyMthdName_${item.sbjCd}"><meditag:codename code="${item.studyMthd}" category="STUDY_MTHD"/></div>
						<div class="input-group" id="studyMthdSelect_${item.sbjCd}" style="display:none">
							<select name="studyMthd_${item.sbjCd}" id="studyMthd_${item.sbjCd}" class="form-control input-sm">
							<c:forEach var="code" items="${studyMthdList}">
								<c:set var="codeName" value="${code.codeNm}"/>
								<c:forEach var="lang" items="${code.codeLangList }">
									<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
								</c:forEach>
								<option value="${code.codeCd}" <c:if test="${code.codeCd eq item.studyMthd}">selected</c:if>>${codeName}</option>
							</c:forEach>
							</select>
							<span class="input-group-btn">
								<button class="btn btn-primary btn-sm" onclick="javascript:editOnlineMthd('${item.sbjCd}')"><spring:message code="button.add"/></button>
							</span>
						</div>
						<input type="hidden" name="studyMthdValue_${item.sbjCd}" id="studyMthdValue_${item.sbjCd}" value="${item.studyMthd}"/>
					</td> --%>
					<td class="text-right">${item.unitCnt}</td>
					<td class="text-center">
						<%-- <button id="studyMthdEditButton_${item.sbjCd}" class="btn btn-info btn-sm" onclick="javascript:showEditOnlineMthd('${item.sbjCd}');"><spring:message code="button.edit"/></button> --%>
						<button id="studyMthdCancelButton_${item.sbjCd}" class="btn btn-info btn-sm" onclick="javascript:hideEditOnlineMthd('${item.sbjCd}');" style="display:none"><spring:message code="button.cancel"/></button>
						<button class="btn btn-warning btn-sm" onclick="javascript:delOnline('${item.sbjCd}');"><spring:message code="button.delete"/></button>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty crsOnlnSbjList}">
				<tr>
					<td colspan="7"><spring:message code="course.message.subject.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#sortable-online').sortable({
					handle : 'i.glyphicon-move',
			    	stop : function(event, ui) {
			    		var sortString = "";
			    		$("#sortable-online").children('tr').each(function(){
			    			sortString += "|"+$(this).attr("id");
			    		});
			    		sortString = sortString.substring(1);
			    		onlineSort(sortString);
			    	}
				});
				$( "#sortable-online" ).disableSelection();
				resizeForm();
			});
		</script>