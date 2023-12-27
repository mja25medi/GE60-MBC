<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="faqCtgrList" value="${faqCtgrList}" />
<c:set var="faqCtgrVO"  value="${vo}" />
<c:set var="faqList" value="${faqList}" />
			<form id="frm" name="frm" action="">
				<div class="content-section">
					<div class="contentWrap">
						<div class="alert alert-success">
							<form action="/home/brd/faq" onsubmit="return false" id="faqForm" name="faqForm">
							<input type="hidden" id="ctgrCd" name="ctgrCd" vlaue="${vo.ctgrCd}"/>
							<input type="hidden" id="curPage" name="curPage" value="${vo.curPage}"/>

							<fieldset class="faq_search">
								<legend class="sr-only">FAQ</legend>
								<span style="float:left;line-height:35px;margin-right:10px;"><label for="faq">FAQ <spring:message code="common.title.search"/></label></span>
								<div class="input-group" style="width:200px;">
									<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" title="common.title.input.searchvalue"/>
									<span class="input-group-addon btn_search" style="cursor:pointer"><i class="fa fa-search"></i></span>
								</div>
								<p style="padding-top:10px;"><spring:message code="board.message.faq.input.keyword"/></p>
							</fieldset>
							</form>
						</div>
						<div class="well" style="padding:0px;">
							<ul class="nav nav-pills nav-justified">
								<li <c:if test="${empty vo.ctgrCd}">class="active"</c:if>><a href="<c:url value="/home/brd/faq/main"/>"><spring:message code="common.title.all"/></a></li>
								<c:forEach var="item" items="${faqCtgrList}">
								<li <c:if test="${item.ctgrCd eq vo.ctgrCd}">class="active"</c:if>><a href="<c:url value="/home/brd/faq/main"/>?ctgrCd=${item.ctgrCd}">${item.ctgrNm}</a></li>
								</c:forEach>
							</ul>
						</div>						
						<div style="display:block;" id="faqList">
							<c:if test="${not empty faqList}">
							<div class="panel-group" id="accordion">
								<c:forEach var="item" items="${faqList}" varStatus="status">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#faqcnts_${status.count}" onclick="resizeCnts('${status.count}')">
												${item.atclTitle}
											</a>
										</h4>
									</div>
									<div id="faqcnts_${status.count}" class="panel-collapse collapse">
										<div class="panel-body faqcntsBody wordbreak" id="faqcntsBody_${status.count}">
											${item.atclCts}
										</div>
									</div>
								</div>
								</c:forEach>
							</div>
							</c:if>
							<c:if test="${empty faqList}">
							<div class="well">
								<spring:message code="board.message.faq.nodata"/>
							</div>
							</c:if>						
						</div>
						<div class="row" style="margin-bottom:20px;">
							<div class="col-lg-12">
								<meditag:paging pageInfo="${pageInfo}" funcName="list"/>
							</div>
						</div>
					</div>	
				</div>
			</form>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				$("#ctgrCd").val("");
				list(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			$("#ctgrCd").val("");
			list(1);
		});
		
		
//		listPaging(1);

	});	
	
	var itemVo = {};
	itemVo.ctgrCd = "";
	
/* 	function listPaging(page){
		
		var map = {};
		map.ctgrCd = itemVo.ctgrCd;
		map.searchKey = $("#frm #searchKey").val();
		map.searchValue = $("#frm #searchValue").val();
		map.pageIndex = page;
		
		$("#faqList").load(
				"<c:url value="/home/brd/faq/list"/>"
				, map
		)
	}
*/	

	function list(page) {
	
		$('#faqForm')
		.find('input[name=curPage]').val(page).end();
		$('#faqForm').attr("atcion", "/home/brd/faq/main");
		document.faqForm.submit();
	}

	function doChangeCtgr(ctgrCd){
		itemVo.ctgrCd = ctgrCd;
	}
	
	function doSwitchAns(atclSn){
		
		if($("#a_"+atclSn).attr("style") == "display: none;"){
			$("#a_"+atclSn).show();
		}else{
			$("#a_"+atclSn).hide();
		}
	}		
	
	
	</script>
	
	
