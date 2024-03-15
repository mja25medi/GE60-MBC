<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="assignmentVO" value="${returnAssignmentVO}" />
	<div class="modal_cont">
        <div class="table_list">
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.type"/></label></li>
                <li>
                	<c:if test="${assignmentVO.asmtTypeCd eq 'ON' }">
	                	<label class="btn3 sm solid fcGreen">온라인</label>
	                </c:if>
	                <c:if test="${assignmentVO.asmtTypeCd eq 'OFF' }">
	                <label class="btn3 sm solid fcViolet">오프라인</label>
	                </c:if>
                </li>
                <li class="head"><label><spring:message code="lecture.title.assignment.seltype"/></label></li>
                <li><meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.name"/></label></li>
                <li>${assignmentVO.asmtTitle }</li>
            </ul>
            <c:if test="${assignmentVO.asmtStareTypeCd != 'S'}"> 
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.duration"/></label></li>
                <li>${assignmentVO.asmtStartDttm }&nbsp;${assignmentVO.asmtStartHour }:${assignmentVO.asmtStartMin } ~
				${assignmentVO.asmtEndDttm }&nbsp;${assignmentVO.asmtEndHour }:${assignmentVO.asmtEndMin }</li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.delaydate"/></label></li>
                <li>${assignmentVO.extSendDttm }&nbsp;${assignmentVO.extSendHour }:${assignmentVO.extSendMin }</li>
            </ul>
            </c:if>
            <c:if test="${assignmentVO.asmtTypeCd eq 'ON' }">
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.send.cnt"/></label></li>
                <li>${assignmentVO.asmtLimitCnt } <spring:message code="common.title.times.postfix"/></li>
                <li class="head"><label><spring:message code="lecture.title.assignment.send.ratio"/></label></li>
                <li>${assignmentVO.sendCritPrgrRatio }%</li>
            </ul>
            </c:if>
            <ul class="list">
                <li class="head"><label><spring:message code="common.title.atachfile"/></label></li>
                <li>
                    <ul class="add_file">
						  <c:forEach var="fileItem" items="${assignmentVO.attachFiles}" varStatus="status">
                        <li>                    
                            <a href="javascript:fileDown('${fileItem.fileSn}');" class="file_down" title="${fileItem.fileNm}"> 
                                <!-- <img src="../../_img/board/file_doc.png" alt=""> -->
                                <span class="text">${fileItem.fileNm}</span>
                                <span class="fileSize">(${fileItem.fileSizeStr})</span>
                            </a>                    
                            <span class="link">
                                <a class="btn-line btn-down" href="javascript:fileDown('${fileItem.fileSn}');">다운로드<i></i></a>
                            </span>
                        </li>
                        	</c:forEach>
                    </ul>
                </li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
                <li>${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}</li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.regyn"/></label></li>
                <li><meditag:codename category="REG_YN" code="${assignmentVO.regYn}"/></li>
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn" onclick="javascript:parent.modalBoxClose()">닫기</button>
    </div>

