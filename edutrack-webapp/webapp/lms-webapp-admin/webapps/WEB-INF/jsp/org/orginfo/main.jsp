<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<section class="content">
	<div class="box">
		<div class="box-body">
			<div class="row" id="content">
				<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
				<div class="col-md-6 col-sm-6">
					<select name="searchKey" id="searchKey" class="form-control input-sm">
						<option value="orgNm"><spring:message code="org.title.orginfo.orgname"/></option>
						<option value="domain"><spring:message code="org.title.orginfo.domain"/></option>
					</select>
					<div class="input-group" style="width:160px;">
						<input type="text" name="searchValue" id="searchValue" style="width:100%" class="_enterBind form-control input-sm"/>
						<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 text-right">
					<button class="btn btn-primary btn-sm" onclick="orgWrite()"><spring:message code="button.write"/></button>
					<select name="listScale" id="listScale" onChange="listPageing(1);" class="form-control input-sm">
						<option value="10">10</option>
						<option value="20" selected="selected">20</option>
						<option value="40">40</option>
						<option value="60">60</option>
						<option value="80">80</option>
						<option value="100">100</option>
						<option value="200">200</option>
					</select>
				</div>
				</form>
				<div class="col-md-12">
					<div id="orgList" style="margin-top:5px;">
						<table summary="<spring:message code="org.title.orginfo.manage"/>" class="table table-bordered">
							<colgroup>
								<col style="width:30px;"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:80px;"/>
								<col style="width:80px;"/>
								<col style="width:50px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">No</th>
									<th scope="col"><spring:message code="org.title.orginfo.orgname"/></th>
									<th scope="col"><spring:message code="org.title.orginfo.domain"/></th>
									<th scope="col"><spring:message code="org.title.orginfo.startdate"/></th>
									<th scope="col"><spring:message code="org.title.orginfo.enddate"/></th>
									<th scope="col"><spring:message code="common.title.useyn"/></th>
									<th scope="col"><spring:message code="common.title.edit"/></th>
									<th scope="col"><spring:message code="org.title.orginfo.homepage.manage"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="8"><spring:message code="org.message.orginfo.nodata"/></td>
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
	var ItemDTO = new Object();
	var modalBox = null;
	/**
	 * 초기 화면 구성
	 */
	$(document).ready(function() {
		ItemDTO.sortKey = "ORG_NAME_ASC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault(); //-- 엔터키 클릭시 form의 submit 막음.
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
		ItemDTO.curPage = page;
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();
		$('#orgList').load(
				cUrl("/adm/org/orginfo/list"),
				{ "cmd":"list",
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "sortKey":ItemDTO.sortKey,
				  "pageIndex":page,
				  "listScale":listScale
				}
			);
	}

	/**
	 * 기관 등록 폼
	 */
	function orgWrite() {
		var addContent = "<iframe id='addOrgFrame' name='addOrgFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/adm/org/orginfo/addFormPop"/>'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="org.title.orginfo.write"/>");
		modalBox.show();
	}

	/**
	 * 기관 수정 폼
	 */
	function orgEdit(orgCd) {
		var addContent = "<iframe id='addOrgFrame' name='addOrgFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/adm/org/orginfo/editFormPop"/>"
			+ "?orgCd="+orgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="org.title.orginfo.edit"/>");
		modalBox.show();
	}

	/**
	 * 기관 이미지 관리 폼
	 */
	function orgManage(orgCd) {
		var curPage = ItemDTO.curPage;
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();

		var url = cUrl("/adm/org/orginfo/editInfoFormMain")+"?orgCd="+orgCd + "&pageIndex="+curPage+"&searchKey="+searchKey+"&searchValue="+searchValue+"&listScale="+listScale;
		document.location.href = url;
	}

	/**
	 * 기관 메뉴 관리 폼
	 */
	function orgMenu(orgCd) {
		var curPage = ItemDTO.curPage;
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();

		var url = cUrl("/adm/org/menu/main")+"?orgCd="+orgCd + "&pageIndex="+curPage+"&searchKey="+searchKey+"&searchValue="+searchValue+"&listScale="+listScale;
		document.location.href = url;

		//var addContent = "<iframe id='addOrgMenuFrame' name='addOrgMenuFrame' width='100%' height='100%' "
		//	+ "frameborder='0' scrolling='auto' src='<c:url value="/OrgMenuAdmin.do"/>"
		//	+ "?cmd=main&orgMenuDTO.orgCd="+orgCd+"'/>";
		//modalBox.clear();
		//modalBox.addContents(addContent);
		//modalBox.resize(1000, 530);
		//modalBox.setTitle("<spring:message code="system.title.menu.manage"/>");
		//modalBox.show();
	}

	/**
	 * 기관 도메인 관리 폼
	 */
	function orgDomain(orgCd) {
		var addContent = "<iframe id='addOrgDomainFrame' name='addOrgDomainFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/adm/org/domain/mainPop"/>"
			+ "?orgCd="+orgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 530);
		modalBox.setTitle("<spring:message code="system.title.menu.manage"/>");
		modalBox.show();
	}

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listPageing(1);
	}
</script>
