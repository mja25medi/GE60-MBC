package egovframework.edutrack.modules.lecture.project.result.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class ProjectResultVO extends DefaultVO{

	private static final long serialVersionUID = 6597643862343827698L;

	//프로젝트 팀
	private String crsCreCd;
	private int prjtSn;
	private int prjtTeamSn;
	private String prjtTeamNm;
	private float teamScore = 0;
	
	//ID 이름
	private String teamLeader;
	private String leaderId;
	private String mbrCnt;
	
	//프로젝트 팀 회원
	private int prjtMbrSn;
	private String mbrRole;
	private float mbrScore = 0;
	private String stdNo;
	
	//프로젝트 과제
	private int	asmtSn;
	private String asmtTitle;
	private String asmtStartDttm;
	private String asmtEndDttm;
	private String asmtCts;
	
	public int getAsmtSn() {
		return asmtSn;
	}
	public void setAsmtSn(int asmtSn) {
		this.asmtSn = asmtSn;
	}
	public String getAsmtTitle() {
		return asmtTitle;
	}
	public void setAsmtTitle(String asmtTitle) {
		this.asmtTitle = asmtTitle;
	}
	public String getAsmtStartDttm() {
		return asmtStartDttm;
	}
	public void setAsmtStartDttm(String asmtStartDttm) {
		this.asmtStartDttm = asmtStartDttm;
	}
	public String getAsmtEndDttm() {
		return asmtEndDttm;
	}
	public void setAsmtEndDttm(String asmtEndDttm) {
		this.asmtEndDttm = asmtEndDttm;
	}
	public String getAsmtCts() {
		return asmtCts;
	}
	public void setAsmtCts(String asmtCts) {
		this.asmtCts = asmtCts;
	}
	public String getMbrRole() {
		return mbrRole;
	}
	public void setMbrRole(String mbrRole) {
		this.mbrRole = mbrRole;
	}
	public float getMbrScore() {
		return mbrScore;
	}
	public void setMbrScore(float mbrScore) {
		this.mbrScore = mbrScore;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public int getPrjtMbrSn() {
		return prjtMbrSn;
	}
	public void setPrjtMbrSn(int prjtMbrSn) {
		this.prjtMbrSn = prjtMbrSn;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getMbrCnt() {
		return mbrCnt;
	}
	public void setMbrCnt(String mbrCnt) {
		this.mbrCnt = mbrCnt;
	}
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
	public int getPrjtTeamSn() {
		return prjtTeamSn;
	}
	public void setPrjtTeamSn(int prjtTeamSn) {
		this.prjtTeamSn = prjtTeamSn;
	}
	public String getPrjtTeamNm() {
		return prjtTeamNm;
	}
	public void setPrjtTeamNm(String prjtTeamNm) {
		this.prjtTeamNm = prjtTeamNm;
	}
	public float getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(float teamScore) {
		this.teamScore = teamScore;
	}
	

	
	
}
