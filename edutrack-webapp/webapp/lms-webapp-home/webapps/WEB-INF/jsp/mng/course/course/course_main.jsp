<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/popupbox.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:mng_head>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div id="listLayer" class="rowLayer">
					<form name="Search" onsubmit="return false" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="input-group">
							<div class="input-group-btn btn-group">
	 							<button onclick="clickDropdown()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
	 								<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu" id="ctgrDrop">
									<li style="width:280px;">
										<div id="categoryTreeArea" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:300px"></div>
									</li>
								</ul>
							</div>
							<input type="text" name="crsCtgrNm" id="crsCtgrNm" class="form-control input-sm" readonly="readonly" style="background-color:#ffffff;width:140px;" value="<spring:message code="common.title.all"/>" onclick="clickDropdown()"/>
							<input type="hidden" name="crsCtgrCd" id="crsCtgrCd"/>
						</div>
						<select class="form-control input-sm" name="crsOperMthd" id="crsOperMthd" onchange="listCourse(1)" >
							<option value="" 	selected="selected">과정유형</option>
							<option value="ON"  <c:if test="${vo.crsOperMthd eq 'ON' }">selected</c:if>>온라인</option>
							<option value="OF" <c:if test="${vo.crsOperMthd eq 'OF' }">selected</c:if>>오프라인</option>
							<option value="BL"  <c:if test="${vo.crsOperMthd eq 'BL' }">selected</c:if>>혼합교육</option>
						</select>
						<div class="input-group" style="width:180px;">
							<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="<spring:message code="common.title.all"/>"/>
							<span class="input-group-addon" onclick="listCourse()" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 text-right">
						<button class="btn btn-primary btn-sm" onclick="crsWrite()"><spring:message code="course.title.course.write"/></button>
						<select name="listScale" id="listScale" onchange="listCourse(1)" class="form-control input-sm">
							<option value="10">10</option>
							<option value="20" selected="selected">20</option>
							<option value="40">40</option>
							<option value="60">60</option>
							<option value="80">80</option>
							<option value="100">100</option>
							<option value="200">200</option>
						</select>
					</div>
					<input type="submit" value="submit" style="display:none" />
					</form>
					<div class="col-md-12">
						<div id="courseList" style="margin-top:5px;">
							<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:60px"/>
									<col style="width:auto;min-width:90px;"/>
									<col style="width:auto"/>
									<col style="width:auto;min-width:110px;"/>
									<col style="width:auto;min-width:110px;"/>
									<col style="width:auto;min-width:110px;"/>
									<col style="width:75px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순서</th>
										<th scope="col"><spring:message code="course.title.course.code"/></th>
										<th scope="col"><spring:message code="course.title.course.category"/></th>
										<th scope="col"><spring:message code="course.title.course.name"/></th>
										<th scope="col">과정유형</th>
										<th scope="col"><spring:message code="common.title.useyn"/></th>
										<th scope="col"><spring:message code="common.title.manage"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="8"><spring:message code="course.message.course.select.category"/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div id="workLayer" class="rowLayer">
					<div class="col-md-12" id="workBody">

					</div>
				</div>
			</div>
		</div>
	</div>
	
