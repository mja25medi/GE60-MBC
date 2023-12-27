<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
				<section class="content">
					<div class="row" id="content">
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
									<c:forEach items="${bbsAtclList}" var="item" varStatus="status">
									<div class="panel panel-default">
										<c:set var="atclLvl" value="${item.atclLvl}" />
										<div class="panel-heading" style="padding-left:${atclLvl*15}px;" >
											${item.atclTitle}&nbsp;<c:if test="${item.recently eq 'NEW' }"><img src="<c:url value="/img/common/new.png"/>"  alt="New"/></c:if>
											<span class="pull-right" style="font-size:12px;color:gray">
												${item.regNm} (${item.regId}) | <meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/> | <spring:message code="common.title.hits"/> : ${item.hits} | <spring:message code="board.title.bbs.info.comment"/> : ${item.cmntCnt}
											</span>
										</div>
										<div class="panel-body">
											<div style="float:left;width:100%;min-height:160px;Y">
												<c:if test="${item.editorYn eq 'Y' }">
												${item.atclCts}
												</c:if>
												<c:if test="${item.editorYn ne 'Y' }">
												${fn:replace(item.atclCts,crlf,"<br/>")}
												</c:if>
											</div>
											<c:if test="${not empty item.atclTag}">
											<div style="float:left;width:100%;margin-top:10px;"><spring:message code="common.title.tag"/> : ${item.atclTag}</div>
											</c:if>
											<c:if test="${not empty item.attachFiles}">
											<div style="float:left;width:100%;margin-top:10px;">
												<div style="float:left"><spring:message code="common.title.atachfile"/> : </div>
												<div style="float:left">
												<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status"><div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div></c:forEach>
												</div>
											</div>
											</c:if>
<%-- 											<c:if test="${item.regNo eq USERNO}"> --%>
											<div style="margin-top:10px;" class="text-center">
												<a href="#_none" onclick="javascript:editAtcl('${item.atclSn}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
											</div>
<%-- 											</c:if> --%>
										</div>
									</div>
									</c:forEach>
									<c:if test="${empty bbsAtclList}">
									<table summary="<spring:message code="board.title.bbs.atcl.cnts"/>" style="width:100%" class="table_dtl">
										<tbody>
											<tr style="height:35px;">
												<td class="top" style="text-align:center">
													<spring:message code="board.message.bbs.atcl.nodata"/>
												</td>
											</tr>
										</tbody>
									</table>
									</c:if>
									<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
				
								</div>
							</div>
						</div>
					</div>
				</section>		
			<%@ include file="./list_atcl_javascript.jsp" %>
