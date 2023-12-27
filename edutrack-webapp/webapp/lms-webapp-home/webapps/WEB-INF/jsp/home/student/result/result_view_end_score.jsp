<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

<div id="listEndScore"></div>

				
<script type="text/javascript">
var modalBox = null;
$(document).ready(function() {
	$(".sub_title_2.ohddien").text("종료과정 성적조회");
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1",
		"nomargin" : false //m_large
	});
	listEndScore(1);
});

function listEndScore(page){
	$('#listEndScore')
		.load(cUrl("/home/student/listEndScore"),
			{ 
				"curPage" : page
		    }
		);
}

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

