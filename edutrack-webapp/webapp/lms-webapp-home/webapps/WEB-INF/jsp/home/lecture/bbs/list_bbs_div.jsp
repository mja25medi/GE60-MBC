<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
	<table class="tstyle list">
                      <caption>공지사항 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w8 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="title">제목</th>
                                <th scope="col" class="m_hidden">첨부파일</th>
                                <th scope="col" class="m_hidden">작성자</th>
                                <th scope="col" class="m_hidden">등록일</th>
                                <th scope="col" class="m_hidden">조회수</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">
                            <c:if test="${not empty bbsAtclList }">
								<c:forEach var="item" items="${bbsAtclList }" varStatus="status">
								 <tr class="notice">
	                                <td class="m_hidden">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
	                                <td class="title">
	                                	<a href="javascript:readAtcl('${item.atclSn}','${item.lockYn }','${item.originRegNo }', '${item.regNo }','${item.parRegNo }' )">
											${fn:substring(item.title,0, 30)}<c:if test="${fn:length(item.title) > 30 }">...</c:if>
											<c:if test="${item.cmntCnt > 0 }">(${item.cmntCnt })</c:if>
											<c:if test="${item.lockYn eq 'Y'}"><img src="/img/common/lock.gif"  alt="Lock"/></c:if>
											<c:if test="${item.recently eq 'NEW' }">
												<i class="xi-new" aria-hidden="true"></i><span class="sr-only">새글</span>
											</c:if>										
											</a>
									</td>
	                                <td class="m_hidden">
											<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="fiSts">
												<a href="#" onclick="javascript:fileDown('${fileItem.fileSn}');" title="Download: ${fileItem.fileNm}">
													<i class="xi-attachment"></i>
													<span class="sr-only"></span>
												</a>
											</c:forEach>
										</td>
	                                <td class="m_hidden" data-title="작성자">${item.regNm }</td>
	                                <td class="m_hidden" data-title="등록일"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}" /></td>
	                                <td class="m_hidden" data-title="조회수">${item.hits}</td>
                          		  </tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty bbsAtclList }">
								<c:set var="colspan" value="6" />
								<tr>
									<td colspan="${colspan}"><spring:message code="board.message.bbs.atcl.nodata" /></td>
								</tr>
							</c:if>
                        </tbody>
                    </table>
	
		
	 <div class="board_pager">
        <span class="inner">
            <meditag:paging pageInfo="${pageInfo}" funcName="listAtcl" name="front"/>
        </span>
    </div>
