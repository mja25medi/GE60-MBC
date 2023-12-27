<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
			<table summary='회원로그인 api' class="table table-bordered">
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
						<c:when test="${empty userLoginApiList}">
							<tr>
								<td colspan="8"><spring:message code="common.message.nodata"/></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:set var="syncSuccessCnt" value="0"/>
							<c:set var="syncFailCnt" value="0"/>
							<c:set var="syncWaitCnt" value="0"/>
							<c:forEach items="${userLoginApiList}" var="item" varStatus="status">
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
								<td class="text-center">회원 로그인 정보</td>
								<td class="text-center">${syncTotalCnt}</td>
								<td class="text-center">${syncSuccessCnt}</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${syncFailCnt > 0}">
											<a href="javascript:viewUserLoginApiSyncPop('USER_LOGIN','F');">${syncFailCnt}</a>
										</c:when>
										<c:otherwise>
											${syncFailCnt}
										</c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${syncWaitCnt > 0}">
											<a href="javascript:viewUserLoginApiSyncPop('USER_LOGIN','W');">${syncWaitCnt}</a>
										</c:when>
										<c:otherwise>
											${syncWaitCnt}
										</c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">
									<button type="button" class="btn btn-info btn-sm" onclick="javascript:callUserLoginHrdApi();">발송</button>
									<button type="button" class="btn btn-info btn-sm" onclick="javascript:viewApiLogPop('USER_LOGIN');">이력</button>
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
					"modalid" : "modal2"
				});
				
			});
		
			function modalBoxClose() {
				modalBox.clear();
				modalBox.close();
			}
			
			function callUserLoginHrdApi() {
				$(".overlay").show();
				var searchFrom = $("#userLoginSearchFrom").val();
				var searchTo = $("#userLoginSearchTo").val();
				if(searchFrom != '' && searchTo != ''){
					$.ajax({
						url:cUrl("/mng/etc/HrdApi/callUserLoginHrdApi"),
						type:"POST", 
						data:{
							"searchFrom" : searchFrom,
							"searchTo" : searchTo,
							"syncStatus": "W",
							"syncGubunCd": "USER_LOGIN"
						},
						success:function(data){
							$(".overlay").hide();
							if(data.result > 0) {
								alert("전송에 성공하였습니다.")							
							} else {
								alert("전송에 실패하였습니다.(내용:"+data.message+")")							
							}
							listUserLoginApiInfo();
						},error:function(e){
							$(".overlay").hide();
							//alert("ERROR==="+JSON.stringify(e));
							alert("장애가 발생하였습니다.(내용:"+JSON.stringify(e)+")\n잠시 후에 이용해 주시기 바랍니다.")
						}
					});
				}else{
					alert("기간을 선택하세요.");							
				}
				
			}
			
			function viewApiLogPop(syncGubunCd){
				var searchFrom = $("#userLoginSearchFrom").val();
				var searchTo = $("#userLoginSearchTo").val();
				if(searchFrom != '' && searchTo != ''){
					var url = generateUrl("/mng/etc/HrdApi/viewApiLogPop",{"syncGubunCd":syncGubunCd,"searchFrom":searchFrom,"searchTo":searchTo});
					var addContent  = "<iframe id='logApiFrame' name='logApiFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
					modalBox.clear();
					modalBox.addContents(addContent);
					modalBox.resize(800, 400);
					modalBox.setTitle("이력 조회");
					modalBox.show();
				}else{
					alert("기간을 선택하세요.");							
				}
			}
			
			function viewUserLoginApiSyncPop(syncGubunCd,syncStatus){
				var searchFrom = $("#userLoginSearchFrom").val();
				var searchTo = $("#userLoginSearchTo").val();
				if(searchFrom != '' && searchTo != ''){
					var url = generateUrl("/mng/etc/HrdApi/viewUserLoginApiSyncPop",{"syncGubunCd":syncGubunCd,"searchFrom":searchFrom,"searchTo":searchTo,"syncStatus":syncStatus});
					var addContent  = "<iframe id='failApiFrame' name='failApiFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
					var title = "";
					modalBox.clear();
					modalBox.addContents(addContent);
					modalBox.resize(800, 400);
					if(syncStatus == 'F'){title = "실패 리스트 조회";}else{title = "대기 리스트 조회";}
					modalBox.setTitle(title);
					modalBox.show();
				}else{
					alert("기간을 선택하세요.");							
				}
			}
			
		</script>
