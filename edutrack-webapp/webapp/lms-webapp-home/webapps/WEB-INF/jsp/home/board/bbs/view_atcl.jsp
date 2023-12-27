<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<% // 서브 메뉴 체크 %>
<c:set var="orgMenuVO" value="${orgMenuVO}" scope="request" />
<c:set var="chkSubMenu" value="N" />
<c:set var="bbsInfoVO" value="${bbsInfoVO}" />
<head>
	<script src="/tpl/002/js/jquery/jquery.star-rating-svg.js"></script>
 	<script src="/tpl/002/js/jquery/jquery-rating.js"></script>
</head>
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

<form id="bbsAtclForm" name="bbsAtclForm">
	<!-- 검색조건 유지 -->
	<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex }">
	<input type="hidden" id="searchKey" name="searchKey" value="${vo.searchKey }"> 
	<input type="hidden" id="searchValue" name="searchValue" value="${vo.searchValue }">
	<!-- // 검색조건 유지 -->
	<input type="hidden" id="bbsCd" name="bbsCd" value="${vo.bbsCd }">
	<input type="hidden" id="atclSn" name="atclSn" value="${vo.atclSn }">
	<input type="hidden" id="crsCreCd" name="crsCreCd" value="${vo.crsCreCd}">
</form>

                   <div class="tstyle_view">
                        <div class="title">${vo.atclTitle}</div>
                        <ul class="head">
                            <li class="write"><strong>작성자</strong><span>${vo.regNm}</span></li>
                            <li class="date"><strong>작성일시</strong><span><meditag:dateformat type="0" delimeter="." property="${vo.regDttm}" /></span></li>
                            <li class="hit"><strong>조회수</strong><span>${vo.hits}</span></li>
						<c:if test="${vo.bbsCd eq 'REVIEW'}">
							<li class="hit">
								<strong>만족도</strong>
								<span class="jr-stars review mb5">
								<c:forEach begin="1" end="${vo.starScore }" step="1">
								 	<div class="jr-ratenode jr-rating"></div>
								</c:forEach>
								</span>
							</li>
						</c:if>
                        </ul>
                        <div class="tb_contents">
							<c:if test="${vo.editorYn eq 'Y' }">
								${vo.atclCts}
							</c:if>
							<c:if test="${vo.editorYn ne 'Y' }">
								${fn:replace(vo.atclCts,crlf,"<br/>")}
							</c:if>
                         
                        </div>
						<c:if test="${vo.bbsCd ne 'REVIEW'}">
							<div class="add_file_list">
								<strong class="title">첨부파일</strong>
								<ul class="add_file">
									<c:if test="${not empty vo.attachFiles}">
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
									</c:if>
												
								</ul>
							</div>
						</c:if>
                   
				<c:if test="${bbsInfoVO.cmntUseYn eq 'Y'}">
					<div class="CommentWrap">
						<form id="bbsCmntForm" name="bbsCmntForm" class="cmtForm">
						<fieldset>
							<input type="hidden" id="atclSn" name="atclSn" value="${vo.atclSn }">
							<input type="hidden" id="emoticonNo" name="emoticonNo">
							<c:if test="${not empty sessionScope.USERNO }">
							<h4>댓글</h4>
								<div class="flex wrap gap0">
									<textarea name="cmntCts" id="cmntCts" class="form-control col-sm-11" placeholder="댓글을 입력하세요."></textarea>
									<button type="button" class="col-sm-1" onclick="addCmnt();"><spring:message code="button.add"/></button>
								</div>
							</c:if>
						</fieldset>
						</form>
						<div class="commentList" id="commentList"></div>
						
						<div style="width:100%; margin:10px 10px 10px 10px; display:none; text-align:center" id="cmntPage"></div>
					</div>
				</c:if> 
				</div>

                    <div class="btn_area">
                        <button type="button" onclick="listAtcl();" class="btn gray2">목록</button>
					<c:if test = "${vo.regNo eq sessionScope.USERNO }">
						<button type="button" onclick="editFormAtcl();" class="btn">수정</button>
						<button type="button" onclick="deleteAtcl();" class="btn">삭제</button>
					</c:if>
                    </div>


                    <ul class="list_board">
						<li>
							<span>이전글</span>
							<c:if test="${empty vo.preAtclSn}">
								이전글이 없습니다.
							</c:if>
							<c:if test="${not empty vo.preAtclSn}">
								<a href="javascript:readAtcl('${vo.preAtclSn }')">
									${vo.preAtclTitle }
								</a>
							</c:if>
						</li>
						<li>
							<span>다음글</span>
							<c:if test="${empty vo.nextAtclSn}">
								다음글이 없습니다.
							</c:if>
							<c:if test="${not empty vo.nextAtclSn}">
								<a href="javascript:readAtcl('${vo.nextAtclSn }')">
									${vo.nextAtclTitle }
								</a>
							</c:if>
						</li>
                    </ul>   



