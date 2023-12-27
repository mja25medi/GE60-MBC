<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="vo" value="${vo}"/>

	
	<form name="" id="" onsubmit="return false">
	
	<div style="width:100%;">
		<div style="float:right; height:40px; line-height:30px;">
			<button class="btn btn-default btn-sm" onclick="closeWriteArea()" style="float:right">기수목록</button>
		</div>
	</div>
	<table summary="<spring:message code="course.title.createcourse.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:17%"/>
			<col style="width:33%"/>
			<col style="width:17%"/>
			<col style="width:33%"/>
		</colgroup>
		<tr>
			<td class="text-left"><spring:message code="course.title.term.year"/></td>
			<td class="text-left">${vo.creYear}년도</td>
			<td class="text-left"><spring:message code="course.title.term"/></td>
			<td class="text-left">${vo.creTerm}기</td>
		</tr>
		<tr>
			<td class="text-left"><spring:message code="course.title.createterm.aplc.date"/></td>
			<td class="text-left">
				<c:if test="${vo.enrlAplcStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${vo.enrlAplcStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${vo.enrlAplcEndDttm}"/></c:if>
			</td>
			<td class="text-left"><spring:message code="course.title.createterm.enrl.date"/></td>
			<td class="text-left">
				<c:if test="${vo.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${vo.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${vo.enrlEndDttm}"/></c:if>
			</td>
		</tr>
		<tr>
			<td class="text-left"><spring:message code="course.title.createterm.end.date"/></td>
			<td class="text-left">
				<c:if test="${vo.termEndDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${vo.termEndDttm}"/></c:if>
			</td>
			<td class="text-left"><spring:message code="course.title.createterm.score.date"/></td>
			<td class="text-left">
				<c:if test="${vo.scoreStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${vo.scoreStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${vo.scoreEndDttm}"/></c:if>
			</td>
		</tr>
		<tr>
			<td class="text-left">기업명</td>
			<td class="text-left" colspan="3"></td>
		</tr>
		<tr>
			<td class="text-left"><spring:message code="course.title.createterm.desc"/></td>
			<td class="text-left" colspan="3">${vo.termDesc}</td>
		</tr>		
	</table>
	</form>
			
	<form name="courseForm" id="courseForm" onsubmit="return false">		
	<div style="width:100%;">
		<div style="float:left;">
			<h5>기수내 과정정보</h5>
		</div>
		<div style="float:right">
			<button class="btn btn-primary btn-sm" onclick=""><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick=""><spring:message code="button.delete"/></button>
			<button class="btn btn-default btn-sm" onclick=""><spring:message code="button.close"/></button>
			<button class="btn btn-default btn-sm" onclick="addCreateCourseForm()" >과정등록</button>
		</div>
	</div>
	<table summary="<spring:message code="course.title.createcourse.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:50px"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:95px"/>
			<col style="width:85px"/>
			<col style="width:80px"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="common.title.no"/></th>
				<th scope="col">기업명</th>
				<th scope="col">과정명</th>
				<th scope="col">결제금액</th>
				<th scope="col">모집인원</th>
				<th scope="col"><spring:message code="course.title.createcourse.info"/></th>
				<th scope="col"><spring:message code="course.title.createcourse.operate"/></th>
				<th scope="col"><spring:message code="course.title.createcourse.registrate"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cretermCourseList}" var="item" varStatus="status">
			<tr>
				<td class="text-right">${status.count}</td>
				<td class="text-center">기업명1</td>
				<td class="text-center">과정명1</td>
				<td class="text-center">결제금액1</td>
				<td class="text-center">모집인원1</td>
				<td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsInfoMngForm()"><spring:message code="course.title.createcourse.info"/></button>
				</a>
				<td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsOperationMngForm()"><spring:message code="course.title.createcourse.operate"/></button>
				</td>
				<td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsStudentMngForm()"><spring:message code="course.title.createcourse.registrate"/></button>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty cretermCourseList}">
			<tr>
<%-- 				<td colspan="8"><spring:message code="course.message.createcourse.nodata"/></td> --%>
				<td class="text-right">${status.count}</td>
				<td class="text-center">기업명1</td>
				<td class="text-center">과정명1</td>
				<td class="text-center">결제금액1</td>
				<td class="text-center">모집인원1</td>
				<td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsInfoMngForm()"><spring:message code="course.title.createcourse.info"/></button>
				</a>
				<td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsOperationMngForm()"><spring:message code="course.title.createcourse.operate"/></button>
				</td>
				<td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsStudentMngForm()"><spring:message code="course.title.createcourse.registrate"/></button>
				</td>
			</tr>
			</c:if>
		</tbody>
	</table>		
	</form>


