<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="forumVO" value="${vo}"/>
				<form id="forumForm" name="forumForm" >
				<input type="hidden" name="forumSn" value="${vo.forumSn}" />
				<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
				<input type="hidden" name="curPage" value="${vo.curPage}" />
				<table class="table table-bordered wordbreak">
					<caption class="sr-only"><spring:message code="lecture.title.forum.manage"/></caption>
					<tr>
						<th scope="row" style="width:20%"><spring:message code="lecture.title.forum.title"/></th>
						<td><input type="text" maxlength="100" dispName="<spring:message code="lecture.title.forum.title"/>" isNull="N" lenCheck="100" name="forumTitle" value="${forumVO.forumTitle}" class="form-control input-sm"/></td>
					</tr>
					<tr>
						<th scope="row" style="width:20%"><spring:message code="lecture.title.forum.duration"/></th>
						<td>
							<div class="input-group" style="float:left;width:128px;">
								<input type="text" dispName="<spring:message code="lecture.title.forum.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="forumStartDttm" value="${forumVO.forumStartDttm}" id="forumStartDttm" class="inputDate form-control input-sm"/>
								<span class="input-group-addon btn-sm" onclick="_clickCalendar('forumStartDttm')" style="cursor:pointer">
									<i class="fa fa-calendar"></i>
								</span>
							</div>
							<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
							<div class="input-group" style="float:left;width:128px;">
								<input type="text" dispName="<spring:message code="lecture.title.forum.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="forumEndDttm" value="${forumVO.forumEndDttm}" id="forumEndDttm" class="inputDate form-control input-sm"/>
								<span class="input-group-addon btn-sm" onclick="_clickCalendar('forumEndDttm')" style="cursor:pointer">
									<i class="fa fa-calendar"></i>
								</span>
							</div>
							<meditag:datepicker name1="forumStartDttm" name2="forumEndDttm"/>
						</td>
					</tr>
					<tr>
						<th scope="row" style="width:15%"><spring:message code="lecture.title.forum.regyn"/></th>
						<td>
							<c:forEach var="item" items="${regYnList}" varStatus="status">
								<c:set var="codeName" value="${item.codeNm}"/>
								<c:forEach var="lang" items="${item.codeLangList}">
									<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
								</c:forEach>
								<input type="radio" name="forumRegYn" value="${item.codeCd}" id="regYn_${item.codeCd}"/><label for="regYn_${item.codeCd}"> ${codeName}</label>
								<c:choose>
									<c:when test="${status.last}"></c:when>
									<c:otherwise>&nbsp;&nbsp;</c:otherwise>
								</c:choose>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th scope="row" style="width:15%"><spring:message code="lecture.title.forum.period.after.write"/></th>
						<td>
							<input type="radio" name="periodAfterWriteYn" value="Y" id="periodAfterWriteY" <c:if test="${forumVO.periodAfterWriteYn eq 'Y'}">checked</c:if>/><label for="periodAfterWriteY"> <spring:message code="lecture.title.forum.writeY"/></label>
							&nbsp;&nbsp;
							<input type="radio" name="periodAfterWriteYn" value="N" id="periodAfterWriteN" <c:if test="${forumVO.periodAfterWriteYn eq 'N'}">checked</c:if>/><label for="periodAfterWriteN"> <spring:message code="lecture.title.forum.writeN"/></label>

						</td>
					</tr>
					<tr>
						<th scope="row" style="width:20%"><spring:message code="lecture.title.forum.desc"/></th>
						<td><textarea style="height:120px;" dispName="<spring:message code="lecture.title.forum.desc"/>" isNull="N" name="forumCts" cols="100" rows="25" class="form-control input-sm">${forumVO.forumCts}</textarea></td>
					</tr>
				</table>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div class="text-right">
							<a href="javascript:editForum()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
							<a href="javascript:goView()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
						</div>
					</div>
				</div>
				</form>

<script type="text/javascript">
$(document).ready(function() {
$('#periodAfterWriteN').attr("checked",true);
	$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
	$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
});

function goView(){
	parent.modalBox.resize(800,400);
	history.go(-1);
}

/**
 * 서브밋 처리
 */
function process(cmd) {
	if(!validate(document.forumForm)) return;
	var forumStartDttm = chgDate($('#forumStartDttm').val());
	var forumEndDttm = chgDate($('#forumEndDttm').val());
	if(parseInt(forumStartDttm,10) >= parseInt(forumEndDttm,10)){
		alert('<spring:message code="lecture.message.forum.alert.result.date"/>');
		return;
	}
	$('#forumForm').attr("action","/lec/forum/"+cmd);
	$('#forumForm').ajaxSubmit(processCallback);
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

/**
 * .forum개설
 */
function editForum() {
	process("editForumPop");	// cmd
}

</script>
