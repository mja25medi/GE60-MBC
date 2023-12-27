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
										<option value="${item.headCd}" <c:if test="${vo.headCd eq item.headCd }">selected="selected"</c:if>>${item.headNm}</option>
										</c:forEach>
									</select>
									</c:if>
									<select name="searchKey" id="searchKey" title="<spring:message code="board.title.bbs.search.select"/>" class="_enterBind form-control input-sm">
										<option value="title" <c:if test="${vo.searchKey eq 'title' }">selected="selected"</c:if>><spring:message code="common.title.title"/></option>
										<option value="regNm" <c:if test="${vo.searchKey eq 'regNm' }">selected="selected"</c:if>><spring:message code="common.title.reguser"/></option>
									</select>
									<div class="input-group" style="width:160px">
										<input type="text" name="searchValue" id="searchValue" value="${vo.searchValue }" title="<spring:message code="common.title.input.searchvalue"/>" class="_enterBind form-control input-sm"/>
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
								<div class="col-md-12" style="margin-top: 5px; margin-bottom:20px;">
									<div class="row text-left">
									<c:forEach items="${bbsAtclList}" var="item" varStatus="status">
										<div class="col-sm-6 col-md-3">
				  							<div class="thumbnail" style="min-height:240px;">
				    							<a href="#none" onclick="javascript:readAtcl(${item.atclSn})" title="<spring:message code="common.title.view"/> : ${item.atclTitle}">
				    								<c:if test="${item.thumbFileSn eq '0' || empty item.thumbFileSn }"><img src="<c:url value="/img/noimage_image.png"/>" alt="${item.atclTitle}"></c:if>
													<c:if test="${item.thumbFileSn ne '0' && not empty item.thumbFileSn}"><img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" alt="${item.atclTitle}"></c:if>
				    							</a>
				     							<div class="caption">
				       								<h5><a href="#none" onclick="javascript:readAtcl(${item.atclSn})" title="<spring:message code="common.title.view"/> : ${item.atclTitle}">
				  									${fn:substring(item.atclTitle,0, 30)}<c:if test="${fn:length(item.atclTitle) > 30 }">...</c:if>
				       								<c:if test="${item.recently eq 'NEW' }"><img src="<c:url value="/img/common/new.png"/>"  alt="New" style="display: inline;"/></c:if>
				       								</a></h5>
				       								<p style="font-size:12px;color:gray;"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/> | <spring:message code="common.title.hits"/> : ${item.hits} | <spring:message code="board.title.bbs.info.comment"/> : ${item.cmntCnt}</p>
				     							</div>
				  							</div>
										</div>
									</c:forEach>
									</div>
				
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

