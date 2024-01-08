<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="attendList" value="${attendList}"/>
<c:set var="periodList" value="${periodList}"/>
        <style type="text/css">
            .attend .table-bordered>tbody>tr>td {vertical-align: middle; padding:8px 2px; text-align: center;}
        </style>
        <!-- contents -->
        <section class="content">
            <div class="row" id="content">
                    <div class="box-body">
                        <div class="subject-list mt5" >
                            <table class="table table-bordered wordbreak" style="margin-top: 5px; outline: 1px solid #cdcdcd;">
                                <colgroup>
                                    <col style="width:20%">
                                    <col style="width:30%">
                                    <col style="width:20%">
                                    <col style="width:30%">
                                </colgroup>
                                <tbody>
                                <tr>                           
                                    <th scope="row">과정명</th>
                                    <td class="text-left">${createCourseVO.crsCreNm } ${createCourseVO.creTerm }회차</td>
                                     <th scope="row">교육기간</th>
                                    <td class="text-left">${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}</td>
                                </tr>                       
                                
                                </tbody>
                            </table>

                            <div class="panel panel-default mb5">
                            	<form name="Search" onsubmit="return false" class="form-inline">
                              	  <div class="panel-heading">
                                    <div class="panel-body" style="padding:0px;">
                                    
                                        <div class="input-group" style="float:left;width:128px;">
                                        	<input type="text" maxlength="10" id="week-picker" class="inputDate form-control input-sm" value="${searchPeriod}" style="width: 150px;"/>
                                            <input type="hidden" maxlength="10" name="searchDate" id="searchDate" class="inputDate form-control input-sm" value="${searchDate}"/>
                                            <span class="input-group-addon btn-sm" onclick="_clickCalendar('searchDate')" style="cursor:pointer">
                                                <i class="fa fa-calendar"></i>
                                            </span>
                                            <%-- <meditag:datepicker name1="searchDate" /> --%>
                                        </div>
                                        <div class="pull-right">
                                            <button class="btn btn-primary btn-sm" style="margin-right: 5px;" id="search_btn"><i class="fa fa-search fa-fw" ></i>검색</button>
                                            <a href="#0" class="btn btn-primary btn-sm" style="float:right;" onclick="excelDownload('${createCourseVO.crsCreCd}');"><i class="fa fa-file-excel-o fa-fw"></i>엑셀다운로드</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </form>
                            <br>
                            <h5 class="text-left"><i class="fa fa-check" aria-hidden="true"></i><b>화면상태 표시 설명 출석</b>: [○] 지각:[◎] 조퇴:[▲] 결석:[<span class="f22" style="line-height:10px; vertical-align: middle; height:20px">×</span>] 외출:[◇]</h5>
                            <div class="table-responsive attend">
                                <table summary="게시물목록" style="margin-top:5px;" class="table table-bordered" id="stuPayTable">
                                    
                                    <thead>
                                    <tr>
                                        <th scope="col" rowspan="2">번호</th>
                                        <th scope="col" rowspan="2" style="width:80px !important">이름</th>
                                        <c:forEach var="attendPeriod" items="${attendPeriod}" varStatus="status">
                                        	<th scope="col" colspan="8"><meditag:dateformat type="3" delimeter="/" property="${attendPeriod}" /></th>
                                        </c:forEach>
                                        
                                        <th scope="col" rowspan="2">소정<br>출석일</th>
                                        <th scope="col" rowspan="2">실제<br>출석일</th>
                                        <th scope="col" rowspan="2">결석</th>
                                        <th scope="col" rowspan="2">지각</th>
                                        <th scope="col" rowspan="2">조퇴</th>
                                        <th scope="col" rowspan="2">외출</th>
                                    </tr>
                                    <tr>
                                        <th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th>
                                        <th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th>
                                        <th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th>
                                        <th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th>
                                        <th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th>
                                     <tr>   
                                    </thead>
                                    <tbody>    
                                    
                                    <c:if test="${not empty attendList }">
                                    <c:forEach var="data" items="${attendList}" varStatus="status">                            
	                                    <tr>
	                                        <td>${status.count}</td>
	                                        <td><a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">${data.userNm}</a></td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col01 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col01 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col01 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col01 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col01 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col02 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col02 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col02 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col02 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col02 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col03 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col03 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col03 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col03 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col03 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col04 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col04 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col04 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col04 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col04 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col05 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col05 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col05 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col05 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col05 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col06 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col06 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col06 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col06 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col06 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col07 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col07 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col07 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col07 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col07 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[0]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col08 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col08 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col08 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col08 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col08 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col11 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col11 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col11 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col11 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col11 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col12 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col12 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col12 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col12 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col12 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col13 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col13 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col13 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col13 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col13 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col14 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col14 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col14 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col14 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col14 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col15 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col15 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col15 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col15 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col15 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col16 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col16 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col16 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col16 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col16 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col17 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col17 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col17 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col17 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col17 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[1]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col18 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col18 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col18 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col18 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col18 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col21 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col21 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col21 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col21 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col21 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col22 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col22 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col22 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col22 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col22 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col23 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col23 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col23 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col23 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col23 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col24 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col24 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col24 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col24 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col24 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col25 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col25 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col25 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col25 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col25 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col26 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col26 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col26 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col26 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col26 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col27 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col27 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col27 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col27 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col27 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[2]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col28 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col28 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col28 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col28 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col28 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col31 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col31 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col31 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col31 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col31 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col32 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col32 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col32 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col32 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col32 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col33 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col33 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col33 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col33 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col33 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col34 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col34 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col34 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col34 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col34 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col35 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col35 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col35 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col35 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col35 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col36 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col36 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col36 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col36 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col36 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col37 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col37 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col37 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col37 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col37 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[3]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col38 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col38 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col38 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col38 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col38 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                       
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col41 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col41 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col41 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col41 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col41 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col42 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col42 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col42 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col42 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col42 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col43 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col43 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col43 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col43 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col43 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col44 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col44 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col44 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col44 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col44 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col45 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col45 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col45 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col45 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col45 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col46 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col46 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col46 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col46 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col46 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col47 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col47 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col47 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col47 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col47 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>
	                                        <td>
	                                        	<a href="javascript:userPop('${data.userNo}','${attendPeriod[4]}')">
	                                        	<c:choose>
	                                        		<c:when test="${data.col48 eq 'S'}"><span class="f22">○</span></c:when>
	                                        		<c:when test="${data.col48 eq 'F'}"><span class="f22">×</span></c:when>
	                                        		<c:when test="${data.col48 eq 'T'}"><span>◎</span></c:when>
	                                        		<c:when test="${data.col48 eq 'L'}"><span>▲</span></c:when>
	                                        		<c:when test="${data.col48 eq 'O'}"><span>◇</span></c:when>
	                                        		<c:otherwise>-</c:otherwise>
	                                        	</c:choose>
	                                        	</a>
	                                        </td>

											<td>${data.totalDay}</td>
											<td>${data.realDay}</td>
											<td>${data.absentDay}</td>
											<td>${data.lateDay}</td>
											<td>${data.leftDay}</td>
											<td>${data.outDay}</td>	
	                                    </tr>
                                    </c:forEach> 
                                    </c:if>
                                    <c:if test="${empty attendList }">
										<tr>
											<td colspan='48'>해당 기간에 데이터가 없습니다.</td>
										</tr>                                    
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>


                        
                    </div>
            </div>
        </section> 
