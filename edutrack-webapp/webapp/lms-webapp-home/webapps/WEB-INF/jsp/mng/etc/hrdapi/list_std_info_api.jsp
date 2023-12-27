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
						<c:when test="${not empty countMap}">
							<tr>
								<td class="text-center">수강 정보</td>
								<td class="text-center"><a href="javascript:viewStdApiResultPop('');">${countMap.totalCnt}</a></td>
								<td class="text-center"><a href="javascript:viewStdApiResultPop('S');">${countMap.successCnt}</a></td>
								<td class="text-center"><a href="javascript:viewStdApiResultPop('F');">${countMap.failCnt}</a></td>
								<td class="text-center"><a href="javascript:viewStdApiResultPop('W');">${countMap.waitCnt}</a></td>
								<td class="text-center">
									<button type="button" class="btn btn-info btn-sm" onclick="javascript:callStdInfoHrdApi();">발송</button>
									<button type="button" class="btn btn-info btn-sm" onclick="javascript:viewApiLogPop('ATTEND_INFO');">이력</button>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="8"><spring:message code="common.message.nodata"/></td>
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
			
			function callStdInfoHrdApi() {
				var crsCd = $("#crsCd_std").val();
				
				if(crsCd != ''){
					$.ajax({
						url:cUrl("/mng/etc/HrdApi/callStdInfoHrdApi"),
						type:"POST", 
						data:{
							"crsCd" : crsCd
							,"syncGubunCd": "STD_INFO"
						},
						success:function(data){
							if(data.result > 0) {
								alert("전송에 성공하였습니다.")							
							} else {
								alert("전송에 실패하였습니다.(내용:"+data.message+")")							
							}
							listStdApiInfo();
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
				var crsCd = $("#crsCd_std").val();
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
			
			function viewStdApiResultPop(syncStatus){
				var crsCd = $("#crsCd_std").val();
				if(crsCd != ''){
					var url = generateUrl("/mng/etc/HrdApi/viewStdApiResultPop",{"crsCd":crsCd,"syncStatus":syncStatus});
					var addContent  = "<iframe id='failApiFrame' name='failApiFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
					modalBox.clear();
					modalBox.addContents(addContent);
					modalBox.resize(800, 600);
					if(syncStatus == ''){
						modalBox.setTitle("전체 리스트 조회");
					}else if(syncStatus == 'S'){
						modalBox.setTitle("성공 리스트 조회");
					}else if(syncStatus == 'F'){
						modalBox.setTitle("실패 리스트 조회");
					}else if(syncStatus == 'W'){
						modalBox.setTitle("대기 리스트 조회");
					}
					modalBox.show();
				}else{
					alert("기수를 선택하세요.");							
				}
			}
			
		</script>
