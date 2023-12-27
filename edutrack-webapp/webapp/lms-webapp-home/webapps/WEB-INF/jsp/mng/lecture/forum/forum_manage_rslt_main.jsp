<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<% request.setAttribute("vEnter", "\n"); %>
<c:set var="forumVO" value="${vo}"/>
	<br>
	<form id="forumForm" name="forumForm" onsubmit="return false" action="/mng/lecture/forum">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="forumSn" value="${vo.forumSn }" />
	</form>
	<table summary="<spring:message code="lecture.title.forum.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.title"/></th>
			<td colspan="3">
				${forumVO.forumTitle}
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.duration"/></th>
			<td>
				<meditag:dateformat type="0" delimeter="." property="${forumVO.forumStartDttm}"/> ~ <meditag:dateformat type="0" delimeter="." property="${forumVO.forumEndDttm}"/>
				<%-- ${forumVO.forumStartDttm} ~ ${forumVO.forumEndDttm} --%>
			</td>
			<th scope="row"><spring:message code="lecture.title.forum.regyn"/></th>
			<td>
				<meditag:codename code="${forumVO.forumRegYn}" category="REG_YN"/>
			</td>
		</tr>
		<tr>
			<th scope="row" ><spring:message code="lecture.title.forum.period.after.write"/></th>
			<td colspan="3">
				<c:if test="${forumVO.periodAfterWriteYn eq 'Y' }"><spring:message code="lecture.title.forum.writeY"/></c:if>
				<c:if test="${forumVO.periodAfterWriteYn eq 'N' }"><spring:message code="lecture.title.forum.writeN"/></c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.desc"/></th>
			<td colspan="3" class="wordbreak">
				${fn:replace(forumVO.forumCts,vEnter,"<br>")}
			</td>
		</tr>
	</table>
	<div class="text-right">
		<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
	</div>
	<ul class="nav nav-tabs">
		<li><a href="javascript:forumManageAtcl()"><spring:message code="lecture.title.forum.atcl.manage.tab"/></a></li>
		<li><a href="javascript:forumManageRate()"><spring:message code="lecture.title.forum.rate.manage.tab"/></a></li>
		<li class="active"><a href="#"><spring:message code="lecture.title.forum.status.manage.tab"/></a></li>
	</ul>
	<div style="border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
		<div class="row" style="margin-top:5px; margin-bottom:20px;">
			<div class="col-md-12">
				<div class="flot-chart">
					<div class="flot-chart-content" id="flot-line-chart-multi"></div>
				</div>
			</div>
		</div>
	</div>

	<meditag:js src="/tpl/bootstrap/bower_components/flot/excanvas.min.js"/>
	<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.js"/>
	<meditag:js src="/tpl/bootstrap/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"/>
<script type="text/javascript">
	var ItemDTO = new Object();

	// 페이지 초기화
	$(document).ready(function() {
		var data = [{
			label: "Number of Students",
			data : [
<c:forEach var="item" items="${statusList}" varStatus="status">
	[${item.keyCode}, ${item.keyValue}] <c:if test="${!status.last}">,</c:if>
</c:forEach>
			],
			color : ["green"]
		}];
		var position = 'right';
		var options = {
				series: {
		            lines: {
		                show: true
		            },
		            points : {
		            	show : true
		            }
		        },
		        xaxis: {
		        	axisLabelUseCanvas: true,
		         	axisLabelPadding: 1
		        },
		        yaxis: {
	                axisLabelUseCanvas: true,
	                tickSize: 1,
	                tickDecimals: 0
		        },
	        legend: {
	            position: 'ne'
	        },
	        grid: {
	            hoverable: true //IMPORTANT! this is needed for tooltip to work
	        },
	        tooltip: true,
	        tooltipOpts: {
	            content: "Score : %x , Students : %y",
	            onHover: function(flotItem, $tooltipEl) {
	                // console.log(flotItem, $tooltipEl);
	            }
	        }
		};
		var plot = $.plot($("#flot-line-chart-multi"), data, options);

		parentResize();
	});

	/**
	* resize
	*/
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	* 토론 목록으로 이동
	*/
	function goList(){
		document.location.href = cUrl("/mng/lecture/forum/main?crsCreCd=${forumVO.crsCreCd}");
	}

	/**
	 * 토론 평가 관리 이동
	 */
	function forumManageAtcl() {
		document.location.href = cUrl("/mng/lecture/forum/manageForumAtclMain")+"?crsCreCd=${forumVO.crsCreCd}${AMPERSAND}forumSn=${forumVO.forumSn}";
	}

	/**
	 * Score Management
	 */
	function forumManageRate() {
		document.location.href = cUrl("/mng/lecture/forum/manageForumRateMain")+"?crsCreCd=${forumVO.crsCreCd}${AMPERSAND}forumSn=${forumVO.forumSn}";
	}

</script>
