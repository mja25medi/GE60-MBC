<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content box">
	<div class="overlay">
		<i class="fa fa-refresh fa-spin"></i>
	</div>
	<div class="row" id="content">
		<form id="etcApiSyncForm" name="etcApiSyncForm" onsubmit="return false" >
			<input type="hidden" id="searchFrom" name="searchFrom" value="${searchFrom}" />
			<input type="hidden" id="searchTo" name="searchTo" value="${searchTo}" />
			<input type="hidden" id="syncStatus" name="syncStatus" value="${vo.syncStatus}" />
			<div class="col-md-12 col-xs-12 mb5">
				<div class="pull-left" style="margin-top: 12px;">
					<div class="input-group" style="float:left;width:130px;margin-top: -12px;">
						<input type="text" name="userLoginStartDt" id="userLoginStartDt" value="${searchFrom}" class="form-control input-sm" readonly/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('userLoginStartDt')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<div class="input-group" style="float:left;margin-left: 10px;margin-top:-8px;">~
					</div>
					<div class="input-group" style="float:left;width:130px;margin-left:10px; margin-right: 10px;margin-top: -12px;">
						<input type="text" name="userLoginEndDt" id="userLoginEndDt" value="${searchTo}" class="form-control input-sm" readonly/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('userLoginEndDt')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="userLoginStartDt" name2="userLoginEndDt" />
				</div>
				<div class="form-group" style="width:180px; float: left;">
					<select id="searchKey" name="searchKey" class="form-control input-sm" >
						<option value="">상위분류선택</option>
						<option value="pk">PK</option>
						<option value="pkNm">PK명</option>
					</select>
				</div>
				<div class="input-group" style="width:180px; float: left;">
					<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="전체">
					<span class="input-group-addon" onclick="listUserLoginApiSync(1)" style="cursor:pointer">
						<i class="fa fa-search"></i>
					</span>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="col-md-12" style="margin-top:5px;">
				<div id="listUserLoginApiSync">
				</div>
				<div class="text-right">
					<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
				</div>
			</div>
		</form>
	</div>
</section>

<script type="text/javascript">
	$(document).ready(function() {
		listUserLoginApiSync();
		$(".overlay").hide();
		
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listUserLoginApiSync();
			}
		});
	});

	/**
	 * 실패 데이터 조회
	 */
	function listUserLoginApiSync(page) {
		var searchKey = $('#searchKey').val();
		var searchValue = $('#searchValue').val();
		var searchFrom = $('#userLoginStartDt').val();
		var searchTo = $('#userLoginEndDt').val();
		//var listScale = $("#listScale option:selected").val();
		var syncStatus = $("#syncStatus").val();

		$('#listUserLoginApiSync')
			.load(cUrl("/mng/etc/HrdApi/listUserLoginApiSync"),
				{ 
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "syncStatus":syncStatus,
				  "curPage":page,
				  //"listScale":listScale,
				  "searchFrom":searchFrom,
				  "searchTo":searchTo
				 }
			);

	}
</script>
