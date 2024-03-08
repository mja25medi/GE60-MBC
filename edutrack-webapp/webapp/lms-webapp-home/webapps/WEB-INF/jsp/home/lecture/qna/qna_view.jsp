<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<%pageContext.setAttribute("crlf", "\n");%>
                <div class="learn_top">
                    <div class="left_title">
                        <h3>학습문의</h3>
                    </div>
                    <div class="right_btn" id="btnArea">
                    	<c:if test="${USERNO eq vo.regNo and vo.stsCd eq 'WAIT'}">
                    		<button type="button" class="btn type4" onclick="javascript:editQstnForm(${vo.qnaSn })"><spring:message code="button.edit"/></button>
							</c:if>
							
							<c:if test="${CLASSUSERTYPE eq 'TCH'}">
								<c:if test="${empty ansrVO}">
									<button type="button" class="btn type4" onclick="javascript:writeAnsrForm(${vo.qnaSn })">답변</button>
								</c:if>
								<c:if test="${not empty ansrVO}">
									<button type="button" class="btn type4" onclick="javascript:editAnsrForm(${vo.qnaSn })">답변 수정</button>
								</c:if>
							</c:if>			
                    </div>
                </div>
                <div class="segment">
                    <div class="tstyle_view">
                        <div class="title">${vo.qnaTitle}</div>
                        <ul class="head">
                            <li><strong>작성자</strong><span>${vo.regNm}</span></li>
                            <li><strong>회차</strong><span>
                            <c:if test="${vo.parUnitCd eq vo.unitCd}">${vo.parUnitNm } - </c:if>
                            ${vo.unitNm }</span></li>
                            <li><strong>작성일시</strong><span><meditag:dateformat type="1" delimeter="." property="${vo.regDttm}"/> <meditag:dateformat type="7" delimeter="." property="${vo.regDttm}"/></span></li>
                        </ul>
                        <div class="tb_contents" style="word-break : break-all">
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
                    </div>
                
					<!-- 답변  -->
					<div id="ansrFormLoadDiv">
						<c:if test="${not empty ansrVO }">
		                   	<div class="answer">
		                   		<div class="title_area">
		                        	<strong class="title">[답변] ${vo.qnaTitle}</strong>
		                            <span class="date">
	                             		<b>${ansrVO.chrgPrsnNm}</b>
		                             	<em><meditag:dateformat type="1" delimeter="." property="${ansrVO.regDttm}"/> <meditag:dateformat type="7" delimeter="." property="${ansrVO.regDttm}"/></em>
		                             </span>
		                        </div>
		                        <div class="cont">
									${ansrVO.qnaCts}<br>
				                    <c:if test="${not empty ansrVO.attachFiles}">
					                	<div class="add_file_list">
			                            	<strong class="title">첨부파일</strong>
			                           		<ul class="add_file">
			                            		<c:forEach var="fileItem" items="${ansrVO.attachFiles}" varStatus="status">
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
		                        </div>
		                   	</div>
	                	</c:if>
			        </div>
               		<br>
	               	<div class="btn_area">
	                	<button type="button" class="btn gray2" onclick="listQstn()" ><spring:message code="button.list"/></button>
	                </div>
                </div>
                
                <form id="lecQnaForm" name="lecQnaForm" onsubmit="return false">
					<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
					<input type="hidden" name="unitCd" value="${vo.unitCd}" />
					<input type="hidden" name="qnaSn" value="${vo.qnaSn}"/>
				</form>
                
				

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {

	});
	
	function editQstnForm(qnaSn) {
		document.location.href = "/lec/qna/editQstnMain?qnaSn=" + qnaSn;
	}
	
	function writeAnsrForm(qnaSn) {
		$("#ansrFormLoadDiv").load("/lec/qna/writeAnsrForm?qnaSn=" + qnaSn);
		$("#btnArea").hide();
	}
	
	function editAnsrForm(qnaSn) {
		$("#ansrFormLoadDiv").load("/lec/qna/editAnsrForm?qnaSn=" + qnaSn);
		$("#btnArea").hide();
	}


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
	
	function listQstn() {
		document.location.href = "/lec/qna/main";
	}

</script>
