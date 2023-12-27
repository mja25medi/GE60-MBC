<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="panel-title"><i class="glyphicon glyphicon-edit"></i> <spring:message code="student.title.student.enroll"/></span>
						</div>
						<div class="panel-body">

							<h4 class="stit first" style="padding-bottom: 15px;"><spring:message code="student.title.student.payment.course"/></h4>
							<table class="table table-bordered wordbreak">
								<caption class="sr-only"><spring:message code="student.title.student.payment.course"/></caption>
								<colgroup>
									<col style="width:6%" />
									<col />
									<col style="width:15%" />
									<col style="width:15%" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><spring:message code="common.title.no"/></th>
										<th scope="col"><spring:message code="course.title.course.name"/></th>
										<th scope="col"><spring:message code="course.title.createcourse.creterm"/></th>
										<th scope="col"><spring:message code="course.title.course.edufee"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>${createCourseVO.crsCreNm}</td>
										<td>${createCourseVO.creYear}<spring:message code="common.title.year"/>/${createCourseVO.creTerm}</td>
										<td><fmt:formatNumber value="${createCourseVO.eduPrice}"/></td>
									</tr>
									<tr>
										<td colspan="4" style="text-align:right;font-weight:bold;background-color:#f3f3f3;"><spring:message code="student.title.student.payment.totalfee"/> : <fmt:formatNumber value="${createCourseVO.eduPrice}"/></td>
									</tr>
								</tbody>
							</table>
							<h4 class="stit" style="padding-top:40px;padding-bottom: 15px;"><spring:message code="student.title.student.payment.info"/></h4>
							<table class="table table-bordered wordbreak">
								<caption class="sr-only"><spring:message code="student.title.student.payment.info"/></caption>
								<colgroup>
									<col style="width:15%" />
									<col style="width:75%" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><spring:message code="student.title.student.payment.mthd"/></th>
										<td>
											<c:forEach var="code" items="${codeList}" varStatus="codeStatus">
												<c:set var="codeName" value="${code.codeNm}"/>
												<c:forEach var="lang" items="${code.codeLangList}">
													<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
												</c:forEach>
											<label style="font-weight: normal; margin-right:10px;"><input type="radio" name="paymMthdCd" value="${code.codeCd}" <c:if test="${codeStatus.first}">checked="checked"</c:if>/> ${codeName}</label>
											</c:forEach>
										</td>
									</tr>
									<tr>
										<th scope="row">
											<spring:message code="org.title.orginfo.bankinfo"/>
										</th>
										<td>
											<ul style="list-style-type: none; padding-left:0px;">
												<li style="margin-top:5px;"><spring:message code="org.title.orginfo.bankname"/> : ${orgInfoVO.bankNm}</li>
												<li style="margin-top:5px;"><spring:message code="org.title.orginfo.acntnum"/> : ${orgInfoVO.acntNo }</li>
												<li style="margin-top:5px;margin-bottom:5px;"><spring:message code="org.title.orginfo.acntname"/> : ${orgInfoVO.acntNm }</li>
											</ul>
										</td>
									</tr>
								</tbody>
							</table>
							<div class="text-center">
								<a href="javascript:goPayment()" class="btn btn-primary">결제하기</a>
								<a href="<c:url value="/home/main/goMenuPage?amp;mcd=${MENUCODE}"/>" class="btn btn-default"><spring:message code="button.cancel"/></a>
							</div>

						</div>
					</div>
				</div>
			</div>

<script type="text/javascript">
	function goPayment() {
		 var paymMthdCd = $('input:radio[name="paymMthdCd"]:checked').val();
		 if(paymMthdCd == "" || paymMthdCd == null){
			 alert("<spring:message code="student.message.student.payment.choice.mthd"/>");
			 return;
		 }
		 var email = $('input:text[name="email"]').val();
		 $('input:hidden[name="LGD_BUYEREMAIL"]').val(email);
		 if(paymMthdCd == 'PAYM001') { //-- 신용카드 결제
		 } else if(paymMthdCd == 'PAYM002') { //-- 실시간 계좌이체
		 } else { //-- 무통장, 현장수납 등
			 document.location.href = cUrl("/home/student/enrollRegistMain")+"?crsCreCd=${createCourseVO.crsCreCd}${AMPERSAND}paymMthdCd="+paymMthdCd;
		 }
	}
</script>

 --%>
 
 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@page import="java.util.*"%>
<%@page import="egovframework.edutrack.modules.student.payment.service.PaymentVO"%>



