<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:js src="/js/modaldialog.js"/>
	<mhtml:head_module paging="Y"/>
</mhtml:home_head>

<mhtml:home_body>
			<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
			<div class="row">
					<div class="col-lg-12" style="margin-bottom:20px;">
						<!--CONTENTS START-->
						<ul class="nav nav-pills nav-justified" style="border:1px solid #dedede">
							<c:set var="itemCnt" value="1"/>
							<li class="wordbreak ctgrList active" id="list_all"><a href="javascript:setCtgr('')"><spring:message code="common.title.all"/></a></li>
							<c:forEach var="item" items="${listCtgr}" varStatus="status">
							<c:set var="itemCnt" value="${itemCnt +1}"/>
							<li class="wordbreak ctgrList" id="list_${item.ctgrCd }"><a href="javascript:setCtgr('${item.ctgrCd }')">${item.ctgrNm}</a></li>
							<c:if test="${itemCnt >= 4 }">
						</ul>
						<ul class="nav nav-pills nav-justified" style="border:1px solid #dedede">
							<c:set var="itemCnt" value="0"/>
							</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			<div class="row">
				<div id="displayArea">
					<div class="panel-body">
						<ul class="nav nav-tabs" id="tabMenuOper" style="margin-bottom: 5px;">
				  			<li class="active" id="mediaTab" ><a href="javascript:onclickTab(0)"><spring:message code="library.title.contents.media"/></a></li>
				  			<li id="olcTab" ><a href="javascript:onclickTab(1)"><spring:message code="course.title.contents.olccontents"/></a></li>
						</ul>
						<form name="Search" onsubmit="return false" class="form-inline">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="pull-right" style="margin-top:-4px;">
									<select name="searchKey" id="searchKey" class="form-control input-sm">
										<option value="ALL"><spring:message code="common.title.all"/></option>
										<option value="cntsNm"><spring:message code="library.title.contents.name"/></option>
										<option value="cntsTag"><spring:message code="common.title.tag"/></option>
										<option value="regUser"><spring:message code="common.title.writer.name"/></option>
									</select>
									<div class="input-group" style="width:180px;">
										<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm">
										<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
											<i class="fa fa-search"></i>
										</span>
									</div>
									<select name="listScale" id="listScale" onchange="listPageing('1')" class="form-control input-sm">
										<option value="10">10</option>
										<option value="20" selected="selected">20</option>
										<option value="40">40</option>
										<option value="60">60</option>
										<option value="80">80</option>
										<option value="100">100</option>
										<option value="200">200</option>
									</select>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="panel-body" id="contentsList">
								<ul class="list-group">
									<li class="list-group-item"><spring:message code="library.message.contents.category.select.category"/></li>
								</ul>
							</div>
						</div>
						</form>
					</div>
				</div>
				<div id="workArea" style="dispaly:none" class="col-md-12">

				</div>
			</div>
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
				//listCourse(1);
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

	function setCtgr(ctgrCd){
		$(".ctgrList").removeClass("active");
		if(ctgrCd == ""){
			ItemDTO.nodeId = "";
			$("#list_all").addClass("active");
		} else {
			ItemDTO.nodeId = ctgrCd;
			$("#list_"+ctgrCd).addClass("active");
		}
		listPageing(1);
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
			url = cUrl("/home/library/clibShareCnts/listShareMediaKnow");
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
			url = cUrl("/home/library/clibShareCnts/listShareOlcKnow");
			$("#contentsList").load(url,
					{	"ctgrCd":ItemDTO.nodeId
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

	function previewMedia(cntsCd) {
		var url = cUrl("/home/library/clibShareCnts/previewMediaPop")+"?cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
		var contentsWin = window.open(url, "contentsWin", winOption);
		contentsWin.focus();
	}

	function previewOlc(userNo, cntsCd, cntNm) {
		var url = cUrl("/home/library/clibShareCnts/previewOlcMain")+"?cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=no,resizable=no,width=1100,height=775";
		var contentsPage = window.open(url, "contentsWin", winOption);
		contentsPage.focus();
	}

	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/UserInfoHome.do"/>"
			+ "?cmd=viewUser&amp;userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}
</script>
</mhtml:home_body>

</mhtml:home_html>
