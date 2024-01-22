<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
	String authGrpCd = UserBroker.getClassUserType(request);
	request.setAttribute("authGrpCd", authGrpCd);
	String redisYn = Constants.REDIS_CHECK_YN;
	request.setAttribute("redisYn", redisYn);
%>
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
	                <button type="button" class="btn type6" onclick="resHelp()" id="resHelp_main">코딩실습 도움주기</button>
	            </c:if>
				<c:if test="${authGrpCd ne 'TCH'}">
	            	<c:if test="${createCourseVO.creTypeCd eq 'OF' or createCourseVO.creTypeCd eq 'BL' }">
		                 <div class="course_btn">
								<button type="button" class="btn type3" onclick="openQrReader('enter')">출석하기</button>
			                    <!-- <button type="button" id="quitBtn" class="btn type3" onclick="openQrReader('quit')" style="display: none;">퇴실하기</button> -->
			                    <button type="button" class="btn type3" onclick="classOutingCheck()">외출/조퇴하기</button>
		                 </div>
	                 </c:if>
	            </c:if>
			</div>
	    </div>
	</div> 

<div class="segment">
	<div class="board_top">
		<h4>나의 강의현황</h4>
	</div>
	<div class="scroll_table_w">
		<div class="scroll_table">
	    	<table>
	        	<caption>나의 강의현황</caption>
	        	<thead>
	            	<tr>
		                <th scope="col">총 학습자수</th>
		                <th scope="col">시험채점<br /><small>(완료/응시자/전체)</small></th>
		                <th scope="col">진행단계채점<br /><small>(완료/응시자/전체)</small></th>
		                <th scope="col">과제채점<br /><small>(완료/응시자/전체)</small></th>
		                <th scope="col">질의응답<br /><small>(완료/전체)</small></th>
		                <th scope="col">자료게시</th>
		                <th scope="col">채점완료 수</th>
	                </tr>
	        	</thead>
	        	<tbody>
	            	<tr>
	                    <td>${mainLectureVO.stdCnt}명</td>
	                    <td>
	                        <ul class="table_unit">
	                            <li>${mainLectureVO.examRateCnt }/${mainLectureVO.examAnsrCnt }/${mainLectureVO.stdCnt }<br /><button class="btn" onclick="location.href='/lec/exam/examMain'">채점하기</button></li>
	                        </ul>    
	                    </td>
	                    <td>
	                        <ul class="table_unit">
	                            <li>${mainLectureVO.semiExamRateCnt }/${mainLectureVO.semiExamAnsrCnt }/${mainLectureVO.stdCnt }<br /><button class="btn" onclick="location.href='/lec/exam/examMain'">채점하기</button></li>
	                        </ul>    
		                </td>
		                <td>
	                        <ul class="table_unit">
	                            <li>${mainLectureVO.asmtRateCnt }/${mainLectureVO.asmtSendCnt }/${mainLectureVO.stdCnt }<br /><button class="btn" onclick="location.href ='/lec/assignment/tchAssignmentMain'">채점하기</button></li>
	                        </ul>    
		                </td>
		                <td>${mainLectureVO.bbsRateCnt }/${mainLectureVO.bbsCnt }</td>
		                <td>${mainLectureVO.pdsCnt}</td>
		                <td>1</td>
           			</tr>
           		</tbody>
	        </table>
		</div>
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
								<td data-label="보낸일"><meditag:dateformat type="1" delimeter="." property="${msgItem.transDttm }"/></td>
								<td data-label="확인"> <button type="button" class="btn3 sm solid fcBlue" onclick="viewRecvMsgPop('${msgItem.msgSn }','${msgItem.msgTransSn }')">확인</button></td>
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

			<%-- <div class="grid-row">
                    <div class="m-box size-6">
                       <div class="sub_title">나의 강의현황</div> 
                       <!-- 반응형을 위해 테이블 대신 ul li 사용, thead의 하위 span과 tbody > li 내의 dl 개수는 동일하여야 합니다. -->
                        <div class="like-table" data-toggle="like-table">
                            <div class="thead">
                                <span class="th w11">기수명</span>
                                <span class="th">과정명</span>
                                <span class="th w7">개강일</span>
                                <span class="th w7">종강일</span>
                                <span class="th w5">분반</span>
                                <span class="th w7">총 학습자수</span>
                                <span class="th w10">시험채점<br><span class="txt-small">(완료/응시자/전체)</span></span>
                                <span class="th w10">과제채점<br><span class="txt-small">(완료/응시자/전체)</span></span>
                                <span class="th w8">진행단계채점<br><span class="txt-small">(완료/전체)</span></span>
                                <span class="th w7">질의응답<br><span class="txt-small">(완료/전체)</span></span>
                                <span class="th w6">자료게시</span>
                            </div>
                            <ul class="tbody">
                                <li class="tr">
                                    <dl class="td"><dt>기수명</dt><dd>${mainLectureVO.crsYear }년도 ${mainLectureVO.crsTerm }기수</dd></dl>
                                    <dl class="td txt-left"><dt>과정명</dt><dd>${mainLectureVO.crsCreNm }</dd></dl>
                                    <dl class="td"><dt>개강일</dt><dd><meditag:dateformat type="1" delimeter="-" property="${mainLectureVO.enrlStartDttm }"/></dd></dl>
                                    <dl class="td"><dt>종강일</dt><dd><meditag:dateformat type="1" delimeter="-" property="${mainLectureVO.enrlEndDttm }"/></dd></dl>
                                    <dl class="td"><dt>분반</dt><dd>1반</dd></dl>
                                    <dl class="td"><dt>총 학습자수</dt><dd>${mainLectureVO.stdCnt }명</dd></dl>
                                    <dl class="td"><dt>시험채점<br><span class="txt-small">(완료/응시자/전체)</span></dt><dd>${mainLectureVO.examRateCnt }/${mainLectureVO.examAnsrCnt }/${mainLectureVO.stdCnt }<br><a type="button" class="btn type1" href="/lec/main/goMenuPage?mcd=ML08000001">채점하기</a></dd></dl>
                                    <dl class="td"><dt>과제채점<br><span class="txt-small">(완료/응시자/전체)</span></dt><dd>${mainLectureVO.asmtRateCnt }/${mainLectureVO.asmtSendCnt }/${mainLectureVO.stdCnt }<br><a type="button" class="btn type1" href="/lec/main/goMenuPage?mcd=ML10000000">채점하기</a></dd></dl>
                                    <dl class="td"><dt>진행단계채점<br><span class="txt-small">(완료/응시자/전체)</span></dt><dd>${mainLectureVO.semiExamRateCnt }/${mainLectureVO.semiExamAnsrCnt }/${mainLectureVO.stdCnt }<br><a type="button" class="btn type1" href="/lec/main/goMenuPage?mcd=ML08000002">채점하기</a></dd></dl>
                                    <dl class="td"><dt>질의응답<br><span class="txt-small">(완료/전체)</span></dt><dd>${mainLectureVO.bbsRateCnt }/${mainLectureVO.bbsCnt }</dd></dl>
                                    <dl class="td"><dt>자료게시</dt><dd>${mainLectureVO.pdsCnt }</dd></dl>
                                </li>                                              
                            </ul>
                        </div>
                    </div> --%>
<script type="text/javascript">
	var modalBox = null;
	$(document).ready(function() {
		
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1",
			"nomargin" : false //m_large
		});
		$("#course").css("display","none");
		
		callRedis();
		<%if("Y".equals(redisYn)){%>
		setInterval(callRedis, 3000);
    	<%}%>
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	//redis 도움 요청 조회
 	function callRedis(){
 		$.getJSON(cUrl("/lec/bookmark/callRedis"), 	// url
 				{ 
 				  "crsCreCd" : '${createCourseVO.crsCreCd}'
 				}, function(data) { 
 					if(data.result < 0) {
 						$("#resHelp_main").attr("style","display: none");
 					} else if (data.result > 0) {
 						$("#resHelp_main").attr("style","display: block");
 					}
 				}
 			);
 	}
	
</script>
