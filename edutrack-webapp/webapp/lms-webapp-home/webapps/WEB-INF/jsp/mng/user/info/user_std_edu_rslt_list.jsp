<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#stdEduTable th {background-color: #eee;}
</style>

		<table class="table table-bordered wordbreak" id="stdEduTable" style="margin-top: 10px;">
			<colgroup>
				<col style="width:11%;"/>
				<col style="width:auto;"/>
				<col style="width:8%;"/>
				<col style="width:8%;"/>
				<col style="width:8%;"/>
				<col style="width:8%;"/>
				<col style="width:8%;"/>
				<col style="width:8%;"/>
				<col style="width:8%;"/>
				<col style="width:8%;"/>
			</colgroup>
			<thead>
				<tr>
					<th>기수</th>
					<th>과정</th>
					<th>진도</th>
					<th>시험</th>
					<th>과제</th>
					<th>진행단계평가</th>
					<th>합계</th>
					<th>진도율</th>
					<th>수료여부</th>
					<th>수료증명서</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eduResultList}" var="item" varStatus="status">
					<tr>
						<td class="text-center">${item.crsYear}년도 ${item.crsTerm}기</td>
						<td class="text-center">${item.crsCreNm}</td>
						<td class="text-center">${meditag:round(item.prgrScore,1)}점</td>
						<td class="text-center">${meditag:round(item.examScore,1)}점</td>
						<td class="text-center">${meditag:round(item.asmtScore,1)}점</td>
						<td class="text-center">${meditag:round(item.semiExamScore,1)}점</td>
						<td class="text-center">${meditag:round(item.totScore,1)}점</td>
						<td class="text-center">${item.prgrRate}%</td>
						<c:choose>
							<c:when test="${item.enrlSts eq 'C' }">
								<td class="text-center">수료</td>
							</c:when>
							<c:otherwise>
								<td class="text-center">미수료</td>
							</c:otherwise>
						</c:choose>
						<td class="text-center"><button class="btn btn-default btn-sm" onclick="javascript:printCerti('${item.crsCreCd}','${item.stdNo}');">발급</button></td>
					</tr>
				</c:forEach>
				<c:if test="${empty eduResultList}">
					<tr>
						<td colspan="10">성적정보가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<meditag:paging pageInfo="${pageInfo}" funcName="listStd"/> 

	
<script type="text/javascript">

function printCerti(crsCreCd, stdNo) {
	// download용 iframe이 없으면 만든다.
	if ( $("#_m_pdf_iframe").length == 0 ) {
		iframeHtml =
			'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
		$("body").append(iframeHtml);
	}
	// 폼에 action을 설정하고 submit시킨다.
	var url = cUrl("/home/student/printCert?crsCreCd="+crsCreCd+"&stdNo="+stdNo);
	$("#_m_pdf_iframe").attr("src",url);
}

</script>
	
