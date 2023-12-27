<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="examQuestionVO" value="${vo}" />

	<form id="examForm" name="examForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="examSn" value="${vo.examSn}" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn}" />
	<input type="hidden" name="rgtAnsr" value="${vo.rgtAnsr}" />
	<input type="hidden" name="subNo" value="${vo.subNo}" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" value="Y"/>
	<input type="hidden" name="qstnType" id="qstnType" value="${vo.qstnType}"/>
	<div class="modal_cont">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2"><spring:message code="lecture.title.exam.question.title"/><i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" isNull="N" name="title" value="${vo.title}" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="numInput" class="form-label col-sm-2"><spring:message code="lecture.title.exam.question.no"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control w20" type="text" maxlength="9" isNull="N" lenCheck="9" name="qstnNo"id="qstnNo"  maxlength="3" onfocus="this.select()" value="${vo.qstnNo}" > 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="editor">
											<div id="summernote" class="wordbreak">${examQuestionVO.qstnCts}</div>
											<textarea name="qstnCts" id="contentTextArea"  class="sr-only">${vo.qstnCts}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            
                                 <c:set var="checked1" value=""/><c:set var="checked2" value=""/><c:set var="checked3" value=""/><c:set var="checked4" value=""/><c:set var="checked5" value=""/>
								<c:if test="${examQuestionVO.rgtAnsr eq '1'}"><c:set var="checked1" value="checked"/></c:if>
								<c:if test="${examQuestionVO.rgtAnsr eq '2'}"><c:set var="checked2" value="checked"/></c:if>
								<c:if test="${examQuestionVO.rgtAnsr eq '3'}"><c:set var="checked3" value="checked"/></c:if>
								<c:if test="${examQuestionVO.rgtAnsr eq '4'}"><c:set var="checked4" value="checked"/></c:if>
								<c:if test="${examQuestionVO.rgtAnsr eq '5'}"><c:set var="checked5" value="checked"/></c:if>
                            
                            <li class="qstnTypeK qstnItem" style="display:none">
                                <div class="row">
                                    <label for="examInput1" class="form-label col-sm-2">보기1</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea name="empl1" style="height:30px;" class="form-control">${vo.empl1}</textarea>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                              <li class="qstnTypeK qstnItem" style="display:none">
                                <div class="row">
                                    <label for="examInput2" class="form-label col-sm-2">보기2</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                          <textarea name="empl2" style="height:30px;" class="form-control">${vo.empl2}</textarea>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                             <li class="qstnTypeK qstnItem" style="display:none">
                                <div class="row">
                                    <label for="examInput3" class="form-label col-sm-2">보기3</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                             <textarea name="empl3" style="height:30px;" class="form-control">${vo.empl3}</textarea>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                             <li class="qstnTypeK qstnItem" style="display:none">
                                <div class="row">
                                    <label for="examInput4" class="form-label col-sm-2">보기4</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea name="empl4" style="height:30px;" class="form-control">${vo.empl4}</textarea>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                             <li class="qstnTypeK qstnItem" style="display:none">
                                <div class="row">
                                    <label for="examInput5" class="form-label col-sm-2">보기5</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                           <textarea name="empl5" style="height:30px;" class="form-control">${vo.empl5}</textarea>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                             <li class="qstnTypeK qstnItem" style="display:none">
                                <div class="row">
                                    <label for="exam1" class="form-label col-sm-2">정답<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row m_flex_column">
                                            <span class="custom-input"><input type="radio" id="answer1" name="answerK" value="1" ${checked1}><label for="answer1">보기1</label></span>  
                                            <span class="custom-input"><input type="radio" id="answer2" name="answerK" value="2" ${checked2}><label for="answer2">보기2</label></span>  
                                            <span class="custom-input"><input type="radio" id="answer3" name="answerK" value="3" ${checked3}><label for="answer3">보기3</label></span>  
                                            <span class="custom-input"><input type="radio" id="answer4" name="answerK" value="4" ${checked4}><label for="answer4">보기4</label></span>  
                                            <span class="custom-input"><input type="radio" id="answer5" name="answerK" value="5" ${checked5}><label for="answer5">보기5</label></span>  
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li class="qstnTypeD qstnItem" style="display:none">
						          <div class="row">
						              <label for="correctInput5" class="form-label col-sm-2">정답<i class="icon_star" aria-hidden="true"></i></label>
						              <div class="col-sm-10">
						                  <div class="form-row">
						                      <input type="text" name="answerD"  class="form-control input-sm" value="${examQuestionVO.rgtAnsr}" />
						                  </div>             
						                  <ul class="message_box mt10">
						                     <li>단답형의 중복답안 처리에 대한 답안 구분은 파이프('|' Shift+\)로 해 주세요.</li>
						                  </ul>
						              </div>
						          </div>
						      </li>
                              	<c:set var="checkedO" value=""/><c:set var="checkedX" value=""/>
								<c:if test="${examQuestionVO.rgtAnsr eq 'O'}"><c:set var="checkedO" value="checked"/></c:if>
								<c:if test="${examQuestionVO.rgtAnsr eq 'X'}"><c:set var="checkedX" value="checked"/></c:if>
                            <li class="qstnTypeS qstnItem" style="display:none">
                                <div class="row">
                                    <label for="exam21" class="form-label col-sm-2"><spring:message code="lecture.title.exam.question.rightanswer"/><i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <span class="custom-input">
                                                <input type="radio" id="answerO" name="answerS" value="O"  ${checkedO}/>
                                                <label for="answerO">O</label>
                                            </span>
                                            <span class="custom-input">
                                            <input type="radio" />
                                                <input type="radio" id="answerX" name="answerS" value="X"  ${checkedX}/>
                                                <label for="answerX">X</label>
                                            </span>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li class="qstnTypeJ qstnItem" style="display:none">
                                <div class="row">
                                    <label for="correctInput" class="form-label col-sm-2">정답<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea name="answerJ" style="height:50px;" class="form-control">${examQuestionVO.rgtAnsr}</textarea>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="commentInput" class="form-label col-sm-2"><spring:message code="lecture.title.exam.question.desc"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea name="qstnExpl" style="height:40;"class="form-control">${vo.qstnExpl}</textarea>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal_btns">
                    	<c:if test="${gubun eq 'A'}">
                    	  <button type="button" class="btn type2" onclick="addQuestion()"><spring:message code="button.add"/></button>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<button type="button" class="btn type4" onclick="editQuestion()"><spring:message code="button.add"/></button>
				<button type="button" class="btn type3" onclick="delQuestion()"><spring:message code="button.delete"/></button>
				</c:if>
				 <button type="button" class="btn " onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
                </div>
	
	</form>
