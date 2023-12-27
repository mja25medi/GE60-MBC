package egovframework.edutrack.modules.student.payment.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.student.payment.service.PaymentService;
import egovframework.edutrack.modules.student.payment.service.PaymentVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.ValidateRollbackStudentException;
import egovframework.edutrack.modules.student.student.service.impl.StudentMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("paymentService")
@Transactional(readOnly=true)
public class PaymentServiceImpl extends EgovAbstractServiceImpl implements PaymentService{
	
	@Resource(name="paymentMapper")
	private PaymentMapper paymentMapper;
	
	@Resource(name="studentMapper")
	private StudentMapper studentMapper;

	@Resource(name="studentService")
	private StudentService 	 studentService;

	/**
	 * [HRD]장바구니 추가 - 단건
	 */
	@Override
	@Transactional
	public ProcessResultVO<PaymentVO> addBasket(PaymentVO vo) {
		
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		
		//0. crsCreCd 가 수강생이 신청할 수 있는 crsCreCd 인지 확인
		StudentVO studentVO = new StudentVO();
		studentVO.setCrsCreCd(vo.getCrsCreCd());
		studentVO.setUserNo(vo.getUserNo());
		
		if("Y".equals(studentMapper.isEnroll(studentVO).getStdYn())) {
			throw new ServiceProcessException("이미 수강신청한 과정입니다.");
		}
		
		//1. merge
		int result = paymentMapper.mergeBasket(vo);
		
		resultVO.setResult(result);
		
		if(result > 0) {
			resultVO.setMessage("수강 바구니에 추가하였습니다.");
		}else {
			throw new ServiceProcessException("수강 바구니에 추가하지 못하였습니다.");
		}
		
		//2. 등록되어있는 장바구니에서 수강신청 기간 지나거나, 수강신청한 과정은 장바구니에서 삭제
		//resultVO.setMessage("");
		
		
		return resultVO;
	}
	
	/**
	 * [HRD]장바구니 삭제 - 단건
	 * @param vo
	 * @return
	 */
	@Override
	@Transactional
	public ProcessResultVO<PaymentVO> deleteBasket(PaymentVO vo) {
		
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		
		int result = paymentMapper.deleteBasket(vo);
		
		resultVO.setResult(result);
		
		if(result > 0) {
			resultVO.setMessage("수강 바구니에서 삭제하였습니다.");
		}else {
			resultVO.setMessage("수강 바구니에서 삭제하지 못하였습니다.");
		}
		
		return resultVO;
	}
	
	/**
	 * [HRD] 회원 장바구니 초기화
	 */
	@Override
	@Transactional
	public ProcessResultVO<PaymentVO> deleteUserBasket(PaymentVO vo) {
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		
		//TB_STD_PAY_INFO 테이블 INSERT
		
		int result = paymentMapper.deleteUserBasket(vo);
		
		resultVO.setResult(result);
		
		if(result > 0) {
			resultVO.setMessage("수강 바구니에서 삭제하였습니다.");
		}else {
			resultVO.setMessage("수강 바구니에서 삭제하지 못하였습니다.");
		}
		
		return resultVO;
		
	}
	
	/**
	 * [HRD] 회원의 장바구니 조회
	 */
	@Override
	public ProcessResultListVO<PaymentVO> listBasketByUserNo(PaymentVO vo) {
		ProcessResultListVO<PaymentVO> resultListVO = new ProcessResultListVO<>();
		
		resultListVO.setReturnList(paymentMapper.listBasketByUserNo(vo));
		
		return resultListVO;
	}
	
	/**
	 * [HRD] [교육과정/신청>수강신청결제] - 장바구니 조회 : 수강신청기간, 회원번호, 기업번호
	 */
	@Override
	public ProcessResultListVO<PaymentVO> listBasketForEnrollByUserNoDeptCd(PaymentVO vo) {
		ProcessResultListVO<PaymentVO> resultListVO = new ProcessResultListVO<>();
		
		resultListVO.setReturnList(paymentMapper.listBasketForEnrollByUserNoDeptCd(vo));
		
		return resultListVO;
	}
	

