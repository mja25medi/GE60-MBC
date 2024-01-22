<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<c:set var="orgMenuVO" value="${orgMenuVO}" scope="request" />
<c:set var="bbsAtclVO" value="${vo}" />
	<ul class="tabs">
		 <li <c:if test="${empty vo.ctgrCd || vo.ctgrCd eq ''}" >class="active"</c:if>>
         		<a href="#_none;" onClick="selectCategory()">전체</a>
         </li>
         <c:forEach var="item" items="${categoryList}" varStatus="status">
         	<li <c:if test="${vo.ctgrCd eq item.codeCd}" >class="active"</c:if>>
         		<a href="#_none;" onClick="selectCategory('${item.codeCd}')">${item.codeNm }</a>
         	</li>
         </c:forEach>
    </ul>

                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>${pageInfo.totalRecordCount}</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>${pageInfo.currentPageNo}</strong>/${pageInfo.totalPageCount}</span>
                        </div>
                        <form onsubmit="listAtcl(1); return false;" id="bbsAtclForm" name="bbsAtclForm" class="board_search">
							<input type="hidden" id="ctgrCd" name="ctgrCd" value="${vo.ctgrCd }">
							<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex }">
							<input type="hidden" id="pageScale" name="pageScale" value="${bbsInfoVo.listViewCnt }">
							<input type="hidden" id="bbsCd" name="bbsCd" value="${vo.bbsCd }"> 
							<input type="hidden" id="atclSn" name="atclSn">
                            <fieldset class="form-row">
                                <legend class="blind">공지사항 검색</legend>
								<select class="form-select mr5" name="searchKey" id="select" title="검색어 분류를 선택하세요.">
									<option value="all" <c:if test="${vo.searchKey eq 'all' && empty vo.searchKey}"> selected="selected" </c:if>>전체</option>
									<option value="title" <c:if test="${vo.searchKey eq 'title'}"> selected="selected" </c:if>>제목</option>
									<option value="cts" <c:if test="${vo.searchKey eq 'cts'}"> selected="selected" </c:if>>내용</option>
								</select> 
								<input type="text" class="form-control w50" name="searchValue" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="${vo.searchValue }">
                                 <button type="submit" class="btn type2">검색</button>
                            </fieldset>
                        </form>
                    </div><!-- //board_info -->
	
			
				<c:if test="${bbsInfoVo.writeUseYn eq 'Y' && not empty sessionScope.USERNO}">
					<div style=" margin-bottom: 15px; text-align: right; margin-top: -15px;">
						<a href="javascript:addAtcl();" class="btn btn-primary btn-sm"><spring:message code="button.write.article" /></a>
					</div>
				</c:if>

 





					<div class="listFaq">
						<ul>
							<c:forEach var="item" items="${bbsAtclList}" varStatus="status">
								<li>
									 <a href="#A${status.index }" class="question" >
									 	<span class="category">Q</span>
									 	<span class="title">${item.atclTitle}</span>
									 </a>
									 <div class="answer" id="A${status.index }">
									 	<div class="answer_box">
									 		<span class="category">A</span>
									 		<div class="con">${item.atclCts }</div>
									 	</div>
									 </div>
								</li>
							</c:forEach>
						<c:if test="${empty bbsAtclList}">
							<li>
									 <a href="#" class="question">등록한 FAQ 내역이 없습니다.</a>
							</li>
						</c:if>
						</ul>
					</div>
				
					<div class="board_pager">
						<span class="inner"> 
							<meditag:paging pageInfo="${pageInfo}" funcName="listAtcl" name="front"/>
						</span>
					</div>

                    <!-- //board_pager -->


<script type="text/javascript">
	$(document).ready(function(){
		
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listAtcl(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			listAtcl(1);
		});
		
	});

	$(".listFaq.question").on("click", function(){
        active(this, "accordion");
    });
	
	//검색
	function listAtcl(page) {
		$('#bbsAtclForm')
			.find('input[name=pageIndex]').val(page).end()
		$("#bbsAtclForm").attr("action","/home/brd/bbs/listAtclMain");
		document.bbsAtclForm.submit();
	}	
	
	function selectCategory(ctgrCd){
		$("#ctgrCd").val(ctgrCd);
		listAtcl(1);
	}
	
	<c:if test="${bbsInfoVo.writeUseYn eq 'Y' && not empty sessionScope.USERNO}">
		/** 글 등록 화면으로 이동 */
	 	function addAtcl() {
			$("#bbsAtclForm").attr("action","/home/brd/bbs/addFormAtclMain");
			document.bbsAtclForm.submit();
		} 
	</c:if>
	
	</script>
