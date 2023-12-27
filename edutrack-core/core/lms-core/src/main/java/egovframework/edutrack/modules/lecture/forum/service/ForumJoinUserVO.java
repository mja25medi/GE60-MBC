package egovframework.edutrack.modules.lecture.forum.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class ForumJoinUserVO  extends DefaultVO {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 6100488861999662333L;

	private String  crsCreCd   ;//VARCHAR2(10 BYTE)
	private Integer forumSn    ;// NUMBER(9)
	private String  stdNo      ;// NVARCHAR2(12)
	private double score ;// NUMBER(3),
	private String 	userNm;
	private String	userId;
	private Integer atclCnt;
	private Integer cmntCnt;
	private Integer declsNo;

	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getForumSn() {
		return forumSn;
	}
	public void setForumSn(Integer forumSn) {
		this.forumSn = forumSn;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
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
	public Integer getAtclCnt() {
		return atclCnt;
	}
	public void setAtclCnt(Integer atclCnt) {
		this.atclCnt = atclCnt;
	}
	public Integer getCmntCnt() {
		return cmntCnt;
	}
	public void setCmntCnt(Integer cmntCnt) {
		this.cmntCnt = cmntCnt;
	}
	public Integer getDeclsNo() {
		return declsNo;
	}
	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

}