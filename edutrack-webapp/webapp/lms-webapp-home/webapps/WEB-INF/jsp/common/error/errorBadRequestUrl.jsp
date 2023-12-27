<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
// String errorCode = exception.getMessage();
// try {
// 	errorCode = exception.getMessage().split(":")[1].replaceAll(" ", "");
// } catch (Exception e) {}
// request.setAttribute("errorCode", errorCode);
%>
<html>
<mhtml:head>
	<script type="text/JavaScript">
		$(document).ready(function(){
			$("#btnOk").bind("click", eventBacktohome);
			function eventBacktohome(event) {
				alert(document.parent);
				if(opener != null) {
					window.close();
				} else if(document.parent != undefined) {
					parent.document.location.reload();
				} else {
					document.location.href = "/";
					
				}
			}
		});	
	</script>
</mhtml:head>
<body>
    <section class="content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="box ">
                    <div class="box-header with-border" style="padding:5px 15px 5px 15px;">
                    	<h3><spring:message code="common.title.error.badrequest.nomethod"/></h4>
                    </div>
                    <div class="box-body wordbreak" style="background: url('<c:url value="/img/common/error_none.png"/>') no-repeat 10px 10px; padding-left:160px;">
						<h4>
							<spring:message code="common.messate.error.badrequest.nomethod"/>
                    	</h4>
                    	<button class="btn btn-success btn-block" id="btnOk"><spring:message code="button.ok"/></button>
                    </div>
                </div>
            </div>
        </div>
    <section>
</body>
</html>