<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="onlineSubjectList" value="${onlineSubjectList}"/>
<c:set var="createCourseVO" value="${createCourseVO}"/>
<c:set var="contentsList" value="${contentsList}"/>

		<table summary="<spring:message code="course.title.subject.manage"/>" class="table table-bordered wordbreak" style="font-size: 14px;">
			<colgroup>
                <col style="width:50px"/>
                <col style="width:auto"/>
                <col style="width:auto;"/>
                <col style="width:auto;"/>
                <col style="width:auto"/>
                <col style="width:auto"/>
                <col style="width:auto"/>
                <col style="width:auto"/>
                <col style="width:75px"/>
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">과목코드</th>
                    <th scope="col">과목명</th>
                    <th scope="col">과목유형</th>
                    <th scope="col">목차수</th>
                    <th scope="col">강사</th>
                    <th scope="col">교육 일정</th>
                    <th scope="col">장소</th>
                    <th scope="col">삭제</th>
                </tr>
            </thead>
			<tbody id="sortable-online">
				<c:forEach items="${onlineSubjectList}" var="item" varStatus="status">
				<tr>
					<td class="text-center">${fn:length(onlineSubjectList) - status.index}</td>
					<td class="text-center">${item.sbjCd}</td>
					<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.sbjNm}</td>
					<td class="text-center">
						<c:if test="${item.sbjType eq 'ON' }">온라인</c:if>
						<c:if test="${item.sbjType eq 'OF' }">오프라인</c:if>
					</td>
					<td class="text-center">${item.contentsCnt}</td>
					<td class="text-center">
						<div>
							<c:if test="${item.sbjType eq 'ON' }">온라인</c:if>
							<c:if test="${item.sbjType eq 'OF' }">
							<c:choose>
								<c:when test='${createCourseVO.oflnEduPlace eq null || createCourseVO.oflnEduPlace eq ""}'>미등록</c:when>
								<c:otherwise>${teacher.userNm }</c:otherwise>
							</c:choose>
							</c:if>
						</div>
						<div class="input-group" id="studyMthdSelect_${item.sbjCd}" style="display:none">
							<select name="studyMthd_${item.sbjCd}" id="studyMthd_${item.sbjCd}" class="form-control input-sm">
							<c:forEach var="code" items="${studyMthdList}">
								<c:set var="codeName" value="${code.codeNm}"/>
								<c:forEach var="lang" items="${code.codeLangList }">
									<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
								</c:forEach>
								<option value="${code.codeCd}" <c:if test="${code.codeCd eq item.studyMthd}">selected</c:if>>${codeName}</option>
							</c:forEach>
							</select>
							<span class="input-group-btn">
								<button class="btn btn-primary btn-sm" onclick="javascript:editOnlineMthd('${item.sbjCd}')"><spring:message code="button.add"/></button>
							</span>
						</div>
						<input type="hidden" name="studyMthdValue_${item.sbjCd}" id="studyMthdValue_${item.sbjCd}" value="${item.studyMthd}"/>
					</td>
					<td class="text-center">${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}</td>
					<td class="text-center">
						<c:if test="${item.sbjType eq 'ON' }">온라인</c:if>
						<c:if test="${item.sbjType eq 'OF' }">
							<c:choose>
								<c:when test='${createCourseVO.oflnEduPlace eq null || createCourseVO.oflnEduPlace eq ""}'></c:when>
								<c:otherwise>${createCourseVO.oflnEduPlace}</c:otherwise>
							</c:choose>
						</c:if>
					</td>
					<td class="text-center">
						<%-- <button id="studyMthdEditButton_${item.sbjCd}" class="btn btn-info btn-sm" onclick="javascript:showEditOnlineMthd('${item.sbjCd}');"><spring:message code="button.edit"/></button> --%>
						<button id="studyMthdCancelButton_${item.sbjCd}" class="btn btn-info btn-sm" onclick="javascript:hideEditOnlineMthd('${item.sbjCd}');" style="display:none"><spring:message code="button.cancel"/></button>
						<button class="btn btn-warning btn-sm" onclick="javascript:delOnline('${item.sbjCd}');"><spring:message code="button.delete"/></button>
					</td>
				</tr>
				<tr>
	                <td colspan="9" class="text-center">
	                    <div class="button-area">
	                        <a href="#0" class="box-close" id="setting" onclick="contentsList('${item.sbjCd}')")>목차설정 ▼</a>
	                    </div>
	                    <div class="table-responsive" id="contentsList_${item.sbjCd }" style="display: none; height: auto;"> </div>
                </c:forEach> 
				<c:if test="${empty onlineSubjectList}">
				<tr>
					<td colspan="6"><spring:message code="course.message.subject.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
					
      
    </div>
</div>
		
<script type="text/javascript">

	$(document).ready(function() {
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
/* 	$('.box-close').click(function() {
        $(".subject-list").toggle(1);
        resizeForm
    }); */
	
	function contentsList(sbjCd) {
    	if($("#contentsList_"+sbjCd).css("display") == "none"){
    	    $("#contentsList_"+sbjCd).show();
    	    $("#contentsList_"+sbjCd).load(
        			cUrl("/mng/course/createCourse/subject/listContentsCreate"),
        			{  "crsCreCd": "${createCourseVO.crsCreCd}", "sbjCd" : sbjCd },
        		);
    	} else {
    	    $("#contentsList_"+sbjCd).hide();
    	}
		
	}
</script>