<script>
var modalBox = null;
$(document).ready(function() {
	
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
	
	$('#search_btn').click(function() {

        var crsCreCd = '${createCourseVO.crsCreCd}';
    	var searchDate = $('#searchDate').val();
    	var url = cUrl("/mng/course/createCourse/listAttend");
    	$("#workBody")
    		.load(url, {
    			"crsCreCd" : crsCreCd,
    			"searchDate" : searchDate
    		});
    });
	
	 $.datepicker.setDefaults({
		    showMonthAfterYear:true,
		    closeText: "닫기",
		    currentText: "오늘",
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		    weekHeader: "주",
		    yearSuffix: '년'
    });    
});


/**
 * 유저상세 팝업 팝업
 */
function userPop(userNo, searchDate) {
	var crsCreCd = '${createCourseVO.crsCreCd}';
	var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
		+ "frameborder='0' src='<c:url value="/mng/course/createCourse/attendUserPop"/>"
		+ "?userNo="+userNo+"&crsCreCd="+crsCreCd+"&attendDttm="+searchDate+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1280, 750);
	modalBox.setTitle("출석 상세");
	modalBox.show();
}

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}


/**
 * 엑셀 다운로드
 */
function excelDownload(crsCreCd) {
	var url = cUrl("/mng/course/createCourse/excelDownloadAttendList")+"?crsCreCd="+crsCreCd;
	if ( $("#_m_download_iframe").length == 0 ) {
		iframeHtml =
			'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
		$("body").append(iframeHtml);
	}
	$('#_m_download_iframe').attr('src', url);
}

//주간달력
$(function() {
    var startDate;
    var endDate;
    
    $('#week-picker').datepicker( {
        showOtherMonths: true,
        selectOtherMonths: true,
		selectWeek:true,
        onSelect: function(dateText, inst) { 
            var date = $(this).datepicker('getDate');
            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1);
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 5);
			var dateFormat = 'yy.mm.dd'
            startDate = $.datepicker.formatDate( dateFormat, startDate, inst.settings );
            endDate = $.datepicker.formatDate( dateFormat, endDate, inst.settings );

			$('#week-picker').val(startDate + '~' + endDate);
			$('#searchDate').val(startDate);
            
            setTimeout("applyWeeklyHighlight()", 100);
        },
		beforeShow : function() {
			setTimeout("applyWeeklyHighlight()", 100);
		},
        onChangeMonthYear : function() {
        	setTimeout("applyWeeklyHighlight()", 100);
        }
    });
});

function applyWeeklyHighlight() {
	$('.ui-datepicker-calendar tr').each(function() {
		if ($(this).parent().get(0).tagName == 'TBODY') {
			$(this).mouseover(function() {
				$(this).find('a').css({
					'background' : '#a5ccff',
					'border' : '1px solid #dddddd'
				});
				$(this).find('a').removeClass('ui-state-default');
				$(this).css('background', '#a5ccff');
			});
			
			$(this).mouseout(function() {
				$(this).css('background', '#ffffff');
				$(this).find('a').css('background', '');
				$(this).find('a').addClass('ui-state-default');
			});
		}

	});
}
</script>        
        
        
        
