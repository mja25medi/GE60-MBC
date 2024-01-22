<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="createCourseVO" value="${vo}"/>
<c:set var="courseVO" value="${courseVO}"/>

			<form id="createCourseForm" name="createCourseForm" onsubmit="return false">
			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
			<input type="hidden" name="stuCnt" value="${vo.stuCnt }" />
			<input type="hidden" name="sbjCnt" value="${vo.sbjCnt }" />
			<input type="hidden" name="crsCd" value="${vo.crsCd }" />
			<input type="hidden" name="eduTeam" id="eduTeam" value="${vo.eduTeam }"/>
			<input type="hidden" value="${vo.nopLimitYn }" />
			<input type="hidden" name="qrFileSn" id="qrFileSn" value="${vo.qrFileSn }"/>
			<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:17%"/>
					<col style="width:33%"/>
					<col style="width:17%"/>
					<col style="width:33%"/>
				</colgroup>
				<tr>
					<th scope="row"><label for="crsCreNm_${creId}"><spring:message code="course.title.course.name"/></label></th>
					<td >
						<input class="form-control input-sm" name="crsCreNm" id="crsCreNm" style="float:left;width: 70%;margin-right: 5px;" value="${vo.crsCreNm}" readonly />
					</td>
					<th scope="row"><label for="creTerm"><spring:message code="course.title.createcourse.creterm"/></label></th>
					<td >
						<%-- <div class="input-group" style="float:left">
							<input type="text" style="width:55px;" dispName="<spring:message code="course.title.createcourse.creyear"/>" maxlength="4" isNull="N" lenCheck="4" dataType="number" name="creYear" value="${vo.creYear }" onfocus="this.select()" class="form-control input-sm" id="creYear" readonly="true"/>
						</div>
						<span style="float:left;line-height:30px;"><spring:message code="common.title.year"/></span> --%>
						<div class="input-group" style="float:left">
							<input type="text" style="width:50px;" dispName="<spring:message code="course.title.createcourse.creterm"/>" maxlength="50" isNull="N" lenCheck="50" name="creTerm" value="${vo.creTerm }" onfocus="this.select()" class="form-control input-sm" id="creTerm" readonly="true"/>
						</div>
					</td>
				</tr>
				<c:if test="${vo.creTypeCd ne 'ON' }">
					<tr id="qrInput">
						<th scope="row"><label for="QR">QR 등록</label></th>
						<td colspan="3">
							<a href="javascript:uploderclick('QRuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
							<input type="file" name="QRuploader" id="QRuploader" title="QRuploader" multiple style="display:none" accept="image/*"  "/><%-- 첨부파일 버튼 --%>
							<div class="upload">
								<div class="upload_inbox">
									<div id="QRprogress" class="progress">
					    				<div class="progress-bar progress-bar-success"></div>
									</div>
								</div>
								<div id="QRfiles" class="multi_inbox"></div>
							</div>
						</td>
					</tr>
				</c:if>
				<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.title.createcourse.duration.aplc"/></th>
			<td colspan="4">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcStartDttm" id="enrlAplcStartDttm" class="inputDate form-control input-sm" value="${vo.enrlAplcStartDttm}"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcEndDttm" id="enrlAplcEndDttm" class="inputDate form-control input-sm" value="${vo.enrlAplcEndDttm}"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlAplcStartDttm" name2="enrlAplcEndDttm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span>
				<spring:message code="course.title.createcourse.duration.edu"/>
			</th>
			<td colspan="4">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlStartDttm" id="enrlStartDttm" class="inputDate form-control input-sm"" value="${vo.enrlStartDttm}"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlEndDttm" id="enrlEndDttm" class="inputDate form-control input-sm" value="${vo.enrlEndDttm}"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlStartDttm" name2="enrlEndDttm" />
			</td>
		</tr>
				<%-- 
				<tr >
					<th scope="row"><spring:message code="course.title.createcourse.duration.aplc"/></th>
					<td>
						<div class="input-group" style="float:left;width:128px;">
							<input type="text" dispName="<spring:message code="course.title.createcourse.aplc.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlAplcStartDttm" value="${vo.enrlAplcStartDttm }" id="enrlAplcStartDttm" class="inputDate form-control input-sm"/>
							<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcStartDttm')" style="cursor:pointer">
								<i class="fa fa-calendar"></i>
							</span>
						</div>
						<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
						<div class="input-group" style="float:left;width:128px;">
							<input type="text" dispName="<spring:message code="course.title.createcourse.aplc.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlAplcEndDttm" value="${vo.enrlAplcEndDttm }" id="enrlAplcEndDttm" class="inputDate form-control input-sm"/>
							<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcEndDttm')" style="cursor:pointer">
								<i class="fa fa-calendar"></i>
							</span>
						</div>
						<meditag:datepicker name1="enrlAplcStartDttm" name2="enrlAplcEndDttm" />
					</td>
					<c:if test="${courseVO.crsOperType eq 'R'}">
					<th scope="row">
						<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'ON' }"><spring:message code="course.title.createcourse.method.online"/></c:if>
						<c:if test="${courseVO.crsOperMthd eq 'OF' }"><spring:message code="course.title.createcourse.method.offline"/></c:if>
						<spring:message code="course.title.createcourse.duration.edu"/>
					</th>
					<td>
						<div class="input-group" style="float:left;width:128px;">
							<input type="text" dispName="<spring:message code="course.title.createcourse.edu.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlStartDttm" value="${vo.enrlStartDttm }" id="enrlStartDttm" class="inputDate form-control input-sm"/>
							<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm')" style="cursor:pointer">
								<i class="fa fa-calendar"></i>
							</span>
						</div>
						<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
						<div class="input-group" style="float:left;width:128px;">
							<input type="text" dispName="<spring:message code="course.title.createcourse.edu.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlEndDttm" value="${vo.enrlEndDttm }" id="enrlEndDttm" class="inputDate form-control input-sm"/>
							<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm')" style="cursor:pointer">
								<i class="fa fa-calendar"></i>
							</span>
						</div>
						<meditag:datepicker name1="enrlStartDttm" name2="enrlEndDttm" />
					</td>
					</c:if>
					<c:if test="${courseVO.crsOperType eq 'S'}">
					<th scope="row"><spring:message code="course.title.createcourse.eduday"/></th>
					<td>
						<input type="hidden" name="enrlEndDttm" value="${vo.enrlEndDttm }" />
						<input type="hidden" name="enrlStartDttm" value="${vo.enrlStartDttm }" />
						<input type="text" dispName="<spring:message code="course.title.createcourse.eduday"/>" maxlength="3" isNull="Y" lenCheck="3" name="enrlDaycnt" value="${vo.enrlDaycnt }" id="enrlDaycnt" class="inputNumber form-control input-sm" style="text-align:right;width:50px;float:left;" onkeyup="isChkNumber(this)"/>
						<span style="float:left;line-height:30px;padding-left:5px"><spring:message code="common.title.day"/></span>
					</td>
					</c:if>
				</tr>
				 --%>
				 
				 <tr>
					<th scope="row"><span style="color:red;">* </span><label for="scoreOpenDttm"><spring:message code="course.title.createterm.score.date"/></label></th>
					<td>
						<div style="float:left">
							<div class="input-group" style="float:left;width:128px;">
								<input type="text" maxlength="10" name="scoreOpenDttm" id="scoreOpenDttm" class="inputDate form-control input-sm" value="${vo.scoreOpenDttm}"/>
								<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreOpenDttm')" style="cursor:pointer">
									<i class="fa fa-calendar"></i>
								</span>
							</div>
							<meditag:datepicker name1="scoreOpenDttm" />
						</div>
					</td>
					<th scope="row"><label for="eduPrice"><spring:message code="course.title.course.edufee"/></label></th>
					<td>
						<div class="input-group">
						<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
							<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
						</c:if>
							<input type="text" style="width:120px;text-align:right;" dispName="<spring:message code="course.title.createcourse.edufee"/>" maxlength="10" dataType="number" isNull="N" lenCheck="10" name="eduPrice" value="${vo.eduPrice }" onfocus="this.select()" class="inputNumber form-control input-sm" id="eduPrice" onkeyup="isChkNumber(this)"/>
						<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
							<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
						</c:if>
						</div>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span style="color:red;">* </span><label for="cpltScore"><spring:message code="course.title.course.completescore"/></label></th>
					<td>
						<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" maxValue="100" name="cpltScore" id="cpltScore" class="inputNumber form-control input-sm" value="${vo.cpltScore}" onkeyup="isChkMaxNumber(this,100)"/>
						<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.score"/></span>
					</td>
					<td colspan="3">
						<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" name="onlnEduTm" id="onlnEduTm" class="inputNumber form-control input-sm" value="${vo.onlnEduTm}" onkeyup="isChkNumber(this)"/>
						<span style="float:left;margin-left:5px;line-height:30px;"><spring:message code="common.title.time"/></span>
					</td>
					<th scope="row" style="display: none"><span style="color:red;">* </span><spring:message code="course.title.createcourse.decls.cnt"/></th>
					<td style="display: none">
						<input type="text" style="width:40px;text-align:right;" maxlength="3" name="declsCnt" id="declsCnt" value="1" class="inputNumber form-control input-sm" onkeyup="isChkNumber(this)"/>
					</td>
				</tr>
				 
				<tr>
					<th scope="row">과정구분</th>
					<td>
						<select name="creTypeCd" id="creTypeCd" class="form-control input-sm" style="float:left;width: 70%;margin-right: 5px;" disabled>
							<option value="">과정구분을 선택하세요</option>
							<option value="ON" <c:if test="${vo.creTypeCd eq 'ON' }">selected</c:if>>온라인</option>
							<option value="OF" <c:if test="${vo.creTypeCd eq 'OF' }">selected</c:if>>오프라인</option>
							<option value="BL" <c:if test="${vo.creTypeCd eq 'BL' }">selected</c:if>>혼합</option>						
						</select>
					</td>
					<c:if test="${vo.creTypeCd ne 'ON' }">
					<td colspan="2">
						<select name="oflnEduPlace_0" id="oflnEduPlace_0" class="form-control input-sm">
							<option value="">장소를 선택하세요.</option>
							<c:forEach var="roomList" items="${lecRoomList}">
								<option value="${roomList.addr1}${roomList.addr2}"<c:if test ="${vo.oflnEduPlace eq (roomList.addr1+=roomList.addr2)}">selected="selected"</c:if>>${roomList.addr1}${roomList.addr2}</option>
							</c:forEach>
						</select>
					</td>
					</c:if>
				</tr>
				<%-- <tr>
					<th scope="row">환급구분</th>
					<td>
						<select name="refundYn" id="refundYn" class="form-control input-sm" style="float:left;width: 70%;margin-right: 5px;">
							<option value="">환급구분을 선택하세요</option>
							<option value="Y" <c:if test="${vo.refundYn eq 'Y' }">selected</c:if>>환급</option>
							<option value="N" <c:if test="${vo.refundYn eq 'N' }">selected</c:if>>비환급</option>
						</select>
					</td>	
					<th scope="row"><spring:message code="course.title.course.stdcnt"/></th>
					<td>
						<input type="text" style="width:50px;float:left;text-align:right;" dispName="<spring:message code="course.title.course.stdcnt"/>" maxlength="9" dataType="number" isNull="N" lenCheck="9" name="enrlNop" value="${vo.enrlNop }" onfocus="this.select()" class="inputNumber form-control input-sm" id="enrlNop" onkeyup="isChkNumber(this)"/>
						<span style="float:left;text-align:right;line-height:30px;"><spring:message code="common.title.cnt.user"/></span>
					</td>	
				</tr>
				<tr >
					
					<th scope="row"><spring:message code="course.title.createcourse.decls.cnt"/></th>
					<td>
						${createCourseVO.declsCnt}
						<input type="hidden" name="declsCnt" id="declsCnt" value="${vo.declsCnt }"/>
					</td>
					
					<th scope="row"><label for="eduPrice"><spring:message code="course.title.course.edufee"/></label></th>
					<td>
						<div class="input-group">
						<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
							<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
						</c:if>
							<input type="text" style="width:120px;text-align:right;" dispName="<spring:message code="course.title.createcourse.edufee"/>" maxlength="10" dataType="number" isNull="N" lenCheck="10" name="eduPrice" value="${vo.eduPrice }" onfocus="this.select()" class="inputNumber form-control input-sm" id="eduPrice" onkeyup="isChkNumber(this)"/>
						<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
							<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
						</c:if>
						</div>
					</td>
					<th scope="row">환급액</th>
					<td>
						<input type="text" style="width:120px;text-align:right;" maxlength="10" dataType="number" isNull="N" lenCheck="10" name="refundPrice" value="${vo.refundPrice }" onfocus="this.select()" class="inputNumber form-control input-sm" id="refundPrice" onkeyup="isChkNumber(this)"/>
					</td>
				</tr>
				<tr>
					<th scope="row">진도제한</th>
					<td>
						<p style="margin: 5px 5px;float: left;">하루</p>
						<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="dayStudyLimit" value="${vo.dayStudyLimit }" class="form-control input-sm" id="dayStudyLimit" style="float: left;width: 20%"  onkeyup="isChkNumber(this)"/>
						<p style="margin: 5px 5px;float: left;">차시까지만 수강 가능</p>
					</td>
					<th scope="row">회차</th>
					<td>
						<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="tracseTme" value="${vo.tracseTme }" class="form-control input-sm" id="tracseTme" style="float: left;width: 20%"  onkeyup="isChkNumber(this)"/>
						<span style="float:left;text-align:right;line-height:30px;">차</span>
					</td>
				</tr> --%>				
				
				<tr>
					<th scope="row"><label for="eduPrps"><spring:message code="course.title.course.scoreratio"/> (%)</label></th>
					<td colspan="3">
						<ul style="list-style-type:none;padding:0px;width:100%">
							<%-- <li style="float:left;margin-right:20px;">
								<div class="input-group">
									<span style="float:left;margin-right:5px;padding-top:5px;"><label for="prgrRatio"><spring:message code="course.title.course.ratio.progress"/></label></span>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.progress"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="prgrRatio" value="${vo.prgrRatio }" class="inputNumber form-control input-sm" id="prgrRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
							</li> --%>
							<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<span style="float:left;margin-right:5px;padding-top:5px;"><label for="examRatio"><spring:message code="course.title.course.ratio.exam"/></label></span>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.exam"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="examRatio" value="${vo.examRatio }" class="inputNumber form-control input-sm" id="examRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
							</li>
							<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<span style="float:left;margin-right:5px;padding-top:5px;"><label for="asmtRatio"><spring:message code="course.title.course.ratio.asmt"/></label></span>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.asmt"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="asmtRatio" value="${vo.asmtRatio }" class="inputNumber form-control input-sm" id="asmtRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
							</li>
							<c:if test="${vo.etcRatio ne 0 }">
								<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<input type="text" style="font-align:right;width:60px;" maxlength="200" name="etcNm" id="etcNm" class="form-control input-sm" value="${vo.etcNm}"/>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.etc"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="etcRatio" value="${vo.etcRatio }" class="inputNumber form-control input-sm" id="etcRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
								</li>
							</c:if>
							<c:if test="${vo.etcRatio2 ne 0 }">
								<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<input type="text" style="font-align:right;width:60px;" maxlength="200" name="etcNm2" id="etcNm2" class="form-control input-sm" value="${vo.etcNm2}"/>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.etc"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="etcRatio2" value="${vo.etcRatio2 }" class="inputNumber form-control input-sm" id="etcRatio2" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
								</li>
							</c:if>
							<c:if test="${vo.etcRatio3 ne 0 }">
								<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<input type="text" style="font-align:right;width:60px;" maxlength="200" name="etcNm3" id="etcNm3" class="form-control input-sm" value="${vo.etcNm3}"/>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.etc"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="etcRatio3" value="${vo.etcRatio3 }" class="inputNumber form-control input-sm" id="etcRatio3" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
								</li>
							</c:if>
							<c:if test="${vo.etcRatio4 ne 0 }">
								<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<input type="text" style="font-align:right;width:60px;" maxlength="200" name="etcNm4" id="etcNm4" class="form-control input-sm" value="${vo.etcNm4}"/>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.etc"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="etcRatio4" value="${vo.etcRatio4 }" class="inputNumber form-control input-sm" id="etcRatio4" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
								</li>
							</c:if>
							<c:if test="${vo.etcRatio5 ne 0 }">
								<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<input type="text" style="font-align:right;width:60px;" maxlength="200" name="etcNm5" id="etcNm5" class="form-control input-sm" value="${vo.etcNm5}"/>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.etc"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="etcRatio5" value="${vo.etcRatio5 }" class="inputNumber form-control input-sm" id="etcRatio5" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
								</li>
							</c:if>
							<li style="float:left;margin-right:20px;">
								<div class="input-group">
									<span style="float:left;margin-right:5px;padding-top:5px;"><label for="etcRatio">진행단계</label></span>
									<input type="text" dispName="<spring:message code="course.title.course.ratio.semiExam"/>" style="text-align:right;width:50px;" maxlength="3" maxValue="100" isNull="Y" name="semiExamRatio" value="${vo.semiExamRatio }" class="inputNumber form-control input-sm" id="semiExamRatio" onkeyup="isChkMaxNumber(this,100);sumTotal();"/>
								</div>
							</li>
							<li style="float:left;margin-right:20px;height:35px;">
								<div class="input-group">
									<span style="float:left;margin-right:5px;padding-top:5px;"><label for="totRatio"><spring:message code="course.title.course.ratio.total"/></label></span>
									<input type="text" name="totRatio" id="totRatio" class="inputNumber form-control input-sm" style="text-align:right;width:50px;background-color:#fff;" readonly="readonly"/>
								</div>
							</li>
						</ul>
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="course.title.createcourse.info"/></th>
					<td colspan="3">
						<textarea style="height:50px" dispName="<spring:message code="course.title.createcourse.info"/>" isNull="N" lenCheck="2000" name="creDesc" class="form-control input-sm" id="creDesc_0">${vo.creDesc }</textarea>
					</td>
				</tr>
			</table>
			<div style="width:100%;">
				<div style="float:left; height:40px; line-height:30px;">
					<b><spring:message code="course.message.course.alert.ratio"/></b>
				</div>
			</div>
			<div style="width:100%;margin-top:10px;margin-bottom:10px;" class="text-right">
				<button class="btn btn-primary btn-sm" onclick="creCrsEdit()" ><spring:message code="button.add"/></button>
				<%-- <button class="btn btn-info btn-sm" onclick="parent.creCrsInfoMngForm('${createCourseVO.crsCreCd}')" ><spring:message code="course.title.createcourse.info"/></button> --%>
				<button class="btn btn-warning btn-sm" onclick="creCrsDelete()" ><spring:message code="button.delete"/></button>
				<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
			</div>

			<input type="submit" value="submit" style="display:none" />
			</form>

