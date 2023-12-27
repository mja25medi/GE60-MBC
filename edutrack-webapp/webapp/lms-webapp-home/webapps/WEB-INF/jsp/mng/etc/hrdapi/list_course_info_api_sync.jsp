<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="panel panel-default">
					<div class="panel-heading">
						조회건수 : ${courseApiSyncList.size()} 건
						, 전체건수 : ${totalCnt} 건
						<div class="pull-right">
							<button class="btn btn-primary btn-sm" type="button" style="float: right; margin-right: 5px; margin-top:-4px;" onclick="callReTotCourseInfoHrdApi('${vo.syncStatus}');">전체 발송</button>
							<!-- <a class="btn btn-info btn-sm" href="javascript:">발송</a> -->
						</div>
					</div>
					<div class="panel-body" style="padding:0px;">
						<div style="width:100%; overflow:auto; background-color:white; padding:5px; line-height:20px; margin-top:5px;">
							<table class="table table-bordered wordbreak">
								<caption class="sr-only">조회리스트</caption>
								<colgroup>
									<col style="width:10%" />
									<col style="width:20%" />
									<col style="width:20%" />
									<col style="width:30%" />
									<col style="width:15%" />
									<col style="width:5%" />
								</colgroup>
								<thead>
										<tr>
											<th scope="col">num</th>
											<th scope="col">PK</th>
											<th scope="col">PK명</th>
											<th scope="col">내용</th>
											<th scope="col">전송일</th>
											<th scope="col"></th>
										</tr>
									</thead>
								<tbody>
									<c:choose>
										<c:when test="${empty courseApiSyncList}">
											<tr>
												<td colspan="6"><spring:message code="common.message.nodata"/></td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${courseApiSyncList}" var="item" varStatus="status">
												<tr>
													<td class="text-center">${item.num}</td>
													<td class="text-center">${item.courseAgentPk}</td>
													<td class="text-center">${item.name}</td>
													<td class="text-center">${item.syncResultMsg}</td>
													<td class="text-center">
														<fmt:parseDate var="sendDate" value="${item.modDttm}" pattern="yyyyMMddHHmmss" />
														<fmt:formatDate value="${sendDate}" pattern="yyyy.MM.dd(E)HH:mm:ss "/>
													</td>
													<td class="text-center">
														<button class="btn btn-info btn-sm" type="button" onclick="javascript:callReSendCourseInfoHrdApi('${item.num}','${item.courseAgentPk}')">
															<c:if test="${vo.syncStatus eq 'F' }">재발송</c:if>
															<c:if test="${vo.syncStatus ne 'F' }">발송</c:if>
														</button>
													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							<meditag:paging pageInfo="${pageInfo}" funcName="listCourseInfoApiSync"/>
						</div>
					</div>
				</div>

<script type="text/javascript">
	$(document).ready(function() {

	});

	function callReSendCourseInfoHrdApi(num,courseAgentPk) {
		$.ajax({
			url:cUrl("/mng/etc/HrdApi/callCourseInfoHrdApi"),
			type:"POST", 
			data:{
				"num" : num
				,"courseAgentPk": courseAgentPk
			},
			success:function(data){
				if(data.result > 0) {
					alert(data.message)							
				} else {
					alert("전송에 실패하였습니다.(내용:"+data.message+")")							
				}
				listCourseInfoApiSync();
				if(typeof parent.listCourseApiInfo == 'function'){
					parent.listCourseApiInfo();
				}
			},error:function(e){
				//alert("ERROR==="+JSON.stringify(e));
				alert("장애가 발생하였습니다.(내용:"+JSON.stringify(e)+")\n잠시 후에 이용해 주시기 바랍니다.")
			}
		});
		
	}
	function callReTotCourseInfoHrdApi(syncStatus) {
		$.ajax({
			url:cUrl("/mng/etc/HrdApi/callCourseInfoHrdApi"),
			type:"POST", 
			data:{
				"syncStatus" : syncStatus
			},
			success:function(data){
				if(data.result > 0) {
					alert(data.message)							
				} else {
					alert("전송에 실패하였습니다.(내용:"+data.message+")")							
				}
				listCourseInfoApiSync();
				if(typeof parent.listCourseApiInfo == 'function'){
					parent.listCourseApiInfo();
				}
			},error:function(e){
				//alert("ERROR==="+JSON.stringify(e));
				alert("장애가 발생하였습니다.(내용:"+JSON.stringify(e)+")\n잠시 후에 이용해 주시기 바랍니다.")
			}
		});
	}
</script>
