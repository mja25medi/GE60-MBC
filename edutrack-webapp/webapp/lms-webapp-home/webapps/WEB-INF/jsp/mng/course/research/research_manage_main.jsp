<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="researchBankVO" value="${vo}" />
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<div class="row" id="content">
					<div class="col-md-12">
						<form id="researchBankForm" name="researchBankForm" onsubmit="return false" action="/mng/course/researchBank">
						<input type="hidden" name="reshSn" value="${vo.reshSn }" />
						<input type="hidden" name="itemCnt" id="itemCnt" value="${vo.itemCnt }"/>
						<input type="hidden" name="useCnt" id="useCnt" value="${vo.useCnt }"/>
						</form>
						<table summary="<spring:message code="course.title.reshbank.item.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:20%"/>
								<col style="width:80%"/>
							</colgroup>
							<tbody>
							<tr>
								<th scope="row" class="top"><spring:message code="course.title.reshbank.title"/></th>
								<td class="wordbreak">
									${researchBankVO.reshTitle}
								</td>
							</tr>
							<tr>
								<th scope="row"><spring:message code="course.title.reshbank.desc"/></th>
								<td class="wordbreak">
									${researchBankVO.reshCts}
								</td>
							</tr>
							</tbody>
						</table>
						<div class="text-right">
							<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
						</div>
						<ul class="nav nav-tabs" id="tabMenuExam">
							<li class="active"><a href="#"><spring:message code="course.title.reshbank.item.manage.tab"/></a></li>
						</ul>
						<br/>
						<div class="text-left">
						<c:if test="${researchBankVO.useCnt > 0 }">
							<p style="font-weight: bold;"><spring:message code="course.title.reshbank.item.using"/></p>
						</c:if>
						</div>
						<div class="text-right">
						<c:if test="${researchBankVO.useCnt == 0 }">
							<a href="javascript:questionWrite();" class="btn btn-primary btn-sm" ><spring:message code="button.write.question"/> </a>
							<a href="javascript:questionExcelWrite()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
						</c:if>
						</div>
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

		ItemDTO.curPage = 1;
		listQstn();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function goList() {
		var url = generateUrl("/mng/course/researchBank/main");
		document.location.href = url;
	}


	/**
	 * 문제 목록 조회
	 */
	function listQstn() {
		$("#qstnList")
			.load(
		 		cUrl("/mng/course/researchBank/listItem"),		// url
				{
		 			"reshSn" : '${researchBankVO.reshSn}'
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
			var url = generateUrl("/mng/course/researchBank/addItemPop",{"reshSn":"${researchBankVO.reshSn}"});
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
		var url = generateUrl("/mng/course/researchBank/addResearchItemExcelPop",{ "reshSn":"${researchBankVO.reshSn}"});
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
			var url = generateUrl("/mng/course/researchBank/editItemPop",{"reshSn":"${researchBankVO.reshSn}","reshItemSn":reshItemSn});
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
		$.getJSON( cUrl( "/mng/course/researchBank/sortReserchItem"),
				{ "reshSn": "${researchBankVO.reshSn}", "reshItemSns" : sortString },			// params
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

</script>
