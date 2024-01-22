<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="onlineSubjectVO" value="${vo}"/>
	<br/>
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="course.title.subject.manage"/></h5>
		</div>
		<div style="float:right">
			<button class="btn btn-primary btn-sm" onclick="onlineSearch()" ><spring:message code="button.write.subject"/></button>
		</div>
	</div>
	<div id="onlineList">
		<table summary="<spring:message code="course.title.subject.manage.online"/>" class="table table-bordered wordbreak" style="font-size: 14px;">
			<colgroup>
               <col style="width:50px"/>
               <col style="width:auto"/>
               <col style="width:auto;"/>
               <col style="width:auto;"/>
               <col style="width:auto"/>
               <col style="width:auto"/>
               <col style="width:auto"/>
               <col style="width:auto"/>
               <col style="width:75px"/>
           </colgroup>
           <thead>
               <tr>
                   <th scope="col">번호</th>
                   <th scope="col">과목코드</th>
                   <th scope="col">과목명</th>
                   <th scope="col">과목유형</th>
                   <th scope="col">목차수</th>
                   <th scope="col">강사</th>
                   <th scope="col">교육 일정</th>
                   <th scope="col">장소</th>
                   <th scope="col">삭제</th>
               </tr>
           </thead>
			<tbody>
				<tr>
					<td colspan="6"><spring:message code="course.message.subject.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="margin-bottom:30px;"></div>
	<%-- <c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'OF'}">
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="course.title.subject.manage.offline"/></h5>
		</div>
		<div style="float:right">
			<button class="btn btn-primary btn-sm" onclick="offlineSearch()" ><spring:message code="button.write.subject"/></button>
		</div>
	</div>
	<div id="offlineList">
		<table summary="<spring:message code="course.title.subject.manage.offline"/>" class="table table-bordered wordbreak" style="font-size: 14px;">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:110px"/>
				<col style="width:200px"/>
				<col style="width:180px"/>
				<col style="width:140px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.subject.code"/></th>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.category"/></th>
					<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"><spring:message code="course.message.subject.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	</c:if> --%>

