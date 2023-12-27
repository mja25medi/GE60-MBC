/**
 *
 */
package egovframework.edutrack.modules.course.subject.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 오프라인 과목 VO
 */
public class OfflineSubjectVO extends DefaultVO  {

	private static final long	serialVersionUID	= 8253876018152412203L;
	private String  orgCd;
	private String	sbjCd;
	private String	sbjNm;
	private String	sbjDesc;
	private String 	useYn;
	private String	useYnNm;
	private String  sbjTypeCd;
	private String  sbjTypeNm;
	private int		examRatio;
	private int		asmtRatio;
	private	String	etc1RatioNm = "기타 1";
	private int		etc1Ratio;
	private	String	etc2RatioNm = "기타 2";
	private int		etc2Ratio;
	private	String	etc3RatioNm = "기타 3";
	private int		etc3Ratio;
	private String	plusScoreUseYn = "N";

	private String	sbjCtgrCd;
	private String	sbjCtgrNm;
	private String[]	sbjCtgrArr;

	private String	autoMakeYn = "Y";
	private String 	crsCreCd;
	private String  createMode;


	private int crsSubCnt;
	private int creCrsSubCnt;
	private int openCrsSubCnt;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
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
	public String getSbjDesc() {
		return sbjDesc;
	}
	public void setSbjDesc(String sbjDesc) {
		this.sbjDesc = sbjDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getSbjTypeCd() {
		return sbjTypeCd;
	}
	public void setSbjTypeCd(String sbjTypeCd) {
		this.sbjTypeCd = sbjTypeCd;
	}
	public String getSbjTypeNm() {
		return sbjTypeNm;
	}
	public void setSbjTypeNm(String sbjTypeNm) {
		this.sbjTypeNm = sbjTypeNm;
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
	public String getAutoMakeYn() {
		return autoMakeYn;
	}
	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getCreateMode() {
		return createMode;
	}
	public void setCreateMode(String createMode) {
		this.createMode = createMode;
	}
	public int getCrsSubCnt() {
		return crsSubCnt;
	}
	public void setCrsSubCnt(int crsSubCnt) {
		this.crsSubCnt = crsSubCnt;
	}
	public int getCreCrsSubCnt() {
		return creCrsSubCnt;
	}
	public void setCreCrsSubCnt(int creCrsSubCnt) {
		this.creCrsSubCnt = creCrsSubCnt;
	}
	public int getOpenCrsSubCnt() {
		return openCrsSubCnt;
	}
	public void setOpenCrsSubCnt(int openCrsSubCnt) {
		this.openCrsSubCnt = openCrsSubCnt;
	}
	public String[] getSbjCtgrArr() {
		return sbjCtgrArr;
	}
	public void setSbjCtgrArr(String[] sbjCtgrArr) {
		this.sbjCtgrArr = sbjCtgrArr;
	}
}