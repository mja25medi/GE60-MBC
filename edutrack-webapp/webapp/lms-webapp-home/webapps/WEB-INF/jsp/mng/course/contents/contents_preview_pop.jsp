<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<mhtml:mng_html>
<mhtml:mng_head titleName="온라인 과목 교재 관리">
	<mhtml:head_module/>
	<script type="text/javascript">
		$(document).ready(function() {
			var winWidth = ${onlineSubjectVO.winWidth};
			var winHight = ${onlineSubjectVO.winHeight};
			window.resizeTo(winWidth, winHight);
		});

	</script>

	<c:if test="${onlineSubjectVO.winMenuUseYn eq 'Y'}">
	<frameset rows="45,620,*" frameborder="no" border="0" framespacing="0" valign="top" scrolling="no">
		<frame name="scTitle" scrolling="no" noresize src="<c:url value="/home/openCourse/skinTop?sbjCd=${onlineSubjectVO.sbjCd}"/>" >
		<frameset cols="190, 820" scrolling="no" noresize>
			<frame name="scLeft" scrolling="no" noresize src="<c:url value="/home/openCourse/skinLeft?sbjCd=${contentsVO.sbjCd}&amp;unitCd=${contentsVO.unitCd}"/>" />
			<frame name="scMain" scrolling="no" noresize src="<c:url value="/contents${contentsVO.unitFilePath}"/>" />
		</frameset>
		<frame name="scBottom" scrolling="no" noresize src="<c:url value="/home/openCourse/skinBottom?sbjCd=${contentsVO.sbjCd}"/>" />
	</frameset>
	</c:if>
	<c:if test="${onlineSubjectVO.winMenuUseYn ne 'Y'}">
	<frameset rows = "*,0" frameborder="no" border="0"  framespacing="0" scrolling="no">
		<frame name="scMain" src = "${contentsVO.unitFilePath}" width="100%" height="100%" scrolling=auto marginwidth=0 marginheight=0>
		<frame name="scHidden" src = "about:blank">
	</frameset>
	</c:if>
</mhtml:mng_head>
</mhtml:mng_html>
