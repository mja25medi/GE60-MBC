<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>


<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
						<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
						<div class="col-md-12 col-sm-12 text-right">
							<div class="btn-group">
								<button class="btn btn-primary btn-sm" onclick="ipWrite()"><i class="fa fa-edit"></i> <spring:message code="button.write"/></button>
							</div>
						</div>
						</form>
						<div class="col-md-12" style="margin-top:5px;">						
							<div id="dataList" style="width:100%;">
								<table summary="<spring:message code="org.title.conn.ip.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:100px"/>
										<col style="width:auto"/>
										<col style="width:12%"/>
										<col style="width:100px"/>
										<col style="width:80px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope='col'><spring:message code="common.title.no"/></th>
											<th scope='col'><spring:message code="org.title.conn.ip"/></th>
											<th scope='col'><spring:message code="org.title.conn.ip.type"/></th>
											<th scope='col'><spring:message code="common.title.useyn"/></th>
											<th scope='col'><spring:message code="common.title.delete"/></th>
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
	    $("#dataList").load(
	    	cUrl("/mng/org/connIp/list"),
	    	{ "pageIndex" : page, "orgCd" : '${vo.orgCd}'},
	    	function () {$("#loadingBar").hide();}
	    );
	}
	
	/**
	 * IP 등록 폼
	 */
	function ipWrite() {
		var addContent  = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/org/connIp/addPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 260);
		modalBox.setTitle("<spring:message code="org.title.conn.ip.write"/>");
		modalBox.show();
	}

	/**
	 * Ip 삭제
	 */
	function delIp(connIp) {
		if(confirm('<spring:message code="org.message.conn.ip.confirm.delete"/>')) {
			$.getJSON( cUrl("/mng/org/connIp/remove"),		// url
					{
					  "connIp" : connIp
					},			// params
					function(result) {
						alert(result.message);
						if(result.result == 1) {
							// 정상 처리
							listPageing(1);
						}
					}
				);
		} else {
			return;
		}
	}
	
</script>


