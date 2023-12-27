<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<form name="Search" id="Search" onsubmit="return false" class="form-inline">
					<div class="col-md-6 col-sm-6">
						<div class="input-group" style="width:360px;">
							<select name="usageCd" id="usageCd" class="form-control input-sm" style="float:left;width: 50%;">
								<option value="">용도를 선택하세요.</option>
								<option value="01">강의실</option>
								<option value="02">회의실</option>
								<option value="03">실습실</option>
							</select>
							<select name="useYn" id="useYn" class="form-control input-sm" style="float:left;width: 50%;">
								<option value="">상태를 선택하세요.</option>
								<option value="Y">사용가능</option>
								<option value="N">사용불가</option>
							</select>
							<span class="input-group-addon" onclick="listLecRoom()" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
 				<div class="col-md-6 col-sm-6 text-right">
					<a href="javascript:lecRoomWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write.lecroom"/> </a>
					<select name="listScale" id="listScale" onchange="listLecRoom(1)" class="form-control input-sm">
						<option value="10">10</option>
						<option value="20" selected="selected">20</option>
						<option value="40">40</option>
						<option value="60">60</option>
						<option value="80">80</option>
						<option value="100">100</option>
						<option value="200">200</option>
					</select>
				</div>
				</form>
				<form name="lecRoomSnForm" id="lecRoomSnForm" onsubmit="return false" class="form-inline">
					<input type="hidden" id="lecRoomSn" name="lecRoomSn" value=""/>
				</form>
				<div class="col-md-12" style="margin-top:5px;">
					<div id="lecRoomList" >
						<table summary="<spring:message code="course.title.subject.category.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:110px"/>
								<col style="width:250px"/>
								<col style="width:300px"/>
								<col style="width:300px;"/>
								<col style="width:100px"/>
								<col style="width:100px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.title.subject.code"/></th>
									<th scope="col"><spring:message code="course.title.subject.category"/></th>
									<th scope="col"><spring:message code="course.title.subject.name"/></th>
									<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
									<th scope="col"><spring:message code="common.title.stats"/></th>
									<th scope="col"><spring:message code="course.title.subject.contents.manage"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="9"><spring:message code="course.message.subject.select.category"/></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>		
<script type="text/javascript">
	// 팝업박스
	var modalBox = null;

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		listLecRoom();
		
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listLecRoom();
			}
		}
	});//ready

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 과목 목록 조회
	 */
	function listLecRoom(page) {

		var listScale = $("#listScale option:selected").val();
		var usageCd = $("#usageCd option:selected").val();
		var useYn = $("#useYn option:selected").val();
		
		
		$('#lecRoomList')
			.load(
				cUrl("/mng/course/subject/listLecRoom"),
				{ 
				   "usageCd":usageCd,
				   "useYn":useYn,
				  "curPage":page,
				  "listScale":listScale
				}
			);
	}

	/**
	 * 과목 등록 폼
	 */
	function lecRoomWrite() {
		var addContent = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/subject/addFormLecRoomPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 750);
		modalBox.setTitle("<spring:message code="course.title.lecroom.write"/>");
		modalBox.show();
	}

	/**
	 * 과목 수정 폼
	 */
	 function lecRoomEdit(lecRoomSn) {
		var addContent = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/subject/editFormLecRoomPop"/>"
			+ "?lecRoomSn="+lecRoomSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 800);
		modalBox.setTitle("<spring:message code="course.title.subject.edit"/>");
		modalBox.show();
	}
	
	/**
	 * 강의실 삭제
	 */
	function delLecRoom(sn) {

		//과정에서 사용중인 강의실 체크 조건 추후 추가
/* 		if(){
			alert("<spring:message code="course.message.lecroom.alert.delete1"/>");
			return;
		} */

		if(confirm('<spring:message code="course.message.lecroom.confirm.delete"/>')) {
			process("deleteLecRoom",sn);	// cmd
		} else {
			return;
		}
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd,sn) {
		
		$('#lecRoomSn').val(sn);
		$('#lecRoomSnForm').attr("action","/mng/course/subject/" + cmd);
		$('#lecRoomSnForm').attr("method","post");
		$('#lecRoomSnForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listLecRoom(1);
		} else {
		}
	}

</script>
