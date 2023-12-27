<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<form  onsubmit="return false" name="bkgImgForm" id="bkgImgForm">
					<input type="hidden" name="cmd" />
					<div class="col-md-12">
						<div style="float:right">
							<select name="listScale" id="listScale" onchange="bkgImgList(1);" class="form-control input-sm" style="width: 65px;float: right;padding-left: 5px;">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
						</div>
						<div style="float:right">
							<a class="btn btn-primary btn-sm" href="javascript:bkgImgWrite()"><spring:message code="button.write" /></a>
							<a class="btn btn-info btn-sm" href="javascript:bkgImgSort()"><spring:message code="button.sort" /></a>
						</div>
					</div>
					<div class="col-md-12" style="margin-top:5px;padding-top:5px;border-top:1px solid gray;" id="bkgImgList">
						<div class="well">
							<spring:message code="common.message.nodata"/>
						</div>
					</div>
					</form>
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

		bkgImgList(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 *  베너 목록
	 */
	function bkgImgList(page) {
		var listScale = $("#listScale option:selected").val();
		$('#bkgImgList').load(
				cUrl("/mng/etc/bkgImg/list"),
				{ 
					"curPage" : page ,
					"listScale":listScale
				},
				function () {}
			);
	}

	/**
	 * 베너 등록 폼
	 */
	function bkgImgWrite() {
		var addContent  = "<iframe id='addBkgImgFrame' name='addBkgImgFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/etc/bkgImg/addPop'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 450);
		modalBox.setTitle("<spring:message code="etc.title.bkgimg.write"/>");
		modalBox.show();
	}

	/**
	 * 베너 수정 폼
	 */
	function bkgImgEdit(bkgrImgSn) {
		var addContent  = "<iframe id='addBkgImgFrame' name='addBkgImgFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/etc/bkgImg/editPop"
			+ "?bkgrImgSn="+bkgrImgSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 450);
		modalBox.setTitle("<spring:message code="etc.title.bkgimg.edit"/>");
		modalBox.show();
	}

	/**
	 * 베너 순서 변경 폼
	 */
	function bkgImgSort() {
		var addContent = "<iframe id='addBkgImgFrame' name='addBkgImgFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='/mng/etc/bkgImg/sortPop'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(300, 304);
		modalBox.setTitle("<spring:message code="etc.title.bkgimg.sort"/>");
		modalBox.show();
	}

	/**
	 * 이미지 미리보기
	 */
	function preview(fileSn) {
		if(fileSn != "0"){
			var addContent  = "<div style='width:100%;height:100%;overflow-y:auto;'><img src='/app/file/view2/"+fileSn+"' style='width:1050px;'></div>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(1100, 700);
			modalBox.setTitle("Preview Image");
			modalBox.show();
		}
	}
</script>
