<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<head>
<style type="text/css">
	table tr.record { background-color: #FFFFFF }
	table tr.stacktrace { background-color: #F4F8F8; padding-left: 15px; }
</style>
</head>
<body>
<section class="content">
<div class="box">
<div class="box-body">
	<div class="row" id="content">
		<div class="col-md-12">
			<table summary="<spring:message code="log.title.course.status"/>" style="width:100%" class="table table-bordered">
				<colgroup>
					<col style="width:50px;"/>
					<col style="width:auto;"/>
	
					<col style="width:70px;"/>
					<col style="width:70px;"/>
	
					<col style="width:70px;"/>
					<col style="width:80px;"/>
	
					<col style="width:70px;"/>
					<col style="width:70px;"/>
					<col style="width:70px;"/>
	
					<col style="width:70px;"/>
					<col style="width:70px;"/>
					<col style="width:70px;"/>
	
				</colgroup>
				<thead>
					<tr>
						<th scope="col" rowspan="2" style="border-right:1px solid #dadada"><spring:message code="common.title.no"/></th>
						<th scope="col" rowspan="2" style="border-right:1px solid #dadada"><spring:message code="org.title.orginfo.orgname"/></th>
						<th scope="col" colspan="2" style="border-right:1px solid #dadada"><spring:message code="log.title.org.user"/></th>
						<th scope="col" colspan="2" style="border-right:1px solid #dadada"><spring:message code="log.title.org.crs"/></th>
						<th scope="col" colspan="3" style="border-right:1px solid #dadada"><spring:message code="log.title.org.crecrs"/></th>
						<th scope="col" colspan="3" style="border-right:1px solid #dadada"><spring:message code="log.title.org.enroll"/></th>
					</tr>
					<tr>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.org.user.active"/></th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.org.user.withdrawn"/></th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="common.title.status.useyn_y"/></th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="common.title.status.useyn_n"/></th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.org.crecrs.bef"/></th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.org.crecrs.ing"/></th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.org.crecrs.cplt"/></th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;">신청완료</th>
						<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.org.student.complete"/></th>
						<th scope="col" style="border-top:1px solid #dadada;"><spring:message code="log.title.org.student.incomplete"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${orgStatusList}" varStatus="status">
					<tr>
						<td class="text-right" style="border-right:1px solid #dadada">${pageInfo.totalRecordCount - status.index}</td>
						<td class="wordbreak" style="border-right:1px solid #dadada">${item.orgNm}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.userUseCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.userOutCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.crsUseCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.crsNouseCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.creCrsBefCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.creCrsIngCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.creCrsEndCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.stuEnrlCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.stuCpltCnt}</td>
						<td class="text-right" style="border-right:1px solid #dadada">${item.stuFailCnt}</td>
					</tr>
					</c:forEach>
					<c:if test="${empty orgStatusList}">
					<tr>
						<td colspan="12"><spring:message code="org.message.orginfo.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
</div>
</section>
</body>
