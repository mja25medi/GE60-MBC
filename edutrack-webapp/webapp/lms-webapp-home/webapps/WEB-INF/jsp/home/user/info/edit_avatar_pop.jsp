<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<iframe id="avatarFrame" src="${avatarEditUrl}" style="width: 100%; height: 110vh;  border : 0; framespacing : 0; marginheight : 0; marginwidth : 0; scrolling : no;  vspace : 0;"></iframe> 


<script type="text/javascript">
	window.addEventListener('message', function(e) {
		  console.log('child message');
		  console.log(e.data); 
		  console.log("e.origin : " + e.origin); 
		  var obj = e.data;
		  console.log("result : "+obj.result);
		  if(obj.result == 'success'){
			  parent.location.reload();
			  //parent.initAvatar(obj.userNo);
		  }
	});
           
</script>	
