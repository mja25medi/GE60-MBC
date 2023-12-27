<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form name="Search" id="Search" onsubmit="return false">
	<div style="float:left;width:100%;padding:5px 0px 5px 0px">
		<div style="float:left">
			<select name="declsNo" id="declsNo" onchange="listStudent(1)">
				<option value=""><spring:message code="course.title.decls.all"/></option>
				<c:forEach var="decls" items="${creCrsDeclsList}">
				<option value="${decls.declsNo}">${decls.declsNo}</option>
				</c:forEach>
			</select>
		</div>
		<div style="float:left">
			<spring:message code="user.title.userinfo.name"/> :
			<input type="text" name="searchKey" id="searchKey" style="width:60px" style="ime-mode:active;width:60px;"  maxlength="10" class="_enterBind txt"/>
		</div>
		<div style="float:left">
			<spring:message code="student.title.student.enrolldate"/> :
			<input type="text" name="startDate" id="startDate" style="width:70px" class="txt" readonly>
			~
			<input type="text" name="endDate" id="endDate" style="width:70px" class="txt" readonly>
			<meditag:datepicker name1="startDate" name2="endDate"/>
			<input type="image" src="<c:url value="/img/framework/icon/icon_function_search.gif"/>" alt="검색" title="검색" onclick="listStudent(1)">
		</div>
		<div style="float:right">
			<select name="listScale" id="listScale" onchange="listStudent(1)">
				<option value="10">10</option>
				<option value="20" selected="selected">20</option>
				<option value="40">40</option>
				<option value="60">60</option>
				<option value="80">80</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
		</div>
		<div style="float:right">
			<c:if test="${MSG_SMS eq 'Y' }">
			<meditag:button value="button.sms" title="button.sms" func="messageForm('SMS')" />
			</c:if>
			<c:if test="${MSG_EMAIL eq 'Y' }">
			<meditag:button value="button.email" title="button.email" func="messageForm('EMAIL')" />
			</c:if>
			<c:if test="${MSG_NOTE eq 'Y' }">
			<meditag:button value="button.note" title="button.note" func="messageForm('MSG')" />
			</c:if>
		</div>
	</div>
	<div style="float:left;width:100%;padding:0px 0px 5px 0px">
		<div style="float:left">
			<select name="sort1" id="sort1" onchange="listStudent(1)">
				<c:forEach var="sort" items="${declsSortList}">
				<option value="${sort.codeCd}" <c:if test="${sort.codeCd eq 'DECLS_NO'}">selected="selected"</c:if>>${sort.codeNm}</option>
				</c:forEach>
			</select>
		</div>
		<div style="float:left">
			<select name="sort2" id="sort2" onchange="listStudent(1)">
				<c:forEach var="sort" items="${declsSortList}">
				<option value="${sort.codeCd}" <c:if test="${sort.codeCd eq 'USER_NM'}">selected="selected"</c:if>>${sort.codeNm}</option>
				</c:forEach>
			</select>
		</div>
		<div style="float:left">
			<select name="sort3" id="sort3" onchange="listStudent(1)">
				<c:forEach var="sort" items="${declsSortList}">
				<option value="${sort.codeCd}" <c:if test="${sort.codeCd eq 'USER_ID'}">selected="selected"</c:if>>${sort.codeNm}</option>
				</c:forEach>
			</select>
		</div>
		<div style="float:left">
			<meditag:button value="button.create.eduno" title="button.create.eduno" func="makeEduNo();"  />
			<meditag:button value="button.add.eduno" title="button.add.eduno" func="addEduNoAll();"  />
		</div>
		<div style="float:right">
			<meditag:button value="button.print.namecard" title="button.print.namecard" func="viewReport('nameCard');" icon="icon_report.gif"  />
			<meditag:button value="button.print.attendbook" title="button.print.attendbook" func="viewReport('attendBook');"  icon="filetype/xls.gif" />
		</div>
	</div>
	<div id="studentList">
		<table summary="<spring:message code="student.title.student.manage"/>" style="width:100%" class="table_list">
			<colgroup>
				<col style="width:30px" />
				<col style="width:50px" />
				<col style="width:50px" />
				<col style="width:50px" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:50px" />
				<col style="width:50px" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" ></th>
					<th scope="col" ><spring:message code="common.title.no"/></th>
					<th scope="col" ><spring:message code="student.title.student.eduno"/></th>
					<th scope="col" ><spring:message code="student.title.student.decls"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.mobileno"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col" ><spring:message code="common.title.stats"/></th>
					<th scope="col" ><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="10"><spring:message code="student.message.student.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<form id="studentFrom" name="studentFrom" onsubmit="return false" >
		<input type="hidden" name="stdNo">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }"/>
		<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);


		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listStudent(1);
			}
		}

		ItemDTO.crsCreCd = '${vo.cstudentVO}'
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
	 * 개설 과정 목록 조회
	 * 개설 년도별 개설 과정 목록
	 */
	function listStudent(page) {
		displayWorkProgress();
		ItemDTO.curPage = page;
		var f = document.Search;
		var crsCreCd = ItemDTO.crsCreCd;
		var userNm = $("#searchKey").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var sort1 = $("#sort1 option:selected").val();
		var sort2 = $("#sort2 option:selected").val();
		var sort3 = $("#sort3 option:selected").val();
		var listScale = $("#listScale option:selected").val();
		var declsNo = $("#declsNo option:selected").val();

		$("#studentList")
			.load( cUrl("/mng/std/student/listEduNoStudent"), {
				"crsCreCd" : ItemDTO.crsCreCd,
				"enrlSts" : '|S|C|F|',
				"userNm" : userNm,
				"startDate" : startDate,
				"endDate" : endDate,
				"declsNo" : declsNo,
				"sort1" : sort1,
				"sort2" : sort2,
				"sort3" : sort3,
				"curPage" : ItemDTO.curPage,
				"listScale" : listScale },
				listStudentCallback
			);
	}


	/**
	 * 수강관리 수강생 리스트 콜백
	 */
	function listStudentCallback() {
		closeWorkProgress();
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
		parent.resizeForm();
	}

	function makeEduNo() {
		if(!confirm('<spring:message code="student.message.student.confirm.eduno.cretae"/>')) {
			return;
		} else {
			var f= document.Search;
			var listScale = $("#listScale option:selected").val();
			var startNo = parseInt(listScale,10) * parseInt(ItemDTO.curPage -1,10);
			var ObjStdNo = document.getElementsByName("sel");
			var ObjEduNo = document.getElementsByName("eduNo");
			for(var i=0; i < ObjStdNo.length; i++) {
				startNo = startNo + 1;
				ObjEduNo[i].value = startNo;
			}
		}
	}

	function addEduNoAll() {
		if(!confirm('<spring:message code="student.message.student.confirm.eduno.add"/>')){
			return;
		}
		var eduNos = arrayToString('eduNo');
		if(eduNos != '') {
			$.getJSON( cUrl("/mng/std/student/addEduNoAll"),		// url
					{
					  "crsCreCd" : ItemDTO.crsCreCd,
					  "strStdNo" : arrayToString('sel'),
					  "eduNo" : eduNos
					},			// params
					processCallback				// callback function
				);
		}
	}

	function arrayToString(objName) {
		obj = document.getElementsByName(objName);
		if(obj == null) {
			return "";
		}
		var retStr = '';
		for(var i=0; i < obj.length; i++) {
			for( var j=0; j < i; j++) {
				if(obj[j].value == obj[i].value) {
					alert("<spring:message code="student.message.student.alert.eduno.dupno"/>");
					obj[i].focus();
					return '';
				}
			}
			retStr = retStr + "|" + obj[i].value;
		}
		retStr = retStr.substring(1);
		return retStr;
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

	function printNameCard() {
		var url = cUrl("/mng/std/student/viewPrintNameCard?studentVO.crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}studentVO.nameType=tag1");
		var printWin11 = window.open(url, 'nameCardPrint','width=760, height=700, top=10, left=10, scrollbars=0');
	}

	function excelDownloadAttend() {

		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		$("#studentFrom").attr('target', '_m_download_iframe');
		$("#studentFrom").attr("action", "/mng/std/student/listExcelDownloadAttend");
		$("#studentFrom").submit();
		$("#studentFrom").removeAttr('target');
	}

	//교육생 상세 정보
	function viewStudent(stdNo){
		var url = cUrl("/mng/std/student/viewStudentPop")+"?stdNo="+stdNo+"${AMPERSAND}stayYn=${courseVO.stayYn}";
		var option = "width=700, height=560, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var editWin = window.open(url,'editWin', option);
		editWin.focus();
	}


	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		alert('called  : '+msgDivCd);
		var userList = $("#Search input[name='sel']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var url = cUrl("/mng/log/message/addMessagePop")+"?logMsgLogVO.msgDivCd="+msgDivCd+"${AMPERSAND}messageTransVO.stdNoList="+userList;
		var option = "width=700, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var msgWin = window.open(url,'msgWin', option);
		msgWin.focus();
	}

	// 사용자 상세 정보 조회
	function userInfo(userNo) {
		var url = cUrl("/mng/user/userInfo/viewUserPop")+"?userNo="+userNo;
		var option = "width=800, height=560, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var userWin = window.open(url,'userWin', option);
		userWin.focus();
	}

	function viewReport(str) {
		var width = "830";
		var height = "640";
		if(str == 'attendBook') {
			width = "1000";
			height = "640";
		}
		var url = "${reportUrl}?rex_rptname="+str+"${AMPERSAND}crsCreCd=${studentVO.crsCreCd}";

		var option = "width="+width+", height="+height+", toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var reportWin = window.open(url,'reportWin', option);
		reportWin.focus();
	}
</script>
