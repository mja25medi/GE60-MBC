<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

                    <div class="res_tbl_wrap mt20">
                    	<table>
                    		<caption>예약조회 목록</caption>
                    		<thead>
                    			<tr>
                                	<th scope="col" width="10%">번호</th>
                                    <th scope="col">장비명</th>
                                    <th scope="col" width="10%">장비대여수</th>
                                    <th scope="col" width="20%">대여기간</th>
                                    <th scope="col" width="10%">신청자</th>
                                    <th scope="col" width="12%">신청일시</th>
                                    <th scope="col" width="10%">상태</th>
                                </tr>
                    		</thead>
                    		<tbody>
                    			<c:if test="${not empty rentInfoList}">
                    				<c:forEach var="item" items="${rentInfoList }" varStatus="status">
                    					<tr>
		                                    <td scope="row" data-label="번호">${pageInfo.totalRecordCount - status.index }</td>
		                                    <td scope="row" class="title" data-label="장비명">${item.equNm }</td>
		                                    <td data-label="장비대여수">${item.rentCnt }개</td>
		                                    <td data-label="대여기간">
		                                    	<a href="javascript:detailView('${item.rentCd}');">
		                                            <meditag:dateformat type="8" delimeter="." property="${item.rentStartDttm }" /><br>
													 ~ <meditag:dateformat type="8" delimeter="." property="${item.rentEndDttm }" />
		                                        </a>
		                                    </td>
		                                    <td data-label="신청자">${item.userNm }</td>
		                                    <td data-label="신청일시"><meditag:dateformat type="1" delimeter="." property="${item.regDttm }"/></td>
		                                    <td data-label="상태">
		                                    	<c:set var="resSts" value="${item.rentSts }" />
		                                    	<c:choose>
		                                        	<c:when test="${rentSts eq 'APPROVED' or rentSts eq 'RENT'}">
		                                        		<c:set var="btnColor" value="fcBlue" />
		                                        	</c:when>
		                                        	<c:when test="${rentSts eq 'RENT_CANCEL' or rentSts eq 'REQ_CANCEL' or rentSts eq'CLOSE'}">
		                                        		<c:set var="btnColor" value="gray" />
		                                        	</c:when>
		                                        	<c:when test="${rentSts eq 'OVERDUE'}">
		                                        		<c:set var="btnColor" value="dark" />
		                                        	</c:when>
		                                        	<c:otherwise>
		                                        		<c:set var="btnColor" value="fcDarkgray" />
		                                        	</c:otherwise>
		                                        </c:choose> 
		                                        <button class="btn solid ${btnColor}" onclick="detailView('${item.rentCd}')">${item.rentSts.codeNm }</button>
		                                    </td>
		                                </tr>
                    				</c:forEach>
                    			</c:if>
                    		</tbody>
                        </table>
                    </div>
                    
                    <c:if test="${empty rentInfoList }">
						<div class="no-list">조회된 예약 내역이 없습니다.</div>
					</c:if>

				<div class="board_pager">
					<span class="inner"> 
						<meditag:paging pageInfo="${pageInfo}" funcName="listRent" name="front"/>
					</span>
				</div>                

