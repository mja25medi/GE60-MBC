<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
	<form id="examQbankForm" name="examQbankForm" onsubmit="return false">
	<table summary="<spring:message code="course.title.course.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<c:choose>
			<c:when test ="${subAddCategory eq 'Y'}"><%-- 하위분류등록 --%>
				<tr>
					<th><spring:message code="course.title.exambank.category.name"/></th>
					<td>
						<div class="input-group" style="width:100%">
							<c:if test="${gubun eq 'A'}">
							<input type="text" maxlength="500" name="parCtgrNm" id="parCtgrNm" class="form-control input-sm" value="${parVO.ctgrNm}" readonly="readonly"/>
							<input type="hidden" name="parCtgrCd" id="parCtgrCd" class="form-control input-sm" value="${parVO.ctgrCd}"/>
							</c:if>
							<c:if test="${gubun eq 'E'}">
							<input type="text" maxlength="500" name="parCtgrNm" id="parCtgrNm" class="form-control input-sm" value="${vo.parCtgrNm}" readonly="readonly"/>
							<input type="hidden" maxlength="100" name="ctgrCd" class="form-control input-sm" id="ctgrCd" value="${vo.ctgrCd}"/>
							<input type="hidden" maxlength="100" name="parCtgrCd" class="form-control input-sm" id="parCtgrCd" value="${vo.parCtgrCd}"/>
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<th><span style="color:red;">* </span><spring:message code="course.title.exambank.subcategory.name"/></th>
					<td>
						<div class="input-group" style="width:100%">
							<input type="text" maxlength="500" name="ctgrNm" id="ctgrNm" class="form-control input-sm" id="ctgrNm" value="${vo.ctgrNm}"  />
						</div>	
					</td>
				</tr>
				<tr>
					<th></span><spring:message code="course.title.exambank.subcategory.desc"/></th>
					<td>
						<textarea name="ctgrDesc" id="ctgrDesc" style="height:150px; width:100%; " class="form-control input-sm">${vo.ctgrDesc}</textarea>
					</td>
				</tr>
				<tr>
				<th>사용 여부</th>
				<td>
					<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y' or empty vo.useYn }">checked="chekced"</c:if> /> <spring:message code="common.title.useyn_y"/></label>
					<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn eq 'N' }">checked="chekced"</c:if>/> <spring:message code="common.title.useyn_n"/></label>
				</td>
				</tr>										
			</c:when>
			
			<c:otherwise><%-- 상위분류 등록 --%>
				<tr>
					<th><span style="color:red;">* </span><spring:message code="course.title.exambank.category.name"/></th>
					<td>
						<div class="input-group" style="width:100%">
							<c:if test="${gubun eq 'A'}">
								<input type="text" maxlength="500" name="ctgrNm" class="form-control input-sm" id="ctgrNm" />
							</c:if>	
							<c:if test="${gubun eq 'E'}">
								<input type="text" maxlength="500" name="ctgrNm" class="form-control input-sm" id="ctgrNm" value="${vo.ctgrNm}"/>
								<input type="hidden" maxlength="100" name="ctgrCd" class="form-control input-sm" id="ctgrCd" value="${vo.ctgrCd}"/>
							</c:if>
						</div>
					</td>
				</tr>			
			</c:otherwise>
		</c:choose>

	</table>
	<c:choose>
		<c:when test ="${subAddCategory eq 'Y'}">	
			<div class="text-right" style="margin-bottom:20px;" >
				<c:if test="${gubun eq 'A'}">
				<button class="btn btn-primary btn-sm" onclick="subAddCategory()"><spring:message code="button.add"/></button>
				<button class="btn btn-default btn-sm" onclick="closeWriteArea()"><spring:message code="button.close"/></button>
				</c:if>	
				<c:if test="${gubun eq 'E'}">
				<button class="btn btn-primary btn-sm" onclick="subEditCategory()"><spring:message code="button.edit"/></button>
				<button class="btn btn-primary btn-sm" onclick="subDeleteCategory()"><spring:message code="button.delete"/></button>
				<button class="btn btn-default btn-sm" onclick="closeWriteArea()"><spring:message code="button.close"/></button>
				</c:if>			
			</div>		
		</c:when>
		<c:otherwise>
			<div class="text-right" style="margin-bottom:20px;" >
				<c:if test="${gubun eq 'A'}">
					<button class="btn btn-primary btn-sm" onclick="addCategory()"><spring:message code="button.add"/></button>
					<button class="btn btn-default btn-sm" onclick="closeWriteArea()"><spring:message code="button.close"/></button>
				</c:if>	
				<c:if test="${gubun eq 'E'}">
<%-- 				<button class="btn btn-primary btn-sm" onclick="addCategory()"><spring:message code="button.add"/></button> --%>
					<button class="btn btn-primary btn-sm" onclick="editCategory()"><spring:message code="button.edit"/></button>
					<button class="btn btn-primary btn-sm" onclick="deleteCategory()"><spring:message code="button.delete"/></button>
					<button class="btn btn-default btn-sm" onclick="closeWriteArea()"><spring:message code="button.close"/></button>
				</c:if>	
			</div>		
		</c:otherwise>
	</c:choose>

	</form>
	
<script type="text/javascript">
$(document).ready(function() {
});
</script>