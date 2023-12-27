<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="usrUserInfoVO" value="${userInfoForm.usrUserInfoVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:js src="/js/common_iframe.js"/>

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<ul class="nav nav-tabs" id="tabMenu">
  		<li><a href="javascript:onclickTab(0)"><spring:message code="user.title.userinfo.user.info"/></a></li>
  		<li class="active"><a href="javascript:onclickTab(1)"><spring:message code="user.title.userinfo.teacher.into"/></a></li>
	</ul><br/>
	<iframe name="subWorkFrame" id="subWorkFrame" frameborder="0" src="about:blank" scrolling="no" title="sub work frame" style="width:100%; min-height:400px; overflow: scroll;"></iframe>

<script type="text/javascript">
$(document).ready(function() {
	$('#tabMenu a').click(function (e) {
		  $(this).tab('show');
		})

	onclickTab(1);
});

function onclickTab(tab) {
	var url = {};
	url['0'] = "/mng/user/userInfo/editUserPop?usrUserInfoVO.userNo=${usrUserInfoVO.userNo}";
	url['1'] = "/mng/user/userInfo/editFormTeacher/?usrUserInfoVO.userNo=${usrUserInfoVO.userNo}";

	document.subWorkFrame.location.href = cUrl(url[tab]);
}

function modalBoxClose(){
	parent.modalBoxClose();
}


</script>
</mhtml:frm_body>
</mhtml:mng_html>