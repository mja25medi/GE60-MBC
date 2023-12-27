<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

					<div class="text-right" style="padding:7px;background-color:#f3f3f3;">
						<a href="javascript:uploderclick('uploader');" id="fileUploadButton" class="btn btn-primary btn-xs" title="<spring:message code="course.title.contents.add.file"/>" alt="<spring:message code="course.title.contents.add.file"/>"><i class="fa fa-upload"></i></a>
						<input type="file" name="uploader" id="uploader" multiple style="display:none"/>
						<a href="javascript:unzipFile();" id="fileUnzipButton" class="btn btn-primary btn-xs" title="<spring:message code="course.title.contents.unzip"/>" alt="<spring:message code="course.title.contents.unzip"/>"><i class="glyphicon glyphicon-compressed"></i></a>
						<a href="javascript:delFile();" id="fileDeleteButton" class="btn btn-primary btn-xs" title="<spring:message code="course.title.contents.delfile"/>" alt="<spring:message code="course.title.contents.delfile"/>"><i class="glyphicon glyphicon-trash"></i></a>
						<div id="progress" class="progress" style="margin-bottom:0px;width:300px;"><div class="progress-bar progress-bar-success"></div></div>
					</div>
					<ul class="list-group" style="height:518px;overflow-y:auto;margin-bottom:0px;">
						<form name="FileForm" id="FileForm">
						<c:set var="linkExt" value=".html.htm.mp3.mp4.wmv.wma"/>
						<c:forEach var="item" items="${fileList}" varStatus="status">
						<li class="list-group-item cnts-item" style="padding:5px 10px 5px 10px;">
							<input type="checkbox" name="fileNm" value="${item.fileNm}" /> ${item.fileNm}
						</li>
						</c:forEach>
						</form>
					</ul>

					<script type="text/javascript">
						$(document).ready(function() {
							$('#uploader').fileupload({

								'url'			: cUrl('/app/jquery/upload'),
								'dataType'		: 'json',
								'formData'		: { 'fileDir': '/usercnts${filePath}/${fileNm}',
													"organization"	: "${USER_ORGCD}",
													'type'		: 'contents'},			// 추가 파라매터
								'add'			: function (e, data) {
													$('#progress').show();
													data.submit();
												},
								'done'			: onCompleteCallback,	// 단일 ${attachName} 전송후 콜백함수
								'progressall' 	: function (e, data) {
													var progress = parseInt(data.loaded / data.total * 100, 10);
													$('#progress .progress-bar').css(
														'width',
														progress + '%'
													)
												}
							}).prop('disabled', !$.support.fileInput)
						    .parent().addClass($.support.fileInput ? undefined : 'disabled');

							$('#progress').hide();
						});

						function onCompleteCallback(e, data) {
							$('#progress').hide();
							listFile();
						}

						function unzipFile() {
							var fileList = $("#FileForm input[name='fileNm']:checked").stringValues().split(",");

							if(fileList.length > 1) {
								alert("<spring:message code="olc.message.userfile.alert.selectonee"/>");
								return;
							} else {
								var fileExt = getFileExtention(fileList[0]);
								if(fileExt != "zip") {
									alert("<spring:message code="olc.message.userfile.alert.nozipfile"/>");
									return;
								}
							}
				            $.post(
				                    "/mng/olc/userFile/unzipFile",
				                    {
				                          filePath : "${filePath}/${fileNm}"
				                         ,fileNm : fileList[0]
				                    },
				                    function(result) {
				                    	listFile();
				                    	reloadPage();
				                    }
				               );
						}

						function delFile() {
							var fileList = $("#FileForm input[name='fileNm']:checked").stringValues();
							if(fileList.length < 1) {
								alert("<spring:message code="olc.message.userfile.alert.selectonee"/>");
								return;
							}
							if(confirm("<spring:message code="common.message.alert.filedelete"/>")) {
					            $.post(
					                    cUrl("/mng/olc/userFile/removeFile"),
					                    {
					                         filePath : "${filePath}/${fileNm}/",
					                         fileNm : fileList
					                    },
					                    function(result) {
					                    	listFile();
					                    }
					               );
							} else {
								return;
							}
						}
					</script>