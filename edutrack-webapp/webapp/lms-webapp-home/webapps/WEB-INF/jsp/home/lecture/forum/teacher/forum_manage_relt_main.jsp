<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<% request.setAttribute("vEnter", "\n"); %>
<c:url var="img_base" value="/img/home" />
<c:set var="forumVO" value="${vo}" />
<c:set var="forumJoinUserVO" value="${forumJoinUserVO}" />
<c:set var="forumJoinList" value="${forumJoinUserList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

				<div class="row">
					<div class="col-md-12">
						<div id="viewForum">
							<table class="table table-bordered">
								<caption class="sr-only"><spring:message code="lecture.title.forum.info"/></caption>
								<colgroup>
									<col style="width:20%" />
									<col />
									<col style="width:20%" />
									<col />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><spring:message code="lecture.title.forum.title"/></th>
										<td colspan="3" class="wordbreak">
											${forumVO.forumTitle}
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="lecture.title.forum.duration"/></th>
										<td>
											<meditag:dateformat type="0" delimeter="." property="${forumVO.forumStartDttm}"/> ~ <meditag:dateformat type="0" delimeter="." property="${forumVO.forumEndDttm}"/>
										</td>
										<th scope="row"><spring:message code="lecture.title.forum.regyn"/></th>
										<td>
											<meditag:codename code="${forumVO.forumRegYn}" category="REG_YN" />
										</td>
									</tr>
									<tr>
										<th scope="row" ><spring:message code="lecture.title.forum.period.after.write"/></th>
										<td colspan="3">
											<c:if test="${forumVO.periodAfterWriteYn eq 'Y' }"><spring:message code="lecture.title.forum.writeY"/></c:if>
											<c:if test="${forumVO.periodAfterWriteYn eq 'N' }"><spring:message code="lecture.title.forum.writeN"/></c:if>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="lecture.title.forum.desc"/></th>
										<td colspan="3" class="wordbreak">
											${fn:replace(forumVO.forumCts,vEnter,"<br>")}
										</td>
									</tr>
								</tbody>
							</table>
							<div class="text-right">
								<a href="javascript:viewEditForm()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
								<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/></a>
							</div>
						</div>
						<div id="editForum" style="display:none">
							<form id="forumForm" name="forumForm" onsubmit="return false" >
							<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
							<input type="hidden" name="forumSn" value="${vo.forumSn}" />
							<input type="hidden" name="gubun" value="${vo.gubun}" />
							<!-- 검색용 -->
							<input type="hidden" name="curPage" />
							<input type="hidden" name="listScale" />
							<input type="hidden" name="forumJoinUserVO.sortKey" id="sortKey" value="${forumJoinUserVO.sortKey}"/>
							<input type="hidden" name="forumJoinUserVO.stdNo" id="stdNo" value="${forumJoinUserVO.stdNo}"/>
							<input type="hidden" name="forumJoinUserVO.score" id="score" value="${forumJoinUserVO.score}"/>

							<input type="hidden" name="declsNo" />
							<input type="hidden" name="userNm" />
							<input type="hidden" name="strStdNo" />
							<input type="hidden" name="strScore" />

							<table class="table table-bordered">
								<caption class="sr-only"><spring:message code="lecture.title.forum.info"/></caption>
								<colgroup>
									<col style="width:20%" />
									<col style="width:80%" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><spring:message code="lecture.title.forum.title"/></th>
										<td>
											<input type="text" dispName="<spring:message code="lecture.title.forum.title"/>" maxlength="100" isNull="N" lenCheck="100" name="forumTitle" value="${vo.forumTitle}" onfocus="this.select()" class="form-control input-sm"/>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="lecture.title.forum.duration"/></th>
										<td>
											<div class="input-group" style="float:left;width:128px;">
												<input type="text" dispName="<spring:message code="lecture.title.forum.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="forumStartDttm" value="${vo.forumStartDttm}" id="forumStartDttm" class="inputDate form-control input-sm"/>
												<span class="input-group-addon btn-sm" onclick="_clickCalendar('forumStartDttm')" style="cursor:pointer">
													<i class="fa fa-calendar"></i>
												</span>
											</div>
											<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.start.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="forumStartHour" value="${vo.forumStartHour}" id="forumStartHour" class="form-control input-sm" onkeyup="isChkHours(this)" />
											<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
											<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.start.min"/>" maxlength="2" isNull="N" lenCheck="2" name="forumStartMin" value="${vo.forumStartMin}" id="forumStartMin" class="form-control input-sm" onkeyup="isChkMinute(this)" />
											<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>

											<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>

											<div class="input-group" style="float:left;width:128px;">
												<input type="text" dispName="<spring:message code="lecture.title.forum.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="forumEndDttm" value="${vo.forumEndDttm}" id="forumEndDttm" class="inputDate form-control input-sm"/>
												<span class="input-group-addon btn-sm" onclick="_clickCalendar('forumEndDttm')" style="cursor:pointer">
													<i class="fa fa-calendar"></i>
												</span>
											</div>
											<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.end.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="forumEndHour" value="${vo.forumEndHour}" id="forumEndHour" class="form-control input-sm" onkeyup="isChkHours(this)" />
											<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
											<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.end.min"/>" maxlength="2" isNull="N" lenCheck="2" name="forumEndMin" value="${vo.forumEndMin}" id="forumEndMin" class="form-control input-sm" onkeyup="isChkMinute(this)" />
											<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
											<meditag:datepicker name1="forumStartDttm" name2="forumEndDttm"/>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="lecture.title.forum.regyn"/></th>
										<td>
											<c:forEach var="item" items="${regYnList}" varStatus="status">
												<c:set var="codeName" value="${item.codeNm}"/>
												<c:forEach var="lang" items="${item.codeLangList}">
													<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
												</c:forEach>
												<input type="radio" name="forumRegYn" value="${item.codeCd}" id="regYn_${item.codeCd}" <c:if test="${vo.forumRegYn eq item.codeCd}">checked</c:if>/><label for="regYn_${item.codeCd}"> ${codeName}</label>
											<c:choose><c:when test="${status.last}"></c:when><c:otherwise>&nbsp;&nbsp;</c:otherwise></c:choose>
											</c:forEach>
										</td>
									</tr>
									<tr>
										<th scope="row" style="width:15%"><spring:message code="lecture.title.forum.period.after.write"/></th>
										<td>
											<input type="radio" property="periodAfterWriteYn" value="Y" id="periodAfterWriteY" <c:if test="${vo.periodAfterWriteYn eq 'Y'}">checked</c:if>/><label for="periodAfterWriteY"> <spring:message code="lecture.title.forum.writeY"/></label>
											&nbsp;&nbsp;
											<input type="radio" property="periodAfterWriteYn" value="N" id="periodAfterWriteN" <c:if test="${vo.periodAfterWriteYn eq 'N'}">checked</c:if>/><label for="periodAfterWriteN"> <spring:message code="lecture.title.forum.writeN"/></label>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="lecture.title.forum.desc"/></th>
										<td>
											<textarea style="height:120px;" rows="13" dispName="<spring:message code="lecture.title.forum.desc"/>" name="forumCts" isNull="N" class="form-control input-sm">${vo.forumCts}</textarea>
										</td>
									</tr>
								</tbody>
							</table>
							<div class="text-right">
								<a href="javascript:editForum()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
								<a href="javascript:delForum()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
								<a href="javascript:hideEditForm()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul class="nav nav-tabs">
							<li><a href="<c:url value="/lec/forum/editForumMain"/>?forumSn=${forumVO.forumSn}&amp;gubun=A"><spring:message code="lecture.title.forum.atcl.manage.tab"/></a></li>
							<li class="active"><a href="#"><spring:message code="lecture.title.forum.rate.manage.tab"/></a></li>
							<li><a href="<c:url value="/lec/forum/editForumMain"/>?forumSn=${forumVO.forumSn}&amp;gubun=S"><spring:message code="lecture.title.forum.status.manage.tab"/></a></li>
						</ul>
					</div>
				</div>
				<form name="SearchStd" id="SearchStd" action="javascript:listStd(1)" class="form-inline">
				<div class="row" style="padding-top:10px;padding-bottom:5px;">
					<div class="col-md-6 col-sm-6">
						<select name="declsNo" id="declsNo" onchange="listStd(1)" class="form-control input-sm" style="max-width:160px;">
							<option value=""><spring:message code="course.title.decls.all"/></option>
							<c:forEach var="decls" items="${creCrsDeclsList}">
								<c:set var="selected" value=""/>
								<c:if test="${declsNo eq decls.declsNo}">
									<c:set var="selected" value="selected"/>
								</c:if>
							<option value="${decls.declsNo}" ${selected}>${decls.declsNo}</option>
							</c:forEach>
						</select>
						<div class="input-group" style="width:160px;">
							<input type="text" id="searchKey" name="searchKey" class="_enterBind form-control input-sm" value="${userNm}" placeholder="<spring:message code="user.title.userinfo.name"/>"/>
							<span class="input-group-addon" onclick="listStd(1)" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 text-right">
						<a href="javascript:addScoreAll()" class="btn btn-primary btn-sm"><spring:message code="button.add.score"/></a>
						<select name="listScale" id="listScale" onchange="listStd(1)" class="form-control input-sm">
							<option value="10" <c:if test="${forumForm.listScale == 10}">selected</c:if>>10</option>
							<option value="20" <c:if test="${forumForm.listScale == 20}">selected</c:if>>20</option>
							<option value="40" <c:if test="${forumForm.listScale == 40}">selected</c:if>>40</option>
							<option value="60" <c:if test="${forumForm.listScale == 60}">selected</c:if>>60</option>
							<option value="80" <c:if test="${forumForm.listScale == 80}">selected</c:if>>80</option>
							<option value="100" <c:if test="${forumForm.listScale == 100}">selected</c:if>>100</option>
							<option value="200" <c:if test="${forumForm.listScale == 200}">selected</c:if>>200</option>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered">
							<colgroup>
								<col style="width:10%" />
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:15%" />
								<col style="width:15%" />
								<col style="width:15%" />
								<col style="width:18%" />
							</colgroup>
							<thead>
							<tr>
								<th scope="col"><spring:message code="common.title.no"/></th>
								<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'USER_NAME') == true}">
											<c:if test="${forumJoinUserVO.sortKey eq 'USER_NAME_ASC'}">
										<a href="javascript:setSortKey('USER_NAME_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${forumJoinUserVO.sortKey eq 'USER_NAME_DESC'}">
										<a href="javascript:setSortKey('USER_NAME_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('USER_NAME_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
								</th>
								<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'USER_ID') == true}">
											<c:if test="${forumJoinUserVO.sortKey eq 'USER_ID_ASC'}">
										<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${forumJoinUserVO.sortKey eq 'USER_ID_DESC'}">
										<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
								</th>
								<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'ATCL_CNT') == true}">
											<c:if test="${forumJoinUserVO.sortKey eq 'ATCL_CNT_ASC'}">
										<a href="javascript:setSortKey('ATCL_CNT_DESC')"><spring:message code="lecture.title.forum.atcl.cnt"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${forumJoinUserVO.sortKey eq 'ATCL_CNT_DESC'}">
										<a href="javascript:setSortKey('ATCL_CNT_ASC')"><spring:message code="lecture.title.forum.atcl.cnt"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('ATCL_CNT_ASC')"><spring:message code="lecture.title.forum.atcl.cnt"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
								</th>
								<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'CMNT_CNT') == true}">
											<c:if test="${forumJoinUserVO.sortKey eq 'CMNT_CNT_ASC'}">
										<a href="javascript:setSortKey('CMNT_CNT_DESC')"><spring:message code="lecture.title.forum.cmnt.cnt"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${forumJoinUserVO.sortKey eq 'CMNT_CNT_DESC'}">
										<a href="javascript:setSortKey('CMNT_CNT_ASC')"><spring:message code="lecture.title.forum.cmnt.cnt"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CMNT_CNT_ASC')"><spring:message code="lecture.title.forum.cmnt.cnt"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
								</th>
								<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'SCORE') == true}">
											<c:if test="${forumJoinUserVO.sortKey eq 'SCORE_ASC'}">
										<a href="javascript:setSortKey('SCORE_DESC')"><spring:message code="lecture.title.forum.rate.score"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${forumJoinUserVO.sortKey eq 'SCORE_DESC'}">
										<a href="javascript:setSortKey('SCORE_ASC')"><spring:message code="lecture.title.forum.rate.score"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('SCORE_ASC')"><spring:message code="lecture.title.forum.rate.score"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
								</th>
								<th scope="col" class="rnone"><spring:message code="lecture.title.forum.rate"/></th>
							</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${forumJoinList}" varStatus="status">
								<tr>
									<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
									<td>${item.userNm}<input type="hidden" name="selStd" value="${item.stdNo}"/></td>
									<td>${item.userId}</td>
									<td class="text-right">${item.atclCnt}</td>
									<td class="text-right">${item.cmntCnt}</td>
									<td class="text-right">${item.score}</td>
									<td class="text-center">
										<div class="input-group" style="width:120px;">
											<input type="text" style="text-align:right;" name="score" class="form-control input-sm inputNumber" value="" onkeyup="isChkMaxNumber(this,100)" onblur="chkNumber(this,100);" maxlength="5"/>
											<span class="input-group-addon" onclick="addScore('${status.index}')" style="cursor:pointer"><spring:message code="button.add"/></span>
										</div>
									</td>
								</tr>
								</c:forEach>
								<c:if test="${empty forumJoinList}">
									<tr>
										<td colspan="7"><spring:message code="student.message.student.nodata"/></td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				</form>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-md-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="listStd"/>
					</div>
				</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listStd(1);
			}
		});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		hideEditForm();

		if('${forumVO.periodAfterWriteYn}' == 'Y'){
			$('#periodAfterWriteY').attr("checked",true);
		}else{
			$('#periodAfterWriteN').attr("checked",true);
		}
	});

	/**
	 * 수정 화면 토글
	 */
	function viewEditForm() {
		$("#viewForum").hide();
		$("#editForum").show();
	}

	/**
	 * 수정 화면 토글
	 */
	function hideEditForm() {
		$("#viewForum").show();
		$("#editForum").hide();
	}

	/**
	* 목록으로 이동
	*/
	function goList() {
		document.location.href = cUrl("/lec/forum/main");
	}

	/**
	 * 토론 수정
	 */
	function editForum() {
		var f = document.forumForm;
		if(!validate(f)) return;
		var startDttm = $("#forumStartDttm").val();
		var endDttm = $("#forumEndDttm").val();
		var startHour = $("#forumStartHour").val();
		var startMin = $("#forumStartMin").val();
		var endHour = $("#forumEndHour").val();
		var endMin = $("#forumEndMin").val();
		var startDttmArray = startDttm.split(".");
		var endDttmArray = endDttm.split(".");
		var StartDttmObj = new Date(startDttmArray[0], Number(startDttmArray[1])-1, startDttmArray[2], startHour, startMin, 01);
		var EndDttmObj = new Date(endDttmArray[0], Number(endDttmArray[1])-1, endDttmArray[2], endHour, endMin, 01);

		if(StartDttmObj >= EndDttmObj){
			alert('<spring:message code="lecture.message.forum.alert.result.date"/>');
			return;
		}
		process("editForm");	// cmd
	}

	/**
	 * 토론 삭제
	 */
	function delForum(){
		if(confirm("<spring:message code="lecture.message.forum.confirm.delete"/>")){
			process("deleteForum");
		}
	}

	/**
	* 학습자 목록
	*/
	function listStd(page){
		var declsNo = $("#declsNo option:selected").val();
		var userNm = $("#searchKey").val();
		var listScale = $("#listScale option:selected").val();

		$('#forumForm').attr("action","/lec/forum/editForumMain")
		.find('input[name=curPage]').val(page).end()
		.find('input[name=declsNo]').val(declsNo).end()
		.find('input[name=userNm]').val(userNm).end()
		.find('input[name=listScale]').val(listScale);
		document.forumForm.submit();
	}

	function setSortKey(sortKey) {
		$("#sortKey").val(sortKey);
		listStd(1);
	}

	/**
	* 개별 성적 저장
	*/
	function addScore(index){
		if(dateCheck("${curDateTime}","${forumVO.forumEndDttm}")){
			if(!confirm("<spring:message code="lecture.message.forum.confirm.chkdate"/>")){
				return;
			}
		}

		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");
		if(ScoreObj[index].value == ""){
			alert('<spring:message code="lecture.message.common.alert.needscore"/>');
			return;
		}
		if(ScoreObj[index].value>100){
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			ScoreObj[index].value = "0";
			ScoreObj[index].focus();
			return;
		}
		
		var listScale = $("#listScale option:selected").val();
		var curPage = 1;
		$('#forumForm').attr("action","/lec/forum/addScore");
		$("#stdNo").val(StdNoObj[index].value);
		$("#score").val(ScoreObj[index].value);
		$("input[name=curPage]").val(curPage);
		$("input[name=listScale]").val(listScale);
		document.forumForm.submit();

	}

	/**
	* 일괄 성적 저장
	*/
	function addScoreAll() {
		if(dateCheck("${curDateTime}","${forumVO.forumEndDttm}")){
			if(!confirm("<spring:message code="lecture.message.forum.confirm.chkdate"/>")){
				return;
			}
		}

		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");

		var strStdNo = "";
		var strScore = "";
		var cntScore = 0;
		var cntScore100 = 0;

		for(var i=0; i < ScoreObj.length; i++) {
			//-- 값이 들어온것만 잡는다.
			if(!isEmpty(ScoreObj[i].value)) {
				strStdNo = strStdNo+"|"+StdNoObj[i].value;
				strScore = strScore+"|"+ScoreObj[i].value;
				cntScore++;
			}

			if(!isEmpty(ScoreObj[i].value) && ScoreObj[i].value>100){
				cntScore100++;
				ScoreObj[i].value = 0;
			}
		}
		if(cntScore == 0){
			alert('<spring:message code="lecture.message.common.alert.needscore"/>');
			return;
		}

		if(cntScore100 > 0){
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			return;
		}

		strStdNo = strStdNo.substring(1);
		strScore = strScore.substring(1);

		$('#forumForm').attr("action","/lec/forum/addScoreAll")
		.find('input[name=strStdNo]').val(strStdNo).end()
		.find('input[name=strScore]').val(strScore);
		document.forumForm.submit();

	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#forumForm').attr("action","/lec/forum/"+cmd);
		document.forumForm.submit();
	}


	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
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
