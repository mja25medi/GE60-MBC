<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="messageTransVO" value="${vo.logMsgTransLogVO}"/>
	<form id="logMessageForm" name="logMessageForm" onsubmit="return false">
    <input type="hidden" name="msgDivCd" value="EMAIL" />
	<input type="hidden" name="attachFileSns"  value="${vo.attachFileSns}"/>
	<input type="hidden" name="logMsgTransLogVO.userNoList" value="${messageTransVO.userNoList}"/>
	<input type="hidden" name="sendNm" value="${vo.sendNm}"/>
	<input type="hidden" name="sendAddr" value="${vo.sendAddr}"/>  
	
	
	
	<div class="modal_cont">
        <div class="tstyle">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label class="form-label col-sm-2">대상자수</label>
                        <div class="col-sm-10">${fn:length(targetList)}명</div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label class="form-label col-sm-2">수신자</label>
                        <div class="col-sm-10">
                       		<c:set var="receverCnt" value="0" />
		    				   <ul class="message_box">
		    				<c:forEach var="recever" items="${targetList}">
		    					<%-- <c:if test="${recever.recvYn eq 'Y'}">
		    						<c:set var="receverCnt" value="${receverCnt + 1}" />
		    					<c:if test="${receverCnt == 1 }">
		    						${recever.recvNm}(${recever.recvAddr})
		    					</c:if>
		    					</c:if> --%>
		    					<li>${recever.recvNm}(${recever.recvAddr})</li>
	    					</c:forEach>
	    					</ul>
	    					<%-- <c:if test="${receverCnt > 1}">
				    			<div style="float:right;">
				    				<div style="float:left;"><spring:message code="common.message.usercnt.include" arguments="${receverCnt}"/>&nbsp;</div>
									<div class="input-group-btn btn-group">
				  						<ul class="dropdown-menu" role="menu" style="padding:10px;right:0;left:auto;">
				  							<c:forEach var="recever" items="${targetList}">
			    								<c:if test="${recever.recvYn eq 'Y'}">
				    								<li>${recever.recvNm}(${recever.recvAddr})</li>
				    							</c:if>
				    						</c:forEach>
				  						</ul>
				    					<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
				    						<span class="caret"></span>
				  						</button>
				  					</div>
				  				</div>
	  						</c:if> --%>	
		    			</div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label class="form-label col-sm-2">수신거부자</label>
                        <div class="col-sm-10">
                            <ul class="message_box">
	  							<c:forEach var="recever" items="${targetList}">
	   								<c:if test="${recever.recvYn ne 'Y'}">
	    								<li>${recever.recvNm}&lt;${recever.recvAddr}&gt;</li>
	    							</c:if>
	    						</c:forEach>
                            </ul>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="txtTitle" class="form-label col-sm-2">제목</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input class="form-control" type="text" name="title" id="txtTitle" value="" maxlength="100" placeholder="제목을 입력하세요">
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="atchuploader" class="form-label col-sm-2">첨부파일</label>
                        <div class="col-sm-10 attach_area">
                        	<a href="javascript:uploderclick('atchuploader');" class="btn gray2">파일 찾기</a>
                            <input type="file" class="input_attach sr-only" id="atchuploader" name="atchuploader" multiple="multiple" style="display:none">
                            <div id="atchprogress" class="progress">
			    				<div class="progress-bar progress-bar-success"></div>
							</div>
                            <div id="atchfiles" class="multi_inbox"></div>                                      
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="txtContents" class="form-label col-sm-2">내용</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <textarea class="form-control" rows="10" id="txtContents" name="cts" ></textarea>
                            </div>
                        </div>
                    </div>
                </li>                           
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2" onclick="sendMail()">발송</button>
        <button type="button" class="btn" onclick="parent.modalBoxClose()" >닫기</button>
    </div>
	</form>

<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	// 페이지 초기화
	$(document).ready(function() {
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
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function sendMail() {
		if($('#txtTitle').val() == ""){
			alert("<spring:message code="log.message.msg.alert.input.title"/>");
			$('#txtTitle').focus();
		} else if($('#txtContents').val() == ""){
			alert("<spring:message code="log.message.msg.alert.input.cnts"/>");
			$('#txtContents').focus();
		}else{
			$(':input:hidden[name=attachFileSns]').val( attachFiles.getFileSnAll() );
			$('#logMessageForm').attr("action","/lec/message/addMessage");
			$('#logMessageForm').ajaxSubmit(
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
</script>
