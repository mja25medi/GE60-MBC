package egovframework.edutrack.modules.student.student.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class StudentExcelVO extends DefaultVO {

	private static final long serialVersionUID = -6731261092116648229L;
	private String  userId;
	private String  userNo;
	private String  userNm;
	private String  stdNo;
	private String  userPass;
//	private String  ssn;
	private String  compNm;
	private String  birth;
	private String  sexCd;
	private String  email;
	private String  mobileNo;
	private String  homePhoneno;
	private String  homeAddrDivCd;
	private String  homePostNo;
	private String  homeAddr1;
	private String  homeAddr2;

	private String  errorCode;
	private String  userSts; //-- 사용자 상태
	private String  enrlSts; //-- 수강신청 상태
	
	private String  lineNo;
	private Integer certScore;
	private String  certSts;
	private String  certPassYn;
	private String  certAplcDttm;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
/*	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
*/	
	public String getCompNm() {
		return compNm;
	}
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getHomePhoneno() {
		return homePhoneno;
	}
	public void setHomePhoneno(String homePhoneno) {
		this.homePhoneno = homePhoneno;
	}
	public String getHomeAddrDivCd() {
		return homeAddrDivCd;
	}
	public void setHomeAddrDivCd(String homeAddrDivCd) {
		this.homeAddrDivCd = homeAddrDivCd;
	}
	public String getHomePostNo() {
		return homePostNo;
	}
	public void setHomePostNo(String homePostNo) {
		this.homePostNo = homePostNo;
	}
	public String getHomeAddr1() {
		return homeAddr1;
	}
	public void setHomeAddr1(String homeAddr1) {
		this.homeAddr1 = homeAddr1;
	}
	public String getHomeAddr2() {
		return homeAddr2;
	}
	public void setHomeAddr2(String homeAddr2) {
		this.homeAddr2 = homeAddr2;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getUserSts() {
		return userSts;
	}
	public void setUserSts(String userSts) {
		this.userSts = userSts;
	}
	public String getEnrlSts() {
		return enrlSts;
	}
	public void setEnrlSts(String enrlSts) {
		this.enrlSts = enrlSts;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public Integer getCertScore() {
		return certScore;
	}
	public void setCertScore(Integer certScore) {
		this.certScore = certScore;
	}
	public String getCertSts() {
		return certSts;
	}
	public void setCertSts(String certSts) {
		this.certSts = certSts;
	}
	public String getCertPassYn() {
		return certPassYn;
	}
	public void setCertPassYn(String certPassYn) {
		this.certPassYn = certPassYn;
	}
	public String getCertAplcDttm() {
		return certAplcDttm;
	}
	public void setCertAplcDttm(String certAplcDttm) {
		this.certAplcDttm = certAplcDttm;
	}
	
	
}
