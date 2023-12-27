package egovframework.edutrack.modules.course.crstch.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 과정 강사 DTO
 * 
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 강사
 * </pre>
 * 
 * @author MediopiaTech
 */
public class CrsTchVO extends DefaultVO{

	private static final long serialVersionUID = -2739867985289932641L;
	private String  crsCd;
	private String  userNo;
	private String  userNm;
	private String  tchType;
	private String  tchTypeNm;
	private Integer tchOdr;
	
	private String  authGrpCd;
	private String  mobileNo;
	private String  email;
	private String  userId;
	
	private String  orgCd;
	
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
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
	public String getTchType() {
		return tchType;
	}
	public void setTchType(String tchType) {
		this.tchType = tchType;
	}
	public String getTchTypeNm() {
		return tchTypeNm;
	}
	public void setTchTypeNm(String tchTypeNm) {
		this.tchTypeNm = tchTypeNm;
	}
	public Integer getTchOdr() {
		return tchOdr;
	}
	public void setTchOdr(Integer tchOdr) {
		this.tchOdr = tchOdr;
	}
	public String getAuthGrpCd() {
		return authGrpCd;
	}
	public void setAuthGrpCd(String authGrpCd) {
		this.authGrpCd = authGrpCd;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
}
