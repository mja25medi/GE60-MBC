<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
	<mhtml:home_head mainPage="y" titleName="${USER_ORGNM}">
		<mhtml:head_module encryptjs="y"/>
	</mhtml:home_head>
	<mhtml:home_body subContent="Y" subMenu="Y">
				<form id="frm" name="frm" action="/KnowHome.do">
				<input type="hidden" name="cmd" id="cmd">
				<input type="hidden" name="knowSn" id="knowSn">
				<div class="content-section">				
					<div class="contentWrap">
						<div class="contDesc2">
							<spring:message code="home.study.mystudymessage1"/>
						</div>
						<div class="srhBtn-option">
							<span class="totalNum"><spring:message code="home.board.totcnt" arguments="${fn:length(knowStudyList) }"/></span>							
						</div>
						<div class="kw_list">
							<c:if test="${not empty knowStudyList }">
							<ul>
								<c:forEach items="${knowStudyList }" var="item">
								<li>
									<div class="infoImg">
										<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />" class="view-img">
											<c:if test="${item.knowType eq 'MOV' or item.knowType eq 'STD_MOV'}"><p class="icon_video" title="video"></p></c:if>
											<c:if test="${item.knowType eq 'LINK' or item.knowType eq 'DOC' or item.knowType eq 'IMG' or item.knowType eq 'HTML' or item.knowType eq 'STD_DOC'}"><p class="icon_book" title="book"></p></c:if>
											<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>">
										</a>
									</div>
									<div class="infoBox">
										<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />">
											<h3>${item.knowNm }</h3>
											<p>${item.knowDesc }</p>
										</a>
									</div>
									<ul class="kw_date">
										<li><spring:message code="home.study.studyprocess"/>:
											<em>
												<c:choose>
												<c:when test="${item.userStudyCnt eq item.studyCnt and item.studyCnt ne 0}"><spring:message code="home.study.complete"/></c:when>
												<c:otherwise><spring:message code="home.study.ing"/></c:otherwise>
												</c:choose>
											</em>
											(${item.userStudyCnt } <spring:message code="home.study.complete"/> / ${item.studyCnt } <spring:message code="home.know.know" />)
										</li>
										<li><spring:message code="home.study.examprocess"/>:
											
												<c:choose>
												<c:when test="${item.rateUseYn eq 'Y'}">
													<c:choose>
													<c:when test="${not empty item.cpltYn}">
														<em><spring:message code="home.study.complete"/></em>
														(<spring:message code="home.exam.correct"/> : ${item.rgtAnsrCnt } / ${item.totQstnCnt } <spring:message code="home.study.examquestion"/>)
													</c:when>
													<c:otherwise><em><spring:message code="home.study.beforeexam"/></em></c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise><em><spring:message code="home.study.noexam"/></em></c:otherwise>
												</c:choose>
											
										</li>
										<li><spring:message code="home.study.finishyn"/>:
											<em>
												<c:choose>
												<c:when test="${item.cpltUseYn eq 'Y'}">
													<c:if test="${item.cpltYn eq 'Y'}"><spring:message code="home.study.finishy"/></c:if>
													<c:if test="${item.cpltYn ne 'Y'}"><spring:message code="home.study.finishn"/></c:if>
												</c:when>
												<c:otherwise><spring:message code="home.study.nofinish"/></c:otherwise>
												</c:choose>
											</em>
										</li>
									</ul>
									<div class="btnArea">
										<c:choose>
										<c:when test="${item.cpltUseYn eq 'Y' and item.cpltYn eq 'Y' }">
											<a href="javascript:doPrintCert('${item.knowSn }');" class="btn btn-blue"><spring:message code="home.study.finishcert"/></a>
										</c:when>
										<c:otherwise>
											<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />" class="btn btn-red"><spring:message code="home.study.dostudy"/></a>
										</c:otherwise>
										</c:choose>
										<a href="javascript:delUserKnowStudy('${item.knowSn }');" class="btn"><spring:message code="home.study.cancelstudy"/></a>
									</div>
								</li>
								</c:forEach>
							</ul>
							<p class="clearFix"></p>
							</c:if>
							<c:if test="${empty knowStudyList}">
								<p class="clearFix more" style="height:80px"></p>
								<center class="more" style="font-size:20px"><spring:message code="home.study.nodata"/></center>
							</c:if>
						</div>
					</div>
				</div>
				</form>
	</mhtml:home_body>
	<script type="text/javascript">
	
	/* 사용자 지식학습 삭제 */
	function delUserKnowStudy(knowSn){
		if(!confirm("<spring:message code="home.study.cancelstudymessage1"/>")){
			return;
		}
		
		$("#frm #cmd").val("delUserKnowStudy");
		$("#frm #knowSn").val(knowSn);
		$('#frm').ajaxSubmit(function(resultVO) {
			if(resultVO.result >= 0) {
				// 정상 처리
				window.location.reload();
			} else {
				// 비정상 처리
				alert("<spring:message code="home.study.alreadydelete"/>");
			}			
		});
	}
	
	function doPrintCert(knowSn){
		
		var popUrl = "/KnowHome.do?cmd=printCert&knowSn="+knowSn;
		var popOption = "width=800, height=600, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"certPop",popOption);
		
	}
	
	</script>
</mhtml:home_html>