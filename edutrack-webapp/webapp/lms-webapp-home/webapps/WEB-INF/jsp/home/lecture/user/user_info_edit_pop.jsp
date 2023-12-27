<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:css href="css/home/pop.css"/>
</mhtml:home_head>
<body>
	<div class="wrap" style="width:514px;">
		<h1>회원정보 수정</h1>
		<div class="contents">

			<form id="userInfoForm" name="userInfoForm" onsubmit="return false" >
			<input type="hidden" name="userNo" />
			<input type="hidden" name="wwwAuthGrpCd" />
			<input type="hidden" name="adminAuthGrpCd" />
			<input type="hidden" name="mngAuthGrpCd" />
			<input type="hidden" name="tempYn" value="N" />
			<table class="board_b">
				<caption>회원정보 입력</caption>
				<colgroup>
					<col style="width:20%;" />
					<col style="width:80%;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="userNm">* 이름</label></th>
						<td><input type="text" style="width:80px;" dispName="이름" isNull="N" lenCheck="14" name="userNm" id="userNm" class="text" title="이름 입력"/></td>
					</tr>
					<tr>
						<th scope="row">* 생년월일</th>
						<td>
							<input type="hidden" name="birth" id="birth"/>
							<c:if test="${fn:length(usrUserInfoVO.birth) == 8 }">
								<c:set var="bYear" value="${fn:substring(usrUserInfoVO.birth,0,4)}"/>
								<c:set var="bMonth" value="${fn:substring(usrUserInfoVO.birth,4,6)}"/>
								<c:set var="bDay" value="${fn:substring(usrUserInfoVO.birth,6,8)}"/>
							</c:if>
							<select id="birthYear" title="년 입력">
								<c:forEach var="year" items="${yearList}">
								<option value="${year}" <c:if test="${year eq bYear}">selected</c:if>>${year}</option>
								</c:forEach>
							</select> 년
							<select id="birthMonth" title="월 입력" onchange="checkBirthDay()">
								<option value="01" <c:if test="${bMonth eq '01'}">selected</c:if>>1</option>
								<option value="02" <c:if test="${bMonth eq '02'}">selected</c:if>>2</option>
								<option value="03" <c:if test="${bMonth eq '03'}">selected</c:if>>3</option>
								<option value="04" <c:if test="${bMonth eq '04'}">selected</c:if>>4</option>
								<option value="05" <c:if test="${bMonth eq '05'}">selected</c:if>>5</option>
								<option value="06" <c:if test="${bMonth eq '06'}">selected</c:if>>6</option>
								<option value="07" <c:if test="${bMonth eq '07'}">selected</c:if>>7</option>
								<option value="08" <c:if test="${bMonth eq '08'}">selected</c:if>>8</option>
								<option value="09" <c:if test="${bMonth eq '09'}">selected</c:if>>9</option>
								<option value="10" <c:if test="${bMonth eq '10'}">selected</c:if>>10</option>
								<option value="11" <c:if test="${bMonth eq '11'}">selected</c:if>>11</option>
								<option value="12" <c:if test="${bMonth eq '12'}">selected</c:if>>12</option>
							</select> 월
							<select id="birthDay" title="일 입력"></select> 일
						</td>
					</tr>
					<tr>
						<th scope="row">* 성별</th>
						<td>
							<input type="radio" style="border:0" name="sexCd" value="M" id="sexCdM"/> <label for="sexCdM">남</label>,
							<input type="radio" style="border:0" name="sexCd" value="F" id="sexCdF"/> <label for="sexCdF">여</label>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="userPass">* 비밀번호</label></th>
						<td>
							<input type="password" maxlength="14" name="userPass" id="userPass" class="text" title="비밀번호 입력" value=""/>
							<p>"* 비밀번호는 4~14자의 영문과 숫자를 혼합하여 만들어야 합니다."</p>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="userPassChk">* 비밀번호 확인</label></th>
						<td><input type="password" maxlength="14" name="userPassChk" id="userPassChk" class="text" title="비밀번호 확인"/>
					</tr>
					<tr>
						<th scope="row"><label for="mobile1">* 휴대폰번호</label></th>
						<td>
							<input type="hidden" name="mobileNo" id="mobileNo" />
							<select id="mobile1">
								<c:forEach var="mobile" items="${mobileList}">
								<option value="${mobile.codeCd}" <c:if test="${mbileNoF eq mobile.codeCd}">selected</c:if>>${mobile.codeNm}</option>
								</c:forEach>
							</select> -
							<input type="text" style="width:30px;" id="mobile2" class="text inputNumber" title="휴대폰번호 입력" value="${mobileNoM}"/> -
							<input type="text" style="width:30px;" id="mobile3" class="text inputNumber" title="휴대폰번호 입력" value="${mobileNoL}"/>
						</td>
					</tr>

					<tr>
						<th scope="row"><label for="email1">* 이메일</label></th>
						<td>
							<input type="hidden" name="email" id="email"/>
							<input type="text" id="email1" class="text" title="이메일 아이디" style="width:80px;" value="${emailF}"/> @
							<input type="text" id="email2" class="text" title="이메일 주소" style="width:100px;display:none;" value="${emailL}"/>
							<select id="emailSelect" title="이메일주소" onchange="changeEmail()">
								<c:forEach var="email" items="${emailList}">
								<option value="${email.codeCd}">${email.codeNm}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<c:if test="${CLASSUSERTYPE eq 'TCH'}">
					<tr>
						<th scope="row"><label for="tchSbj">* 담당과목</label></th>
						<td><input type="text" style="width:160px;" dispName="담당과목" isNull="Y" lenCheck="80" name="tchSbj" id="tchSbj" class="text" title="담당과목 입력"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<div class="btn_center">
				<input type="image" id="btn_ok" src="${img_base}/common/btn/btn_cofirm1.gif" alt="확인" />
				<a href="" onclick="javascript:window.close();"><img src="${img_base}/common/btn/btn_cancel1.gif" alt="취소" /></a>
			</div>
			</form>
		</div>
	</div>

