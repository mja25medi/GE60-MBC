<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
	<div class="modal_cont">
		<div class="board_top">
           <h4>반영율</h4>
        </div>
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>과정명</label></li>
                <li>${creCourse.crsCreNm }</li>
            </ul>
            <ul class="list">
                <li class="head"><label>진도</label></li>
                <li>${creCourse.prgrRatio }%</li>
                <li class="head"><label>시험</label></li>
                <li>${creCourse.examRatio }%</li>
            </ul>
            <ul class="list">
                <li class="head"><label>과제</label></li>
                <li>${creCourse.asmtRatio }%</li>
                <li class="head"><label>진행단계</label></li>
                <li>${creCourse.semiExamRatio }%</li>
            </ul>
            <ul class="list">
                <li class="head"><label>합계</label></li>
                <li><div class="total">100%</div></li>
            </ul>
        </div>
        <div class="board_top mt30">
            <h4>성적</h4>
        </div>
        <div class="table_list">
        	<c:if test="${not empty eduResult }">
            <ul class="list">
                <li class="head"><label>수강생명</label></li>
                <li>${eduResult.userNm }</li>
            </ul>
            <ul class="list">
                <li class="head"><label>진도</label></li>
                <li>${eduResult.prgrScore }%</li>
                <li class="head"><label>시험</label></li>
                <li>${eduResult.examScore }점</li>
            </ul>
            <ul class="list">
                <li class="head"><label>과제</label></li>
                <li>${eduResult.asmtScore }점</li>
                <li class="head"><label>진행단계</label></li>
                <li>${eduResult.semiExamScore }점</li>
            </ul>
            <ul class="list">
                <li class="head"><label>총점</label></li>
                <li><div class="total">${eduResult.totScore }점</div></li>
            </ul>
            </c:if>
            <c:if test="${empty eduResult }">
				<li><div class="txt-center" >입력된 성적 정보가 없습니다.</div></li>
			</c:if>
            
        </div>
        <ul class="message_box mt10">
            <li>필수 수료기준 : <span class="fcRed">진도율 80% 이상</span>, 환산 수료 점수 <span class="fcRed">60점 이상</span></li>
        </ul>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn" onclick="parent.modalBoxClose()">닫기</button>
    </div>
			
			


<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
