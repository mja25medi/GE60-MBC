<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsSbjList" value="${openCrsSbjList}"/>
<c:set var="openCrsVO" value="${vo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>
<mhtml:home_body>
				<mhtml:home_location />
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default" >
							<div class="panel-heading">
								<span class="panel-title"><i class="glyphicon glyphicon-book"></i> <spring:message code="lecture.title.classroom.lecture.contents"/></span>
								<div class="pull-right" style="margin-top:-3px;">
									<select name="sbjCd" id="sbjCd" onchange="listContents()" class="form-control input-sm" >
									<c:forEach items="${openCrsSbjList}" var="item">
										<option value="${item.sbjCd}">${item.sbjNm}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div id="contentsListArea">
								<ul class="list-group">
									<li class="list-group-item"><spring:message code="lecture.message.contents.list.loading"/></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="text-right">
					<a href="/home/openCourse/main" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
				</div>
				<iframe id='edutrackAPIFrame' name='edutrackAPIFrame' width='0' height='0' frameborder='0' src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" ></iframe>

<script type="text/javascript">
	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		<c:if test="${fn:length(openCrsSbjList) > 0}">
			listContents();
		</c:if>
	});

	/**
	 * 콘텐츠 목록 조회
	 */
	function listContents() {
		var sbjCd = $("#sbjCd > option:selected").val();
		$("#contentsListArea")
			.load(cUrl("/home/openCourse/listCnts"),
				{ 
				  "crsCd" : "${vo.crsCd}",
				  "sbjCd" : sbjCd
				});
	}

	function viewContents(sbjCd, unitCd) {
		var url = cUrl("/home/openCourse/viewCntsPop")+"?sbjCd="+sbjCd+"${AMPERSAND}unitCd="+unitCd;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=auto,resizable=yes,width=1000,height=700";
		var contentsWin = window.open(url, "contentsWin", winOption);
		if(contentsWin != null){
			contentsWin.focus();
		}
	}
</script>
</mhtml:home_body>
</mhtml:home_html>