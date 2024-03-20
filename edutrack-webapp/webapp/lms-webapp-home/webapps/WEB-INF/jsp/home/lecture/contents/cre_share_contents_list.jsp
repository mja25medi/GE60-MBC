<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibMediaCntsList" value="${clibMediaCntsList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
	<div class="segment">
	 
	 	<div class="res_tbl_wrap lecture_setting">
             <table>
                <caption>미디어콘텐츠 목록</caption>
                <thead>
                    <tr>
                        <th scope="col" width="auto"><spring:message code="library.title.contents.name"/></th>
                        <th scope="col" width="15%">등록일자</th>
                        <th scope="col" width="10%">설정</th>
                    </tr>
                </thead>
                <tbody>
                	 <c:forEach items="${clibMediaCntsList}" var="item" varStatus="status">
					 	<tr>
							 <td scope="row" class="title" data-label="제목">
                                ${item.cntsNm}                                  
                              </td>
                              <td class="time_setting" data-label="등록일자">
                                  <meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/>                             
                              </td>
                              <td data-label="설정"><button class="btn type3" onclick="shareContentsEdit('${item.cntsCd}')">설정</button></td>    
                     	</tr>
                     </c:forEach>
                     <c:if test="${empty clibMediaCntsList}">
                     	<tr>
                     		<td colspan="3"><spring:message code="common.message.nodata"/></td>
                     	</tr>
					</c:if>
                </tbody>
             </table>
        </div> 
	 
		 <div class="board_pager">
	        <span class="inner">
	            <meditag:paging pageInfo="${pageInfo}" funcName="listPageing" name="front"/>
	        </span>
	      </div>
	</div>
	   

