<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="transVO" value="${vo.logMsgTransLogVO}"/>

	<form name="messageForm" id="messageForm" method="post" action="/adm/log/message/addMessage" onsubmit="return false">
		<input type="hidden" name="msgDivCd" value="EMAIL"/>
		<input type="hidden" name="attachFileSns"  value="${vo.attachFileSns}"/>
		<input type="hidden" name="logMsgTransLogVO.userNoList" value="${transVO.userNoList}"/>
		<input type="hidden" name="sendNm" value="${vo.sendNm}"/>
		<input type="hidden" name="sendAddr" value="${vo.sendAddr}"/>
		<table class="table table-striped table-bordered">
			<colgroup>
				<col width="25%" />
				<col width="75%" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><spring:message code="log.title.msg.receiver.cnt" /></th>
					<td>${fn:length(targetList)}</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="log.title.msg.receiver" /></th>
					<td style="line-height: 22px;">
						<div style="float: left">
							<c:set var="receverCnt" value="0" />
							<c:forEach var="recever" items="${targetList}">
								<c:if test="${recever.recvYn eq 'Y'}">
									<c:set var="receverCnt" value="${receverCnt + 1}" />
									<c:if test="${receverCnt == 1 }">
	    								${recever.recvNm}&lt;${recever.recvAddr}&gt;
	    							</c:if>
								</c:if>
							</c:forEach>
						</div> 
						<c:if test="${receverCnt > 1}">
							<div style="float: right;">
								<div style="float: left;">
									<spring:message code="common.message.usercnt.include" arguments="${receverCnt}" />
									&nbsp;
								</div>
								<div class="input-group-btn btn-group">
									<ul class="dropdown-menu" role="menu" style="padding: 10px; right: 0; left: auto;">
										<c:forEach var="recever" items="${targetList}">
											<c:if test="${recever.recvYn eq 'Y'}">
												<li>${recever.recvNm}&lt;${recever.recvAddr}&gt;</li>
											</c:if>
										</c:forEach>
									</ul>
									<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
										<span class="caret"></span>
									</button>
								</div>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="log.title.msg.reject.receive" /></th>
					<td style="line-height: 22px;">
						<p>
							<spring:message code="log.message.msg.reject.email" />
						</p>
						<div style="float: left">
							<c:set var="receverCnt" value="0" />
							<c:forEach var="recever" items="${targetList}">
								<c:if test="${recever.recvYn ne 'Y'}">
									<c:set var="receverCnt" value="${receverCnt + 1}" />
									<c:if test="${receverCnt == 1 }">
			    						${recever.recvNm}&lt;${recever.recvAddr}&gt;
			    					</c:if>
								</c:if>
							</c:forEach>
						</div> 
						<c:if test="${receverCnt > 1}">
							<div style="float: right;">
								<div style="float: left;">
									<spring:message code="common.message.usercnt.include" arguments="${receverCnt}" />
									&nbsp;
								</div>
								<div class="input-group-btn btn-group">
									<ul class="dropdown-menu" role="menu" style="padding: 10px; right: 0; left: auto;">
										<c:forEach var="recever" items="${targetList}">
											<c:if test="${recever.recvYn ne 'Y'}">
												<li>${recever.recvNm}&lt;${recever.recvAddr}&gt;</li>
											</c:if>
										</c:forEach>
									</ul>
									<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
										<span class="caret"></span>
									</button>
								</div>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="common.title.title" /></th>
					<td style="padding: 5px">
						<input type="text" id="title" name="title" style="width:500px" title="<spring:message code="common.title.title"/>" maxlength="200" class="form-control input-sm" />
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="common.title.atachfile" /></th>
					<td style="padding: 5px">
						<table style="width: 100%;">
							<tr>
								<td style="width: 100px;">
									<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs">
										<spring:message code="button.select.file" />
									</a> 
									<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display: none" /><%-- 첨부파일 버튼 --%>
								</td>
								<td>
									<div id="atchprogress" class="progress" style="margin-bottom: 0px;">
										<div class="progress-bar progress-bar-success"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="atchfiles" class="files"></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="common.title.cnts" /></th>
					<td style="padding: 5px">
						<textarea id="cts" name="cts" style="width:500px;height:180px" class="form-control input-sm"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<div style="width: 1005; margin-top: 10px;" class="text-right">
			<button class="btn btn-primary btn-sm" id="btnSend">
				<spring:message code="button.msg.send" />
			</button>
			<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()">
				<spring:message code="button.close" />
			</button>
		</div>
	</form>

<script type="text/javascript">

	var attachFiles;	// 첨부 파일

	// 페이지 초기화
	$(document).ready(function() {

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		attachFiles = new $M.JqueryFileUpload({
							"varName"			: "attachFiles",
							"files" 			:$.parseJSON('[]'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'MESSAGE',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});


		$('#btnSend').bind('click keydown', function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();
				if($('#title').val() == ""){
					alert("<spring:message code="log.message.msg.alert.input.title"/>");
					$('#title').focus();
				} else if($('#cts').val() == ""){
					alert("<spring:message code="log.message.msg.alert.input.cnts"/>");
					$('#cts').focus();
				} else {
					//$('#messageForm').find('input[name=cmd]').val('addMessage');
					
					// atchFiles에서 첨부파일 정보를 가져온다.
					$(':input:hidden[name=attachFileSns]').val( attachFiles.getFileSnAll() );					
					$('#messageForm').ajaxSubmit(
						function(resultDTO) {
							if(resultDTO.result >= 0) {
								alert('<spring:message code="log.message.msg.note.add.success"/>');
								parent.modalBoxClose();
							} else {
								alert('<spring:message code="log.message.msg.note.add.failed"/>');
							}
						}
					);
				}
			}
		});
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

</script>
