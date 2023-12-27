<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="box">
		<div class="box-body">
			<div class="row" id="content">
				<form name="Search" id="search" onsubmit="return false" method="POST">
				<div class="col-md-6 col-sm-6">
					<div class="input-group" style="float:left;width:30%">
						<select name="orgCd" id="orgCd" class="form-control input-sm">
							<option value=""><spring:message code="org.title.orginfo.all"/></option>
							<c:forEach var="org" items="${orgInfoList}">
							<option value="${org.orgCd}">${org.orgNm}</option>
							</c:forEach>
						</select>
						<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="text-right">
						<button class="btn btn-primary btn-sm" onclick="pageWrite()"><spring:message code="button.write"/></button>
					</div>
				</div>
				</form>
				<div class="col-md-12" style="margin-top:5px;">
					<div id="pageList" style="width:100%">
						<table summary="<spring:message code="system.title.page.manage"/>" class="table table-bordered">
							<colgroup>
								<col style="width:auto;"/>
								<col style="width:110px"/>
								<col style="width:auto"/>
								<col style="width:70px"/>
								<col style="width:50px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="org.title.orginfo.orgname"/></th>
									<th scope='col'><spring:message code="system.title.page.code"/></th>
									<th scope='col'><spring:message code="system.title.page.name"/></th>
									<th scope='col'><spring:message code="common.title.useyn"/></th>
									<th scope='col'><spring:message code="common.title.edit"/></th>
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

		listPageing(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 시스템 페이지 목록 조회
	 */
	function listPageing(page) {
		var orgCd = $("#orgCd option:selected").val();
		$('#pageList')
			.load(
				cUrl("/adm/org/page/list"),
				{ "orgCd":orgCd, "pageIndex":page },
				function(data) {}
			);
	}

	/**
	 * 페이지 등록 폼
	 */
	function pageWrite() {
		var orgCd = $("#orgCd option:selected").val();
		if(orgCd == "") {
			alert("<spring:message code="org.message.orgpage.alert.selectorg"/>");
			return;
		}
		var addContent  = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/org/page/addFormPop"/>"
			+ "?orgCd="+orgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="system.title.page.add"/>");
		modalBox.show();
	}

	/**
	 * 페이지 수정 폼
	 */
	function pageEdit(orgCd, pageCd) {
		var addContent = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/org/page/editFormPop"/>"
			+ "?orgCd="+orgCd+"&pageCd="+pageCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="system.title.page.edit"/>");
		modalBox.show();
	}
</script>


