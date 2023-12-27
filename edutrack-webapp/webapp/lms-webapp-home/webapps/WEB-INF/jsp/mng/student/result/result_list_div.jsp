<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentList" value="${eduResultList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="eduResultVO" value="${vo}"/>

	<c:set var="colspan" value="4"/>
	<table summary="<spring:message code="student.title.result.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:30px;"/>
			<col style="width:auto;"/>
			<col style="width:auto;"/>
			<!-- <col style="width:50px;"/> -->
			<col style="width:80px;"/>
			<c:set var="colspan" value="${colspan +1}"/>
			<c:if test="${createCourseVO.prgrRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>			
			<c:if test="${createCourseVO.attdRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.examRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.semiExamRatio > 0}">
				<col style="width:120px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.asmtRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.forumRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.joinRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.etcRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.etcRatio2 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.etcRatio3 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.etcRatio4 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<c:if test="${createCourseVO.etcRatio5 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
			</c:if>
			<col style="width:80px;"/>
			<col style="width: auto"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" onclick="checkAll()"/></th>
				<%-- <th scope="col" >
				<c:choose>
					<c:when test="${fn:startsWith(eduResultVO.sortKey,'DECLS_NO') == true}">
						<c:if test="${eduResultVO.sortKey eq 'DECLS_NO_ASC'}">
					<a href="javascript:setSortKey('DECLS_NO_DESC')"><spring:message code="student.title.student.decls"/>▼</a>
						</c:if>
						<c:if test="${eduResultVO.sortKey eq 'DECLS_NO_DESC'}">
					<a href="javascript:setSortKey('DECLS_NO_ASC')"><spring:message code="student.title.student.decls"/>▲</a>
						</c:if>
					</c:when>
					<c:otherwise>
					<a href="javascript:setSortKey('DECLS_NO_ASC')"><spring:message code="student.title.student.decls"/></a>
					</c:otherwise>
				</c:choose>
				</th> --%>
				<th scope="col" >
				<c:choose>
					<c:when test="${fn:startsWith(eduResultVO.sortKey,'USER_NM') == true}">
						<c:if test="${eduResultVO.sortKey eq 'USER_NM_ASC'}">
					<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/>▼</a>
						</c:if>
						<c:if test="${eduResultVO.sortKey eq 'USER_NM_DESC'}">
					<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/>▲</a>
						</c:if>
					</c:when>
					<c:otherwise>
					<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/></a>
					</c:otherwise>
				</c:choose>
				</th>
				<th scope="col" >
				<c:choose>
					<c:when test="${fn:startsWith(eduResultVO.sortKey,'USER_ID') == true}">
						<c:if test="${eduResultVO.sortKey eq 'USER_ID_ASC'}">
					<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/>▼</a>
						</c:if>
						<c:if test="${eduResultVO.sortKey eq 'USER_ID_DESC'}">
					<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/>▲</a>
						</c:if>
					</c:when>
					<c:otherwise>
					<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/></a>
					</c:otherwise>
				</c:choose>
				</th>
				<%-- <th scope="col"><spring:message code="student.title.result.score.excellent"/></th> --%>
				<th scope="col">진도율<br></th>
				<c:if test="${createCourseVO.prgrRatio > 0}">
				<th scope="col">진도<br>(${createCourseVO.prgrRatio}%)</th>
				</c:if>				
				<c:if test="${createCourseVO.attdRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.attend"/><br>(${createCourseVO.attdRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.examRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.exam"/><br>(${createCourseVO.examRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.semiExamRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.semiExam"/><br>(${createCourseVO.semiExamRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.asmtRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.asmt"/><br>(${createCourseVO.asmtRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.forumRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.forum"/><br>(${createCourseVO.forumRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.joinRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.join"/><br>(${createCourseVO.joinRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.etcRatio > 0}">
				<th scope="col">${createCourseVO.etcNm}<br>(${createCourseVO.etcRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.etcRatio2 > 0}">
				<th scope="col">${createCourseVO.etcNm2}<br>(${createCourseVO.etcRatio2}%)</th>
				</c:if>
				<c:if test="${createCourseVO.etcRatio3 > 0}">
				<th scope="col">${createCourseVO.etcNm3}<br>(${createCourseVO.etcRatio3}%)</th>
				</c:if>
				<c:if test="${createCourseVO.etcRatio4 > 0}">
				<th scope="col">${createCourseVO.etcNm4}<br>(${createCourseVO.etcRatio4}%)</th>
				</c:if>
				<c:if test="${createCourseVO.etcRatio5 > 0}">
				<th scope="col">${createCourseVO.etcNm5}<br>(${createCourseVO.etcRatio5}%)</th>
				</c:if>
				
				<th scope="col"><spring:message code="student.title.result.totalscore"/><br>(100%)</th>
				<%-- <th scope="col"><spring:message code="common.title.add"/></th> --%>
				<th scope="col">상태</th>
				<th scope="col">수료번호</th>
				<th scope="col">저장</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${studentList}" varStatus="status">
			<c:set var="fontColor" value="#FEDFDF"/>
			<c:if test="${item.resultYn eq 'Y'}"><c:set var="fontColor" value="#DFF6FE"/></c:if>
			<tr>
				<td class="text-center"><input type='checkbox' name='sel' id='sel_${status.index}' value='${item.stdNo}' style='border:0'></td>
				<%-- <td class="text-right">${item.declsNo}</td> --%>
				<td>${item.userNm}</td>
				<td>${item.userId}</td>
				<%-- <td class="text-center">
					<c:if test="${item.totScore > 0 }">
					<input type="checkbox" name="scoreEcltYn" value="${item.stdNo}" <c:if test="${item.scoreEcltYn eq 'Y'}">checked</c:if>/>
					</c:if>
					<c:if test="${item.totScore == 0 || empty item.totScore}">
					<input type="hidden" name="scoreEcltYn" value="${item.scoreEcltYn}" />
					</c:if>
				</td> --%>
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="prgrScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.prgrRate}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.prgrRatio})"/></td>
				<c:if test="${createCourseVO.prgrRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="prgrScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.prgrScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.prgrRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.attdRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="attdScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.attdScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.attdRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.examRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="examScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.examScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.examRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.semiExamRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="semiExamScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.semiExamScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.semiExamRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.asmtRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="asmtScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.asmtScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.asmtRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.forumRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="forumScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.forumScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.forumRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.joinRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" onfocus="this.select()" name="joinScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.joinScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.joinRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.etcRatio > 0}">
				<td class="text-center"><input type="text" class="InputNumber form-control input-sm" onfocus="this.select()" name="etcScore" style="width:50px; background-color:${fontColor}; text-align:right;" value='${item.etcScore}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.etcRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.etcRatio2 > 0}">
				<td class="text-center"><input type="text" class="InputNumber form-control input-sm" onfocus="this.select()" name="etcScore2" style="width:50px; background-color:${fontColor}; text-align:right;" value='${item.etcScore2}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.etcRatio2})"/></td>
				</c:if>
				<c:if test="${createCourseVO.etcRatio3 > 0}">
				<td class="text-center"><input type="text" class="InputNumber form-control input-sm" onfocus="this.select()" name="etcScore3" style="width:50px; background-color:${fontColor}; text-align:right;" value='${item.etcScore3}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.etcRatio3})"/></td>
				</c:if>
				<c:if test="${createCourseVO.etcRatio4 > 0}">
				<td class="text-center"><input type="text" class="InputNumber form-control input-sm" onfocus="this.select()" name="etcScore4" style="width:50px; background-color:${fontColor}; text-align:right;" value='${item.etcScore4}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.etcRatio4})"/></td>
				</c:if>
				<c:if test="${createCourseVO.etcRatio5 > 0}">
				<td class="text-center"><input type="text" class="InputNumber form-control input-sm" onfocus="this.select()" name="etcScore5" style="width:50px; background-color:${fontColor}; text-align:right;" value='${item.etcScore5}' onBlur="checkTotalScore(${status.count -1})" onkeyup="isChkMaxNumber(this,${createCourseVO.etcRatio5})"/></td>
				</c:if>
				<td><input type="text" class="inputNumber form-control input-sm" disabled onfocus="this.select()" name="totScore" style="width:50px;background-color:${fontColor};text-align:right;" value='${item.totScore}' readonly="readonly" onBlur="checkTotalScore(${status.count -1})"/></td>
				<td class="text-center">
				<c:choose>
					<c:when test="${item.enrlSts eq 'C' }">수료</c:when>
					<c:otherwise>미수료</c:otherwise>
				</c:choose>
				</td>
				<td class="text-center" style="width: 250px;">
 					<c:if test="${empty item.cpltNo }">-</c:if>
					<c:if test="${not empty item.cpltNo }">${item.cpltNo }</c:if>
				</td>
				
				<td class="text-center" style="width: 200px;">
				
					<c:if test="${not empty item.cpltNo }">
						<button class="btn btn-default btn-sm" onclick="javascript:printCerti('${item.crsCreCd}','${item.stdNo}');"><i class="glyphicon glyphicon-print"></i> 수료증출력</button>
					</c:if>
					<a href="javascript:addResult(${status.index});" class="btn btn-primary btn-sm" ><spring:message code="button.add"/></a>
					<c:if test="${createCourseVO.prgrRatio eq 0}"><input type="hidden" name="prgrScore" value="0"/></c:if>
					<c:if test="${createCourseVO.attdRatio eq 0}"><input type="hidden" name="attdScore" value="0"/></c:if>
					<c:if test="${createCourseVO.examRatio eq 0}"><input type="hidden" name="examScore" value="0"/></c:if>
					<c:if test="${createCourseVO.semiExamRatio eq 0}"><input type="hidden" name="semiExamScore" value="0"/></c:if>
					<c:if test="${createCourseVO.asmtRatio eq 0}"><input type="hidden" name="asmtScore" value="0"/></c:if>
					<c:if test="${createCourseVO.forumRatio eq 0}"><input type="hidden" name="forumScore" value="0"/></c:if>
					<c:if test="${createCourseVO.joinRatio eq 0}"><input type="hidden" name="joinScore" value="0"/></c:if>
					<c:if test="${createCourseVO.etcRatio eq 0}"><input type="hidden" name="etcScore" value="0"/></c:if>
					<c:if test="${createCourseVO.etcRatio2 eq 0}"><input type="hidden" name="etcScore2" value="0"/></c:if>
					<c:if test="${createCourseVO.etcRatio3 eq 0}"><input type="hidden" name="etcScore3" value="0"/></c:if>
					<c:if test="${createCourseVO.etcRatio4 eq 0}"><input type="hidden" name="etcScore4" value="0"/></c:if>
					<c:if test="${createCourseVO.etcRatio5 eq 0}"><input type="hidden" name="etcScore5" value="0"/></c:if>
				</td>
	
			</tr>
			</c:forEach>
			<c:if test="${empty studentList}">
			<tr>
				<td colspan="${colspan}"><spring:message code="student.message.student.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listStudent"/>
