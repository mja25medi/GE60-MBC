package egovframework.edutrack.modules.student.payment.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//없는 필드 무시 : 이니시스에서 필드 변경하게되는 경우, 오류가 날 수 있어 삭제하면 안됨
public class PaymentInicisParCancelResultDTO {
	/**
	 * 이니시스 부분 취소 API 결과 응답 DTO
	 */
	private String resultCode;//결과코드 "00":성공, 이외 실패
	private String resultMsg;//결과메세지
	private String prtcDate;//취소일자 [YYYYMMDD]
	private String prtcTime;//취소시간 [hhmmss]
	private String prtcTid;//원 승인 거래번호
	private String tid;//부분취소 거래번호
	private String prtcPrice;//부분취소금액
	private String prtcRemains;//부분취소 후 남은금액
	private String prtcCnt;//부분취소 요청 횟수
	private String prtcType;//부분취소 구분 ["0":재승인방식, "1":부분취소]
	private String pointAmount;//부분취소 시 취소된 포인트 금액
	private String discountAmount;//부분취소 시 취소된 할인 금액
	private String creditAmount;//부분취소 시 취소된 여신 금액
	
	private String receiptInfo;//특정 가맹점 전용 응답필드
	
	public String getReceiptInfo() {
		return receiptInfo;
	}
	public void setReceiptInfo(String receiptInfo) {
		this.receiptInfo = receiptInfo;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
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
	public String getPrtcDate() {
		return prtcDate;
	}
	public void setPrtcDate(String prtcDate) {
		this.prtcDate = prtcDate;
	}
	public String getPrtcTime() {
		return prtcTime;
	}
	public void setPrtcTime(String prtcTime) {
		this.prtcTime = prtcTime;
	}
	public String getPrtcTid() {
		return prtcTid;
	}
	public void setPrtcTid(String prtcTid) {
		this.prtcTid = prtcTid;
	}
	public String getPrtcPrice() {
		return prtcPrice;
	}
	public void setPrtcPrice(String prtcPrice) {
		this.prtcPrice = prtcPrice;
	}
	public String getPrtcRemains() {
		return prtcRemains;
	}
	public void setPrtcRemains(String prtcRemains) {
		this.prtcRemains = prtcRemains;
	}
	public String getPrtcCnt() {
		return prtcCnt;
	}
	public void setPrtcCnt(String prtcCnt) {
		this.prtcCnt = prtcCnt;
	}
	public String getPrtcType() {
		return prtcType;
	}
	public void setPrtcType(String prtcType) {
		this.prtcType = prtcType;
	}
	public String getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(String pointAmount) {
		this.pointAmount = pointAmount;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	@Override
	public String toString() {
		return "PaymentInicisParCancelResultDTO [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", prtcDate="
				+ prtcDate + ", prtcTime=" + prtcTime + ", prtcTid=" + prtcTid + ", tid=" + tid + ", prtcPrice="
				+ prtcPrice + ", prtcRemains=" + prtcRemains + ", prtcCnt=" + prtcCnt + ", prtcType=" + prtcType
				+ ", pointAmount=" + pointAmount + ", discountAmount=" + discountAmount + ", creditAmount="
				+ creditAmount + "]";
	}
}
