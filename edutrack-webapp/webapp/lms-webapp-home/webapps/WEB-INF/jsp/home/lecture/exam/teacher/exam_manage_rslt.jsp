<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="examVO" value="${vo}" />
<c:set var="examStareList" value="${examStareListVO}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
				<table class="table mt20 ">
								<caption class="sr-only"><spring:message code="lecture.title.exam.manage"/></caption>
								<colgroup>
									<col style="width:20%" />
									<col style="wdith:30%" />
									<col style="width:20%" />
									<col style="wdith:30%" />
								</colgroup>
								<tbody>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.examtype"/></th>
									<td>
										<span class="label outline fcBlue">
											<c:if test="${examVO.examTypeCd eq 'ON'}">온라인</c:if>
											<c:if test="${examVO.examTypeCd eq 'OFF'}">오프라인</c:if>
										</span>
									</td>
									<th scope="row"><spring:message code="lecture.title.exam.ansrtype"/></th>
									<td>
										<meditag:codename code="${examVO.examStareTypeCd}" category="EXAM_STARE_TYPE_CD" />
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.name"/></th>
									<td colspan="3" class="wordbreak">
										${examVO.examTitle}
									</td>
								</tr>
								<c:if test="${examVO.examTypeCd eq 'ON'}">
								<tr>
									<c:if test ="${examVO.semiExamYn eq 'Y'}">
										<th scope="row">응시 기준 강의</th>
										<td>
											${examVO.stareLecCount} 강 수강 후 
										</td>
									</c:if>
									<c:if test ="${examVO.semiExamYn eq 'N'}">
										<th scope="row"><spring:message code="lecture.title.exam.answer.ratio"/></th>
										<td>
											${examVO.stareCritPrgrRatio} % <spring:message code="common.title.over"/>
										</td>
									</c:if>
									<th scope="row"><spring:message code="lecture.title.exam.limitcnt"/></th>
									<td>
										${examVO.stareLimitCnt} <spring:message code="common.title.times.postfix"/>
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.timelimit"/></th>
									<td colspan="3">
										<c:choose>
											<c:when test="${examVO.examStareTm > 0}">
												${examVO.examStareTm} <spring:message code="common.title.min"/>
											</c:when>
											<c:otherwise>
												&nbsp;<spring:message code="lecture.message.exam.timelimit.nouse"/>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<th scope="row">시험 출제 문항수/배점</th>
									<td>
										<c:if test="${not empty examVO.selCnt && examVO.selCnt > 0 }">
											선택형  : ${examVO.selCnt }개 / ${examVO.selPnt } 점
										</c:if>
										<c:if test="${not empty examVO.shortCnt && examVO.shortCnt > 0 }">
											<br>단답형  : ${examVO.shortCnt }개 / ${examVO.shortPnt } 점
										</c:if>
										<c:if test="${not empty examVO.desCnt && examVO.desCnt > 0 }">
											<br>서술형 : ${examVO.desCnt }개 / ${examVO.desPnt } 점
										</c:if>
									</td>
								</tr>
									<c:if test="${examVO.examStareTypeCd eq 'R'}">
									<tr>
										<th scope="row"><spring:message code="lecture.title.exam.duration"/></th>
										<td colspan="3">
											<meditag:dateformat type="1" delimeter="." property="${examVO.examStartDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}"/>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="lecture.title.exam.result.date"/></th>
										<td>
											<meditag:dateformat type="1" delimeter="." property="${examVO.rsltCfrmDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.rsltCfrmDttm}"/>
										</td>
									</tr>
									</c:if>
								</c:if>
								<c:if test="${examVO.examTypeCd eq 'OFF'}">
									<tr>
										<th scope="row"><spring:message code="lecture.title.exam.duration"/></th>
										<td colspan="3">
											<meditag:dateformat type="1" delimeter="." property="${examVO.examStartDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}"/>
										</td>
									</tr>
								</c:if>
								
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.regyn"/></th>
									<td colspan="3">
										<c:set var="regYn" value="${examVO.regYn}"/>
										<c:if test="${empty examVO.regYn}"><c:set var="regYn" value="N"/></c:if>
										<meditag:codename code="${regYn}" category="REG_YN" />
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.desc"/></th>
									<td colspan="3" class="wordbreak">
										${fn:replace(examVO.examCts,crlf,"<br/>")}
									</td>
								</tr>
								</tbody>
							</table>
							<div class="txt-right mt20 mb40">
								<a href="javascript:goList()" class="btn3"><spring:message code="button.list"/></a>
							</div>

				<div class="row">
					<div class="col-md-12">
						<ul class="nav nav-tabs">
					 		<c:if test="${examVO.examTypeCd eq 'ON' }">
							<li><a href="<c:url value="/lec/exam/editFormExamMain?examSn=${examVO.examSn}&amp;crsCreCd=${examVO.crsCreCd}&amp;gubun=Q"/>"><spring:message code="lecture.title.exam.manage.question.tab"/></a></li>
							</c:if>
							<li><a href="<c:url value="/lec/exam/editFormExamMain?examSn=${examVO.examSn}&amp;crsCreCd=${examVO.crsCreCd}&amp;gubun=R"/>"><spring:message code="lecture.title.exam.manage.result.tab"/></a></li>
							<li class="active"><a href="#"><spring:message code="lecture.title.exam.manage.status.tab"/></a></li>
						</ul>
					</div>
				</div>

				<div class="row" style="margin-top:5px; margin-bottom:20px;">
					<div class="col-md-12">
						<div class="flot-chart">
							<div class="flot-chart-content" id="flot-line-chart-multi"></div>
						</div>
					</div>
				</div>

				<meditag:js src="/tpl/bootstrap/bower_components/flot/excanvas.min.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot/jquery.flot.js"/>
				<meditag:js src="/tpl/bootstrap/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"/>

