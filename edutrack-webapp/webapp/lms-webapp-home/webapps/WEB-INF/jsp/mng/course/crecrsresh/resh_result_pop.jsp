<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%
out.clear();
%>
<c:set var="creCrsReshVO" value="${vo}" />
<c:set var="crsReshResultVO" value="${crsReshResultVO}" />
<c:set var="crsReshResultList" value="${crsReshResultList}" />

	<div style="float:right; ">
		<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.download.excel"/> </a>
	</div>
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
				${creCrsReshVO.reshTitle}
			</td>
		</tr>
		<tr>
			<th class="hwname lm70"><spring:message code="lecture.title.research.duration"/></th>
			<td colspan="3"><meditag:dateformat type="8" delimeter="." property="${creCrsReshVO.startDttm}"/> ~<meditag:dateformat type="8" delimeter="." property="${creCrsReshVO.endDttm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="lecture.title.research.regyn"/></th>
			<td>
				<c:set var="regYn" value="${creCrsReshVO.regYn}"/>
				<c:if test="${empty creCrsReshVO.regYn}"><c:set var="regYn" value="N"/></c:if>
				<meditag:codename code="${regYn}" category="REG_YN" />
			</td>
		</tr>
		</tbody>
	</table>

	<ul class="list-gruop" style="padding-left:0px;margin-top: 20px;">
	<c:forEach var="item" items="${crsReshResultList}" varStatus="status">
		<li class="list-group-item" style="word-break:break-all;word-wrap: break-word;">
			${status.count}. ${item.reshItemCts}
			<div class="well" style="padding: 5px 20px 5px 20px;">
			<c:if test="${item.reshItemTypeCd eq 'D'}">
				<a href="javascript:resultOpinionD('${item.reshItemSn}')" class="btn btn-primary btn-sm"><spring:message code="button.view.result"/></a>
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
  								<c:if test="${item.empl1Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','1')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl1Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl1Ratio}%;" >
	    									<span> ${item.empl1Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl1Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl1Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl1Ratio}%;" >
    									<span> ${item.empl1Cnt} Hits</span>
  									</div>
  								</c:if>
								</div>
							</td>
							<td class="text-right">${item.empl1Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl2}">
						<tr>
							<td>2. ${item.empl2}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl2Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','2')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl2Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl2Ratio}%;" >
	    									<span> ${item.empl2Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl2Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl2Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl2Ratio}%;" >
	    									<span> ${item.empl2Cnt} Hits</span>
	  									</div>
  								</c:if>
								</div>
							</td>
							<td class="text-right">${item.empl2Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl3}">
						<tr>
							<td>3. ${item.empl3}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl3Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','3')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl3Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl3Ratio}%;" >
	    									<span> ${item.empl3Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl3Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl3Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl3Ratio}%;" >
    									<span> ${item.empl3Cnt} Hits</span>
  									</div>
  								</c:if>
								</div>
							</td>
							<td class="text-right">${item.empl3Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl4}">
						<tr>
							<td>4. ${item.empl4}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl4Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','4')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl4Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl4Ratio}%;" >
	    									<span> ${item.empl4Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl4Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl4Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl4Ratio}%;" >
	    									<span> ${item.empl4Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl4Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl5}">
						<tr>
							<td>5. ${item.empl5}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl5Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','5')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl5Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl5Ratio}%;" >
	    									<span> ${item.empl5Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl5Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl5Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl5Ratio}%;" >
	    									<span> ${item.empl5Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl5Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl6}">
						<tr>
							<td>6. ${item.empl6}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl6Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','6')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl6Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl6Ratio}%;" >
	    									<span> ${item.empl6Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl6Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl6Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl6Ratio}%;" >
	    									<span> ${item.empl6Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl6Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl7}">
						<tr>
							<td>7. ${item.empl7}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl7Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','7')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl7Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl7Ratio}%;" >
	    									<span> ${item.empl7Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl7Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl7Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl7Ratio}%;" >
	    									<span> ${item.empl7Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl7Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl8}">
						<tr>
							<td>8. ${item.empl8}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl8Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','8')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl8Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl8Ratio}%;" >
	    									<span> ${item.empl8Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl8Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl8Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl8Ratio}%;" >
	    									<span> ${item.empl8Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl8Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl9}">
						<tr>
							<td>9. ${item.empl9}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl9Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','9')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl9Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl9Ratio}%;" >
	    									<span> ${item.empl9Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl9Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl9Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl9Ratio}%;" >
	    									<span> ${item.empl9Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl9Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl10}">
						<tr>
							<td>10. ${item.empl10}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl10Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','10')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl10Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl10Ratio}%;" >
	    									<span> ${item.empl10Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl10Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl10Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl10Ratio}%;" >
	    									<span> ${item.empl10Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl10Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl11}">
						<tr>
							<td>11. ${item.empl11}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl11Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','11')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl11Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl11Ratio}%;" >
	    									<span> ${item.empl11Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl11Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl11Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl11Ratio}%;" >
	    									<span> ${item.empl11Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl11Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl12}">
						<tr>
							<td>12. ${item.empl12}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl12Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','12')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl12Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl12Ratio}%;" >
	    									<span> ${item.empl12Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl12Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl12Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl12Ratio}%;" >
	    									<span> ${item.empl12Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl12Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl13}">
						<tr>
							<td>13. ${item.empl13}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl13Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','13')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl13Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl13Ratio}%;" >
	    									<span> ${item.empl13Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl13Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl13Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl13Ratio}%;" >
	    									<span> ${item.empl13Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl13Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl14}">
						<tr>
							<td>14. ${item.empl14}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl14Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','14')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl14Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl14Ratio}%;" >
	    									<span> ${item.empl14Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl14Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl14Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl14Ratio}%;" >
	    									<span> ${item.empl14Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl14Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl15}">
						<tr>
							<td>15. ${item.empl15}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl15Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','15')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl15Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl15Ratio}%;" >
	    									<span> ${item.empl15Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl15Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl15Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl15Ratio}%;" >
	    									<span> ${item.empl15Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl15Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl16}">
						<tr>
							<td>16. ${item.empl16}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl16Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','16')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl16Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl16Ratio}%;" >
	    									<span> ${item.empl16Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl16Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl16Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl16Ratio}%;" >
	    									<span> ${item.empl16Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl16Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl17}">
						<tr>
							<td>17. ${item.empl17}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl17Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','17')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl17Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl17Ratio}%;" >
	    									<span> ${item.empl17Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl17Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl17Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl17Ratio}%;" >
	    									<span> ${item.empl17Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl17Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl18}">
						<tr>
							<td>18. ${item.empl18}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl18Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','18')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl18Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl18Ratio}%;" >
	    									<span> ${item.empl18Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl18Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl18Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl18Ratio}%;" >
	    									<span> ${item.empl18Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl18Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl19}">
						<tr>
							<td>19. ${item.empl19}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl19Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','19')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl19Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl19Ratio}%;" >
	    									<span> ${item.empl19Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl19Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl19Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl19Ratio}%;" >
	    									<span> ${item.empl19Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl19Ratio}%</td>
						</tr>
					</c:if>
					<c:if test="${not empty item.empl20}">
						<tr>
							<td>20. ${item.empl20}</td>
							<td>
								<div class="progress progress-striped" style="margin:10px;" >
								<c:if test="${item.empl20Ratio > 0}">
									<a href="javascript:resultOpinionK('${item.reshItemSn}','20')">
	  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl20Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl20Ratio}%;" >
	    									<span> ${item.empl20Cnt} Hits</span>
	  									</div>
  									</a>
  								</c:if>
  								<c:if test="${item.empl20Ratio == 0}">
  									<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${item.empl20Ratio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.empl20Ratio}%;" >
	    									<span> ${item.empl20Cnt} Hits</span>
	  									</div>
  								</c:if>
  								</div>
							</td>
							<td class="text-right">${item.empl20Ratio}%</td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</c:if>
			</div>
		</li>
	</c:forEach>
	<c:if test="${empty crsReshResultList}">
		<li class="list-group-item">
			<spring:message code="course.message.resh.nodata"/>
		</li>
	</c:if>
	</ul>

	<div class="text-center">
		<a href="#none" onclick="parent.modalBoxClose();" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	<form name="reshAnsrFrm" id="reshAnsrFrm" >
		<input type="hidden" name="crsCreCd" id="crsCreCd">
		<input type="hidden" name="reshSn" id="reshSn">
		<input type="hidden" name="reshItemSn" id="reshItemSn">
		<input type="hidden" name="reshAnsr" id="reshAnsr">
		<input type="hidden" name="reshAnsrTypeCd" id="reshAnsrTypeCd">
	</form>

