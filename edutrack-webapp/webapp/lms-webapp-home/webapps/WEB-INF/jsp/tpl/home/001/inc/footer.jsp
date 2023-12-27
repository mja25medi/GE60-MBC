<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		 import="org.springframework.web.context.support.WebApplicationContextUtils"
		 import="egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteService"
		 import="egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteVO"
		 import="egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteCtgrVO"
		 import="egovframework.edutrack.comm.service.SysMenuMemService"
		 import="egovframework.edutrack.modules.org.menu.service.OrgMenuVO"
		 import="java.util.List" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<%
	String authGrpCd = UserBroker.getUserType(request);
	String orgCd = UserBroker.getUserOrgCd(request);
	if("".equals(authGrpCd)) authGrpCd = "GUEST";
	SysMenuMemService service1 = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(SysMenuMemService.class);
	OrgMenuVO footerMenuVO = service1.getOrgHomeMenuList(orgCd, authGrpCd);
	request.setAttribute("footerMenuVO", footerMenuVO);

	EtcRelatedSiteService relatedSiteService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(EtcRelatedSiteService.class);
	EtcRelatedSiteCtgrVO rscVO = new EtcRelatedSiteCtgrVO();
	rscVO.setOrgCd(orgCd);
	List<EtcRelatedSiteCtgrVO> siteCtgrList = relatedSiteService.listSiteAll(rscVO).getReturnList();
	request.setAttribute("siteCtgrList", siteCtgrList);
%>
		<footer>
			<div class="container repair">
				<ul class="logo_b">
					<li>
						<c:if test="${not empty SUB_LOGO1SN}">
							<c:if test="${not empty SUB_LOGO1URL}">
						<a href="${SUB_LOGO1URL}" target="_blank">
							</c:if>
						<img src="/app/file/view/${SUB_LOGO1SN}" alt="${SUB_LOGO1URL}" style="width:156px;height:40px" />
							<c:if test="${not empty SUB_LOGO1URL}">
						</a>
							</c:if>
						</c:if>
					</li>
					<li>
						<c:if test="${not empty SUB_LOGO2SN}">
							<c:if test="${not empty SUB_LOGO2URL}">
						<a href="${SUB_LOGO2URL}" target="_blank">
							</c:if>
						<img src="/app/file/view/${SUB_LOGO2SN}" alt="${SUB_LOGO1URL}" style="width:156px;height:40px" />
							<c:if test="${not empty SUB_LOGO2URL}">
						</a>
							</c:if>
						</c:if>
					</li>
				</ul>
				<ul class="f_menu">
				<c:forEach var="item" items="${footerMenuVO.subList}">
					<c:if test="${item.divLineUseYn eq 'Y'}">
						<c:set var="menuName1" value="${item.menuNm}"/>
						<c:forEach var="lang" items="${item.menuLangList}">
							<c:if test="${LOCALEKEY eq lang.langCd}"><c:set var="menuName1" value="${lang.menuNm}"/></c:if>
						</c:forEach>
					<li>
						<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${item.menuCd}"/>" id="menu_${item.menuCd}">${menuName1}</a>
					</li>
					</c:if>
					<c:forEach items="${item.subList}" var="item1">
						<c:if test="${item1.divLineUseYn eq 'Y'}">
							<c:set var="menuName2" value="${item1.menuNm}"/>
							<c:forEach var="lang2" items="${item1.menuLangList}">
								<c:if test="${LOCALEKEY eq lang2.langCd}"><c:set var="menuName2" value="${lang2.menuNm}"/></c:if>
							</c:forEach>
					<li>
						<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${item1.menuCd}"/>" id="menu_${item1.menuCd}">${menuName2}</a>
					</li>
						</c:if>
					</c:forEach>
				</c:forEach>
				</ul>
				<div class="warp_sfamily">
					<c:if test="${fn:length(langList) > 1 && LANG_MULTIUSE eq 'Y' }">
					<div class="dropdown dropup pull-right">
					<form name="Lang">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <spring:message code="common.messate.select.language"/> <span class="caret"> </span> </a>
						<ul class="dropdown-menu">
							<c:forEach var="lang" items="${langList}" varStatus="status">
							<li>
								<label style="padding:0px 20px 0px 20px;">
									<input type="radio" name="lang" onclick="changeLang('${lang.codeCd}');" <c:if test="${LOCALEKEY eq lang.codeCd}">checked="checked"</c:if> /> ${lang.codeNm}
								</label>
							</li>
							</c:forEach>
							<script type="text/javascript">
								function changeLang(lang) {
									document.location.href = "<c:url value="/home/main/indexChgLang?langCd="/>"+lang;
								}	
							
							</script>
						</ul>
					</form>
					</div>
					</c:if>
					<c:forEach var="relCtgr" items="${siteCtgrList}">
					<div class="dropdown dropup pull-left">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<%-- ${relCtgr.title} --%>
						${fn:substring(relCtgr.title,0,15)}<c:if test="${fn:length(relCtgr.title) >15 }">..</c:if> <%-- <spring:message code="common.title.golink"/> --%>
						<span class="caret"> </span>
						</a>
						<ul class="dropdown-menu">
							<c:forEach var="relSite" items="${relCtgr.subList}">
							<li>
								<a href="${relSite.siteUrl}" target="_blank">${relSite.title}</a>
							</li>
							</c:forEach>
						</ul>
					</div>
					</c:forEach>
				</div>
				<address>
					<p>${fn:replace(WWW_FOOTER, crlf, '<br/>')}</p>
				</address>
			</div>
		</footer>