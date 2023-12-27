<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentVO" value="${studentVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<form name="Input">
<table style="width:100%;">
	<colgroup>
		<col style="width:40%"/>
		<col style="width:10%"/>
		<col style="width:40%"/>
		<col style="width:10%"/>
	</colgroup>
	<tr>
		<td>
			<select name="source" id="source" multiple style="width:100%;height:240px;" class="form-control input-sm">
		<c:forEach items="${resultCfgList}" var="cfgItem" varStatus="status">
			<c:if test="${cfgItem.useYn eq 'Y' }">
				<c:if test="${cfgItem.fieldNm eq 'BIRTH' }"><option value="BIRTH"><spring:message code="user.title.userinfo.birth"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'USERNMKANA' }"><option value="USERNMKANA"><spring:message code="user.title.userinfo.manage.usernmkana"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'SEX' }"><option value="SEX"><spring:message code="user.title.userinfo.sex"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'USERNMENG' }"><option value="USERNMENG"><spring:message code="user.title.userinfo.manage.usernmeng"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'AREA' }"><option value="AREA"><spring:message code="user.title.userinfo.manage.area"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'USERDIV' }"><option value="USERDIV"><spring:message code="user.title.userinfo.manage.userdiv"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'EMAIL' }"><option value="EMAIL"><spring:message code="user.title.userinfo.email"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'DEPT' }"><option value="DEPT"><spring:message code="user.title.userinfo.deptname"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'MOBILENO' }"><option value="MOBILENO"><spring:message code="user.title.userinfo.mobileno"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'PHONENO' }"><option value="PHONENO"><spring:message code="user.title.userinfo.phoneno"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'FAXNO' }"><option value="FAXNO"><spring:message code="user.title.userinfo.fax"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'COMPPHONE' }"><option value="COMPPHONE"><spring:message code="user.title.userinfo.manage.compphone"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'ETCPHONE' }"><option value="ETCPHONE"><spring:message code="user.title.userinfo.manage.etcphone"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'COMPADDR' }"><option value="COMPADDR"><spring:message code="user.title.userinfo.manage.compaddr"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'HOMEADDR' }"><option value="HOMEADDR"><spring:message code="user.title.userinfo.manage.homeaddr"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'DISABLILITY' }"><option value="DISABLILITY"><spring:message code="user.title.userinfo.manage.disablility"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'INTEREST' }"><option value="INTEREST"><spring:message code="user.title.userinfo.manage.interest"/></option></c:if>
				<c:if test="${cfgItem.fieldNm eq 'MEMO' }"><option value="MEMO"><spring:message code="user.title.userinfo.manage.memo"/></option></c:if>
			</c:if>
		</c:forEach>
			</select>
		</td>
		<td style="padding:5px;">
			<a class="btn btn-default btn-sm btn-block" href="#none" onclick="addUnit()"><i class="fa fa-chevron-right "></i></a>
			<a class="btn btn-default btn-sm btn-block" href="#none" onclick="removeUnit()"><i class="fa fa-chevron-left "></i></a>
		</td>
		<td>
			<select name="target" id="target" multiple style="width:100%;height:240px;" class="form-control input-sm">
				<option value="NO"><spring:message code="common.title.no"/></option>
				<option value="DECLS"><spring:message code="student.title.student.decls"/></option>
				<option value="USERNM"><spring:message code="user.title.userinfo.name"/></option>
				<option value="USERID"><spring:message code="user.title.userinfo.userid"/></option>
				<c:if test="${downloadType eq 'ENROLL' || downloadType eq 'STUDENT'}">
				<option value="APLCDTTM"><spring:message code="student.title.student.enrolldate"/></option>
				<option value="PAYMMTHD"><spring:message code="student.title.student.payment.mthd"/></option>
				<option value="PAYMPRICE"><spring:message code="student.title.student.payment.fee"/></option>
				<option value="PAYMSTS"><spring:message code="student.title.student.payment.status"/></option>
				</c:if>
				<c:if test="${downloadType eq 'COMPLETE' }">
				<option value="GETSCORE"><spring:message code="student.title.student.getscore"/></option>
				<option value="SCOREECLT"><spring:message code="student.title.student.scoretop.user"/></option>
				<option value="CPLTNO"><spring:message code="student.title.student.completeno"/></option>
				</c:if>
				<option value="TOT_RATIO"><spring:message code="lecture.title.contents.study.ratio"/></option>
				<option value="TOT_SCORE"><spring:message code="student.title.result.score.final"/></option>
				<option value="ENRLSTS"><spring:message code="common.title.stats"/></option>
			</select>
		</td>
		<td style="padding:5px;">
			<a class="btn btn-info btn-sm btn-block" href="#none" onclick="moveUnit('up')"><i class="fa fa-chevron-up "></i></a>
			<a class="btn btn-info btn-sm btn-block" href="#none" onclick="moveUnit('down')"><i class="fa fa-chevron-down "></i></a>
		</td>
	</tr>
