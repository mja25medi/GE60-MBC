<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="projectVO" value="${vo}"/>
<c:set var="teamList" value="${teamList}"/>
<c:set var="bbsList" value="${bbsList}"/>
<mhtml:class_html>
<mhtml:class_head titleName="[${courseVO.crsNm}] ${projectVO.prjtTitle}">
	<meditag:js src="/js/common_iframe.js"/>
	<meditag:css href="css/home/pop.css" />
</mhtml:class_head>
<body>
<div class="wrap" style="width:953px;">
	<table border="0" cellpadding="0" cellspacing="0" style="width:100%;height:100%;">
		<tr>
			<td colspan="2" style="background:url('/lms/img/home/prjt_head_bg.jpg') no-repeat;width:953px;height:145px">
				<div style="padding:52px 50px 0px 230px;font-size:24px;">팀프로젝트 : ${projectVO.prjtTitle}</div>
				<div style="padding:47px 50px 0px 20px;">
					<form id="projectForm" name="projectForm" onsubmit="return false" action="/lec/project">
						팀선택 :
						<select name="strPrjtTeamSn" id="strPrjtTeamSn" onchange="javascript:frameUrl('1')" class="text">
							<c:forEach items="${teamList}" var="item">
								<option value="${item.prjtTeamSn}" ${selected}>${item.prjtTeamNm}</option>
							</c:forEach>
						</select>
					</form>
				</div>
			</td>
		</tr>
		<tr>
			<td style="width:150px;" valign="top">
				<ul id="lmenu">
					<li style="padding:5px 0px 0px 20px;width:130px;height:24px;background:url('/lms/img/home/prjt_menu_off_bg.gif') no-repeat;">
						<a href="javascript:frameUrl('0')">프로젝트 정보</a>
					</li>
					<li style="padding:5px 0px 0px 20px;width:130px;height:24px;background:url('/lms/img/home/prjt_menu_off_bg.gif') no-repeat;">
						<a href="javascript:frameUrl('1')">역할명세서</a>
					</li>
					<c:forEach items="${bbsList}" var="item" varStatus="status">
					<c:set var="cnt" value="${status.count+3}"/>
					<li style="padding:5px 0px 0px 20px;width:130px;height:24px;background:url('/lms/img/home/prjt_menu_off_bg.gif') no-repeat;">
						<a href="javascript:frameUrl('${cnt}','${item.bbsCd}')" name="bbsNm">${item.bbsNm}</a>
					</li>
					</c:forEach>
					<li style="padding:5px 0px 0px 20px;width:130px;height:24px;background:url('/lms/img/home/prjt_menu_off_bg.gif') no-repeat;">
						<a href="javascript:frameUrl('2')">게시판 관리</a>
					</li>
					<li style="padding:5px 0px 0px 20px;width:130px;height:24px;background:url('/lms/img/home/prjt_menu_off_bg.gif') no-repeat;">
						<a href="javascript:frameUrl('3')">팀 과제 제출</a>
					</li>
				</ul>
			</td>
			<td style="width:803px;padding:20px;">
				<iframe name="subWorkFrame2" id="subWorkFrame2" frameborder="0" src="about:blank" scrolling="no" title="하위 작업 프레임" style="width:763px;"></iframe>
			</td>
		</tr>


	</table>
</div>
<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectVO.prjtSn}';

		var bun = '${bun}';
		frameUrl(bun);
	});

	//프로젝트 정보
	function frameUrl(bun, bbsCd){
		var bbsNm = document.getElementsByName("bbsNm");
		var prjtTeamSn = $("#strPrjtTeamSn option:selected").val();
		var asmtSn = '${prjAssignmentVO.asmtSn}';
		var url = {};

 		url['0'] = "/lec/project/editFormPrjStudent?projectVO.prjtSn="+ItemDTO.prjtSn;
		url['1'] = "/lec/prj/member/listPrjMember?prjMemberVO.prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjMemberVO.prjtTeamSn="+prjtTeamSn;
 		url['2'] = "/lec/prj/bbs/listPrjBbs?prjBbsVO.prjtSn="+ItemDTO.prjtSn;

		if(bun == 3 && asmtSn == 0){
			alert("등록된 과제가 없습니다.");
			return;
		}else{
			url['3'] = "/lec/prj/assignment/addFormSend?prjAssignmentSendVO.prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjAssignmentSendVO.prjtTeamSn="+prjtTeamSn;
		}
		for(var i = 4; i <= bbsNm.length+3; i++){
			url[i] = "/lec/prj/bbsAtcl/listPrjBbsAtcl?prjBbsAtclVO.prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjBbsAtclVO.bbsCd="+bbsCd;
		}

		document.subWorkFrame2.location.href = cUrl(url[bun]);
		//selectUrl(bun);
	}

	function selectUrl(conName) {
		$("li").each(function () {
			$(this).removeClass("selected");
		});

		var tarObj = document.getElementById("con"+conName);
		tarObj.className = "selected";
	}

	function resizeTopIframe(dynheight) {
		//alert(dynheight);
		//$("#contents").css("height:"+parseInt(dynheight)+10);
       	document.getElementById("subWorkFrame2").height = parseInt(dynheight) + 10;
       //	$("#subWorkFrame2").attr("style", "height:"+dynheight);
  	}
</script>
</body>
</mhtml:class_html>