<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="etcEventVO" value="${vo}"/>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<form onsubmit="return false" id="courseCategoryForm">
					<div class="col-md-12">
						<div class="text-right">
							<a class="btn btn-primary btn-sm" href="javascript:eventWrite()"><spring:message code="button.write" /></a>
							<a class="btn btn-info btn-sm" href="javascript:eventSort()"><spring:message code="button.sort" /></a>
						</div>
					</div>
					<div class="col-md-12" style="margin-top:5px;padding-top:5px;border-top:1px solid gray;" id="eventList">
						<div class="well">
							<spring:message code="common.message.nodata"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>			
<script type="text/javascript">

	var modalBox = null;

	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		eventList(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 *  베너 목록
	 */
	function eventList(page) {
		$('#eventList').load(
				cUrl("/mng/etc/event/list"),
				{ "curPage" : page },
				function () {}
			);
	}

	/**
	 * 베너 등록 폼
	 */
	function eventWrite() {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/etc/event/addPop'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("<spring:message code="etc.title.event.write"/>");
		modalBox.show();
	}

	/**
	 * 베너 수정 폼
	 */
	function eventEdit(eventSn) {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/etc/event/editPop"
			+ "?eventSn="+eventSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("<spring:message code="etc.title.event.edit"/>");
		modalBox.show();
	}

	/**
	 * 베너 순서 변경 폼
	 */
	function eventSort() {
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='/mng/etc/event/sortPop'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(300, 304);
		modalBox.setTitle("<spring:message code="etc.title.event.sort"/>");
		modalBox.show();
	}

</script>
