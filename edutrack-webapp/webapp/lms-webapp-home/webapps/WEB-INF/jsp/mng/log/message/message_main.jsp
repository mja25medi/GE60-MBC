<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="rowLayer" id="listLayer">
						<form name="Search" onsubmit="return false" class="form-inline">
						<div class="col-md-6 col-md-6" style="width: 800px; float: left;">
							<div class="input-group">
								<select class="form-control input-sm" id="msgDivType" name="msgDivType" style="width:200px;" onchange="listMessage(1)">
									<option value="" selected>메시지 종류 선택</option>
									<option value="sms">SMS</option>
									<option value="email">EMAIL</option>
									<option value="msg">쪽지</option>
								</select>
							</div>
							<div class="input-group">
								<select class="form-control input-sm" id="searchType" name="searchType" style="width:200px;">
									<option value="title">제목</option>
									<option value="sendNm">발신자</option>
								</select> 
							</div>	
																				
							<div class="input-group" style="width:250px;">
								<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" title="<spring:message code="common.message.input.search"/>" placeholder="검색어를 입력하세요."/>
								<span class="input-group-addon" onclick="listMessage()" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 text-right" style="width: 600px; float: right;">
						 	<!-- div class="checkbox">
								<label style="line-height:20px;"><input type="checkbox" id="createdOnly" name="createdOnly" value="Y" onclick="listMessage(1)"/> <spring:message code="course.title.createcourse.createdonly"/></label>
							</div -->
							<select name="listScale" id="listScale" onchange="listMessage(1)" class="form-control input-sm">
								<option value="30" selected="selected">30</option>
								<option value="60">60</option>
								<option value="90">90</option>
								<option value="100">100</option>
								<option value="200">200</option>
								<option value="500">500</option>
								<option value="1000">1000</option>
							</select>
							<%--
							<c:if test= "${MNGTYPE ne '|AVMANAGER' &&  MNGTYPE ne '|TUTOR' &&  MNGTYPE ne '|KORCHAM' &&  MNGTYPE ne '|PROFESSOR' && CREAUTH eq 'Y'}">
							<button class="btn btn-primary btn-sm" onclick="creCrsWriteForm()"><spring:message code="button.write.subject"/></button>
							</c:if>
							--%>
						</div>
						</form>
						<div class="col-md-12" style="margin-top:5px;">
							<div id="messageList">
							</div>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
</section>			

<script type="text/javascript">
	var modalBox = null;
	var ctgrTree = null;
	var ItemDTO = {
			sortKey : "",
		}

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listMessage(1);
			}
		}
		listMessage(1);

	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 과정 정보 목록 조회
	 */
	function listMessage(page) {
		var listScale = $("#listScale option:selected").val();
		var msgDivType = $("#msgDivType").val();
		var searchType = $("#searchType").val();
		var searchKey = $("#searchKey").val();
		
		$('#messageList')
			.load(cUrl("/mng/log/message/listMessage"),
				{ 
				  "sortKey":ItemDTO.sortKey,
				  "curPage":page,
				  "listScale":listScale,
				  "msgDivType":msgDivType,
				  "searchType":searchType,
				  "searchKey":searchKey
			    },
			);
	}

	function msgDetailForm(msgSn) {
		var addContent  = "<iframe id='detailMessageFrame' name='detailMessageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/mng/log/message/detailFormPop"/>"
			+ "?msgSn="+msgSn+"'/>";

			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(1200, 800);
			modalBox.setTitle("상세");
			modalBox.show();
	}

	function setSortKey1(sortKey) {
		ItemDTO.sortKey = sortKey;
		listMessage(1);
	}

	function setSortKey2(sortKey) {
		ItemDTO.sortKey = sortKey;
		listMessage(1);
	}
	
</script>
