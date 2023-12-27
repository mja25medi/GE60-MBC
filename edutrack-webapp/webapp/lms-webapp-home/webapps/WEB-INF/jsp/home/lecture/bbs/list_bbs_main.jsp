<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="lecBbsAtclVO" value="${vo}"/>
<c:set var="isTch"  value="N" />
<c:if test="${CLASSUSERTYPE eq 'TCH'}"><c:set var="isTch"  value="Y" /></c:if>
			
				  <div class="learn_top">
                    <div class="left_title">
                        <c:if test="${lecBbsAtclVO.bbsCd eq 'NOTICE' }"><h3>과목 공지사항</h3></c:if>  
                        <c:if test="${lecBbsAtclVO.bbsCd eq 'PDS' }"><h3>과목 자료실</h3></c:if>  
                    </div>
                </div>
                <div class="segment">
                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>${pageInfo.totalRecordCount}</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>${pageInfo.currentPageNo}</strong>/${pageInfo.totalPageCount}</span>
                        </div>
                        <form class="board_search" onsubmit="return false" >
                        	<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}"/>
                            <fieldset class="form-row">
                            <legend class="blind">공지사항 검색</legend>
                            <select class="form-select" name="searchKey" id="searchKey" title="검색어 분류를 선택하세요.">
                              		<option value="all"><spring:message code="common.title.all"/></option>
									<option value="title"><spring:message code="common.title.title"/></option>
									<option value="regNm"><spring:message code="common.title.reguser"/></option>
                            </select>
                            <input type="text" class="_enterBind form-control w50" name="searchValue" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="${lecBbsAtclVO.searchValue }" id="searchValue" />
                            <button type="button" class="btn type2 btn_search">검색</button>
                            </fieldset>
                        </form>
                        <div class="page_btn">
                            <c:if test="${CLASSUSERTYPE eq 'TCH'}">
						<c:if test="${lecBbsAtclVO.bbsCd ne 'QNA'}">
							  <button type="button" class="btn type4" onclick="addAtcl('${lecBbsAtclVO.bbsCd}')"><spring:message code="button.write.article"/></button>
						</c:if>
					</c:if>
					<c:if test="${CLASSUSERTYPE eq 'STU'}">
						<c:if test="${lecBbsAtclVO.bbsCd eq 'QNA'}">
							  <button type="button" class="btn type4" onclick="addAtcl('${lecBbsAtclVO.bbsCd}')"><spring:message code="button.write.article"/></button>
						</c:if>
					</c:if>		
                        </div>
                        
                    </div>
					<div class="col-md-12" id="listDiv"></div>
                </div>


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
		listAtcl(1);
	});

	function listAtcl(page) {
		var searchKey = $("#searchKey").val();
		var searchValue = $("#searchValue").val();
		var bbsCd = $("#bbsCd").val();
		$("#listDiv").load(cUrl("/lec/bbs/listAtcl"),{
			"curPage" : page,
			"bbsCd" : bbsCd,
			"searchKey" : searchKey,
			"searchValue" : searchValue
		});
	}

	function readAtcl(atclSn, lockYn,oriUserNo, userNo,parRegNo){
		var bbsCd="${lecBbsAtclVO.bbsCd}";
		/* bbsAtclDTO.atclSn=${item.atclSn}&amp; */
		var searchKey="${lecBbsAtclVO.searchKey}";
		var searchValue="${lecBbsAtclVO.searchValue}";
		var curPage="${lecBbsAtclVO.curPage}";
		var isTch = "${isTch}";
		var ssUserNo = "${USERNO}";

		if(isTch == "Y"){

//		} else if(ssUserNo == regNo){

//		} else if(ssUserNo == userNo){

//		} else if(ssUserNo == parRegNo){

		} else {
			if("Y" == lockYn && ssUserNo != oriUserNo){
				alert("<spring:message code="board.message.qna.alert.private"/>");
				return;
			}
		}
		location.href = cUrl("/lec/bbs/readAtclMain")+"?bbsCd="+bbsCd
				+"${AMPERSAND}atclSn="+atclSn
				+"${AMPERSAND}searchKey="+searchKey
				+"${AMPERSAND}searchValue="+searchValue
				+"${AMPERSAND}curPage"+curPage;

	}
	
	function addAtcl(bbsCd){
		var url = generateUrl("/lec/bbs/addAtclMain")+"?bbsCd="+bbsCd;
		document.location.href = url;
	}
</script>
