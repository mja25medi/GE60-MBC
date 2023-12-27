<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="egovframework.edutrack.modules.system.config.service.SysCfgService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>

	<c:choose>
			<c:when test="${not empty createCourseVO.attachFiles }">
				<c:forEach var="fileItem" items="${createCourseVO.attachFiles}" varStatus="status">
					<a href="/app/file/download/${fileItem.fileSn }" class="btn btn-warning btn-xs"><i class="xi-paper-o xi-x" aria-hidden="true"></i>과정계획서</a>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	
	
	<div class="table_list">
        <ul class="list">
            <li class="head"><label>과정명</label></li>
            <li>${createCourseVO.crsCreNm}</li>
            <li class="head"><label>교육구분</label></li>
            <li>
            	<c:if test="${createCourseVO.crsSvcType eq 'R'}">
            		국비지원
            	</c:if>
            	<c:if test="${createCourseVO.crsSvcType eq 'S'}">
            		개발원 자체
            	</c:if>
            	<%-- ${createCourseVO.crsSvcType} --%>
            </li>
        </ul>
        <ul class="list">
            <li class="head"><label>과목수</label></li>
            <li>${createCourseVO.sbjCnt}</li>
            <li class="head"><label>결제금액</label></li>
            <li><fmt:formatNumber value="${createCourseVO.eduPrice}" pattern="#,###" />원</li>
        </ul>
        <ul class="list">
            <li class="head"><label>과정소개</label></li>
            <li>${createCourseVO.crsDesc}</li>
        </ul>
        <ul class="list">
            <li class="head"><label>과정목표</label></li>
            <li>
            ${fn:replace(createCourseVO.eduPrps,crlf,"<br/>")}
             </li>
        </ul>                                        
    </div>
	
	
	
	<%-- <table class="table st2">
	    <colgroup>
	        <col width="20%">
	        <col width="*">
	        <col width="20%">
	        <col width="*">
	    </colgroup>
	    <tbody>
	        <tr>
	            <th>과정명</th>
	            <td>${createCourseVO.crsCreNm}</td>
	            <th>기수명</th>
	            <td>${createCourseVO.crsYear }년도 ${createCourseVO.crsTerm } 기수</td>
	        </tr>
	        <tr>
	            <th>결제금액</th>
	            <td>${createCourseVO.eduPrice}원</td>
	            <th>모집정원</th>
	            <td>${createCourseVO.enrlNop}명</td>
	        </tr>
	        <tr>
	            <th>과정 등급</th>
	            <td>${createCourseVO.eduGrade}</td>
	            <th>1일 수강 제한</th>
	            <td>1일 <span class="red">${createCourseVO.dayStudyLimit}</span>차시만 수강 가능</td>
	        </tr>
	        <tr>
	            <th>학습개요</th>
	            <td colspan="3">${fn:replace(createCourseVO.sbjDesc,crlf,"<br/>")}</td>
	        </tr>
	        <tr>
	            <th>학습목표</th>
	            <td colspan="3">${fn:replace(createCourseVO.eduPrps,crlf,"<br/>")}</td>
	        </tr>
	        <tr>
	            <th>학습대상</th>
	            <td colspan="3">${fn:replace(createCourseVO.eduTarget,crlf,"<br/>")}</td>
	        </tr>
	        <tr>
	            <th>학습평가</th>
	            <td colspan="3">${fn:replace(createCourseVO.evalDesc,crlf,"<br/>")}</td>
	        </tr>
	    </tbody>
	</table> --%>
