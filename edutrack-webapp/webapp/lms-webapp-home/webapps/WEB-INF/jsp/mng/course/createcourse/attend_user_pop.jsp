<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="vo" value="${vo}"/>

<div id="popup-wrap" class="popup-wrap">
    <div class="box-body">
        <div class="col-md-5 col-xs-18">
            <div class="panel panel-default">
                
       <form name="attendUpdate" id="attendUpdate" onsubmit="return false">
           	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}"/>
                <div class="panel-body" id="leftWorkArea" style="border-top:0px;">                   
                    <div class="mb10">                       
                        <span class="text-left f16" style="float:left;">
                        	<select class="form-control input-sm" name="userNo" id="userNo" style="width:100%" onChange="selectUser()">
                            	<c:forEach var="stuList" items="${stuList}">
                            		<option <c:if test="${vo.userNo eq stuList.userNo}">selected</c:if> value="${stuList.userNo }">${stuList.userNm}</option>
                            	</c:forEach>
                            </select>
                        </span>
                        <span class="text-right" style="float: right; width: 180px">
                            <select class="form-control input-sm" name="attendDttm" id="attendDttm" style="width:100%" onChange="selectAttend()">
                            	<c:forEach var="periodList" items="${periodList}">
                            		<option value="<meditag:dateformat type="1" delimeter="" property="${periodList.attendDttm}" />"<c:if test="${periodList.attendDttm eq vo.attendDttm }"> selected</c:if>><meditag:dateformat type="1" delimeter="-" property="${periodList.attendDttm}" /></option>
                            	</c:forEach>
                            </select>
                        </span>
                    </div>
                    <div style="clear:both"></div>

                    <div id="attendBox" style="width:100%;margin-top:10px;">
                        <table summary="과정분류관리" class="table table-bordered wordbreak">
                            <colgroup>
                                <col style="width:30%">
                                <col style="width:70%">
                            </colgroup>
                            <tbody>
                                <tr height="35">
                                    <th class="top" scope="row">시간</th>
                                    <td class="top">상태변경</td>
                                </tr>
                                <tr height="35">
                                    <th class="top">1</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat1" id="classStat1" style="width:60%" onChange="chgStat(1)">
                                        	<option value="" <c:if test="${vo.classStat1 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat1 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat1 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat1 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat1 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat1 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="35">
                                    <th class="top">2</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat2" id="classStat2" style="width:60%" onChange="chgStat(2)">
                                        	<option value="" <c:if test="${vo.classStat2 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat2 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat2 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat2 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat2 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat2 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="35">
                                    <th class="top">3</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat3" id="classStat3" style="width:60%" onChange="chgStat(3)">
                                        	<option value="" <c:if test="${vo.classStat3 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat3 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat3 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat3 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat3 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat3 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="35">
                                    <th class="top">4</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat4" id="classStat4" style="width:60%" onChange="chgStat(4)">
                                        	<option value="" <c:if test="${vo.classStat4 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat4 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat4 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat4 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat4 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat4 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="35">
                                    <th class="top">5</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat5" id="classStat5" style="width:60%" onChange="chgStat(5)">
                                        	<option value="" <c:if test="${vo.classStat5 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat5 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat5 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat5 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat5 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat5 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="35">
                                    <th class="top">6</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat6" id="classStat6" style="width:60%" onChange="chgStat(6)">
                                        	<option value="" <c:if test="${vo.classStat6 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat6 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat6 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat6 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat6 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat6 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="35">
                                    <th class="top">7</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat7" id="classStat7" style="width:60%" onChange="chgStat(7)">
                                        	<option value="" <c:if test="${vo.classStat7 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat7 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat7 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat7 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat7 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat7 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="35">
                                    <th class="top">8</th>
                                    <td class="top">
                                        <select class="form-control input-sm" name="classStat8" id="classStat8" style="width:60%" onChange="chgStat(8)">
                                       		<option value="" <c:if test="${vo.classStat8 eq null}"> selected</c:if>>선택</option>
                                            <option value="S" <c:if test="${vo.classStat8 eq 'S'}"> selected</c:if>>츨석</option>
                                            <option value="T" <c:if test="${vo.classStat8 eq 'T'}"> selected</c:if>>지각</option>
                                            <option value="O" <c:if test="${vo.classStat8 eq 'O'}"> selected</c:if>>외출</option>
                                            <option value="L" <c:if test="${vo.classStat8 eq 'L'}"> selected</c:if>>조퇴</option>
                                            <option value="F" <c:if test="${vo.classStat8 eq 'F'}"> selected</c:if>>결석</option>
                                        </select>
                                    </td>
                                </tr>                                                              
                            </tbody>
                        </table>
                        
                    </div>
                    <div class="text-right" style="margin-top:10px;">
                        <button class="btn btn-primary btn-sm" onclick="updateAttendStat()">등록하기</button>                       
                    </div>
					</form>
                </div>
            </div>
        </div>

        <div class="col-md-7 col-xs-18">
            <div class="panel panel-default">
                
                <div class="panel-body">
                    <h5 class="text-left"><i class="fa fa-check" aria-hidden="true"></i><b>출결 상태 이력</b></h5> 
                    <div id="contentsList" style="width:100%;height:430px;overflow:auto;border:2px solid #4FADBC;font-size:14px;">
                               
                        <table summary="게시물목록" class="table table-bordered" id="stuPayTable">
                            <colgroup>
                                <col style="width:auto"/>
                                <col style="width:100px;"/>
                                <col style="width:auto"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">시간</th>
                                    <th scope="col">출결상태</th>
                                    <th scope="col">변경자</th>    
                                    <th scope="col">변경일</th>                                
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="logList" items="${logList}">                                                        
                               		 <tr>
	                                    <td class="text-center">${logList.classTime}</td>
	                                    <td class="text-center">
	                                    <c:if test="${logList.classStat eq 'S'}">출석</c:if>
	                                    <c:if test="${logList.classStat eq 'T'}">지각</c:if>
	                                    <c:if test="${logList.classStat eq 'O'}">외출</c:if>
	                                    <c:if test="${logList.classStat eq 'L'}">조퇴</c:if>
	                                    <c:if test="${logList.classStat eq 'F'}">결석</c:if>
	                                    </td>
	                                    <td class="text-center">${logList.userNm}</td>
	                                    <td class="text-center"><meditag:dateformat type="0" delimeter="" property="${logList.modDttm}" /></td>
                                	</tr>
                                </c:forEach>
                            </tbody>
                        </table>
                         <c:if test="${logList[0].classTime eq null }">
	                    	<b style="display: block; text-align: center;">변경 이력이 없습니다.</b>
	                    </c:if>                                
                    </div>
                    <div class="text-right" style="margin-top:10px;">                       
                        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()">닫기</button>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
