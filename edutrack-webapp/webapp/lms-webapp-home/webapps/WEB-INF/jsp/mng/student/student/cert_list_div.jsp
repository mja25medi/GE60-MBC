<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<script type="text/JavaScript" src="/js/paginator3000.js"></script>
<link rel="stylesheet" href="/css/paginator3000.css" type="text/css">
						<div class="input-group" style="float:left">
							<c:choose>
								<c:when test="${empty studentList}">
									응시자 : <span id="totalCnt">0</span>명,  합격자 : <span id="passCnt">0</span>명, 불합격자 : <span id="nonePassCnt">0</span>명, 자격증 신청자 : <span id="cpltStsICnt">0</span>명 
								</c:when>
								<c:otherwise>
									<%-- 
									응시자 : <span id="totalCnt">${studentList[0].totalCnt}</span>명,  합격자 : <span id="passCnt">${studentList[0].passCnt}</span>명, 불합격자 : <span id="nonePassCnt">${studentList[0].nonePassCnt}</span>명, 자격증 신청자 : <span id="cpltStsICnt">${studentList[0].cpltStsICnt}</span>명 
									 --%>
								</c:otherwise>
							</c:choose>
						</div>
						<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:30px"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:auto;min-width:50px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:auto;min-width:120px;"/>
								<col style="width:auto;min-width:90px;"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
									<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'USER_NM') == true}">
												<c:if test="${courseVO.sortKey eq 'USER_NM_ASC'}">
													<a href="javascript:setSortKey('USER_NM_DESC')">신청자명<i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'USER_NM_DESC'}">
													<a href="javascript:setSortKey('USER_NM_ASC')">신청자명<i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
												<a href="javascript:setSortKey('USER_NM_ASC')">신청자명<i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope="col">점수</th>
									<th scope="col">합격/불합격</th>
									<th scope="col">자격증 발급 신청일</th>
									<th scope="col">발급 신청 상태</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${studentList}" var="item" varStatus="status">
									<tr>
										<td><input type='checkbox' name='sel' id='sel' value='${item.stdNo}' style='border:0' title='<spring:message code="common.title.select"/>'></td>
										<td class="text-center">
											<a href="#" onclick="javascript:userInfo('${item.userNo}');">${item.userNm}</a>
										</td>
										<td class="text-center">
											${item.certScore}
										</td>
										<td class="text-center">
											<c:choose>
												<c:when test="${item.certPassYn eq 'Y' }">
													합격
												</c:when>
												<c:otherwise>
													불합격
												</c:otherwise>
											</c:choose>
										</td>
										<td class="text-center">
											<c:if test="${item.certAplcDttm ne ''}">
												<meditag:dateformat type="8" delimeter="." property="${item.certAplcDttm}"/>
											</c:if>
										</td>
										<td class="text-center">
											<a href="#" onclick="javascript:changeCertSts('${item.stdNo}');">${item.certStsNm}</a>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${empty studentList}">
								<tr>
									<td colspan="8" class="text-center">현재 검색한 검색조건으로 조회된 검색결과가 없습니다.</td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listCertStudent"/>
