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
	var url = cUrl("/home/student/printCertificate?crsCreCd="+crsCreCd+"&stdNo="+stdNo);
	var option = "width=830, height=640, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
	var reportWin = window.open(url,'reportWin', option);
	reportWin.focus();
}

	
	

</script>

