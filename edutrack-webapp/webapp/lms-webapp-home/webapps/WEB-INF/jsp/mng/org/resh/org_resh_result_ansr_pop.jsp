<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<div style="clear: both;"></div>
	<table class="table table-bordered wordbreak" style="margin-top: 5px;">
     		<caption class="sr-only"><spring:message code="lecture.title.research.info"/></caption>
     		<colgroup>
     			<col style="width:20%;"/>
     			<col style="width:80%;"/>
     		</colgroup>
     		<tbody>
		<tr>
			<th><spring:message code="lecture.title.research.name"/></th>
			<td>
				${vo.reshTitle}
			</td>
		</tr>
		<tr>
			<th class="hwname lm70"><spring:message code="lecture.title.research.duration"/></th>
			<td colspan="3">${vo.searchValue}</td>
		</tr>
		<tr>
			<th><spring:message code="lecture.title.research.regyn"/></th>
			<td>
				<meditag:codename code="${vo.regYn}" category="REG_YN" />
			</td>
		</tr>
		</tbody>
	</table>

	<ul class="list-gruop" style="padding-left:0px;margin-top: 20px;">
	<c:forEach var="item" items="${ansrList}" varStatus="status">
		<li class="list-group-item" style="word-break:break-all;word-wrap: break-word;">
			${status.count}. ${item.reshItemCts}
			<div class="well" style="padding: 5px 20px 5px 20px;">
			<c:if test="${item.reshItemTypeCd eq 'D'}">
				<a href="javascript:resultOpinionD('${item.reshItemSn}','${item.reshItemCts}')" class="btn btn-primary btn-sm"><spring:message code="button.view.result"/></a>
			</c:if> 
			<c:if test="${item.reshItemTypeCd eq 'K'}">
				<table style="width:100%;font-size:12px;">
					<colgroup>
						<col style="width:50%"/>
						<col style="width:40%"/>
						<col style="width:10%"/>
					</colgroup>
					<tbody>
					<c:if test="${not empty item.empl1}">
						<tr>
							<td>1. ${item.empl1}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl1Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore1/item.reshCnt*100}" />
								<fmt:parseNumber var="empl1Ratio" value="${empl1Ratio}" integerOnly="true" />
  									<c:if test="${empl1Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','1')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl1Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl1Ratio}%;" >
	    									<span> ${item.emplScore1} Hits</span>
	  									</div>
  									<c:if test="${empl1Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl1Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl2}">
						<tr>
							<td>2. ${item.empl2}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl2Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore2/item.reshCnt*100}" />
								<fmt:parseNumber var="empl2Ratio" value="${empl2Ratio}" integerOnly="true" />
  									<c:if test="${empl2Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','2')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl2Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl2Ratio}%;" >
	    									<span> ${item.emplScore2} Hits</span>
	  									</div>
  									<c:if test="${empl2Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl2Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl3}">
						<tr>
							<td>3. ${item.empl3}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl3Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore3/item.reshCnt*100}" />
								<fmt:parseNumber var="empl3Ratio" value="${empl3Ratio}" integerOnly="true" />
  									<c:if test="${empl3Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','3')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl3Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl3Ratio}%;" >
	    									<span> ${item.emplScore3} Hits</span>
	  									</div>
  									<c:if test="${empl3Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl3Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl4}">
						<tr>
							<td>4. ${item.empl4}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl4Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore4/item.reshCnt*100}" />
								<fmt:parseNumber var="empl4Ratio" value="${empl4Ratio}" integerOnly="true" />
  									<c:if test="${empl4Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','4')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl4Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl4Ratio}%;" >
	    									<span> ${item.emplScore4} Hits</span>
	  									</div>
  									<c:if test="${empl4Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl4Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl5}">
						<tr>
							<td>5. ${item.empl5}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl5Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore5/item.reshCnt*100}" />
								<fmt:parseNumber var="empl5Ratio" value="${empl5Ratio}" integerOnly="true" />
  									<c:if test="${empl5Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','5')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl5Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl5Ratio}%;" >
	    									<span> ${item.emplScore5} Hits</span>
	  									</div>
  									<c:if test="${empl5Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl5Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl6}">
						<tr>
							<td>6. ${item.empl6}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl6Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore6/item.reshCnt*100}" />
								<fmt:parseNumber var="empl6Ratio" value="${empl6Ratio}" integerOnly="true" />
  									<c:if test="${empl6Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','6')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl6Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl6Ratio}%;" >
	    									<span> ${item.emplScore6} Hits</span>
	  									</div>
  									<c:if test="${empl6Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl6Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl7}">
						<tr>
							<td>7. ${item.empl7}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl7Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore7/item.reshCnt*100}" />
								<fmt:parseNumber var="empl7Ratio" value="${empl7Ratio}" integerOnly="true" />
  									<c:if test="${empl7Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','7')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl7Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl7Ratio}%;" >
	    									<span> ${item.emplScore7} Hits</span>
	  									</div>
  									<c:if test="${empl7Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl7Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl8}">
						<tr>
							<td>8. ${item.empl8}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl8Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore8/item.reshCnt*100}" />
								<fmt:parseNumber var="empl8Ratio" value="${empl8Ratio}" integerOnly="true" />
  									<c:if test="${empl8Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','8')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl8Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl8Ratio}%;" >
	    									<span> ${item.emplScore8} Hits</span>
	  									</div>
  									<c:if test="${empl8Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl8Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl9}">
						<tr>
							<td>9. ${item.empl9}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl9Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore9/item.reshCnt*100}" />
								<fmt:parseNumber var="empl9Ratio" value="${empl9Ratio}" integerOnly="true" />
  									<c:if test="${empl9Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','9')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl9Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl9Ratio}%;" >
	    									<span> ${item.emplScore9} Hits</span>
	  									</div>
  									<c:if test="${empl9Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl9Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl10}">
						<tr>
							<td>10. ${item.empl10}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl10Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore10/item.reshCnt*100}" />
								<fmt:parseNumber var="empl10Ratio" value="${empl10Ratio}" integerOnly="true" />
  									<c:if test="${empl10Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','10')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl10Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl10Ratio}%;" >
	    									<span> ${item.emplScore10} Hits</span>
	  									</div>
  									<c:if test="${empl10Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl10Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl11}">
						<tr>
							<td>11. ${item.empl11}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl11Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore11/item.reshCnt*100}" />
								<fmt:parseNumber var="empl11Ratio" value="${empl11Ratio}" integerOnly="true" />
  									<c:if test="${empl11Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','11')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl11Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl11Ratio}%;" >
	    									<span> ${item.emplScore11} Hits</span>
	  									</div>
  									<c:if test="${empl11Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl11Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl12}">
						<tr>
							<td>12. ${item.empl12}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl12Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore12/item.reshCnt*100}" />
								<fmt:parseNumber var="empl12Ratio" value="${empl12Ratio}" integerOnly="true" />
  									<c:if test="${empl12Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','12')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl12Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl12Ratio}%;" >
	    									<span> ${item.emplScore12} Hits</span>
	  									</div>
  									<c:if test="${empl12Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl12Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl13}">
						<tr>
							<td>13. ${item.empl13}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl13Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore13/item.reshCnt*100}" />
								<fmt:parseNumber var="empl13Ratio" value="${empl13Ratio}" integerOnly="true" />
  									<c:if test="${empl13Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','13')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl13Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl13Ratio}%;" >
	    									<span> ${item.emplScore13} Hits</span>
	  									</div>
  									<c:if test="${empl13Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl13Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl14}">
						<tr>
							<td>14. ${item.empl14}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl14Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore14/item.reshCnt*100}" />
								<fmt:parseNumber var="empl14Ratio" value="${empl14Ratio}" integerOnly="true" />
  									<c:if test="${empl14Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','14')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl14Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl14Ratio}%;" >
	    									<span> ${item.emplScore14} Hits</span>
	  									</div>
  									<c:if test="${empl14Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl14Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl15}">
						<tr>
							<td>15. ${item.empl15}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl15Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore15/item.reshCnt*100}" />
								<fmt:parseNumber var="empl15Ratio" value="${empl15Ratio}" integerOnly="true" />
  									<c:if test="${empl15Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','15')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl15Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl15Ratio}%;" >
	    									<span> ${item.emplScore15} Hits</span>
	  									</div>
  									<c:if test="${empl15Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl15Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl16}">
						<tr>
							<td>16. ${item.empl16}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl16Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore16/item.reshCnt*100}" />
								<fmt:parseNumber var="empl16Ratio" value="${empl16Ratio}" integerOnly="true" />
  									<c:if test="${empl16Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','16')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl16Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl16Ratio}%;" >
	    									<span> ${item.emplScore16} Hits</span>
	  									</div>
  									<c:if test="${empl16Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl16Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl17}">
						<tr>
							<td>17. ${item.empl17}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl17Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore17/item.reshCnt*100}" />
								<fmt:parseNumber var="empl17Ratio" value="${empl17Ratio}" integerOnly="true" />
  									<c:if test="${empl17Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','17')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl17Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl17Ratio}%;" >
	    									<span> ${item.emplScore17} Hits</span>
	  									</div>
  									<c:if test="${empl17Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl17Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl18}">
						<tr>
							<td>18. ${item.empl18}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl18Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore18/item.reshCnt*100}" />
								<fmt:parseNumber var="empl18Ratio" value="${empl18Ratio}" integerOnly="true" />
  									<c:if test="${empl18Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','18')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl18Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl18Ratio}%;" >
	    									<span> ${item.emplScore18} Hits</span>
	  									</div>
  									<c:if test="${empl18Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl18Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl19}">
						<tr>
							<td>19. ${item.empl19}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl19Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore19/item.reshCnt*100}" />
								<fmt:parseNumber var="empl19Ratio" value="${empl19Ratio}" integerOnly="true" />
  									<c:if test="${empl19Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','19')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl19Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl19Ratio}%;" >
	    									<span> ${item.emplScore19} Hits</span>
	  									</div>
  									<c:if test="${empl19Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl19Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl20}">
						<tr>
							<td>20. ${item.empl20}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;">
								<c:set var="empl20Ratio" value="${item.reshCnt eq 0 ? 0 :item.emplScore20/item.reshCnt*100}" />
								<fmt:parseNumber var="empl20Ratio" value="${empl20Ratio}" integerOnly="true" />
  									<c:if test="${empl20Ratio > 0}"> <a href="javascript:resultOpinionK('${item.reshItemSn}','${item.reshItemCts}','20')"> </c:if>
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${empl20Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${empl20Ratio}%;" >
	    									<span> ${item.emplScore20} Hits</span>
	  									</div>
  									<c:if test="${empl20Ratio > 0}"> </a> </c:if>
								</div>
							</td>
							<td class="text-right">${empl20Ratio}%</td>
						</tr>
					</c:if>
					
					</tbody>
				</table>
			</c:if>
			</div>
		</li>
	</c:forEach>
	<c:if test="${empty ansrList}">
		<li class="list-group-item">
			<spring:message code="course.message.resh.nodata"/>
		</li>
	</c:if>
	</ul>

	<div class="text-center">
		<a href="#none" onclick="parent.modalBoxClose();" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	<form name="reshAnsrFrm" id="reshAnsrFrm" >
		<input type="hidden" name="reshItemCts" id="reshItemCts">
		<input type="hidden" name="reshSn" id="reshSn">
		<input type="hidden" name="reshItemSn" id="reshItemSn">
		<input type="hidden" name="reshAnsr" id="reshAnsr">
		<input type="hidden" name="reshAnsrTypeCd" id="reshAnsrTypeCd">
		<input type="hidden" name="reshTitle" id="reshTitle" value="${vo.reshTitle}">
		<input type="hidden" name="regYn" id="regYn"  value="${vo.regYn}">
		<input type="hidden" name="searchValue" id="searchValue"  value="${vo.searchValue}">
	</form>

<script type="text/javascript">

	$(document).ready(function() {
		//
	});

	function resultOpinionD(reshItemSn, reshItemCts){
		var frm = document.forms.reshAnsrFrm;
		$("#reshAnsrFrm").attr("action","/mng/org/research/mainOpinionPop");
		$("#reshItemCts").val(reshItemCts);
		$("#reshSn").val("${vo.reshSn}");
		$("#reshItemSn").val(reshItemSn);
		$("#reshAnsr").val("");
		$("#reshAnsrTypeCd").val("D");
		$("#reshAnsrFrm").submit();
	}

	function resultOpinionK(reshItemSn, reshItemCts, reshAnsr){
		var frm = document.forms.reshAnsrFrm;
		$("#reshAnsrFrm").attr("action","/mng/org/research/mainOpinionPop");
		$("#reshItemCts").val(reshItemCts);
		$("#reshSn").val("${vo.reshSn}");
		$("#reshItemSn").val(reshItemSn);
		$("#reshAnsr").val(reshAnsr);
		$("#reshAnsrTypeCd").val("K");
		$("#reshAnsrFrm").submit();
	}

	function setHeader() {
		var retVal = "";
		for(var i=0; i < $("#target option").size(); i++) {
			if(i > 0 && i  < $("#target option").size()) retVal += "@$";
			retVal += $("#target option:eq("+i+")").val();
		}
		return retVal;
	}
</script>
