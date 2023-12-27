<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="List">
	<table summary="<spring:message code="system.title.menu.manage"/>" class="table table-bordered">
		<colgroup>
			<col style="width:120px"/>
			<col style="width:auto"/>
			<col style="width:80px"/>
			<col style="width:80px"/>
			<col style="width:50px"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="system.title.menu.code"/></th>
				<th scope="col"><spring:message code="system.title.menu.name"/></th>
				<th scope="col"><a href="#none" onclick="javascript:checkAllList();"><spring:message code="system.title.menu.auth.use"/></a></th>
				<th scope="col"><a href="#none" onclick="javascript:checkAllWrite();"><spring:message code="system.title.menu.auth.write"/></a></th>
				<th scope="col"><spring:message code="common.title.edit"/></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item1" items="${menuList}">
			<c:url var="typeImg" value="/img/framework/icon/icon_contents.gif"/>
			<c:if test="${item1.subCnt > 0}">
				<c:url var="typeImg" value="/img/framework/icon/icon_category.gif"/>
			</c:if>
			<c:set var="menuName1" value="${item1.menuNm}"/>
			<c:forEach var="lang" items="${item1.menuLangList}">
				<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
			</c:forEach>
			<c:set var="menuCd" value="|${item1.menuCd}" />
			<c:set var="menuPath" value="${fn:replace(item1.menuPath, menuCd, '')}" />
			<c:set var="menuPath" value="${fn:replace(menuPath, '|', ' menu_')}" />
			<tr class="menu_Lvl${item1.menuLvl} ${menuPath}">
				<td class="text-center"><a href="javascript:toggleMenu('${item1.menuCd}');">${item1.menuCd}</a></td>
				<td class="wordbreak">
					<img src="/img/framework/common/blank.gif" height="10" width="${(item1.menuLvl-1)*20}" />
					<img src="${typeImg}" align="absmiddle" />
					<c:if test="${item1.menuLvl < 3}">
						<a href="javascript:subMenuWrite('${item1.menuCd}');"><img src="<c:url value="/img/framework/icon/icon_function_add.gif"/>" align="absmiddle" alt="${menuName1}" /></a>
					</c:if>
					&nbsp;${menuName1}
				</td>
				<td class="text-center">
					<c:if test="${item1.subCnt == 0}">
					<input type="checkbox" name="viewAuth" value="${item1.menuCd}" style="border:0" <c:if test="${item1.viewAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/>
					</c:if>
				</td>
				<td class="text-center">
					<c:if test="${item1.subCnt == 0}">
					<input type="checkbox" name="creAuth" value="${item1.menuCd}" style="border:0" <c:if test="${item1.creAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/>
					</c:if>
				</td>
				<td class="text-center">
					<a class="btn btn-primary btn-sm" href="javascript:menuEdit('${item1.menuCd}')" title="<spring:message code="button.edit"/>"><spring:message code="button.edit"/></a>
				</td>
			</tr>
			<c:forEach var="item2" items="${item1.subList}">
				<c:url var="typeImg" value="/img/framework/icon/icon_contents.gif"/>
				<c:if test="${item2.subCnt > 0}">
					<c:url var="typeImg" value="/img/framework/icon/icon_category.gif"/>
				</c:if>
				<c:set var="menuName2" value="${item2.menuNm}"/>
				<c:forEach var="lang" items="${item2.menuLangList}">
					<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName2" value="${lang.menuNm}"/></c:if>
				</c:forEach>
				<c:set var="menuCd" value="|${item2.menuCd}" />
				<c:set var="menuPath" value="${fn:replace(item2.menuPath, menuCd, '')}" />
				<c:set var="menuPath" value="${fn:replace(menuPath, '|', ' menu_')}" />
			<tr class="menu_Lvl${item2.menuLvl} ${menuPath}">
				<td class="text-center"><a href="javascript:toggleMenu('${item2.menuCd}');">${item2.menuCd}</a></td>
				<td class="wordbreak">
					<img src="/img/framework/common/blank.gif" height="10" width="${(item2.menuLvl-1)*20}" />
					<img src="${typeImg}" align="absmiddle" />
					<c:if test="${item2.menuLvl < 3}">
						<a href="javascript:subMenuWrite('${item2.menuCd}');"><img src="<c:url value="/img/framework/icon/icon_function_add.gif"/>" align="absmiddle" alt="${menuName2}" /></a>
					</c:if>
					&nbsp;${menuName2}
				</td>
				<td class="text-center">
					<c:if test="${item2.subCnt == 0}">
					<input type="checkbox" name="viewAuth" value="${item2.menuCd}" style="border:0" <c:if test="${item2.viewAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/>
					</c:if>
				</td>
				<td class="text-center">
					<c:if test="${item2.subCnt == 0}">
					<input type="checkbox" name="creAuth" value="${item2.menuCd}" style="border:0" <c:if test="${item2.creAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/>
					</c:if>
				</td>
				<td class="text-center">
					<a class="btn btn-primary btn-sm" href="javascript:menuEdit('${item2.menuCd}')" title="<spring:message code="button.edit"/>"><spring:message code="button.edit"/></a>
				</td>
			</tr>
				<c:forEach var="item3" items="${item2.subList}">
					<c:url var="typeImg" value="/img/framework/icon/icon_contents.gif"/>
					<c:if test="${item3.subCnt > 0}">
						<c:url var="typeImg" value="/img/framework/icon/icon_category.gif"/>
					</c:if>
					<c:set var="menuName3" value="${item3.menuNm}"/>
					<c:forEach var="lang" items="${item3.menuLangList}">
						<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName3" value="${lang.menuNm}"/></c:if>
					</c:forEach>
					<c:set var="menuCd" value="|${item3.menuCd}" />
					<c:set var="menuPath" value="${fn:replace(item3.menuPath, menuCd, '')}" />
					<c:set var="menuPath" value="${fn:replace(menuPath, '|', ' menu_')}" />
			<tr class="menu_Lvl${item3.menuLvl} ${menuPath}">
				<td class="text-center"><a href="javascript:toggleMenu('${item3.menuCd}');">${item3.menuCd}</a></td>
				<td class="wordbreak">
					<img src="/img/framework/common/blank.gif" height="10" width="${(item3.menuLvl-1)*20}" />
					<img src="${typeImg}" align="absmiddle" />
					<c:if test="${item3.menuLvl < 3}">
						<a href="javascript:subMenuWrite('${item3.menuCd}');"><img src="<c:url value="/img/framework/icon/icon_function_add.gif"/>" align="absmiddle" alt="${menuName3}" /></a>
					</c:if>
					&nbsp;${menuName3}
				</td>
				<td class="text-center">
					<c:if test="${item3.subCnt == 0}">
					<input type="checkbox" name="viewAuth" value="${item3.menuCd}" style="border:0" <c:if test="${item3.viewAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/>
					</c:if>
				</td>
				<td class="text-center">
					<c:if test="${item3.subCnt == 0}">
					<input type="checkbox" name="creAuth" value="${item3.menuCd}" style="border:0" <c:if test="${item3.creAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/>
					</c:if>
				</td>
				<td class="text-center">
					<a class="btn btn-primary btn-sm" href="javascript:menuEdit('${item3.menuCd}')" title="<spring:message code="button.edit"/>"><spring:message code="button.edit"/></a>
				</td>
			</tr>
				</c:forEach>	
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<input type="submit" value="submit" style="display:none" />
	</form>