<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="imageBase" value="/img/"/>
                    <div class="segment">
                    <div class="board_top">
                        <h4>기본정보</h4>
                    </div>                
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">과목명<i class="icon_star"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                         	 ${osVO.sbjNm }
                                        </div>             
                                    </div>
                                </div>
                            </li>
                               <c:if test="${createCourseVO.creTypeCd eq 'OF' || createCourseVO.creTypeCd eq 'BL'}">
                            <li>
                                <div class="row">
                                    <label for="contTextarea" class="form-label col-sm-2">강의실<i class="icon_star"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control w50" type="text" maxlength="3" value="${createCourseVO.oflnEduPlace }" readonly="readonly">
                                        </div>
                                    </div>
                                </div>
                            </li>
                                 </c:if>  
                            <c:if test="${createCourseVO.creOperTypeCd ne 'S' }"> 
                            <li>
                                <div class="row">
                                    <span class="form-label col-sm-2">교육기간<i class="icon_star"></i></span>
                                    <div class="col-sm-10">
                                        <div class="form-inline">
                                            <input type="date" class="inputDate form-control md" name="enrlStartDttm_0" id="enrlStartDttm_0" value="" readonly="readonly">
                                            <span class="ml5 mr5">~</span>
                                            <input type="date" class="inputDate form-control md" name="enrlEndDttm_0" id="enrlEndDttm_0"   value="" readonly="readonly">
                                        </div>                                      
                                    </div> 
                                </div>
                            </li>  
                       		</c:if>           
                        </ul>
                    </div>
                </div>     
                         
                 
                
             
                <div class="segment">
                    <div class="board_top">
                        <div class="left_title">
                            <h4>학습 목차 설정</h4>
                        </div>
                        <div class="right_btn">
                        	<button type="button" class="btn type1" onclick="javascript:shareCntsWrite()">미디어 콘텐츠 관리</button>
                            <button type="button" class="btn type4" onclick="javascript:cntsWrite('1','')">목차추가</button>
                        </div>
                    </div>
                      <form id="contentsForm" name="contentsForm" class="form-inline" onsubmit="return false">
                    <div class="res_tbl_wrap lecture_setting">
                        <table>
                            <caption>학습 목차 설정 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="10%">목차</th>
                                    <th scope="col">목차명</th>
                                    <c:if test="${osVO.sbjType eq 'OF' }">
                                    	<th scope="col" width="37%">시간</th>
                                    </c:if>
                                    <th scope="col" width="10%">설정</th>
                                </tr>
                            </thead>
                            <tbody>
                             <c:forEach items="${contentsList}" var="item" varStatus="status">
                                <tr>
                                    <td scope="row" data-label="목차">
                                     <c:choose>
	                                		<c:when test="${item.unitLvl == 1 }">${item.unitOdr}차시</c:when>
	                                		<c:when test="${item.unitLvl == 2 }">-</c:when>
	                                	</c:choose>
                                    </td>
                                    <td class="title ico_type" data-label="목차명">
                                        <input class="form-control w80" id="unitNm" name="subList[${status.index}].unitNm"  type="text" value="${item.unitNm}">
                                        <c:choose>
                                        	<c:when test="${item.unitLvl == 1 && item.prgrChkType eq 'PAGE'}"><button onclick="cntsWrite('2' ,'${item.unitCd}')" class="btn type3">페이지 추가</button></c:when>
                                        	<c:when test="${item.cntsTypeCd eq 'CDN' }"><img src="/tpl/${COLOR_TPL}/img/class/icon_course_cdn.svg" alt="icon" title="CDN"></c:when>
                                        	<c:when test="${item.cntsTypeCd eq 'VOD' }"><img src="/tpl/${COLOR_TPL}/img/class/icon_course_play.svg" alt="icon" title="VOD"></c:when>
                                        	<c:when test="${item.cntsTypeCd eq 'LECTURE' }"><i class="fa fa-desktop ml5" title="코딩강의"></i></c:when>
                                        	<c:when test="${item.cntsTypeCd eq 'PRACTICE' }"><i class="fa fa-code ml5" title="코딩실습"></i></c:when>
                                        </c:choose>
                                        <!--  <img src="/tpl/${COLOR_TPL}/img/class/icon_course_link.svg" alt="icon" title="영상링크">
                                        <img src="/tpl/${COLOR_TPL}/img/class/icon_course_video.svg" alt="icon" title="Zoom">
                                        <img src="/tpl/${COLOR_TPL}/img/class/icon_course_code.svg" alt="icon" title="코딩학습"> -->
                                    </td>
                                    <c:if test="${osVO.sbjType eq 'OF' && osVO.sbjType eq 'BL' }">
                                    <td class="time_setting" data-label="시간">
                                        <div class="form-inline">
                                            <input type="date" class="form-control md" name="classDay_${status.index}" id="classDay_${status.index }" value="<meditag:dateformat type="1" delimeter="-" property="${item.classStartTime}"/>" >
                                            <input class="form-control sm time_col" type="text" maxlength="5" placeholder="00:00" id="startTime_${status.index}" value="<meditag:dateformat type="7" delimeter="." property="${item.classStartTime}"/>" />
                                            <span class="ruffle_sign">~</span>
                                            <input class="form-control sm time_col" type="text" maxlength="5" placeholder="00:00" id="endTime_${status.index}" value="<meditag:dateformat type="7" delimeter="." property="${item.classEndTime}"/>"/>
                                        </div>                                       
                                    </td>
                                    </c:if>
                                    <td data-label="설정"><button class="btn type3" onclick="cntsEditFormPop('${item.unitCd}')">설정</button>
                                    
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
                            </tbody>
                        </table>
                    </div> 
                </form>
                 <form id="addContentsForm" name="addContentsForm" class="form-inline" onsubmit="return false">
                        			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
									<input type="hidden" name="sbjCd" value="${vo.sbjCd}" />
									<input type="hidden" name="unitType" value="${vo.unitType}" />
									<input type="hidden" name="unitCd" value="${vo.unitCd}" />
									<input type="hidden" name="unitNm" value="${vo.unitNm}" />
									<input type="hidden" name="parUnitCd" value="${vo.parUnitCd}" />
									<input type="hidden" name="unitLvl" value="${vo.unitLvl}" />
									<!-- 등록만 하기 위해 디폴트값 추가 -->
									<input type="hidden" name="unitOdr" value="0" />
									<input type="hidden" name="moveType" value="${vo.moveType}" />
									<input type="hidden" name="olcYn" value="N" />
									<input type="hidden" name="bookmarkCnt" value="0" />
									<input type="hidden" name="subCnt" value="0" />
				</form>
                    <div class="btns mt30">
                        <button type="button" class="btn gray2" onclick="editContentsList('${osVO.sbjCd}','${fn:length(contentsList)}')">저장</button>
                        <!-- <button type="reset" class="btn type5">취소</button> -->
                    </div>
                </div>

