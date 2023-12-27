<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<section class="content">
		<div class="row" id="content">
			<div class="box">
				<div class="box-body">
					<div class="col-md-6">
						<div class="input-group">
							<div class="input-group-btn text-right">
								<button class="btn btn-primary btn-sm" onclick="ctgrWrite()"><spring:message code="button.write.category"/></button>
							</div>
						</div>
						<div id="categoryList" style="width:100%;margin-top:5px;"></div>
					</div>
					<div class="col-md-6">
						<div class="input-group">
							<div style="float:left;line-height:30px;">
								<spring:message code="system.title.config.manage"/>
							</div>
							<div style="float:left;line-height:30px;">
								<span id="configTitle"></span>
							</div>
							<div class="input-group-btn text-right">
								<button class="btn btn-primary btn-sm" onclick="configWrite()"><spring:message code="button.write"/></button>
							</div>
						</div>
						<div id="configList" style="width:100%;margin-top:5px;"></div>
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

		listCategory();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	// 페이지 전역 변수 객체.
	var ConfigCategoryDTO = {
		cfgCtgrCd : "",
		ctgrNm : ""
	};

	/**
	 * 시스템설정 카테고리 목록 조회
	 */
	function listCategory(page) {
		$("#categoryList").load(cUrl("/adm/system/config/listCtgr"), {"pageIndex":page,"listScale":10});
		listConfig();
	}

	/**
	 * 시스템 분류 설정 셋팅
	 */
	function setCategoryConfig(cfgCtgrCd, ctgrNm) {
		ConfigCategoryDTO.cfgCtgrCd = cfgCtgrCd;
		ConfigCategoryDTO.ctgrNm = ctgrNm;
		listConfig();
	}

	/**
	 * 시스템 설정 목록 조회
	 */
	function listConfig() {
	 	var title = ":&nbsp;&nbsp;<b>"+ConfigCategoryDTO.ctgrNm+"</b>";
	 	$("#configTitle").show().html(title);
		$("#configList").load(cUrl("/adm/system/config/listCfg"), {"cfgCtgrCd" : ConfigCategoryDTO.cfgCtgrCd});
	}

	/**
	 * 설정 분류 등록 폼
	 */
	function ctgrWrite() {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/config/addFormCtgrPop"/>' />";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(430, 310);
		modalBox.setTitle("<spring:message code="system.title.config.add.category"/>");
		modalBox.show();
	}


	/**
	 * 설정 분류 수정 폼
	 */
	function ctgrEdit(cfgCtgrCd) {
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/config/editFormCtgrPop"/>"
			+ "?cfgCtgrCd="+cfgCtgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(430, 310);
		modalBox.setTitle("<spring:message code="system.title.config.edit.category"/>");
		modalBox.show();
	}

	/**
	 * 설정 등록 폼
	 */
	function configWrite() {
		if(ConfigCategoryDTO.cfgCtgrCd == "") {
			alert('<spring:message code="system.message.config.alert.select.category"/>');
		} else {
			var addContent  = "<iframe id='addConfigFrame' name='addConfigFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/config/addFormCfgPop"/>"
				+ "?cfgCtgrCd="+ConfigCategoryDTO.cfgCtgrCd+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(430, 390);
			modalBox.setTitle("<spring:message code="system.title.config.add"/>");
			modalBox.show();
		}
	}

	/**
	 * 설정 수정 폼
	 */
	function configEdit(cfgCd) {
		var addContent = "<iframe id='addConfigFrame' name='addConfigFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/config/editFormCfgPop"/>"
			+ "?cfgCtgrCd="+ConfigCategoryDTO.cfgCtgrCd+"&cfgCd="+cfgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(430, 390);
		modalBox.setTitle("<spring:message code="system.title.config.edit"/>");
		modalBox.show();
	}
</script>
