<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="finalScore" value="final" />

				<div class="row">
					<div class="col-lg-12">
						<div id="chartMessage"><p class="tm20 red"><spring:message code="student.title.result.score.final.chart.info"/></p></div>
				    	<p class="tm20 red"><spring:message code="student.message.result.view.msg2"/></p>
					</div>
				</div>
				<div class="row" style="margin-bottom:20px;text-align:center;padding-top:10px;" id="chartArea">

				</div>

				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<table class="table table-bordered">
				    		<caption class="sr-only"><spring:message code="student.title.result.view"/></caption>
				    		<colgroup>
				     			<col style="width:10%" />
				     			<col style="width:auto" />
				     			<col style="width:10%" />
				     			<col style="width:10%" />
				     			<col style="width:10%" />
				    		</colgroup>
				    		<thead>
								<tr>
				            		<th scope="col" rowspan="2"><spring:message code="student.title.result.score.item"/></th>
				            		<th scope="col" rowspan="2"><spring:message code="student.title.result.score.type"/></th>
				            		<th><spring:message code="student.title.result.score.origin"/></th>
				            		<th><spring:message code="student.title.result.score.scaled"/></th>
				            		<th><spring:message code="student.title.result.score.final"/></th>
				        		</tr>
				    		</thead>
				    		<tbody>
				    			<c:set var="totalScore" value="0"/>
				    			<!-- Progress.Start -->
								<c:if test="${createCourseVO.prgrRatio > 0}">
								<tr>
				    				<td rowspan="3" class="text-center"><spring:message code="student.title.result.ratio.prgr"/><br /><span class="red">(${createCourseVO.prgrRatio}%)</span></td>
									<td class="wordbreak"><spring:message code="student.title.result.ratio.prgr"/></td>
									<td class="text-right">${bookmarkVO.prgrRatio}%</td>
									<td rowspan="3" class="text-right">${meditag:round( (bookmarkVO.prgrRatio  ) * (createCourseVO.prgrRatio/100), 1)}

									</td>
									<td rowspan="3" class="text-right">
										<c:choose>
										<c:when test="${vo.prgrScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											${vo.prgrScore}
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
				    				<td class="wordbreak"><spring:message code="student.title.result.cnt.prgr"/></td>
				    				<td class="text-right">${bookmarkVO.connCnt}</td>
								</tr>
								<tr>
				    				<td class="wordbreak"><spring:message code="student.title.result.time.prgr"/></td>
				    				<td class="text-right">${bookmarkVO.connTm} <spring:message code="common.title.min"/></td>
								</tr>
									<c:set var="prgrScore" value="${meditag:round( (bookmarkVO.prgrRatio  ) * (createCourseVO.prgrRatio/100), 1)}"/>
									<c:if test="${not empty prgrScore}">
										<c:set var="totalScore" value="${totalScore + prgrScore}"/>
									</c:if>
								</c:if>
								<!-- Progress.End -->
								<!-- Attend.Start -->
								<c:if test="${createCourseVO.attdRatio > 0 }">
								<tr>
				    				<td class="text-center"><spring:message code="student.title.result.ratio.attd"/><br/><span class="red">(${createCourseVO.attdRatio}%)</span></td>
									<td class="wordbreak">　</td>
									<td>　</td>
									<td class="text-right">${vo.attdScore}</td>
									<td class="text-right">
										<c:choose>
										<c:when test="${vo.attdScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											${vo.attdScore}
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
									<c:if test="${not empty vo.attdScore}">
										<c:set var="totalScore" value="${totalScore + vo.attdScore}"/>
									</c:if>
								</c:if>
								<!-- Attend.End -->
								<!-- Exam.Start -->
								<c:if test="${createCourseVO.examRatio > 0}">
									<c:set var="sumGetScore" value="0"/>
									<c:set var="sumTotScore" value="0"/>
									<c:if test="${not empty examList}">
										<c:forEach items="${examList}" var="exam" varStatus="exstatus">
										<%-- 시험응시 완료와 상관없이 총점, 소계를 계산한다. --%>
											<c:if test="${exam.rateYn eq 'Y' }">
											<c:set var="sumGetScore" value="${sumGetScore + exam.totGetScore}"/>
											</c:if>
											<c:set var="sumTotScore" value="${sumTotScore + 1}"/>
								<tr>
									<c:if test="${exstatus.count eq 1}">
									<td rowspan="${fn:length(examList) + 1}" class="text-center"><spring:message code="lecture.title.classroom.status.exam" /><br /><span class="red">(${createCourseVO.examRatio}%)</span></td>
									</c:if>
									<td class="wordbreak">${exam.examTitle} </td>
									<td class="text-right">
										<c:if test="${exam.rateYn eq 'Y'}"><fmt:formatNumber value="${exam.totGetScore}" pattern="#.#" minFractionDigits="1"/></c:if>
										<c:if test="${exam.rateYn ne 'Y'}"><fmt:formatNumber value="0" pattern="#.#" minFractionDigits="1"/></c:if>
									</td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
								</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty examList}">
								<tr>
				    				<td rowspan="2" class="text-center"><spring:message code="student.title.result.ratio.exam"/><br /><span class="red">(${createCourseVO.examRatio}%)</span></td>
				    				<td class="wordbreak"><spring:message code="lecture.message.exam.nodata"/></td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
								</tr>
									</c:if>

								<tr>
				    				<td class="wordbreak" class="text-center"><strong><spring:message code="student.title.result.sum.group"/></strong></td>
				    				<td class="text-right"><strong><fmt:formatNumber value="${sumGetScore}" pattern="#.#" minFractionDigits="1"/></strong></td>
									<td class="text-right"><strong>
										<c:choose>
										<c:when test="${sumGetScore > 0 && sumTotScore > 0 }">
											<fmt:formatNumber value="${meditag:round((sumGetScore/sumTotScore)*(createCourseVO.examRatio/100),2)}" pattern="#.#" minFractionDigits="1"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="0" pattern="#.#" minFractionDigits="1"/>
										</c:otherwise>
										</c:choose></strong>
									</td>
									<td class="text-right"><strong>
										<c:choose>
										<c:when test="${vo.examScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="${vo.examScore}" pattern="#.#" minFractionDigits="1"/>
										</c:otherwise>
										</c:choose>
										</strong>
									</td>
								</tr>
									<c:if test="${sumGetScore > 0 && sumTotScore > 0 }">
										<c:set var="totalScore" value="${totalScore + meditag:round( (sumGetScore/sumTotScore)*(createCourseVO.examRatio/100),1 )}"/>
									</c:if>
								</c:if>
								<!-- Exam.End -->
								<!-- Forum.Start -->
								<c:if test="${createCourseVO.forumRatio > 0 }">
									<c:set var="sumForumScore" value="0"/>
									<c:set var="sumForumTotScore" value="0"/>
									<c:if test="${not empty forumList}">
										<c:forEach items="${forumList}" var="forum" varStatus="frstatus">
											<c:if test="${forum.rsltYn eq 'Y'  }">
											<c:set var="sumForumScore" value="${sumForumScore + forum.score}"/>
											<c:set var="sumForumTotScore" value="${sumForumTotScore + 100}"/>
											</c:if>
								<tr>
												<c:if test="${frstatus.count eq 1}">
									<td rowspan="${fn:length(forumList) + 1}" class="text-center"><spring:message code="student.title.result.ratio.forum"/><br /><span class="red">(${createCourseVO.forumRatio}%)</span></td>
												</c:if>
									<td class="wordbreak">${forum.forumTitle}</td>
									<td class="text-right">
										<c:if test="${forum.rsltYn eq 'Y'  }"><fmt:formatNumber value="${forum.score}" pattern="#.#" minFractionDigits="1"/></c:if>
										<c:if test="${forum.rsltYn ne 'Y'  }"><fmt:formatNumber value="0" pattern="#.#" minFractionDigits="1"/></c:if>
									</td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
								</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty forumList}">
								<tr>
				    				<td rowspan="2" class="text-center"><spring:message code="student.title.result.ratio.forum"/><br /><span class="red">(${createCourseVO.asmtRatio}%)</span></td>
				    				<td class="wordbreak"><spring:message code="lecture.message.forum.nodata"/></td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
								</tr>
									</c:if>
								<tr>
				    				<td class="wordbreak"><strong><spring:message code="student.title.result.sum.group"/></strong></td>
				    				<td class="text-right"><strong><fmt:formatNumber value="${sumForumScore}" pattern="#.#" minFractionDigits="1"/></strong></td>
									<td class="text-right"><strong>
										<c:choose>
										<c:when test="${sumForumScore > 0 && sumForumTotScore > 0 }">
										<fmt:formatNumber value="${meditag:round((sumForumScore/sumForumTotScore*100)*(createCourseVO.forumRatio/100),1)}" pattern="#.#" minFractionDigits="1"/>
										</c:when>
										<c:otherwise>
											0
										</c:otherwise>
										</c:choose></strong>
									</td>
									<td class="text-right"><strong>
										<c:choose>
										<c:when test="${vo.forumScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="${vo.forumScore}" pattern="#.#" minFractionDigits="1"/>
										</c:otherwise>
										</c:choose>
										</strong>
									</td>
								</tr>
									<c:if test="${sumForumScore > 0 && sumForumTotScore > 0 }">
										<c:set var="totalScore" value="${totalScore + (sumForumScore/sumForumTotScore*100)*(createCourseVO.forumRatio/100)}"/>
									</c:if>
								</c:if>
								<!-- Forum.End -->
								<!-- Assignment.Start -->
								<c:if test="${createCourseVO.asmtRatio >0}">
									<c:set var="sumAsmtScore" value="0"/>
									<c:set var="sumAsmtTotScore" value="0"/>
									<c:if test="${not empty asmtList}">
										<c:set var="sumAsmtTotScore" value="${fn:length(asmtList) * 100}"/>
										<c:forEach items="${asmtList}" var="asmt" varStatus="asstatus">
											<c:if test="${asmt.rsltYn eq 'Y'  }">
											<c:set var="sumAsmtScore" value="${sumAsmtScore + asmt.score}"/>
											</c:if>
								<tr>
											<c:if test="${asstatus.count eq 1}">
									<td rowspan="${fn:length(asmtList) + 1}" class="text-center"><spring:message code="student.title.result.ratio.asmt"/><br /><span class="red">(${createCourseVO.asmtRatio}%)</span></td>
											</c:if>
									<td class="wordbreak">${asmt.asmtTitle}</td>
									<td class="text-right">
										<c:if test="${asmt.rsltYn eq 'Y'  }"><fmt:formatNumber value="${asmt.score}" pattern="#.#" minFractionDigits="1"/></c:if>
										<c:if test="${asmt.rsltYn ne 'Y'  }"><fmt:formatNumber value="0" pattern="#.#" minFractionDigits="1"/></c:if>
									</td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
								</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty asmtList}">
								<tr>
				    				<td rowspan="2" class="text-center"><spring:message code="student.title.result.ratio.asmt"/><br /><span class="red">(${createCourseVO.asmtRatio}%)</span></td>
				    				<td class="wordbreak"><spring:message code="lecture.message.assignment.nodata"/></td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
				    				<td class="text-right">-</td>
								</tr>
									</c:if>
								<tr>
				    				<td class="subject"><strong><spring:message code="student.title.result.sum.group"/></strong></td>
				    				<td class="text-right"><strong><fmt:formatNumber value="${sumAsmtScore}" pattern="#.#" minFractionDigits="1"/></strong></td>
									<td class="text-right"><strong>
										<c:choose>
										<c:when test="${sumAsmtScore > 0 && sumAsmtTotScore > 0 }">
										<fmt:formatNumber value="${meditag:round((sumAsmtScore/sumAsmtTotScore*100)*(createCourseVO.asmtRatio/100),1)}" pattern="#.#" minFractionDigits="1"/>
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="0" pattern="#.#" minFractionDigits="1"/>
										</c:otherwise>
										</c:choose></strong>
									</td>
									<td class="text-right"><strong>
										<c:choose>
										<c:when test="${vo.asmtScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="${vo.asmtScore}" pattern="#.#" minFractionDigits="1"/>
										</c:otherwise>
										</c:choose></strong>
									</td>
								</tr>
									<c:if test="${sumAsmtScore > 0 && sumAsmtTotScore > 0 }">
										<c:set var="totalScore" value="${totalScore + (sumAsmtScore/sumAsmtTotScore*100)*(createCourseVO.asmtRatio/100)}"/>
									</c:if>
								</c:if>
								<!-- Assignment.End -->
								<!-- Join.Start -->
								<c:if test="${createCourseVO.joinRatio > 0 }">
								<tr>
				    				<td class="text-center"><spring:message code="student.title.result.ratio.join"/><br/><span class="red">(${createCourseVO.joinRatio}%)</span></td>
									<td class="wordbreak">　</td>
									<td class="text-right">　</td>
									<td class="text-right">${vo.joinScore}</td>
									<td class="text-right">
										<c:choose>
										<c:when test="${vo.joinScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											${vo.joinScore}
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
									<c:if test="${not empty vo.joinScore}">
										<c:set var="totalScore" value="${totalScore + vo.joinScore}"/>
									</c:if>
								</c:if>
								<!-- Join.End -->
								<!-- Etc.Start -->
								<c:if test="${createCourseVO.etcRatio > 0 }">
								<tr>
				    				<td class="text-center"><spring:message code="student.title.result.ratio.etc"/><br/><span class="red">(${createCourseVO.etcRatio}%)</span></td>
									<td class="wordbreak">　</td>
									<td class="text-right">　</td>
									<td class="text-right">${vo.etcScore}</td>
									<td class="text-right">
										<c:choose>
										<c:when test="${vo.etcScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											${vo.etcScore}
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
									<c:if test="${not empty vo.etcScore}">
										<c:set var="totalScore" value="${totalScore + vo.etcScore}"/>
									</c:if>
								</c:if>
								<!-- Etc.End -->
								<tr>
				    				<td class="text-center"><strong><spring:message code="student.title.result.totalscore"/></strong></td>
				    				<td class="wordbreak">　</td>
				    				<td class="text-right">　</td>
				    				<td class="text-right"><strong>${meditag:round(totalScore,1)}</strong></td>
				    				<td class="text-right"><strong>
										<c:choose>
										<c:when test="${vo.totScore == 0 }">
											<c:set var="finalScore" value="ready" />
											<spring:message code="student.title.result.score.final.ready"/>
										</c:when>
										<c:otherwise>
											${meditag:round(vo.totScore,1)}
										</c:otherwise>
										</c:choose>
									</strong></td>
								</tr>
				        	</tbody>
				    	</table>
				    </div>

				</div>

