<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="examListVO" value="${examListVO}"/>
<c:set var="examVO" value="${vo}"/>
<c:set var="avgScore" value="0"/>
 				<form id="examForm" name="examForm" onsubmit="return false" >
					<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
					<input type="hidden" name="examSn" value="${vo.examSn}" />
					<input type="hidden" name="stdNo" value="${vo.stdNo}" />
					<input type="hidden" name="stareLimitCnt" id="stareLimitCnt" />
					<input type="hidden" name="stareCnt" id="stareCnt" />
				</form>
					<form name="Search" id="Search" action="javascript:listExam()">
                <div class="learn_top">
                    <div class="left_title">
                        <h3>시험정보</h3>
                    </div>
                </div>
				<c:forEach var="item" items="${examListVO}" varStatus="status">
				<c:set var="stareLimitCnt" value="${item.stareCnt}/${item.stareLimitCnt}"/>
	                <div class="segment">
	                    <div class="learn_info">
	                        <div class="header">
	                        <!-- 온라인 -->
	 						<c:if test="${item.examTypeCd eq 'ON'}">
	 							<label class="online">온라인</label>
	 							<h4>${item.examTitle}</h4>
	 						</c:if>                   
							<!-- 오프라인  -->
							<c:if test="${item.examTypeCd ne 'ON'}">
								<label class="offline">오프라인</label>
								<a href="javascript:examInfo('${item.examSn}')"><h4>${item.examTitle}</h4></a>
							</c:if>
	                            
	                        </div>
	                         
	                    </div>
	                    <div class="course_list test_custom">
	                        <div class="item">
	                            <ul class="info_disc">
									<c:choose>
										<c:when test="${item.examStareTypeCd eq 'R' }">
											<li>
												<strong><spring:message code="lecture.title.exam.duration"/></strong>
                                      		 	<meditag:dateformat type="8" delimeter="." property="${item.examStartDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${item.examEndDttm}"/>
											</li>
											<c:if test="${item.semiExamYn eq  'Y'}">
												<li><strong>응시 기준 강의 수 : </strong>${item.sbjNm} ${item.stareLecCount} 강</li>
											</c:if>
											<c:if test="${item.semiExamYn eq 'N' }">
												<li>
                                      			 	 <strong><spring:message code="lecture.title.exam.answer.ratio"/> : </strong>
                                       				 ${item.stareCritPrgrRatio}% <spring:message code="common.title.over"/>
                                  				</li>
											</c:if>
										</c:when>
										<c:otherwise>
											<c:if test="${item.semiExamYn eq  'Y'}">
												<li><strong>응시 기준 강의 수 : </strong>${item.stareLecCount} 강</li>
											</c:if>
											<c:if test="${item.semiExamYn eq 'N' }">
												<li>
                                      			 	 <strong><spring:message code="lecture.title.exam.answer.ratio"/> : </strong>
                                       				 ${item.stareCritPrgrRatio}% <spring:message code="common.title.over"/>
                                  				</li>
											</c:if>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${item.examTypeCd eq 'ON' }">
											<c:choose>
												<c:when test="${item.stareTmUseYn eq 'Y' }">
												 <li>
                                       				 <strong><spring:message code="lecture.title.exam.time"/> : </strong>
                                        			 ${item.examStareTm} <spring:message code="common.title.min"/>
                                   				 </li>
												</c:when>
												<c:otherwise>
													<li>
                                       				 <strong><spring:message code="lecture.title.exam.time"/> : </strong>
                                        			 <spring:message code="lecture.title.exam.Unlimited"/>
                                   				 </li>
												</c:otherwise>
											</c:choose>
												 <li>
                                       				 <strong><spring:message code="lecture.title.exam.answer.cnt"/> : </strong>
                                        			${stareLimitCnt}
                                   				 </li>
										</c:when>
									</c:choose>




	                            </ul>
	                            <div class="button_group">
                                    <c:if test="${item.examTypeCd eq 'ON'}">
											<!-- 상시 시험일 경우 -->
											<c:if test="${item.examStareTypeCd eq 'S' }">
												<!-- 시험일 경우 진도율 비고, 진행단계평가일 경우 강의 수로 비교 -->
												<c:if test="${item.semiExamYn eq 'Y' }">
													<!-- 들은 강의 개수가 응시 가능 강의 개수 보다 큰 경우  응시 가능-->
													<c:if test="${item.stareLecCount <= item.prgrLecCount}">
														<c:if test="${empty item.endDttm}">
															<button type="button" class="primary" onclick="examPaper('${item.examSn}','${item.stareLimitCnt}','${status.count}')"><spring:message code="button.answer.exam"/></button>
														</c:if>
														<c:if test="${not empty item.endDttm}">
															<%-- <button type="button" class="primary" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')"><spring:message code="button.answer.exam"/></button> --%>
																<button type="button" class="default" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')">응시완료</button>
														</c:if>
													</c:if>
													<!-- 응시 가능 강의 개수 가 들은 강의 개수 보다 큰 경우 응시 불가 -->
													<c:if test="${item.stareLecCount > item.prgrLecCount}">
														<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.answer.completeCnt" arguments="${item.stareLecCount}"/>')"><spring:message code="button.answer.exam"/></button>
													</c:if>
												</c:if>
												<c:if test="${item.semiExamYn eq 'N' }">
													<!-- 시험 응시 가능 진도율과 학습자 진도율 비교 (응시 가능 진도율 보다 학습자 진도율이 클 경우)-->
													<c:if test="${item.stareCritPrgrRatio <= item.stdRatio }">
														<!-- 시험에 대한 평가가 완료 되었을 경우 결과 화면 보여주기 -->
														<c:if test="${item.rateYn eq 'Y'}">
															<button type="button" class="primary" onclick="viewRate('${item.examSn}')"><spring:message code="button.view.result"/></button>
														</c:if>
														<!-- 시험에 대한 평가가 완료 되지 않았을 경우 -->
														<c:if test="${item.rateYn ne 'Y'}">
															<!-- 응시 횟수가 제한 횟수보다 클경우 제한 초과 경고창-->
															<c:if test="${item.stareCnt >= item.stareLimitCnt}">
																<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.answer.cnt" arguments="${item.stareLimitCnt}" />')"><spring:message code="button.answer.exam"/></button>
															</c:if>
															<!-- 응시 횟수가 제한 횟수보다 작은 경우 -->
															<c:if test="${item.stareCnt < item.stareLimitCnt}">
																<!-- 강의평가 완료했을 경우에 응시 가능 -->
																<c:if test="${reshVO.itemCnt eq reshVO.ansrCnt}">
																	<c:if test="${empty item.endDttm}">
																		<button type="button" class="primary" onclick="examPaper('${item.examSn}','${item.stareLimitCnt}','${status.count}')"><spring:message code="button.answer.exam"/></button>
																	</c:if>
																	<c:if test="${not empty item.endDttm}">
																		<%-- <button type="button" class="primary" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')"><spring:message code="button.answer.exam"/></button> --%>
																			<button type="button" class="default" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')">응시완료</button>
																	</c:if>
																</c:if>
																<c:if test="${reshVO.itemCnt ne reshVO.ansrCnt}">
																	<button type="button" class="primary" onclick="alert('강의평가 완료 후 시험 응시 가능합니다.')"><spring:message code="button.answer.exam"/></button>
																</c:if>
															</c:if>
														</c:if>
													</c:if>
													<!-- 시험 응시 가능 진도율과 학습자 진도율 비교 (응시 가능 진도율이 학습자 진도율보다 클 경우)-->
													<c:if test="${item.stareCritPrgrRatio > item.stdRatio }">
														<!-- 진도 미달 -->
														<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.answer.ratio" arguments="${item.stareCritPrgrRatio}"/>')"><spring:message code="button.answer.exam"/></button>
													</c:if>
												</c:if>
											</c:if>                            
											<!-- 정규 시험일 경우 -->
											<c:if test="${item.examStareTypeCd ne 'S' }">
												<!-- 시험 기간일 경우 -->
												<c:if test="${item.connYn eq 'Y'}">
													<!-- 시험에 대한 평가가 완료 되었을 경우 -->
													<c:if test="${item.rateYn eq 'Y'}">
														<!-- 결과 확인일일 이후 일 경우 결과 화면 보여준다 -->
														<c:if test="${item.rsltYn eq 'Y'}">
															<button type="button" class="primary" onclick="viewRate('${item.examSn}')"><spring:message code="button.view.result"/></button>
														</c:if>
														<!-- 결과 확인일일 이전 일 경우 결과 확인일 경고창 -->
														<c:if test="${item.rsltYn ne 'Y'}">
															<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.noreulstdate"/>')"><spring:message code="button.view.result"/></button>
														</c:if>
													</c:if>
													<!-- 시험에 대한 평가가 완료 되지 않았을 경우 -->
													<c:if test="${item.rateYn ne 'Y'}">
														<!-- 응시 횟수가 제한 횟수보다 클경우 제한 초과 경고창-->
														<c:if test="${item.stareCnt >= item.stareLimitCnt}">
															<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.answer.cnt" arguments="${item.stareLimitCnt}"/>')"><spring:message code="button.answer.exam"/></button>
														</c:if>
														<!-- 응시 횟수가 제한 횟수보다 작을 경우 응시 -->
														<c:if test="${item.stareCnt < item.stareLimitCnt }">
															<!-- 진행단계 평가일 경우  -->
															<c:if test="${item.semiExamYn eq 'Y' }">
																<!-- 들은 강의 개수가 응시 가능 강의 개수 보다 큰 경우  응시 가능-->
																<c:if test="${item.stareLecCount <= item.prgrLecCount}">
																	<c:if test="${empty item.endDttm}">
																		<button type="button" class="primary" onclick="examPaper('${item.examSn}','${item.stareLimitCnt}','${status.count}')"><spring:message code="button.answer.exam"/></button>
																	</c:if>
																	<c:if test="${not empty item.endDttm}">
																		<%-- <button type="button" class="primary" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')"><spring:message code="button.answer.exam"/></button> --%>
																		<button type="button" class="default" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')">응시완료</button>
																	</c:if>
																</c:if>
																<!-- 응시 가능 강의 개수 가 들은 강의 개수 보다 큰 경우 응시 불가 -->
																<c:if test="${item.stareLecCount > item.prgrLecCount}">
																	<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.answer.completeCnt" arguments="${item.stareLecCount}"/>')"><spring:message code="button.answer.exam"/></button>
																</c:if>
															</c:if>
															<c:if test="${item.semiExamYn eq 'N' }">
																<!-- 시험 응시 가능 진도율과 학습자 진도율 비교 (응시 가능 진도율 보다 학습자 진도율이 클 경우)-->
																<c:if test = "${item.stareCritPrgrRatio <= item.stdRatio }">
																	<!-- 강의평가 완료 후 시험 응시 가능 -->
																	<c:if test="${reshVO.itemCnt eq reshVO.ansrCnt}">
																		<c:if test="${ empty item.endDttm}">
																			<button type="button" class="primary" onclick="examPaper('${item.examSn}','${item.stareLimitCnt}','${status.count}')"><spring:message code="button.answer.exam"/></button>
																		</c:if>
																		<c:if test="${not empty item.endDttm}">
																			<%-- <button type="button" class="primary" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')"><spring:message code="button.answer.exam"/></button> --%>
																				<button type="button" class="default" onclick="alert('시험을 이미 응시하였습니다. 재응시는 불가합니다.')">응시완료</button>
																		</c:if>
																	</c:if>
																	<!-- 강의평가 미완료 시 시험 응시 불가 -->
																	<c:if test="${reshVO.itemCnt ne reshVO.ansrCnt}">
																		<button type="button" class="primary" onclick="alert('강의평가 완료 후 시험 응시 가능합니다.')"><spring:message code="button.answer.exam"/></button>
																	</c:if>
																</c:if>
																<!-- 시험 응시 가능 진도율과 학습자 진도율 비교 (응시 가능 진도율이 학습자 진도율보다 클 경우)-->
																<c:if test = "${item.stareCritPrgrRatio > item.stdRatio }">	
																	<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.answer.ratio" arguments="${item.stareCritPrgrRatio}"/>')"><spring:message code="button.answer.exam"/></button>

																</c:if>
															</c:if>
														</c:if>
													</c:if>
												</c:if>
												<!-- 시험 기간이 아닐 경우 -->
												<c:if test="${item.connYn ne 'Y'}">
													<!-- 응시 횟수가 있을 경우 -->
													<c:if test="${item.stareCnt > 0 }">
														<!-- 시험에 대한 평가가 완료 되었을 경우 -->
														<c:if test="${item.rateYn eq 'Y'}">
															<!-- 결과 확인일일 이후 일 경우 결과 화면 보여준다 -->
															<c:if test="${item.rsltYn eq 'Y'}">
																<button type="button" class="secondary" onclick="viewRate('${item.examSn}')"><spring:message code="button.view.result"/></button>
															</c:if>
															<!-- 결과 확인일일 이전 일 경우 결과 확인일 경고창 -->
															<c:if test="${item.rsltYn ne 'Y'}">
																<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.noreulstdate"/>')"><spring:message code="button.view.result"/></button>
															</c:if>
														</c:if>
														<!-- 시험에 대한 평가가 완료 되지 않았을 경우 -->
														<c:if test="${item.rateYn ne 'Y'}">
															<c:if test="${empty item.endDttm}">
																<button type="button" class="primary" onclick="alert('최종 제출된 응시 내역이 존재하지 않습니다. 관리자에게 문의바랍니다.')"><spring:message code="button.view.result"/></button>
															</c:if>
															<c:if test="${not empty item.endDttm }">
																<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.state.rateing"/>')"><spring:message code="button.view.result"/></button>
															</c:if>
														</c:if>
													</c:if>
													<!-- 응시 횟수가 없을 경우 -->
													<c:if test="${item.stareCnt <= 0 }">
														<button type="button" class="primary" onclick="alert('<spring:message code="lecture.message.exam.stare.alert.noexamdate"/>')"><spring:message code="button.answer.exam"/></button>
													</c:if>
												</c:if>
											</c:if>
										</c:if>                            
 										<!-- 오프라인일 경우 상세내용 보여주기 -->
										<c:if test="${item.examTypeCd ne 'ON'}">
										 <button type="button" class="default" onclick="javascript:examInfo('${item.examSn}')">시험 정보</button>
											<!-- 결과 확인일 이후 일 경우 결과 보여주기 -->
											<c:if test="${item.rsltYn eq 'Y'}">
												<!-- 시험에 대한 평가가 완료 되었을 경우 -->
												<c:if test="${item.rateYn eq 'Y'}">
												 <button type="button" class="secondary" onclick="javascript:viewRate('${item.examSn}')"><spring:message code="button.view.result"/></button>
												</c:if>
												<!-- 시험에 대한 평가가 완료 되지 않았을 경우 -->
												<c:if test="${item.rateYn ne 'Y'}">
												<button type="button" class="secondary" onclick="javascript:alert('<spring:message code="lecture.message.exam.state.rateing"/>')"><spring:message code="button.view.result"/></button>
												</c:if>
											</c:if>
											<!-- 결과 확인일 이전 일 경우 상세 내용 보여주기 -->
											<c:if test="${item.rsltYn ne 'Y'}">
												<%-- <a href="javascript:viewExam('${item.examSn}')" class="btn btn-info btn-sm"><spring:message code="button.view.detail"/></a> --%>
											</c:if>
										</c:if>
                                
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
				<c:if test="${empty examListVO}">
					 <div class="segment">
					 	<spring:message code="lecture.message.exam.nodata"/>
					 </div>
				</c:if>
					</form>

