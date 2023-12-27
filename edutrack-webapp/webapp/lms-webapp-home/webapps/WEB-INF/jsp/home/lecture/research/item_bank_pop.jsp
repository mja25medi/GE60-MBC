<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="researchDTO" value="${researchForm.researchDTO}" />
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:css href="css/home/pop.css"/>
	<mhtml:head_module encryptjs="Y"/> <!-- 개인정보 복호화에 사용 -->
</mhtml:home_head>
<body>
	<div class="wrap" style="width:754px;">
		<h1>시험문제은행</h1>
		<div class="contents">

			<form name="Search" id="Search">
			<table class="board_b" align="center">
				<caption>분류선택</caption>
				<colgroup>
					<col style="width:20%"/>
					<col style="width:80%"/>
				</colgroup>
				<tr>
					<th scope="row" >설문은행</th>
					<td >
						<select name="reshSn" id="reshSn" onchange="changeBank()">
						<c:forEach items="${bankList}" var="item">
							<option value="${item.reshSn}">${item.reshTitle}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<div class="btn_right" style="margin-top:10px;margin-bottom:10px;">
				<a href="javascript:addQuestion()" class="btn02">문제등록</a>
				<a href="javascript:self.close();" class="btn01">창닫기</a>
			</div>
			<table summary="문제은행 문제 목록" class="board_a" align="center">
				<colgroup>
					<col style="width:5%"/>
					<col style="width:5%"/>
					<col style="width:auto"/>
					<col style="width:10%"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="전체 선택" onclick="checkAll()"/></th>
						<th scope="col">번호</th>
						<th scope="col">문제</th>
						<th scope="col">문제유형</th>
					</tr>
				</thead>
				<tbody id="tbodyList"></tbody>
			</table>
			</form>
			<nested:form action="/ResearchLecture.do" onsubmit="return false" id="researchForm">
			<input type="hidden" name="cmd" />
			<input type="hidden" property="researchDTO.crsCreCd" value="${researchDTO.crsCreCd}"/>
			<input type="hidden" property="researchDTO.reshSn" value="${researchDTO.reshSn}"/>
			<input type="hidden" property="researchDTO.tarReshSn" value=""/>
			</nested:form>
		</div>

<script type="text/javascript">

	$(document).ready(function() {
		listItem();

        //전체선택 /전체취소
		$('#chkAll').click(function(){
		    var state=$('input[name=chkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#Search input[name='sel']").attr({checked:true});
		    }else{
		    	$(document).find("#Search input[name='sel']").attr({checked:false});
		    }
		});
	});

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listItem() {
		var reshSn = $("#reshSn > option:selected").val();
		$.getJSON( cUrl("/ResearchLecture.do"),		// url
			{ "cmd" : "listBankItem" , "reshSn" : reshSn },			// params
			listItemCallback				// callback function
		);
	}

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listItemCallback(ProcessResultListDTO) {
		var itemList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(itemList.length == 0) {
			listStr +="	<tr><td colspan='3' align='center'><font color=blud>* 등록된 문제가 없습니다.</font></td></tr>\n";
		}
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];

			listStr +="	<tr>\n";
			listStr +="     <td><input type='checkbox' name='sel' id='sel' value='"+item.reshItemSn+"' style='border:0' title='선택'></td>\n";
			listStr +="		<td >"+(i+1)+"</td>\n";
			listStr +="		<td class='subject'>"+item.reshItemCts+"</td>\n";
			listStr +="		<td >"+item.reshItemTypeNm+"</td>\n";
			listStr +="	</tr>\n";
		}
		$("#tbodyList").html(listStr);
	}

	function addQuestion() {
		//var reshSn = $("#reshSn > option:selected").val();
		var reshSn = $("#Search input[name='sel']:checked").stringValues();
		alert(reshSn);
		$("#researchForm").find('input[name$=tarReshSn]').val(reshSn);
		process('addResearchItemFromBank');
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		//if(!validate(document.researchForm)) return;
		$('#researchForm').find('input[name=cmd]').val(cmd);
		$('#researchForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listItem();
			parent.bankBox.close();
		} else {
			// 비정상 처리
		}
	}

</script>
</body>
</mhtml:home_html>