package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import egovframework.edutrack.comm.service.DefaultVO;

public class HrdApiScoreVO extends DefaultVO{ 
	private String agentPk         ;
	private int num;
	private double seq             ;
	private String userAgentPk     ;
	private String courseAgentPk   ;
	private String classAgentPk    ;
	private String contentsAgentPk ;
	private String evalType        ;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date submitDate      ;
	private double score           ;
	private String accessIp        ;
	private String submitDueDt     ;
	private String changeState     ;
	private String isCopiedAnswer  ;
	private String evalCd          ;
	private int chasi           ;
	private int scoreTime       ;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date submitDateEnd   ;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regDate         ;
	
	private String startEndFlag;
	private String crsCd;
	private String syncStatus;
	private String syncResultMsg;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	private String crsCreCd;
	private String evalTypeCd;
	private int oldMaxEvalType;
	private String lmsFlag;
	private String stdNo;
	
	private String termEndDttm;
	
	private int creEvalCnt;
	private int evalRatio;
	
	public void convertScore() throws Exception {
		if(creEvalCnt == 0) throw new Exception("기준 갯수 0");
		double rawScore = (score * evalRatio / 100) / creEvalCnt;
		score = Math.round(rawScore*100) / 100.0;
	}
	
	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getLmsFlag() {
		return lmsFlag;
	}

	public void setLmsFlag(String lmsFlag) {
		this.lmsFlag = lmsFlag;
	}

	public int getOldMaxEvalType() {
		return oldMaxEvalType;
	}

	public void setOldMaxEvalType(int oldMaxEvalType) {
		this.oldMaxEvalType = oldMaxEvalType;
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

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public String getEvalTypeCd() {
		return evalTypeCd;
	}

	public void setEvalTypeCd(String evalTypeCd) {
		this.evalTypeCd = evalTypeCd;
	}

	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getStartEndFlag() {
		return startEndFlag;
	}
	public void setStartEndFlag(String startEndFlag) {
		this.startEndFlag = startEndFlag;
	}
	public String getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(String agentPk) {
		this.agentPk = agentPk;
	}
	public double getSeq() {
		return seq;
	}
	public void setSeq(double seq) {
		this.seq = seq;
	}
	public String getUserAgentPk() {
		return userAgentPk;
	}
	public void setUserAgentPk(String userAgentPk) {
		this.userAgentPk = userAgentPk;
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
	public String getContentsAgentPk() {
		return contentsAgentPk;
	}
	public void setContentsAgentPk(String contentsAgentPk) {
		this.contentsAgentPk = contentsAgentPk;
	}
	public String getEvalType() {
		return evalType;
	}
	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public String getSubmitDueDt() {
		return submitDueDt;
	}
	public void setSubmitDueDt(String submitDueDt) {
		this.submitDueDt = submitDueDt;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public String getIsCopiedAnswer() {
		return isCopiedAnswer;
	}
	public void setIsCopiedAnswer(String isCopiedAnswer) {
		this.isCopiedAnswer = isCopiedAnswer;
	}
	public String getEvalCd() {
		return evalCd;
	}
	public void setEvalCd(String evalCd) {
		this.evalCd = evalCd;
	}
	public int getChasi() {
		return chasi;
	}
	public void setChasi(int chasi) {
		this.chasi = chasi;
	}
	public int getScoreTime() {
		return scoreTime;
	}
	public void setScoreTime(int scoreTime) {
		this.scoreTime = scoreTime;
	}
	public Date getSubmitDateEnd() {
		return submitDateEnd;
	}
	public void setSubmitDateEnd(Date submitDateEnd) {
		this.submitDateEnd = submitDateEnd;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getCreEvalCnt() {
		return creEvalCnt;
	}

	public void setCreEvalCnt(int creEvalCnt) {
		this.creEvalCnt = creEvalCnt;
	}

	public int getEvalRatio() {
		return evalRatio;
	}

	public void setEvalRatio(int evalRatio) {
		this.evalRatio = evalRatio;
	}

	public String getTermEndDttm() {
		return termEndDttm;
	}

	public void setTermEndDttm(String termEndDttm) {
		this.termEndDttm = termEndDttm;
	}
	
}
