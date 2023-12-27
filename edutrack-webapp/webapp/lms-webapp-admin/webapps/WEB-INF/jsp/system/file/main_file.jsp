<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<head>
	<style type="text/css">
	.resize100x40 {
		width:  expression((this.width >50)?((this.height>40)?((this.width<this.height)?true:50):50):true);
		height: expression((this.height>40)?((this.width >50)?((this.width>this.height)?true:40):40):true);
		max-width:50px; max-height:40px;}
	</style>
</head>
<body>

			<c:set var="repoNm" value="${repoVo.repoNm}"/>
			<c:forEach var="lang" items="${repoVo.fileRepoLangList}">
				<c:if test="${lang.langCd eq LOCALEKEY}">
					<c:set var="repoNm" value="${lang.repoNm}"/>
				</c:if>
			</c:forEach>
			<mhtml:title title="${MENUNAME} : ${repoNm}" location="${MENUPATH} &gt ${repoNm}"/>
			<section class="content">
				<div class="row" id="content">
					<div class="box">
						<div class="box-body">
							<div class="col-md-8 col-sm-8">
								<form name="fileForm" id="searchForm" onsubmit="return false;" role="form" class="form-inline">
								<select name="usingCnt" id="inputUsingCnt" class="bindSearch form-control input-sm">
									<option value=""><spring:message code="common.title.all"/></option>
									<option value="0"><spring:message code="common.title.nouse"/></option>
									<option value="1"><spring:message code="common.title.use"/></option>
								</select>
								<input type="text" name="fileNm" class="bindSearch form-control input-sm" id="inputFileName" placeholder="<spring:message code="common.title.filename"/>" style="width:120px;"/>
								<div style="width:120px;" class="input-group">
									<input type="text" name="fileNm" class="bindSearch form-control input-sm" id="inputFileExt" placeholder="<spring:message code="common.title.fileext"/>"/>
									<span class="input-group-addon" id="btnlistFile" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>
								<input type="submit" value="submit" style="display:none" />
								</form>
							</div>
							<div class="col-md-4 col-sm-4">
								<div class="text-right">
									<c:url var="hrefUrl" value="/adm/system/file/repoMain"/>
									<a class="btn btn-default btn-sm" href="${hrefUrl}"><spring:message code="button.list.repository"/></a>
								</div>
							</div>
							<div class="col-md-12">
								<!-- 목록 컨트롤 -->
								<div id="fileListArea" style="margin-top:5px;">
									<table summary="<spring:message code="system.title.file.list"/>" class="table table-bordered">
										<colgroup>
											<col width="50px">
											<col width="100px">
											<col width="auto">
											<col width="60px">
											<col width="60px">
											<col width="80px">
											<col width="160px">
											<col width="100px">
										</colgroup>
										<thead>
											<tr>
												<th scope='col' class="top"><spring:message code="common.title.no"/></th>
												<th scope='col' class="top"><spring:message code="system.title.file.thumb"/></th>
												<th scope='col' class="top"><spring:message code="common.title.file"/></th>
												<th scope='col' class="top"><spring:message code="common.title.useyn"/></th>
												<th scope='col' class="top"><spring:message code="common.title.hits"/></th>
												<th scope='col' class="top"><spring:message code="system.title.file.filetype"/></th>
												<th scope='col' class="top"><spring:message code="system.title.file.lastview.dttm"/></th>
												<th scope='col' class="top"><spring:message code="common.title.manage"/></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="8"><spring:message code="system.message.file.nofile"/></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div id="divAttachFile" style="display: none; width: 400px; height: 200px">
									<input type="file" name="uploadify" id="uploadify" title="첨부파일"/><%-- 첨부파일 버튼 --%>
									<div id="fileQueue"></div>
								</div>
			
							</div>
						</div>
					</div>
				</div>
			</section>

<script type="text/javascript">
	var modalBox = null;

	var atchFiles;


	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		// 검색 text컨트롤 Enter Binding
		$("#btnlistFile").bind("click keydown", function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();
				listFile(1);
			}
		});

		$("#btnResetForm").bind("click keydown", function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();
				$('#searchForm').clearForm();
				listFile(1);
			}
		});

		$(".bindSearch").bind("keydown", function(event) {
			if($M.Check.Event.isEnter(event)) {
				event.preventDefault();
				listFile(1);
			}
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"uploadSetting"	: {
							"queueID"		: "fileQueue",
							"scriptData"	:	{
							"repository"	:	"${vo.repoCd}",
							"type"			:	"manage"		},
							"onComplete"	: function(event, queueID, fileObj, response, data) {
								listFile();
								$("div").trigger("click.nyroModal");
							}
				}});

		listFile();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	// 페이지 전역 변수 객체.
	var fileForm = {
		curPage : 1
	};

	/**
	 * 시스템파일 카테고리 목록 조회
	 */
	function listFile(curPage) {
		$("#fileListArea")
			.load(
				cUrl("/adm/system/file/listFile"),
				{
					"repoCd"     : "${vo.repoCd}",
					"fileNm"     : $('#inputFileName').val(),
					"fileExt"    : $('#inputFileExt').val(),
					"usingCnt"   : $('#inputUsingCnt').val(),
					"pageIndex"  : curPage
				}
			);
	}

	function viewFile(fileSn) {
		// 팝업을 이용해서 파일의 상세 정보를 보여주기..
		var url = Context.ROOT + "/adm/system/file/viewFilePop?fileSn=" + fileSn;
		modalBox.clear();
		modalBox.addContents(popupContent("viewFileFrame", url));
		modalBox.resize(700, 400);
		modalBox.setTitle("<spring:message code="system.title.file.view.file"/>");
		modalBox.show();
	}

	function deleteFile(fileSn, usingCnt) {
		// 미사용이 아닐 경우..
		if(usingCnt != 0) {
			if(!confirm('<spring:message code="system.message.file.confirm.delete"/>')) return;
		}

		$.getJSON(
				Context.FILE_DELETE + fileSn, {},
				function(result) {
					if(result.result != 'success') {
						alert(result.message);
					} else {
						listFile();
					}
				}
			);
	}

	function popupContent(name, url) {
		return "<iframe id='"+name+"' name='"+name+"' width='100%' height='100%' frameborder='0' scrolling='yes' src='"+url+"'/>";
	}

</script>
</body>

