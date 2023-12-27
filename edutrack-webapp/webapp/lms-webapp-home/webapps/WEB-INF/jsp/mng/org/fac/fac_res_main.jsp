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
							<li><a href="/mng/org/fac/listFacMain">시설 관리</a></li>
					  		<li class="active"><a href="/mng/org/fac/listResMain">예약 접수 관리</a></li>							
						</ul>
					</div>					
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-7 col-xs-12 mb5">
							<select name="facCd" id="facCd" class="form-control input-sm" style="float:left" onchange="listPageing(1);">
								<option value="">전체시설</option>
								<c:forEach var="item" items="${faclist}">
									<option value="${item.facCd}" <c:if test="${item.facCd eq vo.facCd}">selected="selected"</c:if>>${item.facNm}</option>
								</c:forEach>
							</select>
							
							<select name="resSts" id="resSts" class="form-control input-sm" style="float:left" onchange="listPageing(1);">
								<option value=""><spring:message code="user.title.userinfo.stats.all"/></option>
								<c:forEach var="item" items="${stsCodeList}">
									<option value="${item.codeCd}">${item.codeNm}</option>
								</c:forEach>
							</select>

							<div>
								<div class="input-group" style="float:left;width:128px;">
									<input type="text" maxlength="100" dispname="시작일" isnull="N" lencheck="100" id="workStartDate" name="workStartDate" value="${vo.workStartDate}" class="inputDate form-control input-sm">
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('workStartDate')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<div class="input-group" style="float:left;width:128px;">
									<input type="text" maxlength="100" dispname="종료일" isnull="N" lencheck="100" id="workEndDate" name="workEndDate" value="${vo.workEndDate}" class="inputDate form-control input-sm">
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('workEndDate')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
							</div>
							<div class="input-group" style="width:200px;float:left">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
							<div name="totCnt" id="totCnt" class="input-group" style="float:left">
								
							</div>
						</div>
						<div class="col-md-5 col-xs-12 mb5">
							<div class="pull-right">
								<a class="btn btn-success btn-sm mr10" href="javascript:resExcelDownload();" ><i class="fa fa-file-excel-o fa-fw"></i>엑셀 다운로드</a>
								<a class="btn btn-primary btn-sm mr10" href="javascript:notiRecvPop();" >예약 신청 알림 관리</a>
								<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm" style="width:60px;float:right">
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
	
	<form id="facResExcelForm" name="facResExcelForm">
		<input type="hidden" name="facCd">
		<input type="hidden" name="workStartDate">
		<input type="hidden" name="workEndDate">
		<input type="hidden" name="resSts">
		<input type="hidden" name="searchValue">
	</form>
<meditag:datepicker name1="workStartDate" name2="workEndDate" />
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

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listPageing(1);
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
	function listPageing(page) {
		$("#loadingBar").show();
		var facCd = $("#facCd option:selected").val();
		var searchValue = $('#searchValue').val();
		var workStartDate = $("#workStartDate").val();
		if(workStartDate!=null) {
			workStartDate = this.replaceAll(workStartDate, "\\.", "");
		}
		var workEndDate = $("#workEndDate").val();
		if(workEndDate!=null) {
			workEndDate = this.replaceAll(workEndDate, "\\.", "");
		}
		var resSts = $("#resSts option:selected").val();
		var listScale = $("#listScale option:selected").val();
		
		//alert("page["+page+"] workStartDate["+workStartDate+"] workEndDate["+workEndDate+"] searchValue["+searchValue+"]");
		
		$('#facList')
			.load(
				cUrl("/mng/org/fac/listRes"),
				{ 
					"facCd": facCd,
				  	"searchValue":searchValue,
				  	"workStartDate":workStartDate,
				  	"workEndDate":workEndDate,
				  	"resSts":resSts,
				  	"sortKey":ItemDTO.sortKey,
				  	"curPage":page,
				  	"listScale":listScale
				},
				function () {$("#loadingBar").hide();}
			);
	}

	function replaceAll(str, searchStr, replaceStr) {
		  return str.split(searchStr).join(replaceStr);
	}
	
	function viewPopup(resCd) {
		var url = "/mng/org/fac/viewResInfoPop?resCd="+resCd;
		var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(880, 750);
		modalBox.setTitle("예약 상세 조회");
		modalBox.show();
	}
	
	function notiRecvPop() {
		var url = "/mng/org/noti/recv/recvPop?notiCtgr=FAC";
		var addContent = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("예약 알림 메일 관리");
		modalBox.show();
	}
	
	function resExcelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}

		var url = "/mng/org/fac/resExcelDownload";
		
		var facCd = $('#facCd').val();
		var workStartDate = $("#workStartDate").val();
		var workEndDate = $("#workEndDate").val();
		var resSts = $("#resSts option:selected").val();
		var searchValue = $('#searchValue').val();
		
		// 폼에 action을 설정하고 submit시킨다.
		$("#facResExcelForm").attr('target', '_m_download_iframe');
		$("#facResExcelForm").find('input[name=facCd]').val(facCd);
		$("#facResExcelForm").find('input[name=workStartDate]').val(workStartDate);
		$("#facResExcelForm").find('input[name=workEndDate]').val(workEndDate);
		$("#facResExcelForm").find('input[name=resSts]').val(resSts);
		$("#facResExcelForm").find('input[name=searchValue]').val(searchValue);
		$("#facResExcelForm").attr("action",url);
		$("#facResExcelForm").submit();
		$("#facResExcelForm").removeAttr('target');
	}
</script>
