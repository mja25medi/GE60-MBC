package egovframework.edutrack.comm.util.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.util.security.AES128;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.modules.student.student.service.StudentVO;

public class InicisUtil {
	/**
	 * 이니시스 전체 취소 파라미터 세팅 - 22.12 이니시스 샘플 기준
	 */
	public static Map<String, Object> setRefundParameter(HttpServletRequest request, StudentVO studentVO) {
		//int stdPrice = StringUtil.nvl(studentVO.getStdPrice(),0);//결제 세부 건의 금액(std)
		//String enrlSts = StringUtil.nvl(studentVO.getEnrlSts());
		String tid = StringUtil.nvl(studentVO.getTid());
		String paymethod = StringUtil.nvl(studentVO.getPayMethod());
		
		//step1. 요청을 위한 파라미터 설정
		Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = "ItEQKi3rY7uvDS8l";//API key 
		String type = "Refund";
		String timestamp = fourteen_format.format(date_now);
		String clientIp = request.getRemoteAddr();
		
		String testPayYn				= Constants.INICIS_TEST_YN;
		String mid = "";
		String deviceType = studentVO.getDeviceType();
		if("Y".equals(testPayYn)){
			mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
		}else if("PC".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_PC_MID;
		}else if("MOBILE".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_MOBILE_MID;
		}
		
		String msg = "취소요청";
		
		// Hash Encryption
		String data_hash = key + type + paymethod + timestamp + clientIp + mid + tid;
		String hashData = CryptoUtil.SHA512Encode(data_hash);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("type", type);
		resultMap.put("paymethod", paymethod);
		resultMap.put("timestamp", timestamp);
		resultMap.put("clientIp", clientIp);
		resultMap.put("mid", mid);
		resultMap.put("tid", tid);
		resultMap.put("msg", msg);
		resultMap.put("hashData", hashData);
		return resultMap;
	}
	
	/**
	 * 이니시스 부분취소 파라미터 세팅 - 22.12 이니시스 샘플 기준
	 * @param request
	 * @param viewStudentVO
	 * @param requestStudentVO 
	 * @return
	 */
	public static Map<String, Object> setPartialRefundParameter(HttpServletRequest request, StudentVO viewStudentVO, StudentVO requestStudentVO) {
		String enrlSts = StringUtil.nvl(viewStudentVO.getEnrlSts());
		String tid = StringUtil.nvl(viewStudentVO.getTid());
		String paymethod = StringUtil.nvl(viewStudentVO.getPayMethod());
		int stdPrice = StringUtil.nvl(viewStudentVO.getStdPrice(),0);//결제 세부 건의 금액(std)
		int refundPay = StringUtil.nvl(viewStudentVO.getRepayPrice(),0);//현재까지 환불 금액
		int paymPrice = StringUtil.nvl(viewStudentVO.getPaymPrice(),0);//결제 건의 총 금액(pay_info)
		int stdRepayPriceSum = StringUtil.nvl(viewStudentVO.getStdRepayPriceSum(), 0);//결제 건의 환불금액 합(환불 처리 총 금액)
		
		int inputRefundPay = StringUtil.nvl(requestStudentVO.getRepayPrice(), 0);//입력받은 환불금액
		int lmsConfirmPrice = paymPrice - inputRefundPay - stdRepayPriceSum;//총 금액 - (입력받은 환불금액 + 기존 환불처리 총 금액)
		
		String price = String.valueOf(inputRefundPay);//입력받은 환불금액
		String confirmPrice = String.valueOf(lmsConfirmPrice);//환불 처리 후 남은 금액
		
		//step1. 요청을 위한 파라미터 설정
		Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = "ItEQKi3rY7uvDS8l";//운영 시, 운영 키로 수정 
		String type = "PartialRefund";
		String timestamp = fourteen_format.format(date_now);
		String clientIp = request.getRemoteAddr();
		String msg = "취소요청";
		
		String testPayYn				= Constants.INICIS_TEST_YN;
		String mid = "";
		String deviceType = viewStudentVO.getDeviceType();
		if("Y".equals(testPayYn)){
			mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
		}else if("PC".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_PC_MID;
		}else if("MOBILE".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_MOBILE_MID;
		}
		
		
		// Hash Encryption
		String data_hash = key + type + paymethod + timestamp + clientIp + mid + tid + price + confirmPrice;
		String hashData = CryptoUtil.SHA512Encode(data_hash);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("type", type);
		resultMap.put("paymethod", paymethod);
		resultMap.put("timestamp", timestamp);
		resultMap.put("clientIp", clientIp);
		resultMap.put("mid", mid);
		resultMap.put("tid", tid);
		resultMap.put("msg", msg);
		resultMap.put("price", price);
		resultMap.put("confirmPrice", confirmPrice);
		resultMap.put("hashData", hashData);
		return resultMap;
	}
	
