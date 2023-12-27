<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

						<div class="col-lg-12">
							<ul class="nav nav-tabs" id="tabMenu">
								<li><a href="/mng/org/orgInfo/editInfoMain"><spring:message code="org.title.orginfo.defaultinfo.manage"/></a></li>
					<%-- 		<li><a href="//mng/org/userInfoCfg/editCfgMain"><spring:message code="org.title.orginfo.orgdata.manage"/></a></li> --%>	
						  		<li class="active"><a href="/mng/org/orgInfo/editTemplateMain"><spring:message code="org.title.orginfo.template.manage"/></a></li>
						  		<li><a href="/mng/org/orgInfo/editDesignMain"><spring:message code="org.title.orginfo.design.manage"/></a></li>
						  		<%-- <li><a href="/mng/org/crsCert/editCertMain"><spring:message code="org.title.crscert.manage"/></a></li> --%>
							</ul>
						</div>
						<div class="col-lg-12" style="margin-top:10px;">
							<form name="orgInfoForm" id="orgInfoForm" onsubmit="return false" method="POST" >
							<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
							<table summary="<spring:message code="org.title.orginfo.defaultinfo"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col width="80%"/>
								</colgroup>
								<input type="hidden" name="layoutTplCd" value="center"/>
								<c:if test="${empty tplList }">
								<input type="hidden" name="colorTplCd" value="dark"/>
								</c:if>
								<c:if test="${not empty tplList}">
								<tr>
									<td>
										<ul id="template" style="width:100%;list-style: none;padding-left:0px;">
										<c:forEach var="item" items="${tplList}">
											<c:set var="mainImg" value="/img/common/image.jpg"/>
											<c:if test="${item.mainImgFileSn > 0 }"><c:set var="mainImg" value="/app/file/thumb/${item.mainImgFileSn}"/></c:if>
											<c:set var="subImg" value="/img/common/image.jpg"/>
											<c:if test="${item.subImgFileSn > 0 }"><c:set var="subImg" value="/app/file/thumb/${item.subImgFileSn}"/></c:if>
											<li style="width:360px;min-height:200px; margin:5px 10px 5px 0px;float:left;border:1px solid #dedede;padding:5px;">
												<a href="#none" onclick="preview('${item.mainImgFileSn}')"><img src="<c:url value="${mainImg}"/>" style="width:170px;height:170px;float:left;margin-right:5px;"></a>
												<a href="#none" onclick="preview('${item.subImgFileSn}')"><img src="<c:url value="${subImg}"/>" style="width:170px;height:170px;float:left"></a><br/>
												<label style="margin-top:10px;">
													<input type="radio" name="colorTplCd" value="${item.tplCd}" <c:if test="${ not (vo.orgCd eq 'ORG0000066' or vo.orgCd eq 'ORG0000182') and (item.tplCd eq '001' or item.tplCd eq '006')}"> disabled="disabled"</c:if> <c:if test="${vo.colorTplCd eq item.tplCd}">checked</c:if>/> ${item.tplNm}</label>
											</li>
										</c:forEach>
										</ul>
									</td>
								</tr>
								</c:if>
							</table>
							<div class="text-right">
								<a href="javascript:reflectOrgInfo()" class="btn btn-primary btn-sm"><spring:message code="button.reflect"/> </a>
							</div>
							</form>
						</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">

	var modalBox = null;

	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		checkTemplate();
	});

	function checkTemplate() {
		var template = $(':input:radio[name=colorTplCd]:checked').val();
		if(template == '006') $(".sel_tpl").show();
		else $(".sel_tpl").hide();
	}

	/**
	 * 서브밋 처리
	 */
	function reflectOrgInfo() {
		$('#orgInfoForm').attr("action", "/mng/org/orgInfo/editTemplate");
		$('#orgInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
	}

	/**
	 * 이미지 미리보기
	 */
	function preview(fileSn) {
		if(fileSn != "0"){
			var addContent  = "<div style='width:100%;height:100%;overflow-y:auto;'><img src='/app/file/view2/"+fileSn+"' style='width:1050px;'></div>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(1100, 700);
			modalBox.setTitle("Preview Image");
			modalBox.show();
			
		}
	}

</script>
