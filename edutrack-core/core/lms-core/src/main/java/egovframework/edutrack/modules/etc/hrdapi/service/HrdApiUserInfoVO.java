package egovframework.edutrack.modules.etc.hrdapi.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class HrdApiUserInfoVO extends DefaultVO {

	private static final long serialVersionUID = 1L;
	private int num;               //'과정코드'
	private String agentPk;        //'훈련기관 PK'
	private int seq;               //'시퀀스'
	private String userAgentPk;    //'훈련기관 회원PK(구분자(,)로 연결한 회원 PK'
	private String userName;       //'회원이름'
	private String resNo;          //'생년월일성별'
	private String encResNo;       //'수강자소속'
	private String email;          //'이메일'
	private String mobile;         //'휴대폰'
	private String changeState;    //'변경상태 C: 삽입, U: 수정, D: 삭제'
	private String regDate;        //'연동일시'
	private String nwIno;          //'비용수급사업장번호'
	private String trneeSe;        //'훈련생구분'
	private String irglbrSe;       //'비정규직구분'
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getResNo() {
		return resNo;
	}
	public void setResNo(String resNo) {
		this.resNo = resNo;
	}
	public String getEncResNo() {
		return encResNo;
	}
	public void setEncResNo(String encResNo) {
		this.encResNo = encResNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getNwIno() {
		return nwIno;
	}
	public void setNwIno(String nwIno) {
		this.nwIno = nwIno;
	}
	public String getTrneeSe() {
		return trneeSe;
	}
	public void setTrneeSe(String trneeSe) {
		this.trneeSe = trneeSe;
	}
	public String getIrglbrSe() {
		return irglbrSe;
	}
	public void setIrglbrSe(String irglbrSe) {
		this.irglbrSe = irglbrSe;
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
