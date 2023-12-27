<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

		<section class="content">
			<div class="row" id="content">
				<div class="box">
					<div class="box-body">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-12 col-sm-12 text-right">
							<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
						</div>
						</form>
						<div class="col-md-12" style="margin-top:5px;">						
							<div id="dataList" style="width:100%;">
								<table summary="data" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:100px"/>
										<col style="width:20%"/>
										<col style="width:20%"/>
										<col style="width:20%"/>
										<col style="width:auto"/>
									</colgroup>
									<thead>
										<tr>
											<th scope='col'><spring:message code="common.title.no"/></th>
											<th scope='col'><spring:message code="user.title.userinfo.name"/></th>
											<th scope='col'><spring:message code="user.title.userinfo.userid"/></th>
											<th scope='col'><spring:message code="log.title.mgr.conn.datetime"/></th>
											<th scope='col'><spring:message code="log.title.mgr.conn.ip"/></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="5"><spring:message code="common.message.nodata"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="overlay" id="loadingBar">
							<i class="fa fa-spinner fa-spin"></i>
						</div>
					</div>
				</div>
			</div>
		</section>
<script type="text/javascript">
	var modalBox = null;
	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	
		listPageing(1);
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 * 게시판 정보 목록 조회
	 */
	function listPageing(page) {
		$("#loadingBar").show();
		var listScale = $("#listScale option:selected").val();
	    $("#dataList").load(
	    	cUrl("/mng/log/login/list"),
	    	{ "pageIndex" : page, "listScale" : listScale, "orgCd" : '${vo.orgCd}'},
	    	function () {$("#loadingBar").hide();}
	    );
	}
</script>

