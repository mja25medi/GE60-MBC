<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcShareRelVO" value="${olcShareRelVO}"/>
<c:set var="olcShareRelList" value="${olcShareRelList}"/>

					<ul class="list-group wordbreak" style="margin-top:5px;">
						<c:forEach var="item" items="${olcShareRelList}" varStatus="status">
						<table summary="<spring:message code="course.title.course.manage"/>" class="table table-striped">
							<colgroup>
								<col />
								<col style="width:5%"/>
							</colgroup>
							<tr>
								<td>${item.ctgrNm}</td>
								<td>
									<a href="javascript:deleteShareCtgr('${item.userNo}','${item.ctgrCd}','${olcShareForm.olcShareRelVO.ctgrDivCd }')">
										<i class="fa fa-times "></i>
									</a>
								</td>
							</tr>
						</table>
<%-- 						<li class="list-group-item" style="valign:middle">
							<span style="margin-right:10px">
								${item.ctgrNm}
							</span>
							<span style="margin-right:10px">
								&nbsp;
							</span>
							<span class="pull-right">
								<a href="javascript:deleteShareCtgr('${item.userNo}','${item.ctgrCd}')">
									<i class="fa fa-times "></i>
								</a>
							</span>
						</li> --%>
						</c:forEach>
					</ul>
					<input type="hidden" name="shareListSize_${olcShareRelVO.ctgrDivCd }"  id="shareListSize_${olcShareRelVO.ctgrDivCd }" value="${fn:length(olcShareRelList)}">
					<script type="text/javascript">
						$("#shareCnt").val(${fn:length(olcShareRelList)});
					</script>