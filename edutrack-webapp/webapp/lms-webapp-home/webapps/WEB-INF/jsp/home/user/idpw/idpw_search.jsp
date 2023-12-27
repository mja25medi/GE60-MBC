<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<c:url var="img_base" value="/img/home" />
<c:url var="gpinUrlId"		value="/UserHome.do?cmd=gpinFindIdRequest" />
<c:url var="gpinUrlPass"	value="/UserHome.do?cmd=gpinFindPassRequest" />
<c:url var="nmchkUrlId"		value="/UserHome.do?cmd=nmchkFindIdRequest" />
<c:url var="nmchkUrlPass"	value="/UserHome.do?cmd=nmchkFindPassRequest" />
<c:url var="sfchkUrlId"		value="/UserHome.do?cmd=sfchkFindIdRequest" />
<c:url var="sfchkUrlPass"	value="/UserHome.do?cmd=sfchkFindPassRequest" />
<c:url var="authUrl" 		value="/UserHome.do?cmd=joinNameCheck"/>

<c:set var="display"  value=""/>
<c:if test="${COLOR_TPL eq '007'}">
<c:set var="display"  value="display: none;"/>
<style>
.row{margin-right: 0px;margin-left: 0px;}
</style>
</c:if>

				<div class="row">
					<div class="col-lg-12">
<c:if test="${COLOR_TPL eq '007'}">
						<ul class="nav nav-tabs" id="tabMenu">
							<li <c:if test="${tab eq 'searchId'}"> class="active"</c:if> ><a href="/edu/edu/UserHome.do?cmd=searchIdPw&amp;mcd=${findidMcd}&amp;tab=searchId"><spring:message code="user.title.login.search.id"/></a></li>
							<li <c:if test="${tab eq 'searchPw'}"> class="active"</c:if> ><a href="/edu/edu/UserHome.do?cmd=searchIdPw&amp;tab=searchPw"><spring:message code="user.title.login.search.pass"/></a></li>
						</ul><br/>
</c:if>
						<form name="Input" onsubmit="return false;">
						<div id="idArea" style="${display}">
							<h4><i class="glyphicon glyphicon-info-sign"></i> <spring:message code="user.title.login.search.id"/></h4>
							<div id="idSearchArea" style="margin-top:10px;">

							</div>

							<div class="well" id="idSearchAreaForm" style="margin-top:10px; ">
								<ul style="list-style-type: none">
									<li style="height:30px;margin-bottom:5px;">
										<span style="float:left;width: 80px;line-height:30px;font-weight: bold"><spring:message code="user.title.userinfo.name"/></span>
										<input type="text" style="width:200px;" class="form-control input-sm" name="userName" id="idSearchUserName"/>
									</li>
									<li style="height:30px;margin-bottom:5px;">
										<span style="float:left;width: 80px;line-height:30px;font-weight: bold"><spring:message code="user.title.userinfo.email"/></span>
										<input type="text" style="width:200px;" class="form-control input-sm" name="email" id="idSearchEmail"/>
									</li>
								</ul>
								<div class="well well-sm" style="background-color:#5bc0de;margin-top:10px;margin-bottom:0px;">
									<ul style="list-style-type: none;padding-left:0px;">
										<li><spring:message code="user.message.login.search.id.msg1"/></li>
										<li><spring:message code="user.message.login.search.id.msg2"/></li>
									</ul>
								</div>
							</div>
							<div class="text-center">
								<a href="javascript:idSearch()" class="btn btn-primary"><spring:message code="button.search.id"/></a>
							</div>
						</div>

						<div id="pwArea" style="${display}">
							<h4 style="<c:if test="${COLOR_TPL ne '007'}">margin-top:50px;</c:if>"><i class="glyphicon glyphicon-info-sign"></i> <spring:message code="user.title.login.search.pass"/></h4>
							<div id="passSearchArea" style="margin-top:10px;">

							</div>
							<div class="well" id="passSearchAreaForm" style="margin-top:10px;">
								<ul style="list-style-type: none">
									<li style="height:30px;margin-bottom:5px;">
										<span style="float:left;width: 80px;line-height:30px;font-weight: bold"><spring:message code="user.title.userinfo.userid"/></span>
										<input type="text" style="width:200px;" class="form-control input-sm" name="userId" id="passSearchUserID"/>
									</li>
									<li style="height:30px;margin-bottom:5px;">
										<span style="float:left;width: 80px;line-height:30px;font-weight: bold"><spring:message code="user.title.userinfo.name"/></span>
										<input type="text" style="width:200px;" class="form-control input-sm" name="userName" id="passSearchUserName"/>
									</li>
									<li style="height:30px;margin-bottom:5px;">
										<span style="float:left;width: 80px;line-height:30px;font-weight: bold"><spring:message code="user.title.userinfo.email"/></span>
										<input type="text" style="width:200px;" class="form-control input-sm" name="email" id="passSearchEmail"/>
									</li>
								</ul>
								<div class="well well-sm" style="background-color:#5bc0de;margin-top:10px;margin-bottom:0px;">
									<ul style="list-style-type: none;padding-left:0px;">
										<li><spring:message code="user.message.login.search.pass.msg1"/></li>
										<li><spring:message code="user.message.login.search.pass.msg2"/></li>
									</ul>
								</div>
							</div>
							<div class="text-center">
								<a href="javascript:passSearch()" class="btn btn-primary"><spring:message code="button.search.pass"/></a>
							</div>
						</div>
						</form>
					</div>
				</div>

