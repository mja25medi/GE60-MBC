<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:mng_head>

<mhtml:mng_body>
			<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
			<div class="row" id="content">
				<div class="col-md-12">
					<table summary="<spring:message code="prdt.title.prjt.info.prjt.manage"/>" class="table table-bordered wordbreak" style="margin-bottom:5px;">
						<colgroup>
							<col style="width:20%;"/>
							<col style="width:80%"/>
						</colgroup>
						<tr>
							<th scope="row"><spring:message code="prdt.title.prjt.info.prjt.name"/></th>
							<td>${prjtInfoVO.prjtNm}</td>
						</tr>
					</table>
					<span class="pull-right">
						<a href="<c:url value="/PrdtPrjtInfoManage.do"/>?cmd=main" class="btn btn-sm btn-default"><i class="fa fa-list"></i> <spring:message code="button.list"/></a>
					</span>
				</div>
				<div class="col-md-12">
					<ul class="nav nav-tabs" id="ctgrTabs">
					<c:set var="defCtgrCd" value=""/>
					<c:forEach var="tabs" items="${codeCtgrList}" varStatus="status">
						<c:if test="${status.first}"><c:set var="defCtgrCd" value="${tabs.codeCtgrCd}"/></c:if>
						<li class="ctgrtab <c:if test="${status.first}">active</c:if>" id="ctgrtab_${tabs.codeCtgrCd}"><a href="javascript:clickTabs('${tabs.codeCtgrCd}')">${tabs.codeCtgrNm}</a></li>
					</c:forEach>
					</ul>
				</div>
				<div class="col-md-6">
					<div style="width:100%;margin-top:5px;">
						<div class="text-right" style="height:35px;">
							<a href="javascript:writeCodeForm()" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> <spring:message code="button.write.code"/></a>
						</div>
						<div id="listArea" style="width:100%;">
							
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-12" id="workArea" style="margin-top:35px;">
					
						</div>
					</div>
				</div>
			</div>
</mhtml:mng_body>
<script type="text/javascript">
	var ItemVO = {"codeCtgrCd":"${defCtgrCd}"}
	/**
	 * Initialize
	 */
	$(document).ready(function() {
		listCode();
		writeCodeForm();
	});

	/**
	 * On click tab menu function
	 */
	function clickTabs(codeCtgrCd) {
		ItemVO.codeCtgrCd = codeCtgrCd;
		$(".ctgrtab").removeClass("active");
		$("#ctgrtab_"+codeCtgrCd).addClass("active");
		listCode();
		writeCodeForm();
	}

	/**
	 * Call the code list.
	 */
	function listCode() {
		$("#listArea").load(cUrl("/PrdtCodeManage.do"),{"cmd":"listCode", "prjtCd":"${vo.prjtCd}", "codeCtgrCd": ItemVO.codeCtgrCd});
	}
	
	/**
	 * Call the code write form.
	 */
	function writeCodeForm() {
		$("#workArea").load(cUrl("/PrdtCodeManage.do"),{"cmd":"addCodeForm", "prjtCd":"${vo.prjtCd}", "codeCtgrCd": ItemVO.codeCtgrCd});
	}

	/**
	 * Call the code modified form.
	 */
	function editCodeForm(codeCd) {
		$("#workArea").load(cUrl("/PrdtCodeManage.do"),{"cmd":"editCodeForm", "prjtCd":"${vo.prjtCd}", "codeCtgrCd": ItemVO.codeCtgrCd, "codeCd":codeCd });
	}
	
	/**
	 * Sort code.
	 */
	function sortCode(sortString) {
		$.getJSON( cUrl( "/PrdtCodeManage.do"),
			{ "cmd":"sortCode", "prjtCd":"${vo.prjtCd}", "codeCtgrCd":ItemVO.codeCtgrCd, "codeCd":sortString },
			function(data) {
				alert(data.message);
				if(data.result >= 0) {
	 	  		} else {
	 	  			//-- on error
	 	  			listCtgr();
	 	  		}
			}
		);
	}

</script>
</mhtml:mng_html>