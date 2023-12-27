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
				<div class="col-md-6">
					<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
					<div style="width:50%;float:left;">
						<div class="input-group" style="width:160px;">
							<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
							<span class="input-group-addon" onclick="listCtgr(1)" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div style="width:50%;float:right;" class="text-right">
						<a href="javascript:writeCtgrForm()" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> <spring:message code="button.write.category"/></a>
						<select name="listScale" id="listScale" onchange="listCtgr(1);" class="form-control input-sm" style="margin-left:5px;float:right;max-width:80px;">
							<option value="10">10</option>
							<option value="20" selected="selected">20</option>
							<option value="40">40</option>
							<option value="60">60</option>
							<option value="80">80</option>
							<option value="100">100</option>
							<option value="200">200</option>
						</select>
					</div>
					<div class="clearfix"></div>
					</form>
					<div style="width:100%;margin-top:5px;">
						<div id="listArea" style="width:100%;">
		
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-12" style="margin-top:35px" id="workArea">
					
						</div>
					</div>
				</div>
			</div>
</mhtml:mng_body>
<script type="text/javascript">
	var modalBox = null;
	var ItemVO = new Object();
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
				listCtgr();
			}
		}
		listCtgr();
		writeCtgrForm();
	});

	/**
	 *  분류 목록 조회
	 */
	function listCtgr() {
		$('#listArea').load(cUrl("/PrdtCodeManage.do"),{"cmd" : "listCtgr"});
	}

	/**
	 * 코드 분류 등록 폼
	 */
	function writeCtgrForm() {
		$("#workArea").load(cUrl("/PrdtCodeManage.do"),{"cmd":"addCtgrForm"});
	}

	/**
	 * 코드 분류 수정 폼
	 */
	function editCtgrForm(codeCtgrCd) {
		$("#workArea").load(cUrl("/PrdtCodeManage.do"),{"cmd":"editCtgrForm", "codeCtgrCd": codeCtgrCd });
	}
	
	function sortCtgr(sortString) {
		$.getJSON( cUrl( "/PrdtCodeManage.do"),
			{ "cmd":"sortCtgr", "codeCtgrCd": sortString },			// params
			function(data) {
				alert(data.message);
				if(data.result >= 0) {
					//-- 정상 처리
	 	  		} else {
	 	  			//-- 비정상 처리
	 	  			listCtgr();
	 	  		}
			}
		);
	}

</script>
</mhtml:mng_html>