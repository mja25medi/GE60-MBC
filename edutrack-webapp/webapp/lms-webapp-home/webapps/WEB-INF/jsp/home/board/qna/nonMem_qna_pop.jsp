<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<form id="nonMemQnaForm" name="nonMemQnaForm" onsubmit="return false" >
	    <div class="tstyle mb30">
	        <ul class="dbody">                       
	            <li>
	                <div class="row">
	                    <label for="regNm" class="form-label col-sm-2">이름</label>
	                    <div class="col-sm-10">
	                        <div class="form-row">
	                            <input class="form-control w50" type="text" name="regNm" id="regNm" required value="">
	                        </div>
	                    </div>
	                </div>
	            </li> 
	            <li>
	                <div class="row">
	                    <label for="qsTel" class="form-label col-sm-2">연락처</label>
	                    <div class="col-sm-10">
	                        <div class="form-row">
	                            <input class="form-control w100" type="text" name="qsTel" id="qsTel" required value="">
	                        </div>
	                    </div>
	                </div>
	            </li> 
	            <li>
	                <div class="row">
	                    <label for="qsCts" class="form-label col-sm-2">문의 내용</label>
	                    <div class="col-sm-10">
	                        <div class="form-row">
	                            <textarea class="form-control" rows="5" name="qsCts" id="qsCts" required></textarea>
	                        </div>
	                    </div>
	                </div>
	            </li>                          
	        </ul>
	        
	    </div>
	    
	
	    <div class="box terms txt_medium">
	        <ul class="item">
	            <li>귀하는 개인(신용)정보의 선택적인 수집∙이용, 제공에 대한 동의를 거부할 수 있습니다. 다만, 동의하지 않을 경우 관련 편의제공 안내 등 이용 목적에 따른 혜택에 제한이 있을 수 있습니다. 동의한 경우에도 귀하는 동의를 철회하거나 마케팅 목적으로 귀하에게 연락하는 것을 중지하도록 요청할 수 있습니다.</li>
	            <li class="mt10">
	               1) 목적 : 교육 참여기회 제공<br>
	               2) 항목 : 성함, 전화번호<br>
	               3) 보유기간 : 교육 수강 시 교육 종료 후 1년 이내, 교육 미수강 시 신청 후 1년
	            </li>
	            <li class="mt20">
	                <span class="custom-input">
	                    <input type="checkbox" name="agree" id="agree">
	                    <label for="agree" class="fweb">동의합니다.</label>
	                </span>  
	            </li>
	        </ul>                                 
	    </div>
	    <!-- </div> -->
	    <br>
	    <div class="modal_btns">
	        
	        <button type="button" class="btn type4" onclick="addNonMemQna()">저장</button>
	        <button type="button" class="btn type2" onclick="modalBoxClose()">취소</button>
	    </div>
	</form>
	
<script type="text/javascript">

	/**
	 * 비회원 상담 등록
	 */
	function addNonMemQna() {
		// 빈값 확인
		if($('#regNm').val().trim() == '') { // 이름
			alert('이름을 입력하세요.');
			$('#regNm').focus();
			return false;
		} else if($('#qsTel').val().trim() == '') { // 연락처
			alert('연락처를 입력하세요.');
			$('#qsTel').focus();
			return false;
		} else if($('#qsCts').val().trim() == '') { // 문의내용
			alert('문의내용을 입력하세요.');
			$('#qsCts').focus();
			return false;
		} else if($('#agree').is(':checked')==false){ // 동의여부
			alert('개인정보처리방침에 동의 해주세요.');
			$('#agree').focus();
			return false;
		} else {
			process("add");	// cmd
		}
	};

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.nonMemQnaForm)) return;

		$('#nonMemQnaForm').attr("action","/home/brd/qna/" + cmd);
		$('#nonMemQnaForm').ajaxSubmit(processCallback);
	};

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVo) {
		alert(resultVo.message);

		if(resultVo.result >= 0) {
			parent.modalBoxClose();
		}
	};
	
	function modalBoxClose(){
		parent.modalBoxClose();
	}
</script>
