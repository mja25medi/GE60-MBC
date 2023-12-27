<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<!-- <div class="modal_cont"> -->
	<div class="board_top">
	    <h4>예약 정보</h4>
	</div>
	<div class="table_list">
	    <ul class="list">
	        <li class="head"><label>예약시설</label></li>
	        <li>${resInfo.facNm }</li>
		</ul>
		<ul class="list">
	    	<li class="head"><label>진행상태</label></li>
	   		<c:set var="resSts" value="${resInfo.resSts }" />
			<c:choose>
				<c:when test="${resSts eq 'RESERVED'}"><!-- 예약완료 -->
					<c:set var="fontColor" value="fcBlue" />
				</c:when>
				<c:when test="${resSts eq 'CANCEL' or resSts eq 'CLOSE'}"><!-- 예약취소/종료 -->
					<c:set var="fontColor" value="gray" />
				</c:when>
				<c:when test="${resSts eq 'RETURN'}">
					<c:set var="fontColor" value="dark" /><!-- 예약반려 -->
				</c:when>
				<c:otherwise>
					<c:set var="fontColor" value="fcDarkgray" /><!-- 예약대기 -->
				</c:otherwise>
			</c:choose>           
			<li>
				<label class="btn3 sm solid ${fontColor }">${resSts.codeNm }</label>
		    </li>
		</ul>
		<ul class="list">
		    <li class="head"><label>예약날짜</label></li>
		    <li>${resInfo.resDt } ${resInfo.resStm } ~ ${resInfo.resEtm }</li>
		</ul>
		<ul class="list">
		    <li class="head"><label>사용목적</label></li>
		    <li>${resInfo.eventDesc }</li>
		</ul>
		<ul class="list">
		    <li class="head"><label>사용인원</label></li>
		    <li>${resInfo.attCnt}명</li>
	    </ul>
	</div>
<!-- </div> -->
<br>
<div class="modal_btns">
	<button type="button" class="btn type2" onclick="parent.modalBoxClose()">취소</button>
	<c:if test="${USERNO eq resInfo.userNo and resSts eq 'WAIT' }">
		<button type="button" class="btn type4" onclick="goFacCancle()">예약취소</button>
	</c:if>
</div>
    
<script type="text/javascript">
	// 페이지 초기화
	
	function goFacCancle() {
		var resCd = '${resInfo.resCd}';
		
		$.post(cUrl("/home/org/fac/cancelResWait"),{
			"resCd" : resCd
		}, 
		function(data) {
			alert(data.message);
			if(data.result == 1) {
				parent.location.reload();
				parent.modalBoxClose();
			}
		});
	}
	
	function goHref() {
		document.location.href="/home/org/fac/resListMain";
	}
</script>
