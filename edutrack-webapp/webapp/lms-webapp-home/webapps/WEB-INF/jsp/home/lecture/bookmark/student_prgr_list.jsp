<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="bookmarkVO" value="${vo}"/>
<c:url var="img_base" value="/img/home" />



				<form id="bookmarkForm" name="bookmarkForm" class="form-inline" onsubmit="return false" action="/lec/bookmark/listStdPrgrRatioMain" >
				<input type="hidden" name="curPage" value="${curPage}" />
				<input type="hidden" name="crsCreCd" value="${CRSCRECD}" />
				<input type="hidden" name="sortKey" id="sortKey" value="${vo.sortKey}" />
				<div class="row" style="margin-bottom:5px;">
					<div class="col-md-7 col-sm-7">
						<div class="input-group" style="width:250px;">
							<span class="input-group-addon"> <spring:message code="lecture.title.contents.study.ratio"/>(%) </span>
							<input type="text" name="searchFrom" id="searchFrom" style="text-align:right" class="form-control input-sm _enterBind" value="${searchFrom}" placeholder="<spring:message code="common.title.over"/>"/>
							<span class="input-group-addon" style="background-color:#ffffff;"> ~ </span>
							<input type="text" name="searchTo" id="searchTo" style="text-align:right" class="form-control input-sm _enterBind" value="${searchTo}" placeholder="<spring:message code="common.title.less"/>"/>
						</div>
						<div class="input-group" style="width:150px;">
							<input type="text" name="userNm" id="userNm" class="form-control input-sm _enterBind" value="${userNm}"  placeholder="<spring:message code="user.title.userinfo.name"/>"/>
							<span class="input-group-addon btn_search" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-5  col-sm-5 text-right">
						<c:if test="${MSG_SMS eq 'Y' }">
						<a href="javascript:messageForm('SMS');" class="btn btn-info btn-sm"><spring:message code="button.sms"/></a>
						</c:if>
						<c:if test="${MSG_EMAIL eq 'Y' }">
						<a href="javascript:messageForm('EMAIL');" class="btn btn-info btn-sm"><spring:message code="button.email"/></a>
						</c:if>
						<c:if test="${MSG_NOTE eq 'Y' }">
						<a href="javascript:messageForm('MSG');" class="btn btn-info btn-sm"><spring:message code="button.note"/></a>
						</c:if>

						<select name="listScale" title="" class="form-control input-sm" onchange="chgListScale()">
							<option value="20" <c:if test="${listScale eq 20}">selected</c:if>>20</option>
							<option value="40" <c:if test="${listScale eq 40}">selected</c:if>>40</option>
							<option value="60" <c:if test="${listScale eq 60}">selected</c:if>>60</option>
							<option value="80" <c:if test="${listScale eq 80}">selected</c:if>>80</option>
							<option value="100" <c:if test="${listScale eq 100}">selected</c:if>>100</option>
							<option value="200" <c:if test="${listScale eq 200}">selected</c:if>>200</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="lecture.title.contents.study.ratio.student"/></caption>
							<colgroup>
								<col width="5%" />
								<col width="8%" />
								<col width="12%" />
								<col width="12%" />
								<col width="auto" />
								<col width="12%" />
								<col width="12%" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="text-center"><input type="checkbox" name="chkAll" id="chkAll" value="N" /></th>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'USER_NM') == true}">
												<c:if test="${vo.sortKey eq 'USER_NM_ASC'}">
											<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'USER_NM_DESC'}">
											<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'USER_ID') == true}">
												<c:if test="${vo.sortKey eq 'USER_ID_ASC'}">
											<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'USER_ID_DESC'}">
											<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'TOT_RATIO') == true}">
												<c:if test="${vo.sortKey eq 'TOT_RATIO_ASC'}">
											<a href="javascript:setSortKey('TOT_RATIO_DESC')"><spring:message code="lecture.title.contents.study.ratio"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'TOT_RATIO_DESC'}">
											<a href="javascript:setSortKey('TOT_RATIO_ASC')"><spring:message code="lecture.title.contents.study.ratio"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('TOT_RATIO_ASC')"><spring:message code="lecture.title.contents.study.ratio"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope="col" ><spring:message code="lecture.title.contents.study.time"/></th>
									<th scope="col" class="rnone"><spring:message code="button.view.detail"/></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty studentList}">
					            	<tr>
										<td colspan="6" class="rnone" align="center">
											 <font color="red"><spring:message code="student.message.student.nodata"/></font>
										</td>
									</tr>
					            </c:if>
								<c:forEach items="${studentList}" var="item" varStatus="status">
								<tr>
									<td class="text-center"><input type="checkbox" name="sel" value="${item.userNo}" /></td>
									<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
									<td>${item.userNm}</td>
									<td>${item.userId}</td>
									<td>
										<div class="progress" style="margin-bottom:0px;margin-left:5px;">
  											<div class="progress-bar" role="progressbar" aria-valuenow="${item.totRatio}" aria-valuemin="0" aria-valuemax="100" style="width: ${item.totRatio}%;">
  												<c:set var="color"  value="color: #000;"/>
    											<c:if test="${item.totRatio >0}">
    											<c:set var="color"  value="color: #fff;"/>
    											</c:if>
    											<span style="${color}">${item.totRatio}%</span>
  											</div>
										</div>
									</td>
									<td  class="text-right">
										<%-- ${item.totBmkTime}<spring:message code="common.title.min"/> --%>
										<fmt:parseNumber var= "min" integerOnly= "true" value= "${item.totBmkTime % 3600 / 60 }" />
										<fmt:parseNumber var= "sec" integerOnly= "true" value= "${item.totBmkTime % 3600 % 60 }" />
										${min } <spring:message code="common.title.min"/> ${sec } <spring:message code="common.title.sec"/>
									</td>
									<td class="text-center">
										<a href="<c:url value="/lec/bookmark/listStdPrgrRatioDetailMain?crsCreCd=${item.crsCreCd}&amp;stdNo=${item.stdNo}"/>" title="<spring:message code="lecture.title.contents.view.study.ratio.student"/>" class="btn btn-info btn-sm">
											<spring:message code="button.detail"/>
										</a>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				</form>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="listStdPrgrRatio"/>
					</div>
				</div>

<script type="text/javascript">
	var Item = new Object();
	var ItemDTO = new Object();
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

	    //전체선택 /전체취소
		$('#chkAll').click(function(){
		    var state=$('input[name=chkAll]:checked').size();
		    if(state==1){
		   		$(document).find("#bookmarkForm input[name='sel']").prop({checked:true});
		    }else{
		    	$(document).find("#bookmarkForm input[name='sel']").prop({checked:false});
		    }
		});

		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listStdPrgrRatio(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			listStdPrgrRatio(1);
		});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function listStdPrgrRatio(page) {
		if($('#searchFrom').val() =="" && $('#searchTo').val() ==""){
			if(Number($('#searchFrom').val()) >  Number($('#searchTo').val())){
				alert("진도율(%)를 잘못 입력하셨습니다.");
				return;
			}
		}

		$('#bookmarkForm').find('input[name=curPage]').val(page);
		document.bookmarkForm.submit();
	}

	function chgListScale(){
		$('#bookmarkForm').find('input[name=curPage]').val(1);
		document.bookmarkForm.submit();
	}

	/**
	* 메세지 전송 popup
	*/
	function messageForm(msgDivCd) {
		userList = $("#bookmarkForm input[name='sel']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/message/messageWritePop"/>"
			+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.userNoList="+userList+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}

	function setSortKey(key) {
		$("#sortKey").val(key);
		listStdPrgrRatio(1);
	}
</script>
