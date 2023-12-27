<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@page import="java.util.*"%>
<%@page import="egovframework.edutrack.modules.student.payment.service.PaymentVO"%>

<%

	/*
		//*** 위변조 방지체크를 signature 생성 ***

			oid, price, timestamp 3개의 키와 값을

			key=value 형식으로 하여 '&'로 연결한 하여 SHA-256 Hash로 생성 된값

			ex) oid=INIpayTest_1432813606995&price=819000&timestamp=2012-02-01 09:19:04.004
				

			 * key기준 알파벳 정렬

			 * timestamp는 반드시 signature생성에 사용한 timestamp 값을 timestamp input에 그대로 사용하여야함
	*/

	//############################################
	// 1.전문 필드 값 설정(***가맹점 개발수정***)
	//############################################

	// 여기에 설정된 값은 Form 필드에 동일한 값으로 설정
	String mid					= "";
	String mobileMid 			= ""; //운영 pc mid와 모바일 mid가 다름
	
	//인증
	String signKey			    = "";
	String testPayYn				= Constants.INICIS_TEST_YN;
	
	if("Y".equals(testPayYn)){//테스트
	//if("127.0.0.1".equals(request.getRemoteAddr()) || "0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())){
		mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
		mobileMid = Constants.INICIS_TEST_MID;//테스트 MID, PC와 동일
		signKey = Constants.INICIS_TEST_SIGNKEY;//테스트 Sign KEY
		testPayYn = "Y";
	}else{//운영
		mid = Constants.INICIS_SERVICE_PC_MID;// 가맹점 ID(가맹점 수정후 고정)
		mobileMid = Constants.INICIS_SERVICE_MOBILE_MID;// 모바일 MID, PC와 다름
		signKey = Constants.INICIS_SERVICE_SIGNKEY;// 가맹점에 제공된 웹 표준 사인키(가맹점 수정후 고정)
	}

	//강제 테스트, 모바일의 경우 controller 내에서도 수정 필요
	//mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
	//signKey = Constants.INICIS_TEST_SIGNKEY;//테스트 Sign KEY
	//testPayYn = "Y";
	
	//mid = Constants.INICIS_SERVICE_MID;// 가맹점 ID(가맹점 수정후 고정)
	//signKey = Constants.INICIS_SERVICE_SIGNKEY;// 가맹점에 제공된 웹 표준 사인키(가맹점 수정후 고정)
	
	
	String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성

	String oid					= mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
	String price				= request.getAttribute("usrBskTotPrice").toString();//request.getAttribute("usrBskTotPrice");													// 상품가격(특수기호 제외, 가맹점에서 직접 설정)

	String cardNoInterestQuota	= "11-2:3:,34-5:12,14-6:12:24,12-12:36,06-9:12,01-3:4";		// 카드 무이자 여부 설정(가맹점에서 직접 설정)
	String cardQuotaBase		= "2:3:4:5:6:11:12:24:36";		// 가맹점에서 사용할 할부 개월수 설정

	//###############################################
	// 2. 가맹점 확인을 위한 signKey를 해시값으로 변경 (SHA-256방식 사용)
	//###############################################
	String mKey = SignatureUtil.hash(signKey, "SHA-256");
	
	//###############################################
	// 2.signature 생성
	//###############################################
	Map<String, String> signParam = new HashMap<String, String>();

	signParam.put("oid", oid); 					// 필수
	signParam.put("price", price);				// 필수
	signParam.put("timestamp", timestamp);		// 필수

	// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
	String signature = SignatureUtil.makeSignature(signParam);
	
	
	/* 기타 */
	//String siteDomain = "http://127.0.0.1:8080/INIpayStdSample"; //가맹점 도메인 입력
	String siteDomain = request.getServerName(); //가맹점 도메인 입력
	String sslYn = "Y";
	if(request.isSecure()){
		siteDomain = "https://" + siteDomain;
		sslYn = "Y";
	}else{
		siteDomain = "http://" + siteDomain;
		sslYn = "N";
	}
	
	// 페이지 URL에서 고정된 부분을 적는다. 
	// Ex) returnURL이 http://localhost:8080INIpayStdSample/INIStdPayReturn.jsp 라면
	// http://localhost:8080/INIpayStdSample 까지만 기입한다.
	
	String pNotiUrl = "/home/student/indexEnrollPaymentVBankMobile";//
	
	response.setHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + "; Secure; SameSite=None");//세션 이슈
