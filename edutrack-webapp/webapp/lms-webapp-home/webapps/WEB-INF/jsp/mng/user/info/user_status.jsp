
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<section class="content">
		<div class="row">
<!-- 			<div class="col-md-12"> -->
			<div class="col-md-12 col-sm-12 box box-info"> 
				<div class="box">
					<div class="box-body">
						
						<div class="well well-sm">
							<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
							<div class="col-md-7 col-xs-12 mb5 ">
								<input type="hidden" name="searchAuthGrp" id="searchAuthGrp" value="MEMBER">
								<label class="filter-col" for="knowCtgrCdLvl1" style="margin-left:50px;"><spring:message code="user.title.userinfo.regtype" text="가입유형"/>&nbsp; : &nbsp;</label>
								<select name="userSts" id="userSts" class="form-control input-sm" onchange="listPageing(1);">
									<option value=""><spring:message code="user.title.userinfo.userall" text="회원전체"/></option>
									<option value="U"><spring:message code="user.title.userinfo.regtype.sns" text="소셜가입"/></option>
									<option value="C"><spring:message code="user.title.login.joinin" text="회원가입"/></option>
								</select>
								
								<label class="filter-col" for="knowCtgrCdLvl1" style="margin-left:50px;"><spring:message  code="user.title.userinfo.manage.userdiv2" text="국가"/>&nbsp; : &nbsp;</label>
								<select name="userDivCd" id="userDivCd" class="form-control input-sm" style="width:300px;" onchange="setTitle(this)">
									<option value=""><spring:message code="board.title.editor.table.select.line" text="선택"/></option>
									<c:forEach items="${codeList}" var="item">
									<c:set var="codeName" value="${item.codeNm}"/>
									<c:forEach var="lang" items="${item.codeLangList}">
										<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
									</c:forEach>
									<option value="${item.codeCd}" <c:if test="${item.codeCd eq vo.userDivCd }"> selected</c:if>>${codeName}</option>
								</c:forEach>
								</select>
							</br></br>	
							
							<input type="hidden" name="searchAuthGrp" id="searchAuthGrp" value="MEMBER">
							<label class="filter-col" for="knowCtgrCdLvl1" style="margin-left:50px;"><spring:message code="common.title.useyn" text="사용여부"/>&nbsp; : &nbsp;</label>
							<select name="userSts" id="userSts" class="form-control input-sm" onchange="listPageing(1);">
								<option value=""><spring:message code="common.title.all" text="전체"/></option>
								<option value="U"><spring:message code="common.title.useyn_y" text="사용"/></option>
								<option value="C"><spring:message code="common.title.useyn_n" text="미사용"/></option>
							</select>
							
							<label class="filter-col" for="knowCtgrCdLvl1" style="margin-left:50px;"><spring:message code="user.title.userinfo.age" text="나이"/>&nbsp; : &nbsp;</label>
							<select name="userSts" id="userSts" class="form-control input-sm" onchange="listPageing(1);">
								<option value=""><spring:message code="common.title.all" text="전체"/></option>
								<option value="U">&nbsp;&nbsp;0~10</option>
								<option value="C">10~20</option>
								<option value="C">20~30</option>
								<option value="C">30~40</option>
								<option value="C">40~50</option>
								<option value="C">50~60</option>
								<option value="C">60~70</option>
								<option value="C">70~80</option>
							</select>							
															
								<div class="clearfix"></div>
							</div>
							<div class="col-md-5 col-xs-12 mb5" style="margin-top:50px;">
								<div class="pull-right">
									<div class="btn-group" style="float:left;">
										<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.download.excel"/> </a>
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
							</div>
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
		
		$('#userList')
			.load(
				cUrl("/mng/user/userInfo/listUserStatus"),
				{ 
					"searchKey":searchKey,
				  	"searchValue":searchValue,
				  	"searchAuthGrp":searchAuthGrp,
				  	"userSts":userSts,
				  	"sortKey":ItemDTO.sortKey,
				  	"pageIndex":page,
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
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="user.title.userinfo.write"/>");
		modalBox.show();
	}

	/**
	 * 사용자 수정 폼
	 */
	function userEdit(userNo) {
		var url = generateUrl("/mng/user/userInfo/editUserPop",{ "userNo":userNo});
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="user.title.userinfo.edit"/>");
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
