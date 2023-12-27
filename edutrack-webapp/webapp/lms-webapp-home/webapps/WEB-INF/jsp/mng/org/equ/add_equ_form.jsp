<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

	<form name="equViewForm" id="equViewForm" method="POST" onsubmit="return false">
		<input type="hidden" id="orgCd" name="orgCd" value="${vo.orgCd }"/>
		<input type="hidden" id="equCd" name="equCd" value="${vo.equCd }"/>
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:20%;"/>
				<col style="width:80%;"/>
			</colgroup>			
			<tr>
				<th scope="row"><spring:message code="null" text="장비명"/></th>
				<td><input class="form-control input-sm" type="text" id="equNm" name="equNm" value="${vo.equNm }"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="장비 이미지"/></th>
				<td>
					<div class="upload">
						<div class="upload_inbox" >
							<input type="file" name="atchuploader" id="atchuploader" title="첨부파일" multiple="" style="display:none"> 
							<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message text="파일업로드" code="null"/></a>
							<div id="atchprogress" class="progress">
								<div class="progress-bar progress-bar-success"></div>
							</div>
						</div>
						<div id="atchfiles" class="files multi_inbox"></div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="장비설명"/></th>
				<td><textarea class="form-control input-sm" rows="3" name="equDesc" id="equDesc">${vo.equDesc }</textarea></td>
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
				<button class="btn btn-primary btn-sm" onclick="addEqu();" ><spring:message code="button.add"/></button>
			</c:if>
	    	<c:if test="${gubun eq 'E' }">
				<button class="btn btn-primary btn-sm" onclick="editEqu();" >수정</button>
				<button class="btn btn-warning btn-sm" onclick="delEqu();" >삭제</button>
			</c:if>			
	        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	    </div>
	</form>
	<script type="text/javascript">
	var atchFiles = "";	// 첨부파일 변수	
	$(document).ready(function() {
		atchFiles = new $M.JqueryFileUpload({
			"varName"			: "atchFiles",
			"files" 			: $.parseJSON('${vo.attachFilesJson}'),
			"uploaderId"		: "atchuploader",
			"fileListId"		: "atchfiles",
			"progressId"		: "atchprogress",
			"previewImage"		: true,
			"maxcount"			: 3,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'EQU_INFO',
									'organization' : "${USER_ORGCD}",
									'type'		: 'file' }
			}
		});
	});
		function addEqu(){
			if(chkIn()) {
				$("#equViewForm").attr("action", "/mng/org/equ/addEqu")
				$("#equViewForm").ajaxSubmit(processCallback);
			}
		}
		
		function editEqu(){
			if(chkIn()) {
				$("#equViewForm").attr("action", "/mng/org/equ/editEqu")
				$("#equViewForm").ajaxSubmit(processCallback);
			}
		}
		
		function delEqu() {
			if(confirm("장비 삭제 시 해당 장비의 상품 및 모든 예약 내역도 함께 삭제됩니다.\n\n정말 삭제 하시겠습니까?")) {
				$("#equViewForm").attr("action", "/mng/org/equ/deleteEqu");
				$("#equViewForm").ajaxSubmit(processCallback);
			}
		}
		
		/**
		 * 처리 결과 표시 콜백
		 */
		function processCallback(data) {
			if(data.result == 1) {
				alert(data.message);
				parent.listEqu(1);
				parent.modalBoxClose();
			} else {
				alert(data.message);
			}
		}
		/**
		 * 시설 입력 정보 확인
		 */
		function chkIn() {
			var equNm = $("#equNm").val(); //시설명
			var equDesc = $("#equDesc").val(); //용도
			
			var useYn = $("input[name=useYn]:checked").val();
			
			if(equNm==null || equNm=="") {
				$("#equNm").focus();
				alert("장비명을 입력해 주세요.");
				return false;
			}
			
			if(equDesc==null || equDesc=="") {
				$("#equDesc").focus();
				alert("장비 설명을 입력해 주세요.");
				return false;
			}
		
			if(useYn==null || useYn=="") {
				$("#useYn").focus();
				alert("사용여부를 체크해 주세요.");
				return false;
			}
			
			var atchuploader = $("#atchfiles div").children().length;
			if(atchuploader<=0) {
				alert("장비 사진을 업로드하세요.");
				return false;
			}
			//파일 no 리스팅
			var imgfile = $("#atchfiles").find("a[id*=btnRemoveFile_]");
			var imgfileList = new Array();
			for (var k = 0; k < imgfile.length; k++) {
				imgfileList[k] = imgfile[k].id.split("_")[1];
				$("#equViewForm").append("<input type='hidden' name='attachFiles["+k+"].fileSn'  value='"+imgfileList[k]+"'/>");
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
		