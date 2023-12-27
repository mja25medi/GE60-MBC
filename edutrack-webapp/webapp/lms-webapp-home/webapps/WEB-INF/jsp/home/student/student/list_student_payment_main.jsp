<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<div class="board_info m_column_row">
	    <h3 class="subMenu_title">결제내역</h3>
	    <div class="page_btn flex-none">
	        <button type="button" class="btn" onclick="javascript:cancelPayInfo()">환불안내</button>
	    </div>
	</div>

<div class="res_tbl_wrap" style="clear: both;">
	<table>
	    <caption>결제내역 목록</caption>
	    <thead>
	        <tr>
	            <th scope="col" width="14%">회차</th>
	            <th scope="col">과정명</th>
	            <th scope="col" width="10%">과정가격</th>
	            <th scope="col" width="10%">결제상태</th>
	            <th scope="col" width="8%">결제방법</th>
	            <th scope="col" width="10%">총금액</th>
	            <th scope="col" width="10%">신청일</th>
	            <th scope="col" width="12%">기타</th>
	        </tr>
	    </thead>
	    <tbody>
		    <c:if test="${not empty stuPayList}">
				<c:forEach items="${stuPayList}" var="item">
				   <tr>
				       <td scope="row" data-label="회차">${item.creTerm }</td>
				       <td scope="row" class="title" data-label="과정명">${item.crsCreNm }</td>
				       <td data-id="${item.paymNo }"><fmt:formatNumber value="${item.stdTotPrice }" pattern="#,#00" />원</td><%-- paymPrice 는 총 금액, stdTotPrice는 E,N,S,C,F 상태의 수강생의 stdPrice의 합 --%>
				       <td data-label="결제상태" data-id="${item.paymNo }">
					   <%-- <c:choose>
						<c:when test="${item.enrlSts eq 'N' }">
							결제 취소<!-- 수강 취소  -->
						</c:when>
						<c:when test="${item.paymStsCd eq 'DF'}">결제완료</c:when>
						<c:when test="${item.paymStsCd eq 'DI' }">입금대기
							<c:if test="${item.enrlSts eq 'E' }"><!-- 수강상태가 대기인 경우에만 수강취소 가능  -->
								<br> <!-- 결제수단에 따른 상태 : 무   -->
								<a href="javascript:cancelEnrollStudent('${item.stdNo }', '${item.crsCreCd }')" class="btn type2 btn_search">수강취소</a>
							</c:if>
						</c:when>
						<c:when test="${item.paymStsCd eq 'DC' }">입금취소</c:when>
						<c:when test="${item.paymStsCd eq 'RF' }">환불완료</c:when>
						<c:otherwise>-</c:otherwise>
						</c:choose>		 --%>	
						<c:choose>
							<c:when test="${item.repayStsCd eq 'REFUND001'}">
								환불요청
							</c:when>
							<c:when test="${item.repayStsCd eq 'REFUND003'}">
								환불완료
							</c:when>
							<c:when test="${item.enrlSts eq 'N' }">
								결제 취소
							</c:when>
							<c:when test="${item.enrlSts eq 'E' }">
								입금 대기
								<div class="mt8">
									<a href="javascript:cancelEnrollStudent('${item.stdNo }', '${item.crsCreCd }')" class="btn solid dark">수강취소</a>
								</div>
							</c:when>
							<c:when test="${item.enrlSts eq 'S' || item.enrlSts eq 'C' || item.enrlSts eq 'F' }">
								결제 완료
								<c:if test="${item.enrlSts ne 'C' }">
								<!-- <div class="mt8"><a href="javascript:cancelPayInfo()" class="btn solid dark">결제취소안내</a></div> -->
									<c:if test="${item.bookmarkCnt eq 0 }">
									<c:if test="${not empty item.tid }">
										<c:choose>
											<c:when test="${ item.payMethod eq 'Card' || item.payMethod eq 'VCard' || item.payMethod eq 'CARD' || item.payMethod eq 'DirectBank'  || item.payMethod eq 'BANK'}">
												<div class="mt8">
													<c:choose>
														<c:when test="${item.stuCnt > 1 }">
															<a href="javascript:iniParCancelPay('${item.stdNo }', '${item.crsCreCd }')" class="btn solid dark">결제취소</a><%-- 부분--%>
														</c:when>
														<c:otherwise>
															<a href="javascript:iniCancelPay('${item.stdNo }', '${item.crsCreCd }')" class="btn solid dark">결제취소</a><%-- 전체--%>
														</c:otherwise>
													</c:choose>
												</div>     
											</c:when>
										<c:when test="${item.payMethod eq 'VBank' || item.payMethod eq 'VBANK' }">
										<a href="javascript:addFormRefund('${item.stdNo }')" class="btn solid dark">환불신청</a>
										</c:when>
										</c:choose>
									</c:if>
									</c:if>
							    </c:if>
					           	<c:if test="${item.bookmarkCnt > 0 }">
					           		<div class="mt8">
					           			<a href="javascript:addFormRefund('${item.stdNo }')" class="btn solid dark">환불신청</a>
					           		</div>
					           	</c:if>
							</c:when>
						<c:otherwise>-</c:otherwise>
						</c:choose>
						<%-- <a href="javascript:addFormRefund('${item.stdNo }')" class="btn solid dark">환불신청</a> --%>
						<%-- <div class="mt8"><a href="javascript:addFormRefund('${item.stdNo }')" class="btn solid dark">환불신청</a></div> --%>
				       </td>
				       <td data-label="결제방법" data-id="${item.paymNo }">${item.paymMthdNm }</td>
				       <td data-label="총금액" data-id="${item.paymNo }"><fmt:formatNumber value="${item.stdTotPrice }" pattern="#,#00" />원</td><%-- paymPrice 는 총 금액, stdTotPrice는 E,N,S,C,F 상태의 수강생의 stdPrice의 합 --%>
				       <td data-label="신청일" data-id="${item.paymNo }"><meditag:dateformat type="1" property="${item.regDttm }"/></td>
				       <td data-label="기타" data-id="${item.paymNo }">
					       <div class="txt-left sm">
								<c:if test="${item.paymMthdCd eq 'PAYM003' && item.paymStsCd eq 'DI' && item.enrlSts ne 'N' }"><%-- 무통장입금 가상계좌 --%>
									가상계좌번호 : ${item.vactNum }<br>
									입금은행명 : ${item.vactBankName  }<br>
									예금주명 : ${item.vactName      }<br>
									송금자명 : ${item.vactInputName }<br>
									입금기한 : <meditag:dateformat type="8" delimeter="." property="${item.vactDate}${item.vactTime}"/>
								</c:if>
							</div>
				        </td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty stuPayList}">
				<td colspan="8">결제내역이 없습니다.</td>
			</c:if>
		</tbody>
	</table>
