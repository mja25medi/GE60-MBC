<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="projectVO" value="${vo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:css href="css/simpletab.css"/>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/calendar.js"/>
	<meditag:js src="/js/simpletab.js"/>
	<meditag:js src="/js/common_iframe.js"/>
	<meditag:js src="/js/popupbox.js"/>
	<mhtml:head_module uploadify="y"/>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />
		<form id="projectForm" name="projectForm" onsubmit="return false" >
	 	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
	 	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
		<div id="viewProject" style="display:block">
			<table class="board_b" style="width: 106%;">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<th scope="row" class="top">프로젝트 명</th>
					<td class="top" colspan="3">${projectVO.prjtTitle}</td>
				</tr>
				<tr>
					<th scope="row">프로젝트기간</th>
					<td colspan="3">${projectVO.prjtStartDttm} ~ ${projectVO.prjtEndDttm}</td>
				</tr>
				<tr>
					<th scope="row">성적공개여부</th>
					<td colspan="3">${projectVO.scoreOpenYnNm}</td>
				</tr>
				<c:if test="${projectVO.scoreOpenYn eq 'Y'}" >
				<tr>
					<th scope="row">성적공개일자</th>
					<td colspan="3">${projectVO.scoreCfrmDttm}</td>
				</tr>
				</c:if>
			</table>
			<div class="btn_right" style="width: 106%">
				<a href="javascript:editProject()" class="btn02">수정</a>
				<a href="javascript:projectList()" class="btn02">목록</a>
			</div>
		</div>
		<div id="editProject" style="display:none">
			<table class="board_b" style="width: 106%">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<th scope="row" class="top">프로젝트 명</th>
					<td class="top" colspan="3">
						<input type="text" style="float:left; width:500px;" maxlength="100" dispName="프로젝트 명" isNull="N" lenCheck="100" name="prjtTitle" value="${vo.curPage}" class="text" id="projectVO.prjtTitle" />
					</td>
				</tr>
				<tr>
					<th scope="row">프로젝트기간</th>
					<td colspan="3">
						<input type="text" style="width:100px;" maxlength="100" dispName="프로젝트 시작일" isNull="N" lenCheck="100" name="prjtStartDttm" value="${vo.prjtStartDttm}" class="text" readonly="true" />
						<img class="fn_calimg" src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'projectForm', 'prjtStartDttm', '.', 'position=right,datetype=00,clear=yes');"/>
						~ <input type="text" style="width:100px;" maxlength="100" dispName="프로젝트 종료일" isNull="N" lenCheck="100" name="prjtEndDttm" value="${vo.prjtEndDttm}" class="text" readonly="true" />
						<img class="fn_calimg" src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'projectForm', 'prjtEndDttm', '.', 'position=right,datetype=00,clear=yes');"/>
					</td>
				</tr>
				<tr>
					<th scope="row">성적 공개 여부</th>
					<td colspan="3">
						<c:forEach var="item" items="${scoreOpenYn}" varStatus="status">
							<input type="radio" name="scoreOpenYn" value="${item.codeCd}" id="scoreOpenYn_${item.codeCd}" onclick="scoreDttm('${item.codeCd}')"  <c:if test="${vo.scoreOpenYn eq item.codeCd}">checked</c:if>/>
							<label for="scoreOpenYn_${item.codeCd}">${item.codeNm}</label>
							<c:choose>
								<c:when test="${status.last}"></c:when>
								<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
				</tr>
				<tr id="scoreCfrmDttm" style="display:none;">
					<th scope="row">성적 공개 일자</th>
					<td colspan="3">
						<input type="text" style="width:100px;" maxlength="100" dispName="성적공개일자" isNull="N" lenCheck="100" name="scoreCfrmDttm" value="${vo.scoreCfrmDttm}" class="text" id="scoreCfrmDttm" readonly="true" />
						<img class="fn_calimg" src="<c:url value="/img/framework/icon/icon_calendar.gif" />" alt="달력" onClick="Calendar('달력', 'projectForm', 'scoreCfrmDttm', '.', 'position=right,datetype=00,clear=yes');"/>
					</td>
				</tr>
				<tr height="70">
					<th scope="row">프로젝트 내용</th>
					<td colspan="3">
						<textarea style="width:500px;height:50px" dispName="게시판 설명" lenCheck="1000" isNull="N" name="prjtCts" id="prjtCts">${vo.prjtCts}</textarea>
					</td>
				</tr>
				<tr height="70">
					<th scope="row">첨부파일</th>
					<td colspan="3">
						<div style="flot:left"><input type="file" title="첨부파일" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%></div>
						<div style="flot:left" id="fileQueue"></div>
						<div style="flot:left" id="fileListview"></div>
					</td>
				</tr>
			</table>
			<div class="btn_right" style="width: 106%">
				<a href="javascript:editPrj()" class="btn02">저장</a>
				<a href="javascript:delPrj()" class="btn02">삭제</a>
				<a href="javascript:goReset()" class="btn01">취소</a>
			</div>
		</div>
		<br/>

		<ul class="tab_arrow" id="tab">
			<li id="tab0"><a href="javascript:onclickTab('0')">팀관리</a></li>
			<li id="tab1"><a href="javascript:onclickTab('1')">과제관리</a></li>
			<li id="tab2"><a href="javascript:onclickTab('2')">게시판관리</a></li>
			<li id="tab3"><a href="javascript:onclickTab('3')">게시글관리</a></li>
			<li id="tab4" class="last"><a href="javascript:onclickTab('4')">평가관리</a></li>
		</ul>

		<iframe name="subWorkFrame2" id="subWorkFrame2" frameborder="0" src="about:blank" scrolling="no" title="하위 작업 프레임" style="width:106%;"></iframe>

		</form>
	</div>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	var ItemDTO = new Object();

	//수정화면 감추기
	$('#editProject').hide();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectVO.prjtSn}';


		var tab = '${projectForm.tab}';

		if(tab == ""){
			tabUrl('0');
		}else{
			tabUrl(tab);
		}

		if('${projectVO.scoreOpenYn}' == 'Y'){
			scoreDttm('Y');
		}else{
			scoreDttm('N');
		}



		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"maxcount"			: 5,
					"files"				: $.parseJSON('${projectVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"fileDesc"		:	"문서 파일 (*.hwp, *.doc, *.xls, *.docx, *.xlsx, *.zip, *.ppt, *.pptx)",
							"fileExt"		:	"*.hwp; *.doc; *.xls; *.docx; *.xlsx; *.zip; *.ppt; *.pptx;",
							"scriptData"	:	{
									"repository"	:	"PRJT",
									"type"			:	"file"		}
				}});

	});

	function scoreDttm(codeCd){
		if(codeCd == 'Y'){
			$('#scoreCfrmDttm').show();
		}else if(codeCd == 'N'){
			$('#scoreCfrmDttm').hide();
		}
	}

	function onclickTab(tab){
		document.location.href = cUrl("/lec/project/editFormProject")+"?prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}tab="+tab;
	}

	// tab화면
	function tabUrl(tab) {
		var url = {};
 		url['0'] = "//lec/prj/team/mainPrjTeam?prjtSn="+ItemDTO.prjtSn;
		url['1'] = "/lec/prj/assignment/editFormPrjAssignment?prjtSn="+ItemDTO.prjtSn;
		url['2'] = "/lec/prj/bbs/mainPrjBbs?prjtSn="+ItemDTO.prjtSn;
		url['3'] = "/lec/prj/bbsAtcl/mainPrjBbsAtcl?prjtSn="+ItemDTO.prjtSn;
		url['4'] = "/lec/prj/result/mainPrjResult?prjtSn="+ItemDTO.prjtSn;

		document.subWorkFrame2.location.href = cUrl(url[tab]);
		selectTab(tab);
	}
	/**
	 * 목록으로 이동
	 */
	function projectList(){
		document.location.href = cUrl("/lec/project/mainProject")+"?crsCreCd="+ItemDTO.crsCreCd;
	}

	/**
	 * 팀프로젝트 관리 수정
	 */
	function editPrj() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var f = document.projectForm;

		var scoreOpenYn = $('input:radio[id="scoreOpenYn_Y"]:checked').val();

		var prjtStartDttm = chgDate(f["prjtStartDttm"].value);
		var prjtEndDttm = chgDate(f["prjtEndDttm"].value);
		var scoreCfrmDttm = chgDate(f["scoreCfrmDttm"].value);

		if(isEmpty(f["prjtTitle"].value)) {
			alert("과제 제목을 입력해 주세요.");
			f["prjtTitle"].focus();
			return;
		}

		if(isEmpty(f["prjtCts"].value)) {
			alert("프로젝트 내용을 입력해 주세요.");
			f["prjtCts"].focus();
			return;
		}

		if(isEmpty(f["prjtStartDttm"].value)) {
			alert("프로젝트 시작일을 입력해주세요.");
			return;
		}

		if(isEmpty(f["prjtEndDttm"].value)) {
			alert("프로젝트 종료일을 입력해주세요.");
			return;
		}

		if(!dateCheck(prjtStartDttm, prjtEndDttm)) {
			alert("종료일을 시작일 보다 늦은 날짜를 선택하셔야 합니다.");
			return;
		}


		if(scoreOpenYn == 'Y'){
			if(isEmpty(f["scoreCfrmDttm"].value)) {
				alert("성적공개일자를 입력해 주세요.");
				return;
			}

			if(!dateCheck(prjtEndDttm, scoreCfrmDttm)){
				alert("성적공개일자는 종료일 보다 늦은 날짜를 선택하셔야 합니다.");
				return;
			}
		}

		$('#projectForm').acton("/lec/project/editProject");
		$("#projectForm").submit();
	}

	/**
	 * 팀프로젝트 관리 삭제
	 */
	function delPrj() {
		$('#projectForm').acton("/lec/project/removeProject");
		$("#projectForm").submit();
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

	/**
	 * 수정버튼 처리
	 */
	function editProject() {
		$('#viewProject').hide();
		$('#editProject').show();
	}

	/**
	 * 취소버튼 처리
	 */
	function goReset(){
		$('#viewProject').show();
		$('#editProject').hide();
	}

	function resizeTopIframe(dynheight) {
       document.getElementById("subWorkFrame2").height = parseInt(dynheight) + 20;
  	}

</script>
</mhtml:class_body>
</mhtml:class_html>