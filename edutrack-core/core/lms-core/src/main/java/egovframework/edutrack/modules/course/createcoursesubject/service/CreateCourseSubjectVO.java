package egovframework.edutrack.modules.course.createcoursesubject.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class CreateCourseSubjectVO extends DefaultVO{
	
	private static final long serialVersionUID = 8329349811544065244L;

	private String 	crsCreCd;
	private String 	sbjCd;
	private String	sbjNm;
	private String  sbjType;
	private int		sbjOdr;
	private int		prgrRatio;
	private int		examRatio;
	private int		asmtRatio;
	private int		forumRatio;
	private String	etc1RatioNm;
	private int		etc1Ratio;
	private String	etc2RatioNm;
	private int		etc2Ratio;
	private String	etc3RatioNm;
	private int		etc3Ratio;
	private String	plusScoreUseYn = "N";
	private String	studyMthd;
	private String	studyMthdNm;
	
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
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	public String getSbjType() {
		return sbjType;
	}
	public void setSbjType(String sbjType) {
		this.sbjType = sbjType;
	}
	public int getSbjOdr() {
		return sbjOdr;
	}
	public void setSbjOdr(int sbjOdr) {
		this.sbjOdr = sbjOdr;
	}
	public int getPrgrRatio() {
		return prgrRatio;
	}
	public void setPrgrRatio(int prgrRatio) {
		this.prgrRatio = prgrRatio;
	}
	public int getExamRatio() {
		return examRatio;
	}
	public void setExamRatio(int examRatio) {
		this.examRatio = examRatio;
	}
	public int getAsmtRatio() {
		return asmtRatio;
	}
	public void setAsmtRatio(int asmtRatio) {
		this.asmtRatio = asmtRatio;
	}
	public int getForumRatio() {
		return forumRatio;
	}
	public void setForumRatio(int forumRatio) {
		this.forumRatio = forumRatio;
	}
	public String getEtc1RatioNm() {
		return etc1RatioNm;
	}
	public void setEtc1RatioNm(String etc1RatioNm) {
		this.etc1RatioNm = etc1RatioNm;
	}
	public int getEtc1Ratio() {
		return etc1Ratio;
	}
	public void setEtc1Ratio(int etc1Ratio) {
		this.etc1Ratio = etc1Ratio;
	}
	public String getEtc2RatioNm() {
		return etc2RatioNm;
	}
	public void setEtc2RatioNm(String etc2RatioNm) {
		this.etc2RatioNm = etc2RatioNm;
	}
	public int getEtc2Ratio() {
		return etc2Ratio;
	}
	public void setEtc2Ratio(int etc2Ratio) {
		this.etc2Ratio = etc2Ratio;
	}
	public String getEtc3RatioNm() {
		return etc3RatioNm;
	}
	public void setEtc3RatioNm(String etc3RatioNm) {
		this.etc3RatioNm = etc3RatioNm;
	}
	public int getEtc3Ratio() {
		return etc3Ratio;
	}
	public void setEtc3Ratio(int etc3Ratio) {
		this.etc3Ratio = etc3Ratio;
	}
	public String getPlusScoreUseYn() {
		return plusScoreUseYn;
	}
	public void setPlusScoreUseYn(String plusScoreUseYn) {
		this.plusScoreUseYn = plusScoreUseYn;
	}
	public String getStudyMthd() {
		return studyMthd;
	}
	public void setStudyMthd(String studyMthd) {
		this.studyMthd = studyMthd;
	}
	public String getStudyMthdNm() {
		return studyMthdNm;
	}
	public void setStudyMthdNm(String studyMthdNm) {
		this.studyMthdNm = studyMthdNm;
	}
}
