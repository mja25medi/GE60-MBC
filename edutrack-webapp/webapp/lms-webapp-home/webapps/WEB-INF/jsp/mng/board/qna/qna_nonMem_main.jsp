<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>
<c:set var="nonMemQnaVO" value="${vo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="form-inline">
					<div class="col-md-7 col-xs-12 mb5">
						<select name ="ansYn" id = "ansYn" class="_enterBind form-control input-sm" style="float:left;" onchange="listQstn(1)" title="board.title.bbs.search.select">
							<option value="">상태 전체</option>
							<option value="N">답변대기</option>	
							<option value="Y">답변완료</option>	
						</select>
						<select name ="searchKey" id = "searchKey" class="_enterBind form-control input-sm" style="float:left;" title="board.title.bbs.search.select">
							<!-- <option value="qnaTitle">제목</option> -->
							<option value="qsCts">내용</option>
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
	var curPage = 1;
	
	$(document).ready(function(){
		$("._enterBind").bind("keydown", eventForSearch);

		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listQstn(1);
			}
		}
		listQstn(${vo.curPage});
	});

	function listQstn(page) {
		var ansYn = $('#ansYn').val();
		var searchKey = $('#searchKey').val();
		var searchValue = $('#searchValue').val();
		
		$("#listArea").load(cUrl("/mng/brd/qna/listNonMemQstn"),{
			"ansYn" : ansYn,
			"curPage" : page,
			"searchKey" : searchKey,
			"searchValue" : searchValue
		});
	}
	
	function viewQstn(qnaSn,curPage) {
		document.location.href = "/mng/brd/qna/viewNonMemMain?qnaSn=" + qnaSn + "&curPage=" + curPage;
	}

	function writeAnsr(qnaSn) {
		var url = generateUrl("/mng/brd/qna/writeNonMemAnsrMain",{"qnaSn" : qnaSn});
		document.location.href = url;
	}
	
	function editAnsr(qnaSn) {
		var url = generateUrl("/mng/brd/qna/editNonMemAnsrMain",{"qnaSn" : qnaSn});
		document.location.href= url;
	}

</script>

