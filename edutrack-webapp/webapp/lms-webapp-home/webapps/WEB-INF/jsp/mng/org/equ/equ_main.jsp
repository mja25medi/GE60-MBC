<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/page_init.jsp" %>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<div class="col-md-12 col-xs-18" style="margin-bottom:10px;">
							<ul class="nav nav-tabs" id="tabMenu">
								<li class="active"><a href="/mng/org/equ/listEquMain">장비 관리</a></li>
						  		<li><a href="/mng/org/equ/listRentMain">예약 접수 관리</a></li>							
							</ul>
						</div>
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-12 col-xs-12 mb5">
						</div>
						<div class="col-md-12 col-xs-12 mb5">
							<b id="equCntInfo">장비목록(0)</b>
							<button class="btn btn-primary btn-sm" id="save0" name="save0" onclick="JavaScript:itemUse('Y');" ><spring:message code="null" text="사용"/></button>
							<button class="btn btn-primary btn-sm" id="save1" name="save1" onclick="JavaScript:itemUse('N');" ><spring:message code="null" text="사용안함"/></button>		
							<div class="pull-right">
								<div class="input-group mr5" style="width:300px; float:left">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="장비명을 입력하세요."/>
									<span class="input-group-addon" onclick="listEqu(1)" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>							
								<%-- TODO 시설 분류 관리 --%>
								<a class="btn btn-primary btn-sm mr5" id="addEqu" name="addEqu" href="javascript:addEquForm();" ><spring:message code="null" text="장비등록"/></a>
								<a class="btn btn-primary btn-sm mr5" href="javascript:printQR()" >QR코드 출력</a>
								<select name="listScale" id="listScale" onchange="listEqu(1);" class="form-control input-sm" style="width:60px;float:right;">
									<option value="10">10</option>
									<option value="20" selected="selected">20</option>
									<option value="40">40</option>
									<option value="60">60</option>
									<option value="80">80</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
						</div>
						<div class="col-md-12" id="equList"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<script type="text/javascript">
	var modalBox = null;
	var modalBox2 = null;
	var ItemDTO = new Object();
	/**
	 * 초기 화면 구성
	 */
	$(document).ready(function() {
		
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		modalBox2 = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listEqu(1);
			}
		}
		
		listEqu(1);
	});

	function itemUse(tmp) {
		var str = "";
		var i = 0;
		if($('input:checkbox[name="chk_"]:checked').length == 0) {
			alert("선택된 장비가 없습니다.");
			return;
		}
		$('input:checkbox[name="chk_"]:checked').each(function() {
	    	if(i==0) {
	    		str = str + "" + this.value + "";
	    	} else {
	    		str = str + "," + this.value + "";
	    	}
	        i++;
		});
		
    	$.ajax({
			url : "/mng/org/equ/equUseUpdate",
			data :{
				"equCdArray":str,
			  	"useYn":tmp
			},
			dataType : "json",
			success:function(result) {
				alert(result.message);
				listEqu(1);
			}
		});
	}
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 *  페이징처리
	 */
	 function listEqu(page) {
		$("#loadingBar").show();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();
		$("#equList").load(
			cUrl("/mng/org/equ/listEqu"),
			{
				"searchValue":searchValue,
			  	"curPage":page,
			  	"listScale":listScale
			},
			function () {
				$("#loadingBar").hide();
			});
	}

	function viewEquEditForm(equCd) {
		var url = "/mng/org/equ/editEquFormPop?equCd="+equCd;
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("장비 정보 수정");
		modalBox.show();
	}
	
	function addEquForm(){
		var url = "/mng/org/equ/addEquFormPop";
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("장비 정보 등록");
		modalBox.show();
	}
	
	function listItemMain(equCd){
		document.location.href = "/mng/org/equ/listItemMain?equCd=" + equCd;
	}
	
	//QR코드 출력
	function printQR() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_pdf_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		var url = cUrl("mng/org/equ/printQR");
		$("#_m_pdf_iframe").attr("src",url);
	}
	
	function sortEqu(equCd, equOdr) {
		$.post(cUrl( "/mng/org/equ/sortEquOdr"),
				{ "equCd": equCd,
				  "equOdr" : equOdr },
				function(data) {
					if(data.result != 1) {
		 	  			alert(data.message);
						listEqu(1);
		 	  		}
				}
			);
	} 
</script>