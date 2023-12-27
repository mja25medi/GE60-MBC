<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
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
				<th scope="col" >과제</th>
				<th scope="col" >시험</th>
				<th scope="col" >포럼</th>
				<th scope="col" >설문</th>
				<th scope="col" >프로젝트</th>
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
				<td>${item.asmtCnt}건</td>
				<td>${item.examCnt}건</td>
				<td>${item.forumCnt}건</td>
				<td>${item.reshCnt}건</td>
				<td>${item.prjtCnt}건</td>
				<td><a class="squarebutton" href="<c:url value="/LectureManageAdmin.do"/>?cmd=manage&amp;createCourseVO.crsCreCd=${item.crsCreCd}" title="관리" ><span>관리</span></a></td>
			</tr>
			</c:forEach>
			<c:if test="${empty createCourseList}">
			<tr>
				<td colspan="10">*등록된 데이타가 없습니다.</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listCreateCourse"/>
</mhtml:frm_body>
</mhtml:html>