<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form onsubmit="return false" name="userDeptInfoForm" id="userDeptInfoForm" method="post">
	<div style="float:left;width:100%;line-height:30px;height:30px;"><span style="color:red;font-weight:bold;">* </span><spring:message code="common.message.input.required"/></div>
	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="useYn">사용여부</label></th>
			<td>
				<select name="useYn" id="useYn" class="form-control input-sm">
					<option value=""><spring:message code="course.open.title.category.useyn"/></option>
					<option value="Y" <c:if test="${vo.useYn eq 'Y'}">selected</c:if> ><spring:message code="common.title.status.useyn_y"/></option>
					<option value="N" <c:if test="${vo.useYn eq 'N'}">selected</c:if> ><spring:message code="common.title.status.useyn_n"/></option>
				</select>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="deptTypeCd">기업종류</label></th>
			<td >
				<select name="deptTypeCd" id="deptTypeCd" class="form-control input-sm" >
					<option value="">선택하세요</option>
					<c:forEach var="item" items="${deptTypeCode}" varStatus="status">
						<option value="${item.codeCd}" <c:if test="${vo.deptTypeCd eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>
				</select>
			</td>			
		</tr>
		<tr>
<%-- 			<th scope="row"><label for="deptMngNo">기업관리자</label></th>
			<td>
				<select name="deptMngNo" id="deptMngNo" class="form-control input-sm">
					<option value="">선택하세요</option>
					<c:forEach var="item" items="${userList}" varStatus="status">
						<option value="${item.userNo}" <c:if test="${vo.deptMngNo eq item.userNo}">selected</c:if> >${item.userNm}</option>
					</c:forEach>
				</select>
			</td> --%>
			<c:if test="${gubun eq 'E' }">
			<th scope="row" ><span style="color:red;">* </span><label for="deptCd">기업코드</label></th>
			<td>
				<div class="input-group" style="width:80%">
					<input type="text" name="deptCd" id="deptCd" class="form-control input-sm" value="${vo.deptCd}" readonly="readonly" />	
				</div>	    								
			</td>
			</c:if>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="deptNm">법인명</label></th>
			<td>
				<input type="text" name="deptNm" id="deptNm" class="form-control input-sm" value="${vo.deptNm}" isNull="N" lenCheck="500" maxlength="500" style="vertical-align: middle;" />
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="regNum">사업자등록번호</label></th>
			<td>
				<input type="text" name="regNum" id="regNum" class="form-control input-sm inputNumber" value="${vo.regNum}" isNull="N" maxlength="10" /> *10자리 번호를 입력해주세요 EX)0123456789
			</td>
		</tr>
<%--	<tr>
			<th scope="row"><label for="deptEngNm">법인영문명</label></th>
			<td>
				<input type="text" name="deptEngNm" id="deptEngNm" class="form-control input-sm" value="${vo.deptEngNm}" lenCheck="500" maxlength="500" />
			</td>
			<th scope="row"><label for="expStartDate"></label>유효기간</th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="expStartDate" id="expStartDate" value="${vo.expStartDate}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('expStartDate')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="expEndDate" id="expEndDate" value="${vo.expEndDate}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('expEndDate')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="expStartDate" name2="expEndDate" />
			</td>
		</tr> --%>	
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="ceoNm">대표자명</label></th>
			<td>
				<input type="text" name="ceoNm" id="ceoNm" class="form-control input-sm" value="${vo.ceoNm}" isNull="N" />
			</td>
			<th scope="row"><label for="infoMngNm">개인정보보호책임자</label></th>
			<td>
				<input type="text" name="infoMngNm" id="infoMngNm" class="form-control input-sm" value="${vo.infoMngNm}" />
			</td>			
<%-- 			<th scope="row"><label for="staffCnt">종업원수</label></th>
			<td>
				<c:if test="${vo.staffCnt eq 0}">
					<input type="number" name="staffCnt" id="staffCnt" class="form-control input-sm inputNumber" value="0" /> 
				</c:if>
				<c:if test="${vo.staffCnt > 0}">
					<input type="text" name="staffCnt" id="staffCnt" class="form-control input-sm inputNumber" value="${vo.staffCnt}" /> 
				</c:if>
			</td> --%>
		</tr>
