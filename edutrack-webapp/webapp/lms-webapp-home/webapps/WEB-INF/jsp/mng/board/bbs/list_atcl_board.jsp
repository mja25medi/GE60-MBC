<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-body">
								<form name="bbsAtclForm" id="bbsAtclForm" onsubmit="return false" class="form-inline" method="POST">
								<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}"/>
								<input type="hidden" name="atclSn" id="atclSn" value="${vo.atclSn}"/>
								<input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex}"/>
								<input type="hidden" id="crsCreCd" name="crsCreCd" value="${vo.crsCreCd}">
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
								<div class="col-lg-12" style="margin-top:5px;">
									<table summary="<spring:message code="board.title.bbs.atcl.list"/>" style="margin-top:5px;" class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:60px;"/>
											<col style="width:auto"/>
											<col style="width:150px;"/>
											<col style="width:70px;"/>
											<col style="width:100px;"/>
										</colgroup>
										<thead>
										<tr>
											<th scope="col"><spring:message code="common.title.no"/></th>
											<c:if test="${vo.bbsCd eq 'REVIEW'}">
												<th scope="col">과정명</th>
											</c:if>
											<th scope="col"><spring:message code="common.title.title"/></th>
											<c:if test="${vo.bbsCd eq 'REVIEW'}">
												<th scope="col">만족도</th>
											</c:if>
											<th scope="col"><spring:message code="common.title.reguser"/></th>
											<th scope="col"><spring:message code="common.title.hits"/></th>
											<th scope="col"><spring:message code="common.title.regdate"/></th>
										</tr>
										</thead>
										<tbody id="tbodyList">
										<c:forEach items="${bbsAtclList}" var="item" varStatus="status">
										<tr>
											<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
											<c:if test="${vo.bbsCd eq 'REVIEW'}">
												<td>${item.crsCreNm }</td>
											</c:if>
											<td class='left'>
												<meditag:reply level="${item.atclLvl}">
												<a href="#none" onclick="javascript:readAtcl(${item.atclSn})" title="${item.atclTitle}">
													<c:if test="${not empty item.headNm}">[${item.headNm}] </c:if>${item.atclTitle}
													<c:if test="${item.recently eq 'NEW' }"><img src="<c:url value="/img/common/new.png"/>"  alt="New"/></c:if>
												</a>
												<c:if test="${item.cmntCnt > 0}">[${item.cmntCnt}]</c:if>
												</meditag:reply>
											</td>
											<c:if test="${vo.bbsCd eq 'REVIEW'}">
												<td>${item.starScore }</td>
											</c:if>
											<td>${item.regNm}</td>
											<td class="text-right">${item.hits}</td>
											<td><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
										</tr>
										</c:forEach>
										<c:if test="${empty bbsAtclList}">
										<tr>
											<td colspan="5"><spring:message code="board.message.bbs.atcl.nodata"/></td>
										</tr>
										</c:if>
										</tbody>
									</table>
									<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<%@ include file="./list_atcl_javascript.jsp" %>

