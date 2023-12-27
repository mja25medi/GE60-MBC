<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="oflnSbjTchTmVO" value="${vo}"/>
<mhtml:html>
<mhtml:head titleName="오프라인 과목 강사 등록">
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/calendar.js"/>
</mhtml:head>

<mhtml:frm_body>
	<br>
	<form id="oflnSbjTchForm" name="oflnSbjTchForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }"/>
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }"/>
	<input type="hidden" name="declsNo" value="${vo.declsNo }"/>
	<input type="hidden" name="userNo" value="${vo.userNo }"/>
	<input type="hidden" name="lecSn" value="${vo.lecSn }"/>
	<table class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row">강의실</th>
			<td class="top">
				<meditag:selectbox width="110" fieldName="oflnSbjTchTmVO.classViewCd" formName="oflnSbjTchForm" height="200" value="${oflnSbjTchTmVO.classViewCd}" onChange="changeUrl()"/>
				<meditag:selectboxOption formName="oflnSbjTchForm" value="" fieldName="oflnSbjTchTmVO.classViewCd" text="강의실 선택" />
				<c:forEach var="item" items="${codeList}">
				<meditag:selectboxOption formName="oflnSbjTchForm" value="${item.codeCd}" fieldName="oflnSbjTchTmVO.classViewCd" text="${item.codeNm}" />
				</c:forEach>
			</td>
		</tr>
		<tr height="33">
			<th scope="row">모니터링</th>
			<td>
				<input type="radio" style="border:0" name="viewYn" value="Y" id="viewYnY" <c:if test="${vo.viewYn eq 'Y'}">checked</c:if>/>허용&nbsp;&nbsp;&nbsp;
				<input type="radio" style="border:0" name="viewYn" value="N" <c:if test="${vo.viewYn eq 'N'}">checked</c:if>/>허용안함
			</td>
		</tr>
		<tr height="33">
			<th scope="row">URL</th>
			<td>
				<input type="text" name="url" id="url" value="${monitorUrl}" readonly="readonly" class="txt" style="width:250px;"/>
			</td>
		</tr>
		<tr height="33">
			<th scope="row">강의기간</th>
			<td style="padding:5px;">
				<div style="float:left;width:100%">
					<div style="float:left">
			 			<input type="text" style="width:70px;" dispName="시작일" maxlength="50" isNull="N" lenCheck="50" name="startDt" value="${vo.startDt }" readonly="true" class="txt" title="시작일"/>
						<img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" alt="달력" onClick="Calendar('달력', 'oflnSbjTchForm', 'oflnSbjTchTmVO.startDt', '.', 'position=right,datetype=00,clear=yes');">
						<input type="hidden" name="startTm" id="startTm" value="${vo.startTm }"/>
					</div>
					<div style="float:left;padding-left:5px;">
						<select name="startHour" id="startHour">
							<option value=""></option>
							<c:forEach var="hour" items="${hourList}">
							<option value="${hour}" <c:if test="${hour eq oflnSbjTchTmVO.startHour}">selected</c:if>>${hour}</option>
							</c:forEach>
						</select>
					</div>
					<div style="float:left;padding-left:5px;">
						<select name="startMin" id="startMin">
							<option value=""></option>
							<c:forEach var="min" items="${minList}">
							<option value="${min}" <c:if test="${min eq oflnSbjTchTmVO.startMin}">selected</c:if>>${min}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div style="float:left;width:100%;margin-top:5px;">
					<div style="float:left">
			 			<input type="text" style="width:70px;" dispName="종료일" maxlength="50" isNull="N" lenCheck="50" name="endDt" value="${vo.endDt }" readonly="true" class="txt" title="종료일"/>
						<img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" alt="달력" onClick="Calendar('달력', 'oflnSbjTchForm', 'oflnSbjTchTmVO.endDt', '.', 'position=right,datetype=00,clear=yes');">
						<input type="hidden" name="endTm" id="endTm" value="${vo.endTm }"/>
					</div>
					<div style="float:left;padding-left:5px;">
						<select name="endHour" id="endHour">
							<option value=""></option>
							<c:forEach var="hour" items="${hourList}">
							<option value="${hour}" <c:if test="${hour eq oflnSbjTchTmVO.endHour}">selected</c:if>>${hour}</option>
							</c:forEach>
						</select>
					</div>
					<div style="float:left;padding-left:5px;">
						<select name="endMin" id="endMin">
							<option value=""></option>
							<c:forEach var="min" items="${minList}">
							<option value="${min}" <c:if test="${min eq oflnSbjTchTmVO.endMin}">selected</c:if>>${min}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</td>
		</tr>
		<tr height="33">
			<th scope="row">강의설명</th>
			<td style="padding:5px;">
				 <textarea style="width:300px;height:50px" dispName="강의설명" isNull="Y" lenCheck="1000" name="lecDesc" id="lecDesc">${vo.lecDesc}</textarea>
			</td>
		</tr>
	</table>

	<meditag:buttonwrapper style="padding: 3px 0px 3px 0px; width: 96%">
		<c:if test="${gubun eq 'A'}">
			<meditag:button value="등록" title="등록" func="addTime()" />
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<meditag:button value="수정" title="수정" func="editTime()" />
			<meditag:button value="삭제" title="삭제" func="delTime()" />
		</c:if>
		<meditag:button value="닫기" title="닫기" func="parent.tchPopBox.close()" />
	</meditag:buttonwrapper>

	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	$(document).ready(function() {
		changeUrl();
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.oflnSbjTchForm)) return;

		$('#oflnSbjTchForm').attr("action","/mng/course/ofln/sbjTch/" + cmd);
		$('#oflnSbjTchForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.addCreateFrame.subWorkFrame.listTime();
			parent.tchPopBox.close();
		} else {
			// 비정상 처리
			//parent.tchPopBox.close();
		}
	}

	/**
	 * 강의 등록
	 */
	function addTime() {
		var classroom = oflnSbjTchFormoflnSbjTchTmVO_classViewCd.getSelectedValue();
		if($("#viewYnY").attr("checked")) {
			if(classroom == '') {
				alert('강의실을 선택하셔야 합니다.');
				return;
			}
		}

		var startHour = $("#startHour option:selected").val();
		var startMin = $("#startMin option:selected").val();
		var endHour = $("#endHour option:selected").val();
		var endMin = $("#endMin option:selected").val();


		var startTm = "";
		var endTm = "";

		if(startHour != '' && startMin != '') startTm = startHour+startMin;
		if(endHour != '' && endMin != '') endTm = endHour+endMin;

		$("#startTm").val(startTm);
		$("#endTm").val(endTm);

		process("addTime");	// cmd
	}

	/**
	 * 강사 수정
	 */
	function editTime() {
		var classroom = oflnSbjTchFormoflnSbjTchTmVO_classViewCd.getSelectedValue();
		if($("#viewYnY").attr("checked")) {
			if(classroom == '') {
				alert('강의실을 선택하셔야 합니다.');
				return;
			}
		}
		var startHour = $("#startHour option:selected").val();
		var startMin = $("#startMin option:selected").val();
		var endHour = $("#endHour option:selected").val();
		var endMin = $("#endMin option:selected").val();

		var startTm = "";
		var endTm = "";

		if(startHour != '' && startMin != '') startTm = startHour+startMin;
		if(endHour != '' && endMin != '') endTm = endHour+endMin;

		$("#startTm").val(startTm);
		$("#endTm").val(endTm);

		process("editTime");	// cmd
	}

	/**
	 * 강사 삭제
	 */
	function delTime() {
		var f = document.oflnSbjTchForm;
		if(confirm('강의를 삭제하려고 합니다.\n\n\n\n삭제 하시겠습니까?')) {
			process("removeTime");	// cmd
		} else {
			return;
		}
	}

	function changeUrl() {
		var classroom = oflnSbjTchFormoflnSbjTchTmVO_classViewCd.getSelectedValue();
		var murl = {};
		murl[''] = '';
		<c:forEach var="item" items="${codeList}">
		murl['${item.codeCd}'] = '${item.codeOptn}';
		</c:forEach>
		$("#url").val(murl[classroom]);

	}
</script>
</mhtml:frm_body>
</mhtml:html>