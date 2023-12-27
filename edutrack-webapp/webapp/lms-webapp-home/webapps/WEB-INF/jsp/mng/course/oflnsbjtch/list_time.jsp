<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

					<table summary="시간 목룍 표" style="width:100%" class="table_list">
						<colgroup>
							<col style="width:70px"/>
							<col style="width:auto"/>
							<col style="width:120px"/>
							<col style="width:120px"/>
							<col style="width:80px"/>
							<col style="width:50px"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">강의실</th>
								<th scope="col">강의시작</th>
								<th scope="col">강의종료</th>
								<th scope="col">모니터링</th>
								<th scope="col">수정</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${timeList}" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${item.classViewNm}</td>
								<td><meditag:dateformat type="1" delimeter="." property="${item.startDt}"/> <c:if test="${not empty item.startTm}">${item.startHour}:${item.startMin}</c:if></td>
								<td><meditag:dateformat type="1" delimeter="." property="${item.endDt}"/> <c:if test="${not empty item.endTm}">${item.endHour}:${item.endMin}</c:if></td>
								<td>
									<c:if test="${item.viewYn eq 'Y'}">허용</c:if>
									<c:if test="${item.viewYn ne 'Y'}">허용안함</c:if>
								</td>
								<td><meditag:button func="javascript:editTime('${item.lecSn}');" title="수정" value="수정"/></td>
							</tr>							
							</c:forEach>
							<c:if test="${empty timeList}">
							<tr>
								<td colspan="6">* 등록된 강의 정보가 없습니다.</td>
							</tr>
							</c:if>
						</tbody>
					<script type="text/javascript">
					resizeForm();
					</script>