<script>
var modalBox = null;
$(document).ready(function() {
	
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
});
function selectAttend() {
	var crsCreCd = '${vo.crsCreCd}';
	var userNo = $('#userNo').val();
	var attendDttm = $('#attendDttm').val();
	location.href="/mng/course/createCourse/attendUserPop?userNo="+userNo+"&crsCreCd="+crsCreCd+"&attendDttm="+attendDttm;
	
}

function selectUser() {
	var crsCreCd = '${vo.crsCreCd}';
	var userNo = $('#userNo').val();
	var attendDttm = $('#attendDttm').val();
	location.href="/mng/course/createCourse/attendUserPop?userNo="+userNo+"&crsCreCd="+crsCreCd+"&attendDttm="+attendDttm;
	
}

function chgStat(time) {
	var stat = $('#classStat'+time).val()
	if(stat == "F") {
		for(var i=1; i<9; i++){
			$('#classStat'+i).val("F");
		}
	} else if(stat == "L") {
		for(var i=time; i<9; i++) {
			$('#classStat'+i).val("L");
		}
	}
}

function updateAttendStat() {
	$('#attendUpdate').attr("action","/mng/course/createCourse/updateAttendStat");
	$('#attendUpdate').ajaxSubmit(function (resultDTO) {
		if(resultDTO.result >= 0) {
			alert("수정되었습니다.")
			selectAttend();
		}
	});
}

</script>