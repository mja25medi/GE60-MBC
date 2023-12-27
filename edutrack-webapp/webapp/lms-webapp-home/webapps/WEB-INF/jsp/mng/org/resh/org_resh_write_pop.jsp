<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="orgReshVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>

	<form id="orgReshForm" name="orgReshForm" onsubmit="return false" >
	<input type="hidden" name="reshSn" value="${vo.reshSn }" />
	<input type="hidden" name="itemCnt" value="${vo.itemCnt }" />
	<input type="hidden" name="useCnt" value="${vo.useCnt }" />
	<input type="hidden" name="reshTypeCd" value="G" />
	<input type="hidden" name="reshType" value="G" />
	<table summary="<spring:message code="course.title.reshbank.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:25%"/>
			<col style="width:75%"/>
		</colgroup>
		<tbody>
		<tr height="35">
			<th scope="row" class="top"><span style="color:red;">* </span><label for="reshTitle"><spring:message code="course.title.reshbank.title"/></label></th>
			<td class="top">
				<input type="text" dispName="<spring:message code="course.title.reshbank.title"/>" maxlength="100" isNull="N" lenCheck="100" name="reshTitle" value="${vo.reshTitle }" class="form-control input-sm" id="reshTitle"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="reshCts"><spring:message code="course.title.resh.duration"/></label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="course.title.resh.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="startDttm" value="${vo.startDttm }" id="startDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>

				<div class="input-group text-center" style="float:left;line-heigth:30px;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="course.title.resh.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="endDttm" value="${vo.endDttm }" id="endDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>

				<meditag:datepicker name1="startDttm" name2="endDttm"/>
			</td>
		</tr>
		<tr height="35">
			<th scope="row"><span style="color:red;">* </span><label for="reshCts"><spring:message code="course.title.reshbank.desc"/></label></th>
			<td>
				<textarea name="reshCts" style="height:100px" dispName="<spring:message code="course.title.reshbank.desc"/>" isNull="N" id="reshCts" class="form-control input-sm">${vo.reshCts }</textarea>
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if> /><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if> /><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
<%-- 		<tr height="33">
			<th scope="row">설문유형</th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="reshType" value="G" <c:if test="${vo.reshType eq 'G'}">checked</c:if> />일반형</label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="reshType" value="I" <c:if test="${vo.reshType eq 'I'}">checked</c:if>  /> 점수형</label>
			</td>
		</tr>	
 --%>		</tbody>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addResh()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editResh()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:delResh()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	$(document).ready(function() {
		var gubun = '${gubun}';
		if(gubun == "A"){
			$("input:radio[name='useYn']:radio[value='N']").prop('checked', true);
			$("input:radio[name='reshTypeCd']:radio[value='G']").prop('checked', true);
			$("input:radio[name='reshType']:radio[value='G']").prop('checked', true);
		}
		//
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.orgReshForm)) return;
		var startDttm = $("#startDttm").val();
		var endDttm = $("#endDttm").val();
		var startDttmArray = startDttm.split(".");
		var endDttmArray = endDttm.split(".");
		var StartDttmObj = new Date(startDttmArray[0], Number(startDttmArray[1])-1, startDttmArray[2],  01);
		var EndDttmObj = new Date(endDttmArray[0], Number(endDttmArray[1])-1, endDttmArray[2],  01);

		if(StartDttmObj > EndDttmObj){
			alert('<spring:message code="lecture.message.resh.alert.result.date"/>');
			return;
		}

		$('#orgReshForm').attr("action","/mng/org/research/" + cmd);
		$('#orgReshForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			var curPage = ${vo.curPage};
			if(curPage <= 0 ) curPage =1 ;
			// 정상 처리
			parent.listResearch(curPage);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	<c:if test="${gubun eq 'A'}">
	/**
	 * 과목 분류 등록
	 */
	function addResh() {
		process("add");	// cmd
	}
	</c:if>

	<c:if test="${gubun eq 'E'}">
	/**
	 * 과목 분류 수정
	 */
	function editResh() {
		process("edit");	// cmd
	}

	/**
	 * 과목 분류 삭제
	 */
	function delResh() {
		if(parseInt(document.orgReshForm["itemCnt"].value,10) > 0) {
			alert('<spring:message code="course.message.reshbank.alert.delete1"/>')
			return
		} else{
			 if(confirm('<spring:message code="course.message.reshbank.confirm.delete"/>')) {
				process("delete");
			 }
		}
	}
	</c:if>
</script>
