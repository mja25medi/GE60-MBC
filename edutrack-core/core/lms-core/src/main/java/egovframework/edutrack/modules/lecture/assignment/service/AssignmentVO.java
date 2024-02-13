package egovframework.edutrack.modules.lecture.assignment.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;



public class AssignmentVO  extends DefaultVO {

	private static final long	serialVersionUID	= -155074520767701661L;

	private String  crsCreCd;
	private int		asmtSn;
	private String  asmtTitle;
	private String  asmtCts;
	private String  asmtTypeCd;
	private String	asmtTypeNm;
	private String  asmtUseCd;
	private String  asmtSelectTypeCd;
	private String	asmtSelectTypeNm;
	private String  asmtStartDttm;
	private String	asmtStartHour;
	private String	asmtStartMin;
	private String  asmtEndDttm;
	private String	asmtEndHour;
	private String	asmtEndMin;
	private String  extSendDttm;
	private String	extSendHour;
	private String	extSendMin;
	private int		asmtLimitCnt;
	private int		sendCritPrgrRatio;
	private String  regYn;
	private String	regYnNm;
	private int		subCnt;
	private int		sendCnt;
	private String	strQstnSn ;
	private String	sbjCd;
	private String	unitCd;
	private String	ctgrCd;
	private String	userNm;
	private String	stdNo;
	private String	asmtDuration;
	private String	startDttm;
	private String	endDttm;
	private String	sendCanYn;
	private double score;
	private String	sendYn;
	private String connYn;
	private String	rsltYn;

	private Integer declsNo;

	private List<SysFileVO>	attachFiles;	// 첨부파일 목록

	private String sbjNm;		//과목 명

	private String rateYn;//평가완료여부

	private String scoreYn;	//점수평가 여부

	private String atchCts;//첨언

	private double avgScore;	//과제별 평균점수
	private double myScore;
	private int		stdRatio;
	
	private String sendDttm;
	private String rateNo;
	private String rateDttm;
	
	private AssignmentSendVO assignmentSendVO;
	private AssignmentSubVO assignmentSubVO;
	
	private String[] stdNoObjArr;
	
	private String asmtSvcCd;			// 과제 서비스 코드(코딩과제:CODE, 일반과제:GEN)
	
	
	public String getAsmtSvcCd() {
		return asmtSvcCd;
	}
	public void setAsmtSvcCd(String asmtSvcCd) {
		this.asmtSvcCd = asmtSvcCd;
	}
	public AssignmentVO(){
		assignmentSendVO = new AssignmentSendVO();
		assignmentSubVO = new AssignmentSubVO();
	}

