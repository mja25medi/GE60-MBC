<%@ page pageEncoding="utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="helpList" value="${helpList }"/>
<c:set var="createCourseVO" value="${createCourseVO }"/>
        <div class="board_top">
            <h4>${createCourseVO.crsCreNm }</h4>
        </div>
        <div class="res_tbl_wrap">
            <table>
                <thead>
                    <tr>
                        <th scope="col">목차명</th>
                        <th scope="col" width="15%">요청자</th>
                        <th scope="col" width="15%">요청시간</th>
                        <th scope="col" width="15%">수락</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${not empty helpList}">
	                	<c:forEach var="helpList" items="${helpList}">
		                    <tr>
		                        <td scope="row" class="title" data-label="목차명">${helpList.unitNm}</td>
		                        <td data-label="요청자">${helpList.userNm}(${helpList.userId})</td>
		                        <td data-label="요청시간"><meditag:dateformat type="7" delimeter="." property="${helpList.reqTime}"/></td>
		                        <td data-label="수락"><button class="btn type3" onclick="openPairCodingPop('${helpList.stdNo }','${helpList.asmtSn}', '${helpList.asmtSubSn}')">수락</button></td>
		                    </tr>
	                    </c:forEach>
                    </c:if>
                    <c:if test="${empty helpList}">
                    	<tr>
                    		<td class="txt-center"> 현재 요청이 없습니다.</td>
                    	</tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    <div class="modal_btns">
        <button type="button" class="btn" onclick="parent.modalBoxClose()">닫기</button>
    </div>
<script type="text/javascript">
function openPairCodingPop(stdNo, asmtSn, asmtSubSn) {
	var url = cUrl("/lec/bookmark/viewPairCodingPop")+"?stdNo="+stdNo+"&asmtSn="+asmtSn+"&asmtSubSn="+asmtSubSn;
	var winOption = "fullscreen=yes,left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=no,resizable=yes";
	var contentsWin = window.open(url, "contentsWin");
}

</script>
