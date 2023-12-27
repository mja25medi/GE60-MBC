<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:mng_head>

<mhtml:mng_body>
			<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
			<div class="row" id="content">
				<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
				<div class="col-md-7 col-sm-7">
					<div class="input-group" style="width:140px;">
						<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
						<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="col-md-5 col-sm-5 text-right">
					<a href="javascript:writePrjt()" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> <spring:message code="prdt.button.prjt.write"/></a>
					<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm">
						<option value="10">10</option>
						<option value="20" selected="selected">20</option>
						<option value="40">40</option>
						<option value="60">60</option>
						<option value="80">80</option>
						<option value="100">100</option>
						<option value="200">200</option>
					</select>
				</div>
				</form>
				<div class="col-md-12" style="margin-top:5px;">
					<div id="listArea" style="width:100%;">

					</div>
				</div>
			</div>
</mhtml:mng_body>
<script type="text/javascript">
	var modalBox = null;
	var ItemVO = new Object();;
	/**
	 * 초기 화면 구성
	 */
	$(document).ready(function() {
		ItemVO.sortKey = "PRJT_NM_ASC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modalBox"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listPageing(1);
			}
		}
		listPageing(1);
	});

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listPageing(1);
	}

	/**
	 *  페이징처리
	 */
	function listPageing(page) {
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();

		$('#listArea')
			.load(
				cUrl("/PrdtPrjtInfoManage.do"),
				{
					"cmd" : "list",					
					"searchKey" : searchKey,
				  	"searchValue" : searchValue,
				  	"sortKey" : ItemVO.sortKey,
				  	"pageIndex" : page,
				  	"listScale" : listScale
				},
				function () {}
			);
	}

	/**
	 * 프로젝트 등록 폼
	 */
	function writePrjt() {
		var url = generateUrl("/PrdtPrjtInfoManage.do",{"cmd":"addForm"});
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="prdt.title.prjt.info.prjt.write"/>");
		modalBox.show();
	}

	/**
	 * 프로젝트 수정 폼
	 */
	function editPrjt(prjtCd) {
		var url = generateUrl("/PrdtPrjtInfoManage.do",{"cmd":"editForm", "prjtCd":prjtCd});
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="prdt.title.prjt.info.prjt.edit"/>");
		modalBox.show();
	}

	/**
	 * 프로젝트 코드 관리
	 */
	function manageCode(prjtCd) {
		var url = generateUrl("/PrdtCodeManage.do",{"cmd":"mainCode", "prjtCd":prjtCd});
		document.location.href = url;
	}
</script>
</mhtml:mng_html>