<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<% request.setAttribute("vEnter", "\n"); %>
<c:url var="img_base" value="/img/home" />
<c:set var="forumVO" value="${vo}" />
<c:set var="forumAtclList" value="${forumAtclList}"/>
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
							<input type="hidden" name="curPage" value="${vo.curPage}" />
							<input type="hidden" name="listScale" value="${vo.listScale}"/>
							<input type="hidden" name="searchKey" value="${vo.searchKey}"/>
							<input type="hidden" name="searchValue" value="${vo.searchValue}"/>
							<input type="hidden" name="stdNo" />
							<input type="hidden" name="score" />
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
											<input type="radio" name="periodAfterWriteYn" value="Y" id="periodAfterWriteY" <c:if test="${vo.periodAfterWriteYn eq 'Y'}">checked</c:if>/><label for="periodAfterWriteY"> <spring:message code="lecture.title.forum.writeY"/></label>
											&nbsp;&nbsp;
											<input type="radio" name="periodAfterWriteYn" value="N" id="periodAfterWriteN" <c:if test="${vo.periodAfterWriteYn eq 'N'}">checked</c:if>/><label for="periodAfterWriteN"> <spring:message code="lecture.title.forum.writeN"/></label>
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
							<li class="active"><a href="#"><spring:message code="lecture.title.forum.atcl.manage.tab"/></a></li>
							<li><a href="<c:url value="/lec/forum/editForumMain"/>?forumSn=${forumVO.forumSn}&amp;gubun=R"><spring:message code="lecture.title.forum.rate.manage.tab"/></a></li>
							<li><a href="<c:url value="/lec/forum/editForumMain"/>?forumSn=${forumVO.forumSn}&amp;gubun=S"><spring:message code="lecture.title.forum.status.manage.tab"/></a></li>
						</ul>
					</div>
				</div>
				<form name="SearchAtcl" id="SearchAtcl" onsubmit="return false;" class="form-inline">
				<div class="row" style="padding-top:10px;padding-bottom:5px;">
					<div class="col-md-6 col-sm-6">
						<select name="searchKey" id="searchKey" class="form-control input-sm" style="max-width:160px;">
							<option value="title" <c:if test="${ searchKey eq 'title' }">selected="selected"</c:if>><spring:message code="common.title.title"/></option>
							<option value="regNm" <c:if test="${ searchKey eq 'regNm' }">selected="selected"</c:if>><spring:message code="common.title.reguser"/></option>
						</select>
						<div class="input-group" style="width:160px;">
							<input type="text" name="searchValue" class="_enterBind form-control input-sm" value="${searchValue}" placeholder="<spring:message code="common.title.all"/>"/>
							<span class="input-group-addon" onclick="listAtcl(1)" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 text-right">
						<a href="javascript:writeAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.write.article"/></a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul class="list-group">
							<c:forEach var="item" items="${forumAtclList}" varStatus="status">
							<li class="list-group-item" style="clear: both;">
								<div class="pull-left"><meditag:reply level="${item.atclLvl}" /></div>
								<div class="wordbreak" style="min-height:60px;">

									<div class="media-body">
 										<a href="javascript:readAtcl('${item.atclSn}')">
										<h4 class="media-heading" title="${item.title }">
											${fn:substring(item.title,0, 30)}<c:if test="${fn:length(item.title) > 30 }">...</c:if>
										</h4>
										<p style="font-size:12px;color:gray;margin-top: 15px;">${item.regNm} |
											<meditag:dateformat type="0" delimeter="." property="${item.regDttm}"/> |
											<spring:message code="common.title.hits"/> : ${item.hits} |
											<spring:message code="board.title.bbs.info.comment"/> : ${item.commentCount}
											<c:if test="${not empty item.attachFiles}">
											<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="status">
												| <a href="<c:url value="/app/file/download/${fileItem.fileSn}"/>" onclick="javascript:fileDown('${fileItem.fileSn}');" title="${fileItem.fileNm}"><i class="glyphicon glyphicon-floppy-save"></i></a>
											</c:forEach>
										</c:if>
										</p>
										<div style="clear: both;"></div>
										</a>
									</div>
								</div>

								</li>
							</c:forEach>
							<c:if test="${empty forumAtclList}">
							<li class="list-group-item"><spring:message code="board.message.bbs.atcl.nodata"/></li>
							</c:if>
						</ul>
					</div>
				</div>
				</form>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-md-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="listAtcl"/>
					</div>
				</div>

<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listAtcl(1);
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

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

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
		if(confirm("<spring:message code="lecture.message.forum.atcl.confirm.delete"/>")){
			process("deleteForum");
		}
	}

	/**
	* 토론게시글 목록
	*/
	function listAtcl(page){
		var f = document.SearchAtcl;
		var searchKey = f.searchKey[f.searchKey.selectedIndex].value;
		var searchValue = f.searchValue.value;
		$('#forumForm').attr("action","/lec/forum/editForumMain")
		.find('input[name=curPage]').val(page).end()
		.find('input[name=gubun]').val("A").end()
		.find('input[name=searchKey]').val(searchKey).end()
		.find('input[name=searchValue]').val(searchValue);
		document.forumForm.submit();
	}

	/**
	* 토론글 상세보기
	*/
	function readAtcl(atclSn){
		var addContent  = "<iframe id='userPasswordFrame' name='userPasswordFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/forum/readForumArticlePop"/>"
			+ "?forumSn=${forumVO.forumSn}&amp;isHitup=Y&amp;atclSn="+atclSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("");
		modalBox.show();
	}

	/**
	* 토론글 등록 폼
	*/
	function writeAtcl() {
		var addContent  = "<iframe id='userPasswordFrame' name='userPasswordFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/forum/addForumArticlePop"/>"
			+ "?forumSn=${forumVO.forumSn}&amp;gubun=A'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="lecture.title.forum.atcl.write"/>");
		modalBox.show();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#forumForm').attr("action","/lec/forum/" + cmd);
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
</script>
