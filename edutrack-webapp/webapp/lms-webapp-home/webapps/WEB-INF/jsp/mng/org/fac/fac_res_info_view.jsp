<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

	<form name="facViewForm" id="facViewForm" onsubmit="return false" method="POST">
		<c:if test="${gubun eq 'E'}">
		</c:if>
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:25%;"/>
				<col style="width:25%;"/>
				<col style="width:25%;"/>
				<col style="width:25%;"/>
			</colgroup>
			<tr>
				<td id="serviceCode1">&nbsp;${vo.facNm}</td>
				<td id="serviceCode2" colspan="2">${vo.resDt}&nbsp;${vo.resStm} ~ ${vo.resEtm}</td>
				<td id="serviceCode3"><font size="" color="red">[${vo.resSts.codeNm}]</font></td>
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
				<td>${vo.userNm}(${vo.userId})</td>
				<th scope="row"><spring:message code="null" text="신청일"/></th>
				<td><meditag:dateformat type="8" delimeter="." property="${vo.regDttm }" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="휴대폰번호"/></th>
				<td>${vo.mobileNo}</td>
				<th scope="row"><spring:message code="null" text="이메일"/></th>
				<td>${vo.email}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="신청자"/></th>
				<td colspan="3">[${vo.homePostNo}] ${vo.homeAddr1 } ${vo.homeAddr2 }</td>
			</tr>								
		</table>
		행사정보
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
				<col style="width:100px;"/>
				<col style="width:30%;"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="null" text="참석인원"/></th>
				<td colspan="3">${vo.attCnt}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="행사내용"/></th>
				<td colspan="3">${vo.eventDesc}</td>
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
						<div class="contents">
							<c:forEach items="${stsCodeList}" var="item">
								<label class="mb5" style="width: 18%">
									<input type="radio" name="resSts" value="${item.codeCd}" <c:if test="${item.codeCd eq vo.resSts}">checked</c:if>/>&nbsp;${item.codeNm}
								</label>
							</c:forEach>
						</div>
					</div>
				</td>
			</tr>			
			<tr>
				<th scope="row"><spring:message code="null" text="메   모"/></th>
				<td colspan="3">
					<input type="text" name="memo" id="memo" class="_enterBind form-control input-sm" style="text-align:left" placeholder="메모" value="${vo.memo}"/>
				</td>
			</tr>
		</table>
	    <div class="text-right" style="margin-top:10px;">
	        <c:if test="${gubun eq 'A' }">
			<button class="btn btn-primary btn-sm" onclick="addCtgr()" ><spring:message code="button.add"/></button>
	        </c:if>
	        <c:if test="${gubun eq 'E' }">
				<button class="btn btn-primary btn-sm" onclick="editCtgr()" ><spring:message code="button.add"/></button>
				<button class="btn btn-warning btn-sm" onclick="delCtgr()" ><spring:message code="button.delete"/></button>
	        </c:if>
	        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	    </div>
	    <input type="hidden" id="resCd" name="resCd" value="${vo.resCd}"/>
	    <input type="hidden" id="facUseYn" name="facUseYn" value=null/>
	</form>
	<script type="text/javascript">
	
	$(document).ready(function() {
		
	});
	
	
	/**
	 * 서브밋 처리
	 */
	function process() {
		if(chkVal()) {
			$('#facViewForm').attr("action","/mng/org/fac/editRes");
			$('#facViewForm').ajaxSubmit(processCallback);
		}
		
	}
	
	function chkVal() {
		var resSts = $("input[name=resSts]:checked").val();
		if(resSts == 'RETURN') return chkMemo();
		return true;
	}
	
	/**
	 * 반려시 메모 체크
	 */
	function chkMemo() {
		var memo = $("#memo").val();
		if(memo==null || memo=="") {
			$("#memo").focus();
			alert("메모를 입력해 주세요.");
			return false;
		}
		return true;
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(result) {
		alert(result.message);
		if(result.result == 1) {
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
	
	/**
	 * 수정
	 */
	function editCtgr() {
		$('#facUseYn').val("Y");
		process();
	}
	
	/**
	 * 삭제
	 */
	function delCtgr() {
		var msg = '<spring:message code="null" text="대관신청 내역을 삭제 하시겠습니까?"/>';
		if(confirm(msg)) {
			$('#facUseYn').val("N");
			process(); // cmd
		} else {
			return;
		}
	}
	</script>
		