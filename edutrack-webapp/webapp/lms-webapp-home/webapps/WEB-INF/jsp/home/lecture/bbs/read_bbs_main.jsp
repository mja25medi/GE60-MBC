<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="lecBbsAtclVO" value="${vo}"/>
<%pageContext.setAttribute("crlf", "\n");%>
                <div class="learn_top">
                    <div class="left_title">
                        <h3>과목 공지사항</h3>
                    </div>
                    <div class="right_btn">
                        <c:choose>
							<c:when test="${CLASSUSERTYPE eq 'TCH' && USERNO ne lecBbsAtclVO.regNo}">
							<%-- <a href="#" onclick="editForm()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a> --%>
							</c:when>
							<c:otherwise>
								<c:if test="${USERNO eq lecBbsAtclVO.regNo}">
								<button type="button" class="btn type3" onclick="editForm()"><spring:message code="button.edit"/></button>
								<button type="button" class="btn type9" onclick="deleteActl()"><spring:message code="button.delete"/></button>
								</c:if>
							</c:otherwise>
						</c:choose>
                        
                    </div>
                </div>
                <div class="segment">
                    <div class="tstyle_view">
                        <div class="title">${lecBbsAtclVO.title}</div>
                        <ul class="head">
                            <li class="write"><strong>작성자</strong><span>${lecBbsAtclVO.regNm}</span></li>
                            <li class="date"><strong>작성일시</strong><span><meditag:dateformat type="1" delimeter="." property="${lecBbsAtclVO.regDttm}"/> 
									<meditag:dateformat type="7" delimeter="." property="${lecBbsAtclVO.regDttm}"/> </span></li>
                            <li class="hit"><strong>조회수</strong><span>${lecBbsAtclVO.hits}</span></li>
                        </ul>
                        <div class="tb_contents">
                      		<c:if test="${lecBbsAtclVO.editorYn eq 'Y' }">
								${lecBbsAtclVO.cts}
							</c:if>
							<c:if test="${lecBbsAtclVO.editorYn ne 'Y' }">
								${fn:replace(lecBbsAtclVO.cts,crlf,"<br/>")}
							</c:if>		
                        </div>
                         <c:if test="${not empty lecBbsAtclVO.attachFiles}">
                        <div class="add_file_list">
                            <strong class="title">첨부파일</strong>
                            <ul class="add_file">
                            <c:forEach var="fileItem" items="${lecBbsAtclVO.attachFiles}" varStatus="status">
                                <li>                    
                                    <a href="#_none" onclick="javascript:fileDown('${fileItem.fileSn}');" class="file_down">
                                        <!-- <img src="../../_img/board/file_doc.png" alt=""> -->
                                        <span class="text">${fileItem.fileNm}</span>
                                        <span class="fileSize">(${fileItem.fileSizeStr})</span>
                                    </a>                    
                                    <span class="link">
                                        <a class="btn-line btn-down" href="#_none" onclick="javascript:fileDown('${fileItem.fileSn}');" title="${fileItem.fileNm}">다운로드<i></i></a>
                                    </span>
                                </li>
                                </c:forEach>
                            </ul>
                        </div>
                        </c:if>
                        
                        
                        <form id="lecBbsForm" name="lecBbsForm" onsubmit="return false">
						<input type="hidden" name="crsCreCd" value="${lecBbsAtclVO.crsCreCd}" />
						<input type="hidden" name="bbsCd" value="${lecBbsAtclVO.bbsCd}" />
						<input type="hidden" name="atclSn" value="${lecBbsAtclVO.atclSn}"/>
						<input type="hidden" name="searchKey" value="${lecBbsAtclVO.searchKey}" />
						<input type="hidden" name="searchValue" value="${lecBbsAtclVO.searchValue}"/>
						<input type="hidden" name="curPage" id="curPage" value="${lecBbsAtclVO.curPage}"/>
						</form>
						
						<c:if test="${lecBbsVO.cmntUseYn eq 'Y' }">
							<form id="bbsCmntForm" name="bbsCmntForm">
								<input type="hidden" id="atclSn" name="atclSn" value="${lecBbsAtclVO.atclSn }"/>
								<input type="hidden" id="bbsCd" name="bbsCd" value="${lecBbsAtclVO.bbsCd }">
								<input type="hidden" id="emoticonNo" name="emoticonNo">								
								<div class="input-group">
									<textarea name="cmntCts" id="cmntCts" styleClass="form-control" style="height:74px; width:100%;"></textarea>
									<span class="input-group-addon" onclick="javascript:addCmnt()" style="cursor:pointer"><spring:message code="button.add"/></span>
								</div>
							</form>
						</c:if>
						
						<c:if test="${not empty lecBbsAtclVO.cmntList }">
							<div class="well">
								<ul id="cmntList" class="list-group" style="margin-top:10px;">
									<c:forEach items="${lecBbsAtclVO.cmntList }" var="item" varStatus="status">
										<li class="list-group-item"><i class='fa fa-user fa-fw'></i>${item.regNm } - <meditag:dateformat type="0" delimeter="." property="${item.regDttm}" /> 
										<c:if test="${item.regNo eq USERNO}">
											<button style='float:right' class='btn btn-warning btn-xs' type="button" onclick="javascript:delCmnt(${item.cmntSn});"><spring:message code="button.delete"/></button>
										</c:if>
										<p class="wordbreak">${item.cmntCts }</p>
										</li>                                                                                               
									</c:forEach>
								</ul>
							</div>
						</c:if>
                    </div>
                    <div class="btn_area">
                        <button type="button" class="btn gray2" onclick="listActl()"><spring:message code="button.list"/></button>
                    </div>
                </div>
