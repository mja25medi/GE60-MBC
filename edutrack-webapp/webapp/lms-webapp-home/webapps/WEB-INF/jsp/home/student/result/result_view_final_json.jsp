<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@
	taglib prefix="meditag" uri="tag-utils" %><%@	
	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@	
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@
	page import="egovframework.edutrack.comm.util.web.UserBroker"%><%@
	page import="egovframework.edutrack.Constants"
%>[
<c:set var="score" value="" />
<c:if test="${createCourseVO.prgrRatio > 0}">
	<c:set var="score" value="${vo.prgrScore  * (100/createCourseVO.prgrRatio) }" />
{"id": "PRGR","order": "1","score" : "${score }","weight" : "${createCourseVO.prgrRatio/10}","color": "#9E0041","label" : "<spring:message code="course.title.course.ratio.progress"/>","tip":"${vo.prgrScore}/<fmt:formatNumber value="${createCourseVO.prgrRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.attdRatio > 0 }">
	<c:set var="score" value="${vo.attdScore  * (100/createCourseVO.attdRatio) }" />
{"id": "ATTD","order": "2","score" : "${score}","weight" : "${createCourseVO.attdRatio/10}","color": "#E1514B","label" : "<spring:message code="course.title.course.ratio.attend"/>","tip":"${vo.attdScore}/<fmt:formatNumber value="${createCourseVO.attdRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.examRatio > 0}">
	<c:set var="score" value="${vo.examScore * (100/createCourseVO.examRatio) }" />
{"id": "EXAM","order": "3","score" : "${score }","weight" : "${createCourseVO.examRatio/10}","color": "#F47245","label" : "<spring:message code="course.title.course.ratio.exam"/>","tip":"${vo.examScore}/<fmt:formatNumber value="${createCourseVO.examRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.forumRatio > 0 }">
	<c:set var="score" value="${vo.forumScore * (100/createCourseVO.forumRatio) }" />
{"id": "FORUM","order": "4","score" : "${score }","weight" : "${createCourseVO.forumRatio/10}","color": "#EAF195","label" : "<spring:message code="course.title.course.ratio.forum"/>","tip":"${vo.forumScore}/<fmt:formatNumber value="${createCourseVO.forumRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.asmtRatio >0}">
	<c:set var="score" value="${vo.asmtScore * (100/createCourseVO.asmtRatio) }" />
{"id": "ASMT","order": "5","score" : "${score }","weight" : "${createCourseVO.asmtRatio/10}","color": "#9CD6A4","label" : "<spring:message code="course.title.course.ratio.asmt"/>","tip":"${vo.asmtScore}/<fmt:formatNumber value="${createCourseVO.asmtRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.joinRatio > 0 }">
	<c:set var="score" value="${vo.joinScore * (100/createCourseVO.joinRatio) }" />
{"id": "JOIN","order": "6","score" : "${score }","weight" : "${createCourseVO.joinRatio/10}","color": "#4D9DB4","label" : "<spring:message code="course.title.course.ratio.join"/>","tip":"${vo.joinScore}/<fmt:formatNumber value="${createCourseVO.joinRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.etcRatio > 0 }">
	<c:set var="score" value="${vo.etcScore * (100/createCourseVO.etcRatio) }" />
{"id": "ETC","order": "7","score" : "${score }","weight" : "${createCourseVO.etcRatio/10}","color": "#5E4EA1","label" : "<spring:message code="course.title.course.ratio.etc"/>","tip":"${vo.etcScore}/<fmt:formatNumber value="${createCourseVO.etcRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>
{"id": "EMP","order": "8","score" : "0","weight" : "0","color": "#5E4EA1","label" : "","tip":""}
]