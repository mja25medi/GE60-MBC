<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
						<c:if test="${not empty ctgrList}">
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
						</c:if>
						<c:if test="${empty ctgrList}">
							<c:if test="${divCd eq 'CNTS' }" ><li style="padding-left:0px;">* <spring:message code="library.message.share.ctgr.nodata.cnts"/> </li></c:if>
							<c:if test="${divCd eq 'KNOW' }" ><li style="padding-left:0px;">* <spring:message code="library.message.share.ctgr.nodata.know"/> </li></c:if>
						</c:if>