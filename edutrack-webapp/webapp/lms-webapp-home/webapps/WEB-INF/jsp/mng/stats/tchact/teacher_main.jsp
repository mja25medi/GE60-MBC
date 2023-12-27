<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<select name="searchKey" id="searchKey" class="form-control input-sm">
								<option value="usernm"><spring:message code="user.title.userinfo.name"/></option>
								<option value="userid"><spring:message code="user.title.userinfo.userid"/></option>
							</select>
							<div class="input-group" style="width:180px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>"/>
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 text-right">
						<div class="form-group">
							<c:if test="${MSG_SMS eq 'Y' }">
							<a href="javascript:messageForm('SMS')" class="btn btn-info btn-sm"><spring:message code="button.sms"/> </a>
							</c:if>
							<c:if test="${MSG_EMAIL eq 'Y' }">
							<a href="javascript:messageForm('EMAIL')" class="btn btn-info btn-sm"><spring:message code="button.email"/> </a>
							</c:if>
							<c:if test="${MSG_NOTE eq 'Y' }">
							<a href="javascript:messageForm('MSG')" class="btn btn-info btn-sm"><spring:message code="button.note"/> </a>
							</c:if>
							<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.download.excel"/> </a>
						</div>
					</div>
					<div class="col-md-12" style="margin-top:5px;">
						<div id="listArea"></div>
					</div>
					</form>
				</div>
	
				<form id="tchInfoForm"></form>
			</div>
		</div>
	</div>
</section>				
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.sortKey = "USER_NM_ASC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
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

	function listPageing(page) {
		var searchKey = $("#searchKey").val();
		var searchValue = $("#searchValue").val();
		$("#listArea")
			.load(
				cUrl("/mng/stats/tchAct/teacherList"), {
				"searchKey" : searchKey,
				"searchValue" : searchValue,
				"sortKey":ItemDTO.sortKey,
				"curPage" : page
			});
	}

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$("#Search input[name='sel']").prop({checked:true});
	    }else{
	    	$("#Search input[name='sel']").prop({checked:false});
	    }
	}

	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var userList = $("#Search input[name='sel']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/log/message/addMessagePop"/>"
			+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}

	function tchInfo(userNo){
		var addContent  = "<iframe id='tchInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/stats/tchAct/viewTchPop"/>"
			+ "?userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}


	function excelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}

		// 폼에 action을 설정하고 submit시킨다.
		$("#tchInfoForm").attr('target', '_m_download_iframe');
		$("#reshAnsrFrm").attr("action","/mng/stats/tchAct/listExcelDownload");
		$("#tchInfoForm").submit();
		$("#tchInfoForm").removeAttr('target');

	}
</script>


