<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="forum" value="${forumVO}"/>
<c:set var="forumListVO" value="${forumListVO}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/message_box.js"/>
	<script type="text/javascript">

		$(document).ready(function() {
		});

		function goList(){
			location.href=cUrl("/lec/forum/main");
		}
	</script>
</mhtml:class_head>
<body>

	<div id="Class_wrap">
		<div id="Cheader">
	    	<h1>강의실</h1>
	        <h2>${CRSCRENM}</h2>
	    </div>
	    <div id="Contanier">
	    	<c:import url="../../common/lec_menu.jsp"/>
	        <div id="Contents">

				<div id="location">
	                <p class="c_tit"><meditag:title title="${MENUNAME}" width="320" height="22" /></p>
	                <ul class="breadcrumb">
	                    <li class="home">HOME</li>
						<li>${MENUPATH}</li>
	                </ul>
	            </div>
				<form id="forumForm" name="forumForm" onsubmit="return false" action="/lec/forum">
				<input type="hidden" name="forumSn" />
				<input type="hidden" name="curPage" />
				<input type="hidden" name="parForumSn" />  <!--상위글 유무 파악 -->

				<table style="width:98%; margin: 0 auto"  class="vt_dtable">
					<caption class="sr-only">게시판 정보 등록 폼</caption>
					<tr height="35">
						<th>토론 제목</th>
						<td colspan="3">
							${forum.forumTitle}
						</td>
					</tr>
					<tr height="35">
						<th class="top" width="20%">토론사용 여부</th>
						<td class="top">
							<input type="radio" style="border:0" name="forumRegYn" value="Y"  disabled="true"/>사용&nbsp;
							<input type="radio" style="border:0" name="forumRegYn" value="N" disabled="true"/>미사용
						</td>
					</tr>
					<tr height="33">
						<th>토론등록일시</th>
						<td >
							${forum.forumStartDttm}
						</td>
						<th>토론종료일시</th>
						<td >
							${forum.forumEndDttm}
						</td>
					</tr>
					<tr height="35">
						<th>토론 내용</th>
						<td colspan="3" height="250px" valign="top" style="padding: 10px;">${forum.forumCts}</td>
					</tr>
				</table>
				<br>
				<p class="btnsright">
					<a href="#" onclick="goList()"><img src="${img_base}/common/btn/btn_list02.gif" alt="목록"></img></a>
				</p>

			</form>
		</div>
	</div>
</div>
</body>
</mhtml:class_html>