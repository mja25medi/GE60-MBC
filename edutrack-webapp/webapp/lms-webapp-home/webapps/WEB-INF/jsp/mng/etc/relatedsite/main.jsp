<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">


						<div class="row" id="content">
							<div class="col-md-5 col-sm-12">
								<div class="input-group" style="width:100%">
<%-- 									<div class="text-right">
										<button class="btn btn-primary btn-sm" onclick="ctgrWrite()" ><spring:message code="button.write.category"/></button>
									</div> --%>
								</div>
								<div id="ctgrList" style="margin-top:35px;width:100%;"></div>
							</div>
							<div class="col-md-7 col-sm-12">
								<div class="input-group" style="width:100%">
									<div style="float:left;line-height:30px;">
										<span id="siteTitle"></span>
									</div>
									<div class="text-right">
										<button class="btn btn-primary btn-sm" onclick="siteWrite()"><spring:message code="button.write"/></button>
									</div>
								</div>
								<div id="siteList" style="margin-top:5px;width:100%;"></div>
							</div>
						</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = new Object;
	
	/** 관련사이트 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		ItemDTO.ctgrCd = "";
		ItemDTO.ctgrTitle = "";
		listCtgr();
	});
		
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 * 시스템 분류 관련사이트 셋팅
	 */
	function setCtgr(ctgrCd, ctgrTitle) {
		ItemDTO.ctgrCd = ctgrCd;
		ItemDTO.ctgrTitle = ctgrTitle;
		listSite();
	}

	/**
	 * 시스템관련사이트 카테고리 목록 조회
	 */
	function listCtgr() {
		$("#ctgrList").load(cUrl("/mng/etc/relatedSite/listCtgr"));
		listSite();
	}

	/**
	 * 시스템 관련사이트 목록 조회
	 */
	function listSite() {
		var title = "&nbsp;&nbsp;<b>"+ItemDTO.ctgrTitle+"</b>";
	 	$("#siteTitle").show().html(title);
		$("#siteList").load(cUrl("/mng/etc/relatedSite/listSite"),{"ctgrCd":ItemDTO.ctgrCd});
	}

	/**
	 * 관련사이트 분류 등록 폼
	 */
	function ctgrWrite() {
		
		var url = generateUrl("/mng/etc/relatedSite/addCtgrPop");
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="etc.title.relatedsite.ctgr.write"/>");
		modalBox.show();
		
	}


	/**
	 * 관련사이트 분류 수정 폼
	 */
	function ctgrEdit(ctgrCd) {
		var url = generateUrl("/mng/etc/relatedSite/editCtgrPop",{ "ctgrCd":ctgrCd });
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="etc.title.relatedsite.ctgr.edit"/>");
		modalBox.show();
	}

	/**
	 * 관련사이트 등록 폼
	 */
	function siteWrite() {
		if(ItemDTO.ctgrCd == "") {
			alert('<spring:message code="etc.message.relatedsite.site.alert.select.category"/>');
		} else {
			var url = generateUrl("/mng/etc/relatedSite/addSitePop",{ "ctgrCd":ItemDTO.ctgrCd });
			var addContent  = "<iframe id='addSiteFrame' name='addSiteFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='no' src='"+url+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(500, 350);
			modalBox.setTitle("<spring:message code="etc.title.relatedsite.site.write"/>");
			modalBox.show();
		}
	}



	/**
	 * 관련사이트 수정 폼
	 */
	function siteEdit(siteSn) {
		var url = generateUrl("/mng/etc/relatedSite/editSitePop",{ "ctgrCd":ItemDTO.ctgrCd, "siteSn":siteSn });
		var addContent = "<iframe id='addSiteFrame' name='addSiteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 350);
		modalBox.setTitle("<spring:message code="etc.title.relatedsite.site.edit"/>");
		modalBox.show();
	}

	/**
	 * 관련사이트 순서 변경
	 */
	function sortSite(sortString) {
		$.getJSON( cUrl( "/mng/etc/relatedSite/sortSite"),
				{ "ctgrCd": ItemDTO.ctgrCd,"siteSn" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listSite();
		 	  		}
				}
			);
	}

	/**
	 * 분류 순서 변경
	 */
	function sortCtgr(sortString) {
		$.getJSON( cUrl( "/mng/etc/relatedSite/sortCtgr"),
				{ "ctgrCd": sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listCtgr(ItemDTO.ctgrCd, ItemDTO.ctgrNm);
		 	  		}
				}
			);
	}
	
</script>

