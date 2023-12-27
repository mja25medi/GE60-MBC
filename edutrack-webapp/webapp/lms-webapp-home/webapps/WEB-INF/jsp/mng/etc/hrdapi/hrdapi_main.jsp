<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
		<section class="content">
			<div class="row">
				<div class="col-md-12 col-xs-12 box">
					<div class="overlay">
						<i class="fa fa-refresh fa-spin"></i>
					</div>
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">회원 정보 API</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						<div class="box-body">
							<div class="pull-right">
								<div class="input-group" style="float:left; line-height:30px;">
									<button type="button" class="btn btn-info btn-sm" onclick="javascript:listUserApiInfo();">검색</button>
								</div>
							</div>
							<div class="clearfix"></div>							
							<div class="col-md-12">
								<div id="userInfoApiList" style="margin-top:5px;">
									<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:150px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col">api명</th>
												<th scope="col">전체수</th>
												<th scope="col">성공수</th>
												<th scope="col">실패수</th>
												<th scope="col">대기수</th>
												<th scope="col">등록</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="8">검색버튼을 클릭해주세요</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">회원 로그인 API</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						<div class="box-body">
							<div class="pull-right">
								<div class="input-group" style="float:left;width:130px;margin-top: -3px;">
									<input type="text" name="searchFrom" id="userLoginSearchFrom" value="${nowDate}" class="form-control input-sm"/>
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('userLoginSearchFrom')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<div class="input-group" style="float:left;margin-left: 10px;margin-top:1px;">~
								</div>
								<div class="input-group" style="float:left;width:130px;margin-left:10px; margin-right: 10px;margin-top: -3px;">
									<input type="text" name="searchTo" id="userLoginSearchTo" value="${nowDate}" class="form-control input-sm"/>
									<span class="input-group-addon btn-sm" onclick="_clickCalendar('userLoginSearchTo')" style="cursor:pointer">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
								<meditag:datepicker name1="userLoginSearchFrom" name2="userLoginSearchTo" />
								<div class="input-group"  style="float:left;margin-left: -10px;margin-right: 2px;margin-top: -3px;border-color: #d2d6de;border-right-style: solid;border-right-width: thin;height: 30px;">
									<span class="input-group-addon" onclick="listUserLoginApiInfo(1)" style="cursor:pointer;width:10px;">
										<i class="fa fa-search"></i>
									</span>
								</div>
							</div>
							<div class="clearfix"></div>							
							<div class="col-md-12">
								<div id="userLoginApiList" style="margin-top:5px;">
									<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:75px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col">api명</th>
												<th scope="col">전체수</th>
												<th scope="col">성공수</th>
												<th scope="col">실패수</th>
												<th scope="col">대기수</th>
												<th scope="col">등록</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="8">기간을 선택해주세요</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					
					<!-- 수강정보 API Start -->
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">수강 정보 API</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						<div class="box-body">
							<div class="pull-right">
								<div class="input-group" style="float:left; line-height:30px;">
									<select name="crsCd_std" id="crsCd_std" class="form-control input-sm" style="float:left;" onchange="listStdApiInfo();">
										<option value="">기수를 선택하세요</option>
										<c:forEach items="${courseList }" var="courseItem">
											<option value="${courseItem.crsCd }" <c:if test="${courseVO.crsCd eq courseItem.crsCd }">selected</c:if>>${courseItem.crsYear }년도 ${courseItem.crsTerm }기수</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="clearfix"></div>							
							<div class="col-md-12">
								<div id="stdInfoApiList" style="margin-top:5px;">
									<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:75px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col">api명</th>
												<th scope="col">전체수</th>
												<th scope="col">성공수</th>
												<th scope="col">실패수</th>
												<th scope="col">대기수</th>
												<th scope="col">등록</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="6">기간을 선택해주세요</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- 수강정보 API END -->
					
					<!-- 과정정보 API Start -->
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">과정 정보 API</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						<div class="box-body">
							<div class="clearfix"></div>							
							<div class="col-md-12">
								<div id="courseInfoApiList" style="margin-top:5px;">
									<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:75px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col">api명</th>
												<th scope="col">전체수</th>
												<th scope="col">성공수</th>
												<th scope="col">실패수</th>
												<th scope="col">대기수</th>
												<th scope="col">등록</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="6">기간을 선택해주세요</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- 과정정보 API END -->
					
					<!-- 수업정보 API Start -->
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">수업 정보 API</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						<div class="box-body">
							<div class="pull-right">
								<div class="input-group" style="float:left; line-height:30px;">
									<select name="creInfoCrsCd" id="creInfoCrsCd" class="form-control input-sm" style="float:left;" onchange="listCreApiInfo();">
										<option value="">기수를 선택하세요</option>
										<c:forEach items="${courseList }" var="courseItem">
											<option value="${courseItem.crsCd }" <c:if test="${courseVO.crsCd eq courseItem.crsCd }">selected</c:if>>${courseItem.crsYear }년도 ${courseItem.crsTerm }기수</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="clearfix"></div>							
							<div class="col-md-12">
								<div id="creInfoApiList" style="margin-top:5px;">
									<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:75px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col">api명</th>
												<th scope="col">전체수</th>
												<th scope="col">성공수</th>
												<th scope="col">실패수</th>
												<th scope="col">대기수</th>
												<th scope="col">등록</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="6">기수를 선택해주세요</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- 수업정보 API END -->
					<!-- 성적이력 API START -->
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">성적이력 API</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>
						</div>
						
						<div class="box-body">
							<div class="pull-right">
								<div class="input-group" style="float:left; line-height:30px;">
									<select name="scoreCrsCd" id="scoreCrsCd" class="form-control input-sm" style="float:left;" onchange="listScoreApiInfo();">
										<option value="">기수를 선택하세요</option>
										<c:forEach items="${courseList }" var="courseItem">
											<option value="${courseItem.crsCd }" <c:if test="${courseVO.crsCd eq courseItem.crsCd }">selected</c:if>>${courseItem.crsYear }년도 ${courseItem.crsTerm }기수</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="clearfix"></div>		
							<div class="col-md-12">
								<div id="scoreInfoApiList" style="margin-top:5px;">
									<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:auto;min-width:90px;"/>
											<col style="width:75px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col">api명</th>
												<th scope="col">전체수</th>
												<th scope="col">성공수</th>
												<th scope="col">실패수</th>
												<th scope="col">대기수</th>
												<th scope="col">등록</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="8">기간을 선택해주세요</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- 성적이력 API END -->
				</div>
			</div>
		</section>

		<script type="text/javascript">
			var ItemDTO = {
					sortKey : ""
				};
			
			$(document).ready(function() {
				modalBox = new $M.ModalDialog({
					"modalid" : "modal1"
				});
				$(".overlay").hide();
				listCourseApiInfo();
			});
		
			/**
			 * 회원 API 정보 조회
			 */
			function listUserApiInfo() {
				var searchKey = $('#searchKey').val();
				var searchValue = $('#searchValue').val();
				var listScale = $("#listScale option:selected").val();

				$('#userInfoApiList')
					.load(cUrl("/mng/etc/HrdApi/listUserInfoApi"),
						{ 
						  "searchKey":searchKey,
						  "searchValue":searchValue,
						  "listScale":listScale
						 }
					);

			}
		
			/**
			 * 수강 API 정보 조회
			 */
			function listStdApiInfo() {
				var searchKey = $('#searchKey').val();
				var searchValue = $('#searchValue').val();
				var listScale = $("#listScale option:selected").val();
				var crsCd = $("#crsCd_std").val();

				$('#stdInfoApiList')
					.load(cUrl("/mng/etc/HrdApi/listStdInfoApi"),
						{ 
						  "searchKey":searchKey,
						  "searchValue":searchValue,
						  "listScale":listScale,
						  "crsCd":crsCd
						 }
					);

			}
			
			/**
			 * 과정 API 정보 조회
			 */
			function listCourseApiInfo() {
				var searchKey = $('#searchKey').val();
				var searchValue = $('#searchValue').val();
				var listScale = $("#listScale option:selected").val();

				$('#courseInfoApiList')
					.load(cUrl("/mng/etc/HrdApi/listCourseInfoApi"),
						{ 
						  
						 }
					);

			}
		
			/**
			 * 수업 API 정보 조회
			 */
			function listCreApiInfo() {
				var crsCd = $("#creInfoCrsCd").val();

				$('#creInfoApiList')
					.load(cUrl("/mng/etc/HrdApi/listCreInfoApi"),
						{ 
						  "crsCd":crsCd
						 }
					);

			}
			
			
			/**
			 * 회원 로그인 API 정보 조회
			 */
			function listUserLoginApiInfo() {
				var searchFrom = $("#userLoginSearchFrom").val();
				var searchTo = $("#userLoginSearchTo").val();

				$('#userLoginApiList')
					.load(cUrl("/mng/etc/HrdApi/listUserLoginApi"),
						{ 
						  "searchFrom":searchFrom,
						  "searchTo":searchTo
						 }
					);

			}
			
			/**
			 * 성적 이력 API 정보 조회
			 */
			function listScoreApiInfo() {
				var crsCd = $("#scoreCrsCd").val();

				$('#scoreInfoApiList')
					.load(cUrl("/mng/etc/HrdApi/listScoreApi"),
						{ 
						  "crsCd":crsCd
						 }
					);

			}
		
		
		</script>
