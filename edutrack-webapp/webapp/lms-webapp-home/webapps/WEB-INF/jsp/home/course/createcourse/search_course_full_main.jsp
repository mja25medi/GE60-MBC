<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
			
    <div class="board_info">
        <div class="page_info">
            <span class="total_page">강의수 <b>${fn:length(courseList)}</b>개 </span>
        </div>

		<form id="createCourseForm" name="createCourseForm" onsubmit="return false" class="board_search">
		<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
		<input type="hidden" name="crsOperMthd" id="crsOperMthd" value="${vo.crsOperMthd}"/>
		<input type="hidden" name="crsCtgrCd" id="crsCtgrCd" value="${vo.crsCtgrCd}"/>
		<input type="hidden" name="searchValue" id="searchValue" value="${vo.searchValue}"/>
	
		<fieldset class="form-row">
		<legend class="blind">과정 정렬</legend>
		<select class="form-select" name="searchKey" onchange="setSortKey('',1)" >
			<option value="" 	selected="selected">과정별</option>
			<option value="ON"  <c:if test="${vo.searchKey eq 'ON' }">selected</c:if>>원격훈련과정</option>
			<option value="OF" <c:if test="${vo.searchKey eq 'OF' }">selected</c:if>>집체훈련과정</option>
			<option value="BL"  <c:if test="${vo.searchKey eq 'BL' }">selected</c:if>>혼합훈련과정</option>
		</select>
		<select class="form-select" name="sortKey" title="정렬 순서를 선택하세요." onchange="setSortKey(this.value,1)">
			<option value="">선택</option>
			<option value="REG_DTTM_DESC" <c:if test="${vo.sortKey eq 'REG_DTTM_DESC' }">selected</c:if>>최근 등록순</option>
			<option value="EDU_PRICE_DESC" <c:if test="${vo.sortKey eq 'EDU_PRICE_DESC' }">selected</c:if>>높은 가격순</option>
			<option value="EDU_PRICE_ASC" <c:if test="${vo.sortKey eq 'EDU_PRICE_ASC' }">selected</c:if>>낮은 가격순</option>
        </select>
		
		</form>  
		
        </fieldset>
	</div><!-- //board_info -->
   	<c:if test="${not empty courseList}">                  
    <div class="course_list">
        <ul class="courseList">
    	<c:forEach items="${courseList}" var="item">
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
                                 	<c:when test="${item.eduPrice eq 0}"><strong class="price">무료</strong></c:when>
                                 	<c:otherwise><strong class="price"><fmt:formatNumber value="${item.eduPrice}" pattern="#,###" />원</strong></c:otherwise>
                                 </c:choose>
                                </li>
                            </ul>
                        </div>                    
                    </div>
                    <div class="btn_apply">
                        <a href="<c:url value="/home/course/readCourseCreateMain"/>?crsCreCd=${item.crsCreCd}&crsCd=${item.crsCd}&amp;searchMode=${courseVO.searchMode}"><button class="btn type4">상세보기</button></a>
                    </div>
                </div>
            </li>
        </c:forEach>
        </ul>
    </div>
    </c:if>
    <c:if test="${empty courseList}">             
	 <div class="no_content">
	      <div class="no_text">
	          <i class="icon-cont-none ico f170"></i>
	          <span>찾으시는 검색 결과가 없습니다. </span>
	      </div>
	 </div> 
	 </c:if>


<script type="text/javascript">

	var ItemVO = new Object();

	function courseSearch(page) {
		$('#createCourseForm')
		.find('input[name=curPage]').val(page).end()
		.attr("action","/home/course/listSearchCourseFullMain")
		document.createCourseForm.submit();
	}

	
 	function setSortKey(key,page) {
		ItemVO.sortKey = key;
		courseSearch(1);
	} 
	
</script>

