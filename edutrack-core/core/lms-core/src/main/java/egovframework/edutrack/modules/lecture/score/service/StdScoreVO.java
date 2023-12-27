package egovframework.edutrack.modules.lecture.score.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class StdScoreVO extends DefaultVO {

	private static final long serialVersionUID = 2288954043408719823L;
	private String  crsCreCd;
	private String  stdNo;
	private String  userId;
	private String  userNo;
	private String  userNm;
	private Integer examScore;
	private Integer asmtScore;
	private Integer forumScore;
	private Integer joinScore;
	private Integer etcScore;
	private Integer prgrScore;

	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Integer getExamScore() {
		return examScore;
	}
	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
	}
	public Integer getAsmtScore() {
		return asmtScore;
	}
	public void setAsmtScore(Integer asmtScore) {
		this.asmtScore = asmtScore;
	}
	public Integer getForumScore() {
		return forumScore;
	}
	public void setForumScore(Integer forumScore) {
		this.forumScore = forumScore;
	}
	public Integer getJoinScore() {
		return joinScore;
	}
	public void setJoinScore(Integer joinScore) {
		this.joinScore = joinScore;
	}
	public Integer getEtcScore() {
		return etcScore;
	}
	public void setEtcScore(Integer etcScore) {
		this.etcScore = etcScore;
	}
	public Integer getPrgrScore() {
		return prgrScore;
	}
	public void setPrgrScore(Integer prgrScore) {
		this.prgrScore = prgrScore;
	}
}
