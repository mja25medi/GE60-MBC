<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="olcCartrgList" value="${olcCartrgList}"/>

						<table summary='<spring:message code="olc.title.main.manage"/>' class="table table-striped table-bordered">
							<colgroup>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:75px"/>
								<col style="width:80px"/>
								<col style="width:100px"/>
								<col style="width:75px"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="olc.title.category.name"/></th>
									<th scope="col"><spring:message code="olc.title.cartridge.name"/></th>
									<th scope="col"><spring:message code="common.title.manage"/></th>
									<th scope="col"><spring:message code="olc.title.cartridge.contents.cnt"/></th>
									<th scope="col"><spring:message code="common.title.regdate"/></th>
									<th scope="col"><spring:message code="common.title.edit"/></th>
									<th scope="col"><spring:message code="org.title.orginfo.design"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${olcCartrgList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${status.count}</td>
									<td class="wordbreak">${item.ctgrNm}</td>
									<td class="wordbreak">
										<a href="javascript:cntsManage('${item.cartrgCd}')">${item.cartrgNm}</a>
										<c:if test="${item.cntsCnt > 0 }">
										<a href="javascript:preview('${item.userNo}', '${item.cartrgCd}', '${item.cartrgNm}');"><i class="glyphicon glyphicon-eye-open"></i></a>
										</c:if>
									</td>
									<td class="text-center">
										<c:choose>
											<c:when test="${status.last}"><a href="#" class="btn btn-default btn-xs"><i class="fa fa-arrow-down"></i></a></c:when>
											<c:otherwise><a href="javascript:moveOLC('down','${item.cartrgCd}')" class="btn btn-primary btn-xs"><i class="fa fa-arrow-down"></i></a></c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${status.first}"><a href="#" class="btn btn-default btn-xs"><i class="fa fa-arrow-up"></i></a></c:when>
											<c:otherwise><a href="javascript:moveOLC('up','${item.cartrgCd}')" class="btn btn-primary btn-xs"><i class="fa fa-arrow-up"></i></a></c:otherwise>
										</c:choose>
									</td>
									<td class="text-right">${item.cntsCnt}</td>
									<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
									<td class="text-center"><a href="javascript:olcEdit('${item.cartrgCd}')" class="btn btn-info btn-sm"><spring:message code="button.edit"/></a></td>
									<td class="text-center"><a href="javascript:olcEditDesign('${item.cartrgCd}')" class="btn btn-info btn-sm"><spring:message code="button.edit"/></a></td>
								</tr>
								</c:forEach>
								<c:if test="${empty olcCartrgList}">
								<tr>
									<td colspan="8"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
