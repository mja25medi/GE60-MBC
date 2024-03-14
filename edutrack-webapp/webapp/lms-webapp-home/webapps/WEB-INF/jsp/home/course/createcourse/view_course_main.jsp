<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss" var="nowDate" />

                    <div class="course_detail">
                        <div class="detail_top">
                            <div class="course_img">
                                <img src="/app/file/thumb/${courseVO.thumbFileSn }" alt="이미지" aria-hidden="true" onerror="this.style.display='none'">     
                            </div>
                            <div class="course_info">
                                <div class="card_label">
                                    <span class="label basic bcBlue">${courseVO.crsCtgrNm}</span>
                                </div>
                                <!-- 컨트롤러에서 값을 받아올 때 VO로 받아올 시 값을 받아오지 못해 List형식으로 받아와 출력한다. 해당 쿼리는 단일 select 쿼리이다 -->
                                <c:set var="totEduPrice" value="0"/>
								<c:set var="totGoods" value=""/>
                                <c:forEach items="${createCourseList}" var="item">
	                                <div class="item">
	                                	<c:set value="${totGoods}${status}${item.crsCreNm}" var="totGoods" />
	                                	<c:set value="${paramBskListStr}${status.first}${item.crsCreCd}" var="paramBskListStr" />
	                                    <h2>${item.crsCreNm} ${item.creTerm }회차</h2>
	                                    
	                                    <ul>
	                                        <li>
	                                        	<span>교육구분</span>
	                                        	<c:choose>
	                                        		<c:when test="${courseVO.crsSvcType eq 'R' }">국비지원</c:when>
	                                        		<c:when test="${courseVO.crsSvcType eq 'S' }">개발원 자체</c:when>
	                                        	</c:choose>
	                                        </li>
	                                        <li>
	                                        	<span>과정유형</span>
	                                        	<c:choose>
	                                        		<c:when test="${item.creTypeCd eq 'ON' }">온라인</c:when>
	                                        		<c:when test="${item.creTypeCd eq 'OF' }">오프라인</c:when>
	                                        		<c:when test="${item.creTypeCd eq 'BL' }">혼합</c:when>
	                                        	</c:choose>
	                                        </li>
	                                        <li><span>과목 수</span>${item.sbjCnt }과목</li>
	                                       
	                                        <c:choose>
		                                	<c:when test="${item.crsOperType  eq 'S'}">
		                                		<li><span>수강일수</span>${item.enrlDaycnt}일</li>	
		                                	</c:when>
		                                	<c:otherwise>
				                              	 <li><span>교육신청기간</span><meditag:dateformat type="1" delimeter="." property="${item.enrlAplcStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.enrlAplcEndDttm}"/></li>
	                                       		 <li><span>수강기간</span><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/> (${item.onlnEduTm }시간)</li>
		                                	</c:otherwise>
		                                </c:choose>
	                                        <li><span>교육비용</span>
                                               	<c:choose>
                                                	<c:when test="${item.eduPrice eq 0 or empty item.eduPrice}"><strong class="price">무료</strong></c:when>
                                                	<c:otherwise>
                                                		<strong class="price"><fmt:formatNumber value="${item.eduPrice}" pattern="#,#00" />원</strong>
                                                		<c:set var="totEduPrice" value="${totEduPrice + item.eduPrice }"/>
                                                	</c:otherwise>
                                                </c:choose>
                                            </li>
	                                    </ul>
	                                </div>
                            </div>
                        </div>

                        <div class="btns right mt20">
                        	<c:if test="${empty sessionScope.USERNO}">
                            	<button class="btn gray2" onclick="location.href='javascript:addQna();' "><spring:message code="button.write.qna.nonMem"/></button>
                            </c:if>
                            <c:if test="${item.enrlAplcYn eq 'Y' || item.crsOperType  eq 'S' }">                            
	                            <c:choose>
	                            	  <c:when test="${stdYn eq 'Y' }">
	                            	  		<button class="btn type4" onclick="location.href='/home/main/goMenuPage?mcd=MC00000051'"><spring:message code="course.title.createcourse.enroll"/>완료</button>
	                            	  </c:when>
	                                  <c:when test="${item.eduPrice eq 0 or empty item.eduPrice}">
	                                  		<button class="btn type4" onclick="addCourse('${item.crsCtgrCd}','${item.crsCd}','${item.crsCreCd}');"><spring:message code="course.title.createcourse.enroll"/></button>
	                                  </c:when>
	                                  <c:otherwise>
	                           				<button class="btn type4" onclick="javascript:addSingleBasket('${item.crsCreCd}','${item.crsOperType}');"><spring:message code="course.title.createcourse.enroll"/></button>
	                            	  </c:otherwise>
	                           </c:choose>
	                           <c:choose>
	                           		<c:when test="${item.eduPrice eq 0 or empty item.eduPrice}"></c:when>
	                           		<c:otherwise>
	                           			<a href="javascript:addBasket('${item.crsCreCd}');" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs">장바구니에 담기</a>
	                           		</c:otherwise>
	                           </c:choose>
                           </c:if>
                        </div>  

                        <div class="detail_con">
                            <h5 class="subtitle">과정소개</h5>
                            <p>
                               ${item.crsDesc }
                            </p>
                        </div>
					</c:forEach>
                   <div class="btns mt30">
                       <button type="button" class="btn gray2" onclick="goList()">목록</button>
                   </div>
                   
       
