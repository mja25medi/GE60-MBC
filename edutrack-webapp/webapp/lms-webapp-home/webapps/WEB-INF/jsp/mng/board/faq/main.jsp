<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
		<section class="content">
			<div class="row" id="content">
				<div class="box">
					<div class="box-body">
						<div class="col-md-4 col-sm-4">
							<div class="text-right">
								<a href="javascript:ctgrWrite('','')" class="btn btn-primary btn-sm"><spring:message code="button.write.category"/> </a>
							</div>
							<div id="ctgrList" style="width:100%;margin-top:5px;"></div>
						</div>
						<div class="col-md-8 col-sm-8">
							<form name="Search" onsubmit="return false" class="form-inline">
							<div class="row">
								<div class="col-md-5 col-sm-5 text-left">
									<div style="float:left;line-height:30px;">
									<span id="ctgrTitle"></span>
									</div>
								</div>
								<div class="col-md-7 col-sm-7 text-right">
									<div class="input-group" style="width:160px;">
										<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.title.input.searchvalue"/>" value="${vo.searchValue}" placeholder="<spring:message code="common.title.all"/>"/>
										<span class="input-group-addon" onclick="listCtgr()" style="cursor:pointer">
											<i class="fa fa-search"></i>
										</span>
									</div>
									<a href="javascript:atclWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write"/> </a>
								</div>
							</div>
							<input type="submit" value="submit" style="display:none" />
							</form>
							<div id="faqList" style="margin-top:5px;">
								<table summary="<spring:message code="board.title.faq.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:50px"/>
										<col style="width:auto"/>
										<col style="width:100px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="common.title.no"/></th>
											<th scope="col"><spring:message code="common.title.title"/></th>
											<th scope="col"><spring:message code="common.title.regdate"/></th>
										</tr>
									</thead>
									<tbody id="sortable-atcl">
										<c:forEach items="${brdFaqAtclList}" var="item" varStatus="status">
										<tr id="${item.atclSn}">
											<td class="text-right">${status.count}</td>
											<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.atclTitle} <a href="javascript:atclEdit('${item.atclSn}');"><i class="fa fa-cog"></i></a></td>
											<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
										</tr>
										</c:forEach>
										<c:if test="${empty brdFaqAtclList}">
										<tr>
											<td colspan="3"><spring:message code="board.message.faq.nodata"/></td>
										</tr>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
						<div class="overlay" id="loadingBar">
							<i class="fa fa-spinner fa-spin"></i>
						</div>
					</div>
				</div>
			</div>
		</section>
<script type="text/javascript">
	var modalBox = null;
	var CtgrVO = new Object();
	
	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		CtgrVO.ctgrCd = "${vo.ctgrCd}";
		CtgrVO.ctgrNm = "${vo.ctgrNm}";
		showArea("list");
		listCtgr(CtgrVO.ctgrCd, CtgrVO.ctgrNm);
		
		$("#loadingBar").hide();/* 개발 시 삭제할 라인 */
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	
	function showArea(str) {
		$("#dvlist").hide();
		$("#dvwork").hide();
		$("#dv"+str).show();
	}

	function closeWriteArea(){
		$("#dvwork").html("");
		showArea('list');
	}

	/**
	 * 분류 목록 조회
	 */
	function listCtgr(ctgrCd, ctgrNm) {
		// 리스트 갱신전에 코드용 자료 삭제.
		if(ctgrCd != undefined) {
			CtgrVO.ctgrCd = ctgrCd;
			CtgrVO.ctgrNm = ctgrNm;
		}
		$("#ctgrList")
			.load(cUrl("/mng/brd/faq/listCtgr"));

		listFaq();
	}

	/**
	 * 분류 코드 셋팅
	 */
	function setCtgr(ctgrCd, ctgrNm) {
		CtgrVO.ctgrCd = ctgrCd;
		CtgrVO.ctgrNm = ctgrNm;
		listFaq();
	}

	/**
	 * FAQ 목록 조회
	 */
	function listFaq() {
		if(CtgrVO.ctgrCd == ''){
			return;
		}
		var title = "<b>"+CtgrVO.ctgrNm+"</b>";
		var searchValue = $("#searchValue").val();
		$("#ctgrTitle").show().html(title);
		$("#faqList")
				.load(cUrl("/mng/brd/faq/listAtcl"), {"ctgrCd":CtgrVO.ctgrCd, "searchValue":searchValue});
	}

	/**
	 * 분류 등록 폼
	 */
	function ctgrWrite() {
		var addContent  = "<iframe id='addCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/brd/faq/addFormCtgrPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 360);
		modalBox.setTitle("<spring:message code="board.title.faq.category.write"/>");
		modalBox.show();
	}

	/**
	 * 분류 수정 폼
	 */
	function ctgrEdit(ctgrCd) {
		var addContent 	= "<iframe id='addCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/brd/faq/editFormCtgrPop"/>"
			+ "?ctgrCd="+ctgrCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 360);
		modalBox.setTitle("<spring:message code="board.title.faq.category.edit"/>");
		modalBox.show();
	}

	/**
	 * 분류 순서 변경
	 */
	function ctgrSort(sortString) {
		$.getJSON( cUrl( "/mng/brd/faq/sortCtgr"),
				{ "ctgrCd": sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listCtgr(CtgrVO.ctgrCd, CtgrVO.ctgrNm);
		 	  		}
				}
			);
	}

	/**
	 * FAQ 등록 폼
	 */
	function atclWrite() {
		if(CtgrVO.ctgrCd == "") {
			alert('<spring:message code="board.message.faq.category.alert.select"/>');
		} else {
			var searchValue = $("#searchValue").val();
			document.location.href = cUrl("/mng/brd/faq/addFormAtclMain")+"?ctgrCd="+CtgrVO.ctgrCd+"&ctgrNm="+CtgrVO.ctgrNm+"&searchValue="+searchValue;
		}
	}

	/**
	 * FAQ 수정 폼
	 */
	function atclEdit(atclSn) {
		var searchValue = $("#searchValue").val();
		document.location.href = cUrl("/mng/brd/faq/editFormAtclMain")+"?ctgrCd="+CtgrVO.ctgrCd+"&ctgrNm="+CtgrVO.ctgrNm+"&searchValue="+searchValue+"&atclSn="+atclSn;
	}

	/**
	 * FAQ 순서 변경
	 */
	function atclSort(sortString) {
		$.getJSON( cUrl( "/mng/brd/faq/sortAtcl"),
				{ "ctgrCd": CtgrVO.ctgrCd, "atclSns" : sortString},			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listCtgr(CtgrVO.ctgrCd, CtgrVO.ctgrNm);
		 	  		}
				}
			);
	}
	
</script>

