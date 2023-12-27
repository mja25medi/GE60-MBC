<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsTermVO" value="${vo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="rowLayer" id="listLayer">
						<div class="col-md-12 text-right">
							<button class="btn btn-primary btn-sm" onclick="addCretermForm()">기수등록</button>
						</div>					
						<div class="col-md-12" style="margin-top:5px;">
							<div id="cretermList">
							</div>
						</div>
					</div>
					<div class="rowLayer" id="workLayer">
						<div class="col-md-12" id="workBody">
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
</section>			

<script type="text/javascript">

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		listCreterm();
	});


	/**
	 * 기수 관리 목록 조회
	 */
	function listCreterm(page) {
		$('#cretermList')
			.load(cUrl("/mng/course/creterm/listCreterm"),
				{ 
				  "curPage":1,
				  "listScale":20
			    }
			);
	}
	
	/**
	 * 기수 관리 정보
	 */	
	function cretermInfoForm(crsTermCd) {
		var url = cUrl("/mng/course/creterm/cretermInfo");
		$("#workBody")
			.load(url, {
				"crsTermCd":crsTermCd
			});
		showArea('work');
	}
	
	function showArea(str) {
		$(".rowLayer").hide();
		$("#"+str+"Layer").show();
	}
	
	function closeWriteArea(){
		$("#workBody").html("");
		listCreterm();
		showArea('list');
	}
	
	function addCretermForm(){
		var url = cUrl("/mng/course/creterm/addFormCreterm");
		$("#workBody")
			.load(url, {
			});
		showArea('work');		
	}
	
	function editCretermForm(crsTermCd){
		var url = cUrl("/mng/course/creterm/editFormCreterm");
		$("#workBody")
			.load(url, {
				"crsTermCd" : crsTermCd 
			});
		showArea('work');		
	}

	function addCreateCourseForm(){
		var url = cUrl("/mng/course/creterm/addFormCreateCourse");
		$("#workBody")
			.load(url, {
				"crsCtgrCd" : 1 ,
				"crsOperMthd" : 2
			});
		showArea('work');		
	}
	
</script>
