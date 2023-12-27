<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br>
	<div id="messageArea">
		<table style="width:98%" class="table table-bordered wordbreak" align="center">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top">제목</th>
				<td class="top" colspan="3">${detailMsg.title}</td>
			</tr>			
			<tr>
				<th scope="row" class="top">발신자</th>
				<td class="top" colspan="3">${detailMsg.sendNm}</td>
			</tr>
			<tr>
				<th scope="row" class="top">수신자</th>
				<td class="top" colspan="3">${detailMsg.recvNm}</td>
			</tr>
			<tr>
				<th scope="row" class="top">등록일자</th>
				<td class="top" colspan="3">${detailMsg.regDttm}</td>
			</tr>
			<tr>
				<th scope="row" class="top">내용</th>
				<td class="top" colspan="3">${detailMsg.cts}</td>
			</tr>			
		</table>
		
		<p><strong>발송 리스트 상세 내역</strong></p>
                <table class="table tablet table-bordered">
                    <thead>
                        <tr>
                        	<th scope="col">번호</th>
                            <th scope="col">아이디</th>
                            <th scope="col">이름</th>
                            <th scope="col">
                            	<c:if test="${detailMsg.msgDivCd eq 'SMS' }">휴대폰번호</c:if>
                            	<c:if test="${detailMsg.msgDivCd eq 'MSG' }">회원번호</c:if>
                            	<c:if test="${detailMsg.msgDivCd eq 'EMAIL' }">이메일</c:if>
                            </th>
                            <th scope="col">전송시간</th>
                            <th scope="col">전송상태</th>                                            
                        </tr>
                    </thead>
                    <tbody class="text-center">
                    	<c:forEach var="item" items="${transList }" varStatus="status">
                   		<tr>
	                     	<td>${status.count }</td>
	                     	<td><a href="javascript:crmOpen('${item.userNo }');">${item.recvId }</a></td>
	                     	<td>${item.recvNm}</td>
	                     	<td>${item.recvAddr }</td>
	                     	<td>
								<c:choose>
									<c:when test="${detailMsg.msgDivCd eq 'MSG'}">
										<meditag:dateformat type="0" delimeter="." property="${detailMsg.regDttm }" />
									</c:when>
									<c:otherwise>
										<meditag:dateformat type="0" delimeter="." property="${item.regDttm }" />															
									</c:otherwise>
								</c:choose>	                     	
	                     	</td>
	                     	<td>
								<c:choose>
									<c:when test="${detailMsg.msgDivCd eq 'MSG'}">
										성공
									</c:when>
									<c:otherwise>
										<c:if test="${item.sendSts eq 'S' }">성공</c:if>
		                            	<c:if test="${item.sendSts eq 'F' }">실패</c:if>
		                            	<c:if test="${item.sendSts eq 'R' }">대기</c:if>
		                            	<c:if test="${item.sendSts eq 'D' }">수신거부</c:if>																
									</c:otherwise>
								</c:choose>
	                     	</td>
                     	<tr>                    	
                    	</c:forEach>
                    </tbody>
                </table>
			<div class="text-right">
				<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
			</div>
		</div>
		
<script type="text/javascript">

	function crmOpen(userNo) {
		var url = generateUrl("/mng/user/userInfo/crmOpenPop",{"userNo":userNo});
		window.open(url);
	}
</script>