</section>
<script type="text/javascript">
	var ctgrTree = null;
	var modalBox = null;
	var ItemDTO = {
			crsCtgrCd : "",
			crsOperMthd : "${crsOperMthd}",
			sortKey : ""
		};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		ItemDTO.sortKey = "CRS_NM_ASC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listCourse(1);
			}
		}
		listCourse(1);
		showArea('list');
		//$('#categoryTreeArea').jstree("destroy").empty();
		ctgrTree = $('#categoryTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "checkbox", "cookies" ],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/course/category/listCategoryJsTree"),
					"data" : function (n) {
						return {
							"id" : n.attr ? n.attr("id") : ""
						};
					}
				}
			},
			"types" : {
				"max_depth" : -2,
				"max_children" : -2,
				"valid_children" : [ "root" ],
				"types" : {
					"default" : {
						"valid_children" : "none",
						"icon" : { "image" : "/img/framework/icon/filetype/file.png" },
						"start_drag" : false
					},
					"contents" : {
						"valid_children" : "all",
						"icon" : { "image" : "/img/framework/icon/icon_lesson.gif" },
						"start_drag" : false
					},
					"category" : {
						"valid_children" :  "all",
						"icon" : { "image" : "/img/framework/icon/icon_contents.gif" },
						"start_drag" : false
					},
					"root" : {
						"valid_children" : "all",
						"icon" : { "image" : "/img/framework/icon/icon_course.gif" },
						"start_drag" : false
					}
				}
			},
			"themes" : { "theme" : "default", "dots" : true }
		}).bind("click.jstree", function(e, data) {
			clickjstree();
		}).bind("loaded.jstree", function(e, data){
			//-- 페이지 로딩시
			ItemDTO.crsCtgrCd = "FIRST";
			clickjstree();
		});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function showArea(str) {
		$(".rowLayer").hide();
		$("#"+str+"Layer").show();
	}

	function closeWriteArea(){
		$("#workBody").html("");
		showArea('list');
	}

	function clickjstree() {
		var crsCtgrCd = "";
		var crsCtgrNm = "<spring:message code="common.title.all"/>";
		var crsCtgrCnt = 0;
		ctgrTree.jstree("get_checked", null, true).each(function(i, data){
			crsCtgrCd += "/"+this.id;
			crsCtgrCnt++;
			crsCtgrNm = this.title;
		});

		if(ItemDTO.crsCtgrCd == crsCtgrCd) {
			//-- 변경된것이 없으면.... 그냥 넘김.
		}
		else {
			//-- 변경된 것이 있으면 과목 목록 호출.
			ItemDTO.crsCtgrCd = crsCtgrCd;
			if(crsCtgrCnt > 1) {
				crsCtgrNm = "<spring:message code="common.title.select.multi"/>";
			}
			$("#crsCtgrNm").val(crsCtgrNm);
			listCourse(1);
			$("#ctgrDrop").hide();
		}
	}

	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	/**
	 * 과정 목록 조회
	 */
	function listCourse(page) {
		var crsNm = $('#searchKey').val();
		var listScale = $("#listScale option:selected").val();
		var crsOperMthd = $("#crsOperMthd option:selected").val();
		var useYn = "";
		if($("#chkUse").is(":checked") ){
			useYn = "Y";
		} else {
			useYn = "";
		}

		$('#courseList')
			.load(cUrl("/mng/course/course/listCourse"),
				{ 
				  "crsCtgrCd":ItemDTO.crsCtgrCd,
				  "crsNm":crsNm,
				  "sortKey":ItemDTO.sortKey,
				  "useYn":useYn,
				  "curPage":page,
				  "listScale":listScale,
				  "crsOperMthd":crsOperMthd,
				 }
			);

	}

	/**
	 * 과정 등록 폼
	 */
	function crsWriteForm(crsCtgrCd) {

		var url = cUrl("/mng/course/course/addFormCourse");
		$("#workBody")
			.load(url, {
				"crsCtgrCd" : crsCtgrCd ,
				"crsOperMthd" : ItemDTO.crsOperMthd
			});
		showArea('work');
	}

	/**
	 * 과정 수정 폼
	 */
	function crsEditForm(crsCd) {
		var url = cUrl("/mng/course/course/editFormCourse");
		$("#workBody")
			.load(url, {
				"crsCd" : crsCd
			});
		showArea('work');
	}

	/**
	 * 과정 관리 폼
	 */
	function crsMngForm(crsCd) {
		var url = cUrl("/mng/course/course/mngCourse");
		$("#workBody")
			.load(url, {
				"crsCd" : crsCd
			});
		showArea('work');
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listCourse(1);
	}
	
	/**
	 * 과정 등록 팝업
	 */
	
	 var ctgrTree = null;
	 var modalBox = null;
	 var ItemDTO = {
		  "crsCtgrCd":ItemDTO.crsCtgrCd,
		  "crsNm":ItemDTO.crsNm,
		  "sortKey":ItemDTO.sortKey
		};
	
	/**
	 * 과정 등록 폼
	 */
	function crsWrite() {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/course/addCoursePop"/>"
			+ "?crsCd=${vo.crsCd}+'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 750);
		modalBox.setTitle("<spring:message code="course.title.course.write"/>");
		modalBox.show();
	}
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 * 과정 수정 폼
	 */
	 function crsEditForm(crsCd) {
		var addContent = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/course/editFormCoursePop"/>"
			+ "?crsCd="+crsCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 800);
		modalBox.setTitle("<spring:message code="course.title.course.edit"/>");
		modalBox.show();
	}
</script>