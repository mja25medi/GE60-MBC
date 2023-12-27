<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="createCourseVO" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="paging" value="Y"/>
	
	<c:if test="${not empty courseList}">
		<div class="like-table mt40" data-toggle="like-table">
		    <div class="thead">                                
		        <!-- <span class="th w10">신청자 이름</span> -->
		        <span class="th">기수명</span>
		        <span class="th">모집과정</span>
		        <span class="th ">신청기간</span>
		        <span class="th ">수강기간</span>
		    </div>
		    <ul class="tbody">
		    	<c:forEach items="${courseList}" var="item" varStatus="status">
		    		<c:if test="${status.index eq 0}">
				        <li class="tr">
				            <!-- <dl class="td"><dt>신청자 이름</dt><dd>학습자2</dd></dl> -->
				            <dl class="td"><dt>기수명</dt><dd>${item.crsYear }년도 ${item.crsTerm }기수</dd></dl> 
				            <dl class="td"><dt>모집과정</dt><dd>${pageInfo.totalRecordCount } 과정</dd></dl>
				            <dl class="td"><dt>신청기간</dt><dd><meditag:dateformat type="1" property="${item.enrlAplcStartDttm }"/> ~ <meditag:dateformat type="1" property="${item.enrlAplcEndDttm }"/></dd></dl>
				            <dl class="td"><dt>수강기간</dt><dd><meditag:dateformat type="1" property="${item.enrlStartDttm }"/> ~ <meditag:dateformat type="1" property="${item.enrlEndDttm }"/></dd></dl>
				        </li>                        
			        </c:if>
		        </c:forEach>
		    </ul>
		</div>
	</c:if>
	
	<div class="like-table mt40" data-toggle="like-table">
		<c:if test="${empty courseList}">
			<ul class="tbody">
	    		<li class="tr alert">
	    			<div class="cnt"> 
						<b>신청 가능한 과정이 없습니다.</b>
					</div>
	    		</li>
    		</ul>
    	</c:if>
    	
    	<c:if test="${not empty courseList}">
	    <div class="thead">                                
	        <span class="th">과정명</span>
	        <span class="th w10">강의계획서</span>
	        <span class="th">샘플강의</span>
	        <span class="th ">결제금액</span>
	        <span class="th ">수강신청</span>
	    </div>
	    <ul class="tbody">
	    		<c:forEach items="${courseList}" var="item" varStatus="status">
					<li class="tr">
			            <dl class="td txt-left"><dt>과정명</dt><dd>${item.crsCreNm}</dd></dl>
			            <dl class="td"><dt>강의계획서</dt><dd>
			            		<c:choose>
									<c:when test="${not empty item.attachFiles }">
										<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status">
											<a href="/app/file/download/${fileItem.fileSn }" class="btn" title="강의계획서"><i class="xi-paper-o xi-x" aria-hidden="true"></i></a>
										</c:forEach>
									</c:when>
									<c:otherwise>
										
									</c:otherwise>
								</c:choose>
			            	</dd>
			            </dl>
			            <dl class="td"><dt>샘플강의</dt><dd>
			            		<c:if test="${item.contentsCnt > 0 }">
									<a href="javascript:previewContents('${item.sbjCd }')" class="btn solid bluegreen" title="샘플강의보기"><i class="xi-play-circle-o xi-x" aria-hidden="true"></i> 맛보기</a>
								</c:if>
			            	</dd>
			            </dl>
			            <dl class="td"><dt>결제금액</dt><dd>
			            		<c:choose>
				            		<c:when test="${item.eduPrice > 0 }"><fmt:formatNumber value="${item.eduPrice}"/>원</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</dd>
			            </dl>
			            <dl class="td"><dt>수강신청</dt><dd>
			            		<c:choose>
									<c:when test="${item.enrlYn eq 'Y' }">
										<c:choose>
											<c:when test="${item.enrlAplcYn eq 'N'}">
												<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.eduend"/>')" title="<spring:message code="course.title.createcourse.eduend"/>" class="btn solid gray"><spring:message code="course.title.createcourse.eduend"/></a>
											</c:when>
											<c:otherwise>
												<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.enrollend"/>')" title="<spring:message code="course.title.createcourse.enrollend"/>" class="btn solid gray"><spring:message code="course.title.createcourse.enrollend"/></a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:if test="${item.enrlAplcYn eq 'B'}">
											<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.noduration"/>')" title="<spring:message code="course.title.createcourse.noduration"/>" class="btn solid gray"><spring:message code="course.title.createcourse.noduration"/></a>
										</c:if>
										<c:if test="${item.enrlAplcYn eq 'Y'}">
											<c:choose>
												<%-- <c:when test="${item.nopLimitYn eq 'Y' && (item.enrlCnt + item.cnfmCnt) >= item.enrlNop }"> --%>
												<c:when test="${item.nopLimitYn eq 'Y' && (item.stuCnt) >= item.enrlNop }">
													<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.stdover"/>')" title="<spring:message code="course.title.createcourse.stdover"/>" class="btn solid gray"><spring:message code="course.title.createcourse.stdover"/></a>
												</c:when>
												<c:otherwise>
													<c:if test="${not empty USERNO}">
														<c:choose>
															<c:when test="${item.bskCnt == 0  }">
																<a href="javascript:addBasket('${item.crsCreCd}');" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn solid navy">바구니 담기</a>
															</c:when>
															<c:when test="${item.bskCnt > 0  }">
																<a href="javascript:removeBasket('${item.crsCreCd}');" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs">바구니 삭제</a>
															</c:when>
															<c:otherwise>
																-
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${empty USERNO}">
														<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.login"/>')" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn solid gray"><spring:message code="course.title.createcourse.enroll"/></a>
													</c:if>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${item.enrlAplcYn eq 'I'}">
											<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.noduration"/>')" title="<spring:message code="course.title.createcourse.studying"/>" class="btn solid gray"><spring:message code="course.title.createcourse.studying"/></a>
										</c:if>
										<c:if test="${item.enrlAplcYn eq 'N'}">
											<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.eduend"/>')" title="<spring:message code="course.title.createcourse.eduend"/>" class="btn solid gray"><spring:message code="course.title.createcourse.eduend"/></a>
										</c:if>
									</c:otherwise>
								</c:choose>
			            	</dd>
			            </dl>
			        </li>     	    			
	    		</c:forEach>
	    	</ul>
    	</c:if>
	</div>
	
	<c:if test="${not empty courseList && not empty pageInfo }">
		<div class="board_pager">
			<span class="inner"> 
				<meditag:paging pageInfo="${pageInfo}" funcName="courseSearch" name="front"/>
			</span>
		</div>
	</c:if>
