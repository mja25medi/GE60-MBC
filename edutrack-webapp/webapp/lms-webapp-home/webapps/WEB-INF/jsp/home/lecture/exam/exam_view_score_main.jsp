<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="vo" value="${createCourseVO}"/>
				 <div class="learn_top">
                    <div class="left_title">
                        <h3>성적조회</h3>
                    </div>
                </div>
				<div class="segment">
					<div class="res_tbl_wrap">
						<table>
							<caption>성적조회 목록</caption>
							<thead>
								<tr>
									<th scope="col">과정명</th>
									<th scope="col" width="15%">시험</th>
									<th scope="col" width="15%">진행단계평가</th>
									<th scope="col" width="10%">과제</th>
									<th scope="col" width="10%">총점</th>
									<th scope="col" width="10%">수료여부</th>
									<th scope="col" width="15%">이의제기</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty stdScoreList}">
									<c:forEach items="${stdScoreList}" var="item" varStatus="status">
									<jsp:useBean id="now" class="java.util.Date" />
									<c:set value="${vo.scoreOpenDttm }" var="startDate" />
									<c:set value="${now}" var="nowDate" />             <%-- 오늘날짜 --%>
									<c:set value="${startDate}"  var="openDate"/>       <%-- 성적공개시작날짜 --%>
									<fmt:formatNumber type="number"  value = "${item.examScore}" pattern = "0.0" var = "examScore"/>
									<fmt:formatNumber type="number"  value = "${item.semiExamScore}" pattern = "0.0" var = "semiExamScore"/>
									<fmt:formatNumber type="number"  value = "${item.asmtScore}" pattern = "0.0" var = "asmtScore"/>
									<fmt:formatNumber type="number"  value = "${item.totScore}" pattern = "0.0" var = "totScore"/>
									<c:set var="isOpen" value="${openDate < nowDate }" />
									<tr>
										<td scope="row" class="title" data-label="과정명" style="text-align: center;">${item.crsCreNm }</td>
											<c:if test="${isOpen == true}">
												<td data-label="시험">${examScore}점</td>
												<td data-label="진행단계평가">${semiExamScore}점</td>
												<td data-label="과제">${asmtScore}점</td>
												<td data-label="총점">${totScore }점</td>
											</c:if>
											<c:if test="${isOpen == false}">
												<td data-label="시험">-</td>
												<td data-label="진행단계평가">-</td>
												<td data-label="과제">-</td>
												<td data-label="총점">-</td>
											</c:if>
										<td data-label="수료여부">${item.enrlStsNm }</td>
										<td data-label="이의제기"><button class="btn-line" onclick="javascript:goObjtMenu();">이의제기</button></td>
									</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty stdScoreList}">
									<tr>
										<td colspan="6" data-label="no data">성적조회 내역이 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<%-- <c:if test="${isOpen == true}">
					<div id="examInfoDiv"></div>
					<div id="semiInfoDiv"></div>
					<div id="asmtInfoDIv"></div>
				</c:if> --%>
<script type="text/javascript">
var modalBox = null;
$(document).ready(function() {
	$(".sub_title_2.ohddien").text("성적조회");
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1",
		"nomargin" : false //m_large
	});
	loadExamInfo();
	loadSemiInfo();
	loadAsmtInfo();
});

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}


/**
 * 진행단계평가 결과
 */
function viewSemiExamResultPop(crsCreCd,stdNo,examSn){
	var url = generateUrl("/lec/exam/viewRatePop",{"crsCreCd":crsCreCd, "examSn":examSn, "stdNo":stdNo});
	var addContent  = "<iframe id='examInfoFrame' name='examInfoFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 700);
	modalBox.setTitle("진행단계평가확인");
	modalBox.show();
}

function viewExamResultPop(crsCreCd,stdNo,examSn){
	var url = generateUrl("/lec/exam/viewRatePop",{"crsCreCd":crsCreCd, "examSn":examSn, "stdNo":stdNo});
	var addContent  = "<iframe id='examInfoFrame' name='examInfoFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 700);
	modalBox.setTitle("시험평가확인");
	modalBox.show();
}

function viewAsmtResultPop(asmtSn){
	var url = generateUrl("/lec/assignment/sendResultPop",{"asmtSn":asmtSn});
	var addContent  = "<iframe id='examInfoFrame' name='examInfoFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1300, 700);
	modalBox.setTitle("과제평가확인");
	modalBox.show();
}

function viewDetailPop(dataType, dataSn) {
	var stdNo = '${vo.stdNo}';
	var crsCreCd = '${vo.crsCreCd}';
	if(dataType == 'EXAM') viewExamResultPop(crsCreCd, stdNo, dataSn);
	else if(dataType == 'SEMI') viewSemiExamResultPop(crsCreCd, stdNo, dataSn);
	else if(dataType == 'ASMT') viewAsmtResultPop(dataSn);
}


/**
 * 이의제기로 이동
 */
function goObjtMenu(){
	document.location.href = "/lec/objt/objtMain";
}

function loadExamInfo(){
	$("#examInfoDiv").load("/lec/exam/examInfoList", {
			"stdNo" : '${vo.stdNo}',
			"crsCreCd" : '${vo.crsCreCd}'
		});
}
function loadSemiInfo(){
	$("#semiInfoDiv").load("/lec/exam/semiInfoList", {
			"stdNo" : '${vo.stdNo}',
			"crsCreCd" : '${vo.crsCreCd}'
		});
}
function loadAsmtInfo(){
	$("#asmtInfoDIv").load("/lec/assignment/asmtInfoList", {
			"stdNo" : '${vo.stdNo}',
			"crsCreCd" : '${vo.crsCreCd}'
		});
}
</script>

