<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<c:set var="mexams" value="${fn:split(vo.view1,'|')}"/>
<c:set var="mansrs" value="${fn:split(vo.view2,'|')}"/>
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
	<!-- include summernote css/js-->
	<meditag:css href="libs/summernote/summernote.css"/>
	<meditag:css href="css/summernote_custom.css"/>
	<meditag:js src="/libs/summernote/summernote.js"/>
	<meditag:js src="/libs/summernote/lang/summernote-ko-KR.js"/>
	<meditag:js src="/libs/summernote/lang/summernote-ja-JP.js"/>
	<meditag:js src="/js/common_summernote.js"/>
	
	<style type="text/css">
		.iexam-qstn {
			padding:3px;
			background-color:#f3f3f3;
			border:1px solid gray;
		}
	</style>
	<div class="row" id="content">
		<div class="box">
		<div class="box-body">
		<div id="displayArea">
	<form id="examQstnForm" name="examQstnForm" onsubmit="return false"  >
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }"/>
	<input type="hidden" name="ctgrCd" value="${vo.parCtgrCd }" />
	<input type="hidden" name="parCtgrCd" value="${vo.ctgrCd }" />
	<input type="hidden" name="qstnSn" value="${vo.qstnSn }" />
	<input type="hidden" name="rgtAnsr" value="${vo.rgtAnsr }" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }"/>
	<input type="hidden" name="editorYn" value="Y"/>
	<input type="hidden" name="qstnType" id="qstnType" value="${vo.qstnType }"/>
	<input type="hidden" name="searchKey" id="searchKey" value="${searchKey }"/>
	<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:10%"/>
			<col style="width:40%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.title"/></th>
			<td colspan="3"><input type="text" name="title" value="${vo.title }" maxlength="200"  isNull="N" dispName="제목" lenCheck="200" class="form-control input-sm" /></td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span>내용</th>
			<td colspan="3" style="padding:0px;">
				<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${examQbankQstnVO.qstnCts}</div>
				<textarea name="qstnCts" id="contentTextArea"  class="sr-only">${vo.qstnCts }</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.item1"/></th>
			<td colspan="3">
				<div id="summernote1" style="float:left; width:100%; margin: 0 auto;">${vo.view1 }</div>
				<textarea name="view1" id="view1"  class="sr-only">${vo.view1 }</textarea></td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.item2"/></th>
			<td colspan="3">
				<div id="summernote2" style="float:left; width:100%; margin: 0 auto;">${vo.view2 }</div>
				<textarea name="view2" id="view2"  class="sr-only">${vo.view2 }</textarea></td>
			</td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.item3"/></th>
			<td colspan="3">
				<div id="summernote3" style="float:left; width:100%; margin: 0 auto;">${vo.view3 }</div>
				<textarea name="view3" id="view3"  class="sr-only">${vo.view3 }</textarea></td>
			
			</td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item4"/></th>
			<td colspan="3">
				<div id="summernote4" style="float:left; width:100%; margin: 0 auto;">${vo.view4 }</div>
				<textarea name="view4" id="view4"  class="sr-only">${vo.view4 }</textarea></td>
			
			</td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item5"/></th>
			<td colspan="3">
				<div id="summernote5" style="float:left; width:100%; margin: 0 auto;">${vo.view5 }</div>
				<textarea name="view5" id="view5"  class="sr-only">${vo.view5 }</textarea></td>
			</td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<ul style="list-style-type: none;padding-left:0px;">
				
					<li style="float:left;margin-right:10px;">
						1.<input type="radio" name="answerK" value="1" style="border:0" <c:if test="${vo.rgtAnsr eq '1'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						2.<input type="radio" name="answerK" value="2" style="border:0" <c:if test="${vo.rgtAnsr eq '2'}">checked="checked"</c:if> />
					</li>
					<li style="float:left;margin-right:10px;">
						3.<input type="radio" name="answerK" value="3" style="border:0" <c:if test="${vo.rgtAnsr eq '3'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						4.<input type="radio" name="answerK" value="4" style="border:0" <c:if test="${vo.rgtAnsr eq '4'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						5.<input type="radio" name="answerK" value="5" style="border:0" <c:if test="${vo.rgtAnsr eq '5'}">checked="checked"</c:if>/>
					</li>
				</ul>
			</td>
		</tr>
		<tr class="qstnTypeD qstnType">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<textarea name="answerD" class="form-control input-sm">${vo.rgtAnsr}</textarea><br>
				<spring:message code="course.message.exambank.msg1"/>&nbsp;<spring:message code="course.message.exambank.msg2"/>
			</td>
		</tr>
		<tr class="qstnTypeS qstnType">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<ul style="list-style-type: none;padding-left:0px;">
					<li style="float:left;margin-right:10px;">
						O.<input type="radio" name="answerS" value="O" style="border:0" <c:if test="${vo.rgtAnsr eq 'O'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						X.<input type="radio" name="answerS" value="X" style="border:0" <c:if test="${vo.rgtAnsr eq 'X'}">checked="checked"</c:if>/>
					</li>
				</ul>
			</td>
		</tr>
		<tr class="qstnTypeJ qstnType">
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<textarea name="answerJ" class="form-control input-sm">${vo.rgtAnsr}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeM qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item"/></th>
			<td colspan="3">
				<table class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:10%"/>
						<col style="width:45%"/>
						<col style="width:45%"/>
					</colgroup>
					<tr>
						<th></th>
						<th><spring:message code="course.title.exambank.question"/></th>
						<th><spring:message code="course.title.exambank.rightansr"/></th>
					</tr>
					<c:forEach var="no" begin="1" end="10">
						<c:set var="mexam_value" value=""/>
						<c:forEach var="mexam" items="${mexams}" varStatus="status">
							<c:if test="${no == status.count }"><c:set var="mexam_value" value="${mexam}"/></c:if>
						</c:forEach>
						<c:set var="mansr_value" value=""/>
						<c:forEach var="mansr" items="${mansrs}" varStatus="status">
							<c:if test="${no == status.count }"><c:set var="mansr_value" value="${mansr}"/></c:if>
						</c:forEach>
					<tr>
						<td><spring:message code="course.title.exambank.question"/>${no}</td>
						<td><input type="text" name="mexam_${no}" class="form-control input-sm" value="${mexam_value}"/></td>
						<td><input type="text" name="mansr_${no}" class="form-control input-sm" value="${mansr_value}"/></td>
					</tr>
					</c:forEach>
					<c:forEach var="no" begin="11" end="20">
					<tr>
						<td><spring:message code="course.title.exambank.question"/>${no}</td>
						<td></td>
						<td><input type="text" name="mansr_${no}" class="form-control input-sm" value=""/></td>
					</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr class="qstnTypeI qstnType">
			<td colspan="4" class="text-right">
				<a href="javascript:InnerQstn('choice')" class="btn btn-info btn-sm">선택형</a>
				<a href="javascript:InnerQstn('input')" class="btn btn-info btn-sm">입력형</a>
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="course.title.exambank.comment"/></th>
			<td colspan="3">
				<textarea name="qstnExpl" class="form-control input-sm" >${vo.qstnExpl}</textarea>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:deleteQuestion()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:closeWorkArea()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	</form>
	</div>
	</div>
	</div>
	</div>
	
<script type="text/javascript">
	var summernote;
	var summernote1;
	var summernote2;
	var summernote3;
	var summernote4;
	var summernote5;

	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"EXAM_BANK",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"330px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
		});
		summernote1 = new $M.SummerNote({
			"editorId"			:	"summernote1",
			"textareaId"		:	"view1",
			"repositoryCode"	:	"EXAM_BANK",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"50px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}'),
			"toolbar"			: "hide"
		});
		summernote2 = new $M.SummerNote({
			"editorId"			:	"summernote2",
			"textareaId"		:	"view2",
			"repositoryCode"	:	"EXAM_BANK",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"50px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}'),
			"toolbar"			: "hide"
		});
		summernote3 = new $M.SummerNote({
			"editorId"			:	"summernote3",
			"textareaId"		:	"view3",
			"repositoryCode"	:	"EXAM_BANK",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"50px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}'),
			"toolbar"			: "hide"
		});
		summernote4 = new $M.SummerNote({
			"editorId"			:	"summernote4",
			"textareaId"		:	"view4",
			"repositoryCode"	:	"EXAM_BANK",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"50px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}'),
			"toolbar"			: "hide"
		});
		summernote5 = new $M.SummerNote({
			"editorId"			:	"summernote5",
			"textareaId"		:	"view5",
			"repositoryCode"	:	"EXAM_BANK",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"50px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}'),
			"toolbar"			: "hide"
		});

		//summernote1.options.toolbar.disabled();


		//summernote1.invoke('toolbar.deactivate', true);
		//$('.note-toolbar').get()[1].hide();
		//var summernoteArr = $('.note-toolbar').get();
		//summernoteArr[1].hide();
		//alert(summernoteArr.length);
		changeType();
	});

	function changeType() {
		var qstnType = '${vo.qstnType}';
		$(".qstnType").hide();
		$(".qstnType"+qstnType).show();
	}

	function InnerQstn(str) {
		iexam_cnt++;
		var $dom = $("<span class='iexam-qstn' id='iexam-qstn-"+iexam_cnt+"'>{:Select:}</span> ");
		if(str == 'input') $dom = $("<span class='iexam-qstn' id='iexam-qstn-"+iexam_cnt+"'>{:Input:}</span> ");
		summernote.insertObject($dom[0]);
		summernote.insertText(' ');
	}
	

	function submitCheck(cmd) {
		$('#examQstnForm').attr("action","/mng/course/examQbank/" + cmd);
		var f = document.examQstnForm;
		
		var qstnType = f["qstnType"].value;
		
		if(!validate(document.examQstnForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="common.message.alert.input.cnts"/>");
			return;
		}

		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		
		
		
		
		var answerCnt = 0;
		if(qstnType == "J") {
			if(jsByteLength(f["answerJ"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>");
				return false;
			}
			if(jsByteLength(f["answerJ"].value) > 1000) {
				alert("<spring:message code="lecture.messgae.exam.question.alert.over.rightanser"/>");
				return false;
			}
			f["rgtAnsr"].value = f["answerJ"].value;
		} else if(qstnType == "K") {
			
			var _content_view1 = $("#summernote1").summernote('code');
			if(_content_view1 == "<p><br></p>" ){_content_view1= "";}
			var _content_view2 = $("#summernote2").summernote('code');
			if(_content_view2 == "<p><br></p>" ){_content_view2= "";}
			var _content_view3 = $("#summernote3").summernote('code');
			if(_content_view3 == "<p><br></p>" ){_content_view3= "";}
			var _content_view4 = $("#summernote4").summernote('code');
			if(_content_view4 == "<p><br></p>" ){_content_view4= "";}
			var _content_view5 = $("#summernote5").summernote('code');
			if(_content_view5 == "<p><br></p>" ){_content_view5= "";}
			f["view1"].value = _content_view1;
			f["view2"].value = _content_view2;
			f["view3"].value = _content_view3;
			f["view4"].value = _content_view4;
			f["view5"].value = _content_view5;

		
			if(jsByteLength(f["view1"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.item3"/>");
				return false;
			}
			if(jsByteLength(f["view1"].value) > 2000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item1"/>");
				return false;
			}
			if(jsByteLength(f["view2"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.item3"/>");
				return false;
			}
			if(jsByteLength(f["view2"].value) > 2000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item2"/>");
				return false;
			}
			
			if(jsByteLength(f["view3"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.item3"/>");
				return false;
			}
			if(jsByteLength(f["view3"].value) > 2000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item3"/>");
				return false;
			}
			if(jsByteLength(f["view4"].value) > 2000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item4"/>");
				return false;
			}
			if(jsByteLength(f["view5"].value) > 2000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item5"/>");
				return false;
			}
			answerCnt = 0;
			if(f.answerK[0].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = '1';
			}
			if(f.answerK[1].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = '2';
			}
			if(f.answerK[2].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = '3';
			}
			if(f.answerK[3].checked == true) {
				if(jsByteLength(f["view4"].value) <= 0) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer3"/>");
					return false;
				}
				answerCnt++;
				f["rgtAnsr"].value = '4';
			}
			if(f.answerK[4].checked == true) {
				if(jsByteLength(f["view5"].value) <= 0) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer4"/>");
					return false;
				}
				answerCnt++;
				f["rgtAnsr"].value = '5';
			}

			if(answerCnt <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.check.rightanser"/>");
				return false;
			}
		} else if(qstnType == "S") {
			answerCnt = 0;
			if(f.answerS[0].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = 'O';
			}
			if(f.answerS[1].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = 'X';
			}
			if(answerCnt <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.check.rightanser"/>");
				return false;
			}
		} else if(qstnType == "D") {
			if(jsByteLength(f["answerD"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>");
				return false;
			}
			if(jsByteLength(f["answerD"].value) > 1000) {
				alert("<spring:message code="lecture.messgae.exam.question.alert.over.rightanser"/>");
				return false;
			}
			f["rgtAnsr"].value = f["answerD"].value;
		} else if(qstnType == "M") {
			var qstns = "";
			var ansrs = "";
			var qstnCnt = 0;
			for(var i=1; i <= 10; i++) {
				if(!isEmpty(f["mexam_"+i].value)) {
					qstnCnt++;
					if(isEmpty(f["mansr_"+i].value)) {
						alert("<spring:message code="course.message.exambank.question.alert.input.answer"/>");
						f["mansr_"+i].focus();
						return;
					}
					qstns = qstns + "|" + f["mexam_"+i].value;
				}
				if(!isEmpty(f["mansr_"+i].value)) {
					ansrs = ansrs + "|" + f["mansr_"+i].value;
				}
			}
			for(var i=11; i <= 20; i++) {
				if(!isEmpty(f["mansr_"+i].value)) {
					ansrs = ansrs + "|" + f["mansr_"+i].value;
				}
			}
			f["view1"].value = qstns;
			f["view2"].value = ansrs;
		}
		if(jsByteLength(f["qstnExpl"].value) > 1000) {
			alert("<spring:message code="lecture.message.exam.question.alert.over.desc"/>");
			return false;
		}
		var _paramImages = summernote.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);

		var formData = $('#examQstnForm').serialize();
		$.ajax({
					cache : false,
					url : "/mng/course/examQbank/" + cmd, // 요기에
					type : 'POST', 
					data : formData, 
					success : function(resultDTO) {
						processCallback(resultDTO);
					}, // success 
					error : function() {
						alert("등록실패!! 다시 시도해 주세요.");
					}
				}); // $.ajax */
	}

	function addQuestion() {
		submitCheck("addQuestion");
	}

	function editQuestion() {
		submitCheck("editQuestion");
	}

	function deleteQuestion() {
		if(confirm("<spring:message code="lecture.message.exam.question.confirm.delete"/>"))
			process("deleteQuestion");
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examQstnForm').attr("action","/mng/course/examQbank/" + cmd);
		if(cmd == "deleteQuestion"){
			$('#examQstnForm').ajaxSubmit(processDelCallback);
		}else{
			$('#examQstnForm').ajaxSubmit(processCallback);
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		var item = resultDTO.returnVO;
		var ctgrCd = item.ctgrCd;
		var parCtgrCd = item.parCtgrCd;
		var searchKey = item.searchKey;
		var qstnType = item.qstnType;
		
		if(resultDTO.result >= 0) {
			// 정상 처리
			<c:if test="${vo.qstnType ne 'I'}">
			document.location.href = cUrl("/mng/course/examQbank/questionMain")+"?ctgrCd=" +parCtgrCd +"&parCtgrCd=" + ctgrCd+"&qstnType=" + qstnType+"&searchKey=" + searchKey;
			</c:if>
			<c:if test="${vo.qstnType eq 'I'}">
			alert('next step...');
			document.location.href = cUrl("/mng/course/examQbank/editQstnFormStep2");
			</c:if>
		} else {
			// 비정상 처리
		}
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processDelCallback(resultDTO) {
		alert(resultDTO.message);
		var item = resultDTO.returnVO;
		var ctgrCd = item.ctgrCd;
		var parCtgrCd = item.parCtgrCd;
		var searchKey = item.searchKey;
		var qstnType = item.qstnType;
		if(resultDTO.result >= 0) {
			document.location.href = cUrl("/mng/course/examQbank/questionMain")+"?ctgrCd=" +ctgrCd +"&parCtgrCd=" + parCtgrCd+"&qstnType=" + qstnType+"&searchKey=" + searchKey;
		} else {
			// 비정상 처리
		}
	}
</script>
