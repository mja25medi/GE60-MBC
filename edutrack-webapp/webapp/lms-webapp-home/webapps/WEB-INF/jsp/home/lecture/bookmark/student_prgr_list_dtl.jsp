<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img" />
<c:set var="bookmarkVO" value="${vo}"/>

<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>

<mhtml:class_body>
				<mhtml:class_location />
				<div class="row" style="margin-bottom:5px;">
					<form name="Search" id="Search">
					<div class="col-md-4 col-sm-4">
						<select name="sbjCd" id="sbjCd" onchange="listContents()" class="form-control input-sm">
						<c:forEach items="${sbjList}" var="item">
							<option value="${item.sbjCd}" <c:if test="${bookmarkVO.sbjCd eq item.sbjCd}">selected</c:if>>${item.sbjNm}</option>
						</c:forEach>
						</select>
					</div>
					<div class="col-md-8 col-sm-8 text-right">
						<a href="<c:url value="/lec/bookmark/listStdPrgrRatioMain"/>" class="btn btn-default btn-sm" ><spring:message code="button.list.student"/></a>
					</div>
					</form>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="lecture.title.bookmark.contents.ratio"/></caption>
							<colgroup>
				            	<col width="7%" />
				                <col width="auto" />
				                <!-- <col width="10%" /> -->
				                <col width="10%" />
				                <col width="10%" />
				                <col width="10%" />
				                <col width="12%" />
				            </colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="course.title.contents.name"/></th>
									<th scope="col"><spring:message code="course.title.course.ratio.progress.check.title"/></th>
									<th scope="col"><spring:message code="lecture.title.contents.study.ratio"/></th>
									<th scope="col"><spring:message code="lecture.title.contents.study.cnt"/></th>
									<th scope="col" class="rnone"><spring:message code="lecture.title.contents.study.time"/></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty bookmarkList }">
								<c:forEach items="${bookmarkList}" var="item" varStatus="status">
								<c:set var="blankImg" value="<img src='${img_base}/framework/common/blank.gif' height='10' width='${(item.unitLvl-1) * 20}'>"/>
								<c:set var="typeImg" value=""/>
								<c:if test="${item.subCnt > 0 }"><c:set var="typeImg" value="<img src='${img_base}/framework/icon/icon_category.gif' align='absmiddle'>"/></c:if>
								<c:if test="${item.subCnt <= 0 }"><c:set var="typeImg" value="<img src='${img_base}/framework/icon/icon_contents.gif' align='absmiddle'>"/></c:if>
								<tr>
				               		<td class="text-right"><%-- ${status.count} --%>
				               			<c:if test="${item.unitType eq 'L' }">
											<c:set var="cnt" value="${cnt+1}"/>
											${cnt}
										</c:if>
				               		</td>
				               		<td class="subject">${blankImg}${typeImg} ${item.unitNm}</td>
				               		<td class="text-center">
				               			<c:if test="${item.unitType eq 'L' }">
											<c:if test="${item.prgrChkYn eq 'Y'}"><spring:message code="course.title.contents.required"/></c:if>
											<c:if test="${item.prgrChkYn eq 'N'}"><spring:message code="course.title.contents.option"/></c:if>
										</c:if>
				               		</td>
				               		<td class="text-right"><c:if test="${item.unitType eq 'L'}">${item.prgrRatio}%</c:if></td>
									<td class="text-right"><c:if test="${item.unitType eq 'L'}">${item.connCnt}</c:if></td>
									<td class="text-right">
										<c:if test="${item.unitType eq 'L'}">
										<fmt:parseNumber var= "min" integerOnly= "true" value= "${item.connTotTm % 3600 / 60 }" />
										<fmt:parseNumber var= "sec" integerOnly= "true" value= "${item.connTotTm % 3600 % 60 }" />
										${min } <spring:message code="common.title.min"/> ${sec } <spring:message code="common.title.sec"/>
										</c:if>
									</td>
								</tr>
								</c:forEach>
								</c:if>
								<c:if test="${empty bookmarkList }">
									<tr>
										<td colspan="6"><spring:message code="lecture.message.contents.list.nodata"/></td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<form id="bookmarkForm" name="bookmarkForm" onsubmit="return false" >
						<input type="hidden" name="sbjCd" id="bookmarkSbjCd" value="${vo.sbjCd}"/>
						<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}"/>
						<input type="hidden" name="stdNo" value="${vo.stdNo}"/>
						</form>
					</div>
				</div>

<script type="text/javascript">
	function listContents() {
		$("#bookmarkSbjCd").val($("#sbjCd > option:selected").val());
		$("#bookmarkForm").attr("action","/lec/bookmark/listStdPrgrRatioDetailMain");
		
		document.bookmarkForm.submit();
	}
</script>
</mhtml:class_body>
</mhtml:class_html>