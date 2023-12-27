<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<% // 서브 메뉴 체크 %>
<c:set var="orgMenuVO" value="${orgMenuVO}" scope="request" />
<c:set var="chkSubMenu" value="N" />
<c:set var="bbsInfoVO" value="${bbsInfoVO}" />

<form id="bbsAtclForm" name="bbsAtclForm">
	<!-- 검색조건 유지 -->
	<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex }">
	<input type="hidden" id="searchKey" name="searchKey" value="${vo.searchKey }"> 
	<input type="hidden" id="searchValue" name="searchValue" value="${vo.searchValue }">
	<!-- // 검색조건 유지 -->
	<input type="hidden" id="bbsCd" name="bbsCd" value="${vo.bbsCd }">
	<input type="hidden" id="atclSn" name="atclSn" value="${vo.atclSn }">
	<div class="tstyle_view">
		<ul class="head">
			<li class="write">
				<strong>비밀번호</strong>
				<span>
					<input type="text" id="password" name="password">
					<button type="button" onclick="passwordCheck();" class="btn_list">확인</button>
				</span>
			</li>
		</ul>
	</div>
</form>

 <div class="btn_area">
	<button type="button" onclick="listAtcl();" class="btn_list">목록</button>
</div>

<script type="text/javascript">
	$(document).ready(function(){
	});	
	
	function passwordCheck(){
		
		var password = $('#password').val(); 
		
		if(password==""){
			alert("비밀번호를 입력하세요.");
			return;
		}
		
		var encrypt = makeSendInfo(password);
		
		$.getJSON("<c:url value="/home/brd/bbs/checkPassword"/>",
				{
					"encryptData" : encrypt,
				  	"atclSn" :  "${vo.atclSn}"},
				  	function(result) {
						if(result.result == "1"){
							$("#bbsAtclForm").attr("action","/home/brd/bbs/viewAtclMain");
							document.bbsAtclForm.submit();
						}else{
							alert("비밀번호가 틀렸습니다.");
						}
				  	}
				);
	}
	
	/** 글 목록 화면으로 이동 */
	function listAtcl(){
		$("#atclSn").val("");
		$("#bbsAtclForm").attr("action","/home/brd/bbs/listAtclMain");
		document.bbsAtclForm.submit();
	}
</script>
