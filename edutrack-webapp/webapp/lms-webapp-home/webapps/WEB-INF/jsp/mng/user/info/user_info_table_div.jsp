<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#userInfoTable th {background-color: #eee;}
	#userInfoTable td {vertical-align: middle;}
</style>

<table id="userInfoTable" summary="<spring:message code="user.title.userinfo.user.info"/>" class="table table-bordered wordbreak" style="margin-bottom:0px;">
			<colgroup>
				<col width="8%"/>
				<col width="8%"/>
				<col width="8%"/>
				<col width="8%"/>
				<col width="8%"/>
				<col width="8%"/>
				<col width="8%"/>
				<col width="8%"/>
				<col width="9%"/>
				<col width="9%"/>
				<col width="9%"/>
				<col width="9%"/>				
			</colgroup>
			<tr class="text-center">
				<th scope="row">회원명</th>
				<td>${vo.userNm}</td>
				<th scope="row">회원 로그인</th>
				<td>
					<c:if test="${not empty vo.wwwAuthGrpCd}">
						<a href="javascript:adminLogin();" class="btn btn-default btn-sm">로그인</a>
					</c:if>
				</td>			
				<th scope="row">휴대폰번호</th>
				<td>${vo.mobileNo} </td>
				<th scope="row">전화번호</th>
				<c:if test="${empty vo.homePhoneno }">
					<td class="text-center">
						-
					</td>
				</c:if>
				<c:if test="${not empty vo.homePhoneno }">
					<td>
					${vo.homePhoneno}
					</td>
				</c:if>
				<th scope="row">최근접속일</th>
				<td><meditag:dateformat type="8" delimeter="." property="${vo.lastLoginDttm}"/></td>
				<th scope="row">등록일</th>
				<td><meditag:dateformat type="1" delimeter="." property="${vo.regDttm}"/></td>
			</tr>
		</table>
		
<script>
	function adminLogin() {
		var userNo = '${vo.userNo}';
		var url = generateUrl("/mng/main/adminLoginProcess",{"userNo":userNo});
		var contentsWin = window.open(url);
	}
</script>		
