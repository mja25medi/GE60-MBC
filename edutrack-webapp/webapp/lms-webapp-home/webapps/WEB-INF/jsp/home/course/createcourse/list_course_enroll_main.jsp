<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="createCourseVO" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="paging" value="Y"/>

	<c:if test="${not empty courseList }">
		<dl class="infoTag">
		    <div class="box  wrap">
		        <dt class="title"><b>환급선택</b></dt>
		        <dl class="mt10">
		            <a href="javascript:setRefundYn('')" class="btn on">전체</a>
		            <a href="javascript:setRefundYn('Y')" class="btn">환급</a>
		            <a href="javascript:setRefundYn('N')" class="btn">비환급</a>
		        </dl>
		    </div>
		    <div class="box wrap">
		        <dt class="title"><b>기수선택</b></dt>
		        <dl class="mt10">
		        	<c:forEach items="${courseList}" var="item" varStatus="status">
		        		<a href="javascript:setCrsCd('${item.crsCd }')" class="btn <c:if test="${status.index eq 0 }"> on</c:if>">${item.crsYear }년도 ${item.crsTerm }기수</a>
		        	</c:forEach>
		        </dl>
		    </div>
		</dl>
	</c:if>
	
	<form id="createCourseForm" name="createCourseForm" onsubmit="return false">
		<input type="hidden" name="sbjCtgrCd" id="sbjCtgrCd"/>
		<input type="hidden" name="refundYn" id="refundYn"/>
		<input type="hidden" name="crsCd" id="crsCd"/>
	</form>
	
	<div id="courseEnrollListArea">
	
	</div>
	
<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function(){
		/* modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		}); */

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				courseSearch(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			courseSearch(1);
		});
		
		$('.tabs li').click(function (e) {
			$(this).parent().children().removeClass('active');
			$(this).addClass("active");
		});
		
		$('.infoTag .box dl a').click(function (e) {
			$(this).parent().children().removeClass('on');
			$(this).addClass("on");
		});
		
		courseSearch(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/* function courseSearch(page) {
		$('#createCourseForm')
		.find('input[name=curPage]').val(page).end()
		.attr("action","/home/course/listSearchCourseMain")
		document.createCourseForm.submit();
	} */
	
	function courseSearch(page){
		if(isNull(page)){
			page = 1;
		}
		
		if(isNull($("#crsCd").val())){
			setCrsCd('${courseList[0].crsCd}');
			return;
		}
		
		$('#courseEnrollListArea').load(
				cUrl("/home/course/listCourseEnroll"),
				{ 
					"crsCd": $("#crsCd").val(),
					"curPage": page,
					"refundYn" : $("#refundYn").val()
				}
			);
	}

	/**
	 * 개설 과정 정보 보기
	 */
	function showCreCrs(crsCreCd) {
		var addContent  = "<iframe id='courseInfoFrame' name='courseInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/home/course/readCoursePop"/>"
			+ "?crsCreCd="+crsCreCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(700, 650);
		modalBox.setTitle("<spring:message code="course.title.course.info"/>");
		modalBox.show();
	}

	function addBasket(crsCreCd) {
		$.ajax({
			url : '/home/student/addBasket'
			,data : {
				'crsCreCd' : crsCreCd
			}
			, method: "POST" 
			,success : function(resultVO) {
				if(resultVO.result > 0){
					if(confirm("해당 강의를 수강신청 항목에 담았습니다.\n결제를 위해 수강신청결제 페이지로 이동하시겠습니까?")){
						location.href = "/home/main/goMenuPage?mcd=MC00000024";//수강신청		
						return;
					}
				}else{
					alert(resultVO.message);
					
				}
				courseSearch(1);
			}
			,error : function(request,status,error) {
				alert("수강신청 항목 담기에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
				courseSearch(1);
			}
		});
	}
	
	function removeBasket(crsCreCd) {
		$.ajax({
			url : '/home/student/removeBasket'
			,data : {
				'crsCreCd' : crsCreCd
			}
			,success : function(resultVO) {
				alert(resultVO.message);
				courseSearch(1);
			}
			,method: "POST"
			,error : function(request,status,error) {
				alert("수강신청 항목 삭제에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
				courseSearch(1);
			}
		});
	}
	
	function setRefundYn(refundYn){
		$("#refundYn").val(refundYn);
		courseSearch(1);
	}
	
	function setCrsCd(crsCd){
		$("#crsCd").val(crsCd);
		courseSearch(1);
	}
	
	function previewContents(sbjCd){
		$.ajax({
			url : '/home/course/subject/getFirstContents'
			,data : {
				'sbjCd' : sbjCd
			}
			,success : function(resultListVO) {
				var url = cUrl("/home/course/subject/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+resultListVO.unitCd;
				var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=auto,resizable=yes,width=1200,height=830";
				var contentsWin = window.open(url, "contentsWin", winOption);
				contentsWin.focus();
			}
			,error : function(request,status,error) {
			}
		});
	}
	
</script>
