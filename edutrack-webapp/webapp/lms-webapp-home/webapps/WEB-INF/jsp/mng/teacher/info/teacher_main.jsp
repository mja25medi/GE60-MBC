<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="itemList" value="${userInfoList}"/>
<c:set var="usrUserInfoVO" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="rowLayer" id="listLayer">
					<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<select name="searchKey" id="searchKey" class="form-control input-sm">
								<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
								<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
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
							<c:if test="${MSG_SMS eq 'Y' }">
							<a href="javascript:messageForm('SMS')" class="btn btn-info btn-sm"><spring:message code="button.sms"/> </a>
							</c:if>
							<c:if test="${MSG_EMAIL eq 'Y' }">
							<a href="javascript:messageForm('EMAIL')" class="btn btn-info btn-sm"><spring:message code="button.email"/> </a>
							</c:if>
							<c:if test="${MSG_NOTE eq 'Y' }">
							<a href="javascript:messageForm('MSG')" class="btn btn-info btn-sm"><spring:message code="button.note"/> </a>
							</c:if>

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
					</div>
					<div class="col-md-12" style="margin-top:5px;">
						<div id="teacherList">
							<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:30px"/>
									<col style="width:70px"/>
									<col style="width:auto"/>
									<col style="width:auto"/>
									<col style="width:auto"/>
									<col style="width:auto"/>
									<col style="width:100px"/>
									<col style="width:50px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
										<th scope="col"><spring:message code="common.title.no"/> </th>
										<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
										<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
										<th scope="col"><spring:message code="teacher.title.teacherinfo.tchctgrcd"/></th>
										<th scope="col"><spring:message code="teacher.title.teacherinfo.tchdivcd"/></th>
										<th scope="col"><spring:message code="common.title.regdate"/></th>
										<th scope="col"><spring:message code="common.title.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="8"><spring:message code="user.message.userinfo.nodata"/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>		
	</div>
</section>		
<script type="text/javascript">
	//팝업박스
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

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$("#Search input[name='sel']").prop({checked:true});
	    }else{
	    	$("#Search input[name='sel']").prop({checked:false});
	    }
	}

	function listPageing(page) {
		if(page > 0)
			ItemDTO.curPage = page;
		var orgCd = $("#orgCd option:selected").val();
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();

		$('#teacherList')
			.load(
				cUrl("/mng/tch/info/list"),
				{ 
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "orgCd":orgCd,
				  "sortKey":ItemDTO.sortKey,
				  "curPage":ItemDTO.curPage,
				  "listScale":listScale
				},
				function () { }
			);

	}

	/**
	 * 사용자 정보 폼
	 */
	function tchInfo(userNo) {
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/tch/info/viewTchPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 780);
		modalBox.setTitle("<spring:message code="teacher.title.teacherinfo.teacher.manage"/>");
		modalBox.show();
	}

	/**
	 * 사용자 수정 폼
	 */
	function tchEdit(userNo) {
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/tch/info/editPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 780);
		modalBox.setTitle("<spring:message code="teacher.title.teacherinfo.teacher.manage"/>");
		modalBox.show();
	}

	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var userList = $("#Search input[name='sel']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/log/message/addMessagePop"/>"
			+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}
</script>
