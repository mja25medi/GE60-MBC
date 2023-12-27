<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<% // 서브 메뉴 체크 %>
<c:set var="chkSubMenu" value="N" />

<script>
function formatSizeUnits(bytes){
	  if      (bytes >= 1073741824) { bytes = (bytes / 1073741824).toFixed(2) + " GB"; }
	  else if (bytes >= 1048576)    { bytes = (bytes / 1048576).toFixed(2) + " MB"; }
	  else if (bytes >= 1024)       { bytes = (bytes / 1024).toFixed(2) + " KB"; }
	  else if (bytes > 1)           { bytes = bytes + " bytes"; }
	  else if (bytes == 1)          { bytes = bytes + " byte"; }
	  else                          { bytes = "0 bytes"; }
	  return bytes;
}
</script>

<div class="tstyle_view">
	<div class="title">${vo.qnaTitle}</div>
	<ul class="head">
		<li class="write">
			<strong>작성자</strong>
			<span>${vo.userNm}</span>
		</li>
		<li class="date">
			<strong>작성일시</strong>
			<span><meditag:dateformat type="0" delimeter="." property="${vo.regDttm}" /></span>
		</li>
		<li class="sts">
			<strong>처리상태</strong>
			<span>
				<c:if test="${vo.stsCd ne 'COMP' }">답변대기</c:if>
				<c:if test="${vo.stsCd eq 'COMP' }">답변완료</c:if>
			</span>
		</li>
	</ul>
	<div class="tb_contents">
		<c:if test="${vo.editorYn eq 'Y' }">
			${vo.qnaCts}
		</c:if>
		<c:if test="${vo.editorYn ne 'Y' }">
			${fn:replace(vo.qnaCts,crlf,"<br/>")}
		</c:if>
	</div>
	<c:if test="${not empty vo.attachFiles}">
		<div class="add_file_list">
			<strong class="title">첨부파일</strong>
			<ul class="add_file">
				<c:forEach var="fileItem" items="${vo.attachFiles}" varStatus="status">
					<li>
						<a href="javascript:fileDown('${fileItem.fileSn}');" class="file_down">
							<img src="../../../tpl/002/img/board/file_doc.png" alt="">
							<span class="text">${fileItem.fileNm}</span>
							<span class="fileSize fileSize${status.index}">
								<script type="text/javascript">
									var fileSize = "${fileItem.fileSize}";
									var index = "${status.index}";
									fileSize = formatSizeUnits(fileSize);
									
									$(".fileSize"+index).html("("+fileSize+")");
								</script>
							</span>
						</a>
						<span class="link">
							<a class="btn-line btn-down" href="#_none" onclick="javascript:fileDown('${fileItem.fileSn}');" title="다운로드">다운로드<i></i></a>
						</span>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<c:if test="${not empty ansrVO }">
		<div class="answer">
			<div class="title_area">
			    <strong class="title">[답변] ${vo.qnaTitle}</strong>
				<span class="date">
					<b>${ansrVO.chrgPrsnNm}</b>
					<em>
						<c:choose>
							<c:when test="${ansrVO.regDttm eq ansrVO.modDttm}">
								<meditag:dateformat type="1" delimeter="." property="${ansrVO.regDttm}" />
							</c:when>
							<c:otherwise>
								<meditag:dateformat type="1" delimeter="." property="${ansrVO.modDttm}" />
							</c:otherwise>
						</c:choose>
				   	</em>
			    </span>
			</div>
			<div class="cont">
				<c:if test="${ansrVO.editorYn eq 'Y' }">
					${ansrVO.qnaCts}
				</c:if>
				<c:if test="${ansrVO.editorYn ne 'Y' }">
					${fn:replace(ansrVO.qnaCts,crlf,"<br/>")}
				</c:if><br>
				<c:if test="${not empty ansrVO.attachFiles}">
					<div class="add_file_list">
						<strong class="title">첨부파일</strong>
						<ul class="add_file">
							<c:forEach var="fileItem" items="${ansrVO.attachFiles}" varStatus="status">
								<li>
									<a href="javascript:fileDown('${fileItem.fileSn}');" class="file_down">
										<img src="../../../tpl/002/img/board/file_doc.png" alt="">
										<span class="text">${fileItem.fileNm}</span>
										<span class="fileSize fileSize${status.index}">
											<script type="text/javascript">
												var fileSize = "${fileItem.fileSize}";
												var index = "${status.index}";
												fileSize = formatSizeUnits(fileSize);
												
												$(".fileSize"+index).html("("+fileSize+")");
											</script>
										</span>
									</a>
									<span class="link">
										<a class="btn-line btn-down" href="#_none" onclick="javascript:fileDown('${fileItem.fileSn}');" title="다운로드">다운로드<i></i></a>
									</span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</c:if>
</div>

<div class="btn_area">
	<button type="button" onclick="listQstn();" class="btn gray2">목록</button>
	<c:if test = "${vo.viewMode eq 'admin' and vo.stsCd eq 'WAIT' }">
		<button type="button" onclick="writeAnsr();" class="btn">답변</button>
	</c:if>
	<c:if test = "${vo.regNo eq sessionScope.USERNO and vo.stsCd eq 'WAIT'}">
		<button type="button" onclick="editQstn();" class="btn">수정</button>
		<button type="button" onclick="deleteQstn();" class="btn">삭제</button>
	</c:if>
</div>

<script type="text/javascript">
		/** 페이지 초기화 */
		var pageIndex = 1;
		$(document).ready(function(){
			
		});	// end ready..
		
		/** 글 수정 화면으로 이동 */
		function editQstn(){
			var qnaSn = '${vo.qnaSn}';
			var url = generateUrl("/home/brd/qna/editQstnMain",{"qnaSn" : qnaSn});
			document.location.href = url;
		}
		
		/** 글 목록 화면으로 이동 */
		function listQstn(){
			var url = generateUrl("/home/brd/qna/main");
			document.location.href = url;
		}

		/** 글 삭제 */
		function deleteQstn(){
			var qnaSn = '${vo.qnaSn}';
			if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")){
				$.ajax({
					url : '/home/brd/qna/deleteQstn',
					data : {
						"qnaSn" : qnaSn
					}
					,method : "post"
					,success : function(data) {
						if(data.result > 0) {
							alert("정상 처리 되었습니다.");
							listQstn();
						} else {
							alert(data.message);
							document.location.reload();
						}
					}
					,error : function(request, status, error) {
						alert("삭제 중 오류가 발생했습니다. " + error);
						document.location.reload();
					}
				});
			}
		}
		
		function writeAnsr() {
			var qnaSn = '${vo.qnaSn}';
			var url = "/home/brd/qna/writeAnsrMain?qnaSn=" + qnaSn;
			document.location.href = url;
		}
	</script>
