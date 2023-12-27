<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:mng_head>
<mhtml:mng_body>
	<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<form name="statsLoginForm" id="statsLoginForm" onsubmit="return false" class="form-inline" method="post" action="/StatsKnowManage.do">
						<input type="hidden" id="cmd" name="cmd" />
						<input type="hidden" id="knowSn" name="knowSn" />
						<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex}"/>
						<div class="col-md-12 col-sm-12 box box-info">
							<div class="box-body">
								<div class="form-group">
									<label class="filter-col" for="knowCtgrCdLvl1"><spring:message code="know.title.ctgr.type"/> : </label>
									<select name="knowCtgrCdLvl1" id="knowCtgrCdLvl1" onchange="chgKnowCate(1,false);" class="form-control input-sm filter-col" style="width:150px;">
										<option value=""><spring:message code="know.title.ctgr.type.lvl1.all"/></option>
										<c:forEach var="item" items="${knowCtgrList}">
											<c:if test="${item.knowCtgrLvl == 1}">
											<option value="${item.knowCtgrCd}" <c:if test="${vo.knowCtgrCdLvl1 eq item.knowCtgrCd}">selected="selected"</c:if>>${item.knowCtgrNm}</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<select name="knowCtgrCdLvl2" id="knowCtgrCdLvl2" onchange="chgKnowCate(2,false);" class="form-control input-sm filter-col" style="width:150px;">
										<option value=""><spring:message code="know.title.ctgr.type.lvl2.all"/></option>
									</select>
								</div>
								<div class="form-group" style="margin-left:50px;"></div>
								<div class="form-group">
									<label class="filter-col" for="knowType"><spring:message code="know.title.data.type"/> : </label>
									<select name="knowType" id="knowType" onchange="listPageing(1);" class="form-control input-sm filter-col" style="width:150px;">
										<option value=""><spring:message code="common.title.all"/></option>
										<option value="DOC" <c:if test="${vo.knowType eq 'DOC'}">selected="selected"</c:if>><meditag:codename code="DOC" category="KNOW_TYPE" /></option>
										<option value="IMG" <c:if test="${vo.knowType eq 'IMG'}">selected="selected"</c:if>><meditag:codename code="IMG" category="KNOW_TYPE" /></option>
										<option value="HTML" <c:if test="${vo.knowType eq 'HTML'}">selected="selected"</c:if>><meditag:codename code="HTML" category="KNOW_TYPE" /></option>
										<option value="LINK" <c:if test="${vo.knowType eq 'LINK'}">selected="selected"</c:if>><meditag:codename code="LINK" category="KNOW_TYPE" /></option>
										<option value="MOV" <c:if test="${vo.knowType eq 'MOV'}">selected="selected"</c:if>><meditag:codename code="MOV" category="KNOW_TYPE" /></option>
										<option value="STD_DOC" <c:if test="${vo.knowType eq 'STD_DOC'}">selected="selected"</c:if>><meditag:codename code="STD_DOC" category="KNOW_TYPE" /></option>
										<option value="STD_MOV" <c:if test="${vo.knowType eq 'STD_MOV'}">selected="selected"</c:if>><meditag:codename code="STD_MOV" category="KNOW_TYPE" /></option>
									</select>
								</div>
							</div>
							<div class="box-body" style="padding-top:0;">
								<div class="form-group">
									<label class="filter-col" for="useYn"><spring:message code="common.title.useyn"/> : </label>
									<select name="useYn" id="useYn" onchange="listPageing(1);" class="form-control input-sm filter-col" style="width:150px;">
										<option value=""><spring:message code="common.title.all"/></option>
										<option value="Y" <c:if test="${vo.useYn eq 'Y'}">selected="selected"</c:if>><spring:message code="common.title.useyn_y"/></option>
										<option value="N" <c:if test="${vo.useYn eq 'N'}">selected="selected"</c:if>><spring:message code="common.title.useyn_n"/></option>
									</select>
								</div>
								<div class="form-group" style="margin-left:203px;"></div>
								<div class="form-group" <c:if test="${getKnowCount == 0}">style="display:none;"</c:if>>
									<label class="filter-col" for="originKnowSn"><spring:message code="know.title.data.type.share"/> : </label>
									<select name="originKnowSn" id="originKnowSn" onchange="listPageing(1);" class="form-control input-sm filter-col" style="width:150px;">
										<option value=""><spring:message code="common.title.all"/></option>
										<option value="0" <c:if test="${vo.originKnowSn == 0}">selected="selected"</c:if>><spring:message code="know.title.data.type.self"/></option>
										<option value="1" <c:if test="${vo.originKnowSn == 1}">selected="selected"</c:if>><spring:message code="know.title.data.type.share"/></option>
									</select>
								</div>
							</div>
							<div class="box-body" style="padding-top:0;">
								<div class="form-group">
									<label class="filter-col" for="searchKey" style="margin-right:28px;"><spring:message code="common.title.search"/> : </label>
									<select name="searchKey" id="searchKey" class="form-control input-sm filter-col" style="width:150px;">
										<option value="KNOWNM" <c:if test="${vo.searchKey eq 'KNOWNM'}">selected="selected"</c:if>><spring:message code="know.title.knownm"/></option>
										<option value="COPYRT" <c:if test="${vo.searchKey eq 'COPYRT'}">selected="selected"</c:if>><spring:message code="know.title.know.copy.holder"/></option>
									</select>
									<div class="input-group">
									<input type="text" name="searchValue" id="searchValue" value="<c:out value="${vo.searchValue}"/>" class="_enterBind form-control input-sm"/>
									<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
										<i class="fa fa-search"></i>
									</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 text-right">
							<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.download.excel"/> </a>
							<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm">
								<option value="10" <c:if test="${vo.listScale == 10}">selected="selected"</c:if>>10</option>
								<option value="20" <c:if test="${empty vo.listScale || vo.listScale == 20}">selected="selected"</c:if>>20</option>
								<option value="40" <c:if test="${vo.listScale == 40}">selected="selected"</c:if>>40</option>
								<option value="60" <c:if test="${vo.listScale == 60}">selected="selected"</c:if>>60</option>
								<option value="80" <c:if test="${vo.listScale == 80}">selected="selected"</c:if>>80</option>
								<option value="100" <c:if test="${vo.listScale == 100}">selected="selected"</c:if>>100</option>
								<option value="200" <c:if test="${vo.listScale == 200}">selected="selected"</c:if>>200</option>
							</select>
						</div>
						</form>
						<div class="col-md-12" style="margin-top:5px;">						
							<div id="dataList" style="width:100%;">
								<table summary="data" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:auto"/>
										<col style="width:10%"/>
										<col style="width:6%"/>
										<col style="width:6%"/>
										<col style="width:6%"/>
										<col style="width:6%"/>
										<col style="width:6%"/>
										<col style="width:6%"/>
										<col style="width:6%"/>
