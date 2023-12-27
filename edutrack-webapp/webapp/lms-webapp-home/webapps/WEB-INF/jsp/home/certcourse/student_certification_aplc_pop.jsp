<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script defer src="/tpl/002/js/common.js"></script>	
				<form id="certificationForm" name="certificationForm" onsubmit="return false">
					<input type="hidden" name="stdNo" value="${vo.stdNo }"/>
					<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }"/>
					<input type="hidden" name="certSts" value="I"/>
					<input type="submit" value="submit" style="display:none" />
						<div class="table txt_small">
							<table>
								<caption>자격증 신청</caption>
								<colgroup>
									<col class="w35">                                       
									<col>                                        
								</colgroup>                                                                
								<tbody>
									<tr>
										<th>수강과정명</th>
										<td class="txt_left">
											${vo.crsCreNm}
										</td>                                        
									</tr>
								</tbody>
							</table>
						</div>
						<div class="modal_btns">
							<button type="button" class="btn" onclick="javascript:certAplcModalBoxClose();">취소</button>
							<button type="button" class="btn type2" id="aplcButtion" onclick="javascript:addCert();">신청</button>
						</div>
				</form>
				
<script type="text/javascript">
	$(document).ready(function() {
		
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.certificationForm)) return;
		$('#certificationForm').attr("action","/home/certcourse/" + cmd);
		$('#certificationForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listCreateCourse(1);
			parent.certAplcModalBoxClose();
		} else {
			
		}
	}

	/**
	 * 자격증 신청
	 */
	function addCert() {
		process("addCert");	// cmd
	}


</script>
  