<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

								<ul class="list-group menu-group">
									<c:if test="${empty menuList}">
									<li><div class="menu-group-item"><spring:message code="common.message.nodata"/></div></li>
									</c:if>
									<c:forEach var="item1" items="${menuList}" varStatus="status1">
										<c:set var="typeImg" value="fa fa-file-o"/>
										<c:set var="menuName1" value="${item1.menuNm}"/>
										<c:forEach var="lang" items="${item1.menuLangList}">
											<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
										</c:forEach>
										<c:if test="${not empty item1.subList}"><c:set var="typeImg" value="fa fa-folder-open-o"/></c:if>
									<li data-id="${item1.menuCd}" data-parcd="">
										<div class="menu-group-item menu-group-lvl1">
											<i class="${typeImg}" style="cursor:move"></i> <span class="ml10 mr15">${menuName1} <small class="hidden-xs"> | ${item1.menuCd}</small></span> 
											<a href="javascript:menuEdit('${item1.menuCd}')" title="<spring:message code="button.edit"/>" class="mr5"><i class="fa fa-cog"></i></a>
											<a href="javascript:subMenuWrite('${item1.menuCd}');" title="<spring:message code="button.write.menu"/>"><i class="fa fa-edit"></i></a>											
										<c:if test="${empty item1.subList}">
											<span class="pull-right">
												<label><spring:message code="system.title.menu.auth.use"/> <input type="checkbox" name="viewAuth" value="${item1.menuCd}" style="border:0" <c:if test="${item1.viewAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/></label>
												<label class="ml10"><spring:message code="system.title.menu.auth.write"/> <input type="checkbox" name="creAuth" value="${item1.menuCd}" style="border:0" <c:if test="${item1.creAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/></label>
											</span>
										</c:if>
											<div class="clearfix"></div>
										</div>
										<c:if test="${not empty item1.subList}">
										<ul class="list-group menu-group">
											<c:forEach var="item2" items="${item1.subList}">
												<c:set var="typeImg" value="fa fa-file-o"/>
												<c:if test="${item2.subCnt > 0}">
													<c:set var="typeImg" value="fa fa-folder-open-o"/>
												</c:if>
												<c:set var="menuName2" value="${item2.menuNm}"/>
												<c:set var="fontColor" value="#333"/><c:if test="${item2.useYn ne 'Y'}"><c:set var="fontColor" value="#999999"/></c:if>
												<c:forEach var="lang" items="${item2.menuLangList}">
													<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName2" value="${lang.menuNm}"/></c:if>
												</c:forEach>
											<li data-id="${item2.menuCd}" data-parcd="${item2.parMenuCd}">
												<div class="menu-group-item menu-group-lvl2">
													<i class="${typeImg}" style="cursor:move"></i> <span class="ml10 mr15">${menuName2} <small class="hidden-xs"> | ${item2.menuCd}</small></span> 
													<a href="javascript:menuEdit('${item2.menuCd}')" title="<spring:message code="button.edit"/>"><i class="fa fa-cog"></i></a>
													<a href="javascript:subMenuWrite('${item2.menuCd}');" title="<spring:message code="button.write.menu"/>"><i class="fa fa-edit"></i></a>													
												<c:if test="${empty item2.subList}">
													<span class="pull-right">
														<label><spring:message code="system.title.menu.auth.use"/> <input type="checkbox" name="viewAuth" value="${item2.menuCd}" style="border:0" <c:if test="${item2.viewAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/></label>
														<label class="ml10"><spring:message code="system.title.menu.auth.write"/> <input type="checkbox" name="creAuth" value="${item2.menuCd}" style="border:0" <c:if test="${item2.creAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/></label>
													</span>
												</c:if>
													<div class="clearfix"></div>
												</div>
												<c:if test="${not empty item2.subList}">
												<ul class="list-group menu-group">
													<c:forEach var="item3" items="${item2.subList}">
														<c:set var="typeImg" value="fa fa-file-o"/>
														<c:if test="${item3.subCnt > 0}">
															<c:set var="typeImg" value="fa fa-folder-open-o"/>
														</c:if>
														<c:set var="menuName3" value="${item3.menuNm}"/>
														<c:set var="fontColor" value="#333"/><c:if test="${item3.useYn ne 'Y'}"><c:set var="fontColor" value="#999999"/></c:if>
														<c:forEach var="lang" items="${item3.menuLangList}">
															<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName3" value="${lang.menuNm}"/></c:if>
														</c:forEach>
													<li data-id="${item3.menuCd}" data-parcd="${item3.parMenuCd}">
														<div class="menu-group-item menu-group-lvl3">
															<i class="${typeImg}" style="cursor:move"></i> <span class="ml10 mr15">${menuName3} <small class="hidden-xs"> | ${item3.menuCd}</small></span> 
															<a href="javascript:menuEdit('${item3.menuCd}')" title="<spring:message code="button.edit"/>"><i class="fa fa-cog"></i></a>
															<span class="pull-right">
																<label><spring:message code="system.title.menu.auth.use"/> <input type="checkbox" name="viewAuth" value="${item3.menuCd}" style="border:0" <c:if test="${item3.viewAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/></label>
																<label class="ml10"><spring:message code="system.title.menu.auth.write"/> <input type="checkbox" name="creAuth" value="${item3.menuCd}" style="border:0" <c:if test="${item3.creAuth eq 'Y'}">checked</c:if> <c:if test="${authGrpCd eq ''}">disabled</c:if>/></label>
															</span>
															<div class="clearfix"></div>
														</div>
													</li>
													</c:forEach>												
												</ul>
												</c:if>
											</li>
											</c:forEach>
										</ul>
										</c:if>
									</li>	
									</c:forEach>
								</ul>
								<script type="text/javascript">
									$(document).ready(function() {
										var dragItem = "";
									    $(".list-group").sortable({
									    	handle : 'i.fa-folder-open-o, i.fa-file-o',
									    	stop : function(event, ui) {
									    		var parcd = ui.item.attr('data-parcd');
									    		sortString = "";
									    		$(".list-group").children('li').each(function(){
									    			if($(this).attr("data-parcd") == parcd) {
									    				sortString += "|"+$(this).attr("data-id");
									    			}
									    		});
									    		sortString = sortString.substring(1);
									    		menuSort(parcd, sortString);
									    	}
									    });
									    $(".list-group").disableSelection();
									    
									    ItemDTO.menuCount = ${fn:length(menuList)};
									});
								</script>	
