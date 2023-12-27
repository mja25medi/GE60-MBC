<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="brdQnaQstnVO" value="${vo}"/>

				<table summary="<spring:message code="board.title.bbs.atcl.list"/>" style="margin-top:5px;" class="table table-bordered">
					<colgroup>
						<col style="width:60px;"/>
						<col style="width:150px;"/>
						<col style="width:auto"/>
						<col style="width:150px;"/>
						<col style="width:70px;"/>
						<col style="width:100px;"/>
						<col style="width:100px;"/>
					</colgroup>
					<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col">분류</th>
						<th scope="col"><spring:message code="common.title.title"/></th>
						<th scope="col"><spring:message code="common.title.reguser"/></th>
						<th scope="col"><spring:message code="common.title.regdate"/></th>
						<th scope="col"><spring:message code="common.title.answeryn"/></th>
						<th scope="col"><spring:message code="common.title.answer"/></th>
					</tr>
					</thead>
					<tbody id="tbodyList">
					<c:forEach items="${qnaQstnList}" var="item" varStatus="status">
					<tr>
						<td>${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
						<td class="text-center">${item.qnaCtgrNm}</td>
						<td class='left wordbreak'>
							<a href="javascript:viewQstn('${item.qnaSn }')">${item.qnaTitle}</a>
						</td>
						
						<td>${item.userNm}</td>
						<td><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
						<td class="text-center">
							<c:choose>
								<c:when test="${item.stsCd eq 'COMP'}">답변완료</c:when>
							    <c:otherwise>답변대기</c:otherwise>
							</c:choose>
						</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${item.stsCd eq 'COMP'}"><a class="btn btn-default btn-sm" href="#none" onclick="javascript: editAnsr(${item.qnaSn})" title="${item.qnaTitle}">답변수정</a></c:when>
							    <c:otherwise><a class="btn btn-primary btn-sm" href="#none" onclick="javascript: writeAnsr(${item.qnaSn})" title="${item.qnaTitle}">답변작성</a></c:otherwise>
							</c:choose>
							
						</td>
					</tr>
					</c:forEach>

					<c:if test="${empty qnaQstnList}">
					<tr>
						<td colspan="7"><spring:message code="common.message.nodata"/></td>
					</tr>
					</c:if>
					</tbody>
				</table>
				<meditag:paging pageInfo="${pageInfo}" funcName="listQstn"/>

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
				listQna(1);
			}
		}
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}


</script>

