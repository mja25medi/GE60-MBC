package egovframework.edutrack.modules.student.student.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.code.impl.AttendStatusCode;
import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdStdSearchable;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdStdSendable;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;

/**
 * 학습자 VO
 */
public class StudentVO
		extends DefaultVO implements HrdStdSearchable, HrdStdSendable {

	private static final long	serialVersionUID	= -1000707904140866708L;

	private String  stdNo;
	private String  crsCreCd;
	private String  crsCreNm;
	private Integer declsNo;
	private String  userNo;
	private String  enrlSts;
	private String  enrlStartDttm;
	private String  enrlEndDttm;
	private String  auditEndDttm;
	private String  enrlAplcDttm;
	private String  enrlCancelDttm;
	private String  enrlCertDttm;
	private String  enrlCpltDttm;
	private Integer eduNo;
	private String  cpltNo;
	private Integer getCredit;
	private String  stayAplcYn;
	private String  paymMthdCd;
	private String  paymBankNm;
	private String  paymMthdNm;
	private Integer paymPrice;
	private String  paymNo;
	private String  paymStsCd;
	private String  paymStsNm;
	private String  paymDttm;
	private String  ueinsAplcYn;
	private String  ueinsAplyYn;
	private Integer refundPrice;
	private String  docReceiptYn;
	private String  docReceiptNo;
	private String  receiptDt;
	private String  receiptUserNm;
	private Integer repayPrice;
	private String  repayMthdCd;
	private String  repayMthdNm;
	private String  repayStsCd;
	private String  repayStsNm;
	private String  repayBankNm;
	private String  repayUserNm;
	private String  repayAcntNo;
	private String  repayReason;
	private String  repayMemo;
	private String  repayReqDttm;
	private String  repayDttm;
	private String  compNm;
	private String  bizzNo;
	private String  deptNm;
	private String  areaCd;
	private String  orgCd;
	private String  empNo;

	private String	homePostNo;
	private String	homeAddr;
	private String	homePhoneno;
	private String	homePhonenoF;
	private String	homePhonenoM;
	private String	homePhonenoL;
	private String	mobileNo;
	private String	mobileNoF;
	private String	mobileNoM;
	private String	mobileNoL;
	private String	areaNm;
	private String	jobDiv;
	private String	jobDivNm;
	private String	jobCtgr;
	private String	jobCtgrNm;
	private String	deptCd;
	private String	deptOrgNm;
	private String	deptDeptNm;
	private String  compFaxNo;

	private String	posngCd;
	private String	posngNm;
	private String	posnNm;
	private String	dutyNm;
	private String	chrgSbjCd;
	private String	chrgSbjNm;
	private String	orgNm;
	private String	orgPostNo;
	private String	orgAddr;
	private String	orgPhoneno;
	private String	orgPhonenoF;
	private String	orgPhonenoM;
	private String	orgPhonenoL;
	private String	faxNo;
	private String	faxNoF;
	private String	faxNoM;
	private String	faxNoL;
	private String	trainingNomiNo;
	private int		totRatio;
	private int		totBmkTime;
	private String	vstnorPurp;
	private String	vstnorArea;
	private String	vstnorPurpNm;
	private String	vstnorAreaNm;

	// -- 추가 조회용
	private String	enrlStsNm;
	private String	userNm;
	private String	userId;
	private String	ssn;

	private String	email;
	private String	birth;
	private String	sex;
	private String	sexNm;
	private String	userNmGana;
	private String	userNmEng;
	private String	sexCd;
	private String	userAreaCd;
	private String	userDivCd;
	private String	compPhoneno;
	private String	etcPhoneno;
	private String	compAddr1;
	private String	homeAddr1;
	private String	interestFieldCd;
	private String	disablilityYn;
	private String	memo;




	private int		prgrScore	= 0;
	private int		examScore	= 0;
	private int		asmtScore	= 0;
	private int		forumScore	= 0;
	private int		attdScore	= 0;
	private int		joinScore	= 0;
	private int		etcscore	= 0;
	private int		totScore	= 0;
	private double	avgScore	= 0;
	private double	convScore	= 0;

	private String	crsOperMthd;
	private String	crsOperType;
	private String	crsCd;
	private String	crsCtgrCd;
	private int		enrlDaycnt;
	private String  scoreEcltYn;
	private String	paymItgyDiv;
	private String	paymOidNo;
	private String	repayRsltCd;

	// -- 추가 검색용
	private String	dateSearchType;
	private String	startDate;
	private String	endDate;

	private String	stdYn;

	private String	enrlDuration;
	private String	prgrRatio;
	private String	prgrTime;

	private String	emailRecv;

	private String	sort1;
	private String	sort2;
	private String	sort3;

	private String	ssnYn;

	private int		enrlCnt;

	private String	schlTypeCd;
	private String	schlTypeNm;

	private String	stayAplcYn2;
	private String	stayYn;

	private String	regNo;
	private String	deptTxt;
	private String	dutyTxt;

	private int		reportCnt;
	private String	nameType;

	private String  schlTxt;
	private String  majorTxt;
	private String  ntnlCd;
	private String  entrcYear;
	private String  entrcMonth;

	private String sbjCd;//수강생 진도현황에 이용

	private String tranType;//sms email 쪽지에  이용

	private String reshAnsrYn;		//설문참여여부 (Y: 참여, N: 미참여)
	private String sbjFailYn;		//과락 여부 (Y:과락있음 , N:과락없음)

	private String errcode; // 프로시저 호출시 에러 코드 받는 용도
	private String searchMode;

	private String searchDelYn; //-- 삭제 처리 수강생 검색 포함.

	private String resh1JoinYn;
	private String resh2JoinYn;
	private String resh3JoinYn;
	private String resh1JoinDttm;
	private String resh2JoinDttm;
	private String resh3JoinDttm;

	private String crsRatio;
	
	private String creYear;
	private String creTerm;
	
	private Integer stdPrice;
	private Integer stdTotPrice;
	private String  refundYn;
	private String regIp;
	private String inDttm;
	private Integer inPrice;
	
	/* 기수 start */
	private String  crsYear;
	private String  crsTerm;
	private String  enrlAplcStartDttm;
	private String  enrlAplcEndDttm;
	private String  scoreOpenStartDttm;
	private String  scoreOpenEndDttm;
	private String  termEndDttm;
	private String  deptCds;
	private String  deptNms;
	private String  crsNm;
	/* 기수 end */
	
	//이니시스 추가
	private String tid;
	private String payMethod;
	private String totPrice;
	private String moid;
	private String applDate;
	private String applTime;
	private String cardNum;
	private String applNum;
	private String eventCode;
	private String cardQuota;
	private String cardInterest;
	private String interestFreeYn;
	private String cardPoint;
	private String cardCode;
	private String cardCropFlag;
	private String cardCheckFlag;
	private String cardPrtcCode;
	private String cardBankcode;
	private String cardSrcCode;
	
	//이니시스 실시간 계좌이체
	private String acctBankCode;
	private String cshrResultCode;
	private String cshrType;
	private String acctName;

	private String cardCodeNm;
	private String acctBankCodeNm;
	
	
	//이니시스 가상계좌
	private String vactNum;
	private String vactBankCode;
	private String vactBankName;
	private String vactName;
	private String vactInputName;
	private String vactDate;
	private String vactTime;
	
	private String deviceType;
	
	//수강신청관리 필드
	private int stuCnt = 0;//신청현황
	private int stuECnt = 0;//대기 인원
	private int stuNCnt = 0;//취소인원
	private int stuSCnt = 0;//수강중 인원
	private int stuFCnt = 0;//수료취소 인원
	private int stuCCnt = 0;//수료완료 인원
	private int payCnt = 0;//결제현황
	private int vactCnt = 0;//가상계좌(무통장입금)
	private int acctCnt = 0;//실시간계좌이체
	private int cardCnt = 0;//카드결제
	private int siteCnt = 0;//현장결제(엑셀업로드를 통한 등록)
	//엑셀업로드를 통한 결제 수단 추가
	private int bookmarkCnt = 0;//수강생 bookmark 갯수
	private int stdRepayPriceSum;
	private String refundTypeCd;
	
	private int attendCnt;
	private int alterCnt;
	private int absenceCnt;

	private String searchEnrlSts;
	private String searchConfirmYn;
	
	private String sbjNm;
	private String ideUrl;
	
	private String[] stdNos;
	private String[] ideUrls;

	
	private List<String> stdNoList = new ArrayList<>();	//SyncAspect에 PK 넘기기 위한 용도
	
	private List<BookmarkVO> bookmarkList = new ArrayList<>();
	
	/*자격증 과정관련 추가*/
	private String creOperTypeCd;	//개설과정 타입 확인용
	private String certAplcDttm;
	private String certPassYn;
	private int certScore;
	private String certSts;
	private String certStsNm;
	private String certFailReason;
	
	public StudentVO() {};
	
	public StudentVO(HrdStdSearchable target) {
		super();
		this.stdNo = target.callHrdStdNo();
	}
	
	public StudentVO(String stdNo) {
		this.stdNo = stdNo;
	}

	public void setAddRefund(String stdNo, String enrlSts, Integer repayPrice, String repayStsCd, String repayBankNm,
			String repayUserNm, String repayAcntNo, String repayReason) {
		this.stdNo = stdNo;
		this.enrlSts = enrlSts;
		this.repayPrice = repayPrice;
		this.repayStsCd = repayStsCd;
		this.repayBankNm = repayBankNm;
		this.repayUserNm = repayUserNm;
		this.repayAcntNo = repayAcntNo;
		this.repayReason = repayReason;
	}

	public int getStdRepayPriceSum() {
		return stdRepayPriceSum;
	}

	public void setStdRepayPriceSum(int stdRepayPriceSum) {
		this.stdRepayPriceSum = stdRepayPriceSum;
	}

	public int getBookmarkCnt() {
		return bookmarkCnt;
	}

	public void setBookmarkCnt(int bookmarkCnt) {
		this.bookmarkCnt = bookmarkCnt;
	}

	public Integer getStdTotPrice() {
		return stdTotPrice;
	}

	public void setStdTotPrice(Integer stdTotPrice) {
		this.stdTotPrice = stdTotPrice;
	}

	public String getSearchConfirmYn() {
		return searchConfirmYn;
	}

	public void setSearchConfirmYn(String searchConfirmYn) {
		this.searchConfirmYn = searchConfirmYn;
	}

	public String getCardCodeNm() {
		return cardCodeNm;
	}

	public void setCardCodeNm(String cardCodeNm) {
		this.cardCodeNm = cardCodeNm;
	}

	public String getAcctBankCodeNm() {
		return acctBankCodeNm;
	}

	public void setAcctBankCodeNm(String acctBankCodeNm) {
		this.acctBankCodeNm = acctBankCodeNm;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(String totPrice) {
		this.totPrice = totPrice;
	}

	public String getMoid() {
		return moid;
	}

	public void setMoid(String moid) {
		this.moid = moid;
	}

	public String getApplDate() {
		return applDate;
	}

	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	public String getApplTime() {
		return applTime;
	}

	public void setApplTime(String applTime) {
		this.applTime = applTime;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getApplNum() {
		return applNum;
	}

	public void setApplNum(String applNum) {
		this.applNum = applNum;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getCardQuota() {
		return cardQuota;
	}

	public void setCardQuota(String cardQuota) {
		this.cardQuota = cardQuota;
	}

	public String getCardInterest() {
		return cardInterest;
	}

	public void setCardInterest(String cardInterest) {
		this.cardInterest = cardInterest;
	}

	public String getInterestFreeYn() {
		return interestFreeYn;
	}

	public void setInterestFreeYn(String interestFreeYn) {
		this.interestFreeYn = interestFreeYn;
	}

	public String getCardPoint() {
		return cardPoint;
	}

	public void setCardPoint(String cardPoint) {
		this.cardPoint = cardPoint;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardCropFlag() {
		return cardCropFlag;
	}

	public void setCardCropFlag(String cardCropFlag) {
		this.cardCropFlag = cardCropFlag;
	}

	public String getCardCheckFlag() {
		return cardCheckFlag;
	}

	public void setCardCheckFlag(String cardCheckFlag) {
		this.cardCheckFlag = cardCheckFlag;
	}

	public String getCardPrtcCode() {
		return cardPrtcCode;
	}

	public void setCardPrtcCode(String cardPrtcCode) {
		this.cardPrtcCode = cardPrtcCode;
	}

	public String getCardBankcode() {
		return cardBankcode;
	}

	public void setCardBankcode(String cardBankcode) {
		this.cardBankcode = cardBankcode;
	}

	public String getCardSrcCode() {
		return cardSrcCode;
	}

	public void setCardSrcCode(String cardSrcCode) {
		this.cardSrcCode = cardSrcCode;
	}

	public String getAcctBankCode() {
		return acctBankCode;
	}

	public void setAcctBankCode(String acctBankCode) {
		this.acctBankCode = acctBankCode;
	}

	public String getCshrResultCode() {
		return cshrResultCode;
	}

	public void setCshrResultCode(String cshrResultCode) {
		this.cshrResultCode = cshrResultCode;
	}

	public String getCshrType() {
		return cshrType;
	}

	public void setCshrType(String cshrType) {
		this.cshrType = cshrType;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getSearchEnrlSts() {
		return searchEnrlSts;
	}

	public void setSearchEnrlSts(String searchEnrlSts) {
		this.searchEnrlSts = searchEnrlSts;
	}

	public int getSiteCnt() {
		return siteCnt;
	}

	public void setSiteCnt(int siteCnt) {
		this.siteCnt = siteCnt;
	}

	public String getSbjNm() {
		return sbjNm;
	}

	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}

	public int getStuCnt() {
		return stuCnt;
	}

	public void setStuCnt(int stuCnt) {
		this.stuCnt = stuCnt;
	}

	public int getStuECnt() {
		return stuECnt;
	}

	public void setStuECnt(int stuECnt) {
		this.stuECnt = stuECnt;
	}

	public int getStuNCnt() {
		return stuNCnt;
	}

	public void setStuNCnt(int stuNCnt) {
		this.stuNCnt = stuNCnt;
	}

	public int getStuSCnt() {
		return stuSCnt;
	}

	public void setStuSCnt(int stuSCnt) {
		this.stuSCnt = stuSCnt;
	}

	public int getStuFCnt() {
		return stuFCnt;
	}

	public void setStuFCnt(int stuFCnt) {
		this.stuFCnt = stuFCnt;
	}

	public int getStuCCnt() {
		return stuCCnt;
	}

	public void setStuCCnt(int stuCCnt) {
		this.stuCCnt = stuCCnt;
	}

	public int getPayCnt() {
		return payCnt;
	}

	public void setPayCnt(int payCnt) {
		this.payCnt = payCnt;
	}

	public int getVactCnt() {
		return vactCnt;
	}

	public void setVactCnt(int vactCnt) {
		this.vactCnt = vactCnt;
	}

	public int getAcctCnt() {
		return acctCnt;
	}

	public void setAcctCnt(int acctCnt) {
		this.acctCnt = acctCnt;
	}

	public int getCardCnt() {
		return cardCnt;
	}

	public void setCardCnt(int cardCnt) {
		this.cardCnt = cardCnt;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getInDttm() {
		return inDttm;
	}

	public void setInDttm(String inDttm) {
		this.inDttm = inDttm;
	}

	public Integer getInPrice() {
		return inPrice;
	}

	public void setInPrice(Integer inPrice) {
		this.inPrice = inPrice;
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

	public String getCrsNm() {
		return crsNm;
	}

	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}

	public String getVactNum() {
		return vactNum;
	}

	public void setVactNum(String vactNum) {
		this.vactNum = vactNum;
	}

	public String getVactBankCode() {
		return vactBankCode;
	}

	public void setVactBankCode(String vactBankCode) {
		this.vactBankCode = vactBankCode;
	}

	public String getVactBankName() {
		return vactBankName;
	}

	public void setVactBankName(String vactBankName) {
		this.vactBankName = vactBankName;
	}

	public String getVactName() {
		return vactName;
	}

	public void setVactName(String vactName) {
		this.vactName = vactName;
	}

	public String getVactInputName() {
		return vactInputName;
	}

	public void setVactInputName(String vactInputName) {
		this.vactInputName = vactInputName;
	}

	public String getVactDate() {
		return vactDate;
	}

	public void setVactDate(String vactDate) {
		this.vactDate = vactDate;
	}

	public String getVactTime() {
		return vactTime;
	}

	public void setVactTime(String vactTime) {
		this.vactTime = vactTime;
	}

	public Integer getStdPrice() {
		return stdPrice;
	}

	public void setStdPrice(Integer stdPrice) {
		this.stdPrice = stdPrice;
	}

	public String getDeptTxt() {
		return deptTxt;
	}

	public void setDeptTxt(String deptTxt) {
		this.deptTxt = deptTxt;
	}


	public String getSchlTypeCd() {
		return this.schlTypeCd;
	}


	public void setSchlTypeCd(String schlTypeCd) {
		this.schlTypeCd = schlTypeCd;
	}


	public String getSchlTypeNm() {
		return this.schlTypeNm;
	}


	public void setSchlTypeNm(String schlTypeNm) {
		this.schlTypeNm = schlTypeNm;
	}

	public int getEnrlCnt() {
		return this.enrlCnt;
	}

	public void setEnrlCnt(int enrlCnt) {
		this.enrlCnt = enrlCnt;
	}

	public String getDateSearchType() {
		return this.dateSearchType;
	}

	public void setDateSearchType(String dateSearchType) {
		this.dateSearchType = dateSearchType;
	}


	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSsnYn() {
		return this.ssnYn;
	}

	public void setSsnYn(String ssnYn) {
		this.ssnYn = ssnYn;
	}

	public String getVstnorPurpNm() {
		return this.vstnorPurpNm;
	}

	public void setVstnorPurpNm(String vstnorPurpNm) {
		this.vstnorPurpNm = vstnorPurpNm;
	}

	public String getVstnorAreaNm() {
		return this.vstnorAreaNm;
	}

	public void setVstnorAreaNm(String vstnorAreaNm) {
		this.vstnorAreaNm = vstnorAreaNm;
	}

	public String getVstnorPurp() {
		return this.vstnorPurp;
	}

	public void setVstnorPurp(String vstnorPurp) {
		this.vstnorPurp = vstnorPurp;
	}

	public String getVstnorArea() {
		return this.vstnorArea;
	}

	public void setVstnorArea(String vstnorArea) {
		this.vstnorArea = vstnorArea;
	}

	public String getSort1() {
		return this.sort1;
	}

	public void setSort1(String sort1) {
		this.sort1 = sort1;
	}

	public String getSort2() {
		return this.sort2;
	}

	public void setSort2(String sort2) {
		this.sort2 = sort2;
	}

	public String getSort3() {
		return this.sort3;
	}

	public void setSort3(String sort3) {
		this.sort3 = sort3;
	}

	/**
	 * @return the trainingNomiNo
	 */
	public String getTrainingNomiNo() {
		return this.trainingNomiNo;
	}

	/**
	 * @param trainingNomiNo the trainingNomiNo to set
	 */
	public void setTrainingNomiNo(String trainingNomiNo) {
		this.trainingNomiNo = trainingNomiNo;
	}

	/**
	 * @return the homePhonenoF
	 */
	public String getHomePhonenoF() {
		return this.homePhonenoF;
	}

	/**
	 * @return the emailRecv
	 */
	public String getEmailRecv() {
		return this.emailRecv;
	}

	/**
	 * @param emailRecv the emailRecv to set
	 */
	public void setEmailRecv(String emailRecv) {
		this.emailRecv = emailRecv;
	}

	/**
	 * @param homePhonenoF the homePhonenoF to set
	 */
	public void setHomePhonenoF(String homePhonenoF) {
		this.homePhonenoF = homePhonenoF;
	}

	/**
	 * @return the homePhonenoM
	 */
	public String getHomePhonenoM() {
		return this.homePhonenoM;
	}

	/**
	 * @param homePhonenoM the homePhonenoM to set
	 */
	public void setHomePhonenoM(String homePhonenoM) {
		this.homePhonenoM = homePhonenoM;
	}

	/**
	 * @return the homePhonenoL
	 */
	public String getHomePhonenoL() {
		return this.homePhonenoL;
	}

	/**
	 * @param homePhonenoL the homePhonenoL to set
	 */
	public void setHomePhonenoL(String homePhonenoL) {
		this.homePhonenoL = homePhonenoL;
	}

	/**
	 * @return the mobileNoF
	 */
	public String getMobileNoF() {
		return this.mobileNoF;
	}

	/**
	 * @param mobileNoF the mobileNoF to set
	 */
	public void setMobileNoF(String mobileNoF) {
		this.mobileNoF = mobileNoF;
	}

	/**
	 * @return the mobileNoM
	 */
	public String getMobileNoM() {
		return this.mobileNoM;
	}

	/**
	 * @param mobileNoM the mobileNoM to set
	 */
	public void setMobileNoM(String mobileNoM) {
		this.mobileNoM = mobileNoM;
	}

	/**
	 * @return the mobileNoL
	 */
	public String getMobileNoL() {
		return this.mobileNoL;
	}

	/**
	 * @param mobileNoL the mobileNoL to set
	 */
	public void setMobileNoL(String mobileNoL) {
		this.mobileNoL = mobileNoL;
	}

	/**
	 * @return the orgPhonenoF
	 */
	public String getOrgPhonenoF() {
		return this.orgPhonenoF;
	}

	/**
	 * @param orgPhonenoF the orgPhonenoF to set
	 */
	public void setOrgPhonenoF(String orgPhonenoF) {
		this.orgPhonenoF = orgPhonenoF;
	}

	/**
	 * @return the orgPhonenoM
	 */
	public String getOrgPhonenoM() {
		return this.orgPhonenoM;
	}

	/**
	 * @param orgPhonenoM the orgPhonenoM to set
	 */
	public void setOrgPhonenoM(String orgPhonenoM) {
		this.orgPhonenoM = orgPhonenoM;
	}

	/**
	 * @return the orgPhonenoL
	 */
	public String getOrgPhonenoL() {
		return this.orgPhonenoL;
	}

	/**
	 * @param orgPhonenoL the orgPhonenoL to set
	 */
	public void setOrgPhonenoL(String orgPhonenoL) {
		this.orgPhonenoL = orgPhonenoL;
	}

	/**
	 * @return the faxNoF
	 */
	public String getFaxNoF() {
		return this.faxNoF;
	}

	/**
	 * @param faxNoF the faxNoF to set
	 */
	public void setFaxNoF(String faxNoF) {
		this.faxNoF = faxNoF;
	}

	/**
	 * @return the faxNoM
	 */
	public String getFaxNoM() {
		return this.faxNoM;
	}

	/**
	 * @param faxNoM the faxNoM to set
	 */
	public void setFaxNoM(String faxNoM) {
		this.faxNoM = faxNoM;
	}

	/**
	 * @return the faxNoL
	 */
	public String getFaxNoL() {
		return this.faxNoL;
	}

	/**
	 * @param faxNoL the faxNoL to set
	 */
	public void setFaxNoL(String faxNoL) {
		this.faxNoL = faxNoL;
	}

	/**
	 * @return the crsCd
	 */
	public String getCrsCd() {
		return this.crsCd;
	}

	/**
	 * @param crsCd the crsCd to set
	 */
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}

	/**
	 * @return the crsCtgrCd
	 */
	public String getCrsCtgrCd() {
		return this.crsCtgrCd;
	}

	/**
	 * @param crsCtgrCd the crsCtgrCd to set
	 */
	public void setCrsCtgrCd(String crsCtgrCd) {
		this.crsCtgrCd = crsCtgrCd;
	}

	/**
	 * @return the enrlDuration
	 */
	public String getEnrlDuration() {
		return this.enrlDuration;
	}

	/**
	 * @param enrlDuration the enrlDuration to set
	 */
	public void setEnrlDuration(String enrlDuration) {
		this.enrlDuration = enrlDuration;
	}

	/**
	 * @return the prgrRatio
	 */
	public String getPrgrRatio() {
		return this.prgrRatio;
	}

	/**
	 * @param prgrRatio the prgrRatio to set
	 */
	public void setPrgrRatio(String prgrRatio) {
		this.prgrRatio = prgrRatio;
	}

	/**
	 * @return the prgrTime
	 */
	public String getPrgrTime() {
		return this.prgrTime;
	}

	/**
	 * @param prgrTime the prgrTime to set
	 */
	public void setPrgrTime(String prgrTime) {
		this.prgrTime = prgrTime;
	}

	/**
	 * @return the jobCtgrNm
	 */
	public String getJobCtgrNm() {
		return this.jobCtgrNm;
	}

	/**
	 * @return the stdYn
	 */
	public String getStdYn() {
		return this.stdYn;
	}

	/**
	 * @param stdYn the stdYn to set
	 */
	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}

	/**
	 * @param jobCtgrNm the jobCtgrNm to set
	 */
	public void setJobCtgrNm(String jobCtgrNm) {
		this.jobCtgrNm = jobCtgrNm;
	}

	/**
	 * @return the jobDivNm
	 */
	public String getJobDivNm() {
		return this.jobDivNm;
	}

	/**
	 * @param jobDivNm the jobDivNm to set
	 */
	public void setJobDivNm(String jobDivNm) {
		this.jobDivNm = jobDivNm;
	}

	/**
	 * @return the posnNm
	 */
	public String getPosnNm() {
		return this.posnNm;
	}

	/**
	 * @param posnNm the posnNm to set
	 */
	public void setPosnNm(String posnNm) {
		this.posnNm = posnNm;
	}

	/**
	 * @return the areaNm
	 */
	public String getAreaNm() {
		return this.areaNm;
	}

	/**
	 * @param areaNm the areaNm to set
	 */
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}

	/**
	 * @return the stdNo
	 */
	public String getStdNo() {
		return this.stdNo;
	}

	/**
	 * @param stdNo the stdNo to set
	 */
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	/**
	 * @return the crsCreCd
	 */
	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	/**
	 * @param crsCreCd the crsCreCd to set
	 */
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return this.userNo;
	}

	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return the enrlSts
	 */
	public String getEnrlSts() {
		return this.enrlSts;
	}

	/**
	 * @param enrlSts the enrlSts to set
	 */
	public void setEnrlSts(String enrlSts) {
		this.enrlSts = enrlSts;
	}

	/**
	 * @return the enrlStartDttm
	 */
	public String getEnrlStartDttm() {
		return this.enrlStartDttm;
	}

	/**
	 * @param enrlStartDttm the enrlStartDttm to set
	 */
	public void setEnrlStartDttm(String enrlStartDttm) {
		this.enrlStartDttm = enrlStartDttm;
	}

	/**
	 * @return the enrlEndDttm
	 */
	public String getEnrlEndDttm() {
		return this.enrlEndDttm;
	}

	/**
	 * @param enrlEndDttm the enrlEndDttm to set
	 */
	public void setEnrlEndDttm(String enrlEndDttm) {
		this.enrlEndDttm = enrlEndDttm;
	}

	/**
	 * @return the auditEndDttm
	 */
	public String getAuditEndDttm() {
		return this.auditEndDttm;
	}

	/**
	 * @param auditEndDttm the auditEndDttm to set
	 */
	public void setAuditEndDttm(String auditEndDttm) {
		this.auditEndDttm = auditEndDttm;
	}

	/**
	 * @return the enrlAplcDttm
	 */
	public String getEnrlAplcDttm() {
		return this.enrlAplcDttm;
	}

	/**
	 * @param enrlAplcDttm the enrlAplcDttm to set
	 */
	public void setEnrlAplcDttm(String enrlAplcDttm) {
		this.enrlAplcDttm = enrlAplcDttm;
	}

	/**
	 * @return the enrlCancelDttm
	 */
	public String getEnrlCancelDttm() {
		return this.enrlCancelDttm;
	}

	/**
	 * @param enrlCancelDttm the enrlCancelDttm to set
	 */
	public void setEnrlCancelDttm(String enrlCancelDttm) {
		this.enrlCancelDttm = enrlCancelDttm;
	}

	/**
	 * @return the enrlCertDttm
	 */
	public String getEnrlCertDttm() {
		return this.enrlCertDttm;
	}

	/**
	 * @param enrlCertDttm the enrlCertDttm to set
	 */
	public void setEnrlCertDttm(String enrlCertDttm) {
		this.enrlCertDttm = enrlCertDttm;
	}

	/**
	 * @return the enrlCpltDttm
	 */
	public String getEnrlCpltDttm() {
		return this.enrlCpltDttm;
	}

	/**
	 * @param enrlCpltDttm the enrlCpltDttm to set
	 */
	public void setEnrlCpltDttm(String enrlCpltDttm) {
		this.enrlCpltDttm = enrlCpltDttm;
	}

	/**
	 * @return the eduNo
	 */
	public Integer getEduNo() {
		return this.eduNo;
	}

	/**
	 * @param eduNo the eduNo to set
	 */
	public void setEduNo(Integer eduNo) {
		this.eduNo = eduNo;
	}

	/**
	 * @return the cpltNo
	 */
	public String getCpltNo() {
		return this.cpltNo;
	}

	/**
	 * @param cpltNo the cpltNo to set
	 */
	public void setCpltNo(String cpltNo) {
		this.cpltNo = cpltNo;
	}


	/**
	 * @return the homePostNo
	 */
	public String getHomePostNo() {
		return this.homePostNo;
	}

	/**
	 * @param homePostNo the homePostNo to set
	 */
	public void setHomePostNo(String homePostNo) {
		this.homePostNo = homePostNo;
	}

	/**
	 * @return the homeAddr
	 */
	public String getHomeAddr() {
		return this.homeAddr;
	}

	/**
	 * @param homeAddr the homeAddr to set
	 */
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	/**
	 * @return the homePhoneno
	 */
	public String getHomePhoneno() {
		return this.homePhoneno;
	}

	/**
	 * @param homePhoneno the homePhoneno to set
	 */
	public void setHomePhoneno(String homePhoneno) {
		this.homePhoneno = homePhoneno;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return this.mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the areaCd
	 */
	public String getAreaCd() {
		return this.areaCd;
	}

	/**
	 * @param areaCd the areaCd to set
	 */
	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	/**
	 * @return the jobDiv
	 */
	public String getJobDiv() {
		return this.jobDiv;
	}

	/**
	 * @param jobDiv the jobDiv to set
	 */
	public void setJobDiv(String jobDiv) {
		this.jobDiv = jobDiv;
	}

	/**
	 * @return the jobCtgr
	 */
	public String getJobCtgr() {
		return this.jobCtgr;
	}

	/**
	 * @param jobCtgr the jobCtgr to set
	 */
	public void setJobCtgr(String jobCtgr) {
		this.jobCtgr = jobCtgr;
	}

	/**
	 * @return the deptOrgNm
	 */
	public String getDeptOrgNm() {
		return this.deptOrgNm;
	}

	/**
	 * @param deptOrgNm the deptOrgNm to set
	 */
	public void setDeptOrgNm(String deptOrgNm) {
		this.deptOrgNm = deptOrgNm;
	}

	/**
	 * @return the deptDeptNm
	 */
	public String getDeptDeptNm() {
		return this.deptDeptNm;
	}

	/**
	 * @param deptDeptNm the deptDeptNm to set
	 */
	public void setDeptDeptNm(String deptDeptNm) {
		this.deptDeptNm = deptDeptNm;
	}

	/**
	 * @return the posngCd
	 */
	public String getPosngCd() {
		return this.posngCd;
	}

	/**
	 * @param posngCd the posngCd to set
	 */
	public void setPosngCd(String posngCd) {
		this.posngCd = posngCd;
	}

	/**
	 * @return the posngNm
	 */
	public String getPosngNm() {
		return this.posngNm;
	}

	/**
	 * @param posngNm the posngNm to set
	 */
	public void setPosngNm(String posngNm) {
		this.posngNm = posngNm;
	}

	/**
	 * @return the dutyNm
	 */
	public String getDutyNm() {
		return this.dutyNm;
	}

	/**
	 * @param dutyNm the dutyNm to set
	 */
	public void setDutyNm(String dutyNm) {
		this.dutyNm = dutyNm;
	}

	/**
	 * @return the chrgSbjCd
	 */
	public String getChrgSbjCd() {
		return this.chrgSbjCd;
	}

	/**
	 * @param chrgSbjCd the chrgSbjCd to set
	 */
	public void setChrgSbjCd(String chrgSbjCd) {
		this.chrgSbjCd = chrgSbjCd;
	}

	/**
	 * @return the chrgSbjNm
	 */
	public String getChrgSbjNm() {
		return this.chrgSbjNm;
	}

	/**
	 * @param chrgSbjNm the chrgSbjNm to set
	 */
	public void setChrgSbjNm(String chrgSbjNm) {
		this.chrgSbjNm = chrgSbjNm;
	}

	/**
	 * @return the orgNm
	 */
	public String getOrgNm() {
		return this.orgNm;
	}

	/**
	 * @param orgNm the orgNm to set
	 */
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	/**
	 * @return the orgPostNo
	 */
	public String getOrgPostNo() {
		return this.orgPostNo;
	}

	/**
	 * @param orgPostNo the orgPostNo to set
	 */
	public void setOrgPostNo(String orgPostNo) {
		this.orgPostNo = orgPostNo;
	}

	/**
	 * @return the orgAddr
	 */
	public String getOrgAddr() {
		return this.orgAddr;
	}

	/**
	 * @param orgAddr the orgAddr to set
	 */
	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}

	/**
	 * @return the orgPhoneno
	 */
	public String getOrgPhoneno() {
		return this.orgPhoneno;
	}

	/**
	 * @param orgPhoneno the orgPhoneno to set
	 */
	public void setOrgPhoneno(String orgPhoneno) {
		this.orgPhoneno = orgPhoneno;
	}

	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return this.faxNo;
	}

	/**
	 * @param faxNo the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * @return the enrlStsNm
	 */
	public String getEnrlStsNm() {
		return this.enrlStsNm;
	}

	/**
	 * @param enrlStsNm the enrlStsNm to set
	 */
	public void setEnrlStsNm(String enrlStsNm) {
		this.enrlStsNm = enrlStsNm;
	}

	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return this.userNm;
	}

	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return this.birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the prgrScore
	 */
	public int getPrgrScore() {
		return this.prgrScore;
	}

	/**
	 * @param prgrScore the prgrScore to set
	 */
	public void setPrgrScore(int prgrScore) {
		this.prgrScore = prgrScore;
	}

	/**
	 * @return the examScore
	 */
	public int getExamScore() {
		return this.examScore;
	}

	/**
	 * @param examScore the examScore to set
	 */
	public void setExamScore(int examScore) {
		this.examScore = examScore;
	}

	/**
	 * @return the asmtScore
	 */
	public int getAsmtScore() {
		return this.asmtScore;
	}

	/**
	 * @param asmtScore the asmtScore to set
	 */
	public void setAsmtScore(int asmtScore) {
		this.asmtScore = asmtScore;
	}

	/**
	 * @return the forumScore
	 */
	public int getForumScore() {
		return this.forumScore;
	}

	/**
	 * @param forumScore the forumScore to set
	 */
	public void setForumScore(int forumScore) {
		this.forumScore = forumScore;
	}

	/**
	 * @return the attdScore
	 */
	public int getAttdScore() {
		return this.attdScore;
	}

	/**
	 * @param attdScore the attdScore to set
	 */
	public void setAttdScore(int attdScore) {
		this.attdScore = attdScore;
	}

	/**
	 * @return the joinScore
	 */
	public int getJoinScore() {
		return this.joinScore;
	}

	/**
	 * @param joinScore the joinScore to set
	 */
	public void setJoinScore(int joinScore) {
		this.joinScore = joinScore;
	}

	/**
	 * @return the etcscore
	 */
	public int getEtcscore() {
		return this.etcscore;
	}

	/**
	 * @param etcscore the etcscore to set
	 */
	public void setEtcscore(int etcscore) {
		this.etcscore = etcscore;
	}

	/**
	 * @return the totScore
	 */
	public int getTotScore() {
		return this.totScore;
	}

	/**
	 * @param totScore the totScore to set
	 */
	public void setTotScore(int totScore) {
		this.totScore = totScore;
	}

	/**
	 * @return the convScore
	 */
	public double getConvScore() {
		return this.convScore;
	}

	/**
	 * @param convScore the convScore to set
	 */
	public void setConvScore(double convScore) {
		this.convScore = convScore;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the crsOperMthd
	 */
	public String getCrsOperMthd() {
		return this.crsOperMthd;
	}

	/**
	 * @param crsOperMthd the crsOperMthd to set
	 */
	public void setCrsOperMthd(String crsOperMthd) {
		this.crsOperMthd = crsOperMthd;
	}

	/**
	 * @return the enrlDaycnt
	 */
	public int getEnrlDaycnt() {
		return this.enrlDaycnt;
	}

	/**
	 * @param enrlDaycnt the enrlDaycnt to set
	 */
	public void setEnrlDaycnt(int enrlDaycnt) {
		this.enrlDaycnt = enrlDaycnt;
	}

	/**
	 * @return the crsOperType
	 */
	public String getCrsOperType() {
		return this.crsOperType;
	}

	/**
	 * @param crsOperType the crsOperType to set
	 */
	public void setCrsOperType(String crsOperType) {
		this.crsOperType = crsOperType;
	}

	/**
	 * @return the deptCd
	 */
	public String getDeptCd() {
		return this.deptCd;
	}

	/**
	 * @param deptCd the deptCd to set
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public int getTotRatio() {
		return this.totRatio;
	}

	public void setTotRatio(int totRatio) {
		this.totRatio = totRatio;
	}

	public int getTotBmkTime() {
		return this.totBmkTime;
	}

	public void setTotBmkTime(int totBmkTime) {
		this.totBmkTime = totBmkTime;
	}


	public String getSex() {
		return this.sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getSexNm() {
		return this.sexNm;
	}



	public void setSexNm(String sexNm) {
		this.sexNm = sexNm;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentDTO [email=" + this.email + ", homeAddr=" + this.homeAddr + ", homePhoneno="
				+ this.homePhoneno + ", orgPhoneno=" + this.orgPhoneno + ", userId=" + this.userId
				+ ", userNm=" + this.userNm + ", userNo=" + this.userNo + "]";
	}


	public String getStayAplcYn() {
		return stayAplcYn;
	}


	public void setStayAplcYn(String stayAplcYn) {
		this.stayAplcYn = stayAplcYn;
	}


	public String getStayYn() {
		return stayYn;
	}


	public void setStayYn(String stayYn) {
		this.stayYn = stayYn;
	}


	public String getRegNo() {
		return regNo;
	}


	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getDutyTxt() {
		return dutyTxt;
	}


	public void setDutyTxt(String dutyTxt) {
		this.dutyTxt = dutyTxt;
	}

	public int getReportCnt() {
		return reportCnt;
	}


	public void setReportCnt(int reportCnt) {
		this.reportCnt = reportCnt;
	}


	public String getNameType() {
		return nameType;
	}


	public void setNameType(String nameType) {
		this.nameType = nameType;
	}


	public String getStayAplcYn2() {
		return stayAplcYn2;
	}


	public void setStayAplcYn2(String stayAplcYn2) {
		this.stayAplcYn2 = stayAplcYn2;
	}


	/**
	 * @return the schlTxt
	 */
	public String getSchlTxt() {
		return schlTxt;
	}


	/**
	 * @param schlTxt the schlTxt to set
	 */
	public void setSchlTxt(String schlTxt) {
		this.schlTxt = schlTxt;
	}


	/**
	 * @return the majorTxt
	 */
	public String getMajorTxt() {
		return majorTxt;
	}


	/**
	 * @param majorTxt the majorTxt to set
	 */
	public void setMajorTxt(String majorTxt) {
		this.majorTxt = majorTxt;
	}


	/**
	 * @return the ntnlCd
	 */
	public String getNtnlCd() {
		return ntnlCd;
	}


	/**
	 * @param ntnlCd the ntnlCd to set
	 */
	public void setNtnlCd(String ntnlCd) {
		this.ntnlCd = ntnlCd;
	}


	/**
	 * @return the entrcYear
	 */
	public String getEntrcYear() {
		return entrcYear;
	}


	/**
	 * @param entrcYear the entrcYear to set
	 */
	public void setEntrcYear(String entrcYear) {
		this.entrcYear = entrcYear;
	}


	/**
	 * @return the entrcMonth
	 */
	public String getEntrcMonth() {
		return entrcMonth;
	}

	/**
	 * @param entrcMonth the entrcMonth to set
	 */
	public void setEntrcMonth(String entrcMonth) {
		this.entrcMonth = entrcMonth;
	}

	public String getSbjCd() {
		return sbjCd;
	}

	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getReshAnsrYn() {
		return reshAnsrYn;
	}

	public void setReshAnsrYn(String reshAnsrYn) {
		this.reshAnsrYn = reshAnsrYn;
	}

	public String getSbjFailYn() {
		return sbjFailYn;
	}

	public void setSbjFailYn(String sbjFailYn) {
		this.sbjFailYn = sbjFailYn;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public Integer getDeclsNo() {
		return declsNo;
	}

	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}

	public String getPaymMthdCd() {
		return paymMthdCd;
	}

	public void setPaymMthdCd(String paymMthdCd) {
		this.paymMthdCd = paymMthdCd;
	}

	public Integer getPaymPrice() {
		return paymPrice;
	}

	public void setPaymPrice(Integer paymPrice) {
		this.paymPrice = paymPrice;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getBizzNo() {
		return bizzNo;
	}

	public void setBizzNo(String bizzNo) {
		this.bizzNo = bizzNo;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public void setGetCredit(Integer getCredit) {
		this.getCredit = getCredit;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public Integer getGetCredit() {
		return getCredit;
	}

	public String getPaymMthdNm() {
		return paymMthdNm;
	}

	public void setPaymMthdNm(String paymMthdNm) {
		this.paymMthdNm = paymMthdNm;
	}

	public String getPaymNo() {
		return paymNo;
	}

	public void setPaymNo(String paymNo) {
		this.paymNo = paymNo;
	}

	public String getUeinsAplcYn() {
		return ueinsAplcYn;
	}

	public void setUeinsAplcYn(String ueinsAplcYn) {
		this.ueinsAplcYn = ueinsAplcYn;
	}

	public String getUeinsAplyYn() {
		return ueinsAplyYn;
	}

	public void setUeinsAplyYn(String ueinsAplyYn) {
		this.ueinsAplyYn = ueinsAplyYn;
	}

	public Integer getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(Integer refundPrice) {
		this.refundPrice = refundPrice;
	}

	public String getDocReceiptYn() {
		return docReceiptYn;
	}

	public void setDocReceiptYn(String docReceiptYn) {
		this.docReceiptYn = docReceiptYn;
	}

	public String getDocReceiptNo() {
		return docReceiptNo;
	}

	public void setDocReceiptNo(String docReceiptNo) {
		this.docReceiptNo = docReceiptNo;
	}

	public String getReceiptDt() {
		return receiptDt;
	}

	public void setReceiptDt(String receiptDt) {
		this.receiptDt = receiptDt;
	}

	public String getReceiptUserNm() {
		return receiptUserNm;
	}

	public void setReceiptUserNm(String receiptUserNm) {
		this.receiptUserNm = receiptUserNm;
	}

	public String getPaymStsCd() {
		return paymStsCd;
	}

	public void setPaymStsCd(String paymStsCd) {
		this.paymStsCd = paymStsCd;
	}

	public String getPaymStsNm() {
		return paymStsNm;
	}

	public void setPaymStsNm(String paymStsNm) {
		this.paymStsNm = paymStsNm;
	}

	public String getPaymDttm() {
		return paymDttm;
	}

	public void setPaymDttm(String paymDttm) {
		this.paymDttm = paymDttm;
	}

	public String getRepayStsCd() {
		return repayStsCd;
	}

	public void setRepayStsCd(String repayStsCd) {
		this.repayStsCd = repayStsCd;
	}

	public String getRepayStsNm() {
		return repayStsNm;
	}

	public void setRepayStsNm(String repayStsNm) {
		this.repayStsNm = repayStsNm;
	}

	public String getRepayReqDttm() {
		return repayReqDttm;
	}

	public void setRepayReqDttm(String repayReqDttm) {
		this.repayReqDttm = repayReqDttm;
	}

	public String getRepayDttm() {
		return repayDttm;
	}

	public void setRepayDttm(String repayDttm) {
		this.repayDttm = repayDttm;
	}

	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}


	public String getSearchDelYn() {
		return searchDelYn;
	}


	public void setSearchDelYn(String searchDelYn) {
		this.searchDelYn = searchDelYn;
	}


	public String getScoreEcltYn() {
		return scoreEcltYn;
	}


	public void setScoreEcltYn(String scoreEcltYn) {
		this.scoreEcltYn = scoreEcltYn;
	}


	public String getPaymBankNm() {
		return paymBankNm;
	}


	public void setPaymBankNm(String paymBankNm) {
		this.paymBankNm = paymBankNm;
	}


	public Integer getRepayPrice() {
		return repayPrice;
	}


	public void setRepayPrice(Integer repayPrice) {
		this.repayPrice = repayPrice;
	}


	public String getRepayMthdCd() {
		return repayMthdCd;
	}


	public void setRepayMthdCd(String repayMthdCd) {
		this.repayMthdCd = repayMthdCd;
	}


	public String getRepayMthdNm() {
		return repayMthdNm;
	}


	public void setRepayMthdNm(String repayMthdNm) {
		this.repayMthdNm = repayMthdNm;
	}


	public String getRepayBankNm() {
		return repayBankNm;
	}


	public void setRepayBankNm(String repayBankNm) {
		this.repayBankNm = repayBankNm;
	}


	public String getRepayUserNm() {
		return repayUserNm;
	}


	public void setRepayUserNm(String repayUserNm) {
		this.repayUserNm = repayUserNm;
	}


	public String getRepayAcntNo() {
		return repayAcntNo;
	}


	public void setRepayAcntNo(String repayAcntNo) {
		this.repayAcntNo = repayAcntNo;
	}


	public String getRepayReason() {
		return repayReason;
	}


	public void setRepayReason(String repayReason) {
		this.repayReason = repayReason;
	}


	public String getPaymItgyDiv() {
		return paymItgyDiv;
	}


	public void setPaymItgyDiv(String paymItgyDiv) {
		this.paymItgyDiv = paymItgyDiv;
	}


	public String getPaymOidNo() {
		return paymOidNo;
	}


	public void setPaymOidNo(String paymOidNo) {
		this.paymOidNo = paymOidNo;
	}


	public String getRepayRsltCd() {
		return repayRsltCd;
	}


	public void setRepayRsltCd(String repayRsltCd) {
		this.repayRsltCd = repayRsltCd;
	}

	public String getCrsRatio() {
		return crsRatio;
	}


	public void setCrsRatio(String crsRatio) {
		this.crsRatio = crsRatio;
	}


	public String getResh1JoinYn() {
		return resh1JoinYn;
	}


	public void setResh1JoinYn(String resh1JoinYn) {
		this.resh1JoinYn = resh1JoinYn;
	}


	public String getResh2JoinYn() {
		return resh2JoinYn;
	}


	public void setResh2JoinYn(String resh2JoinYn) {
		this.resh2JoinYn = resh2JoinYn;
	}


	public String getResh3JoinYn() {
		return resh3JoinYn;
	}


	public void setResh3JoinYn(String resh3JoinYn) {
		this.resh3JoinYn = resh3JoinYn;
	}


	public String getResh1JoinDttm() {
		return resh1JoinDttm;
	}


	public void setResh1JoinDttm(String resh1JoinDttm) {
		this.resh1JoinDttm = resh1JoinDttm;
	}


	public String getResh2JoinDttm() {
		return resh2JoinDttm;
	}


	public void setResh2JoinDttm(String resh2JoinDttm) {
		this.resh2JoinDttm = resh2JoinDttm;
	}


	public String getResh3JoinDttm() {
		return resh3JoinDttm;
	}


	public void setResh3JoinDttm(String resh3JoinDttm) {
		this.resh3JoinDttm = resh3JoinDttm;
	}


	public String getCompFaxNo() {
		return compFaxNo;
	}


	public void setCompFaxNo(String compFaxNo) {
		this.compFaxNo = compFaxNo;
	}


	public String getUserNmGana() {
		return userNmGana;
	}


	public void setUserNmGana(String userNmGana) {
		this.userNmGana = userNmGana;
	}


	public String getUserNmEng() {
		return userNmEng;
	}


	public void setUserNmEng(String userNmEng) {
		this.userNmEng = userNmEng;
	}


	public String getSexCd() {
		return sexCd;
	}


	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}


	public String getUserAreaCd() {
		return userAreaCd;
	}


	public void setUserAreaCd(String userAreaCd) {
		this.userAreaCd = userAreaCd;
	}


	public String getUserDivCd() {
		return userDivCd;
	}


	public void setUserDivCd(String userDivCd) {
		this.userDivCd = userDivCd;
	}


	public String getCompPhoneno() {
		return compPhoneno;
	}


	public void setCompPhoneno(String compPhoneno) {
		this.compPhoneno = compPhoneno;
	}


	public String getEtcPhoneno() {
		return etcPhoneno;
	}


	public void setEtcPhoneno(String etcPhoneno) {
		this.etcPhoneno = etcPhoneno;
	}


	public String getCompAddr1() {
		return compAddr1;
	}


	public void setCompAddr1(String compAddr1) {
		this.compAddr1 = compAddr1;
	}


	public String getHomeAddr1() {
		return homeAddr1;
	}


	public void setHomeAddr1(String homeAddr1) {
		this.homeAddr1 = homeAddr1;
	}


	public String getInterestFieldCd() {
		return interestFieldCd;
	}


	public void setInterestFieldCd(String interestFieldCd) {
		this.interestFieldCd = interestFieldCd;
	}


	public String getDisablilityYn() {
		return disablilityYn;
	}


	public void setDisablilityYn(String disablilityYn) {
		this.disablilityYn = disablilityYn;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getCreYear() {
		return creYear;
	}


	public void setCreYear(String creYear) {
		this.creYear = creYear;
	}


	public String getCreTerm() {
		return creTerm;
	}


	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}


	public String getCrsCreNm() {
		return crsCreNm;
	}


	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}

	public List<String> getStdNoList() {
		return stdNoList;
	}

	public void setStdNoList(List<String> stdNoList) {
		this.stdNoList = stdNoList;
	}

	public String getRefundYn() {
		return refundYn;
	}

	public void setRefundYn(String refundYn) {
		this.refundYn = refundYn;
	}

	@Override
	public String callHrdStdNo() {
		return this.stdNo;
	}

	@Override
	public String callHrdUserAgentPk() {
		return this.userNo;
	}

	@Override
	public String callHrdCourseAgentPk() {
		return this.crsCd;
	}

	@Override
	public String callHrdClassAgentPk() {
		return this.crsCreCd;
	}

	@Override
	public Integer callHrdPassFlag() {
		if(!"C".equals(this.enrlSts)) return 0;
		if(this.cpltNo == null || "".equals(this.cpltNo)) return 0;
		return 1;
	}

	@Override
	public Integer callHrdAttendValidFlag() {
		if(this.enrlSts == null) return 0;
		if("SCF".contains(this.enrlSts)) return 1;
		return 0;
	}

	@Override
	public Integer callHrdEmpInsFlag() {
		if(!"Y".equals(this.refundYn)) return 0;
		if("1559293119".equals(this.deptCd)) return 4; // 내일배움카드 코드
		return 1;
	}

	public String getRepayMemo() {
		return repayMemo;
	}

	public void setRepayMemo(String repayMemo) {
		this.repayMemo = repayMemo;
	}

	public List<BookmarkVO> getBookmarkList() {
		return bookmarkList;
	}

	public void setBookmarkList(List<BookmarkVO> bookmarkList) {
		this.bookmarkList = bookmarkList;
	}
	
	public int getAttendCnt() {
		return attendCnt;
	}

	public void setAttendCnt(int attendCnt) {
		this.attendCnt = attendCnt;
	}

	public int getAlterCnt() {
		return alterCnt;
	}

	public void setAlterCnt(int alterCnt) {
		this.alterCnt = alterCnt;
	}

	public int getAbsenceCnt() {
		return absenceCnt;
	}

	public void setAbsenceCnt(int absenceCnt) {
		this.absenceCnt = absenceCnt;
	}	
	
	public void calcAttendCnt() {
		bookmarkList.forEach(this::offlineChkSts);
	}
	
	private void offlineChkSts(BookmarkVO bookmark) {
		AttendStatusCode stsCd = bookmark.getAttendStsCd();
		switch(stsCd) {
			case ATTEND:
				this.setAttendCnt(this.getAttendCnt() + 1);
				break;
			case ALTER :
				this.setAlterCnt(this.getAlterCnt() + 1);
				break;
			case ABSENCE :
				this.setAbsenceCnt(this.getAbsenceCnt() + 1);
				break;
			default :
				return;
		}
	}

	public String getRefundTypeCd() {
		return refundTypeCd;
	}

	public void setRefundTypeCd(String refundTypeCd) {
		this.refundTypeCd = refundTypeCd;
	}
	
	public String getCreOperTypeCd() {
		return creOperTypeCd;
	}

	public void setCreOperTypeCd(String creOperTypeCd) {
		this.creOperTypeCd = creOperTypeCd;
	}

	public String getCertAplcDttm() {
		return certAplcDttm;
	}

	public void setCertAplcDttm(String certAplcDttm) {
		this.certAplcDttm = certAplcDttm;
	}

	public String getCertPassYn() {
		return certPassYn;
	}

	public void setCertPassYn(String certPassYn) {
		this.certPassYn = certPassYn;
	}

	public int getCertScore() {
		return certScore;
	}

	public void setCertScore(int certScore) {
		this.certScore = certScore;
	}

	public String getCertSts() {
		return certSts;
	}

	public void setCertSts(String certSts) {
		this.certSts = certSts;
	}

	public String getCertStsNm() {
		return certStsNm;
	}

	public void setCertStsNm(String certStsNm) {
		this.certStsNm = certStsNm;
	}

	public String getCertFailReason() {
		return certFailReason;
	}

	public void setCertFailReason(String certFailReason) {
		this.certFailReason = certFailReason;
	}

	public String[] getStdNos() {
		return stdNos;
	}

	public void setStdNos(String[] stdNos) {
		this.stdNos = stdNos;
	}

	public String[] getIdeUrls() {
		return ideUrls;
	}

	public void setIdeUrls(String[] ideUrls) {
		this.ideUrls = ideUrls;
	}

	public String getIdeUrl() {
		return ideUrl;
	}

	public void setIdeUrl(String ideUrl) {
		this.ideUrl = ideUrl;
	}
	
	
	

}