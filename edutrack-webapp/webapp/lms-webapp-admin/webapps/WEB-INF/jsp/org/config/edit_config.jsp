<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<section class="content">
<div class="box">
<div class="box-body">
	<div class="row" id="content">
		<div class="col-lg-12">
			<ul class="nav nav-tabs" id="tabMenu">
				<li><a href="/adm/org/orginfo/editInfoFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.defaultinfo.manage"/></a></li>
		  		<li class="active"><a href="/adm/org/config/editCfgFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.orgdata.manage"/></a></li>
		  		<li><a href="/adm/org/orginfo/editTemplateFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.template.manage"/></a></li>
		  		<li><a href="/adm/org/orginfo/editDesignFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.design.manage"/></a></li>
		  		<li><a href="/adm/org/crscert/editCertFormMain?orgCd=${vo.orgCd}"><spring:message code="org.title.crscert.manage"/></a></li>
		  		<li><a href="/adm/org/menu/main?orgCd=${vo.orgCd}"><spring:message code="org.title.orginfo.menu.manage"/></a></li>
			</ul>
			<div class="pull-right" style="margin-top:-35px;">
				<a class="btn btn-default btn-sm" href="javascript:closeWrite()"><spring:message code="button.list"/></a>
			</div>
		</div>
		<form name="userInfoCfgForm" id="userInfoCfgForm" onsubmit="return false;" method="POST">
		<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}" />
		<input type="hidden" name="fieldNms" id="fieldNms" value="" />
		<input type="hidden" name="fieldNm" id="fieldNm" value="" />
		<input type="hidden" name="useYn" id="useYn" value="" />
		<input type="hidden" name="requiredYn" id="requiredYn" value="" />
		<input type="hidden" name="viewYn" id="viewYn" value="" />
		<input type="hidden" name="dfltYn" id="dfltYn" value="" />
		<input type="hidden" name="viewOdr" id="viewOdr" value="" />
		<div class="col-lg-12" style="margin-top: 10px;">
			<ul id="sortable" style="list-style: none; padding-left:0px;">
			<c:set var="display" value=""/>
			<c:set var="disabled" value=""/>
			<c:forEach items="${userInfoCfgList}" var="item" varStatus="status">
				<c:choose>
					<c:when test="${item.fieldNm eq 'USERID'}"><c:set var="disabled" value="disabled"/></c:when>
					<c:when test="${item.fieldNm eq 'USERNM'}"><c:set var="disabled" value="disabled"/></c:when>
					<c:otherwise><c:set var="disabled" value=""/></c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${item.fieldNm eq 'PHOTO'}"><c:set var="display" value="display: none;"/></c:when>
					<c:when test="${item.fieldNm eq 'FAXNO'}"><c:set var="display" value="display: none;"/></c:when>
					<c:when test="${item.fieldNm eq 'COMPPHONE'}"><c:set var="display" value="display: none;"/></c:when>
					<c:when test="${item.fieldNm eq 'ETCPHONE'}"><c:set var="display" value="display: none;"/></c:when>
					<c:when test="${item.fieldNm eq 'COMPADDR'}"><c:set var="display" value="display: none;"/></c:when>
					<c:when test="${item.fieldNm eq 'HOMEADDR'}"><c:set var="display" value="display: none;"/></c:when>
					<c:when test="${item.fieldNm eq 'INTEREST'}"><c:set var="display" value="display: none;"/></c:when>
					<c:when test="${item.fieldNm eq 'MEMO'}"><c:set var="display" value="display: none;"/></c:when>
					<c:otherwise><c:set var="display" value=""/></c:otherwise>
				</c:choose>
				<li style="margin-bottom:5px;" id="${item.fieldNm}">
					<div class="well well-sm" style="margin-bottom:0px;">
						<div class="col-md-4 col-xs-9">
							<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> <spring:message code="user.title.userinfo.manage.${fn:toLowerCase(item.fieldNm)}"/>
						</div>
						<div class="col-md-8 col-xs-9">
							<label style="float:left;font-weight: normal;margin-right: 30px;">
								<input type="checkbox" value="${item.fieldNm }" name="${item.fieldNm }_USE_YN"  id="${item.fieldNm }_USE_YN" <c:if test="${item.useYn eq 'Y' }">checked</c:if> onchange="updateUserInfoCfg('${item.fieldNm }','use')"  ${disabled }/>&nbsp;<spring:message code="common.title.useyn"/>
							</label>
							<label style="float:left;font-weight: normal;margin-right: 30px;">
								<input type="checkbox" value="${item.fieldNm }" name="${item.fieldNm }_REQUIRED_YN"  id="${item.fieldNm }_REQUIRED_YN" <c:if test="${item.requiredYn eq 'Y' }">checked</c:if> onchange="updateUserInfoCfg('${item.fieldNm }','required')" ${disabled } />&nbsp;<spring:message code="course.title.contents.required"/>
							</label>
							<label style="float:left;font-weight: normal;margin-right: 30px;${display}">
								<input type="checkbox" value="${item.fieldNm }" name="${item.fieldNm }_VIEW_YN"  id="${item.fieldNm }_VIEW_YN" <c:if test="${item.viewYn eq 'Y' }">checked</c:if> onchange="updateUserInfoCfg('${item.fieldNm }','view')"  ${disabled }/>&nbsp;<spring:message code="user.title.userinfo.manage.list"/>
							</label>
							<input type="hidden" name="${item.fieldNm }_DFLT_YN"  id="${item.fieldNm }_DFLT_YN"  value="${item.dfltYn }">
							<input type="hidden" name="${item.fieldNm }_VIEW_ODR"  id="${item.fieldNm }_VIEW_ODR"  value="${item.viewOdr }">				
						</div>
						<div style="clear:both;"></div>
					</div>
				</li>	
			</c:forEach>
			</ul>
		</div>
		</form>
	</div>
