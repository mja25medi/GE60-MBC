<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
     <c:if test="${CLASSUSERTYPE eq 'STU'}">
       <div class="segment grid_2">
	    <div class="prog_rate">
	        <h3>학습진도율</h3>
	        <ul>
	            <li>
	                <span class="header">나의 진도율</span><span class="meta">${mainLectureVO.prgrRatio }%</span>
	                <div class="progress">
	                    <div class="bar red_type" style="width: ${mainLectureVO.prgrRatio }%;"></div>
	                </div>
	            </li>
	            <li>
	                <span class="header">권장 진도율</span>
	                <span class="meta">
	                	<c:choose>
							<c:when test="${termDayCnt < 0 }">
								0%
							</c:when>
							<c:when test="${createCourseVO.enrlStartDttm > nowDate }"> <!-- 교육기간 시작 이전 -->
								0%
							</c:when>
							<c:otherwise>
								${prpsRatio}%
							</c:otherwise>
						</c:choose>	
	                </span>
	                <div class="progress">
                 	     <c:choose>
							<c:when test="${termDayCnt < 0 }">
								<div class="bar blue_type" style="width: 0%;"></div>
							</c:when>
							<c:otherwise>
								<div class="bar blue_type" style="width: ${prpsRatio }%;"></div>
							</c:otherwise>
						</c:choose>	
	                </div>

	            </li>
	        </ul>
	    </div>
	    <div class="test_part">
	        <h3>성적적용비율(%)</h3>
	        <ul>
	        	<c:if test="${createCourseVO.prgrRatio eq 0}"></c:if>
	        	<c:if test="${createCourseVO.prgrRatio ne 0}">
	            	<li><span class="header">진도</span>${createCourseVO.prgrRatio }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.examRatio eq 0}"></c:if>
	        	<c:if test="${createCourseVO.examRatio ne 0}">
	            	<li><span class="header">시험</span>${createCourseVO.examRatio }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.semiExamRatio eq 0}"></c:if>
	        	<c:if test="${createCourseVO.semiExamRatio ne 0}">
	            	<li><span class="header">진행단계평가</span>${createCourseVO.semiExamRatio }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.asmtRatio eq 0}"></c:if>
	        	<c:if test="${createCourseVO.asmtRatio ne 0}">
	            	<li><span class="header">과제</span>${createCourseVO.asmtRatio }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.etcRatio eq 0}"></c:if>
	        	<c:if test="${createCourseVO.etcRatio ne 0}">
	            	<li><span class="header">${createCourseVO.etcNm }</span>${createCourseVO.etcRatio }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.etcRatio2 eq 0}"></c:if>
	        	<c:if test="${createCourseVO.etcRatio2 ne 0}">
	            	<li><span class="header">${createCourseVO.etcNm2 }</span>${createCourseVO.etcRatio2 }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.etcRatio3 eq 0}"></c:if>
	        	<c:if test="${createCourseVO.etcRatio3 ne 0}">
	            	<li><span class="header">${createCourseVO.etcNm3 }</span>${createCourseVO.etcRatio3 }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.etcRatio4 eq 0}"></c:if>
	        	<c:if test="${createCourseVO.etcRatio4 ne 0}">
	            	<li><span class="header">${createCourseVO.etcNm4 }</span>${createCourseVO.etcRatio4 }%</li>
	            </c:if>
	            <c:if test="${createCourseVO.etcRatio5 eq 0}"></c:if>
	        	<c:if test="${createCourseVO.etcRatio5 ne 0}">
	            	<li><span class="header">${createCourseVO.etcNm5 }</span>${createCourseVO.etcRatio5 }%</li>
	            </c:if>
	        </ul>
	    </div>
	</div>
      </c:if>

	<div class="grid-row" id="contentsList"></div>
         
    <div class="overlay" id="loadingBar">
		<i class="fa fa-spinner fa-spin"></i>
	</div>
				
	<iframe id='edutrackAPIFrame' name='edutrackAPIFrame' width='0' height='0' frameborder='0' src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" ></iframe>

<script type="text/javascript">

	var API_1484_11 = null;
	var edutrackAPI = null;
	var otpModalBox = null;
	var modalBox = null;
	var ItemVO1 = {};
	
	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		ItemVO1.returnMethod 	= 'viewContents';
		listContents();
		
		otpModalBox =new $M.ModalDialog({
			"modalid" : "otpModal"
		});
		
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	});

	/**
	 * 과정 분류 목록 조회
	 */
	function listContents() {
		if("${CLASSUSERTYPE}" == 'STU'){
			$('#contentsList')
				.load(
					cUrl("/lec/bookmark/listStuBookmark"),
					{ 
						"sbjCd":"${createCourseVO.sbjCd}"
					},
					function () {$("#loadingBar").hide();}
				);
		}else{
			$('#contentsList')
				.load(
						cUrl("/lec/bookmark/listTchBookmark"),
					{ 
						"sbjCd":"${createCourseVO.sbjCd}"
					},
					function () {$("#loadingBar").hide();}
				);
		}
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	function listViewLearn(sbjCd){
		document.location.href =  "/lec/bookmark/listStdPrgrRatioDetailMain?sbjCd="+sbjCd;
	}
	function listViewTch(sbjCd){
		document.location.href =  "/lec/bookmark/listTchPrgrRatioDetailMain?sbjCd="+sbjCd;
	}

</script>
