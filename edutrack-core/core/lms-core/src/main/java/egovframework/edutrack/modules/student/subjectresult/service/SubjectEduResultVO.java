package egovframework.edutrack.modules.student.subjectresult.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class SubjectEduResultVO extends DefaultVO {

	private static final long serialVersionUID = -8179875877817721202L;
	
	private String	stdNo;
	private Integer	eduNo;
	private String	userNm;
	private String	userId;
	private String	userNo;
	
	private String  deptCd;
	private String	deptCdNm;
	private String	posngCdNm;
	private String	crsCreCd;
	private String	sbjCd;
	private double	prgrScore;
	private double	examScore;
	private double	asmtScore;
	private double	forumScore;
	private double	etc1Score;
	private double	etc2Score;
	private double	etc3Score;
	private double	plusScore;
	private double	totScore;
	private double	avgScore;
	
	private String	crsCreNm;
	private String	creTerm; 
	
	
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public Integer getEduNo() {
		return eduNo;
	}
	public void setEduNo(Integer eduNo) {
		this.eduNo = eduNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeptCdNm() {
		return deptCdNm;
	}
	public void setDeptCdNm(String deptCdNm) {
		this.deptCdNm = deptCdNm;
	}
	public String getPosngCdNm() {
		return posngCdNm;
	}
	public void setPosngCdNm(String posngCdNm) {
		this.posngCdNm = posngCdNm;
	}
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
	public double getPrgrScore() {
		return prgrScore;
	}
	public void setPrgrScore(double prgrScore) {
		this.prgrScore = prgrScore;
	}
	public double getExamScore() {
		return examScore;
	}
	public void setExamScore(double examScore) {
		this.examScore = examScore;
	}
	public double getAsmtScore() {
		return asmtScore;
	}
	public void setAsmtScore(double asmtScore) {
		this.asmtScore = asmtScore;
	}
	public double getForumScore() {
		return forumScore;
	}
	public void setForumScore(double forumScore) {
		this.forumScore = forumScore;
	}
	public double getEtc1Score() {
		return etc1Score;
	}
	public void setEtc1Score(double etc1Score) {
		this.etc1Score = etc1Score;
	}
	public double getEtc2Score() {
		return etc2Score;
	}
	public void setEtc2Score(double etc2Score) {
		this.etc2Score = etc2Score;
	}
	public double getEtc3Score() {
		return etc3Score;
	}
	public void setEtc3Score(double etc3Score) {
		this.etc3Score = etc3Score;
	}
	public double getPlusScore() {
		return plusScore;
	}
	public void setPlusScore(double plusScore) {
		this.plusScore = plusScore;
	}
	public double getTotScore() {
		return totScore;
	}
	public void setTotScore(double totScore) {
		this.totScore = totScore;
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
	public String getCreTerm() {
		return creTerm;
	}
	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
}
