<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseCategoryVO" value="${vo}"/>

<section class="content">
			<div class="row" id="content">
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
					<div class="text-right" style="margin-top:10px;">
						<button class="btn btn-default btn-sm" onclick="parent.reloadForm()" ><spring:message code="button.close"/></button>
					</div>
					<input type="submit" value="submit" style="display:none" />
					</form>
				</div>
			</div>
</section>
<script type="text/javascript">

	var modalBox = null;

	// 페이지 초기화
	$(document).ready(function() {
		$("body").css("overflow-x","hidden");

		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		parent.modalBox.resize(1000, 700);
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
		location.href="/mng/course/category/addFormCategoryPop";
	}

	/**
	 * 서브 분류 등록 폼
	 */
	function subCtgrWrite(parCrsCtgrCd) {
		location.href="/mng/course/category/addFormCategorySub?parCrsCtgrCd="+parCrsCtgrCd+"${AMPERSAND}isPop=Y";
	}

	/**
	 * 분류 수정 폼
	 */
	function ctgrEdit(crsCtgrCd) {
		location.href="/mng/course/category/editFormCategory?crsCtgrCd="+crsCtgrCd+"${AMPERSAND}isPop=Y";
	}

	/**
	 * 과정 분류 순서 변경 폼
	 */
	function ctgrSort() {
		location.href="/mng/course/category/sortFormCategory?isPop=Y";
	}

</script>