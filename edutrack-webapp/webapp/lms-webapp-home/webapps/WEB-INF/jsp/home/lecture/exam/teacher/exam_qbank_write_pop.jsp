<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="examVO" value="${vo}" />
	<form name="Search" id="Search">
	<div class="well well-sm">
		<div class="modal_cont">
                    <div class="row mb20">
                        <label for="typeSelect" class="form-label col-sm-2">상위분류선택</label>
                        <div class="col-sm-4">
                            <div class="form-row">
                                <select name="ctgrCd" id="ctgrCd" onChange="subListCategory(1)" class="form-select" >
								<option value = ""></option>
							</select>
                            </div>             
                        </div>
                        <label for="typeSelect2" class="form-label col-sm-2">하위분류선택</label>
                        <div class="col-sm-4">
                            <div class="form-row">
                                <select name="parCtgrCd" id="parCtgrCd" onChange="listQuestion(1)" class="form-select" style="width:255px">
								<option value = ""></option>
							</select>
                            </div>             
                        </div>
                    </div>
                    <div class="res_tbl_wrap" id="qstnList">
                        <table  id="qstnTable">
                            <caption>시험문제 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col">시험문제</th>
                                    <th scope="col" width="15%">문제유형</th>
                                    <th scope="col" width="10%"><span class="custom-input onlychk"><input type="checkbox" id="chkall"><label for="chkall"></label></span></th>
                                </tr>
                            </thead>
                            <tbody>
                            <tr>
								<td colspan="4">분류를 선택해주세요</td>
							</tr>
                                <!-- <tr>
                                    <td scope="row" data-label="번호">1</td>
                                    <td class="title" data-label="시험문제">문학이 현대사회에서 미치는 영향을 고르시오.</td>
                                    <td data-label="문제유형">선택형</td>
                                    <td data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk1"><label for="chk1"></label></span></td>
                                </tr> -->
                            </tbody>
                        </table>
                    </div>
               </div>
                  <div class="modal_btns">
                    <button type="button" class="btn type2" onclick="addQuestion()">저장</button>
                    <button type="button" class="btn" onclick="parent.modalBoxClose()">닫기</button>
                </div>
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
		enableQstnNo = Number($("#lastQstnNo", parent.examForm.document ).val()) +1;
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
		$.getJSON( cUrl("/lec/examQbank/listCategory"),		// url
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
		$.getJSON( cUrl("/lec/examQbank/subListCategory"),		// url
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
				cUrl("/lec/examQbank/listQbankQuestion"),		// url
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
		$('#examForm').attr("action","/lec/exam/" + cmd);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.document.location.reload();
		} else {
			// 비정상 처리
		}
	}

</script>