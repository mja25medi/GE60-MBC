<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<section class="content">
		<div class="row" id="content">
			<div class="box">
				<div class="box-body">
					<form name="fileRepoForm" id="fileRepoForm" onsubmit="return false;" class="form-inline">
						<div class="col-md-6 col-sm-6">
							<div class="input-group" style="width:160px;">
								<input type="text" name="repoNm" id="repoNm" maxlength="30" title="<spring:message code="common.message.input.search"/>" style="width:100%; float:left;" class="form-control input-sm"/> 
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<div class="input-group-btn text-right">
								<button class="btn btn-primary btn-sm" onclick="addRepo()"><spring:message code="button.write.repository"/></button>
							</div>
						</div>
					</form>
					<div class="col-md-12">
						<div id="repoListArea" style="margin-top:5px;">
							<table summary="<spring:message code="system.title.file.manage.repository"/>" class="table table-bordered">
								<colgroup>
									<col width="auto">
									<col width="120px">
									<col width="110px">
									<col width="180px">
									<col width="230px">
									<col width="100px">
									<col width="60px">
								</colgroup>
								<thead>
									<tr>
										<th scope='col'><spring:message code="system.title.file.name.repository"/></th>
										<th scope='col'><spring:message code="system.title.file.code.repository"/></th>
										<th scope='col'><spring:message code="system.title.file.filecnt"/></th>
										<th scope='col'><spring:message code="system.title.file.path.repository"/></th>
										<th scope='col'><spring:message code="system.title.file.tablenm.repository"/></th>
										<th scope='col'><spring:message code="common.title.manage"/></th>
										<th scope='col'><spring:message code="common.title.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="7"><spring:message code="system.message.file.norepository"/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

<script type="text/javascript">
	var modalBox = null;
	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		// 검색 text컨트롤 Enter Binding
		$("#repoNm").bind("keydown", function(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listPageing(1);
			}
		});
		listPageing(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	// 페이지 전역 변수 객체.
	var RepositoryDTO = {
		fileRepoCd : "",
		fileRepoNm : ""
	};

	/**
	 * 시스템파일 카테고리 목록 조회
	 */
	function listPageing(page) {
		$("#repoListArea")
			.load(cUrl("/adm/system/file/listRepo"), {"pageIndex" : page,	"repoNm" : $("#repoNm").val()});
	}

	/**
	 * 파일 분류 등록 폼
	 */
	function addRepo() {
		var addContent  = "<iframe id='addRepositoryFrame' name='addRepositoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/file/addFormRepoPop"/>'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 480);
		modalBox.setTitle("<spring:message code="system.title.file.write.repository"/>");
		modalBox.show();
	}

	/**
	 * 파일 분류 수정 폼
	 */
	function editRepo(repoCd) {
		var addContent = "<iframe id='addRepositoryFrame' name='addRepositoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/file/editFormRepoPop"/>"
			+ "?repoCd="+repoCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 480);
		modalBox.setTitle("<spring:message code="system.title.file.edit.repository"/>");
		modalBox.show();
	}

</script>


