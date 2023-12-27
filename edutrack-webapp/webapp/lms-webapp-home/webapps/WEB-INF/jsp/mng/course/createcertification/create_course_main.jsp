<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsCtgrCd" value="${vo.crsCtgrCd}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
					<div class="rowLayer" id="listLayer">
						<div class="col-md-6 col-sm-6">
						<div class="input-group">
							<select name="crsCd" id="crsCd" class="form-control input-sm" style="float:left;width: 25%;" onchange="listCreateCourse();">
								<c:forEach items="${courseList }" var="courseItem">
									<option value="${courseItem.crsCd }" <c:if test="${createCourseVO.crsCd eq courseItem.crsCd }">selected</c:if>>${courseItem.crsNm}</option>
								</c:forEach>
							</select>
							<input type="text" name="searchKey" id="searchKey" value="${createCourseVO.crsCreNm }" class="_enterBind2 form-control input-sm" style="float:left;width: 75%;" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="과정명을 입력해주세요"/>
							<span class="input-group-addon" onclick="listCreateCourse();" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right">
						<button class="btn btn-primary btn-sm"  onclick="crsCreWriteForm('')">과정<spring:message code="button.write"/></button>
						<select name="listScale" id="listScale" onchange="listCreateCourse(1)"  class="form-control input-sm" style="width:100px; float:right;">
							<option value="10">10</option>
							<option value="20" selected="selected">20</option>
							<option value="40">40</option>
							<option value="60">60</option>
							<option value="80">80</option>
							<option value="100">100</option>
							<option value="200">200</option>
						</select>
					</div>
						<div class="col-md-12" id="listBody">
						</div>
					</div>
					<div class="rowLayer" id="workLayer">
						<div class="col-md-12" id="workBody">
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
			crsCd : "",
			sortKey : "",
			sortKey2 : ""
		}

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		ItemDTO.sortKey = "CRS_CD_DESC";
		
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listCreateCourse();
			}
		}
		listCreateCourse();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 개설 과정 목록 조회
	 * 개설 년도별 개설 과정 목록
	 */
	function listCourse(page) {
		var crsNm = $('#searchKey').val();
		var creYear = $("#creYear option:selected").val();
		var listScale = $("#listScale option:selected").val();
		var createdOnly = "N";
		if($("#createdOnly").is(":checked")) createdOnly = "Y";

		$('#courseList')
			.load(cUrl("/mng/course/createCourse/listCourse"),
				{ 
				  "crsNm":crsNm,
				  "creYear":creYear,
				  "sortKey":ItemDTO.sortKey,
				  "createdOnly":createdOnly,
				  "curPage":page,
				  "listScale":listScale
			    },
			    function(d) {
		        	$('.inputNumber').inputNumber();
		        }
			);
	}

	/**
	 * 개설 과정 등록 폼
	 */
	function crsCreWriteForm() {
		var crsCd = $("#crsCd option:selected").val();
		var addContent  = "<iframe id='certInfoFrame' name='certInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/createCertification/addFormCreateCoursePop"/>"
			+ "?crsCd="+crsCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(650, 350);
		modalBox.setTitle("과정 등록");
		modalBox.show();
	}

	/**
	 * 개설 과정 목록
	 */
	function listCreateCourse() {
		var url = cUrl("/mng/course/createCertification/listCreateCourse");
		var crsCreNm = $('#searchKey').val();
		var crsCd = $("#crsCd option:selected").val();
		
		//2015.11.18 (redmine : KNOTZ_NG_223) 수정
		//sort값이 정의되지 않았을 때 기수별 정렬을 넣는다.
		if(ItemDTO.sortKey2 == "" || ItemDTO.sortKey2 == undefined){
			ItemDTO.sortKey2 = "CRS_CD_DESC";
		}
		$("#listBody")
			.load(url, {
				"crsCreNm":crsCreNm,
				"crsCd" : crsCd ,
				"deptCd" : $("#deptCd").val() ,
				"sbjCd" : $("#sbjCd").val() ,
				"sortKey" : ItemDTO.sortKey2
			});
		showArea('list');
	}

	/**
	 * 개설 과정 학습자 관리 폼 
	 */
	function creCrsStudentMngForm(crsCreCd) {
		var creOperTypeCd = "E";
		document.location.href=cUrl("/mng/std/student/certCourseManageMain")+"?crsCreCd="+crsCreCd+"&creOperTypeCd="+creOperTypeCd;
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listCourse(1);
	}

	function setSortKey2(sortKey) {
		ItemDTO.sortKey2 = sortKey;
		listCreateCourse();
	}
	
	function showArea(str) {
		$(".rowLayer").hide();
		$("#"+str+"Layer").show();
	}

	/**
	 * 개설 과정 수정
	 */
	function editCreCrsPop(crsCreCd) {
		var crsCd = $("#crsCd option:selected").val();
		var addContent  = "<iframe id='certInfoFrame' name='certInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/createCertification/editCreateCoursePop"/>"
			+ "?crsCreCd="+crsCreCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(650, 350);
		modalBox.setTitle("과정 수정");
		modalBox.show();
	}
	
	/**
	 * 자격증 과정 응시생 관리 폼
	 */
	function viewCertStudentMngForm(crsCreCd) {
		var url = cUrl("/mng/std/student/certStudentManage");
		$("#workBody")
			.load(url, {
				"crsCreCd" : crsCreCd
			});
		showArea('work');
	}

</script>
