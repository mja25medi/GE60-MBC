<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}" >
	<mhtml:head_module paging="Y"/>
</mhtml:mng_head>
<mhtml:mng_body>
			<mhtml:mng_title title="${bbsInfoVO.bbsNm}" location="${MENUPATH}"/>
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-body">			
								<form name="bbsAtclForm" id="bbsAtclForm" onsubmit="return false" class="form-inline" method="POST">
								<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}"/>
								<input type="hidden" name="atclSn" id="atclSn" value="${vo.atclSn}"/>
								<input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex}"/>
								<div class="col-md-10 col-sm-10">
									<c:if test="${not empty bbsHeadList}">
									<select name="headCd" id="headCd" title="<spring:message code="board.title.bbs.head.select"/>" class="_enterBind form-control input-sm">
										<option value=""><spring:message code="board.title.bbs.head.select"/></option>
										<c:forEach var="item" items="${bbsHeadList}">
										<opiton value="${item.headCd}">${vo.headNm}</opiton>
										</c:forEach>
									</select>
									</c:if>
									<select name="searchKey" id="searchKey" title="<spring:message code="board.title.bbs.search.select"/>" class="_enterBind form-control input-sm">
										<option value="title"><spring:message code="common.title.title"/></option>
										<option value="regNm"><spring:message code="common.title.reguser"/></option>
									</select>
									<div class="input-group" style="width:160px">
										<input type="text" name="searchValue" id="searchValue" title="<spring:message code="common.title.input.searchvalue"/>" class="_enterBind form-control input-sm"/>
										<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
											<i class="fa fa-search"></i>
										</span>
									</div>
								</div>
								<div class="col-md-2 col-sm-2">
									<div class="text-right">
										<a href="javascript:addAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.write.article"/> </a>
										<a href="javascript:goBoardInfoMain();" class="btn btn-info btn-sm"><spring:message code="button.list"/> </a>
									</div>
								</div>
								</form>
								<div class="col-lg-12" style="margin-top:5px;margin-bottom:20px;">
									<ul class="list-group">
										<c:forEach var="item" items="${bbsAtclList}" varStatus="status">
										<li class="list-group-item" style="height:70px;">
											<div class="wordbreak" style="min-height:60px;">
												<div class="media-body">
													<a href="javascript:readAtcl(${item.atclSn})">
														<h4 class="media-heading" title="${item.atclTitle }">
														${fn:substring(item.atclTitle,0, 30)}<c:if test="${fn:length(item.atclTitle) > 30 }">...</c:if>&nbsp;
														<c:if test="${item.recently eq 'NEW' }"><img src="<c:url value="/img/common/new.png"/>"  alt="New"/></c:if></h4>
				 										<%-- ${fn:substring(item.atclCtsStr,0, 130)}<c:if test="${fn:length(item.atclCtsStr) > 130 }">...</c:if> --%>
				 									</a>
				 									<p style="font-size:12px;color:gray;"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/> |
				 									<spring:message code="common.title.hits"/> : ${item.hits} |
				 									<spring:message code="board.title.bbs.info.comment"/> : ${item.cmntCnt}</p>
				 									<c:if test="${not empty item.attachFiles}">
														<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status">
															| <a href="<c:url value="/app/file/download/${fileItem.fileSn}"/>" onclick="javascript:fileDown('${fileItem.fileSn}');" title="${fileItem.fileNm}"><i class="glyphicon glyphicon-floppy-save"></i></a>
														</c:forEach>
													</c:if>
												</div>
											</div>
										</li>
										</c:forEach>
										<c:if test="${empty bbsAtclList}">
										<li class="list-group-item"><spring:message code="board.message.bbs.atcl.nodata"/></li>
										</c:if>
									</ul>
									<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<%@ include file="./list_atcl_javascript.jsp" %>
</mhtml:mng_body>
</mhtml:mng_html>

