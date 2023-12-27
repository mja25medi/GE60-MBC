<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>
<c:set var="brdQnaQstnVO" value="${vo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="form-inline">
					<div class="col-md-7 col-xs-12 mb5">
						<select name ="qnaCtgrCd" id = "qnaCtgrCd" class="_enterBind form-control input-sm" style="float:left;" onchange="listQstn(1)" title="board.title.bbs.search.select">
							<option value="">분류 전체</option>
							<c:forEach var="item" items="${qnaCtgrList}" varStatus="status">
								<option value="${item.codeCd }">${item.codeNm }</option>
							</c:forEach>
						</select>
						<select name ="stsCd" id = "stsCd" class="_enterBind form-control input-sm" style="float:left;" onchange="listQstn(1)" title="board.title.bbs.search.select">
							<option value="">상태 전체</option>
							<option value="WAIT">답변대기</option>	
							<option value="COMP">답변완료</option>	
						</select>
						<select name ="searchKey" id = "searchKey" class="_enterBind form-control input-sm" style="float:left;" title="board.title.bbs.search.select">
							<option value="qnaTitle">제목</option>
							<option value="qnaCts">내용</option>
							<option value="regNm">작성자</option>
						</select>
						<div class="input-group" style="width:160px" style="float:left;">
							<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.title.input.searchvalue"/>"  value="${vo.searchValue}" placeholder="<spring:message code="common.title.all"/>" />
							<span class="input-group-addon" onclick="listQstn(1)" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="col-lg-12" style="margin-top:5px;" id="listArea">
					
				</div>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	//팝업박스
	var modalBox = null;
	var curPage = 1;

	$(document).ready(function(){
		$("._enterBind").bind("keydown", eventForSearch);

		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listQstn(1);
			}
		}
		listQstn(1);
	});

	function listQstn(page) {
		var qnaCtgrCd = $('#qnaCtgrCd').val();
		var searchKey = $('#searchKey').val();
		var searchValue = $('#searchValue').val();
		var stsCd = $('#stsCd').val();
		
		$("#listArea").load(cUrl("/mng/brd/qna/listQstn"),{
			"qnaCtgrCd" : qnaCtgrCd,
			"stsCd" : stsCd,
			"curPage" : page,
			"lecYn" : 'N',
			"searchKey" : searchKey,
			"searchValue" : searchValue
		});
	}
	
	function viewQstn(qnaSn) {
		document.location.href = "/mng/brd/qna/viewQnaMain?qnaSn=" + qnaSn;
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	function writeAnsr(qnaSn) {
		var url = generateUrl("/mng/brd/qna/writeAnsrMain",{"qnaSn" : qnaSn});
		document.location.href = url;
	}
	
	function editAnsr(qnaSn) {
		var url = generateUrl("/mng/brd/qna/editAnsrMain",{"qnaSn" : qnaSn});
		document.location.href= url;
	}

</script>

