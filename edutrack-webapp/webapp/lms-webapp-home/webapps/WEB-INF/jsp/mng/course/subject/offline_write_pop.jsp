<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="offlineSubjectVO" value="${vo}" />

	<form id="subjectForm" name="subjectForm" onsubmit="return false">
	<input type="hidden" name="autoMakeYn" value="Y"/>
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }"/>
	<table summary="<spring:message code="course.title.subject.manage.offline"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="course.title.subject.category"/></th>
			<td>
				<div class="input-group">
					<input type="hidden" name="sbjCtgrCd" id="sbjCtgrCd" value="${vo.sbjCtgrCd}"/>
					<div class="input-group-btn btn-group">
    					<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
    						<span class="caret"></span>
  						</button>
  						<ul class="dropdown-menu" style="max-height:220px;overflow:auto;">
  						<c:set var="defLvl" value="0" />
						<c:set var="lineNo" value="0" />
						<c:forEach items="${categoryList}" var="item">
							<c:set var="lineNo" value="${lineNo + 1}" />
    						<c:if test="${lineNo == 1}" >
								<c:set var="defLvl" value="1" />
							</c:if>
    						<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
							<c:if test="${item.subCnt > 0}">
								<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
							</c:if>
							<c:url var="blankImgPath" value="/img/framework/common/blank.gif" />

    						<%-- <li><a href="javascript:setSbjCtgr('${item.sbjCtgrCd}','${item.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.sbjCtgrNm} </a></li> --%>

    						<c:if test="${item.ctgrLvl == 1}">
								<c:set var="sbjCtgrCd_01" value="${item.sbjCtgrCd}" />
								<li><a href="javascript:setSbjCtgr('${item.sbjCtgrCd}','${item.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.sbjCtgrNm} </a></li>
							</c:if>

							<c:if test="${item.ctgrLvl == 2}">
								<c:if test="${sbjCtgrCd_01 eq item.parSbjCtgrCd}">
								<c:set var="sbjCtgrCd_02" value="${item.sbjCtgrCd}" />
								<li><a href="javascript:setSbjCtgr('${item.sbjCtgrCd}','${item.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.sbjCtgrNm} </a></li>
								</c:if>
							</c:if>

							<c:if test="${item.ctgrLvl == 3}">
								<c:if test="${sbjCtgrCd_02 eq item.parSbjCtgrCd}">
								<c:set var="sbjCtgrCd_03" value="${item.sbjCtgrCd}" />
								<li><a href="javascript:setSbjCtgr('${item.sbjCtgrCd}','${item.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.sbjCtgrNm} </a></li>
								</c:if>
							</c:if>
    					</c:forEach>
    						<c:if test="${empty categoryList}">
								<li style="padding-left:0px;">* <spring:message code="library.message.ctgr.nodata"/> </li>
							</c:if>
  						</ul>
  					</div>
  					<input type="text" dispName="<spring:message code="course.title.course.category"/>" maxlength="100" isNull="N" name="sbjCtgrNm" value="${vo.sbjCtgrNm }" class="form-control input-sm" id="sbjCtgrNm" readonly="true" style="background-color:#ffffff;"/>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="sbjNm"><spring:message code="course.title.subject.name"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="course.title.subject.name"/>" maxlength="50" isNull="N" lenCheck="50" name="sbjNm" value="${vo.sbjNm }" class="form-control input-sm" id="sbjNm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="sbjDesc"><spring:message code="course.title.subject.desc"/></label></th>
			<td><textarea style="height:50px" dispName="<spring:message code="course.title.subject.desc"/>" lenCheck="1000" isNull="N" name="sbjDesc" id="sbjDesc" class="form-control input-sm">${vo.sbjDesc }</textarea></td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if> /><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn ne 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addSubject()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editSubject()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:delSubject()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	$(document).ready(function() {
	});

	function setSbjCtgr(ctgrCd, ctgrNm) {
		$('#sbjCtgrCd').val(ctgrCd);
		$('#sbjCtgrNm').val(ctgrNm);
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.subjectForm)) return;

		$('#subjectForm').attr("action","/mng/course/subject/" + cmd);
		$('#subjectForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		<c:if test="${vo.createMode eq 'creCrsSbj'}">
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.addCreateFrame.subWorkFrame.offlineSearch();
		} else {
			// 비정상 처리
			parent.addCreateFrame.subWorkFrame.offlineSearch();
		}
		</c:if>
		<c:if test="${vo.createMode ne 'creCrsSbj'}">
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listSubject(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
			//parent.modalBoxClose();
		}
		</c:if>
	}

	/**
	 * 과목 등록
	 */
	function addSubject() {
		process("addOffline");	// cmd
	}

	/**
	 * 과목 수정
	 */
	function editSubject() {
		process("editOffline");	// cmd
	}

	/**
	 * 과목 삭제
	 */
	function delSubject() {
		var f = document.subjectForm;
		if(confirm('<spring:message code="course.message.subject.confirm.delete"/>')) {
			process("deleteOffline");	// cmd
		} else {
			return;
		}
	}

</script>
