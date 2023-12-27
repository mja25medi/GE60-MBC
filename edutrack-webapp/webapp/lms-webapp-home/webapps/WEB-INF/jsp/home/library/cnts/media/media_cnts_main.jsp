<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibMediaCntsList" value="${clibMediaCntsList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="clibMediaCntsVO" value="${clibMediaCntsVO}"/>
<c:set var="curPage" value="${clibMediaCntsVO.curPage}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y" fileupload="y"/>
	<meditag:js src="/js/modaldialog.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:home_head>

<mhtml:home_body>
			<mhtml:home_location />
			<div class="row">
				<div id="displayArea">
					<div class="col-md-8 col-sm-8">
						<div class="input-group">
							<div class="input-group-btn btn-group" style="margin-right: 5px;">
								<button onclick="clickDropdown()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
									<span class="caret" style="margin-top: 6px; margin-bottom: 5px;"></span>
								</button>
								<ul class="dropdown-menu" role="menu" id="ctgrDrop">
									<li style="width:380px;">
										<div id="ctgrTreeArea" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
									</li>
								</ul>
							</div>
							<input type="hidden" name="ctgrCdList" id="ctgrCdList" class="form-control input-sm"  value="${clibMediaCntsVO.ctgrCd}"/>
							<input type="text" name="ctgrNmList" id="ctgrNmList" class="form-control input-sm" style="width:140px;background-color:#ffffff;margin-right: 5px;" readonly="readonly" value=""/>
							<select name="searchKey" id="searchKey" class="form-control input-sm" style="width: 80px;margin-right: 5px;">
								<option value="ALL"><spring:message code="common.title.all"/></option>
								<option value="cntsNm" <c:if test="${clibMediaCntsVO.searchKey eq 'cntsNm' }">selected</c:if>><spring:message code="common.title.title"/></option>
								<option value="cntsTag" <c:if test="${clibMediaCntsVO.searchKey eq 'cntsTag' }">selected</c:if>><spring:message code="common.title.tag"/></option>
								<option value="regUser" <c:if test="${clibMediaCntsVO.searchKey eq 'regUser' }">selected</c:if>><spring:message code="common.title.writer.name"/></option>
							</select>
							<div class="input-group" style="width:180px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" value="${clibMediaCntsVO.searchValue }">
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
					</div>
					<div style="float:right">
						<select name="listScale" id="listScale" onchange="listPageing('1')" class="form-control input-sm">
							<option value="10" <c:if test="${clibMediaCntsForm.listScale eq '10' }">selected</c:if>>10</option>
							<option value="20" <c:if test="${clibMediaCntsForm.listScale eq '20' }">selected</c:if>>20</option>
							<option value="40" <c:if test="${clibMediaCntsForm.listScale eq '40' }">selected</c:if>>40</option>
							<option value="60" <c:if test="${clibMediaCntsForm.listScale eq '60' }">selected</c:if>>60</option>
							<option value="80" <c:if test="${clibMediaCntsForm.listScale eq '80' }">selected</c:if>>80</option>
							<option value="100" <c:if test="${clibMediaCntsForm.listScale eq '100' }">selected</c:if>>100</option>
							<option value="200" <c:if test="${clibMediaCntsForm.listScale eq '200' }">selected</c:if>>200</option>
						</select>
					</div>
					<div style="float:right">
						<a href="javascript:ctgrManage()" class="btn btn-primary btn-sm"><spring:message code="etc.title.relatedsite.ctgr.write"/></a>
						<a href="javascript:cntsWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write.contents"/></a>
					</div>
					<div class="panel-body"  id="cntsList" style="clear: both;">
						<ul class="list-group wordbreak">
						<c:forEach var="item" items="${clibMediaCntsList}">
							<li class="list-group-item">
								<c:if test="${not empty item.thumbFileSn && item.thumbFileSn > 0}">
								<div style="width:140px;float:left;">
									<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" style="width: 130px;" alt="${item.cntsNm}"/>
								</div>
								</c:if>
								<c:if test="${item.useYn eq 'Y'}"><i class="glyphicon glyphicon-ok-circle" style="color:skyblue"></i></c:if>
   								<c:if test="${item.useYn ne 'Y'}"><i class="glyphicon glyphicon-ban-circle" style="color:orange"></i></c:if>
   								${item.cntsNm}
								<a href="javascript:cntsEdit('${item.cntsCd}')" >
									<%-- <spring:message code="button.edit"/> --%>
									<i class="fa fa-cog"></i>
								</a>
								<span class="pull-right" style="text-align: center;">
									<c:if test="${item.uldStsCd eq 'complete' }">
									<a href="javascript:preview('${item.cntsCd}','${item.uldStsCd}')" class="btn btn-xs btn-default"><spring:message code="button.preview"/></a>
									<a href="javascript:contentsShare('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="common.title.share"/></a>
									</c:if>
								</span>
								<div class="small">
									<spring:message code="common.title.regdate"/> : <meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/> |
									<spring:message code="common.title.stats"/> : <c:if test="${item.uldStsCd eq 'upload'}"><spring:message code="library.title.contents.stats.upload"/></c:if><c:if test="${item.uldStsCd eq 'complete'}"><spring:message code="library.title.contents.stats.channel"/></c:if>
									<c:if test="${item.uldStsCd eq 'complete'}"><i class="fa fa-check-circle fa-1x"></i></c:if>
									<c:if test="${item.uldStsCd eq 'upload'}"><i class="fa fa-refresh fa-spin fa-1x"></i></c:if>
									| <spring:message code="common.title.tag"/> : ${item.cntsTag }
								</div>
								<div style="clear:both"></div>
							</li>
						</c:forEach>
						<c:if test="${empty clibMediaCntsList}">
							<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
						</c:if>
						</ul>

						<%-- <meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/> --%>
					</div>
					<div class="row" style="margin-bottom:20px;">
						<div class="col-lg-12">
							<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
						</div>
					</div>
				</div>
				<div id="workArea" style="dispaly:none" class="col-md-12">

				</div>
			</div>
<script type="text/javascript">
	var ctgrTree = null;
	var modalBox = null;
	var ItemDTO = { "nodeId" : "", "subCnt" : 0, "cntsCnt" : 0, "nodeType" : "root"};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("#writeBtn").bind("click", function(event) {
			cntsWrite();
		});

		olcCtgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/home/library/clibCntsCtgr/listCtgrJsTree"),
					"data" : function (n) {
						return {
							"id" : n.attr ? n.attr("id") : "#"
						};
					}
				}
			},
			"types" : {
				"valid_children" : [ "root" ],
				"types" : {
					"default" : {
						"valid_children" : "none",
						"icon" : { "image" : "/img/framework/icon/filetype/file.png" }
					},
					"root" : {
						"valid_children" : "all",
						"icon" : { "image" : "/img/framework/icon/icon_administrator.gif" }
					},
					"category" : {
						"valid_children" :  "all",
						"icon" : { "image" : "/img/framework/icon/icon_contents.gif" }
					},
					"contents" : {
						"valid_children" : "none",
						"icon" : { "image" : "/img/framework/icon/icon_lesson.gif" }
					}
				}
			},
			"themes" : { "theme" : "default", "dots" : true }
		}).bind("select_node.jstree", function(e, data) {
			setSelectNode(data);
		}).bind("loaded.jstree", function(e, data){
			$(this).jstree("open_all");
		});
		<c:if test="${empty clibMediaCntsVO.ctgrNm }">$("#ctgrNmList").val("<spring:message code="library.title.category.all"/>");</c:if>
		<c:if test="${not empty clibMediaCntsVO.ctgrNm }">$("#ctgrNmList").val("${clibMediaCntsVO.ctgrNm }");</c:if>
	});


	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function setSelectNode(data) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("ctgrNm");
		var rel = data.rslt.obj.attr("rel");
		var subCnt = data.rslt.obj.attr("subCnt");
		var cntsCnt = data.rslt.obj.attr("cntsCnt");

		ItemDTO.nodeId = id;
		ItemDTO.nodeType = rel;
		ItemDTO.subCnt = subCnt;
		ItemDTO.cntsCnt = cntsCnt;

		if("root" == id){
			name = "";
		}
		$("#ctgrCdList").val(id);
		$("#ctgrNmList").val(name);
		$("#ctgrDrop").hide();
		listPageing(1);
	}

	/**
	 * 콘텐츠 목록을 조회한다.
	 */
	function listPageing(page) {
		var id = $("#ctgrCdList").val();
		var name = $("#ctgrNmList").val();
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		ItemDTO.page = page;
		if("root" == id){
			name = "";
		}
		$("#ctgrNmList").val(name);
		$("#ctgrDrop").hide();
		var listScale = $("#listScale option:selected").val();
		location.href="/home/library/clibMediaCnts/listCnts"
			+"?clibMediaCntsVO.ctgrCd="+id+"${AMPERSAND}"
			+"clibMediaCntsVO.ctgrNm="+name+"${AMPERSAND}"
			+"clibMediaCntsVO.searchKey="+searchKey+"${AMPERSAND}"
			+"clibMediaCntsVO.searchValue="+searchValue+"${AMPERSAND}"
			+"curPage="+page+"${AMPERSAND}"
			+"listScale="+listScale;
	}

	/**
	 * OLC 분류 등록 폼
	 */
	function ctgrManage() {
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/home/library/clibCntsCtgr/manageCtrg"/>"
			+ "'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("<spring:message code="library.title.contents.category.write"/>");
		modalBox.show();
	}

	/**
	 * 콘텐츠 등록 폼
	 */
	function cntsWrite() {
		$("#workArea").empty();
		$("#workArea").load(cUrl("/home/library/clibMediaCnts/addForm"),{"clibMediaCntsVO.ctgrCd":ItemDTO.nodeId,"curPage":"${curPage}"});
		$("#displayArea").slideUp('slow');
		$("#workArea").show();
	}

	/**
	 * 콘텐츠 수정 폼
	 */
	function cntsEdit(cntsCd) {
		$("#workArea").empty();
		$("#workArea").load(cUrl("/home/library/clibMediaCnts/editForm"),{"clibMediaCntsVO.cntsCd":cntsCd,"curPage":"${curPage}"});
		$("#displayArea").slideUp('slow');
		$("#workArea").show();
	}


	function closeWriteArea() {
		$("#displayArea").slideDown('slow');
		$("#workArea").empty();
		$("#workArea").slideUp('slow');
	}

	function preview(cntsCd) {
		var url = cUrl("/home/library/clibMediaCnts/preview")+"?clibMediaCntsVO.cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
		var contentsWin = window.open(url, "contentsWin", winOption);
		contentsWin.focus();
	}

	function contentsShare(cntsCd) {
        var addContent = "<iframe id='contentsShareFrame' name='contentsShareFrame' width='100%' height='100%' "
            + "frameborder='0' scrolling='auto' src='<c:url value="/home/library/clibMediaCnts/addShareForm"/>"
            + "?clibMediaCntsVO.cntsCd="+cntsCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 500);
		modalBox.setTitle("<spring:message code="library.title.contents.share"/>");
		modalBox.show();
	}

</script>
</mhtml:home_body>

</mhtml:home_html>
