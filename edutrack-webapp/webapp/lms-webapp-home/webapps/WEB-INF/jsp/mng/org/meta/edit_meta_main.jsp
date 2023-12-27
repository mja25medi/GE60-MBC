<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<form onsubmit="return false" id="orgInfoForm" name="orgInfoForm">
						<input type="hidden" name="roomUrl" id="roomUrl"/>
						<div class="col-md-12">
						</div>
						<div class="col-md-12" style="margin-top:5px;padding-top:5px;" id="sceneList">
							<div class="row" id="sceneRow">
									<%-- <div class="col-sm-6 col-md-3">
				 						<div class="thumbnail" style="height:200px;">
				 							<img src="/app/file/view2/${item.bnnrImgSn}" style="width:100%;height:110px;"/>
				    						<div class="caption">
				      							<h5>
				      								<c:if test="${item.openYn eq 'Y'}"><i class="glyphicon glyphicon-ok-circle" style="color:skyblue"></i></c:if>
				      								<c:if test="${item.openYn ne 'Y'}"><i class="glyphicon glyphicon-ban-circle" style="color:orange"></i></c:if>
				      								${item.bnnrNm}
				      							</h5>
				      							<p>
				      								<a href="javascript:bnnrEdit('${item.bnnrSn}')" ><i class="fa fa-cog"></i></a>
				      							</p>
				    						</div>
				 						</div>
									</div> --%>
								<div class="col-md-12">
									<div class="well" id="message">
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>		

<script type="text/javascript">
	var modalBox = null;
	var contractFile;
	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		getMetaScenes('${vo.sceneId}');
	});
	
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
   				$("#message").html("등록 된 Scene 정보가 없습니다.");
   			} else {
   				for(var i=0; i< data.items.length; i++){
   					var item =  data.items[i];
   					var checked = "";
   				
   					if(sceneId == item.id) checked = "checked"
   					 
   					addContent += "<div class='col-sm-6 col-md-3'>";
   					addContent += "<div class='thumbnail' style='height:205px;'>";
   					addContent += "<img src='"+item.thumbnailUrl+"' style='width:440px; height:198px'/><p style='text-align: center; margin-top:5px;'><input type='radio' name='sceneId' id='"+item.id+"' "+checked+"  onclick='addMetaClass(\""+item.id+"\");' value='"+item.id+"'/></p><label for='"+item.id+"'></label>";
   					addContent += "</div>";	
   					addContent += "</div>";	
   				}
   				
   				$("#sceneRow").html(addContent);
   			}
   			
   		})
   		.catch(error => {
		    $("#message").html("api 연동에 실패했습니다.");
		})
    		
	}
	
	function addMetaClass(id){
   		fetch("https://api.xrcloud.app/api/rooms",{
   			method : "POST",
   			headers: {'X-XRCLOUD-API-KEY' : '${xrcloud_api_key}' , "Content-Type": "application/json"},
   		   	body : JSON.stringify({
		   		   	projectId: "${xrcloud_project_id}",
		   			sceneId: id,
		   			//name: "${vo.orgCd}_${vo.orgNm}",
		   			name: "${vo.orgNm}",
		   			size:1000,
		   			returnUrl:"${product_host_url}"
   		        })
   		})
   		.then(resp => resp.json())
   		.then(json => {
   			var obj = JSON.stringify(json);
   			console.log(obj);
   			var data = JSON.parse(obj);
   			$("#roomUrl").val(data.roomUrl.public.guest);
   			
   		})
   		.catch(error => {
   		 	alert("api 연동에 실패했습니다.");
		})
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
		} else {
			// 비정상 처리
		}
	}
</script>
