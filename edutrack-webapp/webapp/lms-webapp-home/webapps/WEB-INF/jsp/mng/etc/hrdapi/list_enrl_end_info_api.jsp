<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
			<table summary='회원정보 api' class="table table-bordered">
				<colgroup>
					<col style="width:auto;min-width:90px;"/>
					<col style="width:auto;min-width:90px;"/>
					<col style="width:auto"/>
					<col style="width:auto;min-width:90px;"/>
					<col style="width:auto;min-width:90px;"/>
					<col style="width:150px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col" >api명</th>
						<th scope="col">전체수</th>
						<th scope="col">성공수</th>
						<th scope="col">실패수</th>
						<th scope="col">대기수</th>
						<th scope="col">등록</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty userInfoApiList}">
							<c:choose>
								<c:when test="${syncTotalCnt > 0}">
									<tr>
										<td class="text-center">회원 정보</td>
										<td class="text-center">${syncTotalCnt}</td>
										<td class="text-center">0</td>
										<td class="text-center">0</td>
										<td class="text-center">0</td>
										<td class="text-center">
											<button type="button" class="btn btn-info btn-sm" onclick="javascript:callUserInfoHrdApi();">발송</button>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="8"><spring:message code="common.message.nodata"/></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:set var="syncSuccessCnt" value="0"/>
							<c:set var="syncFailCnt" value="0"/>
							<c:set var="syncWaitCnt" value="0"/>
							<c:forEach items="${userInfoApiList}" var="item" varStatus="status">
								<c:choose>
									<c:when test="${item.syncStatus eq 'S'}">
										<c:set var="syncSuccessCnt" value="${syncSuccessCnt+1}"/>
									</c:when>
									<c:when test="${item.syncStatus eq 'F'}">
										<c:set var="syncFailCnt" value="${syncFailCnt+1}"/>
									</c:when>
									<c:otherwise>
										<c:set var="syncWaitCnt" value="${syncWaitCnt+1}"/>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<tr>
								<td class="text-center">회원 정보</td>
								<td class="text-center">${syncTotalCnt}</td>
								<td class="text-center">${syncSuccessCnt}</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${syncFailCnt > 0}">
											<a href="javascript:viewEtcApiSyncPop('USER_INFO','F');">${syncFailCnt}</a>
										</c:when>
										<c:otherwise>
											${syncFailCnt}
										</c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${syncWaitCnt > 0}">
											<a href="javascript:viewEtcApiSyncPop('USER_INFO','W');">${syncWaitCnt}</a>
										</c:when>
										<c:otherwise>
											${syncWaitCnt}
										</c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">
									<button type="button" class="btn btn-info btn-sm" onclick="javascript:callUserInfoHrdApi();">발송</button>
									<button type="button" class="btn btn-info btn-sm" onclick="javascript:viewApiLogPop('USER_INFO');">이력</button>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
					
		<script type="text/javascript">
			var modalBox = null;
			var ItemDTO = {
					sortKey : ""
				};
			
			$(document).ready(function() {
				modalBox = new $M.ModalDialog({
					"modalid" : "modal1"
				});
				
			});
		
			function modalBoxClose() {
				modalBox.clear();
				modalBox.close();
			}
			
			function callUserInfoHrdApi() {
				var crsCd = $("#crsCd").val();
				
				if(crsCd != ''){
					$.ajax({
						url:cUrl("/mng/etc/HrdApi/callUserInfoHrdApi"),
						type:"POST", 
						data:{
							"crsCd" : crsCd
							,"syncGubunCd": "USER_INFO"
						},
						success:function(data){
							if(data.result > 0) {
								alert("전송에 성공하였습니다.")							
							} else {
								alert("전송에 실패하였습니다.(내용:"+data.message+")")							
							}
							listUserApiInfo();
						},error:function(e){
							//alert("ERROR==="+JSON.stringify(e));
							alert("장애가 발생하였습니다.(내용:"+JSON.stringify(e)+")\n잠시 후에 이용해 주시기 바랍니다.")
						}
					});
				}else{
					alert("기수를 선택하세요.");							
				}
				
			}
			
			function viewApiLogPop(syncGubunCd){
				var crsCd = $("#crsCd").val();
				if(crsCd != ''){
					var url = generateUrl("/mng/etc/HrdApi/viewApiLogPop",{"syncGubunCd":syncGubunCd,"crsCd":crsCd});
					var addContent  = "<iframe id='logApiFrame' name='logApiFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
					modalBox.clear();
					modalBox.addContents(addContent);
					modalBox.resize(800, 400);
					modalBox.setTitle("이력 조회");
					modalBox.show();
				}else{
					alert("기수를 선택하세요.");							
				}
			}
			
			function viewEtcApiSyncPop(syncGubunCd,syncStatus){
				var crsCd = $("#crsCd").val();
				if(crsCd != ''){
					var url = generateUrl("/mng/etc/HrdApi/viewEtcApiSyncPop",{"syncGubunCd":syncGubunCd,"crsCd":crsCd,"syncStatus":syncStatus});
					var addContent  = "<iframe id='failApiFrame' name='failApiFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
					modalBox.clear();
					modalBox.addContents(addContent);
					modalBox.resize(800, 400);
					modalBox.setTitle("실패 리스트 조회");
					modalBox.show();
				}else{
					alert("기수를 선택하세요.");							
				}
			}
			
		</script>
