<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
	<c:if test="${not empty cmntList}">
		<div class="title"></div>
		<ul id="cmntList" style="margin-top:10px;">
			<c:forEach var="item" items="${cmntList }">
				<li>
					<div class="cmt_info">
						<span class="name">${item.regNm }</span>
						<span class="date"><meditag:dateformat type="0" delimeter="." property="${item.regDttm}" /></span>
						<c:if test="${item.regNo eq USERNO }">
							<button type="button" class="btn fr" onclick="delCmnt(${item.cmntSn})">삭제</button>
						</c:if>
					</div>
					${item.cmntCts }
				</li>
			</c:forEach>
		</ul>
		
	<div class="board_pager">
		<span class="inner"> 
			<meditag:paging pageInfo="${pageInfo}" funcName="listCmnt" name="front"/>
		</span>
	</div>
		
	</c:if>