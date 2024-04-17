<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
							<div id="shareBox" style="width:100%;margin-top:10px;">
								<form name="MediaSearch" class="board_search ">
   								<div class="input-group" style="width:40%;float:left;">
									<%-- <div class="input-group-btn">
										<button onclick="clickDropdown()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu" id="shareCtgrDrop">
											<c:forEach var="item" items="${ctgrList}">
											<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
											<c:set var="typeImgName" value="contents" />
											<c:if test="${fn:length(item.subList) > 0}">
												<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
												<c:set var="typeImgName" value="category" />
											</c:if>
											<li style="padding-left:0px;"><a href="javascript:setCtgr('${item.ctgrCd}','${item.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item.ctgrNm}</a></li>
											<c:forEach var="item1" items="${item.subList}">
												<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
												<c:set var="typeImgName" value="contents" />
												<c:if test="${fn:length(item1.subList) > 0}">
													<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
													<c:set var="typeImgName" value="category" />
												</c:if>
											<li style="padding-left:15px;"><a href="javascript:setCtgr('${item1.ctgrCd}','${item1.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item1.ctgrNm}</a></li>
												<c:forEach var="item2" items="${item1.subList}">
													<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
													<c:set var="typeImgName" value="contents" />
													<c:if test="${fn:length(item2.subList) > 0}">
														<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
														<c:set var="typeImgName" value="category" />
													</c:if>
											<li style="padding-left:30px;"><a href="javascript:setCtgr('${item2.ctgrCd}','${item2.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item2.ctgrNm}</a></li>
													<c:forEach var="item3" items="${item2.subList}">
														<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
														<c:set var="typeImgName" value="contents" />
														<c:if test="${fn:length(item3.subList) > 0}">
															<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
															<c:set var="typeImgName" value="category" />
														</c:if>
											<li style="padding-left:45px;"><a href="javascript:setCtgr('${item3.ctgrCd}','${item3.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item3.ctgrNm}</a></li>
														<c:forEach var="item4" items="${item3.subList}">
															<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
															<c:set var="typeImgName" value="contents" />
															<c:if test="${fn:length(item4.subList) > 0}">
																<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
																<c:set var="typeImgName" value="category" />
															</c:if>
											<li style="padding-left:60px;"><a href="javascript:setCtgr('${item4.ctgrCd}','${item4.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item4.ctgrNm}</a></li>
														</c:forEach>
													</c:forEach>
												</c:forEach>
											</c:forEach>
										</c:forEach>
										</ul>
									</div> --%>
									<input type="hidden" name="cntsTypeCd" id="cntsTypeCd" value="${cntsTypeCd}"/>
									<input type="hidden" name="shareCtgrCd" id="shareCtgrCd"/>
									<!-- <input type="text" name="shareCtgrNm" id="shareCtgrNm" class="form-control mr5" value="" onclick="clickDropdown()"/> -->
								</div>
								<div class="input-group" style="width:60%;float:left;">
									<input type="text" name="shareSearchValue" id="shareSearchValue" class="form-control input-sm" value=""/>
									<input type="hidden" name="searchKey" id="searchKey" class="form-control input-sm" value=""/>
									<span class="input-group-btn">
										<a href="javascript:searchShare();" class="btn type2"><spring:message code="button.search"/></a>
									</span>
								</div>
								<div style="clear:both;"></div>
								</form>
								<div id="shareContentsListArea" class="subject-area scroll_custom" ></div>
							</div>

							<script type="text/javascript">
								$(document).ready(function() {
									searchShare();
								});

								//-- 검색의 드롭다운 버튼 클릭시...
								function clickDropdown() {
									$("#shareCtgrDrop").toggle();
								}

								//-- 트리에서 분류 선택시
								function setCtgr(ctgrCd, ctgrNm) {
									$("#shareCtgrCd").val(ctgrCd);
									$("#shareCtgrNm").val(ctgrNm);
									searchShare();
									$("#shareCtgrDrop").hide();
								}

								function searchShare() {
									var ctgrCd = $("#shareCtgrCd").val();
									var searchValue = $("#shareSearchValue").val();
									var searchKey = "cntsNm";
									var cntsTypeCd = $('#cntsTypeCd').val();
									var sbjType = "${sbjType}";
									if(["VOD", "CDN","MLINK"].includes(cntsTypeCd)) {
										var url = cUrl("/lec/cnts/listMediaCntsForCntsMgr");										
										$("#shareContentsListArea").load(url, { "ctgrCd" : ctgrCd, "useYn":"Y", "searchValue" : searchValue, "cntsTypeCd": '${cntsTypeCd}', "searchKey":searchKey, "sbjType":sbjType})
									} 
								}

								function selectShareCnts(cntsCd, cntsNm) {
									var cntsTypeCd = $('#cntsTypeCd').val();
									var unitCd = $("#contentsForm").find('input[name="unitCd"]').val();
									var unitType = $("#contentsForm").find('input[name="unitType"]').val();
									if(unitCd == "" || unitCd == undefined) {
										alert("<spring:message code="course.message.contents.alert.msg1"/>");
										return;
									}
									if(unitType != "L") {
										alert("<spring:message code="course.message.contents.alert.msg2"/>");
										return;
									}

									//$("#contentsForm").find('input[name="unitNm"]').val(cntsNm);
									$("#contentsForm").find('input[name="unitFilePath"]').val(cntsCd);
									//$("input:radio[name=cntsTypeCd]").prop("checked", false);
									//$("input:radio[name=cntsTypeCd][value="+cntsTypeCd+"]").prop("checked", true);
									$("#contentsForm").find('input[name="olcYn"]').val("N");
									$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("checked", true);
									$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("disabled", false);
									$("#contentsForm").find('input[name="critTm"]').attr("disabled",false);
									$("#contentsForm").find('input[name="critTm"]').val("0");
									//$("#contentsForm").find('input[name="prgrChkYn"]').prop("checked", true);
								}
								
								function selectMobileShareCnts(cntsCd, cntsNm, filePath) {
									var unitCd = $("#contentsForm").find('input[name="unitCd"]').val();
									var unitType = $("#contentsForm").find('input[name="unitType"]').val();
									if(unitCd == "" || unitCd == undefined) {
										alert("<spring:message code="course.message.contents.alert.msg1"/>");
										return;
									}
									/* if(unitType != "L") {
										alert("<spring:message code="course.message.contents.alert.msg2"/>");
										return;
									} */

									//$("#contentsForm").find('input[name="unitNm"]').val(cntsNm);
									$("#contentsForm").find('input[name="mobileFilePath"]').val(filePath);
									//$("#contentsForm").find('input[name="cntsTypeCd"]').val("MEDIA");
									$("input:radio[name=cntsTypeCd]").prop("checked", false);
									$("input:radio[name=cntsTypeCd][value="+cntsTypeCd+"]").prop("checked", true);
									$("#contentsForm").find('input[name="olcYn"]').val("N");

									$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("disabled", false);
									$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("checked", true);
									$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("disabled", true);
									$("#contentsForm").find('input[name="critTm"]').attr("disabled",false);
									$("#contentsForm").find('input[name="critTm"]').val("0");
									//$("#contentsForm").find('input[name="contentsVO.prgrChkYn"]').prop("checked", false);
									
								}
								
								function previewShareCnts(cntsCd) {
									var url = cUrl("/lec/cnts/previewMediaPop")+"?cntsCd="+cntsCd;
									var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=780,height=480";
									var contentsWin = window.open(url, "contentsWin", winOption);
									contentsWin.focus();
								}
							</script>