<c:url var="img_base" value="/img/home" scope="request"/>
					
                       
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>장바구니 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col">과정명</th>
                                    <th scope="col" width="10%">샘플강의</th>
                                    <th scope="col" width="10%">가격</th>
                                    <th scope="col" width="10%">삭제</th>
                                    <th scope="col" width="10%">주문</th>
                                    <th scope="col" width="10%">
                                        <span class="custom-input onlychk"><input type="checkbox" id="cbx_chkAll" name="cbx_chkAll" ><label for="cbx_chkAll"></label></span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<form name="paymentForm" id="paymentForm" action="/home/student/enrollPayBasketMain?mcd=MC10000013" method="post" onsubmit="return false">
                            		
                            		<input type="hidden" name="stdNo" id="stdNo"/>
									<input type="hidden" name="crsCreCd" id="crsCreCd"/>
                            		
	                            	<c:set var="totEduPrice" value="0"/>
									<c:set var="totGoods" value=""/>
									<c:set var="paramBskListStr" value=""/>
									<c:forEach items="${usrBskList}" var="item" varStatus="status">
										<c:set var="totEduPrice" value="${totEduPrice + item.eduPrice }"/>
										<c:set value="${paramBskListStr}${status.first ? '' : ','}${item.crsCreCd}" var="paramBskListStr" />
										<c:set value="${totGoods}${status.first ? '' : ','}${item.crsCreNm}" var="totGoods" />
		                                <tr>
		                                    <td scope="row" class="title" data-label="과정명">${item.crsCreNm } ${item.creTerm }회차</td>
		                                    <td class="m_hidden" data-label="미리보기">
		                                    	<c:if test="${not empty item.sbjCd }">
		                                    		<a href="javascript:previewContents('${item.sbjCd }')" class="btn3 sm solid fcViolet">맛보기</a>
		                                    	</c:if>
		                                    </td>
		                                    <td data-label="가격"><fmt:formatNumber value="${item.eduPrice }" pattern="#,#00" />원</td>
		                                    <td data-label="삭제">
		                                        <a href="javascript:removeBasket('${item.crsCreCd }')"><label class="btn3 sm solid dark">삭제</label></a>
		                                    </td>
		                                    <td data-label="주문">
		                                        <a href="javascript:onePayment('${item.crsCreCd }')" class="btn3 sm solid fcViolet">선택</a>
		                                    </td>
		                                    <td data-label="선택"><span class="custom-input onlychk"><input type="checkbox" name="crsCreCds" id="chk_${status.index}" value="${item.crsCreCd}"><label for="chk_${status.index}"></label></span></td>
		                                </tr>
	                             	</c:forEach>   
                                </form>
                            </tbody>
                         
                        </table>
                    </div>

					
                    <div class="btns justify mt20">
                        <div class="left"><button class="btn gray2" onclick="location.href='/home/course/listCourseMain?mcd=MC00000023&crsOperMthd=ON'">강좌 더보기</button></div>
                        <div class="right">
                            <button class="btn type4" onclick="goPayment()">다음</button>
                        </div>
                    </div>
                        
                        



<script type="text/javascript">
	$(document).ready(function(){
		
		$('.tabs li').click(function (e) {
			$(this).parent().children().removeClass('active');
			$(this).addClass("active");
		});
		
		
		$('#cbx_chkAll').click(function (e) {
			if($("#cbx_chkAll").is(":checked")) {
				$("input[name=crsCreCds]").prop("checked", true);
			}else{
				$("input[name=crsCreCds]").prop("checked", false);
			}
		});
		
	});

	
	function goEnrollPayBasketPage(){
		location.href = '/home/student/enrollPaymMain?mcd=MC10000013';
	}
	
	function removeBasket(crsCreCd) {
		if(confirm("장바구니 목록에서 삭제하시겠습니까?")){
			$.ajax({
				url : '/home/student/removeBasket'
				,data : {
					'crsCreCd' : crsCreCd
				}
				, method: "POST"
				,success : function(resultVO) {
					alert(resultVO.message);
					goEnrollPayBasketPage();
				}
				,error : function(request,status,error) {
					alert("장바구니 항목 삭제에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
					goEnrollPayBasketPage();
				}
			});			
		}
	}
	
	function goPayment(){
		if($('input:checkbox[name=crsCreCds]:checked').length == 0){
			alert("하나 이상 선택이 필요합니다.");
			return;
		}
		var paymentForm = document.getElementById("paymentForm");
		paymentForm.submit();	
	}
	
	function onePayment(crsCreCd){
		
		$("input[name=cbx_chkAll]").prop("checked", false);
		$("input[name=crsCreCds]").prop("checked", false);
		
		var paymentForm = document.getElementById("paymentForm");
		$("#crsCreCd").val(crsCreCd);
		paymentForm.submit();	
	}
	
	function previewContents(sbjCd){
		$.ajax({
			url : '/home/course/subject/getFirstContents'
			,data : {
				'sbjCd' : sbjCd
			}
			,success : function(resultListVO) {
				var url = cUrl("/home/course/subject/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+resultListVO.unitCd;
				var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=auto,resizable=yes,width=1200,height=830";
				var contentsWin = window.open(url, "contentsWin", winOption);
				contentsWin.focus();
			}
			,error : function(request,status,error) {
			}
		});
	}
	
</script>


 