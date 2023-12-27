<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<head>
</head>
<c:set var="orgMenuVO" value="${orgMenuVO}" scope="request" />
<c:set var="bbsAtclVO" value="${vo}" />



                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>${pageInfo.totalRecordCount}</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>${pageInfo.currentPageNo}</strong>/${pageInfo.totalPageCount}</span>
                        </div>

                        <form onsubmit="return" id="bbsAtclForm" name="bbsAtclForm" class="board_search">
							<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex }">
							<input type="hidden" id="pageScale" name="pageScale" value="${bbsInfoVo.listViewCnt }">
							<input type="hidden" id="bbsCd" name="bbsCd" value="${vo.bbsCd }"> 
							<input type="hidden" id="atclSn" name="atclSn">
                            <fieldset class="form-row">
                            <legend class="blind">공지사항 검색</legend>
                            <select class="form-select" name="searchKey" id="select" title="검색어 분류를 선택하세요.">
									<option value="all" <c:if test="${vo.searchKey eq 'all' && empty vo.searchKey}"> selected="selected" </c:if>>전체</option>
									<option value="title" <c:if test="${vo.searchKey eq 'title'}"> selected="selected" </c:if>>제목</option>
									<option value="cts" <c:if test="${vo.searchKey eq 'cts'}"> selected="selected" </c:if>>내용</option>
									<option value="regNm" <c:if test="${vo.searchKey eq 'regNm'}"> selected="selected" </c:if>>작성자</option>
                            </select>
                            <input type="text" class="form-control w50" name="searchValue" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="${vo.searchValue }">
                            <button type="submit" class="btn type2">검색</button>	
							<c:if test="${bbsInfoVo.writeUseYn eq 'Y' && not empty sessionScope.USERNO}">
									<a href="javascript:addAtcl();" class="btn btn-primary btn-sm"><spring:message code="button.write.article" /></a>
							</c:if>
                            </fieldset>
                        </form>
                    </div><!-- //board_info -->

                    <table class="tstyle list">
                        <caption>공지사항 테스트 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
							<c:if test="${vo.bbsCd eq 'REVIEW'}">
								<col class="w20 m_hidden">
							</c:if>			
                            <col>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w8 m_hidden">
                        </colgroup>
                        <thead class="dhead">
							<tr>
								<th scope="col" class="m_hidden">번호</th>
								<c:if test="${vo.bbsCd eq 'REVIEW'}">
									<th scope="col">과정명</th>
								</c:if>
								<th scope="col" class="title">제목</th>
								<c:if test="${vo.bbsCd eq 'REVIEW'}">
									<th scope="col">만족도</th>
								</c:if>
								<c:if test="${vo.bbsCd ne 'REVIEW'}">
									<th scope="col" class="m_hidden">첨부파일</th>
								</c:if>
								<th scope="col" class="m_hidden">등록일</th>
								<th scope="col" class="m_hidden">조회수</th>
							</tr>

                        </thead>
                        <tbody class="dbody" id="listView">
							<c:forEach var="item" items="${bbsAtclList}" varStatus="status">
								<tr class="notice">
									<td class="m_hidden">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
									<c:if test="${vo.bbsCd eq 'REVIEW'}">
										<td class="m_hidden">${item.crsCreNm}</td>
									</c:if>
									<td class="title">
									<meditag:reply level="${item.atclLvl}"/>
										<a href="javascript:readAtcl('${item.atclSn}','${item.lockYn }','${item.originRegNo }', '${item.regNo }','${item.parRegNo }' )">
											${item.atclTitle}
											<c:if test="${item.recently eq 'NEW' }">
												<i class="xi-new"></i><span class="sr-only">새글</span>
											</c:if>
										</a>
									</td>
									<c:if test="${vo.bbsCd eq 'REVIEW'}">
										<td class="m_hidden">
											<div class="jr-stars review mr0">
											<c:forEach begin="1" end="${item.starScore }" step="1">
											 	<div class="jr-ratenode jr-rating"></div>
											</c:forEach>
											</div>
										</td>
									</c:if>
									<c:if test="${vo.bbsCd ne 'REVIEW'}">
										<td class="m_hidden">
											<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="fiSts">
												<a href="#" onclick="javascript:fileDown('${fileItem.fileSn}');" title="Download: ${fileItem.fileNm}">
													<i class="xi-attachment"></i>
													<span class="sr-only"></span>
												</a>
											</c:forEach>
										</td>
									</c:if>
									<td class="m_hidden"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}" /></td>
									<td class="m_hidden">${item.hits}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty bbsAtclList}">
								<c:set var="colspan" value="6" />
								<tr>
									<td colspan="${colspan}"><spring:message code="board.message.bbs.atcl.nodata" /></td>
								</tr>
							</c:if>

                        </tbody>
                    </table>
            
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

	//검색
	function listAtcl(page) {
		$('#bbsAtclForm')
			.find('input[name=pageIndex]').val(page).end()
		$("#bbsAtclForm").attr("action","/home/brd/bbs/listAtclMain");
		document.bbsAtclForm.submit();
	}	
	
	<c:if test="${bbsInfoVo.writeUseYn eq 'Y' && not empty sessionScope.USERNO}">
		/** 글 등록 화면으로 이동 */
	 	function addAtcl() {
			$("#bbsAtclForm").attr("action","/home/brd/bbs/addFormAtclMain");
			document.bbsAtclForm.submit();
		} 
	</c:if>
	
	function readAtcl(atclSn, lockYn,oriUserNo, userNo,parRegNo){
		var writeUseYn
		var url;
		if(lockYn == "Y"){
			url = "/home/brd/bbs/viewPasswordCheckMain";
		} else {
			url = "/home/brd/bbs/viewAtclMain";
		}
		$("#atclSn").val(atclSn);
		$("#bbsAtclForm").attr("action",url);
		document.bbsAtclForm.submit();
	}	
	</script>
