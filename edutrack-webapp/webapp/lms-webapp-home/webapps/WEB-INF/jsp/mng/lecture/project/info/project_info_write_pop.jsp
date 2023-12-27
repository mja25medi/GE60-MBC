<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="projectVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module uploadify="y"/>
</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<br>
	<form id="projectForm" name="projectForm" onsubmit="return false">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
		<input type="hidden" name="prjtSn" value="${vo.prjtSn }" />
		<input type="hidden" name="attachFileSns" value="${vo.attachFileSns }" />
		<table summary="<spring:message code="lecture.title.project.manage"/>" style="width:98%" class="table_dtl" align="center">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top"><spring:message code="lecture.title.project.name"/></th>
				<td class="top" colspan="3">
					<input type="text" style="float:left; width:600px;" maxlength="100" dispName="<spring:message code="lecture.title.project.name"/>" isNull="N" lenCheck="100" name="prjtTitle" value="${vo.prjtTitle }" class="txt" id="projectVO.prjtTitle" />
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.project.duration"/></th>
				<td colspan="3">
					<input type="text" style="width:60px;" maxlength="100" dispName="<spring:message code="lecture.title.project.startdate"/>" isNull="N" lenCheck="100" name="prjtStartDttm" id="prjtStartDttm" value="${vo.prjtStartDttm }" class="txt" readonly="true" />
					~
					<input type="text" style="width:60px;" maxlength="100" dispName="<spring:message code="lecture.title.project.enddate"/>" isNull="N" lenCheck="100" name="prjtEndDttm" id="prjtEndDttm" value="${vo.prjtEndDttm }" class="txt" readonly="true" />
					<meditag:datepicker name1="prjtStartDttm" name2="prjtEndDttm" />
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.project.scoreopenyn"/></th>
				<td colspan="3">
					<c:forEach var="item" items="${scoreOpenYn}" varStatus="status">
						<input type="radio" name="scoreOpenYn" value="${item.codeCd}" id="scoreOpenYn_${item.codeCd}" <c:if test="${vo.scoreOpenYn eq item.codeCd}">checked</c:if>/>
						<label for="scoreOpenYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr id="scoreCfrmDttm">
				<th scope="row"><spring:message code="lecture.title.project.scoreopendate"/></th>
				<td colspan="3">
					<input type="text" style="width:100px;" maxlength="100" dispName="<spring:message code="lecture.title.project.scoreopendate"/>" isNull="Y" lenCheck="100" name="scoreCfrmDttm" value="${vo.scoreCfrmDttm }" class="txt" id="scoreCfrmDttm" readonly="true" />
					<meditag:datepicker name1="scoreCfrmDttm" />
				</td>
			</tr>
			<tr height="70">
				<th scope="row"><spring:message code="lecture.title.project.desc"/></th>
				<td colspan="3">
					<textarea style="width:600px;height:50px" dispName="<spring:message code="lecture.title.project.desc"/>" lenCheck="1000" isNull="N" name="prjtCts" id="prjtCts" >${vo.prjtCts }</textarea>
				</td>
			</tr>
			<tr height="70">
				<th scope="row"><spring:message code="common.title.atachfile"/></th>
				<td colspan="3">
					<div style="flot:left"><input type="file" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%></div>
					<div style="flot:left" id="fileQueue"></div>
					<div style="flot:left" id="fileListview"></div>
				</td>
			</tr>
		</table>
		<div style="padding:10px 5px 5px 0px;float:right">
			<c:if test="${gubun eq 'A' }">
			<meditag:button value="button.add" title="button.add" func="addProject()" />
			</c:if>
			<c:if test="${gubun eq 'E' }">
			<meditag:button value="button.add" title="button.add" func="editProject()" />
			<meditag:button value="button.delete" title="button.delete" func="deleteProject()" />
			</c:if>
			<meditag:button value="button.close" title="button.close" func="parent.projectPopBox.close();" />
		</div>
	</form>
<script type="text/javascript">
	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {

		$('#scoreCfrmDttm').hide();
		$('#scoreOpenYn_Y').click(function(){
			$('#scoreCfrmDttm').show();
		});
		$('#scoreOpenYn_N').click(function(){
			$('#scoreCfrmDttm').hide();
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"maxcount"			: 5,
					"files"				: $.parseJSON('${projectVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"fileDesc"		:	"Document Files (*.hwp, *.doc, *.xls, *.docx, *.xlsx, *.zip, *.ppt, *.pptx)",
							"fileExt"		:	"*.hwp; *.doc; *.xls; *.docx; *.xlsx; *.zip; *.ppt; *.pptx;",
							"scriptData"	:	{
									"repository"	:	"PRJT",
									"type"			:	"file"		}
				}});
	});

	<c:if test="${gubun eq 'A' }">
	/**
	 * 프로젝트 등록
	 */
	function addProject() {
		if(!validate(document.projectForm)) return;
		if(!submitCheck()) return;
		process("add");
	}
	</c:if>

	<c:if test="${gubun eq 'E' }">
	/**
	 * 프로젝트 수정
	 */
	function editProject() {
		if(!validate(document.projectForm)) return;
		if(!submitCheck()) return;
		process("edit");
	}

	/**
	 * 프로젝트 삭제
	 */
	function addProject() {
		if(!confirm("<spring:message code="lecture.message.project.confirm.delete"/>")) return;
		process("add");
	}
	</c:if>

	function submitCheck() {
		var scoreOpenYn = $('input:radio[name="scoreOpenYn"]:checked').val();
		var prjtEndDttm = $("#prjtEndDttm").val();
		var scoreCfrmDttm = $("#scoreCfrmDttm").val();

		if(scoreOpenYn == 'Y'){
			if(isEmpty(scoreCfrmDttm)) {
				alert("<spring:message code="lecture.message.project.alert.input.scoreopendate"/>");
				return false;
			}

			if(!dateCheck(prjtEndDttm, scoreCfrmDttm)){
				alert("<spring:message code="lecture.message.project.alert.validate.scoreopendate"/>");
				return false;
			}
		}
		return true;
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		$('#projectForm').attr("action", "/mng/lecture/project"+cmd);
		$('#projectForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listProject();
			parent.projectPopBox.close();
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

</script>
</mhtml:frm_body>
</mhtml:mng_html>