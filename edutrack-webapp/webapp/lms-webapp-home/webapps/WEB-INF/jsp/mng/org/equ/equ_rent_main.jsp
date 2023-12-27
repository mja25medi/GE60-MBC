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
							<li><a href="/mng/org/equ/listEquMain">장비 관리</a></li>
					  		<li class="active"><a href="/mng/org/equ/listRentMain">예약 접수 관리</a></li>							
						</ul>
					</div>					
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-12 col-xs-12 mb5">

							<select name="rentSts" id="rentSts" class="form-control input-sm" style="float:left" onchange="listRent(1);">
								<option value=""><spring:message code="user.title.userinfo.stats.all"/></option>
								<c:forEach var="item" items="${rentStsCdList}">
									<option value="${item.codeCd}">${item.codeNm}</option>
								</c:forEach>
							</select>

							<div>
								<div class="input-group" style="float:left;width:128px;">
									<input type="text" maxlength="100" dispname="시작일" isnull="N" lencheck="100" id="rentStartDttm" name="rentStartDttm" value="${startDt}" onchange="listRent(1)" class="inputDate form-control input-sm">
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('rentStartDttm')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<div class="input-group" style="float:left;width:128px;">
									<input type="text" maxlength="100" dispname="종료일" isnull="N" lencheck="100" id="rentEndDttm" name="rentEndDttm" value="${endDt}" onchange="listRent(1)" class="inputDate form-control input-sm">
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('rentEndDttm')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<div class="input-group" style="width:250px;float:left">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="신청자명을 입력하세요."/>
									<span class="input-group-addon" onclick="listRent(1)" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>								
							</div>
							
							<div class="pull-right">
								<a class="btn btn-success btn-sm mr10" href="javascript:rentExcelDownload();" ><i class="fa fa-file-excel-o fa-fw"></i>엑셀 다운로드</a>
								<a class="btn btn-primary btn-sm mr10" href="javascript:notiRecvPop();" >예약 신청 알림 관리</a>
								<select name="listScale" id="listScale" onchange="listRent(1);" class="form-control input-sm" style="width:60px;float:right">
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
						</form>
						<div class="col-md-12 mt10" id="rentList"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<form id="equRentExcelForm" name="equRentExcelForm">
		<input type="hidden" name="rentStartDttm">
		<input type="hidden" name="rentEndDttm">
		<input type="hidden" name="rentSts">
		<input type="hidden" name="searchValue">
	</form>
<meditag:datepicker name1="rentStartDttm" name2="rentEndDttm" />
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
				listRent(1);
			}
		}
		listRent(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listRent(1);
	}

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$("#Search input[name='sel']").prop({checked:true});
	    }else{
	    	$("#Search input[name='sel']").prop({checked:false});
	    }
	}

	/**
	 *  페이징처리
	 */
	function listRent(page) {
		$("#loadingBar").show();
		var searchValue = $('#searchValue').val();
		var rentStartDttm = $("#rentStartDttm").val();
		var rentEndDttm = $("#rentEndDttm").val();

		var rentSts = $("#rentSts option:selected").val();
		var listScale = $("#listScale option:selected").val();
		
		$('#rentList')
			.load(
				cUrl("/mng/org/equ/listRent"),
				{ 
				  	"searchValue":searchValue,
				  	"rentStartDttm":rentStartDttm,
				  	"rentEndDttm":rentEndDttm,
				  	"rentSts":rentSts,
				  	"curPage":page,
				  	"listScale":listScale
				},
				function () {$("#loadingBar").hide();}
			);
	}

	function replaceAll(str, searchStr, replaceStr) {
		  return str.split(searchStr).join(replaceStr);
	}
	
	function viewRentInfoPop(rentCd) {
		var url = "/mng/org/equ/viewRentInfoPop?rentCd="+rentCd;
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(880, 750);
		modalBox.setTitle("예약 상세 조회");
		modalBox.show();
	}
	
	function notiRecvPop() {
		var url = "/mng/org/noti/recv/recvPop?notiCtgr=EQU";
		var addContent = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("예약 알림 메일 관리");
		modalBox.show();
	}
	
	function rentExcelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}

		var url = "/mng/org/equ/rentExcelDownload";
		
		var searchValue = $('#searchValue').val();
		var rentStartDttm = $("#rentStartDttm").val();
		var rentEndDttm = $("#rentEndDttm").val();
		var rentSts = $("#rentSts option:selected").val();
		
		// 폼에 action을 설정하고 submit시킨다.
		$("#equRentExcelForm").attr('target', '_m_download_iframe');
		$("#equRentExcelForm").find('input[name=rentStartDttm]').val(rentStartDttm);
		$("#equRentExcelForm").find('input[name=rentEndDttm]').val(rentEndDttm);
		$("#equRentExcelForm").find('input[name=rentSts]').val(rentSts);
		$("#equRentExcelForm").find('input[name=searchValue]').val(searchValue);
		$("#equRentExcelForm").attr("action",url);
		$("#equRentExcelForm").submit();
		$("#equRentExcelForm").removeAttr('target');
	}
</script>
