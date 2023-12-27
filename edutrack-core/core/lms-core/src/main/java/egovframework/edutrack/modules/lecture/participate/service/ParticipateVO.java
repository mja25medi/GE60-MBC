package egovframework.edutrack.modules.lecture.participate.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class ParticipateVO extends DefaultVO{

	private static final long serialVersionUID = 4021611244817086281L;
	private String crsCreCd;
	private Integer declsNo;
	private int qnaAtclCnt;
	private int qnaCmntCnt;
	private int pdsAtclCnt;
	private int pdsCmntCnt;
	private int forumAtclCnt;
	private int forumCmntCnt;
	private int totalAtclCnt;
	private int totalCmntCnt;
	private int classConnCnt;

	private String userNm;
	private String userId;
	private String userNo;

 	//학습참여점수 컬럼
	private String stdNo;
	private float joinScore = 0;

	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getDeclsNo() {
		return declsNo;
	}
	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}
	public int getQnaAtclCnt() {
		return qnaAtclCnt;
	}
	public void setQnaAtclCnt(int qnaAtclCnt) {
		this.qnaAtclCnt = qnaAtclCnt;
	}
	public int getQnaCmntCnt() {
		return qnaCmntCnt;
	}
	public void setQnaCmntCnt(int qnaCmntCnt) {
		this.qnaCmntCnt = qnaCmntCnt;
	}
	public int getPdsAtclCnt() {
		return pdsAtclCnt;
	}
	public void setPdsAtclCnt(int pdsAtclCnt) {
		this.pdsAtclCnt = pdsAtclCnt;
	}
	public int getPdsCmntCnt() {
		return pdsCmntCnt;
	}
	public void setPdsCmntCnt(int pdsCmntCnt) {
		this.pdsCmntCnt = pdsCmntCnt;
	}
	public int getForumAtclCnt() {
		return forumAtclCnt;
	}
	public void setForumAtclCnt(int forumAtclCnt) {
		this.forumAtclCnt = forumAtclCnt;
	}
	public int getForumCmntCnt() {
		return forumCmntCnt;
	}
	public void setForumCmntCnt(int forumCmntCnt) {
		this.forumCmntCnt = forumCmntCnt;
	}
	public int getTotalAtclCnt() {
		return totalAtclCnt;
	}
	public void setTotalAtclCnt(int totalAtclCnt) {
		this.totalAtclCnt = totalAtclCnt;
	}
	public int getTotalCmntCnt() {
		return totalCmntCnt;
	}
	public void setTotalCmntCnt(int totalCmntCnt) {
		this.totalCmntCnt = totalCmntCnt;
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
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public float getJoinScore() {
		return joinScore;
	}
	public void setJoinScore(float joinScore) {
		this.joinScore = joinScore;
	}
	public int getClassConnCnt() {
		return classConnCnt;
	}
	public void setClassConnCnt(int classConnCnt) {
		this.classConnCnt = classConnCnt;
	}
}
