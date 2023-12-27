<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

			<div class="row">
				<div class="col-xs-4" style="padding-right:0;">
					<div class="table-responsive">
					<table id="test" summary="출결관리" class="table table-bordered wordbreak table-hover" style="width: max-content; max-width: inherit;">
						<colgroup>
							<col style="width:50px" />
							<col style="width:45px" />
							<col style="width:95px" />
							<col style="width:auto" />
							<col style="width:auto" />
							<col style="width:auto" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col" class="hold-transition"><input type='checkbox' name='checkAllStudent' onclick="checkAllStudent();" style='border:0;margin:0'></th>
								<th scope="col">번호</th>
								<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
								<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
								<th scope="col">이메일</th>
								<th scope="col">출석/보강/결석</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="length" value="${fn:length(stdList) }" />
							<c:forEach items="${stdList}" var="item" varStatus="status">
								<tr>
									<td class="text-center"><input type='checkbox' name='selStudent' value='${item.userNo}' style='border:0;margin:0'></td>
									<td class="text-center">${length - status.index}</td>
									<td class="text-center">${item.userNm}</td>
									<td><a href="javascript:userInfo('${item.userNo}')">${item.userId}</a></td>
									<td class="text-center">${item.email}</td>
									<td class="text-center" id="attdCnt_${item.stdNo }">${item.attendCnt }/${item.alterCnt }/${item.absenceCnt }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
 				<div class="col-xs-8" style="padding-left: 0">
 					<div class="table-responsive">
 					<table id="test" summary="출결관리" class="table table-bordered wordbreak table-hover" style="width: max-content;  max-width: inherit;">
						<colgroup>
							<c:set var="cntsCnt" value="${fn:length(attendCntsList) }" />
							<c:forEach begin="1" end="${cntsCnt }" step="1">
								<col style="width:180px;" />
							</c:forEach>
						</colgroup>
						<thead>
							<tr>
								<c:forEach var="item" items="${attendCntsList }" varStatus="status">
									<th scope="col" id="cnts_${status.count }">
										<span><a href="javascript:showStsBtn('cnts_${status.count }')">${status.count }주차</a></span>
										<div name="stsBtns" style="display :none;">
											<button name="stsBtn" onclick="updateStsAll('ATTEND', '${item.unitCd }')" class="btn btn-default btn-sm">출석</button>
											<button name="stsBtn" onclick="updateStsAll('ABSENCE', '${item.unitCd }')" class="btn btn-default btn-sm">미출석</button>
											<button name="stsBtn" onclick="updateStsAll('ALTER', '${item.unitCd }')" class="btn btn-default btn-sm">보강</button>
										</div>							
									</th>					
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:set var="length" value="${fn:length(stdList) }" />
							<c:forEach items="${stdList}" var="item" varStatus="status">
								<tr id="R_${item.stdNo }">
									<c:forEach items="${item.bookmarkList }" var="bookmark" varStatus="status">
										<td id="${item.stdNo }_${status.count }" class="text-center">
											<a href="javascript:showStsBtn('${item.stdNo}_${status.count }');">
												<c:choose>
													<c:when test="${bookmark.attendStsCd eq 'EMPTY'}"><span class="text-muted">[미입력]</span></c:when>
													<c:when test="${bookmark.attendStsCd eq 'ATTEND'}"><span class="text-light-blue">출석</span></c:when>
													<c:when test="${bookmark.attendStsCd eq 'ABSENCE'}"><span class="text-red">미출석</span></c:when>
													<c:when test="${bookmark.attendStsCd eq 'ALTER'}"><span class="text-yellow">보강출석</span></c:when>
												</c:choose>
											</a>
											<div name="stsBtns" style="display :none;">
												<button name="stsBtn" onclick="updateSts('ATTEND','${item.stdNo}', '${bookmark.unitCd }')" class="btn btn-default btn-sm">출석</button>
												<button name="stsBtn" onclick="updateSts('ABSENCE','${item.stdNo}', '${bookmark.unitCd }')" class="btn btn-default btn-sm">미출석</button>
												<button name="stsBtn" onclick="updateSts('ALTER','${item.stdNo}', '${bookmark.unitCd }')" class="btn btn-default btn-sm">보강</button>
											</div>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
 				</div>
			</div>