%>

<!-- 이니시스 표준결제 js -->
  <!--
    연동시 유의 사항!!
    1) 테스트 URL(stgstdpay.inicis.com) - 샘플에 제공된 테스트 MID 전용으로 실제 가맹점 MID 사용 시 에러가 발생 할 수 있습니다.
    2) 상용 URL(stdpay.inicis.com) - 실제 가맹점 MID 로 테스트 및 오픈 시 해당 URL 변경하여 사용합니다.
    3) 가맹점의 URL이 http: 인경우 js URL도 https://stgstdpay.inicis.com/stdjs/INIStdPay.js 로 변경합니다.	
    4) 가맹점에서 사용하는 케릭터셋이 EUC-KR 일 경우 charset="UTF-8"로 UTF-8 일 경우 charset="UTF-8"로 설정합니다.
  -->	
  
<head>
	<%	if(mid.equals(Constants.INICIS_SERVICE_PC_MID)){//운영	%>
		<!-- 상용 JS(가맹점 MID 변경 시 주석 해제, 테스트용 JS 주석 처리 필수!) -->
		<script language="javascript" type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>	
	<%	} else{//그외 테스트 	%>
		<!-- 테스트 JS(샘플에 제공된 테스트 MID 전용) -->	
		<script language="javascript" type="text/javascript" src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
	<%	} %>
</head>


