<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<form  onsubmit="return false" name="userInfoForm" id="userInfoForm">
	<div class="well">
		<ul style="padding-left:10px;">
			<li><spring:message code="user.message.userdept.excelupload1"/> </li>
			<li><spring:message code="user.message.userinfo.excelupload2"/></li>
		</ul>
		<a href="javascript:downloadSample()" class="btn btn-info btn-sm"><i class="fa fa-file-excel-o fa-fw"></i><spring:message code="button.download.samplefile"/></a>
		<table style="width:100%;margin-top:15px;border-top:1px solid gray">
			<tr>
				<td style="padding-top:15px;">
					<spring:message code="common.title.upload.file"/> : <a href="javascript:uploderclick('useruploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
					<input type="file" name="useruploader" id="useruploader" style="display:none" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/><%-- 첨부파일 버튼 --%>
				</td>
			</tr>
			<tr>
				<td>
					<div id="userprogress" class="progress" style="margin-bottom:0px;">
						<div class="progress-bar progress-bar-success"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="text-center">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	</form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
	    var url = '/app/jquery/upload';  // 사용
	    var fileDir = "/${USER_ORGCD}/excelfile/userdeptinfo";
	    $('#useruploader').fileupload({
	        url: url,
	        dataType: 'json',
	        formData : {"fileDir":fileDir, "organization":"${USER_ORGCD}","type":"excel"},
	        done: function (e, data) {
	        	var file = data.result;
	    		$.ajax({
	    			url : '/mng/user/deptInfo/deptExcelUpload'
	    			, data : {
	    				"fileName" : file.fileSaveNm,
	    				"filePath" : file.filePath
	    			}
	    			, method : "post"
	    			, success : function(resultVO) {
	    				if(resultVO.result > 0) {
	    					alert(resultVO.message);
	    					parent.listPageing(1);
	    					parent.modalBoxClose();
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
		var url = cUrl("/mng/user/deptInfo/sampleExcelDept");
		$("#_m_download_iframe").attr("src",url);
	}


</script>