<script type="text/javascript">
	$(document).ready(function() {
		listOnline();
		//listOffline();
	});
	
	//오프라인 과목을 분리하지 않고 온라인 과목테이블에 sbjType으로 구분하여 공통 사용한다.
	function listOnline() {
		$('#onlineList').load(
			cUrl("/mng/course/createCourse/subject/listOnlineSubject"),
			{ "crsCreCd": "${onlineSubjectVO.crsCreCd}"},
			resizeForm
		);
	}

	function onlineSearch() {
		var addContent  = "<iframe id='addSubjectFrame' name='addSubjectFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/course/createCourse/subject/searchFormOnlineSubjectPop"/>"
			+ "?crsCreCd=${onlineSubjectVO.crsCreCd}'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(700, 480);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.subject.write"/>");
		parent.modalBox.show();
	}

	function onlineSort(sortString) {
		$.getJSON( cUrl( "/mng/course/createCourse/subject/sortOnlineSubject"),
				{  "crsCreCd": "${onlineSubjectVO.crsCreCd}", "sbjCd" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listOnline();
		 	  		}
				}
			);
	}

	function showEditOnlineMthd(sbjCd) {
		var studyMthd = $("#studyMthdValue_"+sbjCd).val();
		$("#studyMthd_"+sbjCd).val(studyMthd) //-- select 값 변경
		$("#studyMthdName_"+sbjCd).hide();
		$("#studyMthdSelect_"+sbjCd).show();
		$("#studyMthdEditButton_"+sbjCd).hide();
		$("#studyMthdCancelButton_"+sbjCd).show();
	}

	function hideEditOnlineMthd(sbjCd) {
		$("#studyMthdSelect_"+sbjCd).hide();
		$("#studyMthdName_"+sbjCd).show();
		$("#studyMthdCancelButton_"+sbjCd).hide();
		$("#studyMthdEditButton_"+sbjCd).show();
	}

	function editOnlineMthd(sbjCd) {
		var studyMthd = $("#studyMthd_"+sbjCd+" option:selected").val();
		var studyMthdText = $("#studyMthd_"+sbjCd+" option:selected").text();

		$.getJSON( cUrl( "/mng/course/createCourse/subject/editOnlineSubject"),
					{  "crsCreCd": "${onlineSubjectVO.crsCreCd}", "sbjCd" : sbjCd, "studyMthd" : studyMthd },			// params
					function(data) {
						alert(data.message);
						if(data.result >= 0) {
							//-- 정상 처리
							$("#studyMthdName_"+sbjCd).html(studyMthdText);
							$("#studyMthdValue_"+sbjCd).val(studyMthd);
							hideEditOnlineMthd(sbjCd);
						} else {
			 	  			//-- 비정상 처리
			 	  		}
					}
				);
	}

	/**
	 * 온라인 과목 삭제
	 */
	function delOnline(sbjCd) {
		if(confirm("<spring:message code="course.message.subject.confirm.delete"/>")) {
			$.getJSON( cUrl( "/mng/course/createCourse/subject/deleteOnlineSubject"),
					   {  "crsCreCd": "${onlineSubjectVO.crsCreCd}", "sbjCd" : sbjCd },			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert("<spring:message code="course.message.subject.delete.success"/>");
								listOnline();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert("<spring:message code="course.message.subject.delete.failed"/>");
				 	  		}
						}
					);
		} else return;
	}

	<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'OF'}">
	function listOffline() {
		$('#offlineList').load(
			cUrl("/mng/course/createCourse/subject/listOfflineSubject"),
			{ "crsCreCd": "${onlineSubjectVO.crsCreCd}"},
			resizeForm
		);
	}

	function offlineSearch() {
		var addContent  = "<iframe id='addSubjectFrame' name='addSubjectFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/createCourse/subject/searchFormOfflineSubjectPop"/>"
			+ "?crsCreCd=${onlineSubjectVO.crsCreCd}'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(620, 480);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.subject.write"/>");
		parent.modalBox.show();
	}

	function offlineSort(sortString) {
		$.getJSON( cUrl( "/mng/course/createCourse/subject/sortOfflineSubject"),
				{ "crsCreCd": "${onlineSubjectVO.crsCreCd}", "sbjCd" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listOnline();
		 	  		}
				}
			);
	}

	function showEditOfflineMthd(sbjCd) {
		var eduMthd = $("#eduMthdValue_"+sbjCd).val();
		$("#eduMthd_"+sbjCd).val(eduMthd);
		$("#eduMthdName_"+sbjCd).hide();
		$("#eduMthdSelect_"+sbjCd).show();
		$("#eduMthdEditButton_"+sbjCd).hide();
		$("#eduMthdCancelButton_"+sbjCd).show();
	}

	function hideEditOfflineMthd(sbjCd) {
		$("#eduMthdSelect_"+sbjCd).hide();
		$("#eduMthdName_"+sbjCd).show();
		$("#eduMthdCancelButton_"+sbjCd).hide();
		$("#eduMthdEditButton_"+sbjCd).show();
	}

	function editOfflineMthd(sbjCd) {
		var eduMthd = $("#eduMthd_"+sbjCd+" option:selected").val();
		var eduMthdText = $("#eduMthd_"+sbjCd+" option:selected").text();

		$.getJSON( cUrl( "/mng/course/createCourse/subject/editOfflineSubject"),
					{  "crsCreCd": "${onlineSubjectVO.crsCreCd}", "offlineSubjectVO.sbjCd" : sbjCd, "eduMthdCd" : eduMthd },			// params
					function(data) {
						alert(data.message);
						if(data.result >= 0) {
							//-- 정상 처리
							$("#eduMthdName_"+sbjCd).html(eduMthdText);
							$("#eduMthdValue_"+sbjCd).val(eduMthd);
							hideEditOfflineMthd(sbjCd);
						} else {
			 	  			//-- 비정상 처리
			 	  		}
					}
				);
	}

	/**
	 * 오프라인 과목 삭제
	 */
	function delOffline(sbjCd) {
		if(confirm("<spring:message code="course.message.subject.confirm.delete"/>")) {
			$.getJSON( cUrl( "/mng/course/createCourse/subject/deleteOfflineSubject"),
					   {  "crsCreCd": "${onlineSubjectVO.crsCreCd}", "sbjCd" : sbjCd },			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert("<spring:message code="course.message.subject.delete.success"/>");
								listOffline();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert("<spring:message code="course.message.subject.delete.failed"/>");
				 	  		}
						}
					);
		} else return;
	}
	</c:if>

	function resizeForm() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}
	
	function sbjCntsReload(sbjCd){
		$("#contentsList_"+sbjCd).load(
    			cUrl("/mng/course/createCourse/subject/listContentsCreate"),
    			{  "crsCreCd": "${createCourseVO.crsCreCd}", "sbjCd" : sbjCd },
    		);
		
	}

</script>
