<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />

                        <table>
                            <caption>이의제기 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col" width="15%">상태</th>
                                    <th scope="col">제목</th>
                                    <th scope="col" width="25%">등록일</th>
                                </tr>
                            </thead>
                            <tbody>
			<c:if test="${not empty objtList }">
				<c:forEach var="item" items="${objtList }" varStatus="status">
                                <tr>
                                    <td scope="row" class="m_hidden" data-label="번호">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
                                    <td data-label="상태">
 							<c:if test="${item.stsCd eq 'I'}">
								<label class="btn3 sm solid fcViolet">처리중</label>
							</c:if>
							<c:if test="${item.stsCd eq 'C'}">
								<label class="btn3 sm solid fcBlack">처리완료</label>
							</c:if>
                                    
                                    </td>
                                    <td class="title" data-label="제목">
										 <a href="javascript:viewObjt(${item.objtSn })">${item.title }
												<c:if test="${item.cmntsCnt ne 0}">
														(${item.cmntsCnt })
												</c:if>
										  </a>                                   
                                    
                                    </td>
                                    <td data-label="등록일"><meditag:dateformat type="0" delimeter="." property="${item.regDttm }" /></td>
                                </tr>
 				</c:forEach>
			</c:if>
			<c:if test="${empty objtList }">
								<tr>
                                    <td colspan="4" data-label="no data">이의제기 내역이 없습니다.</td>
                                </tr>				
			</c:if>
                            </tbody>
                        </table>

	
	 <div class="board_pager">
	    <ul class="pagination">
	        <li>
	            <meditag:paging pageInfo="${pageInfo}" funcName="listObjt" name="lect"/>
	        </li>
	    </ul>
    </div>

<script type="text/javascript">

	function viewObjt(objtSn){
		location.href = cUrl("/lec/objt/viewObjtMain") + "?objtSn=" + objtSn;
	}

</script>
