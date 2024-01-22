<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
				
				<div class="learn_top">
                    <div class="left_title">
                        <h3>과정정보</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>강의계획서</label></li>
<%--                             		<c:if test="${not empty vo.attachFiles}">
										<c:forEach var="fileItem" items="${vo.attachFiles}" varStatus="status">
											<li>
												<a href="javascript:fileDown('${fileItem.fileSn}');" class="file_down">
													<img src="../../../tpl/002/img/board/file_doc.png" alt="">
													<span class="text">${fileItem.fileNm}</span>
													<span class="fileSize fileSize${status.index}">
														<script type="text/javascript">
															var fileSize = "${fileItem.fileSize}";
															var index = "${status.index}";
															fileSize = formatSizeUnits(fileSize);
															
															$(".fileSize"+index).html("("+fileSize+")");
														</script>
													</span>
												</a>
												<span class="link">
													<a class="btn-line btn-down" href="#_none" onclick="javascript:fileDown('${fileItem.fileSn}');" title="다운로드">다운로드<i></i></a>
												</span>
											</li>
										</c:forEach>
									</c:if> --%>
									<li>
										<c:forEach var="fileItem" items="${courseVO.attachFiles}" varStatus="status">
			                           		<a class="btn-line btn-down" href="#" onclick="javascript:fileDown('${fileItem.fileSn}');" title="강의계획서 다운로드">다운로드<i></i></a>
			                           	</c:forEach>
		                           	</li>
                            <li class="head"><label>교육비</label></li>
                            <li>
                            	<c:if test="${createCourseVO.eduPrice eq 0 }">무료</c:if>
                            	<c:if test="${createCourseVO.eduPrice ne 0 }">
                            		<fmt:formatNumber value="${createCourseVO.eduPrice }" pattern="#,###" />원
                            	</c:if>
                            </li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>기수명</label></li>
                            <li>${createCourseVO.creTerm }</li>
                            <li class="head"><label>1일 수강 제한</label></li>
                            <li>
                            <c:if test="${empty createCourseVO.dayStudyLimit}">-</c:if>
                            <c:if test="${not empty createCourseVO.dayStudyLimit}">${createCourseVO.dayStudyLimit }차시</c:if>
                            </li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습정원</label></li>
                            <li>${createCourseVO.eduNop }명</li>
                            <li class="head"><label>학습방법</label></li>
                            <li><meditag:codename code="${createCourseVO.crsOperType}" category="CRS_OPER_TYPE" /></li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습개요</label></li>
                            <c:if test="${empty courseVO.crsDesc }">
                            	<li>설명 내용이 등록되지 않았습니다.</li>
                            </c:if>
                            <c:if test="${not empty courseVO.crsDesc }">
                            	<li class="list_desc">${courseVO.crsDesc }</li>
                            </c:if>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습목표</label></li>
                            <c:if test="${empty courseVO.eduPrps }">
                            	<li>설명 내용이 등록되지 않았습니다.</li>
                            </c:if>
                            <c:if test="${not empty courseVO.eduPrps }">
                            	<li class="list_pre">${courseVO.eduPrps }</li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                
<script type="text/javascript">
//팝업박스
var modalBox = null;
$(document).ready(function() {
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
	
	$(".modal").hide();
});

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

</script>
