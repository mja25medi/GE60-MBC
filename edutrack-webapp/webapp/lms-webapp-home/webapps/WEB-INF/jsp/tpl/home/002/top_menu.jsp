<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
			<div id="toplinks">
				<h1><a href="<c:url value="/home/main/goMenuPage?amp;mcd=MC00000000"/>"><img src="${img_base}/heading/h1_logo.gif" width="193" height="51" alt="한국기술교육대학교 고용노동연수원" /></a></h1>
				<hr />
                <ul id="utilities">
                    <li><a href="<c:url value="/home/main/goMenuPage?amp;mcd=MC00000000"/>"><img src="${img_base}/menu/util_menu01.gif" width="11" height="10" alt="홈" /></a></li>
                    <c:if test="${empty sessionScope.USERNO || sessionScope.USERTYPE eq 'MEM_PROVIS'}"><%--준회원은 로그인과 회원가입이 표시되야 함 --%>
                    <li><a href="<c:url value="/home/main/goMenuPage?amp;mcd=MC00000204"/>"><img src="${img_base}/menu/util_menu02.gif" width="28" height="10" alt="로그인" /></a></li>
                    <li><a href="<c:url value="/home/main/goMenuPage?amp;mcd=MC00000203"/>"><img src="${img_base}/menu/util_menu03.gif" width="36" height="10" alt="회원가입" /></a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.USERNO && sessionScope.USERTYPE ne 'MEM_PROVIS'}"><%--준회원은 마이페이지가 표시되면 안 --%>
                    <li><a href="<c:url value="/home/main/goMenuPage?amp;mcd=MC00000212"/>"><img src="${img_base}/menu/util_menu05.gif" alt="마이페이지" /></a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.USERNO}">
                    <li><a href="<c:url value="/home/user/logout" />"><img src="${img_base}/menu/util_menu0201.gif" width="37" height="10" alt="로그아웃" /></a></li>
                    </c:if>
                    <li class="end"><a href="<c:url value="/home/main/goMenuPage?amp;mcd=MC00000228"/>"><img src="${img_base}/menu/util_menu04.gif" width="37" height="10" alt="사이트맵" /></a></li>
                </ul>
				<hr />
			</div>
			<div id="topnav">
                <h2 class="hidden">주요 서비스메뉴</h2>
                <ul>
				<c:set var="menuCnt" value="0" />
				<c:forEach items="${menuDTO.subList}" var="item">
					<c:if test="${item.topMenuYn eq 'Y'}">
						<c:set var="menuCnt" value="${menuCnt + 1}" />
                    	<li id="topmenu${menuCnt}">
                    		<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${item.menuCd}"/>" onmouseover="navSwitch(${menuCnt})" onfocus="navSwitch(${menuCnt})"  <c:if test="${item.optnCtgrCd1 eq 'TARGET'}">target="${item.optnCd1}"</c:if> >
							<c:if test="${not empty item.topMenuImg}">
                    			<img src="${img_base}/menu/${item.topMenuImg}_off.gif" alt="${item.menuNm}" id="tbtn${menuCnt}">
        					</c:if>
        					<c:if test="${empty item.topMenuImg}">${item.menuNm}</c:if>
        					</a>
							<c:if test="${not empty item.subList}">
							    <ul id="submenu${menuCnt}">
								<c:set var="subMenuCnt" value="0"/>
								<c:forEach items="${item.subList}" var="item1">
									<c:set var="subMenuCnt" value="${subMenuCnt + 1}"/>
									<c:if test="${subMenuCnt eq 1 }">
                            			<li class="first">
                       					<c:if test="${empty item1.topMenuImg}">
                            				<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${item1.menuCd}"/>" <c:if test="${fn:startsWith(item1.menuUrl,'http://')}">target="_blank"</c:if>>${item1.menuNm}</a>
                       					</c:if>
                       					<c:if test="${not empty item1.topMenuImg}">
                            				<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${item1.menuCd}"/>" <c:if test="${fn:startsWith(item1.menuUrl,'http://')}">target="_blank"</c:if>><img src="${img_base}/menu/${item1.topMenuImg}_on.gif" alt="${item1.menuNm}"></a>
					            		</c:if>
                     					</li>
									</c:if>
									<c:if test="${subMenuCnt > 1 }">
                            			<li>
                       					<c:if test="${empty item1.topMenuImg}">
                            				<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${item1.menuCd}"/>" <c:if test="${fn:startsWith(item1.menuUrl,'http://')}">target="_blank"</c:if>>${item1.menuNm}</a>
                       					</c:if>
                       					<c:if test="${not empty item1.topMenuImg}">
                            				<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${item1.menuCd}"/>" <c:if test="${fn:startsWith(item1.menuUrl,'http://')}">target="_blank"</c:if>><img src="${img_base}/menu/${item1.topMenuImg}_on.gif" alt="${item1.menuNm}"></a>
					            		</c:if>
                            			</li>
									</c:if>
								</c:forEach>
								</ul>
							</c:if>
                    	</li>
					</c:if>
				</c:forEach>
                </ul>
            </div>
