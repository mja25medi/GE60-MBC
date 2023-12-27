<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<section class="content">
	<div class="box">
		<div class="box-body">
			<div class="row" id="content">
				<form name="Input" id="Input" onsubmit="return false" class="form-inline">
				<div class="col-md-6 col-sm-6">
					<div class="input-group">
						<select name="orgCd" id="orgCd" class="form-control input-sm">
							<option value=""><spring:message code="org.title.orginfo.all"/></option>
							<c:forEach var="item" items="${orgInfoList}">
							<option value="${item.orgCd}">${item.orgNm}</option>
							</c:forEach>
						</select>
						<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="text-right">
						<button class="btn btn-primary btn-sm" onclick="addInfo()"><spring:message code="button.write.bbs"/></button>
					</div>
				</div>
				</form>
				<div class="col-md-12">
					<div id="bbsList" style="margin-top:5px;">
						<table summary="<spring:message code="board.title.bbs.info.list"/>" class="table table-bordered">
							<colgroup>
								<col style="width:auto;"/>
								<col style="width:auto;"/>
								<col style="width:auto;"/>
								<col style="width:120px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
							<tr>
								<th scope="col"><spring:message code="org.title.orginfo.orgname"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.bbscd"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.bbsnm"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.listtype"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.reply"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.comment"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.atache"/></th>
								<th scope="col"><spring:message code="common.title.edit"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.head"/></th>
							</tr>
							</thead>
							<tbody></tbody>
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
	 * 게시판 정보 목록 조회
	 */
	function listPageing(page) {
		var orgCd = $("#orgCd option:selected").val();
        $("#bbsList").load(cUrl("/adm/brd/bbs/list"),{ "pageIndex" : page, "orgCd" : orgCd});
	}

	/**
	 * 게시판 정보 등록 폼
	 */
	function addInfo() {
		var orgCd = $("#orgCd option:selected").val();
		/* if(isEmpty(orgCd)){
			alert('사이트를 선택바랍니다.');
			$("#orgCd").focus();
			return;
		} */
		var addContent  = "<iframe id='addBbsInfoFrame' name='addBbsInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/brd/bbs/addForm"/>"
			//+ "?orgCd="+orgCd+"'/>";
			
			+ "?orgCd="+orgCd+""
			+ "'/>";
			
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(900, 540);
		modalBox.setTitle("<spring:message code="board.title.bbs.info.write"/>");
		modalBox.show();
	}

	/**
	 * 게시판 정보 수정 폼
	 */
	function editInfo(orgCd, bbsCd) {
		var addContent = "<iframe id='addBbsInfoFrame' name='addBbsInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/brd/bbs/editForm"/>"
			+ "?orgCd="+orgCd+"&bbsCd="+bbsCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(900, 540);
		modalBox.setTitle("<spring:message code="board.title.bbs.info.edit"/>");
		modalBox.show();
	}

	/**
	 * 게시판 머릿말 관리
	 */
	function mngHead(orgCd, bbsCd) {
		var addContent = "<iframe id='addBbsInfoFrame' name='addBbsInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/brd/bbs/listHeadPop"/>"
			+ "?orgCd="+orgCd+"&bbsCd="+bbsCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 400);
		modalBox.setTitle("<spring:message code="board.title.bbs.head.manage"/>");
		modalBox.show();
	}

</script>


