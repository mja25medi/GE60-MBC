package egovframework.edutrack.modules.student.payment.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//없는 필드 무시 : 이니시스에서 필드 변경하게되는 경우, 오류가 날 수 있어 삭제하면 안됨
public class InicisParCancelAbleViewResultDTO {
	/**
	 * 이니시스 부분 취소 가능여부조회 API 결과 응답 DTO
	 */
	private String resultCode;//결과코드 "00":성공, 이외 실패
	private String resultMsg;//결과메세지
	private String tid;//부분취소 거래번호
	private String prtcCheckRemains;//부분취소 잔액
	private String prtcCheckCnt;//부분취소 진행 누적회차
	private String partCheckCode;//부분취소 가능여부	["0": 불가능, "1": 가능]
	
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
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getPrtcCheckRemains() {
		return prtcCheckRemains;
	}
	public void setPrtcCheckRemains(String prtcCheckRemains) {
		this.prtcCheckRemains = prtcCheckRemains;
	}
	public String getPrtcCheckCnt() {
		return prtcCheckCnt;
	}
	public void setPrtcCheckCnt(String prtcCheckCnt) {
		this.prtcCheckCnt = prtcCheckCnt;
	}
	public String getPartCheckCode() {
		return partCheckCode;
	}
	public void setPartCheckCode(String partCheckCode) {
		this.partCheckCode = partCheckCode;
	}
	@Override
	public String toString() {
		return "InicisParCancelAbleViewResultDTO [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", tid="
				+ tid + ", prtcCheckRemains=" + prtcCheckRemains + ", prtcCheckCnt=" + prtcCheckCnt + ", partCheckCode="
				+ partCheckCode + "]";
	}
	
}
