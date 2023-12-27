package egovframework.edutrack.modules.lecture.forum.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamStareVO;

public class ForumVO  extends DefaultVO {

	private static final long	serialVersionUID	= -5283921335944697624L;

    private String 	crsCreCd;
    private String 	crsCreNm;
    private Integer forumSn = 0;
    private String 	forumTitle;
    private String 	forumCts;
    private String 	forumRegYn = "Y";
    private String 	forumRegYnNm;
    private String  forumStartDttm;
	private String	forumStartHour;
	private String	forumStartMin;
    private String 	forumEndDttm;
	private String	forumEndHour;
	private String	forumEndMin;
    private String 	proceedYn;
    private Integer atclCnt;
    private String sbjCd;
    private String sbjNm;
    private String periodAfterWriteYn;
    private String stdNo;
    private Integer score;
	private String	rsltYn;

    private double avgScore;
	private double myScore;
	
	private ForumJoinUserVO forumJoinUserVO;
	public ForumVO() {
		setForumJoinUserVO(new ForumJoinUserVO());
	}

	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
	public Integer getForumSn() {
		return forumSn;
	}
	public void setForumSn(Integer forumSn) {
		this.forumSn = forumSn;
	}
	public String getForumTitle() {
		return forumTitle;
	}
	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	public String getForumCts() {
		return forumCts;
	}
	public void setForumCts(String forumCts) {
		this.forumCts = forumCts;
	}
	public String getForumRegYn() {
		return forumRegYn;
	}
	public void setForumRegYn(String forumRegYn) {
		this.forumRegYn = forumRegYn;
	}
	public String getForumRegYnNm() {
		return forumRegYnNm;
	}
	public void setForumRegYnNm(String forumRegYnNm) {
		this.forumRegYnNm = forumRegYnNm;
	}
	public String getForumStartDttm() {
		return forumStartDttm;
	}
	public void setForumStartDttm(String forumStartDttm) {
		this.forumStartDttm = forumStartDttm;
	}
	public String getForumEndDttm() {
		return forumEndDttm;
	}
	public void setForumEndDttm(String forumEndDttm) {
		this.forumEndDttm = forumEndDttm;
	}
	public String getProceedYn() {
		return proceedYn;
	}
	public void setProceedYn(String proceedYn) {
		this.proceedYn = proceedYn;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	public Integer getAtclCnt() {
		return atclCnt;
	}
	public void setAtclCnt(Integer atclCnt) {
		this.atclCnt = atclCnt;
	}
	public String getPeriodAfterWriteYn() {
		return periodAfterWriteYn;
	}
	public void setPeriodAfterWriteYn(String periodAfterWriteYn) {
		this.periodAfterWriteYn = periodAfterWriteYn;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getForumStartHour() {
		return forumStartHour;
	}
	public void setForumStartHour(String forumStartHour) {
		this.forumStartHour = forumStartHour;
	}
	public String getForumStartMin() {
		return forumStartMin;
	}
	public void setForumStartMin(String forumStartMin) {
		this.forumStartMin = forumStartMin;
	}
	public String getForumEndHour() {
		return forumEndHour;
	}
	public void setForumEndHour(String forumEndHour) {
		this.forumEndHour = forumEndHour;
	}
	public String getForumEndMin() {
		return forumEndMin;
	}
	public void setForumEndMin(String forumEndMin) {
		this.forumEndMin = forumEndMin;
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	public double getMyScore() {
		return myScore;
	}
	public void setMyScore(double myScore) {
		this.myScore = myScore;
	}
	public String getRsltYn() {
		return rsltYn;
	}
	public void setRsltYn(String rsltYn) {
		this.rsltYn = rsltYn;
	}

	public ForumJoinUserVO getForumJoinUserVO() {
		return forumJoinUserVO;
	}

	public void setForumJoinUserVO(ForumJoinUserVO forumJoinUserVO) {
		this.forumJoinUserVO = forumJoinUserVO;
	}
}
