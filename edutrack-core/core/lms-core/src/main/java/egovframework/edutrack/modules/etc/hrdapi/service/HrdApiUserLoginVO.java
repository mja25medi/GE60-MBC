package egovframework.edutrack.modules.etc.hrdapi.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class HrdApiUserLoginVO extends DefaultVO {

	private static final long serialVersionUID = 1L;
	private int num;               //'과정코드'
	private String agentPk;        //'훈련기관 PK'
	private int seq;               //'시퀀스'
	private String userAgentPk;    //'훈련기관 회원PK(구분자(,)로 연결한 회원 PK'
	private String loginDate;      //'로그인시간'
	private String loginIp;        //'로그인IP'
	private String syncStatus;     //'연동상태'
	private String syncResultMsg;  //'연동결과메세지'
	private String regNo;          //'등록자'
	private String regDttm;        //'등록일시'
	private String modNo;          //'수정자'
	private String modDttm;        //'수정일시'
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(String agentPk) {
		this.agentPk = agentPk;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUserAgentPk() {
		return userAgentPk;
	}
	public void setUserAgentPk(String userAgentPk) {
		this.userAgentPk = userAgentPk;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}
	public String getSyncResultMsg() {
		return syncResultMsg;
	}
	public void setSyncResultMsg(String syncResultMsg) {
		this.syncResultMsg = syncResultMsg;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	
}
