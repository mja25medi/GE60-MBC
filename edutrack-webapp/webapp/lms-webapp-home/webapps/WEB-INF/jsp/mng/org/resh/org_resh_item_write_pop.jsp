<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="researchBankItemVO" value="${vo}" />
	<form id="orgReshForm" name="orgReshForm" onsubmit="return false">
	<input type="hidden" name="reshSn" value="${vo.reshSn }" />
	<input type="hidden" name="itemOdr" value="${vo.itemOdr }" />
	<input type="hidden" name="reshItemSn" value="${vo.reshItemSn }" />
	<input type="hidden" name="editorYn" value="N" />
	<table summary="<spring:message code="course.title.reshbank.item.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:15%"/>
			<col style="width:35%"/>
			<col style="width:15%"/>
			<col style="width:35%"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="course.title.reshbank.item.type"/></th>
				<td colspan="3">
					<select name="reshItemTypeCd" id="reshItemTypeCd" class="form-control input-sm"  onChange="changeType()">
						<c:forEach var="item" items="${reshItemTypeCdList}" varStatus="status">
						<option value="${item.codeCd}" <c:if test="${vo.reshItemTypeCd eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
						</c:forEach>				
					</select>
				</td>
			</tr>
			<tr class="itemViewTypeK">
				<th scope="row"><spring:message code="course.title.reshbank.item.view"/></th>
				<td>
					<select name="emplViewType" id="emplViewType" class="form-control input-sm" >
						<c:forEach var="item" items="${emplViewTypeList}" varStatus="status">
						<option value="${item.codeCd}" <c:if test="${vo.emplViewType eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
						</c:forEach>				
					</select>
				</td>
				<th scope="row"><spring:message code="course.title.reshbank.item.emplcnt"/></th>
				<td>
					<c:set var="emplCnt" value="${researchBankItemVO.emplCnt}"/>
					<c:if test="${empty emplCnt}"><c:set var="emplCnt" value="5"/></c:if>
					<select name="emplCnt" id="emplCnt" class="form-control input-sm" onChange="changeEmpl()">
						<option value="1" <c:if test="${emplCnt == 1}">selected</c:if>>1</option>
						<option value="2" <c:if test="${emplCnt == 2}">selected</c:if>>2</option>
						<option value="3" <c:if test="${emplCnt == 3}">selected</c:if>>3</option>
						<option value="4" <c:if test="${emplCnt == 4}">selected</c:if>>4</option>
						<option value="5" <c:if test="${emplCnt == 5}">selected</c:if>>5</option>
						<option value="6" <c:if test="${emplCnt == 6}">selected</c:if>>6</option>
						<option value="7" <c:if test="${emplCnt == 7}">selected</c:if>>7</option>
						<option value="8" <c:if test="${emplCnt == 8}">selected</c:if>>8</option>
						<option value="9" <c:if test="${emplCnt == 9}">selected</c:if>>9</option>
						<option value="10" <c:if test="${emplCnt == 10}">selected</c:if>>10</option>
						<option value="11" <c:if test="${emplCnt == 11}">selected</c:if>>11</option>
						<option value="12" <c:if test="${emplCnt == 12}">selected</c:if>>12</option>
						<option value="13" <c:if test="${emplCnt == 13}">selected</c:if>>13</option>
						<option value="14" <c:if test="${emplCnt == 14}">selected</c:if>>14</option>
						<option value="15" <c:if test="${emplCnt == 15}">selected</c:if>>15</option>
						<option value="16" <c:if test="${emplCnt == 16}">selected</c:if>>16</option>
						<option value="17" <c:if test="${emplCnt == 17}">selected</c:if>>17</option>
						<option value="18" <c:if test="${emplCnt == 18}">selected</c:if>>18</option>
						<option value="19" <c:if test="${emplCnt == 19}">selected</c:if>>19</option>
						<option value="20" <c:if test="${emplCnt == 20}">selected</c:if>>20</option>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="reshItemCts"><spring:message code="course.title.reshbank.item.desc"/></label></th>
				<td colspan="3" style="paddgin:3px 0px 3px 0px">
					<textarea name="reshItemCts" style="height:80px" id="reshItemCts" class="form-control input-sm">${vo.reshItemCts }</textarea>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl1" >
				<th scope="row"><label for="empl1"><spring:message code="course.title.reshbank.item.empl"/>1</label></th>
				<td colspan="3">
					<textarea name="empl1" lenCheck="1000" style="height:30px;" id="empl1" class="form-control input-sm">${vo.empl1 }</textarea>
					<input type="hidden" name="emplScore1" value="${vo.emplScore1 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl2" >
				<th scope="row"><label for="empl2"><spring:message code="course.title.reshbank.item.empl"/>2</label></th>
				<td colspan="3">
					<textarea name="empl2" lenCheck="1000" style="height:30px;" id="empl2" class="form-control input-sm">${vo.empl2 }</textarea>
					<input type="hidden" name="emplScore2" value="${vo.emplScore2 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl3" >
				<th scope="row"><label for="empl3"><spring:message code="course.title.reshbank.item.empl"/>3</label></th>
				<td colspan="3">
					<textarea name="empl3" lenCheck="1000" style="height:30px;" id="empl3" class="form-control input-sm">${vo.empl3 }</textarea>
					<input type="hidden" name="emplScore3" value="${vo.emplScore3 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl4" >
				<th scope="row"><label for="empl4"><spring:message code="course.title.reshbank.item.empl"/>4</label></th>
				<td colspan="3">
					<textarea name="empl4" lenCheck="1000" style="height:30px;" id="empl4" class="form-control input-sm">${vo.empl4 }</textarea>
					<input type="hidden" name="emplScore4" value="${vo.emplScore4 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl5" >
				<th scope="row"><label for="empl5"><spring:message code="course.title.reshbank.item.empl"/>5</label></th>
				<td colspan="3">
					<textarea name="empl5" lenCheck="1000" style="height:30px;" id="empl5" class="form-control input-sm">${vo.empl5 }</textarea>
					<input type="hidden" name="emplScore5" value="${vo.emplScore5 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl6" >
				<th scope="row"><label for="empl6"><spring:message code="course.title.reshbank.item.empl"/>6</label></th>
				<td colspan="3">
					<textarea name="empl6" lenCheck="1000" style="height:30px;" id="empl6" class="form-control input-sm">${vo.empl6 }</textarea>
					<input type="hidden" name="emplScore6" value="${vo.emplScore6 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl7" >
				<th scope="row"><label for="empl7"><spring:message code="course.title.reshbank.item.empl"/>7</label></th>
				<td colspan="3">
					<textarea name="empl7" lenCheck="1000" style="height:30px;" id="empl7" class="form-control input-sm">${vo.empl7 }</textarea>
					<input type="hidden" name="emplScore7" value="${vo.emplScore7 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl8" >
				<th scope="row"><label for="empl8"><spring:message code="course.title.reshbank.item.empl"/>8</label></th>
				<td colspan="3">
					<textarea name="empl8" lenCheck="1000" style="height:30px;" id="empl8" class="form-control input-sm">${vo.empl8 }</textarea>
					<input type="hidden" name="emplScore8" value="${vo.emplScore8 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl9" >
				<th scope="row"><label for="empl9"><spring:message code="course.title.reshbank.item.empl"/>9</label></th>
				<td colspan="3">
					<textarea name="empl9" lenCheck="1000" style="height:30px;" id="empl9" class="form-control input-sm">${vo.empl9 }</textarea>
					<input type="hidden" name="emplScore9" value="${vo.emplScore9 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl10" >
				<th scope="row"><label for="empl10"><spring:message code="course.title.reshbank.item.empl"/>10</label></th>
				<td colspan="3">
					<textarea name="empl10" lenCheck="1000" style="height:30px;" id="empl10" class="form-control input-sm">${vo.empl10 }</textarea>
					<input type="hidden" name="emplScore10" value="${vo.emplScore10 }"/>
				</td>
			</tr>

			<tr class="itemViewTypeK empl empl11" >
				<th scope="row"><label for="empl11"><spring:message code="course.title.reshbank.item.empl"/>11</label></th>
				<td colspan="3">
					<textarea name="empl11" lenCheck="1000" style="height:30px;" id="empl11" class="form-control input-sm">${vo.empl11 }</textarea>
					<input type="hidden" name="emplScore11" value="${vo.emplScore11 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl12" >
				<th scope="row"><label for="empl12"><spring:message code="course.title.reshbank.item.empl"/>12</label></th>
				<td colspan="3">
					<textarea name="empl12" lenCheck="1000" style="height:30px;" id="empl12" class="form-control input-sm">${vo.empl12 }</textarea>
					<input type="hidden" name="emplScore12" value="${vo.emplScore12 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl13" >
				<th scope="row"><label for="empl13"><spring:message code="course.title.reshbank.item.empl"/>13</label></th>
				<td colspan="3">
					<textarea name="empl13" lenCheck="1000" style="height:30px;" id="empl13" class="form-control input-sm">${vo.empl13 }</textarea>
					<input type="hidden" name="emplScore13" value="${vo.emplScore13 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl14" >
				<th scope="row"><label for="empl14"><spring:message code="course.title.reshbank.item.empl"/>14</label></th>
				<td colspan="3">
					<textarea name="empl14" lenCheck="1000" style="height:30px;" id="empl14" class="form-control input-sm">${vo.empl14 }</textarea>
					<input type="hidden" name="emplScore14" value="${vo.emplScore14 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl15" >
				<th scope="row"><label for="empl15"><spring:message code="course.title.reshbank.item.empl"/>15</label></th>
				<td colspan="3">
					<textarea name="empl15" lenCheck="1000" style="height:30px;" id="empl15" class="form-control input-sm">${vo.empl15 }</textarea>
					<input type="hidden" name="emplScore15" value="${vo.emplScore15 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl16" >
				<th scope="row"><label for="empl16"><spring:message code="course.title.reshbank.item.empl"/>16</label></th>
				<td colspan="3">
					<textarea name="empl16" lenCheck="1000" style="height:30px;" id="empl16" class="form-control input-sm">${vo.empl16 }</textarea>
					<input type="hidden" name="emplScore16" value="${vo.emplScore16 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl17" >
				<th scope="row"><label for="empl17"><spring:message code="course.title.reshbank.item.empl"/>17</label></th>
				<td colspan="3">
					<textarea name="empl17" lenCheck="1000" style="height:30px;" id="empl17" class="form-control input-sm">${vo.empl17 }</textarea>
					<input type="hidden" name="emplScore17" value="${vo.emplScore17 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl18" >
				<th scope="row"><label for="empl18"><spring:message code="course.title.reshbank.item.empl"/>18</label></th>
				<td colspan="3">
					<textarea name="empl18" lenCheck="1000" style="height:30px;" id="empl18" class="form-control input-sm">${vo.empl18 }</textarea>
					<input type="hidden" name="emplScore18" value="${vo.emplScore18 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl19" >
				<th scope="row"><label for="empl19"><spring:message code="course.title.reshbank.item.empl"/>19</label></th>
				<td colspan="3">
					<textarea name="empl19" lenCheck="1000" style="height:30px;" id="empl19" class="form-control input-sm">${vo.empl19 }</textarea>
					<input type="hidden" name="emplScore19" value="${vo.emplScore19 }"/>
				</td>
			</tr>
			<tr class="itemViewTypeK empl empl20" >
				<th scope="row"><label for="empl20"><spring:message code="course.title.reshbank.item.empl"/>20</label></th>
				<td colspan="3">
					<textarea name="empl20" lenCheck="1000" style="height:30px;" id="empl20" class="form-control input-sm">${vo.empl20 }</textarea>
					<input type="hidden" name="emplScore20" value="${vo.emplScore20 }"/>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addItem()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:addItem()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:delItem()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	$(document).ready(function() {
		changeType();
	});

	function changeType() {
		var itemType = $("#reshItemTypeCd > option:selected").val();
		if(itemType == "K"){
			$(".itemViewTypeK").show();
			changeEmpl();
		}else{
			$(".itemViewTypeK").hide();
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listQstn();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 설문 문제 등록
	 */
	function addItem() {
		var itemType = $("#reshItemTypeCd > option:selected").val();
		var f = document.orgReshForm;
		if(isEmpty(f["reshItemCts"].value)) {
			alert('<spring:message code="course.message.reshbank.item.alert.input.desc"/>');
			return;
		}
		if(itemType == "K") {
			if(isEmpty(f["empl1"].value)) {
				alert("<spring:message code="course.message.reshbank.item.alert.input.empl"/>");
				return;
			}
			if(isEmpty(f["empl2"].value)) {
				alert("<spring:message code="course.message.reshbank.item.alert.input.empl"/>");
				return;
			}
			for(i=1 ; i<=20 ; i++) {
				if(jsByteLength(f["empl"+i].value) > 1000) {
					alert("<spring:message code="course.title.reshbank.item.empl"/> "+ i + "<spring:message code="course.message.reshbank.item.alert.input.emplsize"/>");
					return;
				}
			}
		}
		if(document.orgReshForm["reshItemSn"].value != "0")
			$('#orgReshForm').attr("action","/mng/org/research/editItem");
		else $('#orgReshForm').attr("action","/mng/org/research/addItem");
		
		var emplCnt = $("#emplCnt > option:selected").val() + 1;
		for(var i = emplCnt; i <= 20; i++) {
			$("#empl"+i).val("");
		}
		$('#orgReshForm').ajaxSubmit(processCallback);
	}


	/**
	 * 설문 문제 삭제
	 */
	function delItem() {
		if(confirm('<spring:message code="course.message.reshbank.item.confirm.delete"/>')) {
			$('#orgReshForm').attr("action","/mng/org/research/deleteItem");
			$('#orgReshForm').ajaxSubmit(processCallback);
		} else {
			return;
		}
	}

	function changeEmpl() {
		var cnt = $("#emplCnt > option:selected").val();
		var itemType = $("#reshItemTypeCd > option:selected").val();
		$(".empl").hide();
		if(itemType == 'K') {
			for(i=1; i<=cnt;i++) {
				$(".empl"+i).show();
			}
		}
	}

</script>
