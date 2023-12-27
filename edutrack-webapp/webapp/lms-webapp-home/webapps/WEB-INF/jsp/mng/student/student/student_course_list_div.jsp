<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="createCourseList" value="${createCourseList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
</mhtml:head>

<mhtml:frm_body>
	<table summary="과정 목룍 표" style="width:100%" class="table_list">
		<colgroup>
			<col style="width:auto"/>
			<col style="width:60px"/>
			<col style="width:50px"/>
			<col style="width:140px"/>
			<col style="width:80px"/>
			<col style="width:60px"/>
			<col style="width:60px"/>
			<col style="width:60px"/>
			<col style="width:60px"/>
			<col style="width:60px"/>
			<col style="width:50px"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col" >과정 명</th>
				<th scope="col" >년도</th>
				<th scope="col" >기수</th>
				<th scope="col" >교육기간</th>
				<th scope="col" >고용보험</th>
				<th scope="col" >정원</th>
				<th scope="col" >신청</th>
				<th scope="col" >수강</th>
				<th scope="col" >수료</th>
				<th scope="col" >미수료</th>
				<th scope="col" >관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${createCourseList}" var="item" varStatus="status">
			<tr>
				<td class="left" style="padding-left:5px">${item.crsCreNm}</td>
				<td>${item.creYear} 년</td>
				<td>${item.creTerm}</td>
				<td>
					<c:if test="${item.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></c:if>
					<c:if test="${item.enrlStartDttm eq ''}">${item.creYear}년 상시</c:if>
				</td>
				<td>
					<c:if test="${item.ueinsYn eq 'Y'}">적용</c:if>
					<c:if test="${item.ueinsYn ne 'Y'}">미적용</c:if>
				</td>
				<td>
					<c:if test="${item.nopLimitYn eq 'Y'}">${item.enrlNop} 명</c:if>
					<c:if test="${item.nopLimitYn ne 'Y'}">-</c:if>
				</td>
				<td>${item.enrlCnt} 명</td>
				<td>${item.cnfmCnt} 명</td>
				<td>${item.cpltCnt} 명</td>
				<td>${item.failCnt} 명</td>
				<td><a class="squarebutton" href="<c:url value="/StudentStudentAdmin.do"/>?cmd=manageStudent&amp;studentVO.crsCreCd=${item.crsCreCd}" title="관리" ><span>관리</span></a></td>
			</tr>
			</c:forEach>
			<c:if test="${empty createCourseList}">
			<tr>
				<td colspan="11">* 등록된 데이타가 없습니다.</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listCreateCourse"/>
</mhtml:frm_body>
</mhtml:html>