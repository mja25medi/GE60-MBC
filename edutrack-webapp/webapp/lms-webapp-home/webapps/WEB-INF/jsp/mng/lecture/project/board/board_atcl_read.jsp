<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjBbsAtclVO" value="${prjBbsAtclVO}"/>
<c:set var="prjBbsVO" value="${prjBbsVO}"/><mhtml:html>
<mhtml:head titleName="게시글 상세보기">
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/common_paging.js"/>
	<meditag:css href="libs/daumeditor/css/_orig/contents4view.css"/>
</mhtml:head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
		<div style="float:left; width:100%; height:30px;">
			<div style="float:left"><p style="font-weight:bold;margin-top:10px; margin-left:10px;">${prjBbsVO.bbsNm}</p></div>
		</div>
		<table summary="게시글  내용" style="width:100%" class="table_dtl" align="center">
			<col width="12%">
			<col width="22%">
			<col width="12%">
			<col width="21%">
			<col width="12%">
			<col width="21%">
			<tbody>
				<tr style="height:35px;">
					<td colspan="6" class="top" style="background-color:#f3f3f3">
						<div style="float:left;"><span style="font-weight:bold;font-size:14px;">${prjBbsAtclVO.atclTitle}</span></div>
						<div style="float:right;padding-right:10px;"><meditag:dateformat property="${prjBbsAtclVO.regDttm}" type="1" delimeter="." /> <meditag:dateformat property="${prjBbsAtclVO.regDttm}" type="7" delimeter="." /></div>
					</td>
				</tr>
				<tr style="height:32px;">
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
								<form id="prjBbsAtclCmntForm" name="prjBbsAtclCmntForm" onsubmit="return false" action="/LecturePrjBbsAtclAdmin.do">
								<input type="hidden" name="cmd"/>
								<input type="hidden" name="crsCreCd" value="${prjBbsAtclVO.crsCreCd}"/>
								<input type="hidden" name="prjtSn" value="${prjBbsAtclVO.prjtSn}"/>
								<input type="hidden" name="bbsCd" value="${prjBbsAtclVO.bbsCd}"/>
								<input type="hidden" name="atclSn" value="${prjBbsAtclVO.atclSn}"/>
								<input type="hidden" name="emoticonNo"/>
								<div style="float:left;width:50px;">아이콘</div>
								<div style="float:left;width:730px;">
									<textarea name="cmntCts" style="width:100%; height:60px ;margin: 0 auto;" id="cmntCts" class="txt"/>
								</div>
								<div style="float:left;padding-left:5px;">
									<a href="#_none" onclick="javascript:addCmnt()"><img src="/img/common/btn_entry01.gif" alt="확인" style="border:0"/></a>
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
		<meditag:buttonwrapper style="float:left; width:98%;">
			<meditag:button value="목록" title="목록" id="btnList"/>
			<c:if test="${prjBbsVO.ansrUseYn eq 'Y'}">
			<meditag:button value="답글" title="답글" id="btnReply"/>
			</c:if>
			<meditag:button value="수정" title="수정" id="btnEdit"/>
			<meditag:button value="삭제" title="삭제" id="btnRemove"/>
		</meditag:buttonwrapper>
	<form id="prjBbsAtclForm" name="prjBbsAtclForm" onsubmit="return false" action="/LecturePrjBbsAtclAdmin.do">
	<input type="hidden" name="cmd"/>
	<input type="hidden" name="curPage" />
	<input type="hidden" name="crsCreCd" />
	<input type="hidden" name="prjtSn" />
	<input type="hidden" name="bbsCd" />
	<input type="hidden" name="atclSn"/>
	<input type="hidden" name="parAtclSn" value="${prjBbsAtclVO.atclSn}"/>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();

	setFrame();

	/** 페이지 초기화 */
	$(document).ready(function(){
		displayWorkProgress();


		// 글 목록
		$('#btnList').bind("click keydown", function(e) {
			if($M.Check.Event.isClickEnter(e)) {
				e.preventDefault();
				$('#prjBbsAtclForm')
				.find('input[name=cmd]').val('main').end()
				.submit();
				return false;
			}
		});

		// 글 수정
		$('#btnEdit').bind("click keydown", function(e) {
			if($M.Check.Event.isClickEnter(e)) {
				e.preventDefault();
				$('#prjBbsAtclForm')
				.find('input[name=cmd]').val('editForm').end()
				.submit();
			}
		});

		// 답글 작성
		<%--
		$('#btnReply').bind("click keydown", function(e) {
			if($M.Check.Event.isClickEnter(e)) {
				e.preventDefault();
				$('#bbsAtclForm')
				.find('input[name=cmd]').val('addForm').end()
				.find('input[name="bbsAtclDTO.atclSn"]').val('').end()
				.submit();
			}
		});
		--%>

		// 글 삭제
		$('#btnRemove').bind("click keydown", function(e) {
			if($M.Check.Event.isClickEnter(e)) {
				if(confirm('글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
					e.preventDefault();
					$('#prjBbsAtclForm')
					.find('input[name=cmd]').val('remove').end()
					.submit();
				}
			}
		});

		<c:if test="${prjBbsVO.cmntUseYn eq 'Y'}">
		listCmnt(1); //-- 코멘트 목록을 호출한다.
		</c:if>
		closeWorkProgress();
	});	// end ready..

	function listCmnt(page) {
		displayWorkProgress();
		ItemDTO.curPage = page;
		// 댓글 목록 표시
		$.getJSON(
			cUrl("/LecturePrjBbsAtclAdmin.do"),
			{ "cmd"					:	"listCmnt",
			  "curPage"           	:   page,
			  "prjBbsCmntVO.atclSn"	:	"${prjBbsAtclVO.atclSn}" },
			function(result) {
				var itemList = result.returnList;
				var pageInfo = result.pageInfo;
				if(itemList.length > 0) {
					var listStr = "<table style='width:97%'>";
					for (var i=0; i < itemList.length; i++) {
						var item = itemList[i];
						var delLink = "";
						delLink = "<a href=\"#_none\" onclick=\"delCmnt('"+item.atclSn+"','"+item.cmntSn+"')\"><img src=\"/img/framework/icon/icon_delete.gif\" style=\"border:0\" align=\"absmiddle\" alt=\"삭제\"/></a>";
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
				var iframeObj = parent.document.getElementById("subWorkFrame2");
				parent.resizeIframe3(iframeObj, $(document).height());
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
	function addCmnt() {
		$('#prjBbsAtclCmntForm').find('input[name=cmd]').val('addCmnt');
		$('#prjBbsAtclCmntForm').ajaxSubmit(
			function(result) {
				if(result.returnResult >= 0) {
					$("#cmntCts").val('');
					listCmnt(1);
				} else {
					alert(result.returnMessage);	// 실패 안내 메시지 표시
				}
			}
		);
		return false;
	}

	function delCmnt(atclSn, cmntSn) {
		if(!confirm('댓글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) return false;
		var ajaxData = { "cmd" : "removeCmnt",
						 "prjBbsCmntVO.cmntSn" : cmntSn,
						 "prjBbsCmntVO.atclSn" : atclSn };
		$.getJSON (
			cUrl("/LecturePrjBbsAtclAdmin.do"), ajaxData,
			function(response) {
				if(response.returnResult >= 0) {
					listCmnt(ItemDTO.curPage);
				} else {
					alert(response.returnMessage);	// 실패 안내 메시지 표시
				}
		});
		return false;
	}

	function setFrame(){
		var iframeObj = parent.document.getElementById("subWorkFrame2");
		parent.resizeIframe3(iframeObj, $(document).height());
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
</mhtml:frm_body>
</mhtml:html>

