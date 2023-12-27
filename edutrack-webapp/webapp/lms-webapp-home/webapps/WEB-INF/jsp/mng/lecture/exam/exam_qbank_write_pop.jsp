<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examVO" value="${vo}" />
	<form name="Search" id="Search">
	<div class="well well-sm">
		<table>
			<colgroup>
				<col style="width:15%">
				<col style="width:35%">
				<col style="width:15%">
				<col style="width:35%">
			</colgroup>
			<tbody>
				<tr>
					<td colspan="2" style="padding:2px;">
						<div class="input-group">
							<span class="input-group-addon"><spring:message code="course.title.exambank.select.category"/></span>
							<select name="ctgrCd" id="ctgrCd" onChange="subListCategory(1)" class="form-control input-sm" style="width:255px">
								<option value = ""></option>
							</select>
						</div>
					</td>
					<td colspan="2" style="padding:2px;">
						<div class="input-group">
							<span class="input-group-addon"><spring:message code="course.title.exambank.select.subcategory"/></span>
							<select name="parCtgrCd" id="parCtgrCd" onChange="listQuestion(1)" class="form-control input-sm" style="width:255px">
								<option value = ""></option>
							</select>
						</div>
					</td>
				</tr>
			<%-- 	<tr>
					<td  style="padding:2px;">
						<div class="input-group" style="float:left;">
							<span class="input-group-addon"><spring:message code="lecture.title.exam.question.no"/></span>
							<input type="text" style="width:50px;text-align:right;" name="qstnNo" id="qstnNo" maxlength="9" onfocus="this.select()" class="form-control input-sm"/>
						</div>
					</td>
					<td colspan="3" style="padding:2px;">
						<span style="float:left;lien-height:30px;"><spring:message code="lecture.message.exam.question.msg.qbank"/></span>
					</td>
				</tr> --%>
			</tbody>
		</table>
	</div>
	<div class="text-right">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add.select"/></a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>

	<div id="qstnList" style="margin-top:5px;">
		<table id="qstnTable" summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:100px;"/>
				<col style="width:50px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question.type"/></th>
					<th scope="col" class="text-center"><input type="checkbox" style="border:0" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4">분류를 선택해주세요</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<form id="examForm" name="examForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${examVO.crsCreCd}"/>
	<input type="hidden" name="examSn" value="${examVO.examSn}"/>
	<input type="hidden" name="qstnNo" value=""/>
	<input type="hidden" name="strQstnSn" value=""/>
	<input type="hidden" name="strCtgrCd" value=""/>
	<input type="hidden" name="sbjCd" value=""/>
	<input type="hidden" name="ctgrCd" value=""/>
	</form>
<script type="text/javascript">

	var enableQstnNo;
	$(document).ready(function() {
		enableQstnNo = Number($("#lastQstnNo", parent.subWorkFrame.document ).val()) +1;
		listCategory();

	});

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$(document).find("#Search input[name='sel']").prop({checked:true});
	    }else{
	    	$(document).find("#Search input[name='sel']").prop({checked:false});
	    }
	}

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listCategory() {
		$.getJSON( cUrl("/mng/course/examQbank/listCategory"),		// url
			{ 		},			// params
			listCategoryCallback				// callback function
		);
	}

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listCategoryCallback(ProcessResultListDTO) {
		$("#ctgrCd").find("option").remove();
		var itemList = ProcessResultListDTO.returnList;
		$("#ctgrCd").append("<option value=''><spring:message code="course.title.exambank.select.category"/></option>");
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];
			$("#ctgrCd").append("<option value='"+item.ctgrCd+"'>"+item.ctgrNm+"</option>");
		}
		subListCategory();
	}


	/**
	 * 하위 분류 목록 조회
	 */
	function subListCategory() {
		var parCtgrCd = $("#ctgrCd option:selected").val();
		var useYn = "Y";
		$.getJSON( cUrl("/mng/course/examQbank/subListCategory"),		// url
				{ 
					"parCtgrCd": parCtgrCd 
					,"useYn": useYn
				},			// params
				subListCategoryCallback
			);		
	}	
	/*
	하위 분류 관리 리스트를 가져온다
	*/
	function subListCategoryCallback(ProcessResultListDTO) {
		$("#parCtgrCd option").remove();
		var itemList = ProcessResultListDTO.returnList;
		$("#parCtgrCd").append("<option value=''><spring:message code="course.title.exambank.select.subcategory"/></option>");
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];
			$("#parCtgrCd").append("<option value='"+item.ctgrCd+"'>"+item.ctgrNm+"</option>");
		}
		listQuestion(1);
	}

	/**
	 * 문제 리스틀 가져온다.
	 */
	function listQuestion(page) {
		var ctgrCd = $("#parCtgrCd > option:selected").val();
		var parCtgrCd = $("#ctgrCd > option:selected").val();
		if(ctgrCd !='' && parCtgrCd !='') {
			$("#qstnList")
			.load(
				cUrl("/mng/lecture/exam/listQbankQuestion"),		// url
				{
				  	"ctgrCd" : ctgrCd,
				  	"parCtgrCd" : parCtgrCd,
				  	"curPage" : page
				}
			);
		}
	}

	function addQuestion() {
		var strs = "";
		var strsCtgr ="";
		$('input[name=sel]:checked').each(function() {
				strs = strs + "|" + $(this).val();
				strsCtgr = strsCtgr + "|" + $("#selCtgrCd"+$(this).val()).val();
			}
		);
		strs = strs.substring(1);
		strsCtgr = strsCtgr.substring(1);
		if(strs == "") {
			alert("<spring:message code="lecture.message.exam.question.alert.select.question"/>");
			return ;
		}

	/* 	if(Number($("#qstnNo").val()) > enableQstnNo ){
			alert('<spring:message code="lecture.message.exam.question.alert.input.enableqstnno" arguments="'+enableQstnNo+'"/>');
			return;
		} */
		document.examForm["strQstnSn"].value = strs;
		document.examForm["strCtgrCd"].value = strsCtgr;

	//	document.examForm["qstnNo"].value = document.Search.qstnNo.value;
	//	document.examForm["sbjCd"].value = $("#sbjCd > option:selected").val();
		document.examForm["ctgrCd"].value = $("#ctgrCd > option:selected").val();

		process("addQbankQuestion")
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		//if(!validate(document.examFrom)) return;
		$('#examForm').attr("action","/mng/lecture/exam/" + cmd);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listQuestion();
		} else {
			// 비정상 처리
		}
	}

</script>
