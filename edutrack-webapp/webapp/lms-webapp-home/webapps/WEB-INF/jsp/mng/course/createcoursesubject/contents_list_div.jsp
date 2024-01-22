<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="createCourseVO" value="${createCourseVO}"/>
<c:set var="contentsList" value="${contentsList}"/>
<c:set var="createOnlineSubjectVO" value="${createOnlineSubjectVO}"/>
		<h5 class="text-left"><i class="fa fa-check" aria-hidden="true"></i>학습 목차 설정</h5>
		 <div class="subject-list mt5" style=" padding:10px; border:1px solid #e9e9e9;">
	                        <c:if test="${sbjType eq 'OF'}">
	                        	<h5 class="text-left"><i class="fa fa-check" aria-hidden="true"></i>오프라인 일정 설정</h5>
	                        </c:if>
	                        <table class="table table-bordered wordbreak" style="margin-top: 5px;" id="stuSearchTable">
	                            <colgroup>
	                                <col style="width:30%">
	                                <col style="width:70%">
	                            </colgroup>
	                            <tbody>
	                          <c:if test="${sbjType eq 'OF'}">
	                            <tr>                           
	                                <th scope="row"><span style="color:red;">* </span> 강사선택 ${createOnlineSubjectVO.sbjType}</th>
	                                <td class="text-left">
	                                    <select class="form-control input-sm mr10" id="teacher1" title="강사 선택" style="width:20%; display: inline-block;">
	                                       <option value="">선택</option>
	                                       <c:forEach items="${TeacherList}" var="item" varStatus="status">
	                                       			<option value="${item.userNo}" <c:if test="${teacher1.userNo eq item.userNo}">selected</c:if> >${item.userNm } </option>
	                                       </c:forEach>
	                                    </select>
	                                    <select class="form-control input-sm mr10" id="teacher2" title="강사 선택" style="width:20%; display: inline-block;">
	                                        <option value="">선택</option>
	                                       	<c:forEach items="${TeacherList}" var="item" varStatus="status" >
		                                    	<option value="${item.userNo}" <c:if test="${teacher2.userNo eq item.userNo}">selected</c:if>>${item.userNm }</option>
		                                    </c:forEach>
	                                    </select>
	                             
	                              
	                                    <span style="color:red; margin-left: 10px;">* </span>멘토선택
	                                    <select class="form-control input-sm mr10" id="tutor" title="멘토 선택" style="width:20%; display: inline-block;">
	                                        <option value="">선택</option>
	                                        <c:forEach items="${TutorList}" var="item" varStatus="status">
		                                    <option value="${item.userNo}" <c:if test="${tutor.userNo eq item.userNo}">selected</c:if> >${item.userNm }</option>
		                                    </c:forEach>
	                                    </select>
	                                    <a href="#0" class="btn btn-primary btn-sm" onClick="addTeacher()">적용</a>
	                                </td>
	                            </tr>                       
	                            <tr>
	                                <th scope="row"><span style="color:red;">* </span> 강의실</th>
	                                <td class="text-left">
	                                    <input type="text" style="width:50%" disabled class="form-control input-sm" value="${createCourseVO.oflnEduPlace }">
	                                </td>
	                            </tr>
	                            <tr>
	                                <th><span style="color:red;">* </span> 교육일정</th>
	                                <td>
	                                    <div class="input-group" style="float:left;width:128px;">
	                                        <input type="text" maxlength="10" name="enrlStartDttm_0" id="enrlStartDttm_0" class="inputDate form-control input-sm" value="${createCourseVO.enrlStartDttm }"/>
	                                        <span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm_0')" style="cursor:pointer">
	                                            <i class="fa fa-calendar"></i>
	                                        </span>
	                                    </div>
	                                    <div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
	                                    <div class="input-group" style="float:left;width:128px;">
	                                        <input type="text" maxlength="10" name="enrlEndDttm_0" id="enrlEndDttm_0" class="inputDate form-control input-sm"  value="${createCourseVO.enrlEndDttm }"/>
	                                        <span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm_0')" style="cursor:pointer">
	                                            <i class="fa fa-calendar"></i>
	                                        </span>
	                                    </div>
	                                </td>
	                            </tr>
                           </tbody>
                         </table>
                   </c:if>
                   <div class="panel-heading">
						<%-- <font color="red"><spring:message code="course.title.content.help.message"/></font> --%>
						<div class="pull-right">
							<a href="javascript:cntsWrite('1','')" class="btn btn-default btn-xs">차시 추가</a>
							<a href="javascript:cntsSort()" class="btn btn-default btn-xs">순서 변경</a>
						</div>
					</div>
                   <form id="contentsForm" name="contentsForm" class="form-inline" onsubmit="return false">
                        <div class="table-responsive" >
                            <table summary="게시물목록" style="margin-top:5px;" class="table table-bordered" id="stuPayTable">
                            
                                <colgroup>
                                    <col style="width:auto"/>
                                    <col style="width:auto"/>
                                    <c:if test="${sbjType eq 'OF'}">
	                                    <col style="width:auto;"/>
	                                    <col style="width:360px;"/>
                                    </c:if>
                                    <col style="width:auto"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th scope="col">목차</th>
                                    <th scope="col">목차명</th>
                                    <c:if test="${sbjType eq 'OF'}">
                                    	<th scope="col">강사</th>
                                    	<th scope="col">시간표</th>
                                    </c:if>
                                    <th scope="col">설정</th>
                                </tr>
                                </thead>
                                <c:forEach items="${contentsList}" var="item" varStatus="status">
                                <tbody>
                                <tr>
                                    <td>${item.unitOdr}차시</td>
                                    <td class="text-left">
                                        <input type="hidden" style="width:80%; display:inline-block;" id="unitNm" name="subList[${status.index}].unitNm" class="form-control input-sm" value="${item.unitNm}">
                                        ${item.unitNm}
                                        <c:choose>
                                        	<c:when test="${item.cntsTypeCd eq 'CDN' }"><i class="fa fa-cloud ml5" title="CDN"></i></c:when>
                                        	<c:when test="${item.cntsTypeCd eq 'VOD' }"><i class="fa fa-play-circle ml5" title="VOD"></i></c:when>
                                        	<c:when test="${item.cntsTypeCd eq 'LECTURE' }"><i class="fa fa-desktop ml5" title="코딩강의"></i></c:when>
                                        	<c:when test="${item.cntsTypeCd eq 'PRACTICE' }"><i class="fa fa-code ml5" title="코딩실습"></i></c:when>
                                        </c:choose>
                                    </td>
                                    <c:if test="${sbjType eq 'OF'}">
	                                    <td>
	                                        <select class="form-control input-sm" name="subList[${status.index}].teacherNo" style="width:80%">
	                                           <option value="">선택</option>
		                                       <c:forEach items="${TeacherList}" var="teacherList" varStatus="1">
		                                       		<option value="${teacherList.userNo}"<c:if test="${item.teacherNo eq teacherList.userNo}">selected</c:if> >${teacherList.userNm }</option>
		                                       </c:forEach>
	                                        </select>
	                                    </td>
	                                    <td>
	                                        <div class="input-group" style="float:left;width:128px; margin-right: 5px;">
	                                            <input type="text" maxlength="10" name="classDay_${status.index}" id="classDay_${status.index }" class="inputDate form-control input-sm" value="<meditag:dateformat type="1" delimeter="." property="${item.classStartTime}"/>"/>
												<span class="input-group-addon btn-sm" onclick="_clickCalendar('classDay_${status.index}')" style="cursor:pointer">
													<i class="fa fa-calendar"></i>
												</span>
	                                        </div>
	                                        <meditag:datepicker name1="classDay_${status.index}" />
	        
	                                        <input type="text" id="startTime_${status.index}" style="width:55px;float:left;" placeholder="00:00" class="form-control input-sm" value="<meditag:dateformat type="7" delimeter="." property="${item.classStartTime}"/>"/>
	                                        <div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
	                                        <input type="text" id="endTime_${status.index}" style="width:55px;float:left;" placeholder="00:00" class="form-control input-sm" value="<meditag:dateformat type="7" delimeter="." property="${item.classEndTime}"/>"/>
		                                    </td>
                                    </c:if>
                                    <td class="text-center">
                                       <button class="btn btn-default btn-sm" onclick="cntsEditFormPop('${item.unitCd}')">설정</button>
                                    </td>
                                </tr>
                                </tbody>
                                <input type="hidden" name="subList[${status.index}].crsCreCd" value="${createCourseVO.crsCreCd}">
                                <input type="hidden" name="subList[${status.index}].sbjCd" value="${item.sbjCd}" />
								<input type="hidden" name="subList[${status.index}].unitType" value="${item.unitType}" />
								<input type="hidden" name="subList[${status.index}].cntsTypeCd" value="${item.cntsTypeCd}" />
								<input type="hidden" name="subList[${status.index}].unitCd" value="${item.unitCd}" />
								<input type="hidden" name="subList[${status.index}].parUnitCd" value="${item.parUnitCd}" />
								<input type="hidden" name="subList[${status.index}].unitLvl" value="${item.unitLvl}" />
								<input type="hidden" name="subList[${status.index}].unitOdr" value="${item.unitOdr}" />
								<input type="hidden" name="subList[${status.index}].moveType" value="${item.moveType}" />
								<input type="hidden" name="subList[${status.index}].olcYn" value="${item.olcYn}" />
								<input type="hidden" id="classStartTime_${status.index}" name="subList[${status.index}].classStartTime" />
								<input type="hidden" id="classEndTime_${status.index}" name="subList[${status.index}].classEndTime" />
                               </c:forEach>
                            </table>
                        </div>
                        </form>
                        <div id="contentsWriteForm" style="width:100%;display:none;">
                        <form id="addContentsForm" name="addContentsForm" class="form-inline" onsubmit="return false">
                        			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
									<input type="hidden" name="sbjCd" value="${vo.sbjCd}" />
									<input type="hidden" name="unitType" value="${vo.unitType}" />
									<input type="hidden" name="unitCd" value="${vo.unitCd}" />
									<input type="hidden" name="parUnitCd" value="${vo.parUnitCd}" />
									<input type="hidden" name="unitLvl" value="${vo.unitLvl}" />
									<!-- 등록만 하기 위해 디폴트값 추가 -->
									<input type="hidden" name="unitOdr" value="0" />
									<input type="hidden" name="moveType" value="${vo.moveType}" />
									<input type="hidden" name="olcYn" value="N" />
									<input type="hidden" name="bookmarkCnt" value="0" />
									<input type="hidden" name="subCnt" value="0" />
									<table style="width:96%;margin-top:5px;margin-left:10px;font-size:14px;">
										<tr>
											<td style="width:22%;padding:2px;">
												<label for="unitNm" id="unitLvNm"></label>
											</td>
											<td colspan="2" style="padding:2px;">
												<input type="text" dispName="<spring:message code="course.title.contents.name"/>" style="width:100%" maxlength="100" name="unitNm" value="${vo.unitNm }"  onfocus="this.select()" class="form-control input-sm" id="unitNm" />
											</td>
										</tr>
										<tr id="metaTr" style="display: none;">
											<td style="padding:2px;">
												Scene 선택
											</td>
											<td colspan="2" style="padding:2px;" id="metaTd">
												
											</td>
										</tr>
										<tr id="prgrChkTypeTr">
											<td style="padding:2px;">
												<spring:message code="course.title.contents.progress.method"/>
											</td>
											<td colspan="2" style="padding:2px;">
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" style="border:0" name="prgrChkType" value="TIME" <c:if test="${vo.prgrChkType eq 'TIME'}">checked</c:if> id="prgrChkTypeTIME" onclick="prgrChkTypeChk()"/> <spring:message code="course.title.contents.progress.time"/>
												</label>
												<label style="font-weight: normal;">
													<input type="radio" style="border:0" name="prgrChkType" value="PAGE" <c:if test="${vo.prgrChkType eq 'PAGE'}">checked</c:if> id="prgrChkTypePAGE" onclick="prgrChkTypeChk()"/> <spring:message code="course.title.contents.progress.page"/>
												</label>
											</td>
										</tr>
										<tr id="cntsTypeSel">
											<td style="padding:2px;">
												<label for="cntsTypeCd">컨텐츠 타입</label>
											</td>
											<td colspan="2" style="padding:2px;">
												<div class="input-group" style="width:100%">
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" name="cntsTypeCd" id="cntsTypeCd1" value="CDN" onclick="cntsTypeChk()"/> CDN
												</label>
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" name="cntsTypeCd" id="cntsTypeCd2" value="VOD" onclick="cntsTypeChk()" /> VOD
												</label>
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" name="cntsTypeCd" id="cntsTypeCd3" value="MLINK" onclick="cntsTypeChk()" /> 영상링크
												</label>
												<!-- <label style="font-weight: normal;margin-right:20px;">
													<input type="radio" name="cntsTypeCd" id="cntsTypeCd4" value="FILE" onclick="cntsTypeChk()" /> HTML파일
												</label> -->
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" name="cntsTypeCd" id="cntsTypeCd5" value="CODING_L" onclick="cntsTypeChk()" /> 코딩강의
												</label>
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" name="cntsTypeCd" id="cntsTypeCd6" value="CODING_T" onclick="cntsTypeChk()" /> 코딩실습
												</label>
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" name="cntsTypeCd" id="cntsTypeCd7" value="META" onclick="cntsTypeChk()" /> 메타버스 강의
												</label>												
												
												</div>
											</td>
										</tr>
										<tr>
											<td style="padding:2px;">
												<label for="critTm"><spring:message code="course.title.contents.default.time"/></label>
											</td>
											<td width="65%" style="padding:2px;">
												<input type="text" style="width:50px;text-align:right;float:left;" dispName="<spring:message code="course.title.contents.default.time"/>" maxlength="5" lenCheck="5" name="critTm" value="0"  class="form-control input-sm" id="critTm"  onkeyup="isChkNumber(this)"/>
												<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
											</td>
											<td align="right" style="padding:2px;" id="prgrChkYnTd">
												<label for="prgrChkYn"><spring:message code="course.title.course.ratio.progress.check"/></label> &nbsp;<input type="checkbox" name="prgrChkYn" style="border:0" value="Y" id="prgrChkYn"  <c:if test="${vo.prgrChkYn eq 'Y'}">checked</c:if>/>
											</td>
										</tr>
										<tr>
											<td height="30" colspan="4" class="text-right">
												<a href="javascript:contentsEdit()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
												<a href="javascript:cntsRemove()"   class="btn btn-primary btn-sm">삭제 </a>
												<a href="javascript:closeContentsEditForm()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/> </a>
											</td>
										</tr>
									</table>
									<input type="submit" value="submit" style="display:none" />
									</form>
							</div>
						<c:if test="${sbjType eq 'OF'}">	
	                        <div class="text-center" style="margin-bottom:10px;">
	                            <a href="#0" class="btn btn-primary" onclick="editContentsList('${createOnlineSubjectVO.sbjCd}','${fn:length(contentsList)}')">저장 </a>                                   
	                            <a href="#0" class="btn btn-default" onclick="layerHide('${createOnlineSubjectVO.sbjCd}')">닫기 </a>
	                        </div>
                        </c:if>
                    </div>
                    </td>
                </tr> 
            </tbody>
        </table>
		