<c:url var="img_base" value="/img/home" scope="request"/>

		 <div class="course_pay">
            <div class="agree_course">
                <div class="terms_area">
		        <h4 class="seg_title">강의계획서 확인/동의</h4>   
		        <ul class="tabs mb0" id="courseInfoTab">
		            <c:forEach items="${usrBskList}" var="item" varStatus="status">
						<li <c:if test="${status.index eq 0 }">class="active"</c:if> ><a href="javascript:loadCourseInfo('${item.crsCreCd }')" >${item.crsCreNm }</a></li>
					</c:forEach>
		        </ul>
		         <div class="box terms rect"  id="courseInfoArea"></div>
		          <div class="terms_agree txt-left">
		            <span class="custom-input">
                        <input type="checkbox" name="courseAgreeYn" id="courseAgreeY" value="Y">
                        <label for="courseAgreeY">위 강의계획서를 과정별로 확인하였으며 동의합니다.</label>
                     </span>
		        </div>
		    </div>
		</div>
		<div class="agree_pay">
        	<div class="terms_area">
		       <h4 class="seg_title">환불규정 확인/동의</h4>  
		        <div class="box terms rect" id="refundRuleInfoArea">
		        	${refundPageVO.pageCts }
		        </div>
		        <div class="terms_agree txt-left">
                     <span class="custom-input">
                         <input type="checkbox" name="refundAgreeYn" id="refundAgreeY" value="Y">
                         <label for="refundAgreeY">위 환불규정을 확인하였으며 동의합니다.</label>
                     </span>
                 </div>
		    </div>
		</div>
		
		
		<div class="res_tbl_wrap">
             <table>
                 <caption>장바구니 목록</caption>
                 <thead>
                     <tr>
                         <th scope="col" width="25%">교육구분</th>
                         <th scope="col">과정명</th>
                         <th scope="col" width="25%">가격</th>
                     </tr>
                 </thead>
                 <tbody>
                 	<c:set var="totEduPrice" value="0"/>
					<c:set var="totGoods" value=""/>
					<c:set var="paramBskListStr" value=""/>
                  	<c:forEach items="${usrBskList}" var="item" varStatus="status">
                  		<c:set var="totEduPrice" value="${totEduPrice + item.eduPrice }"/>
						<c:set value="${paramBskListStr}${status.first ? '' : ','}${item.crsCreCd}" var="paramBskListStr" />
						<c:set value="${totGoods}${status.first ? '' : ','}${item.crsCreNm}" var="totGoods" />
	                     <tr>
	                         <td class="m_hidden" data-label="교육구분">${item.crsOperType}</td>
	                         <td scope="row" class="title" data-label="과정명">${item.crsCreNm }</td>
	                         <td data-label="가격" c><fmt:formatNumber value="${item.eduPrice}" pattern="#,#00" /> 원</td>                                       
	                     </tr>
                    </c:forEach>                                
                 </tbody>
             </table>
             <div class="total_price">
                 <div class="price">
                     <strong>총 결제금액 : <span class="fcBlue"><fmt:formatNumber value="${totEduPrice }" pattern="#,#00" /> 원</span></strong>
                 </div>
             </div>
         </div>
		
		
	
		<form id="SendPayForm_id" name="" method="POST" ><!-- method="POST" action="/home/student/enrollBasketRegistMain"  -->
			<input type="hidden" style="width:100%;" name="version" value="1.0" >

			<input type="hidden" style="width:100%;" name="mid" value="<%=mid%>" >
			<input type="hidden" style="width:100%;" name="goodname" value="${totGoods}" >
			<input type="hidden" style="width:100%;" name="oid" value="<%=oid%>" >
			<c:choose>
				<c:when test="${userInfoVO.deptCd eq '1559293119'}">
					<input type="hidden" name="gopaymethod" value="Card"><%-- 수강생이 내일배움카드 기업인 경우 카드결제만 활성화 --%>
					<input type="hidden" style="width:100%;" name="ini_onlycardcode" value="14:41" ><%-- 신한, 농협 카드만 활성화 --%> 
					<input type="hidden" style="width:100%;" name="acceptmethod" value="noeasypay" ><%-- 간편결제 PAYCO, L포인트 등 미노출 --%>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="gopaymethod" value="Card:DirectBank:VBank">
				</c:otherwise>
			</c:choose>
			<input type="hidden" style="width:100%;" name="price" value="<%=price %>" >
			<input type="hidden" style="width:100%;" name="currency" value="WON" >
			<input type="hidden" style="width:100%;" name="buyername" value="${userInfoVO.userNm }" >
			<input type="hidden" style="width:100%;" name="buyertel" value="${userInfoVO.mobileNo }" >
			<input type="hidden" style="width:100%;" name="buyeremail" value="${userInfoVO.email }" >
		
			<input type="hidden" style="width:100%;" name="timestamp" value="<%=timestamp %>" >
			<input type="hidden" style="width:100%;" name="signature" value="<%=signature%>" >

			<input type="hidden" style="width:100%;" name="returnUrl" value="<%=siteDomain%>/home/student/enrollBasketRegistMain?paramBskListStr=${paramBskListStr}&paramDeptCd=${paymentVO.deptCd }&paramTotEduPrice=${totEduPrice}" >
			<input type="hidden"  name="mKey" value="<%=mKey%>" >
			<input type="hidden" style="width:100%;" name="closeUrl" value="<%=siteDomain%>/inicis/close.jsp" >
			
			<%--선택옵션 가상계좌 입금기한 --%>
			<input type="hidden" style="width:100%;" name="acceptmethod" value="vbank(${vbankDateLimit }2359)" >
			
			
			
			<%-- <input type="hidden" name="paramBskListStr" value="${paramBskListStr }">
			<input type="hidden" name="paramDeptCd" value="${paymentVO.deptCd }">
			<input type="hidden" name="paramUserNo" value="${USERNO }">
			
			<input type="hidden" name="paramTotEduPrice" value="${totEduPrice }"> --%>
		</form>
		
		<form id="mobileweb" name="mobileweb" method="POST" accept-charset="euc-kr">
			<!--*************************필수 세팅 부분************************************-->
			<!-- 리턴받는 가맹점 URL 세팅 -->
			<input type="hidden" name="P_NEXT_URL" value="<%=siteDomain%>/home/student/enrollBasketRegistMobileMain?paramBskListStr=${paramBskListStr}&paramDeptCd=${paymentVO.deptCd }&paramTotEduPrice=${totEduPrice}"> 
			<!-- 지불수단 선택 (신용카드,계좌이체,가상계좌,휴대폰) -->
			<!-- 모바일은 여러 개 선택 불가  -->
			<div class="join_form" id="mobilePaySelArea">
				<div class="tstyle mb30">
				    <ul class="dbody">
				        <li>
				            <div class="row">
				                <label for="P_INI_PAYMENT" class="form-label col-sm-2"><span>결제수단 선택</span><i class="icon_star" aria-hidden="true"></i></label>
				                <div class="col-sm-10">
				                    <div class="form-inline">
				                        <select class="form-select" name="P_INI_PAYMENT" id="P_INI_PAYMENT">
				                        	<c:choose>
												<c:when test="${userInfoVO.deptCd eq '1559293119'}">
													<option value="CARD">신용카드</option>													
												</c:when>
												<c:otherwise>
													<option value="CARD">신용카드</option>
													<option value="BANK">계좌이체</option>
													<option value="VBANK">가상계좌</option>
												</c:otherwise>
											</c:choose>
				                        </select>
				                    </div>
				                </div>
				            </div>
						</li>
				    </ul>
				</div>
			</div>
			
			<!-- 복합/옵션 파라미터 -->
			<c:choose>
				<c:when test="${userInfoVO.deptCd eq '1559293119'}">
					<input type="hidden" name="P_RESERVED" value="twotrs_isp=Y&block_isp=Y&twotrs_isp_noti=N&noeasypay=Y">
					<input type="hidden" name="P_ONLY_CARDCODE" value="14:41"><%-- 신한, 농협 카드만 활성화 --%>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="P_RESERVED" value="twotrs_isp=Y&block_isp=Y&twotrs_isp_noti=N"> <!-- 에스크로옵션 : useescrow=Y -->
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="P_MID" value="<%=mobileMid%>"> <!-- 에스크로테스트 : iniescrow0, 모바일빌링(정기과금)은 별도연동필요 -->
			<input type="hidden" name="P_OID" value="<%=oid%>">  
			<input type="hidden" name="P_GOODS" value="${totGoods }"> 
			<input type="hidden" name="P_AMT" value="<%=price %>"> 
			<input type="hidden" name="P_CHARSET" value="utf8">
			<input type="hidden" name="P_UNAME" value="${userInfoVO.userNm }">
			
			<!--*************************선택 필수 세팅 부분************************************--> 
			<!-- 가상계좌 입금 노티 사용시 필수 -->
			<input type="hidden" name="P_NOTI_URL" value="<%=siteDomain%>/home/student/indexEnrollPaymentVBankMobile">
			<!-- 휴대폰결제 필수 [1:컨텐츠, 2:실물] -->
			<input type="hidden" name="P_HPP_METHOD" value="1">  
			
			<%--선택옵션 가상계좌 입금기한 --%>
			<input type="hidden" name="P_VBANK_DT" value="${vbankDateLimit }">
			<input type="hidden" name="P_VBANK_TM" value="2359">						
		</form>
		
			<div class="btns mt30">
                 <button type="button" class="btn type4" onclick="goPayment()">결제하기</button>
                 <!-- <button type="button" class="btn type2">취소</button> -->
             </div>
        </div>
			


