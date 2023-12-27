<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjBbsAtclVO" value="${vo}"/>
<c:set var="prjBbsVO" value="${prjBbsVO}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/message_box.js"/>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/common_paging.js"/>
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:class_head>
<mhtml:body>
		<div style="float:left; width:100%; height:30px;">
			<div style="float:left"><p style="font-weight:bold;margin-top:10px; margin-left:10px;">${prjBbsVO.bbsNm}</p></div>
		</div>
		<table class="board_b">
			<col width="12%"/>
			<col width="22%"/>
			<col width="12%"/>
			<col width="21%"/>
			<col width="12%"/>
			<col width="21%"/>
			<tbody>
				<tr>
					<td colspan="6" class="top" style="background-color:#f3f3f3">
						<div style="float:left;"><span style="font-weight:bold;font-size:14px;">${prjBbsAtclVO.atclTitle}</span></div>
						<div style="float:right;padding-right:10px;"><meditag:dateformat property="${prjBbsAtclVO.regDttm}" type="1" delimeter="." /> <meditag:dateformat property="${prjBbsAtclVO.regDttm}" type="7" delimeter="." /></div>
					</td>
				</tr>
				<tr>
					<td colspan="6" style="background-color:#f3f3f3">
						<div style="float:left">${prjBbsAtclVO.regNm} (${prjBbsAtclVO.regId})</div>
						<div style="float:right;padding-right:10px;">조회수: ${prjBbsAtclVO.hits}</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
					<div style="float:left;width:100%;min-height:160px;" class="tx-content-container">${prjBbsAtclVO.atclCts}</div>
					</td>
				</tr>
				<tr>
					<td colspan="6" valign="top" style="padding:10px 5px 10px 5px">
						<c:if test="${not empty prjBbsAtclVO.attachFiles}">
						<div style="float:left;width:100%;margin-top:10px;">
							<div style="float:left">첨부파일 : </div>
							<div style="float:left">
							<c:forEach var="fileItem" items="${prjBbsAtclVO.attachFiles}" varStatus="status"><div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div></c:forEach>
							</div>
						</div>
						</c:if>
						<c:if test="${prjBbsVO.cmntUseYn eq 'Y'}">
						<div id="comment_box" style="float:left;width:100%;background-color:#f3f3f3;">
							<div style="float:left;width:100%;margin:10px 10px 10px 10px;display:none" id="cmntListArea"></div>
							<div style="float:left;width:100%;margin:10px 10px 10px 10px;">
								<form id="prjBbsAtclCmntForm" name="prjBbsAtclCmntForm" onsubmit="return false" >
								<input type="hidden" name="crsCreCd" value="${prjBbsAtclVO.crsCreCd}"/>
								<input type="hidden" name="prjtSn" value="${prjBbsAtclVO.prjtSn}"/>
								<input type="hidden" name="bbsCd" value="${prjBbsAtclVO.bbsCd}"/>
								<input type="hidden" name="atclSn" value="${prjBbsAtclVO.atclSn}"/>
								<input type="hidden" name="cmntSn"/>
								<input type="hidden" name="emoticonNo"/>
								<div style="float:left;width:50px;">아이콘</div>
								<div style="float:left;width:550px; height: 80px;">
									<textarea name="cmntCts" style="width:100%; height:60px ;margin: 0 auto;" id="cmntCts" class="txt"></textarea>
								</div>
								<div style="float:left;padding-left:15px;">
									<a href="javascript:addCmnt()" class="btn02">확인</a>
								</div>
								<input type="submit" value="submit" style="display:none" />
								</form>
							</div>
							<div style="float:left;width:100%;margin:10px 10px 10px 10px;display:none;text-align:center" id="cmntPageArea"></div>
						</div>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_right">
			<a href="javascript:goList()" class="btn02">목록</a>
			<c:if test="${prjBbsVO.ansrUseYn eq 'Y'}">
				<!-- <a href="javascript:replyAtcl()" class="btn02">답글</a> -->
			</c:if>
			<a href="javascript:editAtcl()" class="btn02">수정</a>
			<a href="javascript:delAtcl()" class="btn02">삭제</a>
		</div>
	<form id="prjBbsAtclForm" name="prjBbsAtclForm" onsubmit="return false" >
	<input type="hidden" name="curPage" value="${vo.curPage}" />
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
	<input type="hidden" name="bbsCd" value="${vo.bbsCd}" />
	<input type="hidden" name="atclSn" value="${vo.atclSn}"/>
	<input type="hidden" name="parAtclSn" value="${prjBbsAtclVO.atclSn}"/>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">
	var ItemDTO = new Object();
	/** 페이지 초기화 */
	$(document).ready(function(){
		displayWorkProgress();
		<c:if test="${prjBbsVO.cmntUseYn eq 'Y'}">
		listCmnt(1); //-- 코멘트 목록을 호출한다.
		</c:if>
		closeWorkProgress();
	});

	//목록 이동
	function goList(){
		$('#prjBbsAtclForm')
		.attr("action","/lec/prj/bbsAtcl/listPrjBbsAtcl")
		.submit();

	}

	/* function replyAtcl(){
		$('#prjBbsAtclForm')
		.find('input[name=cmd]').val("addFormPrjBbsAtcl").end()
		.find('input[name="bbsAtclDTO.atclSn"]').val('').end()
		.submit();
	} */

	//게시글 수정
	function editAtcl(){
		$('#prjBbsAtclForm')
		.attr("action","/lec/prj/bbsAtcl/editFormPrjBbsAtclStu")
		.submit();
	}

	//게시글 삭제
	function delAtcl(){
		if(confirm('게시글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#prjBbsAtclForm')
			.attr("action","/lec/prj/bbsAtcl/removePrjBbsAtclStu")
			.submit();
		}else{
			return;
		}
	}

	function listCmnt(page) {
		displayWorkProgress();
		ItemDTO.curPage = page;
		// 댓글 목록 표시
		$.getJSON(
			cUrl("/lec/prj/bbsAtcl/listPrjBbsCmnt"),
			{ 
			  "curPage"           	:   page,
			  "atclSn"	:	"${prjBbsAtclVO.atclSn}" },
			function(result) {
				var itemList = result.returnList;
				var pageInfo = result.pageInfo;
				if(itemList.length > 0) {
					var listStr = "<table style='width:97%'>";
					for (var i=0; i < itemList.length; i++) {
						var item = itemList[i];
						var delLink = "";
						delLink = "<a href=\"#_none\" onclick=\"delCmnt('"+item.atclSn+"','"+item.cmntSn+"')\"><img src=\"/lms/img/framework/icon/icon_delete.gif\" style=\"border:0\" align=\"absmiddle\" alt=\"삭제\"/></a>";
						listStr += "<tr style='min-height:50px;border-bottom:1px dotted gray'>";
						listStr += "<td valign='top' style='width:50px;padding-top:10px;'>아이콘</td>";
						listStr += "<td valign='top' style='padding-top:10px;line-height:1.5em'><span style='font-weight:bold;'>"+item.regNm+"("+item.regNo+")</span> | "+addDateFormatStr(item.regDttm)+" "+delLink+"<br/>"+item.cmntCts.replace(/\n/g,"<br/>")+"</td>";
						listStr += "</tr>";
					}
					listStr += "</table>";
					$("#cmntListArea").html(listStr).show();
					$("#cmntPageArea").pagingHtml(pageInfo, "listCmnt").show();
				} else {
					$("#cmntListArea").html("").hide();
					$("#cmntPageArea").html("").hide();
				}
				callResize();
			}
		);
		closeWorkProgress();
	}

	/*
	function viewPhoto(fileSn) {
		$(".photoView").hide();
		$("#photo_"+fileSn).show();
	}
	*/

	//댓글 등록
	function addCmnt() {
		var cmntCts = document.getElementById('cmntCts');

		if(cmntCts.value != "")
			$('#prjBbsAtclCmntForm').attr("action","/lec/prj/bbsAtcl/addPrjBbsCmntStu").submit();
		else alert('본문의 내용이 너무 잛습니다.');
	}

	//댓글 삭제
	function delCmnt(atclSn, cmntSn) {
		if(confirm('게시글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#prjBbsAtclCmntForm')
				.attr("action","/lec/prj/bbsAtcl/removePrjBbsCmntStu")
				.find('input[name=atclSn]').val(atclSn).end()
				.find('input[name=cmntSn]').val(cmntSn).end()
				.submit();
		} else {
			return;
		}
	}

	function callResize() {
		var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}

