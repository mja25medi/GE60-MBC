<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
	<mhtml:home_head mainPage="y" titleName="${USER_ORGNM}">
		<mhtml:head_module encryptjs="y"/>
	</mhtml:home_head>
	<mhtml:home_body subContent="Y" subMenu="Y">
				<div class="content-section">
					<div class="contentWrap">
						<c:if test="${!empty recmKnowList }">
							<article class="re-content">
								<h2><spring:message code="home.know.recmcts"/></h2>
								<ul class="one-bxslider">
									<c:forEach items="${recmKnowList }" var="item" varStatus="status">
										<li>
											<div class="infoBox">
												<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />">
													<h3>${item.knowNm }</h3>
													<p>${item.knowDesc }</p>
												</a>
											</div>
											<div class="infoImg">
												<!-- TODO : 뷰어 연동 후 수정 -->
												<c:choose>
												<c:when test="${item.knowType eq 'MOV'}">
<!-- 													<iframe width="320" height="193" src="https://www.youtube.com/embed/jydoV7r6ZOY" frameborder="0" class="fl" style="margin:-20px 0;" allowfullscreen></iframe> -->
													<iframe width="320" height="193" src="${MOV_BASE}?mediaID=${item.connKey}" frameborder="0" class="fl" allowfullscreen></iframe>
												</c:when>
												<c:when test="${item.knowType eq 'LINK' and item.convSts eq '9'}">
													<!-- 2017.04.25 링크(동영상) 추가 -->
													<iframe width="320" height="193" src="${item.startFile }" frameborder="0" class="fl" allowfullscreen></iframe>
												</c:when>
												<c:otherwise>
													<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />" class="view-img">
														<c:if test="${item.knowType eq 'MOV' or item.knowType eq 'STD_MOV' or (item.knowType eq 'LINK' and item.convSts eq '9') }"><p class="icon_video" title="video"></p></c:if>
														<c:if test="${item.knowType eq 'DOC' or item.knowType eq 'IMG' or item.knowType eq 'HTML' or item.knowType eq 'STD_DOC' or (item.knowType eq 'LINK' and empty item.convSts)}"><p class="icon_book" title="book"></p></c:if>
														<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" alt="<spring:message code="home.know.ctsimg"/>"/>
													</a>
												</c:otherwise>
												</c:choose>
											</div>
										</li>
									</c:forEach>
								</ul>
							</article>
						</c:if>
						
						<!-- TODO 맞춤지식 검색엔진 AREA -->
						<c:if test="${not empty data.RcdKnowSearchList}">
							<c:if test="${data.rcd_TotalSize > 0}">
								<div class="subTit">
									<h4><spring:message code="home.know.matchknow"/></h4>
									<p class="setting"><a href="/home/main/goMenuPage?mcd=MH06005000" class="btn"><spring:message code="home.know.ctgrsetting"/></a></p>
								</div>
							<div class="mypage">	
								<div class="listBox">							
									<div class="grid-content">
										<c:forEach var="searchList" items="${data.RcdKnowSearchList}" varStatus="status" > 
													<ul class="grid-content-box size_1">
														<li>
															<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${searchList.know_sn}" />" class="view-img">
																<c:if test="${searchList.knowType eq 'MOV' or searchList.knowType eq 'STD_MOV'}"><p class="icon_video" title="video"></p></c:if>
																<c:if test="${searchList.knowType eq 'LINK' or searchList.knowType eq 'DOC' or searchList.knowType eq 'IMG' or searchList.knowType eq 'HTML' or searchList.knowType eq 'STD_DOC'}"><p class="icon_book" title="book"></p></c:if>
																<img src="<c:url value="/app/file/thumb/${searchList.thumb_file_sn }"/>">
															</a>
														</li>
														<li>
															<dl>
																<dt>
																	<a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${searchList.know_sn}" />">${searchList.know_nm }</a>
																	<c:set var="bookmarkVal" value="0" />
																	<c:if test="${searchList.bookmark_cnt eq 0}"><c:set var="bookmarkVal" value="-18" /></c:if>
																	<span class="switch_fa" style="cursor:default"><span class="background" style="left:${bookmarkVal}px;"></span></span>
																</dt>
																<c:if test="${searchList.satis_avg_score ne '' && not empty searchList.satis_avg_score}">
																	<c:set value="${fn:split(searchList.satis_avg_score,'.')}" var="star"/>
																	<c:set value="0" var="starCount"/>
																	<dd>
																		<c:forEach begin="1" step="1" varStatus="i" end="${star[0]}">
																			<img src="<c:url value="/001/img/star_full.png"/>" alt="1" />
																			<c:set value="${starCount+1}" var="starCount"/>
																		</c:forEach>
																		<c:if test="${not empty star[1] && star[1] ne '0'}">
																			<img src="<c:url value="/001/img/star_half.png"/>" alt="0.5" />
																			<c:set value="${starCount+1}" var="starCount"/>
																		</c:if>
																		<c:forEach begin="1" step="1" varStatus="i" end="${5-starCount}">
																			<img src="<c:url value="/001/img/star_none.png"/>" alt="0" />
																		</c:forEach>
																		<span class="blind"><spring:message code="home.know.satis"/> ${searchList.satis_avg_score }</span>
																	</dd>
																</c:if>
																<dd>${searchList.lcate_nm } > ${searchList.mcate_nm }</dd>
																<dd><i class="fa fa-tag"></i>
																	<c:set var="wordsArr" value="${fn:split(searchList.sech_words,',')}"></c:set>
																	<c:forEach var="wordsList" items="${wordsArr}" varStatus="status" >
																		<a href="javascript:hashTagSearch('${wordsList}');">${wordsList }${status.count ne fn:length(wordsArr) ? ',' : ''}</a>
																	</c:forEach>
																</dd>
															</dl>
														</li>
													</ul>
												</c:forEach>
										<p class="clearFix"></p>
									</div>					
								</div>
								</div>
							</c:if>
						</c:if>
						<!-- END 맞춤지식 검색엔진 AREA -->
						
						<c:if test="${!empty rcntKnowList }">
						<div class="subTit mt25">
							<h4><spring:message code="home.know.rcntregknow"/></h4>
						</div>
						<div class="mypage">
							<div class="listBox">							
								<div class="grid-content">
									<c:forEach items="${rcntKnowList }" var="item" varStatus="status">
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
												<dt><a href="<c:url value="/home/main/goMenuPage?mcd=${viewKnowMcd}&subParam=view@$knowSn=${item.knowSn}" />">${item.knowNm}</a>
											<c:if test="${not empty USER_NO}">
												<c:set var="bookmarkVal" value="0" />
												<c:if test="${item.bookmarkCnt eq 0}"><c:set var="bookmarkVal" value="-18" /></c:if>
												<span class="switch_fa" style="cursor:default"><span class="background" style="left:${bookmarkVal}px;"></span></span>
											</c:if>
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
									<c:if test="${empty rcntKnowList}">
										<p class="clearFix more" style="height:60px"></p>
										<center class="more" style="font-size:20px"><spring:message code="home.know.rcntregnodata"/></center>
										<p class="clearFix more" style="height:60px"></p>
									</c:if>
								</div>					
							</div>	
						</div>
						</c:if>	
					</div>
				</div>
	</mhtml:home_body>
</mhtml:home_html>