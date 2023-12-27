<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp"%>
<c:url var="img_base" value="/img/home" />

<div class="join_cont">
	<div class="h2_center">
		<h5 class="title_h2">수강신청결과</h5>
	</div>

	<div class="join_area join_complete">
		<h5 class="title_h2">
			<c:choose>
				<c:when test="${errMsgCode eq '0' }">
					<strong>수강 신청</strong>을 완료하였습니다.
				</c:when>
				<c:otherwise>
					<strong>수강 신청 실패</strong>하였습니다.
				</c:otherwise>
			</c:choose>
		</h5>
		<img src="/tpl/002/img/contents/picto_join.png" alt="" class="picto">
		<p class="desc">
			<c:choose>
				<c:when test="${errMsgCode eq '0' }">
					나의 강의실로 이동하여 서비스를 이용해보세요.<br>
					<strong>가상계좌(무통장입금)의 입금 정보는 결제 내역에서 확인 가능</strong>합니다.<br>입금 완료 후 수강 인증까지 1~3일 정도의 시간이 소요됩니다.(주말,공휴일 제외)
					<br>
					<c:if test="${not empty paymentVO }">
						<c:if test="${paymentVO.paymMthdCd eq 'PAYM003' && paymentVO.paymStsCd eq 'DI'}"><%-- 무통장입금 가상계좌 --%>
							<strong>
							<br>가상계좌번호 : ${paymentVO.vactNum }<br>
							입금은행명 : ${paymentVO.vactBankName  }<br>
							예금주명 : ${paymentVO.vactName      }<br>
							<%-- 송금자명 : ${paymentVO.vactInputName }<br> --%>
							입금기한 : <meditag:dateformat type="8" delimeter="." property="${paymentVO.vactDate}${paymentVO.vactTime}"/>
							</strong>
						</c:if>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${not empty errMsg }">
						오류 안내 : <span style="color:red;font-weight: bold;">${errMsg }</span>
					</c:if>
					<br>수강신청 실패가 반복되는 경우, 아래 연락처로 연락바랍니다.
					<br>전화:02-2197-4229
					<br>E-mail:hrd@ocu.ac.kr
				</c:otherwise>
			</c:choose>
		</p>
	</div>

	<div class="btns">
		<button type="button" class="btn type5" onclick="javascript:location.href='/'">메인으로</button>
		<button type="button" class="btn type1" onclick="javascript:location.href='/home/main/goMenuPage?mcd=MC00000051'">강의실 이동</button>
		<%-- <c:choose>
			<c:when test="${errMsgCode eq '0' }">
				<c:if test="${paymentVO.paymStsCd eq 'DF' }">
					<button type="button" class="btn type1" onclick="javascript:location.href='/home/main/goMenuPage?mcd=MC00000051'">강의실 이동</button>
				</c:if>
				<button type="button" class="btn type1" onclick="javascript:location.href='/home/main/goMenuPage?mcd=MC00000025'">결제내역 이동</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn type1" onclick="javascript:location.href='/home/main/goMenuPage?mcd=MC00000024'">수강신청결제 이동</button>
			</c:otherwise>
		</c:choose> --%>
	</div>
</div>

