<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>

                  <%--   <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>${pageInfo.totalRecordCount}</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>${pageInfo.currentPageNo}</strong>/${pageInfo.totalPageCount}</span>
                        </div>
                        <form name="srhForm" method="post"  class="board_search" onsubmit="listQstn(1); return false;">
                        	<input type="hidden" id=qnaCtgrCd name="qnaCtgrCd" value="${vo.qnaCtgrCd }">
                            <fieldset class="form-row">
                            <legend class="blind">공지사항 검색</legend>
                            <select class="form-select" name="searchKey" id="searchKey" title="검색어 분류를 선택하세요.">
								<option value="qnaTitle" <c:if test="${vo.searchKey eq 'qnaTitle'}"> selected="selected" </c:if>>제목</option>
								<option value="qnaCts" <c:if test="${vo.searchKey eq 'qnaCts'}"> selected="selected" </c:if>>내용</option>
							</select> 
							<input type="text" class="form-control w50" name="searchValue" id="searchValue" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="${vo.searchValue }">
                            <button type="submit" class="btn type2">검색</button>
							<c:if test="${not empty sessionScope.USERNO && vo.viewMode ne 'admin' }">
								<a href="javascript:writeQstn();" class="btn btn-primary btn-sm">문의하기</a>	
							</c:if>
                            </fieldset>
                        </form>
                    </div><!-- //board_info --> --%>

                    <table class="tstyle list">
                        <caption>문의 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w10 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                            <th scope="col" class="m_hidden">번호</th>
                            <th scope="col" class="m_hidden">분류</th>
                            <th scope="col" class="title">제목</th>
                            <th scope="col" class="m_hidden">첨부파일</th>
                            <th scope="col" class="m_hidden">등록일</th>
                            <th scope="col" class="m_hidden">처리상태</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">
						<c:forEach var="item" items="${qnaQstnList}" varStatus="status">
							<tr>
								<td class="m_hidden" data-title="번호">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
								<td class="m_hidden" data-title="분류">${item.qnaCtgrNm }</td>
								<td class="title" data-title="타이틀">
								<a href="javascript:viewQstn('${item.qnaSn}')">
										${item.qnaTitle}
								</a>
								</td>
									<td class="m_hidden" data-title="첨부파일">
										<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="fiSts">
											<a href="javascript:fileDown('${fileItem.fileSn}')" title="Download: ${fileItem.fileNm}">
												<i class="xi-attachment"></i>
												<span class="sr-only">첨부파일</span>
											</a>
										</c:forEach>
									</td>
								<td class="m_hidden" data-title="등록일"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}" /></td>
								<td class="m_hidden" data-title="처리상태">
									<c:if test="${item.stsCd eq 'WAIT' }"><span class="label fcGray">답변대기</span></c:if>
									<c:if test="${item.stsCd eq 'COMP' }"><span class="label fcBlue">답변완료</span></c:if>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty qnaQstnList}">
							<c:set var="colspan" value="5" />
							<tr>
								<td colspan="${colspan}">등록한 문의 내역이 없습니다.</td>
							</tr>
						</c:if>
 
 
  
                        </tbody>
                    </table>
            
                    <div class="board_pager">
                        <span class="inner">
                            <meditag:paging pageInfo="${pageInfo}" funcName="listQstn" name="front"/>
                        </span>
                    </div>
                    <!-- //board_pager -->



	


<script type="text/javascript">
	$(document).ready(function() {
	/* 	$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listQstn(1);
			}
		});
		$(".btn_search").bind("click", function(event) {
			listQstn(1);
		}); */
	});
</script>