<script type="text/javascript">
//팝업박스
var modalBox = null;
$(document).ready(function() {
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1",
		"nomargin"	: true
	});
	
/* 	<c:if test="${not empty createCourseList}">
		loadCourseInfo('${createCourseList[0].crsCreCd }');
	</c:if>
	 */
});

/**
 * 비회원 상담 신청 팝업
 */
function addQna() {
	var addContent = "<iframe id='nonMemQnaForm' name='nonMemQnaForm' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/brd/qna/nonMemQnaPop"/>'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(900, 720);
	modalBox.setTitle("<spring:message code="button.write.qna.nonMem"/>");
	modalBox.show();
}

/**
 * 사용자 정보 폼
 */
/* function tchInfo(userNo) {
	var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/tch/info/viewTch"/>"
		+ "?userNo="+userNo+"&amp;isPop=N&amp;CRSCRECD=${createCourseVO.crsCreCd}'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 780);
	modalBox.setTitle("<spring:message code="teacher.title.teacherinfo.teacher.manage"/>");
	modalBox.show();
} */


function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

function addBasket(crsCreCd) {

	$.ajax({
		url : '/home/student/addBasket'
		,data : {
			'crsCreCd' : crsCreCd
		}
		, method: "POST"
		, dataType: 'json'
		,success : function(resultVO) {
			if(resultVO.result > 0){
				if(confirm("해당 강의를 수강신청 항목에 담았습니다.\n결제를 위해 수강신청결제 페이지로 이동하시겠습니까?")){
					location.href = "/home/main/goMenuPage?mcd=MC10000013";//수강신청		
					return;
				}
			}else{
				alert(resultVO.message);
				
			}
		}
		,error : function(request,status,error) {
			alert("수강신청 항목 담기에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
		}
	});
}

function addSingleBasket(crsCreCd,crsOperType) {
	if('${USERNO}' == null || '${USERNO}' == ""){
		location.href = "/home/main/goMenuPage?mcd=HM04001000"
		alert("로그인 후 신청 가능합니다.");
	}else{
		$.ajax({
			url : '/home/student/addBasket'
			,data : {
				'crsCreCd' : crsCreCd
			}
			, method: "post"
			, dataType: 'json'
			, async: false
			, success : function(resultVO) {
				if(resultVO.result > 0){
					location.href = "/home/student/enrollPayBasketMain?crsCreCd="+crsCreCd+"&crsOperType="+crsOperType;//수강신청	
					return;
				}else{
					alert(resultVO.message);	
				}
			}
			,error : function(request,status,error) {
				alert("수강신청 항목 담기에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
			}
		});
	}
}


function goList() {
	var crsOperMthd = '${courseVO.crsOperMthd}';
	location.href="/home/course/listCourseMain?crsOperMthd="+crsOperMthd;
}

function addCourse(crsCtgrCd,crsCd,crsCreCd){
	if('${USERNO}' == null || '${USERNO}' == ""){
		location.href = "/home/main/goMenuPage?mcd=HM04001000"
		alert("로그인 후 신청 가능합니다.");
	}else{
		if(confirm("수강신청 하시겠습니까?")){
			location.href='/home/student/enrollCourseMain?crsCtgrCd='+crsCtgrCd+'&crsCd='+crsCd+'&crsCreCd='+crsCreCd;
		}else{
			alert("수강신청을 취소하였습니다.");
		}
	}
}


</script>
