package egovframework.edutrack.modules.atalk;

import egovframework.edutrack.comm.service.DefaultVO;

public class AtalkVO extends DefaultVO {

	private static final long serialVersionUID = 5248390587068287399L;
	
	/**
	 * 발송자 id
	 */
	private String tranEtc1;
	
	/**
	 * 수신자 핸드폰 번호
	 */
	private String tranPhone;
	
	/**
	 * 발송 템플릿 코드 
	 */
	private String tranTmplCd; 
	
	/**
	 * 발송 키
	 */
	private String tranSenderKey;
	
	/**
	 * 제목
	 */
	private String tranSubject;
	
	/**
	 * 발송메시지
	 */
	private String tranMsg;
	
	/**
	 * 발솔 변환 메시지
	 */
	private String tranReplaceMsg;
	
	/**
	 * 송진자 번호 
	 */
	private String tranCallback;

	public String getTranEtc1() {
		return tranEtc1;
	}

	public void setTranEtc1(String tranEtc1) {
		this.tranEtc1 = tranEtc1;
	}

	public String getTranTmplCd() {
		return tranTmplCd;
	}

	public void setTranTmplCd(String tranTmplCd) {
		this.tranTmplCd = tranTmplCd;
	}

	public String getTranSenderKey() {
		return tranSenderKey;
	}

	public void setTranSenderKey(String tranSenderKey) {
		this.tranSenderKey = tranSenderKey;
	}

	public String getTranSubject() {
		return tranSubject;
	}

	public void setTranSubject(String tranSubject) {
		this.tranSubject = tranSubject;
	}

	public String getTranMsg() {
		return tranMsg;
	}

	public void setTranMsg(String tranMsg) {
		this.tranMsg = tranMsg;
	}

	public String getTranReplaceMsg() {
		return tranReplaceMsg;
	}

	public void setTranReplaceMsg(String tranReplaceMsg) {
		this.tranReplaceMsg = tranReplaceMsg;
	}

	public String getTranCallback() {
		return tranCallback;
	}

	public void setTranCallback(String tranCallback) {
		this.tranCallback = tranCallback;
	}

	public String getTranPhone() {
		return tranPhone;
	}

	public void setTranPhone(String tranPhone) {
		this.tranPhone = tranPhone;
	}

}
