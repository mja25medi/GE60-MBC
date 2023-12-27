<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="rowLayer" id="listLayer">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-6 col-sm-6">
							<div class="form-group">
								<select name="use" id="use" class="form-control input-sm" onChange="listPageing(1);">
									<option value=""><spring:message code="course.open.title.category.useyn"/></option>
									<option value="Y"><spring:message code="common.title.status.useyn_y"/></option>
									<option value="N"><spring:message code="common.title.status.useyn_n"/></option>
								</select>
								<select name="searchKey" id="searchKey" class="form-control input-sm">
									<option value="">검색</option>
									<option value="deptNm">법인명</option>
									<option value="deptMngNm">담당자</option>
									<option value="deptCd">기업코드</option>
								</select>							
								<div class="input-group" style="width:180px;">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>" />
									<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right">
							<div class="form-group">
								<a href="javascript:viewDeptExcelWritePop()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
								<a href="javascript:userDeptWrite()" class="btn btn-primary btn-sm">기업등록</a>
								<input type="hidden" name="listScale" id="listScale" value="20"  />
	<!-- 							<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm"> -->
	<!-- 								<option value="10">10</option> -->
	<!-- 								<option value="20" selected="selected">20</option> -->
	<!-- 								<option value="40">40</option> -->
	<!-- 								<option value="60">60</option> -->
	<!-- 								<option value="80">80</option> -->
	<!-- 								<option value="100">100</option> -->
	<!-- 								<option value="200">200</option> -->
	<!-- 							</select> -->
							</div>
						</div>
						<div class="col-md-12" style="margin-top:5px;">
							<div id="userDeptList">
							</div>
						</div>
						</form>
					</div>
					<div class="rowLayer" id="workLayer">
						<div class="col-md-12" id="workBody">
						</div>
					</div>					
					
				</div>
				<div style="margin-top:15px;"></div>
			</div>
		</div>
	</div>
</section>				
<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	var ItemDTO = new Object();

	/**
	 * 초기 화면 구성
	 */
	$(document).ready(function() {

		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listPageing(1);
			}
		}
		listPageing(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 *  페이징처리
	 */
	function listPageing(page) {
		if(page > 0)
			ItemDTO.curPage = page;
		var searchKey 	= $("#searchKey").val();
		var searchValue = $('#searchValue').val();
		var listScale 	= $("#listScale").val();
		var useYn		= $('#use').val();

		$('#userDeptList')
			.load(
				cUrl("/mng/user/deptInfo/listDeptInfo"),
				{ 
				  "curPage":ItemDTO.curPage,
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "useYn":useYn,
				  "listScale":listScale
				},
				function () {
				}
			);

	}
	
	//기업등록 폼
	function userDeptWrite(){
		var url = cUrl("/mng/user/deptInfo/addFormUserDeptPop");
		$("#workBody")
			.load(url, {
			});
		showArea('work');			
	}
	
	function showArea(str) {
		$(".rowLayer").hide();
		$("#"+str+"Layer").show();
	}
	
	function closeComWriteArea(){
		$("#workBody").html("");
		listPageing(1);
		showArea('list');
	}
	
	function userDeptEditForm(deptCd){
		var url = cUrl("/mng/user/deptInfo/editFormDeptEPop");
		$("#workBody")
			.load(url, {
				"deptCd" : deptCd 
			});
		showArea('work');			
	}
	
	function viewDeptExcelWritePop(){
		var url = generateUrl("/mng/user/deptInfo/addUserDeptExcelPop");
		var addContent  = "<iframe id='addUserDeptFrame' name='addUserDeptFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("엑셀 업로드");
		modalBox.show();
	}
</script>