	/**
	 * 이니시스 거래조회 파라미터 세팅 - 22.12 이니시스 샘플 기준
	 * @param request
	 * @param studentVO
	 * @return
	 */
	public static Map<String, Object> setSelectParameter(HttpServletRequest request, StudentVO studentVO) {
		String tid = StringUtil.nvl(studentVO.getTid());
		String paymethod = "Inquiry";
		
		Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = "ItEQKi3rY7uvDS8l";//운영 시, 운영 키로 수정 
		String type = "Extra";
		String timestamp = fourteen_format.format(date_now);
		String clientIp = request.getRemoteAddr();
		
		String testPayYn				= Constants.INICIS_TEST_YN;
		String mid = "";
		String deviceType = studentVO.getDeviceType();
		if("Y".equals(testPayYn)){
			mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
		}else if("PC".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_PC_MID;
		}else if("MOBILE".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_MOBILE_MID;
		}
		
		// Hash Encryption
		String data_hash = key + type + paymethod + timestamp + clientIp + mid;
		String hashData = CryptoUtil.SHA512Encode(data_hash);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("type", type);
		resultMap.put("paymethod", paymethod);
		resultMap.put("timestamp", timestamp);
		resultMap.put("clientIp", clientIp);
		resultMap.put("mid", mid);
		resultMap.put("originalTid", tid);
		resultMap.put("hashData", hashData);
		return resultMap;
	}
	
	/**
	 * 이니시스 부분취소 가능여부조회 파라미터 세팅 - 22.12 이니시스 샘플 기준
	 * @param request
	 * @param studentVO
	 * @return
	 */
	public static Map<String, Object> setSelectAblePartialRefundParameter(HttpServletRequest request, StudentVO studentVO) {
		String tid = StringUtil.nvl(studentVO.getTid());
		String paymethod = "Inquiry";
		
		Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = "ItEQKi3rY7uvDS8l";//운영 시, 운영 키로 수정 
		String type = "RepayChk";
		String timestamp = fourteen_format.format(date_now);
		String clientIp = request.getRemoteAddr();
		
		String testPayYn				= Constants.INICIS_TEST_YN;
		String mid = "";
		String deviceType = studentVO.getDeviceType();
		if("Y".equals(testPayYn)){
			mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
		}else if("PC".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_PC_MID;
		}else if("MOBILE".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_MOBILE_MID;
		}
		
		// Hash Encryption
		String data_hash = key + type + paymethod + timestamp + clientIp + mid;
		String hashData = CryptoUtil.SHA512Encode(data_hash);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("type", type);
		resultMap.put("paymethod", paymethod);
		resultMap.put("timestamp", timestamp);
		resultMap.put("clientIp", clientIp);
		resultMap.put("mid", mid);
		resultMap.put("originalTid", tid);
		resultMap.put("hashData", hashData);
		return resultMap;
	}
	
	/**
	 * 이니시스 가상계좌 전체 환불 파라미터 세팅 - 22.12 이니시스 샘플 기준
	 * @param request
	 * @param studentVO
	 * @return
	 */
	public static Map<String, Object> setVRefundParameter(HttpServletRequest request, StudentVO studentVO, StudentVO requestStudentVO) {
		AES128 aes128 = new AES128();
		//int stdPrice = StringUtil.nvl(studentVO.getStdPrice(),0);//결제 세부 건의 금액(std)
		//String enrlSts = StringUtil.nvl(studentVO.getEnrlSts());
		String tid = StringUtil.nvl(studentVO.getTid());
		
		//step1. 요청을 위한 파라미터 설정
		Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = "ItEQKi3rY7uvDS8l";//API key
		String iv = "HYb3yQ4f65QL89==";
		String type = "Refund";
		String paymethod = "Vacct";//고정
		String timestamp = fourteen_format.format(date_now);
		String clientIp = request.getRemoteAddr();
		String msg = "가상계좌 환불요청";
		
		String testPayYn				= Constants.INICIS_TEST_YN;
		String mid = "";
		String deviceType = studentVO.getDeviceType();
		if("Y".equals(testPayYn)){
			mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
		}else if("PC".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_PC_MID;
		}else if("MOBILE".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_MOBILE_MID;
		}
		
		String refundAcctNum = "";//환불계좌번호 		AES암호화 대상파라미터
		String refundBankCode = "";//환불계좌 은행코드
		String refundAcctName = "";//환불계좌 예금주명
		
		// AES Encryption
		String enc_refundAcctNum = "";
		try {
			enc_refundAcctNum = aes128.encAES(refundAcctNum, key, iv);
		} catch (Exception e) {
			throw new ServiceProcessException("암호화 오류");
		}
		
		// Hash Encryption
		String data_hash = key + type + paymethod + timestamp + clientIp + mid + tid + enc_refundAcctNum;
		String hashData = CryptoUtil.SHA512Encode(data_hash);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("type", type);
		resultMap.put("paymethod", paymethod);
		resultMap.put("timestamp", timestamp);
		resultMap.put("clientIp", clientIp);
		resultMap.put("mid", mid);
		resultMap.put("tid", tid);
		resultMap.put("msg", msg);
		resultMap.put("refundAcctNum", enc_refundAcctNum);
		resultMap.put("refundBankCode", refundBankCode);
		resultMap.put("refundAcctName", refundAcctName);
		resultMap.put("hashData", hashData);
		return resultMap;
	}
	