<script type="text/javascript">
	$(document).ready(function() {

		alert('개인 정보를 수정해 주세요.');

		$('.inputDate').inputDate('.');
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		// 확인(사용자 정보 전송)
		$('#btn_ok').bind("click keydown",function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();

				if(validate(document.userInfoForm) ==false) return ;

				if(isEmpty($("#mobile2").val()) || isEmpty($("#mobile3").val())) {
					alert("휴대폰번호를 입력하세요.");
					return false;
				}

				if(isEmpty($("#email1").val()) || isEmpty($("#email2").val())) {
					alert("이메일을 입력하세요.");
					return false;
				}

				if(isEmpty($("#userPass").val())) {
					alert("비밀번호를 입력하세요.");
					return false;
				}

				if(isEmpty($("#userPassChk").val())) {
					alert("비밀번호 확인을 입력하세요.");
					return false;
				}

				if( $('#userPass').val() != $('#userPassChk').val() ) {
					alert("비밀번호가 일치하지 않습니다.");
					return false;
				}


				//-- 생년월일, 전화번호  및 메일 값 셋팅
				$("#birth").val($("#birthYear option:selected").val()+$("#birthMonth option:selected").val()+$("#birthDay option:selected").val());
				$("#mobileNo").val($("#mobile1 option:selected").val()+"-"+$("#mobile2").val()+"-"+$("#mobile3").val());
				$("#email").val($("#email1").val()+"@"+$("#email2").val());

				process("editUser");
			}

		});
		changeEmail();
		checkBirthDay();
	});

	function changeEmail() {
		var emailCd = $("#emailSelect option:selected").val();
		if(emailCd == '99') {
			$("#email2").val("${emailL}");
			$("#email2").show();
		} else {
			$("#email2").hide();
			$("#email2").val($("#emailSelect option:selected").text());
		}
	}

	function checkBirthDay() {
		var year = $("#birthYear option:selected").val();
		var month = parseInt($("#birthMonth option:selected").val(),10);
		var length = 0;

		if(month == 4 || month == 6 || month == 9 || month == 11) length = 30;
		else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) length = 31;
		else if((year%4==0) && (year%100 !=0) || (year%400 == 0)) length = 29;
		else length = 28;

		$("#birthDay option").remove();
		for(var i=1; i <= length; i++ ) {
			if(i < 10) day = "0"+i;
			else day = i;
			if(day == '${bDay}')
				$("#birthDay").append("<option value='"+day+"' selected>"+i+"</option>");
			else
				$("#birthDay").append("<option value='"+day+"'>"+i+"</option>");
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').attr("action","/lec/user/"+cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			window.close();
		} else {
			// 비정상 처리
		}
	}
</script>
</body>
</mhtml:home_html>