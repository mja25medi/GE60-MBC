<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<div class="modal_cont">
        <div class="board_top type2">
            <div class="left_title">
                <h4>학습 목차 설정: 1차시 ${vo.unitNm }</h4>
            </div>
            <div class="page_btn">
                <button type="button" class="btn type2" onclick="javascript:contentsEdit()">저장</button>
                <button type="button" class="btn type9" onclick="javascript:creCntsRemove()">삭제</button>
                <button type="button" class="btn" onclick="javascript:modalBoxClose()">닫기</button>
            </div>
        </div>
        <div class="row subject_pop">
            <div class="col-sm-6">
                <div class="segment">
                    <ul class="class_tab"  id="tabMenuCnts">
                        <li id="tabBox2"><a href="javascript:tabBox('2')">VOD</a></li>
                        <li class="active"  id="tabBox1"><a href="javascript:tabBox('1')">CDN</a></li>
                        <li id="tabBox3"><a href="javascript:tabBox('3')">영상링크</a></li>
                        <!-- 
                        <li id="tabBox5"><a href="javascript:tabBox('5')">코딩과제</a></li>
                        <li id="tabBox4"><a href="javascript:tabBox('4')">콘텐츠파일</a></li>
                         -->
                    </ul>
                    <div class="panel-body" id="leftWorkArea" style="border-top:0px;">

					</div>
                </div>
            </div>
            
            
            <div class="col-sm-6">
                <div class="segment subject_pop">
               			<form id="contentsForm" name="contentsForm" class="form-inline" onsubmit="return false" style="display: unset">
						<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
						<input type="hidden" name="sbjCd" value="${vo.sbjCd}" />
						<input type="hidden" name="unitType" value="${vo.unitType}" />
						<input type="hidden" name="unitCd" value="${vo.unitCd}" />
						<input type="hidden" name="parUnitCd" value="${vo.parUnitCd}" />
						<input type="hidden" name="unitLvl" value="${vo.unitLvl}" />
						<input type="hidden" name="unitOdr" value="${vo.unitOdr}" />
						<input type="hidden" name="moveType" value="${vo.moveType}" />
						<input type="hidden" name="olcYn" value="${vo.olcYn}" />
						<input type="hidden" name="bookmarkCnt" value="${vo.bookmarkCnt}" />
						<input type="hidden" name="subCnt" value="${vo.subCnt}" />
						<input type="hidden" name="roomId" id="roomId" value="${vo.roomId}" />
	                    <div class="tstyle">
	                        <ul class="dbody">                            
	                            <li>
	                                <div class="row">
	                                    <label for="titleInput" class="form-label col-sm-2">차시명</label>
	                                    <div class="col-sm-10">
	                                        <div class="form-row">
	                                            <input class="form-control" type="text" name="unitNm" id="unitNm" value="${vo.unitNm }" onfocus="this.select()" maxlength="100" placeholder=""> 
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                            <li id="cntsTypeSel">
	                                <div class="row" >
	                                    <label for="conType" class="form-label col-sm-2">콘텐츠 타입</label>
	                                    <div class="col-sm-10">
	                                        <div class="form-row">
	                                            <div class="ra_inline">
	                                                <span class="custom-input">
	                                                    <input type="radio" name="cntsTypeCd" id="cntsTypeCd1" value="VOD" onclick="cntsTypeChk()"/>
	                                                    <label for="cntsTypeCd1">VOD</label>
	                                                </span>
	                                                <span class="custom-input ml5">
	                                                    <input type="radio"  name="cntsTypeCd" id="cntsTypeCd2" value="CDN" onclick="cntsTypeChk()"/>
	                                                    <label for="cntsTypeCd2">CDN</label>
	                                                </span>
	                                                <span class="custom-input ml5">
	                                                    <input type="radio" name="cntsTypeCd" id="cntsTypeCd3" value="MLINK" onclick="cntsTypeChk()"/>
	                                                    <label for="cntsTypeCd3">영상링크</label>
	                                                </span>
	                                                <!-- 
	                                                <span class="custom-input ml5">
	                                                    <input type="radio"  name="cntsTypeCd" id="cntsTypeCd5" value="CODING_L" onclick="cntsTypeChk()" /> 
	                                                    <label for="cntsTypeCd5">코딩강의</label>
	                                                </span>
	                                                <c:if test ="${createOnlineSubjectVO.sbjType eq 'OF'}">
		                                                <span class="custom-input ml5">
		                                                    <input type="radio" name="cntsTypeCd" id="cntsTypeCd6" value="CODING_T" onclick="cntsTypeChk()" /> 
		                                                    <label for="cntsTypeCd6">코딩실습</label>
		                                                </span>
		                                                <span class="custom-input ml5">
		                                                    <input type="radio" name="cntsTypeCd" id="cntsTypeCd7" value="META" onclick="cntsTypeChk()" /> 
		                                                    <label for="cntsTypeCd7">메타버스 강의</label>
		                                                </span>
	                                                </c:if>
	                                                 -->
	                                            </div>
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                            <li id="metaTr"  style="display: none;">
	                                <div class="row">
	                                <label for="Scene" class="form-label col-sm-2">Scene</label>
	                                    <div class="col-sm-10">
	                                        <div class="form-row" >
											 <span class="custom-input" id="metaTd">
												
											</span>
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                            <li  id="zoomUrlTr">
	                                <div class="row">
	                                    <label for="zoomUrl" class="form-label col-sm-2">Zoom URL</label>
	                                    <div class="col-sm-10">
	                                        <div class="form-row">
		                                         <div class="input_btn full">
		                                            <input class="form-control" type="text" name="zoomUrl" id="zoomUrl" value="${vo.zoomUrl }" maxlength="100" >
		                                            <label onclick="delPath('zoomUrl')"><i class="xi-close"></i></label>
		                                        </div>             
	                                        </div>
	                                    </div>
	                                </div>
	                            </li>
	                            <li id="codingUrlTr" style="display: none;">
	                                <div class="row">
	                                    <label for="asmtSn" class="form-label col-sm-2">코딩과제 경로</label>
	                                    <div class="col-sm-10">
	                                        <div class="form-row">
	                                            <div class="input_btn full">
	                                                <input type="text" style="background-color:#f3f3f3" name="asmtSn" value="${vo.asmtSn }" readonly="true" class="form-control input-sm" id="asmtSn" />
	                                                <label onclick="delPath('asmtSn')"><i class="xi-close"></i></label>
	                                            </div>
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                            <li id="unitFilePathTr">
	                                <div class="row">
	                                    <label for="unitFilePath" class="form-label col-sm-2">콘텐츠 경로</label>
	                                    <div class="col-sm-10">
	                                        <div class="form-row">
	                                            <div class="input_btn full">
	                                                <input type="text" style="background-color:#f3f3f3" name="unitFilePath" value="${vo.unitFilePath }" readonly="true" class="form-control input-sm" id="unitFilePath" />
	                                                <label onclick="delPath('unitFilePath')"><i class="xi-close"></i></label>
	                                            </div>
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                            <li id="atchFilePathTr">
	                                <div class="row">
	                                    <label for="pdfIatchFilePathnput" class="form-label col-sm-2">교안파일경로(PDF)</label>
	                                    <div class="col-sm-10">
	                                        <div class="form-row">
	                                            <div class="input_btn full">
	                                                <input type="text" style="background-color:#f3f3f3" dispName="<spring:message code="course.title.contents.attach"/>" name="atchFilePath" value="${vo.atchFilePath }" readonly="true" class="form-control input-sm" id="atchFilePath" />
	                                                <label onclick="delPath('atchFilePath')"><i class="xi-close"></i></label>
	                                            </div>
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                            <li>
	                                <div class="row">
	                                  <label for="critTm" class="form-label col-sm-2"><spring:message code="course.title.contents.default.time"/></label>
	                                     <div class="col-sm-10">
	                                        <div class="form-row">
	                                            <div class="input_btn">
	                                            	<span><input type="text" maxlength="5" lenCheck="5" name="critTm" value="${vo.critTm }" class="form-control" id="critTm"  onkeyup="isChkNumber(this)"/>
													<spring:message code="common.title.min"/></span>
	                                            </div>
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                            <li>
	                                <div class="row">
	                                    <label for="prgrChkYn" class="form-label col-sm-2"><spring:message code="course.title.course.ratio.progress.check"/></label>
	                                     <div class="col-sm-10">
	                                        <div class="form-row">
	                                            <span class="custom-input onlychk"> 
	                                            <input type="checkbox" name="prgrChkYn"  value="Y" id="prgrChkYn" <c:if test="${vo.prgrChkYn eq 'Y'}">checked</c:if>/>
	                                            <label for="prgrChkYn"></label></span>
	                                        </div>             
	                                    </div>
	                                </div>
	                            </li>
	                        </ul>
	                    </div>
	                	<input type="submit" value="submit" style="display:none" />
					</form>
                </div>
            </div>
        </div>
    </div>
									
								
							
