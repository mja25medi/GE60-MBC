<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form name="Search" id="Search" onsubmit="return false">
	<div style="float:left;width:100%;padding:5px 0px 5px 0px">
		<div style="float:left;padding-right:5px">
				<select name="repaySts" id="repaySts" class="form-control input-sm" onChange="listStudent(1)">
					<option value="">환불상태선택</option>
					<c:forEach var="item" items="${repayStsList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.repaySts eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
		</div>
		<div style="float:left;padding-right:5px">
			이름 : <input type="text" name="searchKey" style="width:50px;" class="_enterBind txt" title="이름 입력" />
			<input type="image" src="<c:url value="/img/framework/icon/icon_function_search.gif"/>" alt="검색" title="검색" onclick="listStudent(1)"/>
		</div>
		<div style="float:right">
			<meditag:selectbox width="50" fieldName="listScale" formName="Search" height="100" id="listScale" onChange="listStudent(1)" value="20"/>
			<meditag:selectboxOption formName="Search" value="10" text="10" fieldName="listScale"/>
			<meditag:selectboxOption formName="Search" value="20" text="20" fieldName="listScale"/>
			<meditag:selectboxOption formName="Search" value="40" text="40" fieldName="listScale"/>
			<meditag:selectboxOption formName="Search" value="60" text="60" fieldName="listScale"/>
			<meditag:selectboxOption formName="Search" value="80" text="80" fieldName="listScale"/>
			<meditag:selectboxOption formName="Search" value="100" text="100" fieldName="listScale"/>
			<meditag:selectboxOption formName="Search" value="200" text="200" fieldName="listScale"/>
		</div>
		<div style="float:right">
			<meditag:button value="SMS" title="SMS" func="messageForm('SMS')" />
			<meditag:button value="E-MAIL" title="MAIL" func="messageForm('EMAIL')" />
			<meditag:button value="쪽지" title="쪽지" func="messageForm('MSG')" />
		</div>
	</div>
	<div id="studentList">
		<table summary="과정 목룍 표" style="width:100%" class="table_list">
			<colgroup>
				<col style="width:30px" />
				<col style="width:50px" />
				<col style="width:70px" />
				<col style="width:70px" />
				<col style="width:100px" />
				<col style="width:100px" />
				<col style="width:100px" />
				<col style="width:100px" />
				<col style="width:auto" />
				<col style="width:100px" />
				<col style="width:100px" />
				<col style="width:50px" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" ></th>
					<th scope="col" >번호</th>
					<th scope="col" >이 름</th>
					<th scope="col" >아이디</th>
					<th scope="col" >회원사</th>
					<th scope="col" >수강신청일</th>
					<th scope="col" >결제금액</th>
					<th scope="col" >결제상태</th>
					<th scope="col" >환불요청일</th>
					<th scope="col" >환불일</th>
					<th scope="col" >환불상태</th>
					<th scope="col" >관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="12">* 등록된 환불관련 정보가 없습니다.</td>
				</tr>
			</tbody>
		</table>
	</div>

	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">
	var popBox = PopupBox("", "", "", "width=400,height=220,style=normal,scrolling=no,bgcolor=,modal=yes,resizable=no,move=yes,titlebar=yes,position=center,top=0,left=0","set2");
	var ItemDTO = new Object();

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listStudent(1);
			}
		}
		ItemDTO.crsCreCd = '${studentVO.crsCreCd}';
		listStudent(1);
	});

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$(document).find("#Search input[name='sel']").attr({checked:true});
	    }else{
	    	$(document).find("#Search input[name='sel']").attr({checked:false});
	    }
	}

	/**
	 * 수강신청자 목록을 조회한다.
	 */
	function listStudent(page) {
		displayWorkProgress();
		ItemDTO.curPage = page;
		var f = document.Search;
		//f.chkAll.checked = false;
		var repayStsCd = SearchrepaySts .getSelectedValue();
		var userNm = f.searchKey.value;
		var listScale = f.listScale[f.listScale.selectedIndex].value;

		$("#studentList")
			.load( cUrl("/StudentStudentAdmin.do"), {
				"cmd" : "listRepayStudent",
				"studentVO.crsCreCd" : ItemDTO.crsCreCd,
				"studentVO.userNm" : userNm,
				"studentVO.repayStsCd" : repayStsCd,
				"curPage" : ItemDTO.curPage,
				"listScale" : listScale },
				listStudentCallback
			);
		closeWorkProgress();
	}

	function listStudentCallback() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	function setStudent() {
		var strs = "";
		$('input[name=sel]:checked').each(function() {
				strs = strs + "|" + $(this).val();
			}
		);
		strs = strs.substring(1);
		if(strs == "") {
			alert("학습자를 선택해 주세요.");
			return false;
		}
		document.studentForm["studentVO.stdNo"].value = strs;
		return true;
	}


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.studentForm)) return;

		$('#studentForm').find('input[name=cmd]').val(cmd);
		$('#studentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
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
			+ "?logMsgLogVO.msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.stdNoList="+userList+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		parent.modalBox.show();
	}

	// 사용자 상세 정보 조회
	function userInfo(userNo) {
		var url = cUrl("/UserInfoAdmin.do")+"?cmd=viewUser${AMPERSAND}usrUserInfoVO.userNo="+userNo;
		var option = "width=800, height=560, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var userWin = window.open(url,'userWin', option);
		userWin.focus();
	}

	// 교육생 환불 관리
	function editRepay(stdNo){
			var addContent = "<iframe id='stdPopFrame' name='stdPopFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/StudentStudentAdmin.do"/>"
			+ "?cmd=editFormRepay&amp;studentVO.stdNo="+stdNo+"'/>";
			parent.stdViewPopBox.clear();
			parent.stdViewPopBox.addContents(addContent);
			parent.stdViewPopBox.resize(650, 500);
			parent.stdViewPopBox.setTitle("교육생 환불관리");
			parent.stdViewPopBox.show();
	}

	// 교육생 환불 결과 팝업
	function resultRepay(stdNo){
			var addContent = "<iframe id='repayRsltPopFrame' name='repayRsltPopFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/StudentStudentAdmin.do"/>"
			+ "?cmd=readResultRepay&amp;studentVO.stdNo="+stdNo+"'/>";
			parent.stdViewPopBox.clear();
			parent.stdViewPopBox.addContents(addContent);
			parent.stdViewPopBox.resize(550, 300);
			parent.stdViewPopBox.setTitle("교육생 환불 결과");
			parent.stdViewPopBox.show();
	}
</script>
