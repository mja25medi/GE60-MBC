<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="offlineSubjectList" value="${offlineSubjectList}"/>

		<table summary="<spring:message code="course.title.subject.manage.offline"/>" class="table table-bordered wordbreak" style="font-size: 14px;">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:110px"/>
				<col style="width:200px"/>
				<col style="width:180px"/>
				<col style="width:140px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.code"/></th>
					<th scope="col"><spring:message code="course.title.subject.category"/></th>
					<th scope="col"><spring:message code="course.title.subject.type"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody id="sortable-offline">
				<c:forEach items="${offlineSubjectList}" var="item" varStatus="status">
				<tr id="${item.sbjCd}">
					<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.sbjNm}</td>
					<td>${item.sbjCd}</td>
					<td>${item.sbjCtgrNm}</td>
					<td class="text-center">
						<div id="eduMthdName_${item.sbjCd}"><meditag:codename code="${item.eduMthdCd}" category="LEC_KIND_CD"/></div>
						<div class="input-group" id="eduMthdSelect_${item.sbjCd}" style="display:none">
							<select name="eduMthd_${item.sbjCd}" id="eduMthd_${item.sbjCd}" class="form-control input-sm">
							<c:forEach var="code" items="${eduMthdList}">
								<c:set var="codeName" value="${code.codeNm}"/>
								<c:forEach var="lang" items="${code.codeLangList }">
									<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
								</c:forEach>
								<option value="${code.codeCd}" <c:if test="${code.codeCd eq item.eduMthdCd}">selected</c:if>>${codeName}</option>
							</c:forEach>
							</select>
							<span class="input-group-btn">
								<button class="btn btn-primary btn-sm" onclick="javascript:editOfflineMthd('${item.sbjCd}')"><spring:message code="button.add"/></button>
							</span>
							<input type="hidden" name="eduMthdValue_${item.sbjCd}" id="eduMthdValue_${item.sbjCd}" value="${item.eduMthdCd}"/>
						</div>
					</td>
					<td class="text-center">
						<button id="eduMthdEditButton_${item.sbjCd}" class="btn btn-info btn-sm" onclick="javascript:showEditOfflineMthd('${item.sbjCd}');" ><spring:message code="button.edit"/></button>
						<button id="eduMthdCancelButton_${item.sbjCd}" class="btn btn-info btn-sm" onclick="javascript:hideEditOfflineMthd('${item.sbjCd}');" style="display:none"><spring:message code="button.cancel"/></button>
						<button class="btn btn-warning btn-sm" onclick="javascript:delOffline('${item.sbjCd}');"><spring:message code="button.delete"/></button>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty offlineSubjectList}">
				<tr>
					<td colspan="5"><spring:message code="course.message.subject.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#sortable-offline').sortable({
					handle : 'i.glyphicon-move',
			    	stop : function(event, ui) {
			    		var sortString = "";
			    		$("#sortable-offline").children('tr').each(function(){
			    			sortString += "|"+$(this).attr("id");
			    		});
			    		sortString = sortString.substring(1);
			    		offlineSort(sortString);
			    	}
				});
				$( "#sortable-offline" ).disableSelection();
			});
		</script>
