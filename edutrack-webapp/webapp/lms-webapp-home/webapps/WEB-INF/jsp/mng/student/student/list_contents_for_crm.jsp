<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#contentsTable th {background-color: #eee;}
</style>

	<table summary="수강내역" class="table table-bordered wordbreak custom-table" style="margin-top: 10px;" id="contentsTable">
		<colgroup>
			<col style="width:10%"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">차시</th>
				<th scope="col">강의명</th>	
				<th scope="col">수강여부</th>	
				<th scope="col">진도체크</th>	
				<th scope="col">진도율</th>	
				<th scope="col">상세이력</th>	
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty contentsList }">
				<c:forEach var="item" items="${contentsList }" varStatus="status">
					<c:if test="${item.unitLvl eq 1 }">
						<tr class="text-center">
							<td>${item.unitOdr }회차</td>
							<td>${item.unitNm }</td>
							<td>
								<c:choose>
									<c:when test="${item.studyYn eq 'Y'}">
										O
									</c:when>
									<c:otherwise>
										X
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${item.subCnt > 0}">
										${item.connPageCnt }페이지/${item.subCnt }페이지
									</c:when>
									<c:otherwise>
										-
									</c:otherwise>
								</c:choose>
							</td>
							<td>${item.prgrRatio }%</td>
							<td>
							<c:if test="${not empty item.stdNo }">
								<button class="btn btn-default btn-sm" onclick="listBookmarkLog('${item.stdNo }','${item.unitCd }');">상세이력</button>
							</c:if>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty contentsList }">
				<tr>
					<td colspan="6">내역이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	
<script type="text/javascript">
	var modalBox = null;
	
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 * 사용자 수정 폼
	 */
	function listBookmarkLog(stdNo, unitCd) {
		var url = generateUrl("/mng/std/student/listBookmarkLogPop",{"stdNo":stdNo, "unitCd":unitCd});
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1200, 700);
		modalBox.setTitle("상세이력");
		modalBox.show();
	}
</script>