</div>
	
	<form name="paymentForm" id="paymentForm" action="/home/student/removeStudent" method="post" onsubmit="return false">
		<input type="hidden" name="stdNo" id="stdNo"/>
		<input type="hidden" name="crsCreCd" id="crsCreCd"/>
	</form>
	
	<script>
	var cancelInfoModalBox = null;
	var refundModalBox = null;
	
	$(document).ready(function() {
		cancelInfoModalBox = new $M.ModalDialog({
			"modalid" : "cancelInfoModalBox1",
			"nomargin" : true
		});
		refundModalBox = new $M.ModalDialog({
			"modalid" : "cancelInfoModalBox1",
			"nomargin" : true
		});
		
		for (var i = 0; i < $(".subMenu_title").length; i++) {
			$(".subMenu_title").eq(0).hide();
		}
		
		//data-id 로 병합
		$.fn.rowspan = function(colIdx, isStats) {
		  return this.each(function(){
		    var that;
		    $('tr', this).each(function(row) {
		      $('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
		        if ($(this).attr('data-id') == $(that).attr('data-id')
		                && (!isStats
		                        || isStats && $(this).prev().attr('data-id') == $(that).prev().attr('data-id')
		                )
		        ) {
		          rowspan = $(that).attr("rowspan") || 1;
		          rowspan = Number(rowspan)+1;

		          $(that).attr("rowspan",rowspan);
		          $(this).hide();
		        } else {
		          that = this;
		        }
		        that = (that == null) ? this : that;
		      });
		    });
		  });
		};
		
		
		$("#studentTable").rowspan(0);
		//$("#studentTable").rowspan(1);//과정명, 가격 은 구분
		//$("#studentTable").rowspan(2);
		//$("#studentTable").rowspan(3);<!-- 결제 상태는 수강상태와 맞물려 개별 건으로 표시 --> 
		$("#studentTable").rowspan(4);
		$("#studentTable").rowspan(5);
		$("#studentTable").rowspan(6);
		$("#studentTable").rowspan(7);
	});
	
	function cancelEnrollStudent(stdNo, crsCreCd){
		<%-- $.ajax({
			url : '/home/student/removeStudent'
			,data : {
				'stdNo' : stdNo,
				'crsCreCd' : crsCreCd
			}
			,success : function(resultVO) {
				alert(resultVO.message);
				goListPaymentPage();
			}
			,method: "POST"
			,error : function(request,status,error) {
				alert("신청 취소에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
				goListPaymentPage();
			}
		}); --%>
		if(confirm('해당 과정을 수강취소하시겠습니까?')){
			var paymentForm = document.getElementById("paymentForm");
			$("#stdNo").val(stdNo);
			$("#crsCreCd").val(crsCreCd);
			paymentForm.submit();	
		}
	}
	
	function goListPaymentPage(){
		//location.href = "/home/main/goMenuPage?mcd=MC00000025";
		location.href = "/home/student/listPaymentMain?mcd=MC00000025";
	}
	
	function cancelInfoModalBoxClose() {
		cancelInfoModalBox.clear();
		cancelInfoModalBox.close();
	}
	function refundModalBoxClose() {
		refundModalBox.clear();
		refundModalBox.close();
	}
	
	function refundInfoPop(){
		location.href="/home/main/goMenuPage?mcd=MC00000040";
		/* modalBox.clear();
		modalBox.addContents('/home/org/page/viewPagePop2?pageCd=PAGE053');
		modalBox.setTitle("환불 안내");
		modalBox.show(); */
	}
	
	function cancelPayInfo(){
		cancelInfoModalBox.clear();
		var addContent  = "<iframe id='viewPagePop2' name='viewPagePop2' style='width:100%; height:410px'"
			+ "frameborder='0' src='<c:url value="/home/org/page/viewPagePop?pageCd=PAGE053"/>"
			+ "'/>";
		cancelInfoModalBox.addContents(addContent);
		cancelInfoModalBox.setTitle("환불 안내");
		cancelInfoModalBox.show();
	}
	
	function iniCancelPay(stdNo, crsCreCd){
		if(confirm('해당 과정을 결제취소하시겠습니까?')){
			var paymentForm = document.getElementById("paymentForm");
			$("#stdNo").val(stdNo);
			$("#crsCreCd").val(crsCreCd);
			paymentForm.action = "/home/student/inicisRefundMain";
			paymentForm.submit();	
		}
	}
	
	function iniParCancelPay(stdNo, crsCreCd){
		if(confirm('해당 과정을 결제취소하시겠습니까?')){
			var paymentForm = document.getElementById("paymentForm");
			$("#stdNo").val(stdNo);
			$("#crsCreCd").val(crsCreCd);
			paymentForm.action = "/home/student/inicisParRefundMain";
			paymentForm.submit();
		}
	}
	
	function addFormRefund(stdNo){
		cancelInfoModalBox.clear();
		var addContent  = "<iframe id='viewPagePop2' name='viewPagePop2' style='width:100%; height:410px'"
			+ "frameborder='0' src='/home/student/addRefundPop2?stdNo="+stdNo+"'/>";
		cancelInfoModalBox.addContents(addContent);
		cancelInfoModalBox.setTitle("환불 안내");
		cancelInfoModalBox.show();
	}
	
	</script>
</body>
