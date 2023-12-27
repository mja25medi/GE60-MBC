<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />

                    
			<div id="workBody"></div>
                
                
<c:if test="${not empty ALERT_MESSAGE}">
	<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
	<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
	<script type="text/javascript">
		//$.growlUI('알림', '${flashMsg}');
		$(document).ready(function() {
			alert('${flashMsg}');
		});
	</script>
</c:if>	
<script type="text/javascript">
	var reshType = 'ReshType';
	// 팝업박스
	var modalBox = null;
	
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "reshInfoPop"
		});
		listResh(1);
	});
	
	function listResh(page){
		$('#workBody')
			.load(cUrl("/home/resh/listResh"),
				{ 
					"curPage" : page
// 				    ,"sortKey":sortKey
			    }
			);
	}
	
	function reshInfo(reshSn){ //시험정보
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/home/resh/viewReshPop"/>"
			+ "?reshSn="+reshSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("설문정보");
		modalBox.show();
	}
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	

</script>