<script type="text/javascript">
	var modalBox = null;
	var otpModalBox = null;
	
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "examModal"
		});
		
		otpModalBox =new $M.ModalDialog({
			"modalid" : "otpModal"
		});
		
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 학습자 시험 결과
	 */
	function viewRate(examSn) {
		var url = generateUrl("/lec/exam/viewRatePop",{"crsCreCd":"${CRSCRECD}", "examSn":examSn, "stdNo":"${STUDENTNO}"});
		var addContent  = "<iframe id='examInfoFrame' name='examInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1450, 860);
		modalBox.setTitle("<spring:message code="lecture.title.exam.info"/>");
		modalBox.show();
		
		
	}
/* 
	function viewExam(examSn) {
		var url = generateUrl("/lec/exam/readExam",{"crsCreCd":"${sessionScope.LOGIN_CRSCRECD}", "examSn":examSn});
		var addContent  = "<iframe id='examInfoFrame' name='examInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(700, 340);
		modalBox.setTitle("<spring:message code="lecture.title.exam.info"/>");
		modalBox.show();
	} */

	//2015.11.04 redmine #267(KNOTZ_NG_102) 수정
	//
	var statusCnt = "";
	function examPaper(examSn, stareLimitCnt, statusCount) {
		statusCnt = statusCount
		stareLimitProcess(examSn, stareLimitCnt);
	}
	/**
	 * 서브밋 처리
	 */
	function stareLimitProcess(examSn, stareLimitCnt) {
		var crsCreCd 	= '${CRSCRECD}';
		var stdNo 		= '${STUDENTNO}';
		$('#examForm').find('input[name=crsCreCd]').val(crsCreCd);
		$('#examForm').find('input[name=examSn]').val(examSn);
		$('#examForm').find('input[name=stdNo]').val(stdNo);
		$('#examForm').find('input[name=stareLimitCnt]').val(stareLimitCnt);
		$('#examForm').attr("action","/lec/exam/examPaperStareCnt");
		$('#examForm').ajaxSubmit(processCallback);

	}
	
	/**
	 * 시헝응시 응시횟수 조정 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {

		var stareLimitCnt = $('#examForm').find('input[name=stareLimitCnt]').val();
		var stareCnt = 0;
		if(resultDTO.result > 0){	//기존에 응시한 적이 있으면 
			stareCnt = parseInt(resultDTO.returnVO.stareCnt)+1;
		}else{
			stareCnt = 1;
		}
		if(parseInt(stareLimitCnt) >= parseInt(stareCnt)){
			eval("$('#stareCntLable"+statusCnt+"')").html(stareCnt+"/"+stareLimitCnt);
		}

		var examSn 			= $('#examForm').find('input[name=examSn]').val();
		var stareLimitCnt 	= $('#examForm').find('input[name=stareLimitCnt]').val();
		var agreeYn = resultDTO.returnVO.agreeYn;
		examPaperAfter(examSn, stareLimitCnt, stareCnt)
	}
	
	function examPaperAfter(examSn, stareLimitCnt, stareCnt) {
		if(parseInt(stareLimitCnt) < parseInt(stareCnt)){
			alert("<spring:message code="lecture.message.exam.stare.alert.answer.cnt"  arguments='"+stareLimitCnt+"' />");
			return;
		}
		var url = generateUrl("/lec/exam/agreePop",{ "crsCreCd":"${CRSCRECD}", "examSn":examSn, "stdNo":"${STUDENTNO}", "stareCnt":stareCnt,  "semiExamYn":"${vo.semiExamYn}"});
 		

		var addContent  = "<iframe id='examPaperFrame' name='examPaperFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(560, 450);
		modalBox.setTitle("<spring:message code="lecture.title.exam.agree"/>");
		modalBox.show();
	}
	
	function examPaperStare(examSn, agreeYn, stareCnt) {
		var url = generateUrl("/lec/exam/addPaperPop",{ "crsCreCd":"${CRSCRECD}", "examSn":examSn, "stdNo":"${STUDENTNO}" , "agreeYn":agreeYn, "stareCnt": stareCnt});
 		var addContent  = "<iframe id='examPaperFrame' name='examPaperFrame' width='100%' height='100%' "
			+ "frameborder='0'scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1500, 800);
		modalBox.setTitle("<spring:message code="lecture.title.exam.answer"/>");
		modalBox.show();
	}
	
	function examInfo(examSn){
		var url = generateUrl("/lec/exam/viewExamPop",{"examSn":examSn});
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 400);
		modalBox.setTitle("<spring:message code="lecture.title.exam.info"/>");
		modalBox.show();
	}
</script>
 