<%--		<tr>
			<th scope="row"><label for="bsnsTypeCd">업종</label></th>
			<td>
				<select name="bsnsTypeCd" id="bsnsTypeCd" class="form-control input-sm">
					<option value="">선택하세요</option>
					<c:forEach var="item" items="${bsnsTypeCode}">
						<option value="${item.codeCd }" <c:if test="${vo.bsnsTypeCd eq item.codeCd }">selected</c:if>>${item.codeNm }</option>
					</c:forEach>
				</select>
			</td>
			<th scope="row"><label for="bsnsCndtn">업태</label></th>
			<td>
				<input type="text" name="bsnsCndtn" id="bsnsCndtn" value="${vo.bsnsCndtn}" class="form-control input-sm" />		
			</td>
		</tr>  --%>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="phoneno1">전화번호</label></th>
			<td>
				<div class="input-group" style="float:left;width:65px;">
				<input type="hidden" name="phoneno" id="phoneno" />
				<c:set var="phoneNoArray" value="${fn:split(vo.phoneno,'-') }"/>
				<select name="phoneno1" id="phoneno1" class="form-control input-sm">
					<c:forEach var="item" items="${localPhoneCdList }">
						<option value="${item.codeCd }" <c:if test="${phoneNoArray[0] eq item.codeCd }">selected</c:if>>${item.codeNm }</option>
					</c:forEach>
				</select>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input type="text" maxlength="4" name="phoneno2" id="phoneno2" value="${phoneNoArray[1] }" class="inputDate form-control input-sm inputNumber"/>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input type="text" maxlength="4" name="phoneno3" id="phoneno3" value="${phoneNoArray[2] }" class="inputDate form-control input-sm inputNumber"/>
				</div>						
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="deptEmail">이메일</label></th>
			<td>
				<input type="text" name="deptEmail" id="deptEmail" class="form-control input-sm" value="${vo.deptEmail}" isNull="N" />
			</td>			
<%--			<th scope="row"><label for="faxNo1">팩스번호</label></th>
			<td>
				<div class="input-group" style="float:left;width:65px;">
				<input type="hidden" name="faxNo" id="faxNo" />
				<c:set var="faxNoArray" value="${fn:split(vo.faxNo,'-') }"/>
				<select name="faxNo1" id="faxNo1" class="form-control input-sm">
					<c:forEach var="item" items="${localPhoneCdList }">
						<option value="${item.codeCd }" <c:if test="${faxNoArray[0] eq item.codeCd }">selected</c:if>>${item.codeNm }</option>
					</c:forEach>
				</select>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input type="text" maxlength="4" name="faxNo2" id="faxNo2" value="${faxNoArray[1] }" class="inputDate form-control input-sm inputNumber"/>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input type="text" maxlength="4" name="faxNo3" id="faxNo3" value="${faxNoArray[2] }" class="inputDate form-control input-sm inputNumber"/>
				</div>						
			</td> --%>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="deptPostNo">주소</label></th>
			<td colspan="3">
			<div class="input-group" >
				<input type="text" name="deptPostNo" id="deptPostNo" class="form-control input-sm" value="${vo.deptPostNo}" isNull="N" style="width:200px;" placeholder="우편번호"/>
					<button class="btn btn-info btn-sm" type="button" onclick="goDeptPopup();">우편검색</button>
			</div>
			<div class="input-group" >
				<input type="text" name="deptAddr1" id="deptAddr1" class="form-control input-sm" value="${vo.deptAddr1}" isNull="N" style="width:600px;" placeholder="기본주소"/>
			</div>
			<div class="input-group" >
				<input type="text" name="deptAddr2" id="deptAddr2" class="form-control input-sm" value="${vo.deptAddr2}" isNull="N" style="width:600px;" placeholder="상세주소"/>
			</div>
			</td>
		</tr>
		<tr>


		</tr>
<%--		<tr>
			<th scope="row"><label for="storeRegNo">통신판매업신고번호</label></th>
			<td>
				<input type="text" name="storeRegNo" id="storeRegNo" class="form-control input-sm" value="${vo.storeRegNo }" />
			</td>
			<th scope="row"><label for="partner">파트너</label></th>
			<td>
				<select name="partner" id="partner" class="form-control input-sm">
					<option value="">선택하세요</option>
					<option value="1" <c:if test="${vo.partner eq '1'}">selected</c:if> >파트너1</option>
					<option value="2" <c:if test="${vo.partner eq '2'}">selected</c:if> >파트너2</option>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="사업자등록증">사업자등록증</label></th>
			<input type="hidden" name="bsnsLcnsFileSn" id="bsnsLcnsFileSn" value="${vo.bsnsLcnsFileSn }"/>
			<td colspan="3">
			<div class="input-group" style="float:left; width:60px; ">
				<a href="javascript:uploderclick('bsnsLcnsloader');" class="btn btn-primary btn-sm"><spring:message code="button.select.file"/></a>
				<input type="file" name="bsnsLcnsloader" id="bsnsLcnsloader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none"/>
			</div>
				<!-- The global progress bar -->
				<div id="bsnsLcnsprogress" class="progress">
		    		<div class="progress-bar progress-bar-success"></div>
				</div>
				<!-- The container for the uploaded files -->
			<div class="input-group" style="float:left;width:800px;">
				<div id="bsnsLcnsfiles" class="files multi_inbox" style="margin-top: 0px;"></div>
			</div>
			</td>
		</tr> --%>
	</table>
	
	
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
			<a href="javascript:addUserDept()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<a href="javascript:editUserDept()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
			<a href="javascript:delUserDept('D')" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="closeComWriteArea()"><spring:message code="button.close"/></button>
	</div>
	</form>
