<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="creCrsReshList" value="${creCrsReshList}"/>
<c:url var="img_base" value="/img/home" />
			<div class="learn_top">
                    <div class="left_title">
                        <h3>강의평가</h3>
                    </div>
                </div>
                	<c:forEach var="item" items="${creCrsReshList}" varStatus="status">
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <!-- <label class="online">온라인</label> -->
                           	<h4 title="${item.reshTitle }">
								${fn:substring(item.reshTitle,0, 30)}<c:if test="${fn:length(item.reshTitle) > 30 }">...</c:if>
							</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                                <li><strong><spring:message code="lecture.title.research.duration"/></strong><meditag:dateformat type="8" delimeter="." property="${item.startDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${item.endDttm}"/></li>
                            </ul>
                            <div class="button_group">
                      			<c:choose>
								<c:when test="${item.joinYn eq 'Y' }">
								<button type="button" class="default"  onClick="javascript:alert('<spring:message code="lecture.message.research.alert.join"/>');"><spring:message code="button.join.research.complete"/></button>
								</c:when>
								<c:when test="${item.ansrUseYn eq 'N' }">
									<button type="button" class="primary"  onClick="javascript:alert('<spring:message code="lecture.message.research.alert.duration"/>');" class="btn3 solid fcBlue sm"><spring:message code="button.join.research"/></button>
								</c:when>
								<c:otherwise>
								<button type="button" class="primary"  onClick="goJoin('${item.crsCreCd}','${item.reshSn}')" class="btn3 solid fcBlue sm"><spring:message code="button.join.research"/></button>
								</c:otherwise>
							</c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
                <c:if test="${empty creCrsReshList}">
					<div class="segment">
						<div class="course_list test_custom">
							<div class="item">
								* 등록된 강의평가가 없습니다.
							</div>
						</div>
					</div>
				</c:if>

                <script>
                function goJoin(cd, sn){
                	 document.location.href = cUrl("/lec/creCrsResh/joinMain")+"?crsCreCd"+cd+"&reshSn="+sn;
                }
                
                </script>