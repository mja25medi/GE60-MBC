<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="clibMediaCntsVO" value="${vo}"/>

<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"></script>
<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"></script>
<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.fileupload.js"></script>
<script type="text/JavaScript" src="/js/common_fileupload.js"></script> 

<form id="clibMediaCntsForm" name="clibMediaCntsForm" onsubmit="return false">
	<input type="hidden" name="cntsCd" value="${vo.cntsCd }" />
	<input type="hidden" name="ctgrCd" value="${vo.ctgrCd }" />
	<input type="hidden" name="ctgrNm" value="${vo.ctgrNm }" />
	<input type="hidden" name="cntsOdr" value="${vo.cntsOdr }" />
	<input type="hidden" name="cntsType" value="${vo.cntsType }" />
	<input type="hidden" name="thumbFileSn" value="${vo.thumbFileSn }"/>
	<input type="hidden" name="playerDiv" id="playerDiv" value="${vo.playerDiv }"/>
	<input type="hidden" name="fileNm" id="fileNm" value="${vo.fileNm }"/>
	<input type="hidden" name="uldStsCd" id="uldStsCd" value="${vo.uldStsCd }"/>
	<input type="hidden" name="uldFileKey" id="uldFileKey" value="${vo.uldFileKey }"/>
	
	<div class="modal_cont">
    	<div class="tstyle">
        	<ul class="dbody">
            	<li>
                   <div class="row">
                       <label for="cntsNm" class="form-label col-sm-2"><spring:message code="library.title.contents.name"/></label>
                       <div class="col-sm-10">
                           <div class="form-row">
                               <input class="form-control" type="text" name="cntsNm"  id="cntsNm" value="${vo.cntsNm }" maxlength="100" placeholder=""> 
                           </div>             
                       </div>
                   </div>
              	</li>
                <c:choose>
					<c:when test="${vo.cntsType eq 'VOD' }">
		                <li>
		                    <div class="row">
		                        <label for="vodFilePath" class="form-label col-sm-2">영상파일 경로</label>
		                        <div class="col-sm-10">
		                            <div class="form-row">
		                                <div class="input_btn full">
		                                	<input type="hidden" name="filePath" id="filePath" value="${vo.filePath }"/>
		                                	<c:if test="${empty vo.fileNm}">
												<a href="javascript:mediaFileUpload()" class="btn btn-primary btn-xs" id="media-file-upload-button"><spring:message code="library.title.contents.media.file.select"/></a>
											</c:if>
											<input class="form-control" id="media-file-name" name="vodFilePath" type="text" value="${vo.fileNm}" readonly>
		                                </div>
		                            </div>             
		                        </div>
		                    </div>
		                </li>
	                </c:when>
					<c:otherwise>	<!-- 영상 링크  -->
						 <li>
		                    <div class="row">
		                        <label for="filePath" class="form-label col-sm-2">링크 경로</label>
		                        <div class="col-sm-10">
		                            <div class="form-row">
		                                <div class="input_btn full">
		                                    <input class="form-control" id="filePath" type="text" name="filePath" value="${vo.filePath}" >
		                                </div>
		                            </div>             
		                        </div>
		                    </div>
		                </li>
					</c:otherwise>
				</c:choose>
             </ul>
         </div>
    </div>
	<div class="modal_btns">
		<c:if test="${gubun eq 'A'}">
			<button class="btn type2"" onclick="addCnts()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn type2"" onclick="editCnts()" ><spring:message code="button.add"/></button>
			<button class="btn type9" onclick="delCnts()" ><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn" onclick="closeWriteArea()" ><spring:message code="button.list"/></button>
	</div>
	
</form>

<script type="text/javascript">
	var attachModalBox = null;
	$(document).ready(function() {
		attachModalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		var fileName = '${vo.fileNm}';
		if(isEmpty(fileName)){
			$("#media-file-name").hide();
		}
	});
	
	function modalBoxClose() {
		parent.modalBox.close();
	}


	function attachModalBoxClose() {
		attachModalBox.clear();
		attachModalBox.close();
	}
	

	function uploderclick(str) {
		$("#"+str).click();
	}


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		var cntsNm = $("#cntsNm").val();
		if(isEmpty(cntsNm)) {
			alert("<spring:message code="library.message.contents.alert.input.contents.name"/>")
			$('#cntsNm').focus();
			return;
		}
		
		if( '${vo.cntsType}' == 'VOD'){
			if($("#media-file-name").val() == ""){
				alert("<spring:message code="library.title.contents.alert.media.file"/>");
				return;
			}
		}else{
			if($("#filePath").val() == null || $("#filePath").val() == ""){
				alert("콘텐츠 경로 입력하세요.")
				$('#filePath').focus();
				return;
			}
		}
		
		// 이부분을 처리 하지 않으면 multiPartFile 호출 됨.
		$('#clibMediaCntsForm').attr("action","/lec/cnts/" + cmd);
		$('#clibMediaCntsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		listPageing(1,'${vo.cntsType}');
	}

	function addCnts() {
		process("addShareContents");
	}

	function editCnts() {
		process("editShareContents");
	}

	function delCnts() {
		if(confirm("<spring:message code="library.message.contents.confirm.delete"/>")){
			process("removeShareContents");
		}
	}
	
	function closeWriteArea(){
		listPageing(1,'${vo.cntsType}');
	}

	function mediaFileUpload() {
		var cntsNm = $("#cntsNm").val();
		if(isEmpty(cntsNm)) {
			alert("<spring:message code="library.message.contents.alert.input.contents.name"/>");
			$('#cntsNm').focus();
			return;
		}
		var addContent  = "<iframe id='addFileUpload' name='addFileUpload' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/cnts/addUploadPop"/>"
			+ "'/>";
		attachModalBox.clear();
		attachModalBox.addContents(addContent);
		attachModalBox.resize(500, 300);
		attachModalBox.setTitle("<spring:message code="library.title.contents.media.file.upload"/>");
		attachModalBox.show();
	}


</script>
