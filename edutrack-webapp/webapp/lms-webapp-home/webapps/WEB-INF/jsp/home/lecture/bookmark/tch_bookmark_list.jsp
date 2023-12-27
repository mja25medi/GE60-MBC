<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
	  <div class="learn_top">
                    <div class="left_title">
                        <h3>강의관리</h3>
                    </div>
                </div>
	 
	 <c:if test="${CLASSUSERTYPE eq 'TCH' }">
                  	<ul class="class_tab" id="tabMenuOper">
						<c:forEach items="${onlineSubjectList }" var="item">
							<li class="sbj_${item.sbjCd} "><a href="javascript:onclickTab('${item.sbjCd}')">${item.sbjNm }</a></li>
         				  </c:forEach>
				</ul>
    </c:if>
	
	<iframe name="subWorkFrame" id="subWorkFrame" frameborder="0" src="about:blank" scrolling="yes" title="Sub Work Frame" style="width:100%;min-height:100vh;"></iframe>			

<script type="text/javascript">
	$(document).ready(function() {
		var sbjCd = "${onlineSubjectList[0].sbjCd}"
		onclickTab(sbjCd)
	});
	
	
	function onclickTab(sbjCd) {
		var url = "/lec/bookmark/listTchBookmarkPop?sbjCd="+sbjCd;
		$(".active").removeClass("active");
		$(".sbj_"+sbjCd).addClass("active");
		document.getElementById('subWorkFrame').contentWindow.location.href=cUrl(url);
	}

</script>
