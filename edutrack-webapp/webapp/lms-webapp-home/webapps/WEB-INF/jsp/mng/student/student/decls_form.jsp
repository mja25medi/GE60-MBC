<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br/>
	<form name="Search" id="Search" onsubmit="return false">
	<table style="width:100%">
		<tr>
			<td width="46%" valign="top">
				<div class="row" style="padding-left:15px;padding-right:15px;">
					<div style="float:left;margin-right:5px; line-height:30px;">1 <spring:message code="course.title.decls"/></div>
					<div class="input-group" style="float:left;width:120px;">
						<input type="text" name="searchKey1" style="ime-mode:active;" maxlength="10" class="_enterBind form-control input-sm"/>
						<span class="input-group-addon" onclick="listSource()" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
					<div style="clear: both;"></div>
				</div>
				<div id="sourceList" style="margin-top:5px;">
					<table summary="<spring:message code="student.title.student.list"/>" class="table table-bordered wordbreak">
						<colgroup>
							<col style="width:30px" />
							<col style="width:50px" />
							<col style="width:auto;" />
							<col style="width:auto;" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col" ></th>
								<th scope="col" ><spring:message code="common.title.no"/></th>
								<th scope="col" ><spring:message code="user.title.userinfo.name"/></th>
								<th scope="col" ><spring:message code="user.title.userinfo.userid"/></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4"><spring:message code="student.message.student.nodata"/></td>
							</tr>
						</tbody>
					</table>
				</div>
			</td>
			<td width="8%" class="text-center">
				<a href="javascript:moveTarget()" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-arrow-right"></i></a>
				<br/><br/>
				<a href="javascript:moveSource()" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-arrow-left"></i></a>
			</td>
			<td width="46%" valign="top">
				<div class="row" style="padding-left:15px;padding-right:15px;">
					<div class="input-group" style="float:left">
						<select name="declsNo" id="declsNo" onchange="listTarget()" class="form-control input-sm">
							<c:forEach var="decls" items="${creCrsDeclsList}">
							<c:if test="${decls.declsNo != 1 }">
							<option value="${decls.declsNo}">${decls.declsNo} <spring:message code="course.title.decls"/></option>
							</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="input-group" style="float:left;width:120px;">
						<input type="text" name="searchKey2" style="ime-mode:active;" maxlength="10" class="_enterBind2 form-control input-sm"/>
						<span class="input-group-addon" onclick="listTarget()" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div id="targetList" style="margin-top:5px;">
					<table summary="<spring:message code="student.title.student.list"/>" class="table table-bordered wordbreak">
						<colgroup>
							<col style="width:30px" />
							<col style="width:50px" />
							<col style="width:auto;" />
							<col style="width:auto;" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col" ></th>
								<th scope="col" ><spring:message code="common.title.no"/></th>
								<th scope="col" ><spring:message code="user.title.userinfo.name"/></th>
								<th scope="col" ><spring:message code="user.title.userinfo.userid"/></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4"><spring:message code="student.message.student.nodata"/></td>
							</tr>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</table>
	</form>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = new Object();

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);

		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listSource();
			}
		}

		$("._enterBind2").bind("keydown", eventForSearch2);

		function eventForSearch2(event) {
			if (event.keyCode == '13') {
				listTarget();
			}
		}
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		ItemDTO.crsCreCd = '${studentVO.crsCreCd}';
		listSource();
		listTarget();
	});

	function checkAllSource() {
	    var state=$('input[name=chkAllSource]:checked').size();
	    if(state==1){
	   		$(document).find("#Search input[name='selSource']").prop({checked:true});
	    }else{
	    	$(document).find("#Search input[name='selSource']").prop({checked:false});
	    }
	}

	function checkAllTarget() {
	    var state=$('input[name=chkAllTarget]:checked').size();
	    if(state==1){
	   		$(document).find("#Search input[name='selTarget']").prop({checked:true});
	    }else{
	    	$(document).find("#Search input[name='selTarget']").prop({checked:false});
	    }
	}
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	/**
	 * 1분반 교육생 목록 조회.
	 * 소스는 무조건 1분반으로...
	 */
	function listSource() {
		var f = document.Search;
		var crsCreCd = ItemDTO.crsCreCd;
		var userNm = f.searchKey1.value;
		var declsNo = '1';

		$("#sourceList")
			.load( cUrl("/mng/std/student/listDeclsSource"), {
				"studentVO.crsCreCd" : ItemDTO.crsCreCd,
				"studentVO.enrlSts" : '|E|S|C|F|',
				"studentVO.userNm" : userNm,
				"studentVO.declsNo" : declsNo },
				listCallback
			);
	}

	/**
	 * 타겟 분반 교육생 목록 조회
	 */
	function listTarget() {
		var f = document.Search;
		var crsCreCd = ItemDTO.crsCreCd;
		var userNm = f.searchKey2.value;
		var declsNo = f.declsNo[f.declsNo.selectedIndex].value;

		$("#targetList")
			.load( cUrl("/mng/std/student/listDeclsTarget"), {
				"studentVO.crsCreCd" : ItemDTO.crsCreCd,
				"studentVO.enrlSts" : '|E|S|C|F|',
				"studentVO.userNm" : userNm,
				"studentVO.declsNo" : declsNo },
				listCallback
			);
	}


	/**
	 * 수강관리 수강생 리스트 콜백
	 */
	function listCallback() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
		parent.resizeForm();
	}

	// 타겟 분반으로 이동
	function moveTarget() {
		var userList = $("#Search input[name='selSource']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="student.message.decls.select.student"/>");
			return;
		}
		var f = document.Search;
		var declsNo = f.declsNo[f.declsNo.selectedIndex].value;
		$.getJSON( cUrl( "/mng/std/student/editDecls"),
				   {  "userList" : userList, "declsNo" : declsNo },			// params
				   function(data) {
						if(data.result >= 0) {
							//-- 정상 처리
							alert("<spring:message code="student.message.decls.change.success"/>");
							listSource();
							listTarget();
			 	  		} else {
			 	  			//-- 비정상 처리
			 	  			alert("<spring:message code="student.message.decls.change.failed"/>");
			 	  		}
					}
				);
	}

	// 소스 분밪으로 이동
	function moveSource() {
		var userList = $("#Search input[name='selTarget']:checked").stringValues();
		if(userList == "") {
			alert("이동할 사용자를 선택하세요.");
			return;
		}
		var declsNo = '1';
		$.getJSON( cUrl( "/mng/std/student/editDecls"),
				   {  "userList" : userList, "declsNo" : declsNo },			// params
				   function(data) {
						if(data.result >= 0) {
							//-- 정상 처리
							alert("<spring:message code="student.message.decls.change.success"/>");
							listSource();
							listTarget();
			 	  		} else {
			 	  			//-- 비정상 처리
			 	  			alert("<spring:message code="student.message.decls.change.failed"/>");
			 	  		}
					}
				);
	}

	// 사용자 상세 정보 조회
/* 	function userInfo(userNo) {
		var url = cUrl("/mng/user/userInfo/")+"?cmd=viewUser${AMPERSAND}usrUserInfoVO.userNo="+userNo;
		var option = "width=800, height=560, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var userWin = window.open(url,'userWin', option);
		userWin.focus();
	}
 */
	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/user/userInfo/viewUserPop"
			+ "?usrUserInfoVO.userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}
</script>
