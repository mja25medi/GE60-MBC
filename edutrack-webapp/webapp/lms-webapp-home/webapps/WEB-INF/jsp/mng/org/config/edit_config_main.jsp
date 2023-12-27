<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
						<div class="col-lg-12">
							<ul class="nav nav-tabs" id="tabMenu">
								<li><a href="/mng/org/orgInfo/editInfoMain"><spring:message code="org.title.orginfo.defaultinfo.manage"/></a></li>
						  		<li class="active"><a href="/mng/org/userInfoCfg/editCfgMain"><spring:message code="org.title.orginfo.orgdata.manage"/></a></li>
						  		<li><a href="/mng/org/orgInfo/editTemplateMain"><spring:message code="org.title.orginfo.template.manage"/></a></li>
						  		<li><a href="/mng/org/orgInfo/editDesignMain"><spring:message code="org.title.orginfo.design.manage"/></a></li>
						  		<%-- <li><a href="/mng/org/crsCert/editCertMain"><spring:message code="org.title.crscert.manage"/></a></li> --%>
							</ul>
						</div>
						<form name="userInfoCfgForm" id="userInfoCfgForm" onsubmit="return false;" method="POST" >
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
													<input type="checkbox" value="${item.fieldNm }" name="${item.fieldNm }_USE_YN"  id="${item.fieldNm }_USE_YN" <c:if test="${item.useYn eq 'Y' }">checked</c:if> onchange="updateUserInfoCfg('${item.fieldNm }','use')"  ${disabled }/>&nbsp;<spring:message code="common.title.useyn_y"/>
												</label>
												<label style="float:left;font-weight: normal;margin-right: 30px;">
													<input type="checkbox" value="${item.fieldNm }" name="${item.fieldNm }_REQUIRED_YN"  id="${item.fieldNm }_REQUIRED_YN" <c:if test="${item.requiredYn eq 'Y' }">checked</c:if> onchange="updateUserInfoCfg('${item.fieldNm }','required')" ${disabled } />&nbsp;<spring:message code="course.title.contents.required"/>
												</label>
												<label style="float:left;font-weight: normal;margin-right: 30px;${display}">
													<input type="checkbox" value="${item.fieldNm }" name="${item.fieldNm }_VIEW_YN"  id="${item.fieldNm }_VIEW_YN" <c:if test="${item.viewYn eq 'Y' }">checked</c:if> onchange="updateUserInfoCfg('${item.fieldNm }','view')"  ${disabled }/>&nbsp;<spring:message code="user.title.userinfo.manage.list"/>
												</label>
												<input type="hidden" name="${item.fieldNm }_DFLT_YN"  id="${item.fieldNm }_DFLT_YN"  value="${item.dfltYn }">
												<input type="hidden" name="${item.fieldNm }_VIEW_ODR"  id="${item.fieldNm }_VIEW_ODR"  value="${item.viewOdr }">	
												<c:if test="${item.fieldNm eq 'AREA'}"><a href="javascript:addCode('AREA_CD')" class="btn btn-primary btn-sm"><spring:message code="button.write.code"/></a></c:if>
												<c:if test="${item.fieldNm eq 'USERDIV'}"><a href="javascript:addCode('USER_DIV_CD')" class="btn btn-primary btn-sm"><spring:message code="button.write.code"/></a></c:if>
												<c:if test="${item.fieldNm eq 'DEPT'}"><a href="javascript:addCode('DEPT_CD')" class="btn btn-primary btn-sm"><spring:message code="button.write.code"/></a></c:if>
												<c:if test="${item.fieldNm eq 'JOB'}"><a href="javascript:addCode('JOB_CD')" class="btn btn-primary btn-sm"><spring:message code="button.write.code"/></a></c:if>			
											</div>
											<div style="clear:both;"></div>
										</div>
									</li>
								</c:forEach>
								<!-- 관심분야, 관심검색어 필수값 추가 -->
								<li style="margin-bottom:5px;">
									<div class="well well-sm" style="margin-bottom:0px;">
										<div class="col-md-4 col-xs-9">
											<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> <spring:message code="user.title.userinfo.manage.knowctgr"/>
										</div>
										<div class="col-md-8 col-xs-9">
											<label style="float:left;font-weight: normal;margin-right: 30px;">
												<input type="checkbox" disabled="disabled" checked="checked"/>&nbsp;<spring:message code="common.title.useyn_y"/>
											</label>
											<label style="float:left;font-weight: normal;margin-right: 30px;">
												<input type="checkbox" disabled="disabled" checked="checked"/>&nbsp;<spring:message code="course.title.contents.required"/>
											</label>
											<label style="float:left;font-weight: normal;margin-right: 30px;${display}">
												<input type="checkbox" disabled="disabled" checked="checked"/>&nbsp;<spring:message code="user.title.userinfo.manage.list"/>
											</label>
<%-- 											<a href="javascript:goKnowCtgrMain();" class="btn btn-primary btn-sm"><spring:message code="button.write.code"/></a> --%>
										</div>
										<div style="clear:both;"></div>
									</div>
								</li>
								<li style="margin-bottom:5px;">
									<div class="well well-sm" style="margin-bottom:0px;">
										<div class="col-md-4 col-xs-9">
											<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> <spring:message code="user.title.userinfo.manage.knowkeyword"/>
										</div>
										<div class="col-md-8 col-xs-9">
											<label style="float:left;font-weight: normal;margin-right: 30px;">
												<input type="checkbox" disabled="disabled" checked="checked"/>&nbsp;<spring:message code="common.title.useyn_y"/>
											</label>
											<label style="float:left;font-weight: normal;margin-right: 30px;">
												<input type="checkbox" disabled="disabled" checked="checked"/>&nbsp;<spring:message code="course.title.contents.required"/>
											</label>
											<label style="float:left;font-weight: normal;margin-right: 30px;${display}">
												<input type="checkbox" disabled="disabled" checked="checked"/>&nbsp;<spring:message code="user.title.userinfo.manage.list"/>
											</label>
										</div>
										<div style="clear:both;"></div>
									</div>
								</li>
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
	    		$('#userInfoCfgForm').attr("action", "/mng/org/userInfoCfg/sortCfg");
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

		$('#userInfoCfgForm').attr("action", "/mng/org/userInfoCfg/editCfg");
		$('#userInfoCfgForm').ajaxSubmit(processCallback);
	}

	function processCallback(result) {
		alert(result.message);
	}

	function closeWrite() {
		document.location.href = cUrl("/mng/org/userInfoCfg/?cmd=editCfgForm")
	}
	
	function addCode(codeType){
		var popTtitle = "";
		var url = generateUrl("/mng/user/codeInfo/mainCodePop",{ "codeCtgrCd": codeType});
		var addContent = "<iframe id='addCodeFrame' name='addCodeFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		if(codeType == "DEPT_CD"){
			popTtitle = "<spring:message code="user.title.user.dept.manage"/>";
		} else if(codeType == "AREA_CD"){
			popTtitle = "<spring:message code="user.title.user.areacd.manage"/>";
		} else if(codeType == "USER_DIV_CD"){
			popTtitle = "<spring:message code="user.title.user.divcd.manage"/>";
		} else if(codeType == "JOB_CD"){
			popTtitle = "<spring:message code="user.title.userinfo.manage.job"/>";
		} else{
			return;
		}
		
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 700);
		modalBox.setTitle(popTtitle);
		modalBox.show();
	}
	
	function goKnowCtgrMain(){
		location.href = "/mng/main/goMenuPage?mcd=MC00000101"
	}
	
</script>
