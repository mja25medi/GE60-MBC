<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
				<section class="content">
					<div class="row" id="content">
						<div class="box">
							<div class="box-body">
								<div class="col-lg-12">
									<form name="qnaNonMemAsnrForm" id="qnaNonMemAsnrForm" method="POST">
										<input type="hidden" name="qnaSn" id="qnaSn" value="${vo.qnaSn }"/>
										<table summary="<spring:message code="board.title.bbs.atcl.cnts"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:15%" />
												<col style="width:75%" />
											</colgroup>
											<tr>
												<th scope="row"><label for="atclTitle"><spring:message code="common.title.title"/></label></th>
												<td>
													<input type="text" name="ansrTitle" id="ansrTitle" maxlength="100" required="required" title="<spring:message code="common.title.title"/>" class="form-control input-sm" value="<c:out value="[답변] 질문번호 : ${vo.qnaSn }"/>" readonly />
												</td>
											</tr>
											<tr>
												<c:set var="fontFamily" value="Helvetica"/>
												<c:if test="${LOCALEKEY eq 'jp' }"><c:set var="fontFamily" value="Meiryo"/></c:if>
												<c:if test="${LOCALEKEY eq 'ko' }"><c:set var="fontFamily" value="맑은 고딕"/></c:if>
												<td colspan="2" style="padding:0px;font-family:${fontFamily}">
													<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.ansCts}</div>
													<textarea name="ansCts" id="contentTextArea" title="<spring:message code="board.title.bbs.atcl.cnts"/>" class="sr-only"></textarea>
												</td>
											</tr>
										</table>
										<div class="text-right" style="margin-bottom:20px;">
											<c:if test="${gubun eq 'A'}">
											<a href="javascript:writeAnsr()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
											</c:if>
											<c:if test="${gubun eq 'E'}">
											<a href="javascript:editAnsr()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
											</c:if>
											<a href="javascript:listQstn()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
<script type="text/javascript">
	var summernote;
	var data = "";

	// 페이지 초기화
	$(document).ready(function() {
		ItemVO.orgCd = "${USER_ORGCD}";
		
		summernote = new $M.SummerNote({
							"editorId"			:	"summernote",
							"textareaId"		:	"contentTextArea",
							"repositoryCode"	:	"QNA_ANSR",
							'organization' 		: 	"${USER_ORGCD}",
							"editorHeight"		:	"400px",
						});
	});

	function uploderclick(str) {
		$("#"+str).click();

	}

	/* 서브밋 처리 */
	function process(cmd) {
		$('#qnaNonMemAsnrForm').attr("action","/mng/brd/qna/" + cmd);
		if(!validate(document.qnaNonMemAsnrForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>");
			return;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		$('#qnaNonMemAsnrForm').ajaxSubmit(processCallback);
	}
	
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result == 1) {
			document.location.href = "/mng/brd/qna/nonMemMain";
		} 
	}


	/* 글 저장 */
	function writeAnsr() {
		process("writeNonMemAnsr");
	}

	/* 글 수정 */
	function editAnsr() {
		process("editNonMemAnsr");
	}

	/* 글목록 이동 */
	function listQstn() {
		var url = generateUrl("/mng/brd/qna/nonMemMain");
		document.location.href = url;
	}

</script>
