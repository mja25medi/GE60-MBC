package egovframework.edutrack.modules.lecture.main.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class MainLectureVO extends DefaultVO {

	private static final long	serialVersionUID	= -3179813926635524608L;
	private String  crsCreCd;
	private String  crsCreNm;
	private String  crsYear;
	private String  crsTerm;
	private String  stdNo;
	private String  userNo;
	private String  stdCnt;
	private String  classUserType;

	private String	enrlDuration;
	private String	enrlStartDttm;
	private String	enrlEndDttm;
	private String  termEndDttm;
	private String	enrlTime;
	private String	enrlSts;

	private String	examDuration;
	private String	semiExamDuration;
	private String	asmtDuration;
	private String	reshDuration;
	private String	forumDuration;

	private Integer examCnt = 0;
	private Integer semiExamCnt = 0;
	private Integer asmtCnt = 0;
	private Integer forumCnt = 0;
	private Integer reshCnt = 0;

	private Integer examAnsrCnt = 0;
	private Integer semiExamAnsrCnt = 0;
	private Integer asmtSendCnt = 0;
	private Integer forumJoinCnt = 0;
	private Integer reshJoinCnt = 0;
	
	private Integer examRateCnt = 0;
	private Integer semiExamRateCnt = 0;
	private Integer asmtRateCnt = 0;

	private String prgrRatio;
	private String examScore;
	private String semiExamScore;
	private String asmtScore;
	private String forumScore;

	private Integer termDayCnt;
	private Integer nowDayCnt;
	
	private Integer bbsCnt = 0;
	private Integer pdsCnt = 0;
	private Integer bbsRateCnt = 0;
	
	//수강생 교육기간 여부 (현재날짜 기준으로 교육기간 여부)
	private String stdEnrlYn;
	
	public void hideScore() {
		this.examScore = "";
		this.semiExamScore = "";
		this.asmtScore = "";
	}

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

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getClassUserType() {
		return classUserType;
	}

	public void setClassUserType(String classUserType) {
		this.classUserType = classUserType;
	}

	public String getEnrlDuration() {
		return enrlDuration;
	}

	public void setEnrlDuration(String enrlDuration) {
		this.enrlDuration = enrlDuration;
	}

	public String getEnrlTime() {
		return enrlTime;
	}

	public void setEnrlTime(String enrlTime) {
		this.enrlTime = enrlTime;
	}

	public String getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(String examDuration) {
		this.examDuration = examDuration;
	}

	public String getAsmtDuration() {
		return asmtDuration;
	}

	public void setAsmtDuration(String asmtDuration) {
		this.asmtDuration = asmtDuration;
	}

	public String getReshDuration() {
		return reshDuration;
	}

	public void setReshDuration(String reshDuration) {
		this.reshDuration = reshDuration;
	}

	public String getForumDuration() {
		return forumDuration;
	}

	public void setForumDuration(String forumDuration) {
		this.forumDuration = forumDuration;
	}

	public Integer getExamCnt() {
		return examCnt;
	}

	public void setExamCnt(Integer examCnt) {
		this.examCnt = examCnt;
	}

	public Integer getAsmtCnt() {
		return asmtCnt;
	}

	public void setAsmtCnt(Integer asmtCnt) {
		this.asmtCnt = asmtCnt;
	}

	public Integer getForumCnt() {
		return forumCnt;
	}

	public void setForumCnt(Integer forumCnt) {
		this.forumCnt = forumCnt;
	}

	public Integer getReshCnt() {
		return reshCnt;
	}

	public void setReshCnt(Integer reshCnt) {
		this.reshCnt = reshCnt;
	}

	public Integer getExamAnsrCnt() {
		return examAnsrCnt;
	}

	public void setExamAnsrCnt(Integer examAnsrCnt) {
		this.examAnsrCnt = examAnsrCnt;
	}

	public Integer getAsmtSendCnt() {
		return asmtSendCnt;
	}

	public void setAsmtSendCnt(Integer asmtSendCnt) {
		this.asmtSendCnt = asmtSendCnt;
	}

	public Integer getForumJoinCnt() {
		return forumJoinCnt;
	}

	public void setForumJoinCnt(Integer forumJoinCnt) {
		this.forumJoinCnt = forumJoinCnt;
	}

	public Integer getReshJoinCnt() {
		return reshJoinCnt;
	}

	public void setReshJoinCnt(Integer reshJoinCnt) {
		this.reshJoinCnt = reshJoinCnt;
	}

	public String getPrgrRatio() {
		return prgrRatio;
	}

	public void setPrgrRatio(String prgrRatio) {
		this.prgrRatio = prgrRatio;
	}

	public String getExamScore() {
		return examScore;
	}

	public void setExamScore(String examScore) {
		this.examScore = examScore;
	}

	public String getAsmtScore() {
		return asmtScore;
	}

	public void setAsmtScore(String asmtScore) {
		this.asmtScore = asmtScore;
	}

	public String getForumScore() {
		return forumScore;
	}

	public void setForumScore(String forumScore) {
		this.forumScore = forumScore;
	}

	public String getStdEnrlYn() {
		return stdEnrlYn;
	}

	public void setStdEnrlYn(String stdEnrlYn) {
		this.stdEnrlYn = stdEnrlYn;
	}

	public String getCrsCreNm() {
		return crsCreNm;
	}

	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
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

	public String getEnrlSts() {
		return enrlSts;
	}

	public void setEnrlSts(String enrlSts) {
		this.enrlSts = enrlSts;
	}

	public Integer getSemiExamCnt() {
		return semiExamCnt;
	}

	public void setSemiExamCnt(Integer semiExamCnt) {
		this.semiExamCnt = semiExamCnt;
	}

	public Integer getSemiExamAnsrCnt() {
		return semiExamAnsrCnt;
	}

	public void setSemiExamAnsrCnt(Integer semiExamAnsrCnt) {
		this.semiExamAnsrCnt = semiExamAnsrCnt;
	}

	public String getSemiExamScore() {
		return semiExamScore;
	}

	public void setSemiExamScore(String semiExamScore) {
		this.semiExamScore = semiExamScore;
	}

	public String getEnrlStartDttm() {
		return enrlStartDttm;
	}

	public void setEnrlStartDttm(String enrlStartDttm) {
		this.enrlStartDttm = enrlStartDttm;
	}

	public String getEnrlEndDttm() {
		return enrlEndDttm;
	}

	public void setEnrlEndDttm(String enrlEndDttm) {
		this.enrlEndDttm = enrlEndDttm;
	}

	public String getTermEndDttm() {
		return termEndDttm;
	}

	public void setTermEndDttm(String termEndDttm) {
		this.termEndDttm = termEndDttm;
	}

	public String getSemiExamDuration() {
		return semiExamDuration;
	}

	public void setSemiExamDuration(String semiExamDuration) {
		this.semiExamDuration = semiExamDuration;
	}

	public String getStdCnt() {
		return stdCnt;
	}

	public void setStdCnt(String stdCnt) {
		this.stdCnt = stdCnt;
	}

	public Integer getExamRateCnt() {
		return examRateCnt;
	}

	public void setExamRateCnt(Integer examRateCnt) {
		this.examRateCnt = examRateCnt;
	}

	public Integer getSemiExamRateCnt() {
		return semiExamRateCnt;
	}

	public void setSemiExamRateCnt(Integer semiExamRateCnt) {
		this.semiExamRateCnt = semiExamRateCnt;
	}

	public Integer getAsmtRateCnt() {
		return asmtRateCnt;
	}

	public void setAsmtRateCnt(Integer asmtRateCnt) {
		this.asmtRateCnt = asmtRateCnt;
	}

	public Integer getTermDayCnt() {
		return termDayCnt;
	}

	public void setTermDayCnt(Integer termDayCnt) {
		this.termDayCnt = termDayCnt;
	}

	public Integer getNowDayCnt() {
		return nowDayCnt;
	}

	public void setNowDayCnt(Integer nowDayCnt) {
		this.nowDayCnt = nowDayCnt;
	}

	public Integer getBbsCnt() {
		return bbsCnt;
	}

	public void setBbsCnt(Integer bbsCnt) {
		this.bbsCnt = bbsCnt;
	}

	public Integer getPdsCnt() {
		return pdsCnt;
	}

	public void setPdsCnt(Integer pdsCnt) {
		this.pdsCnt = pdsCnt;
	}

	public Integer getBbsRateCnt() {
		return bbsRateCnt;
	}

	public void setBbsRateCnt(Integer bbsRateCnt) {
		this.bbsRateCnt = bbsRateCnt;
	}
	
}