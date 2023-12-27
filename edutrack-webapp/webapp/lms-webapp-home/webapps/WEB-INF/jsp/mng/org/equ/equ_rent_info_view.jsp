<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

	<form name="rentViewForm" id="rentViewForm" onsubmit="return false" method="POST">
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:25%;"/>
				<col style="width:25%;"/>
				<col style="width:25%;"/>
				<col style="width:25%;"/>
			</colgroup>
			<tr>
				<td id="serviceCode1"><b>${rentInfo.equNm} (${rentInfo.equCd })</b></td>
				<td id="serviceCode2" colspan="2">
					<meditag:dateformat type="8" delimeter="." property="${rentInfo.rentStartDttm }" />
					~<meditag:dateformat type="8" delimeter="." property="${rentInfo.rentEndDttm }" />
				</td>
				<td id="serviceCode3"><font size="" color="red">[${rentInfo.rentSts.codeNm}]</font></td>
			</tr>
		</table>
		신청자정보
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="null" text="신청자"/></th>
				<td>${rentInfo.userNm}(${rentInfo.userId})</td>
				<th scope="row"><spring:message code="null" text="신청일"/></th>
				<td><meditag:dateformat type="8" delimeter="." property="${rentInfo.regDttm }" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="휴대폰번호"/></th>
				<td>${rentInfo.mobileNo}</td>
				<th scope="row"><spring:message code="null" text="이메일"/></th>
				<td>${rentInfo.email}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="주소"/></th>
				<td colspan="3">[${rentInfo.homePostNo}] ${rentInfo.homeAddr1 } ${rentInfo.homeAddr2 }</td>
			</tr>							
		</table>
		대여 장비 정보 <font size="" color="red">[*승인완료, 대여중, 반납완료, 미반납]</font>
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="null" text="장비명"/></th>
				<td>${rentInfo.equNm}</td>
				<th scope="row"><spring:message code="null" text="대여갯수"/></th>
				<td>${rentInfo.rentCnt}</td>				
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="대여상품"/></th>
				<td colspan="3">
					<div class="checkbox">
						<c:forEach var="item" items="${itemList}">
							<label class="mb5" style="width:24%;">
								<input type="checkbox" id="chk_${item.itemCd }" name="itemChk" value="${item.itemCd }"/>
								${item.itemNm }
							</label>
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="대여 목적"/></th>
				<td colspan="3">${rentInfo.rentDesc }</td>
			</tr>					
		</table>
		처리상태값변경
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="null" text="진행상태값"/></th>
				<td colspan="3">
					<div class="wrap">
						<div class="radio">
							<c:forEach items="${rentStsCdList}" var="item">
								<label class="mb5" style="width:24%;">
									<input type="radio" name="rentSts" value="${item.codeCd}" <c:if test="${item.codeCd eq rentInfo.rentSts}">checked</c:if>/>&nbsp;${item.codeNm}
								</label>
							</c:forEach>
						</div>
					</div>
				</td>
			</tr>			
			<tr>
				<th scope="row"><spring:message code="null" text="메   모"/></th>
				<td colspan="3">
					<input type="text" name="memo" id="memo" class="_enterBind form-control input-sm" style="text-align:left" placeholder="메모" value="${rentInfo.memo}"/>
				</td>
			</tr>
		</table>
	    <div class="text-right" style="margin-top:10px;">
			<button class="btn btn-primary btn-sm" onclick="editRent()" ><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick="delRent()" ><spring:message code="button.delete"/></button>
	        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	    </div>
	    <input type="hidden" id="rentCd" name="rentCd" value="${rentInfo.rentCd}"/>
	    <input type="hidden" id="itemCds" name="itemCds" value="" />
	</form>
	<script type="text/javascript">
	
	$(document).ready(function() {
		setItemChk();
	});
	
	function setItemChk() {
		var detailList = ${rentInfo.detailList};
		detailList.forEach(function(detail) {
			$("#chk_" + detail.itemCd).attr("checked", true);
		});
	}
	
	function getItemCdsString() {
		var str = "";
		var i = 0;
		$('input:checkbox[name="itemChk"]:checked').each(function() {
		    	if(i==0) {
		    		str = str + "" + this.value + "";
		    	} else {
		    		str = str + "," + this.value + "";
		    	}
		        i++;
		});
		return str;
	}
	
	/**
	 * 수정
	 */
	function editRent() {
		var rentSts = $("input[name=rentSts]:checked").val();
		if(validateRentSts(rentSts)) {
			$('#itemCds').val(getItemCdsString());
			$('#rentViewForm').attr("action","/mng/org/equ/editRent");
			$('#rentViewForm').ajaxSubmit(processCallback);
		}
	}
	/**
	 * 삭제
	 */
	function delRent() {
		if(confirm("예약 정보를 정말 삭제하시겠습니까?")) {
			$('#rentViewForm').attr("action","/mng/org/equ/delRent");
			$('#rentViewForm').ajaxSubmit(processCallback);
		}
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(result) {
		alert(result.message);
		if(result.result == 1) {
			parent.listRent(1);
			parent.modalBoxClose();
		}
	}
	
	function validateRentSts(rentSts) {
		switch(rentSts) {
			case "REQUEST":
			case "REQ_CANCEL":
				return chkItemSelect();
			case "RENT_CANCEL":
				return chkItemSelect() && chkMemo();
			case "APPROVED":
			case "RENT":
			case "CLOSE":
			case "OVERDUE":
				return chkRentCnt();
		}
	}
	
	function chkItemSelect() {
		var chkItems = $('input:checkbox[name="itemChk"]:checked');
		if(chkItems.length > 0) {
			alert("대여 상품 정보를 체크 해제 해주세요.");
			return false;
		}
		return true;
	}
	
	function chkMemo() {
		var memo = $("#memo").val();
		if(memo == '' || memo == null) {
			alert("대여 취소 사유 메모를 작성해 주세요.")
			$("#memo").focus();
			return false;
		}
		return true;
	}
	
	function chkRentCnt() {
		var rentCnt = ${rentInfo.rentCnt};
		var chkItems = $('input:checkbox[name="itemChk"]:checked');
		if(rentCnt != chkItems.length) {
			alert("장비 대여 갯수와 대여 상품 갯수가 일치하지 않습니다.");
			return false;
		}
		return true;
	}
	
	</script>
		