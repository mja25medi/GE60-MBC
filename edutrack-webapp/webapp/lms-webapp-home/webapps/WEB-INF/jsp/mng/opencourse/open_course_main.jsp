<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/page_init.jsp" %>
<c:set var="openCrsVO" value="${vo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="col-md-4 col-sm-12">
						<div class="input-group" style="width:100%">
							<div class="text-right">
								<button class="btn btn-primary btn-sm" onclick="ctgrWrite()" ><spring:message code="button.write.category"/></button>
							</div>
						</div>
						<div id="ctgrList" style="margin-top:5px;width:100%;"></div>
					</div>
					<div class="col-md-8 col-sm-12">
						<div class="input-group" style="width:100%">
							<div style="float:left;line-height:30px;">
								<span id="courseTitle"></span>
							</div>
							<div class="text-right">
								<button class="btn btn-primary btn-sm" onclick="crsWrite()"><spring:message code="button.write"/></button>
							</div>
						</div>
						<div id="listCource" style="margin-top:5px;width:100%;"></div>
					</div>
				</div>
				<div id="workLayer" class="rowLayer">
					<div class="col-md-12" id="workBody"></div>
				</div>
			</div>	
		</div>
	</div>
</section>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = {"ctgrCd":"","ctgrTitle":""};

	/** 관련사이트 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		listCtgr();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 분류별 과정 조회 리스트
	 */
	function setCtgr(ctgrCd, ctgrTitle) {
		ItemDTO.ctgrCd = ctgrCd;
		ItemDTO.ctgrTitle = ctgrTitle;
		listCourse();
	}

	/**
	 * 분류 목록 조회
	 */
	function listCtgr() {
		$("#ctgrList").load(cUrl("/mng/openCourse/listCtgr"),{ "ctgrCd":"${openCrsVO.ctgrCd}"});
	}

	/**
	 * 분류 등록 폼
	 */
	function ctgrWrite() {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/openCourse/addCtgrPop"/>"
			+ "?gubun=A'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="etc.title.relatedsite.ctgr.write"/>");
		modalBox.show();
	}

	/**
	 * 분류 수정 폼
	 */
	function ctgrEdit(ctgrCd) {
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/openCourse/addCtgrPop"/>"
			+ "?ctgrCd="+ctgrCd+"&amp;gubun=E'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="etc.title.relatedsite.ctgr.edit"/>");
		modalBox.show();
	}

	/**
	 * 분류 순서 변경 폼
	 */
	function ctgrSort(sortString) {
		$.getJSON( cUrl( "/mng/openCourse/sortCtgr"),
				{ "ctgrCd" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listOnline();
		 	  		}
				}
			);
	}

	/**
	 * 공개과정 과정 리스트
	 */
	function listCourse() {
		var title = "<b>"+ItemDTO.ctgrTitle+"</b>";
	 	$("#courseTitle").show().html(title);
	 	$("#listCource").empty();
		$("#listCource").load(cUrl("/mng/openCourse/listCrs"),{ "ctgrCd":ItemDTO.ctgrCd});
	}

	/**
	 * 공개과정 과정 등록 폼
	 */
	function crsWrite() {
		if(ItemDTO.ctgrCd == "") {
			alert('<spring:message code="etc.message.relatedsite.site.alert.select.category"/>');
		} else {
			var addContent  = "<iframe id='addSiteFrame' name='addSiteFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='no' src='<c:url value="/mng/openCourse/addCrsPop"/>"
				+ "?ctgrCd="+ItemDTO.ctgrCd+"&amp;gubun=A'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(500, 350);
			modalBox.setTitle("<spring:message code="course.title.course.write"/>");
			modalBox.show();
		}
	}



	/**
	 * 공개과정 과정 수정 폼
	 */
	function crsEdit(crsCd) {
		var addContent = "<iframe id='addSiteFrame' name='addSiteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/openCourse/addCrsPop"/>"
			+ "?ctgrCd="+ItemDTO.ctgrCd+"&amp;crsCd="+crsCd+"&amp;gubun=E'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 350);
		modalBox.setTitle("<spring:message code="course.title.course.write"/>");
		modalBox.show();
	}

	/**
	 * 공개과정 분류 과정 순서 변경 폼
	 */
	function crsSort(sortString) {
		if(ItemDTO.ctgrCd == "") {
			alert('<spring:message code="etc.message.relatedsite.site.alert.select.category"/>');
		} else {
			$.getJSON( cUrl( "/mng/openCourse/sortCrs"),
					{ "ctgrCd" : ItemDTO.ctgrCd, "crsCd" : sortString },			// params
					function(data) {
						alert(data.message);
						if(data.result >= 0) {
							//-- 정상 처리
			 	  		} else {
			 	  			//-- 비정상 처리
							listOnline();
			 	  		}
					}
				);
		}
	}

	function crsManage(crsCd, ctgrCd){
		location.href = "/mng/openCourse/subject/main?ctgrCd="+ctgrCd+"${AMPERSAND}crsCd="+crsCd;
	}

	function changeExposure(checkbox) {
		var exposureYn = "N";
		if(checkbox.checked) {
			exposureYn = "Y";
		}
		$.getJSON( cUrl("/mng/openCourse/editExposure"),		// url
				{ 
				  "crsCd" : checkbox.value,
				  "exposureYn" : exposureYn
				},			// params
				function(resultDTO) {
					alert(resultDTO.message);
					listCourse();
				}
			);
	}
</script>
