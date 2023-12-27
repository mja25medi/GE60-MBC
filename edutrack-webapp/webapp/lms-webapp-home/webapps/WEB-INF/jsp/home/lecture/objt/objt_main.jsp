<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />

                <div class="learn_top">
                    <div class="left_title">
                        <h3>이의제기</h3>
                    </div>
                     <div class="right_btn">
                    	<c:if test="${CLASSUSERTYPE eq 'STU' }">
							<button type="button" class="btn type4" onclick="addObjectionWrite();">등록</button>
						</c:if>
                    </div>
                </div>
                <c:if test="${CLASSUSERTYPE ne 'STU' }">
                <ul class="class_tab">
                    <li class="tab" id="tab_"><a href="javascript:clickTab('')">전체</a></li>
                    <li class="tab" id="tab_I"><a href="javascript:clickTab('I')">처리중</a></li>
                    <li class="tab" id="tab_C"><a href="javascript:clickTab('C')">처리완료</a></li>
                </ul>
                </c:if>
                <div class="segment">
                    <div class="res_tbl_wrap"  id="loadDiv"></div>
                </div>


<script type="text/javascript">
	var stsCd = "";
	var modalBox = null;
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1",
			"nomargin" : false //m_large
		});
		clickTab('');
		$(".sub_title_2.ohddien").text("이의제기 내역");
	});
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function listObjt(page) {
		$("#loadDiv").load(cUrl("/lec/objt/listObjt"),{
			"crsCreCd" : $("#crsCreCd").val(),
			"stsCd" : stsCd,
			"curPage" : page
		});
	}
	
	function clickTab(searchSts) {
		stsCd = searchSts;
		$(".tab").removeClass("active");
		$("#tab_" + searchSts).addClass("active");
		listObjt(1);
	}

	/* function addObjectionPop(){
		var crsCreCd = $("#crsCreCd").val();
		var addContent  = "<iframe id='writeObjtFrame' name='writeObjtFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/lec/objt/writeObjtPop"/>"
			+ "?crsCreCd="+crsCreCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000,600);
		modalBox.setTitle("이의제기");
		modalBox.show();
	} */
	
	function addObjectionWrite() {
		var crsCreCd = $("#crsCreCd").val();
		var url = generateUrl("lec/objt/writeObjtMain",{"crsCreCd" : crsCreCd});
		document.location.href = url;
	}
</script>
