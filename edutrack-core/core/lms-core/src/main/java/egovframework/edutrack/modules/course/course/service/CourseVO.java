package egovframework.edutrack.modules.course.course.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

/**
 * 과정 정보 VO
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 정보
 * </pre>
 *
 * @author MediopiaTech
 */
public class CourseVO extends DefaultVO {

	private static final long serialVersionUID = 8341765422572750860L;

	private String  orgCd;
	private String  crsCd;
	private String  crsNm;
	private String  crsCtgrCd;
	private String[]  crsCtgrArr;
	private String  crsCtgrNm;
	private String  crsCtgrPath;
	private String  crsCtgrPathNm;
	private String  crsOperMthd;
	private String  crsOperMthdNm;
	private String  crsOperType;
	private String  crsOperTypeNm;
	private String  enrlCertMthd;
	private String  enrlCertMthdNm;
	private String  prsnCertYn;
	private String  cpltHandlType;
	private String  cpltHandlTypeNm;
	private String  scoreHandlMthd;
	private String  scoreHandlMthdNm;
	private String  certIssueYn;
	private String  crsSvcType;
	private String  eduDivCd;
	private String  eduDivNm;
	private String  bsnsDivCd;
	private String  bsnsDivNm;
	private String  eduPeriodCd;
	private String  eduPeriodNm;
	private String  crsTypeCd;
	private String  crsTypeNm;
	private String  crsLvlCd;
	private String  crsLvlNm;
	private String  eduPlaceCd;
	private String  eduPlaceNm;
	private String  alcOrgCd;
	private String  alcOrgNm;
	private String  eduTeam;
	private String  ueinsYn;
	private String  stayYn;
	private String  eduTm;
	private String  oflnEduTm;
	private String  eduNop;
	private String  stayDaycnt;
	private String  eduTarget;
	private String  eduTargetNm;
	private Integer eduPrice;
	private String  eduMthd;
	private String  eduPrps;
	private String  crsDesc;
	private Integer eduCredit;
	private String  useYn="Y";
	private Integer prgrRatio;
	private Integer attdRatio;
	private Integer examRatio;
	private Integer semiExamRatio;
	private Integer forumRatio;
	private Integer asmtRatio;
	private Integer prjtRatio;
	private Integer joinRatio;
	private Integer etcRatio;
	private Integer resh1Sn;
	private Integer resh2Sn;
	private Integer resh3Sn;
	private String  resh1Cd;
	private String  resh2Cd;
	private String  resh3Cd;
	private String  resh1Yn;
	private String  resh2Yn;
	private String  resh3Yn;
	private Integer cpltScore;

	private Integer sbjCnt;
	private Integer profCnt;
	private Integer subCnt;
	private Integer creCrsCnt;
	private String  autoMakeCd = "Y";
	private Integer  creYear;

	private Integer creCnt;
	private Integer yearNopCnt;
	private Integer termNopCnt;

	private String  dupYn;
	private String  openYn;

	private String  searchMode;

	private String  createdOnly = "N";

	private String  parCrsCtgrCd;

	private String sbjCd;
	private String  nopLimitYn;

	private String 	oflnEduPlace;		//교육장소

	private String crsCreCd;
	private String crsCreNm;
	private String enrlStsNm;
	private String enrlSts;
	
	private List<CreateCourseVO> createCourseList;

	/*  첨부파일 목록 */
	private Integer thumbFileSn;
	private SysFileVO thumbFile;
	private List<SysFileVO>	attachFiles; 
	private List<SysFileVO>	attachImages;	
	
	/* 기수 start */
	private Integer  crsYear;
	private Integer  crsTerm;
	private String  enrlAplcStartDttm;
	private String  enrlAplcEndDttm;
	private String  enrlAplcDttm;
	private String  enrlStartDttm;
	private String  enrlEndDttm;
	private String  scoreOpenStartDttm;
	private String  scoreOpenEndDttm;
	private String  termEndDttm;
	private String  deptCds;
	private String  deptNms;
	/* 기수 end */
	
	/*자격증 과정 회차 여부 start */
	private int creCount;
	/*자격증 과정 회차 여부 end */
	
	private String userNo;
	private String deptCd;
	
