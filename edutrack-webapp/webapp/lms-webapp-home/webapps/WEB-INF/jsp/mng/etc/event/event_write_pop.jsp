<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<form  onsubmit="return false" name="eventForm" id="eventForm">
    <input type="hidden" name="eventSn" value="${vo.eventSn} "/>
    <input type="hidden" name="eventOdr" value="${vo.eventOdr} " />
    <input type="hidden" name="eventFileSn" value="${vo.eventFileSn}" />
    <input type="hidden" name="eventPosCd" value="SUB" />
    <table summary="<spring:message code="etc.title.event.write"/>" class="table table-bordered wordbreak">
        <colgroup>
    		<col style="width:20%"/>
    		<col style="width:80%"/>
    	</colgroup>
		<tr>
			<th class="top" scope="row"><span style="color:red;">* </span><label for="eventNm"><spring:message code="etc.title.event.name"/></label></th>
			<td class="top">
				<input type="text" maxlength="50" dispName="<spring:message code="etc.title.event.name"/>" isNull="N" name="eventNm" value="${vo.eventNm }" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="etc.title.event.url"/></th>
			<td>
				<input type="text" maxlength="80" dispName="<spring:message code="etc.title.event.url"/>" isNull="Y" lenCheck="100" name="eventUrl" value="${vo.eventUrl }" class="form-control input-sm"/>
			</td>
		</tr>
<%--	<tr>
			<th scope="row"><span style="color:red;">* </span>배너 위치</th>
			<td>
				<c:forEach var="item" items="${eventPosCdList }">
					<label style="font-weight: normal; margin-right: 10px;" ><input type="radio" style="border:0" name="eventPosCd" value="${item.codeCd }" id="eventPosCd" <c:if test="${vo.eventPosCd eq item.codeCd}">checked</c:if>/>${item.codeNm }</label>
				</c:forEach>
			</td>
		</tr>
		 --%>	
		<tr>
            <th scope="row"><spring:message code="etc.title.event.target"/></th>
            <td>
  				<select name="eventTrgt" id="eventTrgt" class="form-control input-sm">
					<c:forEach var="item" items="${eventTrgtList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.eventTrgt eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
             </td>
        </tr>
		<tr>
            <th scope="row"><span style="color:red;">* </span><label for="eventDesc"><spring:message code="etc.title.event.desc"/></label></th>
            <td>
            	<textarea style="height:50px" dispName="<spring:message code="etc.title.event.desc"/>" isNull="N" lenCheck="1000" name="eventDesc" class="form-control input-sm" id="eventDesc">${vo.eventDesc}</textarea>
            </td>
        </tr>
		<tr>
			<th scope="row"><spring:message code="etc.title.event.openyn"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" style="border:0" name="openYn" value="Y" id="openY" <c:if test="${vo.openYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="openYn" value="N" id="openN" <c:if test="${vo.openYn ne 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="etc.title.event.image"/></th>
			<td>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" name="uploader" id="uploader"  style="display:none" accept="image/*" />
						<div id="progress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="files" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
    </table>
    <div class="text-right">
    	<c:if test="${gubun eq 'A'}">
            <a href="javascript:add()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
        </c:if>
        <c:if test="${gubun eq 'E'}">

        	<a href="javascript:edit()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
        	<a href="javascript:del()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
        </c:if>
        <a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
    </div>
</form>
<script type="text/javascript">
	var eventFile;

	// 페이지 초기화
	$(document).ready(function() {
		// 이미지 파일 첨부 바인딩 및 초기화
		eventFile = new $M.JqueryFileUpload({
						"varName"			: "eventFile",
						"files" 			: $.parseJSON('${vo.eventFileJson}'),
						"uploaderId"		: "uploader",
						"fileListId"		: "files",
						"progressId"		: "progress",
						"maxcount"			: 1,
						"previewImage"		: true,
						"uploadSetting"	: {
							'formData'		: { 'repository': 'EVENT',
												'organization' : "${USER_ORGCD}",
												'type'		: 'image' }
						}
					});
		<c:if test="${gubun eq 'A'}">
		$("#openY").attr("checked",true);
		</c:if>
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.eventForm)) return;
//		if(!chkPosBtn()) return;
		$(':input:hidden[name=eventFileSn]').val(eventFile.getFileSnAll());

		$('#eventForm').attr("action", "/mng/etc/event/" + cmd);
		$('#eventForm').ajaxSubmit(processCallback);
	}

	/*
	function chkPosBtn() {
		if(	$("input[name=eventPosCd]:checked").size() == 0){
			alert("배너 위치를 지정해주세요.");
			return false;
		}
		return true;
	}
	*/

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.eventList(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 코드 추가
	 */
	function add() {
		process("add");	// cmd
	}

	/**
	 * 코드 수정
	 */
	function edit() {
		process("edit"); // cmd
	}

	/**
	 * 코드 삭제
	 */
	function del() {
		if(confirm('<spring:message code="etc.message.event.confirm.delete"/>')) {
			process("delete"); // cmd
		} else {
			return;
		}
	}
</script>
