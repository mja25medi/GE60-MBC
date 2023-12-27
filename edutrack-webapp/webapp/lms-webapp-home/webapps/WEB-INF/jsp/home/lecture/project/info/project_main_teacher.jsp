<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="projectVO" value="${projectVO}"/>
<c:set var="projectListVO" value="${projectListVO}"/>
<mhtml:class_html>
<mhtml:class_head>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />
			<div class="btn_right">
				<a href="javascript:prjAdd('${projectVO.crsCreCd}')" class="btn01"><spring:message code="button.write.project"/></a>
			</div>
			<br/>
			<table class="board_a">
				<colgroup>
					<col style="width:5%"/>
					<col style="width:45%"/>
					<col style="width:20%"/>
					<col style="width:5%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
				</colgroup>
				<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.project.name"/></th>
					<th scope="col"><spring:message code="lecture.title.project.duration"/></th>
					<th scope="col"><spring:message code="lecture.title.project.teamcnt"/></th>
					<th scope="col"><spring:message code="lecture.title.project.scoreopenyn"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${projectListVO}" var="item" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td class="subject">${item.prjtTitle}</td>
						<td>${item.prjtStartDttm} ~<br/> ${item.prjtEndDttm}</td>
						<td>${item.teamCnt}</td>
						<td>${item.scoreOpenYn}</td>
						<td><a href="javascript:prjtEdit('${item.prjtSn}')" class="btn_org"><span><spring:message code="button.manage"/></span></a></td></td>
					</tr>
				</c:forEach>
				<c:if test="${empty projectListVO}">
				<tr>
					<td colspan="6" class="rnone"><spring:message code="lecture.message.project.nodata"/></td>
				</tr>
				</c:if>
				</tbody>
			</table>
	</div>

<script type="text/javascript">
	//프로젝트 등록
	function prjAdd(){
		location.href = cUrl("/lec/project/addFormPrject")+"?projectVO.crsCreCd=${projectVO.crsCreCd}";
	}
	//프로젝트 수정
	function prjtEdit(prjtSn){
		location.href = cUrl("/lec/project/editFormProject")+"?projectVO.prjtSn="+prjtSn;
	}
</script>
</mhtml:class_body>
</mhtml:class_html>