<%--
		// 댓글 컨트롤 토글
		$('#btnWriteComment').bind("click keydown", function(e) {
			$('#commentForm').toggle();
			return false;
		});

		// 댓글 저장
		$('#btnAddComment').bind("click keydown", function(e) {
			$('#commentForm')
			.find('input[name=cmd]').val('addComment').end()
			.ajaxSubmit(
				function(result) {
					if(result.returnResult >= 0) {
						var comment = result.returnDto;
						$('<div class="comment_div" id="comment_'+comment.cmntSn+'"></div>')
							.append(generateComment(comment)).prependTo('#comments');
						$('#commentForm').each(function(){ this.reset(); }).hide();
					} else {
						alert(result.returnMessage);	// 실패 안내 메시지 표시
					}
				}
			);
			return false;
		});

		// 댓글 등록 취소
		$('#btnAddCommentCancle').bind("click keydown", function(e) {
			$('#commentForm').each(function(){ this.reset(); }).hide();
			return false;
		});

		// 댓글 수정 폼 생성
		$('#comments').delegate('a.btnEditComment', 'click keydown', function(e) {
			var cmntSn = e.currentTarget.id.replace("btnEditComment", "");
			var ajaxData = {	cmd : "getComment", "commentDTO.cmntSn" : cmntSn };
			// 서버에 단일 항목을 조회해서 폼에 설정하고....
			$.getJSON(	ajaxUrl, ajaxData,
						function(result) {
							var comment = result.returnDto;
							var saveTag   = '<a class="btnSaveComment" id="btnSaveComment'+comment.cmntSn+'" href="#"><img src="'+lmsIconPath+'/icon_file_upload.gif" alt="저장"/></a>';
							var cancelTag = '<a class="btnCancelComment" id="btnCancelComment'+comment.cmntSn+'" href="#"><img src="'+lmsIconPath+'/icon_cancel.gif" alt="취소"/></a>';

							// 정상처리일 경우 수정폼을 표시
							if(result.returnResult >= 0) {
								$('#comment_'+cmntSn)
								.find('div.comment_content').empty()
								.append('<div>' + saveTag + cancelTag + '</div>')
								.append($('#commentTextarea')
											.clone().attr("id","commentTextArea"+cmntSn)
											.val(comment.cts).data("cmntSn", comment.cmntSn)
								);
							}
						}
			);
			return false;
		});

		// 댓글 수정 취소
		$('#comments').delegate('a.btnCancelComment', 'click keydown', function(e) {
			var cmntSn = e.currentTarget.id.replace("btnCancelComment", "");
			getComment(cmntSn);
			return false;
		});

		// 댓글 수정 저장
		$('#comments').delegate('a.btnSaveComment', 'click keydown', function(e) {
			var cmntSn = e.currentTarget.id.replace("btnSaveComment", "");
			var ajaxData = {	"cmd"				:	"editComment",
								"commentDTO.atclSn"	:	'${bbsAtclDTO.atclSn}',
								"commentDTO.cmntSn"	:	cmntSn,
								"commentDTO.cts"	:	$('#commentTextArea'+cmntSn).val() };
			$.getJSON(	ajaxUrl, ajaxData,
						function(response) {
							if(response.returnResult >= 0) {
								getComment(cmntSn);
							} else {
								alert(response.returnMessage);	// 실패 안내 메시지 표시
							}
			});
			return false;
		});

		// 댓글 삭제 버튼 이벤트
		$('#comments').delegate('.btnRemoveComment', 'click keydown', function(e) {
			var cmntSn = e.currentTarget.id.replace("btnRemoveComment", "");
			if(!confirm('댓글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) return false;
			var ajaxData = {	"cmd"				:	"removeComment",
								"commentDTO.cmntSn"	:	cmntSn			};
			$.getJSON (
					ajaxUrl, ajaxData,
					function(response) {
						if(response.returnResult >= 0) {
							$('#comment_'+cmntSn).remove();
						} else {
							alert(response.returnMessage);	// 실패 안내 메시지 표시
						}
			});
			return false;
		});

		// 댓글 목록 표시
		$.getJSON(	ajaxUrl,
					{	"cmd"				:	"listComment",
						"commentDTO.atclSn"	:	"${bbsAtclDTO.atclSn}" },
					function(response) {
						var commentList = response.returnList;
						for (var i=0; i<commentList.length; i++) {
							var comment = commentList[i];
							$('<div class="comment_div" id="comment_'+comment.cmntSn+'"></div>')
								.append(generateComment(comment)).appendTo('#comments');
						}
		});
--%>

<%--
	function getComment(cmntSn) {
		if(!$('#comment_'+cmntSn)) return false;
		$.getJSON( ajaxUrl,
				{	"cmd"		: "getComment",
					"commentDTO.cmntSn" : cmntSn
				},
				function(response) {
					var comment = response.returnDto;
					$('#comment_'+cmntSn).empty().append(generateComment(comment));
				});
	}

	function generateComment(comment) {
		var modifyTag = '<a class="btnEditComment" id="btnEditComment'+comment.cmntSn+'" href="#"><img src="'+lmsIconPath+'/icon_function_edit.gif" alt="편집"/></a>';
		var removeTag = '<a class="btnRemoveComment" id="btnRemoveComment'+comment.cmntSn+'" href="#"><img src="'+lmsIconPath+'/icon_delete.gif" alt="삭제"/></a>';
		var html = "";
		html += '	<div class="comment_info"><span>'+comment.regNm+'</span>'+addDateTimeFormatStr(comment.regDttm)+ modifyTag + removeTag + '</div>';
		html += '	<div class="comment_content"><p>'+comment.cts+'</p></div>';
		return html;
	}
--%>
</script>
</mhtml:body>
</mhtml:class_html>

