<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="imageBase" value="/img/"/>

			<ul class="list-group" style="margin-bottom:0px;">
				<c:set var="prevRatio" value="100"/>
				<c:forEach var="item" items="${contentsList}">
				<li class="list-group-item" style="padding-left:${(item.unitLvl) * 20}px">
					<c:set var="typeImg" value=""/>
					<c:set var="prgrChkImg" value=""/>
					<c:if test="${item.subCnt > 0 }"><c:set var="typeImg" value="<img src='${imageBase}/framework/icon/icon_category.gif' align='absmiddle'>"/></c:if>
					<c:if test="${item.subCnt <= 0 }"><c:set var="typeImg" value="<img src='${imageBase}/framework/icon/icon_contents.gif' align='absmiddle'>"/></c:if>
					<c:choose>
						<c:when test="${item.unitType eq 'L' }">
							<c:if test="${item.prgrChkYn eq 'Y'}"><c:set var="prgrChkImg" value="<img src='${imageBase}/framework/icon/icon_requird.gif' align='absmiddle'>"/></c:if>
							<c:if test="${item.prgrChkYn ne 'Y'}"><c:set var="prgrChkImg" value="<img src='${imageBase}/framework/icon/icon_optional.gif' align='absmiddle'>"/></c:if>
						</c:when>
						<c:otherwise>
							<c:set var="prgrChkImg" value=""/>
						</c:otherwise>
					</c:choose>
					<h5 class="list-group-item-heading">
						${typeImg} ${item.unitNm}
						<c:if test="${item.unitType eq 'L'}">
						<span class="pull-right">
							<c:if test="${CLASSUSERTYPE eq 'STU' }">
							<c:choose>
								<c:when test="${(crsCrsOnlnSbj.studyMthd eq 'SE' && prevRatio < 100) && item.prgrChkYn eq 'Y' }">
									<a href="javascript:alert('<spring:message code="lecture.message.contents.alert.nextview"/>');" class="btn btn-warning btn-xs"><i class="fa fa-play-circle fa-fw"></i> <spring:message code="lecture.title.contents.study.view"/></a>
								</c:when>
								<c:when test="${(item.prgrRatio == 100)}">
									<a href="javascript:viewContents('${item.sbjCd}','${item.unitCd}');" title="${item.unitNm}" class="btn btn-default btn-xs"><i class="fa fa-play-circle fa-fw"></i> <spring:message code="lecture.title.contents.study.view"/></a>
								</c:when>
								<c:otherwise>
									<a href="javascript:viewContents('${item.sbjCd}','${item.unitCd}');" title="${item.unitNm}" class="btn btn-warning btn-xs"><i class="fa fa-play-circle fa-fw"></i> <spring:message code="lecture.title.contents.study.view"/></a>
								</c:otherwise>
							</c:choose>
							</c:if>
							<c:if test="${CLASSUSERTYPE ne 'STU' }">
								<a href="javascript:viewContents('${item.sbjCd}','${item.unitCd}');" title="${item.unitNm}" class="btn btn-warning btn-xs"><i class="fa fa-play-circle fa-fw"></i> <spring:message code="lecture.title.contents.study.view"/></a>
							</c:if>
						</span>
						</c:if>
					</h5>
<%-- 					<c:if test="${item.unitType eq 'L'}">
					<ul class="list-inline" style="margin-top:10px;margin-left:18px;">
						<c:if test="${not empty item.atchFilePath}"><li><spring:message code="course.title.contents.file"/> : <a href="${item.atchFilePath}" target='_blank'><img src="<c:url value="/img/framework/icon/icon_project.gif"/>" alt="<spring:message code="lecture.title.contents.file.dowonload"/>" style="border:0"/></a></li></c:if>
						<li><spring:message code="lecture.title.contents.study.ratio"/> : ${item.prgrRatio}%</li>
						<li><spring:message code="lecture.title.contents.study.cnt"/> : ${item.connCnt}</li>
						<li><spring:message code="lecture.title.contents.study.time"/> : ${meditag:round((item.connTotTm / 60),0)} <spring:message code="common.title.min"/></li>
						<c:if test="${item.prgrChkType eq 'TIME' }">
						<li><spring:message code="lecture.title.contents.default.time"/> : ${item.critTm} <spring:message code="common.title.min"/></li>
						</c:if>
					</ul>
					</c:if>
					<c:if test="${item.unitType eq 'L'}"><c:set var="prevRatio" value="${item.prgrRatio}"/></c:if>
				 --%></li>
				</c:forEach>
				<c:if test="${empty contentsList}">
					<li class="list-group-item">
						<spring:message code="lecture.message.contents.list.nodata"/>
					</li>
				</c:if>
			</ul>
