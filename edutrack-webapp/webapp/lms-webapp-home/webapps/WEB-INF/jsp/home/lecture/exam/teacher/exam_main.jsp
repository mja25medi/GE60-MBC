<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="examListVO" value="${examListVO}"/>
<c:set var="examVO" value="${vo}"/>
<c:set var="subjectList" value="${subjectList}"/>
<c:set var="examCntChk" value="0"/>
<c:set var="examStartNo" value="0"/>
                <div class="learn_top m_column_row">
                    <div class="left_title">
                        <h3>시험채점</h3>
                    </div>
                    <div class="right_btn">
                        <button type="button" class="btn type4" onclick="examWrite()">시험등록</button>
                    </div>
                </div>
                <c:forEach var="item" items="${examListVO}" varStatus="status">
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
	                       	<c:choose>
								<c:when test="${item.examTypeCd eq 'ON'}">  <label class="online">온라인</label></c:when>
								<c:when test="${item.examTypeCd eq 'OFF'}"> <label class="offline">오프라인</label></c:when>
							</c:choose>	
                            <h4><c:if test="${item.semiExamYn eq 'Y'}">[진행단계평가]</c:if>${item.examTitle }</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                                <c:choose>
                           			<c:when test="${item.examStareTypeCd eq 'R' }">
	                                	<li>
	                                		<strong>시험기간</strong>
	                              			${item.examStartDttm} ~ ${item.examEndDttm}
	                                	</li>
                               		</c:when>
                           			<c:otherwise>
                           			 	<li>
	                                		<strong>응시가능진도율</strong>
	                               			<spring:message code="lecture.message.exam.progress.ratio"/> ${item.stareCritPrgrRatio}% <spring:message code="lecture.message.exam.over"/>
	                                	</li>
                           			</c:otherwise>
                           		</c:choose>
                             	<c:choose>
                           			<c:when test="${item.regYn eq 'Y'}"> <li><strong>공개여부</strong>공개</li></c:when>
                        			<c:otherwise><li><strong>공개여부</strong>비공개</li></c:otherwise>
                           		</c:choose>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="basic" onclick="javascript:examInfo('${item.examSn}')">시험정보</button>
                                <button type="button" class="basic" onclick="examEdit('${item.examSn}');">수정</button>
                                <button type="button" class="secondary" onclick="location.href='/lec/exam/editFormExamMain?examSn=${item.examSn}&crsCreCd=${examVO.crsCreCd}'">채점</button>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
			   	<c:if test="${empty examListVO}">
					<div class="segment">
						<div class="learn_info">
							<h3>
								<spring:message code="lecture.message.exam.nodata" />
							</h3>
						</div>
					</div>
				</c:if>
					
				<form id="examForm" name="examForm" onsubmit="return false" >
					<input type="hidden" name="crsCreCd" value="${examVO.crsCreCd}" />
					<input type="hidden" name="examSn" id="examSn" value="${examVO.examSn}"/>
				</form>


<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	var scores = [];
	var ItemDTO = new Object();
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "examAddPop"
		});
		ItemDTO.crsCreCd = '${examVO.crsCreCd}';
		ItemDTO.semiExamYn = '${examVO.semiExamYn}';
	});


	function listExam(){
		var sbjCd = $("#sbjCd option:selected").val();
		var url = cUrl("/lec/exam/examMain")+"?crsCreCd="+ItemDTO.crsCreCd;
		$(location).attr('href',url);
	}

	function examInfo(examSn){ //시험정보
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/exam/viewExamPop"/>"
			+ "?examSn="+examSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 500);
		modalBox.setTitle("<spring:message code="lecture.title.exam.info"/>");
		modalBox.show();
	}
	
	
	function examWrite() {
		var url = generateUrl("/lec/exam/addExamMain?crsCreCd="+ItemDTO.crsCreCd);
		document.location.href = url;
	}
	
	/**
	 * 시험 정보 수정 폼
	 */
	function examEdit(examSn) {
		var url = generateUrl("/lec/exam/editExamMain?crsCreCd="+ItemDTO.crsCreCd+"&examSn="+examSn);
		document.location.href = url;
		
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
</script>
