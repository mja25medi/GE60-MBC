<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<section class="content">
		<div class="row" id="content">
			<div class="box">
				<div class="box-body">
					<div class="col-md-6 col-sm-12">
						<form name="Search" onsubmit="return false">
						<div class="input-group">
							<div class="input-group"  style="width:160px;">
								<input type="text" name="searchKey" id="codeCtgrNm" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>"/>
								<span class="input-group-addon" onclick="listCategory()" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
							<div class="input-group-btn text-right">
								<button class="btn btn-info btn-sm" onclick="listCategoryInit()" ><spring:message code="button.reload"/></button>
								<button class="btn btn-primary btn-sm" onclick="ctgrWrite()" ><spring:message code="button.write.category"/></button>
							</div>
						</div>
						<input type="submit" value="submit" style="display:none" />
						</form>
						<div id="categoryList" style="margin-top:5px;width:100%;"></div>
					</div>
					<div class="col-md-6 col-sm-12">
						<div class="input-group">
							<div style="float:left;line-height:30px;">
								<spring:message code="system.title.code.manage"/>
							</div>
							<div style="float:left;line-height:30px;">
								<span id="codeTitle"></span>
							</div>
							<div class="input-group-btn text-right">
								<button class="btn btn-primary btn-sm" onclick="codeWrite()"><spring:message code="button.write.code"/></button>
								<button class="btn btn-info btn-sm" onclick="codeSort()"><spring:message code="button.sort"/></button>
							</div>
						</div>
						<div id="codeList" style="margin-top:5px;width:100%;"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
		
<script type="text/javascript">
	
	// 페이지 전역 변수 객체.
	var modalBox = null;
	
	var ItemDTO = { };
	
	var CategoryDTO = {
		codeCtgrCd : "",
		codeCtgrNm : ""
	};

	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listCategory();
			}
		}
		listCategory();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 시스템코드 카테고리 목록 조회
	 */
	function listCategory(page) {
		// 리스트 갱신전에 코드용 자료 삭제.
		ItemDTO.page = page;
		CategoryDTO.codeCtgrCd = "";
		CategoryDTO.codeCtgrNm = "";
		codeCtgrNm = $('#codeCtgrNm').val();
		$("#categoryList").load(cUrl("/adm/system/code/listCtgr"), {"codeCtgrNm":codeCtgrNm,"pageIndex":page,"listScale":12});
		listCode();
	}

	function listCategoryInit() {
		$("#codeCtgrNm").val("");
		listCategory();
	}


	/**
	 * 시스템 분류 코드 셋팅
	 */
	function setCategoryCode(ctgrCd, ctgrNm) {
		CategoryDTO.codeCtgrCd = ctgrCd;
		CategoryDTO.codeCtgrNm = ctgrNm;
		listCode();
	}

	/**
	 * 시스템 코드 목록 조회
	 */
	function listCode() {
		var title = "&nbsp;:&nbsp;&nbsp;<b>"+CategoryDTO.codeCtgrNm+"</b>";
	 	$("#codeTitle").show().html(title);
		$("#codeList").load(cUrl("/adm/system/code/listCode"), {"codeCtgrCd":CategoryDTO.codeCtgrCd});
	}

	/**
	 * 코드 분류 등록 폼
	 */
	function ctgrWrite() {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/code/addFormCtgrPop"/>'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(420, 300);
		modalBox.setTitle("<spring:message code="system.title.code.write.category"/>");
		modalBox.show();
	}

	/**
	 * 코드 분류 수정 폼
	 */
	function ctgrEdit(codeCtgrCd) {
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/code/editFormCtgrPop"/>"
			+ "?&codeCtgrCd="+codeCtgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(420, 300);
		modalBox.setTitle("<spring:message code="system.title.code.edit.category"/>");
		modalBox.show();
	}

	/**
	 * 코드 등록 폼
	 */
	function codeWrite() {
		if(CategoryDTO.codeCtgrCd == "") {
			alert('<spring:message code="system.message.code.select.category"/>');
		} else {
			var addContent  = "<iframe id='addCodeFrame' name='addCodeFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/system/code/addFormCodePop"/>"
				+ "?codeCtgrCd="+CategoryDTO.codeCtgrCd+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(420, 440);
			modalBox.setTitle("<spring:message code="system.title.code.write"/>");
			modalBox.show();
		}
	}

	/**
	 * 코드 수정 폼
	 */
	function codeEdit(codeCd) {
		var addContent = "<iframe id='addCodeFrame' name='addCodeFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/system/code/editFormCodePop"/>"
			+ "?codeCtgrCd="+CategoryDTO.codeCtgrCd+"&codeCd="+codeCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(420, 440);
		modalBox.setTitle("<spring:message code="system.title.code.edit"/>");
		modalBox.show();
	}

	/**
	 * 코드 순서 변경 폼
	 */
	function codeSort() {
        if(CategoryDTO.codeCtgrCd == "") {
            alert('<spring:message code="system.message.code.select.category"/>');
        } else {
            var addContent = "<iframe id='addCodeFrame' name='addCodeFrame' width='100%' height='100%' "
                + "frameborder='0' scrolling='no' src='<c:url value="/adm/system/code/sortFormCodePop"/>"
                + "?codeCtgrCd="+CategoryDTO.codeCtgrCd+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(310, 310);
			modalBox.setTitle("<spring:message code="system.title.code.sort"/>");
			modalBox.show();
        }
	}
</script>


