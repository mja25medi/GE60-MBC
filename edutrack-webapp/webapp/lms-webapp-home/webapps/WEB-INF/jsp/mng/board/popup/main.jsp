<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<section class="content">
		<div class="row" id="content">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<form name="popupNoticeForm" id="popupNoticeForm" class="form-inline">
						<input type="hidden" name="popupSn" id="popupSn" value="${vo.popupSn }"/>
						<input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex}" />
						<div class="col-md-10 col-sm-10">
							<div class="input-group" style="width:160px">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.title.input.searchvalue"/>" value="${vo.searchValue}" placeholder="<spring:message code="common.title.all"/>"/>
								<span class="input-group-addon" onclick="listPopup(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</div>
						<div class="col-md-2 text-right">
							<a href="javascript:popupWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write.popup"/> </a>
						</div>				
						<div class="col-md-12" style="margin-top:5px; margin-bottom:20px;">
							<table summary="<spring:message code="board.title.popup.manage"/>" class="table table-bordered wordbreak">
								<caption class="sr-only"><spring:message code="board.title.popup.manage"/></caption>
								<colgroup>
									<col style="width:60px"/>
									<col style="width:auto"/>
									<col style="width:110px;"/>
									<col style="width:170px;"/>
									<col style="width:90px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><spring:message code="common.title.no"/></th>
										<th scope="col"><spring:message code="common.title.title"/></th>
										<th scope="col"><spring:message code="board.title.popup.opentype"/></th>
										<th scope="col"><spring:message code="board.title.popup.duration"/></th>
										<th scope="col"><spring:message code="common.title.useyn"/></th>
									</tr>
								</thead>
								<tbody id="tbodyEtc">
								<c:if test="${empty popupList}">
									<tr>
										<td colspan="6"><spring:message code="board.message.popup.nodata"/></td>
									</tr>
								</c:if>
								<c:forEach items="${popupList}" var="item" varStatus="status">
									<tr>
										<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
										<td><a href="javascript:previewPopup('${item.title}','${item.xSize}','${item.ySize}','${item.xPos}','${item.yPos}','${item.popupSn}','${item.popupTypeCd}')" title="<spring:message code="common.title.preview"/>">${item.title}</a> <a href="#none" onclick="javascript:editPopup(${item.popupSn})" title="${item.title}"><i class="fa fa-cog"></i></a></td>
										<td class="text-center"><meditag:codename code="${item.popupTypeCd}" category="POPUP_TYPE_CD" /></td>
										<td>
											<meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/>
											<meditag:dateformat type="7" delimeter="." property="${item.startDttm}"/> ~
											<br>
											<meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/>
											<meditag:dateformat type="7" delimeter="." property="${item.endDttm}"/>
										</td>
										<td class="text-center"><meditag:codename code="${item.useYnWww}" category="USE_YN" /></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPopup"/>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<script type="text/javascript">
	var modalBox = null;
	/** 페이지 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault(); //-- 엔터키 클릭시 form의 submit 막음.
				listPopup(1);
			}
		}
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	/**
	 *  페이징처리
	 */
	function listPopup(page) {
		$("#pageIndex").val(page);
		$('#popupNoticeForm').attr("action","/mng/brd/popupNotice/main");
		$("#popupNoticeForm").submit();
	}
	
	/**
	 *  수정 폼
	 */
	 function editPopup(popupSn) {
		$("#popupSn").val(popupSn);
		$('#popupNoticeForm').attr("action","/mng/brd/popupNotice/editFormPopupMain");
		$("#popupNoticeForm").submit();
	}
	 /**
	  *  입력 폼
      */
	function popupWrite() {
		$('#popupNoticeForm').attr("action","/mng/brd/popupNotice/addFormPopupMain");
		$("#popupNoticeForm").submit();
		//document.location.href = cUrl("/BoardPopupAdmin.do?cmd=addFormPopup");
	}

	function previewPopup(title, sizex, sizey, posx, posy, popupSn, popupTypeCd) {
		var url = generateUrl("/mng/brd/popupNotice/viewPop",{ "orgCd":"${USER_ORGCD}", "popupSn":popupSn});
		//--- 팝업 타입이 팝업 박스인 경우
		if(popupTypeCd == 'PBOX') {
			var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(sizex, sizey);
			modalBox.move(posx, posy);
			modalBox.show();
		} else {
			var option = "width="+sizex+",height="+sizey+",top="+posy+",left="+posx;
			var prevWin = window.open(url, "popupNotice", option);
		}

	}
	
</script>