<!-- 										<col style="width:6%"/> -->
<!-- 										<col style="width:6%"/> -->
									</colgroup>
									<thead>
										<tr>
											<th scope='col' rowspan="2"><spring:message code="log.title.know.name"/></th>
											<th scope='col' rowspan="2"><spring:message code="log.title.data.type"/></th>
											<th scope='col' colspan="5"><spring:message code="log.title.social.share.cnt" /></th>
											<th scope='col' rowspan="2"><spring:message code="log.title.view.cnt"/></th>
											<th scope='col' rowspan="2"><spring:message code="log.title.bookmark"/></th>
										</tr>
										<tr>
											<th scope='col'><spring:message code="log.title.sum"/></th>
											<th scope='col'>Facebook</th>
											<th scope='col'>Twitter</th>
											<th scope='col'>Google+</th>
											<th scope='col'>Kakao</th>
<!-- 											<th scope='col'>Line</th> -->
<!-- 											<th scope='col'>Band</th> -->
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="9"><center><spring:message code="common.message.nodata"/></center></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="overlay" id="loadingBar">
							<i class="fa fa-spinner fa-spin"></i>
						</div>						
					</div>
				</div>
			</div>
		</div>
	</section>
	<input type="submit" value="submit" style="display:none" />
	
	<div id="_options" style="display:none;"> 
	<c:forEach var="item" items="${knowCtgrList}"><c:if test="${item.knowCtgrLvl == 2}"><span data-par="${item.parKnowCtgrCd}" data-val="${item.knowCtgrCd}">${item.knowCtgrNm}</span></c:if></c:forEach>
	</div> 
<script type="text/javascript">
	$(document).ready(function() {
		$(".inputDate").inputDate();
		
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault(); //-- 엔터키 클릭시 form의 submit 막음.
				listPageing(1);
			}
		}
		
		chgKnowCate(1, true);
		var knowCtgrCdLvl2 = '${vo.knowCtgrCdLvl2}';
		if(knowCtgrCdLvl2 != ''){
			$("#knowCtgrCdLvl2").val(knowCtgrCdLvl2);
		}
	});
	
	/**
	 * 지식 공유 로그 목록 조회
	 */
	function listPageing(page) {
		$("#loadingBar").show();
		var listScale = $("#listScale option:selected").val();
		
		var map = {
				"cmd":"listKnowShare",
				"pageIndex" : page,
				"listScale" : $("#listScale").val(),
				"knowCtgrCdLvl1" : $("#knowCtgrCdLvl1").val(),
				"knowCtgrCdLvl2" : $("#knowCtgrCdLvl2").val(),
				"knowType" : $("#knowType").val(),
				"useYn" : $("#useYn").val(),
				"originKnowSn" : $("#originKnowSn").val(),
				"searchKey" : $("#searchKey").val(),
				"searchValue" : $("#searchValue").val(),
				"orgCd" : '${vo.orgCd}'
		};
		
	    $("#dataList").load(
	    	cUrl("/StatsKnowManage.do"),
	    	map,
	    	function () {$("#loadingBar").hide();}
	    );
	}
	
	//대분류선택
	function chgKnowCate(lvl, pageBoolean){
		if(lvl == 1){
			$("#knowCtgrCdLvl2").val("");
			$("#knowCtgrCdLvl2 option:not(option:eq(0))").remove();
			
			if($("#knowCtgrCdLvl1").val() != ''){
				$("#_options span").each(function(){
					if($(this).attr("data-par") == $("#knowCtgrCdLvl1").val()){
						$("#knowCtgrCdLvl2").append($('<option>', { text : $(this).text(), value : $(this).attr("data-val") }));
					}
				});
			}
		}
		var page = 1;
		if(pageBoolean){
			page = $("#pageIndex").val();
		}
		listPageing(page);
	}
	
	function excelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml = '<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}

		// 폼에 action을 설정하고 submit시킨다.
		$("#statsLoginForm").removeAttr('onsubmit');
		$("#statsLoginForm").attr('target', '_m_download_iframe');
		$("#statsLoginForm").find('input[name=cmd]').val('listKnowUseExcelDownload');
		$("#statsLoginForm").submit();
		$("#statsLoginForm").removeAttr('target');
		$("#statsLoginForm").attr('onsubmit', 'return false');

	}
</script>
</mhtml:mng_body>
</mhtml:mng_html>

