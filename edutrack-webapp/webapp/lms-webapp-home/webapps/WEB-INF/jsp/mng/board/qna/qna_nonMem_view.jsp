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
												<th class="active" scope="row"><spring:message code="common.title.reguser"/></th>
												<td colspan="3">${vo.regNm}</td>
											</tr>
											<tr>
												<th class="active" scope="row"><spring:message code="user.title.userinfo.contact"/></th>
												<td>${vo.qsTel}</td>
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
													${fn:replace(vo.qsCts,crlf,"<br/>")}
												</td>
											</tr>
										</tbody>
									</table>
				
									<div class="text-right" style="margin-bottom:10px;">
										<c:choose>
											<c:when test="${vo.ansYn eq 'Y' }">
												<a href="javascript:editAnsr('${vo.qnaSn}')" class="btn btn-primary btn-sm"><spring:message code="button.reply"/> </a>
											</c:when>
											<c:otherwise>
												<a href="javascript:writeAnsr('${vo.qnaSn}')" class="btn btn-primary btn-sm"><spring:message code="button.reply"/> </a>
											</c:otherwise>
										</c:choose>
										<a href="javascript:deleteApply('${vo.qnaSn}');" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
										<a href="javascript:listQstn('${curPage}');" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</form>
			<script type="text/javascript">
				/** 페이지 초기화 */
				$(document).ready(function(){
				});	// end ready..
				
				/** 글 삭제 */
				function deleteApply(qnaSn){
					if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")){
						$.ajax({
							url : '/mng/brd/qna/deleteNonMem',
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
					var url = generateUrl("/mng/brd/qna/writeNonMemAnsrMain",{"qnaSn" : qnaSn});
					document.location.href = url;
				}
				
				function editAnsr(qnaSn) {
					var url = generateUrl("/mng/brd/qna/editNonMemAnsrMain",{"qnaSn" : qnaSn});
					document.location.href = url;
				}
				
				function listQstn(curPage) {
					document.location.href = generateUrl("/mng/brd/qna/nonMemMain?curPage=" + curPage);
				}

			</script>