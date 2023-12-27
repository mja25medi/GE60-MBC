<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
			<div class="row" id="content" style="margin-top: 10px;">
				<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
				<div class="col-md-7 col-sm-7">
					<div class="form-group">
						<select name="areaCd" id="areaCd" class="form-control input-sm" style="max-width:270px;" onChange="listPageing(1);" >
							<option value=""><meditag:message messageKey="user.title.area"/></option>
						<c:forEach items="${areaCode}" var="item">
							<c:set var="codeName" value="${item.codeNm}"/>
							<c:forEach var="lang" items="${item.codeLangList}">
								<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
							</c:forEach>
							<option value="${item.codeCd}">${codeName}</option>
						</c:forEach>
						</select>
						<select name="useYn" id="useYn" class="form-control input-sm" onChange="listPageing(1);">
							<option value=""><meditag:message messageKey="course.open.title.category.useyn"/></option>
							<option value="Y"><meditag:message messageKey="common.title.status.useyn_y"/></option>
							<option value="N"><meditag:message messageKey="common.title.status.useyn_n"/></option>
						</select>
						<select name="searchKey" id="searchKey" class="form-control input-sm">
							<option value="NAME"><meditag:message messageKey="user.title.user.dept.name"/></option>
							<option value="PHONE"><meditag:message messageKey="user.title.userinfo.phoneno"/></option>
							<option value="FAX"><meditag:message messageKey="user.title.userinfo.fax"/></option>
						</select>
						<div class="input-group" style="width:180px;">
							<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
							<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
				</div>

				<div class="col-md-5 col-sm-5 text-right">
					<div class="form-group">
						<a href="javascript:userDeptWrite()" class="btn btn-primary btn-sm"><meditag:message messageKey="user.title.user.dept.write"/> </a>

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
				<br/>
				<div class="col-md-12" style="margin-top:20px;">
					<div id="userDeptList">
						<table summary="<meditag:message messageKey="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
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
									<th scope="col"><meditag:message messageKey="common.title.no"/> </th>
									<th scope="col"><meditag:message messageKey="user.title.user.dept.code"/></th>
									<th scope="col"><meditag:message messageKey="user.title.user.dept.name"/></th>
									<th scope="col"><meditag:message messageKey="user.title.address"/></th>
									<th scope="col"><meditag:message messageKey="user.title.area"/></th>
									<th scope="col"><meditag:message messageKey="user.title.userinfo.phoneno"/></th>
									<th scope="col"><meditag:message messageKey="user.title.userinfo.fax"/></th>
									<th scope="col"><meditag:message messageKey="course.open.title.category.useyn"/></th>
									<th scope="col"><meditag:message messageKey="common.title.edit"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="9"><meditag:message messageKey="common.message.nodata"/></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				</form>
			</div>
			<div style="margin-top:15px;"></div>
<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	var ItemDTO = new Object();

	/**
	 * 초기 화면 구성
	 */
	$(document).ready(function() {
		$("body").css("overflow-x","hidden");
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
				cUrl("/UserDeptInfoManage.do"),
				{ "cmd":"listDeptInfo",
				  "curPage":ItemDTO.curPage,
				  "userDeptInfoDTO.searchKey":searchKey,
				  "userDeptInfoDTO.searchValue":searchValue,
				  "userDeptInfoDTO.typeCd":typeCd,
				  "userDeptInfoDTO.areaCd":areaCd,
				  "userDeptInfoDTO.useYn":useYn,
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
		location.href="/UserDeptInfoManage.do?cmd=addFormUserDept${AMPERSAND}isPop=Y";
	}

	/**
	 * 사용자 수정 폼
	 */
	function userDeptEdit(deptCd) {
		location.href="/UserDeptInfoManage.do?cmd=editFormUserDept${AMPERSAND}userDeptInfoDTO.deptCd="+deptCd+"${AMPERSAND}isPop=Y";
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>