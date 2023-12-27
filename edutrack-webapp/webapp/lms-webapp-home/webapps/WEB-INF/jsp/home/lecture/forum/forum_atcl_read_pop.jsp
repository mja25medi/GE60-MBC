<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="forumAtclVO" value="${vo}"/>
<c:set var="forumCommentList" value="${forumCmntList}" />
<c:set var="pageInfo" value="${pageInfo}"/>
<% request.setAttribute("vEnter", "\n"); %>

	<form id="forumForm" name="forumForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="forumSn" value="${vo.forumSn}" />
	<input type="hidden" name="atclSn" value="${vo.atclSn}"/>
	<input type="hidden" name="parAtclSn" value="${forumAtclVO.atclSn}"/>
	<input type="hidden" name="gubun" value="${vo.gubun}" />
	<input type="hidden" name="curPage" value="${vo.curPage}" />

	<table  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:15%" />
			<col style="width:16%" />
			<col style="width:15%" />
			<col style="width:22%" />
			<col style="width:15%" />
			<col style="width:17%" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" style="background-color: #f5f5f5; font-weight: bold;"><spring:message code="common.title.title"/></th>
				<td colspan="5" class="wordbreak">
					${forumAtclVO.title}
				</td>
			</tr>
			<tr>
				<th scope="row" style="background-color: #f5f5f5; font-weight: bold;"><spring:message code="common.title.reguser"/></th>
				<td>
					${forumAtclVO.regNm}
				</td>
				<th scope="row" style="background-color: #f5f5f5; font-weight: bold;"><spring:message code="common.title.regtime"/></th>
				<td>
					<meditag:dateformat property="${forumAtclVO.regDttm}" type="0" delimeter="." />
				</td>
				<th scope="row" style="background-color: #f5f5f5; font-weight: bold;"><spring:message code="common.title.hits"/></th>
				<td>
					${forumAtclVO.hits}
				</td>
			</tr>
			<c:if test="${not empty forumAtclVO.attachFiles}">
			<tr>
				<th scope="row" style="background-color: #f5f5f5; font-weight: bold;"><spring:message code="common.title.atachfile"/></th>
				<td colspan="5">
					<c:forEach var="fileItem" items="${forumAtclVO.attachFiles}" varStatus="status"><div style="margin-left: 5px;">${fileItem.downloadTag}</div></c:forEach>
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="wordbreak" colspan="6" style="min-height:220px;vertical-align:text-top;">
					${fn:replace(forumAtclVO.cts,vEnter,"<br>")}
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-right" style="margin-bottom:5px;">
		<a href="javascript:replyForumAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.reply"/></a>
		<%-- <c:if test="${CLASSUSERTYPE eq 'TCH' || USERNO eq forumAtclVO.regNo}"> --%>
		<%-- <c:if test="${fn:indexOf(MNGTYPE, 'MANAGER') != -1  || USERNO eq forumAtclVO.regNo}"> --%>
		<c:if test="${USERNO eq forumAtclVO.regNo}">
		<a href="javascript:editFormAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
		<a href="javascript:delForumAtcl()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.document.location.reload()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>
	<form method="post" id="commentForm" name="commentForm" onsubmit="return false" >
	<input type="hidden" name="atclSn" value="${forumAtclVO.atclSn}"/>
	<input type="hidden" name="forumSn" value="${forumAtclVO.forumSn}"/>
	<input type="hidden" name="crsCreCd" value="${forumAtclVO.crsCreCd}"/>
	<input type="hidden" name="cmntSn"/>
	<input type="hidden" name="curPage" value="${vo.curPage}" />
	<input type="submit" value="submit" style="display:none" />
	<div class="well well-sm">
		<div class="input-group">
			<textarea name="cmntCts" id="cmntCts" class="form-control input-sm" style="height:40px;"></textarea>
			<span class="input-group-addon" onclick="javascript:addCmnt()" style="cursor:pointer">
				<spring:message code="button.add"/>
			</span>
		</div>
		<ul class="list-group">
			<c:forEach var="item" items="${forumCommentList}" varStatus="status">
			<li class="list-group-item">
				<i class="fa fa-user fa-fw"></i> ${item.regNm} / <meditag:dateformat property="${item.regDttm}" type="0" delimeter="." />
				<%-- <c:if test="${CLASSUSERTYPE eq 'TCH' || USERNO eq item.regNo }"> --%>
				<c:if test="${fn:indexOf(MNGTYPE, 'MANAGER') != -1  || USERNO eq item.regNo}">
				<div style="float:right"><a href="javascript:delCmnt('${item.cmntSn}')" class="btn btn-warning btn-xs"><spring:message code="button.delete"/></a></div>
				</c:if>
				<p class="wordbreak">${fn:replace(item.cmntCts,vEnter,"<br>")}</p>
			</li>
			</c:forEach>
		</ul>
		<meditag:paging pageInfo="${pageInfo}" funcName="listCmnt"/>
	</div>
	</form>

<script type="text/javascript">
	var ItemDTO = new Object();
	var atchFiles;	// 첨부파일 변수

	// 페이지 초기화
	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
		ItemDTO.replyCnt = ${vo.replyCnt};
		//부모창 새로고침
		if("${refreshYn}" == "Y"){		//등록일 경우
			/* parent.opener.location.href = parent.opener.location.href; */
			parent.location.reload();
		}else if("${refreshYn}" == "D"){		//삭제일 경우
			parent.location.reload();
			parent.modalBoxClose();
		}
	});


	/**
	* 수정 화면 이동
	*/
	function editFormAtcl(){
		document.location.href = cUrl("/lec/forum/addForumArticlePop")+"?forumSn=${forumAtclVO.forumSn}${AMPERSAND}atclSn=${forumAtclVO.atclSn}${AMPERSAND}gubun=E";
	}

	/**
	* 댓글 목록
	*/
	function listCmnt(page){
		$('#forumForm').attr("action","/lec/forum/readForumArticlePop")
			.find('input[name=curPage]').val(page);
		var formObj = document.getElementById("forumForm");
		formObj.submit();
	}

	/**
	* 답변 등록 폼
	*/
	function replyForumAtcl(){
		$('#forumForm').attr("action","/lec/forum/addForumArticlePop");
		var formObj = document.getElementById("forumForm");
		formObj.submit();
	}

	/**
	 * 게시글 삭제
	 */
	function delForumAtcl() {
		if(ItemDTO.replyCnt > 0){
			alert("<spring:message code="lecture.messgae.forum.atcl.alert.delete"/>");
			return;
		}
		if(confirm('<spring:message code="lecture.message.forum.atcl.confirm.delete"/>')) {
			process("removeForumArticle");
		}
	}

	/**
	* 댓글 저장
	*/
	function addCmnt(){

		if(isEmpty($("#cmntCts").val())) {
			alert("<spring:message code="board.message.bbs.cmnt.alert.shortcnts"/>");
			return;
		}
		$('#commentForm').attr("action","/lec/forum/addComment");

		var formObj = document.getElementById("commentForm");
		formObj.submit();
	}

	/**
	* 댓글 삭제
	*/
	function delCmnt(cmntSn){
		if(confirm("<spring:message code="lecture.message.forum.cmnt.confirm.delete"/>")){
			$('#commentForm').attr("action","/lec/forum/removeComment");
			$('#commentForm').find('input[name=cmntSn]').val(cmntSn);
			var formObj = document.getElementById("commentForm");
			formObj.submit();
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#forumForm').attr("action","/lec/forum/" + cmd);
		$('#forumForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.location.reload();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}	
</script>
