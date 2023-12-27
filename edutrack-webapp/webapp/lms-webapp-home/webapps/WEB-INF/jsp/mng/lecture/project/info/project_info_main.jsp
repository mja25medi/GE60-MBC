<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="projectVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="projectForm" name="projectForm" onsubmit="return false" action="/mng/lecture/project">
 	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
 	<input type="hidden" name="prjtSn" value="${vo.prjtSn }" />
	<div class="dvcontents">
		<div class="subhead">
			<div style="float:right">
				<meditag:button value="button.write.project" title="button.write.project" func="prjtAdd('${projectVO.crsCreCd}')" />
			</div>
		</div>
		<div id="projectList">
			<table summary="<spring:message code="lecture.title.project.manage"/>" style="width:100%" class="table_list">
				<colgroup>
					<col style="width:50px;"/>
					<col style="width:auto;"/>
					<col style="width:auto;"/>
					<col style="width:5%"/>
					<col style="width:10%"/>
					<col style="width:50px;"/>
					<col style="width:50px;"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="lecture.title.project.name"/></th>
						<th scope="col"><spring:message code="lecture.title.project.duration"/></th>
						<th scope="col"><spring:message code="lecture.title.project.teamcnt"/></th>
						<th scope="col"><spring:message code="lecture.title.project.scoreopenyn"/></th>
						<th scope="col"><spring:message code="common.title.edit"/></th>
						<th scope="col"><spring:message code="common.title.manage"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><spring:message code="lecture.message.project.nodata"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectVO.crsCreCd}';

		//프로젝트 목록 조회
		listProject();
	});

	//프로젝트 목록 조회
	function listProject(){
		displayWorkProgress();
		$("#projectList")
			.load(
				cUrl("/mng/lecture/project/list"),		//url
				{
				  	"crsCreCd" : ItemDTO.crsCreCd,
				},											//parameter
				function() {
					parentResize();
					closeWorkProgress();
				}
		);

	}



	//프로젝트 등록
	function prjtAdd(crsCreCd) {
	 	var addContent  = "<iframe id='addProjectFrame' name='addProjectFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/project/addForm"/>"
			+ "?crsCreCd="+crsCreCd+"'/>";

			parent.projectPopBox.clear();
			parent.projectPopBox.addContents(addContent);
			parent.projectPopBox.resize(800, 400);
			parent.projectPopBox.setTitle("<spring:message code="lecture.title.project.write"/>");
			parent.projectPopBox.show();
	}

	//프로젝트 등록
	function prjtEdit(crsCreCd, prjtSn) {
	 	var addContent  = "<iframe id='addProjectFrame' name='addProjectFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/project/editForm"/>"
			+ "?crsCreCd="+crsCreCd+"&amp;prjtSn="+prjtSn+"'/>";

			parent.projectPopBox.clear();
			parent.projectPopBox.addContents(addContent);
			parent.projectPopBox.resize(800, 400);
			parent.projectPopBox.setTitle("<spring:message code="lecture.title.project.edit"/>");
			parent.projectPopBox.show();
	}

	function parentResize(){
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>