package egovframework.edutrack.modules.course.createcoursetimetable.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class TimetableVO
		extends DefaultVO {

	private static final long	serialVersionUID	= -6334826522849103246L;
	private String	crsCreCd;
	private Integer declsNo;
	private int		tmtabSn;
	private int		tmtabDay;
	private int		tmtabOdr;
	private String	tmtabType;
	private String	tmtabTypeNm;
	private String	sbjCd;
	private String	sbjNm;
	private String	eduTm;
	private String	userNo;
	private String	userNm;
	private String	eduCts;
	private String	tmtabMonthday;
	private String	addTch;

	private int		subCnt;

	private String	strTmtabSn;

	/**
	 * @return the subCnt
	 */
	public int getSubCnt() {
		return this.subCnt;
	}

	/**
	 * @param subCnt the subCnt to set
	 */
	public void setSubCnt(int subCnt) {
		this.subCnt = subCnt;
	}

	public Integer getDeclsNo() {
		return declsNo;
	}

	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}

	/**
	 * @return the tmtabMonthday
	 */
	public String getTmtabMonthday() {
		return this.tmtabMonthday;
	}

	/**
	 * @param tmtabMonthday the tmtabMonthday to set
	 */
	public void setTmtabMonthday(String tmtabMonthday) {
		this.tmtabMonthday = tmtabMonthday;
	}

	/**
	 * @return the addTch
	 */
	public String getAddTch() {
		return this.addTch;
	}

	/**
	 * @param addTch the addTch to set
	 */
	public void setAddTch(String addTch) {
		this.addTch = addTch;
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
	 * @return the tmtabSn
	 */
	public int getTmtabSn() {
		return this.tmtabSn;
	}

	/**
	 * @param tmtabSn the tmtabSn to set
	 */
	public void setTmtabSn(int tmtabSn) {
		this.tmtabSn = tmtabSn;
	}

	/**
	 * @return the tmtabDay
	 */
	public int getTmtabDay() {
		return this.tmtabDay;
	}

	/**
	 * @param tmtabDay the tmtabDay to set
	 */
	public void setTmtabDay(int tmtabDay) {
		this.tmtabDay = tmtabDay;
	}

	/**
	 * @return the tmtabOdr
	 */
	public int getTmtabOdr() {
		return this.tmtabOdr;
	}

	/**
	 * @param tmtabOdr the tmtabOdr to set
	 */
	public void setTmtabOdr(int tmtabOdr) {
		this.tmtabOdr = tmtabOdr;
	}

	/**
	 * @return the tmtabType
	 */
	public String getTmtabType() {
		return this.tmtabType;
	}

	/**
	 * @param tmtabType the tmtabType to set
	 */
	public void setTmtabType(String tmtabType) {
		this.tmtabType = tmtabType;
	}

	/**
	 * @return the tmtabTypeNm
	 */
	public String getTmtabTypeNm() {
		return this.tmtabTypeNm;
	}

	/**
	 * @param tmtabTypeNm the tmtabTypeNm to set
	 */
	public void setTmtabTypeNm(String tmtabTypeNm) {
		this.tmtabTypeNm = tmtabTypeNm;
	}

	/**
	 * @return the sbjCd
	 */
	public String getSbjCd() {
		return this.sbjCd;
	}

	/**
	 * @param sbjCd the sbjCd to set
	 */
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	/**
	 * @return the sbjNm
	 */
	public String getSbjNm() {
		return this.sbjNm;
	}

	/**
	 * @param sbjNm the sbjNm to set
	 */
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}

	/**
	 * @return the eduTm
	 */
	public String getEduTm() {
		return this.eduTm;
	}

	/**
	 * @param eduTm the eduTm to set
	 */
	public void setEduTm(String eduTm) {
		this.eduTm = eduTm;
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
	 * @return the eduCts
	 */
	public String getEduCts() {
		return this.eduCts;
	}

	/**
	 * @param eduCts the eduCts to set
	 */
	public void setEduCts(String eduCts) {
		this.eduCts = eduCts;
	}

	/**
	 * @return the strTmtabSn
	 */
	public String getStrTmtabSn() {
		return this.strTmtabSn;
	}

	/**
	 * @param strTmtabSn the strTmtabSn to set
	 */
	public void setStrTmtabSn(String strTmtabSn) {
		this.strTmtabSn = strTmtabSn;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimetableDTO [addTch=" + this.addTch + ", crsCreCd=" + this.crsCreCd + ", eduCts="
				+ this.eduCts + ", eduTm=" + this.eduTm + ", sbjCd=" + this.sbjCd + ", sbjNm="
				+ this.sbjNm + ", strTmtabSn=" + this.strTmtabSn + ", subCnt=" + this.subCnt
				+ ", tmtabDay=" + this.tmtabDay + ", tmtabMonthday=" + this.tmtabMonthday
				+ ", tmtabOdr=" + this.tmtabOdr + ", tmtabSn=" + this.tmtabSn + ", tmtabType="
				+ this.tmtabType + ", tmtabTypeNm=" + this.tmtabTypeNm + ", userNm=" + this.userNm
				+ ", userNo=" + this.userNo + "]";
	}
}