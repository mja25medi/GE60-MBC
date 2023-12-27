<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
	 <div class="modal_cont">
        <div class="table_list">
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.name"/></label></li>
                <li>${vo.asmtTitle}</li>
            </ul>
            <ul class="list">
                <li class="head"><label>첨부파일</label></li>
                <li>
                    <ul class="add_file">
                       <c:forEach var="fileItem" items="${vo.attachFiles}" varStatus="status">
	                        <li>                    
	                            <a href="javascript:fileDown('${fileItem.fileSn}');" class="file_down" title="${fileItem.fileNm}"> 
	                                <!-- <img src="../../_img/board/file_doc.png" alt=""> -->
	                                <span class="text">${fileItem.fileNm}</span>
	                                <span class="fileSize">(${fileItem.fileSizeStr})</span>
	                            </a>                    
	                            <span class="link">
	                                <a class="btn-line btn-down" href="javascript:fileDown('${fileItem.fileSn}');">다운로드<i></i></a>
	                            </span>
	                        </li>
                       	</c:forEach>
                    </ul>
                </li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.assignment.desc"/></label></li>
                <li>${vo.asmtCts}</li>
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn" onclick="javascript:parent.modalBoxClose()">닫기</button>
    </div>

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
	});
</script>
