package egovframework.edutrack.modules.lecture.project.member.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class PrjMemberVO extends DefaultVO {
	
	private static final long	serialVersionUID	= -155074520767701661L;
	
	private String crsCreCd;
	private int prjtSn;
	private String prjtTeamSn;
	private String prjtMbrSn;
	private String mbrRole;
	private String mbrScore;
	private String modDttm;
	private String modNo;
	private String regDttm;
	private String regNo;
	private String stdNo;
	private String teamLeaderDiv;
	
	//팀원 목록
	private String userNo;
	private String stdNm;
	private String stdId;
	
	private int learnerCnt;
	private int selPrjtSn;
	
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getMbrRole() {
		return mbrRole;
	}
	public void setMbrRole(String mbrRole) {
		this.mbrRole = mbrRole;
	}
	public String getMbrScore() {
		return mbrScore;
	}
	public void setMbrScore(String mbrScore) {
		this.mbrScore = mbrScore;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getPrjtMbrSn() {
		return prjtMbrSn;
	}
	public void setPrjtMbrSn(String prjtMbrSn) {
		this.prjtMbrSn = prjtMbrSn;
	}
	public int getPrjtSn() {
		return prjtSn;
	}
	public void setPrjtSn(int prjtSn) {
		this.prjtSn = prjtSn;
	}
	public String getPrjtTeamSn() {
		return prjtTeamSn;
	}
	public void setPrjtTeamSn(String prjtTeamSn) {
		this.prjtTeamSn = prjtTeamSn;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getTeamLeaderDiv() {
		return teamLeaderDiv;
	}
	public void setTeamLeaderDiv(String teamLeaderDiv) {
		this.teamLeaderDiv = teamLeaderDiv;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getStdNm() {
		return stdNm;
	}
	public void setStdNm(String stdNm) {
		this.stdNm = stdNm;
	}
	public String getStdId() {
		return stdId;
	}
	public void setStdId(String stdId) {
		this.stdId = stdId;
	}
	public int getLearnerCnt() {
		return learnerCnt;
	}
	public void setLearnerCnt(int learnerCnt) {
		this.learnerCnt = learnerCnt;
	}
	public int getSelPrjtSn() {
		return selPrjtSn;
	}
	public void setSelPrjtSn(int selPrjtSn) {
		this.selPrjtSn = selPrjtSn;
	}
	
	
	
	
}   
    