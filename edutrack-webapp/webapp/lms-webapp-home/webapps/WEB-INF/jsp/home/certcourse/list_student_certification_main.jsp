<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<body>
	<div class="table-scroll">
		<div class="tbl-main">
	       <div class="tbl-main-wrap">
	       		<fieldset class="form-row" style="width:20%">
					<select class="form-select" name="crsCd" id="crsCd" onchange="listCreateCourse()" >
						<c:forEach var="item" items="${courseList}">
							<option value="${item.crsCd}" <c:if test="${item.crsCd eq vo.crsCd}">selected="selected"</c:if>>${item.crsNm}</option>
						</c:forEach>
					</select>
				</fieldset>
				<div id="listBody">
				</div>
	        </div>
	    </div>
	</div>
	
	<script>
	var certAplcModalBox = null;
	
	$(document).ready(function() {
		certAplcModalBox = new $M.ModalDialog({
			"modalid" : "certAplcModalBox",
			"nomargin" : false
		});
		listCreateCourse();
	});
	
	/**
	 * 자격증 신청
	 */
	function certAplcStudent(stdNo, crsCreCd){
		var addContent  = "<c:url value="/home/certcourse/certAplcStudentPop2"/>"+"?stdNo="+stdNo+"&crsCreCd="+crsCreCd; 
		certAplcModalBox.clear();
		certAplcModalBox.addContents(addContent);
		certAplcModalBox.setTitle("자격증 신청 ");
		certAplcModalBox.show();
	}
	
	function cancelCertStudent(stdNo, crsCreCd){
		if(confirm('자격증 신청을 취소하시겠습니까?')){
			$.ajax({
				url : '/home/certcourse/cancelCert'
				,data : {
					'stdNo' : stdNo,
					'crsCreCd' : crsCreCd,
					'certSts' : 'N'
				}
				,success : function(resultVO) {
					alert(resultVO.message);
					listCreateCourse();
				}
				,method: "POST"
				,error : function(request,status,error) {
					alert("신청 취소에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
					listCreateCourse();
				}
			});
		}
	}
	
	/**
	 * 개설 과정 목록
	 */
	function listCreateCourse() {
		var url = cUrl("/home/certcourse/stdCourselist");
		var crsCd = $("#crsCd option:selected").val();
	
		$("#listBody")
			.load(url, {
				"crsCd":crsCd,
			});
	}

	function certAplcModalBoxClose() {
		certAplcModalBox.clear();
		certAplcModalBox.close();
	}
	
	function printCerti(crsCreCd, stdNo) {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_pdf_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		var url = cUrl("/home/certcourse/printCertificate?crsCreCd="+crsCreCd+"${AMPERSAND}stdNo="+stdNo);
		$("#_m_pdf_iframe").attr("src",url);
	}
	</script>
</body>
