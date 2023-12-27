<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<% request.setAttribute("vEnter", "\n"); %>
<c:url var="img_base" value="/img/home" />
<c:set var="forumVO" value="${vo}" />
<c:set var="forumAtclList" value="${forumAtclList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered">
							<caption class="sr-only"><spring:message code="lecture.title.forum.info"/></caption>
							<colgroup>
								<col style="width:20%" />
								<col />
								<col style="width:10%" />
								<col style="width:10%" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><spring:message code="lecture.title.forum.title"/></th>
									<td colspan="3" class="wordbreak">
										${forumVO.forumTitle}
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.forum.duration"/></th>
									<td>
										<meditag:dateformat type="0" delimeter="." property="${forumVO.forumStartDttm}"/> ~ <meditag:dateformat type="0" delimeter="." property="${forumVO.forumEndDttm}"/>
									</td>
									<th scope="row"><spring:message code="lecture.title.exam.score"/></th>
									<td>
									<c:if test="${forumVO.rsltYn eq 'Y' }">
										<c:choose>
											<c:when test="${forumVO.score > 0 }">
											<fmt:formatNumber value="${forumVO.score}" pattern="#.#" minFractionDigits="1"/>
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${forumVO.rsltYn ne 'Y' }">-</c:if>
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.forum.desc"/></th>
									<td colspan="3" class="wordbreak">
										${fn:replace(forumVO.forumCts,vEnter,"<br>")}
									</td>
								</tr>
							</tbody>
						</table>
						<div class="text-right">
							<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/></a>
						</div>
					</div>
				</div>
				<div class="row"  style="margin-top:10px;">
					<form id="forumForm" name="forumForm" class="form-inline" onsubmit="return false">
					<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
					<input type="hidden" name="forumSn" value="${vo.forumSn}" />
					<input type="hidden" name="gubun" value="${vo.gubun}" />
					<!-- 검색용 -->
					<input type="hidden" name="searchKey" value="${vo.searchKey}" />
					<input type="hidden" name="searchValue" value="${vo.searchValue}" />
					<input type="hidden" name="curPage" value="${vo.curPage}" />
					</form>
					<form name="SearchAtcl" id="SearchAtcl" onsubmit="return false;" class="form-inline">
					<div class="col-md-6 col-sm-6 mb5">
						<select name="searchKey" id="searchKey" class="form-control input-sm" style="float:left;width:30%">
							<option value="title" <c:if test="${ forumVO.searchKey eq 'title' }">selected="selected"</c:if>><spring:message code="common.title.title"/></option>
							<option value="regNm" <c:if test="${ forumVO.searchKey eq 'regNm' }">selected="selected"</c:if>><spring:message code="common.title.reguser"/></option>
						</select>
						<div class="input-group" style="float:left;width:70%">
							<input type="text" name="searchValue" class="form-control input-sm" value="${forumVO.searchValue}" placeholder="<spring:message code="common.title.all"/>" />
							<span class="input-group-addon" onclick="javascript:listAtcl(1);" style="cursor:pointer"><i class="fa fa-search"></i></span>
						</div>
						<civ class="clearfix"></civ>
					</div>
					<div class="col-md-6 col-sm-6 mb5">
						<span class="pull-right">
						<c:choose>
							<c:when test="${forumVO.proceedYn eq 'Y' }">
								<a href="javascript:writeForumAtclForm()" class="btn btn-primary btn-sm"><spring:message code="button.write.article"/></a>
							</c:when>
							<c:otherwise>
								<c:if test="${forumVO.periodAfterWriteYn eq 'Y' }">
									<a href="javascript:writeForumAtclForm()" class="btn btn-primary btn-sm"><spring:message code="button.write.article"/></a>
								</c:if>
							</c:otherwise>
						</c:choose>
						</span>
						<civ class="clearfix"></civ>
					</div>
					<div class="col-md-12">
						<ul class="list-group">
							<c:forEach var="item" items="${forumAtclList}" varStatus="status">
							<li class="list-group-item" style="clear: both;">
								<div class="pull-left"><meditag:reply level="${item.atclLvl}" /></div>
								<div class="wordbreak" style="min-height:60px;">

									<div class="media-body">
 										<a href="javascript:readForumAtcl('${item.atclSn}')">
										<h4 class="media-heading" title="${item.title }">
											${fn:substring(item.title,0, 30)}<c:if test="${fn:length(item.title) > 30 }">...</c:if>
										</h4>
										<p style="font-size:12px;color:gray;margin-top: 15px;">${item.regNm} |
											<meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/> |
											<spring:message code="common.title.hits"/> : ${item.hits} |
											<spring:message code="board.title.bbs.info.comment"/> : ${item.commentCount}
											<c:if test="${not empty item.attachFiles}">
											<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status">
												| <a href="<c:url value="/app/file/download/${fileItem.fileSn}"/>" onclick="javascript:fileDown('${fileItem.fileSn}');" title="${fileItem.fileNm}"><i class="glyphicon glyphicon-floppy-save"></i></a>
											</c:forEach>
										</c:if>
										</p>
										<div style="clear: both;"></div>
										</a>
									</div>
								</div>

								</li>
							</c:forEach>
							<c:if test="${empty forumAtclList}">
							<li class="list-group-item"><spring:message code="board.message.bbs.atcl.nodata"/></li>
							</c:if>
						</ul>
						<%-- <table class="table table-bordered">
							<colgroup>
								<col style="width:8%" />
								<col style="width:auto" />
								<col style="width:10%" />
								<col style="width:12%" />
								<col style="width:8%" />
							</colgroup>
							<thead>
							<tr>
								<th scope="col"><spring:message code="common.title.no"/></th>
								<th scope="col"><spring:message code="common.title.title"/></th>
								<th scope="col"><spring:message code="common.title.reguser"/></th>
								<th scope="col"><spring:message code="common.title.regdate"/></th>
								<th scope="col" class="rnone"><spring:message code="common.title.hits"/></th>
							</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${forumAtclList}" varStatus="status">
									<c:set var="cmntStr" value=""/>
									<c:if test="${item.commentCount > 0 }">
										<c:set var="cmntStr" value="<span class='badge'>${item.commentCount}</span>"/>
									</c:if>
									<tr>
										<td>${pageInfo.startPageNum - status.count + 1}</td>
										<td class="subject wordbreak">
											<meditag:reply level="${item.atclLvl}">
												<a href="javascript:readForumAtcl('${item.atclSn}')">${item.title}${cmntStr}</a>
											</meditag:reply>
										</td>
										<td>${item.regNm}</td>
										<td><meditag:dateformat property="${item.regDttm}" type="1" delimeter="." /></td>
										<td>${item.hits}</td>
									</tr>
								</c:forEach>
								<c:if test="${empty forumAtclList}">
									<tr>
										<td colspan="5"><spring:message code="lecture.message.forum.atcl.nodata"/></td>
									</tr>
								</c:if>
							</tbody>
						</table> --%>
						</form>
					</div>
				</div>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="listAtcl"/>
					</div>
				</div>

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
	* 목록으로 이동
	*/
	function goList() {
		document.location.href = cUrl("/lec/forum/main");
	}

	/**
	* 토론게시글 목록
	*/
	function listAtcl(page){
		var f = document.SearchAtcl;
		var searchKey = f.searchKey[f.searchKey.selectedIndex].value;
		var searchValue = f.searchValue.value;

		$('#forumForm').attr("action","/lec/forum/joinForumMain")
		.find('input[name=curPage]').val(page).end()
		.find('input[name=searchKey]').val(searchKey).end()
		.find('input[name=searchValue]').val(searchValue).end();
		document.forumForm.submit();
	}

	/**
	* 토론글 상세보기
	*/
	function readForumAtcl(atclSn){
		var addContent  = "<iframe id='userPasswordFrame' name='userPasswordFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/forum/readForumArticlePop"/>"
			+ "?forumSn=${forumVO.forumSn}&amp;isHitup=Y&amp;atclSn="+atclSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("토론글 조회");
		modalBox.show();
	}

	/**
	* 토론글 등록 폼
	*/
	function writeForumAtclForm(){
		var addContent  = "<iframe id='userPasswordFrame' name='userPasswordFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/forum/addForumArticlePop"/>"
			+ "?forumSn=${forumVO.forumSn}&amp;gubun=A'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="lecture.title.forum.atcl.write"/>");
		modalBox.show();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#forumForm').attr("action","/lec/forum/"+cmd)
		.submit();
	}
</script>
