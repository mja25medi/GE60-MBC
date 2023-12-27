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
					<th scope="col">번호</th>
					<th scope="col">프로젝트 명</th>
					<th scope="col">프로젝트 기간</th>
					<th scope="col">팀수</th>
					<th scope="col">보기</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${projectListVO}" var="item" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td class="subject">${item.prjtTitle}</td>
						<td>${item.prjtStartDttm} ~<br/> ${item.prjtEndDttm}</td>
						<td>${item.teamCnt}</td>
						<td><a href="javascript:prjtRead('${item.prjtSn}','${item.prjtTeamSn}')" class="btn_org"><span>보기</span></a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty projectListVO}">
				<tr>
					<td colspan="6" class="rnone">* 등록된 프로젝트가 없습니다.</td>
				</tr>
				</c:if>
				</tbody>
			</table>
	</div>

<script type="text/javascript">
	//팀 프로젝트 수정
	function prjtRead(prjtSn, prjtTeamSn){
		var url = cUrl("/lec/project/readProject")+"?projectVO.prjtSn="+prjtSn+"${AMPERSAND}projectVO.prjtTeamSn="+prjtTeamSn;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=no, menubar=0, scrollbars=yes, resizable=no, width=970, height=600";
		var prjtWin = window.open(url, "prjtWin", winOption);
		prjtWin.focus();

	}
</script>
</mhtml:class_body>
</mhtml:class_html>