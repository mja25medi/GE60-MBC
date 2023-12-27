<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>

	<div class="process_info">
	    <div class="video txt-center">
	        <div class="thumbnail" title="영상보기" onclick="previewContents('${onSbjVO.sbjCd }');">
	            <img src="/app/file/thumb/${onSbjVO.thumbFileSn }" alt="이미지" aria-hidden="true" onerror="this.style.display='none'">
	        </div>
	        <button class="btn solid bluegreen" title="영상맛보기" onclick="previewContents('${onSbjVO.sbjCd }');"><i class="xi-play-circle-o xi-x" aria-hidden="true"></i> 맛보기</button>
	    </div> 
	
	    <div class="table2">
	    	<div class="row">
	            <div class="col">
	                <span class="title">과정명</span>
	                <div>${onSbjVO.sbjNm}</div>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col">
	                <span class="title">과정카테고리</span>
	                <div>${onSbjVO.sbjCtgrNm}</div>
	            </div>
	        </div>
	
	        <div class="row">
	            <div class="col">
	                <span class="title">학습기간</span>
	                <div>
	                	<c:choose>
							<c:when test="${not empty onSbjVO.eduDaycnt }">
								${onSbjVO.eduDaycnt} 일	
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</div>
	            </div>
	        </div>
	        
	        <div class="row">
	            <div class="col">
	            	<span  class="title">학습시간</span>
	                <div>
	                	<c:choose>
							<c:when test="${not empty onSbjVO.eduTm }">
								${onSbjVO.eduTm} 시간
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
	                </div>
	            </div>
	        </div>
	        
	    </div>
	</div>
	
	 <section>
	 	<c:if test="${not empty onSbjVO.sbjDesc }">
		    <article class="mt40">
		        <h5 class="mb20">과정 소개</h5>
		        <p>${fn:replace(onSbjVO.sbjDesc,crlf,"<br/>")}</p>
		    </article>
	    </c:if>
	
		<c:if test="${not empty onSbjVO.eduTarget }">
		    <article class="mt40">
		        <h5 class="mb20">교육대상</h5>
		        <p>${onSbjVO.eduTarget}</p>
		    </article>
	    </c:if>
	
	    <c:if test="${not empty onSbjVO.eduPrps }">
		    <article class="mt40">
		        <h5 class="mb20">과정 목표</h5>
		        <p>${fn:replace(onSbjVO.eduPrps,crlf,"<br/>")}</p>
		    </article>
	    </c:if>
	    
	   <c:if test="${not empty onSbjVO.evalDesc }">
		    <article class="mt40">
		        <h5 class="mb20">평가형식</h5>
		        <p>${fn:replace(onSbjVO.evalDesc,crlf,"<br/>")}</p>
		    </article>
	    </c:if>
	
		<c:if test="${not empty cntsList}">
		    <article class="mt40">
		        <h5 class="mb20">강의 내용</h5>
		
		        <div class="like-table" data-toggle="like-table">
		            <div class="thead">                                
		                <span class="th w10">회차</span>
		                <span class="th">강의명</span>
		            </div>
		            <ul class="tbody">
		            	<c:forEach items="${cntsList}" var="contents">
							<c:if test="${contents.unitLvl == 1 }">
								<li class="tr">
									<dl class="td"><dt>회차</dt>
										<dd>
											<c:set var="cnt" value="${cnt+1}"/>
											${cnt}
										</dd>
									</dl>
									<dl class="td txt-left"><dt>강의명</dt><dd>${contents.unitNm}</dd></dl>
								</li>
							</c:if>
						</c:forEach>
		            </ul>
		        </div>
		    </article>
	    </c:if>
	
	</section>

<script type="text/javascript">
//팝업박스
var modalBox = null;
$(document).ready(function() {
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
});
/**
 * 사용자 정보 폼
 */
function tchInfo(userNo) {
	var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/tch/info/viewTch"/>"
		+ "?userNo="+userNo+"&amp;isPop=N&amp;CRSCRECD=${createCourseVO.crsCreCd}'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 780);
	modalBox.setTitle("<spring:message code="teacher.title.teacherinfo.teacher.manage"/>");
	modalBox.show();
}


function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

function previewContents(sbjCd){
	$.ajax({
		url : '/home/course/subject/getFirstContents'
		,data : {
			'sbjCd' : sbjCd
		}
		,success : function(resultListVO) {
			var url = cUrl("/home/course/subject/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+resultListVO.unitCd;
			var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=auto,resizable=yes,width=1200,height=675";
			var contentsWin = window.open(url, "contentsWin", winOption);
			contentsWin.focus();
		}
		,error : function(request,status,error) {
		}
	});
}
</script>

