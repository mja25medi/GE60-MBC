<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="createCourseList" value="${createCourseList}"/>
<c:set var="vo" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

	<div style="width:100%;">
		
		<div class="input-group" style="float:left; line-height:30px;width:160px;margin-right: 10px;">
			<input type="text" name="searchKey2" id="searchKey2" value="${vo.crsCreNm }" class="_enterBind2 form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="<spring:message code="common.title.all"/>"/>
			<span class="input-group-addon" onclick="listCreateCourse(1);" style="cursor:pointer">
				<i class="fa fa-search"></i>
			</span>
		</div>
		<div style="float:left; height:40px; line-height:30px;">
			<%-- <h5><spring:message code="course.message.createcourse.msg.manage"/></h5> --%>
			<select class="form-control input-sm" name="creTypeCd" id="creTypeCd" onchange="listCreateCourse(1)" >
							<option value="" 	selected="selected">과정구분</option>
							<option value="ON"  <c:if test="${vo.creTypeCd eq 'ON' }">selected</c:if>>온라인</option>
							<option value="OF" <c:if test="${vo.creTypeCd eq 'OF' }">selected</c:if>>오프라인</option>
							<option value="BL"  <c:if test="${vo.creTypeCd eq 'BL' }">selected</c:if>>혼합</option>
						</select>
		</div>
		<div style=" float:right; line-height:30px; display: flex;">
			<button class="btn btn-primary btn-sm" onclick="crsCreWriteFormPop()" ><spring:message code="button.write.round"/></button>
			<%-- <button class="btn btn-default btn-sm" onclick="closeWriteArea();" style="float:right"><spring:message code="button.list.course"/></button> --%>
			<select name="listScale" id="listScale" onchange="listCreateCourse(1)" class="form-control input-sm" style="margin-left: 5px;">
				<option value="10" <c:if test="${vo.listScale eq '10' }">selected="selected"</c:if>>10</option>
				<option value="20" <c:if test="${vo.listScale eq '20' }">selected="selected"</c:if>>20</option>
				<option value="40" <c:if test="${vo.listScale eq '40' }">selected="selected"</c:if>>40</option>
				<option value="60" <c:if test="${vo.listScale eq '60' }">selected="selected"</c:if>>60</option>
				<option value="80" <c:if test="${vo.listScale eq '80' }">selected="selected"</c:if>>80</option>
				<option value="100" <c:if test="${vo.listScale eq '100' }">selected="selected"</c:if>>100</option>
				<option value="200" <c:if test="${vo.listScale eq '200' }">selected="selected"</c:if>>200</option>
			</select>
		</div>
	</div>
	<table summary="<spring:message code="course.title.createcourse.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:60px"/>
			<col style="width:80px"/>
			<col style="width:195px"/>
			<col style="width:195px"/>
			<!-- <col style="width:auto"/> -->
			<!-- <col style="width:auto"/> -->
			<col style="width:auto"/>
			<col style="width:85px"/>
			<col style="width:85px"/>
			<!-- <col style="width:95px"/> -->
			<col style="width:85px"/>
			<!-- <col style="width:80px"/> -->
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="course.title.course.code"/></th>
				<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'CRS_CRE_NM') == true}">
							<c:if test="${vo.sortKey eq 'CRS_CRE_NM_ASC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'CRS_CRE_NM_DESC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
				</th>
				<%-- <th scope="col"><spring:message code="common.title.year"/></th> --%>
				<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'CRE_TERM') == true}">
							<c:if test="${vo.sortKey eq 'CRE_TERM_ASC'}">
						<a href="javascript:setSortKey2('CRE_TERM_DESC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'CRE_TERM_DESC'}">
						<a href="javascript:setSortKey2('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
				</th>
				<th scope="col">과정구분</th>
				<th scope="col">
				<spring:message code="course.title.createcourse.duration.aplc"/>
				<c:if test="${crsOperType eq 'R'}">
				<br/><spring:message code="course.title.createcourse.duration.edu"/>
				</c:if>
				</th>
				<th scope="col"><spring:message code="course.title.createcourse.duration.edu"/></th>
				<%-- <th scope="col"><spring:message code="course.title.createcourse.decls.cnt"/></th> --%>
				<%-- <th scope="col"><spring:message code="course.title.course.stdcnt"/></th> --%>
				<th scope="col"><spring:message code="course.title.createcourse.enrollcnt"/></th>
				
				<th scope="col">QR</th>
				
				<th scope="col"><spring:message code="common.title.useyn"/></th>
				<%-- <th scope="col"><spring:message code="course.title.createcourse.info"/></th> --%>
				<th scope="col"><spring:message code="course.title.createcourse.operate"/></th>
				<%-- <th scope="col"><spring:message code="course.title.createcourse.registrate"/></th> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${createCourseList}" var="item" varStatus="status">
			<tr>
				<td class="text-center">${item.crsCd}</td>
				<td>${item.crsCreNm}<a href="javascript:editCreCrsPop('${item.crsCreCd}');"><i class="fa fa-cog"></i></a></td>
				<%-- <td class="text-center">${item.creYear}</td> --%>
				<td class="text-center">${item.creTerm}</td>
				<td class="text-center">
					<c:if test="${item.creTypeCd eq 'ON'}">온라인</c:if>
					<c:if test="${item.creTypeCd eq 'OF'}">오프라인</c:if>
					<c:if test="${item.creTypeCd eq 'BL'}">혼합</c:if>
				</td>
				<td>
					<meditag:dateformat type="1" delimeter="." property="${item.enrlAplcStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlAplcEndDttm}"/>
					<c:if test="${crsOperType eq 'R'}">
					<br/>
					<c:if test="${item.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></c:if>
					<c:if test="${item.enrlStartDttm eq ''}">${item.creYear}</c:if>
					</c:if>
				</td>
				<td>
					<c:if test="${item.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></c:if>
				</td>
				<%-- <td class="text-right">${item.declsCnt}</td> --%>
				<%-- <td class="text-right">
					<c:if test="${item.nopLimitYn eq 'Y'}">${item.enrlNop}</c:if>
					<c:if test="${item.nopLimitYn ne 'Y'}">-</c:if>
				</td> --%>
				<td class="text-right">${item.cnfmCnt}</td>
				
				<%-- <td class="text-center">
					<c:choose>
						<c:when test="${empty item.crsCreQrInFilePath}">-</c:when>
						<c:otherwise><a href="javascript:readCourseQrPop('${item.crsCreQrInFilePath}','${item.crsCreQrInNo}')">보기</a></c:otherwise>
					</c:choose>
				</td>
				
				<td class="text-center">
					<c:choose>
						<c:when test="${empty item.crsCreQrOutFilePath}">-</c:when>
						<c:otherwise><a href="javascript:readCourseQrPop('${item.crsCreQrOutFilePath}','${item.crsCreQrOutNo}')">보기</a></c:otherwise>
					</c:choose>
				</td> --%>
				<td class="text-center">
					<c:choose>
						<c:when test="${empty item.qrFileSn}">-</c:when>
						<c:otherwise><a href="javascript:readCourseQrPop('${item.qrFileSn}')">보기</a></c:otherwise>
					</c:choose>
				</td>
				
				<td class="text-center">
					${item.useYn}
				</td>
				<%-- <td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsInfoMngForm('${item.crsCreCd}')"><spring:message code="course.title.createcourse.info"/></button>
				</a>
				</td> --%>
				<td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsOperationMngForm('${item.crsCreCd}')"><spring:message code="course.title.createcourse.operate"/></button>
				</td>
				<%-- <td class="text-center">
					<button class="btn btn-info btn-sm" onclick="creCrsStudentMngForm('${item.crsCreCd}')"><spring:message code="course.title.createcourse.registrate"/></button>
				</td> --%>
			</tr>
			</c:forEach>
			<c:if test="${empty createCourseList}">
			<tr>
				<td colspan="13"><spring:message code="course.message.createcourse.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listCreateCourse"/>

	<script type="text/javascript">
	$("._enterBind2").bind("keydown", eventForSearch2);
	function eventForSearch2(event) {
		if (event.keyCode == '13') {
			event.preventDefault();
			listCreateCourse('${createCourseVO.crsCd}','${createCourseVO.crsOperType }');
		}
	}
	
	</script>