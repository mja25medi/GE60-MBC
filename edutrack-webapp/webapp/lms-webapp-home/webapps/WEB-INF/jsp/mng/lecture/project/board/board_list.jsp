<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjBbsVO" value="${prjBbsVO}"/>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
</mhtml:head>
<mhtml:frm_body>
	<form id="prjBbsForm" name="prjBbsForm" onsubmit="return false" action="/LecturePrjBbsAdmin.do">	
		<input type="hidden" name="cmd"/>
		<input type="hidden" name="crsCreCd" />
		<input type="hidden" name="prjtSn" />
	</form>
	<div class="dvcontents">
		<div class="subhead">
			<div style="float:right">
				<meditag:button value="게시판 등록" title="게시판  등록" func="addPrjBbs()" />
			</div>
		</div>
		<table summary="게시판 목록" style="width:100%" class="table_list">
			<colgroup>
				<col style="width:60px"/>
				<col style="width:auto"/>
				<col style="width:120px"/>
				<col style="width:120px"/>
				<col style="width:120px"/>
				<col style="width:120px"/>
				<col style="width:60px"/>
			</colgroup>
			<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">게시판 명</th>
				<th scope="col">답글</th>
				<th scope="col">댓글</th>
				<th scope="col">첨부</th>
				<th scope="col">사용여부</th>
				<th scope="col">수정</th>
			</tr>
			</thead>
			<tbody id="tbodyList"></tbody>
		</table>
	</div>
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjBbsVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjBbsVO.prjtSn}';
		//게시판 목록 조회
		listPjtBbs();
	});

	//게시판 목록 조회
	function listPjtBbs(){
		$.getJSON(cUrl("/LecturePrjBbsAdmin.do"),		//url
				{ "cmd" : "list",
				  "prjBbsVO.crsCreCd" : ItemDTO.crsCreCd,
				  "prjBbsVO.prjtSn" : ItemDTO.prjtSn
				},						//parameter
				listPjtBbsCallback		//callback function
		);
		displayWorkProgress();
	}


	//게시판 목록 조회 callback function
	function listPjtBbsCallback(ProcessResultListDTO){
		var retList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='7' align='center'><font color=blud>* 팀프로젝트 게시판이 없습니다.</font></td></tr>\n";
		}
		var cnt = 0;
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			listStr +="	<tr>\n";
			listStr +="		<td >"+(++cnt)+"</td>\n";
			listStr +="		<td >"+item.bbsNm+"</td>\n";
			listStr +="		<td >"+item.ansrUseYnNm+"</td>\n";
			listStr +="		<td >"+item.cmntUseYnNm+"</td>\n";
			listStr +="		<td >"+item.atchUseYnNm+"</td>\n";
			listStr +="		<td >"+item.useYnNm+"</td>\n";
			listStr +="<td><a class='squarebutton' href='#none' onclick='editPrjBbs(\""+item.bbsCd+"\");'><span>수정</span></a></td> \n";
			listStr +="	</tr>\n";
		}

		$("#tbodyList").html(listStr);

		setFrame();

		closeWorkProgress();
	}

	//게시판 등록 폼
	function addPrjBbs() {
		 var addContent  = "<iframe id='addPrjBbsFrame' name='addPrjBbsFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/LecturePrjBbsAdmin.do"/>"
			+ "?cmd=addForm&amp;prjBbsVO.crsCreCd="+ItemDTO.crsCreCd+""
			+ "&amp;prjBbsVO.prjtSn="+ItemDTO.prjtSn+"'/>";

			parent.parent.projectPopBox.clear();
			parent.parent.projectPopBox.addContents(addContent);
			parent.parent.projectPopBox.resize(800, 400);
			parent.parent.projectPopBox.setTitle("게시판 정보 등록 ");
			parent.parent.projectPopBox.show();
	}

	function editPrjBbs(bbsCd) {
		document.location.href = "/LecturePrjBbsAdmin.do?cmd=editForm${AMPERSAND}prjBbsVO.crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjBbsVO.prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjBbsVO.bbsCd="+bbsCd;
	}

	function setFrame(){
		var iframeObj = parent.document.getElementById("subWorkFrame2");
		parent.resizeIframe3(iframeObj, $(document).height());
	}
</script>
</mhtml:frm_body>
</mhtml:html>