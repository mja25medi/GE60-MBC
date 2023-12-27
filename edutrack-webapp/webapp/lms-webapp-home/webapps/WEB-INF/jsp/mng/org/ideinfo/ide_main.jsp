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
											<button class="btn btn-primary btn-sm" onclick="viewAddIdeExcelPop()">엑셀업로드</button>
											<button class="btn btn-primary btn-sm" onclick="addInfo()">IDE 등록</button>
										</div>
										</form>
									</div>
								</div>
								<div class="col-md-12"  style="margin-top:5px;">
									<div id="ideList">
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
	
		ideList(1);
	});
	
	/**
	 * IDE 정보 목록 조회
	 */
	function ideList(page) {
	    $("#ideList").load(cUrl("/mng/org/ideInfo/listIde"),{ "pageIndex" : page });
	}
	
	/**
	 * IDE 정보 등록 폼
	 */
	function addInfo() {
		var url = generateUrl("/mng/org/ideInfo/addFormIdePop");
		var addContent  = "<iframe id='addIdeInfoFrame' name='addIdeInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 200);
		modalBox.setTitle("IDE 등록");
		modalBox.show();
	}
	
	/**
	 * IDE 상태 수정
	 */
	function editUseYn(ideUrl, element) {
		$.post(cUrl("/mng/org/ideInfo/editUse"),{
			"ideUrl" : ideUrl,
			"useYn" : element
		}, function(data) {
			if(data.result = 1) {
				alert(data.message);
				ideList(1);
			}else{
				alert(data.message);
				ideList(1);
			}
		})
	}

	function delIde(ideUrl) {
		if (confirm('IDE URL을 삭제 하시겠습니까')) {
			$.post(cUrl("/mng/org/ideInfo/removeIde"), {
				"ideUrl" : ideUrl,
			}, function(data) {
				if (data.result = 1) {
					alert(data.message);
					ideList(1);
				} else{
					alert(data.message);
					ideList(1);
				}
			})
		} else {
			return;
		}
	}
	
	function viewAddIdeExcelPop() {
		var url = generateUrl("/mng/org/ideInfo/addIdeExcelPop");
		var addContent  = "<iframe id='addIdeFrame' name='addIdeFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("엑셀 업로드");
		modalBox.show();
	}
	

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
</script>

