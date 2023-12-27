<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>

<style>
	input[type=checkbox] {
	  -webkit-appearance: checkbox;
	  -moz-appearance: checkbox;
	  appearance: checkbox;
	}
</style>

                    <div class="srhBiz form-row">
                        <input class="form-control inputNumber w50" maxlength="10" type="text" name="name" id="regNumInput" placeholder="사업자번호를 입력하세요"/>
                        <button type="button" class="btn gray2 ml5" onclick="searchDept()">사업자번호 검색</button>
                    </div>
                    <p class="mb5">※ 근로자 내일배움 카드 학습자는 <span class="fcRed">1234567891</span> 코드로 입력하세요.</p>
                    <div class="table txt_small">
	                    <table class="tbl tablet">
	                        <thead>
	                            <tr>
	                                <th scope="col" class="w10">선택</th>
	                                <th scope="col">기업코드</th>
	                                <th scope="col">사업자코드</th>
	                                <th scope="col">기업명</th>                                           
	                            </tr>
	                        </thead>
	                        <tbody id="deptInfo">
	                        </tbody>
	                    </table>
                    </div>
                <div class="modal_btns">
                    <button type="button" class="btn" onclick="modalBoxClose()">취소</button>
                    <button type="button" class="btn type2" onclick="selectComplete()">선택완료</button>
                </div>
                    
	<script type="text/javascript">	
	function searchDept() {
		var regNum = $("#regNumInput").val();
		if(!checkRegNum(regNum)){
			alert("유효하지 않은 사업자 등록 번호 입니다. '-'를 제외한 숫자 10자를 입력해주세요.");
			return;
		}
		$("#deptInfo").empty();
			$.getJSON( 
				cUrl("/home/user/searchDept"),
				{"regNum" : regNum },			// params
				function(data) {
					var deptList = data.returnList;
					if(deptList.length > 0){
						deptList.forEach((dept) => {
							$("#deptInfo").append(getDeptDataRow(dept));
						});
					} else {
						$("#deptInfo").append(getNoDataRow());
					};
				}
			);
		}
	
	function getDeptDataRow(dept) {
		return `<tr>
					<td class="center" data-title="선택">
						<input type="checkbox" name="checkSelect" onclick="selectDept(this)"/>
					</td>
					<td class="center" name="searchDeptCd" data-title="기업코드">`+ dept.deptCd + `</td>
					<td class="center" name="searchRegNum" data-title="사업자코드">` + dept.regNum + `</td>
					<td class="center" name="searchDeptNm" data-title="기업명">` + dept.deptNm+ `</td>
				</tr>`;
	}
	
	function getNoDataRow() {
		return `<tr>
					<td colspan="4">해당 기업이 존재하지 않습니다.</tr>
				</tr>`;
	}
	
	//사업자 등록번호 유효성 체크
	function checkRegNum(number){
		var numberMap = number.replace(/-/gi, '').split('').map(function (d){
			return parseInt(d, 10);
		});
		
		if(numberMap.length == 10){
			var keyArr = [1, 3, 7, 1, 3, 7, 1, 3, 5];
			var chk = 0;
			
			keyArr.forEach(function(d, i){
				chk += d * numberMap[i];
			});
			
			chk += parseInt((keyArr[8] * numberMap[8])/ 10, 10);
			return Math.floor(numberMap[9]) === ( (10 - (chk % 10) ) % 10);
		}
		
		return false;
	}
	
	function selectDept(element) {
	     $('input[type="checkbox"][name="checkSelect"]').prop('checked',false);
	     $(element).prop('checked',true);
	}
	
	function selectComplete() {
		var checked = $("input[name=checkSelect]:checked");
		if(checked.size() > 0) {
			var target = checked.parent();
			var deptCd = target.siblings("td[name=searchDeptCd]").text();
			var deptNm = target.siblings("td[name=searchDeptNm]").text();
			$("#deptCd").val(deptCd);
			$("#deptNm").val(deptNm);
			modalBoxClose();
		}
		else {
			alert("소속 기업을 선택해주세요.");
		}

	}
	</script>
