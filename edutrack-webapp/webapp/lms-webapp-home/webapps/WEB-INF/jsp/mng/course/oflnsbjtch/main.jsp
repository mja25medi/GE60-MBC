<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="oflnSbjTchVO" value="${oflnSbjTchVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff">
	<div class="row" style="padding-top:10px;">
		<div class="col-md-4" id="divSbjList" style="padding-top:30px;">

		</div>
		<div class="col-md-8">
			<div class="row">
				<div class="col-md-4">
					<div id="titleSbj" style="padding:5px 0px 5px 0px;">
						<b><spring:message code="course.title.subject.name"/> :</b>
					</div>
				</div>
				<div class="col-md-4 pull-right">
					<a href="#" onclick="addTeacher()" class="btn btn-sm btn-primary" style="float:right;"/><spring:message code="button.write.teacher"/></a>
				</div>
			</div>
			<div id="divTchList" style="width:100%">
				<ul class="list-group">
					<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
				</ul>
			</div>
			<div class="row" style="margin-top:20px;">
				<div class="col-md-4">
					<div id="titleSbj">
						<b><spring:message code="course.title.teacher.name"/> :</b>
					</div>
				</div>
				<div class="col-md-4 pull-right">
					<a href="#" onclick="addTime()" class="btn btn-sm btn-primary" style="float:right"><spring:message code="button.write.edu" /></a>
				</div>
			</div>
			<div id="divTimeList" style="width:100%">
				<ul class="list-group">
					<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
				</ul>
			</div>
		</div>
	</div>
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		listSbj();
	});

	function initDTO() {
		ItemDTO.sbjCd = "";
		ItemDTO.sbjNm = "";
		ItemDTO.declsNo = "";
		ItemDTO.userNo = "";
		ItemDTO.userNm = "";
	}

	function initTCH() {
		ItemDTO.declsNo = "";
		ItemDTO.userNo = "";
		ItemDTO.userNm = "";
	}

	function resizeForm() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	function listSbj() {
		initDTO();
		$("#divSbjList").load(cUrl("/mng/course/ofln/sbjTch/listSbj"),{"oflnSbjTchVO.crsCreCd":"${oflnSbjTchVO.crsCreCd}"});
	}

	function setSbj(sbjCd, sbjNm) {
		ItemDTO.sbjCd = sbjCd;
		ItemDTO.sbjNm = sbjNm;
		listTch();
	}

	function listTch()  {
		$("#titleSbj").html("<b><spring:message code="course.title.subject.name"/> : "+ItemDTO.sbjNm+"</b>");
		$("#divTchList").load(cUrl("/mng/course/ofln/sbjTch/listTch"),{"oflnSbjTchVO.crsCreCd":"${oflnSbjTchVO.crsCreCd}","oflnSbjTchVO.sbjCd":ItemDTO.sbjCd});
		initTCH();
	}

	function addTeacher() {
		if(ItemDTO.sbjCd == '') {
			alert('<spring:message code="course.message.subject.select"/>');
			return;
		}
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/ofln/sbjTch/addFormTch"/>"
			+ "?oflnSbjTchVO.crsCreCd=${oflnSbjTchVO.crsCreCd}&amp;oflnSbjTchVO.sbjCd="+ItemDTO.sbjCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(500, 360);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.teacher.write"/>");
		parent.modalBox.show();
	}

	function editTeacher(userNo, declsNo) {
		if(ItemDTO.sbjCd == '') {
			alert('<spring:message code="course.message.subject.select"/>');
			return;
		}
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/ofln/sbjTch/editFormTch"/>"
			+ "?oflnSbjTchVO.crsCreCd=${oflnSbjTchVO.crsCreCd}&amp;oflnSbjTchVO.sbjCd="+ItemDTO.sbjCd+"&amp;oflnSbjTchVO.userNo="+userNo+"&amp;oflnSbjTchVO.declsNo="+declsNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(500, 360);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.teacher.write"/>");
		parent.modalBox.show();
	}


	function setTch(declsNo, userNo, userNm) {
		ItemDTO.declsNo = declsNo;
		ItemDTO.userNo = userNo;
		ItemDTO.userNm = userNm;
		listTime();
	}

	function listTime() {
		$("#titleTch").html("<b>강사 : "+ItemDTO.userNm+"</b>");
		$("#divTimeList").load(cUrl("/mng/course/ofln/sbjTch/listTime"),{"oflnSbjTchTmVO.crsCreCd":"${oflnSbjTchVO.crsCreCd}","oflnSbjTchTmVO.sbjCd":ItemDTO.sbjCd,"oflnSbjTchTmVO.declsNo":ItemDTO.declsNo,"oflnSbjTchTmVO.userNo":ItemDTO.userNo});
	}

	function addTime() {
		if(ItemDTO.userNo == '') {
			alert('<spring:message code="course.message.teacher.select"/>');
			return;
		}
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/ofln/sbjTch/addFormTime"/>"
			+ "?oflnSbjTchTmVO.crsCreCd=${oflnSbjTchVO.crsCreCd}&amp;oflnSbjTchTmVO.sbjCd="+ItemDTO.sbjCd+"&amp;oflnSbjTchTmVO.declsNo="+ItemDTO.declsNo+"&amp;oflnSbjTchTmVO.userNo="+ItemDTO.userNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(500, 360);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.lecture.write"/>");
		parent.modalBox.show();
	}

	function editTime(lecSn) {
		if(ItemDTO.userNo == '') {
			alert('<spring:message code="course.message.teacher.select"/>');
			return;
		}
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/ofln/sbjTch/editFormTime"/>"
			+ "?oflnSbjTchTmVO.crsCreCd=${oflnSbjTchVO.crsCreCd}&amp;oflnSbjTchTmVO.sbjCd="+ItemDTO.sbjCd+"&amp;oflnSbjTchTmVO.declsNo="+ItemDTO.declsNo+"&amp;oflnSbjTchTmVO.userNo="+ItemDTO.userNo+"&amp;oflnSbjTchTmVO.lecSn="+lecSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(500, 360);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.lecture.edit"/>");
		parent.modalBox.show();
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>