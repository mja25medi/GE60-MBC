<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="projectResultVO" value="${projectResultVO}"/><!-- 과제정보 -->
<c:set var="prjAssignmentSendVO" value="${prjAssignmentSendVO}"/><!-- 제출정보 -->
<mhtml:html>
<mhtml:head titleName="평가관리">
	<meditag:js src="/js/selectbox.js"/>
</mhtml:head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<form id="projectResultForm" name="projectResultForm" onsubmit="return false" action="/LectureProjectResultAdmin.do">
	<input type="hidden" name="cmd" />
	<input type="hidden" name="prjtSn" />
	<div style="padding: 15px;">
		과제 정보
	</div>
	<table summary="제출정보" style="width:96%" class="table_dtl" align="center">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row">과제명</th> 
			<td class="top" colspan="3">
				${projectResultVO.asmtTitle}
				</td>
		</tr>
		<tr> 
			<th scope="row">제출시간</th>
			<td colspan="3" colspan="3">
				 ${projectResultVO.asmtStartDttm}~${projectResultVO.asmtEndDttm}
			</td>
		</tr>
		<tr> 
			<th scope="row">과제내용</th>
			<td colspan="3" colspan="3">
				 <div style="float:left;width:100%;min-height:130px;" class="tx-content-container">
				 	${projectResultVO.asmtCts}
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
		<table summary="제출정보" style="width:96%" align="center" class="table_dtl">
			<colgroup>
				<col/>
			</colgroup>
			<tr height="35">
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
	<table summary="제출정보" style="width:96%" class="table_dtl" align="center">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr height="35">
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
	
	<table style="width:96%">
		<tr>
			<td>
				<meditag:buttonwrapper>
					<meditag:button value="닫기" title="닫기" func="parent.projectPopBox.close();" />
				</meditag:buttonwrapper>
			</td>
		</tr>
	</table>
	
	</form>
	</mhtml:frm_body>
	

</mhtml:html>