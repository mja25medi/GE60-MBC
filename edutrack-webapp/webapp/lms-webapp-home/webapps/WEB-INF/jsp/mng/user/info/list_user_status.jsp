<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:70px"/>
		<c:set var="colspan" value="5" />
		<c:forEach items="${userInfoCfgList}" var="item" varStatus="status">
		<c:if test="${item.viewYn eq 'Y' }">
			<col style="width:auto"/>
			<c:set var="colspan" value="${colspan + 1}" />
		</c:if>
		</c:forEach>
			<col style="width:100px"/>
			<col style="width:70px"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="common.title.no"/> </th>
				<c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">
					<c:if test="${cfgItem.viewYn eq 'Y' }">
						<c:choose>
							<c:when test="${cfgItem.fieldNm eq 'USERID' }">
								<th scope="col">
									<spring:message code="user.title.userinfo.userid"/> 
								</th>
								<th scope="col"><spring:message code="user.title.userinfo.regtype"/></th>
							</c:when>
							<c:when test="${cfgItem.fieldNm eq 'USERNM' }">
								<th scope="col">
								<c:choose>
									<c:when test="${fn:startsWith(vo.sortKey,'USER_NM') == true}">
										<c:if test="${vo.sortKey eq 'USER_NM_ASC'}">
											<spring:message code="user.title.userinfo.name"/> 
										</c:if>
										<c:if test="${vo.sortKey eq 'USER_NM_DESC'}">
											<spring:message code="user.title.userinfo.name"/>
										</c:if>
									</c:when>
									<c:otherwise>
										<spring:message code="user.title.userinfo.name"/>
									</c:otherwise>
								</c:choose>
								</th>
							</c:when>
							<c:otherwise>
								<th scope="col"><spring:message code="user.title.userinfo.manage.${fn:toLowerCase(cfgItem.fieldNm)}"/></th>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'REGDATE') == true}">
							<c:if test="${vo.sortKey eq 'REGDATE_ASC'}">
								<spring:message code="common.title.regdate"/> 
							</c:if>
							<c:if test="${vo.sortKey eq 'REGDATE_DESC'}">
								<spring:message code="common.title.regdate"/> 
							</c:if>
						</c:when>
						<c:otherwise>
							<spring:message code="common.title.regdate"/> 
						</c:otherwise>
					</c:choose>
				</th>
				<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'USER_STS') == true}">
							<c:if test="${vo.sortKey eq 'USER_STS_ASC'}">
								<spring:message code="user.title.userinfo.stats"/>
							</c:if>
							<c:if test="${vo.sortKey eq 'USER_STS_DESC'}">
								<spring:message code="user.title.userinfo.stats"/> 
							</c:if>
						</c:when>
						<c:otherwise>
							<spring:message code="user.title.userinfo.stats"/>
						</c:otherwise>
					</c:choose>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userInfoList}" var="item" varStatus="status">
				<tr>
					<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
					<c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">
					<c:if test="${cfgItem.viewYn eq 'Y' }">
						<c:if test="${cfgItem.fieldNm eq 'USERNM' }"><td>${item.userNm}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'USERID' }">
							<td>${item.userId} </td>
							<td>
								<c:choose>
									<c:when test="${not empty item.snsDiv }">
										<spring:message code="user.title.userinfo.regtype.sns" text="소셜가입"/>
									</c:when>
									<c:otherwise>
										<spring:message code="user.title.login.joinin" text="회원가입"/>
									</c:otherwise>
								</c:choose>
							</td>
						</c:if>
						<c:if test="${cfgItem.fieldNm eq 'SEX' }"><td><c:if test="${item.sexCd eq 'M'}"><spring:message code="user.title.userinfo.sex_m"/></c:if><c:if test="${item.sexCd eq 'F'}"><spring:message code="user.title.userinfo.sex_f"/></c:if></td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'BIRTH' }"><td><meditag:dateformat type="1" delimeter="-" property="${item.birth}"/></td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'EMAIL' }"><td>${item.email}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'MOBILENO' }"><td>${item.mobileNo}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'PHONENO' }"><td>${item.homePhoneno}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'MESSAGE' }">
							<td class="text-center"><c:if test="${item.emailRecv eq 'Y' }"><spring:message code="user.title.userinfo.receive.email"/></c:if>
							<c:if test="${item.emailRecv eq 'Y' && item.smsRecv eq 'Y' }">/</c:if>
							<c:if test="${item.smsRecv eq 'Y' }"><spring:message code="user.title.userinfo.receive.sms"/></c:if></td>
						</c:if>
						<c:if test="${cfgItem.fieldNm eq 'DEPT' }"><td>${item.deptNm}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'AREA' }"><td>${item.areaNm}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'USERDIV' }"><td>${item.userDivNm}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'DISABLILITY' }"><td class="text-center">${item.disablilityYn}</td></c:if>
						<c:if test="${cfgItem.fieldNm eq 'JOB' }"><td class="text-center">${item.jobNm}</td></c:if>
					</c:if>
					</c:forEach>
					<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
					<td class="text-center"><meditag:codename code="${item.userSts}" category="USER_STS"/></td>

				</tr>
			</c:forEach>
			<c:if test="${empty userInfoList}">
			<tr>
				<td colspan="${colspan}"><spring:message code="user.message.userinfo.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>

	<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>