<script type="text/javascript">
	$(document).ready(function(){
		$('.tabs li').click(function (e) {
			$(this).parent().children().removeClass('active');
			$(this).addClass("active");
		});
		
		<c:if test="${not empty usrBskList}">
			loadCourseInfo('${usrBskList[0].crsCreCd }');
		</c:if>
		if(isMobile()){//mobile
			//$("#P_INI_PAYMENT").show();
			$("#mobilePaySelArea").show();
		}else{//PC
			//$("#P_INI_PAYMENT").hide();	
			$("#mobilePaySelArea").hide();	
		}
	});

	function goPayment() {
		 //var courseAgreeYn = $('input:radio[name="courseAgreeYn"]:checked').val();
		 //var refundAgreeYn = $('input:radio[name="refundAgreeYn"]:checked').val();
		 var courseAgreeYn = $('input:checkbox[name="courseAgreeYn"]:checked').val();
		 var refundAgreeYn = $('input:checkbox[name="refundAgreeYn"]:checked').val();
		 
		 if(isNull(courseAgreeYn) || courseAgreeYn == 'N'){
			 alert("강의계획서 확인/동의를 동의해야 합니다.");
			 $('#courseAgreeY').focus();
			 return;
		 }
		 
		 if(isNull(refundAgreeYn) || refundAgreeYn == 'N'){
			 alert("환불규정 확인/동의를 동의해야 합니다.");
			 $('#refundAgreeY').focus();
			 return;
		 }
		 
		 if(confirm('결제를 진행하시겠습니까?')){
			if('<%=testPayYn%>' == 'Y'){
				alert('테스트 결제가 진행됩니다.\n결제된 금액은 금일 12시에 이니시스에서 환불됩니다.\n가상계좌 입금건은 가맹점관리자,API를 통해 환불요청하셔야 환불처리됩니다.(자동처리X)\n국민,카카오뱅크 카드는 테스트 결제가 불가하여 오류가 발생할 수 있습니다.\n다른 카드로 테스트결제 부탁드립니다.');
			}
			
			<c:if test="${userInfoVO.deptCd eq '1559293119'}">
				alert('수강하려는 과정은 내일배움카드로 결제해야합니다.\n다른 카드로 결제 진행하실 경우, 환불 후 다시 결제를 진행하셔야 합니다.');
			</c:if>
			
			if(isMobile()){//mobile
				on_pay();
			}else{//PC
				INIStdPay.pay('SendPayForm_id');	
			}
		 }
		 
	}
	
	function on_pay() { 
		 myform = document.mobileweb; 
		 myform.action = "https://mobile.inicis.com/smart/payment/";//모바일결제 무조건 SSL
		 myform.target = "_self";
		 myform.submit(); 
		}  
	
	function loadCourseInfo(crsCreCd){
		
		$('#courseInfoArea').load(
			cUrl("/home/course/viewCourseEnrollInfo"),
			{ "crsCreCd": crsCreCd}
		);
	}
	
	function goEnrollPayBasketPage(){
		location.href = '/home/student/enrollPayBasketMain';
	}
	
	function removeBasket(crsCreCd) {
		if(confirm("수강신청 목록에서 삭제하시겠습니까?")){
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
					alert("수강신청 항목 삭제에 실패하였습니다. 새로고침 후 다시 이용바랍니다.");
					goEnrollPayBasketPage();
				}
			});			
		}
	}
	
	function isMobile(){
		return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	}
	
	function previewContents(sbjCd){
		$.ajax({
			url : '/home/course/subject/getFirstContents'
			,data : {
				'sbjCd' : sbjCd
			}
			,success : function(resultListVO) {
				var url = cUrl("/home/course/subject/viewContents")+"?sbjCd="+sbjCd+"&unitCd="+resultListVO.unitCd;
				var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=auto,resizable=yes,width=1200,height=675";
				var contentsWin = window.open(url, "contentsWin", winOption);
				contentsWin.focus();
			}
			,error : function(request,status,error) {
			}
		});
	}
</script>

