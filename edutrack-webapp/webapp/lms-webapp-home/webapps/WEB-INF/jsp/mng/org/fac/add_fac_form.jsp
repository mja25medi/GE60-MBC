<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

	<form name="facViewForm" id="facViewForm" method="POST" onsubmit="return false">
		<table summary="data" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:20%;"/>
				<col style="width:80%;"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="null" text="시설 분류"/></th>
				<td>
					<select class="form-control input-sm mr5" name="facCtgrCd" id="facCtgrCd">
						<option value="">선택</option>
						<c:forEach var="item" items="${ctgrList}">
							<option value="${item.facCtgrCd }" <c:if test="${vo.facCtgrCd eq item.facCtgrCd }">selected</c:if>>${item.facCtgrNm }</option>
						</c:forEach>
					</select>
				</td>
			</tr>			
			<tr>
				<th scope="row"><spring:message code="null" text="시설명"/></th>
				<td><input class="form-control input-sm" type="text" id="facNm" name="facNm" value="${vo.facNm}"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="면적"/></th>
				<td><input class="form-control input-sm" type="number" id="facArea" name="facArea" value="${vo.facArea}"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="수용인원"/></th>
				<td><input class="form-control input-sm" type="number" id="facCap" name="facCap" value="${vo.facCap}"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="운영시간"/></th>
				<td><textarea class="form-control input-sm" rows="3" name="facOperTime" id="facOperTime">${vo.facOperTime}</textarea></td>
			</tr>			
			<tr>
				<th scope="row"><spring:message code="null" text="용도"/></th>
				<td><input class="form-control input-sm" class="form-control input-sm" type="text" id="facUse" name="facUse" value="${vo.facUse}"/></td>
			</tr>		
			<tr>
				<th scope="row"><spring:message code="null" text="장비"/></th>
				<td><input class="form-control input-sm" type="text" id="facEqu" name="facEqu" value="${vo.facEqu}"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="null" text="시설위치"/></th>
				<td><input class="form-control input-sm" type="text" id="facLoc" name="facLoc" value="${vo.facLoc}"/></td>
			</tr>
			<tr>
				<th scope="col"><spring:message code="null" text="사용여부"/></th>
				<td>
					사용함<input type="radio" name="useYn" <c:if test="${vo.useYn eq 'Y'}">checked</c:if> value="Y"/>
					사용안함<input type="radio" name="useYn" <c:if test="${vo.useYn eq 'N'}">checked</c:if> value="N"/>
				</td>
			</tr>
			<tr>
				<th scope="col">시설 이미지</th>
				<td>
					<div class="upload">
						<div class="upload_inbox" >
							<input type="file" name="atchuploader" id="atchuploader" title="첨부파일" multiple="" style="display:none"> 
							<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message text="파일업로드" code="null"/></a>
							<div id="atchprogress" class="progress">
								<div class="progress-bar progress-bar-success"></div>
							</div>
						</div>
						<div id="atchfiles" class="files multi_inbox">
							<c:forEach var="itemFile" items="${vo.attachFiles}" varStatus="status">
								${itemFile.thumbUrl}
							</c:forEach>						
						</div>
					</div>
				</td>
			</tr>
		</table>
	    <div class="text-right" style="margin-top:10px;">
	        <c:if test="${gubun eq 'A' }">
			<button class="btn btn-primary btn-sm" onclick="addFac()" >등록</button>
	        </c:if>
	        <c:if test="${gubun eq 'E' }">
				<button class="btn btn-primary btn-sm" onclick="editFac()" >수정</button>
	        </c:if>
	        <button class="btn btn-warning btn-sm" onclick="delFac()" >삭제</button>
	        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" >닫기</button>
	    </div>
	    <input type="hidden" id="facCd" name="facCd" value="${vo.facCd}"/>
	    <input type="hidden" id="orgCd" name="orgCd" value="${vo.orgCd}"/>
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
				'formData'		: { 'repository': 'FAC_INFO',
									'organization' : "${USER_ORGCD}",
									'type'		: 'file' }
			}
		});
	});
		function addFac(){
			process("addFac");
		}
		
		function editFac() {
			process("editFac");
		}
		
		function delFac() {
			var msg = '<spring:message code="null" text="시설 삭제시 시설에 예약된 모든 예약 내역도 함께 삭제됩니다.\n\n정말 삭제 하시겠습니까?"/>';
			if(confirm(msg)) {
				$('#facViewForm').attr("action","/mng/org/fac/deleteFac");
				$('#facViewForm').ajaxSubmit(processCallback);
			} else {
				return;
			}
		}
		
		function process(cmd) {
			if(chkIn()) {
				$('#facViewForm').attr("action","/mng/org/fac/"+cmd);
				$('#facViewForm').ajaxSubmit(processCallback);
			} else {
			}
		}
		
		/**
		 * 처리 결과 표시 콜백
		 */
		function processCallback(result) {
			alert(result.message);
			if(result.result == 1) {
				parent.listPageing(1);
				parent.modalBoxClose();
			}
		}
		/**
		 * 시설 입력 정보 확인
		 */
		function chkIn() {
			var facCtgrCd = $("#facCtgrCd").val(); //시설분류
			var facNm = $("#facNm").val(); //시설명
			var facArea = $("#facArea").val(); //면적
			var facCap = $("#facCap").val(); //수용인원
			var facUse = $("#facUse").val(); //용도
			var facOperTime = $("#facOperTime").val(); //운영시간
			var facEqu = $("#facEqu").val(); //장비
			var facLoc = $("#facLoc").val(); //시설위치
			
			var useYn = $("input[name=useYn]:checked").val();
	
			if(facCtgrCd==null || facCtgrCd=="") {
				$("#facCtgrCd").focus();
				alert("시설 분류를 선택해 주세요.");
				return false;
			} else {
			}
			
			if(facNm==null || facNm=="") {
				$("#facNm").focus();
				alert("시설명을 입력해 주세요.");
				return false;
			} else {
			}
			
			if(facOperTime==null || facOperTime=="") {
				$("#facOperTime").focus();
				alert("운영시간을 입력해 주세요.");
				return false;
			} else {
			}
			
			if(facArea==null || facArea=="") {
				$("#facArea").focus();
				alert("면적을 입력해 주세요.");
				return false;
			} else {
			}
			
			if(facCap==null || facCap=="") {
				$("#facCap").focus();
				alert("수용인원을 입력해 주세요.");
				return false;
			} else {
			}
			
			if(facUse==null || facUse=="") {
				$("#facUse").focus();
				alert("용도를 입력해 주세요.");
				return false;
			} else {
			}
			
			if(facEqu==null || facEqu=="") {
				$("#facEqu").focus();
				alert("장비를 입력해 주세요.");
				return false;
			} else {
			}
			
			if(facLoc==null || facLoc=="") {
				$("#facLoc").focus();
				alert("시설위치를 입력해 주세요.");
				return false;
			} else {
			}
			
			if(useYn==null || useYn=="") {
				$("#useYn").focus();
				alert("사용여부를 체크해 주세요.");
				return false;
			} else {
			}
			
			//사업자등록증(첨부)
			var atchuploader = $("#atchfiles div").children().length;
			if(atchuploader<=0) {
				alert("시설 사진을 업로드하세요.");
				return false;
			}
			//파일 no 리스팅
			var imgfile = $("#atchfiles").find("a[id*=btnRemoveFile_]");
			var imgfileList = new Array();
			for (var k = 0; k < imgfile.length; k++) {
				imgfileList[k] = imgfile[k].id.split("_")[1];
				$("#facViewForm").append("<input type='hidden' name='attachFiles["+k+"].fileSn'  value='"+imgfileList[k]+"'/>");
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
		