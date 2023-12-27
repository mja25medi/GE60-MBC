<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjMemberVO" value="${prjMemberVO}"/>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>
<c:set var="teamList" value="${teamList}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="팀원 관리">
	<meditag:js src="/js/selectbox.js"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<br>
<form id="prjMemberForm" name="prjMemberForm" onsubmit="return false" action="/LecturePrjMemberAdmin.do"/>
	<input type="hidden" name="cmd" />
	<input type="hidden" name="crsCreCd"/>
 	<input type="hidden" name="prjtSn"/>
 	<input type="hidden" name="prjtTeamSn"/>
 	<input type="hidden" name="prjtMbrSn"/>
 	<input type="hidden" name="stdNo"/>
 	<input type="hidden" name="teamLeaderDiv"/>
	<table summary="팀 등록" style="width:96%" class="table_dtl" align="center">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row">팀 명</th>
			<td class="top" colspan="3">
				<meditag:selectbox width="300" height="150" fieldName="prjTeamVO.prjtTeamSn" onChange="listMember()" formName="prjMemberForm" value="${prjTeamVO.prjtTeamSn}"/>
				<c:forEach items="${teamList}" var="list">
					<meditag:selectboxOption formName="prjMemberForm" fieldName="prjTeamVO.prjtTeamSn" value="${list.prjtTeamSn}" text="${list.prjtTeamNm}"/>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row">팀 장</th>
			<td colspan="3" colspan="3">
				${prjTeamVO.teamLeader}(${prjTeamVO.leaderId})
			</td>
		</tr>
	</table>
	<div style="padding: 15px;">
		팀원정보
	</div>
	<div style="padding: 0px 15px 0px 15px">
		<div style="width: 300px; height:250px; float: left; border: 1px solid; border-color: #DADADA; overflow: auto;">
			<table summary="팀원 목록" style="width:100%;" class="table_list" align="center">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:45%"/>
					<col style="width:45%"/>
				</colgroup>
				<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="chkAll1" id="chkAll1" value="N" style="border:0" title="전체 선택" onclick="checkAll()"/></th>
					<th scope="col">이름</th>
					<th scope="col">아이디</th>
				</tr>
				</thead>
				<tbody id="teamList"></tbody>
			</table>
		</div>
		<div style="float: left; width:50px; padding: 100px 0px 0px 5px;">
			<table style="width:100%">
				<tr>
					<td>
						<meditag:buttonwrapper>
							<meditag:button value="▷" title="▷" func="addTeamMbr()" /><br><br>
							<meditag:button value="◁" title="◁" func="delTeamMbr()" />
						</meditag:buttonwrapper>
					</td>
				</tr>
			</table>
		</div>
		<div style="width: 300px; height:250px; float: right; border: 1px solid; border-color: #DADADA; overflow: auto;">
			<table summary="팀원 목록" style="width:100%;" class="table_list" align="center">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:30%"/>
					<col style="width:30%"/>
					<col style="width:30%"/>
				</colgroup>
				<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="chkAll2" id="chkAll2" value="N" style="border:0" title="전체 선택" onclick="checkAll()"/></th>
					<th scope="col">이름</th>
					<th scope="col">아이디</th>
					<th scope="col">팀장선정</th>
				</tr>
				</thead>
				<tbody id="teamList2"></tbody>
			</table>
		</div>
	</div>
	<table style="width:96%">
		<tr>
			<td>
				<meditag:buttonwrapper>
					<meditag:button value="닫기" title="닫기" func="parent.projectPopBox.close();" />
				</meditag:buttonwrapper>
			</td>
		</tr>
	</table>

	</form>
