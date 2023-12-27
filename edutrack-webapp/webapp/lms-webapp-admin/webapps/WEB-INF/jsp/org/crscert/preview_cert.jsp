<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<head>
	<style type="text/css">
		<c:if test="${orgCrsCertDTO.prnDirec eq 'VER'}">
		@page a4 { size : 21.0cm 29.7cm; margin : 10mm }
		.a4 { page : a4; page-break-after:always }
		</c:if>
		<c:if test="${orgCrsCertDTO.prnDirec eq 'HOR'}">
		@page a4 { size : 29.7cm 21.0cm; margin : 10mm }
		.a4 { page : a4; page-break-after:always }
		</c:if>
		.a4 div { position:absolute }
		.cpltno {font-size:14px;}
		.crsnm {font-size:24px;line-height:30px;}
		.crsnm span{font-size:18px;line-height:30px;}
		.prnday {font-size:16px;}
	</style>
</head>

<body>
<div class="a4" id="content">
	<img src="/app/file/view/${vo.imgFileSn}"/>
	<div class="cpltno" style="left:${vo.cpltnoX}px;top:${vo.cpltnoY}px;">
		CPLT-NO-2015-1234
	</div>
	<div class="crsnm" style="left:${vo.crsNmX}px;top:${vo.crsNmY}px;">
		<table>
			<tr>
				<td style="padding:5px;"><spring:message code="user.title.userinfo.name"/></td>
				<td style="padding:5px;">: User Name</tr>
			</tr>
			<tr>
				<td style="padding:5px;"><spring:message code="course.title.course.name"/></td>
				<td style="padding:5px;">: Test Course [2015/001]</tr>
			</tr>
			<tr>
				<td></td>
				<td style="padding:5px;"><span>2015.11.01 ~ 2015.11.21</span></tr>
			</tr>
		</table>
	</div>
	<div class="prnday" style="left:${vo.prndayX}px;top:${vo.prndayY}px;">
		2015.11.23
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		window.print();
	});
</script>
</body>