<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.fileupload.js"></script>
<script type="text/JavaScript" src="/js/common_fileupload.js"></script>	
<script type="text/javascript">
	var ItemDTO = new Object();
	var qrFile;
	
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
		//-- 텝 초기 페이지 호출
		ItemDTO.crsCreCd = '${createCourseVO.crsCreCd}';
		ItemDTO.crsCreNm = '${createCourseVO.crsCreNm}';
		ItemDTO.crsOperMthd = '${courseVO.crsOperMthd}';

		chgNopLimitYn();
		if(ItemDTO.crsOperMthd == 'ON') {
			$("#attdRatio").attr("disabled", true);
		}
		if(ItemDTO.crsOperMthd == 'OF') {
			$("#prgrRatio").attr("disabled", true);
		}
		sumTotal();
		
		qrFile = new $M.JqueryFileUpload({
			"varName"			: "qrFile",
			"files" 			: $.parseJSON('${vo.qrFileJson}'),
			"uploaderId"		: "QRuploader",
			"fileListId"		: "QRfiles",
			"progressId"		: "QRprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"infoUse"		: true,
			"uploadSetting"		: {
				'formData'		: { 'repository': 'CRS_CRE_CRS_QR',
	                                'organization' : "${USER_ORGCD}",
									'type'		: 'QR' }
			}
		});
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.createCourseForm)) return;
		ratioCheck();
		$('#createCourseForm').attr("action","/mng/course/createCourse/"+cmd);
		if(cmd == 'editCreateCourse') {
			$('#createCourseForm').ajaxSubmit(processEditCallback);
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processEditCallback(resultDTO) {
		alert(resultDTO.message);
		parent.location.reload();
		parent.modalBoxClose();
		if(resultDTO.result >= 0) {
			// 정상 처리
			creCrsEditLayerHide();
		/* <c:if test="${gubun eq 'E'}">
			if(confirm('<spring:message code="course.message.createcourse.alert.autoscore"/>')) {
				parent.modalBoxClose();
			} else{
				parent.listCreateCourse('${createCourseVO.crsCd}');
				creCrsEditLayerHide();
			}
		</c:if> */
		} else {
			// 비정상 처리
		}
	}

	/* function addAutoResult() {
		$.getJSON( cUrl("/mng/std/eduResult/addAutoResult"),		// url 
			{ 
			  "crsCreCd" : ItemDTO.crsCreCd
			},			// params
			processCallback				// callback function
		);
	} */

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			//parent.listCreateCourse('${createCourseVO.crsCd}');
			creCrsEditLayerHide();
			parent.location.reload();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processDeleteCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			//parent.listCreateCourse('${createCourseVO.crsCd}');
			creCrsEditLayerHide();
			parent.location.reload();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과정 개설 등록 폼 보이기
	 */
	function creCrsEditLayerShow() {
		$("#creCrsViewLayer").hide();
		$("#creCrsEditLayer").show();
	}

	/**
	 * 과정 개설 등록
	 */
	function creCrsEditLayerHide() {
		$("#creCrsEditLayer").hide();
		$("#creCrsViewLayer").show();
	}

	/**
	 * 과정 개설 수정
	 */
	function creCrsEdit() {
		var f = document.createCourseForm;
		
		if($("#enrlAplcStartDttm").val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.aplc"/>');
			$("#enrlAplcStartDttm").focus();
			return false;
		}
		if($("#enrlAplcEndDttm").val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.aplc"/>');
			$("#enrlAplcEndDttm").focus();
			return false;
		}
		
		if($("#enrlStartDttm").val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edu"/>');
			$("#enrlStartDttm").focus();
			return false;
		}
		
		var enrlStartDate = $("#enrlStartDttm").val();
		var enrlEendDate = $("#enrlEndDttm").val();
		var endDate = $("#enrlAplcEndDttm").val();
		
		var enrlStartArray = enrlStartDate.split('.');
		var enrlEndArray = enrlEendDate.split('.');
		var endArray = endDate.split('.');

		var enrl_start_date = new Date(enrlStartArray[0], enrlStartArray[1], enrlStartArray[2]);
		var enrl_end_date = new Date(enrlEndArray[0], enrlEndArray[1], enrlEndArray[2]);
		var end_date = new Date(endArray[0], endArray[1], endArray[2]);
		
		if(end_date.getTime() > enrl_start_date.getTime()) {
			alert("교육기간은 교육신청기간 종료날짜 이후에만 가능합니다.");
			$("#enrlStartDttm").focus();
			return false;
		}
				
		if($("#enrlEndDttm").val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edu"/>');
			$("#enrlEndDttm").focus();
			return false;
		}
		
		if($("#scoreOpenDttm").val() == '') {
			alert('성적 열람 시작일을 입력하세요.');
			$("#scoreOpenDttm").focus();
			return false;
		}
		
		if($("#cpltScore").val() == '') {
			alert('수료점수를 입력하세요.');
			$("#cpltScore").focus();
			return false;
		}

		if($("#onlnEduTm").val() == '') {
			alert('<spring:message code="course.message.createcourse.input.duration.edutm"/>');
			$("#onlnEduTm").focus();
			return false;
		}
		
		if('${courseVO.crsOperMthd}' == 'OF' || '${courseVO.crsOperMthd}' == 'BL') {
			if($("#oflnEduPlace_0").val() == '') {
				alert('<spring:message code="course.message.createcourse.input.duration.eduplace"/>');
				$("#oflnEduPlace_0").focus();
				return false;
			}
		}
		
		
		var cpltScore = parseInt(nvlNumber($("#cpltScore").val()),10);
		var cpltPrgrRate = parseInt(nvlNumber($("#cpltPrgrRate").val()),10);

		var prgrRatio = parseInt(nvlNumber($("#prgrRatio").val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio").val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio").val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio").val()),10);
		var semiExamRatio = parseInt(nvlNumber($("#semiExamRatio").val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio").val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio").val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio").val()),10);
		var etcRatio2 = parseInt(nvlNumber($("#etcRatio2").val()),10);
		var etcRatio3 = parseInt(nvlNumber($("#etcRatio3").val()),10);
		var etcRatio4 = parseInt(nvlNumber($("#etcRatio4").val()),10);
		var etcRatio5 = parseInt(nvlNumber($("#etcRatio5").val()),10);
		var totRatio = prgrRatio + attdRatio + examRatio + semiExamRatio + forumRatio + asmtRatio + joinRatio + etcRatio + etcRatio2 + etcRatio3 + etcRatio4 + etcRatio5;
		if(totRatio != 100) {
			alert('<spring:message code="course.message.createcourse.alert.totalscore"/>');
			return;
		}
		var _qrFile = qrFile.getFileSnAll();
		$('#qrFileSn').val(_qrFile);
		process("editCreateCourse");
	}

	/**
	 * 과정 개설 삭제
	 */
	function creCrsDelete() {
		if(confirm('<spring:message code="course.message.createcourse.confirm.delete"/>')) {
			$('#createCourseForm').attr("action","/mng/course/createCourse/deleteCreateCourse");
			$('#createCourseForm').ajaxSubmit(processDeleteCallback);
		} else {
			return;
		}
	}

	//인원제한 able <-> disable
	function chgNopLimitYn(){
		var nopLimitYn = $("#nopLimitYn option:selected").val();
		if(nopLimitYn == "Y"){
			//$('#enrlNop').attr("disabled", false);
			$('#enrlNopArea').show();
		}else{
			//$('#enrlNop').attr("disabled", true);
			$('#enrlNopArea').hide();
		}
	}

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

	//날짜 사이의 간격 구하기
	function getGapDate() {
		var f = document.f;
		var from = f.serviceStart.value;
		var to = f.serviceEnd.value;
		if(from != "" && to != "") {
			var days = jsGetDays(chgDate(from),chgDate(to));

			f.serviceDay.value = days;

		}
	}

	/**
	 * 비율 설정 체크
	 */
	function ratioCheck() {
		var prgrRatio = parseInt(nvlNumber($("#prgrRatio").val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio").val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio").val()),10);
		var semiExamRatio = parseInt(nvlNumber($("#semiExamRatio").val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio").val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio").val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio").val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio").val()),10);
		var etcRatio2 = parseInt(nvlNumber($("#etcRatio2").val()),10);
		var etcRatio3 = parseInt(nvlNumber($("#etcRatio3").val()),10);
		var etcRatio4 = parseInt(nvlNumber($("#etcRatio4").val()),10);
		var etcRatio5 = parseInt(nvlNumber($("#etcRatio5").val()),10);

		var totRatio = prgrRatio + attdRatio + examRatio + semiExamRatio + forumRatio + asmtRatio + joinRatio + etcRatio + etcRatio2 + etcRatio3 + etcRatio4 + etcRatio5;

		if(totRatio != 100) {
			alert('<spring:message code="course.message.course.alert.ratio"/>');
			return false;
		}
		return true;

	}

	function sumTotal(){
		var prgrRatio = parseInt(nvlNumber($("#prgrRatio").val()),10);
		var attdRatio = parseInt(nvlNumber($("#attdRatio").val()),10);
		var examRatio = parseInt(nvlNumber($("#examRatio").val()),10);
		var semiExamRatio = parseInt(nvlNumber($("#semiExamRatio").val()),10);
		var forumRatio = parseInt(nvlNumber($("#forumRatio").val()),10);
		var asmtRatio = parseInt(nvlNumber($("#asmtRatio").val()),10);
		var joinRatio = parseInt(nvlNumber($("#joinRatio").val()),10);
		var etcRatio = parseInt(nvlNumber($("#etcRatio").val()),10);
		var etcRatio2 = parseInt(nvlNumber($("#etcRatio2").val()),10);
		var etcRatio3 = parseInt(nvlNumber($("#etcRatio3").val()),10);
		var etcRatio4 = parseInt(nvlNumber($("#etcRatio4").val()),10);
		var etcRatio5 = parseInt(nvlNumber($("#etcRatio5").val()),10);

		var totRatio = prgrRatio + attdRatio + examRatio + semiExamRatio + forumRatio + asmtRatio + joinRatio + etcRatio + etcRatio2 + etcRatio3 + etcRatio4 + etcRatio5;

		$("#totRatio").val(totRatio);

	}
	
	function uploderclick(str) {
		$("#"+str).click();
	}
</script>
