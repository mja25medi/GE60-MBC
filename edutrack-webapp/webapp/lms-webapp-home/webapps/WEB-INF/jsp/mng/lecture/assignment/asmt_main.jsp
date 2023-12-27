<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="subjectList" value="${subjectList}"/>
	<br/>
	<form name="Search" id="Search" action="javascript:listAsmt()">
	<div class="text-right">
		<a href="javascript:writeAsmt();" class="btn btn-primary btn-sm"><spring:message code="button.write.assignment"/></a>
	</div>
	<div id="assignmentList" style="margin-top:5px;">
		<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:50px;"/>
				<col style="width:72px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col" ><spring:message code="common.title.no"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.name"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.duration"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.type"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.regyn"/></th>
					<th scope="col" ><spring:message code="common.title.edit"/></th>
					<th scope="col" ><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="7"><spring:message code="lecture.message.assignment.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${vo.crsCreCd}';
		listAsmt();
	});

	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 과제 목록 조회
	 */
	function listAsmt() {
		var crsCreCd = ItemDTO.crsCreCd;
		$("#assignmentList")
			.load(
		 		cUrl("/mng/lecture/assignment/listAsmt"),		// url
				{ 
				  "crsCreCd" : crsCreCd
				},			// params
				function() {
					parentResize();
				}
			);
	}


	/**
	 * 과제 정보 등록 폼
	 */
	function writeAsmt() {
		var addContent  = "<iframe id='addAssignmentFrame' name='addAssignmentFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/assignment/addAsmtPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.write"/>");
		parent.modalBox.show();
	}

	/**
	 * 과제 정보 등록 폼
	 */
	function editAsmt(asmtSn) {
		var addContent  = "<iframe id='addAssignmentFrame' name='addAssignmentFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/assignment/editAsmtPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"&amp;asmtSn="+asmtSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.edit"/>");
		parent.modalBox.show();
	}

</script>
