package egovframework.edutrack.modules.course.createcourseteacher.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;

public class TeacherVO
		extends DefaultVO {

	private static final long	serialVersionUID	= -6511464431283901251L;
	private String  orgCd;
	private String	crsCreCd;
	private String	crsCd;
	private String	userNo;
	private String	userNm;
	private String	userId;
	private String	mobileNo;
	private String	email;
	private String	birth;
	private String	wwwAuthGrpCd;
	private String	admAuthGrpCd;
	private String	tchType;
	private String	tchTypeNm;
	private int		tchOdr;
	private String	tchYn;
	private Integer	tchRateScore;
	private String	posngNm;
	private String	deptOrgNm;
	private String  lecfeePayCritCd;
	private String  tchPosCd;
	private Integer declsNo;
	
	private List<TeacherVO> teacherList;
	
	public TeacherVO() {
		setTeacherList(new ArrayList<TeacherVO>());
	}

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public Integer getTchRateScore() {
		return this.tchRateScore;
	}
	public void setTchRateScore(Integer tchRateScore) {
		this.tchRateScore = tchRateScore;
	}
	/**
	 * @return the tchYn
	 */
	public String getTchYn() {
		return this.tchYn;
	}

	/**
	 * @param tchYn the tchYn to set
	 */
	public void setTchYn(String tchYn) {
		this.tchYn = tchYn;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return this.mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return this.birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the crsCreCd
	 */
	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	/**
	 * @param crsCreCd the crsCreCd to set
	 */
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return this.userNo;
	}

	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return this.userNm;
	}

	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the wwwAuthGrpCd
	 */
	public String getWwwAuthGrpCd() {
		return this.wwwAuthGrpCd;
	}

	/**
	 * @param wwwAuthGrpCd the wwwAuthGrpCd to set
	 */
	public void setWwwAuthGrpCd(String wwwAuthGrpCd) {
		this.wwwAuthGrpCd = wwwAuthGrpCd;
	}

	/**
	 * @return the admAuthGrpCd
	 */
	public String getAdmAuthGrpCd() {
		return this.admAuthGrpCd;
	}

	/**
	 * @param admAuthGrpCd the admAuthGrpCd to set
	 */
	public void setAdmAuthGrpCd(String admAuthGrpCd) {
		this.admAuthGrpCd = admAuthGrpCd;
	}

	/**
	 * @return the tchType
	 */
	public String getTchType() {
		return this.tchType;
	}

	/**
	 * @param tchType the tchType to set
	 */
	public void setTchType(String tchType) {
		this.tchType = tchType;
	}

	/**
	 * @return the tchTypeNm
	 */
	public String getTchTypeNm() {
		return this.tchTypeNm;
	}

	/**
	 * @param tchTypeNm the tchTypeNm to set
	 */
	public void setTchTypeNm(String tchTypeNm) {
		this.tchTypeNm = tchTypeNm;
	}

	/**
	 * @return the tchOdr
	 */
	public int getTchOdr() {
		return this.tchOdr;
	}

	/**
	 * @param tchOdr the tchOdr to set
	 */
	public void setTchOdr(int tchOdr) {
		this.tchOdr = tchOdr;
	}

	public String getPosngNm() {
		return posngNm;
	}

	public void setPosngNm(String posngNm) {
		this.posngNm = posngNm;
	}

	public String getDeptOrgNm() {
		return deptOrgNm;
	}

	public void setDeptOrgNm(String deptOrgNm) {
		this.deptOrgNm = deptOrgNm;
	}


	public String getLecfeePayCritCd() {
		return lecfeePayCritCd;
	}


	public void setLecfeePayCritCd(String lecfeePayCritCd) {
		this.lecfeePayCritCd = lecfeePayCritCd;
	}


	public String getTchPosCd() {
		return tchPosCd;
	}


	public void setTchPosCd(String tchPosCd) {
		this.tchPosCd = tchPosCd;
	}


	public Integer getDeclsNo() {
		return declsNo;
	}


	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}

	public List<TeacherVO> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<TeacherVO> teacherList) {
		this.teacherList = teacherList;
	}


}
