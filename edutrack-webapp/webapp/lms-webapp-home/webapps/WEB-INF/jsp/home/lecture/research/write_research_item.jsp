<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="gubun" value="${researchForm.gubun}"/>
<c:set var="researchItemDTO" value="${researchForm.researchItemDTO}" />
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module calendar="Y"/>
	<meditag:js src="/js/selectbox.js"/>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />

		<nested:form action="/ResearchLecture.do" onsubmit="return false" id="researchForm">
		<input type="hidden" name="cmd"/>
		<input type="hidden" property="researchItemDTO.crsCreCd" />
		<input type="hidden" property="researchItemDTO.reshSn" />
		<input type="hidden" property="researchItemDTO.reshItemSn" />

		<table class="board_b" >
			<caption>설문관리 - 설문등록</caption>
			<colgroup>
				<col width="20%" />
				<col width="auto" />
			</colgroup>
			<tbody>
				<tr>
					<th><strong>문제 유형</strong></th>
					<td>
						<select name="researchItemDTO.reshItemTypeCd" id="itemType" onchange="changeType()">
							<c:forEach var="item" items="${codeList}">
							<option value="${item.codeCd}" <c:if test="${item.codeCd eq researchItemDTO.reshItemTypeCd}">selected</c:if>>${item.codeNm}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="problem">설문내용</label></th>
					<td  class="conts01"><nested:textarea property="researchItemDTO.reshItemCts" dispName="설문내용" rows="10" style="width:90%;"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw01">보기1</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl1" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw02">보기2</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl2" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw03">보기3</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl3" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw04">보기4</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl4" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw05">보기5</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl5" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw05">보기6</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl6" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw05">보기7</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl7" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw05">보기8</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl8" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw05">보기9</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl9" style="width:90%;height:80"></nested:textarea></td>
				</tr>
				<tr class="itemTypeK">
					<th scope="row"><label for="viw05">보기10</label></th>
					<td class="conts01" ><nested:textarea property="researchItemDTO.empl10" style="width:90%;height:80"></nested:textarea></td>
				</tr>
			</tbody>
		</table>
		</nested:form>

		<div class="btn_right">
			<c:if test="${gubun eq 'A'}">
			<a href="#_none" onclick="addItem()" class="btn02">저장</a>
			</c:if>
			<c:if test="${gubun eq 'E'}">
			<a href="#" onclick="editItem()" class="btn02">수정</a>
			<a href="#" onclick="delItem()" class="btn02">삭제</a>
			</c:if>
			<a href="<c:url value="/ResearchLecture.do"/>?cmd=listItem&amp;researchDTO.reshSn=${researchItemDTO.reshSn}" class="btn01">목록</a>
		</div>
	</div>

<script type="text/javascript">

	$(document).ready(function() {
		changeType();
	});

	function changeType() {
		var itemType = $("#itemType option:selected").val();
		if(itemType != "K") $(".itemTypeK").hide();
		else $(".itemTypeK").show();
	}

	/**
	 * 설문 문제 등록
	 */
	function addItem() {
		var itemType = $("#itemType option:selected").val();
		var f = document.researchForm;
		if(isEmpty(f["researchItemDTO.reshItemCts"].value)) {
			alert('설문내용을 입력하세요.');
			f["researchItemDTO.reshItemCts"].focus();
			return false;
		}

		if(itemType == "K") {
			if(isEmpty(f["researchItemDTO.empl1"].value)) {
				alert("보기는 2개이상 입력하셔야 합니다.");
				f["researchItemDTO.empl1"].focus();
				return false;
			}
			if(isEmpty(f["researchItemDTO.empl2"].value)) {
				alert("보기는 2개이상 입력하셔야 합니다.");
				f["researchItemDTO.empl2"].focus();
				return false;
			}
		}

		$('#researchForm').find('input[name=cmd]').val("addItem");
		$('#researchForm').submit();
	}

	/**
	 * 설문 문제 수정
	 */
	function editItem() {
		var itemType = $("#itemType option:selected").val();
		var f = document.researchForm;
		if(isEmpty(f["researchItemDTO.reshItemCts"].value)) {
			alert('설문내용을 입력하세요.');
			f["researchItemDTO.reshItemCts"].focus();
			return false;
		}

		if(itemType == "K") {
			if(isEmpty(f["researchItemDTO.empl1"].value)) {
				alert("보기는 2개이상 입력하셔야 합니다.");
				f["researchItemDTO.empl1"].focus();
				return false;
			}
			if(isEmpty(f["researchItemDTO.empl2"].value)) {
				alert("보기는 2개이상 입력하셔야 합니다.");
				f["researchItemDTO.empl2"].focus();
				return false;
			}
		}

		$('#researchForm').find('input[name=cmd]').val("editItem");
		$('#researchForm').submit();
	}

	/**
	 * 설문 문제 삭제
	 */
	function delItem() {
		if(confirm('설문 문제를 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#researchForm').find('input[name=cmd]').val('deleteItem');
			$('#researchForm').submit();
		} else {
			return;
		}
	}
</script>
</mhtml:class_body>
</mhtml:class_html>