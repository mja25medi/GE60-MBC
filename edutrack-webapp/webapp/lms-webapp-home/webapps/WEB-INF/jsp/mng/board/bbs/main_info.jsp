<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
				<section class="content">
					<div class="row" id="content">
						<div class="box">
							<div class="box-body">
								<div class="col-md-12">
									<div class="row" style="padding-left:15px; padding-right:15px;">
										<form name="Input" onsubmit="return false">
										<div style="float:right">
											<button class="btn btn-primary btn-sm" onclick="addInfo()"><spring:message code="button.write.bbs"/></button>
										</div>
										</form>
									</div>
								</div>
								<div class="col-md-12"  style="margin-top:5px;">
									<div id="bbsList">
										<table summary="<spring:message code="board.title.bbs.info.list"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:auto"/>
												<col style="width:auto"/>
												<col style="width:120px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:90px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
											</colgroup>
											<thead>
											<tr>
												<th scope="col"><spring:message code="board.title.bbs.info.bbscd"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.bbsnm"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.listtype"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.reply"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.comment"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.atache"/></th>
												<th scope="col"><spring:message code="common.title.useyn"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.atcl.cnt"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.atcl.new"/></th>
												<th scope="col"><spring:message code="common.title.edit"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.head"/></th>
											</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
<script type="text/javascript">
	var modalBox = null;
	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modalBox"
		});
	
		listInfo(1);
	});
	
	/**
	 * 게시판 정보 목록 조회
	 */
	function listInfo(page) {
	    $("#bbsList").load(cUrl("/mng/brd/bbs/listInfo"),{ "pageIndex" : page });
	}
	
	/**
	 * 게시판 정보 등록 폼
	 */
	function addInfo() {
		var url = generateUrl("/mng/brd/bbs/addFormInfoPop");
		var addContent  = "<iframe id='addBbsInfoFrame' name='addBbsInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("<spring:message code="board.title.bbs.info.write"/>");
		modalBox.show();
	}
	
	/**
	 * 게시판 정보 수정 폼
	 */
	function editInfo(orgCd, bbsCd) {
		var url = generateUrl("/mng/brd/bbs/editFormInfoPop",{"bbsCd":bbsCd});
		var addContent = "<iframe id='addBbsInfoFrame' name='addBbsInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("<spring:message code="board.title.bbs.info.edit"/>");
		modalBox.show();
	}
	
	/**
	 * 게시판 머릿말 관리
	 */
	function mngHead(orgCd, bbsCd) {
		var url = generateUrl("/mng/brd/bbs/listHeadPop",{"bbsCd":bbsCd});
		var addContent = "<iframe id='addBbsInfoFrame' name='addBbsInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 400);
		modalBox.setTitle("<spring:message code="board.title.bbs.head.manage"/>");
		modalBox.show();
	}
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
</script>

