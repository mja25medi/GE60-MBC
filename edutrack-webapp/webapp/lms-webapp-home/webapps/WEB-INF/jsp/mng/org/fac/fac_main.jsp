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
							<li class="active"><a href="/mng/org/fac/listFacMain">시설 관리</a></li>
					  		<li><a href="/mng/org/fac/listResMain">예약 접수 관리</a></li>							
						</ul>
					</div>
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-7 col-xs-12 mb5">
							<select name="facCtgrCd" id="facCtgrCd" onchange="listPageing(1);" class="form-control input-sm mr5" style="width:200px;float:left;">
								<option value="">전체</option>
								<c:forEach var="item" items="${ctgrList }">
									<option value="${item.facCtgrCd }">${item.facCtgrNm }</option>
								</c:forEach>
							</select>
							<div class="input-group mr5" style="width:200px;float:left">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
							<button class="btn btn-primary btn-sm" id="save0" name="save0" onclick="JavaScript:itemUse('Y');" ><spring:message code="null" text="사용"/></button>
							<button class="btn btn-primary btn-sm" id="save1" name="save1" onclick="JavaScript:itemUse('N');" ><spring:message code="null" text="사용안함"/></button>
							<div type="text" name="totCnt" id="totCnt" class="input-group" style="float:left">
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-5 col-xs-12 mb5">
							<div class="pull-right">
								<%-- TODO 시설 분류 관리 --%>
								<a class="btn btn-primary btn-sm mr5" href="javascript:mngFacCtgrForm()" >시설 분류 관리</a>
								<a class="btn btn-primary btn-sm mr5" id="addFac" name="addFac" href="javascript:addFacForm();" ><spring:message code="null" text="시설물등록"/></a>
								<a class="btn btn-primary btn-sm mr5" href="javascript:printQR('fac')" >QR코드 출력</a>
								<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm" style="width:60px;float:right;">
									<option value="10">10</option>
									<option value="20" selected="selected">20</option>
									<option value="40">40</option>
									<option value="60">60</option>
									<option value="80">80</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
						<div class="col-md-12" id="facList"></div>
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
		
		ItemDTO.sortKey = "USER_ID_ASC";
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
				listPageing(1);
			}
		}
		
		listPageing(1);
	});

	function itemUse(tmp) {
		var str = "";
		var i = 0;
		if($('input:checkbox[name="chk_"]:checked').length == 0) {
			alert("선택된 시설이 없습니다.");
			return;
		}
		$('input:checkbox[name="chk_"]').each(function() {
		    if(this.checked) {
		    	if(i==0) {
		    		str = str + "" + this.value + "";
		    	} else {
		    		str = str + "," + this.value + "";
		    	}
		        i++;
		    }
		});
		
    	$.ajax({
			url : "/mng/org/fac/itemUpdate",
			data :{
				"facCdArray":str,
			  	"useYn":tmp
			},
			dataType : "json",
			success:function(result) {
				alert(result.message);
				listPageing(1);
			}
		});
	}
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listPageing(1);
	}

	
	/**
	 *  페이징처리
	 */
	 function listPageing(page) {
		$("#loadingBar").show();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();
		var facCtgrCd = $("#facCtgrCd").val();
		$("#facList").load(
			cUrl("/mng/org/fac/listFac"),
			{
				"searchValue":searchValue,
			  	"sortKey":ItemDTO.sortKey,
			  	"curPage":page,
			  	"facCtgrCd" : facCtgrCd,
			  	"listScale":listScale
			},
			function () {
				$("#loadingBar").hide();
			});
	}

	function replaceAll(str, searchStr, replaceStr) {
		  return str.split(searchStr).join(replaceStr);
	}
	
	function viewPopup(no,title) {
		var url = "/mng/org/fac/editFacFormPop?facCd="+no;
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 700);
		modalBox.setTitle(title);
		modalBox.show();
	}
	
	function addFacForm(){
		var url = "/mng/org/fac/addFacFormPop";
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 700);
		modalBox.setTitle("시설물 등록");
		modalBox.show();
	}
	
	function mngFacCtgrForm() {
		var url = "/mng/org/fac/mngFacCtgrFormPop";
		var addContent = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("시설 분류 관리");
		modalBox.show();
	}
	
	//QR코드 출력
	function printQR() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_pdf_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		var url = cUrl("mng/org/fac/printQR");
		$("#_m_pdf_iframe").attr("src",url);
	}
	
	function sortFac(facCd, facOdr) {
		$.post(cUrl( "/mng/org/fac/sortFacOdr"),
				{ "facCd": facCd,
				  "facOdr" : facOdr },
				function(data) {
					if(data.result != 1) {
		 	  			alert(data.message);
		 	  			listFac(1);
		 	  		}
				}
			);
	} 
</script>