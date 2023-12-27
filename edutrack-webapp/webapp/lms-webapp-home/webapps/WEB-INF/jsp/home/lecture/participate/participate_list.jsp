<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="participateListVO" value="${participateListVO}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="participateVO" value="${vo}"/>

				<form id="participateForm" name="participateForm" class="form-inline" >
				<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
				<input type="hidden" name="stdNo" id="stdNo" value=""/>
				<input type="hidden" name="joinScore" id="joinScore" value="0"/>
				<input type="hidden" name="strStdNo" />
				<input type="hidden" name="strJoinScore" />
				<input type="hidden" name="strRegNo"/>
				<div class="row" style="margin-bottom:5px;">
					<div class="col-md-6">
						<select name="declsNo" id="declsNo" onchange="list(1)" class="form-control input-sm">
							<option value=""><spring:message code="course.title.decls.all"/></option>
							<c:forEach var="decls" items="${creCrsDeclsList}">
							<c:set var="selected" value=""/>
							<c:if test="${participateVO.declsNo eq decls.declsNo}"><c:set var="selected" value="selected"/></c:if>
							<option value="${decls.declsNo}" ${selected}>${decls.declsNo}</option>
							</c:forEach>
						</select>
						<div class="input-group" style="width:160px;">
							<input type="text" name="searchValue" id="searchValue" value="${participateVO.searchValue }" class="_enterBind form-control input-sm" placeholder="<spring:message code="user.title.userinfo.name"/>"/>
							<span class="input-group-addon btn_search" styl="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-6 text-right">
						<c:if test="${MSG_SMS eq 'Y' }">
						<a href="javascript:messageForm('SMS');" class="btn btn-info btn-sm"><spring:message code="button.sms"/></a>
						</c:if>
						<c:if test="${MSG_EMAIL eq 'Y' }">
						<a href="javascript:messageForm('EMAIL');" class="btn btn-info btn-sm"><spring:message code="button.email"/></a>
						</c:if>
						<c:if test="${MSG_NOTE eq 'Y' }">
						<a href="javascript:messageForm('MSG');" class="btn btn-info btn-sm"><spring:message code="button.note"/></a>
						</c:if>
						<a href="javascript:addScoreAll();" class="btn btn-primary btn-sm"><spring:message code="button.add.score"/></a>

						<select name="listScale" title="" class="form-control input-sm" onchange="participateForm.action('/lec/participate/lsit').submit()">
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
					<div class="col-md-12">
						<table class="table table-bordered wordbreak" style="font-size: 13px;">
							<caption class="sr-only"><spring:message code="lecture.title.join.status"/></caption>
							<colgroup>
								<col width="4%">
								<col width="5%">
								<col width="10%">
								<col width="11%">
								<col width="8%">
								<col width="auto">
								<col width="auto">
								<col width="8%">
								<col width="auto">
								<col width="10%">
								<col width="15%">
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="text-center"><input type="checkbox" name="chkAll" id="chkAll" value="N" /></th>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
									<th scope="col"><spring:message code="log.title.tchact.connect"/></th>
									<th scope="col"><spring:message code="lecture.title.join.qnacnt"/></th>
									<th scope="col"><spring:message code="lecture.title.join.forumcnt"/></th>
									<th scope="col"><spring:message code="lecture.title.join.pdscnt"/></th>
									<th scope="col"><spring:message code="lecture.title.join.totalcnt"/></th>
									<th scope="col"><spring:message code="lecture.title.join.score"/></th>
									<th scope="col"><spring:message code="lecture.title.forum.rate"/></th>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${empty participateListVO}">
									<tr><td colspan="11" align="center"><font color="blud"><spring:message code="lecture.message.join.nodata"/></font></td></tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${participateListVO}" var="item" varStatus="status">
								<tr>
									<td class="text-center">
										<input type="checkbox" name="sel" value="${item.userNo}" />
										<input type="hidden" name="selStd" id="selStd" value="${item.stdNo}" />
									</td>
									<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
									<td>${item.userNm}</td>
									<td>${item.userId}</td>
									<td class="text-right">${item.classConnCnt}</td>
									<td class="text-right">${item.qnaAtclCnt}/${item.qnaCmntCnt}</td>
									<td class="text-right">${item.forumAtclCnt}/${item.forumCmntCnt}</td>
									<td class="text-right">${item.pdsAtclCnt}</td>
									<td class="text-right">${item.totalAtclCnt}/${item.totalCmntCnt}</td>
									<td class="text-right">${item.joinScore}</td>
									<td class="text-center">
										<div class="input-group" style="widtH:110px;">
											<input type="text" name="score" style="text-align:right;" onfocus="this.select()" class="form-control input-sm inputNumber" onkeyup="isChkMaxNumber(this,100)" onblur="chkNumber(this,100);" maxlength="5"/>
											<span class="input-group-addon" onClick="addScore('${status.index}')" style="cursor:pointer">
												<spring:message code="button.add"/>
											</span>
										</div>
									</td>
								</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
					</div>
				</div>
				</form>
				<div class="row" style="margin-bottom:20px">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="list"/>
					</div>
				</div>