	private String metaTag;
	private String[] metaTagArr;
	
	
	public Integer getSemiExamRatio() {
		return semiExamRatio;
	}
	public void setSemiExamRatio(Integer semiExamRatio) {
		this.semiExamRatio = semiExamRatio;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
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
	public String getCrsNm() {
		return crsNm;
	}
	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}
	public String getCrsCtgrCd() {
		return crsCtgrCd;
	}
	public void setCrsCtgrCd(String crsCtgrCd) {
		this.crsCtgrCd = crsCtgrCd;
	}
	public String getCrsCtgrNm() {
		return crsCtgrNm;
	}
	public void setCrsCtgrNm(String crsCtgrNm) {
		this.crsCtgrNm = crsCtgrNm;
	}
	public String getCrsCtgrPath() {
		return crsCtgrPath;
	}
	public void setCrsCtgrPath(String crsCtgrPath) {
		this.crsCtgrPath = crsCtgrPath;
	}
	public String getCrsCtgrPathNm() {
		return crsCtgrPathNm;
	}
	public void setCrsCtgrPathNm(String crsCtgrPathNm) {
		this.crsCtgrPathNm = crsCtgrPathNm;
	}
	public String getCrsOperMthd() {
		return crsOperMthd;
	}
	public void setCrsOperMthd(String crsOperMthd) {
		this.crsOperMthd = crsOperMthd;
	}
	public String getCrsOperMthdNm() {
		return crsOperMthdNm;
	}
	public void setCrsOperMthdNm(String crsOperMthdNm) {
		this.crsOperMthdNm = crsOperMthdNm;
	}
	public String getCrsOperType() {
		return crsOperType;
	}
	public void setCrsOperType(String crsOperType) {
		this.crsOperType = crsOperType;
	}
	public String getCrsOperTypeNm() {
		return crsOperTypeNm;
	}
	public void setCrsOperTypeNm(String crsOperTypeNm) {
		this.crsOperTypeNm = crsOperTypeNm;
	}
	public String getEnrlCertMthd() {
		return enrlCertMthd;
	}
	public void setEnrlCertMthd(String enrlCertMthd) {
		this.enrlCertMthd = enrlCertMthd;
	}
	public String getEnrlCertMthdNm() {
		return enrlCertMthdNm;
	}
	public void setEnrlCertMthdNm(String enrlCertMthdNm) {
		this.enrlCertMthdNm = enrlCertMthdNm;
	}
	public String getPrsnCertYn() {
		return prsnCertYn;
	}
	public void setPrsnCertYn(String prsnCertYn) {
		this.prsnCertYn = prsnCertYn;
	}
	public String getCpltHandlType() {
		return cpltHandlType;
	}
	public void setCpltHandlType(String cpltHandlType) {
		this.cpltHandlType = cpltHandlType;
	}
	public String getCpltHandlTypeNm() {
		return cpltHandlTypeNm;
	}
	public void setCpltHandlTypeNm(String cpltHandlTypeNm) {
		this.cpltHandlTypeNm = cpltHandlTypeNm;
	}
	public String getScoreHandlMthd() {
		return scoreHandlMthd;
	}
	public void setScoreHandlMthd(String scoreHandlMthd) {
		this.scoreHandlMthd = scoreHandlMthd;
	}
	public String getScoreHandlMthdNm() {
		return scoreHandlMthdNm;
	}
	public void setScoreHandlMthdNm(String scoreHandlMthdNm) {
		this.scoreHandlMthdNm = scoreHandlMthdNm;
	}
	public String getCertIssueYn() {
		return certIssueYn;
	}
	public void setCertIssueYn(String certIssueYn) {
		this.certIssueYn = certIssueYn;
	}
	public String getCrsSvcType() {
		return crsSvcType;
	}
	public void setCrsSvcType(String crsSvcType) {
		this.crsSvcType = crsSvcType;
	}
	public String getEduDivCd() {
		return eduDivCd;
	}
	public void setEduDivCd(String eduDivCd) {
		this.eduDivCd = eduDivCd;
	}
	public String getEduDivNm() {
		return eduDivNm;
	}
	public void setEduDivNm(String eduDivNm) {
		this.eduDivNm = eduDivNm;
	}
	public String getBsnsDivCd() {
		return bsnsDivCd;
	}
	public void setBsnsDivCd(String bsnsDivCd) {
		this.bsnsDivCd = bsnsDivCd;
	}
	public String getBsnsDivNm() {
		return bsnsDivNm;
	}
	public void setBsnsDivNm(String bsnsDivNm) {
		this.bsnsDivNm = bsnsDivNm;
	}
	public String getEduPeriodCd() {
		return eduPeriodCd;
	}
	public void setEduPeriodCd(String eduPeriodCd) {
		this.eduPeriodCd = eduPeriodCd;
	}
	public String getEduPeriodNm() {
		return eduPeriodNm;
	}
	public void setEduPeriodNm(String eduPeriodNm) {
		this.eduPeriodNm = eduPeriodNm;
	}
	public String getCrsTypeCd() {
		return crsTypeCd;
	}
	public void setCrsTypeCd(String crsTypeCd) {
		this.crsTypeCd = crsTypeCd;
	}
	public String getCrsTypeNm() {
		return crsTypeNm;
	}
	public void setCrsTypeNm(String crsTypeNm) {
		this.crsTypeNm = crsTypeNm;
	}
	public String getCrsLvlCd() {
		return crsLvlCd;
	}
	public void setCrsLvlCd(String crsLvlCd) {
		this.crsLvlCd = crsLvlCd;
	}
	public String getCrsLvlNm() {
		return crsLvlNm;
	}
	public void setCrsLvlNm(String crsLvlNm) {
		this.crsLvlNm = crsLvlNm;
	}
	public String getEduPlaceCd() {
		return eduPlaceCd;
	}
	public void setEduPlaceCd(String eduPlaceCd) {
		this.eduPlaceCd = eduPlaceCd;
	}
	public String getEduPlaceNm() {
		return eduPlaceNm;
	}
	public void setEduPlaceNm(String eduPlaceNm) {
		this.eduPlaceNm = eduPlaceNm;
	}
	public String getAlcOrgCd() {
		return alcOrgCd;
	}
	public void setAlcOrgCd(String alcOrgCd) {
		this.alcOrgCd = alcOrgCd;
	}
	public String getAlcOrgNm() {
		return alcOrgNm;
	}
	public void setAlcOrgNm(String alcOrgNm) {
		this.alcOrgNm = alcOrgNm;
	}
	public String getUeinsYn() {
		return ueinsYn;
	}
	public void setUeinsYn(String ueinsYn) {
		this.ueinsYn = ueinsYn;
	}
	public String getStayYn() {
		return stayYn;
	}
	public void setStayYn(String stayYn) {
		this.stayYn = stayYn;
	}
	public String getEduTm() {
		return eduTm;
	}
	public void setEduTm(String eduTm) {
		this.eduTm = eduTm;
	}
	public String getEduNop() {
		return eduNop;
	}
	public void setEduNop(String eduNop) {
		this.eduNop = eduNop;
	}
	public String getStayDaycnt() {
		return stayDaycnt;
	}
	public void setStayDaycnt(String stayDaycnt) {
		this.stayDaycnt = stayDaycnt;
	}
	public String getEduTarget() {
		return eduTarget;
	}
	public void setEduTarget(String eduTarget) {
		this.eduTarget = eduTarget;
	}
	public Integer getEduPrice() {
		return eduPrice;
	}
	public void setEduPrice(Integer eduPrice) {
		this.eduPrice = eduPrice;
	}
	public String getEduMthd() {
		return eduMthd;
	}
	public void setEduMthd(String eduMthd) {
		this.eduMthd = eduMthd;
	}
	public String getEduPrps() {
		return eduPrps;
	}
	public void setEduPrps(String eduPrps) {
		this.eduPrps = eduPrps;
	}
	public String getCrsDesc() {
		return crsDesc;
	}
	public void setCrsDesc(String crsDesc) {
		this.crsDesc = crsDesc;
	}
	public Integer getEduCredit() {
		return eduCredit;
	}
	public void setEduCredit(Integer eduCredit) {
		this.eduCredit = eduCredit;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getPrgrRatio() {
		return prgrRatio;
	}
	public void setPrgrRatio(Integer prgrRatio) {
		this.prgrRatio = prgrRatio;
	}
	public Integer getAttdRatio() {
		return attdRatio;
	}
	public void setAttdRatio(Integer attdRatio) {
		this.attdRatio = attdRatio;
	}
	public Integer getExamRatio() {
		return examRatio;
	}
	public void setExamRatio(Integer examRatio) {
		this.examRatio = examRatio;
	}
	public Integer getForumRatio() {
		return forumRatio;
	}
	public void setForumRatio(Integer forumRatio) {
		this.forumRatio = forumRatio;
	}
	public Integer getAsmtRatio() {
		return asmtRatio;
	}
	public void setAsmtRatio(Integer asmtRatio) {
		this.asmtRatio = asmtRatio;
	}
	public Integer getPrjtRatio() {
		return prjtRatio;
	}
	public void setPrjtRatio(Integer prjtRatio) {
		this.prjtRatio = prjtRatio;
	}
	public Integer getJoinRatio() {
		return joinRatio;
	}
	public void setJoinRatio(Integer joinRatio) {
		this.joinRatio = joinRatio;
	}
	public Integer getEtcRatio() {
		return etcRatio;
	}
	public void setEtcRatio(Integer etcRatio) {
		this.etcRatio = etcRatio;
	}
	public Integer getResh1Sn() {
		return resh1Sn;
	}
	public void setResh1Sn(Integer resh1Sn) {
		this.resh1Sn = resh1Sn;
	}
	public Integer getResh2Sn() {
		return resh2Sn;
	}
	public void setResh2Sn(Integer resh2Sn) {
		this.resh2Sn = resh2Sn;
	}
	public Integer getResh3Sn() {
		return resh3Sn;
	}
	public void setResh3Sn(Integer resh3Sn) {
		this.resh3Sn = resh3Sn;
	}
	public Integer getCpltScore() {
		return cpltScore;
	}
	public void setCpltScore(Integer cpltScore) {
		this.cpltScore = cpltScore;
	}
	public Integer getSbjCnt() {
		return sbjCnt;
	}
	public void setSbjCnt(Integer sbjCnt) {
		this.sbjCnt = sbjCnt;
	}
	public Integer getProfCnt() {
		return profCnt;
	}
	public void setProfCnt(Integer profCnt) {
		this.profCnt = profCnt;
	}
	public Integer getSubCnt() {
		return subCnt;
	}
	public void setSubCnt(Integer subCnt) {
		this.subCnt = subCnt;
	}
	public Integer getCreCrsCnt() {
		return creCrsCnt;
	}
	public void setCreCrsCnt(Integer creCrsCnt) {
		this.creCrsCnt = creCrsCnt;
	}
	public String getAutoMakeCd() {
		return autoMakeCd;
	}
	public void setAutoMakeCd(String autoMakeCd) {
		this.autoMakeCd = autoMakeCd;
	}
	public Integer getCreYear() {
		return creYear;
	}
	public void setCreYear(Integer creYear) {
		this.creYear = creYear;
	}
	public String getEduTeam() {
		return eduTeam;
	}
	public void setEduTeam(String eduTeam) {
		this.eduTeam = eduTeam;
	}
	public Integer getCreCnt() {
		return creCnt;
	}
	public void setCreCnt(Integer creCnt) {
		this.creCnt = creCnt;
	}
	public Integer getYearNopCnt() {
		return yearNopCnt;
	}
	public void setYearNopCnt(Integer yearNopCnt) {
		this.yearNopCnt = yearNopCnt;
	}
	public Integer getTermNopCnt() {
		return termNopCnt;
	}
	public void setTermNopCnt(Integer termNopCnt) {
		this.termNopCnt = termNopCnt;
	}
	public List<CreateCourseVO> getCreateCourseList() {
		return createCourseList;
	}
	public void setCreateCourseList(List<CreateCourseVO> createCourseList) {
		this.createCourseList = createCourseList;
	}
	public String getDupYn() {
		return dupYn;
	}
	public void setDupYn(String dupYn) {
		this.dupYn = dupYn;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getEduTargetNm() {
		return eduTargetNm;
	}
	public void setEduTargetNm(String eduTargetNm) {
		this.eduTargetNm = eduTargetNm;
	}
	public String getCreatedOnly() {
		return createdOnly;
	}
	public void setCreatedOnly(String createdOnly) {
		this.createdOnly = createdOnly;
	}
	public String getResh1Cd() {
		return resh1Cd;
	}
	public void setResh1Cd(String resh1Cd) {
		this.resh1Cd = resh1Cd;
	}
	public String getResh2Cd() {
		return resh2Cd;
	}
	public void setResh2Cd(String resh2Cd) {
		this.resh2Cd = resh2Cd;
	}
	public String getResh3Cd() {
		return resh3Cd;
	}
	public void setResh3Cd(String resh3Cd) {
		this.resh3Cd = resh3Cd;
	}
	public String getResh1Yn() {
		return resh1Yn;
	}
	public void setResh1Yn(String resh1Yn) {
		this.resh1Yn = resh1Yn;
	}
	public String getResh2Yn() {
		return resh2Yn;
	}
	public void setResh2Yn(String resh2Yn) {
		this.resh2Yn = resh2Yn;
	}
	public String getResh3Yn() {
		return resh3Yn;
	}
	public void setResh3Yn(String resh3Yn) {
		this.resh3Yn = resh3Yn;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public String getOflnEduTm() {
		return oflnEduTm;
	}
	public void setOflnEduTm(String oflnEduTm) {
		this.oflnEduTm = oflnEduTm;
	}
	public String getParCrsCtgrCd() {
		return parCrsCtgrCd;
	}
	public void setParCrsCtgrCd(String parCrsCtgrCd) {
		this.parCrsCtgrCd = parCrsCtgrCd;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public String getNopLimitYn() {
		return nopLimitYn;
	}
	public void setNopLimitYn(String nopLimitYn) {
		this.nopLimitYn = nopLimitYn;
	}
	public String getOflnEduPlace() {
		return oflnEduPlace;
	}
	public void setOflnEduPlace(String oflnEduPlace) {
		this.oflnEduPlace = oflnEduPlace;
	}
	public String[] getCrsCtgrArr() {
		return crsCtgrArr;
	}
	public void setCrsCtgrArr(String[] crsCtgrArr) {
		this.crsCtgrArr = crsCtgrArr;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
	public String getEnrlStsNm() {
		return enrlStsNm;
	}
	public void setEnrlStsNm(String enrlStsNm) {
		this.enrlStsNm = enrlStsNm;
	}
	public String getEnrlSts() {
		return enrlSts;
	}
	public void setEnrlSts(String enrlSts) {
		this.enrlSts = enrlSts;
	}
	/* 기수 start */
	public Integer getCrsYear() {
		return crsYear;
	}
	public void setCrsYear(Integer crsYear) {
		this.crsYear = crsYear;
	}
	public Integer getCrsTerm() {
		return crsTerm;
	}
	public void setCrsTerm(Integer crsTerm) {
		this.crsTerm = crsTerm;
	}
	public String getEnrlAplcStartDttm() {
		return enrlAplcStartDttm;
	}
	public void setEnrlAplcStartDttm(String enrlAplcStartDttm) {
		this.enrlAplcStartDttm = enrlAplcStartDttm;
	}
	public String getEnrlAplcEndDttm() {
		return enrlAplcEndDttm;
	}
	public void setEnrlAplcEndDttm(String enrlAplcEndDttm) {
		this.enrlAplcEndDttm = enrlAplcEndDttm;
	}
	public String getEnrlAplcDttm() {
		return enrlAplcDttm;
	}
	public void setEnrlAplcDttm(String enrlAplcDttm) {
		this.enrlAplcDttm = enrlAplcDttm;
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
	public String getScoreOpenStartDttm() {
		return scoreOpenStartDttm;
	}
	public void setScoreOpenStartDttm(String scoreOpenStartDttm) {
		this.scoreOpenStartDttm = scoreOpenStartDttm;
	}
	public String getScoreOpenEndDttm() {
		return scoreOpenEndDttm;
	}
	public void setScoreOpenEndDttm(String scoreOpenEndDttm) {
		this.scoreOpenEndDttm = scoreOpenEndDttm;
	}
	public String getTermEndDttm() {
		return termEndDttm;
	}
	public void setTermEndDttm(String termEndDttm) {
		this.termEndDttm = termEndDttm;
	}
	public String getDeptCds() {
		return deptCds;
	}
	public void setDeptCds(String deptCds) {
		this.deptCds = deptCds;
	}
	public String getDeptNms() {
		return deptNms;
	}
	public void setDeptNms(String deptNms) {
		this.deptNms = deptNms;
	}
	/* 기수 end */
	
	/*자격증 과정 회차 여부  */
	public int getCreCount() {
		return creCount;
	}
	public void setCreCount(int creCount) {
		this.creCount = creCount;
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
	public String getMetaTag() {
		return metaTag;
	}
	public void setMetaTag(String metaTag) {
		this.metaTag = metaTag;
	}
	public String[] getMetaTagArr() {
		return metaTagArr;
	}
	public void setMetaTagArr(String[] metaTagArr) {
		this.metaTagArr = metaTagArr;
	}
	
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}

	/* 에디터 첨부파일 핸들링용 매서드 */
	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}

	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}

	/* 에디터 첨부파일 Json 정보 getter용 */
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}
	
}