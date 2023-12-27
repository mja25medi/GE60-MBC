<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="ProjectResultVO" value="${vo}"/><!-- 과제정보 -->
<c:set var="prjAssignmentSendVO" value="${prjAssignmentSendVO}"/><!-- 제출정보 -->
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:css href="css/home/pop.css" />
</mhtml:class_head>

<mhtml:body>
<div class="wrap" style="width:100%;">
	<h1>제출 정보</h1>
	<div class="contents">
		<form id="projectResultForm" name="projectResultForm" onsubmit="return false" action="/lec/prj/result">
			<input type="hidden" name="prjtSn" value="${ProjectResultVO.prjtSn}" />
			<div style="padding: 15px;">
				과제 정보
			</div>
			<table class="board_b">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<th class="top" scope="row">과제명</th> 
					<td class="top" colspan="3">
						${ProjectResultVO.asmtTitle}
						</td>
				</tr>
				<tr> 
					<th scope="row">제출시간</th>
					<td colspan="3" colspan="3">
						 ${ProjectResultVO.asmtStartDttm}~${ProjectResultVO.asmtEndDttm}
					</td>
				</tr>
				<tr> 
					<th scope="row">과제내용</th>
					<td colspan="3" colspan="3">
						 <div style="float:left;width:100%;min-height:130px;" class="tx-content-container">
						 	${ProjectResultVO.asmtCts}
						 </div>
					</td>
				</tr>
				
				<tr>
		            <th scope="row">첨부파일</th>
		            <td style="width: 65px" colspan="3">
		                
		            </td>
		       </tr>
			</table>
			
			<c:choose>
			<c:when test="${prjAssignmentSend == 'N'}">
				<div style="padding: 15px;">
					제출 정보
				</div>
				<table class="board_b">
					<colgroup>
						<col/>
					</colgroup>
					<tr>
						<td class="top" style="text-align: center;">
							<font color=blud>* 제출 정보가  없습니다.</font>
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<div style="padding: 15px;">
					제출 정보
				</div>
				<table class="board_b">
					<colgroup>
						<col style="width:20%"/>
						<col style="width:30%"/>
						<col style="width:20%"/>
						<col style="width:30%"/>
					</colgroup>
					<tr>
						<th class="top" scope="row">제출팀</th> 
						<td class="top" colspan="3">
							${prjAssignmentSendVO.prjtTeamNm}
						</td>
					</tr>
					<tr> 
						<th scope="row">과제내용</th>
						<td colspan="3" colspan="3">
							 <div style="float:left;width:100%;min-height:130px;" class="tx-content-container">
							 	${prjAssignmentSendVO.sendCts}
							 </div>
						</td>
					</tr>
					<tr>
			            <th scope="row">첨부파일</th>
			            <td style="width: 65px" colspan="3">
			            </td>
			       </tr>
			       
				</table>
			</c:otherwise>
			</c:choose>	
			<div class="btn_right">
				<a href="javascript:window.close()" class="btn01">닫기</a>
			</div>
		</form>
	</div>
</div>
</mhtml:body>
	
</mhtml:class_html>