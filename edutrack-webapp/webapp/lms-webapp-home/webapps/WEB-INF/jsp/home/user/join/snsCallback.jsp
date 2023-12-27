<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){

        var userType = '${userType}';
        var message = '${message}';
        var url = '${url}';
        var memberClass = '${memberClass}';

        alert(message);
        if (memberClass == 'Y'){
            //신규 가입
            self.close();
            opener.location.href = url;
        } else if(memberClass == 'N'){
            //로그인
            self.close();
            opener.location.href = url;
        }
    });
</script>
