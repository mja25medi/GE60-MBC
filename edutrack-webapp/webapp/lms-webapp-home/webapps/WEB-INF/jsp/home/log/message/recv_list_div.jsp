<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="itemList" value="${messageList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
		                    
		             <div class="board_info">
                        <span class="caution fcRed">* 쪽지는 최대 180일 동안 보관됩니다.</span>
                    </div>     
                     
		              <table class="tstyle list">
                        <caption>쪽지 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col class="w15 m_hidden">
                            <col>
                            <col class="w15 m_hidden">
                            <col class="w10 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="m_hidden">보낸사람</th>
                                <th scope="col" class="title">제목</th>
                                <th scope="col" class="m_hidden">보낸시간</th>
                                <th scope="col" class="m_hidden">삭제</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">                            
                            <c:if test="${not empty itemList }">
	                        	<c:forEach var="item" items="${itemList }" varStatus="status">    
		         		            <tr>
		         		            	<td class="m_hidden" data-title="번호">${fn:length(itemList) - status.index}</td>
		         		            	<td class="m_hidden">${item.sendNm }</td>
		         		            	<td class="title"><a href="javascript:viewMsg(${item.msgSn},${item.msgTransSn});">${item.title}</a></td>
		                                <td class="m_hidden"><meditag:dateformat type="0" delimeter="." property="${item.regDttm }" /></td>
		                                <td class="m_hidden"><button class="btn type3" onclick="javascript:deleteMsg(${item.msgTransSn});">삭제</button></td>                                            
		                            </tr>
	                        	</c:forEach>
	                        	</c:if>
	                        	<c:if test="${empty itemList }">
	             					<tr>
	             						<td class="txt_center" colspan="5">쪽지 내역이 없습니다.</td>
	             					</tr>
	                        	</c:if>
                        </tbody>
                    </table>
                   	<div class="board_pager">
						<span class="inner"> 
							<meditag:paging pageInfo="${pageInfo}" funcName="listMsg" name="front"/>
						</span>
					</div>
							
