<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request" />
<c:set var="olcShareRelList" value="${lcShareRelList}"/>
<c:set var="olcShareRelVO" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<mhtml:home_html>
<mhtml:home_head>
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>

<mhtml:home_body>
				<mhtml:home_location />
				<form id="olcKnowShareForm" name="olcKnowShareForm" class="form-inline" onsubmit="return false">
				<input type="hidden" name="curPage" id="curPage"/>
				<input type="hidden" name="sortKey" id="sortKey" value="${vo.sortKey}"/>
				<div class="row">
					<div class="col-md-12 col-sx-12">
						<div class="input-group" style="float:left; width: 20%">
							<div class="input-group-btn btn-group">
								<button onclick="clickDropdown()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
									<span class="caret" style="margin-top: 6px; margin-bottom: 5px;"></span>
								</button>
								<ul class="dropdown-menu" role="menu" id="ctgrDrop">
									<li>
										<div id="ctgrTreeArea" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
									</li>
								</ul>
							</div>
							<input type="text" name="ctgrNm" id="ctgrNm" class="form-control input-sm" style="background-color:#ffffff;" readonly="readonly" value="${vo.ctgrNm}"/>
							<input type="hidden" name="ctgrCd" id="ctgrCd" value="${vo.ctgrCd}"/>
						</div>
						<select name="searchKey"	id="searchKey" class="_enterBind form-control input-sm" title="<spring:message code="common.title.select"/>" style="max-width:130px;float: left;" onchange="showCal();">
							<option value="title"  <c:if test="${vo.searchKey eq 'title'}">selected</c:if> ><spring:message code="common.title.title"/></option>
							<option value="tagNm" <c:if test="${vo.searchKey eq 'tagNm'}">selected</c:if> ><spring:message code="common.title.tag"/></option>
							<option value="regNm" <c:if test="${vo.searchKey eq 'regNm'}">selected</c:if> ><spring:message code="common.title.reguser"/></option>
							<option value="regDttm" <c:if test="${vo.searchKey eq 'regDttm'}">selected</c:if> ><spring:message code="common.title.regdate"/></option>
							<option value="all" <c:if test="${vo.searchKey eq 'all'}">selected</c:if> ><spring:message code="common.title.all"/></option>
						</select>
						<div class="input-group">
							<div id="searchArea">
							<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" value="${vo.searchValue }" style="width: 250px;" title="<spring:message code="common.title.input.searchvalue"/>"/>
							</div>
							<div class="input-group" style="float:left;width:128px;display: none;" id="calArea">
								<!-- <input type="text" maxlength="10" name="searchRegDttm" id="searchRegDttm" class="_enterBind inputDate form-control input-sm"/> -->
								<input type="text" dispName="<spring:message code="common.title.regdate"/>" maxlength="20" name="searchValue" id="searchRegDttm" style="width: 250px;" class="inputDate form-control input-sm" value="${vo.searchValue}"/>
								<!-- <span class="input-group-addon btn-sm" onclick="_clickCalendar('searchRegDttm')" style="cursor:pointer">
									<i class="fa fa-calendar"></i>
								</span> -->
							</div>
							<meditag:datepicker name1="searchRegDttm"/>
							<span class="input-group-addon btn_search" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top:5px;">
					<div class="col-md-12">
						<table class="table table-striped table-bordered">
							<caption class="sr-only"><spring:message code="olc.title.main.manage"/></caption>
							<colgroup>
								<col style="width:50px"/>
								<col style="width:100px;"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:100px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col" style="font-weight: bold;"><spring:message code="common.title.no"/></th>
									<th scope="col" style="font-weight: bold;"><spring:message code="olc.title.category.name"/></th>
									<th scope="col" style="font-weight: bold;">
									<c:choose>
										<c:when test="${fn:startsWith(vo.sortKey,'CARTRG_NM') == true}">
											<c:if test="${vo.sortKey eq 'CARTRG_NM_ASC'}">
										<a href="javascript:setSortKey('CARTRG_NM_DESC')"><spring:message code="olc.title.cartridge.name"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${vo.sortKey eq 'CARTRG_NM_DESC'}">
										<a href="javascript:setSortKey('CARTRG_NM_ASC')"><spring:message code="olc.title.cartridge.name"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CARTRG_NM_ASC')"><spring:message code="olc.title.cartridge.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col" style="font-weight: bold;"><spring:message code="olc.title.cartridge.contents.cnt"/></th>
									<th scope="col" style="font-weight: bold;">
									<c:choose>
										<c:when test="${fn:startsWith(vo.sortKey,'REGDATE') == true}">
											<c:if test="${vo.sortKey eq 'REGDATE_ASC'}">
										<a href="javascript:setSortKey('REGDATE_DESC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${vo.sortKey eq 'REGDATE_DESC'}">
										<a href="javascript:setSortKey('REGDATE_ASC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('REGDATE_ASC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col" style="font-weight: bold;"><spring:message code="common.title.reguser"/></th>
									<th scope="col" style="font-weight: bold;">
									<c:choose>
										<c:when test="${fn:startsWith(vo.sortKey,'HITS') == true}">
											<c:if test="${vo.sortKey eq 'HITS_ASC'}">
										<a href="javascript:setSortKey('HITS_DESC')"><spring:message code="common.title.hits"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${vo.sortKey eq 'HITS_DESC'}">
										<a href="javascript:setSortKey('HITS_ASC')"><spring:message code="common.title.hits"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('HITS_ASC')"><spring:message code="common.title.hits"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${olcShareRelList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${status.count}</td>
									<td class="wordbreak">${item.ctgrNm}</td>
									<td class="wordbreak">
									<c:if test="${item.cntsCnt > 0 }">
										<a href="javascript:preview('${item.userNo}', '${item.cartrgCd}', '${item.cartrgNm}');">
									<c:choose>
										<c:when test="${not empty item.fileSn && item.fileSn ne ''}">
										<img src="<c:url value="/app/file/thumb/${item.fileSn}"/>" style="width:115px;height:85px;" alt="${item.cartrgNm}" class="media-object"/>
										</c:when>
										<c:otherwise>
										<img src="/img/noimage_image.png" alt="${item.cartrgNm}" style="width:115px;height:85px;" >
										</c:otherwise>
									</c:choose>
										${item.cartrgNm}
										</a>
									</c:if>
									<c:if test="${item.cntsCnt == 0 }">
										<c:if test="${not empty item.fileSn && item.fileSn ne ''}">
											<img src="<c:url value="/app/file/thumb/${item.fileSn}"/>" style="height:85px;" alt="${item.cartrgNm}" class="media-object"/>
										</c:if>
										${item.cartrgNm}
									</c:if>
									</td>
									<td class="text-right">${item.cntsCnt}</td>
									<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
									<td class="text-right">${item.regNm}</td>
									<td class="text-right"><div id="hit_${item.cartrgCd}" name="hit_${item.cartrgCd}">${item.hits}</div></td>
								</tr>
								</c:forEach>
								<c:if test="${empty olcShareRelList}">
								<tr>
									<td colspan="9"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				</form>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="list"/>
					</div>
				</div>

