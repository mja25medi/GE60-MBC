package egovframework.edutrack.modules.student.payment.service;

import java.util.Map;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface PaymentService {

	/**
	 * [HRD]장바구니 추가 - 단건
	 */
	public abstract ProcessResultVO<PaymentVO> addBasket(PaymentVO vo);

	/**
	 * [HRD]장바구니 삭제 - 단건
	 */
	public abstract ProcessResultVO<PaymentVO> deleteBasket(PaymentVO vo);

	/**
	 * [HRD] 회원 장바구니 초기화
	 */
	public abstract ProcessResultVO<PaymentVO> deleteUserBasket(PaymentVO vo);
	
	/**
	 * [HRD] 회원의 장바구니 조회
	 */
	public abstract ProcessResultListVO<PaymentVO> listBasketByUserNo(PaymentVO vo);
	
	/**
	 * [HRD] [교육과정/신청>수강신청결제] - 장바구니 조회 : 수강신청기간, 회원번호, 기업번호
	 */
	public abstract ProcessResultListVO<PaymentVO> listBasketForEnrollByUserNoDeptCd(PaymentVO vo);

	/**
	 * [HRD] [교육과정/신청>수강신청결제] - 수강신청 
	 */
	public abstract ProcessResultVO<PaymentVO> addPayment(PaymentVO paymentVO);

	/**
	 * [HRD] 주문번호로 결제 건 조회
	 * @param paramVO
	 */
	public abstract ProcessResultVO<PaymentVO> selectByPaymOidNo(PaymentVO vo);
	
	/**
	 * [HRD] 결제 건 조회
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<PaymentVO> viewUserPayByPaymNo(PaymentVO vo);

	/**
	 * [HRD] 가상계좌 결과 수신에 대한 결제, 수강생 정보 변경
	 * @param resultVO
	 */
	public abstract ProcessResultVO<PaymentVO> editVBankResult(PaymentVO vo);

	/**
	 * [이니시스 연계] 카드,실시간계좌이체 환불 처리 : 부분취소, 전체취소
	 * @param resultMap
	 * @return 
	 */
	public abstract ProcessResultVO<Map<String, Object>> inicisRefund(Map<String, Object> resultMap);
	
	/**
	 * [이니시스 연계] 부분취소가능여부조회
	 * @param tid
	 * @param oid
	 * @param clientIp
	 * @return
	 */
	public abstract ProcessResultVO<Map<String, Object>> viewInicisParRefundAbleInfo(Map<String, Object> resultMap);
}
