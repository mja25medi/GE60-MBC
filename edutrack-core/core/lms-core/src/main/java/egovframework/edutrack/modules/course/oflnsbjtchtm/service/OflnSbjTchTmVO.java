package egovframework.edutrack.modules.course.oflnsbjtchtm.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OflnSbjTchTmVO extends DefaultVO{

	private static final long serialVersionUID = 405221978977746356L;
	
	private String  crsCreCd;
	private String  sbjCd;
	private String  userNo;
	private Integer declsNo;
	private Integer lecSn;
	private String  startDt;
	private String  endDt;
	private String  startTm;
	private String  startHour;
	private String  startMin;
	private String  endTm;
	private String  endHour;
	private String  endMin;
	private String  lecDesc;
	private String  classViewCd;
	private String  classViewNm;
	private String  viewYn;
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Integer getDeclsNo() {
		return declsNo;
	}
	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}
	public Integer getLecSn() {
		return lecSn;
	}
	public void setLecSn(Integer lecSn) {
		this.lecSn = lecSn;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getStartTm() {
		return startTm;
	}
	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}
	public String getEndTm() {
		return endTm;
	}
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}
	public String getLecDesc() {
		return lecDesc;
	}
	public void setLecDesc(String lecDesc) {
		this.lecDesc = lecDesc;
	}
	public String getClassViewCd() {
		return classViewCd;
	}
	public void setClassViewCd(String classViewCd) {
		this.classViewCd = classViewCd;
	}
	public String getClassViewNm() {
		return classViewNm;
	}
	public void setClassViewNm(String classViewNm) {
		this.classViewNm = classViewNm;
	}
	public String getViewYn() {
		return viewYn;
	}
	public void setViewYn(String viewYn) {
		this.viewYn = viewYn;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
}
