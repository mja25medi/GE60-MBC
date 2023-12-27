<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="examQbankQstnVO" value="${vo}"/>
	<style type="text/css">
		.error {color:red; font-weight:bold;}
		.empty {color:blue; font-weight:bold;}
	</style>

	<form name="Validate" id="Validate" onsubmit="return false;" method="post">
	<input type="hidden" name="qstnSn" id="qstnSn" value="${vo.qstnSn}" />
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }" id="sbjCd" />
	<input type="hidden" name="ctgrCd" value="${vo.ctgrCd }" id="ctgrCd" />


	<div class="input-group" style="float:left;width:80%">
		<p class="empty"><spring:message code="course.message.exambank.typeinfo"/></p>
	</div>
	<div class="text-right" style="width:100%">
		<a href="javascript:saveQuestionBank()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<div class="table-container" style="height:500px; margin-top:10px; ">
		<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak" style="margin-bottom:0px; font-size: 12px; width:1990px; max-width:none;">
			<colgroup>
				<col style="min-width:30px;width:30px;"/>
				<col style="min-width:100px;width:100px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:200px;width:200px;"/>
				<col style="min-width:60px;width:60px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question.type"/> </th>
					<th scope="col"><spring:message code="course.title.exambank.title"/></th>
					<th scope="col"><spring:message code="course.title.reshbank.item.reshitemcts.excel"/></th>
					<th scope="col"><spring:message code="course.title.exambank.item1"/></th>
					<th scope="col"><spring:message code="course.title.exambank.item2"/></th>
					<th scope="col"><spring:message code="course.title.exambank.item3"/></th>
					<th scope="col"><spring:message code="course.title.exambank.item4"/></th>
					<th scope="col"><spring:message code="course.title.exambank.item5"/></th>
					<th scope="col"><spring:message code="course.title.exambank.rightansr"/></th>
					<th scope="col"><spring:message code="course.title.exambank.comment"/></th>
					<th scope="col"><spring:message code="common.title.edit"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${qstnList}" varStatus="status">
				<tr>
					<td class="text-center">
						<input type="checkbox" name="chk" id="chk_${item.lineNo}" value="${item.lineNo}" <c:if test="${not empty item.errorCode }">disabled</c:if>>
						<input type="hidden" name="lineNo" id="lineNo_${item.lineNo}" value="${item.lineNo}" />
						<input type="hidden" name="errorCode" id="errorCode_${item.lineNo}" value="${item.errorCode}" />
					</td>
					<td class="text-center">
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'TYPEQSTNTYPE')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="qstnType-view-${item.lineNo}">
							<meditag:codename category="EXAM_QSTN_TYPE" code="${item.qstnType}"/>
						</span>
						<select name="qstnType" id="qstnType-edit-${item.lineNo}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none" >
							<option value="J" <c:if test="${item.qstnType eq 'J'}">selected</c:if> ><spring:message code="course.title.excel.exambank.question.type.j"/></option>
							<option value="K" <c:if test="${item.qstnType eq 'K'}">selected</c:if>><spring:message code="course.title.excel.exambank.question.type.k"/></option>
							<option value="S" <c:if test="${item.qstnType eq 'S'}">selected</c:if> ><spring:message code="course.title.excel.exambank.question.type.s"/></option>
							<option value="D" <c:if test="${item.qstnType eq 'D'}">selected</c:if>><spring:message code="course.title.excel.exambank.question.type.d"/></option>
						</select>
					</td>
					<td class="text-left">
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'TITLE')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="title-view-${item.lineNo}">
							${item.title }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYTITLE')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="title" id="title-edit-${item.lineNo}" value="${item.title}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>

					<td class="text-left">
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'QSTNCTS')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="qstnCts-view-${item.lineNo}">
							${item.qstnCts }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYQSTNCTS')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="qstnCts" id="qstnCts-edit-${item.lineNo}" value="${item.qstnCts}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>
					<td class="text-left">
						<c:set var="errorCss" value=""/>
							<c:if test="${item.qstnType ne 'K' }"><c:set var="errorCss" value="empty"/></c:if>
							<c:if test="${fn:contains(item.errorCode, 'VIEW1') && item.qstnType eq 'K' }"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="view1-view-${item.lineNo}">
							${item.view1 }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYVIEW1')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="view1" id="view1-edit-${item.lineNo}" value="${item.view1}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>
					<td class="text-left">
						<c:set var="errorCss" value=""/>
							<c:if test="${item.qstnType ne 'K' }"><c:set var="errorCss" value="empty"/></c:if>
							<c:if test="${fn:contains(item.errorCode, 'VIEW2') && item.qstnType eq 'K' }"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="view2-view-${item.lineNo}">
							${item.view2 }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYVIEW2')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="view2" id="view2-edit-${item.lineNo}" value="${item.view2}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>
					<td class="text-left">
						<c:set var="errorCss" value=""/>
							<c:if test="${item.qstnType ne 'K' }"><c:set var="errorCss" value="empty"/></c:if>
							<c:if test="${fn:contains(item.errorCode, 'VIEW3') && item.qstnType eq 'K' }"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="view3-view-${item.lineNo}">
							${item.view3 }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYVIEW3')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="view3" id="view3-edit-${item.lineNo}" value="${item.view3}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>
					<td class="text-left">
						<c:set var="errorCss" value=""/>
							<c:if test="${item.qstnType ne 'K' }"><c:set var="errorCss" value="empty"/></c:if>
							<c:if test="${fn:contains(item.errorCode, 'VIEW4') && item.qstnType eq 'K' }"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="view4-view-${item.lineNo}">
							${item.view4 }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYVIEW4')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="view4" id="view4-edit-${item.lineNo}" value="${item.view4}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>
					<td class="text-left">
						<c:set var="errorCss" value=""/>
							<c:if test="${item.qstnType ne 'K' }"><c:set var="errorCss" value="empty"/></c:if>
							<c:if test="${fn:contains(item.errorCode, 'VIEW5') && item.qstnType eq 'K' }"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="view5-view-${item.lineNo}">
							${item.view5 }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYVIEW5')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="view5" id="view5-edit-${item.lineNo}" value="${item.view5}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>

					<td class="text-left">
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'RGTANSR')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="rgtAnsr-view-${item.lineNo}">
							${item.rgtAnsr }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYRGTANSR')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="rgtAnsr" id="rgtAnsr-edit-${item.lineNo}" value="${item.rgtAnsr}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>
					<td class="text-left">
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'QSTNEXPL')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="qstnExpl-view-${item.lineNo}">
							${item.qstnExpl }
							<c:if test="${fn:contains(item.errorCode, 'EMPTYQSTNEXPL')}"><spring:message code="common.message.required"/></c:if>
						</span>
						<input type="text" name="qstnExpl" id="qstnExpl-edit-${item.lineNo}" value="${item.qstnExpl}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/>
					</td>

					<td class="text-center">
						<a id="editBtn_${item.lineNo}" href="javascript:editLine('${item.lineNo}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
						<a id="checkBtn_${item.lineNo}" href="javascript:checkLine('${item.lineNo}')" class="btn btn-warning btn-sm" style="display:none"><spring:message code="button.process"/> </a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty qstnList }">
				<tr>
					<td colspan="12"><spring:message code="user.message.userinfo.nodata"/></td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div>
	</form>
	<form id="examQbankForm" name="examQbankForm" onsubmit="return false" >
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }" id="sbjCd" />
	<input type="hidden" name="ctgrCd" value="${vo.ctgrCd }" id="ctgrCd" />
	<input type="hidden" name="lineNo" value="" id="lineNo" />
	<input type="hidden" name="title" value="" id="title" />
	<input type="hidden" name="qstnType" value="" id="qstnType" />
	<input type="hidden" name="qstnCts" value="" id="qstnCts" />
	<input type="hidden" name="view1" value="" id="view1" />
	<input type="hidden" name="view2" value="" id="view2" />
	<input type="hidden" name="view3" value="" id="view3" />
	<input type="hidden" name="view4" value="" id="view4" />
	<input type="hidden" name="view5" value="" id="view5" />
	<input type="hidden" name="rgtAnsr" value="" id="rgtAnsr" />
	<input type="hidden" name="qstnExpl" value="" id="qstnExpl" />
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		parent.modalBox.resize(1100,600);

	});

	function editLine(lineNo) {
		// unchecked and disabled checkbox
		$("#chk_"+lineNo).attr("checked", false);
		$("#chk_"+lineNo).attr("disabled", true);

		$("#editBtn_"+lineNo).hide();
		$("#checkBtn_"+lineNo).show();

		$(".value-view-"+lineNo).hide();
		$(".value-edit-"+lineNo).show();
	}

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	    	$("#Validate input[name='chk']:enabled").prop({checked:true});
	    }else{
	    	$("#Validate input[name='chk']:enabled").prop({checked:false});
	    }
	}


	function checkLine(lineNo) {
		var qstnType = $("#qstnType-edit-"+lineNo).val();
		var title = $("#title-edit-"+lineNo).val();
		var qstnCts = $("#qstnCts-edit-"+lineNo).val();
		var view1 = $("#view1-edit-"+lineNo).val();
		var view2 = $("#view2-edit-"+lineNo).val();
		var view3 = $("#view3-edit-"+lineNo).val();
		var view4 = $("#view4-edit-"+lineNo).val();
		var view5 = $("#view5-edit-"+lineNo).val();
		var rgtAnsr = $("#rgtAnsr-edit-"+lineNo).val().toUpperCase();
		if(qstnType == "K") {
			rgtAnsr = parseInt(rgtAnsr,10)+"";
			$("#rgtAnsr-edit-"+lineNo).val(rgtAnsr);
		}
		var qstnExpl= $("#qstnExpl-edit-"+lineNo).val();

		if(isEmpty(title)) {
			alert('<spring:message code="log.message.msg.alert.input.title"/>');
			$("#qstnCts-edit-"+lineNo).focus();
			$("#qstnCts-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#qstnCts-edit-"+lineNo).removeClass("validerr");
		}
		if(isEmpty(qstnCts)) {
			alert('<spring:message code="course.message.reshbank.item.alert.input.desc"/>');
			$("#qstnCts-edit-"+lineNo).focus();
			$("#qstnCts-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#qstnCts-edit-"+lineNo).removeClass("validerr");
		}
		if(isEmpty(rgtAnsr)) {
			alert('<spring:message code="course.message.exambank.question.alert.input.answer"/>');
			$("#rgtAnsr-edit-"+lineNo).focus();
			$("#rgtAnsr-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#rgtAnsr-edit-"+lineNo).removeClass("validerr");
		}
		/* 시험문제등록 해설 필수값 체크해제
		if(isEmpty(qstnExpl)) {
			alert('<spring:message code="lecture.message.exam.question.alert.input.desc"/>');
			$("#qstnExpl-edit-"+lineNo).focus();
			return;
		}
 		*/

		if(qstnType == "K"){
			if(isEmpty(view1)) {
				alert('<spring:message code="course.message.excel.exambank.item"/>');
				$("#view1-edit-"+lineNo).focus();
				$("#view1-edit-"+lineNo).addClass("validerr");
				return;
			} else {
				$("#view1-edit-"+lineNo).removeClass("validerr");
			}
			if(isEmpty(view2)) {
				alert('<spring:message code="course.message.excel.exambank.item"/>');
				$("#view2-edit-"+lineNo).focus();
				$("#view2-edit-"+lineNo).addClass("validerr");
				return;
			} else {
				$("#view2-edit-"+lineNo).removeClass("validerr");
			}
			if(isEmpty(view3)) {
				alert('<spring:message code="course.message.excel.exambank.item"/>');
				$("#view3-edit-"+lineNo).focus();
				$("#view3-edit-"+lineNo).addClass("validerr");
				return;
			} else {
				$("#view3-edit-"+lineNo).removeClass("validerr");
			}
			if(isEmpty(view4)) {
				alert('<spring:message code="course.message.excel.exambank.item"/>');
				$("#view4-edit-"+lineNo).focus();
				$("#view4-edit-"+lineNo).addClass("validerr");
				return;
			} else {
				$("#view4-edit-"+lineNo).removeClass("validerr");
			}
			if(!isNumber(rgtAnsr)){
				alert('<spring:message code="course.message.excel.exambank.rgtAnsr.number"/>');
				$("#rgtAnsr-edit-"+lineNo).focus();
				$("#rgtAnsr-edit-"+lineNo).addClass("validerr");
				return;
			} else {
				$("#rgtAnsr-edit-"+lineNo).removeClass("validerr");
				if(rgtAnsr > 5){
					alert('<spring:message code="course.message.excel.exambank.rgtAnsr.number5"/>');
					$("#rgtAnsr-edit-"+lineNo).focus();
					$("#rgtAnsr-edit-"+lineNo).addClass("validerr");
					return;
				} else {
					$("#rgtAnsr-edit-"+lineNo).removeClass("validerr");
				}
				if(isEmpty(view5) && rgtAnsr > 4 ){
					alert('<spring:message code="lecture.message.exam.question.alert.input.answer4"/>');
					$("#rgtAnsr-edit-"+lineNo).focus();
					$("#rgtAnsr-edit-"+lineNo).addClass("validerr");
					return;
				} else {
					$("#rgtAnsr-edit-"+lineNo).removeClass("validerr");
				}
			}
		}
		if(qstnType == "S"){
			if("O" != rgtAnsr && "X" != rgtAnsr){
				alert('<spring:message code="course.message.excel.exambank.rgtAnsr.s"/>');
				$("#rgtAnsr-edit-"+lineNo).focus();
				$("#rgtAnsr-edit-"+lineNo).addClass("validerr");
				return;
			} else {
				$("#rgtAnsr-edit-"+lineNo).removeClass("validerr");
			}
		}

		$("#lineNo").val(lineNo);
		$("#qstnType").val(qstnType);
		$("#title").val(title);
		$("#qstnCts").val(qstnCts);
		$("#view1").val(view1);
		$("#view2").val(view2);
		$("#view3").val(view3);
		$("#view4").val(view4);
		$("#view5").val(view5);
		$("#rgtAnsr").val(rgtAnsr);
		$("#qstnExpl").val(qstnExpl);

		$("#examQbankForm").attr("action","/mng/course/examQbank/questionUploadCheck");
		$('#examQbankForm').ajaxSubmit(checkLineCallback);
	}

	function checkLineCallback(resultDTO) {
		//var result = resultDTO.returnDto;
		//alert(showAttribute(resultDTO));
		//alert(resultDTO.errorCode);
		var lineNo = resultDTO.lineNo;
		var qstnTypeText = "";
		if(isEmpty(resultDTO.errorCode)) {
			//-- error class 삭제
			$(".value-view-"+lineNo).removeClass("error");
			//-- check box 활성화
			$("#chk_"+lineNo).attr("disabled", false);
			if(resultDTO.qstnType == "J"){
				qstnTypeText = "<spring:message code="course.title.excel.exambank.question.type.j"/>";
			} else if(resultDTO.qstnType == "K"){
				qstnTypeText = "<spring:message code="course.title.excel.exambank.question.type.k"/>";
			} else if(resultDTO.qstnType == "S"){
				qstnTypeText = "<spring:message code="course.title.excel.exambank.question.type.s"/>";
			} else if(resultDTO.qstnType == "D"){
				qstnTypeText = "<spring:message code="course.title.excel.exambank.question.type.d"/>";
			}

			$("#qstnType-view-"+lineNo).html(qstnTypeText);
			$("#title-view-"+lineNo).html(resultDTO.title);
			$("#qstnCts-view-"+lineNo).html(resultDTO.qstnCts);
			$("#view1-view-"+lineNo).html(resultDTO.view1);
			$("#view2-view-"+lineNo).html(resultDTO.view2);
			$("#view3-view-"+lineNo).html(resultDTO.view3);
			$("#view4-view-"+lineNo).html(resultDTO.view4);
			$("#view5-view-"+lineNo).html(resultDTO.view5);
			$("#rgtAnsr-view-"+lineNo).html(resultDTO.rgtAnsr);
			$("#qstnExpl-view-"+lineNo).html(resultDTO.qstnExpl);

			$("#errorCode_"+lineNo).val("");

			$("#checkBtn_"+lineNo).hide();
			$("#editBtn_"+lineNo).show();

			$(".value-edit-"+lineNo).hide();
			$(".value-view-"+lineNo).show();

			var errCnt = 0;
			if(resultDTO.qstnType != 'K') {
				if(resultDTO.view1 != "") {
					$("#view1-view-"+lineNo).addClass("empty");
					errCnt++;
				}
				if(resultDTO.view2 != "") {
					$("#view2-view-"+lineNo).addClass("empty");
					errCnt++;
				}
				if(resultDTO.view3 != "") {
					$("#view3-view-"+lineNo).addClass("empty");
					errCnt++;
				}
				if(resultDTO.view4 != "") {
					$("#view4-view-"+lineNo).addClass("empty");
					errCnt++;
				}
				if(resultDTO.view5 != "") {
					$("#view5-view-"+lineNo).addClass("empty");
					errCnt++;
				}
				if(errCnt > 0) {
					$("#chk_"+lineNo).attr("checked", false);
					$("#chk_"+lineNo).attr("disabled", true);
				}
			}

		} else {
			//-- 오류가 있는 경우
			$("#errorCode_"+lineNo).val(resultDTO.errorCode);
			//-- check box 비활성화
			$("#chk_"+lineNo).attr("disabled", true);
		}
	}

	function saveQuestionBank() {
		var questionList = $("#Validate input[name='chk']:checked").stringValues();
		if(isEmpty(questionList)) {
			alert("<spring:message code="course.message.reshbank.select.item"/>");
			return;
		}
		else {
			$("#Validate").attr("action","/mng/course/examQbank/questionUpload");
			$('#Validate').ajaxSubmit(saveQuestionBankCallback);
		}
	}

	function saveQuestionBankCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listQuestion('1');
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
</script>
