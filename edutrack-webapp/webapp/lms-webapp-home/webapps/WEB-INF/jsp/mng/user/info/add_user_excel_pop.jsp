<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
	<form id="examQbankForm" name="userAddForm" onsubmit="return false" action="/mng/user/userInfo">
	<div class="well">
		<ul style="padding-left:10px;">
			<li><spring:message code="user.message.userinfo.excelupload1"/> </li>
			<li><spring:message code="user.message.userinfo.excelupload2"/></li>
		</ul>
		<a href="javascript:downloadSample()" class="btn btn-info btn-sm"><i class="fa fa-file-excel-o fa-fw"></i><spring:message code="button.download.samplefile"/></a>
		<table style="width:100%; margin-top:15px;border-top:1px solid gray">
			<tr>
				<td style="padding-top:10px;">
					<spring:message code="common.title.upload.file"/> : <a href="javascript:uploderclick('useruploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
					<input type="file" name="useruploader" id="useruploader" style="display:none" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/><%-- 첨부파일 버튼 --%>
				</td>
			</tr>
			<tr>
				<td style="padding-top:10px;">
					<div id="userprogress" class="progress" style="margin:0px;">
						<div class="progress-bar progress-bar-success"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="text-center">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
	    var url = '/app/jquery/upload';  // 사용
	    var fileDir = "/${USER_ORGCD}/excelfile/userinfo";
	    $('#useruploader').fileupload({
	        url: url,
	        dataType: 'json',
	        formData : {"fileDir":fileDir, "organization":"${USER_ORGCD}","type":"excel"},
	        done: function (e, data) {
	        	var file = data.result;
	    		$.ajax({
	    			url : '/mng/user/userInfo/userExcelUpload'
	    			, data : {
	    				"fileName" : file.fileSaveNm,
	    				"filePath" : file.filePath
	    			}
	    			, method : "post"
	    			, success : function(resultVO) {
	    				if(resultVO.result > 0) {
	    					alert(resultVO.message);
	    					parent.modalBoxClose();
	    					parent.listPageing(1);
	    				} else {
	    					alert(resultVO.message);
	    					document.location.reload();
	    				}
	    				
	    			}
	    		});
			},
	        progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#userprogress .progress-bar').css(
	                'width',
	                progress + '%'
	            );
	        }
	    }).prop('disabled', !$.support.fileInput)
	        .parent().addClass($.support.fileInput ? undefined : 'disabled');
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function downloadSample() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml = '<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		var url = cUrl("/mng/user/userInfo/sampleExcelUserAdd");
		$("#_m_download_iframe").attr("src",url);
	}
	
	function getUserResultMessage(userList) {
		var resultMessage = "";
		userList.forEach((user) => {
			if(user.errorCode == null){
				resultMessage += user.lineNo + "행 : 성공\n";
			}
			else {
				resultMessage += user.lineNo + "행 : 실패,"+ user.errorCode + "\n";
			}
		});
		return resultMessage;
	}


</script>
