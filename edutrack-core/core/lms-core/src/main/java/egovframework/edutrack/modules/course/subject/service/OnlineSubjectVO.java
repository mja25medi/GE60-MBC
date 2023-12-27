/**
 *
 */
package egovframework.edutrack.modules.course.subject.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdCourseSendable;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


/**
 * 온라인 과목 VO
 */
public class OnlineSubjectVO extends DefaultVO implements HrdCourseSendable {

	private static final long	serialVersionUID	= 3564698753633260671L;
	private String  orgCd;
	private String	sbjCd;
	private String	sbjNm;
	private int		winWidth = 1024;
	private int		winHeight = 760;
	private String	winMenuUseYn = "N";
	private String	winSkinCd = "DEFAULT";
	private String	sbjDesc;
	private String	useYn = "Y";
	private String	mainYn;
	private String	useYnNm;
	private String  sbjTypeCd;
	private int		prgrRatio;
	private int		attdRatio;
	private int		examRatio;
	private int		semiExamRatio;
	private int		asmtRatio;
	private int		forumRatio;
	private int		etcRatio;
	private String	etc1RatioNm = "기타 1";
	private int		etc1Ratio;
	private String	etc2RatioNm = "기타 2";
	private int		etc2Ratio;
	private String	etc3RatioNm = "기타 3";
	private int		etc3Ratio;
	private String	plusScoreUseYn = "N";

	private String	sbjCtgrCd;
	private String	sbjCtgrNm;
	private String[]	sbjCtgrArr;

	private String  sbjTypeNm;
	private String  parSbjCtgrCd;

	private String	autoMakeYn = "Y";

	private String	crsCreCd;
	private String	crsCd;
	private int		contentsCnt;
	private int 	crsCreCnt;
	private String  createMode;
	private String  studyMthd;

	private int crsSubCnt;
	private int creCrsSubCnt;
	private int openCrsSubCnt;

	private String	refundYn;
	private Integer eduPrice;
	private Integer refundPrice;
	private String	simsaCode;
	private String	tracseId;
	private String	eduGrade;
	private Integer eduNop;
	private String  eduPrps;
	private String  eduTarget;
	private String  eduTm;
	private Integer	eduDaycnt;
	private String  evalDesc;
	private String  tchDesc;
	private Integer	dayStudyLimit;
	
	private Integer thumbFileSn;
	private SysFileVO thumbFile;
	private List<SysFileVO>	attachFiles;	// 첨부파일 목록
	
