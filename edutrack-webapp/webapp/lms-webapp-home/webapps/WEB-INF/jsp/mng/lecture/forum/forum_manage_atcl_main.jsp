<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<% request.setAttribute("vEnter", "\n"); %>
<c:set var="forumVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="y"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<br>
	<form id="forumForm" name="forumForm" onsubmit="return false" action="/mng/lecture/forum">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="forumSn" value="${vo.forumSn }" />
	</form>
	<table summary="<spring:message code="lecture.title.forum.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.title"/></th>
			<td colspan="3">
				${forumVO.forumTitle}
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.duration"/></th>
			<td>
				<meditag:dateformat type="0" delimeter="." property="${forumVO.forumStartDttm}"/> ~ <meditag:dateformat type="0" delimeter="." property="${forumVO.forumEndDttm}"/>
			</td>
			<th scope="row"><spring:message code="lecture.title.forum.regyn"/></th>
			<td>
				<meditag:codename code="${forumVO.forumRegYn}" category="REG_YN"/>
			</td>
		</tr>
		<tr>
			<th scope="row" ><spring:message code="lecture.title.forum.period.after.write"/></th>
			<td colspan="3">
				<c:if test="${forumVO.periodAfterWriteYn eq 'Y' }"><spring:message code="lecture.title.forum.writeY"/></c:if>
				<c:if test="${forumVO.periodAfterWriteYn eq 'N' }"><spring:message code="lecture.title.forum.writeN"/></c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.desc"/></th>
			<td colspan="3" class="wordbreak">
				${fn:replace(forumVO.forumCts,vEnter,"<br>")}
			</td>
		</tr>
	</table>
	<div class="text-right">
		<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"><spring:message code="lecture.title.forum.atcl.manage.tab"/></a></li>
		<li><a href="javascript:forumManageRate()"><spring:message code="lecture.title.forum.rate.manage.tab"/></a></li>
		<li><a href="javascript:forumManageRslt()"><spring:message code="lecture.title.forum.status.manage.tab"/></a></li>
	</ul>
	<div style="border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
		<form name="atclSearch" id="atclSearch" onsubmit="return false">
		<input type="hidden" name="curPage"/>
		<div class="row">
			<div class="col-lg-12">
				<div class="input-group" style="float:left">
					<select name="searchKey" id="searchKey" class="form-control input-sm">
						<option value="title"><spring:message code="common.title.title"/></option>
						<option value="regNm"><spring:message code="common.title.reguser"/></option>
					</select>
				</div>
				<div class="input-group" style="float:left;width:200px;">
					<input type="text" name="searchValue" id="searchValue" class="form-control input-sm"/>
					<span class="input-group-addon" onclick="javascript:listAtcl(1)" style="cursor:pointer">
						<i class="fa fa-search"></i>
					</span>
				</div>
				<div style="float:right">
					<a href="javascript:writeForumAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.write.article"/> </a>
				</div>
			</div>
		</div>

		<div id="forumAtclList" style="margin-top:5px;">
			<table summary="<spring:message code="lecture.title.forum.atcl.manage"/> " class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:80px"/>
					<col style="width:auto"/>
					<col style="width:160px"/>
					<col style="width:160px"/>
					<col style="width:100px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col" width="50"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="common.title.title"/></th>
						<th scope="col"><spring:message code="common.title.reguser"/></th>
						<th scope="col"><spring:message code="common.title.regdate"/></th>
						<th scope="col"><spring:message code="common.title.hits"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="5"><spring:message code="lecture.message.forum.atcl.nodata"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<input type="submit" value="submit" style="display:none" />
		</form>
	</div>
<script type="text/javascript">
	var ItemDTO = new Object();

	// 페이지 초기화
	$(document).ready(function() {
		ItemDTO.crsCreCd = "${forumVO.crsCreCd}";
		ItemDTO.forumSn = "${forumVO.forumSn}";

		listAtcl(1);
	});


	/**
	* resize
	*/
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	* 토론 목록으로 이동
	*/
	function goList(){
		document.location.href = cUrl("/mng/lecture/forum/main")+"?forumVO.crsCreCd=${forumVO.crsCreCd}";
	}

	/**
	 * Score Management
	 */
	function forumManageRate() {
		document.location.href = cUrl("/mng/lecture/forum/manageForumRateMain")+"?crsCreCd=${forumVO.crsCreCd}${AMPERSAND}forumSn=${forumVO.forumSn}";
	}

	/**
	 * Result Status
	 */
	function forumManageRslt() {
		document.location.href = cUrl("/mng/lecture/forum/manageForumRsltMain")+"?crsCreCd=${forumVO.crsCreCd}${AMPERSAND}forumSn=${forumVO.forumSn}";
	}

	/**
	 * 게시글 목록 조회
	 */
	function listAtcl(page) {
		$("#forumAtclListArea").show();
		ItemDTO.curPage = page;
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $("#searchValue").val();

		$("#forumAtclList")
			.load(
				cUrl("/mng/lecture/forum/listAtcl"),		// url
				{
				  	"crsCreCd" : ItemDTO.crsCreCd,
				  	"forumSn" : ItemDTO.forumSn,
				  	"searchKey" : searchKey,
				  	"searchValue" : searchValue,
				  	"curPage" : page
				},			// params
				function() {
					parentResize();
				}
			);

	}

	/**
	 * 토론 게시글 상세보기 popup
	 */
	function readforumAtcl(atclSn){

		var addContent  = "<iframe id='viewForumAtclFrame' name='viewForumAtclFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/forum/readAtclPop"/>"
			+ "?crsCreCd=${forumVO.crsCreCd}&amp;forumSn=${forumVO.forumSn}&amp;atclSn="+atclSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.forum.atcl.read"/>");
		parent.modalBox.show();

	}

	/**
	 * 토론 게시글 등록 popup
	 */
	function writeForumAtcl() {

		var addContent  = "<iframe id='addForumAtclFrame' name='addForumAtclFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/forum/addAtclPop"/>"
			+ "?crsCreCd=${forumVO.crsCreCd}&amp;forumSn=${forumVO.forumSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.forum.atcl.write"/>");
		parent.modalBox.show();

	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processAddScoreCallback(resultDTO) {
		closeWorkProgress();
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			listStd(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>