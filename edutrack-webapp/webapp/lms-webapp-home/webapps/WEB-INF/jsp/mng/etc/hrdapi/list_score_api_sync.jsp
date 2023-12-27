<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="panel panel-default">
					<div class="panel-heading">
						조회건수 : ${etcApiSyncList.size()} 건
						, 전체건수 : ${totalCnt} 건
						<div class="pull-right">
							<button class="btn btn-primary btn-sm" type="button" style="float: right; margin-right: 5px; margin-top:-4px;" onclick="callReTotScoreHrdApi('${vo.syncStatus}','${vo.crsCd }');">전체 발송</button>
							<!-- <a class="btn btn-info btn-sm" href="javascript:">발송</a> -->
						</div>
					</div>
					<div class="panel-body" style="padding:0px;">
						<div style="width:100%; overflow:auto; background-color:white; padding:5px; line-height:20px; margin-top:5px;">
							<table class="table table-bordered wordbreak">
								<caption class="sr-only">조회리스트</caption>
								<colgroup>
									<col style="width:8%" />
									<col style="width:10%" />
									<col style="width:10%" />
									<col style="width:10%" />
									<col style="width:5%" />
									<col style="width:8%" />
									<col style="width:8%" />
									<col style="width:8%" />
									<col style="width:20%" />
									<col style="width:8%" />
									<col style="width:5%" />
								</colgroup>
								<thead>
										<tr>
											<th scope="col">num</th>
											<th scope="col">회원</th>
											<th scope="col">과정</th>
											<th scope="col">수업</th>
											<th scope="col">평가방법</th>
											<th scope="col">평가방법코드</th><!-- 시험 : examSn / 과제 : asmtSn / 진도 : unitCd  -->
											<th scope="col">API구분(시작/종료)</th>
											<th scope="col">LMS구분(시작/종료/평가)</th>
											<th scope="col">실패사유</th>
											<th scope="col">전송일</th>
											<th scope="col"></th>
										</tr>
									</thead>
								<tbody>
									<c:choose>
										<c:when test="${empty etcApiSyncList}">
											<tr>
												<td colspan="6"><spring:message code="common.message.nodata"/></td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${etcApiSyncList}" var="item" varStatus="status">
												<tr>
													<td class="text-center">${item.num }</td>
													<td class="text-center">${item.userAgentPk}</td>
													<td class="text-center">${item.courseAgentPk}</td>
													<td class="text-center">${item.classAgentPk}</td>
													<td class="text-center">${item.evalCd}</td>
													<td class="text-center">${item.lmsFlag}</td>
													<td class="text-center">${item.startEndFlag}</td>
													<td class="text-center">${item.lmsFlag}</td>
													<td class="text-center">${item.syncResultMsg}</td>
													<td class="text-center">
														<fmt:parseDate var="sendDate" value="${item.modDttm}" pattern="yyyyMMddHHmmss" />
														<fmt:formatDate value="${sendDate}" pattern="yyyy.MM.dd(E)HH:mm:ss"/>
													</td>
													<td class="text-center">
														<button class="btn btn-info btn-sm" type="button" onclick="javascript:callReSendScoreHrdApi('${item.num}', '${item.crsCd }')">재발송</button>
													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							<meditag:paging pageInfo="${pageInfo}" funcName="listScoreApiSync"/>
						</div>
					</div>
				</div>

<script type="text/javascript">
	$(document).ready(function() {

	});

	function callReSendScoreHrdApi(num, crsCd) {
		$(".overlay").show();
		$.ajax({
			url:cUrl("/mng/etc/HrdApi/callScoreHrdApi"),
			type:"POST", 
			data:{
				"num" : num
				,"crsCd" : crsCd
				,"syncGubunCd": "SCORE"
			},
			success:function(data){
				$(".overlay").hide();
				if(data.result > 0) {
					alert("전송에 성공하였습니다.(내용:"+data.message+")")							
				} else {
					alert("전송에 실패하였습니다.(내용:"+data.message+")")							
				}
				listScoreApiSync();
				if(typeof parent.listScoreInfo == 'function'){
					parent.listScoreInfo();
				}
			},error:function(e){
				$(".overlay").hide();
				alert("장애가 발생하였습니다.(내용:"+JSON.stringify(e)+")\n잠시 후에 이용해 주시기 바랍니다.")
			}
		});
		
	}
	function callReTotScoreHrdApi(syncStatus, crsCd) {
		$(".overlay").show();
		$.ajax({
			url:cUrl("/mng/etc/HrdApi/callScoreHrdApi"),
			type:"POST", 
			data:{
				"syncGubunCd": "SCORE"
				,"crsCd" : crsCd
				,"syncStatus": syncStatus
			},
			success:function(data){
				$(".overlay").hide();
				if(data.result > 0) {
					alert("전송에 성공하였습니다.(내용:"+data.message+")")							
				} else {
					alert("전송에 실패하였습니다.(내용:"+data.message+")")							
				}
				listScoreApiSync();
				if(typeof parent.listScoreInfo == 'function'){
					parent.listScoreInfo();
				}
			},error:function(e){
				$(".overlay").hide();
				alert("장애가 발생하였습니다.(내용:"+JSON.stringify(e)+")\n잠시 후에 이용해 주시기 바랍니다.")
			}
		});
		
	}
</script>
