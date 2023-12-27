<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="messageTransVO" value="${vo.logMsgTransLogVO}"/>

<body>
	<form id="logMessageForm" name="logMessageForm" onsubmit="return false">
    <input type="hidden" name="msgDivCd" value="MSG" />
    <input type="hidden" name="attachFileSns"  value="${vo.attachFileSns}"/>
    <input type="hidden" name="sendAddr"  value="${vo.sendAddr}"/>
    <input type="hidden" name="sendNm"  value="${vo.sendNm}" />
    <input type="hidden" name="logMsgTransLogVO.userNoList" value="${messageTransVO.userNoList}"/>
    <input type="hidden" name="logMsgTransLogVO.stdNoList" value="${messageTransVO.stdNoList}"/>
    <input type="hidden" name="logMsgTransLogVO.etcMbrSnList" value="${messageTransVO.etcMbrSnList}"/>
    <input type="hidden" name="logMsgTransLogVO.entrySnList" value="${messageTransVO.entrySnList}"/>
    
    
    
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
			    			<c:forEach var="recever" items="${targetList}">
			    				<%-- <c:if test="${recever.recvYn eq 'Y'}">
			    					<c:set var="receverCnt" value="${receverCnt + 1}" />
			    					<c:if test="${receverCnt == 1 }"> --%>
			    						${recever.recvNm}(${recever.recvAddr})
			    					<%-- </c:if>
			    				</c:if> --%>
			    			</c:forEach>
		    			</div>
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
                </li>
                <li>
                    <div class="row">
                        <label class="form-label col-sm-2">수신거부자</label>
                        <div class="col-sm-10">
                            <ul class="message_box">
                               <li><p>수신거부자에게는 메일이 발송되지 않습니다.</p>
								<div style="float:left">
				    			<c:set var="receverCnt" value="0" />
				    			<c:forEach var="recever" items="${targetList}">
				    				<c:if test="${recever.recvYn ne 'Y'}">
				    					<c:set var="receverCnt" value="${receverCnt + 1}" />
				    					<c:if test="${receverCnt == 1 }">
				    						${recever.recvNm}(${recever.recvAddr})
				    					</c:if>
				    				</c:if>
				    			</c:forEach>
				    			</div>
				    			<c:if test="${receverCnt > 1}">
				    			<div style="float:right;">
				    				<div style="float:left;"><spring:message code="common.message.usercnt.include" arguments="${receverCnt}"/>&nbsp;</div>
									<div class="input-group-btn btn-group">
				  						<ul class="dropdown-menu" role="menu" style="padding:10px;right:0;left:auto;">
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
                               </li>
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
                        <label for="txtContents" class="form-label col-sm-2">내용</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <textarea class="form-control" rows="10" name="cts"id="txtContents"></textarea>
                            </div>
                        </div>
                    </div>
                </li>                           
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2" id="btnSend">발송</button>
        <button type="button" class="btn" onclick="parent.modalBoxClose()" >닫기</button>
    </div>
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		$('#btnSend').bind('click keydown', function(event) {
			if($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();
				if($('#txtTitle').val() == ""){
					alert("<spring:message code="log.message.msg.alert.input.title"/>");
					$('#txtTitle').focus();
				} else if($('#txtContents').val() == ""){
					alert("<spring:message code="log.message.msg.alert.input.cnts"/>");
					$('#txtContents').focus();
				}else{
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
		});
	});

</script>
</body>
