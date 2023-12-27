<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<form name="Search" id="Search" onsubmit="return false" class="form-inline">
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
						<input type="text" name="sbjCtgrNm" id="sbjCtgrNm" class="form-control input-sm" style="width:140px;" value="<spring:message code="common.title.all"/>"/>
						<input type="hidden" name="sbjCtgrCd" id="sbjCtgrCd"/>
					</div>
					<div class="input-group" style="width:180px;">
						<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" maxlength="20"  placeholder="<spring:message code="common.title.all"/>"/>
						<span class="input-group-addon" onclick="listSubject(1);" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 text-right">
					<div class="checkbox">
						<label style="line-height:20px;"><input type="checkbox" id="useYn" name="useYn" value="Y" onclick="listSubject(1)"/> <spring:message code="course.title.subject.useyn"/></label>
					</div>
					<a href="javascript:sbjWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write.subject"/> </a>
					<select name="listScale" id="listScale" onchange="listSubject(1)" class="form-control input-sm">
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
				<div class="col-md-12"  style="margin-top:5px;">
					<div id="subjectList">
						<table summary="<spring:message code="course.title.subject.category.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:100px"/>
								<col style="width:250px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:50px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.title.subject.code"/></th>
									<th scope="col"><spring:message code="course.title.subject.code"/></th>
									<th scope="col"><spring:message code="course.title.subject.category"/></th>
									<th scope="col"><spring:message code="course.title.subject.name"/></th>
									<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
									<th scope="col"><spring:message code="common.title.stats"/></th>
									<th scope="col"><spring:message code="common.title.edit"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="7"><spring:message code="course.message.subject.select.category"/></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	var ItemDTO = {
		sbjCd : "",
		sbjNm : "",
		sbjCtgrTypeCd : "OF",
		sbjCtgrCd : "",
		sortKey2 : ""
	}
	var ctgrTree = null;

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listSubject();
			}
		}

		ctgrTree = $('#categoryTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "checkbox", "cookies" ],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/course/subject/listCtgrJsTree"),
					"data" : function (n) {
						return {
							"sbjCtgrTypeCd" : ItemDTO.sbjCtgrTypeCd,
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
			ItemDTO.sbjCtgrCd = "FIRST";
			clickjstree();
		});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function clickjstree() {
		var sbjCtgrCd = "";
		var sbjCtgrNm = "<spring:message code="common.title.all"/>";
		var sbjCtgrCnt = 0;
		ctgrTree.jstree("get_checked", null, true).each(function(i, data){
			sbjCtgrCd += "/"+this.id;
			sbjCtgrCnt++;
			sbjCtgrNm = this.title;
		});

		if(ItemDTO.sbjCtgrCd == sbjCtgrCd) {
			//-- 변경된것이 없으면.... 그냥 넘김.
		}
		else {
			//-- 변경된 것이 있으면 과목 목록 호출.
			ItemDTO.sbjCtgrCd = sbjCtgrCd;
			if(sbjCtgrCnt > 1) {
				sbjCtgrNm = "<spring:message code="common.title.select.multi"/>";
			}
			$("#sbjCtgrNm").val(sbjCtgrNm);
			listSubject(1);
			//$("#ctgrDrop").hide();
		}
	}

	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	/**
	 * 과목 목록 조회
	 */
	function listSubject(page) {
		ItemDTO.sbjCtgrCd = "";
		ctgrTree.jstree("get_checked", null, true).each(function(){
			ItemDTO.sbjCtgrCd += "/"+this.id;
		})
		var sbjNm = $('#searchKey').val();
		var listScale = $("#listScale option:selected").val();
		var useYn = "";
		if($("#useYn").is(":checked")) useYn = "Y";

		$('#subjectList')
			.load(
				cUrl("/mng/course/subject/listOffline"),
				{ 
				  "sbjNm":sbjNm,
				  "sbjCtgrCd":ItemDTO.sbjCtgrCd,
				  "sortKey" : ItemDTO.sortKey,
				  "useYn":useYn,
				  "curPage":page,
				  "listScale":listScale
				}
			);
	}

	/**
	 * 과목 등록 폼
	 */
	function sbjWrite() {
		var sbjCtgrCd = ItemDTO.sbjCtgrCd;
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/subject/addFormOfflinePop"/>"
			+ "?sbjCtgrCd="+sbjCtgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 450);
		modalBox.setTitle("<spring:message code="course.title.subject.write"/>");
		modalBox.show();
	}

	/**
	 * 과목 수정 폼
	 */
	function sbjEdit(sbjCd) {
		var addContent = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/subject/editFormOfflinePop"/>"
			+ "?sbjCd="+sbjCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 450);
		modalBox.setTitle("<spring:message code="course.title.subject.edit"/>");
		modalBox.show();
	}

	function subCntInfo(sbjCd) {
		var addContent = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/subject/viewSubInfoOfflinePop"/>"
			+ "?sbjCd="+sbjCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 600);
		modalBox.setTitle("<spring:message code="course.title.course.use.info"/>");
		modalBox.show();
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listSubject(1);
	}
</script>
