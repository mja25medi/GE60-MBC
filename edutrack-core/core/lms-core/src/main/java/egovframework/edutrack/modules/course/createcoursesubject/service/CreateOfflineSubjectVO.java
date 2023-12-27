package egovframework.edutrack.modules.course.createcoursesubject.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 과정 VO
 */
public class CreateOfflineSubjectVO  extends DefaultVO {

	private static final long	serialVersionUID	= 5613876309993671157L;

	private String  orgCd;
	private String 	crsCreCd;
	private String  crsCd;
	private String 	sbjCd;
	private String	sbjNm;
	private String  sbjCtgrCd;
	private String  sbjCtgrNm;
	private int		sbjOdr;
	private String  eduMthdCd;
	private String  eduMthdNm;
	private int		examRatio;
	private int		asmtRatio;
	private String	etc1RatioNm = "기타 1";
	private int		etc1Ratio;
	private String	etc2RatioNm = "기타 2";
	private int		etc2Ratio;
	private String	etc3RatioNm = "기타 3";
	private int		etc3Ratio;
	private String	plusScoreUseYn = "N";


	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getSbjCtgrCd() {
		return sbjCtgrCd;
	}
	public void setSbjCtgrCd(String sbjCtgrCd) {
		this.sbjCtgrCd = sbjCtgrCd;
	}
	public String getSbjCtgrNm() {
		return sbjCtgrNm;
	}
	public void setSbjCtgrNm(String sbjCtgrNm) {
		this.sbjCtgrNm = sbjCtgrNm;
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
	public int getSbjOdr() {
		return sbjOdr;
	}
	public void setSbjOdr(int sbjOdr) {
		this.sbjOdr = sbjOdr;
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
	public String getEduMthdCd() {
		return eduMthdCd;
	}
	public void setEduMthdCd(String eduMthdCd) {
		this.eduMthdCd = eduMthdCd;
	}
	public String getEduMthdNm() {
		return eduMthdNm;
	}
	public void setEduMthdNm(String eduMthdNm) {
		this.eduMthdNm = eduMthdNm;
	}

}