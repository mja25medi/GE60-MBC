/**
 *
 */
package egovframework.edutrack.modules.course.createcourse.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

/**
 * 개설 과정 VO
 */
public class CreateCourseVO extends DefaultVO {

	private static final long	serialVersionUID	= 3686077363002936982L;
	private String  crsCreCd;
	private String  crsCd;
	private String 	creTypeCd;
	private Integer  creYear;
	private Integer  creTerm;
	private String  crsCreNm;
	private String  crsNm;
	private String  crsDesc;
	private String  enrlAplcStartDttm;
	private String  enrlAplcEndDttm;
	private String  oflnStartDttm;
	private String  oflnEndDttm;
	private String  enrlStartDttm;
	private String  enrlEndDttm;
	private String  auditEndDttm;
	private String  nopLimitYn;
	private Integer enrlNop;
	private String  stayYn;
	private String  stayDaycnt;
	private String  eduPeriodCd;
	private String  crsSvcType;
	private String  mngType;
	
	private String enrlStsNm;
	private String enrlSts;
	private String enrlAplcDttm;
	/*	총 교육시간	*/
	private String  onlnEduTm;
	
	private String  oflnEduTm;
	private Integer eduPrice;
	private String  scoreHandlCd;
	private Integer cpltScore;
	private Integer cpltPrgrRate;
	private Integer prgrRatio;
	private Integer attdRatio;
	private Integer examRatio;
	private Integer semiExamRatio;
	private Integer forumRatio;
	private Integer asmtRatio;
	private Integer prjtRatio;
	private Integer joinRatio;
	private String	etcNm;
	private Integer etcRatio;
	private String	etcNm2;
	private Integer etcRatio2;
	private String	etcNm3;
	private Integer etcRatio3;
	private String	etcNm4;
	private Integer etcRatio4;
	private String	etcNm5;
	private Integer etcRatio5;
	private Integer resh1Sn;
	private Integer resh2Sn;
	private Integer resh3Sn;
	private String  resh1Cd;
	private String  resh2Cd;
	private String  resh3Cd;
	private String  resh1Yn;
	private String  resh2Yn;
	private String  resh3Yn;
	private Integer dayStudyLimit;
	private Integer declsCnt;
	private String  targetOrg;
	private String  regNo;
	private String  regDttm;
	private String  modNo;
	private String  modDttm;
	private String  eduTeam;
	private String  ueinsYn;
	private String  useYn;
	private String  scoreOpenDttm;

	private String	w0;
	private String	w1;
	private String	w2;
	private String	w3;
	private String	w4;
	private String	w5;
	private String	w6;

	private Object	w0Str;
	private Object	w1Str;
	private Object	w2Str;
	private Object	w3Str;
	private Object	w4Str;
	private Object	w5Str;
	private Object	w6Str;

	private String	parCtgrCd;

	private int		stuCnt;

	private String	crsCtgrCd;
	private String  crsCtgrNm;
	private String[]	crsCtgrArr;
	private String	crsOperMthd;
	private String	crsOperType;
	private String  eduTeamCd;

	private Integer		enrlDaycnt;
	private Integer		tracseTme;

	private int		enrlCnt;
	private int		cnfmCnt;
	private int		cpltCnt;
	private int		failCnt;

	private int     ueinsAplcCnt;
	private int		ueinsAplyCnt;
	private int		refundPrice;

	private int		sbjScore;
	private int 	sbjCnt;			//과목개수
	private int		contentsCnt;	//컨텐츠개수
	private int		tchCnt;			//개설과정교수자수

	private String 	eduTarget;		//교육대상
	private String	eduYear;		//교육연차

	private int		enrlGapDate;
	
	private String  enrlCertDttm;

	private String  enrlAplcYn;
	private String  refundYn;

	private String  selectMode = "CREATE";
	private Integer examCnt;
	private Integer asmtCnt;
	private Integer forumCnt;
	private Integer reshCnt;
	private Integer prjtCnt;

	private String  yearMonth;

	private Integer paymCardPrice;
	private Integer paymBankPrice;
	private Integer paymBookPrice;
	private Integer paymSitePrice;

	private Integer unpayPrice;
	private Integer repayPrice;

	private String  eduPeriodNm;
	private String  aplcClassNm;
	private Integer classAlcCnt;
	private String  orgCd;
	private String  checkOrgCd = "N";
	private String  searchMode;

	private String  enrlYn;

	private String  createdOnly = "N";

