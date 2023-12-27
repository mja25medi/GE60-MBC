<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script>
    /* Request값 받기 */
    var returnUrl = "${returnUrl}";
    var type = "${type}";

    // 추가 처리사항이 있을 경우 사용
    switch (type) {
        case "login":
            // 로그인 완료
            if("${ssoResultCode}" != "0"  || Number("${resultCode}") < 0 ) {
				alert("로그인 실패!! 다시시도해 주세요.");
			} else if(Number("${resultCode}") ==0) {
				returnUrl = "/";
			} else if(Number("${resultCode}") ==1) { //첫 회원가입 후 추가 정보 등록 화면으로 이동
				returnUrl = "/home/main/goMenuPage?mcd=${editMyinfoMcd}";
			}
            break;
        case "terms":
            // 별도 이용약관 동의가 필요한 경우
            break;
        case "join":
            // 회원가입 완료
            break;
        case "changepwd":
            // 비밀번호 변경완료
            break;
        case "withdraw":
            // 회원탈퇴 완료
            break;
    }
    if (returnUrl != "") {
        // returnUrl이 있을 경우 부모창 페이지 이동
        window.opener.location.href = returnUrl;
     } else {
        // returnUrl이 없을 경우 부모창 새로고침
        window.opener.location.reload();
    }

    // 익스플로러 확인창 없이 새창 닫기
    window.open('about:blank', '_self').close();
</script>
