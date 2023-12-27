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
												<td colspan="3">${vo.atclTitle}</td>
											</tr>
											<tr>
												<th class="active" scope="row"><spring:message code="common.title.reguser"/></th>
												<td>${vo.regNm}</td>
												<th class="active" scope="row"><spring:message code="common.title.regdate"/></th>
												<td>
													<div style="float:left"><meditag:dateformat type="1" delimeter="." property="${vo.regDttm}" /></div>
												    <div style="float:right"><spring:message code="common.title.hits"/> : ${vo.hits}</div>
												</td>
											</tr>
											<tr>
												<th class="active" scope="row">이름</th>
												<td>${vo.serviceRegNm }</td>
												<th class="active" scope="row">연락처</th>
												<td>${vo.serviceTel }</td>
											</tr>
											<tr>
												<th class="active" scope="row">이메일</th>
												<td>${vo.serviceEmail }</td>
												<th class="active" scope="row">패스워드</th>
												<td>*****</td>
												<%-- <td>${vo.servicePw }</td> --%>
											</tr>
											<tr>
												<th class="active" scope="row">상담가능날짜</th>
												<td>${vo.serviceEnableDate}</td>
												<th class="active" scope="row">상담가능시간</th>
												<td>${vo.serviceEnableTime } 시</td>
											</tr>
											<tr>
												<th class="active" scope="row" colspan="4"><spring:message code="common.title.cnts"/></th>
											</tr>
											<tr>
												<td colspan="4" style="min-height:200px;">
													<c:if test="${vo.editorYn eq 'Y' }">
													${vo.atclCts}
													</c:if>
													<c:if test="${vo.editorYn ne 'Y' }">
													${fn:replace(vo.atclCts,crlf,"<br/>")}
													</c:if>
												</td>
											</tr>
											<c:if test="${not empty vo.atclTag}">
											<tr>
												<td colspan="4">Tag : ${vo.atclTag}</td>
											</tr>
											</c:if>
										</tbody>
									</table>
				
									<div class="text-right" style="margin-bottom:10px;">
										<%-- <a href="/mng/brd/bbs/editFormAtclMain?searchKey=${vo.searchKey}&amp;searchValue=${vo.searchValue}&amp;bbsCd=${vo.bbsCd}&amp;pageIndex=${vo.pageIndex}&amp;atclSn=${vo.atclSn}" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a> --%>
										<a href="javascript:deleteAtcl('${vo.atclSn }');" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
										<a href="/mng/brd/bbs/listAtclMain?searchKey=${vo.searchKey}&amp;searchValue=${vo.searchValue}&amp;bbsCd=${vo.bbsCd}&amp;pageIndex=${vo.pageIndex}" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			<form name="bbsAtclForm" id="bbsAtclForm" method="POST" onsubmit="return false">
			<input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex}"/>
			<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}"/>
			<input type="hidden" name="atclSn" id="atclSn" value="${vo.atclSn}"/>
			<input type="hidden" name="parAtclSn" id="parAtclSn" value="${vo.atclSn}"/>
			</form>
			<script type="text/javascript">
				/** 페이지 초기화 */
				var curPage = 1;
				$(document).ready(function(){
					listCmnt(1); //-- 코멘트 목록을 호출한다.
				});	// end ready..
				
				/** 글 삭제 */
				function deleteAtcl(atclSn){
					if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")){
						var url = generateUrl("/mng/brd/bbs/removeAtcl", { "pageIndex":curPage, "bbsCd":"${vo.bbsCd}", "atclSn": atclSn});
						document.location.href = url;	
					}
				}
				function listCmnt(page) {
					curPage = page;
					<c:if test="${bbsInfoVO.cmntUseYn eq 'Y'}">
					// 댓글 사용 여부가 'Y'인 경우만 댓글 목록 표시
					$.getJSON(
						cUrl("/mng/brd/bbs/listCmnt"),
						{
							"pageIndex" : page,
						  	"atclSn" :  "${vo.atclSn}"},
						  	function(result) {
								var itemList = result.returnList;
							  	var pageInfo = result.pageInfo;
							  	console.log(result)
							  	if(itemList.length > 0) {
								  	var listStr = "";
								  	for (var i=0; i < itemList.length; i++) {
									  	var item = itemList[i];
									  	var delLink = "";
									  	if(item.regNo == "${USERNO}")
										  	delLink = "<a href=\"#_none\" onclick=\"delCmnt('"+item.cmntSn+"')\" class='btn btn-warning btn-xs' style='float:right'><spring:message code="button.delete"/></a>";
									  	listStr += "<li class='list-group-item'>";
									  	listStr += "    <i class='fa fa-user fa-fw'></i> "+item.regNm+" / "+addDateTimeFormatStr(item.regDttm)+delLink;
									  	listStr += "    <p class=\"wordbreak\">"+item.cmntCts.replace(/\n/g,"<br/>")+"</p>";
									  	listStr += "</li>";
								  	}
								  	$("#cmntList").html(listStr).show();
								  	$("#cmntPageArea").pagingHtml(pageInfo, "listCmnt").show();
							  	} else {
								  	$("#cmntList").html("").hide();
								  	$("#cmntPageArea").html("").hide();
							  	}
						  	}
						);
					</c:if>
				}
	
				function viewPhoto(fileSn) {
					$(".photoView").hide();
					$("#photo_"+fileSn).show();
				}
				<c:if test="${bbsInfoVO.cmntUseYn eq 'Y'}">
				function addCmnt() {
					$('#bbsCmntForm').attr("action","/mng/brd/bbs/addCmnt");
					$('#bbsCmntForm').ajaxSubmit(
						function(result) {
							if(result.result >= 0) {
								$("#cmntCts").val('');
								listCmnt(1);
							} else {
								alert(result.message);	// 실패 안내 메시지 표시
							}
						}
					);
					return false;
				}
	
				function delCmnt(cmntSn) {
					if(!confirm("<spring:message code="board.message.bbs.cmnt.alert.delete"/>")) return false;
					var ajaxUrl = cUrl("/mng/brd/bbs/removeCmnt");
					var ajaxData = { "cmntSn" : cmntSn };
					$.getJSON (
						ajaxUrl, ajaxData,
						function(response) {
							if(response.result >= 0) {
								listCmnt(curPage);
							} else {
								alert(response.message);	// 실패 안내 메시지 표시
							}
					});
					return false;
				}
				</c:if>
			</script>

