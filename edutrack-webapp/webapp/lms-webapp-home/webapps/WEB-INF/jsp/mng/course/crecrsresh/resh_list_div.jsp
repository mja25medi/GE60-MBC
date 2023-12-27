<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="creCrsReshList" value="${creCrsReshList}"/>
<c:set var="reshSn_chk" value="-1" />
		<table summary="<spring:message code="course.title.resh.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:150px"/>
				<col style="width:150px"/>
				<col style="width:80px"/>
				<col style="width:60px"/>
				<col style="width:60px"/>
				<col style="width:98px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col" ><spring:message code="course.title.resh.title"/></th>
					<th scope="col"><spring:message code="common.title.startdate"/></th>
					<th scope="col"><spring:message code="common.title.enddate"/></th>
					<th scope="col"><spring:message code="lecture.title.research.regyn"/></th>
					<th scope="col"><spring:message code="course.title.resh.qstncnt"/></th>
					<th scope="col"><spring:message code="course.title.resh.ansrcnt"/></th>
					<th scope="col"><spring:message code="course.title.resh.result"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${creCrsReshList}" varStatus="status">
				<tr>
					<c:choose>
						<c:when test="${status.count ne '0' }"><c:set var="reshSn_chk" value="${reshSn_chk}|${item.reshSn}" /></c:when>
					</c:choose>

					<td class="text-right">${status.count}</td>
					<td class="wordbreak">
						${item.reshTitle}
						<a href="javascript:editResh('${item.reshSn}');" ><i class="fa fa-cog"></i></a>
					</td>
					<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.startDttm}"/></td>
					<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.endDttm}"/></td>
					<td class="text-center">
						<c:set var="regYn" value="${item.regYn}"/>
						<c:if test="${empty item.regYn}"><c:set var="regYn" value="N"/></c:if>
						<meditag:codename code="${regYn}" category="REG_YN" />
					</td>
					<td class="text-right">${item.itemCnt}</td>
					<td class="text-right">${item.stdCnt}</td>
					<td class="text-center">
						<a href="javascript:resultResh('${item.reshSn}');" class="btn btn-info btn-sm"><spring:message code="button.view.result"/> </a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty creCrsReshList}">
				<tr>
					<td colspan="9"><spring:message code="course.message.resh.nodata"/></td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<script type="text/javascript">
			document.getElementById("reshSn_chk").value = "${reshSn_chk}";
		</script>