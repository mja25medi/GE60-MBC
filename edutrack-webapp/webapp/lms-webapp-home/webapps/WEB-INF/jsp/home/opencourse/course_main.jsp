<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsList" value="${openCrsList}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>

<mhtml:home_body>
				<mhtml:home_location />
				<div class="row">
					<div class="col-lg-12">
						<div class="alert alert-info">
							<spring:message code="course.open.title.head.msg" arguments="${USER_ORGNM}"/><br /><br />
							<spring:message code="course.open.title.head.sub.msg"/>
						</div>
					</div>
				</div>
				<table class="table table-bordered wordbreak">
					<caption class="sr-olny"><spring:message code="course.open.title.category.manage"/></caption>
					<colgroup>
						<col style="width:auto"/>
						<col style="width:10%"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="course.open.title.course.name"/></th>
							<th scope="col"><spring:message code="common.title.enter"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${openCrsList}" var="item" varStatus="status">
						<tr>
							<td>${item.crsNm}</td>
							<td class="text-center">
								<a href="<c:url value="/home/openCourse/mainCnts"/>?crsCd=${item.crsCd }" class="btn btn-primary btn-sm"><spring:message code="common.title.enter"/> </a>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty openCrsList}">
						<tr>
							<td colspan="3"><spring:message code="course.open.message.course.nodata"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>

<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
</script>
</mhtml:home_body>
</mhtml:home_html>
