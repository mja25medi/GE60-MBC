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
						
						<div class="col-md-12 col-xs-12 mb5">
							<div class="input-group mr5" >
								<table style="width:100%" summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak mb0">
									<colgroup>
										<col style="width:100px"/>
										<col style="width:auto;"/>
									</colgroup>
									<tr>
										<th scope="row">장비명</th>
										<td>${equInfo.equNm }</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 mb5">
							<b>상품목록</b>
							<button class="btn btn-primary btn-sm" id="save0" name="save0" onclick="JavaScript:itemUse('Y');" ><spring:message code="null" text="사용"/></button>
							<button class="btn btn-primary btn-sm" id="save1" name="save1" onclick="JavaScript:itemUse('N');" ><spring:message code="null" text="사용안함"/></button>
							<div class="pull-right">
								<a href="javascript:viewAddItemExcelPop('${equInfo.equCd}');" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> 엑셀 일괄 등록</a>
								<button class="btn btn-primary btn-sm" onclick="addItemForm('${equInfo.equCd}')" ><spring:message code="null" text="상품등록"/></button>
							</div>
						</div>
						<div class="col-md-12" id="itemList"></div>
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
				listItem(1);
			}
		}
		listItem(1);
	});

	function itemUse(tmp) {
		var str = "";
		var i = 0;
		if($('input:checkbox[name="chk_"]:checked').length == 0) {
			alert("선택된 상품이 없습니다.");
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
			url : "/mng/org/equ/equItemUseUpdate",
			data :{
				"itemCdArray": str,
			  	"useYn":tmp
			},
			dataType : "json",
			success: function(data) {
				alert(data.message);
				listItem(1);
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
	 function listItem(page) {
		
		var equCd = '${equInfo.equCd}';
		
		$("#loadingBar").show();
		$("#itemList").load(
			cUrl("/mng/org/equ/listItem"),
			{
				"equCd" : equCd,
			  	"curPage": page
			},
			function () {
				$("#loadingBar").hide();
			});
	}
	
	function viewItemEditForm(itemCd) {
		var url = "/mng/org/equ/editItemFormPop?itemCd="+itemCd;
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("상품 정보 수정");
		modalBox.show();
	}
	
	function addItemForm(equCd){
		var url = "/mng/org/equ/addItemFormPop?equCd=" + equCd;
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("상품 정보 등록");
		modalBox.show();
	}
	
	function viewAddItemExcelPop(equCd) {
		var url = generateUrl("/mng/org/equ/addItemExcelPop?equCd=" + equCd);
		var addContent  = "<iframe id='addItemFrame' name='addItemFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("엑셀 일괄 등록");
		modalBox.show();
	}
</script>