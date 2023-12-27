<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>

<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}"/>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="prjTeamForm" name="prjTeamForm" onsubmit="return false">
 	<input type="hidden" name="crsCreCd" />
 	<input type="hidden" name="prjtSn" />
 	<input type="hidden" name="prjtTeamSn" />
		<div class="subhead">
			<div style="float:right">
				<meditag:button value="button.delete.team" title="button.delete.team" func="delTeam()" />
			</div>
			<div style="float:right">
				<meditag:button value="button.create.auto.team" title="button.create.auto.team" func="createTeam()" />
			</div>
			<div style="float:right">
				<meditag:button value="button.write.team" title="button.write.team" func="addNewTeam()" />
			</div>
		</div>
		<div id="teamList">
			<table summary="<spring:message code="lecture.title.project.team.manage"/>" style="width:100%" class="table_list">
				<colgroup>
					<col style="width:5%"/>
					<col style="width:5%"/>
					<col style="width:60%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:5%"/>
					<col style="width:5%"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="lecture.title.project.team.name"/></th>
						<th scope="col"><spring:message code="lecture.title.project.team.boss"/></th>
						<th scope="col"><spring:message code="lecture.title.project.team.membercnt"/></th>
						<th scope="col"><spring:message code="common.title.edit"/></th>
						<th scope="col"><spring:message code="common.title.manage"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><spring:message code="lecture.message.project.team.nodata"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
<script type="text/javascript">


	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjTeamVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjTeamVO.prjtSn}';

		//프로젝트 목록 조회
		listMember();
	});

	//프로젝트 목록 조회
	function listMember(){
		displayWorkProgress();
		$("#teamList")
			.load(
				cUrl("/mng/lecture/prjTeam/list"),		//url
				{
				  	"prjTeamVO.crsCreCd" : ItemDTO.crsCreCd,
				 	"prjTeamVO.prjtSn" : ItemDTO.prjtSn
				},											//parameter
				function() {
					parentReisze();
					closeWorkProgress();
				}
		);

	}

	//신규팀 등록
	function addNewTeam() {
		var addContent  = "<iframe id='addMemberFrame' name='addMemberFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/prjTeam/addForm"/>"
			+ "?prjTeamVO.crsCreCd="+ItemDTO.crsCreCd+""
			+ "&amp;prjTeamVO.prjtSn="+ItemDTO.prjtSn+"'/>";

			parent.parent.projectPopBox.clear();
			parent.parent.projectPopBox.addContents(addContent);
			parent.parent.projectPopBox.resize(400, 150);
			parent.parent.projectPopBox.setTitle("<spring:message code="lecture.title.project.team.write"/>");
			parent.parent.projectPopBox.show();
	}

	//팀 수정
	function editTeam(prjtTeamSn) {
		var addContent  = "<iframe id='editMemberFrame' name='editMemberFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/prjTeam/editForm"/>"
			+ "?prjTeamVO.crsCreCd="+ItemDTO.crsCreCd+""
			+ "&amp;prjTeamVO.prjtSn="+ItemDTO.prjtSn+""
			+ "&amp;prjTeamVO.prjtTeamSn="+prjtTeamSn+"'/>";

			parent.parent.projectPopBox.clear();
			parent.parent.projectPopBox.addContents(addContent);
			parent.parent.projectPopBox.resize(400, 150);
			parent.parent.projectPopBox.setTitle("<spring:message code="lecture.title.project.team.edit"/>");
			parent.parent.projectPopBox.show();
	}

	//팀자동생성
	function createTeam(){
		var addContent  = "<iframe id='createMemberFrame' name='createMemberFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/lecture/prjTeam/createForm"/>"
			+ "?prjTeamVO.crsCreCd="+ItemDTO.crsCreCd+""
			+ "&amp;prjTeamVO.prjtSn="+ItemDTO.prjtSn+"'/>";

			parent.parent.projectPopBox.clear();
			parent.parent.projectPopBox.addContents(addContent);
			parent.parent.projectPopBox.resize(600, 400);
			parent.parent.projectPopBox.setTitle("<spring:message code="lecture.title.project.team.autocreate"/>");
			parent.parent.projectPopBox.show();
	}

	//팀원관리
	function adminTeam(prjtTeamSn){
		var addContent  = "<iframe id='adminMemberFrame' name='adminMemberFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/LecturePrjMemberManage.do"/>"
			+ "?cmd=main&amp;prjMemberVO.crsCreCd="+ItemDTO.crsCreCd+""
			+ "&amp;prjMemberVO.prjtSn="+ItemDTO.prjtSn+""
			+ "&amp;prjMemberVO.prjtTeamSn="+prjtTeamSn+"'/>";

			parent.parent.projectPopBox.clear();
			parent.parent.projectPopBox.addContents(addContent);
			parent.parent.projectPopBox.resize(700, 500);
			parent.parent.projectPopBox.setTitle("<spring:message code="lecture.title.project.team.member.manage"/>");
			parent.parent.projectPopBox.show();
	}

	//checkbox 전체선택
	function checkAll() {
	    var state = $('input[name=chkAll]:checked').size();
	    if(state == 1){
	   		$("#prjTeamForm input[name='sel']").attr({checked:true});
	    }else{
	    	$("#prjTeamForm input[name='sel']").attr({checked:false});
	    }
	}

	//팀삭제
	function delTeam() {
		if(setTeam()) {
			if(confirm("<spring:message code="lecture.message.project.team.confirm.delete"/>")){
				process("remove");
			}
		}

	}

	function setTeam() {
		var chkTeam = "";
		$('input[name=sel]:checked').each(function() {
				chkTeam = chkTeam + "|" + $(this).val();
			}
		);
		chkTeam = chkTeam.substring(1);
		if(chkTeam == "") {
			alert("<spring:message code="lecture.message.project.team.alert.select"/>");
			return false;
		}
		document.prjTeamForm["prjTeamVO.prjtTeamSn"].value = chkTeam;
		return true;
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjTeamForm').attr("action" ,"/mng/lecture/prjTeam/" + cmd);
		$('#prjTeamForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			listMember();
		} else {
			// 비정상 처리
		}
	}

	function parentReisze(){
		var iframeObj = parent.document.getElementById("subWorkFrame2");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>





