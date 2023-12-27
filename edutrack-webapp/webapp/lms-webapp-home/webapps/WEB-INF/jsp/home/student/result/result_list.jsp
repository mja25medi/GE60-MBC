<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="eduResultVO" value="${vo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:class_head>
<style type="text/css">
.input-sm {font-size:  11px;}
</style>
<mhtml:class_body>
				<mhtml:class_location />

				<div class="row">
					<div class="col-md-4">
						<div class="well">
							<ul class="list-inline">
								<c:if test="${createCourseVO.prgrRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.prgr"/> :</strong>${createCourseVO.prgrRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.attdRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.attd"/> :</strong>${createCourseVO.attdRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.examRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.exam"/> :</strong>${createCourseVO.examRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.semiExamRatio > 0}">
								<li><strong>진행단계평가 :</strong>${createCourseVO.semiExamRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.asmtRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.asmt"/> :</strong>${createCourseVO.asmtRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.forumRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.forum"/> :</strong>${createCourseVO.forumRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.prjtRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.project"/> :</strong>${createCourseVO.prjtRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.joinRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.join"/> :</strong>${createCourseVO.joinRatio}%</li>
								</c:if>
								<c:if test="${createCourseVO.etcRatio > 0}">
								<li><strong><spring:message code="student.title.result.ratio.etc"/> :</strong>${createCourseVO.etcRatio}%</li>
								</c:if>
							</ul>
						</div>
					</div>
					<div class="col-md-8">
						<div class="well">
							<ul style="list-style-type:none;padding-left:10px;">
								<li><spring:message code="student.message.result.msg1"/></li>
								<li><spring:message code="student.message.result.msg2"/></li>
								<li><spring:message code="student.message.result.msg3"/></li>
								<li><spring:message code="student.message.result.msg5"/></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="row" style="margin-bottom:10px;">
					<div class="col-md-12">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#"><spring:message code="student.title.result.manage.score.tab"/></a></li>
							<li><a href="<c:url value="/lec/eduResult/statusResultMain"/>"><spring:message code="student.title.result.manage.status.tab"/></a></li>
						</ul>
					</div>
				</div>

				<form id="eduResultForm" name="eduResultForm" class="form-inline" onsubmit="return false" action="/lec/eduResult/listResultMain">
				<input type="hidden" name="curPage" value="${curPage}" />
				<input type="hidden" name="crsCreCd" value="${CRSCRECD}" />
				<div class="row" style="margin-bottom:5px;">
					<div class="col-md-4">
						<select name="declsNo" id="declsNo" onchange="eduResultForm.submit()" class="form-control input-sm">
							<option value=""><spring:message code="course.title.decls.all"/></option>
							<c:forEach var="decls" items="${creCrsDeclsList}">
							<c:set var="selected" value=""/>
							<c:if test="${vo.declsNo eq decls.declsNo}"><c:set var="selected" value="selected"/></c:if>
							<option value="${decls.declsNo}" ${selected}>${decls.declsNo}</option>
							</c:forEach>
						</select>
						<div class="input-group" style="width:140px;">
							<input type="text" name="userNm" id="userNm" size="12" class="_enterBind form-control input-sm" title="common.title.input.searchvalue" value="${userNm}" placeholder="<spring:message code="user.title.userinfo.name"/>"/>
							<span class="input-group-addon btn_search" style="cursor:pointer"><i class="fa fa-search"></i></span>
						</div>
					</div>
					<div class="col-md-8 text-right" >
						<c:if test="${MSG_SMS eq 'Y' }">
						<a href="javascript:messageForm('SMS');" class="btn btn-info btn-sm"><spring:message code="button.sms"/></a>
						</c:if>
						<c:if test="${MSG_EMAIL eq 'Y' }">
						<a href="javascript:messageForm('EMAIL');" class="btn btn-info btn-sm"><spring:message code="button.email"/></a>
						</c:if>
						<c:if test="${MSG_NOTE eq 'Y' }">
						<a href="javascript:messageForm('MSG');" class="btn btn-info btn-sm"><spring:message code="button.note"/></a>
						</c:if>
						<a href="javascript:viewWorkLog()" class="btn btn-default btn-sm"><spring:message code="log.title.common.view.log"/></a>
	             		<a href="javascript:addAutoResult()" class="btn btn-primary btn-sm"><spring:message code="button.save.score.auto"/></a>
	             		<a href="javascript:addResultAll()" class="btn btn-primary btn-sm"><spring:message code="button.add.all"/></a>
	             		<a href="javascript:scoreEclt()" class="btn btn-primary btn-sm"><spring:message code="button.score.excellent"/></a>
						<select name="listScale" onChange="eduResultForm.submit()" class="form-control input-sm">
							<option value="20" <c:if test="${listScale eq 20}">selected</c:if>>20</option>
							<option value="40" <c:if test="${listScale eq 40}">selected</c:if>>40</option>
							<option value="60" <c:if test="${listScale eq 60}">selected</c:if>>60</option>
							<option value="80" <c:if test="${listScale eq 80}">selected</c:if>>80</option>
							<option value="100" <c:if test="${listScale eq 100}">selected</c:if>>100</option>
							<option value="200" <c:if test="${listScale eq 200}">selected</c:if>>200</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="overflow-x: scroll">
						<c:set var="colspan" value="7"/>
						<table class="table table-bordered wordbreak" style="width: 120%; max-width: 120%">
							<caption class="sr-only"><spring:message code="student.title.student.list"/></caption>
							<colgroup>
								<col width="4%" />
								<col width="6%" />
								<col width="AUTO" />
								<col width="AUTO" />
								<col width="6%" />
								<c:if test="${createCourseVO.prgrRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.attdRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.examRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.semiExamRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.asmtRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.forumRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.prjtRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.joinRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<c:if test="${createCourseVO.etcRatio > 0}">
								<col width="7%" /><c:set var="colspan" value="${colspan + 1}"/>
								</c:if>
								<col width="9%" />
								<col width="8%" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="text-center"><input type="checkbox" name="chkAll" id="chkAll" value="N" /></th>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
									<th scope="col"><spring:message code="student.title.result.score.excellent"/></th>
									<c:if test="${createCourseVO.prgrRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.prgr"/><br />
										<span class="red normal">${createCourseVO.prgrRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.attdRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.attd"/><br />
										<span class="red normal">${createCourseVO.attdRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.examRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.exam"/><br />
										<span class="red normal">${createCourseVO.examRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.semiExamRatio > 0}">
									<th scope="col">진행단계평가<br />
										<span class="red normal">${createCourseVO.semiExamRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.asmtRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.asmt"/><br />
										<span class="red normal">${createCourseVO.asmtRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.forumRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.forum"/><br />
										<span class="red normal">${createCourseVO.forumRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.prjtRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.project"/><br />
										<span class="red normal">${createCourseVO.prjtRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.joinRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.join"/><br />
										<span class="red normal">${createCourseVO.joinRatio}%</span></th>
									</c:if>
									<c:if test="${createCourseVO.etcRatio > 0}">
									<th scope="col"><spring:message code="student.title.result.ratio.etc"/><br />
										<span class="red normal">${createCourseVO.etcRatio}%</span></th>
									</c:if>
									<th scope="col"><spring:message code="student.title.result.totalscore"/><br/>(100%)</th>
									<th scope="col" class="rnone"><spring:message code="common.title.add"/></th>
								</tr>
							</thead>
							<tbody>
							<c:set var="lineCnt" value="0"/>
							<c:forEach items="${studentList}" var="item"  varStatus="status">
								<c:set var="linebg" value="background-color:#FEDFDF;"/>
								<c:if test="${item.resultYn eq 'Y'}"><c:set var="linebg" value="background-color:#DFF6FE;"/></c:if>
								<tr>
									<td class="text-center">
										<input type="checkbox" name="sel" value="${item.userNo}" />
										<input type="hidden" name="stdNo" value="${item.stdNo}" />
									</td>
									<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
									<td>${item.userNm}</td>
									<td>${item.userId}</td>
									<td class="text-center">
										<c:if test="${item.totScore > 0 }">
										<input type="checkbox" name="scoreEcltYn" value="${item.stdNo}" <c:if test="${item.scoreEcltYn eq 'Y'}">checked</c:if>/>
										</c:if>
										<c:if test="${item.totScore == 0 || empty item.totScore}">
										<input type="hidden" name="scoreEcltYn" value="${item.scoreEcltYn}" />
										</c:if>
									</td>
									<c:if test="${createCourseVO.prgrRatio > 0}">
									<td class="text-center"><input type="text" name="prgrScore" style="width:46px;${linebg};text-align:right;" value="${item.prgrScore}" maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.prgrRatio})" class="form-control input-sm"/></td>
									</c:if>
									<c:if test="${createCourseVO.attdRatio > 0}">
									<td class="text-center"><input type="text" name="attdScore" style="width:46px;${linebg};text-align:right;" value="${item.attdScore}"  maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.attdRatio})" class="form-control input-sm"/></td>
									</c:if>
									<c:if test="${createCourseVO.examRatio > 0}">
									<td class="text-center"><input type="text" name="examScore" style="width:46px;${linebg};text-align:right;" value="${item.examScore}" maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.examRatio})" class="form-control input-sm"/></td>
									</c:if>
									<c:if test="${createCourseVO.semiExamRatio > 0}">
									<td class="text-center"><input type="text" name="examScore" style="width:46px;${linebg};text-align:right;" value="${item.semiExamScore}" maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.semiExamRatio})" class="form-control input-sm"/></td>
									</c:if>
									<c:if test="${createCourseVO.asmtRatio > 0}">
									<td class="text-center"><input type="text" name="asmtScore" style="width:46px;${linebg};text-align:right;" value="${item.asmtScore}" maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.asmtRatio})" class="form-control input-sm"/></td>
									</c:if>
									<c:if test="${createCourseVO.forumRatio > 0}">
									<td class="text-center"><input type="text" name="forumScore" style="width:46px;${linebg};text-align:right;" value="${item.forumScore}" maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.forumRatio})" class="form-control input-sm"/></td>
									</c:if>
									<c:if test="${createCourseVO.joinRatio > 0}">
									<td class="text-center"><input type="text" name="joinScore" style="width:46px;${linebg};text-align:right;" value="${item.joinScore}"  maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.joinRatio})" class="form-control input-sm"/></td>
									</c:if>
									<c:if test="${createCourseVO.etcRatio > 0}">
									<td class="text-center"><input type="text" name="etcScore" style="width:46px;${linebg};text-align:right;" value="${item.etcScore}"  maxlength="6" onblur="checkTotalScore(${lineCnt})" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${createCourseVO.etcRatio})" class="form-control input-sm"/></td>
									</c:if>
									<td class="text-center"><input type="text" name="totScore" style="width:46px;${linebg};text-align:right;" value="${item.totScore}" readonly="readonly" class="form-control input-sm"/></td>
									<td class="text-right">
										<a href="javascript:addResult(${lineCnt})" class="btn btn-info btn-sm"><spring:message code="button.add"/></a>
										<c:if test="${createCourseVO.prgrRatio eq 0}"><input type="hidden" name="prgrScore" value="0"/></c:if>
										<c:if test="${createCourseVO.attdRatio eq 0}"><input type="hidden" name="attdScore" value="0"/></c:if>
										<c:if test="${createCourseVO.examRatio eq 0}"><input type="hidden" name="examScore" value="0"/></c:if>
										<c:if test="${createCourseVO.semiExamRatio eq 0}"><input type="hidden" name="semiExamScore" value="0"/></c:if>
										<c:if test="${createCourseVO.asmtRatio eq 0}"><input type="hidden" name="asmtScore" value="0"/></c:if>
										<c:if test="${createCourseVO.forumRatio eq 0}"><input type="hidden" name="forumScore" value="0"/></c:if>
										<c:if test="${createCourseVO.joinRatio eq 0}"><input type="hidden" name="joinScore" value="0"/></c:if>
										<c:if test="${createCourseVO.etcRatio eq 0}"><input type="hidden" name="etcScore" value="0"/></c:if>
									</td>
								</tr>
								<c:set var="lineCnt" value="${lineCnt +1}" />
							</c:forEach>
							<c:if test="${empty studentList}">
								<tr>
									<td colspan="${colspan}"><spring:message code="student.message.student.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
					</div>
				</div>
				</form>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="listResult"/>
					</div>
				</div>

