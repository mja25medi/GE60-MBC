<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

		<section class="content">
			<div class="row" id="content">
				<div class="box">
					<div class="box-body">
						<div id="userContent">
							<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
							<div class="col-md-7 col-xs-12 mb5">
								<select name="searchAuthGrp" id="searchAuthGrp" class="form-control input-sm" style="float:left;" onchange="listPageing(1);">
									<option value="MEMBER">일반회원</option>
									<option value="TEACHER">강사</option>
									<option value="DEPTMNG">기업관리자</option>
									<option value="CNTSMGR">컨텐츠관리자</option>
									<option value="MANAGER">사이트관리자</option>
									<option value="TCHMGR">담임</option>
									<option value="ASSTCHMGR">부담임</option>
								</select>
								<select name="deptCd" id="deptCd" class="form-control input-sm" style="float:left" onchange="listPageing(1);">
									<option value="">기업 선택</option>
									<c:forEach var="item" items="${deptList }">
										<option value="${item.deptCd }">${item.deptNm }</option>
									</c:forEach>
								</select>								
								<select name="userSts" id="userSts" class="form-control input-sm" style="float:left" onchange="listPageing(1);">
									<option value=""><spring:message code="user.title.userinfo.stats.all"/></option>
									<option value="U"><spring:message code="user.title.userinfo.stats.use"/></option>
									<option value="C"><spring:message code="user.title.userinfo.stats.stop"/></option>
<%-- 									<option value="F"><spring:message code="user.title.userinfo.stats.rest"/></option>
									<option value="D"><spring:message code="user.title.userinfo.stats.leave"/></option> --%>
								</select>
								<select name="exceptYn" id="exceptYn" class="form-control input-sm" style="float:left" onchange="listPageing(1);">
									<option value="">아이디 전체</option>
									<option value="Y">예외용 아이디</option>
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
										<a href="javascript:viewAddUserExcelPop();" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
										<c:if test="${vo.itgrtMbrUseYn eq 'N' }">
										<button class="btn btn-primary btn-sm" onclick="userWrite()"><i class="fa fa-edit"></i> <spring:message code="button.write.user"/></button>
										</c:if>
										<c:if test="${MSG_SMS eq 'Y' }">
										<button class="btn btn-default btn-sm" onclick="messageForm('SMS')"><i class="fa fa-envelope-o"></i> <spring:message code="button.sms"/></button>
										</c:if>
										<c:if test="${MSG_EMAIL eq 'Y' }">
										<button class="btn btn-default btn-sm" onclick="messageForm('EMAIL')"><i class="fa fa-envelope-o"></i> <spring:message code="button.email"/></button>
										</c:if>
										<c:if test="${MSG_NOTE eq 'Y' }">
										<button class="btn btn-default btn-sm" onclick="messageForm('MSG')"><i class="fa fa-envelope-o"></i> <spring:message code="button.note"/></button>
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
							<div class="col-md-12" id="userList"></div>
							</form>
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
		var userSts = $("#userSts option:selected").val();
		var listScale = $("#listScale option:selected").val();
		var deptCd = $("#deptCd").val();
		var exceptYn = $("#exceptYn").val();
		
		$('#userList')
			.load(
				cUrl("/mng/user/userInfo/listUser"),
				{ 
					"searchKey":searchKey,
				  	"searchValue":searchValue,
				  	"searchAuthGrp":searchAuthGrp,
				  	"userSts":userSts,
				  	"deptCd" : deptCd,
				  	"exceptYn" : exceptYn,
				  	"sortKey":ItemDTO.sortKey,
				  	"curPage":page,
				  	"listScale":listScale
				},
				function () {$("#loadingBar").hide();}
			);
	}

	/**
	 * 사용자 등록 폼
	 */
	function userWrite() {
		var url = generateUrl("/mng/user/userInfo/addUserPop")
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1200, 700);
		modalBox.setTitle("<spring:message code="user.title.userinfo.write"/>");
		modalBox.show();
	}

	/**
	 * 사용자 수정 폼
	 */
	function userEdit(userNo) {
		var url = generateUrl("/mng/user/userInfo/editUserPop",{"userNo":userNo});
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1200, 700);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}

	function userInfo(userNo, orgCd) {
		var url = generateUrl("/mng/user/userInfo/viewUserPop",{"userNo":userNo});
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}
	
	function crmOpen(userNo) {
		var url = generateUrl("/mng/user/userInfo/crmOpenPop",{"userNo":userNo});
		var contentsWin = window.open(url);
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
		
		var url = generateUrl("/mng/log/message/addMessagePop",{ "msgDivCd":msgDivCd, "logMsgTransLogVO.userNoList":userList});
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}
	
	function viewAddUserExcelPop() {
		var url = generateUrl("/mng/user/userInfo/addUserExcelPop");
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("엑셀 업로드");
		modalBox.show();
	}
	
	function editExceptYn(userNo, element) {
		var exceptYn = element.checked ? 'Y' : 'N';
		$.post(cUrl("/mng/user/userInfo/editExcept"),{
			"userNo" : userNo,
			"exceptYn" : exceptYn
		}, function(data) {
			if(data.result != 1) {
				alert(data.message);
				listPageing(1);
			}
		})
	}
</script>
