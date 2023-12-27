<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<table summary="사용자 정보 폼" style="width:100%" align="center" class="table_dtl">
		<colgroup>
			<col width="30%"/>
			<col width="70%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row">회원사</th>
			<td class="top">${usrUserInfoDTO.orgNm}</td>
		</tr>
		<tr height="33">
			<th scope="row">아이디</th>
			<td>${usrUserInfoDTO.userId}</td>
		</tr>
		<tr height="33">
			<th scope="row">이름</th>
			<td>${usrUserInfoDTO.userNm}</td>
		</tr>
		<tr height="33">
			<th scope="row">전화번호</th>
			<td>${usrUserInfoDTO.homePhoneno}</td>
		</tr>
		<tr height="33">
			<th scope="row">핸드폰번호</th>
			<td>${usrUserInfoDTO.mobileNo}</td>
		</tr>
		<tr height="33">
			<th scope="row">이메일</th>
			<td>${usrUserInfoDTO.email}</td>
		</tr>
	</table>
	<div style="float:right;margin-top:5px;">
		<meditag:button value="강사등록" title="강사등록" func="addTeacher('${usrUserInfoDTO.userNo}')" />
		<meditag:button value="닫기" title="닫기" func="closeView()" />
	</div>
