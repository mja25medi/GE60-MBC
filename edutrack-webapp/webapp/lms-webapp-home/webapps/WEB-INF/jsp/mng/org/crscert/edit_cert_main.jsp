<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row">
					<form name="orgCrsCertForm" id="orgCrsCertForm" onsubmit="return false" method="POST">
						<div class="col-lg-6" style="margin-top:5px;">
							<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}" />
							<input type="hidden" name="attachFileSns" id="attachFileSns" value="${StringUtil.nvl(vo.attachFileSns,'')}">
							<input type="hidden" name="attachFileSns2" id="attachFileSns2" value="${StringUtil.nvl(vo.attachFileSns2,'')}">
							<input type="hidden" name="printDirec" id="printDirec" value="${vo.printDirec}">
							<input type="hidden" name="certContentJson" id="certContentJson" value="${vo.certContentJson}">
							<div id="writeArea">
								<table class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:20%;"/>
										<col style="width:30%;"/>
									</colgroup>
									<tr>
										<th scope="row"><spring:message code="org.title.crscert.prn.direction"/></th>
										<td>
											<label>
												 <input type="radio" name="printDir" id="printDirH" value="HOR" onchange="changePrintDir('HOR')">
												 <spring:message code="org.title.crscert.prn.direction.horizontal"/>
											</label>
											<label style="margin-left:20px;">
												<input type="radio" name="printDir" id="printDirV" value="VER" onchange="changePrintDir('VER')" checked>
												<spring:message code="org.title.crscert.prn.direction.vertical"/>
											</label> 
										</td>
									</tr>
									<c:forEach var="item" items="${certContnetList}"  varStatus="status">  	      
										<tr>
											<th scope="row">${item.codeDesc}</th>
											<td class="dataType">
												<input type="hidden" name="codeCd" value="${item.codeCd}">
												<input type="hidden" name="codeNm" value="${item.codeNm}">
												<!-- 가로 -->
												<span class="spanCm"><spring:message code="course.title.reshbank.item.view.w"/>(px) : </span>&nbsp;
												<input type="text" name="width" id="${item.codeCd}_width" maxlength="4" required="required" title="<spring:message code="org.title.crscert.position.x"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="0" onkeyup="isChkNumber(this)" onblur="moveObjectPosition(this);"/>
												<!-- 세로 -->
												<span class="spanCm" style="margin-left:20px;"><spring:message code="course.title.reshbank.item.view.h"/>(px) : </span>&nbsp;
												<input type="text" name="height" id="${item.codeCd}_height" maxlength="4" required="required" title="<spring:message code="org.title.crscert.position.y"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="0" onkeyup="isChkNumber(this)" onblur="moveObjectPosition(this);"/>
												<!-- 글꼴 크기 -->
												<span class="spanCm" style="margin-left:20px;">글꼴 크기 : </span>&nbsp;
												<input type="text" name="fontSize" id="${item.codeCd}_size" maxlength="4" required="required" title="<spring:message code="org.title.crscert.font.size"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="0" onkeyup="isChkNumber(this)" onblur="moveObjectPosition(this);"/>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<th scope="row"><spring:message code="org.title.crscert.image"/></th>
										<td>
											<div style="margin-top: 10px;">
												<div class="upload0">
													<div class="upload_inbox">
														<a href="javascript:uploderclick('uploader0');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
														<input type="file" name="uploader0" id="uploader0"  style="display:none" accept="image/*" />
														<div id="progress0" class="progress">
										    				<div class="progress-bar progress-bar-success"></div>
														</div>
													</div>
													<div id="bgFiles" class="multi_inbox"></div>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="org.title.crscert.sign.image"/></th>
										<td>
											<div style="margin-top: 10px;">
												<div class="upload1">
													<div class="upload_inbox">
														<a href="javascript:uploderclick('uploader1');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
														<input type="file" name="uploader1" id="uploader1"  style="display:none" accept="image/*" />
														<div id="progress1" class="progress">
										    				<div class="progress-bar progress-bar-success"></div>
														</div>
													</div>
													<div id="signFiles" class="multi_inbox"></div>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<!-- 수료증 이미지 및 위치 -->
						<div class="col-lg-6" style="margin-top:5px;">
							<div style="border:3px solid #f4f4f4; overflow: auto;" id="canvasSize">
	                          	<div id="locationWorkArea" style="position:relative">
	                              	<c:forEach var="item" items="${certContnetList}"  varStatus="status">  
	                              		<div id="${item.codeCd}_Obj" class="dragobj ui-draggable" style="font-size: 20px; position: absolute;  border: 1px dotted red; cursor: move; ">
	                              			<c:if test="${item.codeCd ne 'cpltNo' && item.codeCd ne 'cpltDttm'}">
	                              				${item.codeNm} :  
	                              			</c:if>          
	                              			${item.codeOptn}                    
	                              		</div>
	                              	</c:forEach>
	                          	</div>
	                		</div>
						</div>
				    </form>
				</div>
				
				<div class="text-center" style="margin-top:10px;">
					<c:if test="${gubun eq 'A' }">
						<button class="btn btn-primary btn-sm" onclick="saveCert('A')" ><spring:message code="button.add"/></button>
					</c:if>
					<c:if test="${gubun eq 'E' }">
						<button class="btn btn-primary btn-sm" onclick="saveCert('E')" ><spring:message code="button.add"/></button>
					</c:if>
					<button class="btn btn-info btn-sm" onclick="previewCert()" ><spring:message code="common.title.preview"/></button>
				</div>
			</div>
		</div>
	</div>
