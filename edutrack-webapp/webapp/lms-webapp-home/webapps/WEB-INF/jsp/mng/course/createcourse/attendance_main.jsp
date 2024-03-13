<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
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
				<div class="row" id="content">
					<div class="rowLayer" id="listLayer">
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
							<div class="input-group" style="width:160px;">
								<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="<spring:message code="common.title.all"/>"/>
								<span class="input-group-addon" onclick="listCourse()" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right">
						 	<div class="checkbox">
								<label style="line-height:20px;"><input type="hidden" id="createdOnly" name="createdOnly" value="Y" onclick="listCreateCourse(1)"/></label>
							</div>
							<select name="listScale" id="listScale" onchange="listCreateCourse(1)" class="form-control input-sm">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
						</div>
						</form>
						<div class="col-md-12" style="margin-top:5px;">
							<div id="courseList">
								<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:auto;min-width:90px;"/>
										<col style="width:auto"/>
										<col style="width:60px"/>
										<col style="width:80px"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="course.title.course.code"/></th>
											<th scope="col"><spring:message code="course.title.course.name"/></th>
											<th scope="col"><spring:message code="course.title.createcourse.creterm"/></th>
											<th scope="col"><spring:message code="course.title.createcourse.enrollcnt"/></th>
											<th scope="col"><spring:message code="course.title.createcourse.duration.edu"/></th>
											<th scope="col"><spring:message code="common.title.manage"/></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="8"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="rowLayer" id="workLayer">
						<div class="col-md-12" id="workBody">
						</div>
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
		ItemDTO.sortKey2 = "CRE_TERM_ASC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listCreateCourse(1);
			}
		}
		showArea("list");
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
			listCreateCourse(1);
			$("#ctgrDrop").hide();
		}
	}

	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	/**
	 * 회차 목록 조회
	 */
	 function listCreateCourse(page) {
			var crsNm = $('#searchKey').val();
			var creYear = $("#creYear option:selected").val();
			var listScale = $("#listScale option:selected").val();
			var crsCreNm = $('#searchKey2').val();
			var createdOnly = "N";
			if(ItemDTO.sortKey2 == "" || ItemDTO.sortKey2 == undefined){
				ItemDTO.sortKey2 = "CRS_CRE_NM";
			}
			if($("#createdOnly").is(":checked")) createdOnly = "Y";

			$('#courseList')
			.load(cUrl("/mng/course/createCourse/attendance/courseList"),
				{ 
				  "crsCtgrCd":ItemDTO.crsCtgrCd,
				  "crsNm":crsNm,
				  "sortKey":ItemDTO.sortKey,
				  "curPage":page,
				  "listScale":listScale
				 }
			);
		}
	
	 /**
	 * 출석부 관리
	 */
	function listAttend(crsCreCd) {
		var url = cUrl("/mng/course/createCourse/attendance/listAttend");
		$("#workBody")
			.load(url, {
				"crsCreCd" : crsCreCd
			});
		showArea('work');
	}
	 
	/**
	 * 엑셀 다운로드
	 */
	function excelDownload(crsCreCd) {
		var url = cUrl("/mng/course/createCourse/attendance/excelDownloadAttendList")+"?crsCreCd="+crsCreCd;
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		$('#_m_download_iframe').attr('src', url);
	}

</script>