<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

		<section class="content">
			<div class="row" id="content">
				<div class="box">
						<div class="box-body">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-5">
							<select name="searchKey" id="searchKey" class="form-control input-sm">
								<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
								<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
							</select>
							<div class="input-group" style="width:160px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
						<div class="col-md-7 text-right">
							<div class="input-group">
								<div class="input-group-btn">
									<button class="btn btn-primary btn-sm" onclick="userWrite()" ><spring:message code="button.write.user"/></button>
									<c:if test="${MSG_SMS eq 'Y' }">
									<button class="btn btn-info btn-sm" onclick="messageForm('SMS')" ><spring:message code="button.sms"/></button>
									</c:if>
									<c:if test="${MSG_EMAIL eq 'Y' }">
									<button class="btn btn-info btn-sm" onclick="messageForm('EMAIL')" ><spring:message code="button.email"/></button>
									</c:if>
									<c:if test="${MSG_NOTE eq 'Y' }">
									<button class="btn btn-info btn-sm" onclick="messageForm('MSG')" ><spring:message code="button.note"/></button>
									</c:if>
								</div>
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
						<div class="col-md-12" style="margin-top:5px;">
							<div id="userList" style="float:left;width:100%;">
								<table summary="<spring:message code="user.title.userinfo.manage"/>" style="width:100%" class="table table-bordered">
									<colgroup>
										<col style="width:30px"/>
										<col style="width:50px"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:80px"/>
										<col style="width:70px"/>
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
	var ItemDTO = new Object();
	var modalBox = null;
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
		//showLayer("list");
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();

		$('#userList')
			.load(
				cUrl("/adm/user/info/listAdmin.do"),
				{ "searchKey":searchKey,
				  "searchValue":searchValue,
				  "pageIndex":page,
				  "listScale":listScale
				},
				function () {
					
				}
			);
	}

	/**
	 * 사용자 등록 폼
	 */
	function userWrite() {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/user/info/addFormAdminPop"/>'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 530);
		modalBox.setTitle("<spring:message code="user.title.userinfo.write"/>");
		modalBox.show();
	}

	/**
	 * 사용자 수정 폼
	 */
	function userEdit(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/user/info/editFormAdminPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 460);
		modalBox.setTitle("<spring:message code="user.title.userinfo.edit"/>");
		modalBox.show();
	}

	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/user/info/viewUserPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
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
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/log/message/messageWritePop"/>"
			+ "?msgDivCd="+msgDivCd+"&userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}
</script>
