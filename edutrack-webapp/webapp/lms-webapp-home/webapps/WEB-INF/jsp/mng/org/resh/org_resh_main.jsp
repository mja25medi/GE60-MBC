<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="row" id="content">
					<div class="rowLayer" id="listLayer">
						<form name="Search" onsubmit="return false" class="form-inline">

						<div class="col-md-12 col-sm-12 text-right">
							<input type="checkbox" name="chkReg" id="chkReg" onchange="listResearch(1);" ><spring:message code="course.title.reshbank.regyn"/>
							<c:if test= "${CREAUTH eq 'Y'}">
							<a href="javascript:addForm()" class="btn btn-primary btn-sm"><spring:message code="button.write.research"/> </a>
							</c:if>
							<div class="form-group">
								<select name="listScale" id="listScale" onchange="listResearch(1);" class="form-control input-sm">
									<option value="30" selected="selected">30</option>
									<option value="60">60</option>
									<option value="90">90</option>
									<option value="100">100</option>
									<option value="200">200</option>
									<option value="500">500</option>
									<option value="1000">1000</option>
								</select>
							</div>
						</div>
						<div class="col-md-12">
							<div id="rearchList" style="margin-top:5px;">
								<table summary="<spring:message code="course.title.reshbank.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:80px"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:120px"/>
										<col style="width:50px"/>
										<col style="width:72px"/>
										<col style="width:60px"/>
										<col style="width:300px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="common.title.no"/></th>
											<th scope="col"><spring:message code="course.title.reshbank.title"/></th>
											<th scope="col"><spring:message code="course.title.reshbank.qstn.cnt"/></th>
											<th scope="col"><spring:message code="common.title.useyn"/></th>
											<th scope="col"><spring:message code="common.title.regdate"/></th>
											<th scope="col"><spring:message code="common.title.edit"/></th>
											<th scope="col">참여수</th>
											<th scope="col"><spring:message code="common.title.manage"/></th>
											<th scope="col"><spring:message code="course.title.resh.result"/></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="9"><spring:message code="course.message.reshbank.nodata"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</div>
</section>

<script type="text/javascript">

	var modalBox = null;
	var ItemDTO  = new Object();
	var curPage = 1;

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
				
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listResearch(1);
			}
		}
		listResearch(1);
		ItemDTO.sortKey = "REGDATE_DESC";
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 설문 목록 조회
	 */
	function listResearch(page) {
		var listScale = $("#listScale option:selected").val();
		var searchValue = $('#searchValue').val();
		var regYn = "";
		if($("#chkReg").is(":checked") ){
			regYn = "Y";
		} else {
			regYn = "";
		}
		
		if(page != undefined)
			curPage = page;

		
		$("#rearchList")
			.load(
				cUrl("/mng/org/research/list"),		// url
				{ 
				  "searchValue":searchValue,
				  "regYn":regYn,
				  "sortKey":ItemDTO.sortKey,
				  "curPage":page,
				  "listScale":listScale
				}
			);
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listResearch(1);
	}

	/**
	 * 설문 등록 폼
	 */
	function addForm() {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/org/research/addPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 470);
		modalBox.setTitle("<spring:message code="course.title.reshbank.write"/>");
		modalBox.show();
	}

	/**
	 * 설문 수정 폼
	 */
	function editForm(reshSn) {
		var addContent  = "<iframe id='addFrame' name='addFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/org/research/editPop"/>"
			+ "?curpage="+curPage+"&reshSn="+reshSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 470);
		modalBox.setTitle("<spring:message code="course.title.reshbank.edit"/>");
		modalBox.show();
	}


	/**
	 * 설문 상세정보
	 */
	function reshInfo(reshSn) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/org/research/viewReshPop"/>"
			+ "?reshSn="+reshSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("<spring:message code="course.title.reshbank.info"/>");
		modalBox.show();
	}

	/**
	 * 설문 관리
	 */
	function manageForm( reshSn, reshTypeCd) {
		document.location.href = "/mng/org/research/manageMain?reshSn="+reshSn+"&reshTypeCd="+reshTypeCd;
	}
	
	/**
	* 설문결과 기능 추가 2022-06-15
	*/
	//설문 결과 폼(설문제목, 설문번호, 등록여부(regYn), 설문시작~종료)
	function resultResh(reshTitle, reshSn, regYn, searchValue ) {
		var addContent  = "<iframe id='addResearchFrame' name='addResearchFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/org/research/rsltPop"/>"
			+ "?reshTitle="+reshTitle+"&amp;reshSn="+reshSn+"&amp;regYn="+regYn+"&amp;searchValue="+searchValue+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 650);
		parent.modalBox.setTitle("<spring:message code="course.title.resh.result"/>");
		parent.modalBox.show();
	}

	// 설문 점수 결과 폼
	function resultReshScore(reshSn, reshCnt) {
		var addContent  = "<iframe id='addResearchFrame' name='addResearchFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/org/research/rsltScorePop"/>"
			+ "?reshCnt="+reshCnt+"&amp;reshSn="+reshSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 650);
		parent.modalBox.setTitle("<spring:message code="course.title.resh.result"/>");
		parent.modalBox.show();
	}
	
	// 엑셀다운로드(설문제목, 설문번호, 응답수, 설문시작~종료)
	function excelDownload(reshTitle, reshSn, reshCnt, searchValue ) {
		var url = cUrl("/mng/org/research/reshScoreExcelDownload")+"?reshTitle="+reshTitle+"${AMPERSAND}reshSn="+reshSn+"${AMPERSAND}reshCnt="+reshCnt+"${AMPERSAND}searchValue="+searchValue;

		<c:if test="${BROWSER eq 'IE'}">
		window.open(url,'pdf','top=0;left=0,scrollbars=auto');
		</c:if>
		<c:if test="${BROWSER ne 'IE'}">
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		$('#_m_download_iframe').attr('src', url);
		</c:if>

	}
</script>
