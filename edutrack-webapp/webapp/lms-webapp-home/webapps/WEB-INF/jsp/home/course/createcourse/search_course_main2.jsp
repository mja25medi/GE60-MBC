<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="createCourseVO" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="paging" value="Y"/>


				<div class="row">
					<div class="col-lg-12" style="margin-bottom:20px;">
						${creatCourseVO.crsCtgrCd}
						<!--CONTENTS START-->
						<ul class="nav nav-pills nav-justified" style="border:1px solid #dedede">
							<c:set var="itemCnt" value="1"/>
							<li class="wordbreak <c:if test="${empty createCourseVO.crsCtgrCd}">active</c:if>"><a href="<c:url value="/home/course/listSearchCourseMain"/>?curPage=1"><spring:message code="common.title.all"/></a></li>
							<c:forEach var="item" items="${crsCtgrList}" varStatus="status">
							<c:set var="itemCnt" value="${itemCnt +1}"/>
							<li class="wordbreak <c:if test="${item.crsCtgrCd eq createCourseVO.crsCtgrCd}">active</c:if>"><a href="<c:url value="/home/course/listSearchCourseMain"/>?curPage=1&amp;crsCtgrCd=${item.crsCtgrCd}">${item.crsCtgrNm}</a></li>
							<c:if test="${itemCnt >= 4 }">
						</ul>
						<ul class="nav nav-pills nav-justified" style="border:1px solid #dedede">
							<c:set var="itemCnt" value="0"/>
							</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
				<form id="createCourseForm" name="createCourseForm" onsubmit="return false">
				<div class="row">
					<div class="col-lg-12">
						<div class="alert alert-info">
							<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
							<input type="hidden" name="crsCtgrCd" id="crsCtgrCd" value="${vo.crsCtgrCd}"/>

							<div class="input-group" style="width:60%">
								<input type="text" name="searchValue" value="${vo.searchValue}" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" title="common.title.input.searchvalue"/>
								<span class="input-group-addon btn_search" style="cursor:pointor">
									<i class="fa fa-search"></i>
								</span>
							</div>
							<spring:message code="course.message.createcourse.msg.search"/>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top:20px;">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="panel-title"><i class="glyphicon glyphicon-book"></i> <spring:message code="course.title.createcourse.descinfo"/></span>
								<select name="listScale" onchange="javascript:courseSearch('1');" style="width:80px;margin-top:-4px;" class="form-control input-sm pull-right">
									<option value="10" <c:if test="${listScale eq '10' }">selected</c:if>>10</option>
									<option value="20" <c:if test="${listScale eq '20' }">selected</c:if>>20</option>
									<option value="40" <c:if test="${listScale eq '40' }">selected</c:if>>40</option>
									<option value="80" <c:if test="${listScale eq '80' }">selected</c:if>>80</option>
									<option value="100" <c:if test="${listScale eq '100' }">selected</c:if>>100</option>
									<option value="200" <c:if test="${listScale eq '200' }">selected</c:if>>200</option>
								</select>
							</div>

							<ul class="list-group">
							<c:if test="${empty courseList}">
								<li class="list-group-item">
									<spring:message code="course.message.createcourse.nodata"/>
								</li>
							</c:if>
							<c:forEach var="item" items="${courseList}">
								<li class="list-group-item">
									<h4 class="list-group-item-heading">
										<a href="#_none" onclick="javascript:showCreCrs('${item.crsCreCd}');">${item.crsCreNm}</a>
										<small>[ ${item.creYear} / ${item.creTerm} ]</small>
										<span class="pull-right">
											<c:choose>
												<c:when test="${item.enrlYn eq 'Y' }">
													<c:choose>
														<c:when test="${item.enrlAplcYn eq 'N'}">
															<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.eduend"/>')" title="<spring:message code="course.title.createcourse.eduend"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.eduend"/></a>
														</c:when>
														<c:otherwise>
															<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.enrollend"/>')" title="<spring:message code="course.title.createcourse.enrollend"/>" class="btn btn-primary btn-xs"><spring:message code="course.title.createcourse.enrollend"/></a>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:if test="${item.enrlAplcYn eq 'B'}">
														<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.noduration"/>')" title="<spring:message code="course.title.createcourse.noduration"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.noduration"/></a>
													</c:if>
													<c:if test="${item.enrlAplcYn eq 'Y'}">
														<c:choose>
															<c:when test="${item.nopLimitYn eq 'Y' && (item.enrlCnt + item.cnfmCnt) >= item.enrlNop }">
																<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.stdover"/>')" title="<spring:message code="course.title.createcourse.stdover"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.stdover"/></a>
															</c:when>
															<c:otherwise>
																<c:if test="${not empty USERNO}">
																<%-- <a href="/StudentHome.do?cmd=enrollCourse&amp;studentDTO.crsCtgrCd=${item.crsCtgrCd}&amp;studentDTO.crsCd=${item.crsCd}&amp;studentDTO.crsCreCd=${item.crsCreCd}" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs"><spring:message code="course.title.createcourse.enroll"/></a> --%>
																<a href="javascript:showCreCrsEnroll('${item.crsCreCd}');" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs"><spring:message code="course.title.createcourse.enroll"/></a>
																</c:if>
																<c:if test="${empty USERNO}">
																<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.login"/>')" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs"><spring:message code="course.title.createcourse.enroll"/></a>
																</c:if>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${item.enrlAplcYn eq 'I'}">
														<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.noduration"/>')" title="<spring:message code="course.title.createcourse.studying"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.studying"/></a>
													</c:if>
													<c:if test="${item.enrlAplcYn eq 'N'}">
														<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.eduend"/>')" title="<spring:message code="course.title.createcourse.eduend"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.eduend"/></a>
													</c:if>
												</c:otherwise>
											</c:choose>
										</span>
									</h4>
									<ul class="list-inline" style="margin-top:10px;">
										<li style="padding-right:15px;"><spring:message code="course.title.course.edumthd"/> : <meditag:codename code="${item.crsOperMthd}" category="CRS_OPER_MTHD" /></li>
										<li style="padding-right:15px;"><spring:message code="course.title.course.crstype"/> : <meditag:codename code="${item.crsOperType}" category="CRS_OPER_TYPE" /></li>
										<li style="padding-right:15px;"><spring:message code="course.title.course.edufee"/> :
											<c:if test="${item.eduPrice > 0 }">
												<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">${sessionScope.MNTRYUNIT}</c:if><fmt:formatNumber value="${item.eduPrice}"/><c:if test="${sessionScope.MNTRYPOS eq 'PO'}">${sessionScope.MNTRYUNIT}</c:if>
											</c:if>
											<c:if test="${item.eduPrice == 0 }">
												<spring:message code="course.title.createcourse.free.edufee"/>
											</c:if>
										</li>
										<li>
											<c:if test="${item.nopLimitYn eq 'Y' }">
												<c:set var="stuCnt" value="${item.cnfmCnt}"/>
												<c:if test="${item.cnfmCnt > item.enrlNop}"><c:set var="stuCnt" value="${item.enrlNop}"/></c:if>
												<spring:message code="course.title.course.ing.stdcnt"/>/<spring:message code="course.title.course.stdcnt"/> : ${stuCnt}/${item.enrlNop}
											</c:if>
											<c:if test="${item.nopLimitYn eq 'N' }">
												<spring:message code="course.title.course.ing.stdcnt"/> : ${item.cnfmCnt}
											</c:if>
											<spring:message code="common.title.cnt.user"/>
										</li>
										<li><spring:message code="course.title.course.target"/> : ${item.eduTarget}</li>
										<c:if test="${item.crsOperMthd eq 'OF' || item.crsOperMthd eq 'BL' }">
										<li class="wordbreak"><spring:message code="course.title.course.place"/> : ${item.oflnEduPlace}</li>
										</c:if>
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.duration.aplc"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlAplcStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlAplcEndDttm}"/></li>

										<c:choose>
											<c:when test="${item.crsOperType eq 'S' }">
										<li style="padding-right:15px;">
												<spring:message code="course.title.createcourse.eduday"/> : ${item.enrlDaycnt}<spring:message code="common.title.day"/>
										</li>
											</c:when>
											<c:otherwise>

												<c:choose>
													<c:when test="${item.crsOperMthd eq 'ON' }">
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.online"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
													</c:when>
													<c:when test="${item.crsOperMthd eq 'OF' }">
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.offline"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
													</c:when>
													<c:when test="${item.crsOperMthd eq 'BL' }">
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.online"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.offline"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.oflnStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.oflnEndDttm}"/></li>
													</c:when>
													<c:otherwise>
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
													</c:otherwise>
												</c:choose>

											</c:otherwise>
										</c:choose>

									</ul>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				</form>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="courseSearch"/>
					</div>
				</div>

<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				courseSearch(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			courseSearch(1);
		});

	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function courseSearch(page) {
		$('#createCourseForm')
		.find('input[name=curPage]').val(page).end()
		.attr("action","/home/course/listSearchCourseMain")
		document.createCourseForm.submit();
	}

	/**
	 * 개설 과정 정보 보기
	 */
	function showCreCrs(crsCreCd) {
		var addContent  = "<iframe id='courseInfoFrame' name='courseInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/home/course/readCoursePop"/>"
			+ "?crsCreCd="+crsCreCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(700, 650);
		modalBox.setTitle("<spring:message code="course.title.course.info"/>");
		modalBox.show();
	}

	function showCreCrsEnroll(crsCreCd) {
		location.href = "/home/course/enrollCourseViewMain?crsCreCd="+crsCreCd;
	}
</script>
