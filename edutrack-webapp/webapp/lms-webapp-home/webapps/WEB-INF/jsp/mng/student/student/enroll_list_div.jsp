<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentList" value="${studentList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="studentVO" value="${vo}"/>

		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:20px" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:70px" />
				<col style="width:70px" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" ><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="Select All" onclick="checkAll()"/></th>
					<th scope="col" ><spring:message code="common.title.no"/></th>
					<th scope="col" >
					<c:choose>
						<c:when test="${fn:startsWith(studentVO.sortKey,'DECLS_NO') == true}">
							<c:if test="${studentVO.sortKey eq 'DECLS_NO_ASC'}">
						<a href="javascript:setSortKey('DECLS_NO_DESC')"><spring:message code="student.title.student.decls"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${studentVO.sortKey eq 'DECLS_NO_DESC'}">
						<a href="javascript:setSortKey('DECLS_NO_ASC')"><spring:message code="student.title.student.decls"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('DECLS_NO_ASC')"><spring:message code="student.title.student.decls"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" >
					<c:choose>
						<c:when test="${fn:startsWith(studentVO.sortKey,'USER_NM') == true}">
							<c:if test="${studentVO.sortKey eq 'USER_NM_ASC'}">
						<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${studentVO.sortKey eq 'USER_NM_DESC'}">
						<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" >
					<c:choose>
						<c:when test="${fn:startsWith(studentVO.sortKey,'USER_ID') == true}">
							<c:if test="${studentVO.sortKey eq 'USER_ID_ASC'}">
						<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${studentVO.sortKey eq 'USER_ID_DESC'}">
						<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" >
					<c:choose>
						<c:when test="${fn:startsWith(studentVO.sortKey,'ENRL_APLC_DTTM') == true}">
							<c:if test="${studentVO.sortKey eq 'ENRL_APLC_DTTM_ASC'}">
						<a href="javascript:setSortKey('ENRL_APLC_DTTM_DESC')"><spring:message code="student.title.student.enrolldate"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${studentVO.sortKey eq 'ENRL_APLC_DTTM_DESC'}">
						<a href="javascript:setSortKey('ENRL_APLC_DTTM_ASC')"><spring:message code="student.title.student.enrolldate"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('ENRL_APLC_DTTM_ASC')"><spring:message code="student.title.student.enrolldate"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" ><spring:message code="student.title.student.payment.mthd"/></th>
					<th scope="col" ><spring:message code="student.title.student.payment.fee"/></th>
					<th scope="col" ><spring:message code="student.title.student.payment.status"/></th>
					<th scope="col" ><spring:message code="common.title.stats"/></th>
					<th scope="col" ><spring:message code="student.title.student.repay.status"/></th>
					<th scope="col" ><spring:message code="student.title.student.info"/></th>
					<th scope="col" ><spring:message code="student.title.student.repay"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${studentList}" varStatus="status">
				<tr>
					<td><input type='checkbox' name='sel' id='sel' value='${item.stdNo}' style='border:0' title='check'></td>
					<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
					<td class="text-right">${item.declsNo}</td>
					<td><a href="javascript:userInfo('${item.userNo}')">${item.userNm}</a></td>
					<td>${item.userId}</td>
					<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.enrlAplcDttm}"/></td>
					<td class="text-center">
						<meditag:codename code="${item.paymMthdCd}" category="PAYM_MTHD_CD"/>
					</td>
					<td class="text-right">
						<c:if test="${item.paymMthdCd eq 'FREE'}"> - </c:if>
						<c:if test="${item.paymMthdCd ne 'FREE'}">
							<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">${sessionScope.MNTRYUNIT}</c:if><fmt:formatNumber value="${item.paymPrice}" type="number"/><c:if test="${sessionScope.MNTRYPOS eq 'PO'}">${sessionScope.MNTRYUNIT}</c:if>
						</c:if>
					</td>
					<td class="text-center">
						<c:if test="${item.paymMthdCd eq 'FREE'}"> - </c:if>
						<c:if test="${item.paymMthdCd ne 'FREE'}">
							<c:if test="${item.paymStsCd eq 'Y'}"><spring:message code="student.title.student.payment.complete"/></c:if>
							<c:if test="${item.paymStsCd ne 'Y'}"><spring:message code="student.title.student.payment.nocomplete"/></c:if>
						</c:if>
					</td>
					<td class="text-center"><meditag:codename code="${item.enrlSts}" category="ENRL_STS"/></td>
					<td class="text-center"><meditag:codename code="${item.repayStsCd}" category="REPAY_STS_CD"/></td>
					<td class="text-center">
						<a href="javascript:viewStudent('${item.stdNo}');" class="btn btn-info btn-sm"><spring:message code="button.manage"/> </a>
					</td>
					<td class="text-center">
						<c:if test="${item.paymMthdCd ne 'FREE' && item.paymStsCd eq 'Y'}">
						<a href="javascript:editRepay('${item.stdNo}');" class="btn btn-info btn-sm"><spring:message code="button.manage"/> </a>
						</c:if>
				</tr>
				</c:forEach>
				<c:if test="${empty studentList}">
				<tr>
					<td colspan="13"><spring:message code="student.message.student.enroll.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listStudent"/>
