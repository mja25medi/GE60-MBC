<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br/>
	<form name="Search" id="Search" action="javascript:listResearch()">
	<div class="text-right">
		<a href="javascript:writeResh()" class="btn btn-primary btn-sm"><spring:message code="button.write.research"/> </a>
	</div>
	<input type="hidden" name="reshSn_chk" Id="reshSn_chk" value="" />
	<div id="reshDiv" style="margin-top:5px;">
		<table summary="<spring:message code="course.title.resh.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:150px"/>
				<col style="width:150px"/>
				<col style="width:80px"/>
				<col style="width:60px"/>
				<col style="width:60px"/>
				<col style="width:50px"/>
				<col style="width:98px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col" ><spring:message code="course.title.resh.title"/></th>
					<th scope="col"><spring:message code="common.title.startdate"/></th>
					<th scope="col"><spring:message code="common.title.enddate"/></th>
					<th scope="col"><spring:message code="lecture.title.research.regyn"/></th>
					<th scope="col"><spring:message code="course.title.resh.qstncnt"/></th>
					<th scope="col"><spring:message code="course.title.resh.ansrcnt"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
					<th scope="col"><spring:message code="course.title.resh.result"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="9"><spring:message code="course.message.resh.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${vo.crsCreCd}';
		listResearch();
	});

	/**
	 * 설문 목록 조회
	 */
	function listResearch() {
		var crsCreCd = ItemDTO.crsCreCd;
		$("#reshDiv").load(
			cUrl("/mng/course/creCrs/resh/list"),
			{
				"crsCreCd" : crsCreCd
			},
			parentResize
		);
	}

	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 설문 정보 등록 폼
	 */
	function writeResh() {
		var addContent  = "<iframe id='addResearchFrame' name='addResearchFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/creCrs/resh/addPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(750, 350);
		parent.modalBox.setTitle("<spring:message code="course.title.resh.write"/>");
		parent.modalBox.show();
	}

	/**
	 * 설문 정보 수정 폼
	 */
	function editResh(reshSn) {
		var addContent  = "<iframe id='addResearchFrame' name='addResearchFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/creCrs/resh/editPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"&amp;reshSn="+reshSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(750, 350);
		parent.modalBox.setTitle("<spring:message code="course.title.resh.edit"/>");
		parent.modalBox.show();
	}

	/**
	 * 설문 결과 폼
	 */
	function resultResh(reshSn) {
		var addContent  = "<iframe id='addResearchFrame' name='addResearchFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/creCrs/resh/rsltPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"&amp;reshSn="+reshSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 650);
		parent.modalBox.setTitle("<spring:message code="course.title.resh.result"/>");
		parent.modalBox.show();
	}

	function chk_reshSn(pop_reshSn){
		var reshSn_chk = document.getElementById("reshSn_chk").value;
		var reshSn_ar = reshSn_chk.split("|");
		for(var i=0; i<reshSn_ar.length; i++){
			if(pop_reshSn == reshSn_ar[i]){
				alert("<spring:message code="course.message.resh.alert.duplicate"/>");
				return false;
			}
		}
		return true;
	}
</script>
