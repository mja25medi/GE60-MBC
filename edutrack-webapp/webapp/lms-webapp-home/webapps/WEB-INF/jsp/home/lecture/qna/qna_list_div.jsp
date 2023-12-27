<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
	<table class="tstyle list">
                        <caption>공지사항 테스트 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col>
                            <col>
                            <col class="w10 m_hidden">
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="title">회차</th>
                                <th scope="col" class="title">제목</th>
                                <th scope="col" class="m_hidden">작성자</th>
                                <th scope="col" class="m_hidden">상태</th>
                                <th scope="col" class="m_hidden">등록일</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">
                          <c:if test="${not empty qnaQstnList }">
                          <c:forEach var="item" items="${qnaQstnList }" varStatus="status">
                       		 <tr class="notice">
                                <td class="m_hidden">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
                                <td class="title" data-title="회차" style="text-align: center;" >${item.parUnitNm }</td>
                                <td class="title" style="text-align: center;">
									<a href="javascript:readQstn(${item.qnaSn })">
									${fn:substring(item.qnaTitle,0, 30)}<c:if test="${fn:length(item.qnaTitle) > 30 }">...</c:if>
								</a>
								</td>
                                <td class="m_hidden" data-title="작성자">${item.regNm }</td>
                                <td class="m_hidden" data-title="상태">
                       				<c:if test="${item.stsCd eq 'WAIT' }"><label class="btn3 sm solid fcDarkgray">답변대기</label></c:if>
									<c:if test="${item.stsCd eq 'COMP' }"><label class="btn3 sm solid fcViolet">답변완료</label></c:if>
                                </td>
                                <td class="m_hidden" data-title="등록일"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}" /></td>
                            </tr>
                            </c:forEach>
                            </c:if>
                            <c:if test="${empty qnaQstnList }">
								<c:set var="colspan" value="6" />
								<tr>
									<td colspan="${colspan}">등록된 문의내역이 없습니다.</td>
								</tr>
							</c:if>
                        </tbody>
                    </table>	
	 <div class="board_pager">
        <span class="inner">
            <meditag:paging pageInfo="${pageInfo}" funcName="listAtcl" name="front"/>
        </span>
    </div>
