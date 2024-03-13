package egovframework.edutrack.modules.course.createcoursesubject.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;

/**
 * 회차 온라인 과목 VO
 */
public class CreateOnlineSubjectVO  extends DefaultVO {

	private static final long	serialVersionUID	= 1784925651524018202L;

	private String  orgCd;
	private String	crsCreCd ;
	private String  crsCd;
	private String  stdNo;
	private String	sbjCd	  ;
	private String	sbjNm	  ;
	private String  sbjCtgrCd;
	private String  sbjCtgrNm;
	private String	studyMthd;
	private String	studyMthdNm;
	private int		sbjOdr;
	private int		contentsCnt;
	private int		prgrRatio;
	private int		examRatio;
	private int		asmtRatio;
	private int		forumRatio;
	private String	etc1RatioNm = "기타 1";
	private int		etc1Ratio;
	private String	etc2RatioNm = "기타 2";
	private int		etc2Ratio;
	private String	etc3RatioNm = "기타 3";
	private int		etc3Ratio;
	private String	plusScoreUseYn = "N";
	private String	sbjType;
	private String	sbjCnt;
	private String	sbjYCnt;
	private String	creOperTypeCd;

	ContentsVO contentsVO;


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

	public CreateOnlineSubjectVO(){
		contentsVO = new ContentsVO();
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
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
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
	public int getSbjOdr() {
		return sbjOdr;
	}
	public void setSbjOdr(int sbjOdr) {
		this.sbjOdr = sbjOdr;
	}
	public int getContentsCnt() {
		return contentsCnt;
	}
	public void setContentsCnt(int contentsCnt) {
		this.contentsCnt = contentsCnt;
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
	public ContentsVO getContentsVO() {
		return contentsVO;
	}
	public void setContentsVO(ContentsVO contentsVO) {
		this.contentsVO = contentsVO;
	}
	public String getSbjType() {
		return sbjType;
	}
	public void setSbjType(String sbjType) {
		this.sbjType = sbjType;
	}

	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getSbjCnt() {
		return sbjCnt;
	}

	public void setSbjCnt(String sbjCnt) {
		this.sbjCnt = sbjCnt;
	}

	public String getSbjYCnt() {
		return sbjYCnt;
	}

	public void setSbjYCnt(String sbjYCnt) {
		this.sbjYCnt = sbjYCnt;
	}

	public String getCreOperTypeCd() {
		return creOperTypeCd;
	}

	public void setCreOperTypeCd(String creOperTypeCd) {
		this.creOperTypeCd = creOperTypeCd;
	}

}