<script type="text/javascript">
	var ItemDTO 	= new Object();
	var DragItem	= new Object();
	var modalBox 	= null;
	var fileTree	= null;

	//---- 페이지 초기화 함수
	$(document).ready(function() {
		//-- 기본 모달 박스 생성
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		modalBoxChild = new $M.ModalDialog({
			"modalid" : "modal2"
		});
		ItemDTO.sbjCd = "${vo.sbjCd}"; // 과목 코드 설정
		
		/* $('#tabMenuCnts a').click(function (e) {
			  $(this).tab('show');
		}); */
		var lvl1Num;
		var lvl2Num;
		tabBox(1);
		
		
		// 콘텐츠 타입 체크박스 활성화
		$("input:radio[name ='cntsTypeCd']:input[value='${vo.cntsTypeCd}']").trigger("click");
		
		// 코딩 실습인 경우 코딩과제 경로 추가
		<c:if test="${vo.cntsTypeCd eq 'CODING_T'}">
			$("#codingUrlTr").css("display", "");
		</c:if>
		
		// 메타버스 콘텐츠 활성화
		<c:if test="${vo.cntsTypeCd eq 'META'}">
			$("#metaTr").css("display", "");
			getMetaScenes('${vo.sceneId}');
		</c:if>
		
		
		
		
	});

	function modalBoxClose() {
		//parent.modalBox.clear();
		parent.modalBox.close();
	}
	
	function modalBoxChildClose() {
		modalBoxChild.close();
	}


	function tabBox(str) {
		var ajaxUrl = cUrl("/lec/cnts/leftWorkArea");
		var ajaxData;
		//-- Object 초기화
		fileTree	= null;
		if(str == '1') {
			ajaxData = {"workType":"CDN"};
		} else if(str =='2'){
			ajaxData = {"workType":"VOD"};
		}else if(str =='3'){
			ajaxData = {"workType":"MLINK"};
		}else if(str =='5'){	
			ajaxData = {"workType":"CODING_T" , "crsCreCd" : '${vo.crsCreCd}'};	
		}else {
			ajaxData = {"workType":"FILE"};
		}
		$('.active').removeClass('active');
		$('#tabBox'+str).addClass('active');
		$("#leftWorkArea").load(ajaxUrl, ajaxData);
	}
	
	//진도체크방법 설정
	function prgrChkTypeChk(){
		var chkVal = $('input[name="prgrChkType"]:checked').val();
		if(chkVal =="TIME"){
			$("input:radio[id=cntsTypeCd1]").attr("disabled",false);
			$("input:radio[id=cntsTypeCd2]").attr("disabled",false);
			$("input:radio[id=cntsTypeCd3]").attr("disabled",false);
			$("input:radio[id=cntsTypeCd4]").attr("disabled",true);
		}else if(chkVal =="PAGE"){
			$("input:radio[id=cntsTypeCd1]").attr("disabled",true);
			$("input:radio[id=cntsTypeCd2]").attr("disabled",true);
			$("input:radio[id=cntsTypeCd3]").attr("disabled",true);
			$("input:radio[id=cntsTypeCd4]").attr("disabled",false);
		}
		
	}
	
	//콘텐츠 타입 설정
	function cntsTypeChk(){
		var radioVal = $('input[name="cntsTypeCd"]:checked').val();
		var unitLvl = $("#contentsForm").find('input[name="unitLvl"]').val();
		unitCntsSelDispCtl(radioVal, unitLvl);
	}
	
	// 평가 유형 설정
	function unitCntsSelDispCtl(cntsTypeCd, unitLvl){
		
		// 강좌유형 상태값 
		switch(cntsTypeCd){
    	case "CDN" : 	
    		$("#cntsTypeSel").css("display", "");
    		$('#zoomHost').hide();
	   		$('#zoomPw').hide();
	   		$('#zoomRoomId').hide();
			$('#tabBox2').removeClass('active');
			$('#tabBox3').removeClass('active');
			$('#tabBox4').removeClass('active');
			$('#tabBox5').removeClass('active');
			$('#tabBox1').addClass('active');
			
			$("#metaTr").css("display", "none");
	   		$("#codingUrlTr").css("display", "none");
			
			tabBox(1);
    		break;
	   	case "VOD" :
			$("#cntsTypeSel").css("display", "");
			$('#zoomHost').hide();
	   		$('#zoomPw').hide();
	   		$('#zoomRoomId').hide();
			$('#tabBox1').removeClass('active');
			$('#tabBox3').removeClass('active');
			$('#tabBox4').removeClass('active');
			$('#tabBox5').removeClass('active');
			$('#tabBox2').addClass('active');
			
			$("#metaTr").css("display", "none");
	   		$("#codingUrlTr").css("display", "none");
			
			tabBox(2);
    		break;
		case "MLINK" :
			$("#cntsTypeSel").css("display", "");
			$('#zoomHost').hide();
	   		$('#zoomPw').hide();
	   		$('#zoomRoomId').hide();
			$('#tabBox1').removeClass('active');
			$('#tabBox2').removeClass('active');
			$('#tabBox4').removeClass('active');
			$('#tabBox5').removeClass('active');
			$('#tabBox3').addClass('active');
			
			$("#metaTr").css("display", "none");
	   		$("#codingUrlTr").css("display", "none");
			
			tabBox(3);
    		break;
	   	case "FILE" : 	
	   		$("#cntsTypeSel").css("display", "");
	   		$('#zoomHost').hide();
	   		$('#zoomPw').hide();
	   		$('#zoomRoomId').hide();
			$('#tabBox1').removeClass('active');
			$('#tabBox2').removeClass('active');
			$('#tabBox3').removeClass('active');
			$('#tabBox5').removeClass('active');
			$('#tabBox4').addClass('active');
			
			$("#metaTr").css("display", "none");
	   		$("#codingUrlTr").css("display", "none");
			
			tabBox(4);
    		break;
	   	case "CODING_T" : 	
	   		$('#tabBox1').removeClass('active');
			$('#tabBox2').removeClass('active');
			$('#tabBox3').removeClass('active');
			$('#tabBox4').removeClass('active');
			$('#tabBox5').addClass('active');
			
	   		$("#metaTr").css("display", "none");
	   		$("#codingUrlTr").css("display", "");
	   		
	   		tabBox(5);
    		break;	
	   	case "CODING_L" : 	
	   		$("#metaTr").css("display", "none");
	   		$("#codingUrlTr").css("display", "none");
	   		
    		break;	
	   	case "META" : 	
	   		$("#metaTr").css("display", "");
	   		$("#codingUrlTr").css("display", "none");
	   		
	   		getMetaScenes('');
    		break;	
		}
	}	
	
