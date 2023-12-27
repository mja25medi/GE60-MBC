<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
				<section class="content">
					<div class="row" id="content">
						<div class="box">
							<div class="box-body">			
								<div class="col-lg-12">
									<table class="table table-bordered wordbreak">
										<caption class="sr-only">${MENUNAME}</caption>
										<colgroup>
											<col style="width:15%" />
											<col style="width:35%" />
											<col style="width:20%" />
											<col style="width:30%" />
										</colgroup>
										<tbody>
											<tr>
												<th class="active" scope="row"><spring:message code="common.title.title"/></th>
												<td colspan="3">${vo.qnaTitle }</td>
											</tr>
											<c:if test="${not empty vo.attachFiles}">
											<tr>
												<th class="active" scope="row"><spring:message code="common.title.atachfile"/></th>
												<td colspan="3">
													<c:forEach var="fileItem" items="${vo.attachFiles}" varStatus="status">
														<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
													</c:forEach>
												</td>
											</tr>
											</c:if>
											<tr>
												<th class="active" scope="row"><spring:message code="common.title.reguser"/></th>
												<td>${vo.userNm}</td>
												<th class="active" scope="row"><spring:message code="common.title.regdate"/></th>
												<td>
													<div style="float:left"><meditag:dateformat type="1" delimeter="." property="${vo.regDttm}" /></div>
												</td>
											</tr>
											<tr>
												<th class="active" scope="row" colspan="4"><spring:message code="common.title.cnts"/></th>
											</tr>
											<tr>
												<td colspan="4" style="min-height:200px;">
													<c:if test="${vo.editorYn eq 'Y' }">
													${vo.qnaCts}
													</c:if>
													<c:if test="${vo.editorYn ne 'Y' }">
													${fn:replace(vo.qnaCts,crlf,"<br/>")}
													</c:if>
												</td>
											</tr>
										</tbody>
									</table>
				
									<div class="text-right" style="margin-bottom:10px;">
										<c:if test="${vo.stsCd eq 'WAIT' }">
											<a href="javascript:writeAnsr('${vo.qnaSn}')" class="btn btn-primary btn-sm"><spring:message code="button.reply"/> </a>
										</c:if>
										<c:if test="${vo.stsCd eq 'COMP' }">
											<a href="javascript:editAnsr('${vo.qnaSn}')" class="btn btn-primary btn-sm"><spring:message code="button.reply"/> </a>
										</c:if>
										<a href="javascript:deleteQstn('${vo.qnaSn }');" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
										<a href="javascript:listQstn();" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</form>
			<script type="text/javascript">
				/** 페이지 초기화 */
				var curPage = 1;
				$(document).ready(function(){
				});	// end ready..
				
				/** 글 삭제 */
				function deleteQstn(qnaSn){
					if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")){
						$.ajax({
							url : '/mng/brd/qna/deleteQstn',
							data : {
								"qnaSn" : qnaSn
							}
							,method : "post"
							,success : function(data) {
								if(data.result > 0) {
									alert("정상 처리 되었습니다.");
									listQstn();
								} else {
									alert(data.message);
									document.location.reload();
								}
							}
							,error : function(request, status, error) {
								alert("삭제 중 오류가 발생했습니다. " + error);
								document.location.reload();
							}
						});
					}
				}
				
				function writeAnsr(qnaSn) {
					var url = generateUrl("/mng/brd/qna/writeAnsrMain",{"qnaSn" : qnaSn});
					document.location.href = url;
				}
				
				function editAnsr(qnaSn) {
					var url = generateUrl("/mng/brd/qna/editAnsrMain",{"qnaSn" : qnaSn});
					document.location.href = url;
				}
				
				function listQstn() {
					var url;
					if('${vo.lecYn}' == 'Y') {
						url = generateUrl("/mng/brd/qna/lecMain");
					} else {
						url = generateUrl("/mng/brd/qna/main");
					}
					document.location.href = url;
				}

			</script>

