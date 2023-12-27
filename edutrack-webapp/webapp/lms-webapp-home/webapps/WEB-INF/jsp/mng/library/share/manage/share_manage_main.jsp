<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div id="displayArea">
					<ul class="nav nav-tabs" id="tabMenuOper" style="margin-bottom: 5px;">
			  			<li class="active" id="mediaTab" ><a href="javascript:onclickTab(0)"><spring:message code="library.title.contents.media"/></a></li>
			  			<li id="olcTab" ><a href="javascript:onclickTab(1)"><spring:message code="course.title.contents.olccontents"/></a></li>
					</ul>
					<form name="Search" onsubmit="return false" class="form-inline">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="" style="margin-top:-4px;" align="right">
								<select name="listScale" id="listScale" onchange="listPageing('1')" class="form-control input-sm" style="width: 65px;float: right;">
									<option value="10">10</option>
									<option value="20" selected="selected">20</option>
									<option value="40">40</option>
									<option value="60">60</option>
									<option value="80">80</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
								<select name="searchCd" id="searchCd" class="form-control input-sm" onchange="listPageing(1)" style="width: 100px;float: right;margin-right: 5px;">
									<option value=""><spring:message code="common.title.stats"/></option>
									<option value="01"><spring:message code="library.title.sharecd.01"/></option>
									<option value="03"><spring:message code="library.title.sharecd.03"/></option>
									<option value="04"><spring:message code="library.title.sharecd.04"/></option>
								</select>
								<div class="input-group" style="width:180px;float: right;margin-right: 5px;">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>" />
									<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>
								<select name="searchKey" id="searchKey" class="form-control input-sm" style="width: 100px;float: right;margin-right: 5px;">
									<option value="ALL"><spring:message code="common.title.all"/></option>
									<option value="cntsNm"><spring:message code="library.title.contents.name"/></option>
									<option value="cntsTag"><spring:message code="common.title.tag"/></option>
									<option value="regUser"><spring:message code="common.title.writer.name"/></option>
								</select>
								<div style="clear: both;"></div>
							</div>
						</div>
						<div class="panel-body" id="contentsList">
							<ul class="list-group">
								<li class="list-group-item"><spring:message code="library.message.contents.category.select.category"/></li>
							</ul>
						</div>
					</div>
					</form>

				</div>
				<div id="workArea" style="dispaly:none" class="col-md-12">
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	var ctgrTree = null;
	var modalBox = null;
	var ItemDTO = { };

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		ItemDTO.nodeId ="";
		ItemDTO.tab = "media";

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listPageing(1);
			}
		}

		$("#writeBtn").bind("click", function(event) {
			cntsWrite();
		});

		listPageing(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 콘텐츠 목록을 조회한다.
	 */
	function listPageing(page) {

		var listScale = $("#listScale option:selected").val();
		var url = "";
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var searchCd = $("#searchCd option:selected").val();

		if(ItemDTO.tab == "media"){
			url = cUrl("/mng/library/clibShareCnts/listManageShareMedia");
			$("#contentsList").load(url,
					{	"ctgrCd":ItemDTO.nodeId
						,"useYn":"Y"
						,"searchKey":searchKey
						,"searchValue":searchValue
						,"searchCd":searchCd
						,"curPage" : page
						,"listScale":listScale
					});
		} else if(ItemDTO.tab == "olc"){
			url = cUrl("/mng/library/clibShareCnts/listManageShareOlc");
			$("#contentsList").load(url,
					{"ctgrCd":ItemDTO.nodeId
						,"useYn":"Y"
						,"searchKey":searchKey
						,"searchValue":searchValue
						,"searchCd":searchCd
						,"curPage" : page
						,"listScale":listScale
					});
		}
		/*
		if(ItemDTO.nodeId != 'root' && ItemDTO.nodeId != '') {
		}
		 */
	}

	function onclickTab(tab){
		$("#mediaTab").removeClass("active");
		$("#olcTab").removeClass("active");
		if(tab==0){
			$("#mediaTab").addClass("active");
			ItemDTO.tab = "media";
		} else if(tab==1){
			$("#olcTab").addClass("active");
			ItemDTO.tab = "olc";
		}
		listPageing(1);
	}

	function manageMediaShare(cntsCd){
		 var addContent = "<iframe id='contentsShareFrame' name='contentsShareFrame' width='100%' height='100%' "
	            + "frameborder='0' scrolling='auto' src='<c:url value="/mng/library/clibShareCnts/manageShareMediaForm"/>"
	            + "?cntsCd="+cntsCd+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(600, 300);
			modalBox.setTitle("<spring:message code="library.title.contents.share"/>");
			modalBox.show();
	}

	function manageOlcShare(cntsCd){
		 var addContent = "<iframe id='contentsShareFrame' name='contentsShareFrame' width='100%' height='100%' "
	            + "frameborder='0' scrolling='auto' src='<c:url value="/mng/library/clibShareCnts/manageShareOlcForm"/>"
	            + "?clibOlcCntsDTO.cntsCd="+cntsCd+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(600, 300);
			modalBox.setTitle("<spring:message code="library.title.contents.share"/>");
			modalBox.show();
	}

	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/userInfo/viewUserPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}

	function previewMedia(cntsCd) {
		var url = cUrl("/mng/library/clibMediaCnts/preview")+"?cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
		var contentsWin = window.open(url, "contentsWin", winOption);
		contentsWin.focus();
	}

	function previewOlc(cntsCd) {
		var url = cUrl("/mng/library/clibShareCnts/previewOlcMain")+"?cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=no,resizable=no,width=1100,height=775";
		var contentsPage = window.open(url, "contentsWin", winOption);
		contentsPage.focus();
	}


</script>
