<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="box">
		<div class="box-body">
			<div class="row" id="content">
				<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
				<div class="col-md-7 col-sm-7">
					<select name="orgCd" id="orgCd"  onchange="listPageing(1);" class="form-control input-sm">
						<option value=""><spring:message code="org.title.orginfo.all"/></option>
						<c:forEach var="item" items="${orgList}">
						<option value="${item.orgCd}">${item.orgNm}</option>
						</c:forEach>
					</select>
					<select name="searchAuthGrp" id="searchAuthGrp" onchange="listPageing(1);" class="form-control input-sm">
						<option value="ALL"><spring:message code="user.title.userinfo.userall"/></option>
						<option value="TEACHER"><spring:message code="user.title.userinfo.teacher"/></option>
						<option value="TUTOR"><spring:message code="user.title.userinfo.tutor"/></option>
						<option value="MANAGER"><spring:message code="user.title.userinfo.manager"/></option>
					</select>
					<select name="searchKey" id="searchKey" class="form-control input-sm">
						<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
						<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
					</select>
					<div class="input-group" style="width:140px;">
						<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
						<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="col-md-5 col-sm-5 text-right">
					<div class="input-group">
						<div class="input-group-btn">
							<button class="btn btn-primary btn-sm" onclick="userWrite()"><spring:message code="button.write.user"/></button>
							<c:if test="${MSG_SMS eq 'Y' }">
							<button class="btn btn-info btn-sm" onclick="messageForm('SMS')"><spring:message code="button.sms"/></button>
							</c:if>
							<c:if test="${MSG_EMAIL eq 'Y' }">
							<button class="btn btn-info btn-sm" onclick="messageForm('EMAIL')"><spring:message code="button.email"/></button>
							</c:if>
							<c:if test="${MSG_NOTE eq 'Y' }">
							<button class="btn btn-info btn-sm" onclick="messageForm('MSG')"><spring:message code="button.note"/></button>
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
					<div id="userList" style="width:100%;">
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
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
		var orgCd = $("#orgCd option:selected").val();
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var searchAuthGrp = $("#searchAuthGrp option:selected").val();
		var listScale = $("#listScale option:selected").val();

		$('#userList')
			.load(
				cUrl("/adm/user/info/listUser"),
				{ "searchKey":searchKey,
				  "searchValue":searchValue,
				  "searchAuthGrp":searchAuthGrp,
				  "orgCd":orgCd,
				  "sortKey":ItemDTO.sortKey,
				  "curPage":page,
				  "listScale":listScale
				},
				function () {}
			);
	}

	/**
	 * 사용자 등록 폼
	 */
	function userWrite() {
		var orgCd = $("#orgCd option:selected").val();
		if(orgCd == ""){
			alert("<spring:message code="org.message.orginfo.alert.selectorg"/>");
			$("#orgCd").focus();
			return;
		}
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/user/info/addFormUserPop"/>"
			+ "?orgCd="+orgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="user.title.userinfo.write"/>");
		modalBox.show();
	}

	/**
	 * 사용자 수정 폼
	 */
	function userEdit(userNo, orgCd) {

		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/user/info/editFormUserPop"/>"
			+ "?userNo="+userNo+"'&orgCd="+orgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="user.title.userinfo.edit"/>");
		modalBox.show();
	}

	function userInfo(userNo, orgCd) {

		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/user/info/viewUserPop"/>"
			+ "?userNo="+userNo+"'&orgCd="+orgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}

	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var orgCd = $("#orgCd option:selected").val();
		if(orgCd == ""){
			alert("<spring:message code="org.message.orginfo.alert.selectorg"/>");
			$("#orgCd").focus();
			return;
		}
		
		var userList = "";
		/* $("#Search input[name='sel']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
				userList += $(this).val();
			}
		}); */
		
		var userList = $("#Search input[name='sel']:checked").stringValues();
		
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/log/message/addMessagePop"/>"
			+ "?orgCd="+orgCd+"&msgDivCd="+msgDivCd+"&logMsgTransLogVO.userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}
</script>
