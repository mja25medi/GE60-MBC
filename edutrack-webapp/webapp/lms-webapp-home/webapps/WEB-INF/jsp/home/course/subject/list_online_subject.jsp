<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="courseVO" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

				<%-- <ul class="tabs">
					<li <c:if test="${empty vo.refundYn || vo.refundYn eq ''}" >class="active"</c:if>><a href="javascript:setRefundYn('')" >전체</a></li>
					<li <c:if test="${vo.refundYn eq 'Y'}" >class="active"</c:if>><a href="javascript:setRefundYn('Y')">환급</a></li>
					<li <c:if test="${vo.refundYn eq 'N'}" >class="active"</c:if>><a href="javascript:setRefundYn('N')">비환급</a></li>
				</ul> --%>

				<dl class="infoTag">
					<div class="box wrap">
				        <dt class="title"><b>카테고리</b></dt>
				        <dl class="mt10">
				        	<a href="<c:url value="/home/course/subject/listOnineMain"/>?curPage=1" class="btn <c:if test="${empty vo.sbjCtgrCd || vo.sbjCtgrCd eq ''}" >on</c:if>">전체</a>
				        	<c:forEach items="${sbjCtgrList}" var="item">
				        		<a href="<c:url value="/home/course/subject/listOnineMain"/>?curPage=1&amp;sbjCtgrCd=${item.sbjCtgrCd}" class="btn  <c:if test="${vo.sbjCtgrCd eq item.sbjCtgrCd}" >on</c:if>">${item.sbjCtgrNm }</a>
				        	</c:forEach>
				        </dl>
				    </div>
				</dl>

				<%-- <ul class="tabs">
					<li <c:if test="${empty vo.sbjCtgrCd || vo.sbjCtgrCd eq ''}" >class="active"</c:if>>
						<a href="<c:url value="/home/course/subject/listOnineMain"/>?curPage=1">전체</a>
					</li>
					<c:forEach items="${sbjCtgrList}" var="item">
						 <li <c:if test="${vo.sbjCtgrCd eq item.sbjCtgrCd}" >class="active"</c:if>>
				         		<a href="<c:url value="/home/course/subject/listOnineMain"/>?curPage=1&amp;sbjCtgrCd=${item.sbjCtgrCd}">${item.sbjCtgrNm }</a>
				         </li>
			         </c:forEach>
			    </ul> --%>
         
				<form id="onlineSubjectForm" name="onlineSubjectForm" onsubmit="return false">
					<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
					<input type="hidden" name="sbjCtgrCd" id="sbjCtgrCd" value="${vo.sbjCtgrCd}"/>
					<input type="hidden" name="refundYn" id="refundYn" value="${vo.refundYn}"/>
				</form>
				
				<div class="like-table mt40" data-toggle="like-table">
				    <div class="thead">                                
				        <span class="th">과정명/학습시간</span>
				        <span class="th w10">샘플보기</span>
				        <span class="th w10">과정계획서</span>
				    </div>
				    <ul class="tbody">
				    	<c:if test="${not empty onSbjList}">
				    		<c:forEach items="${onSbjList}" var="item">
				    			<li class="tr">
						            <dl class="td txt-left">
						            	<dt>과정명/학습시간</dt><dd>
							                <a href="<c:url value="/home/course/subject/readCourseMain"/>?sbjCd=${item.sbjCd}">${item.sbjNm }
							                    <p class="gray6">기간: 
							                    <c:choose>
													<c:when test="${item.eduDaycnt  > 0}">${item.eduDaycnt }</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose>일 / 시간 : 총 
												<c:choose>
													<c:when test="${item.eduTm  > 0}">${item.eduTm }</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose>
												시간</p>
							                </a>
							            </dd>
						            </dl> 
						            <dl class="td">
						            	<dt>샘플보기</dt><dd>
							                <div class="video" title="영상보기" aria-modal="true">
							                    <div class="thumbnail" title="영상보기" <c:if test="${item.contentsCnt > 0 }">onclick="javascript:previewContents('${item.sbjCd }')"</c:if>>
							                    	<img src="/app/file/thumb/${item.thumbFileSn }" alt="이미지" aria-hidden="true" onerror="this.style.display='none'">
							                    </div>
							                </div>
							            </dd>
						            </dl>
						            <dl class="td"><dt>과정계획서</dt><dd>
						                <c:choose>
											<c:when test="${not empty item.attachFiles }">
												<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status">
													<a href="/app/file/download/${fileItem.fileSn }" class="btn"><i class="xi-paper-o xi-x" aria-hidden="true"></i></a>
												</c:forEach>
											</c:when>
											<c:otherwise>
												
											</c:otherwise>
										</c:choose>
						            </dd></dl>
						        </li>  
				    		</c:forEach>
				    	</c:if>
				    </ul>
				</div>
				
				<%-- <table class="tstyle list">
					<caption>공지사항 테스트 목록</caption>
					<colgroup>
						<col>
						<col class="w15 m_hidden">
						<col class="w15 m_hidden">
						<col class="w15 m_hidden">
					</colgroup>
					<thead class="dhead">
						<tr>
							<th scope="col">과정명</th>
							<!-- <th scope="col">환급구분</th>
							<th scope="col">교육비/환급액</th>
							<th scope="col">정원</th> -->
							<th scope="col" class="m_hidden">학습기간/시간</th>
							<th scope="col" class="m_hidden">샘플보기</th>
							<th scope="col" class="m_hidden">과정계획서</th>
						</tr>
					</thead>
					<tbody class="dbody" id="listView">
						<c:if test="${empty onSbjList}">
							<tr>
								<td colspan="4"><spring:message code="course.message.course.nodata"/></td>
							</tr>
						</c:if>
						<c:if test="${not empty onSbjList}">
							<c:forEach items="${onSbjList}" var="item">
							<tr class="notice">
								<td class="title">
									<a href="<c:url value="/home/course/subject/readCourseMain"/>?sbjCd=${item.sbjCd}">${item.sbjNm }</a>
								</td>
								<td class="m_hidden">
									<c:choose>
										<c:when test="${item.eduDaycnt  > 0}">${item.eduDaycnt }</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose>
									 일 /  총 
									 <c:choose>
										<c:when test="${item.eduTm  > 0}">${item.eduTm }</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose>
									 시간
								</td>
								<td class="m_hidden">
									<c:if test="${item.contentsCnt > 0 }">
										<a href="javascript:previewContents('${item.sbjCd }')" class="btn btn-warning btn-xs">샘플보기</a>
									</c:if>
								</td>
								<td>
									<c:choose>
										<c:when test="${item.refundYn eq 'Y'}">환급</c:when>
										<c:otherwise>미환급</c:otherwise>
									</c:choose>
								</td>
								<td>${item.eduPrice } 원</td>
								<td>${item.eduNop } 명</td>
								<td class="m_hidden">
									<c:choose>
										<c:when test="${not empty item.attachFiles }">
											<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status">
												<a href="/app/file/download/${fileItem.fileSn }" class="btn btn-warning btn-xs">과정계획서</a>
											</c:forEach>
										</c:when>
										<c:otherwise>
											
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table> --%>
				
				<div class="board_pager">
					<span class="inner"> 
						<meditag:paging pageInfo="${pageInfo}" funcName="subjectSearch" name="front"/>
					</span>
				</div>
				
<script type="text/javascript">
	$(document).ready(function(){
		$('.tabs li').click(function (e) {
			$(this).parent().children().removeClass('active');
			$(this).addClass("active");
		});
	});
	
	function subjectSearch(page) {
		$('#onlineSubjectForm')
		.find('input[name=curPage]').val(page).end()
		.attr("action","/home/course/subject/listOnineMain")
		document.onlineSubjectForm.submit();
	}
	
	function setRefundYn(refundYn){
		$("#refundYn").val(refundYn);
		subjectSearch(1);
	}

	function previewContents(sbjCd){
		$.ajax({
			url : '/home/course/subject/getFirstContents'
			,data : {
				'sbjCd' : sbjCd
			}
			,success : function(resultListVO) {
				var url = cUrl("/home/course/subject/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+resultListVO.unitCd;
				var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=auto,resizable=yes,width=1200,height=675";
				var contentsWin = window.open(url, "contentsWin", winOption);
				contentsWin.focus();
			}
			,error : function(request,status,error) {
			}
		});
	}
</script>
