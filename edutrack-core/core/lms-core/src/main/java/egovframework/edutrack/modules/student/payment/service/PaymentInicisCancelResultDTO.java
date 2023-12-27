package egovframework.edutrack.modules.student.payment.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//없는 필드 무시 : 이니시스에서 필드 변경하게되는 경우, 오류가 날 수 있어 삭제하면 안됨
public class PaymentInicisCancelResultDTO {
	/**
	 * 이니시스 취소/환불 결과 응답 DTO
	 */
	private String resultCode;//결과코드 "00":성공, 이외 실패
	private String resultMsg;//결과메세지
	private String cancelDate;//취소일자 [YYYYMMDD]
	private String cancelTime;//취소시간 [hhmmss]
	private String cshrCancelNum;//현금영수증 취소승인번호(현금영수증 발행건에 한함)
	private String detailResultCode;//취소실패 응답시 상세코드
	private String receiptInfo;//특정 가맹점 전용 응답필드
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public String getCshrCancelNum() {
		return cshrCancelNum;
	}
	public void setCshrCancelNum(String cshrCancelNum) {
		this.cshrCancelNum = cshrCancelNum;
	}
	public String getDetailResultCode() {
		return detailResultCode;
	}
	public void setDetailResultCode(String detailResultCode) {
		this.detailResultCode = detailResultCode;
	}
	public String getReceiptInfo() {
		return receiptInfo;
	}
	public void setReceiptInfo(String receiptInfo) {
		this.receiptInfo = receiptInfo;
	}
	
	@Override
	public String toString() {
		return "PaymentInicisCancelResultDTO [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", cancelDate="
				+ cancelDate + ", cancelTime=" + cancelTime + ", cshrCancelNum=" + cshrCancelNum + ", detailResultCode="
				+ detailResultCode + ", receiptInfo=" + receiptInfo + "]";
	}
	
}
