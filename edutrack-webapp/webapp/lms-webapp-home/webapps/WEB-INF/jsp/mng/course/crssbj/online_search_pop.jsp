<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsOnlnSbjVO" value="${crsOnlnSbjVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff">
	<form name="Search" onsubmit="return false">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="course.title.course.manage.subject"/>
				</div>
				<div class="panel-body">
					<div class="input-group" style="float:left;width:200px;">
						<input type="hidden" name="sbjCtgrCd" id="sbjCtgrCd" value=""/>
						<div class="input-group-btn btn-group">
							<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
	    						<span class="caret"></span>
	  						</button>
	  						<ul class="dropdown-menu" role="menu" style="overflow-y:auto;height:300px;">
	  							<li><a href="javascript:setSbjCtgr('','')"><spring:message code="common.title.select.all"/></a></li>
	  						<c:set var="defLvl" value="0" />
							<c:set var="lineNo" value="0" />
							<c:forEach items="${ctgrList}" var="itemDTO">
								<c:set var="lineNo" value="${lineNo + 1}" />
								<c:if test="${lineNo == 1}" >
									<c:set var="defLvl" value="${itemDTO.ctgrLvl}" />
								</c:if>
								<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
								<c:if test="${item.subCnt > 0}">
									<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
								</c:if>
								<c:url var="blankImgPath" value="/img/framework/common/blank.gif" />
								<li><a href="javascript:setSbjCtgr('${itemDTO.sbjCtgrCd}','${itemDTO.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(itemDTO.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${itemDTO.sbjCtgrNm} </a></li>
							</c:forEach>
	  						</ul>
	  					</div>
	  					<input type="text" name="sbjCtgrNm" id="sbjCtgrNm" class="form-control input-sm" placeholder="<spring:message code="common.title.select.all"/>"/>
					</div>
					<div class="input-group" style="float:left;width:200px;">
						<input type="text" id="searchValue" name="searchValue" class="_enterBind form-control input-sm" maxlength="20" placeholder="<spring:message code="common.title.all"/>"/>
						<span class="input-group-addon" onclick="listSubject()" style="cursor:pointer">
							<i class="fa fa-search"></i>
						</span>
					</div>
					<div style="float:right;" class="text-right">
						<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
					</div>
				</div>
				<div id="sbjList" style="width:100%;text-align:center;overflow-y:auto;height:320px;">
					<table summary="<spring:message code="course.title.subject.manage.online"/>" style="width:100%"  class="table table-bordered wordbreak">
						<colgroup>
							<col style="width:auto;"/>
							<col style="width:auto;"/>
							<col style="width:auto;"/>
							<col style="width:auto;"/>
							<col style="width:auto;"/>
							<col style="width:66px;"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><spring:message code="course.title.subject.category"/></th>
								<th scope="col"><spring:message code="course.title.subject.name"/></th>
								<th scope="col"><spring:message code="course.title.subject.studymthd"/></th>
								<th scope="col"><spring:message code="course.title.subject.type"/></th>
								<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
								<th scope="col"><spring:message code="common.title.select"/></th>
							</tr>
						</thead>
						<tbody id="tbodyList">
							<tr>
								<td colspan="5" align="center"><spring:message code="course.message.subject.search.msg"/></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listSubject();
			}
		}
	});

	function setSbjCtgr(ctgrCd, ctgrNm) {
		$("#sbjCtgrCd").val(ctgrCd);
		$("#sbjCtgrNm").val(ctgrNm);
	}

	function listSubject() {
		var sbjCtgrCd = $("#sbjCtgrCd").val();
		var sbjNm = $("#searchValue").val();
		$('#sbjList')
			.load(cUrl("/mng/course/courseSubject/listOnlnSearch"),
				{	
					"crsCd": "${crsOnlnSbjVO.crsCd}",
					"sbjCtgrCd": sbjCtgrCd,
					"sbjNm": sbjNm
				}
			);
	}

	function addSubject(sbjCd, sbjType) {
		var studyMthd = $("#studyMthd_"+sbjCd+" option:selected").val();
		$.getJSON( cUrl( "/mng/course/courseSubject/addOnlnSbj"),
				   {  "crsCd" : "${crsOnlnSbjVO.crsCd}","sbjCd" : sbjCd, "studyMthd":studyMthd, "sbjType": sbjType},			// params
				   function(data) {
						if(data.result >= 0) {
							//-- 정상 처리
							alert("<spring:message code="course.message.subject.add.success"/>");
							listSubject();
							parent.subWorkFrame.listOnline();
			 	  		} else {
			 	  			//-- 비정상 처리
			 	  			alert("<spring:message code="course.message.subject.add.failed"/>");
			 	  		}
					}
				);
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>