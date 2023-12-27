<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

							<div id="olcBox" style="float:left; width:100%;margin-top:10px;">
								<form name="OLCSearch" class="form-inline">
   								<div class="input-group" style="width:40%;float:left;">
									<div class="input-group-btn">
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
									</div>
									<input type="hidden" name="shareCtgrCd" id="shareCtgrCd"/>
									<input type="text" name="shareCtgrNm" id="shareCtgrNm" class="form-control input-sm" value="" onclick="clickDropdown2()"/>
								</div>
								<div class="input-group" style="width:60%;float:left;">
									<input type="text" name="shareSearchValue" id="shareSearchValue" class="form-control input-sm" value=""/>
									<span class="input-group-btn">
										<a href="javascript:searchShare();" class="btn btn-primary btn-sm"><spring:message code="button.search"/></a>
									</span>
								</div>
								<div style="clear:both;"></div>
								</form>
								<div id="olcContentsListArea" style="float:none;width:100%;height:642px;overflow:auto; border:2px solid #4FADBC;margin-top:6px;font-size:14px;"></div>
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
									var url = cUrl("/mng/library/clibShareCnts/listOlcCntsForCntsMgr");

									$("#olcContentsListArea").load(url, { "clibShareOlcCntsVO.ctgrCd" : ctgrCd, "clibShareOlcCntsVO.useYn":"Y", "clibShareOlcCntsVO.searchValue" : searchValue})
								}

								function previewShareCnts(cntsCd) {
									var url = cUrl("/mng/library/clibShareCnts/previewOlcMain?clibShareOlcCntsVO.cntsCd="+cntsCd);
									var winOption = "width=1100, height=770, top=10, left=10, scrollbars=0";
									var previewWin = window.open(url, "previewWin", winOption);
									previewWin.focus();
								}

								function selectShareCnts(cartrgCd, cartrgNm) {
									var unitCd = $("#contentsForm").find('input[name="contentsVO.unitCd"]').val();
									var unitType = $("#contentsForm").find('input[name="contentsVO.unitType"]').val();
									if(unitCd == "" || unitCd == undefined) {
										alert("<spring:message code="course.message.contents.alert.msg1"/>");
										return;
									}
									if(unitType != "L") {
										alert("<spring:message code="course.message.contents.alert.msg2"/>");
										return;
									}

									$("#contentsForm").find('input[name="contentsVO.unitNm"]').val(cartrgNm);
									$("#contentsForm").find('input[name="contentsVO.unitFilePath"]').val(cartrgCd);
									$("#contentsForm").find('input[name="contentsVO.olcYn"]').val("Y");
									//$("#contentsForm").find('input[name="contentsVO.cntsTypeCd"]').val("OLCCNTS");

									$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("disabled", true);
									$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("disabled", false);
									$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("checked", true);
									$("#contentsForm").find('input[name="contentsVO.critTm"]').val("");
									$("#contentsForm").find('input[name="contentsVO.critTm"]').attr("disabled",true);
									//$("#contentsForm").find('input[name="contentsVO.prgrChkYn"]').prop("checked", false);
								}
							</script>