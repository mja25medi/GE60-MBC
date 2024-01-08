<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
			
    <div class="board_info">
        <div class="page_info">
            <span class="total_page">강의수 <b>${fn:length(createCourseList)}</b>개 </span>
        </div>

		<form id="createCourseForm" name="createCourseForm" onsubmit="return false" class="board_search">
		<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
		<input type="hidden" name="crsOperMthd" id="crsOperMthd" value="${vo.crsOperMthd}"/>
		<input type="hidden" name="crsCtgrCd" id="crsCtgrCd" value="${vo.crsCtgrCd}"/>
	
		<fieldset class="form-row">
		<legend class="blind">과정 정렬</legend>

		<select class="form-select" name="sortKey" title="정렬 순서를 선택하세요." onchange="setSortKey(this.value,1)">
			<option value="">선택</option>
			<option value="REG_DTTM_DESC" <c:if test="${vo.sortKey eq 'REG_DTTM_DESC' }">selected</c:if>>최근 등록순</option>
			<option value="EDU_PRICE_DESC" <c:if test="${vo.sortKey eq 'EDU_PRICE_DESC' }">selected</c:if>>높은 가격순</option>
			<option value="EDU_PRICE_ASC" <c:if test="${vo.sortKey eq 'EDU_PRICE_ASC' }">selected</c:if>>낮은 가격순</option>
        </select>
		<select class="form-select" name="listScale" onchange="setSortKey('',1)" >
			<option value="10" <c:if test="${vo.listScale eq '10' }">selected</c:if>>10</option>
			<option value="20" <c:if test="${vo.listScale eq '20' }">selected</c:if>>20</option>
			<option value="50" <c:if test="${vo.listScale eq '50' }">selected</c:if>>50</option>
		</select>
		</form>  
		
        </fieldset>
	</div><!-- //board_info -->
                     
    <div class="course_list">
        <ul class="courseList">
    	<c:forEach items="${createCourseList}" var="item">
            <li>
                <div class="course_con">
                    <div class="course_img">
                        <a href="<c:url value="/home/course/readCourseCreateMain"/>?crsCreCd=${item.crsCreCd}&crsCd=${item.crsCd}">
                        	<img src="/app/file/thumb/${item.thumbFileSn }" alt="이미지" aria-hidden="true" onerror="this.style.display='none'">
                        </a>
                    </div>
                    <div class="course_info">
                        <div class="card_label">
                            <span class="label basic bcBlue">${item.crsCtgrNm } </span>
                        </div>
                        <div class="item">
                            <h2><a href="<c:url value="/home/course/readCourseCreateMain"/>?crsCreCd=${item.crsCreCd}&crsCd=${item.crsCd}&amp;searchMode=${courseVO.searchMode}">${item.crsCreNm} ${item.creTerm }회차</a></h2>
                            <ul>                                            
                                <li><span>교육신청기간</span><meditag:dateformat type="1" delimeter="." property="${item.enrlAplcStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.enrlAplcEndDttm}"/></li>
                                <li><span>수강기간</span><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
                                <li><span>교육비용</span>
                                	<c:choose>
                                 	<c:when test="${item.eduPrice eq 0 || item.eduPrice eq null}"><strong class="price">무료</strong></c:when>
                                 	<c:otherwise><strong class="price"><fmt:formatNumber value="${item.eduPrice}" pattern="#,###" />원</strong></c:otherwise>
                                 </c:choose>
                                </li>
                            </ul>
                        </div>                    
                    </div>
                    <div class="btn_apply">
                        <a href="<c:url value="/home/course/readCourseCreateMain"/>?crsCreCd=${item.crsCreCd}&crsCd=${item.crsCd}&amp;searchMode=${courseVO.searchMode}"><button class="btn type4">수강신청</button></a>
                    </div>
                </div>
            </li>
        </c:forEach>
        </ul>
    </div>
    
    <div class="board_pager">
         <span class="inner">
             <meditag:paging pageInfo="${pageInfo}" funcName="courseSearch" name="front"/>
         </span>
     </div>
     <!-- //board_pager -->
                  


<script type="text/javascript">

	var ItemVO = new Object();

	function courseSearch(page) {
		$('#createCourseForm')
		.find('input[name=curPage]').val(page).end()
		.attr("action","/home/course/listCourseMain")
		document.createCourseForm.submit();
	}

	
 	function setSortKey(key,page) {
		ItemVO.sortKey = key;
		courseSearch(1);
	} 
	
</script>

