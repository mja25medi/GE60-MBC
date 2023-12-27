<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<% // 서브 메뉴 체크 %>
<c:set var="orgMenuVO" value="${orgMenuVO}" scope="request"/>
<c:set var="chkSubMenu" value="N"/>
<c:forEach items="${orgMenuVO.subList}" var="item" varStatus="status">
	<c:if test="${item.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/></c:if>
	<c:forEach items="${item.subList}" var="item1">
		<c:if test="${item1.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/><c:set var="chkSubMenu" value="Y"/></c:if>
		<c:forEach items="${item1.subList}" var="item2">
			<c:if test="${item2.menuCd eq MENUCODE}"><c:set var="chkedmenu" value="${item.menuCd}"/><c:set var="chkSubMenu" value="Y"/></c:if>
		</c:forEach>
	</c:forEach>
</c:forEach>
				<div class="content-section <c:if test="${chkSubMenu eq 'N'}">full-column</c:if>">
					<form id="frm">
					<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex }">
					<input type="hidden" id="bbsCd" name="bbsCd" value="${vo.bbsCd }">
					<input type="hidden" id="atclSn" name="atclSn">
					
					<div class="contentWrap">
						<div class="srhBtn-option">
							<span class="totalNum"><spring:message code="home.board.totcnt" arguments="${pageInfo.totalRecordCount }"/></span>
							<select id="searchKey" name="searchKey" title="<spring:message code="home.board.sesarchtype"/>">
								<option value="ALL"><spring:message code="home.board.all"/></option>
								<option value="title" <c:if test="${vo.searchKey eq 'title'}">selected="selected"</c:if>><spring:message code="home.board.title"/></option>
								<option value="cts" <c:if test="${vo.searchKey eq 'cts'}">selected="selected"</c:if>><spring:message code="home.board.cts"/></option>
							</select>
							<span class="srh_input">
								<input type="text" id="searchValue" name="searchValue" title="<spring:message code="home.board.inputsearchvalue"/>" value="<c:out value="${vo.searchValue }"/>" placeholder="<spring:message code="home.board.titlecontents"/>" class="_enterBind">
								<input type="text" title="dummy" style="display: none;"/>
								<a class="srhBtn" href="javascript:void(0);" id="btn_search" onclick="listPaging(1);"><span><i class="fa fa-search"></i></span></a>
							</span>
							<c:if test="${bbsInfoVo.writeUseYn eq 'Y' && not empty sessionScope.USERNO}">
							<div class="btn_opt">
								<a href="javascript:void(0);" class="btn btn-red" onclick="addAtcl();"><spring:message code="button.write.article"/></a>
							</div>
							</c:if>
						</div>
						<div class="post_gallery">
						<c:if test="${empty bbsAtclList }">
							<center style="line-height: 150px; font-size: 20px;"><spring:message code="home.board.nodata"/></center>
						</c:if>
							<c:forEach items="${bbsAtclList }" var="item">
							<ul>
								<li class="post_label" style="min-height: 20px;">
									<c:if test="${not empty item.headCd }">
										<em>${item.headNm}</em>
									</c:if>
									<c:if test="${item.cmntCnt > 0 }">
										<em>댓글 : ${item.cmntCnt}</em>
									</c:if>
								</li>
								<!-- 이미지 영역 -->
								<li>
									<c:choose>
									<c:when test="${item.lockYn eq 'Y'}">
										<c:choose>
											<c:when test="${item.regNo eq USERNO }">
												<a href="javascript:viewAtcl('${item.atclSn}');" class="view-img">
													<c:if test="${item.thumbFileSn eq '0' || empty item.thumbFileSn }"><img src="<c:url value="/img/noimage_image.png"/>" alt="${item.atclTitle}"></c:if>
													<c:if test="${item.thumbFileSn ne '0' && not empty item.thumbFileSn}"><img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" alt="${item.atclTitle}"></c:if>
												</a>
											</c:when>
											<c:otherwise>
												<a href="javascript:alert('<spring:message code="home.board.noauth"/>');" class="view-img">
													<c:if test="${item.thumbFileSn eq '0' || empty item.thumbFileSn }"><img src="<c:url value="/img/noimage_image.png"/>" alt="${item.atclTitle}"></c:if>
													<c:if test="${item.thumbFileSn ne '0' && not empty item.thumbFileSn}"><img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" alt="${item.atclTitle}"></c:if>
												</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<a href="javascript:viewAtcl('${item.atclSn}');" class="view-img">
											<c:if test="${item.thumbFileSn eq '0' || empty item.thumbFileSn }"><img src="<c:url value="/img/noimage_image.png"/>" alt="${item.atclTitle}"></c:if>
											<c:if test="${item.thumbFileSn ne '0' && not empty item.thumbFileSn}"><img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" alt="${item.atclTitle}"></c:if>
										</a>
									</c:otherwise>
									</c:choose>
								</li>
								<!-- 제목 영역 -->
								<li>
									<c:choose>
									<c:when test="${item.lockYn eq 'Y'}">
										<c:choose>
											<c:when test="${item.regNo eq USERNO }">
												<a href="javascript:viewAtcl('${item.atclSn}');" class="subject">
													<img src="<c:url value="/img/common/lock.gif"/>"  alt="Lock"/>
													${item.atclTitle}
												</a>
											</c:when>
											<c:otherwise>
												<a href="javascript:alert('<spring:message code="home.board.noauth"/>');" class="subject">
													<img src="<c:url value="/img/common/lock.gif"/>"  alt="Lock"/>
													${item.atclTitle}
												</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<a href="javascript:viewAtcl('${item.atclSn}');" class="subject">
											${item.atclTitle}
										</a>
									</c:otherwise>
									</c:choose>
								</li>
								<li class="post_disc">
									<ul>
										<li><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></li>
										<li><spring:message code="home.board.hits"/> ${item.hits}</li>
									</ul>
								</li>
							</ul>
							</c:forEach>
						</div>
						<!-- 페이징 처리 pageInfo 가 있어야 함 -->
						<c:import url="/WEB-INF/jsp/common/home_paging.jsp"><c:param name="funcName" value="listPaging"/></c:import>
						<!-- //페이징 처리 -->
					</div>
					</form>
				</div>
	
	<script type="text/javascript">
	$(document).ready(function(){
	});
	
	//목록
	function listPaging(page){
		if(page == null){
			$("#frm #pageIndex").val(1);
		}else{
			$("#frm #pageIndex").val(page);
		}
		
		$("#frm").attr("action","/home/brd/bbs/listAtcl");
		$("#frm").submit();
	}
	
	/** 글 상세 화면으로 이동 */
	function viewAtcl(atclSn){
		$("#atclSn").val(atclSn);
		$("#frm").attr("action","/home/brd/bbs/viewAtcl");
		$("#frm").submit();
	}
	
	/** 글 등록 화면으로 이동 */
	function addAtcl() {
		if(!${IS_USER_LOGIN}){
			$("#_LOGIN_BTN")[0].click();
			return;
		}
		$("#frm").attr("action","/home/brd/bbs/addFormAtcl");
		$("#frm").submit();
	}
	</script>
