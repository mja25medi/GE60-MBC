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
		<div class="foot_banner">
		    <div class="container">
		      	<a href="http://www.moel.go.kr/" target="_blank">
			        <img src="/tpl/002/img/main/foot_banner01.gif" alt="고용노동부 새창으로 열림">
			    </a>
			   	<a href="http://www.hrd.go.kr" target="_blank">
			        <img src="/tpl/002/img/main/foot_banner02.gif" alt="HRD-Net 새창으로 열림">
			    </a>
			    <a href="http://www.sen.go.kr/web/services/page/viewPage.action?page=kor/info/info_04_01_tab01.html" target="_blank">
			        <img src="/tpl/002/img/main/foot_banner03.gif" alt="원격평생교육시설 새창으로 열림">
			    </a>
			    <a href="http://www.hrdkorea.or.kr/" target="_blank">
			        <img src="/tpl/002/img/main/foot_banner04.gif" alt="한국산업인력공단 새창으로 열림">
			    </a>
		    </div>
		</div>
		<div class="container">
		    <div class="call_advice">
		        <small>상담센터</small>
		        <a class="title" href="tel:02-3460-8500">02-3460-8500</a>
		        <p><small>FAX 02-3461-8822</small></p>
		        <p>
		            <small>평일 09:00 ~ 18:00(점심시간 12:00 ~ 13:00제외)</small>
		        </p>
		        <p>
		        	<small>토·일·공휴일 휴무</small>
		        </p>
		    </div>
		    <div>
		        <nav class="footer_nav_bar">
		            <ul class="footer_nav">
		                <li><a href="/home/main/goMenuPage?mcd=HM06001000"><strong>이용약관</strong></a></li>
                        <li><a href="/home/main/goMenuPage?mcd=HM06002000"><strong>개인정보처리방침</strong></a></li>
                        <li><a href="/home/main/goMenuPage?mcd=HM06002000"><strong>이메일무단수집거부</strong></a></li>
		            </ul>                
		        </nav>
		        <div class="address_group">
		            <address>
		                ${fn:replace(WWW_FOOTER, crlf, '<br/>')}
		            </address>
		            <span class="copyright">Copyright ⓒ 메디오피아테크. All Rights Reserved.</span>
		        </div>
		          <c:forEach var="item" items="${siteCtgrList }">
	                <div class="relate_site">
	                    <a href="#" class="title" title="관련사이트열기">${fn:substring(item.title,0,15)}<c:if test="${fn:length(item.title) >15 }">..</c:if><i class="xi-caret-up"></i></a>
	                    <ul class="list">
	                    	<c:forEach var="subItem" items="${item.subList }">
	                        	<li><a href="${subItem.siteUrl }" target="_blank" title="새창">${subItem.title }</a> </li>
	                        </c:forEach>
	                    </ul>
	                </div>
                </c:forEach>
		    </div>
		</div>
            

