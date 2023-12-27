<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
	<div class="learn_top">
                    <div class="left_title">
                        <h3>수강생관리</h3>
                    </div>
                </div>
                 <div class="segment">
	
			<div class="board_top">
                  <div class="page_btn flex-none">
                      <button type="button" class="btn" onclick="messageForm('SMS')">SMS</button>
                      <button type="button" class="btn" onclick="messageForm('EMAIL')">EMAIL</button>
                      <button type="button" class="btn"  onclick="messageForm('MSG')">쪽지</button>
                      <button type="button" class="btn" onclick="excelDownload()">엑셀다운로드</button>
                  </div>
                  <form class="board_search" onsubmit="return false" >
                      <fieldset class="form-row">
                      <legend class="blind">게시판 검색</legend>
                      <input type="text" class="form-control _enterBind" id="inputUser" name="userNm" placeholder="이름 또는 ID로 검색하세요." title="검색어를 입력하세요" value="">
                      <button type="submit" class="btn type2" onclick="listStd(1);">검색</button>
                      </fieldset>
                  </form>
              </div>
			
		<div id="loadDiv"></div>
		
		</div>
		
<script type="text/javascript">
	var modalBox = null;
	$(document).ready(function() {
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listStd(1);
			}
		});
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1",
			"nomargin" : false //m_large
		});
		listStd(1);
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function listStd(page) {
		$("#loadDiv").load(cUrl("/lec/std/listStd"),{
			"crsCreCd" : $("#crsCreCd").val(),
			"userNm" : $("#inputUser").val(),
			"curPage" : page
		});
	}
	
	function messageForm(msgDivCd) {
		var userList = "";
		$("input[name='chkStd']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
				userList += $(this).val();
			}
		});
		if(userList == "") {
			alert("발송대상자가 없습니다.\n발송 대상자를 체크 해주세요.");
			return;
		}
		viewMsgPop(msgDivCd, userList);
	}
	
	function viewMsgPop(msgDivCd, userList) {
		var msgType;
		if(msgDivCd == 'SMS') msgType = 'SMS';
		else if(msgDivCd == "MSG") msgType = '쪽지';
		else msgType = "메일";
		var url = generateUrl("/lec/message/messageWritePop",{ "msgDivCd":msgDivCd, "logMsgTransLogVO.userNoList":userList});
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(900, 700);
		modalBox.setTitle(msgType + " 보내기");
		modalBox.show();
	}
	
	function viewEduResultPop(stdNo) {
		var crsCreCd = $("#crsCreCd").val();
		var url = generateUrl("/lec/eduResult/viewResultPop",{ "stdNo": stdNo , "crsCreCd" : crsCreCd });
		var addContent  = "<iframe id='eduResultFrame' name='eduResultFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 520);
		modalBox.setTitle("성적 조회");
		modalBox.show();
	}
	
	function excelDownload() {
		var crsCreCd = $("#crsCreCd").val();
		var userNm = $("#inputUser").val();
		var url = cUrl("/lec/eduResult/listExcelDownloadForMngStd")+"?crsCreCd="+crsCreCd + "&userNm=" + userNm;
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		$('#_m_download_iframe').attr('src', url);
	}
</script>
