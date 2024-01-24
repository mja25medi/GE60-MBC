<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="lecBbsAtclVO" value="${vo}"/>
<c:set var="isTch"  value="N" />
<c:if test="${CLASSUSERTYPE eq 'TCH'}"><c:set var="isTch"  value="Y" /></c:if>
                <div class="learn_top">
                    <div class="left_title">
                        <h3>학습문의</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>${pageInfo.totalRecordCount}</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>${pageInfo.currentPageNo}</strong>/${pageInfo.totalPageCount}</span>
                        </div>
                        <form class="board_search" onsubmit="return false" >
                            <fieldset class="form-row">
                            <legend class="blind">학습문의 검색</legend>
                            <select class="form-select" name="searchKey" id="searchKey" title="검색어 분류를 선택하세요.">
                                <option value="ALL" selected="selected"><spring:message code="common.title.all"/></option>
                                <option value="qnaTitle"><spring:message code="common.title.title"/></option>
                                <option value="regNm"><spring:message code="common.title.reguser"/></option>
                            </select>
                            <input type="text" class="_enterBind form-control w50" name="searchValue" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="${lecBbsAtclVO.searchValue }" id="searchValue" />
                            <button type="button" class="btn type2 btn_search">검색</button>
                            </fieldset>
                        </form>
                        <div class="page_btn">
                           <c:if test="${isTch ne 'Y'}">
                           <button type="button" class="btn type4" onclick="addQstn()"><spring:message code="button.write.article"/></button>
						</c:if>
                        </div>
                    </div>
                    	<div class="col-md-12" id="listDiv"></div>
                </div>
<script type="text/javascript">
	$(document).ready(function(){
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listQstn(1);
			}
		});
		
		$(".btn_search").bind("click", function(event) {
			listQstn(1);
		});
		listQstn(1);
	});

	function listQstn(page) {
		var searchKey = $("#searchKey").val();
		var searchValue = $("#searchValue").val();
		$("#listDiv").load(cUrl("/lec/qna/listQstn"),{
			"curPage" : page,
			"searchKey" : searchKey,
			"searchValue" : searchValue
		});
	}
	
	function readQstn(qnaSn, crsCreCd) {
		document.location.href = "/lec/qna/viewQnaMain?qnaSn=" + qnaSn + "&crsCreCd="+crsCreCd;
	}
	
	function addQstn(){
		var url = generateUrl("/lec/qna/writeQstnMain")
		document.location.href = url;
	}

</script>
