<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsOnlnSbjVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<br/>
	<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'ON'}">
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="course.title.subject.manage"/></h5>
		</div>
		<div style="float:right">
			<button class="btn btn-primary btn-sm" onclick="onlineSearch()" ><spring:message code="button.write.subject"/></button>
		</div>
	</div>
	<div id="onlineList">
		<table summary="<spring:message code="course.title.subject.manage.online"/>" style="width:100%" class="table table-bordered wordbreak">
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
					<td colspan="5"><spring:message code="course.message.subject.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="margin-bottom:30px;"></div>
	</c:if>
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
x		<table summary="<spring:message code="course.title.subject.manage.offline"/>" style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:100px"/>
				<col style="width:auto"/>
				<col style="width:180px"/>
				<col style="width:140px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.code"/></th>
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
		<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'ON'}">
		listOnline();
		</c:if>
		<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'OF'}">
		listOffline();
		</c:if>
	});

	<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'ON'}">
	function listOnline() {
		$('#onlineList').load(cUrl("/mng/course/courseSubject/listOnlnSbj"), {"crsCd": "${crsOnlnSbjVO.crsCd}"});
	}

	function onlineSearch() {
		var addContent  = "<iframe id='addSubjectFrame' name='addSubjectFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/courseSubject/searchFormOnlnSbjPop"/>"
			+ "?crsCd=${crsOnlnSbjVO.crsCd}'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(620, 480);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.subject.write"/>");
		parent.modalBox.show();
	}

	function onlineSort(sortString) {
		$.getJSON( cUrl( "/mng/course/courseSubject/sortOnlnSbj"),
				{  "crsCd": "${crsOnlnSbjVO.crsCd}", "sbjCd" : sortString },			// params
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
		$("#studyMthd_"+sbjCd).val(studyMthd);

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

		$.getJSON( cUrl( "/mng/course/courseSubject/editOnlnSbj"),
					{  "crsCd": "${crsOnlnSbjVO.crsCd}", "sbjCd" : sbjCd, "studyMthd" : studyMthd },			// params
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
			$.getJSON( cUrl( "/mng/course/courseSubject/delOnlnSbj"),
					   {  "crsCd": "${crsOnlnSbjVO.crsCd}", "sbjCd" : sbjCd },			// params
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
	</c:if>

	<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'OF'}">
	function listOffline() {
		$('#offlineList').load(cUrl("/mng/course/courseSubject/listOflnSbj"), { "crsCd": "${crsOnlnSbjVO.crsCd}"});
	}

	function offlineSearch() {
		var addContent  = "<iframe id='addSubjectFrame' name='addSubjectFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/courseSubject/searchFormOflnSbjPop"/>"
			+ "?crsCd=${crsOnlnSbjVO.crsCd}'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(620, 480);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.subject.write"/>");
		parent.modalBox.show();
	}

	function offlineSort(sortString) {
		$.getJSON( cUrl( "/mng/course/courseSubject/sortOflnSbj"),
				{ "crsCd": "${crsOnlnSbjVO.crsCd}", "sbjCd" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listOffline();
		 	  		}
				}
			);
	}

	function showEditOfflineMthd(sbjCd) {
		var eduMthd = $("#eduMthdValue_"+sbjCd).val();
		$("#eduMthd_"+sbjCd).val(eduMthd); //-- select 값 변경

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

		$.getJSON( cUrl( "/mng/course/courseSubject/editOflnSbj"),
					{ "crsCd": "${crsOnlnSbjVO.crsCd}", "sbjCd" : sbjCd, "eduMthdCd" : eduMthd },			// params
					function(data) {
						alert(data.message);
						if(data.result >= 0) {
							//-- 정상 처리
							$("#eduMthdName_"+sbjCd).html(eduMthdText);
							$("#eduMthdValue_"+sbjCd).html(eduMthd);
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
			$.getJSON( cUrl( "/mng/course/courseSubject/delOflnSbj"),
					   {  "crsCd": "${crsOnlnSbjVO.crsCd}", "sbjCd" : sbjCd },			// params
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

</script>
</mhtml:frm_body>
</mhtml:mng_html>