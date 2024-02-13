<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script defer src="/tpl/${COLOR_TPL}/js/common.js"></script>	
				<div class="content" id="cntsDiv"></div>

<script type="text/javascript">
	$(document).ready(function(){
		if("${vo.msgSn}" != '' && "${vo.msgTransSn}" != ''){
			viewMsg("${vo.msgSn}", "${vo.msgTransSn}");
		}else{
			listMsg(1);
		}
	});
	
	function listMsg(page) {
		$("#cntsDiv").load(
			cUrl("/home/message/listMsg"),{
				"curPage" : page
			}
		);
	}
	
	function viewMsg(msgSn, msgTransSn) {
		$("#cntsDiv").load(
			cUrl("/home/message/viewMsg"),
			{
				'msgSn' : msgSn,
				'msgTransSn' : msgTransSn
			}
		);
	}

	function deleteMsg(msgTransSn) {
		if(confirm("해당 쪽지를 정말 삭제 하시겠습니까?")) {
			$.ajax({
				url : '/home/message/deleteMsg'
				,data : {
					'msgTransSn' : msgTransSn
				}
				,success : function(data) {
					if(data.result == '1'){
						// 성공
						listMsg(1);
					} else {
						// 실패
						alert(data.message);
					}
				}
			});
		}
	}
	
</script>
