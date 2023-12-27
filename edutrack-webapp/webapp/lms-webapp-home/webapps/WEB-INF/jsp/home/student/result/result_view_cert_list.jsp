<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="sub_title2"></div>
				<div class="like-table" data-toggle="like-table">
				<div class="thead">
					<span class="th w50">과정명</span>
					<span class="th w14">총점</span>
					<span class="th w18">수료여부</span>
					<span class="th w18">수료증발급</span>
				</div>
				<ul class="tbody">
					<c:forEach items="${stdScoreList}" var="item" varStatus="status">
						<li class="tr">
							<dl class="td txt-left"><dd>${item.crsCreNm }</dd></dl>
							<dl class="td"><dd>${item.totScore } 점</dd></dl>
							<dl class="td"><dd><c:choose><c:when test="${item.enrlSts eq 'C'}">수료</c:when><c:otherwise>미수료</c:otherwise> </c:choose></dd></dl>
							<dl class="td">
								<dd>
									<c:choose>
										<c:when test="${item.enrlSts eq 'C'}">
											<button type="button" class="btn type1" onclick="javascript:printCerti('${item.crsCreCd}','${item.stdNo}');">발급</button>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</dd>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
				
<script type="text/javascript">
var modalBox = null;
$(document).ready(function() {
	$(".sub_title_2.ohddien").text("수료증");
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1",
		"nomargin" : false //m_large
	});
});

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

function printCerti(crsCreCd, stdNo) {
	// download용 iframe이 없으면 만든다.
	if ( $("#_m_pdf_iframe").length == 0 ) {
		iframeHtml =
			'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
		$("body").append(iframeHtml);
	}
	// 폼에 action을 설정하고 submit시킨다.
	var url = cUrl("/home/student/printCert?crsCreCd="+crsCreCd+"&stdNo="+stdNo);
	$("#_m_pdf_iframe").attr("src",url);
}
</script>