<script type="text/javascript">
	var Item = new Object();
	var ItemDTO = new Object();
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		ItemDTO.crsCreCd = '${vo.crsCreCd}';

        //전체선택 /전체취소
		$('#chkAll').click(function(){
		    var state=$('input[name=chkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#eduResultForm input[name='sel']").prop({checked:true});
		    }else{
		    	$(document).find("#eduResultForm input[name='sel']").prop({checked:false});
		    }
		});

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				$("#eduResultForm").submit();
			}
		});

		$(".btn_search").bind("click", function(event) {
			$("#eduResultForm").submit();
		});

	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function chkNumber(param,maxVlaue){
		var score =  param.value;
		var scoreAr;
		if(score != ""){
			if(score.indexOf(".") != -1){
				scoreAr = score.split(".");
				if(scoreAr[1].length > 1){
					param.value = parseFloat(score).toFixed(1);
				} else {
					param.value = parseFloat(score);
				}
			} else {
				param.value = Number(score);
			}
		}
		if(param.value > maxVlaue){
			param.value = maxVlaue;
		}
	}

	function checkTotalScore(no) {
		var ObjPrgrScore = document.getElementsByName("prgrScore");
		var ObjAttdScore = document.getElementsByName("attdScore");
		var ObjExamScore = document.getElementsByName("examScore");
		var ObjSemiExamScore = document.getElementsByName("semiExamScore");
		var ObjAsmtScore = document.getElementsByName("asmtScore");
		var ObjForumScore = document.getElementsByName("forumScore");
		var ObjJoinScore = document.getElementsByName("joinScore");
		var ObjEtcScore = document.getElementsByName("etcScore");
		var ObjTotScore = document.getElementsByName("totScore");

		chkNumber(ObjPrgrScore[no],${createCourseVO.prgrRatio});
		chkNumber(ObjAttdScore[no],${createCourseVO.attdRatio});
		chkNumber(ObjExamScore[no],${createCourseVO.examRatio});
		chkNumber(ObjSemiExamScore[no],${createCourseVO.semiExamRatio});
		chkNumber(ObjAsmtScore[no],${createCourseVO.asmtRatio});
		chkNumber(ObjForumScore[no],${createCourseVO.forumRatio});
		chkNumber(ObjJoinScore[no],${createCourseVO.joinRatio});
		chkNumber(ObjEtcScore[no],${createCourseVO.etcRatio});

		/*
		var oldTotalScore = parseInt(ObjTotScore[no].value,10);
		var newTotalScore = parseInt(ObjPrgrScore[no].value,10) +
							parseInt(ObjAttdScore[no].value,10) +
		                    parseInt(ObjExamScore[no].value,10) +
		                    parseInt(ObjAsmtScore[no].value,10) +
		                    parseInt(ObjForumScore[no].value,10) +
		                    parseInt(ObjJoinScore[no].value,10) +
		                    parseInt(ObjEtcScore[no].value,10) ;
 */
		if(ObjPrgrScore[no].value == "")	ObjPrgrScore[no].value = "0.0";
		if(ObjAttdScore[no].value == "")	ObjAttdScore[no].value = "0.0";
		if(ObjExamScore[no].value == "")	ObjExamScore[no].value = "0.0";
		if(ObjSemiExamScore[no].value == "")	ObjSemiExamScore[no].value = "0.0";
		if(ObjAsmtScore[no].value == "")	ObjAsmtScore[no].value = "0.0";
		if(ObjForumScore[no].value == "")	ObjForumScore[no].value = "0.0";
		if(ObjJoinScore[no].value == "")	ObjJoinScore[no].value = "0.0";
		if(ObjEtcScore[no].value == "")	ObjEtcScore[no].value = "0.0";

		var oldTotalScore = parseFloat(parseFloat(ObjTotScore[no].value).toFixed(2));
		/*
		var newTotalScore =parseFloat(parseFloat(ObjPrgrScore[no].value).toFixed(2)) +
							parseFloat(parseFloat(ObjAttdScore[no].value).toFixed(2)) +
							parseFloat(parseFloat(ObjExamScore[no].value).toFixed(2)) +
							parseFloat(parseFloat(ObjAsmtScore[no].value).toFixed(2)) +
							parseFloat(parseFloat(ObjForumScore[no].value).toFixed(2)) +
							parseFloat(parseFloat(ObjJoinScore[no].value).toFixed(2)) +
							parseFloat(parseFloat(ObjEtcScore[no].value).toFixed(2)) ;
		 */
		 var newTotalScore = parseFloat(ObjPrgrScore[no].value) +
										parseFloat(ObjAttdScore[no].value) +
										parseFloat(ObjExamScore[no].value) +
										parseFloat(ObjSemiExamScore[no].value) +
										parseFloat(ObjAsmtScore[no].value) +
										parseFloat(ObjForumScore[no].value) +
										parseFloat(ObjJoinScore[no].value) +
										parseFloat(ObjEtcScore[no].value);


		//-- 성적 변화가 있으면 스타일 색상 변경
	    if(oldTotalScore != newTotalScore) {
			ObjPrgrScore[no].style.background = "#FEDFDF";
			ObjAttdScore[no].style.background = "#FEDFDF";
			ObjExamScore[no].style.background = "#FEDFDF";
			ObjSemiExamScore[no].style.background = "#FEDFDF";
			ObjAsmtScore[no].style.background = "#FEDFDF";
			ObjForumScore[no].style.background = "#FEDFDF";
			ObjJoinScore[no].style.background = "#FEDFDF";
			ObjEtcScore[no].style.background = "#FEDFDF";
			ObjTotScore[no].style.background = "#FEDFDF";
			ObjTotScore[no].value = newTotalScore.toFixed(1);
	    }
	}

	function addResult(no) {
		Item.no = no;
		var ObjStdNo = document.getElementsByName("stdNo");
		var ObjPrgrScore = document.getElementsByName("prgrScore");
		var ObjAttdScore = document.getElementsByName("attdScore");
		var ObjExamScore = document.getElementsByName("examScore");
		var ObjSemiExamScore = document.getElementsByName("semiExamScore");
		var ObjAsmtScore = document.getElementsByName("asmtScore");
		var ObjForumScore = document.getElementsByName("forumScore");
		var ObjJoinScore = document.getElementsByName("joinScore");
		var ObjEtcScore = document.getElementsByName("etcScore");
		var ObjTotScore = document.getElementsByName("totScore");

		$.getJSON( cUrl("/lec/eduResult/addResult"),		// url
				{
				  "crsCreCd" 	: "${CRSCRECD}",
				  "stdNo" 		: ObjStdNo[no].value,
				  "prgrScore" 	: ObjPrgrScore[no].value,
				  "attdScore" 	: ObjAttdScore[no].value,
				  "examScore" 	: ObjExamScore[no].value,
				  "semiExamScore" 	: ObjSemiExamScore[no].value,
				  "asmtScore" 	: ObjAsmtScore[no].value,
				  "forumScore" : ObjForumScore[no].value,
				  "joinScore" 	: ObjJoinScore[no].value,
				  "etcScore" 	: ObjEtcScore[no].value,
				  "totScore" 	: ObjTotScore[no].value
				},			// params
				processCallback				// callback function
			);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			var no = Item.no;
			// 정상 처리
			var ObjStdNo = document.getElementsByName("stdNo");
			var ObjPrgrScore = document.getElementsByName("prgrScore");
			var ObjAttdScore = document.getElementsByName("attdScore");
			var ObjExamScore = document.getElementsByName("examScore");
			var ObjSemiExamScore = document.getElementsByName("semiExamScore");
			var ObjAsmtScore = document.getElementsByName("asmtScore");
			var ObjForumScore = document.getElementsByName("forumScore");
			var ObjJoinScore = document.getElementsByName("joinScore");
			var ObjEtcScore = document.getElementsByName("etcScore");
			var ObjTotScore = document.getElementsByName("totScore");

			ObjPrgrScore[no].style.background = "#DFF6FE";
			ObjAttdScore[no].style.background = "#DFF6FE";
			ObjExamScore[no].style.background = "#DFF6FE";
			ObjSemiExamScore[no].style.background = "#DFF6FE";
			ObjAsmtScore[no].style.background = "#DFF6FE";
			ObjForumScore[no].style.background = "#DFF6FE";
			ObjJoinScore[no].style.background = "#DFF6FE";
			ObjEtcScore[no].style.background = "#DFF6FE";
			ObjTotScore[no].style.background = "#DFF6FE";

			Item.no = "";

			// 정상 처리
			document.location.reload();
		} else {
			// 비정상 처리
		}
	}

	function addResultAll() {
		if(arrayToString('sel') == '') {
			alert("<spring:message code="student.message.result.alert.nouser"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/eduResult/scoreAllFormPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 250);
		parent.modalBox.setTitle("<spring:message code="button.add.all"/>");
		parent.modalBox.show();
		/*
		if(!confirm('<spring:message code="student.message.result.confirm.addall"/>')){
			return;
		}
		$.getJSON( cUrl("/lec/eduResult/"),		// url
				{ "cmd" : "addResultAll",
				  "eduResultVO.crsCreCd" : "${CRSCRECD}",
				  "strStdNo" : arrayToString('stdNo'),
				  "strPrgrScore" : arrayToString('prgrScore'),
				  "strAttdScore" : arrayToString('attdScore'),
				  "strExamScore" : arrayToString('examScore'),
				  "strAsmtScore" : arrayToString('asmtScore'),
				  "strForumScore" : arrayToString('forumScore'),
				  "strJoinScore" : arrayToString('joinScore'),
				  "strEtcScore" : arrayToString('etcScore'),
				  "strTotScore" : arrayToString('totScore')
				},			// params
				processCallbackAll				// callback function
			);
		*/
	}

	function addAutoResult() {
		if(!confirm('<spring:message code="student.message.result.alert.scoreauto"/>')) {
			return;
		}
		$.getJSON( cUrl("/lec/eduResult/addAutoResult"),		// url
			{ 
			  "crsCreCd" : "${CRSCRECD}"
			},			// params
			processCallbackAll				// callback function
		);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallbackAuto(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.reload();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallbackAll(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			var ObjStdNo = document.getElementsByName("stdNo");
			var ObjPrgrScore = document.getElementsByName("prgrScore");
			var ObjAttdScore = document.getElementsByName("attdScore");
			var ObjExamScore = document.getElementsByName("examScore");
			var ObjSemiExamScore = document.getElementsByName("semiExamScore");
			var ObjAsmtScore = document.getElementsByName("asmtScore");
			var ObjForumScore = document.getElementsByName("forumScore");
			var ObjJoinScore = document.getElementsByName("joinScore");
			var ObjEtcScore = document.getElementsByName("etcScore");
			var ObjTotScore = document.getElementsByName("totScore");

			for(var i=0; i < ObjStdNo.length; i++) {
				ObjPrgrScore[i].style.background = "#DFF6FE";
				ObjAttdScore[i].style.background = "#DFF6FE";
				ObjExamScore[i].style.background = "#DFF6FE";
				ObjSemiExamScore[i].style.background = "#DFF6FE";
				ObjAsmtScore[i].style.background = "#DFF6FE";
				ObjForumScore[i].style.background = "#DFF6FE";
				ObjJoinScore[i].style.background = "#DFF6FE";
				ObjEtcScore[i].style.background = "#DFF6FE";
				ObjTotScore[i].style.background = "#DFF6FE";
			}

			// 정상 처리
			document.location.reload();
		} else {
			// 비정상 처리
		}
	}

	function listResult(page) {
		$('#eduResultForm')
		.find('input[name=curPage]').val(page).end()
		.submit();
	}

	function arrayToString(objName) {
		obj = document.getElementsByName(objName);
		if(obj == null) {
			return "";
		}
		var retStr = "";
		for(var i=0; i < obj.length; i++) {
			retStr = retStr + "|" + obj[i].value;
		}
		retStr = retStr.substring(1);
		return retStr;
	}

	function excelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		$("#eduResultForm").attr('target', '_m_download_iframe');
		$("#eduResultForm").attr("action", "/lec/eduResult/listExcelDownload");
		$("#eduResultForm").submit();
		$("#eduResultForm").attr("action", "/lec/eduResult/listResultMain");
		$("#eduResultForm").removeAttr('target');
	}

	/**
	* 메세지 전송 popup
	*/
	function messageForm(msgDivCd) {
		userList = $("#eduResultForm input[name='sel']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/message/messageWritePop"/>"
			+ "?msgDivCd="+msgDivCd+"&amp;userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}

	function scoreEclt() {
		var targetList = $("#eduResultForm input[name='scoreEcltYn']:checked").stringValues();
		var studentList = $("#eduResultForm input[name='scoreEcltYn']").stringValues();

		if(targetList.length == 0){
			alert("<spring:message code="lecture.message.score.excellent.add.noselect"/>");
			return;
		}

		$.getJSON( cUrl("/lec/eduResult/addEclt"),		// url
				{ 
				  "studentList" : studentList,
				  "targetList" : targetList
				},			// params
				function (resultVO) {
					alert(resultVO.message);
					if(resultVO.result >= 0) {
						// 정상 처리
						document.location.reload();
					} else {
						// 비정상 처리
					}
				}
			);
	}


	function addResultAllSubmit(){
		$.getJSON( cUrl("/lec/eduResult/addResultAll"),		// url
				{ 
				  "crsCreCd" : ItemDTO.crsCreCd,
				  //"strStdNo" : arrayToString('sel'),
				  "strStdNo" : arrayToString('stdNo'),
				  "strPrgrScore" : arrayToString('prgrScore'),
				  "strAttdScore" : arrayToString('attdScore'),
				  "strExamScore" : arrayToString('examScore'),
				  "strSemiExamScore" : arrayToString('semiExamScore'),
				  "strAsmtScore" : arrayToString('asmtScore'),
				  "strForumScore" : arrayToString('forumScore'),
				  "strJoinScore" : arrayToString('joinScore'),
				  "strEtcScore" : arrayToString('etcScore'),
				  "strTotScore" : arrayToString('totScore')
				},			// params
				processCallback				// callback function
			);
	}

	function scoreAllSet(prgrScore, attdScore, examScore, asmtScore, forumScore, joinScore, etcScore,totScore){
		var Objsel = document.getElementsByName("sel");
		var ObjPrgrScore = document.getElementsByName("prgrScore");
		var ObjAttdScore = document.getElementsByName("attdScore");
		var ObjExamScore = document.getElementsByName("examScore");
		var ObjSemiExamScore = document.getElementsByName("semiExamScore");
		var ObjAsmtScore = document.getElementsByName("asmtScore");
		var ObjForumScore = document.getElementsByName("forumScore");
		var ObjJoinScore = document.getElementsByName("joinScore");
		var ObjEtcScore = document.getElementsByName("etcScore");
		var ObjTotScore = document.getElementsByName("totScore");

		for(var i=0; i < Objsel.length; i++) {
			if(prgrScore != "")	ObjPrgrScore[i].value = prgrScore;
			if(attdScore != "")	ObjAttdScore[i].value = attdScore;
			if(examScore != "")	ObjExamScore[i].value = examScore;
			if(semiExamScore != "")	ObjSemiExamScore[i].value = semiExamScore;
			if(asmtScore != "")	ObjAsmtScore[i].value = asmtScore;
			if(forumScore != "")	ObjForumScore[i].value = forumScore;
			if(joinScore != "")	ObjJoinScore[i].value = joinScore;
			if(etcScore != "")	ObjEtcScore[i].value = etcScore;
			totScore = parseFloat(ObjPrgrScore[i].value) +
							parseFloat(ObjAttdScore[i].value) +
							parseFloat(ObjExamScore[i].value) +
							parseFloat(ObjSemiExamScore[i].value) +
							parseFloat(ObjAsmtScore[i].value) +
							parseFloat(ObjForumScore[i].value) +
							parseFloat(ObjJoinScore[i].value) +
							parseFloat(ObjEtcScore[i].value);

			ObjTotScore[i].value =totScore.toFixed(1);
		}
		addResultAllSubmit();
		parent.modalBox.close();
	}

	//-- 로그
	function viewWorkLog() {
		var addContent  = "<iframe id='workLogFrame' name='workLogFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/std/eduRsltWorkLog/main"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle('view work log');
		parent.modalBox.show();
	}
</script>
</mhtml:class_body>
</mhtml:class_html>