<script language="javascript">
	var shareCtgrTree = null;
	var modalBox = null;
	var ItemDTO = { "nodeId" : "", "subCnt" : 0, "olcCnt" : 0, "nodeType" : "root", "ctgrCd" : "", "ctgrDivCd" : ""};

	$(document).ready(function(){
		ItemDTO.sortKey = "REGDATE_DESC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				list(1);
			}
		});
		$(".btn_search").bind("click", function(event) {
			list(1);
		});
		showCal();
		ItemDTO.ctgrDivCd = 'K';
		olcCtgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/home/olc/knowShare/listCtgrJsTree"),
					"data" : function (n) {
						return {
							"ctgrDivCd" : ItemDTO.ctgrDivCd,
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
	});

	function list(page) {
		var searchKey = $("#searchKey").val();

		if(searchKey =="regDttm"){
			$("#searchValue").val( $("#searchRegDttm").val() );
		}

		$('#olcKnowShareForm')
			.find('input[name=curPage]').val(page).end()
			.find('input[name=sortKey]').val(ItemDTO.sortKey).end()
			.attr("action","/home/olc/knowShare/main");
		document.olcShareForm.submit();
	}

	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	function setSelectNode(data) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("title");
		if("root" == id){
			name = "";
		}
		//-- form에 적용
		//$("#ctgrCd").val(id);
		$("#ctgrNm").val(name);
		$("#ctgrDrop").hide();
		location.href="/home/olc/knowShare/main?ctgrCd="+id+"&ctgrNm="+name;
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function preview(userNo, cartrgCd, cartrgNm) {
		var hits = "hit_"+cartrgCd;
		 var url = cUrl("/home/olc/cartrg/previewMain")+"?userNo="+userNo+"${AMPERSAND}cartrgCd="+cartrgCd;
		/*
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/OlcCartrgHome.do"/>"
			+ "?cmd=previewMain&amp;olcCntsVO.userNo="+userNo+"&amp;olcCntsVO.cartrgCd="+cartrgCd+"'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 650);
		modalBox.setTitle(cartrgNm);
		modalBox.show();
		*/
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=1100,height=670";
		var contentsWin = window.open(url, "contentsWin", winOption);
		contentsWin.focus();
		 $("div[name="+hits+"]").text( Number($("#"+hits).text())+1 );
	}

	function changeColor(color){
		$(".modal-title").css("color",color);
	}

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		list(1);
	}

	function showCal(){
		var searchKey = $("#searchKey").val();

		if(searchKey =="regDttm"){
			$("#searchArea").hide();
			$("#calArea").show();
		} else {
			$("#searchArea").show();
			$("#calArea").hide();
		}
	}

</script>
</mhtml:home_body>
</mhtml:home_html>
