<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseCategoryVO" value="${vo}"/>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="col-md-12">
					<form id="courseCategoryForm" name="courseCategoryForm" onsubmit="return false" action="/mng/course/category">
					<div class="text-right">
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
									<th scope="col"><spring:message code="course.title.course.category"/></th>
									<th scope="col"><spring:message code="common.title.useyn"/></th>
									<th scope="col"><spring:message code="course.title.category.write"/></th>
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
</section>
<script type="text/javascript">

	var modalBox = null;

	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		ctgrList();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function refreshCategoryList() {
		ctgrList();
	}

	/**
	 *  분류 목록 호출
	 */
	function ctgrList() {
		$('#ctgrList').load(
				cUrl("/mng/course/category/list"),
				function () {}
			);
	}

	/**
	 * 분류 등록 폼
	 */
	function ctgrWrite() {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/category/addFormCategoryPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 290);
		modalBox.setTitle("<spring:message code="course.title.category.write"/>");
		modalBox.show();
	}

	/**
	 * 서브 분류 등록 폼
	 */
	function subCtgrWrite(parCrsCtgrCd) {
		var addContent  = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/category/addFormCategorySubPop"/>"
			+ "?parCrsCtgrCd="+parCrsCtgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 290);
		modalBox.setTitle("<spring:message code="course.title.category.write"/>");
		modalBox.show();
	}

	/**
	 * 분류 수정 폼
	 */
	function ctgrEdit(crsCtgrCd) {
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/category/editFormCategoryPop"/>"
			+ "?crsCtgrCd="+crsCtgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 290);
		modalBox.setTitle("<spring:message code="course.title.category.edit"/>");
		modalBox.show();
	}

	/**
	 * 과정 분류 순서 변경 폼
	 */
	function ctgrSort() {
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/category/sortFormCategoryPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(300, 304);
		modalBox.setTitle("<spring:message code="course.title.category.sort"/>");
		modalBox.show();
	}

</script>
