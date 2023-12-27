<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="sub_title2"></div>
				<div class="like-table" data-toggle="like-table">
				<div class="thead">
					<span class="th w10">기수명</span>
					<span class="th w10">과정명</span>
					<span class="th w10">개강일</span>
					<span class="th w10">종강일</span>
					<span class="th w5">분반</span>
					<span class="th w5">총 학습자수</span>
					<span class="th w10">시험채점</br>(완료/응시자/전체)</span>
					<span class="th w10">과제채점</br>(완료/응시자/전체)</span>
					<span class="th w10">진행단계채점</br>(완료/전체)</span>
					<c:if test="${forumListYn eq 'Y'}">
						<span class="th w10">토론채점</br>(완료/전체)</span>
					</c:if>
					<span class="th w10">질의응답</br>(완료/전체)</span>
					<span class="th w5">자료게시</span>
					<span class="th w5">채점완료 수</span>
				</div>
				<ul class="tbody">
					<c:forEach items="${stdScoreList}" var="item" varStatus="status">
						<li class="tr">
							<dl class="td txt-left"><dd>${empty item.crsNm?'--':item.crsNm}</dd></dl>
							<dl class="td txt-left"><dd>${empty item.crsCreNm?'--':item.crsCreNm}</dd></dl>
							<dl class="td">
								<dd>
									<fmt:parseDate var="enrlStartDttm" value="${item.enrlStartDttm}" pattern="yyyyMMddHHmmss" />
									<c:choose>
										<c:when test="${ not empty enrlStartDttm}"><fmt:formatDate value="${enrlStartDttm}" pattern="yyyy.MM.dd(E)"/></c:when>
										<c:otherwise>--</c:otherwise>
									</c:choose>
								</dd>
							</dl>
							<dl class="td">
								<dd>
									<fmt:parseDate var="enrlEndDttm" value="${item.enrlEndDttm}" pattern="yyyyMMddHHmmss" />
									<c:choose>
										<c:when test="${ not empty enrlEndDttm}"><fmt:formatDate value="${enrlEndDttm}" pattern="yyyy.MM.dd(E)"/></c:when>
										<c:otherwise>--</c:otherwise>
									</c:choose>
								</dd>
							</dl>
							<dl class="td"><dd>${item.declsNo}</dd></dl>
							<dl class="td"><dd>${item.stdCnt}</dd></dl>
							<dl class="td">
								<c:forEach items="${item.examList}" var="item1" varStatus="status1">
									<dd>${item1.rateCnt}/${item1.stareCnt}/${item.stdCnt}</dd>
									<button type="button" class="btn" onclick="javascript:editExamInfo('${item1.examSn}','${item1.crsCreCd }');">채점하기</button>
								</c:forEach>
								<c:if test="${empty item.examList}">
									<dd>0/0/${item.stdCnt}</dd>
								</c:if>
							</dl>
							<dl class="td">
								<c:forEach items="${item.asmtList}" var="item2" varStatus="status2">
									<dd>${item2.rateCnt}/${item2.stareCnt}/${item.stdCnt}</dd>
									<button type="button" class="btn" onclick="javascript:editAsmtInfo('${item2.asmtSn}','${item2.crsCreCd }');">채점하기</button>
								</c:forEach>
								<c:if test="${empty item.asmtList}">
									<dd>0/0/${item.stdCnt}</dd>
								</c:if>
							</dl>
							<dl class="td">
								<c:forEach items="${item.semiExamList}" var="item3" varStatus="status3">
									<dd>${item3.rateCnt}/${item.stdCnt}</dd>
									<button type="button" class="btn" onclick="javascript:editSemiExamInfo('${item3.examSn}','${item3.crsCreCd }');">채점하기</button>
								</c:forEach>
								<c:if test="${empty item.semiExamList}">
									<dd>0/${item.stdCnt}</dd>
								</c:if>
							</dl>
							<c:if test="${forumListYn eq 'Y'}">
								<dl class="td">
									<c:forEach items="${item.forumList}" var="item4" varStatus="status4">
										<dd>${item4.rateCnt}/${item4.stareCnt}/${item.stdCnt}</dd>
									</c:forEach>
									<c:if test="${empty item.forumList}">
										<dd>0/0/${item.stdCnt}</dd>
									</c:if>
								</dl>
							</c:if>
							<dl class="td"><dd>${item.bbsRateCnt}/${item.bbsCnt}</dd></dl>
							<dl class="td"><dd>${item.pdsCnt}</dd></dl>
							<dl class="td">
								<c:choose>
									<c:when test="${not empty item.asmtList}"><!-- //1) 과제가 있을 경우에는 시험 채점 여부 상관없이 과제 채점으로만 카운트 -->
										<c:forEach items="${item.asmtList}" var="item5" varStatus="status5">
											<dd>${item5.rateCnt}</dd>
										</c:forEach>
									</c:when>
									<c:otherwise><!-- //2) 과제가 없을 경우에는 시험 채점으로 카운트 -->
										<c:forEach items="${item.examList}" var="item6" varStatus="status6">
											<dd>${item6.rateCnt}</dd>
										</c:forEach>
										<c:if test="${empty item.examList}">
											<dd>0</dd>
										</c:if>
									</c:otherwise>
								</c:choose>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
				
<script type="text/javascript">
var modalBox = null;
$(document).ready(function() {
	$(".sub_title_2.ohddien").text("미채점현황");
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1",
		"nomargin" : false //m_large
	});
});

function editExamInfo(examSn,crsCreCd){
	location.href = cUrl("/lec/exam/editFormExamMain")+"?crsCreCd="+crsCreCd+"&examSn="+examSn;
}

function editAsmtInfo(asmtSn,crsCreCd){
	location.href = cUrl("/lec/assignment/editAssignmentManageMain")+"?crsCreCd="+crsCreCd+"&asmtSn="+asmtSn;
}

function editSemiExamInfo(examSn,crsCreCd){
	location.href = cUrl("/lec/exam/editFormExamMain")+"?crsCreCd="+crsCreCd+"&examSn="+examSn+"&semiExamYn=Y";
}

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}
</script>

