<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="imageBase" value="/img/"/>

			<ul class="list-group">
				<c:if test="${not empty contentsVO.subList}">
				<c:forEach var="item1" items="${contentsVO.subList}">
				<li class="list-group-item" style="padding-right:0px;">
					<h5 class="list-group-item-heading">
						<c:if test="${not empty item1.subList}"><img src='${imageBase}/framework/icon/icon_category.gif' align='absmiddle'></c:if>
						<c:if test="${empty item1.subList}"><img src='${imageBase}/framework/icon/icon_contents.gif' align='absmiddle'></c:if>
						${item1.unitNm}
						<c:if test="${item1.unitType eq 'L'}">
						<span class="pull-right" style="padding-right:15px"><a href="javascript:viewContents('${item1.sbjCd}','${item1.unitCd}');" title="${item1.unitNm}" class="btn btn-warning btn-sm"><spring:message code="lecture.title.contents.study.view"/></a></span>
						</c:if>
					</h4>
					<c:if test="${item1.unitType eq 'L'}">
					<ul class="list-inline" style="margin-top:10px;">
						<c:if test="${not empty item1.atchFilePath}">
						<li>
							<spring:message code="course.title.contents.file"/> :
							<a href="${item1.atchFilePath}" target='_blank'><img src="<c:url value="/img/framework/icon/icon_project.gif"/>" alt="<spring:message code="lecture.title.contents.file.dowonload"/>" style="border:0"/></a>
						</li>
						</c:if>
						<li><spring:message code="lecture.title.contents.study.ratio"/> : ${item1.prgrRatio}%</li>
						<li><spring:message code="lecture.title.contents.study.cnt"/> : ${item1.connCnt}</li>
						<li><spring:message code="lecture.title.contents.study.time"/> : ${meditag:round((item1.connTotTm / 60),0)} <spring:message code="common.title.min"/></li>
						<li><spring:message code="lecture.title.contents.default.time"/> : ${item.critTm} <spring:message code="common.title.min"/></li>
					</ul>
					</c:if>

					<c:if test="${not empty item1.subList }">
					<ul class="list-group" style="margin-top:10px;">
						<c:forEach var="item2" items="${item1.subList}">
						<li class="list-group-item" style="padding-right:0px;">
							<h5 class="list-group-item-heading">
								<c:if test="${not empty item2.subList}"><img src='${imageBase}/framework/icon/icon_category.gif' align='absmiddle'></c:if>
								<c:if test="${empty item2.subList}"><img src='${imageBase}/framework/icon/icon_contents.gif' align='absmiddle'></c:if>
								${item2.unitNm}
								<c:if test="${item2.unitType eq 'L'}">
								<span class="pull-right" style="padding-right:15px"><a href="javascript:viewContents('${item2.sbjCd}','${item2.unitCd}');" title="${item2.unitNm}" class="btn btn-warning btn-sm"><spring:message code="lecture.title.contents.study.view"/></a></span>
								</c:if>
							</h5>
							<c:if test="${item2.unitType eq 'L'}">
							<ul class="list-inline" style="margin-top:10px;">
								<c:if test="${not empty item2.atchFilePath}">
								<li>
									<spring:message code="course.title.contents.file"/> :
									<a href="${item2.atchFilePath}" target='_blank'><img src="<c:url value="/img/framework/icon/icon_project.gif"/>" alt="<spring:message code="lecture.title.contents.file.dowonload"/>" style="border:0"/></a>
								</li>
								</c:if>
								<li><spring:message code="lecture.title.contents.study.ratio"/> : ${item2.prgrRatio}%</li>
								<li><spring:message code="lecture.title.contents.study.cnt"/> : ${item2.connCnt}</li>
								<li><spring:message code="lecture.title.contents.study.time"/> : ${meditag:round((item2.connTotTm / 60),0)} <spring:message code="common.title.min"/></li>
								<li><spring:message code="lecture.title.contents.default.time"/> : ${item2.critTm} <spring:message code="common.title.min"/></li>
							</ul>
							</c:if>
							<c:if test="${not empty item2.subList }">
							<ul class="list-group"  style="margin-top:10px;">
								<c:forEach var="item3" items="${item2.subList}">
								<li class="list-group-item" style="padding-right:0px;">
									<h5 class="list-group-item-heading">
										<c:if test="${not empty item3.subList}"><img src='${imageBase}/framework/icon/icon_category.gif' align='absmiddle'></c:if>
										<c:if test="${empty item3.subList}"><img src='${imageBase}/framework/icon/icon_contents.gif' align='absmiddle'></c:if>
										${item3.unitNm}
										<c:if test="${item3.unitType eq 'L'}">
										<span class="pull-right" style="padding-right:15px"><a href="javascript:viewContents('${item3.sbjCd}','${item3.unitCd}');" title="${item3.unitNm}" class="btn btn-warning btn-sm"><spring:message code="lecture.title.contents.study.view"/></a></span>
										</c:if>
									</h5>
									<c:if test="${item3.unitType eq 'L'}">
									<ul class="list-inline" style="margin-top:10px;">
										<c:if test="${not empty item3.atchFilePath}">
										<li>
											<spring:message code="course.title.contents.file"/> :
											<a href="${item3.atchFilePath}" target='_blank'><img src="<c:url value="/img/framework/icon/icon_project.gif"/>" alt="<spring:message code="lecture.title.contents.file.dowonload"/>" style="border:0"/></a>
										</li>
										</c:if>
										<li><spring:message code="lecture.title.contents.study.ratio"/> : ${item3.prgrRatio}%</li>
										<li><spring:message code="lecture.title.contents.study.cnt"/> : ${item3.connCnt}</li>
										<li><spring:message code="lecture.title.contents.study.time"/> : ${meditag:round((item3.connTotTm / 60),0)} <spring:message code="common.title.min"/></li>
										<li><spring:message code="lecture.title.contents.default.time"/> : ${item3.critTm} <spring:message code="common.title.min"/></li>
									</ul>
									</c:if>
								</li>
								</c:forEach>
							</c:if>
						</li>
						</c:forEach>
					</ul>
					</c:if>
				</li>
				</c:forEach>
				</c:if>
				<c:if test="${empty contentsVO.subList}">
				<li class="list-group-item"><spring:message code="lecture.message.contents.list.nodata"/></li>
				</c:if>
			</ul>
			<script type="text/javascript">
				showPrgrRatio('${totalRatio}');
			</script>