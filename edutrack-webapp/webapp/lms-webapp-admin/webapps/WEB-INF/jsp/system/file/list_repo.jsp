<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


						<table summary="<spring:message code="system.title.file.manage.repository"/>" style="width: 100%" class="table table-bordered">
							<colgroup>
								<col width="auto">
								<col width="120px">
								<col width="110px">
								<col width="180px">
								<col width="230px">
								<col width="100px">
								<col width="60px">
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="system.title.file.name.repository"/></th>
									<th scope='col'><spring:message code="system.title.file.code.repository"/></th>
									<th scope='col'><spring:message code="system.title.file.filecnt"/></th>
									<th scope='col'><spring:message code="system.title.file.path.repository"/></th>
									<th scope='col'><spring:message code="system.title.file.tablenm.repository"/></th>
									<th scope='col'><spring:message code="common.title.manage"/></th>
									<th scope='col'><spring:message code="common.title.edit"/></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="item" items="${repoList}" varStatus="status">
								<tr>
									<c:set var="repoNm" value="${item.repoNm}"/>
									<c:forEach var="lang" items="${item.fileRepoLangList}">
										<c:if test="${lang.langCd eq LOCALEKEY}">
											<c:set var="repoNm" value="${lang.repoNm}"/>
										</c:if>
									</c:forEach>
									<td class="wordbreak"><a href="<c:url value="/adm/system/file/fileMain"/>?repoCd=${item.repoCd}" title="<spring:message code="system.title.file.list"/>">${repoNm}</a></td>
									<td><a href="<c:url value="/adm/system/file/fileMain"/>?repoCd=${item.repoCd}" title="<spring:message code="system.title.file.list"/>">${item.repoCd}</a></td>
									<td class="text-right">${item.fileCnt}</td>
									<td>${item.repoDfltPath}</td>
									<td>${item.parTableNm}</td>
									<td class="text-center">
										<a class="btn btn-info btn-sm" href="<c:url value="/adm/system/file/fileMain"/>?repoCd=${item.repoCd}" title="<spring:message code="system.title.file.list"/>"><spring:message code="system.title.file.list"/></a>
									</td>
									<td class="text-center">
										<a class="btn btn-primary btn-sm" href="javascript:editRepo('${item.repoCd}');" title='<spring:message code="common.title.edit"/>'><spring:message code="common.title.edit"/></a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty repoList}">
								<tr>
									<td colspan="6"><spring:message code="system.message.file.norepository"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>