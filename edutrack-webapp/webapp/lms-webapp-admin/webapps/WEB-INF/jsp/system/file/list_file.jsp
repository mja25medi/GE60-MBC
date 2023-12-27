<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

			<table summary="<spring:message code="system.title.file.list"/>" style="width: 100%" class="table table-bordered">
				<colgroup>
					<col width="50px">
					<col width="100px">
					<col width="auto">
					<col width="auto">
					<col width="auto">
					<col width="auto">
					<col width="auto">
					<col width="120px;">
				</colgroup>
				<thead>
					<tr>
						<th scope='col' class="top"><spring:message code="common.title.no"/></th>
						<th scope='col' class="top"><spring:message code="system.title.file.thumb"/></th>
						<th scope='col' class="top"><spring:message code="common.title.file"/></th>
						<th scope='col' class="top"><spring:message code="common.title.useyn"/></th>
						<th scope='col' class="top"><spring:message code="common.title.hits"/></th>
						<th scope='col' class="top"><spring:message code="system.title.file.filetype"/></th>
						<th scope='col' class="top"><spring:message code="system.title.file.lastview.dttm"/></th>
						<th scope='col' class="top"><spring:message code="common.title.manage"/></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${fileList}" varStatus="status">
					<tr>
						<td class="text-right">${item.fileSn}</td>
						<td>
							<c:set var="thumTxt" value="-"/>
							<c:if test="${fn:toUpperCase(item.fileExt) eq 'JPG'
										|| fn:toUpperCase(item.fileExt) eq 'GIF'
										|| fn:toUpperCase(item.fileExt) eq 'PNG'
										|| fn:toUpperCase(item.fileExt) eq 'BMP'}">
							<c:set var="thumTxt" value=""/>
								<c:if test="${not empty item.image}">
								<a href="${item.viewUrl}" rel='gal' class='nyroModal' title="<spring:message code="common.title.view"/>" >
								<span><img src="${item.thumbUrl}" class="resize100x40 bm1 tm3"></span></a>
								</c:if>
							</c:if>
							${thumTxt }
						</td>
						<td class="wordbreak">${item.downloadTag}</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${item.usingCnt == -1 }">
									<span class='wblue'><spring:message code="common.title.nodata"/></span>
								</c:when>
								<c:when test="${item.usingCnt == -1 }">
									<span class='accent'><spring:message code="common.title.nouse"/></span>
								</c:when>
								<c:otherwise>
									<span class='dblue'><spring:message code="common.title.use"/>(${item.usingCnt})</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="text-right">${item.hits}</td>
						<td class="text-center">${item.fileType}</td>
						<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.lastInqDttm}"/></td>
						<td class="text-center">
							<a class="btn btn-info btn-sm btnViewFile" href="javascript:viewFile('${item.fileSn}');" title="<spring:message code="button.view"/>"><spring:message code="button.view"/></a>
							<a class="btn btn-warning btn-sm btnDeleteFile" href="javascript:deleteFile('${item.fileSn}', '${item.usingCnt}');" title="<spring:message code="button.delete"/>" ><spring:message code="button.delete"/></a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty fileList}">
					<tr>
						<td colspan="8"><spring:message code="system.message.file.nofile"/></td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<meditag:paging pageInfo="${pageInfo}" funcName="listFile"/>


