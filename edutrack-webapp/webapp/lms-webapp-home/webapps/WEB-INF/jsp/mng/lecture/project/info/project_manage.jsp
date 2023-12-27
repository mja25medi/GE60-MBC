<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="projectVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module simpletab="y" uploadify="y"/>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/common_iframe.js"/>
	<meditag:js src="/js/popupbox.js"/>
</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<br>
	<form id="projectForm" name="projectForm" onsubmit="return false" action="/mng/lecture/project">
 	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
 	<input type="hidden" name="prjtSn" value="${vo.prjtSn }" />
 	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns }" />

	<table summary="<spring:message code="lecture.title.project.manage"/>" style="width:100%" class="table_dtl" align="center">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr height="25">
			<th scope="row" class="top"><spring:message code="lecture.title.project.name"/></th>
			<td class="top" colspan="3">${projectVO.prjtTitle}</td>
		</tr>
		<tr height="25">
			<th scope="row"><spring:message code="lecture.title.project.duration"/></th>
			<td colspan="3">${projectVO.prjtStartDttm} ~ ${projectVO.prjtEndDttm}</td>
		</tr>
		<tr height="25">
			<th scope="row"><spring:message code="lecture.title.project.scoreopenyn"/></th>
			<td colspan="3">${projectVO.scoreOpenYnNm}</td>
		</tr>
		<c:if test="${projectVO.scoreOpenYn eq 'Y'}" >
		<tr height="25">
			<th scope="row"><spring:message code="lecture.title.project.scoreopendate"/></th>
			<td colspan="3">${projectVO.scoreCfrmDttm}</td>
		</tr>
		</c:if>
	</table>
	<div style="padding:10px 5px 10px 0px;float:right;">
		<meditag:button value="button.list" title="button.list" func="projectList()" />
	</div>
	<div class="tabbox" style="width:100%;float:left">
		<ul id="tab" class="tab">
			<li id="tab0"><span onMouseDown="onclickTab('0')"><spring:message code="lecture.title.project.team.manage"/></span></li>
			<li id="tab1"><span onMouseDown="onclickTab('1')"><spring:message code="lecture.title.project.asmt.manage"/></span></li>
			<li id="tab2"><span onMouseDown="onclickTab('2')"><spring:message code="lecture.title.project.bbsinfo.manage"/></span></li>
			<li id="tab3"><span onMouseDown="onclickTab('3')"><spring:message code="lecture.title.project.bbsatcl.manage"/></span></li>
			<li id="tab4"><span onMouseDown="onclickTab('4')"><spring:message code="lecture.title.project.rate.manage"/></span></li>
		</ul>
	</div>
	<iframe name="subWorkFrame2" id="subWorkFrame2" frameborder="0" src="about:blank" scrolling="no" title="Sub Work Frame" style="width:100%; height: 500px;"></iframe>
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {

		ItemDTO.crsCreCd = '${projectVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectVO.prjtSn}';
		//-- 텝 초기 페이지 호출
		onclickTab('0');
		parentResize();
	});

	// tab화면
	function onclickTab(tab) {
		var url = {};
 		url['0'] = "/mng/lecture/prjTeam/main?crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjtSn="+ItemDTO.prjtSn;
 		url['1'] = "/LecturePrjAssignmentManage.do?cmd=main${AMPERSAND}crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjtSn="+ItemDTO.prjtSn;
 		url['2'] = "/LecturePrjBbsManage.do?cmd=main${AMPERSAND}crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjtSn="+ItemDTO.prjtSn;
 		url['3'] = "/LecturePrjBbsAtclManage.do?cmd=main${AMPERSAND}crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjtSn="+ItemDTO.prjtSn;
 		url['4'] = "/LectureProjectResultManage.do?cmd=main${AMPERSAND}crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjtSn="+ItemDTO.prjtSn;

 		document.subWorkFrame2.location.href = cUrl(url[tab]);
		selectTab(tab);

	}

	/**
	 * 목록으로 이동
	 */
	function projectList() {
		document.location.href = "/mng/lecture/project/main?crsCreCd="+ItemDTO.crsCreCd;
	}


	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
		parent.resizeForm();
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>