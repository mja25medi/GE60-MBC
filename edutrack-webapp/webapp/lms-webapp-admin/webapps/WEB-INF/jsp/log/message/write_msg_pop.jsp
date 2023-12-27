<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="transVO" value="${vo.logMsgTransLogVO}"/>
		<form action="/adm/log/message/addMessage" onsubmit="return false" name="messageForm" id="messageForm">
	    <input type="hidden" name="msgDivCd" value="MSG" />
	    <input type="hidden" name="attachFileSns"  value="${vo.attachFileSns}"/>
	    <input type="hidden" name="sendAddr"  value="${vo.sendAddr}"/>
	    <input type="hidden" name="sendNm"  value="${vo.sendNm}" />
	    <input type="hidden" name="logMsgTransLogVO.userNoList" value="${transVO.userNoList}"/>
	    <input type="hidden" name="logMsgTransLogVO.stdNoList" value="${transVO.stdNoList}"/>
	    <input type="hidden" name="logMsgTransLogVO.etcMbrSnList" value="${transVO.etcMbrSnList}"/>
	    <input type="hidden" name="logMsgTransLogVO.entrySnList" value="${transVO.entrySnList}"/>
	    <table class="table table-striped table-bordered">
	    	<colgroup>
	    		<col width="25%"/>
	    		<col width="75%"/>
	    	</colgroup>
	    	<tbody>
	    	<tr>
	    		<th class="top" scope="row"><spring:message code="log.title.msg.receiver.cnt"/></th>
	    		<td class="top">
	    			${fn:length(targetList)}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th scope="row"><spring:message code="log.title.msg.receiver"/></th>
	    		<td style="line-height:22px;">
	    			<div style="float:left">
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
	    			<div style="float:right;">
	    				<div style="float:left;"><spring:message code="common.message.usercnt.include" arguments="${receverCnt}"/>&nbsp;</div>
						<div class="input-group-btn btn-group">
	  						<ul class="dropdown-menu" role="menu" style="padding:10px;right:0;left:auto;">
	  							<c:forEach var="recever" items="${targetList}">
    								<c:if test="${recever.recvYn eq 'Y'}">
	    						<li>${recever.recvNm}&lt;${recever.recvAddr}&gt;</li>
	    							</c:if>
	    						</c:forEach>
	    						</li>
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
	    		<th scope="row"><spring:message code="log.title.msg.reject.receive"/></th>
	    		<td style="line-height:22px;">
					<p><spring:message code="log.message.msg.reject.email"/></p>
					<div style="float:left">
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
	    			<div style="float:right;">
	    				<div style="float:left;"><spring:message code="common.message.usercnt.include" arguments="${receverCnt}"/>&nbsp;</div>
						<div class="input-group-btn btn-group">
	  						<ul class="dropdown-menu" role="menu" style="padding:10px;right:0;left:auto;">
	  							<c:forEach var="recever" items="${targetList}">
    								<c:if test="${recever.recvYn ne 'Y'}">
	    						<li>${recever.recvNm}&lt;${recever.recvAddr}&gt;</li>
	    							</c:if>
	    						</c:forEach>
	    						</li>
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
	    		<th scope="row"><spring:message code="common.title.title"/></th>
	    		<td style="padding:5px">
	    			<input type="text" id="txtTitle" style="width:500px" dispName="<spring:message code="common.title.title"/>"  maxlength="200" isNull="N" lenCheck="200" name="title" class="form-control input-sm" value="${vo.title}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th scope="row"><spring:message code="common.title.cnts"/></th>
	    		<td style="padding:5px">
	    			<textarea id="txtContents" name="cts" style="width:500px;height:220px" class="form-control input-sm">${vo.cts}</textarea>
	    		</td>
	    	</tr>
	    	</tbody>
		</table>
		<div class="text-right" style="width:100%;margin-top:10px;">
			<button class="btn btn-primary btn-sm" id="btnSend"><spring:message code="button.msg.send"/></button>
			<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
		</div>
</form>
<script type="text/javascript">

	var attachFiles;	// 첨부 파일

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
				} else {
					$('#messageForm').find('input[name=cmd]').val('addMessage');
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

</script>