<script type="text/javascript">
		/** 페이지 초기화 */
		var pageIndex = 1;
		$(document).ready(function(){
			listCmnt(1); //-- 코멘트 목록을 호출한다.
		});	// end ready..
		
		/** 답변 등록 화면으로 이동 */
		function addFormAtcl(){
			var url = "/home/brd/bbs/addFormAtcl&searchKey=${vo.searchKey}&searchValue=${vo.searchValue}&bbsCd=${vo.bbsCd}&pageIndex=${vo.pageIndex}&parAtclSn=${vo.atclSn}";
			document.location.href = url;	
		}
		
		/** 글 수정 화면으로 이동 */
		function editFormAtcl(){
			$("#bbsAtclForm").attr("action","/home/brd/bbs/editFormAtclMain");
			document.bbsAtclForm.submit();
		}
		
		/** 글 목록 화면으로 이동 */
		function listAtcl(){
			$("#atclSn").val("");
			$("#bbsAtclForm").attr("action","/home/brd/bbs/listAtclMain");
			document.bbsAtclForm.submit();
		}
		
		/** 글 상세 화면으로 이동 */
		function readAtcl(atclSn){
			$("#atclSn").val(atclSn);
			$("#bbsAtclForm").attr("action","/home/brd/bbs/viewAtclMain");
			document.bbsAtclForm.submit();
		}
		
		/** 글 삭제 */
		function deleteAtcl(){
			if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")){
				$("#bbsAtclForm").attr("action","/home/brd/bbs/removeAtcl");
				document.bbsAtclForm.submit();
			}
		}
		
		/* 댓글 목록 */
		function listCmnt(page) {
			<c:if test="${bbsInfoVO.cmntUseYn eq 'Y'}">
				$("#commentList").load(cUrl("/home/brd/bbs/listCmnt"),{
					"curPage" : page,
					"listScale" : 8,
					"atclSn" : '${vo.atclSn}'
				});
			</c:if>
		}

		/* 댓글 등록, 삭제 */
		<c:if test="${bbsInfoVO.cmntUseYn eq 'Y'}">
		function addCmnt() {
			if($.trim($("#bbsCmntForm #cmntCts").val()) == ""){
				alert("<spring:message code="home.board.insertcnts"/>");return;
			}
			$('#bbsCmntForm').attr("action","/home/brd/bbs/addCmnt");
			
			$('#bbsCmntForm').ajaxSubmit(
				function(result) {
					if(result.result >= 0) {
						$("#cmntCts").val('');
						listCmnt(1);
					} else {
						alert(result.message);	// 실패 안내 메시지 표시
					}
				});
		}

		function delCmnt(cmntSn) {
			if(!confirm("<spring:message code="home.board.deletereply"/>")) return false;
			
			$.getJSON( cUrl( "/home/brd/bbs/removeCmnt"),
					{ "cmntSn" : cmntSn },
					   function(data) {
							if(data.result >= 0) {
								listCmnt(1);
							} else {
								alert(data.message);	// 실패 안내 메시지 표시
							}
						}
					);
			
		}
		</c:if>
	</script>
