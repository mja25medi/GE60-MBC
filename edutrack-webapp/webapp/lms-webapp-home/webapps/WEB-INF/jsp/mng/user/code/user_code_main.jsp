<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<div class="col-md-12 col-xs-18" style="margin-bottom:10px;">
						<ul class="nav nav-tabs" id="tabMenu">
							<!-- class="active" -->
					  		<li><a href="/mng/user/deptInfo/deptInfoMain"><spring:message code="user.title.user.dept.manage"/></a></li>
					  		<li <c:if test="${codeCtgrCd eq 'USER_DIV_CD' }">class='active'</c:if>><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=USER_DIV_CD"><spring:message code="user.title.user.divcd.manage"/></a></li>
					  		<li <c:if test="${codeCtgrCd eq 'AREA_CD' }">class='active'</c:if>><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=AREA_CD"><spring:message code="user.title.user.areacd.manage"/></a></li>
					  		<li <c:if test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }">class='active'</c:if>><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=INTEREST_FIELD_CD"><spring:message code="user.title.user.interestcd.manage"/></a></li>
					  		<li <c:if test="${codeCtgrCd eq 'CCL_CD' }">class='active'</c:if>><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=CCL_CD"><spring:message code="library.title.contents.ccl"/></a></li>
						</ul>
					</div>
					<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<select name="useYn" id="useYn" class="form-control input-sm" onChange="listPageing(1);">
								<option value=""><spring:message code="course.open.title.category.useyn"/></option>
								<option value="Y"><spring:message code="common.title.status.useyn_y"/></option>
								<option value="N"><spring:message code="common.title.status.useyn_n"/></option>
							</select>
							<select name="searchKey" id="searchKey" class="form-control input-sm">
								<option value="CODE"><spring:message code="system.title.code.code"/></option>
								<option value="NAME"><spring:message code="system.title.code.name"/></option>
							</select>
							<div class="input-group" style="width:180px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>" />
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 text-right">
						<div class="form-group">
							<a href="javascript:userCodeWrite()" class="btn btn-primary btn-sm">
								<c:choose>
									<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }"><spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="button.write.code"/></c:when>
									<c:when test="${codeCtgrCd eq 'AREA_CD' }"><spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="button.write.code"/></c:when>
									<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }"><spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="button.write.code"/></c:when>
									<c:when test="${codeCtgrCd eq 'CCL_CD' }"><spring:message code="library.title.contents.ccl"/>&nbsp;<spring:message code="button.write.code"/></c:when>
									<c:otherwise><spring:message code="utton.write.code"/></c:otherwise>
								</c:choose>
							</a>
							<a href="javascript:userCodeExcelWrite()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
							<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
						</div>
					</div>
					<div class="col-md-12" style="margin-top:5px;">
						<div id="userCodeList">
							<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:60px"/>
									<col style="width:200px"/>
									<col style="width:auto"/>
									<col style="width:150px"/>
									<col style="width:50px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><spring:message code="common.title.no"/> </th>
										<th scope='col'>
										<c:choose>
											<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }"><spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
											<c:when test="${codeCtgrCd eq 'AREA_CD' }"><spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
											<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }"><spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
											<c:when test="${codeCtgrCd eq 'CCL_CD' }"><spring:message code="library.title.contents.ccl"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
										</c:choose>
										</th>
										<th scope='col'>
										<c:choose>
											<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }"><spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="system.title.code.name"/></c:when>
											<c:when test="${codeCtgrCd eq 'AREA_CD' }"><spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="system.title.code.name"/></c:when>
											<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }"><spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="system.title.code.name"/></c:when>
											<c:when test="${codeCtgrCd eq 'CCL_CD' }"><spring:message code="library.title.contents.ccl"/>&nbsp;<spring:message code="system.title.code.name"/></c:when>
										</c:choose>
										</th>
										<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
										<th scope="col"><spring:message code="common.title.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="5"><spring:message code="common.message.nodata"/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					</form>
				</div>
				<div style="margin-top:15px;"></div>
			</div>
		</div>
	</div>
</section>				
<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	var ItemDTO = new Object();

	/**
	 * 초기 화면 구성
	 */
	$(document).ready(function() {
		ItemDTO.sortKey = "USER_ID_ASC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listPageing(1);
			}
		}
		listPageing(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 *  페이징처리
	 */
	function listPageing(page) {
		if(page > 0)
			ItemDTO.curPage = page;
		var searchKey 	= $("#searchKey").val();
		var searchValue = $('#searchValue').val();
		var listScale 	= $("#listScale").val();
		var useYn		= $('#useYn').val();

		$('#userCodeList')
			.load(
				cUrl("/mng/user/codeInfo/listCodeInfo"),
				{ 
				  "curPage":ItemDTO.curPage,
				  "codeCtgrCd":"${codeCtgrCd}",
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "useYn":useYn,
				  "listScale":listScale
				},
				function () {
					//alert('callback')
					//closeWorkProgress();
				}
			);

	}

	/**
	 * 사용자 정보 코드 등록 폼
	 */
	function userCodeWrite() {
		var modalTitle = "";
		<c:choose>
			<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }">modalTitle="<spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="system.title.code.write"/>";</c:when>
			<c:when test="${codeCtgrCd eq 'AREA_CD' }">modalTitle="<spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="system.title.code.write"/>";</c:when>
			<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }">modalTitle="<spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="system.title.code.write"/>";</c:when>
			<c:otherwise>modalTitle="<spring:message code="user.title.code.write"/>";</c:otherwise>
		</c:choose>
		var addContent  = "<iframe id='addCodeFrame' name='addCodeFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/codeInfo/addCodePop"/>"
			+ "?codeCtgrCd=${codeCtgrCd}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 350);
		modalBox.setTitle(modalTitle);
		modalBox.show();
	}

	/**
	 * 사용자 정보 코드 수정 폼
	 */
	function userCodeEdit(codeCd) {
		var modalTitle = "";
		<c:choose>
			<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }">modalTitle="<spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="system.title.code.edit"/>";</c:when>
			<c:when test="${codeCtgrCd eq 'AREA_CD' }">modalTitle="<spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="system.title.code.edit"/>";</c:when>
			<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }">modalTitle="<spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="system.title.code.edit"/>";</c:when>
			<c:otherwise>modalTitle="<spring:message code="user.title.code.edit"/>";</c:otherwise>
		</c:choose>
		var addContent  = "<iframe id='addCodeFrame' name='addCodeFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/codeInfo/editCodePop"/>"
			+ "?codeCtgrCd=${codeCtgrCd}&amp;useYn=ALL&amp;codeCd="+codeCd+"'/>";

		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 350);
		modalBox.setTitle(modalTitle);
		modalBox.show();
	}

	/**
	 * 사용자 정보 코드 엑셀 등록 폼
	 */
	function userCodeExcelWrite() {
		var modalTitle = "";
		<c:choose>
			<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }">modalTitle="<spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="button.write.code"/>";</c:when>
			<c:when test="${codeCtgrCd eq 'AREA_CD' }">modalTitle="<spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="button.write.code"/>";</c:when>
			<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }">modalTitle="<spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="button.write.code"/>";</c:when>
			<c:otherwise>modalTitle="<spring:message code="user.title.code.edit"/>";</c:otherwise>
		</c:choose>
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/codeInfo/addCodeExcelPop"/>"
			+ "?codeCtgrCd=${codeCtgrCd}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle(modalTitle);
		modalBox.show();
	}

</script>
