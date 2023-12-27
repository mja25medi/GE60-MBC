<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="forumVO" value="${vo}"/>
	<form name="Search" id="Search" action="javascript:listForum()">
	<div class="text-right">
		<a href="javascript:forumWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write.forum"/> </a>
	</div>
	<div id="forumList" style="margin-top:5px;">
		<table summary="<spring:message code="lecture.title.forum.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:300px;"/>
				<col style="width:70px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:72px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.title"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.duration"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.atclcnt"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.period.after.write"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.regyn"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="7"><spring:message code="lecture.message.forum.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function(){	//페이지가 완료된 후 최초 실행 ($로 시작) body태그안에 onload하지말것
		ItemDTO.crsCreCd = '${forumVO.crsCreCd}';
		listForum();
	});

	//토론 목록 조회
	function listForum(){
		var crsCreCd = ItemDTO.crsCreCd;
		$("#forumList")
			.load(
		 		cUrl("/mng/lecture/forum/listForum"),		// url
				{
				  	"crsCreCd" : crsCreCd
				},			// params
				function() {
					parentResize();
				}
			);

	}

	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	//토론 등록 popup
	function forumWrite() {
		var addContent  = "<iframe id='addFormFrame' name='addFormFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/forum/addForumPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";

		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(700, 550);
		parent.modalBox.setTitle("<spring:message code="lecture.title.forum.write"/>");
		parent.modalBox.show();
	}

	//토론 등록 popup
	function forumEdit(forumSn) {
		var addContent  = "<iframe id='addFormFrame' name='addFormFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/forum/editForumPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"&amp;forumSn="+forumSn+"'/>";

		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(700, 550);
		parent.modalBox.setTitle("<spring:message code="lecture.title.forum.edit"/>");
		parent.modalBox.show();
	}
</script>

