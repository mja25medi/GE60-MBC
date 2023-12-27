<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
		<!-- //공통 상단 부분 -->
		<div class="container repair">
			<div id="cd_shift" class="col-lg-4">
				<div class="panel calendar">
					<div class="panel-heading">
						<span class="panel-title"><spring:message code="course.title.createcourse.calendar.month"/></span>
					</div>
					<div class="panel-body">
						<span class="monthPre" style="font-size:12px;"><a href="#none"><i class="glyphicon glyphicon-chevron-left"></i><spring:message code="common.title.pervmonth"/></a></span>
						<span class="monthNext" style="font-size:12px;"><a href="#none"><spring:message code="common.title.nextmonth"/><i class="glyphicon glyphicon-chevron-right"></i></a></span>
						<strong></strong>
					</div>
				</div>
			</div>
			<div id="mv_shift" class="col-lg-8">
				<div class="mainvisual">
					<div class="cycle-slideshow"
						data-cycle-slides="> .slide"
						data-cycle-speed=""
						data-cycle-timeout="5000"
						data-cycle-pause-on-hover="true"
						data-cycle-stop=".btnStop"
						data-cycle-next=".btnPlay"
						data-cycle-pager="#adv-custom-pager"
						data-cycle-pager-template="<a href='#'></a>" >
						<c:forEach var="mainImg" items="${orgImgList}" varStatus="status">
							<c:if test="${empty mainImg.connUrl}">
						<p class="slide" style="background:#28211e url('<c:url value="/app/file/view2/${mainImg.bkgrFileSn}"/>') center top no-repeat;background-size : cover;"><span>${mainImg.bkgrImgNm}</span></p>
							</c:if>
							<c:if test="${not empty mainImg.connUrl}">
								<c:set var="target" value="_blank"/>
								<c:if test="${not empty mainImg.connTrgt}"><c:set var="target" value="${mainImg.connTrgt}"/></c:if>
						<a href="${mainImg.connUrl}" target="${target}" class="slide" style="background:#28211e url('<c:url value="/app/file/view2/${mainImg.bkgrFileSn}"/>') center top no-repeat;background-size : cover;"><span>${mainImg.bkgrImgNm}</span></a>
							</c:if>
						</c:forEach>
						<div class="pagerWrap">
							<span id="adv-custom-pager" title="<spring:message code="button.slide.rolling"/>"></span>
							<button class="btnStop" data-cycle-cmd="pause">
								<spring:message code="button.slide.stop"/>
							</button>
							<button class="btnPlay blind" data-cycle-cmd="resume">
								<spring:message code="button.slide.start"/>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<c:set var="sampleContents" value="${fn:split(openCrsVO.sampleCntsPath,'@$')}"/>
				<c:set var="cntsTypeCd" value="${sampleContents[0]}"/>
				<c:set var="sampleCntsPath" value="${sampleContents[1]}"/>
				<div class="panel player">
					<span class="player-title"><spring:message code="course.open.title.opencourse"/></span>
					<span class="player-txt">${openCrsVO.crsNm}</span>
					<span class="more_btn"><a href="<c:url value="/home/main/goMenuPage"/>?mcd=${openCrsMcd}"><img src="/tpl/001/img/home/more_btn.png" alt="more"></a></span>
					<c:if test="${cntsTypeCd eq 'LOCAL'}">
					<video class="video" poster="" width="100%" height="auto" controls="" preload="" style="margin-top:30px;">
						<source src="${sampleCntsPath}">
					</video>
					</c:if>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="panel panel_board">
					<div class="panel-heading">
						<span class="panel-title"><spring:message code="board.title.bbs.notice"/></span>
						<span class="more_btn"><a href="<c:url value="/home/main/goMenuPage"/>?mcd=${noticeMcd}"><img src="/tpl/001/img/home/more_btn.png" alt="more"></a></span>
					</div>
					<div class="panel-body">
						<ul class="board">
							<c:forEach var="item" items="${noticeList}">
							<c:url var="noticeUrl" value="/home/main/goMenuPage?mcd=${noticeMcd}&subParam=read@$bbsCd=NOTICE@$atclSn=${item.atclSn}"/>
							<li>
								<span><a href="${noticeUrl}" title="${item.atclTitle}">${fn:substring(item.atclTitle,0, 13)}<c:if test="${fn:length(item.atclTitle) > 15 }">...</c:if><c:if test="${item.recently eq 'NEW' }"><img src="/img/common/new.png" /></c:if></a></span>
								<span class="date"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></span>
							</li>
							</c:forEach>
							<c:if test="${empty noticeList}">
							<li><span style="width:100%"><spring:message code="board.message.bbs.atcl.nodata"/></span></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="panel panel_board">
					<div class="panel-heading">
						<span class="panel-title"><spring:message code="board.title.bbs.pds"/></span>
						<span class="more_btn"><a href="<c:url value="/home/main/goMenuPage"/>?mcd=${archiveMcd}"><img src="/tpl/001/img/home/more_btn.png" alt="more"></a></span>
					</div>
					<div class="panel-body">
						<ul class="board">
							<c:forEach var="item" items="${pdsList}">
<%-- 							<c:url var="pdsUrl" value="/home/main/goMenuPage?amp;mcd=${archiveMcd}&amp;subParam=read@$bbsCd=PDS@$atclSn=${item.atclSn}"/> --%>
							<c:url var="pdsUrl" value="/home/main/goMenuPage?mcd=${archiveMcd}&subParam=read@$bbsCd=PDS@$atclSn=${item.atclSn}"/>
							<li>
								<span><a href="${pdsUrl}" title="${item.atclTitle}">${fn:substring(item.atclTitle,0, 13)}<c:if test="${fn:length(item.atclTitle) > 15 }">...</c:if><c:if test="${item.recently eq 'NEW' }"><img src="/img/common/new.png" /></c:if></a></span>
								<span class="date"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></span>
							</li>
							</c:forEach>
							<c:if test="${empty pdsList}">
							<li><span style="width:100%"><spring:message code="board.message.bbs.atcl.nodata"/></span></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="./inc/quick_menu.jsp" />

		<c:if test="${not empty bannerList}">
		<div class="footInf">
			<div id="banner">
				<ul>
						<c:forEach var="item" items="${bannerList}">
					<c:if test="${empty item.bnnrUrl }">
						<li>
							<img class="img-responsive" src="/app/file/view2/${item.bnnrImgSn}" alt="${item.bnnrNm}" style="width:200px;height:48px;">
						</li>
					</c:if>

					<c:if test="${not empty item.bnnrUrl}">
						<c:set var="bnnrTarget" value="_blank"/>
						<c:if test="${not empty item.bnnrTrgt}"><c:set var="bnnrTarget" value="${item.bnnrTrgt}"/></c:if>
						<li>
							<a href="${item.bnnrUrl}" target="${bnnrTarget}"><img class="img-responsive" src="/app/file/view2/${item.bnnrImgSn}" alt="${item.bnnrNm}" style="width:200px;height:48px;"></a>
						</li>
					</c:if>
						</c:forEach>
					</ul>
			</div>
		</div>
		</c:if>
		
<c:if test="${not empty ALERT_MESSAGE}">
	<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
	<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
	<script type="text/javascript">
		//$.growlUI('알림', '${flashMsg}');
		$(document).ready(function() {
			alert('${flashMsg}');
		});
	</script>
</c:if>	


	