<%-- 						<c:if test="${lecBbsVO.cmntUseYn eq 'Y' && CLASSUSERTYPE eq 'TCH'}">
							<div class="CommentWrap">
								<form id="bbsCmntForm" name="bbsCmntForm" class="cmtForm">
								<fieldset>
									<input type="hidden" id="atclSn" name="atclSn" value="${lecBbsAtclVO.atclSn }">
									<input type="hidden" id="bbsCd" name="bbsCd" value="${lecBbsAtclVO.bbsCd }">
									<input type="hidden" id="emoticonNo" name="emoticonNo">
									<h4>댓글</h4>
									<div class="flex wrap gap0">
										<textarea name="cmntCts" id="cmntCts" class="form-control col-sm-11" placeholder="댓글을 입력하세요."></textarea>
										<button type="button" class="col-sm-1" onclick="addCmnt();"><spring:message code="button.add"/></button>
									</div>
								</fieldset>
								</form>
								<div class="commentList" id="commentList"></div>
								
								<div style="width:100%; margin:10px 10px 10px 10px; display:none; text-align:center" id="cmntPage"></div>
							</div>
						</c:if> --%>

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {

	});

	
	/* 글 수정 */
	function editForm() {
		$('#lecBbsForm').attr("action","/lec/bbs/editAtclMain");
		
		var formObj = document.getElementById("lecBbsForm");
		formObj.submit();
	}


	/* 글 삭제 */
	function deleteActl() {
		if(confirm('<spring:message code="board.message.bbs.atcl.confirm.delete"/>')) {
			$('#lecBbsForm').attr("action","/lec/bbs/removeAtcl");
			var formObj = document.getElementById("lecBbsForm");
			formObj.submit();
		} else {
			return;
		}
	}

		//-- 게시물 댓글 관련 기능
		function listCmnt(page){
			ItemDTO.curPage = page;
			// 게시물 댓글 목록 표시
			$.getJSON(
				cUrl('/lec/bbs/listCmnt'),
				{ 
				  "curPage"           	:   page,
				  "atclSn"	:	"${lecBbsAtclVO.atclSn}",
				  "bbsCd"	:	"${lecBbsAtclVO.bbsCd}"
				  },
				function(result) {
					var itemList = result.returnList;
					var pageInfo = result.pageInfo;
					if(itemList.length > 0) {
						var listStr = "";
						for (var i=0; i < itemList.length; i++) {
							var item = itemList[i];
							listStr += "<li class='list-group-item'>";
							listStr += "<i class='fa fa-user fa-fw'></i> "+item.regNm+" / "+addDateTimeFormatMinStr(item.regDttm);
							if(item.regNo == '${sessionScope.USERNO}') {
								listStr += " <button style='float:right' class='btn btn-warning btn-xs' type=\"button\" onclick=\"javascript:delCmnt('"+item.cmntSn+"')\"><spring:message code="button.delete"/></button>";
							}
							listStr += "<p class=\"wordbreak\">"+item.cmntCts.replace(/\n/g,"<br/>")+"</p>";
							listStr += "</li>";
						}
						$("#commentList").html(listStr).show();
						$("#cmntPage").pagingHtml(pageInfo, "listCmnt").show();
					} else {
						$("#commentList").html("").hide();
						$("#cmntPage").html("").hide();
					}


				}
			);
		}

		/* 게시물 댓글 저장*/
/*		function addCmnt() {
			$('#bbsCmntForm').attr("action","/lec/bbs/addCmnt");
			$('#bbsCmntForm').ajaxSubmit(
				function(result) {
					if(result.result >= 0) {
						$("#cmntCts").val('');
						listCmnt(1);
					} else {
						alert(result.message);	// 실패 안내 메시지 표시
					}
				}
			);
			return false;
		}	
*/
		
		function addCmnt() {
			if($("#cmntCts").val() == '') {
				alert("댓글의 내용을 입려해주세요.");
				return;
			}
			$("#bbsCmntForm").attr("action", "/lec/bbs/addCmnt");
			$("#bbsCmntForm").ajaxSubmit(
					function(data) {
						if(data.result == 1) {
							alert("등록되었습니다.")
							location.reload();
						} else {
							alert(data.message);
						}
					}
				);
		}
		
		function delCmnt(cmntSn) {
			$.post(cUrl("/lec/bbs/removeCmnt"),{
				"cmntSn" : cmntSn
			}, function(data) {
				if(data.result == 1) {
					alert(data.message);
					location.reload();
				} else
					alert(data.message);
			})
		}

		/* 게시물 댓글  삭제 */
/*		function delCmnt(cmntSn) {
			var ajaxUrl = cUrl('/lec/bbs/removeCmnt');
			if(!confirm('<spring:message code="board.message.bbs.cmnt.confirm.delete"/>')) return false;
			var ajaxData = { "cmntSn" : cmntSn };
			$.getJSON (
				ajaxUrl, ajaxData,
				function(response) {
					if(response.result >= 0) {
						listCmnt(ItemDTO.curPage);
					} else {
						alert(response.message);	// 실패 안내 메시지 표시
					}
			});
			return false;
		} */
		
		/* 글 목록으로 이동 */
		function listActl() {
			var url = generateUrl("/lec/bbs/main?searchKey=${lecBbsAtclVO.searchKey}&searchValue=${lecBbsAtclVO.searchValue}&bbsCd=${lecBbsAtclVO.bbsCd}&curPage=${lecBbsAtclVO.curPage}");
			document.location.href = url;
		}
</script>
