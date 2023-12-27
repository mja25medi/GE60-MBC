<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
						<div class="col-lg-12">
							<div class="col-md-12 text-right">
								<button class="btn btn-primary btn-sm" onclick="pageWrite()"><spring:message code="button.write"/></button>
							</div>
						</div>
						<div class="col-lg-12">
							<div style="margin-top:5px; margin-bottom:20px;">
								<div id="pageList" style="width:100%">
									<table summary="<spring:message code="system.title.page.manage"/>" class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:80px"/>
											<col style="width:110px"/>
											<col style="width:auto"/>
											<col style="width:80px"/>
											<col style="width:50px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope='col'><spring:message code="common.title.no"/></th>
												<th scope='col'><spring:message code="system.title.page.code"/></th>
												<th scope='col'><spring:message code="system.title.page.name"/></th>
												<th scope='col'><spring:message code="common.title.useyn"/></th>
												<th scope='col'><spring:message code="common.title.edit"/></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="5"><spring:message code="common.message.nodata"/></td>
											</tr>
										</tbody>
									</table>
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
var summernote;

/** 페이지 초기화 */
$(document).ready(function() {
	MessageVO.titleAdd = "<spring:message code="system.title.page.add"/>";
	MessageVO.titleEdit = "<spring:message code="system.title.page.edit"/>";
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});

	listPage(1);
});

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

/**
 * 시스템 페이지 목록 조회
 */
function listPage(page) {
	$('#pageList')
		.load( cUrl("/mng/org/page/list"), {"sortKey": ItemVO.sortKey, "curPage" : page  } );
}

function setSortKey(sortKey){
	ItemVO.sortKey = sortKey;
	listPage();
}

/**
 * 페이지 등록 폼
 */
function pageWrite() {
	var addContent  = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='"+cUrl("/mng/org/page/addPop")+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1100, 600);
	modalBox.setTitle(MessageVO.titleAdd);
	modalBox.show();
}

/**
 * 페이지 수정 폼
 */
function pageEdit(orgCd, pageCd) {
	var addContent = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='"+cUrl("/mng/org/page/editPop")
		+ "?orgCd="+orgCd+"&pageCd="+pageCd+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1100, 600);
	modalBox.setTitle(MessageVO.titleEdit);
	modalBox.show();
}

</script>

