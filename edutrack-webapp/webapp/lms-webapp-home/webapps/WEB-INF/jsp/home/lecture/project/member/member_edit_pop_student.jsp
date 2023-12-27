<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjMemberVO" value="${vo}"/>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>
<c:set var="projectVO" value="${projectVO}"/>
<c:set var="mbrList" value="${mbrList}"/>
<mhtml:class_html>
<mhtml:class_head>
</mhtml:class_head>
<mhtml:body>
	<form id="prjMemberForm" name="prjMemberForm" onsubmit="return false" >
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
		<input type="hidden" name="prjtTeamSn" value="${vo.prjtTeamSn}" />
		<input type="hidden" name="prjtMbrSn" value="${vo.prjtMbrSn}" />
		<input type="hidden" name="mbrRole" value="${vo.mbrRole}" />
	</form>
	<table class="board_b">
		<tr>
			<th scope="row">팀 장</th>
			<td colspan="3" colspan="3">
				${prjTeamVO.teamLeader}(${prjTeamVO.leaderId})
			</td>
		</tr>
	</table>
	<br/>
	<form name="StdSearch" id="StdSearch" onsubmit="return false">
	<table class="board_a">
		<colgroup>
			<col style="width:60px;"/>
			<col style="width:80px;"/>
			<col style="width:80px;"/>
			<col style="width:auto;"/>
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="전체 선택"/></th>
			<th scope="col">이름 </th>
			<th scope="col">아이디</th>
			<th scope="col">역할 정의</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${mbrList}" var="item" varStatus="status">
			<%-- <input type="hidden" name="prjtMbrSn" value="${item.prjtMbrSn}"/> --%>
			<tr style="height: 40px;">
				<td><input type="checkbox" name="sel" id="sel" value="${item.prjtMbrSn}" style="border:0" title="선택"/></td>
				<td>${item.stdNm}</td>
				<td>${item.stdId}</td>
				<td>
					<c:choose>
						<c:when test="${projectVO.teamLeaderDiv eq 'Y'}">
							<c:set var="readonly" value=""/>
						</c:when>
						<c:otherwise>
							<c:set var="readonly" value="readonly='readonly'"/>
						</c:otherwise>
					</c:choose>
					<textarea name="mbrRole" class="conts tm10" style="width: 97%; height: 35px; overflow-y: scroll;" ${readonly}>${item.mbrRole}</textarea>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty mbrList}">
			<tr>
				<td colspan='7' align='center'><font color=blud>* 검색된 팀원 목록이 없습니다.</font></td>
			</tr>
		</c:if>
		</tbody>
	</table>
	</form>
	<c:if test="${projectVO.teamLeaderDiv eq 'Y'}">
		<div class="btn_right">
			<a href="javascript:editMbr()" class="btn02">수정</a>
		</div>
	</c:if>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjBbsVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjBbsVO.prjtSn}';

		$('#chkAll').click(function(){
		    var state=$('input[name=chkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#StdSearch input[name='sel']").attr({checked:true});
		    }else{
		    	$(document).find("#StdSearch input[name='sel']").attr({checked:false});
		    }
		});

		callResize();

	});

	//역할명세서 수정
	/* function editMbr() {
		if(setMbr()) {
			process("editPrjMemberStu");
		}

	} */

	function editMbr() {
		var prjMbrSnObj = document.getElementsByName("sel");
		var mbrRoleObj = document.getElementsByName("mbrRole");
		var f = document.prjMemberForm;

		var mbrNo = "";
		var mbrRole = "";

		if($('input[name=sel]:checked')){
			for(var i=0; i < prjMbrSnObj.length; i++){
				mbrNo = mbrNo + "|" + prjMbrSnObj[i].value;
				mbrRole = mbrRole + "|" + mbrRoleObj[i].value;

			}
		}

		mbrNo = mbrNo.substring(1);
		mbrRole = mbrRole.substring(1);

		if(mbrNo == "") {
			alert("팀원을 선택해 주세요.");
			return;
		}
		f["prjtMbrSn"].value = mbrNo;
		f["mbrRole"].value = mbrRole;

		process("editPrjMemberStu");
		//return true;
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjMemberForm').attr("action","/lec/prj/member"+cmd);
		$('#prjMemberForm').submit();
	}

	function callResize() {
        var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}

</script>
</mhtml:body>
</mhtml:class_html>