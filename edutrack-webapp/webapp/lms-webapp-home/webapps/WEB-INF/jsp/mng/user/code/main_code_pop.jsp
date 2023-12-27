<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-6 col-sm-6">
							<div class="form-group">
								<select name="useYn" id="useYn" class="form-control input-sm" onChange="listPageing(1);">
									<option value=""><spring:message code="course.open.title.category.useyn"/></option>
									<option value="Y"><spring:message code="common.title.status.useyn_y"/></option>
									<option value="N"><spring:message code="common.title.status.useyn_n"/></option>
								</select>
								<select name="searchKey" id="searchKey" class="form-control input-sm">
									<option value="CODE"><spring:message code="system.title.code.code"/></option>
									<option value="NAME"><spring:message code="system.title.code.name"/></option>
								</select>
								<div class="input-group" style="width:180px;">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
									<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right">
							<div class="form-group">
								<a href="javascript:userCodeWrite()" class="btn btn-primary btn-sm">
									<c:choose>
										<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }"><spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="button.write.code"/></c:when>
										<c:when test="${codeCtgrCd eq 'AREA_CD' }"><spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="button.write.code"/></c:when>
<%-- 										<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }"><spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="button.write.code"/></c:when> --%>
<%-- 										<c:when test="${codeCtgrCd eq 'CCL_CD' }"><spring:message code="library.title.contents.ccl"/>&nbsp;<spring:message code="button.write.code"/></c:when> --%>
										<c:otherwise><spring:message code="button.write.code"/></c:otherwise>
									</c:choose>
								</a>
								<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm">
									<option value="10">10</option>
									<option value="20" selected="selected">20</option>
									<option value="40">40</option>
									<option value="60">60</option>
									<option value="80">80</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
						</div>
						<br/>
						<div class="col-md-12" style="margin-top:20px;">
							<div id="userCodeList">
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	var ItemDTO = new Object();
	
	/**
	 * 초기 화면 구성
	 */
	$(document).ready(function() {
		$("body").css("overflow-x","hidden");
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listPageing(1);
			}
		}
		listPageing(1);
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 *  페이징처리
	 */
	function listPageing(page) {
		if(page > 0)
			ItemDTO.pageIndex = page;
		var searchKey 	= $("#searchKey").val();
		var searchValue = $('#searchValue').val();
		var listScale 	= $("#listScale").val();
		var useYn		= $('#useYn').val();
	
		$('#userCodeList')
			.load(
				cUrl("/mng/user/codeInfo/listCode"),
				{ 
				  "pageIndex":ItemDTO.pageIndex,
				  "codeCtgrCd":"${vo.codeCtgrCd}",
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "useYn":useYn,
				  "listScale":listScale
				},
				function () {
					//alert('callback')
					//closeWorkProgress();
				}
			);
	
	}
	
	/**
	 * 사용자 정보 코드 등록 폼
	 */
	function userCodeWrite() {
		location.href="/mng/user/codeInfo/addCodePop?codeCtgrCd=${vo.codeCtgrCd}&isPop=Y";
	}
	
	/**
	 * 사용자 정보 코드 수정 폼
	 */
	function userCodeEdit(codeCd) {
		location.href="/mng/user/codeInfo/editCodePop?codeCtgrCd=${vo.codeCtgrCd}&useYn=ALL&codeCd="+codeCd+"&isPop=Y";
	}

</script>
