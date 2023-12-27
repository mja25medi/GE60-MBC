<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<div class="col-md-12 col-xs-18" style="margin-bottom:10px;">
						<ul class="nav nav-tabs" id="tabMenu">
					  		<li class="active"><a href="/mng/user/deptInfo/deptInfoMain"><spring:message code="user.title.user.dept.manage"/></a></li>
					  		<li><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=USER_DIV_CD"><spring:message code="user.title.user.divcd.manage"/></a></li>
					  		<li><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=AREA_CD"><spring:message code="user.title.user.areacd.manage"/></a></li>
					  		<li><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=INTEREST_FIELD_CD"><spring:message code="user.title.user.interestcd.manage"/></a></li>
					  		<li><a href="/mng/user/codeInfo/codeInfoMain?codeCtgrCd=CCL_CD"><spring:message code="library.title.contents.ccl"/></a></li>
						</ul>
					</div>
					<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<select name="areaCd" id="areaCd" class="form-control input-sm" style="max-width:270px;" onChange="listPageing(1);" >
								<option value=""><spring:message code="user.title.area"/></option>
							<c:forEach items="${areaCode}" var="item">
								<c:set var="codeName" value="${item.codeNm}"/>
								<c:forEach var="lang" items="${item.codeLangList}">
									<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
								</c:forEach>
								<option value="${item.codeCd}">${codeName}</option>
							</c:forEach>
							</select>
							<%--
							<select name="typeCd" id="typeCd" class="form-control input-sm" style="max-width:270px;" onChange="listPageing(1);" >
								<option value=""><spring:message code="user.title.user.dept.type"/></option>
							<c:forEach items="${deptTypeCode}" var="item">
								<c:set var="codeName" value="${item.codeNm}"/>
								<c:forEach var="lang" items="${item.codeLangList}">
									<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
								</c:forEach>
								<option value="${item.codeCd}">${codeName}</option>
							</c:forEach>
							</select>
							 --%>
	<%-- 						<meditag:codeselectbox category="AREA_CD" fieldName="areaCd" fieldId="areaCd" onChange="listPageing(1);" styleClass="form-control input-sm"/>
							<meditag:codeselectbox category="DEPT_TYPE_CD" fieldName="typeCd" fieldId="typeCd" onChange="listPageing(1);" styleClass="form-control input-sm"/> --%>
							<select name="useYn" id="useYn" class="form-control input-sm" onChange="listPageing(1);">
								<option value=""><spring:message code="course.open.title.category.useyn"/></option>
								<option value="Y"><spring:message code="common.title.status.useyn_y"/></option>
								<option value="N"><spring:message code="common.title.status.useyn_n"/></option>
							</select>
							<select name="searchKey" id="searchKey" class="form-control input-sm">
								<option value="NAME"><spring:message code="user.title.user.dept.name"/></option>
								<option value="PHONE"><spring:message code="user.title.userinfo.phoneno"/></option>
								<option value="FAX"><spring:message code="user.title.userinfo.fax"/></option>
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
							<a href="javascript:userDeptWrite()" class="btn btn-primary btn-sm"><spring:message code="user.title.user.dept.write"/> </a>
							<a href="javascript:userDeptExcelWrite()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
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
						<div id="userDeptList">
							<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:60px"/>
									<col style="width:110px"/>
									<col style="width:130px"/>
									<col style="width:auto"/>
									<col style="width:auto"/>
									<col style="width:100px"/>
									<col style="width:130px"/>
									<col style="width:130px"/>
									<col style="width:60px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><spring:message code="common.title.no"/> </th>
										<th scope="col"><spring:message code="user.title.user.dept.code"/></th>
										<th scope="col"><spring:message code="user.title.user.dept.name"/></th>
										<th scope="col"><spring:message code="user.title.address"/></th>
										<th scope="col"><spring:message code="user.title.area"/></th>
										<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
										<th scope="col"><spring:message code="user.title.userinfo.fax"/></th>
										<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
										<th scope="col"><spring:message code="common.title.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="9"><spring:message code="common.message.nodata"/></td>
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
		var typeCd 		= $("#typeCd").val();
		var areaCd 		= $("#areaCd").val();
		var listScale 	= $("#listScale").val();
		var useYn		= $('#useYn').val();

		$('#userDeptList')
			.load(
				cUrl("/mng/user/deptInfo/listDeptInfo"),
				{ 
				  "curPage":ItemDTO.curPage,
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "typeCd":typeCd,
				  "areaCd":areaCd,
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
	 * 사용자 등록 폼
	 */
	function userDeptWrite() {
		var addContent  = "<iframe id='addUserDeptFrame' name='addUserDeptFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/deptInfo/addUserDeptPop"/>'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("<spring:message code="user.title.user.dept.write"/>");
		modalBox.show();
	}

	/**
	 * 사용자 수정 폼
	 */
	function userDeptEdit(deptCd) {
		var addContent  = "<iframe id='addUserDeptFrame' name='addUserDeptFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/deptInfo/editUserDeptPop"/>?deptCd="+deptCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("<spring:message code="user.title.user.dept.edit"/>");
		modalBox.show();
	}

	/**
	 * 소속관리 엑셀 등록 폼
	 */
	function userDeptExcelWrite() {
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/deptInfo/addUserDeptExcelPop"/>"
			+ "?codeCtgrCd=${codeCtgrCd}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="user.title.user.dept.write"/>");
		modalBox.show();
	}

</script>