function getMetaScenes(sceneId){
		
		var addContent = "";
		var checked = "";
		
   		fetch('https://api.xrcloud.app/api/scenes?projectId=${xrcloud_project_id}', {
   			headers: {'X-XRCLOUD-API-KEY' : '${xrcloud_api_key}'}
   		})
   		.then(resp => resp.json())
   		.then(json => {
   			console.log(JSON.stringify(json))
   			var obj = JSON.stringify(json);
   			var data = JSON.parse(obj);
   		
   			if(data.items.length == 0){
   				alert("등록 된 Scene 정보가 없습니다.");
   			} else {
   				for(var i=0; i< data.items.length; i++){
   					var item =  data.items[i];
   					var checked = "";
   				
   					if(sceneId == item.id) checked = "checked"
   					 
                  
   					addContent += "<img src='"+item.thumbnailUrl+"' style='width:100px; height:60px'/><input type='radio' name='sceneId' id='"+item.id+"' "+checked+"  onclick='addMetaClass(\""+item.id+"\");' value='"+item.id+"'/><label for='"+item.id+"'></label>";
   				}
   				
   				$("#metaTd").html(addContent);
   			}
   			
   		})
   		.catch(error => {
		    alert('api 연동에 실패했습니다.');
		})
    		
	}
	
	
	function addMetaClass(id){
   		fetch("https://api.xrcloud.app/api/rooms",{
   			method : "POST",
   			headers: {'X-XRCLOUD-API-KEY' : '${xrcloud_api_key}' , "Content-Type": "application/json"},
   		   	body : JSON.stringify({
		   		   	projectId: "${xrcloud_project_id}",
		   			sceneId: id,
		   			name: "${osVO.sbjNm}_"+$("#unitNm").val(),
		   			size:1000,
		   			returnUrl:"${product_host_url}/lec/bookmark/listStdPrgrRatioDetailMain?sbjCd=${vo.sbjCd}"
   		        })
   		})
   		.then(resp => resp.json())
   		.then(json => {
   			var obj = JSON.stringify(json);
   			console.log(obj);
   			var data = JSON.parse(obj);
   			
   			$("#unitFilePath").val(data.roomUrl.private.guest);
   			$("#roomId").val(data.id);
   			
   		})
   		.catch(error => {
		    alert('api 연동에 실패했습니다.');
		})
	}
	

	function prgrTypeChk(str) {
		var f = document.contentsForm;
		if(str!="TIME")	f["critTm"].disabled = true;
		else f["critTm"].disabled = false;
	}

	function contentsEdit() {
		var sbjCd = '${vo.sbjCd}';
		if(!validate(document.contentsForm)) return;
		$('#contentsForm').attr("action","/lec/cnts/editCreateContents");
		$('#contentsForm').ajaxSubmit(function (resultDTO) {
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				parent.modalBox.close();
				parent.onclickTab(sbjCd);
			}
		});
	}

	function closeContentsEditForm() {
		$("#contentsForm").find('input[name="unitNm"]').val("");
		$("#contentsForm").find('input[name="asmtSn"]').val("");
		$("#contentsForm").find('input[name="unitFilePath"]').val("");
		$("#contentsForm").find('input[name="mobileFilePath"]').val("");
		$("#contentsForm").find('input[name="atchFilePath"]').val("");
		$("#contentsWriteArea").hide();
		$('#contentsList').css('height', '690px');
	}

	function contentsMove(str) {
		var f = document.contentsForm;
		f["sbjCd"].value = ItemDTO.sbjCd;
		f["unitCd"].value = ItemDTO.unitCd;
		f["parUnitCd"].value = (ItemDTO.parUnitCd == undefined) ? '':ItemDTO.parUnitCd;
		f["unitOdr"].value = ItemDTO.unitOdr;
		f["moveType"].value = str;

		process("moveContents");
	}

	//-- 콘텐츠 수정창의 입력창 텍스트 삭제
	function delPath(pathType) {
		var f = document.contentsForm;
		if(pathType == 'unitFilePath') {
			f["unitFilePath"].value = '';
		} else if(pathType == 'atchFilePath') {
			f["atchFilePath"].value = '';
		} else if(pathType == 'mobileFilePath') {
			f["mobileFilePath"].value = '';
		} else if(pathType == 'zoomUrl') {
			f["zoomUrl"].value = '';
		} else if(pathType == 'asmtSn') {
			f["asmtSn"].value = '';
		}	
	}

	function cntsPreview(obj) {
		var cntsTypeCd = obj.attr("cntsTypeCd");
		if((cntsTypeCd == "OLCCNTS")) {
			var unitFilePath = obj.attr("unitFilePath");
			var url = generateUrl("/lec/cnts/previewOlcMain",{"cntsCd":unitFilePath});
			var winOption = "width=1100, height=770, top=10, left=10, scrollbars=0";
			var previewWin = window.open(url, "previewWin", winOption);
			previewWin.focus();
		} else if((cntsTypeCd == "MEDIA")) {
			var unitFilePath = obj.attr("unitFilePath");
			var url = generateUrl("/lec/cnts/previewMediaPop",{"cntsCd":unitFilePath});
			var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
			var contentsWin = window.open(url, "contentsWin", winOption);
			contentsWin.focus();
		} else {
			var unitCd = obj.attr("uintCd");
			var url = generateUrl("/lec/cnts/viewCntsMain",{"sbjCd":ItemDTO.sbjCd,"unitCd":unitCd});
			var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=auto,resizable=yes,width=1100,height=700";
			var contentsWin = window.open(url, "contentsWin", winOption);
			contentsWin.focus();
		}
	}
	
	
	
	/**
	 * 교제 단원 목차 등록
	 */
	function cntsWrite(dept,unitCd) {
		var sbjCd = '${vo.sbjCd}';
		$("#contentsForm").find('input[name="sbjCd"]').val(sbjCd);
		$("#contentsForm").find('input[name="unitCd"]').val("");
		$("#contentsForm").find('input[name="unitType"]').val("L");
		
		if(dept == 1){
			$("#contentsForm").find('input[name="parUnitCd"]').val("");
			$("#contentsForm").find('input[name="unitLvl"]').val(dept);
			$("#contentsForm").find('input[name="unitNm"]').val("차시");
			$("#unitLvNm").text('차시명');
		}else{
			$("#contentsForm").find('input[name="parUnitCd"]').val(unitCd);
			$("#contentsForm").find('input[name="unitLvl"]').val(dept);
			$("#contentsForm").find('input[name="unitNm"]').val("페이지");
			$("#unitLvNm").text('페이지명');
			$("#prgrChkTypeTr").hide();
			$("#prgrChkYnTd").hide();
		}
		
		$("#metaTd").html('');
		$("#codingUrlTr").css("display", "none");
		$("#metaTr").css("display", "none");
		
		$("#contentsForm").find('input[name="unitFilePath"]').val("");
		$("#contentsForm").find('input[name="mobileFilePath"]').val("");
		$("#contentsForm").find('input[name="atchFilePath"]').val("");
		//$("#contentsForm").find('input[name="cntsTypeCd"]').val("");
		$("#contentsForm").find('input[id="cntsTypeCd1"]').attr("checked", false);
		$("#contentsForm").find('input[id="cntsTypeCd2"]').attr("checked", false);
		$("#contentsForm").find('input[id="cntsTypeCd3"]').attr("checked", false);
		$("#contentsForm").find('input[id="cntsTypeCd4"]').attr("checked", false);
		$("#contentsForm").find('input[id="cntsTypeCd5"]').attr("checked", false);
		$("#contentsForm").find('input[id="cntsTypeCd6"]').attr("checked", false);
		$("#contentsForm").find('input[id="prgrChkTypeTIME"]').attr("checked", false);
		$("#contentsForm").find('input[id="prgrChkTypePAGE"]').attr("checked", false);
		$("#contentsForm").find('input[name="prgrChkYn"]').prop("checked", false);

		$.ajaxSetup({async: false});
		$('#contentsForm').attr("action","/lec/cnts/addContents");
		$('#contentsForm').ajaxSubmit(function (resultDTO) {
			var item = resultDTO.returnVO;
			if(resultDTO.result >= 0) {
				contentsList();
				cntsEditForm(item.unitCd);
				prgrChkTypeChk();
			} else {

			}
		});
        $.ajaxSetup({async: true});		
	}
	
	
	function creCntsRemove() {
		var sbjCd = '${vo.sbjCd}';
		$("#contentsForm").find('input[name="crsCreCd"]').val();
		$("#contentsForm").find('input[name="sbjCd"]').val(ItemDTO.sbjCd);
		$("#contentsForm").find('input[name="unitCd"]').val();
		$("#contentsForm").find('input[name="parUnitCd"]').val();
		$("#contentsForm").find('input[name="unitOdr"]').val();
		if($("#contentsForm").find('input[name="bookmarkCnt"]').val() > 0 ){
			alert("<spring:message code="course.message.contents.alert.delete"/>");
			return;
		}
		//-- 선택된 단원의 하위 단원을 추가 등록함.
		if($("#contentsForm").find('input[name="subCnt"]').val() > 0 ) {
			if(!confirm("<spring:message code="course.message.contents.confirm.delete.sub"/>")) {
				return;
			}
		}
		$('#contentsForm').attr("action","/lec/cnts/deleteCreateContents");
		$('#contentsForm').ajaxSubmit(function (resultDTO) {
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				parent.modalBox.close();
				parent.onclickTab(sbjCd);
			}
		});
	}
	
	
	
</script>