<script type="text/javascript">

	$(document).ready(function() {
		//
	});

	function resultOpinionD(reshItemSn){
		var frm = document.forms.reshAnsrFrm;
		$("#reshAnsrFrm").attr("action","/mng/course/creCrs/resh/mainOpinionPop");
		$("#crsCreCd").val("${crsReshResultVO.crsCreCd}");
		$("#reshSn").val("${crsReshResultVO.reshSn}");
		$("#reshItemSn").val(reshItemSn);
		$("#reshAnsr").val("");
		$("#reshAnsrTypeCd").val("D");
		$("#reshAnsrFrm").submit();
	}

	function resultOpinionK(reshItemSn, reshAnsr){
		var frm = document.forms.reshAnsrFrm;
		$("#reshAnsrFrm").attr("action","/mng/course/creCrs/resh/mainOpinionPop");
		$("#crsCreCd").val("${crsReshResultVO.crsCreCd}");
		$("#reshSn").val("${crsReshResultVO.reshSn}");
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

	function excelDownload() {
		var url = cUrl("/mng/course/creCrs/resh/reshExcelDownload")+"?excelHeader="+setHeader()+"${AMPERSAND}crsCreCd=${crsReshResultVO.crsCreCd}${AMPERSAND}reshSn=${crsReshResultVO.reshSn}";

		<c:if test="${BROWSER eq 'IE'}">
		window.open(url,'pdf','top=0;left=0,scrollbars=auto');
		</c:if>
		<c:if test="${BROWSER ne 'IE'}">
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		$('#_m_download_iframe').attr('src', url);
		</c:if>

	}
</script>
