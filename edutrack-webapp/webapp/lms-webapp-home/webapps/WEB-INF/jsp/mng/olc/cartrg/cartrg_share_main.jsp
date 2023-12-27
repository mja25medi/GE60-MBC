<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:mng_head>

<mhtml:mng_body>
			<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
			<div class="row" id="content">
				<div id="listLayer" class="rowLayer">
					<form name="Search" onsubmit="return false" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="input-group">
							<select name="searchKey" id="searchKey" class="form-control input-sm">
								<option value="OLC"><spring:message code="olc.title.cartridge.name"/></option>
								<option value="NAME"><spring:message code="common.title.reguser"/></option>
							</select>
						</div>
						<div class="input-group" style="width:180px;">
							<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>"/>
							<span class="input-group-addon" onclick="listPageing('1')" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 text-right">
						<input type="checkbox" name="chkRequest" id="chkRequest" onchange="listPageing('1')"><spring:message code="olc.title.request.display"/>
						<select name="listScale" id="listScale" onchange="listPageing('1')" class="form-control input-sm">
							<option value="10">10</option>
							<option value="20" selected="selected">20</option>
							<option value="40">40</option>
							<option value="60">60</option>
							<option value="80">80</option>
							<option value="100">100</option>
							<option value="200">200</option>
						</select>
					</div>
					<input type="submit" value="submit" style="display:none" />
					</form>
					<div class="col-md-12">
						<div id="cartrgList" style="margin-top:5px;">
						<table summary='<spring:message code="olc.title.main.manage"/>' class="table table-striped table-bordered">
							<colgroup>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:120px"/>
								<col style="width:90px"/>
								<col style="width:90px"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="olc.title.cartridge.name"/></th>
									<th scope="col"><spring:message code="olc.title.cartridge.contents.cnt"/></th>
									<th scope="col"><spring:message code="common.title.reguser"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.date.reg"/></th>
									<th scope="col"><spring:message code="olc.title.contents.share"/></th>
									<th scope="col"><spring:message code="olc.title.knowledge.share"/></th>
									<th scope="col"><spring:message code="common.title.manage"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="8">&nbsp;</td>
								</tr>
							</tbody>
						</table>
						</div>
					</div>
				</div>
				<div id="workLayer" class="rowLayer">
					<div class="col-md-12" id="workBody">
					</div>
				</div>
			</div>
<script type="text/javascript">
	var olcCtgrTree = null;
	var modalBox = null;
	var ItemDTO = { "nodeId" : "", "subCnt" : 0, "olcCnt" : 0, "nodeType" : "root", "sortKey" : ""};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		listPageing(1);

/* 		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				//listCourse(1);
			}
		} */
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 카트리지 목록을 조회한다.
	 */
	function listPageing(page) {
		var listScale	= $("#listScale option:selected").val();
		var searchKey	= $("#searchKey").val();
		var searchValue	= $("#searchValue").val();
		var chkRequest	= '';
		if($("#chkRequest").is(":checked")) chkRequest = "01";

		var url = cUrl("/mng/olc/cartrg/shareListCartrg");
		$("#cartrgList").load(url,
			{
			"curPage":page,
			"olcCartrgVO.ctgrCd":ItemDTO.nodeId,
			"listScale":listScale,
			"olcCartrgVO.sortKey":ItemDTO.sortKey,
			"olcCartrgVO.searchKey":searchKey,
			"olcCartrgVO.searchValue":searchValue,
			"olcCartrgVO.cntsShareCd":chkRequest,
			"olcCartrgVO.knowShareCd":chkRequest
			});
	}

	function setSortKey(sortKey){
		ItemDTO.sortKey = sortKey;
		listPageing(1);
	}

	/**
	 * Contents 관리 폼
	 */
	function cntsManage(cartrgCd) {
		var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/olc/cartrg/cntsPop"/>"
			+ "?olcCntsVO.cartrgCd="+cartrgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 700);
		modalBox.setTitle("<spring:message code="olc.title.contents.manage"/>");
		modalBox.show();
	}

	/**
	 * Contents 미리보기
	 */
	function preview(userNo, cartrgCd, cartrgNm) {
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/previewMain"/>"
			+ "?olcCntsVO.userNo="+userNo+"&amp;olcCntsVO.cartrgCd="+cartrgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 650);
		modalBox.setTitle(cartrgNm);
		modalBox.show();
	}

	/**
	 * OLC 수정 폼
	 */
	function olcEdit(cartrgCd) {
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/editCartrgShareForm"/>"
			+ "?olcCartrgVO.cartrgCd="+cartrgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("<spring:message code="olc.title.cartridge.edit"/>");
		modalBox.show();
	}
	/**
	 * 사용자 정보 보기
	 */
	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/user/userInfo/viewUserPop"
			+ "?usrUserInfoVO.userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}
</script>
</mhtml:mng_body>

</mhtml:mng_html>
