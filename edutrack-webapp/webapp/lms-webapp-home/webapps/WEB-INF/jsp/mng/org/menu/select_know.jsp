<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
							<div class="panel panel-default">
								<div class="panel-body">
									<table summary="" class="table table-bordered">
										<colgroup>
											<col width="15%">
										</colgroup>
										<tr>
											<th scope="col"><spring:message code="org.title.know.ctgr"/></th>
											<td>
												<label><input type="radio" name="orgKnowCtgrFlag" value="ALL" <c:if test="${empty vo.knowCtgrs }">checked="checked"</c:if> onclick="doCheckOrgKnowCtgrFlag();"><spring:message code="org.title.know.all"/></label><br>
												<label><input type="radio" name="orgKnowCtgrFlag" value="SEL" <c:if test="${not empty vo.knowCtgrs }">checked="checked"</c:if> onclick="doCheckOrgKnowCtgrFlag();"><spring:message code="org.title.know.sel"/></label><br>
												<c:forEach items="${orgKnowCtgrCdList }" var="data" varStatus="status">
													<label><input type="checkbox" name="orgKnowCtgr" value="${data.knowCtgrCd }" <c:if test="${fn:contains(vo.knowCtgrs, data.knowCtgrCd) }">checked="checked"</c:if>>${data.knowCtgrNm }</label>
												</c:forEach>
											</td>
										</tr>
										<tr>
											<th scope="col"><spring:message code="org.title.know.type"/></th>
											<td>
												<label><input type="radio" name="knowTypeFlag" value="ALL" <c:if test="${empty vo.knowTypes }">checked="checked"</c:if> onclick="doCheckKnowTypeFlag();"><spring:message code="org.title.know.all"/></label><br>
												<label><input type="radio" name="knowTypeFlag" value="SEL" <c:if test="${not empty vo.knowTypes }">checked="checked"</c:if> onclick="doCheckKnowTypeFlag();"><spring:message code="org.title.know.sel"/></label><br>
												<label><input type="checkbox" name="upperKnowType" value="MOV" <c:if test="${fn:contains(vo.knowTypes, 'MOV') }">checked="checked"</c:if>><spring:message code="org.title.know.mov"/></label>
												<label><input type="checkbox" name="upperKnowType" value="DOC" <c:if test="${fn:contains(vo.knowTypes, 'DOC') }">checked="checked"</c:if>><spring:message code="org.title.know.doc"/></label>
											</td>
										</tr>
									</table>
									<div class="text-center" style="margin-top:10px;">
										<a href="#none" onclick="doMakeUrl()" class="btn btn-primary btn-sm"><spring:message code="org.btn.know.url"/></a>
									</div>
								</div>
							</div>
							<form id="knowSelectForm">
								<input type="hidden" id="ctgrs">
								<input type="hidden" id="types">
							</form>
								
								<script type="text/javascript">
									$(function(){
										doCheckOrgKnowCtgrFlag();
										doCheckKnowTypeFlag();
									})
									
									function doCheckOrgKnowCtgrFlag(){
										if($("input[name=orgKnowCtgrFlag]:checked").val() == "ALL"){
											$("input[name=orgKnowCtgr]:checkbox").attr("disabled", "disabled");
										}else{
											$("input[name=orgKnowCtgr]:checkbox").removeAttr("disabled");
										}
									}
									
									function doCheckKnowTypeFlag(){
										if($("input[name=knowTypeFlag]:checked").val() == "ALL"){
											$("input[name=upperKnowType]:checkbox").attr("disabled", "disabled");
										}else{
											$("input[name=upperKnowType]:checkbox").removeAttr("disabled");
										}
									}
									
									function doMakeKnowCtgrStr(){
										if($("input[name=orgKnowCtgrFlag]:checked").val() != "ALL"){
											var knowCtgrObj = $("input[name=orgKnowCtgr]:checked");
											var knowCtgrValObj = new Array();
											$.each(knowCtgrObj, function(index, data){
												knowCtgrValObj.push($(data).val());
											})
											
											$("#ctgrs").val(knowCtgrValObj.join(','));
										}else{
											$("#ctgrs").val("");
										}
									}
									
									function doMakeKnowTypeStr(){
										if($("input[name=knowTypeFlag]:checked").val() != "ALL"){
											var knowTypeObj = $("input[name=upperKnowType]:checked");
											var knowTypeValObj = new Array();
											$.each(knowTypeObj, function(index, data){
												knowTypeValObj.push($(data).val());
											})
											
											$("#types").val(knowTypeValObj.join(','));
										}else{
											$("#types").val("");
										}
									}
									
									function doMakeUrl(){
										doMakeKnowCtgrStr();
										doMakeKnowTypeStr();
										
										var map = {};
										map['ctgrs'] = $("#ctgrs").val();
										map['types'] = $("#types").val();
										
								 		$.getJSON (
								 			cUrl("/mng/org/menu/makeUrl"),
								 			map, 
								 			function(data) { 
								 				var url = "/KnowHome.do?cmd=main&ctgrs=" + data.ctgrs + "&types=" + data.types;
								 				$("#menuUrl").val(url);
								 			}
								 		);
									}
								
								</script>