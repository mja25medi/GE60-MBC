<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


<div class="member">
    <div class="flex-container">
        <div class="con">
       
            <div class="tit-header">
                <h2 class="title">회원가입 하기</h2>
                <div class="desc">ID/PW를 입력하여 직접 회원 가입 또는 소셜 회원가입이 가능합니다.</div>
            </div>

            <div class="entry_btn">
                <button onClick="hp_join()" title="일반 회원 가입" id="logincheck">일반 회원 가입</button>
            </div>
            <div class="ui horizontal divider">또는</div>
            <div class="btns-join center mt30">
                <a href="#0" class="btn kakao" onClick="javascript:joinKakao()">카카오로 시작하기</a>
                <a href="#0" class="btn naver" onClick="javascript:joinNaver()">네이버로 시작하기</a>
            </div>
            
            
        </div>

    </div>
</div>
<script>
function hp_join() {
	location.href="/home/user/joinStep01_Main"
}

function joinKakao() {
	window.open('${kakaoAuthUrl}', 'kakaologinpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550');
}

function joinNaver() {
	window.open('${naverAuthUrl}', 'naverloginpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550');
}
</script>