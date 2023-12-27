<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="messageMainList" value="${messageMainList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="LogMsgMngVO" value="${vo}"/>


						<table summary="<spring:message code="course.title.course.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:80px"/>
								<col style="width:120px"/>
								<col style="width:auto"/>
								<col style="width:150px"/>
								<col style="width:160px"/>
								<col style="width:100px"/>
								<col style="width:100px"/>
								<col style="width:180px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
										<c:choose>	
											<c:when test="${fn:startsWith(LogMsgMngVO.sortKey,'REG_DTTM') == true}">
											<c:if test="${LogMsgMngVO.sortKey eq 'REG_DTTM ASC'}">
												<a href="javascript:setSortKey1('REG_DTTM_DESC')">번호<i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${LogMsgMngVO.sortKey eq 'REG_DTTM DESC'}">
												<a href="javascript:setSortKey1('REG_DTTM_ASC')">번호<i class="fa fa-sort-desc"></i></a>
											</c:if>
											</c:when>
											<c:otherwise>
												<a href="javascript:setSortKey1('REG_DTTM_ASC')">번호<i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>										
									</th>
									<th scope="col">
										메시지 종류
									</th>									
									<th scope="col">
										제목
									</th>
									<th scope="col">
										송신자									
									</th>
									<th scope="col">
										수신자
									</th>
									<th scope="col">
										상태
									</th>
									<th scope="col">
										발송 갯수
									</th>
									<th scope="col">
										등록일자
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${messageMainList}" var="item" varStatus="status">
								<tr>
									<td class="text-center">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
									<td class="text-center">${item.msgDivNm}</td>
									<td class="wordbreak"><a href="javascript:msgDetailForm('${item.msgSn}')">${item.title}</a></td>
									<td class="text-center">${item.sendNm}</td>
									<td class="text-center">${item.recvNm}</td>
									<td class="text-center"><!-- ${item.sendSts} -->완료</td>
									<td class="text-center">${item.recvCnt}</td>
									<td class="text-center"><meditag:dateformat type="0" delimeter="." property="${item.regDttm }" /></td>
								</tr>
								</c:forEach>
								<c:if test="${empty messageMainList}">
								<tr>
									<td colspan="8"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listMessage"/>