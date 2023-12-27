<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

                    <div class="res_tbl_wrap mt20">
                    	<table>
                    		<caption>예약조회 목록</caption>
                    		<thead>
                    			<tr>
                                	<th scope="col" width="10%">번호</th>
                                    <th scope="col">시설명</th>
                                    <th scope="col" width="10%">이용인원</th>
                                    <th scope="col" width="20%">이용날짜/시간</th>
                                    <th scope="col" width="10%">신청자</th>
                                    <th scope="col" width="12%">신청일시</th>
                                    <th scope="col" width="10%">상태</th>
                                </tr>
                    		</thead>
                    		<tbody>
                    			<c:if test="${not empty resInfoList }">
                    				<c:forEach var="item" items="${resInfoList }" varStatus="status">
                    					<tr>
		                                    <td scope="row" data-label="번호">${pageInfo.totalRecordCount - status.index }</td>
		                                    <td scope="row" class="title" data-label="시설명">${item.facNm }</td>
		                                    <td data-label="이용인원">${item.attCnt }명</td>
		                                    <td data-label="이용날짜/시간">
		                                    					<a href="javascript:detailView('${item.resCd}');">
						                                            ${item.resDt}  ${item.resStm} ~ ${item.resEtm}
						                                        </a>
		                                    </td>
		                                    <td data-label="신청자">${item.userNm }</td>
		                                    <td data-label="신청일시"><meditag:dateformat type="1" delimeter="." property="${item.regDttm }"/></td>
		                                    <td data-label="상태">
		                                    	<c:set var="resSts" value="${item.resSts }" />
		                                    	<c:choose>
		                                        	<c:when test="${resSts eq 'RESERVED'}"> <!-- 예약완료 -->
		                                        		<c:set var="btnColor" value="fcBlue" />
		                                        	</c:when>
		                                        	<c:when test="${resSts eq 'CANCEL' or resSts eq 'CLOSE'}"> <!-- 예약취소/예약종료 -->
		                                        		<c:set var="btnColor" value="gray" />
		                                        	</c:when>
		                                        	<c:when test="${resSts eq 'RETURN'}"> <!-- 예약반려 -->
		                                        		<c:set var="btnColor" value="dark" />
		                                        	</c:when>
		                                        	<c:otherwise>
		                                        		<c:set var="btnColor" value="fcDarkgray" /> <!-- 예약대기 -->
		                                        	</c:otherwise>
		                                        </c:choose> 
		                                        <button class="btn3 sm solid ${btnColor}" onclick="detailView('${item.resCd}')">${item.resSts.codeNm }</button>
		                                    </td>
		                                </tr>
                    				</c:forEach>
                    			</c:if>
                    		</tbody>
                    	</table>
                    </div>
                    <c:if test="${empty resInfoList }">
						<div class="no-list">조회된 예약 내역이 없습니다.</div>
					</c:if>     

				<div class="board_pager">
					<span class="inner"> 
						<meditag:paging pageInfo="${pageInfo}" funcName="listRes" name="front"/>
					</span>
				</div>                

