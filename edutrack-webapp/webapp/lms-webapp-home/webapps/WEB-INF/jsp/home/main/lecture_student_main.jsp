<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

<c:url var="img_base" value="/img/home" />

	<div class="segment">
		<div class="course_info style_home" style="align-items: flex-end;">
		    <div class="img_box">
		       <img src="/app/file/thumb/${createCourseVO.thumbFileSn }" alt="이미지" aria-hidden="true" onerror="this.style.display='none'">
		    </div>
	        <div class="item">
				<label class="category" style="width: fit-content; padding: 0.3rem 1rem;">${courseVO.crsCtgrNm }</label>
	           	<div class="class_row">
					<h2>${createCourseVO.crsCreNm }</h2>
	            </div>
				<ul>
					<li><span>교육기간</span>${createCourseVO.enrlStartDttm } ~ ${createCourseVO.enrlEndDttm }</li>
					<li><span>성적열람 시작일</span>${createCourseVO.scoreOpenDttm }</li>
	             	<li>
		                <c:if test="${authGrpCd ne 'TCH'}">
		                <span><i class="xi-calendar-check" aria-hidden="true"></i>전체 ${createCourseVO.sbjCnt }개의 과목 중</span>
			            <c:if test="${empty createCourseVO.sbjCnts}">0</c:if><c:if test="${not empty createCourseVO.sbjCnts}">${createCourseVO.sbjCnts }</c:if>개 수강 완료
		                </c:if>
		                <c:if test="${authGrpCd eq 'TCH'}">
		                <span><i class="xi-calendar-check" aria-hidden="true"></i>전체 ${createCourseVO.sbjCnt }개의 과목</span>
		                </c:if>
	             	</li>					                
				</ul>
			</div>
			<div>
				<c:if test="${authGrpCd eq 'TCH'}">
	                <button type="button" class="btn type6" onclick="resHelp()">코딩실습 도움주기</button>
	            </c:if>
				<c:if test="${authGrpCd ne 'TCH'}">
	            	<c:if test="${(createCourseVO.creTypeCd eq 'OF' or createCourseVO.creTypeCd eq 'BL') && nowDate >= createCourseVO.enrlStartDttm && nowDate <= createCourseVO.enrlEndDttm}">
		                 <c:if test="${attendanceVO.enterFlag ne 'E'}">
                         	<button type="button" class="btn type3" onclick="enterClass()">출석하기</button>
                         </c:if>
                         <c:if test="${attendanceVO.enterFlag eq 'E' }">
                         	 	<button type="button" id="quitBtn" class="btn type3" onclick="quitClass()" style="display: none;">퇴실하기</button>
                         	 	<button type="button" id="outBtn" class="btn type3" onclick="classOutingCheck()">외출/조퇴하기</button>
                         </c:if>
	                 </c:if>
	            </c:if>
			</div>
	    </div>
	</div>    
	
	<div class="segment grid_2">
	    <div class="prog_rate">
	        <h3>학습진도율</h3>
	        <ul>
	            <li>
	                <c:choose>
	                	<c:when test="${createCourseVO.creTypeCd eq 'OF' }"><!-- 오프라인 과정일 경우 등록한 시간표에 맞춰서 진도율 계산 --></c:when>
	                	<c:otherwise> 
			                <span class="header">나의 진도율</span><span class="meta">${mainLectureVO.prgrRatio }%</span>  	                
			                <div class="progress">
			                    <div class="bar red_type" style="width: ${mainLectureVO.prgrRatio }%;"></div>
			                </div>
	                	</c:otherwise>
	                </c:choose>
	            </li>
	            <li>
	                <span class="header">권장 진도율</span>
	                <span class="meta">
	                <c:choose>
	                	<c:when test="${createCourseVO.creTypeCd eq 'ON' }"> <!-- 온라인 과정일 경우 고정 -->80%</c:when>
	                	<c:when test="${createCourseVO.creTypeCd eq 'BL' }"><!-- 혼합 과정일 경우 고정 -->80%</c:when> 
	                	<c:otherwise> 
	                	<!-- 오프라인 과정일 경우 기존 계산 -->	
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
	                	</c:otherwise>
	                </c:choose>

	                </span>
	                <div class="progress">
		                <c:choose>
		                	<c:when test="${createCourseVO.creTypeCd eq 'ON' }"><div class="bar blue_type" style="width: 80%;"></div></c:when>
		                	<c:when test="${createCourseVO.creTypeCd eq 'BL' }"><div class="bar blue_type" style="width: 80%;"></div></c:when> 
		                	<c:otherwise> 
		                		<!-- 오프라인 과정일 경우 기존 계산 -->	
		                 	     <c:choose>
									<c:when test="${termDayCnt < 0 }">
										<div class="bar blue_type" style="width: 0%;"></div>
									</c:when>
									<c:otherwise>
										<div class="bar blue_type" style="width: ${prpsRatio }%;"></div>
									</c:otherwise>
								</c:choose>	
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
	
	<div class="segment-row">
		<div class="segment">
			<div class="board_top">
                <h4>과정 공지사항</h4>
            </div>
            <c:if test="${not empty creNoticeList }">
	            <div class="res_tbl_wrap">
		            <table>
		            	<caption>목록</caption>
		                <thead>
		                    <tr>
		                        <th scope="col">제목</th>
		                        <th scope="col" width="25%">작성일</th>
		                    </tr>
		                </thead>
		                <tbody>
			              	<c:forEach items="${creNoticeList }" var="creNoticeItem">
	   	            			<tr>
	   	            				<td scope="row" class="title" data-label="제목"><a href="/lec/bbs/readAtclMain?bbsCd=${creNoticeItem.bbsCd }&atclSn=${creNoticeItem.atclSn }">${creNoticeItem.title}</a></td>
	   	            				<td data-label="작성일"><meditag:dateformat type="1" delimeter="." property="${creNoticeItem.regDttm }"/></td>
					        	</tr>
			              	</c:forEach>
		                </tbody>
		            </table>
		        </div>
	        </c:if>
	        <c:if test="${empty creNoticeList }">
	        	<div class="text_none"><span>등록된 게시물이 없습니다.</span></div>
	        </c:if>
		</div>
		<div class="segment">
        	<div class="board_top">
            	<h4>과정 자료실</h4>
            </div>
            <c:if test="${not empty pdsList }">
	            <div class="res_tbl_wrap">
		            <table>
		            	<caption>목록</caption>
		                <thead>
		                    <tr>
		                        <th scope="col">제목</th>
		                        <th scope="col" width="25%">작성일</th>
		                    </tr>
		                </thead>
		                <tbody>
			              	<c:forEach items="${pdsList }" var="pdsItem">
		  	            			<tr>
		  	            				<td scope="row" class="title" data-label="제목"><a href="/lec/bbs/readAtclMain?bbsCd=${pdsItem.bbsCd }&atclSn=${pdsItem.atclSn }">${pdsItem.title }</a></td>
		  	            				<td data-label="작성일"><meditag:dateformat type="1" delimeter="." property="${pdsItem.regDttm }"/></td>
					        	</tr>
			              	</c:forEach>
		                </tbody>
		            </table>
	        	</div>
        	</c:if>
        	<c:if test="${empty pdsList }">
        		<div class="text_none"><span>등록된 게시물이 없습니다.</span></div>
	        </c:if>
       </div>
	</div>
	
	<div class="segment-row">
		<div class="segment">
			<div class="board_top">
                <h4>내 쪽지함</h4>
            </div>
            <c:if test="${not empty msgList }">
	            <div class="res_tbl_wrap">
		            <table>
		            	<caption>목록</caption>
		                <thead>
		                    <tr>
		                        <th scope="col" width="25%">보낸사람</th>
		                        <th scope="col">내용</th>
		                        <th scope="col" width="25%">보낸일</th>
		                        <th scope="col" width="15%">확인</th>
		                    </tr>
		                </thead>
		                <tbody>
			              	<c:forEach items="${msgList }" var="msgItem">
	   	            			<tr>
	  	            				<td scope="row" data-label="보낸사람">${msgItem.sendNm }</td>
	                                <td class="title" data-label="내용"><a href="#0"><a href="javascript:viewRecvMsgPop('${msgItem.msgSn }','${msgItem.msgTransSn }');">${msgItem.title }</a></a></td>
                                    <td data-label="보낸일"><meditag:dateformat type="1" delimeter="." property="${msgItem.regDttm }"/></td>
                                    <!-- <td data-label="확인"><label class="btn3 sm solid fcBlue">확인</label></td> -->
                                    <td data-label="확인"><button type="button" class="btn3 sm solid fcBlue" onclick="viewRecvMsgPop('${msgItem.msgSn }','${msgItem.msgTransSn }')">확인</button></td>
                                    
					        	</tr>
			              	</c:forEach>
		                </tbody>
		            </table>
		        </div>
	        </c:if>
	        <c:if test="${empty msgList }">
	        	<div class="text_none"><span>등록된 게시물이 없습니다.</span></div>
	        </c:if>
		</div>
		<div class="segment">
        	<div class="board_top">
            	<h4>전체 공지사항</h4>
            </div>
            <c:if test="${not empty noticeList }">
	            <div class="res_tbl_wrap">
		            <table>
		            	<caption>목록</caption>
		                <thead>
		                    <tr>
		                        <th scope="col">제목</th>
		                        <th scope="col" width="25%">작성일</th>
		                    </tr>
		                </thead>
		                <tbody>
			              	<c:forEach items="${noticeList }" var="noticeItem">
	   	            			<tr>
	   	            				<td scope="row" class="title" data-label="제목"><a href="/home/brd/bbs/viewAtclMain?bbsCd=${noticeItem.bbsCd }&atclSn=${noticeItem.atclSn }&mcd=HM02001000">${noticeItem.atclTitle }</a></td>
	   	            				<td data-label="작성일"><meditag:dateformat type="1" delimeter="." property="${noticeItem.regDttm }"/></td>
					        	</tr>
			              	</c:forEach>
		                </tbody>
		            </table>
	        	</div>
        	</c:if>
        	<c:if test="${empty noticeList }">
	        	<div class="text_none"><span>등록된 게시물이 없습니다.</span></div>
	        </c:if>
       </div>
	</div>

<script type="text/javascript">
	var ItemDTO = new Object();
	var sbjList = '${fn:length(sbjList)}';
	var ItemList = {"prgrRatio" : 0};
	var modalBox = null;
	var noMsg = '<ul class="list-group"><li class="list-group-item"><spring:message code="lecture.message.contents.list.nodata"/></li></ul>';
	
	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		if(sbjList == '0'){
			$("#contentsListArea").html(noMsg);
		}else{
			//listContents();
		}
		$(".modal").css("display","none");
		$("#course").css("display","none");

		clock();
        setInterval(clock, 60000); 
	});

	function clock(){
        let today = new Date();
        let H = today.getHours();
        let M = today.getMinutes();
        let S = today.getSeconds();
		if(H>=18 && M>=0) {
			$('#outBtn').hide();
			$('#quitBtn').show();
		}
    }

</script>