	private String searchMode;
	private String deptCd;
	private String sbjType;
	
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
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
	public int getWinWidth() {
		return winWidth;
	}
	public void setWinWidth(int winWidth) {
		this.winWidth = winWidth;
	}
	public int getWinHeight() {
		return winHeight;
	}
	public void setWinHeight(int winHeight) {
		this.winHeight = winHeight;
	}
	public String getWinMenuUseYn() {
		return winMenuUseYn;
	}
	public void setWinMenuUseYn(String winMenuUseYn) {
		this.winMenuUseYn = winMenuUseYn;
	}
	public String getWinSkinCd() {
		return winSkinCd;
	}
	public void setWinSkinCd(String winSkinCd) {
		this.winSkinCd = winSkinCd;
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
	public String getMainYn() {
		return mainYn;
	}
	public void setMainYn(String mainYn) {
		this.mainYn = mainYn;
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
	public int getSemiExamRatio() {
		return semiExamRatio;
	}
	public void setSemiExamRatio(int semiExamRatio) {
		this.semiExamRatio = semiExamRatio;
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
	public String getSbjTypeNm() {
		return sbjTypeNm;
	}
	public void setSbjTypeNm(String sbjTypeNm) {
		this.sbjTypeNm = sbjTypeNm;
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
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public int getContentsCnt() {
		return contentsCnt;
	}
	public void setContentsCnt(int contentsCnt) {
		this.contentsCnt = contentsCnt;
	}
	public int getCrsCreCnt() {
		return crsCreCnt;
	}
	public void setCrsCreCnt(int crsCreCnt) {
		this.crsCreCnt = crsCreCnt;
	}
	public String getCreateMode() {
		return createMode;
	}
	public void setCreateMode(String createMode) {
		this.createMode = createMode;
	}
	public String getParSbjCtgrCd() {
		return parSbjCtgrCd;
	}
	public void setParSbjCtgrCd(String parSbjCtgrCd) {
		this.parSbjCtgrCd = parSbjCtgrCd;
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
	public String getStudyMthd() {
		return studyMthd;
	}
	public void setStudyMthd(String studyMthd) {
		this.studyMthd = studyMthd;
	}
	public String getRefundYn() {
		return refundYn;
	}
	public void setRefundYn(String refundYn) {
		this.refundYn = refundYn;
	}
	public Integer getRefundPrice() {
		return refundPrice;
	}
	public void setRefundPrice(Integer refundPrice) {
		this.refundPrice = refundPrice;
	}
	public Integer getEduPrice() {
		return eduPrice;
	}
	public void setEduPrice(Integer eduPrice) {
		this.eduPrice = eduPrice;
	}
	public String getSimsaCode() {
		return simsaCode;
	}
	public void setSimsaCode(String simsaCode) {
		this.simsaCode = simsaCode;
	}
	public String getTracseId() {
		return tracseId;
	}
	public void setTracseId(String tracseId) {
		this.tracseId = tracseId;
	}
	public String getEduGrade() {
		return eduGrade;
	}
	public void setEduGrade(String eduGrade) {
		this.eduGrade = eduGrade;
	}
	public Integer getEduNop() {
		return eduNop;
	}
	public void setEduNop(Integer eduNop) {
		this.eduNop = eduNop;
	}
	public String getEduPrps() {
		return eduPrps;
	}
	public void setEduPrps(String eduPrps) {
		this.eduPrps = eduPrps;
	}
	public String getEduTarget() {
		return eduTarget;
	}
	public void setEduTarget(String eduTarget) {
		this.eduTarget = eduTarget;
	}
	public String getEduTm() {
		return eduTm;
	}
	public void setEduTm(String eduTm) {
		this.eduTm = eduTm;
	}
	public Integer getEduDaycnt() {
		return eduDaycnt;
	}
	public void setEduDaycnt(Integer eduDaycnt) {
		this.eduDaycnt = eduDaycnt;
	}
	public String getEvalDesc() {
		return evalDesc;
	}
	public void setEvalDesc(String evalDesc) {
		this.evalDesc = evalDesc;
	}
	public String getTchDesc() {
		return tchDesc;
	}
	public void setTchDesc(String tchDesc) {
		this.tchDesc = tchDesc;
	}
	public Integer getDayStudyLimit() {
		return dayStudyLimit;
	}
	public void setDayStudyLimit(Integer dayStudyLimit) {
		this.dayStudyLimit = dayStudyLimit;
	}
	public int getAttdRatio() {
		return attdRatio;
	}
	public void setAttdRatio(int attdRatio) {
		this.attdRatio = attdRatio;
	}
	public int getEtcRatio() {
		return etcRatio;
	}
	public void setEtcRatio(int etcRatio) {
		this.etcRatio = etcRatio;
	}
	
	//-- 썸네일 파일 관련 처리
	public Integer getThumbFileSn() {
		if(ValidationUtils.isNull(this.thumbFileSn))
		return this.getThumbFile().getFileSn();
		else return this.thumbFileSn;
	}
	public void setThumbFileSn(Integer thumbFileSn) {
		this.getThumbFile().setFileSn(thumbFileSn);
	}
	public SysFileVO getThumbFile() {
		if (this.thumbFile == null)
			this.thumbFile = new SysFileVO();
		return this.thumbFile;
	}
	public void setThumbFile(SysFileVO thumbFile) {
		this.thumbFile = thumbFile;
	}
	public String getThumbFileJson() {
		return SysFileVOUtil.getJson(this.getThumbFile(), false);
	}
	
	//-- 첨부 파일 관련 처리
	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}
	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}
	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}
	@Override
	public String callHrdCouresAgentPk() {
		return this.sbjCd;
	}
	@Override
	public String callHrdName() {
		return this.sbjNm;
	}
	@Override
	public String callHrdSimsaCode() {
		return this.simsaCode != null 
				? this.simsaCode.replaceAll("-", "") 
				: "";
	}
	@Override
	public String callHrdTracseId() {
		return this.simsaCode != null 
				? this.tracseId.replaceAll("-", "") 
				: "";
	}
	
	public String getSbjType() {
		return sbjType;
	}
	public void setSbjType(String sbjType) {
		this.sbjType = sbjType;
	}
}