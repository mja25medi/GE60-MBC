package egovframework.edutrack.modules.etc.hrdapi.service;

import egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType;
import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.student.service.StudentVO;

public class HrdApiStdVO extends DefaultVO {
	
	private static final long serialVersionUID = -2934331403467781088L;
	
	private int num;
	private String crsCd;
	private String agentPk;
	private Integer seq;
	private String stdNo;
	private String userAgentPk;
	private String courseAgentPk;
	private String classAgentPk;
	private Integer passFlag;
	private Integer attendValidFlag;
	private String changeState;
	private String regDate;
	private Integer empInsFlag;
	private Double progressRate;
	private Double totalScore;
	private String crsYear;
	private String crsTerm;
	private String syncStatus;
	private String syncResultMsg;
	
	public HrdApiStdVO() {};
	
	public HrdApiStdVO(HrdStdSendable target, SyncType syncType) {
		super();
		this.defaultSetStdInfo(target);
		this.changeState = syncType.getStringValue();
	}
	
	public void defaultSetStdInfo(HrdStdSendable target) {
		this.agentPk = "edulife";
		this.seq = 0;
		this.userAgentPk = target.callHrdUserAgentPk();
		this.courseAgentPk = target.callHrdCourseAgentPk();
		this.classAgentPk = target.callHrdClassAgentPk();
		this.passFlag = target.callHrdPassFlag();
		this.attendValidFlag = target.callHrdAttendValidFlag();
		this.empInsFlag = target.callHrdEmpInsFlag();
		this.progressRate = 0.0;
		this.totalScore =  0.0;
		this.syncStatus = "W";
	}
	
	public void setSearchInfoFromStudent(StudentVO vo) {
		this.stdNo = vo.getStdNo();
		this.crsCd = vo.getCrsCd();
		this.crsYear = vo.getCrsYear();
		this.crsTerm = vo.getCrsTerm();
	}
	
	public void updateScore(EduResultVO vo) {
		this.progressRate = Double.valueOf(vo.getPrgrRate());
		this.totalScore = vo.getTotScore();
	}
	
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
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
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
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
	public Integer getPassFlag() {
		return passFlag;
	}
	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
	}
	public Integer getAttendValidFlag() {
		return attendValidFlag;
	}
	public void setAttendValidFlag(Integer attendValidFlag) {
		this.attendValidFlag = attendValidFlag;
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
	public Integer getEmpInsFlag() {
		return empInsFlag;
	}
	public void setEmpInsFlag(Integer empInsFlag) {
		this.empInsFlag = empInsFlag;
	}
	public Double getProgressRate() {
		return progressRate;
	}
	public void setProgressRate(Double progressRate) {
		this.progressRate = progressRate;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
