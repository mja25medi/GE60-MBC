<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<form id="apiLogForm" name="apiLogForm" onsubmit="return false" >
			<input type="hidden" id="crsCd" name="crsCd" value="${crsCd }" />
			<input type="hidden" id="syncGubunCd" name="syncGubunCd" value="${vo.syncGubunCd }" />
			<div class="col-md-12 col-xs-12 mb5">
				<div class="form-group" style="width:180px; float: left;">
					<select id="searchKey" name="searchKey" class="form-control input-sm" onchange="listApiLog();" >
						<option value="">상태선택</option>
						<option value="Success">성공</option>
						<option value="Fail">실패</option>
					</select>
				</div>
				<!-- <div class="input-group" style="width:180px; float: left;">
					<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="전체">
					<span class="input-group-addon" onclick="listApiLog(1)" style="cursor:pointer">
						<i class="fa fa-search"></i>
					</span>
				</div> -->
				<div class="pull-right" style="margin-top: 3px;">
					<ul style="list-style-type: none;margin-left:-145px;">
						<li style="float:left;margin-right:10px;">
							전체<input type="radio" name="searchType" value="all" style="border:0" checked="checked">
						</li>
						<li style="float:left;margin-right:10px;">
							기간<input type="radio" name="searchType" value="date" style="border:0">
						</li>
					</ul>
					<div class="input-group" style="float:left;width:130px;margin-top: -12px;">
						<input type="text" name="logStartDt" id="logStartDt" value="${searchFrom}" class="form-control input-sm" readonly/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('logStartDt')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<div class="input-group" style="float:left;margin-left: 10px;margin-top:-8px;">~
					</div>
					<div class="input-group" style="float:left;width:130px;margin-left:10px; margin-right: 10px;margin-top: -12px;">
						<input type="text" name="logEndDt" id="logEndDt" value="${searchTo}" class="form-control input-sm" readonly/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('logEndDt')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="logStartDt" name2="logEndDt" />
					<div class="input-group"  style="float:left;margin-left: -10px;margin-right: 2px;margin-top: -12px;border-color: #d2d6de;border-right-style: solid;border-right-width: thin;height: 30px;">
						<span class="input-group-addon" onclick="listApiLog(1)" style="cursor:pointer;width:10px;">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="col-md-12" style="margin-top:5px;">
				<div id="listApiLog">
				</div>
				<div class="text-right">
					<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
				</div>
				<input type="submit" value="submit" style="display:none" />
			</div>
		</form>
	</div>
</section>

<script type="text/javascript">
	$(document).ready(function() {
		listApiLog();
		
		$("input[name='searchType']").click(function(){
			if("all" == $("input[name='searchType']:checked").val()) {
				$("#logStartDt").prop("readonly",true);
				$("#logEndDt").prop("readonly",true);
			} else {
				$("#logStartDt").prop("readonly",false);
				$("#logEndDt").prop("readonly",false);
			}
		})
	});

	/**
	 * 실패 데이터 조회
	 */
	function listApiLog() {
		var searchKey = $('#searchKey').val();
		//var searchValue = $('#searchValue').val();
		//var listScale = $("#listScale option:selected").val();
		var searchFrom = "";
		var searchTo = "";
		var crsCd = $("#crsCd").val();
		var syncGubunCd = $("#syncGubunCd").val();
		
		if("all" != $("input[name='searchType']:checked").val()) {
			searchFrom = $('#logStartDt').val();
			searchTo = $('#logEndDt').val();
		}

		$('#listApiLog')
			.load(cUrl("/mng/etc/HrdApi/listApiLog"),
				{ 
				  "searchKey":searchKey,
				  //"searchValue":searchValue,
				  //"listScale":listScale,
				  "searchFrom":searchFrom,
				  "searchTo":searchTo,
				  "syncGubunCd":syncGubunCd,
				  "crsCd":crsCd
				 }
			);

	}
</script>
