<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
							<div id="shareBox" style="width:100%;margin-top:10px;">
								<div id="amstListArea" class="subject-area scroll_custom" ></div>
								<input type="hidden" name="cntsTypeCd" id="cntsTypeCd" value="${cntsTypeCd}"/>
							</div>

							<script type="text/javascript">
								$(document).ready(function() {
									amstList()
								});

								function amstList() {
									$("#amstListArea").load(
											cUrl("/lec/cnts/listAsmt"),
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