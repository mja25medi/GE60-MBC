package egovframework.edutrack.modules.lecture.project.member.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class PrjTeamVO extends DefaultVO {
	
	private static final long	serialVersionUID	= -155074520767701661L;
	
	private String crsCreCd;
	private int prjtSn;
	private String prjtTeamSn;
	private String prjtTeamNm;
	private String teamScore;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	private String teamLeader;
	private String mbrCnt;
	
	private String leaderId;
	private int teamCnt;
	private String learnerAssign;
	
	private int learnerCnt;
	private int selPrjtSn;
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
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
	public String getPrjtTeamNm() {
		return prjtTeamNm;
	}
	public void setPrjtTeamNm(String prjtTeamNm) {
		this.prjtTeamNm = prjtTeamNm;
	}
	public String getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(String teamScore) {
		this.teamScore = teamScore;
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
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public String getMbrCnt() {
		return mbrCnt;
	}
	public void setMbrCnt(String mbrCnt) {
		this.mbrCnt = mbrCnt;
	}
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public int getTeamCnt() {
		return teamCnt;
	}
	public void setTeamCnt(int teamCnt) {
		this.teamCnt = teamCnt;
	}
	public String getLearnerAssign() {
		return learnerAssign;
	}
	public void setLearnerAssign(String learnerAssign) {
		this.learnerAssign = learnerAssign;
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
    