<script type="text/javascript">

	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjMemberVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjMemberVO.prjtSn}';
		ItemDTO.prjtTeamSn = '${prjMemberVO.prjtTeamSn}';
		//팀원 정보 조회
		listMember();
	});

	//팀원 정보 조회
	function listMember(){
		var prjtTeamSn = prjMemberFormprjTeamVO_prjtTeamSn.getSelectedValue();
		$.getJSON(cUrl("/LecturePrjMemberAdmin.do"),		//url
				{ "cmd" : "stdList",
				  "prjMemberVO.crsCreCd" : ItemDTO.crsCreCd,
				  "prjMemberVO.prjtSn" : ItemDTO.prjtSn,
				  "prjMemberVO.prjtTeamSn" : prjtTeamSn
				},											//parameter
				listMemberCallback							//callback function
		);
		$.getJSON(cUrl("/LecturePrjMemberAdmin.do"),		//url
				{ "cmd" : "list",
				  "prjMemberVO.crsCreCd" : ItemDTO.crsCreCd,
				  "prjMemberVO.prjtSn" : ItemDTO.prjtSn,
				  "prjMemberVO.prjtTeamSn" : prjtTeamSn
				},											//parameter
				listMemberTeamCallback						//callback function
		);

		displayWorkProgress();
	}

	//팀원 정보 조회Callback
	function listMemberCallback(ProcessResultListDTO){
		var retList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='3' align='center'><font color=blud>* 팀원 목록이  없습니다.</font></td></tr>\n";
		}

		var cnt = 0;
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			listStr +="	<tr>\n";
			listStr +="		<td><input type='checkbox' name='sel1' id='sel1' value='"+item.stdNo+"' style='border:0' title='선택'></td>\n";
			listStr +="		<td>"+item.stdNm+"</td>\n";
			listStr +="		<td>"+item.stdId+"</td>\n";
			listStr +="	</tr>\n";
		}

		$("#teamList").html(listStr);

		closeWorkProgress();
	}


	//프로젝트 목록Callback
	function listMemberTeamCallback(ProcessResultListDTO){
		var retList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='4' align='center'><font color=blud>* 팀원 목록이  없습니다.</font></td></tr>\n";
		}

		var cnt = 0;
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			listStr +="	<tr>\n";
			listStr +="		<td><input type='checkbox' name='sel2' id='sel2' value='"+item.prjtMbrSn+"' style='border:0'></td>\n";
			listStr +="		<td>"+item.stdNm+"</td>\n";
			listStr +="		<td>"+item.stdId+"</td>\n";
			if(item.teamLeaderDiv == 'Y'){
				listStr +="	<td><input type='radio' name='leader' id='leader' onclick='editTeam(\""+item.prjtTeamSn+"\")' value='"+item.prjtMbrSn+"' style='border:0' checked='checked'></td>\n";
			}else{
				listStr +="	<td><input type='radio' name='leader' id='leader' onclick='editTeam(\""+item.prjtTeamSn+"\")' value='"+item.prjtMbrSn+"' style='border:0'></td>\n";
			}
			listStr +="	</tr>\n";
		}

		$("#teamList2").html(listStr);

		closeWorkProgress();
	}

	//checkbox 전체선택
	function checkAll() {
	    var state1 = $('input[name=chkAll1]:checked').size();
	    var state2 = $('input[name=chkAll2]:checked').size();
	    if(state1 == 1){
	   		$("#prjMemberForm input[name='sel1']").attr({checked:true});
	    }else{
	    	$("#prjMemberForm input[name='sel1']").attr({checked:false});
	    }
	    if(state2 == 1){
	   		$("#prjMemberForm input[name='sel2']").attr({checked:true});
	    }else{
	    	$("#prjMemberForm input[name='sel2']").attr({checked:false});
	    }
	}

	//팀원 등록
	function addTeamMbr() {
		if(addTeam()) {
			process("add");
		}

	}

	function addTeam() {
		var stdNo = "";
		$('input[name=sel1]:checked').each(function() {
			stdNo = stdNo + "|" + $(this).val();
			}
		);
		stdNo = stdNo.substring(1);
		if(stdNo == "") {
			alert("팀원을 선택해 주세요.");
			return false;
		}
		document.prjMemberForm["prjMemberVO.stdNo"].value = stdNo;

		return true;
	}

	//팀장 선정
	function editTeam(prjtTeamSn) {
		var chk = $("input[name=leader]:checked").val();

		document.prjMemberForm["prjMemberVO.prjtTeamSn"].value = prjtTeamSn;
		document.prjMemberForm["prjMemberVO.prjtMbrSn"].value = chk;
		document.prjMemberForm["prjMemberVO.teamLeaderDiv"].value = "Y";

		process("edit");
	}


	//팀원 삭제
	function delTeamMbr() {
		if(delTeam()) {
			process("remove");
		}

	}

	function delTeam() {
		var mbrNo = "";
		$('input[name=sel2]:checked').each(function() {
			mbrNo = mbrNo + "|" + $(this).val();
			}
		);
		mbrNo = mbrNo.substring(1);
		if(mbrNo == "") {
			alert("팀원을 선택해 주세요.");
			return false;
		}
		document.prjMemberForm["prjMemberVO.prjtMbrSn"].value = mbrNo;

		return true;
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjMemberForm').find('input[name=cmd]').val(cmd);
		$('#prjMemberForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.href = "/LecturePrjMemberAdmin.do?cmd=main${AMPERSAND}prjMemberVO.crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjMemberVO.prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjMemberVO.prjtTeamSn="+ItemDTO.prjtTeamSn;
			listMember();
			parent.subWorkFrame.subWorkFrame2.listMember();
		} else {
			// 비정상 처리
		}
	}
</script>
	</mhtml:frm_body>
</mhtml:mng_html>