	/**
	 * 이니시스 가상계좌 부분 환불 파라미터 세팅 - 22.12 이니시스 샘플 기준
	 * @param request
	 * @param viewStudentVO
	 * @param requestStudentVO
	 * @return
	 */
	public static Map<String, Object> setVPartialRefundParameter(HttpServletRequest request, StudentVO viewStudentVO, StudentVO requestStudentVO) {
		AES128 aes128 = new AES128();
		
		String enrlSts = StringUtil.nvl(viewStudentVO.getEnrlSts());
		String tid = StringUtil.nvl(viewStudentVO.getTid());
		int stdPrice = StringUtil.nvl(viewStudentVO.getStdPrice(),0);//결제 세부 건의 금액(std)
		int refundPay = StringUtil.nvl(viewStudentVO.getRepayPrice(),0);//현재까지 환불 금액
		int paymPrice = StringUtil.nvl(viewStudentVO.getPaymPrice(),0);//결제 건의 총 금액(pay_info)
		int stdRepayPriceSum = StringUtil.nvl(viewStudentVO.getStdRepayPriceSum(), 0);//결제 건의 환불금액 합(환불 처리 총 금액)
		
		int inputRefundPay = StringUtil.nvl(requestStudentVO.getRepayPrice(), 0);//입력받은 환불금액
		int lmsConfirmPrice = paymPrice - inputRefundPay - stdRepayPriceSum;//총 금액 - (입력받은 환불금액 + 기존 환불처리 총 금액)
		
		String price = String.valueOf(inputRefundPay);//입력받은 환불금액
		String confirmPrice = String.valueOf(lmsConfirmPrice);//환불 처리 후 남은 금액
		
		//step1. 요청을 위한 파라미터 설정
		Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = "ItEQKi3rY7uvDS8l";//API key
		String iv = "HYb3yQ4f65QL89==";
		String type = "Refund";
		String paymethod = "Vacct";//고정
		String timestamp = fourteen_format.format(date_now);
		String clientIp = request.getRemoteAddr();
		String msg = "가상계좌 환불요청";
		
		String testPayYn				= Constants.INICIS_TEST_YN;
		String mid = "";
		String deviceType = viewStudentVO.getDeviceType();
		if("Y".equals(testPayYn)){
			mid = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
		}else if("PC".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_PC_MID;
		}else if("MOBILE".equals(deviceType)){
			mid = Constants.INICIS_SERVICE_MOBILE_MID;
		}
		
		String refundAcctNum = "";//환불계좌번호 		AES암호화 대상파라미터
		String refundBankCode = "";//환불계좌 은행코드
		String refundAcctName = "";//환불계좌 예금주명
		
		// AES Encryption
		String enc_refundAcctNum = "";
		try {
			enc_refundAcctNum = aes128.encAES(refundAcctNum, key, iv);
		} catch (Exception e) {
			throw new ServiceProcessException("암호화 오류");
		}
		
		// Hash Encryption
		String data_hash = key + type + paymethod + timestamp + clientIp + mid + tid + enc_refundAcctNum;
		String hashData = CryptoUtil.SHA512Encode(data_hash);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("type", type);
		resultMap.put("paymethod", paymethod);
		resultMap.put("timestamp", timestamp);
		resultMap.put("clientIp", clientIp);
		resultMap.put("mid", mid);
		resultMap.put("tid", tid);
		resultMap.put("msg", msg);
		resultMap.put("price", price);
		resultMap.put("confirmPrice", confirmPrice);
		resultMap.put("refundAcctNum", enc_refundAcctNum);
		resultMap.put("refundBankCode", refundBankCode);
		resultMap.put("refundAcctName", refundAcctName);
		resultMap.put("hashData", hashData);
		return resultMap;
	}
}
