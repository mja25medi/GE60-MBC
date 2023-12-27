<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


				<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered">
					<colgroup>
						<col style="width:100px"/>
						<col style="width:auto"/>
						<col style="width:80px"/>
						<col style="width:80px"/>
						<col style="width:80px"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="common.title.manage"/></th>
							<th scope="col"><spring:message code="system.title.menu.name"/></th>
							<th scope="col"><spring:message code="system.title.menu.write"/></th>
							<th scope="col"><spring:message code="system.title.menu.auth.use"/></th>
							<th scope="col"><spring:message code="system.title.menu.auth.write"/></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item1" items="${menuList}" varStatus="status1">
						<c:set var="menu_icon" value="fa fa-file fa-fw"/>
						<c:set var="menuName1" value="${item1.menuNm}"/>
						<c:forEach var="lang" items="${item1.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
						</c:forEach>
						<c:if test="${not empty item1.subList}"><c:set var="menu_icon" value="fa fa-folder-open fa-fw"/></c:if>
						<tr>
							<td class="text-center">
								<c:if test="${status1.first ne true}">
								<a href="javascript:moveMenu('up','${item1.menuCd}')" class="btn btn-default btn-sm"><i class="fa fa-arrow-up"></i></a>
								</c:if>
								<c:if test="${status1.last ne true}">
								<a href="javascript:moveMenu('down','${item1.menuCd}')" class="btn btn-default btn-sm"><i class="fa fa-arrow-down"></i></a>
								</c:if>
							</td>
							<td><i class="${menu_icon}" style="margin-left:${item1.menuLvl * 10}px;"></i> ${menuName1} <a href="javascript:menuEdit('${item1.menuCd}')"><i class="glyphicon glyphicon-cog"></i></a></td>
							<td class="text-center">
								<a href="javascript:subMenuWrite('${item1.menuCd}')" class="btn btn-primary btn-sm"><spring:message code="button.write"/></a>
							</td>
							<td class="text-center">
								<c:if test="${empty item1.subList}">
								<input type="checkbox" name="viewAuth" value="${item1.menuCd}" style="border:0" <c:if test="${item1.viewAuth eq 'Y'}">checked</c:if> <c:if test="${empty authGrpCd}">disabled</c:if>/>
								</c:if>
							</td>
							<td class="text-center">
								<c:if test="${empty item1.subList}">
								<input type="checkbox" name="creAuth" value="${item1.menuCd}" style="border:0" <c:if test="${item1.creAuth eq 'Y'}">checked</c:if> <c:if test="${empty authGrpCd}">disabled</c:if>/>
								</c:if>
							</td>
						</tr>
						<c:forEach var="item2" items="${item1.subList}" varStatus="status2">
							<c:set var="menu_icon" value="fa fa-file fa-fw"/>
							<c:set var="menuName2" value="${item2.menuNm}"/>
							<c:forEach var="lang" items="${item2.menuLangList}">
								<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName2" value="${lang.menuNm}"/></c:if>
							</c:forEach>
							<c:if test="${not empty item2.subList}"><c:set var="menu_icon" value="fa fa-folder-open fa-fw"/></c:if>
						<tr>
							<td class="text-center">
								<c:if test="${status2.first ne true}">
								<a href="javascript:moveMenu('up','${item2.menuCd}')" class="btn btn-default btn-sm"><i class="fa fa-arrow-up"></i></a>
								</c:if>
								<c:if test="${status2.last ne true}">
								<a href="javascript:moveMenu('down','${item2.menuCd}')" class="btn btn-default btn-sm"><i class="fa fa-arrow-down"></i></a>
								</c:if>
							</td>
							<td><i class="${menu_icon}" style="margin-left:${item2.menuLvl * 15}px;"></i> ${menuName2} <a href="javascript:menuEdit('${item2.menuCd}')"><i class="glyphicon glyphicon-cog"></i></a></td>
							<td class="text-center">
								<a href="javascript:subMenuWrite('${item2.menuCd}')" class="btn btn-primary btn-sm"><spring:message code="button.write"/></a>
							</td>
							<td class="text-center">
								<c:if test="${empty item2.subList}">
								<input type="checkbox" name="viewAuth" value="${item2.menuCd}" style="border:0" <c:if test="${item2.viewAuth eq 'Y'}">checked</c:if> <c:if test="${empty authGrpCd}">disabled</c:if>/>
								</c:if>
							</td>
							<td class="text-center">
								<c:if test="${empty item2.subList}">
								<input type="checkbox" name="creAuth" value="${item2.menuCd}" style="border:0" <c:if test="${item2.creAuth eq 'Y'}">checked</c:if> <c:if test="${empty authGrpCd}">disabled</c:if>/>
								</c:if>
							</td>
						</tr>
							<c:forEach var="item3" items="${item2.subList}" varStatus="status3">
								<c:set var="menu_icon" value="fa fa-file fa-fw"/>
								<c:set var="menuName3" value="${item3.menuNm}"/>
								<c:forEach var="lang" items="${item3.menuLangList}">
									<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName3" value="${lang.menuNm}"/></c:if>
								</c:forEach>
								<c:if test="${not empty item3.subList}"><c:set var="menu_icon" value="fa fa-folder-open fa-fw"/></c:if>
						<tr>
							<td class="text-center">
								<c:if test="${status3.first ne true}">
								<a href="javascript:moveMenu('up','${item3.menuCd}')" class="btn btn-default btn-sm"><i class="fa fa-arrow-up"></i></a>
								</c:if>
								<c:if test="${status3.last ne true}">
								<a href="javascript:moveMenu('down','${item3.menuCd}')" class="btn btn-default btn-sm"><i class="fa fa-arrow-down"></i></a>
								</c:if>
							</td>
							<td>
								<i class="${menu_icon}" style="margin-left:${item3.menuLvl * 17}px;"></i> ${menuName3} <a href="javascript:menuEdit('${item3.menuCd}')" ><i class="glyphicon glyphicon-cog"></a>
							</td>
							<td class="text-center">
								-
							</td>
							<td class="text-center">
								<c:if test="${empty item3.subList}">
								<input type="checkbox" name="viewAuth" value="${item3.menuCd}" style="border:0" <c:if test="${item3.viewAuth eq 'Y'}">checked</c:if> <c:if test="${empty authGrpCd}">disabled</c:if>/>
								</c:if>
							</td>
							<td class="text-center">
								<c:if test="${empty item3.subList}">
								<input type="checkbox" name="creAuth" value="${item3.menuCd}" style="border:0" <c:if test="${item3.creAuth eq 'Y'}">checked</c:if> <c:if test="${empty authGrpCd}">disabled</c:if>/>
								</c:if>
							</td>
						</tr>
							</c:forEach>
						</c:forEach>
					</c:forEach>
					<c:if test="${empty menuList}">
						<tr>
							<td colspan="6"><spring:message code="system.message.menu.nodata"/></td>
						</tr>
					</c:if>
					</tbody>
				</table>
				<script type="text/javascript">
					$(document).ready(function() {
						ItemDTO.menuCount = ${fn:length(menuList)};
					});
				</script>
