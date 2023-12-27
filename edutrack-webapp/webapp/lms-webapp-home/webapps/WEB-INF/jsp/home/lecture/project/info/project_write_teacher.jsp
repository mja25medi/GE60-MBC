<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="projectVO" value="${vo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module uploadify="y"/>
	<meditag:js src="/js/selectbox.js"/>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />
		<form id="projectForm" name="projectForm" onsubmit="return false">
			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
			<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
			<table class="board_b">
				<caption><spring:message code="lecture.title.project.manage"/></caption>
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<th scope="row" class="top"><spring:message code="lecture.title.project.name"/></th>
					<td class="top" colspan="3">
						<input type="text" style="float:left; width:500px;" maxlength="100" dispName="<spring:message code="lecture.title.project.name"/>" isNull="N" lenCheck="100" name="prjtTitle" value="${vo.prjtTitle}" class="text" />
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="lecture.title.project.duration"/></th>
					<td colspan="3">
						<input type="text" style="width:70px;" maxlength="100" dispName="<spring:message code="lecture.title.project.startdate"/>" isNull="N" lenCheck="100" name="prjtStartDttm" value="${vo.prjtStartDttm}" id="prjtStartDttm" class="text" readonly="true" />
						~
						<input type="text" style="width:70px;" maxlength="100" dispName="<spring:message code="lecture.title.project.enddate"/>" isNull="N" lenCheck="100" name="prjtEndDttm" value="${vo.prjtEndDttm}" id="prjtEndDttm" class="text" readonly="true" />
						<meditag:datepicker name1="prjtStartDttm" name2="prjtEndDttm" />
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="lecture.title.project.scoreopenyn"/></th>
					<td colspan="3">
						<c:forEach var="item" items="${scoreOpenYn}" varStatus="status">
							<input type="radio" name="scoreOpenYn" value="${item.codeCd}" id="scoreOpenYn_${item.codeCd}" onclick="scoreDttm('${item.codeCd}');"  <c:if test="${vo.scoreOpenYn eq item.codeCd}">checked</c:if>/>
							<label for="scoreOpenYn_${item.codeCd}">${item.codeNm}</label>
							<c:choose>
								<c:when test="${status.last}"></c:when>
								<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
				</tr>
				<tr id="scoreDttm" style="display: none;">
					<th scope="row"><spring:message code="lecture.title.project.scoreopendate"/></th>
					<td colspan="3">
						<input type="text" style="width:70px;" maxlength="100" dispName="<spring:message code="lecture.title.project.scoreopendate"/>" isNull="N" lenCheck="100" name="scoreCfrmDttm" value="${vo.scoreCfrmDttm}" id="scoreCfrmDttm" class="text" readonly="true" />
						<meditag:datepicker name1="scoreCfrmDttm" />
					</td>
				</tr>
				<tr height="70">
					<th scope="row"><spring:message code="lecture.title.project.desc"/></th>
					<td colspan="3">
						<textarea style="width:500px;height:50px" dispName="<spring:message code="lecture.title.project.desc"/>" lenCheck="1000" isNull="N" name="prjtCts">${vo.prjtCts}</textarea>
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="common.title.atachfile"/></th>
					<td colspan="3">
						<div style="flot:left"><input type="file" title="<spring:message code="common.title.atachfile"/>" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%></div>
						<div style="flot:left" id="fileQueue"></div>
						<div style="flot:left" id="fileListview"></div>
					</td>
				</tr>
			</table>
			<div class="btn_right">
				<a href="javascript:addProject()" class="btn02"><spring:message code="button.add"/></a>
				<a href="javascript:goList()" class="btn01"><spring:message code="button.list"/></a>
			</div>
		</form>
	</div>

<script type="text/javascript">

	var atchFiles; // 첨부파일 목록
	scoreDttm('N');

	$(document).ready(function() {


		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"files"				: $.parseJSON('${projectVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"scriptData"	:	{
							"repository"	:	"PRJT",
							"type"			:	"file"		}
		}});

	});

	function scoreDttm(codeCd){
		if(codeCd == 'Y'){
			$('#scoreDttm').show();
		}else if(codeCd == 'N'){
			$('#scoreDttm').hide();
		}
	}


	/**
	 * 프로젝트 등록
	 */
	function addProject() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var f = document.projectForm;

		var scoreOpenYn = $('input:radio[id="scoreOpenYn_Y"]:checked').val();

		var prjtStartDttm = chgDate(f["prjtStartDttm"].value);
		var prjtEndDttm = chgDate(f["prjtEndDttm"].value);
		var scoreCfrmDttm = chgDate(f["scoreCfrmDttm"].value);

		if(isEmpty(f["prjtTitle"].value)) {
			alert("<spring:message code="lecture.message.project.alert.input.name"/>");
			f["prjtTitle"].focus();
			return;
		}

		if(isEmpty(f["prjtCts"].value)) {
			alert("<spring:message code="lecture.message.project.alert.input.desc"/>");
			f["prjtCts"].focus();
			return;
		}

		if(isEmpty(f["prjtStartDttm"].value)) {
			alert("<spring:message code="lecture.message.project.alert.input.startdate"/>");
			return;
		}

		if(isEmpty(f["prjtEndDttm"].value)) {
			alert("<spring:message code="lecture.message.project.alert.input.enddate"/>");
			return;
		}

		if(scoreOpenYn == 'Y'){
			if(isEmpty(f["scoreCfrmDttm"].value)) {
				alert("<spring:message code="lecture.message.project.alert.input.scoreopendate"/>");
				return;
			}

			if(!dateCheck(prjtEndDttm, scoreCfrmDttm)){
				alert("<spring:message code="lecture.message.project.alert.validate.scoreopendate"/>");
				return;
			}
		}

		process("addProject");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#projectForm').attr("action","/lec/project/"+cmd);
		document.projectForm.submit();
	}

	//목록으로 이동
	function goList() {
		document.location.href = cUrl("/lec/project/mainProject")+"?crsCreCd=${projectVO.crsCreCd}";
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

</script>
</mhtml:class_body>
</mhtml:class_html>