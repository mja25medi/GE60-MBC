<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<input type="hidden" id="tplType" name="tplType" value="EMAIL">
						<div class="col-md-12 col-sm-12">
							<ul class="nav nav-tabs" id="tabMenu">
								<li id="li_EMAIL" class="active"><a href="javascript:chgTplType('EMAIL');">EMAIL</a></li>
						  		<li id="li_SMS"><a href="javascript:chgTplType('SMS');">SMS</a></li>
							</ul>
						</div>
						<div class="col-md-12 col-sm-12 text-right" style="margin-top:5px;">
							<div class="btn-group">
								<button class="btn btn-primary btn-sm" onclick="emailTplWrite()"><i class="fa fa-edit"></i> <spring:message code="button.write"/></button>
							</div>
						</div>
						</form>
						<div class="col-md-12" style="margin-top:5px;">						
							<div id="dataList" style="width:100%;">
								<table summary="data" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:100px"/>
										<col style="width:auto"/>
										<col style="width:150px"/>
										<col style="width:100px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope='col'><spring:message code="common.title.no"/></th>
											<th scope='col'><spring:message code="org.title.emailtpl.name"/></th>
											<th scope='col'><spring:message code="org.title.emailtpl.code"/></th>
											<th scope='col'><spring:message code="common.title.useyn"/></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="4"><spring:message code="common.message.nodata"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="overlay" id="loadingBar">
							<i class="fa fa-spinner fa-spin"></i>
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
	
		listEmailTpl(1);
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	//탭 변경
	function chgTplType(type){
		if($("#tplType").val() != type){
			$("#tplType").val(type);
			$(".active").removeClass("active");
			$("#li_"+type).addClass("active");		
			listEmailTpl(1);
		}
	}
	
	/**
	 * 목록 조회
	 */
	function listEmailTpl(page) {
		$("#loadingBar").show();
	    $("#dataList").load(
	    	cUrl("/mng/org/emailTpl/list"),
	    	{ "tplType":$("#tplType").val()},
	    	function () {$("#loadingBar").hide();}
	    );
	}
	
	/**
	 * 페이지 등록 폼
	 */
	function emailTplWrite() {
		var addContent  = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/org/emailTpl/addPop"/>"
			+ "?tplType="+$("#tplType").val()+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 700);
		modalBox.setTitle("<spring:message code="org.title.emailtpl.write"/>");
		modalBox.show();
	}

	/**
	 * 페이지 수정 폼
	 */
	function emailTplEdit(orgCd, tplCd) {
		var addContent = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/org/emailTpl/editPop"/>"
			+ "?orgCd="+orgCd+"&amp;tplCd="+tplCd+"&amp;tplType="+$("#tplType").val()+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 700);
		modalBox.setTitle("<spring:message code="org.title.emailtpl.edit"/>");
		modalBox.show();
	}
	
</script>