	private String sbjCd;
	private String sbjNm;
	private Integer sbjCnts;

	private String userNo;
	private String stdNo;
	private String deptCd;
	private String deptNm;

	/* 진행중인과정 TodoList */
	private String courseType;
	private String courseSn;
	private String courseTitle;
	private String courseStartDttm;
	private String courseEndDttm;
	private String courseExtSendDttm;
	private String courseStareYn;
	private Integer atclCount;
	private Integer cmntCount;

	private String 	oflnEduPlace;		//교육장소
	private String 	stdCnts;		//수강신청 학생수
	
	private String sbjCtgrCd;
	private String sbjCtgrNm;
	private int bskCnt;
	
	private Integer  crsYear;
	private Integer  crsTerm;
	private String  termEndDttm;
	
	private String	eduGrade;
	private Integer eduNop;
	private String  sbjDesc;
	private String  eduPrps;
	private String  eduTm;
	private Integer	eduDaycnt;
	private String  evalDesc;
	private String  tchDesc;
	
	private Integer thumbFileSn;
	private SysFileVO thumbFile;
	private List<SysFileVO>	attachFiles;	// 첨부파일 목록
	
	//-- 자격증 과정 과정 시작
	private String creDesc;	//자격증 과정 설명
	private int applyCertCnt;	//자격증 신청자수
	private int waitCertCnt;	//승인 대기자 수 
	private String certSts;		//자격증 신청 상태
	private String creOperTypeCd;
	
	// qr 코드
	private String crsCreQrInFilePath;		// 입실 QR 이미지 경로
	private String crsCreQrInNo;				// 입실 QR 번호
	private String crsCreQrOutFilePath; 	// 퇴실 QR 이미지 경로
	private String crsCreQrOutNo;			// 퇴실 QR 번호
	
	
	
	public String getMngType() {
		return mngType;
	}
	public void setMngType(String mngType) {
		this.mngType = mngType;
	}
	public String getCrsCreQrInFilePath() {
		return crsCreQrInFilePath;
	}
	public void setCrsCreQrInFilePath(String crsCreQrInFilePath) {
		this.crsCreQrInFilePath = crsCreQrInFilePath;
	}
	public String getCrsCreQrInNo() {
		return crsCreQrInNo;
	}
	public void setCrsCreQrInNo(String crsCreQrInNo) {
		this.crsCreQrInNo = crsCreQrInNo;
	}
	public String getCrsCreQrOutFilePath() {
		return crsCreQrOutFilePath;
	}
	public void setCrsCreQrOutFilePath(String crsCreQrOutFilePath) {
		this.crsCreQrOutFilePath = crsCreQrOutFilePath;
	}
	public String getCrsCreQrOutNo() {
		return crsCreQrOutNo;
	}
	public void setCrsCreQrOutNo(String crsCreQrOutNo) {
		this.crsCreQrOutNo = crsCreQrOutNo;
	}
	
