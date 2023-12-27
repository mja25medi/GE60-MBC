<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

		<div class="box">
			<div class="box-body">
				<div id="emailAddDiv" style="display: none;">
					<table class="table table-bordered wordbreak" style="margin-top: 5px;">
						<colgroup>
							<col style="width:25%"/>
							<col style="width:75%"/>
						</colgroup>
						<tr>
							<th scope="row">이메일</th>
							<td>
								<input type="hidden" name="recvId" id="recvId" value=""/>
								<input type="text" class="form-control" name="email" id="email" placeholder="알림 수신 이메일을 입력하세요." />
							</td>
						</tr>
					</table>
					<div id="editBtnGrp" class="input-group mb40 pull-right" style="display:none;">
						<button class="btn btn-default btn-sm mr5" onclick="hideAddForm()">취소</button>
						<button class="btn btn-primary btn-sm mr5" onclick="editEmail()">수정</button>
						<button class="btn btn-warning btn-sm mr5" onclick="deleteEmail()">삭제</button>					
					</div>
					<div id="addBtnGrp" class="input-group mb40 pull-right" style="display:none;">
						<button class="btn btn-default btn-sm mr5" onclick="hideAddForm()">취소</button>
						<button class="btn btn-primary btn-sm mr5" onclick="addEmail()">등록</button>
					</div>
				</div>
				<button id="showBtn" class="btn btn-primary btn-sm pull-right mb5" onclick="showAddForm()">수신 이메일 등록</button>
				<div id="facCtgrListDiv">
					<table class="table table-bordered wordbreak">
						<thead>
							<tr>
								<th>수신 이메일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${recvList }">
								<tr>
									<td class="text-center">
										${item.email }
										<a href="javascript:showEditForm('${item.recvId }')">
											<i class="fa fa-cog"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty recvList }">
								<tr>
									<td class="text-center">등록된 수신 이메일이 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	<script type="text/javascript">
	var orgCd = '${vo.orgCd}';
	var notiCtgr = '${vo.notiCtgr}';
		function addEmail() {
			var email = $("#email").val();
			
			$.post(cUrl("/mng/org/noti/recv/add"),{
				"orgCd" : orgCd,
				"email" : email,
				"notiCtgr" : notiCtgr
			}, function(data) {
				if(data.result > 0) {
					alert(data.message);
					document.location.reload();
				} else {
					alert(data.message);
				}
			});
		}
		
		function editEmail() {
			var recvId = $("#recvId").val();
			var email = $("#email").val();
			
			$.post(cUrl("/mng/org/noti/recv/update"),{
				"recvId" : recvId,
				"email" : email,
			}, function(data) {
				if(data.result > 0) {
					alert(data.message);
					document.location.reload();
				} else {
					alert(data.message);
				}
			});
		}
		
		function deleteEmail() {
			var recvId = $("#recvId").val();			
			$.post(cUrl("/mng/org/noti/recv/delete"),{
				"recvId" : recvId,
			}, function(data) {
				if(data.result > 0) {
					alert(data.message);
					document.location.reload();
				} else {
					alert(data.message);
				}
			});
		}
		
		function showAddForm() {
			hideBtns();
			$("#emailAddDiv").show();
			$("#addBtnGrp").show();
		}
		
		function showEditForm(recvId) {
			$.ajax({
				url : "/mng/org/noti/recv/view"
				, data : {
					"recvId" : recvId
				}
				, success : function(data) {
					if(data.result == 1) {
						var vo = data.returnVO;
						$("#recvId").val(vo.recvId);
						$("#email").val(vo.email);
					} else {
						alert(data.message);
					}
					hideBtns();
					$("#emailAddDiv").show();
					$("#editBtnGrp").show();
				}
				, method : "get"
			});
		}
		
		function hideAddForm() {
			$("#emailAddDiv").hide();
			$("#showBtn").show();
			$("#recvId").val("");
			$("#email").val("");
		}
		
		function hideBtns() {
			$("#showBtn").hide();
			$("#editBtnGrp").hide();
			$("#addBtnGrp").hide();
		}
	</script>
		