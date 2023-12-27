<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<% request.setAttribute("vEnter", "\n"); %>
<c:set var="usrUserInfoVO" value="${vo}"/>
<c:set var="tchInfoVO" value="${tchInfoVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>

</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<ul class="nav nav-tabs" id="tabMenu">
  		<li><a href="<c:url value="/mng/user/userInfo/viewUserPop?userNo=${usrUserInfoVO.userNo}"/>"><spring:message code="user.title.userinfo.user.info"/></a></li>
  		<li class="active"><a href="#none"><spring:message code="user.title.userinfo.teacher.into"/></a></li>
	</ul>

	
	<form id="userInfoForm" name="userInfoForm" onsubmit="return false">
		<input type="hidden" name="idCheck" id="idCheck" value="N" />	
		<input type="hidden" name="userNo" value="${vo.userNo}" />
		<input type="hidden" name="orgCd" value="${vo.orgCd}" />
		<input type="hidden" name="tchInfoVO.userNo" value="${tchInfoVO.userNo}" />
	
	<table summary="<spring:message code="teacher.title.teacherinfo.teacher.manage"/>" class="table table-bordered wordbreak" style="margin-top:30px;">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr>
			<th class="top" scope="row"><label for="userId"><spring:message code="user.title.userinfo.name"/></label></th>
			<td class="top" colspan="3">
				${tchInfoVO.userNm}
			</td>
		</tr>
		<tr height="35">
			<th class="top" scope="row"><label for="userId"><spring:message code="teacher.title.teacherinfo.tchctgrcd"/></label></th>
			<td class="top">
				<meditag:codename code="${tchInfoVO.tchCtgrCd}" category="TCH_CTGR_CD" />
			</td>
			<th class="top" scope="row"><spring:message code="teacher.title.teacherinfo.tchdivcd"/></th>
			<td class="top">
				<meditag:codename code="${tchInfoVO.tchDivCd}" category="TCH_DIV_CD" />
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="crer"><spring:message code="teacher.title.teacherinfo.crer"/></label></th>
			<td colspan="3" class="wordbreak">
				${fn:replace(tchInfoVO.crer,vEnter,"<br />")}
			</td>
		</tr>
		<tr >
			<th scope="row"><label for="major"><spring:message code="teacher.title.teacherinfo.major"/></label></th>
			<td colspan="3" class="wordbreak">
				${fn:replace(tchInfoVO.major,vEnter,"<br />")}
			</td>
		</tr>
	</table>

	<div class="text-right">
		<a href="javascript:tchEdit('${tchInfoVO.userNo}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
	
	<br/>
	<form name="Search" id="Search" onsubmit="return false">
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.school"/></h5>
		</div>
	</div>
	<div id="schoolList" style="width:100%;float:left">
	</div>

	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.writing"/></h5>
		</div>
	</div>
	<div id="writeList" style="width:100%;float:left;">
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	var originOrgCd = '${usrUserInfoVO.orgCd}';

	// 페이지 초기화
	$(document).ready(function() {
		var width = (parent.window.innerWidth > 0) ? parent.window.innerWidth : parent.screen.width;
		width = width - 20;
		if(width > 1000) width = 1000;
		parent.modalBox.resize(width,800);

		listSchool();
	    listWriting();
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').attr("action" , "/mng/tch/info/" + cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			parent.listPageing();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	function listSchool() {
		$('#schoolList').load(
			cUrl("/mng/tch/schs/listSchool"),
			{ "userNo": "${usrUserInfoVO.userNo}", "isView":"Y" }
		);
	}

	function listWriting() {
		$('#writeList').load(
			cUrl("/mng/tch/writing/listWrite"),
			{ "userNo": "${usrUserInfoVO.userNo}", "isView":"Y"}
		);
	}

	function tchEdit(userNo) {
		document.location = "/mng/tch/info/editPop?userNo="+userNo;
	}


</script>
</mhtml:frm_body>
</mhtml:mng_html>