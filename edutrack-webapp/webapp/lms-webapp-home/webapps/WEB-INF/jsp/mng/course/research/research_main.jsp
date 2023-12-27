<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="rowLayer" id="listLayer">
						<form name="Search" onsubmit="return false" class="form-inline">
						<div class="col-md-6 col-sm-6">
							<div class="form-group">
								<div class="input-group" style="width:180px;">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>"/>
									<span class="input-group-addon" onclick="listResearch(1)" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right">
							<input type="checkbox" name="chkReg" id="chkReg" onchange="listResearch(1);" ><spring:message code="course.title.reshbank.regyn"/>
							<a href="javascript:addForm()" class="btn btn-primary btn-sm"><spring:message code="button.write.research"/> </a>
							<div class="form-group">
								<select name="listScale" id="listScale" onchange="listResearch(1);" class="form-control input-sm">
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
						<div class="col-md-12">
							<div id="rearchList" style="margin-top:5px;">
								<table summary="<spring:message code="course.title.reshbank.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:80px"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:120px"/>
										<col style="width:50px"/>
										<col style="width:72px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="common.title.no"/></th>
											<th scope="col"><spring:message code="course.title.reshbank.title"/></th>
											<th scope="col"><spring:message code="course.title.reshbank.qstn.cnt"/></th>
											<th scope="col"><spring:message code="common.title.useyn"/></th>
											<th scope="col"><spring:message code="common.title.regdate"/></th>
											<th scope="col"><spring:message code="common.title.edit"/></th>
											<th scope="col"><spring:message code="common.title.manage"/></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="7"><spring:message code="course.message.reshbank.nodata"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</div>
</section>

<script type="text/javascript">

	var modalBox = null;
	var ItemDTO  = new Object();
	var curPage = 1;

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listResearch(1);
			}
		}
		listResearch(1);
		ItemDTO.sortKey = "REGDATE_DESC";
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 설문 목록 조회
	 */
	function listResearch(page) {
		var listScale = $("#listScale option:selected").val();
		var searchValue = $('#searchValue').val();
		var regYn = "";
		if($("#chkReg").is(":checked") ){
			regYn = "Y";
		} else {
			regYn = "";
		}
		
		if(page != undefined)
			curPage = page;

		
		$("#rearchList")
			.load(
				cUrl("/mng/course/researchBank/list"),		// url
				{ 
				  "searchValue":searchValue,
				  "regYn":regYn,
				  "sortKey":ItemDTO.sortKey,
				  "curPage":page,
				  "listScale":listScale
				}
			);
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listResearch(1);
	}

	/**
	 * 설문 등록 폼
	 */
	function addForm() {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/researchBank/addPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 320);
		modalBox.setTitle("<spring:message code="course.title.reshbank.write"/>");
		modalBox.show();
	}

	/**
	 * 설문 수정 폼
	 */
	function editForm(reshSn) {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/researchBank/editPop"/>"
			+ "?curpage="+curPage+"&reshSn="+reshSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 320);
		modalBox.setTitle("<spring:message code="course.title.reshbank.edit"/>");
		modalBox.show();
	}


	/**
	 * 설문 상세정보
	 */
	function reshInfo(reshSn) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/researchBank/viewReshPop"/>"
			+ "?reshSn="+reshSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="course.title.reshbank.info"/>");
		modalBox.show();
	}

	/**
	 * 설문 관리
	 */
	function manageForm(reshSn) {
		document.location.href = "/mng/course/researchBank/manageMain?reshSn="+reshSn;
	}
</script>
