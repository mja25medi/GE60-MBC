<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br/>
	<form name="Search" action="javascript:list()">
	<table summary="설문 검색 폼" class="table_dtl" style="width:96%" align="center">
		<tr>
			<td align='left' width="60">설문 명:</td>
			<td width="100">
				<input type="text" name="searchValue" style="width:100px">
			</td>
			<td align='left'>
				<meditag:button value="검색" title="설문을 검색합니다." func="list()" />
			</td>
		</tr>
	</table>
	<table summary="설문 목록 표" style="width:96%" class="table_list" align="center">
		<colgroup>
			<col style="width:15%"/>
			<col style="width:60%"/>
			<col style="width:15%"/>
			<col style="width:10%"/>
		</colgroup>
		<thead>
		<tr>
			<th scope="col">번 호</th>
			<th scope="col">설문 명</th>
			<th scope="col">문항수</th>
			<th scope="col">선 택</th>
		</tr>
		</thead>
		<tbody id="tbodyList"></tbody>
	</table>
	</form>
<script type="text/javascript">
	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		list();
	});

	/**
	 * 설문 목록 조회
	 */
	function list() {
		var f = document.Search;
		var searchValue = f.searchValue.value;
		$.getJSON( cUrl("/CourseResearchBankAdmin.do"),		// url
				{ "cmd" : "searchList",
				  "searchValue" :  searchValue
				},			// params
				listCallback				// callback function
			);
		displayWorkProgress();
	}


	/**
	 * 목록 조회 Callback
	 */
	function listCallback(ProcessResultListDTO) {
		var itemList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(itemList.length == 0) {
			listStr +="	<tr><td colspan='3'><font color=blud>* 등록된 설문이 없습니다.</font></td></tr>\n";
		}
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];
			listStr +="	<tr>\n";
			listStr +="		<td>"+item.reshSn+"</td>\n";
			listStr +="		<td class='left'>"+item.reshTitle+"</td>\n";
			listStr +="		<td>"+item.itemCnt+"</td>\n";
			listStr +="		<td><a href=\"javascript:selResh('"+item.reshSn+"','"+item.reshTitle+"')\">선택</a></td>\n";
			listStr +="	</tr>\n";
		}
		$("#tbodyList").html(listStr);
		closeWorkProgress();
	}

	/**
	* 설문 선택
	*/
	function selResh(reshSn, reshTitle){
		if('${reshTpltGubun}' == "course"){
			parent.courseFrom["reshSn"].value = reshSn;
			parent.courseFrom["reshTitle"].value = reshTitle;
		}else{
			parent.createCourseFrom["reshSn"].value = reshSn;
			parent.createCourseFrom["reshTitle"].value = reshTitle;
		}
		parent.reshTpltPopBox.close();

	}
</script>