<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		$('#sortable-online').sortable({
			handle : 'i.glyphicon-move',
	    	stop : function(event, ui) {
	    		var sortString = "";
	    		$("#sortable-online").children('tr').each(function(){
	    			sortString += "|"+$(this).attr("id");
	    		});
	    		sortString = sortString.substring(1);
	    		onlineSort(sortString);
	    	}
		});
	});
	$( "#sortable-online" ).disableSelection();
	
	function layerHide(sbjCd) {
		$("#contentsList_"+sbjCd).hide();
	}
	
	
	//목차 일괄 수정
	function editContentsList(sbjCd, cnt) {
		for(var i=0; i<cnt; i++) {
			var startDay = $("#classDay_"+i).val(); 
			var startTime = $("#startTime_"+i).val();
			var endDay = $("#classDay_"+i).val()
			var endTime = $("#endTime_"+i).val();
			$("#classStartTime_"+i).val(startDay + startTime);
			$("#classEndTime_"+i).val(endDay + endTime);
		}
		
		$('#contentsForm').attr("action","/mng/course/createCourse/subject/editCreateContentsList");
		$('#contentsForm').ajaxSubmit(function (resultDTO) {
			var item = resultDTO.returnVO;
			if(resultDTO.result >= 0) {
				alert("수정되었습니다")
				$("#contentsList_"+sbjCd).hide();
			}
		});
	}
	
	/**
	 * 수정할 단원 정보를 가져온다.
	 */
	function cntsEditFormPop(unitCd) {
		var sbjCd = '${createOnlineSubjectVO.sbjCd}';
		var crsCreCd = '${createOnlineSubjectVO.crsCreCd}';
		var addContent  = "<iframe id='cntsEditFormPop' name='cntsEditFormPop' width='100%' height='100%' "
			+ "frameborder='0' src='<c:url value="/mng/course/createCourse/subject/editFormContentsPop"/>"
			+ "?sbjCd="+sbjCd+"&unitCd="+unitCd+"&crsCreCd="+crsCreCd+"'/>";
			parent.modalBox.clear();
			parent.modalBox.addContents(addContent);
			parent.modalBox.resize(1200, 750);
			parent.modalBox.show();
	}
	
	//강사 등록
	function addTeacher() {
		var teacher1 = $("#teacher1 option:selected").val();
		var teacher2 = $("#teacher2 option:selected").val();
		var tutor = $("#tutor option:selected").val();
		var crsCreCd = '${createOnlineSubjectVO.crsCreCd}';
		var sbjCd = '${createOnlineSubjectVO.sbjCd}';
		if(teacher1 == teacher2) {
			alert("강사가 중복되었습니다. 한명의 강사를 선택하세요");
			return false;
		}
		//var userData = [{  "crsCreCd" : crsCreCd, "tchType": "TEACHER","userNo" : teacher1 }, {  "crsCreCd" : crsCreCd, "tchType": "TEACHER","userNo" : teacher2 }, {  "crsCreCd" : crsCreCd, "tchType": "TUTOR","userNo" : tutor }]
		$.getJSON( cUrl( "/mng/course/createCourse/subject/addTeacher"),
				{ "crsCreCd" : crsCreCd, "sbjCd": sbjCd,
					"teacherList[0].crsCreCd" : crsCreCd, "teacherList[0].tchType": "TEACHER","teacherList[0].userNo" : teacher1, 
					"teacherList[1].crsCreCd" : crsCreCd, "teacherList[1].tchType": "TEACHER","teacherList[1].userNo" : teacher2,
					"teacherList[2].crsCreCd" : crsCreCd, "teacherList[2].tchType": "TUTOR","teacherList[2].userNo" : tutor},			// params
				   function(data) {
						if(data.result >= 0) {
							//-- 정상 처리
							alert("수정되었습니다");
							contentsList('${createOnlineSubjectVO.sbjCd}');
			 	  		} else {
			 	  			//-- 비정상 처리
			 	  			alert("수정에 실패하였습니다.");
			 	  		}
					}
				);
	}
	
	//차시 추가
	function cntsWrite(dept,unitCd) {
		var crsCreCd = '${createOnlineSubjectVO.crsCreCd}';
		var sbjCd = '${createOnlineSubjectVO.sbjCd}';
		$("#addContentsForm").find('input[name="crsCreCd"]').val(crsCreCd);
		$("#addContentsForm").find('input[name="sbjCd"]').val(sbjCd);
		$("#addContentsForm").find('input[name="unitCd"]').val("");
		$("#addContentsForm").find('input[name="unitType"]').val("L");
		
		if(dept == 1){
			$("#addContentsForm").find('input[name="parUnitCd"]').val("");
			$("#addContentsForm").find('input[name="unitLvl"]').val(dept);
			$("#addContentsForm").find('input[name="unitNm"]').val("차시");
			$("#unitLvNm").text('차시명');
		}else{
			$("#addContentsForm").find('input[name="parUnitCd"]').val(unitCd);
			$("#addContentsForm").find('input[name="unitLvl"]').val(dept);
			$("#addContentsForm").find('input[name="unitNm"]').val("페이지");
			$("#unitLvNm").text('페이지명');
			$("#prgrChkTypeTr").hide();
			$("#prgrChkYnTd").hide();
		}
		
		$("#metaTd").html('');
		$("#zoomUrlTr").css("display", "none");
		$("#metaTr").css("display", "none");
		
		
		$("#addContentsForm").find('input[name="unitFilePath"]').val("");
		$("#addContentsForm").find('input[name="mobileFilePath"]').val("");
		$("#addContentsForm").find('input[name="atchFilePath"]').val("");
		//$("#contentsForm").find('input[name="cntsTypeCd"]').val("");
		$("#addContentsForm").find('input[id="cntsTypeCd1"]').attr("checked", false);
		$("#addContentsForm").find('input[id="cntsTypeCd2"]').attr("checked", false);
		$("#addContentsForm").find('input[id="cntsTypeCd3"]').attr("checked", false);
		$("#addContentsForm").find('input[id="cntsTypeCd4"]').attr("checked", false);
		$("#addContentsForm").find('input[id="prgrChkTypeTIME"]').attr("checked", false);
		$("#addContentsForm").find('input[id="prgrChkTypePAGE"]').attr("checked", false);
		$("#addContentsForm").find('input[name="prgrChkYn"]').prop("checked", false);

		$.ajaxSetup({async: false});
		$('#addContentsForm').attr("action","/mng/course/createCourse/subject/addCreateContents");
		$('#addContentsForm').ajaxSubmit(function (resultDTO) {
			var item = resultDTO.returnVO;
			if(resultDTO.result >= 0) {
				$("#contentsList_"+sbjCd).load(
        			cUrl("/mng/course/createCourse/subject/listContentsCreate"),
        			{  "crsCreCd": "${createCourseVO.crsCreCd}", "sbjCd" : sbjCd },
        		);
			} else {
				prgrChkTypeChk();
			}
		});
        $.ajaxSetup({async: true});		
	}
	
	//진도체크방법 설정
	function prgrChkTypeChk(){
		var chkVal = $('input[name="prgrChkType"]:checked').val();
		if(chkVal =="TIME"){
			$("input:radio[id=cntsTypeCd1]").attr("disabled",false);
			$("input:radio[id=cntsTypeCd2]").attr("disabled",false);
			$("input:radio[id=cntsTypeCd3]").attr("disabled",false);
			$("input:radio[id=cntsTypeCd4]").attr("disabled",true);
		}else if(chkVal =="PAGE"){
			$("input:radio[id=cntsTypeCd1]").attr("disabled",true);
			$("input:radio[id=cntsTypeCd2]").attr("disabled",true);
			$("input:radio[id=cntsTypeCd3]").attr("disabled",true);
			$("input:radio[id=cntsTypeCd4]").attr("disabled",false);
		}
		
	}
	
	/**
	 * 차시 순서 변경 폼
	 */
	function cntsSort() {
		//var sbjCtgrTypeCd = $("#sbjCtgrTypeCd  option:selected").val();
		var crsCreCd = '${createOnlineSubjectVO.crsCreCd}';
		var sbjCd = '${createOnlineSubjectVO.sbjCd}';
		var addContent = "<iframe id='addCategoryFrame' name='addCategoryFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/createCourse/subject/sortFormCreCntsPop"/>"
			+ "?sbjCd="+sbjCd+"&crsCreCd="+crsCreCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(300, 304);
		modalBox.setTitle("<spring:message code="course.title.subject.category.sort"/>");
		modalBox.show();
	}
	
</script>