</section>
		

<script type="text/javascript">
	var pageWidth = 0;
	var pageHeight = 0;
	var fixedMargin = 0;
	var backgroundImgFile;
	var stampImgfile;
	
	function getCertFileUpload() {
		  var imgFileJson = '[]';
		  if ('${vo.attachFilesJson}' != '') {
			  imgFileJson = '${vo.attachFilesJson}';
	      }
		  var imgFileJson2 = '[]';
		  if ('${vo.attachFilesJson2}' != '') {
			  imgFileJson2 = '${vo.attachFilesJson2}';
	      }
		  
		//수료증 이미지
		backgroundImgFile = new $M.JqueryFileUpload({
			"varName"			: "backgroundImgFile",
			"files" 			: $.parseJSON(imgFileJson),
			"uploaderId"		: "uploader0",
			"fileListId"		: "bgFiles",
			"progressId"		: "progress0",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'CERTIMG',
									'organization' : "${vo.orgCd}",
									'type'		: 'image' }
			}
		});
		
		//직인 이미지
		stampImgfile  = new $M.JqueryFileUpload({
			"varName"			: "stampImgfile",
			"files" 			: $.parseJSON(imgFileJson2),
			"uploaderId"		: "uploader1",
			"fileListId"		: "signFiles",
			"progressId"		: "progress1",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'CERTSTAMP',
									'organization' : "${vo.orgCd}",
									'type'		: 'image' }
			}
		});
		
	}
	
	function uploderclick(str) {
		$("#"+str).click();
	}

	 $(document).ready(function() { 	  
		/* 파일 첨부 업로더 및 바인딩 및 초기화*/
		getCertFileUpload();
	 });
		
	 $(window).load(function() { 
		 
		 /*출력방향*/
		 var printDirec = "${vo.printDirec}";
		 
		 if(printDirec == "HOR"){
			 $("#printDirec").val(printDirec);
			 $("#printDirH").prop("checked",true);
			 
		 }else{
			 $("#printDirec").val("VER");
			 $("#printDirV").prop("checked",true);
		 }
		 
		 setHeightSize();
		 getJsonData();

	});
	 
	 /*수료증 내용 ${vo.certContentJson}*/
	 function getJsonData(){
		
		if(!'${vo.certContentJson}'){
			 
		}else{
				
			/*JSON 파싱*/ 
			var jsonData = JSON.parse('${vo.certContentJson}');
		
			/*JSON 데이터 for문*/
			$.each(jsonData, function(key, value){	
				/*이중 JSON 1Depth 데이터 추출*/		
				rs = value;
				/* 키값 추출 */
				var keyValue = Object.keys(rs);
			
				$.each(rs, function(key, value){
					/*이중 JSON 2Depth 데이터 추출*/
					 rst = value;
					 $("#"+ keyValue +"_width").val(rst.width);
					 $("#"+ keyValue +"_height").val(rst.height);
					 $("#"+ keyValue +"_size").val(rst.fontSize);
					 $("#"+ keyValue +"_Obj").attr('style',"font-size:18px; position: absolute; top: "+ rst.height +"px; left: "+ rst.width +"px; border: 1px dotted red; cursor: move;");
				 }); 
				
			 });
		}
		moveObjectPosition();
		setDragobj();
	 }
	 
	 
	function setObjectPosition(objectId) {
	 	var leftVal = 0;
		var topVal = 0;
		
		var printDirec = $("#printDirec").val();
		 
		var left = Math.round($("#"+objectId).position().left) - leftVal;
		var top = Math.round($("#"+objectId).position().top) + topVal;

		var objId = objectId.split("_");
		var id = objId[0];
		
		$("#"+ id +"_width").val(left);
		$("#"+ id +"_height").val(top);
	
	}
	
	function moveObjectPosition() {
		var leftVal = 0;
		var topVal = 0;
		var printDirec = $("#printDirec").val();
		
		 $('.dataType').each(function (index, item) {
		     		 	   		 
		     var width = $(this).find('input[name=width]').val();
		     var height = $(this).find('input[name=height]').val();  
		     
		     var id = $(this).find('input[name=codeCd]').val();
			 
			var left = Math.round(width) + leftVal ;
			$("#"+ id +"_Obj").css("left", left);

			var top = Math.round(height) + topVal ;
			$("#"+ id +"_Obj").css("top", top);
			 
		});
		 $( ".dragobj" ).draggable({
				cursor	: "move",
				stop 	: function (event, ui) {
					var objectId = $(ui.helper[0]).children().prevObject.attr("id");
					setObjectPosition(objectId);
				}
			});
	}
	 
	 /*저장*/
	 function saveCert(gubun){
		 var attachFileSns = backgroundImgFile.getFileSnAll();
		 var attachFileSns2 = stampImgfile.getFileSnAll();
		 
		 $("#attachFileSns").val(attachFileSns);
		 $("#attachFileSns2").val(attachFileSns2);
		 
		 if(!checkValid()){return false;}
		 createJSON();
		 
		 if(gubun=="A"){
			 process("add");
		 }else{
			 process("edit");
		 }
	 }
	 
	 /**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#orgCrsCertForm').attr("action", "/mng/org/crsCert/" + cmd);
		$('#orgCrsCertForm').attr("target","");
		$('#orgCrsCertForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		if(resultVO.result >= 0) { //success
			alert(resultVO.message);
		} else { //failed
			alert(resultVO.message);
		}
	}
	 
	/*유효성 체크*/
	function checkValid(){
		 
		var attachFileSns = backgroundImgFile.getFileSnAll();
		var attachFileSns2 = stampImgfile.getFileSnAll();
	
		 if(!attachFileSns){
			 alert("수료증 배경 이미지를 선택해주시기 바랍니다.");
			return false;
		 }
		 
		 if(!attachFileSns2){
			 alert("수료증 직인 이미지를 선택해주시기 바랍니다.");
			return false;
		 }
		 
		 return true;
	 }
	
	 
	 /*취소*/
	function cancel() {
		$("#orgCrsCertForm").attr("action", "/crs/creCrsMgr/Form/certForm");
		$("#orgCrsCertForm").submit();
	}
	 
	 
	 /*출력방향 라디오버튼*/
	 function changePrintDir(value){
		$("#printDirec").val(value);
		setHeightSize();
		createJSON();
	 }
	 
	 /*출력 방향에 따른 이미지 사이즈*/
	 function setHeightSize() {
	    var value = $("#printDirec").val();
	    if(value == "HOR"){
	    	pageHeight = 595;
	    	pageWidth = 842;
	    }else{
	    	pageHeight = 842;
	    	pageWidth = 595;
	    }
	    $("#canvasSize").css('height',pageHeight);
	    $("#canvasSize").css('width',pageWidth);
	}
	 	 
	 /*수료증 내용 JSON만들기*/
	 function createJSON(){
		 
		 var dataList = new Array() ;
		 
		 $('.dataType').each(function (index, item) {
		     		 
		     var displayName = $(this).find('input[name=codeNm]').val();
		     var width = $(this).find('input[name=width]').val();
		     var height = $(this).find('input[name=height]').val();
		     var fontSize = $(this).find('input[name=fontSize]').val();
		     
		     var data = new Object();
		     
		     data.displayName = displayName;
		     data.width = width;
		     data.height = height;
		     data.fontSize = fontSize;
		     
			 var id = $(this).find('input[name=codeCd]').val();
			 
			 var keyData = {[id] : data };
			 			 
			 dataList.push(keyData);
		});
		 
		 /*json으로 변환*/
		 var jsonData = JSON.stringify(dataList);
		 
		 $("#certContentJson").val(jsonData);
	 }
	 
	 function removeImgCallback() {
		$("#previewImage").attr("src", "");
	
	 }
	 
	 function setDragobj(){
		 $( ".dragobj" ).draggable({
				cursor	: "move",
				stop 	: function (event, ui) {
					var objectId = $(ui.helper[0]).children().prevObject.attr("id");
					setObjectPosition(objectId);
				}
			});
	 }
	 
	 /*미리보기*/
	function previewCert() {
 
		if(!checkValid()){return false;}
		 
		var url = "/mng/org/crsCert/preview";
		var option = "width=830, height=640, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var reportWin = window.open(url,'reportWin', option);
		reportWin.focus();
		
	}
	 
</script>
