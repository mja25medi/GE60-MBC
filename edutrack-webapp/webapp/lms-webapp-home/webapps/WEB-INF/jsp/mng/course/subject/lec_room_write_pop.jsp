<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<c:set var="gubun" value="${gubun}" />
<c:set var="lecRoomVO" value="${vo}" />

	<form id="lecRoomForm" name="lecRoomForm" onsubmit="return false" >
	<input type="hidden" name="autoMakeYn" value="Y"/>
	<input type="hidden" name="lecRoomSn" value="${vo.lecRoomSn }"/>
	<table summary="<spring:message code="course.title.lecroom.write"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="deptPostNo">주소</label></th>
			<td colspan="3">
			<div class="input-group" >
				<input type="text" name="postNo" id="deptPostNo" class="form-control input-sm" value="${lecRoomVO.postNo}" maxlength="5" isNull="N" lenCheck="5" style="width:200px;" placeholder="우편번호" dispName="우편번호"/>
				&nbsp;&nbsp;<button class="btn btn-info btn-sm" type="button" onclick="goDeptPopup();">우편검색</button>
			</div>
			<div class="input-group" style="margin-top: 5px;" >
				<input type="text" name="addr1" id="deptAddr1" class="form-control input-sm" value="${lecRoomVO.addr1}" maxlength="120" isNull="N" lenCheck="120" style="width:600px;" placeholder="기본주소" dispName="기본주소"/>
			</div>
			<div class="input-group" style="margin-top: 5px;">
				<input type="text" name="addr2" id="deptAddr2" class="form-control input-sm" value="${lecRoomVO.addr2}" maxlength="120" isNull="N" lenCheck="120" style="width:600px;" placeholder="상세주소" dispName="상세주소"/>
			</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="tracseId">용도</label></th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="usageCd" value="01" <c:if test="${lecRoomVO.usageCd ne '02' and lecRoomVO.usageCd ne '03'}">checked</c:if> />&nbsp;&nbsp;강의실</label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="usageCd" value="02" <c:if test="${lecRoomVO.usageCd eq '02'}">checked</c:if>/>&nbsp;&nbsp;회의실</label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="usageCd" value="03" <c:if test="${lecRoomVO.usageCd eq '03'}">checked</c:if>/>&nbsp;&nbsp;실습실</label>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="eduPrps">설명</label></th>
			<td colspan="3">
				<textarea style="height:50px" isNull="N" name="lecRoomDesc" id="lecRoomDesc" class="form-control input-sm" maxlength="800" lenCheck="800" dispName="설명">${lecRoomVO.lecRoomDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="eduTarget">상태</label></th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${lecRoomVO.useYn ne 'N'}">checked</c:if>/>&nbsp;&nbsp;사용</label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${lecRoomVO.useYn eq 'N'}">checked</c:if>/>&nbsp;&nbsp;미사용</label>
			</td>
		</tr>

	</table>
	
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
			<a href="javascript:addLecRoom()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<a href="javascript:editLecRoom()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			<a href="javascript:delLecRoom()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>


<script type="text/javascript">

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
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		
		if(!validate(document.lecRoomForm)) return false;

 		$('#lecRoomForm').attr("action","/mng/course/subject/" + cmd);
		$('#lecRoomForm').attr("method","post");
		$('#lecRoomForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listLecRoom(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
			parent.modalBoxClose();
		}
	}
		
	/**
	 * 강의실 등록
	 */
	function addLecRoom() {
		process("addLecRoom");	// cmd
	}
	
	/**
	 * 강의실 수정
	 */
	function editLecRoom() {
		process("editLecRoom");	// cmd
	}
	
	/**
	 * 강의실 삭제
	 */
	function delLecRoom() {

		//과정에서 사용중인 강의실 체크 조건 추후 추가
/* 		if(){
			alert("<spring:message code="course.message.lecroom.alert.delete1"/>");
			return;
		} */

		if(confirm('<spring:message code="course.message.lecroom.confirm.delete"/>')) {
			process("deleteLecRoom");	// cmd
		} else {
			return;
		}
	}

</script>
