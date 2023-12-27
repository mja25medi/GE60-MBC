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
					<form id="bbsAtclForm" name="bbsAtclForm">
					<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex }">
					<input type="hidden" id="bbsCd" name="bbsCd" value="${vo.bbsCd }">
					<input type="hidden" id="atclSn" name="atclSn" value="${vo.atclSn}">
					<input type="hidden" id="curPage" name="curPage" value="${vo.curPage }">
					
					<div class="srhBtn-option">
						<div class="row" style="margin-bottom:5px;">
							<div class="col-md-8 col-sm-8 col-xs-10">
								<c:if test="${not empty bbsHeadList }">
								<select name="headCd" id="headCd" class="_enterBind form-control input-sm" title="<spring:message code="board.title.bbs.head.select"/>" style="float:left;max-width:110px;margin-right:10px;">
									<option value=""><spring:message code="board.title.bbs.head.select"/></option>
									<c:forEach var="item" items="${bbsHeadList}">
									<option value="${item.headCd}" <c:if test="${item.headCd eq vo.headCd}">selected="selected"</c:if>>${item.headNm}</option>
									</c:forEach>
								</select>
								</c:if>
								<select name="searchKey"	id="searchKey" class="_enterBind form-control input-sm" title="<spring:message code="common.title.select"/>" style="float:left;max-width:100px;margin-right:10px;">
									<option value="title"><spring:message code="common.title.title"/></option>
									<option value="regNm"><spring:message code="common.title.reguser"/></option>
									<option value="regDttm"><spring:message code="common.title.regdate"/></option>
									<option value="all"><spring:message code="common.title.all"/></option>
								</select>
								<div class="input-group" style="width:250px;">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.title.input.searchvalue"/>"/>
									<span class="input-group-addon btn_search" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-2 text-right">
								<c:if test="${bbsInfoVO.writeUseYn eq 'Y' && not empty sessionScope.USERNO}">
								<a href="javascript:atclWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write.article"/></a>
								</c:if>
							</div>
						</div>
					</div>
					</form>
					<div class="post_news">
						<div class="row">
							<div class="col-md-12">
								<ul class="list-group">
									<c:forEach var="item" items="${bbsAtclList}" varStatus="status">
		
									<li class="list-group-item">
										<div class="pull-left"><meditag:reply level="${item.atclLvl}" /></div>
										<div class="media wordbreak" style="min-height:60px;">
											<c:if test="${not empty item.thumbFileSn && item.thumbFileSn > 0}">
													<%-- <a class="pull-left" href="<c:url value="/BbsAtclHome.do"/>?cmd=read&amp;bbsAtclDTO.bbsCd=${bbsInfoDTO.bbsCd}&amp;bbsAtclDTO.atclSn=${item.atclSn}&amp;bbsAtclDTO.searchKey=${bbsAtclDTO.searchKey}&amp;bbsAtclDTO.searchValue=${bbsAtclDTO.searchValue}&amp;curPage=${bbsAtclForm.curPage}"> --%>
											<a class="pull-left" href="javascript: viewAtcl('${item.atclSn}','${item.lockYn }','${item.originRegNo }', '${item.regNo }','${item.parRegNo }' )">
												<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" style="height:100%;width: 130px;" alt="${item.atclTitle}" class="media-object"/>
											</a>
											</c:if>
											<div class="media-body">
												<%-- <a href="<c:url value="/BbsAtclHome.do"/>?cmd=read&amp;bbsAtclDTO.bbsCd=${bbsInfoDTO.bbsCd}&amp;bbsAtclDTO.atclSn=${item.atclSn}&amp;bbsAtclDTO.searchKey=${bbsAtclDTO.searchKey}&amp;bbsAtclDTO.searchValue=${bbsAtclDTO.searchValue}&amp;curPage=${bbsAtclForm.curPage}"> --%>
		 										<a href="javascript: viewAtcl('${item.atclSn}','${item.lockYn }','${item.originRegNo }', '${item.regNo }','${item.parRegNo }' )">
												<h4 class="media-heading">
		
													${item.atclTitle}&nbsp;
													<c:if test="${item.recently eq 'NEW' }"><img src="/img/common/new.png"  alt="New"/></c:if>
													<c:if test="${item.lockYn eq 'Y'}"><img src="/img/common/lock.gif"  alt="Lock"/></c:if>
												</h4>
													<c:choose>
													<c:when test="${item.lockYn eq 'Y'}">
														<c:choose>
															<c:when test="${isManage eq 'Y'}">${fn:substring(item.atclCtsStr,0, 130)}<c:if test="${fn:length(item.atclCtsStr) > 130 }">...</c:if></c:when>
															<c:when test="${USERNO eq item.originRegNo}">${fn:substring(item.atclCtsStr,0, 130)}<c:if test="${fn:length(item.atclCtsStr) > 130 }">...</c:if></c:when>
															<c:when test="${USERNO eq item.parRegNo}">${fn:substring(item.atclCtsStr,0, 130)}<c:if test="${fn:length(item.atclCtsStr) > 130 }">...</c:if></c:when>
															<c:otherwise><spring:message code="board.message.qna.alert.private"/></c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														${fn:substring(item.atclCtsStr,0, 130)}<c:if test="${fn:length(item.atclCtsStr) > 130 }">...</c:if>
													</c:otherwise>
													</c:choose>
		
		 											</a>
		 											<p>
		 											<c:if test="${not empty item.attachFiles}">
														<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status">
															<c:if test="${status.count  != 1}">| </c:if>
															<a href="<c:url value="/app/file/download/${fileItem.fileSn}"/>" onclick="javascript:fileDown('${fileItem.fileSn}');" title="${fileItem.fileNm}">
																<i class="glyphicon glyphicon-floppy-save"></i>${fileItem.fileNm}
															</a>
														</c:forEach>
													</c:if>
		 											</p>
		 											<p style="font-size:12px;color:gray;">
		 												${item.regNm} (${item.regId}) | <meditag:dateformat type="0" delimeter="." property="${item.regDttm}"/> |
		 												<spring:message code="common.title.hits"/> : ${item.hits} |
		 												<spring:message code="board.title.bbs.info.comment"/> : ${item.cmntCnt}
		 											</p>
											</div>
										</div>
										</li>
									</c:forEach>
									<c:if test="${empty bbsAtclList}">
									<li class="list-group-item"><spring:message code="board.message.bbs.atcl.nodata"/></li>
									</c:if>
								</ul>
							</div>
						</div>					
					</div>		
					<div class="row" style="margin-bottom:20px;">
						<div class="col-lg-12">
							<meditag:paging pageInfo="${pageInfo}" funcName="listAtcl"/>
						</div>
					</div>
				</div>

	
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listPaging(1);
			}
		}
		$(".btn_search").bind("click", function(event) {
			listPaging(1);
		});
	});
	
	//목록
	function listPaging(page){
		if(page == null){
			$("#bbsAtclForm #pageIndex").val(1);
		}else{
			$("#bbsAtclForm #pageIndex").val(page);
		}
		
		$("#bbsAtclForm").attr("action","/home/brd/bbs/listAtclMain");
		$("#bbsAtclForm").submit();
	}
	
	/** 글 상세 화면으로 이동 */
	function viewAtcl(atclSn, lockYn){
		if("Y" == lockYn){
			alert("<spring:message code="board.message.qna.alert.private"/>");
			return;
		}
		$("#atclSn").val(atclSn);
		$("#bbsAtclForm").attr("action","/home/brd/bbs/viewAtclMain");
		$("#bbsAtclForm").submit();
	}
	
	/** 글 등록 화면으로 이동 */
/* 	function addAtcl() {
		if(!${IS_USER_LOGIN}){
			$("#_LOGIN_BTN")[0].click();
			return;
		}
		$("#bbsAtclForm").attr("action","/home/brd/bbs/addFormAtcl");
		$("#bbsAtclForm").submit();
	} */
	</script>
