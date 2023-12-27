<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
		<section class="content">
			<div class="row" id="content">
				<div class="box">
					<div class="box-body">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-6 col-sm-6">
							<div class="input-group">
								<select name="searchKey" id="searchKey" class="form-control input-sm">
									<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
									<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
								</select>
							</div>
							<div class="input-group" style="width:180px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>" />
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer" >
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right">
							<select name="listScale" id="listScale" onchange="listPageing(1)" class="form-control input-sm">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
						</div>
						<div class="col-md-12" style="margin-top:5px;">
							<div id="userList">
								<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:80px"/>
										<col style="width:100px"/>
										<col style="width:100px"/>
										<col style="width:120px"/>
										<col style="width:120px"/>
										<col style="width:auto"/>
										<col style="width:120px"/>
										<col style="width:80px"/>
										<col style="width:50px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="common.title.no"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.userno"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.date.reg"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.date.confirm"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.date.out"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.stats"/></th>
											<th scope="col"><spring:message code="common.title.edit"/></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="9"><spring:message code="user.message.userinfo.nodata"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>		
						</form>
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
		ItemDTO.sortKey = "USER_ID_ASC";
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

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listPageing(1);
	}

	/**
	 *  페이징처리
	 */
	function listPageing(page) {
		var searchKey = $("#searchKey > option:selected").val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale > option:selected").val();

		$('#userList')
			.load(
				cUrl("/mng/user/userInfo/listRemoveUser"),
				{ 
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "sortKey":ItemDTO.sortKey,
				  "curPage":page,
				  "listScale":listScale
				}
			);
	}

	/**
	 * 사용자 수정 폼
	 */
	function userEdit(userNo) {
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/userInfo/editUserPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 690);
		modalBox.setTitle("<spring:message code="user.title.userinfo.edit"/>");
		modalBox.show();
	}

	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/userInfo/viewUserPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}

</script>
