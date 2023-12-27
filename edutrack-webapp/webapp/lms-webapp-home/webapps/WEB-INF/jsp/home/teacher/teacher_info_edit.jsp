<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="usrUserInfoVO" value="${usrUserInfoVO}"/>
<c:set var="tchInfoVO" value="${vo}"/>
<c:url var="home_url" value="/index_home.jsp" />
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>

<mhtml:home_body>
				<mhtml:home_location />
				<form id="userInfoForm" name="userInfoForm" onsubmit="return false" >
				<input type="hidden" name="idCheck" id="idCheck" value="N" />
				<input type="hidden" name="usrUserInfoVO.userNo" value="${usrUserInfoVO.userNo}"/>
				<input type="hidden" name="userNo" value="${usrUserInfoVO.userNo}"/>

				<div class="row">
					<div class="col-lg-12">
						<ul class="nav nav-tabs" id="tabMenu">
		  					<li><a href="<c:url value="/home/user/editFormUser"/>"><spring:message code="user.title.userinfo.user.info"/></a></li>
		  					<li class="active"><a href="#none"><spring:message code="user.title.userinfo.teacher.into"/></a></li>
						</ul>
						<table class="table table-bordered wordbreak" style="margin-top:30px;">
							<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.teacher.manage"/></caption>
							<colgroup>
								<col width="18%"/>
								<col width="32%"/>
								<col width="18%"/>
								<col width="32%"/>
							</colgroup>
							<tr height="35">
								<th class="top" scope="row"><span style="color:red;">* </span><label for="tchCtgrCd"><spring:message code="teacher.title.teacherinfo.tchctgrcd"/></label></th>
								<td class="top">
									<select name="tchCtgrCd" id="tchCtgrCd" class="form-control input-sm">
										<c:forEach var="item" items="${tchCtgrCdList}" varStatus="status">
										<option value="${item.codeCd}" <c:if test="${vo.tchCtgrCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
										</c:forEach>				
									</select>
								</td>
								<th class="top" scope="row"><span style="color:red;">* </span><label for="tchDivCd"><spring:message code="teacher.title.teacherinfo.tchdivcd"/></label></th>
								<td class="top">
									<select name="tchDivCd" id="tchDivCd" class="form-control input-sm">
										<c:forEach var="item" items="${tchDivCdList}" varStatus="status">
										<option value="${item.codeCd}" <c:if test="${vo.tchDivCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
										</c:forEach>				
									</select>
								</td>
							</tr>
							<tr >
								<th scope="row"><span style="color:red;">* </span><label for="crer"><spring:message code="teacher.title.teacherinfo.crer"/></label></th>
								<td colspan="3">
									<textarea name="crer" lenCheck="1000" dispName="<spring:message code="teacher.title.teacherinfo.crer"/>" isNull="N" style="height:80px" id="crer" class="form-control input-sm">${vo.crer}</textarea>
								</td>
							</tr>
							<tr >
								<th scope="row"><span style="color:red;">* </span><label for="major"><spring:message code="teacher.title.teacherinfo.major"/></label></th>
								<td colspan="3">
									<textarea name="major" lenCheck="1000" dispName="<spring:message code="teacher.title.teacherinfo.major"/>" isNull="N" style="height:80px" id="major" class="form-control input-sm">${vo.major}</textarea>
								</td>
							</tr>
						</table>

					</div>
				</div>

				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div class="text-right">
							<a href="javascript:addTeacher()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
							<a class="btn btn-default btn-sm" href="<c:url value="/home/main/goMenuPage"/>?mcd=MC0000000000"><spring:message code="button.cancel"/></a>
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
						<div style="float:right">
							<button class="btn btn-primary btn-sm" onclick="addSchool()" ><spring:message code="button.write.teacher.school"/></button>
						</div>
					</div>

					<div id="schoolList" style="width:100%;float:left;padding-top: 5px;">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.school"/></caption>
							<colgroup>
								<col style="width:50px"/>
								<col style="width:100px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:100px"/>
								<col style="width:100px"/>
								<col style="width:50px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/> </th>
									<th scope="col"><spring:message code="teacher.title.teacherinfo.school.graddiv"/></th>
									<th scope="col"><spring:message code="teacher.title.teacherinfo.school.schlnm"/></th>
									<th scope="col"><spring:message code="teacher.title.teacherinfo.school.schlsubj"/></th>
									<th scope="col"><spring:message code="teacher.title.teacherinfo.school.locatnm"/></th>
									<th scope="col"><spring:message code="teacher.title.teacherinfo.school.regdttm"/></th>
									<th scope="col"><spring:message code="teacher.title.teacherinfo.school.entrgraddt"/></th>
									<th scope="col"><spring:message code="common.title.edit"/></th>
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
						<div style="float:right">
							<button class="btn btn-primary btn-sm" onclick="addWriter()" ><spring:message code="button.write.teacher.writing"/></button>
						</div>
					</div>
					<div id="writeList" style="width:100%;float:left;padding-top: 5px;">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.writing"/></caption>
							<colgroup>
								<col style="width:70px">
								<col style="width:100px;">
								<col style="width:auto">
								<col style="width:100px;">
								<col style="width:130px;">
								<col style="width:100px">
								<col style="width:50px">
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
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function listSchool() {
		$('#schoolList').load(
			cUrl("/home/tch/schs/listSchool"),
			{ "userNo": "${usrUserInfoVO.userNo}" }
		);
	}

	function listWriting() {
		$('#writeList').load(
			cUrl("/home/tch/writing/listWrite"),
			{ "userNo": "${usrUserInfoVO.userNo}"}
		);
	}

	function addSchool(){
		if("" == $("#tch_userNo").val()){
			alert("<spring:message code="teacher.message.teacherinfo.school.empty"/>");
			return;
		}
		document.location = "/home/tch/schs/addFormSchool";
	}

	function addWriter(){
		if("" == $("#tch_userNo").val()){
			alert("<spring:message code="teacher.message.teacherinfo.writing.empty"/>");
			return;
		}
		document.location = "/home/tch/writing/addFormWrite";
	}

	function editSchool(schsSn){
		document.location = "/home/tch/schs/editFormSchool?schsSn="+schsSn;
	}

	function editWriter(lecWritingsSn){
		document.location = "/home/tch/writing/editFormWrite?lecWritingsSn="+lecWritingsSn;
	}

	function process(cmd) {
		if(validate(document.userInfoForm) ==false) return ;
		$('#userInfoForm').attr("action" ,"/home/tch/info/" + cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location = "/home/tch/info/editForm?";
		} else {
			// 비정상 처리
		}
	}

	function addTeacher(){
		process("add");	// cmd
	}

</script>
</mhtml:home_body>
</mhtml:home_html>
