<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="imageBase" value="/img/"/>


                <div class="learn_top">
                    <div class="left_title">
                        <h3>과목별 리스트</h3>
                    </div>
                </div>
                <c:forEach items="${sbjList }" var="sbjList">
		                <div class="segment">
		                    <div class="learn_info">
		                        <div class="header">
				                	<c:if test="${sbjList.sbjType eq 'OFF'}"><label class="offline">오프라인</label></c:if>
				                	<c:if test="${sbjList.sbjType eq 'ON'}"><label class="online">온라인</label></c:if>
				                	<c:if test="${sbjList.sbjType eq 'BL'}"><label class="mixing">혼합</label></c:if>
		                            <h4>${sbjList.sbjNm }</h4>
		                        </div>       
		                        	<c:if test="${sbjList.sbjType eq 'ON' }"><span class="location">온라인</span></c:if>
									<c:if test="${sbjList.sbjType eq 'OFF' }">
										<c:choose>
											<c:when test='${createCourseVO.oflnEduPlace eq null || createCourseVO.oflnEduPlace eq ""}'></c:when>
											<c:otherwise><span class="location">${createCourseVO.oflnEduPlace}</span></c:otherwise>
										</c:choose>
									</c:if>                 
		                    </div>
		                    <div class="course_list">
			                        <div class="item">
			                            <div class="title"></div>
			                            <div class="button_group">
			                                <button type="button" class="primary" onclick="listViewTch('${sbjList.sbjCd}')">과목보기</button>
			                            </div>
			                        </div>
		                    </div>
		                </div>
                </c:forEach>
