<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
	<mhtml:home_head mainPage="y" titleName="${USER_ORGNM}">
		<mhtml:head_module encryptjs="y"/>
	</mhtml:home_head>
	<mhtml:home_body subContent="Y" subMenu="Y">
				<div class="content-section">
					<div class="contentWrap">
						<div class="contDesc2">
							<spring:message code="home.bkmk.mybkmkmessage1"/>
						</div>
						<div class="srhBtn-option">
							<span class="totalNum"><spring:message code="home.board.totcnt" arguments="${totalCount }"/></span>
							<div class="btn_opt">
								<a href="<c:url value="/home/main/goMenuPage?mcd=${MENUCODE}&subParam=mainMyPageBkmk@$viewType=list" />" class="btn list"><spring:message code="home.bkmk.viewlist"/></a>
								<a href="<c:url value="/home/main/goMenuPage?mcd=${MENUCODE}&subParam=mainMyPageBkmk@$viewType=img" />" class="btn grid"><spring:message code="home.bkmk.viewimg"/></a>
							</div>
						</div>
						<div class="mypage">
						<div class="listBox">							
							<div class="grid-content">
								<c:if test="${not empty bkmkList }">
								<c:forEach items="${bkmkList}" var="item" varStatus="status">
								<ul class="grid-content-box size_1">
									<li>
										<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />" class="view-img">
											<c:if test="${item.knowType eq 'MOV' or item.knowType eq 'STD_MOV' or (item.knowType eq 'LINK' and item.convSts eq '9') }"><p class="icon_video" title="video"></p></c:if>
											<c:if test="${item.knowType eq 'DOC' or item.knowType eq 'IMG' or item.knowType eq 'HTML' or item.knowType eq 'STD_DOC' or (item.knowType eq 'LINK' and empty item.convSts)}"><p class="icon_book" title="book"></p></c:if>
											<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>">
										</a>
									</li>
									<li>
										<dl>
											<dt>
												<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />">${item.knowNm}</a>
												<div class="ico-del"><a href="javascript:delBkmk('${item.knowSn}');"><spring:message code="home.board.delete"/></a></div>
											</dt>
											<dd>
												<c:forEach begin="1" end="5" step="1" var="i">
													<c:if test="${i le item.satisAvgScore}"><img src="<c:url value="/001/img/star_full.png"/>" alt="1"></c:if>
													<c:if test="${i gt item.satisAvgScore}">
														<c:choose>
															<c:when test="${(i-item.satisAvgScore) lt 1}"><img src="<c:url value="/001/img/star_half.png"/>" alt="0.5"></c:when>
															<c:otherwise><img src="<c:url value="/001/img/star_none.png"/>" alt="0"></c:otherwise>
														</c:choose>
													</c:if>							
												</c:forEach>
											</dd>
											<dd>
												${item.knowCtgrCdLvl1Nm} <c:if test="${not empty item.knowCtgrCdLvl2}"> &gt; ${item.knowCtgrCdLvl2Nm} </c:if>
											</dd>
											<c:if test="${not empty item.sechWords}">
												<dd><i class="fa fa-tag"></i>
												<c:set var="sechWordList" value="${fn:split(item.sechWords,',')}" />
												<c:forEach var="sechWord" items="${sechWordList}" varStatus="status">
												     <a href="<c:url value="/home/main/goMenuPage?mcd=${searchFullMcd}&subParam=main@$searchValue=${fn:escapeXml(sechWord)}" />">${sechWord}</a><c:if test="${!status.last}">, </c:if>
												</c:forEach> 
												</dd>
											</c:if>
										</dl>
									</li>
								</ul>
								</c:forEach>
								</c:if>
								<p class="clearFix"></p>
								<c:if test="${empty bkmkList}">
									<p class="clearFix more" style="height:80px"></p>
									<center class="more" style="font-size:20px"><spring:message code="home.bkmk.nodata"/></center>
								</c:if>
							</div>				
						</div>
						</div>
					</div>
				</div>
				
	</mhtml:home_body>
	
<script type="text/javascript">

function setBookmarkIcon() {
	/********** 관심컨텐츠 switch-toggle **********/
	$("input[type=checkbox].switch_fa").each(function() {
		$(this).before(
			'<span class="switch_fa">' +
			'<span class="mask" /><span class="background" />' +
			'</span>'
		);	
		$(this).hide();
		if (!$(this)[0].checked) {
			$(this).prev().find(".background").css({left: "-18px"});
		}
	});
	$("span.switch_fa").click(function() {
		if ($(this).next()[0].checked) {
			$(this).find(".background").animate({left: "-18px"}, 0);
		} else {
			$(this).find(".background").animate({left: "0px"}, 0);
		}
		$(this).next()[0].checked = !$(this).next()[0].checked;
	});
}

function delBkmk(knowSn){
	if(confirm("<spring:message code="home.bkmk.cancelbkmkmessage1"/>")){
		$.getJSON("<c:url value="/UserKnowHome.do"/>"
				, {"cmd" : "delBkmk"
					, "knowSn"	: knowSn}
				, function(resultVO){
					if(resultVO.result > 0) window.location.reload();
				});
	}
}
</script>	
	
</mhtml:home_html>