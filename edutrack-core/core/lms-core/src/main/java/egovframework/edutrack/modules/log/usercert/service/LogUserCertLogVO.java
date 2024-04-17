package egovframework.edutrack.modules.log.usercert.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogUserCertLogVO extends DefaultVO {
	
	private static final long serialVersionUID = 6068465611927738735L;
	
	private String crsCd;	
	private String crsYear;
	private String crsTerm;
	private String stdNo;	
	private String userNo;
	private String userId;
	private String crsCreCd;
	private String crsCreNm;
	private String certMthd;
	private String certMthdNm;
	private String certEvalCd;
	private String certEvalNm;
	private String connIp;
	private String certDttm;
	private String creTerm;

	
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getCrsYear() {
		return crsYear;
	}
	public void setCrsYear(String crsYear) {
		this.crsYear = crsYear;
	}
	public String getCrsTerm() {
		return crsTerm;
	}
	public void setCrsTerm(String crsTerm) {
		this.crsTerm = crsTerm;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
	public String getCertMthd() {
		return certMthd;
	}
	public void setCertMthd(String certMthd) {
		this.certMthd = certMthd;
	}
	public String getCertMthdNm() {
		return certMthdNm;
	}
	public void setCertMthdNm(String certMthdNm) {
		this.certMthdNm = certMthdNm;
	}
	public String getCertEvalCd() {
		return certEvalCd;
	}
	public void setCertEvalCd(String certEvalCd) {
		this.certEvalCd = certEvalCd;
	}
	public String getCertEvalNm() {
		return certEvalNm;
	}
	public void setCertEvalNm(String certEvalNm) {
		this.certEvalNm = certEvalNm;
	}
	public String getConnIp() {
		return connIp;
	}
	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}
	public String getCertDttm() {
		return certDttm;
	}
	public void setCertDttm(String certDttm) {
		this.certDttm = certDttm;
	}
	public String getCreTerm() {
		return creTerm;
	}
	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}
	
}