	/**
	 * [HRD] [교육과정/신청>수강신청결제] - 수강신청 
	 */
	@Override
	@Transactional
	public ProcessResultVO<PaymentVO> addPayment(PaymentVO paymentVO) {
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		
		//결제 정보 저장
		String paymNo = paymentMapper.selectKey();
		
		paymentVO.setPaymNo(paymNo);
		if("PAYM003".equals(paymentVO.getPaymMthdCd())) {//가상계좌(무통장입금)인 경우 결제 상태 = 결제대기(DI) 
			paymentVO.setPaymStsCd("DI");
		}else {//그외(카드결제, 실시간결제) 결제 완료 DF
			paymentVO.setPaymStsCd("DF");
		}
		
		if(paymentMapper.insertPayInfo(paymentVO) > 0) {
			resultVO.setMessage("결제 정보 등록 성공");
			resultVO.setResultSuccess();
		}else {
			resultVO.setMessage("결제 정보 등록 오류");
			resultVO.setResultFailed();
		}
		
		int result = 0;
		
		//수강생 리스트 저장
		for (StudentVO studentVO : paymentVO.getStudentList()) {
			try {
				studentVO.setPaymNo(paymNo);
				studentVO.setStdNo(studentMapper.selectKey());
				
				if("PAYM003".equals(paymentVO.getPaymMthdCd())) {//가상계좌(무통장입금)인 경우 수강상태 = 수강대기(E)
					studentVO.setEnrlSts("E");
				}else {//그외(카드결제, 실시간결제) 수강신청 완료 S
					studentVO.setEnrlSts("S");
				}
				
				result = studentService.addStudentForHrdApi(studentVO);
				paymentVO.getStdNoList().add(studentVO.getStdNo());
			} catch (Exception e) {
				resultVO.setResultFailed();
				throw new ValidateRollbackStudentException("수강생 등록 오류");
			}
		}
		
		//insert 후 수강생 장바구니 초기화
		deleteUserBasket(paymentVO);
		
		resultVO.setResultSuccess();
		resultVO.setReturnVO(paymentVO);//결과화면 paymNo 전달
		return resultVO;
		
	}

	/**
	 * [HRD] 주문번호로 결제 건 조회
	 */
	@Override
	public ProcessResultVO<PaymentVO> selectByPaymOidNo(PaymentVO vo) {
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		resultVO.setReturnVO(paymentMapper.selectByPaymOidNo(vo));
		return resultVO;
	}
	
	/**
	 * [HRD] 가상계좌 결과 수신에 대한 결제, 수강생 정보 변경
	 */
	@Override
	@Transactional
	public ProcessResultVO<PaymentVO> editVBankResult(PaymentVO vo) {
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		
		//결제 건 결제 대기에서 결제 완료로 변경 : 입금 일, 입금 금액, 상태값 변경    + 추가로 로그?
		vo.setPaymStsCd("DF");//입금완료
		paymentMapper.updatePaymentInfoVbank(vo);
		
		//결제번호로 수강대기(E)인 수강생, 수강중(S) 상태로 변경
		StudentVO studentVO = new StudentVO();
		studentVO.setPaymNo(vo.getPaymNo());
		List<StudentVO> studentNoList =  studentMapper.selectStdNoByPayNo(studentVO);
		for(StudentVO student : studentNoList) {
			student.setEnrlSts("S");
			studentService.updateStudentEnrlStsForHrdApi(student);
		}
		
		return resultVO;
	}

	/**
	 * [HRD] 결제 건 조회
	 */
	@Override
	public ProcessResultVO<PaymentVO> viewUserPayByPaymNo(PaymentVO vo) {
		return new ProcessResultVO<>(paymentMapper.selectUserPayByPaymNo(vo));
	}
	
