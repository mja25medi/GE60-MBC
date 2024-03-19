<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="assignmentListVO" value="${assignmentListVO}"/>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="cntChk" value="0"/>
<c:set var="startNo" value="0"/>
                <div class="learn_top m_column_row">
                    <div class="left_title">
                        <h3>과제관리</h3>
                    </div>
                    <div class="right_btn">
                        <button type="button" class="btn type4" onclick="assignmentAdd()"><spring:message code="button.write.assignment"/></button>
                    </div>
                </div>
				<c:forEach var="item" items="${assignmentListVO}" varStatus="status">
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                        	<c:if test="${item.asmtTypeCd eq 'ON'}">
                                    	<label class="online">온라인</label>
                            </c:if>
                            <c:if test="${item.asmtTypeCd eq 'OFF'}">
                                   		<label class="offline">오프라인</label>
                            </c:if>
                            
                            <h4>[<meditag:codename code="${item.asmtSvcCd}" category="ASMT_SVC_CD"/>] ${item.asmtTitle }</h4>
                        </div>
                    </div>
                    <c:set var="regYn" value="${item.regYn}"/>
					<c:if test="${empty item.regYn}"><c:set var="regYn" value="N"/></c:if>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                            	 <c:choose>
									<c:when test="${item.asmtSvcCd eq 'CODE' }"><li><strong><spring:message code="lecture.title.assignment.duration"/></strong>-</li></c:when>
									<c:when test="${item.asmtStareTypeCd eq 'S' }"></c:when>
									<c:otherwise><li><strong><spring:message code="lecture.title.assignment.duration"/></strong>${item.startDttm}~${item.endDttm}</li></c:otherwise>
								</c:choose>		
                                <c:if test="${item.asmtTypeCd eq 'ON'}">
                                <li><strong><spring:message code="lecture.title.assignment.seltype"/></strong><meditag:codename code="${item.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></li>
                                </c:if>
                                <li><strong><spring:message code="lecture.title.assignment.regyn"/></strong><meditag:codename code="${regYn}" category="REG_YN" /></li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="basic" onclick="assignmentEdit('${item.asmtSn}')">수정</button>
                                <button type="button" class="secondary" onclick="assignmentManage('${item.asmtSn}')">관리</button>
                            </div>
                        </div>
                    </div>
                </div>
				</c:forEach>
				<c:if test="${empty assignmentListVO}">
				<div class="segment">
                    <div class="learn_info">
                        <div class="header">
                             <h4><spring:message code="lecture.message.assignment.nodata"/></h4>
                        </div>
                    </div>
				</div>
				</c:if>


				<form id="assignmentForm" name="assignmentForm" onsubmit="return false">
					<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
					<input type="hidden" name="asmtSn" id="asmtSn" value="${vo.asmtSn}"/>
				</form>

<script type="text/javascript">
	// 팝업박스
	var modalBox = null;
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		$(".sub_title_2.ohddien").text("과제관리");
	});

	function asmtStatus(){
		var examNo = $("#asmtNo option:selected").val();
		$("#asmtSn").val(examNo);

	    var f = document.examForm;
		$('#assignmentForm').attr("action","/lec/assignment/asmtStatus");
		$('#assignmentForm').ajaxSubmit(processListStatusCallback);
	}

	function processListStatusCallback(dto) {
		
	}
/*  	function assignmentAdd(asmtSn){
			var crsCreCd = "${assignmentVO.crsCreCd}";
			var addContent  = "<iframe id='addAssignmentFrame' name='addAssignmentFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='yes' src='<c:url value="/lec/assignment/addAssignmentPop"/>"
				+ "?crsCreCd="+crsCreCd+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(800, 700);
			modalBox.setTitle("<spring:message code="lecture.title.assignment.write"/>");
			modalBox.show();
		}
 */ 
 	function assignmentAdd(asmtSn){
	 	location.href = cUrl("/lec/assignment/addAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}"; 

	}
 
	function assignmentEdit(asmtSn){
	 	location.href = cUrl("/lec/assignment/editAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}${AMPERSAND}asmtSn="+asmtSn; 
		/* var crsCreCd = "${assignmentVO.crsCreCd}";
		var addContent  = "<iframe id='editAssignmentFrame' name='addAssignmentFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/lec/assignment/editAssignmentPop"/>"
			+ "?crsCreCd="+crsCreCd+"&asmtSn="+asmtSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1500, 700);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.write"/>");
		modalBox.show(); */
	}
	function assignmentManage(asmtSn){
		location.href = cUrl("/lec/assignment/editAssignmentManageMain")+"?crsCreCd=${assignmentVO.crsCreCd}${AMPERSAND}asmtSn="+asmtSn;
	}

	function asmtInfo(asmtSn){
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/viewAssignmentPop"/>"
			+ "?asmtSn="+asmtSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 450);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.desc"/>");
		modalBox.show();
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
</script>