	public String getSbjCtgrNm() {
		return sbjCtgrNm;
	}
	public void setSbjCtgrNm(String sbjCtgrNm) {
		this.sbjCtgrNm = sbjCtgrNm;
	}
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
	public String getTermEndDttm() {
		return termEndDttm;
	}
	public void setTermEndDttm(String termEndDttm) {
		this.termEndDttm = termEndDttm;
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
	public String getSbjDesc() {
		return sbjDesc;
	}
	public void setSbjDesc(String sbjDesc) {
		this.sbjDesc = sbjDesc;
	}
	public String getEduPrps() {
		return eduPrps;
	}
	public void setEduPrps(String eduPrps) {
		this.eduPrps = eduPrps;
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
	public int getBskCnt() {
		return bskCnt;
	}
	public void setBskCnt(int bskCnt) {
		this.bskCnt = bskCnt;
	}
	public String getSbjCtgrCd() {
		return sbjCtgrCd;
	}
	public void setSbjCtgrCd(String sbjCtgrCd) {
		this.sbjCtgrCd = sbjCtgrCd;
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
	public Integer getCreYear() {
		return creYear;
	}
	public void setCreYear(Integer creYear) {
		this.creYear = creYear;
	}
	public Integer getCreTerm() {
		return creTerm;
	}
	public void setCreTerm(Integer creTerm) {
		this.creTerm = creTerm;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
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
	public String getOflnStartDttm() {
		return oflnStartDttm;
	}
	public void setOflnStartDttm(String oflnStartDttm) {
		this.oflnStartDttm = oflnStartDttm;
	}
	public String getOflnEndDttm() {
		return oflnEndDttm;
	}
	public void setOflnEndDttm(String oflnEndDttm) {
		this.oflnEndDttm = oflnEndDttm;
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
	public String getAuditEndDttm() {
		return auditEndDttm;
	}
	public void setAuditEndDttm(String auditEndDttm) {
		this.auditEndDttm = auditEndDttm;
	}
	public String getNopLimitYn() {
		return nopLimitYn;
	}
	public void setNopLimitYn(String nopLimitYn) {
		this.nopLimitYn = nopLimitYn;
	}
	public Integer getEnrlNop() {
		return enrlNop;
	}
	public void setEnrlNop(Integer enrlNop) {
		this.enrlNop = enrlNop;
	}
	public String getStayYn() {
		return stayYn;
	}
	public void setStayYn(String stayYn) {
		this.stayYn = stayYn;
	}
	public String getStayDaycnt() {
		return stayDaycnt;
	}
	public void setStayDaycnt(String stayDaycnt) {
		this.stayDaycnt = stayDaycnt;
	}
	public String getEduPeriodCd() {
		return eduPeriodCd;
	}
	public void setEduPeriodCd(String eduPeriodCd) {
		this.eduPeriodCd = eduPeriodCd;
	}
	public String getOnlnEduTm() {
		return onlnEduTm;
	}
	public void setOnlnEduTm(String onlnEduTm) {
		this.onlnEduTm = onlnEduTm;
	}
	public String getOflnEduTm() {
		return oflnEduTm;
	}
	public void setOflnEduTm(String oflnEduTm) {
		this.oflnEduTm = oflnEduTm;
	}
	public Integer getEduPrice() {
		return eduPrice;
	}
	public void setEduPrice(Integer eduPrice) {
		this.eduPrice = eduPrice;
	}
	public String getScoreHandlCd() {
		return scoreHandlCd;
	}
	public void setScoreHandlCd(String scoreHandlCd) {
		this.scoreHandlCd = scoreHandlCd;
	}
	public Integer getCpltScore() {
		return cpltScore;
	}
	public void setCpltScore(Integer cpltScore) {
		this.cpltScore = cpltScore;
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
	public Integer getSemiExamRatio() {
		return semiExamRatio;
	}
	public void setSemiExamRatio(Integer semiExamRatio) {
		this.semiExamRatio = semiExamRatio;
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
	public Integer getEtcRatio2() {
		return etcRatio2;
	}
	public void setEtcRatio2(Integer etcRatio2) {
		this.etcRatio2 = etcRatio2;
	}
	public Integer getEtcRatio3() {
		return etcRatio3;
	}
	public void setEtcRatio3(Integer etcRatio3) {
		this.etcRatio3 = etcRatio3;
	}
	public Integer getEtcRatio4() {
		return etcRatio4;
	}
	public void setEtcRatio4(Integer etcRatio4) {
		this.etcRatio4 = etcRatio4;
	}
	public Integer getEtcRatio5() {
		return etcRatio5;
	}
	public void setEtcRatio5(Integer etcRatio5) {
		this.etcRatio5 = etcRatio5;
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
	public Integer getDayStudyLimit() {
		return dayStudyLimit;
	}
	public void setDayStudyLimit(Integer dayStudyLimit) {
		this.dayStudyLimit = dayStudyLimit;
	}
	public Integer getDeclsCnt() {
		return declsCnt;
	}
	public void setDeclsCnt(Integer declsCnt) {
		this.declsCnt = declsCnt;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getW0() {
		return w0;
	}
	public void setW0(String w0) {
		this.w0 = w0;
	}
	public String getW1() {
		return w1;
	}
	public void setW1(String w1) {
		this.w1 = w1;
	}
	public String getW2() {
		return w2;
	}
	public void setW2(String w2) {
		this.w2 = w2;
	}
	public String getW3() {
		return w3;
	}
	public void setW3(String w3) {
		this.w3 = w3;
	}
	public String getW4() {
		return w4;
	}
	public void setW4(String w4) {
		this.w4 = w4;
	}
	public String getW5() {
		return w5;
	}
	public void setW5(String w5) {
		this.w5 = w5;
	}
	public String getW6() {
		return w6;
	}
	public void setW6(String w6) {
		this.w6 = w6;
	}
	public Object getW0Str() {
		return w0Str;
	}
	public void setW0Str(Object w0Str) {
		this.w0Str = w0Str;
	}
	public Object getW1Str() {
		return w1Str;
	}
	public void setW1Str(Object w1Str) {
		this.w1Str = w1Str;
	}
	public Object getW2Str() {
		return w2Str;
	}
	public void setW2Str(Object w2Str) {
		this.w2Str = w2Str;
	}
	public Object getW3Str() {
		return w3Str;
	}
	public void setW3Str(Object w3Str) {
		this.w3Str = w3Str;
	}
	public Object getW4Str() {
		return w4Str;
	}
	public void setW4Str(Object w4Str) {
		this.w4Str = w4Str;
	}
	public Object getW5Str() {
		return w5Str;
	}
	public void setW5Str(Object w5Str) {
		this.w5Str = w5Str;
	}
	public Object getW6Str() {
		return w6Str;
	}
	public void setW6Str(Object w6Str) {
		this.w6Str = w6Str;
	}
	public String getParCtgrCd() {
		return parCtgrCd;
	}
	public void setParCtgrCd(String parCtgrCd) {
		this.parCtgrCd = parCtgrCd;
	}
	public int getStuCnt() {
		return stuCnt;
	}
	public void setStuCnt(int stuCnt) {
		this.stuCnt = stuCnt;
	}
	public String getCrsCtgrCd() {
		return crsCtgrCd;
	}
	public void setCrsCtgrCd(String crsCtgrCd) {
		this.crsCtgrCd = crsCtgrCd;
	}
	public String getCrsOperMthd() {
		return crsOperMthd;
	}
	public void setCrsOperMthd(String crsOperMthd) {
		this.crsOperMthd = crsOperMthd;
	}
	public String getCrsOperType() {
		return crsOperType;
	}
	public void setCrsOperType(String crsOperType) {
		this.crsOperType = crsOperType;
	}
	public int getEnrlCnt() {
		return enrlCnt;
	}
	public void setEnrlCnt(int enrlCnt) {
		this.enrlCnt = enrlCnt;
	}
	public int getCnfmCnt() {
		return cnfmCnt;
	}
	public void setCnfmCnt(int cnfmCnt) {
		this.cnfmCnt = cnfmCnt;
	}
	public int getCpltCnt() {
		return cpltCnt;
	}
	public void setCpltCnt(int cpltCnt) {
		this.cpltCnt = cpltCnt;
	}
	public int getSbjScore() {
		return sbjScore;
	}
	public void setSbjScore(int sbjScore) {
		this.sbjScore = sbjScore;
	}
	public int getSbjCnt() {
		return sbjCnt;
	}
	public void setSbjCnt(int sbjCnt) {
		this.sbjCnt = sbjCnt;
	}
	public String getEduTarget() {
		return eduTarget;
	}
	public void setEduTarget(String eduTarget) {
		this.eduTarget = eduTarget;
	}
	public String getEduYear() {
		return eduYear;
	}
	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}
	public Integer getEnrlDaycnt() {
		return enrlDaycnt;
	}
	public void setEnrlDaycnt(Integer enrlDaycnt) {
		this.enrlDaycnt = enrlDaycnt;
	}
	public String getEnrlCertDttm() {
		return enrlCertDttm;
	}
	public void setEnrlCertDttm(String enrlCertDttm) {
		this.enrlCertDttm = enrlCertDttm;
	}
	public int getEnrlGapDate() {
		return enrlGapDate;
	}
	public void setEnrlGapDate(int enrlGapDate) {
		this.enrlGapDate = enrlGapDate;
	}
	public String getSelectMode() {
		return selectMode;
	}
	public void setSelectMode(String selectMode) {
		this.selectMode = selectMode;
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
	public Integer getPrjtCnt() {
		return prjtCnt;
	}
	public void setPrjtCnt(Integer prjtCnt) {
		this.prjtCnt = prjtCnt;
	}
	public String getTargetOrg() {
		return targetOrg;
	}
	public void setTargetOrg(String targetOrg) {
		this.targetOrg = targetOrg;
	}
	public String getEduTeamCd() {
		return eduTeamCd;
	}
	public void setEduTeamCd(String eduTeamCd) {
		this.eduTeamCd = eduTeamCd;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getEduTeam() {
		return eduTeam;
	}
	public void setEduTeam(String eduTeam) {
		this.eduTeam = eduTeam;
	}
	public String getUeinsYn() {
		return ueinsYn;
	}
	public void setUeinsYn(String ueinsYn) {
		this.ueinsYn = ueinsYn;
	}
	public int getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(int failCnt) {
		this.failCnt = failCnt;
	}
	public int getUeinsAplcCnt() {
		return ueinsAplcCnt;
	}
	public void setUeinsAplcCnt(int ueinsAplcCnt) {
		this.ueinsAplcCnt = ueinsAplcCnt;
	}
	public int getUeinsAplyCnt() {
		return ueinsAplyCnt;
	}
	public void setUeinsAplyCnt(int ueinsAplyCnt) {
		this.ueinsAplyCnt = ueinsAplyCnt;
	}
	public int getRefundPrice() {
		return refundPrice;
	}
	public void setRefundPrice(int refundPrice) {
		this.refundPrice = refundPrice;
	}
	public Integer getPaymCardPrice() {
		return paymCardPrice;
	}
	public void setPaymCardPrice(Integer paymCardPrice) {
		this.paymCardPrice = paymCardPrice;
	}
	public Integer getPaymBankPrice() {
		return paymBankPrice;
	}
	public void setPaymBankPrice(Integer paymBankPrice) {
		this.paymBankPrice = paymBankPrice;
	}
	public Integer getPaymBookPrice() {
		return paymBookPrice;
	}
	public void setPaymBookPrice(Integer paymBookPrice) {
		this.paymBookPrice = paymBookPrice;
	}
	public Integer getPaymSitePrice() {
		return paymSitePrice;
	}
	public void setPaymSitePrice(Integer paymSitePrice) {
		this.paymSitePrice = paymSitePrice;
	}
	public Integer getUnpayPrice() {
		return unpayPrice;
	}
	public void setUnpayPrice(Integer unpayPrice) {
		this.unpayPrice = unpayPrice;
	}
	public Integer getRepayPrice() {
		return repayPrice;
	}
	public void setRepayPrice(Integer repayPrice) {
		this.repayPrice = repayPrice;
	}
	public String getEnrlAplcYn() {
		return enrlAplcYn;
	}
	public void setEnrlAplcYn(String enrlAplcYn) {
		this.enrlAplcYn = enrlAplcYn;
	}
	public String getEduPeriodNm() {
		return eduPeriodNm;
	}
	public void setEduPeriodNm(String eduPeriodNm) {
		this.eduPeriodNm = eduPeriodNm;
	}
	public String getAplcClassNm() {
		return aplcClassNm;
	}
	public void setAplcClassNm(String aplcClassNm) {
		this.aplcClassNm = aplcClassNm;
	}
	public Integer getClassAlcCnt() {
		return classAlcCnt;
	}
	public void setClassAlcCnt(Integer classAlcCnt) {
		this.classAlcCnt = classAlcCnt;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCheckOrgCd() {
		return checkOrgCd;
	}
	public void setCheckOrgCd(String checkOrgCd) {
		this.checkOrgCd = checkOrgCd;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getEnrlYn() {
		return enrlYn;
	}
	public void setEnrlYn(String enrlYn) {
		this.enrlYn = enrlYn;
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
	public int getTchCnt() {
		return tchCnt;
	}
	public void setTchCnt(int tchCnt) {
		this.tchCnt = tchCnt;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public Integer getSbjCnts() {
		return sbjCnts;
	}
	public void setSbjCnts(Integer sbjCnts) {
		this.sbjCnts = sbjCnts;
	}
	public int getContentsCnt() {
		return contentsCnt;
	}
	public void setContentsCnt(int contentsCnt) {
		this.contentsCnt = contentsCnt;
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
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseSn() {
		return courseSn;
	}
	public void setCourseSn(String courseSn) {
		this.courseSn = courseSn;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getCourseStartDttm() {
		return courseStartDttm;
	}
	public void setCourseStartDttm(String courseStartDttm) {
		this.courseStartDttm = courseStartDttm;
	}
	public String getCourseEndDttm() {
		return courseEndDttm;
	}
	public void setCourseEndDttm(String courseEndDttm) {
		this.courseEndDttm = courseEndDttm;
	}
	public String getCourseExtSendDttm() {
		return courseExtSendDttm;
	}
	public void setCourseExtSendDttm(String courseExtSendDttm) {
		this.courseExtSendDttm = courseExtSendDttm;
	}
	public String getCourseStareYn() {
		return courseStareYn;
	}
	public void setCourseStareYn(String courseStareYn) {
		this.courseStareYn = courseStareYn;
	}
	public Integer getAtclCount() {
		return atclCount;
	}
	public void setAtclCount(Integer atclCount) {
		this.atclCount = atclCount;
	}
	public Integer getCmntCount() {
		return cmntCount;
	}
	public void setCmntCount(Integer cmntCount) {
		this.cmntCount = cmntCount;
	}
	public String getOflnEduPlace() {
		return oflnEduPlace;
	}
	public void setOflnEduPlace(String oflnEduPlace) {
		this.oflnEduPlace = oflnEduPlace;
	}
	public String getStdCnts() {
		return stdCnts;
	}
	public void setStdCnts(String stdCnts) {
		this.stdCnts = stdCnts;
	}
	public String getCrsNm() {
		return crsNm;
	}
	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}
	public String getCrsDesc() {
		return crsDesc;
	}
	public void setCrsDesc(String crsDesc) {
		this.crsDesc = crsDesc;
	}
	public String[] getCrsCtgrArr() {
		return crsCtgrArr;
	}
	public void setCrsCtgrArr(String[] crsCtgrArr) {
		this.crsCtgrArr = crsCtgrArr;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	public String getRefundYn() {
		return refundYn;
	}
	public void setRefundYn(String refundYn) {
		this.refundYn = refundYn;
	}
	
	public Integer getTracseTme() {
		return tracseTme;
	}
	public void setTracseTme(Integer tracseTme) {
		this.tracseTme = tracseTme;
	}
	public String getCreTypeCd() {
		return creTypeCd;
	}
	public void setCreTypeCd(String creTypeCd) {
		this.creTypeCd = creTypeCd;
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
	public String getCreDesc() {
		return creDesc;
	}
	public void setCreDesc(String creDesc) {
		this.creDesc = creDesc;
	}
	public int getApplyCertCnt() {
		return applyCertCnt;
	}
	public void setApplyCertCnt(int applyCertCnt) {
		this.applyCertCnt = applyCertCnt;
	}
	public int getWaitCertCnt() {
		return waitCertCnt;
	}
	public void setWaitCertCnt(int waitCertCnt) {
		this.waitCertCnt = waitCertCnt;
	}
	
	public String getCertSts() {
		return certSts;
	}
	public void setCertSts(String certSts) {
		this.certSts = certSts;
	}
	public String getCreOperTypeCd() {
		return creOperTypeCd;
	}
	public void setCreOperTypeCd(String creOperTypeCd) {
		this.creOperTypeCd = creOperTypeCd;
	}
	public Integer getCpltPrgrRate() {
		return cpltPrgrRate;
	}
	public void setCpltPrgrRate(Integer cpltPrgrRate) {
		this.cpltPrgrRate = cpltPrgrRate;
	}
	
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getScoreOpenDttm() {
		return scoreOpenDttm;
	}
	public void setScoreOpenDttm(String scoreOpenDttm) {
		this.scoreOpenDttm = scoreOpenDttm;
	}
	public String getCrsCtgrNm() {
		return crsCtgrNm;
	}
	public void setCrsCtgrNm(String crsCtgrNm) {
		this.crsCtgrNm = crsCtgrNm;
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
	public String getEnrlAplcDttm() {
		return enrlAplcDttm;
	}
	public void setEnrlAplcDttm(String enrlAplcDttm) {
		this.enrlAplcDttm = enrlAplcDttm;
	}
	public String getEtcNm() {
		return etcNm;
	}
	public void setEtcNm(String etcNm) {
		this.etcNm = etcNm;
	}
	public String getEtcNm2() {
		return etcNm2;
	}
	public void setEtcNm2(String etcNm2) {
		this.etcNm2 = etcNm2;
	}
	public String getEtcNm3() {
		return etcNm3;
	}
	public void setEtcNm3(String etcNm3) {
		this.etcNm3 = etcNm3;
	}
	public String getEtcNm4() {
		return etcNm4;
	}
	public void setEtcNm4(String etcNm4) {
		this.etcNm4 = etcNm4;
	}
	public String getEtcNm5() {
		return etcNm5;
	}
	public void setEtcNm5(String etcNm5) {
		this.etcNm5 = etcNm5;
	}
	public String getCrsSvcType() {
		return crsSvcType;
	}
	public void setCrsSvcType(String crsSvcType) {
		this.crsSvcType = crsSvcType;
	}
}