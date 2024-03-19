<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
			
					<c:if test="${CLASSUSERTYPE eq 'STU' }">
				<div class="grid-row">
						<div class="m-box size-6">
	                       <div class="sub_title">평가반영비율</div> 
	                       <!-- 반응형을 위해 테이블 대신 ul li 사용, thead의 하위 span과 tbody > li 내의 dl 개수는 동일하여야 합니다. -->
	                        <div class="like-table" data-toggle="like-table">
	                            <div class="thead">
	                                <span class="th w20">진도</span>
	                                <span class="th w20">시험</span>
	                                <span class="th w20">과제</span>
	                                <span class="th w20">진행단계평가</span>
	                                <span class="th w20">총점</span>
	                            </div>
	                            <ul class="tbody">
	                                <li class="tr">
	                                    <dl class="td"><dt>진도</dt><dd>${createCourseVO.prgrRatio }점</dd></dl>
	                                    <dl class="td"><dt>시험</dt><dd>${createCourseVO.examRatio }점</dd></dl>
	                                    <dl class="td"><dt>과제</dt><dd>${createCourseVO.asmtRatio }점</dd></dl>
	                                    <dl class="td"><dt>진행단계평가</dt><dd>${createCourseVO.semiExamRatio }점</dd></dl>
	                                    <dl class="td"><dt>총점</dt><dd>100점</dd></dl>
	                                </li>  
	                            </ul>
	                        </div>
	                    </div>
	                    
	                    <div class="m-box size-6">
							<div class="sub_title">전체 진도율</div> 
							<!-- 메디오피아테크 부트스트랩 버전용 프로그래스로 변경하기 위해 레이아웃으로 사용하는 클래스 flex, wrap를 row, col-sm-*로 변경_221006 -->
							<dl class="row mt10">
								<dt class="col-sm-2 mt5 mb5">나의 진도율</dt>
								<dd class="col-sm-10 mt5 mb5">

									<!-- 메디오피아테크 부트스트랩 버전용 프로그래스로 변경_221006 -->
									<div class="progress">		  <!-- aria-valuenow, style=width: ~~%; 에 값을 넣어주세요 -->									
										<div class="progress-bar blue" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="60" style="width: ${mainLectureVO.prgrRatio }%;">${mainLectureVO.prgrRatio }%</div>
									</div>
								</dd>
							</dl>

							<dl class="row mt10">
								<dt class="col-sm-2 mt5 mb5">권장 진도율</dt>
								<dd class="col-sm-10 mt5 mb5">
									<div class="progress">
										<c:choose>
											<c:when test="${termDayCnt < 0 }">
												<div class="progress-bar green" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0" style="width: 0%;">0%</div>
											</c:when>
											<c:otherwise>
												<div class="progress-bar green" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="13" style="width: ${prpsRatio }%;">
													 ${prpsRatio}%
												</div>
											</c:otherwise>
										</c:choose>											
									</div>
								</dd>
							</dl>
						</div>
							</div>
	                </c:if>
                    
                    
                    <div class="learn_top">
                    <div class="left_title">
                        <h3>과정정보</h3>
                    </div>
                </div>
                    <div class="segment">
                    	<h4 class="seg_title">전체강의일정</h4>
		                    <ul class="message_box">
		                       <li>모든 평가에 응시하여 60점 이상을 받더라도 진도율 80% 이상을 진행하지 않으면 미수료 처리 됩니다.</li> 
		                       <li>과제를 임시저장하신 경우 최종제출 버튼을 클릭하여 제출하지 않으면 미응시로 간주되어 미수료 처리됩니다.</li> 
		                       <li>진도 점수는 각 차시별 진도율이 아닌 전체 출석에 대한 점수를 치환한 점수가 반영됩니다.</li> 
		                    </ul>
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>수강기간</label></li>
                            <li>
                               <c:choose>
									<c:when test="${createCourseVO.creOperTypeCd eq 'S' }">
										${createCourseVO.enrlDaycnt}일
									</c:when>
									<c:otherwise>
										${mainLectureVO.enrlDuration}
									</c:otherwise>
								</c:choose>
                            </li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험기간</label></li>
                            <c:choose>
								<c:when test="${empty mainLectureVO.examDuration}">
									<li>해당 없음</li>
								</c:when>
								<c:otherwise>
									<li>${mainLectureVO.examDuration}</li>
								</c:otherwise>
							</c:choose>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>진행단계</label></li>
                            <c:choose>
								<c:when test="${empty mainLectureVO.semiExamDuration}">
								  <li>해당 없음</li>
								</c:when>
								<c:otherwise>
								  <li>${mainLectureVO.semiExamDuration}</li>
									
								</c:otherwise>
							</c:choose>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>과제</label></li>
                            <c:choose>
								<c:when test="${empty mainLectureVO.asmtDuration}">
								  <li>해당 없음</li>
								</c:when>
								<c:otherwise>
								  <li>${mainLectureVO.asmtDuration}</li>
								</c:otherwise>
							</c:choose>
                        </ul>
                        <c:if test="${createCourseVO.creOperTypeCd ne 'S' }">
	                        <ul class="list">
	                            <li class="head"><label>기수종료일</label></li>
	                            <li>${mainLectureVO.termEndDttm}</li>
	                        </ul>
                        </c:if>
                    </div>
                </div>
                   
			







						

<script type="text/javascript">
//팝업박스
var modalBox = null;
$(document).ready(function() {
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
});


function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

</script>
