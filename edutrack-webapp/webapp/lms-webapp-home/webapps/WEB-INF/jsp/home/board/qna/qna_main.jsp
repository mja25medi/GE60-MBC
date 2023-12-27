<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<c:set var="orgMenuVO" value="${orgMenuVO}" scope="request" />
<c:set var="bbsAtclVO" value="${vo}" />
	<ul class="tabs">
		 <li id="ctgr_" name="qnaCtgrItem">
         	<a href="javascript:selectCategory('')">전체</a>
         </li>
         <c:forEach var="item" items="${qnaCtgrList}" varStatus="status">
         	<li id="ctgr_${item.codeCd }" name="qnaCtgrItem">
         		<a href="javascript:selectCategory('${item.codeCd}')">${item.codeNm }</a>
         	</li>
         </c:forEach>
    </ul>
      <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>${pageInfo.totalRecordCount}</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>${pageInfo.currentPageNo}</strong>/${pageInfo.totalPageCount}</span>
                        </div>
                        <form name="srhForm"  onsubmit="return false;">
                        	<input type="hidden" id=qnaCtgrCd name="qnaCtgrCd" value="${vo.qnaCtgrCd }">
                            <fieldset class="form-row">
                            <legend class="blind">공지사항 검색</legend>
                            <select class="form-select" name="searchKey" id="searchKey" title="검색어 분류를 선택하세요.">
								<option value="qnaTitle" <c:if test="${vo.searchKey eq 'qnaTitle'}"> selected="selected" </c:if>>제목</option>
								<option value="qnaCts" <c:if test="${vo.searchKey eq 'qnaCts'}"> selected="selected" </c:if>>내용</option>
							</select> 
							 <input type="text" class="_enterBind form-control w50 searchValue" id="searchValue" name="searchValue" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="${vo.searchValue }"  />
                            <button type="button" class="btn type2 btn_search" >검색</button>
							<c:if test="${not empty sessionScope.USERNO && vo.viewMode ne 'admin' }">
								<a href="javascript:writeQstn();" class="btn btn-primary btn-sm">문의하기</a>	
							</c:if>
                            </fieldset>
                        </form>
                    </div><!-- //board_info -->
	<div id="listArea"></div>
<script type="text/javascript">
	$(document).ready(function(){
		selectCategory("");
	
	$("._enterBind").bind("keydown", function(event) {
			if ($M.Check.Event.isEnter(event)) {
				listQstn(1);
			}
		});
		$(".btn_search").bind("click", function(event) {
			listQstn(1);
		});
		listQstn(1);
	});

	//검색
	function listQstn(page) {
		var qnaCtgrCd = $("#qnaCtgrCd").val();
		var searchKey = $('#searchKey').val();
		var searchValue = $(".searchValue").val();

		$("#listArea").load(cUrl("/home/brd/qna/listQstn"), {
			"qnaCtgrCd" : qnaCtgrCd,
			"curPage" : page,
			"searchKey" : searchKey,
			"searchValue" : searchValue
		});
	}

	function selectCategory(ctgrCd) {
		$("#qnaCtgrCd").val(ctgrCd);
		$("li[name=qnaCtgrItem]").removeClass("active");
		$("#ctgr_" + ctgrCd).addClass("active");
		listQstn(1);
	}

	function viewQstn(qnaSn) {
		var url = generateUrl("/home/brd/qna/viewQnaMain", {
			"qnaSn" : qnaSn
		});
		document.location.href = url;
	}

	function writeQstn() {
		var url = generateUrl("/home/brd/qna/writeQstnMain");
		document.location.href = url;
	}
</script>
