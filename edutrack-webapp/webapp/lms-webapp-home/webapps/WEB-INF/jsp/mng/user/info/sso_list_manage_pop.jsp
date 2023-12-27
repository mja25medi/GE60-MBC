<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>


										<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:100px"/>
												<col style="width:100px"/>
												<col style="width:100px"/>
												<col style="width:100px"/>
												<col style="width:100px"/>
											</colgroup>
											<thead>
												<tr>
													<th scope="col">idx </th>
													<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
													<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
													<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
													<th scope="col">관리자등록</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${userInfoList}" var="item" varStatus="status">
												<tr>
													<td class="text-right">${item.ssoIdx }</td>
													<td>${item.userId }</td>
													<td>${item.userNm}</td>
													<td>
														<c:choose>
															<c:when test="${empty item.email}">-</c:when>
															<c:otherwise>${item.email}</c:otherwise>
														</c:choose>
													</td>
													<td>
														<button class="btn btn-primary btn-sm" onclick="addManage('${item.userId}','${item.userNm}','${item.ssoIdx}','${item.email}')"><i class="fa fa-edit"></i> 관리자등록</button>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
<form name="userInfoForm" id="userInfoForm" onsubmit="return false" method="POST" >
<input type="hidden" name="idCheck" id="idCheck" value="Y" />
<input type="hidden" name="userId" id="userId" value=""/>
<input type="hidden" name="userNm" id="userNm" value=""/>
<input type="hidden" name="email"  id="email" value=""/>
<input type="hidden" name="ssoIdx" id="ssoIdx" value=""/>
<input type="hidden" name="userPass" id="userPass" value=""/>
<input type="hidden" name="adminAuthGrpCd" id="adminAuthGrpCd" value=""/>
<input type="hidden" name="wwwAuthGrpCd" id="wwwAuthGrpCd" value=""/>
<input type="hidden" name="mngAuthGrpCd" id="mngAuthGrpCd" value=""/>
<input type="hidden" name="photoFileSn" id="photoFileSn" value=""/>
<input type="hidden" name="orgCd"  id="orgCd" value="${USER_ORGCD}"/>
</form>

<script type="text/javascript">
	/**
	 * 관리자 등록 폼
	 */
	function addManage(userId, userNm, idx, email) {
		$("#userId").val(userId);	
		$("#userNm").val(userNm);	
		$("#email").val(email);	
		$("#ssoIdx").val(idx);
		$("#userPass").val("111");  //사용되지 않는 패스워드 임시로 등록함
		$("#mngAuthGrpCd").val("/MANAGER");
		
		
		process("addUser");	// cmd
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').attr("action" ,"/mng/user/userInfo/" + cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);

		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

</script>


	