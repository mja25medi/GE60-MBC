<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

	<form name="itemViewForm" id="itemViewForm" method="POST" onsubmit="return false">
		<input type="hidden" id="itemCd" name="itemCd" value="${vo.itemCd }"/>
		<input type="hidden" id="equCd" name="equCd" value="${vo.equCd }"/>
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:20%;"/>
				<col style="width:80%;"/>
			</colgroup>			
			<tr>
				<th scope="row"><spring:message code="null" text="상품명"/></th>
				<td><input class="form-control input-sm" type="text" id="itemNm" name="itemNm" value="${vo.itemNm }"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="상품설명"/></th>
				<td><textarea class="form-control input-sm" rows="3" name="itemDesc" id="itemDesc">${vo.itemDesc }</textarea></td>
			</tr>
			<tr>
				<th scope="col"><spring:message code="null" text="사용여부"/></th>
				<td>
					사용함<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y' }">checked</c:if> />
					사용안함<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N' }">checked</c:if> />
				</td>
			</tr>
		</table>
	    <div class="text-right" style="margin-top:10px;">
	    	<c:if test="${gubun eq 'A' }">
				<button class="btn btn-primary btn-sm" onclick="addItem();" ><spring:message code="button.add"/></button>
			</c:if>
	    	<c:if test="${gubun eq 'E' }">
				<button class="btn btn-primary btn-sm" onclick="editItem();" >수정</button>
				<button class="btn btn-warning btn-sm" onclick="delItem();" >삭제</button>
			</c:if>			
	        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	    </div>
	</form>
	<script type="text/javascript">
	var atchFiles = "";	// 첨부파일 변수	
	$(document).ready(function() {

	});
		function addItem(){
			if(chkIn()) {
				$("#itemViewForm").attr("action", "/mng/org/equ/addItem")
				$("#itemViewForm").ajaxSubmit(processCallback);
			}
		}
		
		function editItem(){
			if(chkIn()) {
				$("#itemViewForm").attr("action", "/mng/org/equ/editItem")
				$("#itemViewForm").ajaxSubmit(processCallback);
			}
		}
		
		function delItem(){
			if(confirm("상품 삭제 시 예약에 지정된 해당 상품 내역도 함께 삭제 됩니다.\n\n정말 삭제 하시겠습니까?")) {
				$("#itemViewForm").attr("action", "/mng/org/equ/deleteItem")
				$("#itemViewForm").ajaxSubmit(processCallback);				
			}
		}
		
		/**
		 * 처리 결과 표시 콜백
		 */
		function processCallback(data) {
			if(data.result == 1) {
				alert(data.message);
				parent.listItem(1);
				parent.modalBoxClose();
			} else {
				alert(data.message);
			}
		}
		/**
		 * 시설 입력 정보 확인
		 */
		function chkIn() {
			var itemNm = $("#itemNm").val(); //시설명
			var itemDesc = $("#itemDesc").val(); //용도
			
			var useYn = $("input[name=useYn]:checked").val();
			
			if(itemNm==null || itemNm=="") {
				$("#itemNm").focus();
				alert("상품명을 입력해 주세요.");
				return false;
			}
			
			if(itemDesc==null || itemDesc=="") {
				$("#itemDesc").focus();
				alert("상품 설명을 입력해 주세요.");
				return false;
			}
		
			if(useYn==null || useYn=="") {
				$("#useYn").focus();
				alert("사용여부를 체크해 주세요.");
				return false;
			}

			return true;
		}
		
		/**
		 * uploader 클릭
		 */
		function uploderclick(str) {
			$("#"+str).click();

		}
	</script>
		