<script type="text/javascript">
$(document).ready(function() {
	$("#enrlStartDttm_0").val(formatDate("${createCourseVO.enrlStartDttm}"));
	$("#enrlEndDttm_0").val(formatDate("${createCourseVO.enrlEndDttm}"));
});

//미디어콘텐츠 관리 
function shareCntsWrite() {
	var crsCreCd = '${createCourseVO.crsCreCd}';
	var crsCreNm = '${createCourseVO.crsCreNm}';
	var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
		+ "frameborder='0' src='<c:url value="/lec/cnts/shareContentsManagePop"/>"
		+ "?crsCreCd="+crsCreCd+"&crsCreNm="+crsCreNm+"'/>";
		parent.modalBox.clear();
		parent.modalBox.setTitle("미디어 콘텐츠 관리");
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1400, 500);
		parent.modalBox.show();
}

//차시 추가
function cntsWrite(dept,unitCd) {
	var crsCreCd = '${createCourseVO.crsCreCd}';
	var sbjCd = '${osVO.sbjCd}';
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
			  location.reload();
			}
		});
    $.ajaxSetup({async: true});		
}


/**
 * 수정할 단원 정보를 가져온다.
 */
function cntsEditFormPop(unitCd) {
	var sbjCd = '${osVO.sbjCd}';
	var crsCreCd = '${createCourseVO.crsCreCd}';
	var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
		+ "frameborder='0' src='<c:url value="/lec/cnts/editFormContentsPop"/>"
		+ "?sbjCd="+sbjCd+"&unitCd="+unitCd+"&crsCreCd="+crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.setTitle("목차 설정 관리");
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1500, 600);
		parent.modalBox.show();
}

//목차 일괄 수정
function editContentsList(sbjCd, cnt) {
	var f = document.contentsForm;
	for(var i=0; i<cnt; i++) {
		if(isEmpty(f["subList["+i+"].unitNm"].value)) {
			alert("차시명을 입력하세요.");
			f["subList["+i+"].unitNm"].focus();
			return;
		}
	}
	
	<c:if test="${osVO.sbjType ne 'ON'}">
		for(var i=0; i<cnt; i++) {
			var startDay = $("#classDay_"+i).val();
			if(startDay == '') {
				alert("날짜를 입력해주십시오")
				return false;
			}
			var startTime = $("#startTime_"+i).val();
			if(!fn_hourMinutes(startTime)){
				return false;
			};
			var endDay = $("#classDay_"+i).val()
			if(endDay == '') {
				alert("날짜를 입력해주십시오")
				return false;
			}
			var endTime = $("#endTime_"+i).val();
			if(!fn_hourMinutes(endTime)){
				return false;
			};
			$("#classStartTime_"+i).val(startDay + startTime);
			$("#classEndTime_"+i).val(endDay + endTime);
		}
	</c:if>
	
	$('#contentsForm').attr("action","/lec/cnts/editCreateContentsList");
	$('#contentsForm').ajaxSubmit(function (resultDTO) {
		var item = resultDTO.returnVO;
		if(resultDTO.result >= 0) {
			alert("수정되었습니다")
			//$("#contentsList_"+sbjCd).hide();
			window.location.reload();
		}
	});
}
function formatDate(date) {
	var dt = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
	return dt;
}

function fn_hourMinutes(time){
	  var regExp = /^(([0-1][0-9])|(2[0-3])):[0-5][0-9]$/;
	  if(!regExp.test(time)){
	    alert("올바른 시간형식이 아닙니다.");
	    return false;
	  }
	  return true;
	}

</script>