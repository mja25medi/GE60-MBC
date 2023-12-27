<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCartrgVO" value="${vo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
</mhtml:home_head>

<body style="padding-top: 0px;">
	<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false" action="/home/olc/cartrg">
	<input type="hidden" name="cartrgCd" value="${vo.cartrgCd}" />
	<input type="hidden" name="cartrgOdr" value="${vo.cartrgOdr}" />
	<input type="hidden" name="cntsCnt" id="cntsCnt" value="${vo.cntsCnt}" />
	<input type="hidden" name="shareCnt" id="shareCnt" value="${vo.shareCnt}" />
	<input type="hidden" name="useCnt" id="useCnt" value="${vo.useCnt}" />
	<input type="hidden" name="thumbFileSn" value="${vo.thumbFileSn}" />
	<input type="hidden" name="knowShareCd" id="knowShareCd" value="${vo.knowShareCd}" />
	<input type="hidden" name="cntsShareCd" id="cntsShareCd" value="${vo.cntsShareCd}" />
	<input type="hidden" name="openYn" value="${vo.openYn}" />
	<input type="hidden" name="knowOpenYn" value="${vo.knowOpenYn}" />
	<div id="cartrg_view_appl">
	<table class="table table-striped table-bordered">
		<caption class="sr-only"><spring:message code="course.title.category.manage"/></caption>
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="cartrgNm"><spring:message code="olc.title.cartridge.name"/></label></th>
			<td>
				${vo.cartrgNm }
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="olc.title.contents.share"/></th>
			<td>
				<c:choose>
					<c:when test="${vo.cntsShareCd eq '01' }"><a class="btn btn-warning btn-sm" href="javascript:cntsShareCdChange('02')"><spring:message code="button.cancel"/></a></c:when>
					<c:when test="${vo.cntsShareCd eq '03' }"></c:when>
					<c:otherwise><a class="btn btn-primary btn-sm" href="javascript:cntsShareCdChange('01')"><spring:message code="log.title.org.student.enroll"/></a></c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${vo.cntsShareCd eq '02' }"></c:when>
					<c:otherwise><meditag:codename code="${vo.cntsShareCd}" category="CNTS_SHARE_CD" /></c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="olc.title.knowledge.share"/></th>
			<td>
				<c:choose>
					<c:when test="${vo.knowShareCd eq '01' }"><a class="btn btn-warning btn-sm" href="javascript:knowShareCdChange('02')"><spring:message code="button.cancel"/></a></c:when>
					<c:when test="${vo.knowShareCd eq '03' }"></c:when>
					<c:otherwise><a class="btn btn-primary btn-sm" href="javascript:knowShareCdChange('01')"><spring:message code="log.title.org.student.enroll"/></a></c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${vo.knowShareCd eq '02' }"></c:when>
					<c:otherwise><meditag:codename code="${vo.knowShareCd}" category="KNOW_SHARE_CD" /></c:otherwise>
				</c:choose>
			</td>
		</tr>

	</table>

	<div class="text-right" style="margin-top:10px;">
		<button class="btn btn-default btn-sm" onclick="parent.location.reload();parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />

	</div>
	</form>

<script type="text/javascript">

$(document).ready(function() {

});

function cartrg_view_appl(){
	$('#cartrg_view_appl').load(cUrl("/home/olc/cartrg/viewAppl"),{ "cartrgCd" : "${vo.cartrgCd}"});
	$('#cartrg_view_appl').load(cUrl("/home/olc/cartrg/viewAppl"),{ "cartrgCd" : "${vo.cartrgCd}"});
}


/**
 * 처리 결과 표시 콜백
 */
function processCallback(resultDTO) {
	alert(resultDTO.message);

	if(resultDTO.result >= 0) {
		// 정상 처리
		parent.location.reload();
		parent.modalBoxClose();
	} else {
		// 비정상 처리
	}
}

function cntsShareCdChange(cntsShareCd){
	$.post(
			cUrl("/home/olc/cartrg/cntsShareCdChange"),
			{
				
				  "cartrgCd" : "${vo.cartrgCd}",
				  "cntsShareCd" : cntsShareCd
			},
			function(resultDTO) {
				if(resultDTO.result >= 0) {
					// 정상 처리
					cartrg_view_appl();
				} else {
					// 비정상 처리
				}
			}
		);
}


function knowShareCdChange(knowShareCd){
	$.post(
			cUrl("/home/olc/cartrg/knowShareCdChange"),
			{
				  "cartrgCd" : "${vo.cartrgCd}",
				  "knowShareCd" : knowShareCd
			},
			function(resultDTO) {
				if(resultDTO.result >= 0) {
					// 정상 처리
					cartrg_view_appl();
				} else {
					// 비정상 처리
				}
			}
		);
}

</script>
</body>
</mhtml:home_html>
