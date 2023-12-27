<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjBbsAtclVO" value="${prjBbsAtclVO}"/>
<c:set var="prjBbsList" value="${prjBbsList}"/>
<mhtml:html>
<mhtml:head titleName="게시글 관리">
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/common_paging.js"/>
</mhtml:head>
<mhtml:frm_body>
	<form id="prjBbsAtclForm" name="prjBbsAtclForm" onsubmit="return false" action="/LecturePrjBbsAtclAdmin.do">
	<input type="hidden" name="cmd"/>
	<input type="hidden" name="crsCreCd" />
	<input type="hidden" name="prjtSn" />
	<div class="dvcontents">
		<div class="subhead">
			<div style="float:left;padding-right:5px">
				<meditag:selectbox width="210" height="150" fieldName="prjBbsAtclVO.bbsCd" formName="prjBbsAtclForm" onChange="listPrjBbsAtcl(1);" value="${prjBbsAtclVO.bbsCd}"/>
				<meditag:selectboxOption formName="prjBbsAtclForm" fieldName="prjBbsAtclVO.bbsCd" value="" text="게시판 선택"/>
				<c:forEach items="${prjBbsList}" var="prjBbsList" >
					<meditag:selectboxOption formName="prjBbsAtclForm" fieldName="prjBbsAtclVO.bbsCd" value="${prjBbsList.bbsCd}" text="${prjBbsList.bbsNm}"/>
				</c:forEach>
			</div>
			<div style="float:left; padding-right:5px;">
				<select name="searchKey" style="width: 70px;" id="searchKey" class="_enterBind" title="검색 분류 선택">
					<html:option value="title">제 목</html:option>
					<html:option value="regNm">등록자</html:option>
				</select>
			</div>
			<div style="float:left">
				<input type="text" name="searchValue" style="width:100px" id="searchValue" class="_enterBind txt" maxlength="20" title="검색어 입력"/>
				<input type="image" src="<c:url value="/img/framework/icon/icon_function_search.gif"/>" alt="검색" title="검색" onclick="listPrjBbsAtcl(1)">
			</div>
			<div style="float:right">
				<meditag:button value="글쓰기" title="글쓰기" func="addFormPrjBbsAtcl()" />
			</div>
		</div>
		<table summary="게시글 목록" style="width:100%" class="table_list">
			<colgroup>
				<col style="width:100px"/>
				<col style="width:150px"/>
				<col style="width:auto"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
			</colgroup>
			<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">게시판 명</th>
				<th scope="col">제목</th>
				<th scope="col">등록자</th>
				<th scope="col">조회수</th>
				<th scope="col">등록일</th>
			</tr>
			</thead>
			<tbody id="tbodyList"></tbody>
		</table>
		<div id="paging_area" style="height:50;text-align:center;padding-top:10px"></div>
	</div>
	</form>
<script type="text/javascript">


	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjBbsAtclVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjBbsAtclVO.prjtSn}';

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listPrjBbsAtcl(1);
			}
		}
		//게시글 목록 조회
		listPrjBbsAtcl(1);
	});

	//게시글 목록 조회
	function listPrjBbsAtcl(curpage){
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $("#searchValue").val();

		var bbsCd = prjBbsAtclFormprjBbsAtclVO_bbsCd.getSelectedValue();
		$.getJSON(cUrl("/LecturePrjBbsAtclAdmin.do"),		//url
				{ "cmd" : "list",
				  "prjBbsAtclVO.crsCreCd" : ItemDTO.crsCreCd,
				  "prjBbsAtclVO.prjtSn" : ItemDTO.prjtSn,
				  "prjBbsAtclVO.bbsCd" : bbsCd,
				  "prjBbsAtclVO.searchKey" : searchKey,
				  "prjBbsAtclVO.searchValue" : searchValue,
				  "curPage" : curpage,
				},							//parameter
				listPrjBbsAtclCallback		//callback function
		);
		displayWorkProgress();
	}


	//게시글 목록 조회 callback function
	function listPrjBbsAtclCallback(ProcessResultListDTO){
		var f = document.prjBbsAtclForm;
		var retList = ProcessResultListDTO.returnList;
		var pageInfo = ProcessResultListDTO.pageInfo;
		var listStr = "";
		if(retList.length == 0) {
			listStr +="	<tr><td colspan='6' align='center'>* 등록된 게시글이 없습니다.</td></tr>\n";
		}
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			listStr +="	<tr>\n";
			listStr +="		<td >"+(pageInfo.startPageNum-i)+"</td>\n";
			listStr +="		<td >"+item.bbsNm+"</td>\n";
			listStr +="		<td style='text-align: left;'><a href='#' onClick='readPrjBbsAtcl(\""+item.bbsCd+"\",\""+item.atclSn+"\");'>"+item.atclTitle+"</a></td>\n";
			listStr +="		<td >"+item.regNm+"</td>\n";
			listStr +="		<td >"+item.hits+"</td>\n";
			listStr +="		<td >"+addDateFormatStr(item.regDttm)+"</td>\n";
			listStr +="	</tr>\n";
		}

		$("#tbodyList").html(listStr);
		$("#paging_area").pagingHtml(pageInfo, "listPrjBbsAtcl");

		setFrame();

		closeWorkProgress();
	}

	//글쓰기
	function addFormPrjBbsAtcl() {
		var bbsCd = prjBbsAtclFormprjBbsAtclVO_bbsCd.getSelectedValue();
		if(bbsCd == ""){
			alert("게시판을 선택하세요.");
			return;
		}
		location.href="/LecturePrjBbsAtclAdmin.do?cmd=addForm${AMPERSAND}prjBbsAtclVO.crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjBbsAtclVO.prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjBbsAtclVO.bbsCd="+bbsCd;
	}

	//게시글 상세보기
	function readPrjBbsAtcl(bbsCd,atclSn) {
		location.href="/LecturePrjBbsAtclAdmin.do?cmd=read${AMPERSAND}prjBbsAtclVO.crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjBbsAtclVO.prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjBbsAtclVO.bbsCd="+bbsCd+"${AMPERSAND}prjBbsAtclVO.atclSn="+atclSn;
	}

	function setFrame(){
		var iframeObj = parent.document.getElementById("subWorkFrame2");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

</script>
</mhtml:frm_body>
</mhtml:html>

