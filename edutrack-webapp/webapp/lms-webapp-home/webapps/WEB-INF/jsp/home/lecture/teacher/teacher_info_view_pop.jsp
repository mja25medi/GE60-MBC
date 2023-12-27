<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="usrUserInfoVO" value="${vo}"/>
<c:set var="tchInfoVO" value="${tchInfoVO}"/>
<c:url var="home_url" value="/index_home.jsp" />
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>
<body style="background-color:#fff;" class="frmbody">
				<form id="userInfoForm" name="userInfoForm" onsubmit="return false" >
				<input type="hidden" name="idCheck" id="idCheck" value="N" />
				<input type="hidden" name="userNo" value="${vo.userNo}"/>
				<input type="hidden" name="tchInfoVO.userNo" value="${tchInfoVO.userNo}"/>

				<div class="row">
					<div class="col-lg-12">
						<table class="table table-bordered wordbreak" style="margin-top:30px;">
							<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.teacher.manage"/></caption>
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
									${tchInfoVO.crer}
								</td>
							</tr>
							<tr >
								<th scope="row"><label for="major"><spring:message code="teacher.title.teacherinfo.major"/></label></th>
								<td colspan="3" class="wordbreak">
									${tchInfoVO.major}
								</td>
							</tr>
						</table>

					</div>
				</div>

				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div class="text-right">
							<%-- <a href="javascript:addTeacher()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a> --%>
							<%-- <a class="btn btn-default btn-sm" href="<c:url value="/MainHome.do"/>?cmd=goMenuPage&amp;mcd=MC0000000000"><spring:message code="button.cancel"/></a> --%>
							<a href="#none" onclick="parent.modalBoxClose();" class="btn btn-sm btn-default"><spring:message code="button.close"/></a>
						</div>
					</div>
				</div>
				</form>

				<br/>
				<form name="Search" id="Search" onsubmit="return false">
				<div class="row">
				<div class="col-lg-12">
					<div style="width:100%;">
						<div style="float:left;">
							<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.school"/></h5>
						</div>
					</div>

					<div id="schoolList" style="width:100%;float:left;padding-top: 5px;">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.school"/></caption>
							<colgroup>
								<col style="width:50px"/>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
									<th scope="col"><spring:message code="common.title.delete"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="7"><spring:message code="teacher.message.school.nodata"/></td>
								</tr>
							</tbody>
						</table>
					</div>

					<div style="width:100%;">
						<div style="float:left;">
							<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.writing"/></h5>
						</div>
					</div>
					<div id="writeList" style="width:100%;float:left;padding-top: 5px;">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.writing"/></caption>
							<colgroup>
								<col style="width:50px"/>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
									<th scope="col"><spring:message code="common.title.delete"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="7"><spring:message code="teacher.message.writing.nodata"/></td>
								</tr>
							</tbody>
						</table>
					</div>
					<input type="submit" value="submit" style="display:none" />
				</div><!-- class="col-lg-12" -->
				</div><!-- class="row" -->
				</form>
				<br/>

<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		listSchool();
		listWriting();
		$('.inputDate').inputDate('.');
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		$("body").css("overflow-x","hidden");
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function listSchool() {
		$('#schoolList').load(
			cUrl("/lec/tch/schs/listSchool"),
			{  "userNo": "${tchInfoVO.userNo}", "isView":"Y" }
		);
	}

	function listWriting() {
		$('#writeList').load(
			cUrl("/lec/tch/writing/listWrite"),
			{ "userNo": "${tchInfoVO.userNo}", "isView":"Y"}
		);
	}

	function process(cmd) {
		$('#userInfoForm').attr("action","/home/tch/info"+cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}


</script>
</body>
</mhtml:home_html>