</table>
<div class="text-center" style="margin-top:15px;">
	<a href="#none" onclick="downloadFile('EXCEL')" class="btn btn-primary btn-sm"><spring:message code="button.download.excel"/></a>
	<a href="#none" onclick="parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
</div>
</form>
<script type="text/javascript">

	$(document).ready(function() {

	});

	function addUnit() {
		$('#source :selected').each (
			function(i, selected) {
				var value=$(selected).val();
				var text=$(selected).text();
				//-- target의 option 추가
				$("#target").append("<option value='"+value+"'>"+text+"</option>");
				//-- 추가된 option은 source에서 삭제
				$(selected).remove();
			}
		);
	}

	function removeUnit() {
		$('#target :selected').each (
			function(i, selected) {
				var value=$(selected).val();
				var text=$(selected).text();
				//-- target의 option 추가
				$("#source").append("<option value='"+value+"'>"+text+"</option>");
				//-- 추가된 option은 source에서 삭제
				$(selected).remove();
			}
		);
	}

	function moveUnit(moveType) {
		var selectedCount = 0;
		$('#target :selected').each (
			function(i, selected) {
				selectedCount++;
			}
		);
		if(selectedCount > 1) {
			alert('옵션은 하나씩 선택해 주세요.');
		} else {
			var si = $("#target option").index($("#target option:selected"));
			//alert(si);
			var len = $("#target option").size();
			if(si >=0 ) {
				var siValue = $("#target option:eq("+si+")").val();
				var siText = $("#target option:eq("+si+")").text();
				var moveYn = "Y";
				var ti = null;
				if(moveType == 'up' && si != 0) {
					ti = si-1;
				} else if(moveType == 'down' && len-1 > si) {
					ti = si+1;
				} else {
					moveYn = "N";
				}
				if(moveYn == "Y") {
					var tiValue = $("#target option:eq("+ti+")").val();
					var tiText = $("#target option:eq("+ti+")").text();
					//---- 위치변경
					$("#target option:eq("+si+")").val(tiValue);
					$("#target option:eq("+si+")").text(tiText);
					$("#target option:eq("+ti+")").val(siValue);
					$("#target option:eq("+ti+")").text(siText);
					$("#target option:eq("+si+")").removeAttr("selected");
					$("#target option:eq("+ti+")").attr("selected","selected");
				}
			}

		}
	}

	function downloadFile(dnType) {
		var url = cUrl("/mng/std/student/listExcelDownload")+"?excelHeader="+setHeader()+"${AMPERSAND}downloadType=${downloadType}${AMPERSAND}crsCreCd=${studentVO.crsCreCd}${AMPERSAND}printMode="+dnType;
		if(dnType == 'EXCEL') {
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
		} else {
			window.open(url,'pdf','top=0;left=0,scrollbars=auto');
		}
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
</mhtml:frm_body>
</mhtml:mng_html>