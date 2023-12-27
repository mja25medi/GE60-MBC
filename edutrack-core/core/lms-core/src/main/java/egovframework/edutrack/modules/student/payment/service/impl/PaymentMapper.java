package egovframework.edutrack.modules.student.payment.service.impl;

import java.util.List;

import egovframework.edutrack.modules.student.payment.service.PaymentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("paymentMapper")
public interface PaymentMapper {

	/**
	 * [HRD] 결제 정보 추가
	 * @param vo
	 * @return
	 */
	int insertPayInfo(PaymentVO vo);
	
	/**
	 *  [HRD] 장바구니 추가 - 단건
	 * @param vo
	 * @return
	 */
	int mergeBasket(PaymentVO vo);

	/**
	 *  [HRD] 장바구니 삭제 - 단건
	 * @param vo
	 * @return
	 */
	int deleteBasket(PaymentVO vo);
	
	/**
	 *  [HRD] 회원 장바구니 삭제
	 * @param vo
	 * @return
	 */
	int deleteUserBasket(PaymentVO vo);
	
	/**
	 *  [HRD] 장바구니 삭제 - 수강신청 기간이 아닌 강의 삭제
	 * @param vo
	 * @return
	 */
	int deleteBasketNoEnrollAplcPeriod(PaymentVO vo);

	/**
	 * [HRD] 결제 정보 키 조회
	 * @return
	 */
	String selectKey();
	
	/**
	 * [HRD] 장바구니 조회
	 * @param vo
	 * @return
	 */
	List<PaymentVO> listBasketByUserNo(PaymentVO vo);
	
	/**
	 * [HRD] [교육과정/신청>수강신청결제] - 장바구니 조회, 수강신청기간 확인 
	 * @param vo
	 * @return
	 */
	List<PaymentVO> listBasketForEnrollByUserNoDeptCd(PaymentVO vo);

	/**
	 * [HRD] 주문번호로 결제 건 조회
	 * @param vo
	 * @return
	 */
	PaymentVO selectByPaymOidNo(PaymentVO vo);

	/**
	 * [HRD] 가상계좌 결과 수신 : 주문번호로 결제 건 수정
	 * @param vo
	 * @return
	 */
	int updatePaymentInfoVbank(PaymentVO vo);
	
	/**
	 * [HRD] 회원 결제 조회
	 * @param vo
	 * @return
	 */
	PaymentVO selectUserPayByPaymNo(PaymentVO vo);
	
}
