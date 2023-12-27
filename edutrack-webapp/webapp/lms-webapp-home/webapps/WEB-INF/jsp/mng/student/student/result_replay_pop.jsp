<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %> 
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:js src="/js/popupbox.js"/>
</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<table  class="table_dtl" style="width:96%"  align="center">
		<tr height="30">
			<td align="left"><b><span style="color:blue"> * </span>결제 결과 상세정보</b></td>
		</tr>
	</table>
	<table class="table_dtl" style="width:96%"  align="center">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row">환불 코드</th>
			<td class="top">
				${rslt_title}
			</td>
		</tr>
		<tr height="33">
			<th scope="row">환불 메시지</th>
			<td>
				${rslt_desc}
			</td>
		</tr>
	</table>
	<meditag:buttonwrapper style="padding: 6px; width: 96%">
        <meditag:button value="button.close" title="button.close" func="parent.stdViewPopBox.close()" />
    </meditag:buttonwrapper>
</mhtml:frm_body>
</mhtml:mng_html>