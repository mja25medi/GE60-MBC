<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="orgReshItemVO" value="${vo}" />
	<form id="orgReshForm" name="orgReshForm" onsubmit="return false">
	<input type="hidden" name="reshSn" value="${vo.reshSn }" />
	<input type="hidden" name="itemOdr" value="${vo.itemOdr }" />
	<input type="hidden" name="reshItemSn" value="${vo.reshItemSn }" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }" />
	<input type="hidden" name="reshType" value="${vo.reshType }" />
	<input type="hidden" name="editorYn" value="Y" />
	<input type="hidden" id="etcNo" name="etcNo" value="${vo.etcNo }" />
	
	<p class="itemViewTypeD"><spring:message code="course.message.reshbank.itemd.maxlength.info"/></p>
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
					<c:set var="emplCnt" value="${orgReshItemVO.emplCnt}"/>
					<c:if test="${empty emplCnt}">
						<c:if test="${vo.reshType eq 'I'}">
							<c:set var="emplCnt" value="10"/>
						</c:if>
						<c:if test="${vo.reshType eq 'G'}">
							<c:set var="emplCnt" value="5"/>
						</c:if>					
					</c:if>
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
				<c:set var="fontFamily" value="Helvetica"/>
				<c:if test="${LOCALEKEY eq 'jp' }"><c:set var="fontFamily" value="Meiryo"/></c:if>
				<c:if test="${LOCALEKEY eq 'ko' }"><c:set var="fontFamily" value="맑은 고딕"/></c:if>
				<td colspan="4" style="padding:0px;font-family:${fontFamily}">
					<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${orgReshItemVO.reshItemCts}</div>
					<textarea name="reshItemCts" id="contentTextArea"  class="sr-only">${vo.reshItemCts }</textarea>
				</td>
			</tr>
				
		<tr class="itemViewTypeK empl empl1" >
			<th scope="row"><label for="empl1"><spring:message code="course.title.reshbank.item.empl"/>1</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2" >
				<div style="float: left;  width: 80%">
					<textarea name="empl1" lenCheck="1000" style="height:30px;" id="empl1" class="form-control input-sm" onblur="chkBeforeNull('1')">${vo.empl1 }</textarea>
				</div>
				<div style="float: left; margin-left: 15px;">
					<input type="checkbox" id="etcYn1" name="etcYn" value="1"> 기타 작성
				</div>
				</td>
				<td>
					<c:set var="emplScore1" value="${vo.emplScore1}"/>
					<select name="emplScore1" id="emplScore1" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore1 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore1 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore1 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore1 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore1 == 5}">selected</c:if>>5점</option>
					</select>
				</td>					
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl1" lenCheck="1000" style="height:30px;" id="empl1" class="form-control input-sm" onblur="chkBeforeNull('1')">${vo.empl1 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn1" name="etcYn" value="1"> 기타 작성
					</div>
					<input type="hidden" name="emplScore1" value="${vo.emplScore1 }"/>
				</td>				
			</c:if>					
		</tr>
		
		<tr class="itemViewTypeK empl empl2" >
			<th scope="row"><label for="empl2"><spring:message code="course.title.reshbank.item.empl"/>2</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl2" lenCheck="1000" style="height:30px;" id="empl2" class="form-control input-sm" onblur="chkBeforeNull('2')">${vo.empl2 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn2" name="etcYn" value="2"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore2" value="${vo.emplScore2}"/>
					<select name="emplScore2" id="emplScore2" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore2 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore2 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore2 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore2 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore2 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl2" lenCheck="1000" style="height:30px;" id="empl2" class="form-control input-sm" onblur="chkBeforeNull('2')">${vo.empl2 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn2" name="etcYn" value="2"> 기타 작성
					</div>
					<input type="hidden" name="emplScore2" value="${vo.emplScore2 }"/>
				</td>
			</c:if>	
		</tr>
		
		
		<tr class="itemViewTypeK empl empl3" >
			<th scope="row"><label for="empl3"><spring:message code="course.title.reshbank.item.empl"/>3</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl3" lenCheck="1000" style="height:30px;" id="empl3" class="form-control input-sm" onblur="chkBeforeNull('3')">${vo.empl3 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn3" name="etcYn" value="3"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore3" value="${vo.emplScore3}"/>
					<select name="emplScore3" id="emplScore3" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore3 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore3 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore3 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore3 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore3 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl3" lenCheck="1000" style="height:30px;" id="empl3" class="form-control input-sm" onblur="chkBeforeNull('3')">${vo.empl3 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn3" name="etcYn" value="3"> 기타 작성
					</div>
					<input type="hidden" name="emplScore3" value="${vo.emplScore3 }"/>
				</td>				
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl4" >
			<th scope="row"><label for="empl4"><spring:message code="course.title.reshbank.item.empl"/>4</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl4" lenCheck="1000" style="height:30px;" id="empl4" class="form-control input-sm" onblur="chkBeforeNull('4')">${vo.empl4 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn4" name="etcYn" value="4"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore4" value="${vo.emplScore4}"/>
					<select name="emplScore4" id="emplScore4" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore4 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore4 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore4 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore4 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore4 == 5}">selected</c:if>>5점</option>
					</select>
				</td>	
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl4" lenCheck="1000" style="height:30px;" id="empl4" class="form-control input-sm" onblur="chkBeforeNull('4')">${vo.empl4 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn4" name="etcYn" value="4"> 기타 작성
					</div>
					<input type="hidden" name="emplScore4" value="${vo.emplScore4 }"/>
				</td>									
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl5" >
			<th scope="row"><label for="empl5"><spring:message code="course.title.reshbank.item.empl"/>5</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">	
						<textarea name="empl5" lenCheck="1000" style="height:30px;" id="empl5" class="form-control input-sm" onblur="chkBeforeNull('5')">${vo.empl5 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn5" name="etcYn" value="5"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore5" value="${vo.emplScore5}"/>
					<select name="emplScore5" id="emplScore5" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore5 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore5 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore5 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore5 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore5 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl5" lenCheck="1000" style="height:30px;" id="empl5" class="form-control input-sm" onblur="chkBeforeNull('5')">${vo.empl5 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn5" name="etcYn" value="5"> 기타 작성
					</div>
					<input type="hidden" name="emplScore5" value="${vo.emplScore5 }"/>
				</td>					
			</c:if>	
		</tr>	
		<tr class="itemViewTypeK empl empl6" >
			<th scope="row"><label for="empl6"><spring:message code="course.title.reshbank.item.empl"/>6</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">		
						<textarea name="empl6" lenCheck="1000" style="height:30px;" id="empl6" class="form-control input-sm" onblur="chkBeforeNull('6')">${vo.empl6 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn6" name="etcYn" value="6"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore6" value="${vo.emplScore6}"/>
					<select name="emplScore6" id="emplScore6" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore6 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore6 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore6 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore6 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore6 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl6" lenCheck="1000" style="height:30px;" id="empl6" class="form-control input-sm" onblur="chkBeforeNull('6')">${vo.empl6 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn6" name="etcYn" value="6"> 기타 작성
					</div>
					<input type="hidden" name="emplScore6" value="${vo.emplScore6 }"/>
				</td>						
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl7" >
			<th scope="row"><label for="empl7"><spring:message code="course.title.reshbank.item.empl"/>7</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">	
						<textarea name="empl7" lenCheck="1000" style="height:30px;" id="empl7" class="form-control input-sm" onblur="chkBeforeNull('7')">${vo.empl7 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn7" name="etcYn" value="7"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore7" value="${vo.emplScore7}"/>
					<select name="emplScore7" id="emplScore7" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore7 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore7 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore7 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore7 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore7 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl7" lenCheck="1000" style="height:30px;" id="empl7" class="form-control input-sm" onblur="chkBeforeNull('7')">${vo.empl7 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn7" name="etcYn" value="7"> 기타 작성
					</div>
					<input type="hidden" name="emplScore7" value="${vo.emplScore7 }"/>
				</td>	
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl8" >
			<th scope="row"><label for="empl8"><spring:message code="course.title.reshbank.item.empl"/>8</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">	
						<textarea name="empl8" lenCheck="1000" style="height:30px;" id="empl8" class="form-control input-sm" onblur="chkBeforeNull('8')">${vo.empl8 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn8" name="etcYn" value="8"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore8" value="${vo.emplScore8}"/>
					<select name="emplScore8" id="emplScore8" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore8 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore8 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore8 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore8 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore8 == 5}">selected</c:if>>5점</option>
					</select>
				</td>	
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl8" lenCheck="1000" style="height:30px;" id="empl8" class="form-control input-sm" onblur="chkBeforeNull('8')">${vo.empl8 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn8" name="etcYn" value="8"> 기타 작성
					</div>
					<input type="hidden" name="emplScore8" value="${vo.emplScore8 }"/>
				</td>
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl9" >
			<th scope="row"><label for="empl9"><spring:message code="course.title.reshbank.item.empl"/>9</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">	
						<textarea name="empl9" lenCheck="1000" style="height:30px;" id="empl9" class="form-control input-sm" onblur="chkBeforeNull('9')">${vo.empl9 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn9" name="etcYn" value="9"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore9" value="${vo.emplScore9}"/>
					<select name="emplScore9" id="emplScore9" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore9 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore9 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore9 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore9 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore9 == 5}">selected</c:if>>5점</option>
					</select>
				</td>	
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl9" lenCheck="1000" style="height:30px;" id="empl9" class="form-control input-sm" onblur="chkBeforeNull('9')">${vo.empl9 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn9" name="etcYn" value="9"> 기타 작성
					</div>
					<input type="hidden" name="emplScore9" value="${vo.emplScore9 }"/>
				</td>	
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl10" >
			<th scope="row"><label for="empl10"><spring:message code="course.title.reshbank.item.empl"/>10</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">	
						<textarea name="empl10" lenCheck="1000" style="height:30px;" id="empl10" class="form-control input-sm" onblur="chkBeforeNull('10')">${vo.empl10 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn10" name="etcYn" value="10"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore10" value="${vo.emplScore10}"/>
					<select name="emplScore10" id="emplScore10" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore10 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore10 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore10 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore10 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore10 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl10" lenCheck="1000" style="height:30px;" id="empl10" class="form-control input-sm" onblur="chkBeforeNull('10')">${vo.empl10 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn10" name="etcYn" value="10"> 기타 작성
					</div>
					<input type="hidden" name="emplScore10" value="${vo.emplScore10 }"/>
				</td>	
			</c:if>					
		</tr>

		<tr class="itemViewTypeK empl empl11" >
			<th scope="row"><label for="empl11"><spring:message code="course.title.reshbank.item.empl"/>11</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">	
						<textarea name="empl11" lenCheck="1000" style="height:30px;" id="empl11" class="form-control input-sm" onblur="chkBeforeNull('11')">${vo.empl11 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn11" name="etcYn" value="11"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore11" value="${vo.emplScore11}"/>
					<select name="emplScore11" id="emplScore11" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore11 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore11 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore11 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore11 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore11 == 5}">selected</c:if>>5점</option>
					</select>
				</td>	
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl11" lenCheck="1000" style="height:30px;" id="empl11" class="form-control input-sm" onblur="chkBeforeNull('11')">${vo.empl11 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn11" name="etcYn" value="11"> 기타 작성
					</div>
					<input type="hidden" name="emplScore11" value="${vo.emplScore11 }"/>
				</td>
			</c:if>					
		</tr>
		
		
		<tr class="itemViewTypeK empl empl12" >
			<th scope="row"><label for="empl12"><spring:message code="course.title.reshbank.item.empl"/>12</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl12" lenCheck="1000" style="height:30px;" id="empl12" class="form-control input-sm" onblur="chkBeforeNull('12')">${vo.empl12 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn12" name="etcYn" value="12"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore12" value="${vo.emplScore12}"/>
					<select name="emplScore12" id="emplScore12" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore12 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore12 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore12 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore12 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore12 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl12" lenCheck="1000" style="height:30px;" id="empl12" class="form-control input-sm" onblur="chkBeforeNull('12')">${vo.empl12 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn12" name="etcYn" value="12"> 기타 작성
					</div>
					<input type="hidden" name="emplScore12" value="${vo.emplScore12 }"/>
				</td>		
			</c:if>	
		</tr>
		
		
		<tr class="itemViewTypeK empl empl13" >
			<th scope="row"><label for="empl13"><spring:message code="course.title.reshbank.item.empl"/>13</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl13" lenCheck="1000" style="height:30px;" id="empl13" class="form-control input-sm" onblur="chkBeforeNull('13')">${vo.empl13 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn13" name="etcYn" value="13"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore13" value="${vo.emplScore13}"/>
					<select name="emplScore13" id="emplScore13" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore13 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore13 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore13 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore13 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore13 == 5}">selected</c:if>>5점</option>
					</select>
				</td>	
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl13" lenCheck="1000" style="height:30px;" id="empl13" class="form-control input-sm" onblur="chkBeforeNull('13')">${vo.empl13 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn13" name="etcYn" value="13"> 기타 작성
					</div>
					<input type="hidden" name="emplScore13" value="${vo.emplScore13 }"/>
				</td>
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl14" >
			<th scope="row"><label for="empl14"><spring:message code="course.title.reshbank.item.empl"/>14</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl14" lenCheck="1000" style="height:30px;" id="empl14" class="form-control input-sm" onblur="chkBeforeNull('14')">${vo.empl14 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn14" name="etcYn" value="14"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore14" value="${vo.emplScore14}"/>
					<select name="emplScore14" id="emplScore14" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore14 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore14 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore14 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore14 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore14 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl14" lenCheck="1000" style="height:30px;" id="empl14" class="form-control input-sm" onblur="chkBeforeNull('14')">${vo.empl14 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn14" name="etcYn" value="14"> 기타 작성
					</div>
					<input type="hidden" name="emplScore14" value="${vo.emplScore14 }"/>
				</td>
			</c:if>						
		</tr>
		
		<tr class="itemViewTypeK empl empl15" >
			<th scope="row"><label for="empl15"><spring:message code="course.title.reshbank.item.empl"/>15</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl15" lenCheck="1000" style="height:30px;" id="empl15" class="form-control input-sm" onblur="chkBeforeNull('15')">${vo.empl15 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn15" name="etcYn" value="15"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore15" value="${vo.emplScore15}"/>
					<select name="emplScore15" id="emplScore15" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore15 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore15 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore15 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore15 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore15 == 5}">selected</c:if>>5점</option>
					</select>
					
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl15" lenCheck="1000" style="height:30px;" id="empl15" class="form-control input-sm" onblur="chkBeforeNull('15')">${vo.empl15 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn15" name="etcYn" value="15"> 기타 작성
					</div>
					<input type="hidden" name="emplScore15" value="${vo.emplScore15 }"/>
				</td>
			</c:if>						
		</tr>


		<tr class="itemViewTypeK empl empl16" >
			<th scope="row"><label for="empl16"><spring:message code="course.title.reshbank.item.empl"/>16</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl16" lenCheck="1000" style="height:30px;" id="empl16" class="form-control input-sm" onblur="chkBeforeNull('16')">${vo.empl16 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn16" name="etcYn" value="16"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore16" value="${vo.emplScore16}"/>
					<select name="emplScore16" id="emplScore16" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore16 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore16 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore16 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore16 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore16 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl16" lenCheck="1000" style="height:30px;" id="empl16" class="form-control input-sm" onblur="chkBeforeNull('16')">${vo.empl16 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn16" name="etcYn" value="16"> 기타 작성
					</div>
					<input type="hidden" name="emplScore16" value="${vo.emplScore16 }"/>
				</td>
			</c:if>					
		</tr>
		
		
		<tr class="itemViewTypeK empl empl17" >
			<th scope="row"><label for="empl17"><spring:message code="course.title.reshbank.item.empl"/>17</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl17" lenCheck="1000" style="height:30px;" id="empl17" class="form-control input-sm" onblur="chkBeforeNull('17')">${vo.empl17 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn17" name="etcYn" value="17"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore17" value="${vo.emplScore17}"/>
					<select name="emplScore17" id="emplScore17" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore17 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore17 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore17 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore17 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore17 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl17" lenCheck="1000" style="height:30px;" id="empl17" class="form-control input-sm" onblur="chkBeforeNull('17')">${vo.empl17 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn17" name="etcYn" value="17"> 기타 작성
					</div>
					<input type="hidden" name="emplScore17" value="${vo.emplScore17 }"/>
				</td>	
			</c:if>						
		</tr>
		
		
		<tr class="itemViewTypeK empl empl18" >
			<th scope="row"><label for="empl18"><spring:message code="course.title.reshbank.item.empl"/>18</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl18" lenCheck="1000" style="height:30px;" id="empl18" class="form-control input-sm" onblur="chkBeforeNull('18')">${vo.empl18 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn18" name="etcYn" value="18"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore18" value="${vo.emplScore18}"/>
					<select name="emplScore18" id="emplScore18" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore18 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore18 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore18 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore18 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore18 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl18" lenCheck="1000" style="height:30px;" id="empl18" class="form-control input-sm" onblur="chkBeforeNull('18')">${vo.empl18 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn18" name="etcYn" value="18"> 기타 작성
					</div>
					<input type="hidden" name="emplScore18" value="${vo.emplScore18 }"/>
				</td>	
			</c:if>	
		</tr>
		
		
		<tr class="itemViewTypeK empl empl19" >
			<th scope="row"><label for="empl19"><spring:message code="course.title.reshbank.item.empl"/>19</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl19" lenCheck="1000" style="height:30px;" id="empl19" class="form-control input-sm" onblur="chkBeforeNull('19')">${vo.empl19 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn19" name="etcYn" value="19"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore19" value="${vo.emplScore19}"/>
					<select name="emplScore19" id="emplScore19" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore19 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore19 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore19 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore19 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore19 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl19" lenCheck="1000" style="height:30px;" id="empl19" class="form-control input-sm" onblur="chkBeforeNull('19')">${vo.empl19 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn19" name="etcYn" value="19"> 기타 작성
					</div>
					<input type="hidden" name="emplScore19" value="${vo.emplScore19 }"/>
				</td>		
			</c:if>	
		</tr>
		
		
		<tr class="itemViewTypeK empl empl20" >
			<th scope="row"><label for="empl20"><spring:message code="course.title.reshbank.item.empl"/>20</label></th>
			<c:if test="${vo.reshType eq 'I' }">
				<td colspan="2">
					<div style="float: left;  width: 80%">
						<textarea name="empl20" lenCheck="1000" style="height:30px;" id="empl20" class="form-control input-sm" onblur="chkBeforeNull('20')">${vo.empl20 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn20" name="etcYn" value="20"> 기타 작성
					</div>
				</td>
				<td>
					<c:set var="emplScore20" value="${vo.emplScore20}"/>
					<select name="emplScore20" id="emplScore20" class="form-control input-sm" >
						<option value="1" <c:if test="${emplScore20 == 1}">selected</c:if>>1점</option>
						<option value="2" <c:if test="${emplScore20 == 2}">selected</c:if>>2점</option>
						<option value="3" <c:if test="${emplScore20 == 3}">selected</c:if>>3점</option>
						<option value="4" <c:if test="${emplScore20 == 4}">selected</c:if>>4점</option>
						<option value="5" <c:if test="${emplScore20 == 5}">selected</c:if>>5점</option>
					</select>
				</td>
			</c:if>
			<c:if test="${vo.reshType eq 'G' }">
				<td colspan="3">
					<div style="float: left;  width: 80%">
						<textarea name="empl20" lenCheck="1000" style="height:30px;" id="empl20" class="form-control input-sm" onblur="chkBeforeNull('20')">${vo.empl20 }</textarea>
					</div>
					<div style="float: left; margin-left: 15px;">
						<input type="checkbox" id="etcYn20" name="etcYn" value="20"> 기타 작성
					</div>
					<input type="hidden" name="emplScore20" value="${vo.emplScore20 }"/>
				</td>	
			</c:if>						
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
	var summernote;
	$(document).ready(function() {
		changeType();
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"RESH_BANK",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"350px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
		});
		
		var etcNo = "${vo.etcNo}";
		$("#etcYn"+etcNo).prop("checked", true);
		
		$("input[name='etcYn']").on("click", function () {

			var selectedEtcNo = $("#etcNo").val();
			var thisEtcNo = $(this).val();
		    
			if(selectedEtcNo == thisEtcNo){
				$("#etcNo").val("");
				$("#etcYn"+thisEtcNo).prop("checked", false);
			}else{
				$("#etcNo").val(thisEtcNo);
				$("input[name='etcYn']").prop("checked", false);
				$("#etcYn"+thisEtcNo).prop("checked", true);
			}
			
		});
		
	});

	function changeType() {
		var itemType = $("#reshItemTypeCd > option:selected").val();
		if(itemType == "K"){
			$(".itemViewTypeK").show();
			$(".itemViewTypeD").hide();
			changeEmpl();
		}else{
			$(".itemViewTypeK").hide();
			$(".itemViewTypeD").show();
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
		if(!validate(document.orgReshForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		var itemType = $("#reshItemTypeCd > option:selected").val();
		var f = document.orgReshForm;
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
			
			var cnt = parseInt($('#emplCnt').val(),10);
			var chkCnt = 0;
			for(i=1; i<=cnt; i++){
				if(isEmpty($('#empl'+(i)).val())) {
					alert('<spring:message code="course.title.reshbank.message.empl.error" arguments="' + i + '"/>');
					$('#empl'+i).focus();
					return;
				}

			}
		}
		if(document.orgReshForm["reshItemSn"].value != "0")
			$('#orgReshForm').attr("action","/mng/org/research/editItem");
		else $('#orgReshForm').attr("action","/mng/org/research/addItem");
		
		var emplCnt = parseInt($("#emplCnt > option:selected").val()) + 1;
		for(var i = emplCnt; i <= 20; i++) {
			$("#empl"+i).val("");
		}

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);

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

	function chkBeforeNull(chk_no){
/* 		if(Number(chk_no) != 1){
			if($("#empl"+Number(chk_no)).val() != "" && $("#empl"+(Number(chk_no)-1)).val() == ""){
				alert("<spring:message code="course.message.reshbank.item.alert.input.order"/>");
				$("#empl"+(Number(chk_no)-1)).focus();
			}
		}

		if($("#empl"+Number(chk_no)).val() == "" && $("#empl"+(Number(chk_no)+1)).val() != ""){
			alert("<spring:message code="course.message.reshbank.item.alert.input.order"/>");
			$("#empl"+Number(chk_no)).focus();
		} */
	}

</script>
