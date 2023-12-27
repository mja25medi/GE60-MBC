<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<div style="float:left">
		<h4>
			<i class="fa fa-th-large"></i>
			<spring:message code="org.title.domain.manage"/>
		</h4>
	</div>
	<div style="height:25px;float:right">
		<button class="btn btn-default btn-sm" onclick="closeWrite()" ><spring:message code="button.close" /></button>
	</div>
	<div id="domainListArea">
		<table summary="<spring:message code="org.title.orgmenu.manage"/>" style="width:100%" class="table table-bordered">
			<colgroup>
				<col style="width:100px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:60px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="org.title.domain.representative"/></th>
					<th scope="col"><spring:message code="org.title.orginfo.domain"/></th>
					<th scope="col"><spring:message code="common.title.type"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4"><spring:message code="org.message.domain.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<h4>
				<i class="fa fa-th-large"></i>
				<spring:message code="org.title.domain.add"/>
			</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="alert alert-info">
				<form name="orgDomainForm" id="orgDomainForm" onsubmit="return false" method="POST">
				<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
				<div class="input-group">
					<span style="float:left;margin-right:30px;"><spring:message code="org.title.orginfo.domain"/></span>
					<div class="input-group">
						<span class="input-group-addon">http:// </span>
						<input type="text" name="orgDomain" id="orgDomain" maxlength="100" required="required" title="<spring:message code="org.title.orginfo.domain"/>" class="form-control input-sm"/>
						<span class="input-group-addon">도메인 타입</span>
	    				<select name="domainTypeCd" id="domainTypeCd" class="form-control input-sm" >
	    					<c:forEach var="domainType" items="${domainTypeList}">
								<option value="${domainType.codeCd}" <c:if test="${domainType.codeCd eq vo.domainTypeCd}">selected</c:if>>${domainType.codeNm}</option>
							</c:forEach>
						</select>
						<span class="input-group-addon">서비스 타입</span>
	    				<select name="siteServiceTypeCd" id="siteServiceTypeCd" class="form-control input-sm" >
	    					<c:forEach var="siteServiceType" items="${siteServiceTypeList}">
								<option value="${siteServiceType.codeCd}" <c:if test="${siteServiceType.codeCd eq vo.siteServiceTypeCd}">selected</c:if>>${siteServiceType.codeNm}</option>
							</c:forEach>
						</select>
	    			</div>
	    			<span class="input-group-btn">
        				<button class="btn btn-primary btn-sm" type="button" onclick="addDomain()"><spring:message code="button.add"/></button>
      				</span>
	    		</div>
	    		</form>
			</div>
		</div>
	</div>

<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		domainList();
	});

	function closeWrite() {
		parent.modalBoxClose();
	}

	/**
	 *  메뉴 목록 호출
	 */
	function domainList() {
		$('#domainListArea').load(
				cUrl("/adm/org/domain/list"), { "orgCd":"${vo.orgCd}" });
	}

	function addDomain() {
		if(!validate(document.orgDomainForm)) return;
		process("/adm/org/domain/add");
	}

	function deleteDomain(orgDomain) {
		$("#orgDomain").val(orgDomain);
		process("/adm/org/domain/remove");
	}

	function changeRprst(orgDomain) {
		$("#orgDomain").val(orgDomain);
		process("/adm/org/domain/change");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#orgDomainForm').attr("action", cmd);
		$('#orgDomainForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			domainList();
			$("#orgDomain").val("");
			parent.listPageing(1);
		} else {
			// 비정상 처리
		}
	}
</script>
