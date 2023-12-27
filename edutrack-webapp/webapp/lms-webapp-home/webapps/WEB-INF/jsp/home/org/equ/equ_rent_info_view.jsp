<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
	<!-- <div class="modal_cont"> -->
        <div class="board_top">
            <h4>대여 신청 정보</h4>
        </div>
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>장비명</label></li>
                <li>${rentInfo.equNm }</li>
            </ul>
            <ul class="list">
                <li class="head"><label>진행상태</label></li>
	                <c:set var="rentSts" value="${rentInfo.rentSts }" />
					<c:choose>
						<c:when test="${rentSts eq 'APPROVED' or rentSts eq 'RENT'}"><!-- 승인 -->
							<c:set var="fontColor" value="fcBlue" />
						</c:when>
						<c:when test="${rentSts eq 'RENT_CANCEL' or rentSts eq 'REQ_CANCEL'}"><!-- 예약취소 -->
							<c:set var="fontColor" value="gray" />
						</c:when>
						<c:when test="${rentSts eq 'CLOSE'}"><!-- 예약불가 -->
							<c:set var="fontColor" value="fcOlive" />
                        </c:when>
						<c:when test="${rentSts eq 'OVERDUE'}">
							<c:set var="fontColor" value="dark" /><!-- 기한초과 -->
						</c:when>
						<c:otherwise>
							<c:set var="fontColor" value="fcDarkgray" /><!-- 예약대기 -->
						</c:otherwise>
					</c:choose>           
				<li>
					<label class="btn3 sm solid ${fontColor }">${rentSts.codeNm }</label>
			    </li>
            </ul>
            <ul class="list">
                <li class="head"><label>예약날짜</label></li>
                <li>
                	<meditag:dateformat type="8" delimeter="." property="${rentInfo.rentStartDttm }" />
					~ <meditag:dateformat type="8" delimeter="." property="${rentInfo.rentEndDttm }" />
                </li>
            </ul>
            <ul class="list">
                <li class="head"><label>장비대여수</label></li>
                <li>${rentInfo.rentCnt }대</li>
            </ul>
            <ul class="list">
                <li class="head"><label>사용목적</label></li>
                <li>
                    ${rentInfo.rentDesc}
                </li>
            </ul>
        </div>
    <!-- </div> -->
    <br>
    <div class="modal_btns">
        <button type="button" class="btn type2" onclick="parent.modalBoxClose()">취소</button>
        <c:if test="${USERNO eq rentInfo.userNo and rentSts eq 'REQUEST' }">
	        <button type="button" class="btn type4" onclick="rentCancel()">예약취소</button>
 		</c:if>
    </div>
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
	});
	
	function rentCancel() {
		var rentCd = '${rentInfo.rentCd}';
		
		$.post(cUrl("/home/org/equ/cancelRentRequest"), {
			"rentCd" : rentCd
		},
			function(data) {
				alert(data.message);
				if(data.result == 1) {
					parent.location.reload();
					parent.modalBoxClose();
				}
			}
		);
	}
</script>
