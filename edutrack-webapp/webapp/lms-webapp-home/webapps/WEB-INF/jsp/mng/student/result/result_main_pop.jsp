<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<style>
	#cpltTable th {background-color: #eee;}
	#courseTable th {background-color: #eee;}
</style>

<c:set var="studentList" value="${eduResultList}"/>
<c:set var="eduResultVO" value="${vo}"/>
	
	<h4>[과정정보]</h4>
	<table id="courseTable" class="table table-bordered wordbreak">
		<tr>
			<th>과정명</td>
			<td>${createCourseVO.crsCreNm}</td>
			<th>기수명</td>
			<td>${createCourseVO.creYear}년도 ${createCourseVO.creTerm}기수</td>
			<th>학습기간</td>
			<td>${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}</td>
		</tr>
	</table>
	<h4>[수료기준]</h4>
	<table id="cpltTable" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:12%;"/>
			<col style="width:12%;"/>
			<col style="width:12%;"/>
			<col style="width:12%;"/>
			<col style="width:11%;"/>
			<col style="width:12%"/>
			<col style="width:12%;"/>
			<col style="width:12%;"/>
		</colgroup>
		<tr>
			<th>출석</th>
			<td>80% 이상</td>
			<th>시험</th>
			<td>${createCourseVO.examRatio}</td>
			<th>과제</th>
			<td>${createCourseVO.asmtRatio}</td>
			<th>진행단계평가</td>
			<td>${createCourseVO.semiExamRatio}</td>
		</tr>
	</table>
	<div style="float:right">
				<a href="javascript:excelDownload();" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i>엑셀 다운로드</a>
	</div>

	<table summary="<spring:message code="student.title.result.manage"/>" class="table table-bordered wordbreak" style="margin-top:5%">
		<colgroup>
			<col style="width:6%;"/>
			<col style="width:auto;"/>
			<col style="width:auto;"/>
			<col style="width:17%;"/>
			<col style="width:8%;"/>
			<col style="width:8%;"/>
			<col style="width:10%;"/>
			<col style="width:8%;"/>
			<col style="width:8%;"/>
			<col style="width:8%;"/>
			
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th><spring:message code="user.title.userinfo.userid"/></th>
				<th><spring:message code="user.title.userinfo.name"/></th>
				<th>소속기업</th>
				<th><spring:message code="student.title.result.study.ratio"/></th>
				<th><spring:message code="course.title.course.ratio.exam"/></th>
				<th><spring:message code="course.title.course.ratio.semiExam"/></th>
				<th><spring:message code="course.title.course.ratio.asmt"/></th>
				<th><spring:message code="student.title.result.totalscore"/></th>
				<th>수료여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${studentList}" varStatus="status">
			<tr>
				<td class="text-center">${status.count}</td>
				<td class="text-center">${item.userId}</td>
				<td class="text-center">${item.userNm}</td>
				<td class="text-center">${item.deptNm}</td>
				<td class="text-center">${item.prgrRate}</td>
				<td class="text-center">${meditag:round(item.examScore,1)}</td>
				<td class="text-center">${meditag:round(item.semiExamScore,1)}</td>
				<td class="text-center">${meditag:round(item.asmtScore,1)}</td>
				<td class="text-center">${meditag:round(item.totScore,1)}</td>
				<td class="text-center">${item.enrlStsNm}</td>
			</tr>
			</c:forEach>
			<c:if test="${empty studentList}">
			<tr>
				<td colspan="10"><spring:message code="student.message.student.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<form id="eduResultForm" name="eduResultForm">
		<input type="hidden" name="crsCreCd" value="${eduResultVO.crsCreCd}" />
		<input type="submit" value="submit" style="display:none" />
	</form>
	

<script type="text/javascript">	
	function excelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		$("#eduResultForm").attr('target', '_m_download_iframe');
		$("#eduResultForm").attr("action", "/mng/std/eduResult/listExcelDownload");
		$("#eduResultForm").submit();
		$("#eduResultForm").removeAttr('target');
	}
</script>