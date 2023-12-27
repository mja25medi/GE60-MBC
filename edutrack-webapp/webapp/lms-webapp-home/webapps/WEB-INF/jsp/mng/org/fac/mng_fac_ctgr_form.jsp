<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

		<div class="box">
			<div class="box-body">
				<div id="facCtgrAddFormDiv" style="display: none;">
					<table class="table table-bordered wordbreak" style="margin-top: 5px;">
						<colgroup>
							<col style="width:25%"/>
							<col style="width:75%"/>
						</colgroup>
						<tr>
							<th scope="row">시설 분류명</th>
							<td>
								<input type="hidden" name="facCtgrCd" id="facCtgrCd" value=""/>
								<input type="text" class="form-control" name="facCtgrNm" id="facCtgrNm" placeholder="시설 분류명을 입력하세요." />
							</td>
						</tr>
						<tr>
							<th scope="row">사용 여부</th>
							<td>
								<input type="radio" name="useYn" id="useYnY" value="Y" style="border:0" /> 사용
								<input type="radio" name="useYn" id="useYnN" value="N" style="border:0" /> 사용 불가
							</td>
						</tr>
					</table>
					<div id="editBtnGrp" class="input-group mb40 pull-right" style="display:none;">
						<button class="btn btn-default btn-sm mr5" onclick="hideAddForm()">취소</button>
						<button class="btn btn-primary btn-sm mr5" onclick="editFacCtgr()">수정</button>
						<button class="btn btn-warning btn-sm mr5" onclick="deleteFacCtgr()">삭제</button>					
					</div>
					<div id="addBtnGrp" class="input-group mb40 pull-right" style="display:none;">
						<button class="btn btn-default btn-sm mr5" onclick="hideAddForm()">취소</button>
						<button class="btn btn-primary btn-sm mr5" onclick="addFacCtgr()">등록</button>
					</div>
				</div>
				<button id="showBtn" class="btn btn-primary btn-sm pull-right mb5" onclick="showAddForm()">시설 분류 등록</button>
				<div id="facCtgrListDiv">
					<table class="table table-bordered wordbreak">
						<thead>
							<tr>
								<th>시설 분류명</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ctgrList }">
								<tr>
									<td class="text-center">
										${item.facCtgrNm }
										<a href="javascript:showEditForm('${item.facCtgrCd }')">
											<i class="fa fa-cog"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	<script type="text/javascript">
		function addFacCtgr() {
			var facCtgrNm = $("#facCtgrNm").val();
			var useYn = $("input[name=useYn]:checked").val();
			
			$.post(cUrl("/mng/org/fac/addFacCtgr"),{
				"facCtgrNm" : facCtgrNm,
				"useYn" : useYn
			}, function(data) {
				if(data.result > 0) {
					alert(data.message);
					document.location.reload();
				} else {
					alert(data.message);
				}
			});
		}
		
		function editFacCtgr() {
			var facCtgrCd = $("#facCtgrCd").val();
			var facCtgrNm = $("#facCtgrNm").val();
			var useYn = $("input[name=useYn]:checked").val();
			
			$.post(cUrl("/mng/org/fac/editFacCtgr"),{
				"facCtgrCd" : facCtgrCd,
				"facCtgrNm" : facCtgrNm,
				"useYn" : useYn
			}, function(data) {
				if(data.result > 0) {
					alert(data.message);
					document.location.reload();
				} else {
					alert(data.message);
				}
			});
		}
		
		function deleteFacCtgr() {
			var facCtgrCd = $("#facCtgrCd").val();
			
			$.post(cUrl("/mng/org/fac/deleteFacCtgr"),{
				"facCtgrCd" : facCtgrCd,
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
			$("#facCtgrAddFormDiv").show();
			$("#addBtnGrp").show();
		}
		
		function showEditForm(facCtgrCd) {
			$.ajax({
				url : "/mng/org/fac/viewFacCtgr"
				, data : {
					"facCtgrCd" : facCtgrCd
				}
				, success : function(data) {
					if(data.result == 1) {
						var vo = data.returnVO;
						$("#facCtgrCd").val(vo.facCtgrCd);
						$("#facCtgrNm").val(vo.facCtgrNm);
						$("input[name=useYn][value="+ vo.useYn +"]").prop('checked', true);
					} else {
						alert(data.message);
					}
					hideBtns();
					$("#facCtgrAddFormDiv").show();
					$("#editBtnGrp").show();
				}
				, method : "get"
			});
		}
		
		function hideAddForm() {
			$("#facCtgrAddFormDiv").hide();
			$("#showBtn").show();
			$("#facCtgrCd").val("");
			$("#facCtgrNm").val("");
			$("input[name=useYn]").prop('checked', false);
		}
		
		function hideBtns() {
			$("#showBtn").hide();
			$("#editBtnGrp").hide();
			$("#addBtnGrp").hide();
		}
	</script>
		