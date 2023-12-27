package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.Date;

import egovframework.edutrack.comm.service.DefaultVO;

public class HrdApiCreVO extends DefaultVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2934331403467781088L;
	
	private int num;
	private String crsCd;
	private String agentPk;
	private Integer seq;
	private String courseAgentPk;
	private String classAgentPk;
	private String crsCreCd;
	private String fullScore;
	private String startDt;
	private String endDt;
	private String changeState;
	private String syncGubunCd;
	private String regDate;
	private String tracseTme;
	private String crsYear;
	private String crsTerm;
	private String syncStatus;
	private String syncResultMsg;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	
	

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCrsCd() {
		return crsCd;
	}
	public String getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(String agentPk) {
		this.agentPk = agentPk;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getCourseAgentPk() {
		return courseAgentPk;
	}
	public void setCourseAgentPk(String courseAgentPk) {
		this.courseAgentPk = courseAgentPk;
	}
	public String getClassAgentPk() {
		return classAgentPk;
	}
	public void setClassAgentPk(String classAgentPk) {
		this.classAgentPk = classAgentPk;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getFullScore() {
		return fullScore;
	}
	public void setFullScore(String fullScore) {
		this.fullScore = fullScore;
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
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getSyncGubunCd() {
		return syncGubunCd;
	}
	public void setSyncGubunCd(String syncGubunCd) {
		this.syncGubunCd = syncGubunCd;
	}
	public String getTracseTme() {
		return tracseTme;
	}

	public void setTracseTme(String tracseTme) {
		this.tracseTme = tracseTme;
	}
	public String getCrsYear() {
		return crsYear;
	}
	public void setCrsYear(String crsYear) {
		this.crsYear = crsYear;
	}
	public String getCrsTerm() {
		return crsTerm;
	}
	public void setCrsTerm(String crsTerm) {
		this.crsTerm = crsTerm;
	}
	public String getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}
	public String getSyncResultMsg() {
		return syncResultMsg;
	}
	public void setSyncResultMsg(String syncResultMsg) {
		this.syncResultMsg = syncResultMsg;
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
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	
	
}
