<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
                <div class="learn_top">
                    <div class="left_title">
                        <h3>이의제기 내용</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="tstyle_view">
                        <div class="title">${objtVO.title }</div>
                        <ul class="head">
                            <li class="write"><strong>작성자</strong><span>${objtVO.regNm}</span></li>
                            <li class="date"><strong>작성일시</strong><span><meditag:dateformat type="0" delimeter="." property="${objtVO.regDttm}"/></span></li>
                            <li class="hit"><strong>조회수</strong><span>${objtVO.hits}</span></li>
                        </ul>
                        <div class="tb_contents">
                            ${fn:replace(objtVO.cts,crlf,"<br/>")}
                        </div>
                        <c:if test="${not empty objtVO.attachFiles}">
                        	<div class="add_file_list">
                           		<strong class="title">첨부파일</strong>
                            	<ul class="add_file">
	                            	<c:forEach var="fileItem" items="${objtVO.attachFiles}" varStatus="status">
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
						<form id="objtCmntForm" name="objtCmntForm">
							<input type="hidden" id="objtSn" name="objtSn" value="${objtVO.objtSn }"/>
                        	<div class="write_box">
                        		<textarea name="cmntCts" id="cmntCts" Class="form-control" rows="5"></textarea>
                            	<button type="button" class="btn type2"  onclick="javascript:addCmnt()"><spring:message code="button.add"/></button>
                        	</div>
                        </form>
						<c:if test="${not empty objtVO.cmntList }">
	                        <div class="reply_list">
	                            <ul>
		                            <c:forEach items="${objtVO.cmntList }" var="item" varStatus="status">
		                                <li>
		                                    <div class="item">
		                                        <div class="info"><strong>${item.regNm }</strong><meditag:dateformat type="0" delimeter="." property="${item.regDttm}" /> </div>
		                                        <div class="cont">${item.cmntCts }</div>
		                                    </div>
		                                    <c:if test="${item.regNo eq USERNO}">
		                                    	<button type="button" class="btn-line" onclick="javascript:delCmnt(${item.objtCmntSn});"><spring:message code="button.delete"/></button>
											</c:if>
		                                </li>
	                                </c:forEach>
	                            </ul>
	                        </div>
                        </c:if>
                    </div>
                    <div class="btn_area">
                        <button type="button" class="btn gray2" onclick="toList()">목록</button>	 
                    </div>
                </div>
	

<script type="text/javascript">
	$(document).ready(function() {
		$(".sub_title_2.ohddien").text("이의제기 내용");
	});
	function toList() {
		location.href = cUrl("/lec/objt/objtMain");
	}
	
	function addCmnt() {
		if($("#cmntCts").val() == '') {
			alert("댓글의 내용을 입려해주세요.");
			return;
		}
		$("#objtCmntForm").attr("action", "/lec/objt/writeCmnt");
		$("#objtCmntForm").ajaxSubmit(
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
	
	function delCmnt(objtCmntSn) {
		$.post(cUrl("/lec/objt/deleteCmnt"),{
			"objtCmntSn" : objtCmntSn
		}, function(data) {
			if(data.result == 1) {
				alert(data.message);
				location.reload();
			} else
				alert(data.message);
		})
	}

</script>
