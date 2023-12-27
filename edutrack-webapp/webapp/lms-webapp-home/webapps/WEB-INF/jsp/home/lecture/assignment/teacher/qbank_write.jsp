<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<c:set var="assignmentVO" value="${vo}" />
<mhtml:class_html>
<mhtml:class_head>
	<meditag:css href="css/home/pop.css" />
	<meditag:js src="/js/selectbox.js"/>
</mhtml:class_head>
<mhtml:body>
<!--CONTENTS START-->
<div class="wrap" style="width:740px;">
		<h1>시험문제은행</h1>
		<div class="contents">
			<form name="Search" id="Search">
			<table class="board_b" style="width:100%;">
				<caption class="sr-only">시험 문제 등록 폼</caption>
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tbody>
				<tr>
					<th scope="row">과 목</th>
					<td>
						<select name="sbjCd" id="sbjCd" style="width:180px" onChange="changeList()">
						<c:forEach items="${subjectList}" var="itemSubject">
							<c:choose>
								<c:when test="${itemSubject.sbjCd eq examQuestionVO.sbjCd}">
									<c:set var="strSelectSbj" value="selected='selected'"/>
								</c:when>
								<c:otherwise>
									<c:set var="strSelectSbj" value=""/>
								</c:otherwise>
							</c:choose>
							<option value="${itemSubject.sbjCd}" ${strSelectSbj}>${itemSubject.sbjNm}</option>
						</c:forEach>
						</select>
					</td>
					<th scope="row">항 목</th>
					<td>
						<select name="ctgrCd" id="ctgrCd" style="width:180px" onChange="changeList()" >
						<c:if test="${empty sbjCateList }">
							<option value="">항목이 없습니다.</option>
						</c:if>
						<c:forEach items="${sbjCateList}" var="itemCate">
							<c:choose>
								<c:when test="${itemCate.ctgrCd eq examQuestionVO.ctgrCd}">
									<c:set var="strSelectCate" value="selected='selected'"/>
								</c:when>
								<c:otherwise>
									<c:set var="strSelectCate" value=""/>
								</c:otherwise>
							</c:choose>
							<option value="${itemCate.ctgrCd}" ${strSelectCate}>${itemCate.ctgrNm}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				</tbody>
			</table>
			<div class="btn_right">
				<a href="javascript:addQuestion()" class="btn02">선택출제</a>
				<a href="javascript:window.close()" class="btn01">닫기</a>
			</div>
			<table class="board_b" align="center" style="margin-top: 10px;">
				<colgroup>
					<col style="width:50"/>
					<col style="width:auto"/>
					<col style="width:10%"/>
				</colgroup>
				<thead>
				<tr>
					<th scope="col" width="50">번호</th>
					<th scope="col">문제</th>
					<th scope="col" width="8%"><input type="checkbox" name="chkAll" id="chkAll" style="border:0"/></th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${bankQstnList}" var="itemQstn" varStatus="status">
						<tr>
							<td>${status.count+1}</td>
							<td class="subject"><a href="<c:url value="javascript:editFormQuestion('${itemQstn.qstnSn}')"/>">${itemQstn.title}</a></td>
							<td><input type="checkbox" name="sel" value="${itemQstn.qstnSn}" style="border:0"/></td>
						</tr>
					</c:forEach>
					<c:if test="${empty bankQstnList}">
						<tr>
							<td colspan="3" style="text-align: center;">* 등록된 문제가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			</form>
			<form id="assignmentForm" name="assignmentForm" onsubmit="return false">
				<input type="hidden" name="crsCreCd" value="${assignmentVO.crsCreCd}"/>
				<input type="hidden" name="asmtSn" value="${assignmentVO.asmtSn}"/>
				<input type="hidden" name="sbjCd" value=""/>
				<input type="hidden" name="ctgrCd" value=""/>
				<input type="hidden" name="strQstnSn" value=""/>
			</form>
	     </div>
        <!--//CONTENTS END-->
      </div>

<script type="text/javascript">

	$(document).ready(function() {
		//listCategory();

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
		document.assignmentForm["strQstnSn"].value = strs;

		document.assignmentForm["sbjCd"].value = SearchsbjCd.getSelectedValue();
		document.assignmentForm["ctgrCd"].value = SearchctgrCd.getSelectedValue();

		process("addQbankSub");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		//if(!validate(document.assignmentFrom)) return;
		$('#assignmentForm').attr("action","/lec/assignment/" + cmd);
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

	function goList() {
		document.location.href = cUrl("/lec/assignment/tchAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}

</script>
</mhtml:body>
</mhtml:class_html>