<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br/>
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
				<div id="sbjList" style="width:100%;text-align:center">
					<table summary="<spring:message code="course.title.subject.manage"/>" class="table table-bordered wordbreak">
						<colgroup>
							<col style="width:auto;"/>
							<col style="width:auto;"/>
							<col style="width:120px;"/>
							<col style="width:66px;"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><spring:message code="course.title.subject.category"/></th>
								<th scope="col"><spring:message code="course.title.subject.name"/></th>
								<th scope="col"><spring:message code="course.title.subject.type"/></th>
								<th scope="col"><spring:message code="common.title.select"/></th>
							</tr>
						</thead>
						<tbody id="tbodyList">
							<tr>
								<td colspan="4" align="center"><spring:message code="course.message.subject.search.msg"/></td>
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
		ItemDTO.crsCreCd = '${vo.crsCreCd}';
	});

	function setSbjCtgr(ctgrCd, ctgrNm) {
		$("#sbjCtgrCd").val(ctgrCd);
		$("#sbjCtgrNm").val(ctgrNm);
	}

	function listSubject() {
		var sbjCtgrCd = $("#sbjCtgrCd option:selected").val();
		var sbjNm = $("#searchValue").val();
		$('#sbjList')
			.load(cUrl("/mng/course/createCourse/subject/listOfflineSearch"),
				{	
					"crsCreCd": ItemDTO.crsCreCd,
					"sbjCtgrCd": sbjCtgrCd,
					"sbjNm": sbjNm
				}
			);
	}

	function addSubject(sbjCd) {
		var lecKindCd = $("#lecKindCd_"+sbjCd+" option:selected").val();
		$.getJSON(
			cUrl( "/mng/course/createCourse/subject/addOfflineSubject"),
			{
				"crsCreCd" : ItemDTO.crsCreCd,
				"sbjCd" : sbjCd,
				"eduMthdCd" : lecKindCd
			},			// params
			function(data) {
				if(data.result >= 0) {
					//-- 정상 처리
					alert("<spring:message code="course.message.subject.add.success"/>");
					listSubject();
					parent.subWorkFrame.listOffline();
			 	} else {
			 		//-- 비정상 처리
			 		alert("<spring:message code="course.message.subject.add.failed"/>");
			 	}
			}
		);
	}

	function addNewSubject() {
		if(confirm('<spring:message code="course.message.subject.confirm.write"/>"')) {
			document.location.href=cUrl("/mng/openCourse/subject/addFormOffline")+"?createMode=creCrsSbj";
		} else {
			return;
		}

	}

</script>
