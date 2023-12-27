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
				<%-- ${forumVO.forumStartDttm} ~ ${forumVO.forumEndDttm} --%>
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
		<li><a href="javascript:forumManageAtcl()"><spring:message code="lecture.title.forum.atcl.manage.tab"/></a></li>
		<li class="active"><a href="#"><spring:message code="lecture.title.forum.rate.manage.tab"/></a></li>
		<li><a href="javascript:forumManageRslt()"><spring:message code="lecture.title.forum.status.manage.tab"/></a></li>
	</ul>
	<div style="border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
		<form name="stdSearch" id="stdSearch" onsubmit="return false">
		<input type="hidden" name="curPage"/>
		<div class="row">
			<div class="col-lg-12">
				<div class="input-group" style="float:left">
					<select name="declsNo" id="declsNo" onchange="listStd(1)" class="form-control input-sm">
						<option value=""><spring:message code="course.title.decls.all"/></option>
						<c:forEach var="decls" items="${creCrsDeclsList}">
						<option value="${decls.declsNo}">${decls.declsNo}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-group" style="float:left;width:200px;">
					<input type="text" name="searchValue" id="searchValue2" class="form-control input-sm _enterBind" placeholder="<spring:message code="user.title.userinfo.name"/>"/>
					<span class="input-group-addon" onclick="listStd(1)" style=="cursor:pointer">
						<i class="fa fa-search"></i>
					</span>
				</div>
				<div style="float:right">
					<select name="listScale" id="listScale" onchange="listStd(1)" class="form-control input-sm">
						<option value="10">10</option>
						<option value="20" selected="selected">20</option>
						<option value="40">40</option>
						<option value="60">60</option>
						<option value="80">80</option>
						<option value="100">100</option>
						<option value="200">200</option>
					</select>
				</div>
				<div style="float:right">
					<a href="javascript:addScoreAll()" class="btn btn-primary btn-sm"><spring:message code="button.add.score"/> </a>
				</div>
			</div>
		</div>
		<div id="studentList" style="margin-top:5px;">
			<table summary="<spring:message code="lecture.title.forum.rate.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:50px"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:120px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="student.title.student.decls"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
						<th scope="col"><spring:message code="lecture.title.forum.atcl.cnt"/></th>
						<th scope="col"><spring:message code="lecture.title.forum.cmnt.cnt"/></th>
						<th scope="col"><spring:message code="lecture.title.forum.rate.score"/></th>
						<th scope="col"><spring:message code="lecture.title.forum.rate"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="8"><spring:message code="student.message.student.nodata"/></td>
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
		ItemDTO.sortKey = "";
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listStd(1);
			}
		}
		listStd(1);
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
		document.location.href = cUrl("/mng/lecture/forum/main?crsCreCd=${forumVO.crsCreCd}");
	}

	/**
	 * 토론 평가 관리 이동
	 */
	function forumManageAtcl() {
		document.location.href = cUrl("/mng/lecture/forum/manageForumAtclMain")+"?crsCreCd=${forumVO.crsCreCd}${AMPERSAND}forumSn=${forumVO.forumSn}";
	}

	/**
	 * Result Status
	 */
	function forumManageRslt() {
		document.location.href = cUrl("/mng/lecture/forum/manageForumRsltMain")+"?crsCreCd=${forumVO.crsCreCd}${AMPERSAND}forumSn=${forumVO.forumSn}";
	}


	/**
	 * 학습자 목록 조회
	 */
	function listStd(page) {
		$("#studentListArea").show();
		ItemDTO.curStdPage = page;
		var userNm = $("#searchValue2").val();
		var declsNo = $("#declsNo option:selected").val();
		var listScale= $("#listScale option:selected").val();

		$("#studentList")
			.load(
				cUrl("/mng/lecture/forum/listForumStudent"),		// url
				{
				  	"crsCreCd" : ItemDTO.crsCreCd,
				  	"forumSn" : ItemDTO.forumSn,
				  	"userNm" : userNm,
				  	"declsNo" : declsNo,
				  	"sortKey" : ItemDTO.sortKey,
				  	"curPage" : page,
		  		  	"listScale" : listScale
				},			// params
				function() {
					parentResize();
				}
			);
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listStd(1);
	}

	/**
	* 토론 점수 개별 저장
	*/
	function addScore(no) {
		if(dateCheck("${curDateTime}","${forumVO.forumEndDttm}")){
			if(!confirm("<spring:message code="lecture.message.forum.confirm.chkdate"/>")){
				return;
			}
		}
		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");

		if(ScoreObj[no].value == ""){
			alert('<spring:message code="lecture.message.common.alert.needscore"/>');
			return;
		}

		if(ScoreObj[no].value>100){
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			ScoreObj[no].value = "0";
			ScoreObj[no].focus();
			return;
		}else{
			$.getJSON( cUrl("/mng/lecture/forum/addScore"),		// url
					{
					  "crsCreCd" : ItemDTO.crsCreCd,
				  	  "forumSn" : ItemDTO.forumSn,
					  "stdNo" : StdNoObj[no].value,
					  "score" : ScoreObj[no].value
					},			// params
					processAddScoreCallback				// callback function
				);
		}
	}

	/**
	* 토론 점수 일괄 저장
	*/
	function addScoreAll() {
		if(dateCheck("${curDateTime}","${forumVO.forumEndDttm}")){
			if(!confirm("<spring:message code="lecture.message.forum.confirm.chkdate"/>")){
				return;
			}
		}
		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");

		var strStdNo = "";
		var strScore = "";
		var cntScore = 0;
		var cntScore100 = 0;

		for(var i=0; i < ScoreObj.length; i++) {
			//-- 값이 들어온것만 잡는다.
			if(!isEmpty(ScoreObj[i].value)) {
				strStdNo = strStdNo+"|"+StdNoObj[i].value;
				strScore = strScore+"|"+ScoreObj[i].value;
				cntScore++;
			}

			if(!isEmpty(ScoreObj[i].value) && ScoreObj[i].value>100){
				cntScore100++;
				ScoreObj[i].value = 0;
			}
		}
		if(cntScore == 0){
			alert('<spring:message code="lecture.message.common.alert.needscore"/>');
			return;
		}
		if(cntScore100 > 0){
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			return;
		}

		strStdNo = strStdNo.substring(1);
		strScore = strScore.substring(1);

		$.getJSON( cUrl("/mng/lecture/forum/addScoreAll"),		// url
				{ 
				  "crsCreCd" : ItemDTO.crsCreCd,
		  	  	  "forumSn" : ItemDTO.forumSn,
		  	  	  "strStdNo" : strStdNo,
				  "strScore" : strScore
				},			// params
				processAddScoreCallback				// callback function
			);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processAddScoreCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			listStd(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>