<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<form onsubmit="return false" id="emailTplForm" name="emailTplForm" method="post">
<input type="hidden" name="orgCd" value="${vo.orgCd}"/>
<input type="hidden" name="tplOdr" value="${vo.tplOdr}"/>
<input type="hidden" name="attachImageSns" id="attachImageSns" value="${vo.attachImageSns}"/>
<input type="hidden" name="editorYn" value="Y" />
<input type="hidden" name="autoMakeYn" value="Y"/>
<input type="hidden" name="tplType" value="${vo.tplType}"/>
	<table summary="data" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:14%;"/>
			<col style="width:56%;"/>
			<col style="width:12%;"/>
			<col style="width:20%;"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="org.title.emailtpl.code"/></th>
			<td>
				<c:if test="${gubun eq 'A'}">
					<div class="input-group">
	       				<div style="float:left">
							<input type="text" style="width:100px" dispName="<spring:message code="org.title.emailtpl.code"/>" maxlength="10" isNull="N" onkeyup="isChkCharacter(this)" lenCheck="10" name="tplCd" class="form-control input-sm" id="tplCd"/>
							<span id="codeInfo" style="float:left;margin-left:10px;line-height:30px;display: none;">&nbsp;<spring:message code="common.message.code.warning.info"/></span>
						</div>
						<div style="float:left;margin-left:10px;">
							<label style="font-weight:normal;" ><input type="checkbox" id="autoMakeYnY" onclick="autoMakeCd()" style="border:0" checked/><spring:message code="common.title.automake"/></label>
						</div>
	       			</div>
	       		</c:if>
	       		<c:if test="${gubun eq 'E'}">
		       		<input type="hidden" name="tplCd" id="tplCd" value="${vo.tplCd}"/>
		       		${vo.tplCd}
	       		</c:if>
			</td>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight:normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${empty vo.useYn || vo.useYn eq 'Y'}">checked="checked"</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight:normal;margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked="checked"</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="tplNm"><spring:message code="org.title.emailtpl.name"/></label></th>
			<td colspan="3"><input type="text" maxlength="50" dispName="<spring:message code="org.title.emailtpl.name"/>" isNull="N" lenCheck="100" name="tplNm" id="tplNm" class="form-control input-sm" value="<c:out value="${vo.tplNm}"/>"/></td>
		</tr>
		<tr>
			<th scope="row"><label for="tplDesc"><spring:message code="org.title.emailtpl.title"/></label></th>
			<td colspan="3"><input type="text" maxlength="1000" dispName="<spring:message code="org.title.emailtpl.title"/>" isNull="N" lenCheck="1000" name="tplDesc" id="tplDesc" class="form-control input-sm" value="<c:out value="${vo.tplDesc}"/>"/></td>
		</tr>
		<tr>
			<td colspan="4" style="padding:0px;">
				<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.tplCts}</div>
				<textarea name="tplCts" id="contentTextArea"  class="sr-only">${vo.tplCts}</textarea>
			</th>
		</tr>
		<tr>
			<td colspan="4">
				<div class="input-group-btn btn-group dropup">
    				<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
    					<spring:message code="org.title.emailtpl.view.example"/><span class="caret"></span>
  					</button>
  					<ul class="dropdown-menu" role="menu"  style="max-height:300px;overflow:auto;">
						<li style="padding:7px;">
							<table class="table table-bordered wordbreak">
								<!-- 공공활용 개설신청 추가 2017-01-18 arothy -->
								<c:choose>
								<c:when test="${vo.tplCd eq 'TPL000007' and USER_ORGCD eq mgrSiteCd }">
									<tr>
										<th><spring:message code="org.title.orginfo.domain"/></th>
										<td>[$Hostdomain]</td>
									</tr>
									<tr>
										<th><spring:message code="org.title.cre.aplc.certsn"/></th>
										<td>[$CreAplcSn]</td>
									</tr>
									<tr>
										<th><spring:message code="org.title.cre.aplc.certkey"/></th>
										<td>[$CertKey]</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:choose>
									<c:when test="${vo.tplCd eq 'EM006' || vo.tplCd eq 'EM007' || vo.tplCd eq 'EM008'}">
										<tr>
											<th><spring:message code="org.title.emailtpl.username"/></th>
											<td>[$Name]</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:if test="${vo.tplCd eq 'EM000'}">
										<tr>
											<th><spring:message code="org.title.emailtpl.title"/></th>
											<td>[$Title]</td>
										</tr>
										<tr>
											<th><spring:message code="org.title.emailtpl.contents"/></th>
											<td>[$Contents]</td>
										</tr>
										</c:if>
										<c:if test="${vo.tplCd ne 'EM000'}">
										<tr>
											<th><spring:message code="org.title.emailtpl.username"/></th>
											<td>[$Name]</td>
										</tr>
										<tr>
											<th><spring:message code="org.title.emailtpl.userid"/></th>
											<td>[$UserID]</td>
										</tr>
										</c:if>
										<c:if test="${vo.tplCd eq 'EM001' || vo.tplCd eq 'EM002'}">
										<tr>
											<th><spring:message code="org.title.emailtpl.password"/></th>
											<td>[$Password]</td>
										</tr>
										</c:if>
									</c:otherwise>									
									</c:choose>
								</c:otherwise>
								</c:choose>
								<tr>
									<th><spring:message code="org.title.emailtpl.senddate"/></th>
									<td>[$SendDate]</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-top:10px;">
        <c:if test="${gubun eq 'A' }">
		<button class="btn btn-primary btn-sm" onclick="addTpl()" ><spring:message code="button.add"/></button>
        </c:if>
        <c:if test="${gubun eq 'E' }">
		<button class="btn btn-primary btn-sm" onclick="editTpl()" ><spring:message code="button.add"/></button>
		<button class="btn btn-warning btn-sm" onclick="delTpl()" ><spring:message code="button.delete"/></button>
        </c:if>
        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
    </div>
</form>
<script type="text/javascript">
	// 페이지 초기화
	var summernote;
	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"EMAIL_TPL",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"330px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
		});
		autoMakeCd();
	});

	function autoMakeCd() {
		<c:if test="${gubun eq 'A'}">
		if($("#autoMakeYnY").is(":checked")) {
			$("#emailTplForm").find("[name='tplCd']").val("<spring:message code="common.title.automake"/>");
			$("#emailTplForm").find("[name='tplCd']").css("background-color","#f3f3f3");
			$("#emailTplForm").find("[name='tplCd']").attr("readonly",true);
			$("#emailTplForm").find("[name='autoMakeYn']").val("Y");
		} else {
			$("#emailTplForm").find("[name='tplCd']").val("");
			$("#emailTplForm").find("[name='tplCd']").css("background-color","#ffffff");
			$("#emailTplForm").find("[name='tplCd']").attr("readonly",false);
			$("#emailTplForm").find("[name='autoMakeYn']").val("N");
		}
		</c:if>
	}
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#emailTplForm').attr("action", "/mng/org/emailTpl/" + cmd);
		if(!validate(document.emailTplForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)) {
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);
		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$('#emailTplForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(result) {
		alert(result.message);
		if(result.result == 1) {
			// 정상 처리
			parent.listEmailTpl();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 페이지 추가
	 */
	function addTpl() {
		process("add");
	}

	/**
	 * 페이지 수정
	 */
	function editTpl() {
		process("edit");
	}

	/**
	 * 페이지 삭제
	 */
	function delTpl() {
		if(confirm('<spring:message code="system.message.page.confirm.delete"/>')) {
			process("remove"); // cmd
		} else {
			return;
		}
	}	
</script>
