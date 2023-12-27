<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="orgReshVO" value="${vo}" />
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<div class="col-md-12">
						<form id="researchBankForm" name="researchBankForm" onsubmit="return false" action="/mng/org/research">
						<input type="hidden" name="reshSn" value="${vo.reshSn }" />
						<input type="hidden" name="itemCnt" id="itemCnt" value="${vo.itemCnt }"/>
						<input type="hidden" name="useCnt" id="useCnt" value="${vo.useCnt }"/>
						<input type="hidden" name="reshTypeCd" id="reshTypeCd" value="${vo.reshTypeCd }"/>
						</form>
						<table summary="<spring:message code="course.title.reshbank.item.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:20%"/>
								<col style="width:30%"/>
								<col style="width:20%"/>
								<col style="width:30%"/>
							</colgroup>
							<tbody>
							<tr>
								<th scope="row" class="top">기수명</th>
								<td class="wordbreak">
									${crsTermInfo.creTermNm}
								</td>
								<th scope="row" class="top"><spring:message code="course.title.reshbank.title"/></th>
								<td class="wordbreak">
									${orgReshVO.reshTitle}
								</td>
							</tr>
							<tr>
								<th scope="row"><spring:message code="course.title.reshbank.desc"/></th>
								<td class="wordbreak" colspan="3">
									${orgReshVO.reshCts}
								</td>
							</tr>
							

							</tbody>
						</table>
						<div class="text-right">
							<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
						</div>
						<ul class="nav nav-tabs" id="tabMenuOper">
							<li class="active"><a href="javascript:listQstn()"><spring:message code="course.title.reshbank.item.manage.tab"/></a></li>
							<c:if test="${vo.reshTypeCd eq 'D' }">
								<li><a href="javascript:listExpulsionStd(1)">퇴교설문관리</a></li>
							</c:if>
						</ul>
						<br/>
						<div class="text-left">
						<c:if test="${orgReshVO.useCnt > 0 }">
							<p style="font-weight: bold;"><spring:message code="course.title.reshbank.item.using"/></p>
						</c:if>
						</div>
<!-- 						<div class="text-right"> -->
<%-- 						<c:if test= "${CREAUTH eq 'Y'}"> --%>
<%-- 						<c:if test="${orgReshVO.useCnt == 0 }"> --%>
<%-- 							<a href="javascript:questionWrite()" class="btn btn-primary btn-sm" ><spring:message code="button.write.question"/> </a> --%>
<%-- 							<a href="javascript:questionExcelWrite()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a> --%>
<%-- 						</c:if> --%>
<%-- 						</c:if> --%>
<!-- 						</div> -->
						
						<div id="qstnList" style="margin-top:5px;">
							<table summary="<spring:message code="course.title.reshbank.item.manage"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:80px"/>
									<col style="width:auto"/>
									<col style="width:50px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><spring:message code="common.title.no"/></th>
										<th scope="col"><spring:message code="course.title.reshbank.item"/></th>
										<th scope="col"><spring:message code="common.title.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="3"><spring:message code="course.message.reshbank.item.nodata"/> </td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>				
<script type="text/javascript">
	var ItemDTO = new Object();
	var modalBox = null;

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		$('#tabMenuOper a').click(function (e) {
			  $(this).tab('show');
		});

		ItemDTO.curPage = 1;
		listQstn();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function goList() {
		var url = generateUrl("/mng/org/research/main");
		document.location.href = url ;
	}


	/**
	 * 문제 목록 조회
	 */
	function listQstn() {
		$("#qstnList")
			.load(
		 		cUrl("/mng/org/research/listItem"),		// url
				{
		 			"reshSn" : '${orgReshVO.reshSn}'
		 		}
			);
	}

	/**
	 * 문제 등록 폼 호출
	 */
	function questionWrite() {
		if(parseInt($("#useCnt").val(),10) > 0) {
			alert('<spring:message code="course.message.reshbank.item.alert.add"/>')
			return
		} else{
			var url = generateUrl("/mng/org/research/addItemPop",{"reshSn":"${orgReshVO.reshSn}", "reshTypeCd":"${orgReshVO.reshTypeCd}"});
			var addContent  = "<iframe id='addQuestionFrame' name='addQuestionFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(1100, 600);
			modalBox.setTitle("<spring:message code="course.title.reshbank.item.write"/>");
			modalBox.show();
		}
	}

	/**
	 * 문제 등록 폼 호출 - 엑셀
	 */
	function questionExcelWrite() {
		var url = generateUrl("/mng/org/research/addResearchItemExcelPop",{ "reshSn":"${orgReshVO.reshSn}"});
		var addContent  = "<iframe id='addQuestionFrame' name='addQuestionFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(400, 300);
		modalBox.setTitle("<spring:message code="course.title.reshbank.item.write"/>");
		modalBox.show();
	}

	/**
	 * 시험 문제 수정 폼 호출
	 */
	function questionEdit(reshItemSn) {
		if(parseInt($("#useCnt").val(),10) > 0) {
			alert('<spring:message code="course.message.reshbank.item.alert.edit"/>')
			return
		} else{
			var url = generateUrl("/mng/org/research/editItemPop",{"reshSn":"${orgReshVO.reshSn}","reshItemSn":reshItemSn});
			var addContent  = "<iframe id='addQuestionFrame' name='addQuestionFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(1100, 600);
			modalBox.setTitle("<spring:message code="course.title.reshbank.item.edit"/>");
			modalBox.show();
		}
	}

	function researchSort(sortString){
		$.getJSON( cUrl( "/mng/org/research/sortReserchItem"),
				{ "reshSn": "${orgReshVO.reshSn}", "reshItemSns" : sortString },			// params
				function(data) {
					//alert(data.message);
					listQstn();
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
		 	  		}
				}
			);
	}
	
	//-- 퇴교설문 작성 교육생 리스트
	function listExpulsionStd(page) {
		$("#qstnList")
		.load(
	 		cUrl("/mng/org/research/listExpulsionStd"),		// url
			{
	 			"curPage" : page,
	 			"reshSn" : '${orgReshVO.reshSn}'
	 		}
		);
	}
	
	/**
	 * 설문 결과
	 */
	function reshResult(userNo) {
		var reshSn = '${orgReshVO.reshSn}';
		var addContent  = "<iframe id='reshResultFrame' name='reshResultFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/org/research/reshResultPop"/>"
			+ "?userNo="+userNo+"&amp;reshSn="+reshSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 600);
		parent.modalBox.setTitle("설문결과관리");
		parent.modalBox.show();
	}
</script>
