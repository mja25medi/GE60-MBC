<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	.tab a {color: black;}
</style>

	<input type="hidden" id="userNo" value="${vo.userNo}">
	<input type="hidden" id="orgCd" value="${vo.orgCd}">
	
	<div id="userLeft" style="float:left;width:22%;height:900px">
	</div>
	<div id="userDiv" style="float:left;width:3px;height:920px;background:#f3f3f3;" >
	</div>

	<div id="userRight" style="float:left;width:75%;height:900px; margin-left: 10px;margin-top: 20px;">
		<div id="studentInfoTable">
		</div>
		
		<ul class="nav nav-tabs" id="tabMenuOper" style="margin-top:20px;">
  			<li id="basic" class="tab" ><a href="javascript:onclickTab(0)">기본정보</a></li>
  			<li class="tab"><a href="javascript:onclickTab(1)">수강이력</a></li>
  			<li class="tab"><a href="javascript:onclickTab(2)">성적정보</a></li>
  			<li class="tab"><a href="javascript:onclickTab(3)">접속이력</a></li>
  			<li class="tab"><a href="javascript:onclickTab(4)">인증이력</a></li>
  			<li class="tab"><a href="javascript:onclickTab(5)">상담이력</a></li>
  			<li class="tab"><a href="javascript:onclickTab(6)">쪽지내역</a></li>
  			<li class="tab"><a href="javascript:onclickTab(7)">SMS내역</a></li>
  			<li class="tab"><a href="javascript:onclickTab(8)">EMAIL내역</a></li>
  			<li class="tab"><a href="javascript:onclickTab(9)" >수강신청내역</a></li>
  			<li class="tab"><a href="javascript:onclickTab(10)">전화상담내역</a></li>
		</ul>
		
		<div id="listStudent">

		</div>
	</div>

