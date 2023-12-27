<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%-- ${vo.title} 은 너무 길기 때문에 별도로 set --%>
<c:set var="forumAtclVO" value="${vo}"/>
<% request.setAttribute("vEnter", "\n"); %>
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
			<c:if test="${not empty forumAtclVO.attachFiles}">
			<tr>
				<th scope="row"><spring:message code="common.title.atachfile"/></th>
				<td colspan="5">
					<c:forEach var="fileItem" items="${forumAtclVO.attachFiles}" varStatus="status"><div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div></c:forEach>
				</td>
			</tr>
			</c:if>
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
			<tr>
				<th scope="row" colspan="6" style="background-color: #f5f5f5; font-weight: bold;"><spring:message code="lecture.title.forum.atcl.cnts"/></th>
			</tr>
			<tr>
				<td class="wordbreak" colspan="6"  valign="top">
					${fn:replace(forumAtclVO.cts,vEnter,"<br>")}
				</td>
			</tr>
		</tbody>
	</table>
<%-- 	<table summary="<spring:message code="lecture.title.forum.atcl.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col width="12%">
			<col width="22%">
			<col width="12%">
			<col width="21%">
			<col width="12%">
			<col width="21%">
		</colgroup>
		<tbody>
			<tr>
				<td colspan="6" class="wordbreak">
					<div style="float:left;"><span style="font-weight:bold;font-size:14px;">${forumAtclVO.title}</span></div>
					<div style="float:right;padding-right:10px;"><meditag:dateformat property="${forumAtclVO.regDttm}" type="1" delimeter="." /> <meditag:dateformat property="${forumAtclVO.regDttm}" type="7" delimeter="." /></div>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div style="float:left">${forumAtclVO.regNm} (${forumAtclVO.regNo})</div>
					<div style="float:right;padding-right:10px;"><spring:message code="common.title.hits"/>: ${forumAtclVO.hits}</div>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div style="float:left;width:100%;min-height:160px;" class="tx-content-container wordbreak">${fn:replace(forumAtclVO.cts,vEnter,"<br>")}</div>
					<c:if test="${not empty forumAtclVO.attachFiles}">
					<div style="float:left;width:100%;margin-top:10px;">
						<div style="float:left"><spring:message code="common.title.atachfile"/> : </div>
						<div style="float:left">
						<c:forEach var="fileItem" items="${forumAtclVO.attachFiles}" varStatus="status"><div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div></c:forEach>
						</div>
					</div>
					</c:if>
				</td>
			</tr>
		</tbody>
	</table> --%>
	<div class="text-right">
		<a href="javascript:goEditArticle()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
		<a href="javascript:goReplyArticle()" class="btn btn-primary btn-sm"><spring:message code="button.reply"/> </a>
		<a href="javascript:deleteArticle()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<div class="well well-sm" style="margin-top:5px;">
		<form id="commentForm" name="commentForm" onsubmit="return false" >
		<input type="hidden" name="atclSn" value="${forumAtclVO.atclSn}"/>
		<input type="hidden" name="forumSn" value="${forumAtclVO.forumSn}"/>
		<input type="hidden" name="crsCreCd" value="${forumAtclVO.crsCreCd}"/>
		<div class="input-group">
			<textarea name ="cmntCts" style="height:60px ;margin: 0 auto;" id="cmntCts" class="form-control"></textarea>
			<span class="input-group-addon" onclick="addCmnt()" style="cursor:pointer">
				<spring:message code="button.add"/>
			</span>
		</div>
		<input type="submit" value="submit" style="display:none" />
		</form>
		<div class="list-group" id="cmntListArea"></div>
		<div id="cmntPageArea" class="text-center"></div>
	</div>


	<form id="forumForm" name="forumForm" onsubmit="return false">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
		<input type="hidden" name="forumSn" value="${vo.forumSn }" />
		<input type="hidden" name="atclSn" value="${vo.atclSn }"/>
		<input type="hidden" name="parAtclSn" value="${forumAtclVO.atclSn}"/>
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();

	/** 페이지 초기화 */
	$(document).ready(function(){
		ItemDTO.replyCnt = ${forumAtclVO.replyCnt};
		//댓글 목록 조회
		listCmnt(1);
	});

	/**
	* 댓글 저장
	*/
	function addCmnt() {
		$('#commentForm').attr("action","/mng/lecture/forum/addComment");
		$('#commentForm').ajaxSubmit(
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

	/**
	* 댓글 목록 조회
	*/
	function listCmnt(page) {
		ItemDTO.curPage = page;
		// 댓글 목록 표시
		$.getJSON(
			cUrl("/mng/lecture/forum/listComment"),
			{ 
			  "curPage"           	:   page,
			  "atclSn"	:	"${forumAtclVO.atclSn}" },
			function(result) {
				var itemList = result.returnList;
				var pageInfo = result.pageInfo;
				if(itemList.length > 0) {
					var listStr = "<table style='width:97%'>";
					for (var i=0; i < itemList.length; i++) {
						var item = itemList[i];
						var delLink = "";
						delLink = "<a href=\"#_none\" onclick=\"delCmnt('"+item.crsCreCd+"','"+item.forumSn+"','"+item.atclSn+"','"+item.cmntSn+"')\" class='btn btn-warning btn-xs'><spring:message code="button.delete"/></a>";
						listStr += "<li class='list-group-item'>";
						listStr += "  <i class='fa fa-user fa-fw'></i> "+item.regNm+" / "+addDateTimeFormatStr(item.regDttm);
						listStr += "  <div style='float:right'>"+delLink+"</div>";
						listStr += "  <p class=\"wordbreak\">"+item.cmntCts.replace(/\n/g,"<br/>")+"</p>";
						listStr += "</li>";
					}
					listStr += "</table>";
					$("#cmntListArea").html(listStr).show();
					$("#cmntPageArea").pagingHtml(pageInfo, "listCmnt").show();
				} else {
					$("#cmntListArea").html("").hide();
					$("#cmntPageArea").html("").hide();
				}
			}
		);
	}

	/**
	* 댓글 삭제
	*/
	function delCmnt(crsCreCd, forumSn, atclSn, cmntSn) {
		if(!confirm('<spring:message code="lecture.message.forum.cmnt.confirm.delete"/>')) return false;
		var ajaxData = { 
						 "crsCreCd" : crsCreCd,
						 "forumSn" : forumSn,
						 "atclSn" : atclSn,
						 "cmntSn" : cmntSn };
		$.getJSON (
			cUrl("/mng/lecture/forum/removeComment"), ajaxData,
			function(response) {
				if(response.result >= 0) {
					listCmnt(ItemDTO.curPage);
				} else {
					alert(response.message);	// 실패 안내 메시지 표시
				}
		});
		return false;
	}

	/**
	* 수정화면으로 이동
	*/
	function goEditArticle(){
		forumForm = document.getElementById("forumForm");
		$('#forumForm').attr("action","/mng/lecture/forum/editAtclPop");
		forumForm.submit();
	}

	/**
	* 답글 등록 화면으로 이동
	*/
	function goReplyArticle(){
		$('#forumForm').attr("action","/mng/lecture/forum/addAtclPop");
		forumForm = document.getElementById("forumForm");
		forumForm.submit();

	}

	/**
	* 게시글 삭제
	*/
	function deleteArticle(){
		if(ItemDTO.replyCnt > 0){
			alert("<spring:message code="lecture.messgae.forum.atcl.alert.delete"/>");
			return;
		}
		if(confirm('<spring:message code="lecture.message.forum.atcl.confirm.delete"/>')) {
			process("removeAtcl");
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		//if(!validate(document.forumForm)) return;

		$('#forumForm').attr("action","/mng/lecture/forum/" + cmd);
		$('#forumForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listAtcl(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	* 창 닫기
	*/
	function closePop(){
		var curPage = parent.subWorkFrame.document.atclSearch.curPage.value;
		parent.subWorkFrame.listAtcl(curPage);
		parent.forumPopBox.close();
	}
</script>


