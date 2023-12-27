<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="crsBbsAtclVO" value="${vo}"/>
<c:set var="crsBbsInfoVO" value="${crsBbsInfoVO}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/common_paging.js"/>
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:class_head>
<mhtml:class_body>
        <!--CONTENTS START-->
        <div id="content">
			 <mhtml:class_location />

			<table class="board_b" >
				<caption class="sr-only">${crsBbsInfoVO.bbsNm}</caption>
				<colgroup>
					<col width="15%" />
					<col width="40%" />
					<col width="15%" />
					<col width="auto" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3"><strong>${crsBbsAtclVO.title}</strong></td>
					</tr>
                    <tr>
                        <th scope="row">작성자</th>
                        <td>${crsBbsAtclVO.regNm}</td>
                        <th scope="row">작성일</th>
                        <td>
                        	<meditag:dateformat type="1" delimeter="." property="${crsBbsAtclVO.regDttm}"/>
                        	<div style="float:right">조회수 : ${crsBbsAtclVO.hits}</div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="col" colspan="4" class="rnone">내용</th>
                    </tr>
                    <tr>
                        <td colspan="4" class="" valign="top">${crsBbsAtclVO.cts}</td>
                    </tr>
                    <tr>
                        <th scope="row">첨부파일</th>
                        <td colspan="3">
							<c:forEach var="fileItem" items="${crsBbsAtclVO.attachFiles}" varStatus="status">
								<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
							</c:forEach>
						</td>
                    </tr>
                </tbody>
            </table>
            <div class="btn_right">
				<c:if test="${crsBbsAtclVO.regNo eq sessionScope.USERNO}">
				<a href="<c:url value="/lec/crsBbs/editForm?searchKey=${crsBbsAtclVO.searchKey}&amp;searchValue=${crsBbsAtclVO.searchValue}&amp;bbsCd=${crsBbsAtclVO.bbsCd}&amp;curPage=${crsBbsAtclForm.curPage}&amp;atclSn=${crsBbsAtclVO.atclSn}"/>"><img src="${img_base}/common/btn/btn_revisetxt.gif" alt="수정" /></a>
				<a href="<c:url value="/lec/crsBbs/remove?searchKey=${crsBbsAtclVO.searchKey}&amp;searchValue=${crsBbsAtclVO.searchValue}&amp;bbsCd=${crsBbsAtclVO.bbsCd}&amp;curPage=${crsBbsAtclForm.curPage}&amp;atclSn=${crsBbsAtclVO.atclSn}"/>"><img src="${img_base}/common/btn/btn_canceltxt.gif" alt="삭제" /></a>
				</c:if>
				<c:if test="${crsBbsInfoVO.ansrUseYn eq 'Y' && not empty sessionScope.USERNO}">
				<a href="<c:url value="/lec/crsBbs/addForm?searchKey=${crsBbsAtclVO.searchKey}&amp;searchValue=${crsBbsAtclVO.searchValue}&amp;bbsCd=${crsBbsAtclVO.bbsCd}&amp;curPage=${crsBbsAtclForm.curPage}&amp;parAtclSn=${crsBbsAtclVO.atclSn}"/>"><img src="${img_base}/common/btn/btn_answer.gif" alt="답글" /></a>
				</c:if>
				<a href="<c:url value="/lec/crsBbs/main?searchKey=${crsBbsAtclVO.searchKey}&amp;searchValue=${crsBbsAtclVO.searchValue}&amp;bbsCd=${crsBbsAtclVO.bbsCd}&amp;curPage=${crsBbsAtclForm.curPage}"/>"><img src="${img_base}/common/btn/btn_list.gif" alt="목록" /></a>
			</div>
			<c:if test="${crsBbsInfoVO.cmntUseYn eq 'Y'}">
			<div class="regist">
				<c:if test="${not empty sessionScope.USERNO}">
				<form id="crsBbsCmntForm" name="crsBbsCmntForm" onsubmit="return false">
				<input type="hidden" name="atclSn" value="${crsBbsAtclVO.atclSn}"/>
				<input type="hidden" name="bbsCd" value="${crsBbsAtclVO.bbsCd}"/>
				<textarea name="cmntCts" id="cmntCts" class="textarea" style="width:620px;"></textarea>
				<button type="button" class="btn_regist" onclick="javascript:addCmnt()"><span class="hide">등록</span></button>
				<input type="submit" value="submit" style="display:none" />
				</form>
				</c:if>
				<ul id="cmntList"></ul>
				<div style="float:left;width:100%;margin:10px 10px 10px 10px;display:none;text-align:center" id="cmntPage"></div>
			</div>
			</c:if>
		</div>
	<!--//CONTENTS END-->

<script type="text/javascript">
	var ItemDTO = { "curPage" : "1" };
	var ItemDTO = new Object();
	$(document).ready(function() {
		<c:if test="${crsBbsInfoVO.cmntUseYn eq 'Y'}">
			listCmnt(1);
		</c:if>
	});

	<c:if test="${crsBbsInfoVO.cmntUseYn eq 'Y'}">
		//-- 게시물 댓글 관련 기능
		function listCmnt(page){
			ItemDTO.curPage = page;
			// 게시물 댓글 목록 표시
			$.getJSON(
				cUrl('/lec/crsBbs/listCmnt'),
				{ 
				  "curPage"           	:   page,
				  "atclSn"	:	"${crsBbsAtclVO.atclSn}",
				  "bbsCd"	:	"${crsBbsAtclVO.bbsCd}"
				},
				function(result) {
					var itemList = result.returnList;
					var pageInfo = result.pageInfo;
					if(itemList.length > 0) {
						var listStr = "";
						for (var i=0; i < itemList.length; i++) {
							var item = itemList[i];
							listStr += "<li>";
							listStr += "<span class=\"name\">"+item.regNm+"</span><span class=\"date\">"+addDateFormatStr(item.regDttm)+"</span>";
							listStr += "<p>"+item.cmntCts.replace(/\n/g,"<br/>")+"</p>";
							listStr += "<div>";
							/* if(item.regNo == '${sessionScope.USERNO}') { */
								listStr += "<button type=\"button\" onclick=\"javascript:delCmnt('"+item.cmntSn+"')\"><span>삭제</span></button>";
							/* } */

							listStr += "</div>";
							listStr += "</li>";
						}
						$("#cmntList").html(listStr).show();
						$("#cmntPage").pagingHtml(pageInfo, "listCmnt").show();
					} else {
						$("#cmntList").html("").hide();
						$("#cmntPage").html("").hide();
					}


				}
			);
		}

		/* 게시물 댓글 저장*/
		function addCmnt() {
			$('#crsBbsCmntForm').attr("action","/lec/crsBbs/addCmnt");
			$('#crsBbsCmntForm').ajaxSubmit(
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


		/* 게시물 댓글  삭제 */
		function delCmnt(cmntSn) {
			if(!confirm('댓글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) return false;
			var ajaxUrl = cUrl('/lec/crsBbs/removeCmnt');
			var ajaxData = { 
					         "bbsCd" : "${crsBbsAtclVO.bbsCd}",
					         "atclSn" : "${crsBbsAtclVO.atclSn}",
							 "cmntSn" : cmntSn };
			$.getJSON (
				ajaxUrl, ajaxData,
				function(response) {
					if(response.returnResult >= 0) {
						listCmnt(ItemDTO.curPage);
					} else {
						alert(response.returnMessage);	// 실패 안내 메시지 표시
					}
			});
			return false;
		}

	</c:if>
</script>
</mhtml:class_body>
</mhtml:class_html>