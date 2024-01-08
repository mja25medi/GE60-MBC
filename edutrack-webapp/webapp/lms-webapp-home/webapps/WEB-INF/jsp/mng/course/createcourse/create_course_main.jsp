<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsCtgrCd" value="${vo.crsCtgrCd}"/>
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
							<div class="input-group">
								<select name="creYear" id="creYear" onchange="listCourse(1)" class="form-control input-sm">
								<c:forEach items="${yearList}" var="year">
									<c:if test="${year == curYear}">
									<option value="${year}" selected="selected">${year} </option>
									</c:if>
									<c:if test="${year != curYear}">
									<option value="${year}">${year} </option>
									</c:if>
								</c:forEach>
								</select>
							</div>
							<div class="input-group" style="width:160px;">
								<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="<spring:message code="common.title.all"/>"/>
								<span class="input-group-addon" onclick="listCourse()" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right">
							<button class="btn btn-primary btn-sm" onclick="crsCreWriteFormPop()" ><spring:message code="button.write.round"/></button>
						 	<%-- <div class="checkbox">
								<label style="line-height:20px;"><input type="checkbox" id="createdOnly" name="createdOnly" value="Y" onclick="listCourse(1)"/> <spring:message code="course.title.createcourse.createdonly"/></label>
							</div> --%>
							<!-- <select name="listScale" id="listScale" onchange="listCreateCourse(1)" class="form-control input-sm">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select> -->
						</div>
						</form>
						<div class="col-md-12" style="margin-top:5px;">
							<div id="courseList">
								<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:auto;min-width:90px;"/>
										<col style="width:auto"/>
										<col style="width:auto;min-width:90px;"/>
										<col style="width:auto;min-width:110px;"/>
										<col style="width:80px"/>
										<col style="width:80px"/>
										<col style="width:80px"/>
										<col style="width:75px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="course.title.course.category"/></th>
											<th scope="col"><spring:message code="course.title.course.name"/></th>
											<th scope="col"><spring:message code="course.title.course.edumthd"/></th>
											<th scope="col"><spring:message code="course.title.course.crstype"/></th>
											<th scope="col"><spring:message code="common.title.useyn"/></th>
											<th scope="col"><spring:message code="course.title.createcourse.create.cnt"/></th>
											<th scope="col"><spring:message code="course.title.createcourse.creterm"/></th>
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
	var modalBox = null;
	var ctgrTree = null;
	var ItemDTO = {
			crsCtgrCd : "",
			sortKey : "",
			crsCd : "",
			sortKey2 : ""
		}

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
		showArea('list');
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

	function showArea(str) {
		$(".rowLayer").hide();
		$("#"+str+"Layer").show();
	}

	function closeWriteArea(){
		$("#writeLayer").html("");
		listCourse();
		showArea('list');
	}

	/**
	 * 개설 과정 목록 조회
	 * 개설 년도별 개설 과정 목록
	 */
	 function listCreateCourse(page) {
		var crsNm = $('#searchKey').val();
		var creYear = $("#creYear option:selected").val();
		var listScale = $("#listScale option:selected").val();
		var creTypeCd = $("#creTypeCd option:selected").val();
		var crsCreNm = $('#searchKey2').val();
		var createdOnly = "N";
		if(ItemDTO.sortKey2 == "" || ItemDTO.sortKey2 == undefined){
			ItemDTO.sortKey2 = "CRS_CRE_NM";
		}
		if($("#createdOnly").is(":checked")) createdOnly = "Y";

		$('#workBody')
			.load(cUrl("/mng/course/createCourse/listCreateCourse"),
				{ 
				  "crsCreNm":crsCreNm,
				  "crsCd" : ItemDTO.crsCd ,
				 /*  "creYear" : creYear, */
				  "sortKey" : ItemDTO.sortKey2,
				  "curPage": page,
				  "listScale": listScale,
				  "creTypeCd": creTypeCd
			    },
			    function(d) {
		        	$('.inputNumber').inputNumber();
		        }
			);
		showArea('work');
	}

	/**
	 * 개설 과정 등록 폼
	 */
	function crsCreWriteForm(crsCd,crsOperType) {
		var creYear = $("#creYear option:selected").val();
		var creCnt = $('#creCnt_'+crsCd).val();
		if(creCnt <= 0) {
			alert('<spring:message code="common.message.alert.input.over" arguments="1"/>');
			$('#creCnt_'+crsCd).focus();
			return;
		}
		var url = cUrl("/mng/course/createCourse/addFormCreateCourse");
		$("#workBody")
			.load(url, {
				"crsOperType" :crsOperType,
				"crsCd" : crsCd ,
				"creYear" : creYear,
				"creCnt" : creCnt
			});
		showArea('work');
	}
	
	/**
	 * 개설 과정 등록 팝업
	 */
	function crsCreWriteFormPop(crsCd,crsOperType) {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/createCourse/addFormCreateCoursePop"/>"
			+ "?crsCd="+crsCd+"+'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 750);
		modalBox.setTitle("<spring:message code="button.write.round"/>");
		modalBox.show();
	}
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 개설 과정 목록
	 */
	/* function listCreateCourse(crsCd,crsOperType) {
		if(crsCd != undefined && crsCd !="") ItemDTO.crsCd = crsCd;
		var creYear = $("#creYear option:selected").val();
		var url = cUrl("/mng/course/createCourse/listCreateCourse");
		var crsCreNm = $('#searchKey2').val();
		//2015.11.18 (redmine : KNOTZ_NG_223) 수정
		//sort값이 정의되지 않았을 때 기수별 정렬을 넣는다.
		if(ItemDTO.sortKey2 == "" || ItemDTO.sortKey2 == undefined){
			ItemDTO.sortKey2 = "CRS_CRE_NL";
		}
		$("#workBody")
			.load(url, {
				"crsCreNm":crsCreNm,
				"crsOperType" :crsOperType,
				"crsCd" : ItemDTO.crsCd ,
				"creYear" : creYear,
				"sortKey" : ItemDTO.sortKey2
			});
		showArea('work');
	} */

	/**
	 * 개설 과정 정보 관리 폼 (개설 과정 목록에서..)
	 */
	function creCrsInfoMngForm(crsCreCd) {
		var url = cUrl("/mng/course/createCourse/editFormCreateCourse");
		$("#workBody")
			.load(url, {
				"crsCreCd" : crsCreCd
			});
		showArea('work');
	}

	/**
	 * 개설 과정 운영 관리 폼 (개설 과정 목록에서..)
	 */
	function creCrsOperationMngForm(crsCreCd) {
		var url = cUrl("/mng/lecture/manage/manage");
		$("#workBody")
			.load(url, {
				"crsCreCd" : crsCreCd
			});
		showArea('work');
	}

	/**
	 * 개설 과정 학습자 관리 폼 (개설 과정 목록에서..)
	 */
	function creCrsStudentMngForm(crsCreCd) {
		var url = cUrl("/mng/std/student/manageStudent");
		$("#workBody")
			.load(url, {
				"crsCreCd" : crsCreCd
			});
		showArea('work');
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listCourse(1);
	}

	function setSortKey2(sortKey) {
		ItemDTO.sortKey2 = sortKey;
		listCreateCourse();
	}

	/**
	 * 개설 과정 수정
	 */
	function editCreCrs(crsCreCd) {
		var url = cUrl("/mng/course/createCourse/editCreateCoursePop");
		$("#workBody")
			.load(url, {
				"crsCreCd" : crsCreCd
			});
		showArea('work');
	}
	
	/**
	 * 개설 과정 수정 팝업
	 */
	function editCreCrsPop(crsCreCd) {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/createCourse/editCreateCoursePop"/>"
			+ "?crsCreCd="+crsCreCd+"+'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 750);
		modalBox.setTitle("<spring:message code="button.write.round"/>");
		modalBox.show();
	}
	
	/**
	 * qr코드 보기 팝업
	 */
	function readCourseQrPop(qrFileSn) {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/createCourse/readCourseQrPop"/>"
			+ "?qrFileSn="+qrFileSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 550);
		modalBox.setTitle("QR코드 보기");
		modalBox.show();
	}

</script>