<script type="text/javascript">
	var Item = new Object();
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.

	    //전체선택 /전체취소
		$('#chkAll').click(function(){
		    var state=$('input[name=chkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#participateForm input[name='sel']").prop({checked:true});
		    }else{
		    	$(document).find("#participateForm input[name='sel']").prop({checked:false});
		    }
		});

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				list(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			list(1);
		});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function list(page) {
		$('#participateForm').find('input[name=curPage]').val(page);
		$('#participateForm')[0].submit();
	}

	//점수저장
	function addScore(no){
		var stdNoObj = document.getElementsByName("selStd");
		var joinScoreObj = document.getElementsByName("score");
		
		if(joinScore == '') {
			alert('<spring:message code="lecture.message.common.alert.input.score"/>');
			joinScoreObj[no].focus();
			return;
		} else if(joinScore > 100 ) {
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			joinScoreObj[no].focus();
			return;
		} else if(joinScore < 0 ) {
			alert('<spring:message code="lecture.message.common.alert.score.right"/>');
			joinScoreObj[no].focus();
			return;
		}
		$("#stdNo").val(stdNoObj[no].value);
		$("#joinScore").val(joinScoreObj[no].value);
		$('#participateForm').attr("action","/lec/participate/addScore");
		$('#participateForm').submit();
	}

	//점수저장
	function addScoreAll(){
		var stdNoObj = document.getElementsByName("selStd");
		var joinScoreObj = document.getElementsByName("score");

		var strStdNo = "";
		var strJoinScore = "";
		var cntScore = 0;
		for(var i=0; i < joinScoreObj.length; i++){
			if(!isEmpty(joinScoreObj[i].value)) {
				if(joinScoreObj[i].value>100){
					alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
					joinScoreObj[i].value = "0";
					joinScoreObj[i].focus();
					return;
				} else if(joinScoreObj[i].value < 0){
					alert('<spring:message code="lecture.message.common.alert.score.right"/>');
					joinScoreObj[i].focus();
					return;
				}
				strStdNo = strStdNo+"|"+stdNoObj[i].value;
				strJoinScore = strJoinScore+"|"+joinScoreObj[i].value;
				cntScore++;
			}
		}
		if(cntScore == 0){
			alert('<spring:message code="lecture.message.common.alert.needscore"/>');
			return;
		}
		strStdNo = strStdNo.substring(1);
		strJoinScore = strJoinScore.substring(1);
		$('#participateForm').attr("action","/lec/participate/addScoreAll")
		.find('input[name=strStdNo]').val(strStdNo).end()
		.find('input[name=strJoinScore]').val(strJoinScore).end()
		.submit();
	}

	/**
	* 메세지 전송 popup
	*/
	function messageForm(msgDivCd) {
		userList = $("#participateForm input[name='sel']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/message/messageWritePop"/>"
			+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
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
</script>
