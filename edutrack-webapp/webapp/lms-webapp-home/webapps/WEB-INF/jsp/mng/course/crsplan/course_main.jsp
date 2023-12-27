<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsCtgrCd" value="${vo.crsCtgrCd}"/>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
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
					<div class="input-group" style="width:160px;">
						<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="<spring:message code="common.title.all"/>"/>
						<span class="input-group-addon" onclick="listCourse(1)" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="col-mg-6 col-sm-6 text-right">
					<button class="btn btn-primary btn-sm" onclick="javascript:addAllPlan('${item.crsCd}');" ><spring:message code="button.add.all"/></button>
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
				</form>

				<div class="col-md-12"  style="margin-top:5px;">
					<div id="courseList">
						<table summary="<spring:message code="course.title.course.manage"/>" style="width:100%" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:50px"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:90px"/>
								<col style="width:90px"/>
								<col style="width:90px"/>
								<col style="width:56px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.year"/></th>
									<th scope="col"><spring:message code="course.title.course.category"/></th>
									<th scope="col"><spring:message code="course.title.course.name"/></th>
									<th scope="col"><spring:message code="course.title.course.edumthd"/></th>
									<th scope="col"><spring:message code="course.title.course.crstype"/></th>
									<th scope="col"><spring:message code="course.title.createcourse.educnt"/></th>
									<th scope="col"><spring:message code="course.title.plan.personnel.term"/></th>
									<th scope="col"><spring:message code="course.title.plan.personnel.year"/></th>
									<th scope="col"><spring:message code="common.title.add"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="9"><spring:message code="course.message.course.nodata"/></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			<form id="crsPlanFrom" name="crsPlanFrom" onsubmit="return false" >
			<input type="hidden" name="crsCd" id="inCrsCd" value="${vo.crsCd }" />
			<input type="hidden" name="creYear" id="inCreYear" value="${vo.creYear }" />
			<input type="hidden" name="creCnt" id="inCreCnt" value="${vo.creCnt }" />
			<input type="hidden" name="yearNopCnt" id="inYearNopCnt" value="${vo.yearNopCnt }" />
			<input type="hidden" name="termNopCnt" id="inTermNopCnt" value="${vo.termNopCnt }" />
			</form>
		</div>
	</div>
</section>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = {
			crsCtgrCd : "",
			sortKey : ""
		}
	var ctgrTree = null;

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		ItemDTO.sortKey="CRS_NM_ASC";
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
			listCourse(1);
			$("#ctgrDrop").hide();
		}
	}

	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}


	/**
	 * 개설 과정 목록 조회
	 * 개설 년도별 개설 과정 목록
	 */
	function listCourse(page) {
		var crsNm = $('#searchKey').val();
		var creYear = $("#creYear option:selected").val();
		var listScale = $("#listScale option:selected").val();

		$('#courseList')
			.load(cUrl("/mng/course/coursePlan/listCourse"),
				{ 
				  "crsCtgrCd":ItemDTO.crsCtgrCd,
				  "crsNm":crsNm,
				  "creYear":creYear,
				  "sortKey":ItemDTO.sortKey,
				  "curPage" :page,
				  "listScale" : listScale
		        },
		        function(d) {
		        	$('.inputNumber').inputNumber();
		        }
			);
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#crsPlanFrom').attr("action","/mng/course/coursePlan/" + cmd);
		$('#crsPlanFrom').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			listCourse();
		} else {
			// 비정상 처리
		}
	}

	function addPlan(crsCd) {
		var creYear = $("#creYear > option:selected").val();
		var termNopCnt = $("#termNopCnt_"+crsCd).val();
		var yearNopCnt = $("#yearNopCnt_"+crsCd).val();
		if(parseInt(yearNopCnt,10) >= parseInt(termNopCnt,10)) {
			$('#inCrsCd').val(crsCd);
			$("#inCreYear").val(creYear);
			$("#inCreCnt").val($("#creCnt_"+crsCd).val());
			$("#inTermNopCnt").val(termNopCnt);
			$("#inYearNopCnt").val(yearNopCnt);
			process("add");	// cmd
		} else {
			alert('<spring:message code="course.message.plan.alert.countcheck"/>');
			return;
		}
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listCourse(1);
	}

	function checkInteger(obj) {
		var objValue = obj.value.replace(".","");
		if(objValue == ''){
			obj.value = 0;
		}else{
			var value = parseInt(objValue,10);
			obj.value = value;
		}
	}
	function addAllPlan(crsCd) {
		var i = 0;
		var yearNopCnt = 0;
		var termNopCnt = 0;
		var creYear = $("#creYear > option:selected").val();

		//validation 체크
		$('.termNopCntClass').each(function() {
			termNopCnt = $(this).val();
			yearNopCnt = $(this).closest('tr').find('.yearNopCntClass').val();
			if(parseInt(yearNopCnt,10) >= parseInt(termNopCnt,10)) {
				;
			}else{
				i++;
			}
		});
		if(i>0){
			alert('<spring:message code="course.message.plan.alert.countcheck"/>');
			return;
		}

		//input name 세팅
		$('.termNopCntClass').each(function() {
			var termNopCntClass = 'addCrsPlanList[' + i + '].termNopCnt';
			$(this).attr('name', termNopCntClass);

			var yearNopCntClass = 'addCrsPlanList[' + i + '].yearNopCnt';
			$(this).closest('tr').find('.yearNopCntClass').attr('name', yearNopCntClass);

			var creCntClass = 'addCrsPlanList[' + i + '].creCnt';
			$(this).closest('tr').find('.creCntClass').attr('name', creCntClass);

			var creYearClass = 'addCrsPlanList[' + i + '].creYear';
			$(this).closest('tr').find('.creYearClass').val(creYear);
			$(this).closest('tr').find('.creYearClass').attr('name', creYearClass);

			var creCdClass = 'addCrsPlanList[' + i + '].crsCd';
			$(this).closest('tr').find('.creCdClass').attr('name', creCdClass);
			i++;
		});
		processAll("addAll");	// cmd
	}

	/**
	 * 서브밋 처리
	 */
	function processAll(cmd) {
		$('#crsPlanAllAddForm').find('input[name=cmd]').val(cmd);
		$('#crsPlanAllAddForm').ajaxSubmit(processCallback);
	}

</script>
