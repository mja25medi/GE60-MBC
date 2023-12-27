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
	<c:set var="score" value="${meditag:round( (bookmarkVO.prgrRatio  ) ,1 )}" />
	<c:if test="${meditag:round( (bookmarkVO.prgrRatio  ) * (createCourseVO.prgrRatio/100),1 ) > createCourseVO.prgrRatio}"><c:set var="score" value="100" /></c:if>
{"id": "PRGR","order": "1","score" : "${score }","weight" : "${createCourseVO.prgrRatio/10}","color": "#9E0041","label" : "<spring:message code="course.title.course.ratio.progress"/>","tip":"${meditag:round( (bookmarkVO.prgrRatio  ) * (createCourseVO.prgrRatio/100),1 )}/<fmt:formatNumber value="${createCourseVO.prgrRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.attdRatio > 0 }">
	<c:set var="score" value="${vo.attdScore * (100/createCourseVO.attdRatio) }" />
	<c:if test="${vo.attdScore > createCourseVO.attdRatio}"><c:set var="score" value="100" /></c:if>
{"id": "ATTD","order": "2","score" : "${score}","weight" : "${createCourseVO.attdRatio/10}","color": "#E1514B","label" : "<spring:message code="course.title.course.ratio.attend"/>","tip":"${vo.attdScore}/<fmt:formatNumber value="${createCourseVO.attdRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.examRatio > 0}">
	<c:set var="sumGetScore" value="0"/>
	<c:set var="sumTotScore" value="0"/>
	<c:forEach items="${examList}" var="exam" varStatus="exstatus">
		<c:if test="${exam.rateYn eq 'Y' }">
		<c:set var="sumGetScore" value="${sumGetScore + exam.totGetScore}"/>
		</c:if>
		<c:set var="sumTotScore" value="${sumTotScore + 100}"/>
	</c:forEach>
	<c:choose>
		<c:when test="${sumGetScore > 0 && sumTotScore > 0 }">
			<c:set var="score" value="${meditag:round((sumGetScore/sumTotScore*100)*(createCourseVO.examRatio/100),1) * (100/createCourseVO.examRatio) }" />
			<c:if test="${meditag:round((sumGetScore/sumTotScore*100)*(createCourseVO.examRatio/100),1) > createCourseVO.examRatio}"><c:set var="score" value="100" /></c:if>
{"id": "EXAM","order": "3","score" : "${score }","weight" : "${createCourseVO.examRatio/10}","color": "#F47245","label" : "<spring:message code="course.title.course.ratio.exam"/>","tip":"${meditag:round((sumGetScore/sumTotScore*100)*(createCourseVO.examRatio/100),1)}/<fmt:formatNumber value="${createCourseVO.examRatio}" pattern="#.#" minFractionDigits="1"/>"},

		</c:when>
		<c:otherwise>
{"id": "EXAM","order": "3","score" : "0.0","weight" : "${createCourseVO.examRatio/10}","color": "#F47245","label" : "<spring:message code="course.title.course.ratio.exam"/>","tip":"0/<fmt:formatNumber value="${createCourseVO.examRatio}" pattern="#.#" minFractionDigits="1"/>"},
		</c:otherwise>
	</c:choose>
</c:if>

<c:if test="${createCourseVO.forumRatio > 0 }">
	<c:set var="sumForumScore" value="0"/>
	<c:set var="sumForumTotScore" value="0"/>
	<c:forEach items="${forumList}" var="forum" varStatus="frstatus">
		<c:if test="${forum.rsltYn eq 'Y'  }">
		<c:set var="sumForumScore" value="${sumForumScore + forum.score}"/>
		<c:set var="sumForumTotScore" value="${sumForumTotScore + 100}"/>
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${sumForumScore > 0 && sumForumTotScore > 0 }">
			<c:set var="score" value="${meditag:round((sumForumScore/sumForumTotScore*100)*(createCourseVO.forumRatio/100),1) * (100/createCourseVO.asmtRatio) }" />
			<c:if test="${meditag:round((sumForumScore/sumForumTotScore*100)*(createCourseVO.forumRatio/100),1) > createCourseVO.asmtRatio}"><c:set var="score" value="100" /></c:if>
{"id": "FORUM","order": "4","score" : "${score }","weight" : "${createCourseVO.forumRatio/10}","color": "#EAF195","label" : "<spring:message code="course.title.course.ratio.forum"/>","tip":"${meditag:round((sumForumScore/sumForumTotScore*100)*(createCourseVO.forumRatio/100),1)}/<fmt:formatNumber value="${createCourseVO.forumRatio}" pattern="#.#" minFractionDigits="1"/>"},

		</c:when>
		<c:otherwise>
{"id": "FORUM","order": "4","score" : "0.0","weight" : "${createCourseVO.forumRatio/10}","color": "#EAF195","label" : "<spring:message code="course.title.course.ratio.forum"/>","tip":"0/<fmt:formatNumber value="${createCourseVO.forumRatio}" pattern="#.#" minFractionDigits="1"/>"},
		</c:otherwise>
	</c:choose>
</c:if>

<c:if test="${createCourseVO.asmtRatio >0}">
	<c:set var="sumAsmtScore" value="0"/>
	<c:set var="sumAsmtTotScore" value="0"/>
	<c:forEach items="${asmtList}" var="asmt" varStatus="asstatus">
		<c:if test="${asmt.rsltYn eq 'Y'  }">
		<c:set var="sumAsmtScore" value="${sumAsmtScore + asmt.score}"/>
		<c:set var="sumAsmtTotScore" value="${sumAsmtTotScore + 100}"/>
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${sumAsmtScore > 0 && sumAsmtTotScore > 0 }">
			<c:set var="score" value="${meditag:round((sumAsmtScore/sumAsmtTotScore*100)*(createCourseVO.asmtRatio/100),1) * (100/createCourseVO.asmtRatio) }" />
			<c:if test="${meditag:round((sumAsmtScore/sumAsmtTotScore*100)*(createCourseVO.asmtRatio/100),1) > createCourseVO.asmtRatio}"><c:set var="score" value="100" /></c:if>
{"id": "ASMT","order": "5","score" : "${score }","weight" : "${createCourseVO.asmtRatio/10}","color": "#9CD6A4","label" : "<spring:message code="course.title.course.ratio.asmt"/>","tip":"${meditag:round((sumAsmtScore/sumAsmtTotScore*100)*(createCourseVO.asmtRatio/100),1)}/<fmt:formatNumber value="${createCourseVO.asmtRatio}" pattern="#.#" minFractionDigits="1"/>"},
		</c:when>
		<c:otherwise>
{"id": "ASMT","order": "5","score" : "0.0","weight" : "${createCourseVO.asmtRatio/10}","color": "#9CD6A4","label" : "<spring:message code="course.title.course.ratio.asmt"/>","tip":"0/<fmt:formatNumber value="${createCourseVO.asmtRatio}" pattern="#.#" minFractionDigits="1"/>"},
		</c:otherwise>
	</c:choose>
</c:if>

<c:if test="${createCourseVO.joinRatio > 0 }">
	<c:set var="score" value="${vo.joinScore * (100/createCourseVO.joinRatio) }" />
	<c:if test="${vo.joinScore > createCourseVO.joinRatio}"><c:set var="score" value="100" /></c:if>
{"id": "JOIN","order": "6","score" : "${score }","weight" : "${createCourseVO.joinRatio/10}","color": "#4D9DB4","label" : "<spring:message code="course.title.course.ratio.join"/>","tip":"${vo.joinScore}/<fmt:formatNumber value="${createCourseVO.joinRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>

<c:if test="${createCourseVO.etcRatio > 0 }">
	<c:set var="score" value="${vo.etcScore * (100/createCourseVO.etcRatio) }" />
	<c:if test="${vo.etcScore > createCourseVO.etcRatio}"><c:set var="score" value="100" /></c:if>
{"id": "ETC","order": "7","score" : "${score }","weight" : "${createCourseVO.etcRatio/10}","color": "#5E4EA1","label" : "<spring:message code="course.title.course.ratio.etc"/>","tip":"${vo.etcScore}/<fmt:formatNumber value="${createCourseVO.etcRatio}" pattern="#.#" minFractionDigits="1"/>"},
</c:if>
{"id": "EMP","order": "8","score" : "0","weight" : "0","color": "#5E4EA1","label" : "","tip":""}
]