<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

                    <table class="tstyle list">
                        <caption>설문 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col>
                            <col class="w25 m_hidden">
                            <col class="w15 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                        	
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="title">설문명</th>
                                <th scope="col" class="m_hidden">설문 기간</th>
                                <th scope="col" class="m_hidden">참여/결과</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">  
                        	<c:forEach  items="${orgReshList}" var="item" varStatus="status">                          
                            <tr>
                                <td class="m_hidden" data-title="번호">${fn:length(orgReshList) - status.index}</td>
                                <td class="title" data-title="설문명">
                                    <a href="javascript:reshInfo('${item.reshSn}')">${item.reshTitle}</a>
                                </td>
                                <td class="m_hidden" data-title="설문 기간"><meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/></td>
                                <td class="m_hidden" data-title="참여/결과">
                                	<c:if test="${ item.startDttm < nowTime && nowTime < item.endDttm }">
                                		<c:choose>
											<c:when test="${item.joinYn eq 'Y' }">
												<button class="btn type6" onclick="javascript:alert('<spring:message code="lecture.message.research.alert.join"/>');"><spring:message code="button.join.research.complete"/></button>
											</c:when>
											<c:when test="${item.joinYn eq 'N' }">
												<button class="btn type6" onclick="location.href='<c:url value="/home/resh/joinMain"/>?mcd=${MENUCODE }&amp;reshSn=${item.reshSn}'"><spring:message code="button.join.research"/></button>
											</c:when>
										</c:choose>
									</c:if>
									<c:if test="${nowTime < item.startDttm}">
										<button class="btn type6" onclick="alert('진행 가능한 시간이 아닙니다.');"><spring:message code="button.join.research"/></button>
									</c:if>                                            
									<c:if test="${item.endDttm < nowTime}">
										<c:if test="${item.joinYn eq 'Y'}">
											<button class="btn type6" onclick="javascript:alert('<spring:message code="lecture.message.research.alert.join"/>');"><spring:message code="button.join.research.complete"/></button>
										</c:if>
										<c:if test="${item.joinYn eq 'N'}">
											설문종료
										</c:if>
									</c:if>            		
                                </td>
                            </tr>
                            </c:forEach>
                             <c:if test="${empty orgReshList}">
                             <tr>
                                <td colspan="4" data-title="noData">조회된 내용이 없습니다.</td>
                            </tr>                           
 							</c:if>
                          
                        </tbody>
                    </table>

                    <div class="board_pager">
                        <span class="inner">
                            <meditag:paging pageInfo="${pageInfo}" funcName="listResh" name="front"/>
                        </span>
                    </div>
                    <!-- //board_pager -->
 
