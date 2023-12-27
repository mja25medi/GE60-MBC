<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

							<div id="courseBox" style="float:left; width:100%">
								<div id="amstListArea" class="tree" style="float:none;width:100%;height:642px;overflow:auto; border:2px solid #4FADBC;margin-top:6px;font-size:14px;"></div>
								<input type="hidden" name="cntsTypeCd" id="cntsTypeCd" value="${cntsTypeCd}"/>
							</div>
							
							<script type="text/javascript">
								$(document).ready(function() {
									amstList()
								});

								function amstList() {
									$("#amstListArea").load(
											cUrl("/mng/course/contents/listCntAsmt"),
											{ "crsCreCd": "${crsCreCd}"}
											)
								}
								
								function selectShareCnts(cntsCd) {
									var cntsTypeCd = $('#cntsTypeCd').val();
									$("#zoomUrlTr").css("display", "");
							   		$("#codingUrlTr").css("display", "");
									$("#contentsForm").find('input[name="asmtSn"]').val(cntsCd);
									$("input:radio[name=cntsTypeCd]").prop("checked", false);
									$("input:radio[name=cntsTypeCd][value="+cntsTypeCd+"]").prop("checked", true);
								}
							</script>