	public String[] getStdNoObjArr() {
		return stdNoObjArr;
	}
	public void setStdNoObjArr(String[] stdNoObjArr) {
		this.stdNoObjArr = stdNoObjArr;
	}
	public String getSendYn() {
		return this.sendYn;
	}
	public void setSendYn(String sendYn) {
		this.sendYn = HtmlCleaner.cleanTag(sendYn);
	}
	public String getSendCanYn() {
		return this.sendCanYn;
	}
	public void setSendCanYn(String sendCanYn) {
		this.sendCanYn = sendCanYn;
	}
	public String getAsmtDuration() {
		return this.asmtDuration;
	}
	public void setAsmtDuration(String asmtDuration) {
		this.asmtDuration = asmtDuration;
	}
	public String getCrsCreCd() {
		return this.crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public int getAsmtSn() {
		return this.asmtSn;
	}
	public void setAsmtSn(int asmtSn) {
		this.asmtSn = asmtSn;
	}
	public String getAsmtTitle() {
		return this.asmtTitle;
	}
	public void setAsmtTitle(String asmtTitle) {
		this.asmtTitle = asmtTitle;
	}
	public String getAsmtCts() {
		return this.asmtCts;
	}
	public void setAsmtCts(String asmtCts) {
		this.asmtCts = asmtCts;
	}
	public String getAsmtTypeCd() {
		return this.asmtTypeCd;
	}
	public void setAsmtTypeCd(String asmtTypeCd) {
		this.asmtTypeCd = asmtTypeCd;
	}
	public String getAsmtTypeNm() {
		return this.asmtTypeNm;
	}
	public void setAsmtTypeNm(String asmtTypeNm) {
		this.asmtTypeNm = asmtTypeNm;
	}
	public String getAsmtSelectTypeCd() {
		return this.asmtSelectTypeCd;
	}
	public void setAsmtSelectTypeCd(String asmtSelectTypeCd) {
		this.asmtSelectTypeCd = asmtSelectTypeCd;
	}
	public String getAsmtSelectTypeNm() {
		return this.asmtSelectTypeNm;
	}
	public void setAsmtSelectTypeNm(String asmtSelectTypeNm) {
		this.asmtSelectTypeNm = asmtSelectTypeNm;
	}
	public String getAsmtStartDttm() {
		return this.asmtStartDttm;
	}
	public void setAsmtStartDttm(String asmtStartDttm) {
		this.asmtStartDttm = asmtStartDttm;
	}
	public String getAsmtStartHour() {
		return this.asmtStartHour;
	}
	public void setAsmtStartHour(String asmtStartHour) {
		this.asmtStartHour = asmtStartHour;
	}
	public String getAsmtStartMin() {
		return this.asmtStartMin;
	}
	public void setAsmtStartMin(String asmtStartMin) {
		this.asmtStartMin = asmtStartMin;
	}
	public String getAsmtEndDttm() {
		return this.asmtEndDttm;
	}
	public void setAsmtEndDttm(String asmtEndDttm) {
		this.asmtEndDttm = asmtEndDttm;
	}
	public String getAsmtEndHour() {
		return this.asmtEndHour;
	}
	public void setAsmtEndHour(String asmtEndHour) {
		this.asmtEndHour = asmtEndHour;
	}
	public String getAsmtEndMin() {
		return this.asmtEndMin;
	}
	public void setAsmtEndMin(String asmtEndMin) {
		this.asmtEndMin = asmtEndMin;
	}
	public String getExtSendDttm() {
		return this.extSendDttm;
	}
	public void setExtSendDttm(String extSendDttm) {
		this.extSendDttm = extSendDttm;
	}
	public String getExtSendHour() {
		return this.extSendHour;
	}
	public void setExtSendHour(String extSendHour) {
		this.extSendHour = extSendHour;
	}
	public String getExtSendMin() {
		return this.extSendMin;
	}
	public void setExtSendMin(String extSendMin) {
		this.extSendMin = extSendMin;
	}
	public int getAsmtLimitCnt() {
		return this.asmtLimitCnt;
	}
	public void setAsmtLimitCnt(int asmtLimitCnt) {
		this.asmtLimitCnt = asmtLimitCnt;
	}
	public int getSendCritPrgrRatio() {
		return sendCritPrgrRatio;
	}
	public void setSendCritPrgrRatio(int sendCritPrgrRatio) {
		this.sendCritPrgrRatio = sendCritPrgrRatio;
	}
	public String getRegYn() {
		return this.regYn;
	}
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	public String getRegYnNm() {
		return this.regYnNm;
	}
	public void setRegYnNm(String regYnNm) {
		this.regYnNm = regYnNm;
	}
	public int getSubCnt() {
		return this.subCnt;
	}
	public void setSubCnt(int subCnt) {
		this.subCnt = subCnt;
	}
	public int getSendCnt() {
		return this.sendCnt;
	}
	public void setSendCnt(int sendCnt) {
		this.sendCnt = sendCnt;
	}
	public String getStrQstnSn() {
		return this.strQstnSn;
	}
	public void setStrQstnSn(String strQstnSn) {
		this.strQstnSn = strQstnSn;
	}
	public String getSbjCd() {
		return this.sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public String getCtgrCd() {
		return this.ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getUserNm() {
		return this.userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = HtmlCleaner.cleanTag(userNm);
	}
	public String getStdNo() {
		return this.stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	public Integer getDeclsNo() {
		return declsNo;
	}
	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}
	public String getStartDttm() {
		return startDttm;
	}

	public void setStartDttm(String startDttm) {
		this.startDttm = startDttm;
	}

	public String getEndDttm() {
		return endDttm;
	}

	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
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
	public String getRateYn() {
		return rateYn;
	}
	public void setRateYn(String rateYn) {
		this.rateYn = rateYn;
	}

	public String getScoreYn() {
		return scoreYn;
	}

	public void setScoreYn(String scoreYn) {
		this.scoreYn = scoreYn;
	}

	public String getAtchCts() {
		return atchCts;
	}

	public void setAtchCts(String atchCts) {
		this.atchCts = atchCts;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public String getConnYn() {
		return connYn;
	}

	public void setConnYn(String connYn) {
		this.connYn = connYn;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getMyScore() {
		return myScore;
	}

	public void setMyScore(double myScore) {
		this.myScore = myScore;
	}

	public String getRsltYn() {
		return rsltYn;
	}

	public void setRsltYn(String rsltYn) {
		this.rsltYn = rsltYn;
	}

	public AssignmentSendVO getAssignmentSendVO() {
		return assignmentSendVO;
	}

	public void setAssignmentSendVO(AssignmentSendVO assignmentSendVO) {
		this.assignmentSendVO = assignmentSendVO;
	}

	public AssignmentSubVO getAssignmentSubVO() {
		return assignmentSubVO;
	}

	public void setAssignmentSubVO(AssignmentSubVO assignmentSubVO) {
		this.assignmentSubVO = assignmentSubVO;
	}

	public int getStdRatio() {
		return stdRatio;
	}

	public void setStdRatio(int stdRatio) {
		this.stdRatio = stdRatio;
	}
	
	public String getSendDttm() {
		return sendDttm;
	}

	public String getRateNo() {
		return rateNo;
	}

	public String getRateDttm() {
		return rateDttm;
	}

	public void setSendDttm(String sendDttm) {
		this.sendDttm = sendDttm;
	}

	public void setRateNo(String rateNo) {
		this.rateNo = rateNo;
	}

	public void setRateDttm(String rateDttm) {
		this.rateDttm = rateDttm;
	}
	public String getUnitCd() {
		return unitCd;
	}
	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}
	public String getAsmtUseCd() {
		return asmtUseCd;
	}
	public void setAsmtUseCd(String asmtUseCd) {
		this.asmtUseCd = asmtUseCd;
	}

}