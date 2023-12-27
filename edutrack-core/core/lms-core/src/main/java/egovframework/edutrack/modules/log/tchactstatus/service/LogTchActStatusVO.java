package egovframework.edutrack.modules.log.tchactstatus.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogTchActStatusVO extends DefaultVO{

	private static final long serialVersionUID = 5142818098139331545L;
	private String  orgCd;
	private String  userNo;
	private String  userNm;
	private String  userId;
	private String  email;
	private String  mobileNo;
	private Integer endCrsCnt = 0;
	private Integer ingCrsCnt = 0;
	private Integer befCrsCnt = 0;
	private String  tchCtgrCd;
	private String  tchDivCd;
	private String  tchCtgrNm;
	private String  tchDivNm;
	private String  crer;
	private String  major;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getEndCrsCnt() {
		return endCrsCnt;
	}
	public void setEndCrsCnt(Integer endCrsCnt) {
		this.endCrsCnt = endCrsCnt;
	}
	public Integer getIngCrsCnt() {
		return ingCrsCnt;
	}
	public void setIngCrsCnt(Integer ingCrsCnt) {
		this.ingCrsCnt = ingCrsCnt;
	}
	public Integer getBefCrsCnt() {
		return befCrsCnt;
	}
	public void setBefCrsCnt(Integer befCrsCnt) {
		this.befCrsCnt = befCrsCnt;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getTchCtgrCd() {
		return tchCtgrCd;
	}
	public void setTchCtgrCd(String tchCtgrCd) {
		this.tchCtgrCd = tchCtgrCd;
	}
	public String getTchDivCd() {
		return tchDivCd;
	}
	public void setTchDivCd(String tchDivCd) {
		this.tchDivCd = tchDivCd;
	}
	public String getCrer() {
		return crer;
	}
	public void setCrer(String crer) {
		this.crer = crer;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getTchCtgrNm() {
		return tchCtgrNm;
	}
	public void setTchCtgrNm(String tchCtgrNm) {
		this.tchCtgrNm = tchCtgrNm;
	}
	public String getTchDivNm() {
		return tchDivNm;
	}
	public void setTchDivNm(String tchDivNm) {
		this.tchDivNm = tchDivNm;
	}
}
