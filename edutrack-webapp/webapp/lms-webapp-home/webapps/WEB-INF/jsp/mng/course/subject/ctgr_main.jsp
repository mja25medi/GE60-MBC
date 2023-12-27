<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="subjectCategoryVO" value="${vo}"/>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<form id="courseSubjectForm" name="courseSubjectForm" class="form-inline" onsubmit="return false" action="/mng/course/subject">
				<div class="col-md-12 col-xs-18">
					<ul class="nav nav-tabs" id="tabMenu">
				  		<li class="ctgrTab" id="ctgrOn"><a href="javascript:ctgrList('ON')"><spring:message code="course.title.subject.category" /></a></li>
				  		<%-- <li class="ctgrTab" id="ctgrOf"><a href="javascript:ctgrList('OF')"><spring:message code="course.title.subject.offline" /></a></li> --%>
					</ul>
					<div class="pull-right" style="margin-top:-35px;">
						<a class="btn btn-primary btn-sm" href="javascript:ctgrWrite()"><spring:message code="button.write.category" /></a>
						<a class="btn btn-info btn-sm" href="javascript:ctgrSort()"><spring:message code="button.sort" /></a>
					</div>
				</div>
				<div class="col-md-12" style="margin-top:5px;">
					<div id="ctgrList">
						<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:100px"/>
								<col style="width:120px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.title.subject.category"/></th>
									<th scope="col"><spring:message code="common.title.useyn"/></th>
									<th scope="col"><spring:message code="course.title.subject.category.write_under"/></th>
									<th scope="col"><spring:message code="common.title.edit"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="4"><spring:message code="common.message.nodata"/></td>
								</tr>
							</tbody>
						</table>
					</div>
					<input type="submit" value="submit" style="display:none" />
					</form>
				</div>
				</div>
			</div>
		</div>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = new Object();
	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		ctgrList('ON');
		ItemDTO.sbjCtgrTypeCd = "ON";
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function refreshCategoryList() {
		ctgrList(ItemDTO.sbjCtgrTypeCd);
	}

	/**
	 *  분류 목록 호출
	 */
	function ctgrList(sbjCtgrTypeCd) {
		//var sbjCtgrTypeCd = $("#sbjCtgrTypeCd  option:selected").val();
		$(".ctgrTab").removeClass("active");
		if(sbjCtgrTypeCd == "ON"){
			$("#ctgrOn").addClass("active");
			ItemDTO.sbjCtgrTypeCd = "ON";
		} else if(sbjCtgrTypeCd == "OF") {
			$("#ctgrOf").addClass("active");
			ItemDTO.sbjCtgrTypeCd = "OF";
		}
		$('#ctgrList').load(
				cUrl("/mng/course/subject/listCtgr"),
				{
					"sbjCtgrTypeCd" : sbjCtgrTypeCd
				},
				function () {}
			);
	}

	/**
	 * 분류 등록 폼
	 */
	function ctgrWrite() {
		//var sbjCtgrTypeCd = $("#sbjCtgrTypeCd  option:selected").val();
		var sbjCtgrTypeCd = ItemDTO.sbjCtgrTypeCd;
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/subject/addFormCtgrPop"/>"
			+ "?sbjCtgrTypeCd="+sbjCtgrTypeCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="course.title.subject.category.write"/>");
		modalBox.show();
	}

	/**
	 * 서브 분류 등록 폼
	 */
	function subCtgrWrite(parSbjCtgrCd) {
		//var sbjCtgrTypeCd = $("#sbjCtgrTypeCd  option:selected").val();
		var sbjCtgrTypeCd = ItemDTO.sbjCtgrTypeCd;
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/subject/addFormCtgrSubPop"/>"
			+ "?sbjCtgrTypeCd="+sbjCtgrTypeCd+"&amp;parSbjCtgrCd="+parSbjCtgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="course.title.subject.category.write"/>");
		modalBox.show();
	}

	/**
	 * 분류 수정 폼
	 */
	function ctgrEdit(sbjCtgrCd) {
		//var sbjCtgrTypeCd = $("#sbjCtgrTypeCd  option:selected").val();
		var sbjCtgrTypeCd = ItemDTO.sbjCtgrTypeCd;
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/subject/editFormCtgrPop"/>"
			+ "?sbjCtgrTypeCd="+sbjCtgrTypeCd+"&amp;sbjCtgrCd="+sbjCtgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="course.title.subject.category.edit"/>");
		modalBox.show();
	}

	/**
	 * 과정 분류 순서 변경 폼
	 */
	function ctgrSort() {
		//var sbjCtgrTypeCd = $("#sbjCtgrTypeCd  option:selected").val();
		var sbjCtgrTypeCd = ItemDTO.sbjCtgrTypeCd;
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/subject/sortFormCtgrPop"/>"
			+ "?sbjCtgrTypeCd="+sbjCtgrTypeCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(300, 304);
		modalBox.setTitle("<spring:message code="course.title.subject.category.sort"/>");
		modalBox.show();
	}

</script>
