package egovframework.edutrack.modules.log.classconn.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogClassConnVO extends DefaultVO {

	private static final long serialVersionUID = 474590023890786857L;

	private String  orgCd;
	private String  crsCreCd;
	private String  year;
	private String  month;
	private String  day;
	private String  userNo;
	private Integer connCnt;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Integer getConnCnt() {
		return connCnt;
	}
	public void setConnCnt(Integer connCnt) {
		this.connCnt = connCnt;
	}
}