<script type="text/javascript">

$(document).ready(function() {
	if(typeof d3 == 'undefined') {
		$('#chartMessage').hide();
		$('#chartArea').hide();
	} else {
	//try {
		var width = 400,
		height = 400,
		radius = Math.min(width, height) / 2,
		innerRadius = 0.3 * radius;

<c:if test="${finalScore eq 'final' }">
		width = 320;
		height = 320;
		radius = Math.min(width, height) / 2;
		innerRadius = 0.3 * radius;
</c:if>

		var pie = d3.layout.pie()
					.sort(null)
					.value(function(d) { return d.width; });

		var tip = d3.tip()
					.attr('class', 'd3-tip')
					.offset([0, 0])
					.html(function(d) {
						return d.data.label + ": <span style='color:orangered'>" + d.data.tip + "</span>";
					});

		var arc = d3.svg.arc()
					.innerRadius(innerRadius)
					.outerRadius(function (d) {
						return (radius - innerRadius) * (d.data.score / 100.0) + innerRadius;
					});

		var outlineArc =  d3.svg.arc()
		    				.innerRadius(innerRadius)
		    				.outerRadius(radius);

		var svg = d3.select("#chartArea").append("svg")
					.attr("width", width)
					.attr("height", height)
					.attr("id", "svgChart")
					.append("g")
					.attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

		svg.call(tip);
		/* /libs/d3chart/aster_plot/sample.json */
		d3.json(
			'/lec/eduResult/viewScoreChart',
			function(error, data) {
				data.forEach(function(d) {
					d.id     =  d.id;
					d.order  = +d.order;
					d.color  =  d.color;
					d.weight = +d.weight;
					d.score  = +d.score;
					d.width  = +d.weight;
					d.label  =  d.label;
					d.tip  =  d.tip;
				});

				var path =   svg.selectAll(".solidArc")
			  					.data(pie(data))
			  					.enter().append("path")
			  					.attr("fill", function(d) { return d.data.color; })
			  					.attr("class", "solidArc")
			  					.attr("stroke", "gray")
			  					.attr("d", arc)
			  					.on('mouseover', tip.show)
			  					.on('mouseout', tip.hide);

				var outerPath =  svg.selectAll(".outlineArc")
			  						.data(pie(data))
			  						.enter().append("path")
			  						.attr("fill", "none")
			  						.attr("id", function(d) { return d.data.id; })
			  						.attr("stroke", "gray")
			  						.attr("class", "outlineArc")
			  						.attr("d", outlineArc);


				// calculate the weighted mean score
				var score = data.reduce(function(a, b) {
							  return a + (b.score * b.weight);
							}, 0) /
							data.reduce(function(a, b) {
			  					return a + b.weight;
							}, 0);

				svg.append("svg:text")
					.attr("class", "aster-score")
					.attr("dy", ".35em")
					.attr("text-anchor", "middle")
					.text("${meditag:round(totalScore,1)}");

				/* 진도 */
				<c:if test="${createCourseVO.prgrRatio > 0}">
				svg.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
	        		.attr("startOffset","0%")
	        		.style("text-anchor","start")
	        		.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#PRGR").text("<spring:message code="student.title.result.ratio.prgr"/>(${createCourseVO.prgrRatio}%)");
				</c:if>
				/* 출석 */
				<c:if test="${createCourseVO.attdRatio > 0 }">
				svg.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
	        		.attr("startOffset","0%")
	        		.style("text-anchor","start")
	        		.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#ATTD").text("<spring:message code="student.title.result.ratio.attd"/>(${createCourseVO.attdRatio}%)");
				</c:if>
				/* 시험 */
				<c:if test="${createCourseVO.examRatio > 0}">
				svg.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
	        		.attr("startOffset","0%")
	        		.style("text-anchor","start")
	        		.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#EXAM").text("<spring:message code="lecture.title.classroom.status.exam" />(${createCourseVO.examRatio}%)");
				</c:if>
				/* 토론 */
				<c:if test="${createCourseVO.forumRatio > 0 }">
				svg.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
	        		.attr("startOffset","0%")
	        		.style("text-anchor","start")
	        		.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#FORUM").text("<spring:message code="student.title.result.ratio.forum"/>(${createCourseVO.forumRatio}%)");
				</c:if>
				/* 과제 */
				<c:if test="${createCourseVO.asmtRatio >0}">
				svg.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
	        		.attr("startOffset","0%")
	        		.style("text-anchor","start")
	        		.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#ASMT").text("<spring:message code="student.title.result.ratio.asmt"/>(${createCourseVO.asmtRatio}%)");
				</c:if>
				/* 참여 */
				<c:if test="${createCourseVO.joinRatio > 0 }">
				svg.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
	        		.attr("startOffset","0%")
	        		.style("text-anchor","start")
	        		.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#JOIN").text("<spring:message code="student.title.result.ratio.join"/>(${createCourseVO.joinRatio}%)");
				</c:if>
				/* 기타 */
				<c:if test="${createCourseVO.etcRatio > 0 }">
				svg.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
	        		.attr("startOffset","0%")
	        		.style("text-anchor","start")
	        		.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#ETC").text("<spring:message code="student.title.result.ratio.etc"/>(${createCourseVO.etcRatio}%)");
				</c:if>
		});

		<c:if test="${finalScore eq 'final' }">
		$("#svgChart").css("width","360px").css("height","360px").css("padding","25px 20px 20px 20px").css("float","left");
		</c:if>

		<c:if test="${finalScore ne 'final' }">
		$("#svgChart").css("width","460px").css("height","470px").css("padding","25px 20px 20px 20px");
		</c:if>

		/* 점수합산 종료후 차트 */
<c:if test="${finalScore eq 'final' }">

		var tipFinal = d3.tip()
				.attr('class', 'd3-tip')
				.offset([0, 0])
				.html(function(d) {
					return d.data.label + ": <span style='color:orangered'>" + d.data.tip + "</span>";
				});
		var arcFinal = d3.svg.arc()
				.innerRadius(innerRadius)
				.outerRadius(function (d) {
					return (radius - innerRadius) * (d.data.score / 100.0) + innerRadius;
				});

		var outlineArcFinal =  d3.svg.arc()
				.innerRadius(innerRadius)
				.outerRadius(radius);

		var svgFinal = d3.select("#chartArea").append("svg")
				.attr("width", width)
				.attr("height", height)
				.attr("id", "svgChartFinal")
				.append("g")
				.attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

		svgFinal.call(tipFinal);


		/* /libs/d3chart/aster_plot/sample.json */
		d3.json(
			'/lec/eduResult/viewFinalScoreChart',
			function(error, data) {
				data.forEach(function(d) {
					d.id     =  d.id;
					d.order  = +d.order;
					d.color  =  d.color;
					d.weight = +d.weight;
					d.score  = +d.score;
					d.width  = +d.weight;
					d.label  =  d.label;
					d.tip  =  d.tip;
				});

				var path =   svgFinal.selectAll(".solidArc")
			  					.data(pie(data))
			  					.enter().append("path")
			  					.attr("fill", function(d) { return d.data.color; })
			  					.attr("class", "solidArc")
			  					.attr("stroke", "gray")
			  					.attr("d", arc)
			  					.on('mouseover', tip.show)
			  					.on('mouseout', tip.hide);

				var outerPath =  svgFinal.selectAll(".outlineArc")
			  						.data(pie(data))
			  						.enter().append("path")
			  						.attr("fill", "none")
			  						.attr("id", function(d) { return d.data.id; })
			  						.attr("stroke", "gray")
			  						.attr("class", "outlineArc")
			  						.attr("d", outlineArc);


				// calculate the weighted mean score
				var score = data.reduce(function(a, b) {
							  return a + (b.score * b.weight);
							}, 0) /
							data.reduce(function(a, b) {
			  					return a + b.weight;
							}, 0);

				svgFinal.append("svg:text")
					.attr("class", "aster-score")
					.attr("dy", ".35em")
					.attr("text-anchor", "middle")
					.text("${meditag:round(vo.totScore,1)}");

				/* 진도 */
				<c:if test="${createCourseVO.prgrRatio > 0}">
				svgFinal.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
					.attr("startOffset","0%")
					.style("text-anchor","start")
					.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#PRGR").text("<spring:message code="student.title.result.ratio.prgr"/>(${createCourseVO.prgrRatio}%)");
				</c:if>
				/* 출석 */
				<c:if test="${createCourseVO.attdRatio > 0 }">
				svgFinal.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
					.attr("startOffset","0%")
					.style("text-anchor","start")
					.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#ATTD").text("<spring:message code="student.title.result.ratio.attd"/>(${createCourseVO.attdRatio}%)");
				</c:if>
				/* 시험 */
				<c:if test="${createCourseVO.examRatio > 0}">
				svgFinal.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
					.attr("startOffset","0%")
					.style("text-anchor","start")
					.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#EXAM").text("<spring:message code="lecture.title.classroom.status.exam" />(${createCourseVO.examRatio}%)");
				</c:if>
				/* 토론 */
				<c:if test="${createCourseVO.forumRatio > 0 }">
				svgFinal.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
					.attr("startOffset","0%")
					.style("text-anchor","start")
					.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#FORUM").text("<spring:message code="student.title.result.ratio.forum"/>(${createCourseVO.forumRatio}%)");
				</c:if>
				/* 과제 */
				<c:if test="${createCourseVO.asmtRatio >0}">
				svgFinal.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
					.attr("startOffset","0%")
					.style("text-anchor","start")
					.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#ASMT").text("<spring:message code="student.title.result.ratio.asmt"/>(${createCourseVO.asmtRatio}%)");
				</c:if>
				/* 참여 */
				<c:if test="${createCourseVO.joinRatio > 0 }">
				svgFinal.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
					.attr("startOffset","0%")
					.style("text-anchor","start")
					.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#JOIN").text("<spring:message code="student.title.result.ratio.join"/>(${createCourseVO.joinRatio}%)");
				</c:if>
				/* 기타 */
				<c:if test="${createCourseVO.etcRatio > 0 }">
				svgFinal.append("svg:text")
					.attr("class", "aster-label")
					.append("svg:textPath")
					.attr("startOffset","0%")
					.style("text-anchor","start")
					.attr("alignment-baseline","text-after-edge")
					.attr("xlink:href", "#ETC").text("<spring:message code="student.title.result.ratio.etc"/>(${createCourseVO.etcRatio}%)");
				</c:if>
			});

		$("#svgChartFinal").css("width","360px").css("height","360px").css("padding","25px 20px 20px 20px");

</c:if>


	}


});

</script>
<c:if test="${finalScore eq 'final' }">
<style>
.aster-label {
    line-height: 1;
    font-size: 11px;!important;
    font-family: Meiryo, "맑은 고딕", "Malgun gothic", dotum , "Helvetica Neue", Helvetica, Arial, sans-serif;
}
</style>
</c:if>

