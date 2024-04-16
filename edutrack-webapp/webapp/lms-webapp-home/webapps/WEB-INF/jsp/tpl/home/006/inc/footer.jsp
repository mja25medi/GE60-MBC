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


<!-- 오른쪽 퀵메뉴 추가_240405 -->

<aside class="right">
	<nav class="quick">
		<a href="" class="btn">
			<i class="icon-png-logoemblem" aria-hidden="true"></i>HOME
		</a>
		<a href="" class="btn">
			<i class="icon-png-kakaotalk" aria-hidden="true"></i>1:1 카톡상담
		</a>
		<a href="" class="btn">
			<i class="icon-png-talk" aria-hidden="true"></i>1:1 문의
		</a>
		<a href="" class="btn">
			<i class="icon-png-monitor" aria-hidden="true"></i>나의 학습실
		</a>
	</nav>
</aside>

<!-- //오른쪽 퀵메뉴 추가_240405 -->

<footer class="footer">
	<button type="button" class="go_top"><i class="xi-angle-up-min"></i><span>TOP</span></button>
	<div class="container">
		<div class="footer_inner">
			<div class="left">
				<div class="footer_logo"><img src="/tpl/${COLOR_TPL}/img/new_main/bottom_logo.png" /></div>
			</div>
			<div class="right">
				<ul class="btn_area">
					<li>
						<a href="/home/main/goMenuPage?mcd=HM06001000">이용약관</a>
					</li>
					<li>
						<a href="/home/main/goMenuPage?mcd=HM06002000">개인보호처리방침</a>
					</li>
					<li>
						<a href="#" target="_blank">환불규정</a>
					</li>
					<li>
						<button type="button" data-ui-util="dropdown">패밀리 사이트<i class="xi-angle-up-min"></i></button>
						<div>
							<a href="https://corp.twosun.com/" target="_blank">투썬월드</a>
							<a href="https://www.twosunedu.com/" target="_blank">투썬에듀</a>
							<a href="https://www.twosun.ai/" target="_blank">투썬AI스쿨</a>
						</div>
						<script type="text/javascript">
							document.querySelector('[data-ui-util="dropdown"]').addEventListener("click", e => e.target.closest("button").classList.toggle("active") )
						</script>
					</li>
				</ul>
				
			</div>
		</div>

		<div class="address_group">
			<dl>
				<dt>투썬캠퍼스</dt>
				<dd>
					<div>
						<span class="txt_line">
							주식회사 투썬캠퍼스 원격평생교육원
						</span>
						<span class="txt_line">
							<strong>대표 : </strong> 이종현, 김종화
						</span>
					</div>

					<div>
						<span class="txt_line">
							<strong>사업자등록번호 : </strong> 498-81-00322
						</span>
	
						<span class="txt_line">
							<strong>통신판매업신고번호 : </strong> 제 2023-성남분당A-0486
						</span>
					</div>					

					<address class="txt_line">
						<strong>주소 : </strong> (13494) 경기도 성남시 분당구 판교역로 221, 투썬캠퍼스(삼평동, 투썬월드빌딩) 
					</address>

					<div>
						<span class="txt_line">
							<strong>개인정보관리책임자 : </strong> 김태선
						</span>
	
						<span class="txt_line">
							<strong>전화번호 : </strong> 031-706-8400
						</span>
	
						<span class="txt_line">
							<strong>이메일 : </strong> campus@twosun.com
						</span>
					</div>					

					<p class="copy"><br><br>
						ⓒ 주식회사 투썬캠퍼스 원격평생교육원 All Rights Reserved.
					</p>
				</dd>
			</dl>
		</div>
	</div>
</footer>

