package egovframework.edutrack.modules.student.student.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class StudentResultVO extends DefaultVO{

	private static final long serialVersionUID = 7786544368379344466L;
	private String  crsCreCd;
	private String  userId;
	private String  userNo;
	private String  userNm;
	private String  stdNo;
	private Integer declsNo;
	private Integer eduNo;
	private String  orgCd;
	private String  compNm;
	private String  enrlSts;
	private String  enrlStsNm;
	private Integer prgrRatio;
	private Integer examCnt;
	private Integer examStareCnt;
	private Integer examScore;
	private Integer asmtCnt;
	private Integer asmtStareCnt;
	private Integer asmtScore;
	private Integer forumCnt;
	private Integer forumJoinCnt;
	private Integer forumJoinScore;
	private Integer totalScore;
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public Integer getDeclsNo() {
		return declsNo;
	}
	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}
	public Integer getEduNo() {
		return eduNo;
	}
	public void setEduNo(Integer eduNo) {
		this.eduNo = eduNo;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCompNm() {
		return compNm;
	}
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	public String getEnrlSts() {
		return enrlSts;
	}
	public void setEnrlSts(String enrlSts) {
		this.enrlSts = enrlSts;
	}
	public String getEnrlStsNm() {
		return enrlStsNm;
	}
	public void setEnrlStsNm(String enrlStsNm) {
		this.enrlStsNm = enrlStsNm;
	}
	public Integer getPrgrRatio() {
		return prgrRatio;
	}
	public void setPrgrRatio(Integer prgrRatio) {
		this.prgrRatio = prgrRatio;
	}
	public Integer getExamCnt() {
		return examCnt;
	}
	public void setExamCnt(Integer examCnt) {
		this.examCnt = examCnt;
	}
	public Integer getExamStareCnt() {
		return examStareCnt;
	}
	public void setExamStareCnt(Integer examStareCnt) {
		this.examStareCnt = examStareCnt;
	}
	public Integer getExamScore() {
		return examScore;
	}
	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
	}
	public Integer getAsmtCnt() {
		return asmtCnt;
	}
	public void setAsmtCnt(Integer asmtCnt) {
		this.asmtCnt = asmtCnt;
	}
	public Integer getAsmtStareCnt() {
		return asmtStareCnt;
	}
	public void setAsmtStareCnt(Integer asmtStareCnt) {
		this.asmtStareCnt = asmtStareCnt;
	}
	public Integer getAsmtScore() {
		return asmtScore;
	}
	public void setAsmtScore(Integer asmtScore) {
		this.asmtScore = asmtScore;
	}
	public Integer getForumCnt() {
		return forumCnt;
	}
	public void setForumCnt(Integer forumCnt) {
		this.forumCnt = forumCnt;
	}
	public Integer getForumJoinCnt() {
		return forumJoinCnt;
	}
	public void setForumJoinCnt(Integer forumJoinCnt) {
		this.forumJoinCnt = forumJoinCnt;
	}
	public Integer getForumJoinScore() {
		return forumJoinScore;
	}
	public void setForumJoinScore(Integer forumJoinScore) {
		this.forumJoinScore = forumJoinScore;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
}