<script type="text/javascript">
	var originOrgCd = '${vo.orgCd}';
	var bsnsLcnsFile;
	// 페이지 초기화
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		bsnsLcnsFile = new $M.JqueryFileUpload({
			"varName"			: "bsnsLcnsFile",
			"files" 			: $.parseJSON('${vo.bsnsLcnsFileJson}'),
			"uploaderId"		: "bsnsLcnsloader",
			"fileListId"		: "bsnsLcnsfiles",
			"progressId"		: "bsnsLcnsprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'USR_PHOTO',
									'organization' : "${USER_ORGCD}",
									'type'		: 'usrPhoto' }
			}
		});
		
	});

	function deptCdDupCheck(){
 		if(isEmpty($("#deptCd").val())) {
			alert("기업코드를 입력해주세요");
			$("#deptCd").focus();
			return false;
		}
 		var deptCd = $("#deptCd").val();
		$.getJSON( 
			cUrl("/mng/user/deptInfo/deptCdCheck"), 
			{ "deptCd" : deptCd },			// params
			function(data) {
				$("#deptCdCheck").val(data.isUseable);
				if(data.isUseable == 'Y') {
					alert("사용 가능한 기업코드 입니다.");
				} else {
					alert("사용 불가능한 기업 코드 입니다. ");
				}
			}
		);
	}
	
	function resetDeptChk(){
		$("#deptCdCheck").val("N");
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userDeptInfoForm').attr("action","/mng/user/deptInfo/"+cmd);
		$('#userDeptInfoForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			parent.closeComWriteArea();
			parent.listPageing(1);
		} else {
			// 비정상 처리
			alert(resultDTO.message);
		}
	}
	
	/**
	 * 소속 추가
	 */
	function addUserDept() {
//		if(!validate(document.userDeptInfoForm)) return;
		var deptCdCheck = $("#deptCdCheck").val();
		//기업코드 중복검사
//		if(deptCdCheck == 'N'){
//			alert("기업코드 중복검사를 확인해주세요");
//			return false;
//		}
		
		//필수값 확인
		var result = validateChk();
		if(!result){
			return false;
		}
		process("addUserDept");	// cmd
	}

	/**
	 * 소속 수정
	 */
	function editUserDept() {
//		if(!validate(document.userDeptInfoForm)) return;
		//필수값 확인
		var result = validateChk();
		if(!result){
			return false;
		}		
		process("editUserDept");	// cmd
	}

	/**
	 * 소속 삭제
	 */
	function delUserDept() {
		process("removeUserDept");	// cmd
	}
	
	//필수값 체크
	function validateChk(){
 		if(isEmpty($("#useYn").val())) {
			alert("사용여부를 선택해주세요");
			$("#useYn").focus();
			return false;
		}
		if(isEmpty($("#deptTypeCd").val())) {
			alert("기업종류를 선택해주세요");
			$("#deptTypeCd").focus();
			return false;
		}
		if(isEmpty($("#deptNm").val())) {
			alert("법인명을 입력해주세요");
			$("#deptNm").focus();
			return false;
		}
		if(isEmpty($("#regNum").val())) {
			alert("사업자등록번호를 입력해주세요");
			$("#regNum").focus();
			return false;
		}
		if(!checkRegNum($("#regNum").val())){
			alert("유효한 사업자번호를 입력하세요");
			return false;			
		}
		if(isEmpty($("#ceoNm").val())) {
			alert("대표자명을 입력해주세요");
			$("#ceoNm").focus();
			return false;
		}
		if(isEmpty($("#phoneno1").val()) || isEmpty($("#phoneno2").val()) || isEmpty($("#phoneno3").val()) ){
			alert("전화번호를 입력해주세요.");
			return false;
		}else{
			var phoneno = $("#phoneno1").val() +"-"+ $("#phoneno2").val() + "-" + $("#phoneno3").val();
			$("#phoneno").val(phoneno);
		}
/*		if(!isEmpty($("#faxNo2").val()) && !isEmpty($("#faxNo3").val()) ){
			var faxNo = $("#faxNo1").val() +"-"+ $("#faxNo2").val() +"-"+ $("#faxNo3").val();
			$("#faxNo").val(faxNo);
		}*/
		if(isEmpty($("#deptPostNo").val()) || isEmpty($("#deptAddr1").val()) || isEmpty($("#deptAddr2").val()) ){
			alert("주소를 입력해주세요.");
			return false;
		}
		if(isEmpty($("#deptEmail").val())) {
			alert("이메일 입력해주세요");
			$("#deptEmail").focus();
			return false;
		}
		//mail 정규식
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if(!emailRule.test($("#deptEmail").val())) {            
            alert("이메일 형식이 맞지 안습니다.");
            $("#deptEmail").focus();
            return false;
		}
		
		var _bsnsLcnsFile = bsnsLcnsFile.getFileSnAll();
		$("#bsnsLcnsFileSn").val(_bsnsLcnsFile);
		
		return true;
	}
	
	function uploderclick(str) {
		$("#"+str).click();
	}
	
	function goDeptPopup(){
		window.name="jusoPopup";
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/mng/user/deptInfo/jusoDeptPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 	
	}
	
	function jusoDeptCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		$("#deptPostNo").val(zipNo);
		$("#deptAddr1").val(roadAddrPart1);
		$("#deptAddr2").val(addrDetail);
	}	

	//사업자 등록번호 유효성 체크
	function checkRegNum(number){
		if(number == "0123456789") return true;
		
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
</script>