<script type="text/javascript">
	var summernote;
	var enableQstnNo;

	// 페이지 초기화
	$(document).ready(function() {
		//$("body").css("padding-top","0px").css("min-height","0px");
		enableQstnNo = Number(parent.document.getElementById("lastQstnNo").value) +1;
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"EXAM_QSTN",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"330px",
			"attachments"		:	$.parseJSON('${examQuestionVO.attachImagesJson}')
		});

		//부모창 새로고침
		if("${refreshYn}" == "Y"){		//등록일 경우
			parent.location.reload();
			parent.modalBoxClose();
		}else if("${refreshYn}" == "D"){		//삭제일 경우
			parent.location.reload();
			parent.modalBoxClose();
		}
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		<c:if test="${gubun eq 'A'}">
		$("#qstnNo").val(enableQstnNo);
		</c:if>
		changeType();
	});

	function changeType() {
		var qstnType = $("#qstnType").val();
		$(".qstnItem").hide();
		$(".qstnType"+qstnType).show();
	}

	function submitCheck(cmd) {
		$('#examForm').attr("action","/lec/exam/"+ cmd);
		if(!validate(document.examForm)) return false;
		if(Number($("#qstnNo").val()) > enableQstnNo ){
			alert('<spring:message code="lecture.message.exam.question.alert.input.enableqstnno" arguments="'+enableQstnNo+'"/>');
			return;
		}
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert('<spring:message code="common.message.alert.input.cnts"/>');
			return;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);


		var qstnType = $("#qstnType").val();
		var f = document.examForm;
		var answerCnt = 0;

		if(parseInt(f["qstnNo"].value,10) <= 0) {
			alert('<spring:message code="lecture.message.exam.question.alert.input.no"/>');
			return;
		}
		// 문제 제목 확인
		if(isEmpty(f["title"].value)){
			alert('<spring:message code="lecture.message.exam.question.alert.input.title"/>');
			return;
		}

		//-- 문제 유형에 따른 벨리데이션
		if(qstnType == "J") {
			if(isEmpty(f["answerJ"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>');
				return;
			}
			f["rgtAnsr"].value = f["answerJ"].value;
		} else if(qstnType == "K") {
			if(isEmpty(f["empl1"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item3"/>');
				return;
			}
			if(isEmpty(f["empl2"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item3"/>');
				return;
			}
			if(isEmpty(f["empl3"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item3"/>');
				return;
			}
			/*
			if(isEmpty(f["empl4"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item4"/>');
				return false;
			}
			 */
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
				if(isEmpty(f["empl4"].value)) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer3"/>");
					return;
				}
				answerCnt++;
				f["rgtAnsr"].value = '4';
			}
			if(f.answerK[4].checked == true) {
				if(isEmpty(f["empl4"].value)) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer3"/>");
					return;
				}
				if(isEmpty(f["empl5"].value)) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer4"/>");
					return;
				}
				answerCnt++;
				f["rgtAnsr"].value = '5';
			}
			if(answerCnt <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.check.rightanser"/>");
				return;
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
				return;
			}
		} else if(qstnType == "D") {
			if(isEmpty(f["answerD"].value)) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>");
				return;
			}
			f["rgtAnsr"].value = f["answerD"].value;
		}
		/*
		if(isEmpty(f["examQuestionVO.qstnExpl"].value)) {
			alert("<spring:message code="lecture.message.exam.question.alert.input.desc"/>");
			return false;
		}
 		*/
		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);

		$('#examForm').ajaxSubmit(processCallback);
	}


	/**
	 * 시험 문제 등록
	 */
	function addQuestion() {
		submitCheck('addQuestion');
	}

	/**
	* 시험 문제 수정
	*/
	function editQuestion(){
		submitCheck('editQuestion');
	}

	/**
	 * 시험 문제 삭제
	 */
	function delQuestion() {
		if(confirm('<spring:message code="lecture.message.exam.question.confirm.delete"/>')) {
			$('#examForm').attr("action","/lec/exam/deleteQuestion");
			$('#examForm').ajaxSubmit(processCallback);
		} else {
			return;
		}
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.location.reload();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
</script>
