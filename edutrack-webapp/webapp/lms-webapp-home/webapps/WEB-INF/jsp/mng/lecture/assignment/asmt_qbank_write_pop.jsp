<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${assignmentForm.gubun}"/>
<c:set var="assignmentVO" value="${vo}" />

	<br>
	<form name="Search" id="Search">
	<table summary="시험 문제 등록 폼" class="table_dtl" style="width:96%" align="center">
		<tr height="35">
			<th class="top" scope="row" style="width:20%">과 목</th>
			<td class="top" style="width:30%">
				<meditag:selectbox fieldName="sbjCd" formName="Search" width="220" height="100" onChange="changeSubject()" />
				<c:forEach items="${subjectList}" var="itemSubject">
				<meditag:selectboxOption formName="Search" fieldName="sbjCd" value="${itemSubject.sbjCd}" text="${itemSubject.sbjNm}" />
				</c:forEach>
			</td>
			<th class="top" scope="row" style="width:20%">항 목</th>
			<td class="top" style="width:30%">
				<meditag:selectbox fieldName="ctgrCd" formName="Search" width="220" height="100" onChange="changeCategory()" />
			</td>
		</tr>
	</table>
	<meditag:buttonwrapper style="padding: 6px; width: 96%">
		<meditag:button value="선택출제" title="선택한 문제를 시험 문제로 등록합니다." func="addQuestion()" />
		<meditag:button value="닫기" title="문제은행 보기 창을 닫습니다." func="parent.asmtPopBox.close()" />
	</meditag:buttonwrapper>
	<table summary="과제 문제은행 문제 리스트" style="width:98%" class="table_list">
		<thead>
		<tr>
			<th scope="col" width="50">번호</th>
			<th scope="col">문제</th>
			<th scope="col" width="8%"><input type="checkbox" name="chkAll" id="chkAll" style="border:0"/></th>
		</tr>
		</thead>
		<tbody id="tbodyList" style="overflow:auto; width:100%; height:300px"></tbody>
	</table>
	</form>
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" action="/LectureAssignmentAdmin.do">
	<input type="hidden" name="cmd" />
	<input type="hidden" name="crsCreCd" value="${assignmentVO.crsCreCd}"/>
	<input type="hidden" name="asmtSn" value="${assignmentVO.asmtSn}"/>
	<input type="hidden" name="sbjCd" value="${assignmentVO.sbjCd}"/>
	<input type="hidden" name="ctgrCd" value="${assignmentVO.ctgrCd}"/>
	<input type="hidden" name="strQstnSn" value=""${assignmentVO.strQstnSn}/>
	</form>
<script type="text/javascript">

	$(document).ready(function() {
		listCategory();

        //전체선택 /전체취소
		$('#chkAll').click(function(){
		    var state=$('input[name=chkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#Search input[name='sel']").attr({checked:true});
		    }else{
		    	$(document).find("#Search input[name='sel']").attr({checked:false});
		    }
		});
	});

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listCategory() {
		var sbjCd = SearchsbjCd.getSelectedValue();
		$.getJSON( cUrl("/CourseAssignmentQbankAdmin.do"),		// url
			{ "cmd" : "listCategory",
			  "sbjCd" : sbjCd
			},			// params
			listCategoryCallback				// callback function
		);
	}

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listCategoryCallback(ProcessResultListDTO) {
		SearchctgrCd.removeOption();
		var itemList = ProcessResultListDTO.returnList;
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];
			SearchctgrCd.addOption(item.ctgrNm, item.ctgrCd);
		}
		listQuestion();
	}

	/**
	 * 문제 리스틀 가져온다.
	 */
	function listQuestion() {
		var sbjCd = SearchsbjCd.getSelectedValue();
		var ctgrCd = SearchctgrCd.getSelectedValue();
		$.getJSON( cUrl("/CourseAssignmentQbankAdmin.do"),		// url
			{ "cmd" : "listQuestion",
			  "sbjCd" : sbjCd,
			  "ctgrCd" : ctgrCd
			},			// params
			listQuestionCallback				// callback function
		);
		displayWorkProgress();
	}

	/**
	 * 항목 리스틀 가져온다.
	 */
	function listQuestionCallback(ProcessResultListDTO) {
		var itemList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(itemList.length == 0) {
			listStr +="	<tr><td colspan='4' align='center'><font color=blud>* 등록된 문제가 없습니다.</font></td></tr>\n";
		}
		for (var i=0; i<itemList.length; i++) {
			var item = itemList[i];

			listStr +="	<tr>\n";
			listStr +="		<td >"+(i+1)+"</td>\n";
			listStr +="		<td class='left'><a href=\"javascript:editFormQuestion('"+item.qstnSn+"')\">"+item.qstnTitle+"</a></td>\n";
			listStr +="		<td ><input type='checkbox' name='sel' value='"+item.qstnSn+"' style='border:0'></td>\n";
			listStr +="	</tr>\n";
		}
		$("#tbodyList").html(listStr);
		closeWorkProgress();
	}

	function addQuestion() {
		var strs = "";
		$('input[name=sel]:checked').each(function() {
				strs = strs + "|" + $(this).val();
			}
		);
		strs = strs.substring(1);
		if(strs == "") {
			alert("문제를 선택해 주세요.");
			return ;
		}
		document.assignmentForm["assignmentVO.strQstnSn"].value = strs;

		document.assignmentForm["assignmentVO.sbjCd"].value = SearchsbjCd.getSelectedValue();
		document.assignmentForm["assignmentVO.ctgrCd"].value = SearchctgrCd.getSelectedValue();

		process("addQbankSub")
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		//if(!validate(document.assignmentFrom)) return;
		$('#assignmentForm').find('input[name=cmd]').val(cmd);
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listSub();
		} else {
			// 비정상 처리
		}
	}

</script>