	/**
	 * [이니시스 연계] 카드,실시간계좌이체 환불 처리 : 부분취소, 전체취소
	 * @param resultMap
	 * @return
	 */
	public ProcessResultVO<Map<String, Object>> inicisRefund(Map<String, Object> resultMap){
		
		ProcessResultVO<Map<String, Object>> processResultVO = new ProcessResultVO<>();
		StringBuilder postData = new StringBuilder();
		for(Map.Entry<String, Object> params: resultMap.entrySet()) {
			
			if(postData.length() != 0) postData.append("&");
			try {
				postData.append(URLEncoder.encode(params.getKey(), "UTF-8"));
				postData.append("=");
				postData.append(URLEncoder.encode(String.valueOf(params.getValue()), "UTF-8"));
			} catch (Exception e) {
				//e.printStackTrace();
				throw new ServiceProcessException("데이터 변환 오류");
			}
		}
		
		//step2. key=value 로 post 요청 (FORM)
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object> payResultMap = null;
		try {
			URL url = new URL("https://iniapi.inicis.com/api/v1/refund");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			if (conn != null) {
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
				conn.setRequestMethod("POST");
				conn.setDefaultUseCaches(false);
				conn.setDoOutput(true);
				
				if (conn.getDoOutput()) {
					conn.getOutputStream().write(postData.toString().getBytes("UTF-8"));
					conn.getOutputStream().flush();
					conn.getOutputStream().close();
				}

				conn.connect();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
				
				//step3. 요청 결과 (JSON)
				//payResultMap = objectMapper.readValue(br.readLine(), Map.class);
				payResultMap = objectMapper.readValue(br.readLine(), new TypeReference<HashMap<String,Object>>() {});
				//cancelResultDTO =  objectMapper.readValue(br.readLine(), PaymentInicisParCancelResultDTO.class);
				br.close();
			}

		}catch(Exception e ) {
			//e.printStackTrace();
			throw new ServiceProcessException("결제 취소 오류");
		}
		
		//결제 후 dto 변환(이유 : 매뉴얼과 달리 없는 필드가 있어 차후 추가될 경우 에러)
		/*cancelResultDTO = objectMapper.convertValue(payResultMap, PaymentInicisParCancelResultDTO.class);
		processResultVO.setReturnVO(cancelResultDTO);*/
		
		processResultVO.setReturnVO(payResultMap);
		egovLogger.info("[취소 결과] = " + payResultMap);
		return processResultVO;
	}
	
	/**
	 * (23.01 기준. API에 명시되어있지만 호출하면 지원하지 않는 서비스)[이니시스 연계] 거래 조회 : 거래조회, 부분취소가능여부조회
	 * @param tid
	 * @return
	 */
	public ProcessResultVO<Map<String, Object>> viewInicisParRefundAbleInfo(Map<String, Object> resultMap){
		StringBuilder postData = new StringBuilder();
		for(Map.Entry<String, Object> params: resultMap.entrySet()) {
			if(postData.length() != 0) postData.append("&");
			try {
				postData.append(URLEncoder.encode(params.getKey(), "UTF-8"));
				postData.append("=");
				postData.append(URLEncoder.encode(String.valueOf(params.getValue()), "UTF-8"));
			} catch (Exception e) {
				throw new ServiceProcessException("파라미터 오류");
			}
		}
		//step2. key=value 로 post 요청
		Map<String,Object> payResultMap = null;
		try {
			URL url = new URL("https://iniapi.inicis.com/api/v1/extra");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			if (conn != null) {
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
				conn.setRequestMethod("POST");
				conn.setDefaultUseCaches(false);
				conn.setDoOutput(true);
				
				if (conn.getDoOutput()) {
					conn.getOutputStream().write(postData.toString().getBytes("UTF-8"));
					conn.getOutputStream().flush();
					conn.getOutputStream().close();
				}
				conn.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
				
				//step3. 요청 결과
				payResultMap = new ObjectMapper().readValue(br.readLine(), new TypeReference<HashMap<String,Object>>() {});
				br.close();
			}

		}catch(Exception e ) {
			throw new ServiceProcessException("결제 조회 오류");
		} 
		egovLogger.info("[조회 결과] = " + payResultMap);
		return new ProcessResultVO<Map<String,Object>>(payResultMap);
	}
	
}