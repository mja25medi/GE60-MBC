<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibMediaCntsList" value="${clibMediaCntsList}"/>
<div class="modal_cont">
        <div class="board_top type2">
            <div class="page_btn">
                <button type="button" class="btn type2" onclick="javascript:shareContentsWrite()">등록</button>
                <button type="button" class="btn" onclick="javascript:modalBoxClose()">닫기</button>
            </div>
        </div>
        <div class="row subject_pop">
         	<div class="col-sm">
                <div class="segment">
                    <ul class="class_tab"  id="tabMenuCnts">
                    	<li class="active" name = "tabBox" id="tabBox1"><a href="javascript:tabBox('1')">VOD</a></li>
                        <li id="tabBox2"  name = "tabBox"><a href="javascript:tabBox('2')">영상링크</a></li>
                    </ul>
                    <div class="scroll_custom" id="contentsList">
					</div>
                </div>
            </div>
		</div>
								
							
<script type="text/javascript">
var selectedCntsType = null;
var cntsType = "";

$(document).ready(function() {
	tabBox(1);
});
	/*
		탭 추가
	*/
	function tabBox(str) {
		if(str == '1'){
			cntsType = "VOD";
			selectedCntsType = "VOD";
		}else if( str == '2'){
			cntsType = "MLINK";
			selectedCntsType = "MLINK";
		}
		$("li[name=tabBox]").removeClass("active");
		$("#tabBox" + str).addClass("active");
		listPageing(1, cntsType);
	}
		
	/**
	* 콘텐츠 목록을 조회한다.
	*/
	function listPageing(page, cntsType) {
		if(cntsType == null){
			cntsType = selectedCntsType;
		}
		var ctgrCd = "";
		var url = cUrl("/lec/cnts/shareContentsList");
		$("#contentsList").empty();
		$("#contentsList").load(
				url, {
					"cntsType":cntsType
				}
		);
		
	}
	
	function modalBoxClose() {
		parent.modalBox.close();
	}
		
	/**
	* 콘텐츠 등록 폼
	*/
	function shareContentsWrite() {
		$("#contentsList").empty();
		$("#contentsList").load(cUrl("/lec/cnts/addShareContentsPop"),{"ctgrCd":'${vo.crsCreCd}', "ctgrNm":'${vo.crsCreNm}', "cntsType" : cntsType});
	}
		
	/**
	* 콘텐츠 수정 폼
	*/
	function shareContentsEdit(cntsCd) {
		$("#contentsList").empty();
		$("#contentsList").load(cUrl("/lec/cnts/editShareContentsPop"),{"cntsCd":cntsCd, "cntsType":cntsType});
	}
		
	
</script>
