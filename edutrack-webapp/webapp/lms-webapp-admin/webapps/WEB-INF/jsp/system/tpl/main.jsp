<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

			<div class="row" id="content">
				<div class="col-md-12">
					<div class="text-right">
						<a class="btn btn-primary btn-sm" href="javascript:tplWrite()"><spring:message code="button.write" /></a>
						<a class="btn btn-info btn-sm" href="javascript:tplSort()"><spring:message code="button.sort" /></a>
					</div>
				</div>
				<div class="col-md-12" style="margin-top:5px;padding-top:5px;border-top:1px solid gray;" id="templateList">
					<div class="well">
						<spring:message code="common.message.nodata"/>
					</div>
				</div>
			</div>

<script type="text/javascript">
	var modalBox = null;
	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		// 검색 text컨트롤 Enter Binding
		$("#fileRepositoryName").bind("keydown", function(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listPageing(1);
			}
		});
		listPageing(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function listPageing(page) {
		var url = cUrl("/adm/system/tpl/list.do")
		$("#templateList").load(url, {"pageIndex" : page});
	}

	function tplWrite() {
		var addContent  = "<iframe id='addTemplateFrame' name='addTemplateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/system/tpl/addFormPop"/>'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 600);
		modalBox.setTitle("<spring:message code="system.title.template.write"/>");
		modalBox.show();
	}

	function tplEdit(tplCd) {
		var addContent  = "<iframe id='addTemplateFrame' name='addTemplateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/system/tpl/editFormPop"/>"
			+ "?tplCd="+tplCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 600);
		modalBox.setTitle("<spring:message code="system.title.template.edit"/>");
		modalBox.show();
	}

	/**
	 * 코드 순서 변경 폼
	 */
	function tplSort() {
		var addContent  = "<iframe id='addTemplateFrame' name='addTemplateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/tpl/sortFormPop"/>'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(310, 310);
		modalBox.setTitle("<spring:message code="system.title.template.edit"/>");
		modalBox.show();
	}

	/**
	 * 이미지 미리보기
	 */
	function preview(fileSn) {
		var addContent  = "<div style='width:100%;height:100%;overflow-y:auto;'><img src='/app/file/view2/"+fileSn+"' style='width:1050px;'></div>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 700);
		modalBox.setTitle("Preview Image");
		modalBox.show();
	}

</script>


