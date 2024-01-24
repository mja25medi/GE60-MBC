<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-body">
								<div class="row">
									<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
									<div class="col-md-7 col-xs-12 mb5">
										<select name="searchAuthGrp" id="searchAuthGrp" onchange="listPageing(1);" class="form-control input-sm" style="float:left">
											<option value="MANAGER" selected="selected"><spring:message code="user.title.userinfo.manager"/></option>
											<option value="LEARN_MGR"><spring:message code="user.title.userinfo.learnmgr"/></option>
										</select>
										<select name="searchKey" id="searchKey" class="form-control input-sm" style="width:100px;float:left">
											<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
											<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
										</select>
										<div class="input-group" style="width:210px;float:left">
											<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
											<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
												<i class="fa fa-search"></i>
											</span>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="col-md-5 col-xs-12 mb5">
										<div class="pull-right">
											<div class="btn-group" style="float:left;">
												<button class="btn btn-primary btn-sm" onclick="userWrite()"><i class="fa fa-edit"></i> <spring:message code="button.write.user"/></button>
												<c:if test="${MSG_EMAIL eq 'Y' }">
												<button class="btn btn-default btn-sm" onclick="messageForm('EMAIL')"><i class="fa fa-envelope-o"></i> <spring:message code="button.email"/></button>
												</c:if>
											</div>
											<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm" style="width:60px;float:left">
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
									</div>
									<div class="clearfix"></div>
									<div class="col-md-12" id="userList">
										<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
											<colgroup>
												<col style="width:30px"/>
												<col style="width:150px"/>
												<col style="width:100px"/>
												<col style="width:100px"/>
												<col style="width:100px"/>
												<col style="width:200px"/>
												<col style="width:100px"/>
												<col style="width:50px"/>
											</colgroup>
											<thead>
												<tr>
													<th scope="col"></th>
													<th scope="col"><spring:message code="common.title.no"/> </th>
													<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
													<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
													<th scope="col"><spring:message code="user.title.userinfo.mobileno"/></th>
													<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
													<th scope="col"><spring:message code="common.title.regdate"/></th>
													<th scope="col"><spring:message code="user.title.userinfo.stats"/></th>
													<th scope="col"><spring:message code="common.title.edit"/></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td colspan="10"><spring:message code="user.message.userinfo.nodata"/></td>
												</tr>
											</tbody>
										</table>
									</div>
									</form>
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
	var ItemDTO = new Object();;
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
				event.preventDefault();
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

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$("#Search input[name='sel']").prop({checked:true});
	    }else{
	    	$("#Search input[name='sel']").prop({checked:false});
	    }
	}

	/**
	 *  페이징처리
	 */
	function listPageing(page) {
		$("#loadingBar").show();
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var searchAuthGrp = $("#searchAuthGrp").val();
		var listScale = $("#listScale option:selected").val();

		$('#userList')
			.load(
				cUrl("/mng/user/userInfo/listManage"),
				{ 
					"searchKey":searchKey,
				  	"searchValue":searchValue,
				  	"searchAuthGrp":searchAuthGrp,
				  	"sortKey":ItemDTO.sortKey,
				  	"pageIndex":page,
				  	"listScale":listScale
				},
				function () {$("#loadingBar").hide();}
			);
	}

	/**
	 * 관리자 등록 폼
	 */
	function userWrite() {
			var url = generateUrl("/mng/user/userInfo/addManagePop")
			var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(800, 500);
			modalBox.setTitle("<spring:message code="user.title.userinfo.write"/>");
			modalBox.show();
	}

	/**
	 * 관리자 수정 폼
	 */
	function userEdit(userNo, orgCd) {
		var url = generateUrl("/mng/user/userInfo/editManagePop",{ "orgCd":orgCd, "userNo":userNo});
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="user.title.userinfo.edit"/>");
		modalBox.show();
	}

	function userInfo(userNo, orgCd) {
		var url = generateUrl("/mng/user/userInfo/viewUserPop",{ "orgCd":orgCd, "userNo":userNo});
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}

	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var userList = "";
		$("#Search input[name='sel']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
				userList += $(this).val();
			}
		});
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		
		var url = generateUrl("/mng/log/message/addMessagePop",{"msgDivCd":msgDivCd, "logMsgTransLogVO.userNoList":userList});
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}
</script>
