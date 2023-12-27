<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:mng_head>
<mhtml:mng_body>
	<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-7 col-sm-7">
							<select name="" id="" onchange="listPageing(1);" class="form-control input-sm">
								<option value="">전체</option>
								<option value="">1</option>
								<option value="">2</option>
							</select>
							<div class="input-group" style="width:140px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>						
						<div class="col-md-5 col-sm-5 text-right">
							<div class="btn-group">
								<button class="btn btn-primary btn-sm" onclick="userWrite()"><i class="fa fa-edit"></i> <spring:message code="button.write"/></button>
								<c:if test="${MSG_EMAIL eq 'Y' }">
								<button class="btn btn-default btn-sm" onclick="messageForm('EMAIL')"><i class="fa fa-envelope-o"></i> <spring:message code="button.email"/></button>
								</c:if>
							</div>
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
							<div id="dataList" style="width:100%;">
								
							</div>
						</div>
						<div class="overlay" id="loadingBar">
							<i class="fa fa-spinner fa-spin"></i>
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
			"modalid" : "modal1"
		});
	
// 		listPageing(1);
		$("#loadingBar").hide();/* 개발 시 삭제할 라인 */
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 * 게시판 정보 목록 조회
	 */
// 	function listPageing(page) {
// 		$("#loadingBar").show();
// 		var orgCd = $("#orgCd option:selected").val();
// 	    $("#dataList").load(
// 	    	cUrl("/BrdFaqInfoManage.do"),
// 	    	{"cmd":"list", "pageIndex" : page, "orgCd" : '${vo.orgCd}'},
// 	    	function () {$("#loadingBar").hide();}
// 	    );
// 	}
	
</script>
</mhtml:mng_body>
</mhtml:mng_html>

