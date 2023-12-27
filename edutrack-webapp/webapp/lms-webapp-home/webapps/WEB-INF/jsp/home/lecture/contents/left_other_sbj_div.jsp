<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

							<div id="courseBox" style="float:left; width:100%">
								<form name="CourseSearch">
								<div class="input-group" style="margin-top:10px;">
									<input type="hidden" name="sbjCd" />
									<input type="text" name="sbjNm" id="courseSearchField" class="form-control input-sm" onclick="javascript:searchCourse()"/>
									<span class="input-group-btn">
										<a href="javascript:searchCourse()" class="btn btn-primary btn-sm"><spring:message code="button.search.subject"/></a>
									</span>
								</div>
								<input type="submit" value="submit" style="display:none" />
								</form>
								<div id="otherCntsTree" class="tree" style="float:none;width:100%;height:642px;overflow:auto; border:2px solid #4FADBC;margin-top:6px;font-size:14px;"></div>
							</div>

							<script type="text/javascript">
								$(document).ready(function() {
									$('#courseSearchField').keypress(function(e) {
										if(e.which == 13) { //-- 엔터인 경우만 반응
											searchCourse();
											return false;
										}
									});
								});

								/**
								 *  과목 찾기 폼
								 */
								function searchCourse() {
									var sbjNm = $('#courseSearchField').val();
									var addContent  = "<iframe id='courseFrame' name='courseFrame' width='100%' height='100%' "
										+ "frameborder='0' scrolling='no' src='<c:url value="/lec/bookmark/listSearchCourseForm"/>"
										+ "?sbjNm="+sbjNm+"'/>";
									modalBox.clear();
									modalBox.addContents(addContent);
									modalBox.resize(500, 360);
									modalBox.setTitle("<spring:message code="course.title.contents.search.subject"/>");
									modalBox.show();
								}

								function setOtherSubject(sbjCd, sbjNm) {
									if(sbjCd == ItemDTO.sbjCd) {
										alert('<spring:message code="course.message.contents.alert.msg4"/>');
									} else {
										DragItem.sbjCd = sbjCd;
										$('#courseSearchField').val(sbjNm);
										modalBoxClose();
										otherContentsList();
										//conlistDir();
									}
								}

								function otherContentsList() {
									$('#otherCntsTree')
									.jstree({
										"plugins" : [ "themes", "json_data", "ui", "crrm", "dnd", "types", "hotkeys"],
										"json_data" : {
											"ajax" : {
												"url" : "/lec/bookmark/listContentsJsonList",
												"data" : function (n) {
													return {
														"sbjCd" : DragItem.sbjCd,
														"id" : n.attr ? n.attr("id") : ""
													};
												}
											}
										},
										"types" : {
											"max_depth" : -2,
											"max_children" : -2,
											"valid_children" : [ "root" ],
											"types" : {
												"default" : {
													"valid_children" : [],
													"icon" : { "image" : "/img/framework/icon/filetype/file.png" }
												},
												"lesson" : {
													"valid_children" : [],
													"icon" : { "image" : "/img/framework/icon/icon_lesson.gif" }
												},
												"chepter" : {
													"valid_children" : [],
													"icon" : { "image" : "/img/framework/icon/icon_contents.gif" }
												},
												"root" : {
													"valid_children" : [],
													"icon" : { "image" : "/img/framework/icon/icon_course.gif" },
													"start_drag" : false
												}
											}
										},
										"core" : { "initially_open" :  [ "00" ] },
										"themes" : { "theme" : "default", "dots" : true },
										"rules" : { "multitree" : true }
									})
									.bind("move_node.jstree", function (e, data) {
										$.jstree.rollback(data.rlbk);
									})
									.bind("select_node.jstree", function (e, data) {
										DragItem.source = "subject";
									})
									.bind("loaded.jstree", function (e, data) {
									    data.inst.open_all(-1); // -1 opens all nodes in the container     })
									})
								    .bind("refresh.jstree", function (e, data) {
									    data.inst.open_all(-1); // -1 opens all nodes in the container     })
									});
								}

								function goContents() {
									var url = cUrl("/lec/bookmark/viewCntsMain")+"?bookmarkVO.sbjCd="+ItemDTO.sbjCd+"${AMPERSAND}bookmarkVO.unitCd="+ItemDTO.unitCd;
									var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=auto,resizable=yes,width=1100,height=700";
									var contentsWin = window.open(url, "contentsWin", winOption);
									contentsWin.focus();
								}

							</script>