<script type="text/javascript">
	var ItemDTO = {};
	$(document).ready(function() {
		ItemDTO.searchId 	= 'F';
		ItemDTO.searchPass 	= 'F';
<c:if test="${COLOR_TPL eq '007'}">
	<c:if test="${tab eq 'searchId'}">
		$("#idArea").show();
	</c:if>
	<c:if test="${tab eq 'searchPw'}">
		$("#pwArea").show();
	</c:if>
</c:if>
	});

	function idSearch() {
		if(ItemDTO.searchId == 'F'){
			var userNm = $("#idSearchUserName").val();
			var email = $("#idSearchEmail").val();

			if(isEmpty(userNm)) {
				alert('<spring:message code="user.message.login.search.alert.input.name"/>');
				return;
			}
			if(isEmpty(email)) {
				alert('<spring:message code="user.message.login.search.alert.input.email"/>');
				return;
			}
			$("#idSearchAreaForm").hide();
			$("#idSearchArea")
				.load(
					cUrl("/home/user/findId"),
					{
						"userNm":userNm,
						"email":email
					}
				)
			$("#idSearchArea").show()
			ItemDTO.searchId 	= 'S';
		}else if(ItemDTO.searchId == 'S'){
			$("#idSearchAreaForm").show();
			$("#idSearchArea").empty();
			$("#idSearchArea").hide();

			ItemDTO.searchId 	= 'F';
		}
	}

	function passSearch() {
		if(ItemDTO.searchPass == 'F'){
			var userId = $("#passSearchUserID").val();
			var userNm = $("#passSearchUserName").val();
			var email = $("#passSearchEmail").val();


			if(isEmpty(userId)) {
				alert('<spring:message code="user.message.login.search.alert.input.userid"/>');
				return;
			}
			if(isEmpty(userNm)) {
				alert('<spring:message code="user.message.login.search.alert.input.name"/>');
				return;
			}
			if(isEmpty(email)) {
				alert('<spring:message code="user.message.login.search.alert.input.email"/>');
				return;
			}


			$("#passSearchArea")
				.load(
					cUrl("/home/user/findPass"),
					{
						"userId":userId,
						"userNm":userNm,
						"email":email
					}
				)
			$("#passSearchArea").show();
			$("#passSearchAreaForm").hide();
			ItemDTO.searchPass 	= 'S';
		}else if(ItemDTO.searchPass == 'S'){
			$("#passSearchAreaForm").show();
			$("#passSearchArea").empty();
			$("#passSearchArea").hide();

			ItemDTO.searchPass 	= 'F';
		}
	}

	function reloadPage() {
		document.location.reload();
	}
</script>
	
