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
<footer class="footer">
    <button type="button" class="go_top"><i class="xi-angle-up-min"></i><span>TOP</span></button>
    <div class="container">
       
        <div class="inner-wrap">
            <div class="footer_logo"><img src="/tpl/${COLOR_TPL}/img/common/logo_w.svg"></div>
            <ol>
                <li><a href="/home/main/goMenuPage?mcd=HM06001000">이용약관</a></li>
                <li><a href="/home/main/goMenuPage?mcd=HM06002000">개인보호처리방침</a></li>
            </ol>
            <ul>
                <li>TEL : 02-3460-8500, 2981(행정), 2969(교수지원), 2983, 2959(학습지원), 2982,2960(매체지원)</li>
                <li>FAX : 02-3461-8822</li>
                <li>E-mail : MEDIOPIATECH@MEDIOPIA.CO.KR</li>
                <li class="address">
                    <address>주소 : (38066) 서울시 강남구 도곡로 182 양제빌딩 3~4층</address>
                </li>
            </ul>
        </div>

    </div>
    
</footer>





