<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />

	<c:url var="action_url" value="/home/student"/>
	<form name="Cert" id="Cert" action="${action_url}" onsubmit="return false">
   	<div class="box_certify" style="width:360;height:350;text-align:center">
		<fieldset>
			<legend>본인확인 / 실명확인</legend>
			<div class="certify">
				<p class="name"><label for="name"><img src="${img_base}/member/certify_txt02.gif" width="56" height="12" alt="이름(실명)" /></label> <input type="text" id="name" name="" maxlength="" size="" class="txt" style="width:148px;" /></p>
				<p class="jumin"><label for="jumin"><img src="${img_base}/member/certify_txt01.gif" width="56" height="10" alt="주민등록번호" /></label> <input type="text" id="jumin1" name="" maxlength="" size="" class="txt" style="width:65px;" /> - <input type="text" name="" maxlength="jumin2" size="" title="주민번호 뒷자리" class="txt" style="width:65px;" /></p>
				<p class="entry"><input type="image" title="실명인증확인" src="${img_base}/common/btn/btn_entry.gif" onClick="process('readCert')"/>
			</div>
			<ul>
				<li>- 입력하신 주민등록 번호는 암호화되어 전송되며<br />개인정보보호 방침에 의해 관리됩니다.</li>
				<li class="end">- <span class="important">주민등록번호는 교육(등록)수강과 수료증 발급에 사용</span><br />되므로 <span class="important">교육대상자는 실명인증 방식을 이용</span>해주시기<br />바랍니다.</li>
			</ul>
		</fieldset>
	</div>
	</form>

<script type="text/javascript">
	$(document).ready(function() {
		//
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#Cert').attr("action", "/home/student/" + cmd);
		$('#Cert').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {

		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			//-- 정상처리 시
			parent.document.location.href = cUrl("/home/student/enrollCourse?crsCtgrCd=${studentVO.crsCtgrCd}${AMPERSAND}crsCd=${studentVO.crsCd}${AMPERSAND}crsCreCd=${studentVO.crsCreCd}");
			parent.dtlPopBox.close();
		} else {
			// 비정상 처리
		}
	}
</script>

