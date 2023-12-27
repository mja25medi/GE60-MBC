<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">

</mhtml:head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<br/>
<ul style="width:96%;margin:0 auto;">
	<li style="float:left;width:240px;"><span style="color:red">Err-1200</span> : 사용자 이름 입력 오류</li>
	<li style="float:left;width:240px;"><span style="color:red">Err-1300</span> : 비밀번호 입력 오류</li>
	<li style="float:left;width:240px;"><span style="color:red">Err-2100</span> : 이미 수강 신청이 되어 있습니다.</li>
	<li style="float:left;width:240px;"><span style="color:red">Err-2200</span> : 사용중인 사용자 아이디 입니다.</li>
</ul>
<br/>
<form id="studentForm" name="studentForm" onsubmit="return false" action="/StudentStudentAdmin.do">
	<input type="hidden" name="cmd" value="addStudentExcel"/>
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<table class="table_list" width="96%" align="center">
		<colgroup>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
			<col style="width:50px;"/>
		</colgroup>
		<thead>
			<tr>
				<th>에러</th>
				<th>아이디</th>
				<th>이름</th>
				<th>비밀번호</th>
				<th>소속</th>
				<th>생년월일</th>
				<th>성별</th>
				<th>이메일</th>
				<th>핸드폰</th>
				<th>집전화번호</th>
				<th>주소구분</th>
				<th>우편번호</th>
				<th>주소1</th>
				<th>주소2</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${studentExcelList}" varStatus="status">
			<c:if test="${empty item.errorCode}">
			<tr>
				<td> -
					<input type="hidden" name="errorCode" value="${item.errorCode}"/>
					<input type="hidden" name="userSts" value="${item.userSts}"/>
				</td>
				<td>
					${item.userId}
					<input type="hidden" name="userId" value="${item.userId}"/>
				</td>
				<td>
					${item.userNm}
					<input type="hidden" name="userNm" value="${item.userNm}"/>
				</td>
				<td>
					${item.userPass}
					<input type="hidden" name="userPass" value="${item.userPass}"/>
				</td>
				<td>
					${item.compNm}
					<input type="hidden" name="compNm" value="${item.compNm}"/>
				</td>
				<td>
					${item.birth}
					<input type="hidden" name="birth" value="${item.birth}"/>
				</td>
				<td>
					${item.sexCd}
					<input type="hidden" name="sexCd" value="${item.sexCd}"/>
				</td>
				<td>
					${item.email}
					<input type="hidden" name="email" value="${item.email}"/>
				</td>
				<td>
					${item.mobileNo}
					<input type="hidden" name="mobileNo" value="${item.mobileNo}"/>
				</td>
				<td>
					${item.homePhoneno}
					<input type="hidden" name="homePhoneno" value="${item.homePhoneno}"/>
				</td>
				<td>
					${item.homeAddrDivCd}
					<input type="hidden" name="homeAddrDivCd" value="${item.homeAddrDivCd}"/>
				</td>
				<td>
					${item.homePostNo}
					<input type="hidden" name="homePostNo" value="${item.homePostNo}"/>
				</td>
				<td>
					${item.homeAddr1}
					<input type="hidden" name="homeAddr1" value="${item.homeAddr1}"/>
				</td>
				<td>
					${item.homeAddr2}
					<input type="hidden" name="homeAddr2" value="${item.homeAddr2}"/>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty item.errorCode}">
			<tr>
				<td>
					<span style="color:red">${item.errorCode}</span>
					<input type="hidden" name="errorCode" value="${item.errorCode}"/>
					<input type="hidden" name="userSts" value="${item.userSts}"/>
				</td>
				<td>
					<input type="text" name="userId" value="${item.userId}" class="txt" style="width:50px;"/>
				</td>
				<td>
					<input type="text" name="userNm" value="${item.userNm}" class="txt" style="width:50px;"/>
				</td>
				<td>
					<input type="text" name="userPass" value="${item.userPass}" class="txt" style="width:50px;"/>
				</td>
				<td>
					${item.compNm}
					<input type="hidden" name="compNm" value="${item.compNm}"/>
				</td>
				<td>
					${item.birth}
					<input type="hidden" name="birth" value="${item.birth}"/>
				</td>
				<td>
					${item.sexCd}
					<input type="hidden" name="sexCd" value="${item.sexCd}"/>
				</td>
				<td>
					${item.email}
					<input type="hidden" name="email" value="${item.email}"/>
				</td>
				<td>
					${item.mobileNo}
					<input type="hidden" name="mobileNo" value="${item.mobileNo}"/>
				</td>
				<td>
					${item.homePhoneno}
					<input type="hidden" name="homePhoneno" value="${item.homePhoneno}"/>
				</td>
				<td>
					${item.homeAddrDivCd}
					<input type="hidden" name="homeAddrDivCd" value="${item.homeAddrDivCd}"/>
				</td>
				<td>
					${item.homePostNo}
					<input type="hidden" name="homePostNo" value="${item.homePostNo}"/>
				</td>
				<td>
					${item.homeAddr1}
					<input type="hidden" name="homeAddr1" value="${item.homeAddr1}"/>
				</td>
				<td>
					${item.homeAddr2}
					<input type="hidden" name="homeAddr2" value="${item.homeAddr2}"/>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		<c:if test="${empty studentExcelList}">
			<tr>
				<td colspan="14">* 입력된 데이터가 없습니다.</td>
			</tr>
		</c:if>
		</tbody>
	</table>
</form>
<meditag:buttonwrapper style="padding: 6px; width: 96%">
	<meditag:button value="등록" title=" 등록" func="goInsert();" />
	<meditag:button value="닫기" title=" 닫기" func="self.close();" />
</meditag:buttonwrapper>


<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		window.resizeTo(1100,700);
	});

	function goInsert() {
		$('#studentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			opener.listStudent(1);
			self.close();
		} else {
			// 비정상 처리
		}
	}
</script>
</mhtml:frm_body>
</mhtml:html>