<script type="text/javascript">
	
	var userNo = $("#userNo").val();
	var orgCd= $("#orgCd").val();
	var msgDivCd = null;
	
	// 페이지 초기화
	$(document).ready(function() {
		$("#basic").addClass('active');
		//-- 텝 초기 페이지 호출
		$('#tabMenuOper a').click(function (e) {
			$(this).tab('show');
		});
		onclickTab(0);
		listStudent(1);
		studentInfoTable(userNo,orgCd);
	});

	function onclickTab(tab){
 		var goUrl = ""; 
		
		if(tab == 0){//기본정보
			goUrl = "/mng/user/userInfo/viewUserPop";
			getUserInfoArea(goUrl); 
		}else if(tab == 1){//수강이력
			listStudy();
		}else if(tab == 2){//성적정보
			listStd(1);
		}else if(tab == 3){//접속이력
			listConn(1);
		}else if(tab == 4){//인증이력
			listCert(1);
		}else if(tab == 5){//상담이력
			listCnsl(1);
		}else if(tab == 6){//쪽지내역
			setMsgDivCd('MSG');
			listMsg(1);
		}else if(tab == 7){//SMS내역
			setMsgDivCd('SMS');
			listMsg(1);
		}else if(tab == 8){//EMAIL내역
			setMsgDivCd('EMAIL');
			listMsg(1);
		}else if(tab == 9){//수강신청내역
			listCourse(1);
		}else if(tab == 10){//전화상담내역
			listConsult(1);
		}
		
	}
	
	function getUserInfoArea(goUrl){
		var orgCd= $("#orgCd").val();
		var userNo = $("#userNo").val();
		
		$("#listStudent")
		.load(
			cUrl(goUrl),		
			{
				'userNo':userNo,
				'orgCd':orgCd
			}
		);
	}
	
	//수강생 정보
	function studentInfoTable(userNo,orgCd){
		var goUrl = "/mng/user/userInfo/studentInfoTablePop";
		$("#studentInfoTable")
		.load(
				cUrl(goUrl),		
				{
					'userNo':userNo,
					'orgCd':orgCd
				}
			);
	}

	// 좌측 회원리스트
 	function listStudent(page) {
		var userSts = "U";
		var listScale = 20;
		$('#userLeft')
			.load(cUrl("/mng/user/userInfo/userListPop"),
				{ 
			  		"userSts":userSts,
			  		"curPage":page,
			  		"listScale":listScale
				 }
			);
	}
	
	// 회원 페이징
	function listUserPageing(page) {
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var userSts = "U";
		var listScale = 20;
		$('#userLeft')
			.load(
				cUrl("/mng/user/userInfo/userListPop"),
				{ 
					"searchKey":searchKey,
				  	"searchValue":searchValue,
				  	"userSts":userSts,
				  	"curPage":page,
				  	"listScale":listScale
				}
			);
	} 
	
	function selectUser(userNo,orgCd){
		$("#orgCd").val(orgCd);
		$("#userNo").val(userNo);
		studentInfoTable(userNo,orgCd);
		onclickTab(0);
		$(".tab").removeClass('active');
		$("#basic").addClass('active');
		
	}

	//수강이력
	function listStudy(){
		var userNo = $("#userNo").val();
		var goUrl ="/mng/std/student/listStudyForCrm";
		var listScale = 15;
		
		$("#listStudent")
		.load(
			cUrl(goUrl),		
			{
				'userNo':userNo
			}
		);
	}
	
	//성적정보(수료증발급 포함)
	function listStd(page){
		var userNo = $("#userNo").val();
		var goUrl ="/mng/std/eduResult/listStdEduResultForCrm";
		var listScale = 20;
		
		$("#listStudent")
		.load(
			cUrl(goUrl),		
			{
				'userNo':userNo,
				"curPage":page,
				"listScale":listScale
			}
		);
	}
	
	//접속이력 
	function listConn(page) {
		var userNo = $("#userNo").val();
		var listScale = 20;
		var goUrl = "/mng/user/userInfo/connContentPop";
			
		$("#listStudent")
		.load(
			cUrl(goUrl),		
			{
				'userNo':userNo,
				"curPage":page,
				"listScale":listScale
			}
		);
	} 
	
	//인증이력 
	function listCert(page) {
		var userNo = $("#userNo").val();
		var goUrl = "/mng/user/userInfo/certContentPop";
		var listScale = 20;
		
		$("#listStudent")
		.load(
			cUrl(goUrl),		
			{
				'userNo':userNo,
				"curPage":page,
				"listScale":listScale
			}
		);
	}
	
	// 상담 이력 조회
	function listCnsl(page) {
		var userNo = $("#userNo").val();
		$("#listStudent").load(cUrl("/mng/user/userInfo/cnslListPop"),{
			"userNo" : userNo,
			"curPage" : page
		})
	}
	
	// 메시지 내역 조회
	function listMsg(page) {
		var userNo = $("#userNo").val();
		$("#listStudent").load(cUrl("/mng/log/message/crmMsgListPop"),
			{
				"userNo" : userNo,
				"msgDivCd" : msgDivCd,
				"curPage" : page
			}		
		);
	}
	
	// 메시지 구분 코드 세팅
	function setMsgDivCd(param) {
		msgDivCd = param;
	}
	
	function showCts(index) {
		var target = "tr[name=sub_"+index+"]";
		if($(target).hasClass("hide")){
			$(target).removeClass("hide");
		} else {
			$(target).addClass("hide");			
		}
	}
	
	//수강신청내역
	function listCourse(page){
		var userNo = $("#userNo").val();
		var goUrl ="/mng/user/userInfo/courseContentPop";
		var listScale = 20;
		
		$("#listStudent")
		.load(
			cUrl(goUrl),		
			{
				'userNo':userNo,
				"curPage":page,
				"listScale":listScale
			}
		);
	}
	
	//전화상담내역
	function listConsult(page){
		var userNo = $("#userNo").val();
		var goUrl ="/mng/user/userInfo/consultContentPop";
		var listScale = 15;
		
		$("#listStudent")
		.load(
			cUrl(goUrl),		
			{
				'userNo':userNo,
				"curPage":page,
				"listScale":listScale
			}
		);
	}
	
</script>