<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		var data = [{
			label: "Number of Students",
			data : [
<c:forEach var="item" items="${statusList}" varStatus="status">
				[${item.keyCode}, ${item.keyValue}] <c:if test="${!status.last}">,</c:if>
</c:forEach>
			],
			color: ["green"]
		}];
		var position = 'right';
		var options = {
			series: {
	            lines: {
	                show: true
	            },
	            points : {
	            	show : true
	            }
	        },
	        xaxis: {
	        	axisLabelUseCanvas: true,
	         	axisLabelPadding: 1
	        },
	        yaxis: {
                axisLabelUseCanvas: true,
                tickSize: 1,
                tickDecimals: 0
	        },
	        legend: {
	            position: 'ne'
	        },
	        grid: {
	            hoverable: true //IMPORTANT! this is needed for tooltip to work
	        },
	        tooltip: true,
	        tooltipOpts: {
	        	content: "Score : %x , Students : %y",
	            onHover: function(flotItem, $tooltipEl) {
	                // console.log(flotItem, $tooltipEl);
	            }
	        }
		};
		var plot = $.plot($("#flot-line-chart-multi"), data, options);

		if('${examVO.examTypeCd}' == 'OFF'){
			$('.online').hide();
		}
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function goList() {
		document.location.href = cUrl("/lec/exam/examMain?crsCreCd=${examVO.crsCreCd}");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examForm').attr("action","/lec/exam/"+cmd);
		$('#examForm')[0].submit();
	}

	/*입력한 시간의 유효성을 체크한다.
	*  폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
	*/
	function validateTime(){

		var f = document.examForm;

		if(f["examStartDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.startdate"/>");
			return false;
		}

		if(f["examEndDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.enddate"/>");
			return false;
		}

		if(f["rsltCfrmDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.resultdate"/>");
			return false;
		}

		var asmtStartHour = f["examStartHour"].value;  //과제 시작일 시''
		var asmtStartMin = f["examStartMin"].value;   //과제 시작일 분''

		var asmtEndHour = f["examEndHour"].value;  //과제 종료일 시''
		var asmtEndMin = f["examEndMin"].value;   //과제 종료일 분''

		var extSendHour = f["rsltCfrmHour"].value; //결과확인일 시
		var extSendMin = f["rsltCfrmMin"].value;   //결과확인일 분

		if(asmtStartHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.starthour"/>" );
			f["examStartHour"].focus();
			return false;
		}
		if(asmtStartMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.startmin"/>" );
			f["examStartMin"].focus();
			return false;
		}
		if(asmtEndHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.endhour"/>" );
			f["examEndHour"].focus();
			return false;
		}
		if(asmtEndMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.endmin"/>" );
			f["examEndMin"].focus();
			return false;
		}
		if(extSendHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.resulthour"/>" );
			f["rsltCfrmHour"].focus();
			return false;
		}
		if(extSendMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.resultmin"/>" );
			f["rsltCfrmMin"].focus();
			return false;
		}

		if(asmtStartHour>24 || asmtStartHour>24  || extSendHour>24){
			alert("<spring:message code="lecture.message.exam.alert.validate.hour"/>");
			return false;
		}

		if(asmtStartMin>59 || asmtEndMin>59  || extSendMin>59){
			alert("<spring:message code="lecture.message.exam.alert.validate.min"/>");
			return false;
		}

		return true;
	}
</script>
