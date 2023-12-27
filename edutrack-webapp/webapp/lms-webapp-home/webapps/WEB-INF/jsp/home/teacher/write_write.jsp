<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}" />
<c:set var="tchWritingsVO" value="${vo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>

<mhtml:home_body>
		<mhtml:home_location />
		<form id="tchWritingsForm" name="tchWritingsForm" onsubmit="return false" >
		<input type="hidden" name="idCheck" id="idCheck" value="N" />
		<input type="hidden" name="userNo"   id="userNo" value="${vo.userNo}" />
		<input type="hidden" name="lecWritingsSn"   id="lecWritingsSn" value="${vo.lecWritingsSn}" />

		<ul class="nav nav-tabs" id="tabMenu">
			<li><a href="<c:url value="/home/user/editFormUser"/>"><spring:message code="user.title.userinfo.user.info"/></a></li>
			<li class="active"><a href="#none"><spring:message code="user.title.userinfo.teacher.into"/></a></li>
		</ul>

		<div style="width:100%;">
			<div style="float:left;">
				<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="teacher.title.teacherinfo.writing"/></h5>
			</div>
		</div>

		<table class="table table-bordered wordbreak" style="margin-top:30px;">
			<caption class="sr-only"><spring:message code="teacher.title.teacherinfo.teacher.manage"/></caption>
			<colgroup>
				<col width="18%"/>
				<col width="32%"/>
				<col width="18%"/>
				<col width="32%"/>
			</colgroup>
			<tr height="35">
				<th class="top" scope="row"><span style="color:red;">* </span><label for="pblsDt"><spring:message code="teacher.title.teacherinfo.writing.pbls.dt"/></label></th>
				<td colspan="3">
					<div class="input-group" style="float:left;width:128px;">
						<input type="text" dispName="<spring:message code="teacher.title.teacherinfo.writing.pbls.dt"/>" maxlength="50" isNull="N" lenCheck="50" name="pblsDt" value="${vo.pblsDt}" id="pblsDt" class="inputDate form-control input-sm" />
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('pblsDt')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="pblsDt"  />
				</td>
			</tr>
			<tr height="35">
				<th class="top" scope="row"><span style="color:red;">* </span><label for="pblsDeptNm"><spring:message code="teacher.title.teacherinfo.writing.pbls.deptnm"/></label></th>
				<td class="top">
					<input type="text"  maxlength="50" lenCheck="50" name="pblsDeptNm" value="${vo.pblsDeptNm}" isNull="N" class="form-control input-sm" id="pblsDeptNm" dispName="<spring:message code="teacher.title.teacherinfo.writing.pbls.deptnm"/>" style="width:200px;"/>
				</td>
				<th class="top" scope="row"><span style="color:red;">* </span><label for="bookNm"><spring:message code="teacher.title.teacherinfo.writing.booknm"/></label></th>
				<td class="top">
					<input type="text"  maxlength="50" lenCheck="50" name="bookNm" value="${vo.bookNm}" isNull="N" class="form-control input-sm" id="bookNm" dispName="<spring:message code="teacher.title.teacherinfo.writing.booknm"/>" style="width:200px;"/>
				</td>
			</tr>
			<tr >
			<th scope="row"><label for="cts"><spring:message code="teacher.title.teacherinfo.writing.cts"/></label></th>
			<td colspan="3">
				<textarea name="cts" lenCheck="1000" dispName="<spring:message code="teacher.title.teacherinfo.writing.cts"/>" style="height:80px" id="cts" class="form-control input-sm">${vo.cts}</textarea>
			</td>
			<tr height="35">
				<th class="top" scope="row"><span style="color:red;">* </span><label for="pblsCnt"><spring:message code="teacher.title.teacherinfo.writing.pbls.cnt"/></label></th>
				<td class="top">
					<input type="text"  maxlength="9" lenCheck="9" name="pblsCnt" value="${vo.pblsCnt}" isNull="N" class="form-control input-sm" id="pblsCnt" dispName="<spring:message code="teacher.title.teacherinfo.writing.pbls.cnt"/>" style="width:200px;" onblur="gf_Convert2ByteChar2(this)"/>
				</td>
				<th class="top" scope="row"><span style="color:red;">* </span><label for="pblsrPhoneno"><spring:message code="teacher.title.teacherinfo.writing.pbls.phoneno"/></label></th>
				<td class="top">
					<input type="text"  maxlength="14" lenCheck="14" name="pblsrPhoneno" value="${vo.pblsrPhoneno}"  isNull="N" class="form-control input-sm" id="pblsrPhoneno" dispName="<spring:message code="teacher.title.teacherinfo.writing.pbls.phoneno"/>" style="width:200px;" onblur="gf_Convert2ByteChar2(this)"/>
				</td>
			</tr>
		</tr>
		</table>

		<div class="text-right">
			<c:if test="${gubun eq 'A'}">
			<a href="javascript:addWrite()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			</c:if>
			<c:if test="${gubun eq 'E'}">
			<a href="javascript:editWrite()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			<a href="javascript:delWrite()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
			</c:if>

			<a href="/home/tch/info/editForm" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
		</div>
		<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#tchWritingsForm').attr("action" ,"/home/tch/writing/" + cmd);
		$('#tchWritingsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location = "/home/tch/info/editForm";
		} else {
			// 비정상 처리
		}
	}

	function addWrite(){
		if(validate(document.tchWritingsForm) ==false) return ;
		process("addWrite");	// cmd
	}

	function editWrite(){
		if(validate(document.tchWritingsForm) ==false) return ;
		process("editWrite");	// cmd
	}

	function delWrite(){
		process("delWrite");	// cmd
	}

	/*
	*	일본어 전각문자 -> 반각문자 변환 후 전화번호형식 체크
	*/
	function gf_Convert2ByteChar2(param) {
		var x_char = param.value;
		var x_2byteChar = new String;
		var len = x_char.length;
		for (var i = 0; i < len; i++) {
			var c = x_char.charCodeAt(i);
			if (c >= 65281 && c <= 65374 && c != 65340) {
				x_2byteChar += String.fromCharCode(c - 65248);
			} else if (c == 8217) {
				x_2byteChar += String.fromCharCode(39);
			} else if (c == 8221) {
				x_2byteChar += String.fromCharCode(34);
			} else if (c == 12288) {
				x_2byteChar += String.fromCharCode(32);
			} else if (c == 65507) {
				x_2byteChar += String.fromCharCode(126);
			} else if (c == 65509) {
				x_2byteChar += String.fromCharCode(92);
			} else {
				x_2byteChar += x_char.charAt(i);
			}
		}
		param.value = x_2byteChar;

	}
</script>
</mhtml:home_body>
</mhtml:home_html>