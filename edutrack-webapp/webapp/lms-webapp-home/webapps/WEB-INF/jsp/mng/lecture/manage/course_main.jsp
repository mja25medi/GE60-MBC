<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/popupbox.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:mng_head>

<mhtml:mng_body>
	<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
	<div class="dvcontents">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<colgroup>
				<col style="width:220px;"/>
				<col style="width:auto;"/>
			</colgroup>
			<tbody>
			<tr>
				<td valign="top">
					<div style="width:210px; height:25px;"></div>
					<div id="categoryTreeArea" style="font-size:12px;float:none;overflow:auto;border:2px solid #4FADBC;width:206px;height:400px"></div>
				</td>
				<td valign="top">
					<form name="Search" onsubmit="return false">
					<div style="width:100%; height:25px;">
						<div style="float:left;padding-right:5px">
							<select name="eduTeamCd" id="eduTeamCd" class="form-control input-sm" onChange="listCreateCourse(1)">
								<c:forEach var="item" items="${eduTeamCdList}" varStatus="status">
								<option value="${item.codeCd}" <c:if test="${vo.eduTeamCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
								</c:forEach>				
							</select>
						</div>
						<div style="float:left;padding-right:5px">
							<meditag:selectbox width="180" height="100" fieldName="crsCd" formName="Search" onChange="listCreateCourse(1);"/>
						</div>
						<div style="float:left">
							<meditag:selectbox width="70" fieldName="creYear" formName="Search" height="100" id="creYear" onChange="listCourse(1)"/>
							<meditag:selectboxOption formName="Search" value="" text="년도전체" fieldName="creYear"/>
							<c:forEach items="${yearList}" var="year">
							<c:if test="${year == curYear}">
							<meditag:selectboxOption formName="Search" value="${year}" text="${year} 년" fieldName="creYear" selected="yes"/>
							</c:if>
							<c:if test="${year != curYear}">
							<meditag:selectboxOption formName="Search" value="${year}" text="${year} 년" fieldName="creYear"/>
							</c:if>
							</c:forEach>
						</div>
						<div style="float:left;">
							<input type="text" name="creTerm" id="creTerm" style="width:30px" class="txt" maxlength="10" title="기수 입력"/>
							<input type="text" name="searchKey" id="searchKey" style="width:90px" class="_enterBind txt" maxlength="20" title="검색어 입력"/>
							<input type="image" src="<c:url value="/img/framework/icon/icon_function_search.gif"/>" alt="검색" title="검색" onclick="listCreateCourse(1)" />
						</div>
						<div style="float:right">
							<meditag:selectbox width="50" fieldName="listScale" formName="Search" height="100" id="listScale" onChange="listCreateCourse(1)"/>
							<meditag:selectboxOption formName="Search" value="10" text="10" fieldName="listScale"/>
							<meditag:selectboxOption formName="Search" value="20" text="20" fieldName="listScale" selected="yes"/>
							<meditag:selectboxOption formName="Search" value="40" text="40" fieldName="listScale"/>
							<meditag:selectboxOption formName="Search" value="60" text="60" fieldName="listScale"/>
							<meditag:selectboxOption formName="Search" value="80" text="80" fieldName="listScale"/>
							<meditag:selectboxOption formName="Search" value="100" text="100" fieldName="listScale"/>
							<meditag:selectboxOption formName="Search" value="200" text="200" fieldName="listScale"/>
						</div>
					</div>
					</form>
					<div id="courseList">
						<table summary="과정 목룍 표" style="width:100%" class="table_list">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:60px"/>
								<col style="width:50px"/>
								<col style="width:140px"/>
								<col style="width:60px"/>
								<col style="width:60px"/>
								<col style="width:60px"/>
								<col style="width:60px"/>
								<col style="width:60px"/>
								<col style="width:50px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col" >과정 명</th>
									<th scope="col" >년도</th>
									<th scope="col" >기수</th>
									<th scope="col" >교육기간</th>
									<th scope="col" >과제</th>
									<th scope="col" >시험</th>
									<th scope="col" >포럼</th>
									<th scope="col" >설문</th>
									<th scope="col" >프로젝트</th>
									<th scope="col" >관리</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="10">*분류를 선택 하세요.</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
	// 팝업박스
	var systemPopBox = PopupBox("", "", "", "width=750,height=400,style=normal,bgcolor=,modal=yes,resizable=no,move=yes,titlebar=yes,position=center,top=0,left=0","set2");
	var crsPopBox	= systemPopBox;
	var ItemDTO = {
			crsCtgrCd : ""
		}

	var ctgrTree = new Object();

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listCreateCourse(1);
			}
		}
		categoryList();
		listCourse();
	});

	function categoryList() {
		ctgrTree = $('#categoryTreeArea')
		.jstree({
			"plugins" : [ "themes", "json_data", "ui", "crrm", "dnd", "types", "cookies", "hotkeys", "checkbox" ],
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
		})
		.bind("reselect.jstree", function(e, data){
			listCourse();
		})
		.bind("click.jstree", function(e, data) {
			listCourse();
		});

	}

	/**
	 * 과정 선택 박스
	 */
	function listCourse() {
		ItemDTO.crsCtgrCd = "";
		ctgrTree.jstree("get_checked", null, true).each(function(){
			ItemDTO.crsCtgrCd += "/"+this.id;
		})
		var creYear = $('#creYear option:selected').val();
		$.getJSON( cUrl("/mng/course/createCourse/listCourseJson"),		// url
				{ 
				  "courseVO.crsCtgrCd" : ItemDTO.crsCtgrCd,
				  "courseVO.creYear" : creYear
				},			// params
				listCourseCallback				// callback function
			);
	}

	/**
	 * 과정 선택 박스 콜백
	 */
	function listCourseCallback(ProcessResultListDTO) {
		var retList = ProcessResultListDTO.returnList;
		var listStr = "";
		SearchcrsCd.removeOption();
		SearchcrsCd.addOption('과정선택','','');
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			if(item.creCrsCnt > 0)
				SearchcrsCd.addOption(item.crsNm,item.crsCd,'');
		}
		listCreateCourse(1);
	}

	/**
	 * 개설 과정 목록 조회
	 * 개설 년도별 개설 과정 목록
	 */
	function listCreateCourse(page) {
		ItemDTO.crsCtgrCd = "";
		ctgrTree.jstree("get_checked", null, true).each(function(){
			ItemDTO.crsCtgrCd += "/"+this.id;
		})
		var crsCreNm = $('#searchKey').val();
		var crsCd = $('#crsCd').val();
		var creTerm = $('#creTerm').val();
		var creYear = $("#creYear option:selected").val();
		var listScale = $("#listScale option:selected").val();
		var eduTeamCd = SearcheduTeamCd.getSelectedValue();
		if(eduTeamCd == '99') eduTeamCd = '';
		displayWorkProgress();
		$('#courseList')
			.load(cUrl("/mng/lecture/manage/list"),
				{ 
				  "createCourseVO.crsCtgrCd":ItemDTO.crsCtgrCd,
				  "createCourseVO.crsCd":crsCd,
				  "createCourseVO.crsCreNm":crsCreNm,
				  "createCourseVO.creYear":creYear,
				  "createCourseVO.creTerm":creTerm,
				  "createCourseVO.eduTeam":eduTeamCd,
				  "curPage":page,
				  "listScale":listScale
			   }
			);
		closeWorkProgress();
	}

</script>
</mhtml:mng_body>
</mhtml:mng_html>