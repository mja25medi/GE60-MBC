<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-body">
								<div class="col-lg-12">
									<form name="relatedSiteForm" id="relatedSiteForm" method="POST" >
									<input type="hidden" name="ctgrCd" value="${ctgrVo.ctgrCd }"/>
									<input type="hidden" name="siteSn" value="${vo.siteSn }"/>
									<input type="hidden" name="siteOdr" value="${vo.siteOdr }"/>
									
									<div id="error" class="red"></div>
									<table summary="<spring:message code="etc.title.relatedsite.site.manage"/>" class="table table-bordered wordbreak">
								        <colgroup>
								    		<col style="width:30%"/>
								    		<col style="width:70%"/>
								    	</colgroup>
										<tr>
											<th scope="row"><spring:message code="etc.title.relatedsite.ctgr.name"/></th>
											<td>${ctgrVo.title}</td>
										</tr>
										<tr>
											<th scope="row"><spring:message code="etc.title.relatedsite.site.name"/></th>
											<td>
												<input type="text" maxlength="50" dispName="<spring:message code="etc.title.relatedsite.site.name"/>" isNull="N" name="title" value="${vo.title }" class="form-control input-sm"/>
											</td>
										</tr>
										<tr>
											<th scope="row"><spring:message code="etc.title.relatedsite.site.url"/></th>
											<td>
												<input type="text" maxlength="250" dispName="<spring:message code="etc.title.relatedsite.site.url"/>" lenCheck="250" id="siteUrl" isNull="N" name="siteUrl" value="${vo.siteUrl }" class="form-control input-sm"/>
											</td>
										</tr>
										<tr>
											<th scope="row"><spring:message code="etc.title.relatedsite.site.desc"/></th>
											<td>
												<input type="text" area style="height:50px" dispName="<spring:message code="etc.title.relatedsite.site.desc"/>" isNull="N" lenCheck="2000" class="form-control input-sm" name="siteDesc" value="${vo.siteDesc }"/>
											</td>
										</tr>
									</table>
									<div class="text-right">
								    	<c:if test="${gubun eq 'A'}">
										<a href="#none" onclick="addSite()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
								        </c:if>
								        <c:if test="${gubun eq 'E'}">
								        <a href="#none" onclick="editSite()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
								        <a href="#none" onclick="delSite()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
								        </c:if>
								        <a href="#none" onclick="parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
								    </div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
<script type="text/javascript">
	//사이트 초기화
	$(document).ready(function() {
	});
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(cmd != "deleteRelatedSite"){
			if(!validate(document.relatedSiteForm)) return;
			//사이트 url에 2byte이상 글자제한
			var str = $('#siteUrl').val();
			var len = 0;
			for (var i = 0; i < str.length; i++) {
					if (str.charCodeAt(i) > 128) {
					alert('<spring:message code="etc.message.siteurl.not.character"/>');
					$('#siteUrl').focus();
					return;
				}
			}
			//URL형식 체크
			if(!ValidUrl(str)){
				alert('<spring:message code="etc.message.siteurl.not.character"/>');
				$('#siteUrl').focus();
				return;
			}
		}
		$('#relatedSiteForm').attr("action","/mng/etc/relatedSite/"+cmd);
		$('#relatedSiteForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(result) {
		alert(result.message);
		if(result.result == 1) {
			// 정상 처리
			parent.listCtgr();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
	
	/**
	 * 사이트 추가
	 */
	function addSite() {
		process("addSite"); // cmd
	}
	
	/**
	 * 사이트 수정
	 */
	function editSite() {
		process("editSite"); // cmd
	}
	
	/**
	 * 사이트 삭제
	 */
	function delSite() {
		if (confirm('<spring:message code="etc.message.relatedsite.site.confirm.delete"/>')) {
			process("deleteSite"); // cmd
		} else {
			return;
		}
	}
	
	function ValidUrl(str) {
	/* 		var pattern = new RegExp('^(https?:\\/\\/)?' + // 프로토콜
		'((([a-z\d](([a-z\d-]*[a-z\d])|([ㄱ-힣]))*)\.)+[a-z]{2,}|' + // 도메인명 <-이부분만 수정됨
		'((\\d{1,3}\\.){3}\\d{1,3}))' + // 아이피
		'(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*' + // 포트번호
		'(\\?[;&a-z\\d%_.~+=-]*)?' + // 쿼리스트링
		'(\\#[-a-z\\d_]*)?$', 'i'); // 해쉬테그들
	*/
		var pattern = /^(https?:\/\/)?((([a-z\d](([a-z\d-]*[a-z\d]))|([ㄱ-힣])*)\.)+(([a-zㄱ-힣]{2,}))|((\d{1,3}\.){3}\d{1,3}))(\:\d+)?(\/[-a-z\d%_.~+]*)*(\?[;&a-z\d%_.~+=-]*)?(\#[-a-z\d_]*)?$/;
		if (!pattern.test(str)) {
			return false;
		} else {
			return true;
		}
	}
</script>
