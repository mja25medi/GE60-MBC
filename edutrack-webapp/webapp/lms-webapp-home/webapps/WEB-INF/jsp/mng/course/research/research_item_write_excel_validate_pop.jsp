<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="researchBankItemVO" value="${vo}" />

	<form name="Validate" id="Validate" onsubmit="return false;" method="post" >
	<input type="hidden" name="reshSn" id="reshSn" value="${researchBankItemVO.reshSn}" />

	<div class="text-right" style="width:100%">
		<a href="javascript:saveResearchBank()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<div class="table-container" style="height:500px; margin-top:10px; ">
		<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak" style="margin-bottom:0px; font-size: 12px;">
			<colgroup>
				<col style="width:30px"/>
				<col style="width:50px"/>
				<col style="width:100px;"/>
				<col style="width:100px"/>
				<col style="width:80px"/>
				<col style="width:auto"/>
				<col style="width:200px;"/>
				<col style="width:60px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
					<th scope="col"><spring:message code="common.title.no"/> </th>
					<th scope="col"><spring:message code="course.title.exambank.qstntype"/> </th>
					<th scope="col"><spring:message code="course.title.reshbank.item.view"/></th>
					<th scope="col"><spring:message code="course.title.reshbank.item.emplcnt"/></th>
					<th scope="col"><spring:message code="course.title.reshbank.item.reshitemcts.excel"/></th>
					<th scope="col"><spring:message code="course.title.reshbank.item.result"/></th>
					<th scope="col"><spring:message code="common.title.edit"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${researchBankList}" varStatus="status">
				<tr>
					<td>
						<input type="checkbox" name="chk" id="chk_${item.lineNo}" value="${item.lineNo}" <c:if test="${not empty item.errorCode }">disabled</c:if>>
					</td>
					<td class="text-center">
						${(status.index+1)}
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'RESHITEMTYPECD')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="reshItemTypeCd-view-${item.lineNo}">
							<c:if test="${item.reshItemTypeCd eq 'K'}"><spring:message code="course.title.reshbank.item.type.k"/></c:if>
							<c:if test="${item.reshItemTypeCd eq 'D'}"><spring:message code="course.title.reshbank.item.type.d"/></c:if>
							<c:if test="${item.reshItemTypeCd ne 'K' and item.reshItemTypeCd ne 'D'}">${item.reshItemTypeCd}</c:if>
						</span>
						<select name="reshItemTypeCd" id="reshItemTypeCd-edit-${item.lineNo}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none" onchange="changereshItemType('${item.lineNo}');">
							<option value="K" <c:if test="${item.reshItemTypeCd eq 'K'}">selected</c:if> ><spring:message code="course.title.reshbank.item.type.k"/></option>
							<option value="D" <c:if test="${item.reshItemTypeCd eq 'D'}">selected</c:if>><spring:message code="course.title.reshbank.item.type.d"/></option>
						</select>
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPLVIEWTYPE')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="emplViewType-view-${item.lineNo}">
							<c:if test="${item.emplViewType eq 'W'}"><spring:message code="course.title.reshbank.item.view.w"/></c:if>
							<c:if test="${item.emplViewType eq 'H'}"><spring:message code="course.title.reshbank.item.view.h"/></c:if>
							<c:if test="${item.emplViewType ne 'H' and item.emplViewType ne 'W'}">${item.emplViewType}</c:if>
						</span>
						<select name="emplViewType" id="emplViewType-edit-${item.lineNo}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none">
							<option value="W" <c:if test="${item.emplViewType eq 'W'}">selected</c:if> ><spring:message code="course.title.reshbank.item.view.w"/></option>
							<option value="H" <c:if test="${item.emplViewType eq 'H'}">selected</c:if>><spring:message code="course.title.reshbank.item.view.h"/></option>
						</select>
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPLCNT')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="emplCnt-view-${item.lineNo}">${item.emplCnt}</span>
						<select name=emplCnt id="emplCnt-edit-${item.lineNo}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none" onchange="changeEmpl('${item.lineNo}')">
							<option value="1" <c:if test="${item.emplCnt eq '1'}">selected</c:if> >1</option>
							<option value="2" <c:if test="${item.emplCnt eq '2'}">selected</c:if>>2</option>
							<option value="3" <c:if test="${item.emplCnt eq '3'}">selected</c:if>>3</option>
							<option value="4" <c:if test="${item.emplCnt eq '4'}">selected</c:if>>4</option>
							<option value="5" <c:if test="${item.emplCnt eq '5'}">selected</c:if>>5</option>
							<option value="6" <c:if test="${item.emplCnt eq '6'}">selected</c:if> >6</option>
							<option value="7" <c:if test="${item.emplCnt eq '7'}">selected</c:if>>7</option>
							<option value="8" <c:if test="${item.emplCnt eq '8'}">selected</c:if>>8</option>
							<option value="9" <c:if test="${item.emplCnt eq '9'}">selected</c:if>>9</option>
							<option value="10" <c:if test="${item.emplCnt eq '10'}">selected</c:if>>10</option>
							<option value="11" <c:if test="${item.emplCnt eq '11'}">selected</c:if> >11</option>
							<option value="12" <c:if test="${item.emplCnt eq '12'}">selected</c:if>>12</option>
							<option value="13" <c:if test="${item.emplCnt eq '13'}">selected</c:if>>13</option>
							<option value="14" <c:if test="${item.emplCnt eq '14'}">selected</c:if>>14</option>
							<option value="15" <c:if test="${item.emplCnt eq '15'}">selected</c:if>>15</option>
							<option value="16" <c:if test="${item.emplCnt eq '16'}">selected</c:if> >16</option>
							<option value="17" <c:if test="${item.emplCnt eq '17'}">selected</c:if>>17</option>
							<option value="18" <c:if test="${item.emplCnt eq '18'}">selected</c:if>>18</option>
							<option value="19" <c:if test="${item.emplCnt eq '19'}">selected</c:if>>19</option>
							<option value="20" <c:if test="${item.emplCnt eq '20'}">selected</c:if>>20</option>
						</select>
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'RESHITEMCTS')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="reshItemCts-view-${item.lineNo}">${item.reshItemCts}</span>
						<textarea name="reshItemCts" id="reshItemCts-edit-${item.lineNo}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none">${item.reshItemCts}</textarea>
						<c:set var="styleStr" value="float:left;margin-left:20px;"/>
						<c:set var="displayStr" value=""/>
						<c:if test="${item.emplViewType eq 'H' }"><c:set var="styleStr" value="margin-left:20px;"/></c:if>
						<c:if test="${item.reshItemTypeCd eq 'D'}"><c:set var="displayStr" value="display: none;"/></c:if>
						<c:if test="${empty item.reshItemTypeCd }"><c:set var="displayStr" value="display: none;"/></c:if>
						<ul class="value-view-${item.lineNo}" style="list-style-type: none; padding-left:0px; ${displayStr}">
							<li class="value-li-${item.lineNo}-1 li-empl-${item.lineNo}" style="${styleStr}" id="empl1-view-${item.lineNo}">
								<b><spring:message code="course.title.reshbank.item.empl"/>1</b> : ${item.empl1}</li>
							<li class="value-li-${item.lineNo}-2 li-empl-${item.lineNo}" style="${styleStr}" id="empl2-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>2</b> : ${item.empl2}</li>
							<li class="value-li-${item.lineNo}-3 li-empl-${item.lineNo}" style="${styleStr}" id="empl3-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>3</b> : ${item.empl3}</li>
							<li class="value-li-${item.lineNo}-4 li-empl-${item.lineNo}" style="${styleStr}" id="empl4-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>4</b> : ${item.empl4}</li>
							<li class="value-li-${item.lineNo}-5 li-empl-${item.lineNo}" style="${styleStr}" id="empl5-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>5</b> : ${item.empl5}</li>
							<li class="value-li-${item.lineNo}-6 li-empl-${item.lineNo}" style="${styleStr}" id="empl6-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>6</b> : ${item.empl6}</li>
							<li class="value-li-${item.lineNo}-7 li-empl-${item.lineNo}" style="${styleStr}" id="empl7-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>7</b> : ${item.empl7}</li>
							<li class="value-li-${item.lineNo}-8 li-empl-${item.lineNo}" style="${styleStr}" id="empl8-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>8</b> : ${item.empl8}</li>
							<li class="value-li-${item.lineNo}-9 li-empl-${item.lineNo}" style="${styleStr}" id="empl9-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>9</b> : ${item.empl9}</li>
							<li class="value-li-${item.lineNo}-10 li-empl-${item.lineNo}" style="${styleStr}" id="empl10-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>10</b> : ${item.empl10}</li>
							<li class="value-li-${item.lineNo}-11 li-empl-${item.lineNo}" style="${styleStr}" id="empl11-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>11</b> : ${item.empl11}</li>
							<li class="value-li-${item.lineNo}-12 li-empl-${item.lineNo}" style="${styleStr}" id="empl12-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>12</b> : ${item.empl12}</li>
							<li class="value-li-${item.lineNo}-13 li-empl-${item.lineNo}" style="${styleStr}" id="empl13-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>13</b> : ${item.empl13}</li>
							<li class="value-li-${item.lineNo}-14 li-empl-${item.lineNo}" style="${styleStr}" id="empl14-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>14</b> : ${item.empl14}</li>
							<li class="value-li-${item.lineNo}-15 li-empl-${item.lineNo}" style="${styleStr}" id="empl15-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>15</b> : ${item.empl15}</li>
							<li class="value-li-${item.lineNo}-16 li-empl-${item.lineNo}" style="${styleStr}" id="empl16-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>16</b> : ${item.empl16}</li>
							<li class="value-li-${item.lineNo}-17 li-empl-${item.lineNo}" style="${styleStr}" id="empl17-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>17</b> : ${item.empl17}</li>
							<li class="value-li-${item.lineNo}-18 li-empl-${item.lineNo}" style="${styleStr}" id="empl18-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>18</b> : ${item.empl18}</li>
							<li class="value-li-${item.lineNo}-19 li-empl-${item.lineNo}" style="${styleStr}" id="empl19-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>19</b> : ${item.empl19}</li>
							<li class="value-li-${item.lineNo}-20 li-empl-${item.lineNo}" style="${styleStr}" id="empl20-view-${item.lineNo}"><b><spring:message code="course.title.reshbank.item.empl"/>20</b> : ${item.empl20}</li>
						</ul>
						<script type="text/javascript">
							<c:forEach var="i" begin="${item.emplCnt}" end="20" >
								$(".value-li-${item.lineNo}-${(i+1)}").hide();
							</c:forEach>
						</script>
						<div class="value-edit-${item.lineNo}" style="display:none;margin-top: 5px; ">
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL1')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-1 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>1
								<textarea name="empl1" id="empl1-edit-${item.lineNo}"  class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl1}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL2')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-2 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>2
								<textarea name="empl2" id="empl2-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl2}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL3')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-3 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>3
								<textarea name="empl3" id="empl3-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl3}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL4')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-4 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>4
								<textarea name="empl4" id="empl4-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl4}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL5')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-5 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>5
								<textarea name="empl5" id="empl5-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl5}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL6')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-6 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>6
								<textarea name="empl6" id="empl6-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl6}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL7')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-7 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>7
								<textarea name="empl7" id="empl7-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl7}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL8')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-8 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>8
								<textarea name="empl8" id="empl8-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl8}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL9')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-9 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>9
								<textarea name="empl9" id="empl9-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl9}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL10')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-10 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>10
								<textarea name="empl10" id="empl10-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl10}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL11')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-11 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>11
								<textarea name="empl11" id="empl11-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl11}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL12')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-12 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>12
								<textarea name="empl12" id="empl12-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl12}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL13')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-13 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>13
								<textarea name="empl13" id="empl13-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl13}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL14')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-14 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>14
								<textarea name="empl14" id="empl14-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl14}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL15')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-15 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>15
								<textarea name="empl15" id="empl15-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000" >${item.empl15}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL16')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-16 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>16
								<textarea name="empl16" id="empl16-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl16}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL17')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-17 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>17
								<textarea name="empl17" id="empl17-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl17}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL18')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-18 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>18
								<textarea name="empl18" id="empl18-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl18}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL19')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-19 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>19
								<textarea name="empl19" id="empl19-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl19}</textarea>
							</p>
							<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'EMPL20')}"><c:set var="errorCss" value="error"/></c:if>
							<p class="value-p-${item.lineNo}-20 ${errorCss} empl-${item.lineNo}">
								<spring:message code="course.title.reshbank.item.empl"/>20
								<textarea name="empl20" id="empl20-edit-${item.lineNo}" class="value-edit-${item.lineNo} input-sm" style="display:none;width:80%;height:30px;border: 1px solid #ccc;" maxlength="1000">${item.empl20}</textarea>
							</p>
						</div>
						<script type="text/javascript">
							<c:forEach var="i" begin="${item.emplCnt}" end="20" >
								//$('.value-p-${item.lineNo}-${(i+1)}').remove();
								$('#empl${(i+1)}-edit-${item.lineNo}').val("");
							</c:forEach>
						</script>
					</td>
					<td>
						<c:set var="errColor" value="color: blue" />
						<c:if test="${item.errorCode ne null && item.errorCode ne ''}"><c:set var="errColor" value="color: red" /></c:if>
						<c:if test="${item.errorCode eq null && item.errorCode eq ''}"><c:set var="errColor" value="color: blue" /></c:if>
						<p id="errcodeArea_${item.lineNo}" style="${errColor}">
							<c:choose>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'RESHITEMTYPECD'}"><spring:message code="course.title.reshbank.message.reshitemtypecd.error"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPLVIEWTYPE'}"><spring:message code="course.title.reshbank.message.emplviewtype.error"/> </c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'RESHITEMCTS'}"><spring:message code="course.title.reshbank.message.reshitemcts.error"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPLCNT'}"><spring:message code="course.title.reshbank.message.emplcnt.error"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL1'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="1"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL2'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="2"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL3'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="3"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL4'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="4"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL5'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="5"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL6'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="6"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL7'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="7"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL8'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="8"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL9'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="9"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL10'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="10"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL11'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="11"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL12'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="12"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL13'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="13"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL14'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="14"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL15'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="15"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL16'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="16"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL17'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="17"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL18'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="18"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL19'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="19"/></c:when>
								<c:when test="${fn:split(item.errorCode,'|')[0] eq 'EMPL20'}"><spring:message code="course.title.reshbank.message.empl.error"  arguments="20"/></c:when>
								<c:otherwise><spring:message code="course.title.reshbank.message.no.error"/></c:otherwise>
							</c:choose>
						</p>
						<input type="hidden" name="lineNo" id="lineNo_${item.lineNo}" value="${item.lineNo}" />
						<input type="hidden" name="errorCode" id="errorCode_${item.lineNo}" value="${item.errorCode}" />
					</td>
					<td class="text-center">
						<a id="editBtn_${item.lineNo}" href="javascript:editLine('${item.lineNo}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
						<a id="checkBtn_${item.lineNo}" href="javascript:checkLine('${item.lineNo}')" class="btn btn-warning btn-sm" style="display:none"><spring:message code="button.ok"/> </a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty researchBankList }">
				<tr>
					<td colspan="9"L><spring:message code="user.message.userinfo.nodata"/></td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div>
	</form>
	<form id="researchBankForm" name="researchBankForm" onsubmit="return false">
	<input type="hidden" name="lineNo" value="" id="lineNo" />
	<input type="hidden" name="reshItemTypeCd" value="" id="reshItemTypeCd" />
	<input type="hidden" name="emplViewType" value="" id="emplViewType" />
	<input type="hidden" name="emplCnt" value="" id="emplCnt" />
	<input type="hidden" name="reshItemCts" value="" id="reshItemCts" />
	<input type="hidden" name="empl1" value="" id="empl1" />
	<input type="hidden" name="empl2" value="" id="empl2" />
	<input type="hidden" name="empl3" value="" id="empl3" />
	<input type="hidden" name="empl4" value="" id="empl4" />
	<input type="hidden" name="empl5" value="" id="empl5" />
	<input type="hidden" name="empl6" value="" id="empl6" />
	<input type="hidden" name="empl7" value="" id="empl7" />
	<input type="hidden" name="empl8" value="" id="empl8" />
	<input type="hidden" name="empl9" value="" id="empl9" />
	<input type="hidden" name="empl10" value="" id="empl10" />
	<input type="hidden" name="empl11" value="" id="empl11" />
	<input type="hidden" name="empl12" value="" id="empl12" />
	<input type="hidden" name="empl13" value="" id="empl13" />
	<input type="hidden" name="empl14" value="" id="empl14" />
	<input type="hidden" name="empl15" value="" id="empl15" />
	<input type="hidden" name="empl16" value="" id="empl16" />
	<input type="hidden" name="empl17" value="" id="empl17" />
	<input type="hidden" name="empl18" value="" id="empl18" />
	<input type="hidden" name="empl19" value="" id="empl19" />
	<input type="hidden" name="empl20" value="" id="empl20" />
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		parent.modalBox.resize(1100,600);
	});

	function editLine(lineNo) {
		// unchecked and disabled checkbox
		$("#chk_"+lineNo).attr("checked", false);
		$("#chk_"+lineNo).attr("disabled", true);

		$("#editBtn_"+lineNo).hide();
		$("#checkBtn_"+lineNo).show();

		$(".value-view-"+lineNo).hide();
		$(".value-edit-"+lineNo).show();
		changereshItemType(lineNo);
		changeEmpl(lineNo);
	}

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	    	$("#Validate input[name='chk']:enabled").prop({checked:true});
	    }else{
	    	$("#Validate input[name='chk']:enabled").prop({checked:false});
	    }
	}


	function checkLine(lineNo) {

		var reshItemTypeCd = $("#reshItemTypeCd-edit-"+lineNo).val();
		var emplViewType = $("#emplViewType-edit-"+lineNo).val();
		var emplCnt = $("#emplCnt-edit-"+lineNo).val();
		var reshItemCts = $("#reshItemCts-edit-"+lineNo).val();
		var empl1 = $("#empl1-edit-"+lineNo).val();
		var empl2 = $("#empl2-edit-"+lineNo).val();
		var empl3 = $("#empl3-edit-"+lineNo).val();
		var empl4 = $("#empl4-edit-"+lineNo).val();
		var empl5= $("#empl5-edit-"+lineNo).val();
		var empl6 = $("#empl6-edit-"+lineNo).val();
		var empl7 = $("#empl7-edit-"+lineNo).val();
		var empl8 = $("#empl8-edit-"+lineNo).val();
		var empl9 = $("#empl9-edit-"+lineNo).val();
		var empl10 = $("#empl10-edit-"+lineNo).val();
		var empl11 = $("#empl11-edit-"+lineNo).val();
		var empl12 = $("#empl12-edit-"+lineNo).val();
		var empl13 = $("#empl13-edit-"+lineNo).val();
		var empl14 = $("#empl14-edit-"+lineNo).val();
		var empl15 = $("#empl15-edit-"+lineNo).val();
		var empl16 = $("#empl16-edit-"+lineNo).val();
		var empl17 = $("#empl17-edit-"+lineNo).val();
		var empl18 = $("#empl18-edit-"+lineNo).val();
		var empl19 = $("#empl19-edit-"+lineNo).val();
		var empl20 = $("#empl20-edit-"+lineNo).val();

		if(isEmpty(reshItemCts)) {
			alert('<spring:message code="course.message.reshbank.item.alert.input.desc"/>');
			$("#reshItemCts-edit-"+lineNo).focus();
			return;
		}

		var cnt = $("#emplCnt-edit-"+lineNo+" > option:selected").val();
		if(reshItemTypeCd == 'K') {
			for(i=1; i<=cnt;i++) {
				if(isEmpty($("#empl"+i+"-edit-"+lineNo).val()) ) {
					alert("<spring:message code="course.message.reshbank.item.alert.input.empl.empty"/>");
					$("#empl"+i+"-edit-"+lineNo).focus();
					$("#empl"+i+"-edit-"+lineNo).addClass("validerr");
					return;
				} else {
					$("#empl"+i+"-edit-"+lineNo).removeClass("validerr");
				}

				if(jsByteLength($("#empl"+i+"-edit-"+lineNo).val()) > 1000) {
					alert("<spring:message code="course.title.reshbank.item.empl"/> "+ i + "<spring:message code="course.message.reshbank.item.alert.input.emplsize"/>");
					$("#empl"+i+"-edit-"+lineNo).focus();
					$("#empl"+i+"-edit-"+lineNo).addClass("validerr");
					return;
				} else {
					$("#empl"+i+"-edit-"+lineNo).removeClass("validerr");
				}
			}
		}

 		$("#lineNo").val(lineNo);
		 $("#reshItemTypeCd").val(reshItemTypeCd);
		 $("#emplViewType").val(emplViewType);
		 $("#emplCnt").val(emplCnt);
		 $("#reshItemCts").val(reshItemCts);
		 $("#empl1").val(empl1);
		 $("#empl2").val(empl2);
		 $("#empl3").val(empl3);
		 $("#empl4").val(empl4);
		 $("#empl5").val(empl5);
		 $("#empl6").val(empl6);
		 $("#empl7").val(empl7);
		 $("#empl8").val(empl8);
		 $("#empl9").val(empl9);
		 $("#empl10").val(empl10);
		 $("#empl11").val(empl11);
		 $("#empl12").val(empl12);
		 $("#empl13").val(empl13);
		 $("#empl14").val(empl14);
		 $("#empl15").val(empl15);
		 $("#empl16").val(empl16);
		 $("#empl17").val(empl17);
		 $("#empl18").val(empl18);
		 $("#empl19").val(empl19);
		 $("#empl20").val(empl20);

		$("#researchBankForm").attr("action","/mng/course/researchBank/researchBankUploadCheck");
		$('#researchBankForm').ajaxSubmit(checkLineCallback);
	}

	function checkLineCallback(resultDTO) {
		//var result = resultDTO.returnDto;
		//alert(showAttribute(resultDTO));
		var lineNo = resultDTO.lineNo;
		if(isEmpty(resultDTO.errorCode)) {
			//-- error class 삭제
			$(".value-view-"+lineNo).removeClass("error");
			//-- check box 활성화
			$("#chk_"+lineNo).attr("disabled", false);

			if("K" == resultDTO.reshItemTypeCd){
				$("#reshItemTypeCd-view-"+lineNo).html('<spring:message code="course.title.reshbank.item.type.k"/>');
				$("#emplCnt-view-"+lineNo).html(resultDTO.emplCnt);
				if("W" == resultDTO.emplViewType){
					$("#emplViewType-view-"+lineNo).html('<spring:message code="course.title.reshbank.item.view.w"/>');
					$(".li-empl-"+lineNo).removeAttr("style");
					$(".li-empl-"+lineNo).css("float","left");
					$(".li-empl-"+lineNo).css("margin-left","20px");
				}else if("H") {
					$("#emplViewType-view-"+lineNo).html('<spring:message code="course.title.reshbank.item.view.h"/>');
					$(".li-empl-"+lineNo).removeAttr("style");
					$(".li-empl-"+lineNo).css("margin-left","20px");
				}

				$("#empl1-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>1</b> : ' + resultDTO.empl1);
				$("#empl2-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>2</b> : ' + resultDTO.empl2);
				$("#empl3-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>3</b> : ' + resultDTO.empl3);
				$("#empl4-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>4</b> : ' + resultDTO.empl4);
				$("#empl5-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>5</b> : ' + resultDTO.empl5);
				$("#empl6-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>6</b> : ' + resultDTO.empl6);
				$("#empl7-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>7</b> : ' + resultDTO.empl7);
				$("#empl8-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>8</b> : ' + resultDTO.empl8);
				$("#empl9-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>9</b> : ' + resultDTO.empl9);
				$("#empl10-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>10</b> : ' + resultDTO.empl0);
				$("#empl11-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>11</b> : ' + resultDTO.empl1);
				$("#empl12-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>12</b> : ' + resultDTO.empl2);
				$("#empl13-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>13</b> : ' + resultDTO.empl3);
				$("#empl14-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>14</b> : ' + resultDTO.empl4);
				$("#empl15-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>15</b> : ' + resultDTO.emp15);
				$("#empl16-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>16</b> : ' + resultDTO.empl6);
				$("#empl17-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>17</b> : ' + resultDTO.empl7);
				$("#empl18-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>18</b> : ' + resultDTO.empl8);
				$("#empl19-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>19</b> : ' + resultDTO.empl9);
				$("#empl20-view-"+lineNo).html('<b><spring:message code="course.title.reshbank.item.empl"/>20</b> : ' + resultDTO.emp20);
				$("#emplCnt-view-"+lineNo).html(resultDTO.emplCnt);
				$(".empl-"+lineNo).removeClass("error");

			}else if("D") {
				$("#reshItemTypeCd-view-"+lineNo).html('<spring:message code="course.title.reshbank.item.type.d"/>');
				$("#emplCnt-view-"+lineNo).html('');
				$("#emplViewType-view-"+lineNo).html('');
			}
			$("#reshItemCts-view-"+lineNo).html(resultDTO.reshItemCts);
			$("#errorCode_"+lineNo).val("");

			$("#checkBtn_"+lineNo).hide();
			$("#editBtn_"+lineNo).show();
			$(".value-edit-"+lineNo).hide();
			$(".value-view-"+lineNo).show();
			$("#errcodeArea_"+lineNo).css("color","blue");
			$("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.no.error"/>');
			hiddenEmpl(lineNo);
		} else {
			//-- 오류가 있는 경우

			$("#errorCode_"+lineNo).val(resultDTO.errorCode);
			$("#errcodeArea_"+lineNo).text(resultDTO.errorCode);
			$("#errcodeArea_"+lineNo).css("color","red");
			var errCodeAr = resultDTO.errorCode.split("|");

			if(errCodeAr[1] == "RESHITEMTYPECD"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.reshitemtypecd.error"/>'); }
			if(errCodeAr[1] == "EMPLVIEWTYPE"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.emplviewtype.error"/>'); }
			if(errCodeAr[1] == "RESHITEMCTS"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.reshitemcts.error"/>'); }
			if(errCodeAr[1] == "EMPLCNT"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.emplcnt.error"/>'); }
			if(errCodeAr[1] == "EMPL1"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="1"/>'); }
			if(errCodeAr[1] == "EMPL2"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="2"/>'); }
			if(errCodeAr[1] == "EMPL3"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="3"/>'); }
			if(errCodeAr[1] == "EMPL4"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="4"/>'); }
			if(errCodeAr[1] == "EMPL5"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="5"/>'); }
			if(errCodeAr[1] == "EMPL6"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="6"/>'); }
			if(errCodeAr[1] == "EMPL7"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="7"/>'); }
			if(errCodeAr[1] == "EMPL8"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="8"/>'); }
			if(errCodeAr[1] == "EMPL9"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="9"/>'); }
			if(errCodeAr[1] == "EMPL10"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="10"/>'); }
			if(errCodeAr[1] == "EMPL11"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="11"/>'); }
			if(errCodeAr[1] == "EMPL12"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="12"/>'); }
			if(errCodeAr[1] == "EMPL13"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="13"/>'); }
			if(errCodeAr[1] == "EMPL14"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="14"/>'); }
			if(errCodeAr[1] == "EMPL15"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="15"/>'); }
			if(errCodeAr[1] == "EMPL16"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="16"/>'); }
			if(errCodeAr[1] == "EMPL17"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="17"/>'); }
			if(errCodeAr[1] == "EMPL18"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="18"/>'); }
			if(errCodeAr[1] == "EMPL19"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="19"/>'); }
			if(errCodeAr[1] == "EMPL20"){ $("#errcodeArea_"+lineNo).text('<spring:message code="course.title.reshbank.message.empl.error"  arguments="20"/>'); }

			//-- check box 비활성화
			$("#chk_"+lineNo).attr("disabled", true);
		}
	}

	function saveResearchBank() {
		var researchList = $("#Validate input[name='chk']:checked").stringValues();
		if(isEmpty(researchList)) {
			alert("<spring:message code="course.message.reshbank.select.item"/>");
			return;
		}
		else {
			$("#Validate").attr("action","/mng/course/researchBank/researchBankUpload");
			$('#Validate').ajaxSubmit(saveResearchBankCallback);
		}
	}

	function changeEmpl(lineNo) {
		var cnt = $("#emplCnt-edit-"+lineNo+" > option:selected").val();
		var itemType = $("#reshItemTypeCd-edit-"+lineNo+" > option:selected").val();
		$(".empl-"+lineNo).hide();
		if(itemType == 'K') {
			for(i=1; i<=cnt;i++) {
				$(".value-p-"+lineNo+"-"+i).show();
			}
			for(j=20; j>cnt; j--){
				$("#empl"+Number(j)+"-edit-"+lineNo).val("");
			}
		}
	}

	function changereshItemType(lineNo){
		var itemType = $("#reshItemTypeCd-edit-"+lineNo+" > option:selected").val();
		$(".empl-"+lineNo).hide();
		if(itemType == 'K') {
			$("#emplCnt-edit-"+lineNo).show();
			$("#emplViewType-edit-"+lineNo).show();
			changeEmpl(lineNo);
		} else {
			$("#emplCnt-edit-"+lineNo).hide();
			$("#emplViewType-edit-"+lineNo).hide();
		}
	}

	function hiddenEmpl(lineNo){
		var cnt = $("#emplCnt-edit-"+lineNo+" > option:selected").val();
		var itemType = $("#reshItemTypeCd-edit-"+lineNo+" > option:selected").val();
		$(".li-empl-"+lineNo).hide();
		if(itemType == 'K') {
			for(i=1; i<=cnt;i++) {
				$(".value-li-"+lineNo+"-"+i).show();
			}
		}
	}

	function saveResearchBankCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listQstn();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
</script>