</div>
</div>
</section>

<script type="text/javascript">
	var modalBox = null;
	var sortString = "";
	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
	    $("#sortable").sortable({
	    	handle: 'i.glyphicon-move',
	    	stop : function() {
	    		sortString = "";
	    		$("#sortable").children('li').each(function(){
	    			sortString += "|"+$(this).attr("id"); 
	    		});
	    		sortString = sortString.substring(1);
	    		$("#fieldNms").val(sortString);
	    		$('#userInfoCfgForm').attr("action","/adm/org/config/sortCfg.do");
	    		$('#userInfoCfgForm').ajaxSubmit(processCallback);
	    	}
	    });
	    $("#sortable").disableSelection();
	});

	function updateUserInfoCfg(chkFieldNm,chkName){
		var fieldNm = chkFieldNm;
		var useYn = "N";
		var requiredYn = "N";
		var viewYn = "N";
		var dfltYn = $("#"+chkFieldNm+"_DFLT_YN").val();
		var viewOdr = $("#"+chkFieldNm+"_VIEW_ODR").val();

		if(chkName != "use"){
			if(!$("#"+chkFieldNm+"_USE_YN").is(":checked")){
				alert("<spring:message code="org.message.orginfo.checkuseyn"/>");
				if(chkName == "required"){
					$("#"+chkFieldNm+"_REQUIRED_YN").attr('checked', false);
				} else if(chkName == "view"){
					$("#"+chkFieldNm+"_VIEW_YN").attr('checked', false);
				}
				return;
			}
		}

		if($("#"+chkFieldNm+"_USE_YN").is(":checked")){
			useYn = "Y";
			if($("#"+chkFieldNm+"_REQUIRED_YN").is(":checked")){
				requiredYn = "Y";
			}
			if($("#"+chkFieldNm+"_VIEW_YN").is(":checked")){
				viewYn = "Y";
			}
		} else {
			$("#"+chkFieldNm+"_REQUIRED_YN").attr('checked', false);
			$("#"+chkFieldNm+"_VIEW_YN").attr('checked', false);
			requiredYn = "N";
			viewYn = "N";
		}

		$("#fieldNm").val(fieldNm);
		$("#useYn").val(useYn);
		$("#requiredYn").val(requiredYn);
		$("#viewYn").val(viewYn);
		$("#dfltYn").val(dfltYn);
		$("#viewOdr").val(viewOdr);
		$("#orgCd").val("${vo.orgCd}");

		$('#userInfoCfgForm').attr("action","/adm/org/config/editCfg.do");
		$('#userInfoCfgForm').ajaxSubmit(processCallback);
	}

	function processCallback(resultDTO) {
		//alert(resultDTO.returnMessage);
	}

	function closeWrite() {
		document.location.href = cUrl("/adm/org/orginfo/main")
	}
</script>
