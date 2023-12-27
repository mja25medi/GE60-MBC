package egovframework.edutrack.modules.student.payment.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.student.student.service.StudentVO;

public class PaymentVO  extends DefaultVO{

	//기존
	private String crsCreCd;
	private String userNo;
	private String regDttm;
	private String creTerm;
	
	//기존
	private String  paymMthdCd;
	private String  paymBankNm;
	private String  paymOidNo;
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
	private String  repayReqDttm;
	private String  repayDttm;
	private String  compNm;
	private String  bizzNo;
	private String  deptNm;
	private String  areaCd;
	private String  orgCd;
	private String  empNo;
	
	private String regIp;
	private String inDttm;
	private Integer inPrice;
	
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
	
	private String cardCodeNm;
	
	//이니시스 실시간 계좌이체
	private String acctBankCode;
	private String cshrResultCode;
	private String cshrType;
	private String acctName;
	
	//이니시스 가상계좌
	private String vactNum;
	private String vactBankCode;
	private String vactBankName;
	private String vactName;
	private String vactInputName;
	private String vactDate;
	private String vactTime;
	private String vactInputDttm;
	private String vactInputPrice;
	
	private String SearchMode;
	
	//개설과정
	private Integer eduPrice;
	private String crsCreNm;
	private String deptCd;
	private String  nopLimitYn;
	private Integer enrlNop;
	private int		stuCnt;
	private String 	sbjCd;
	private int		contentsCnt;	//컨텐츠개수
	
	//기수
	private String  crsYear;
	private String  crsTerm;
	private String  enrlAplcStartDttm;
	private String  enrlAplcEndDttm;
	private String  enrlStartDttm;
	private String  enrlEndDttm;
	private String  termEndDttm;
	
	//과정
	private String refundYn;
	private String crsSvcType;
	
	private String deviceType;//PC, MOBILE
	private List<StudentVO> studentList = new ArrayList<>();
	private List<String> stdNoList = new ArrayList<>();	//SyncAspect에 PK 넘기기 위한 용도
	
	// 회차코드 배열
	private String [] crsCreCds;
	private String crsOperType;
	
	public String getCrsOperType() {
		return crsOperType;
	}
	public void setCrsOperType(String crsOperType) {
		this.crsOperType = crsOperType;
	}
	public String[] getCrsCreCds() {
		return crsCreCds;
	}
	public void setCrsCreCds(String[] crsCreCds) {
		this.crsCreCds = crsCreCds;
	}
	
	public String getCrsSvcType() {
		return crsSvcType;
	}
	public void setCrsSvcType(String crsSvcType) {
		this.crsSvcType = crsSvcType;
	}
	public String getVactInputDttm() {
		return vactInputDttm;
	}
	public void setVactInputDttm(String vactInputDttm) {
		this.vactInputDttm = vactInputDttm;
	}
	public String getVactInputPrice() {
		return vactInputPrice;
	}
	public void setVactInputPrice(String vactInputPrice) {
		this.vactInputPrice = vactInputPrice;
	}
	public String getCardCodeNm() {
		return cardCodeNm;
	}
	public void setCardCodeNm(String cardCodeNm) {
		this.cardCodeNm = cardCodeNm;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getCardPoint() {
		return cardPoint;
	}
	public void setCardPoint(String cardPoint) {
		this.cardPoint = cardPoint;
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
	public List<StudentVO> getStudentList() {
		if (studentList == null)
			studentList = new ArrayList<>();
		return studentList;
	}
	public void setStudentList(List<StudentVO> studentList) {
		this.studentList = studentList;
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
	public String getCardSrcCode() {
		return cardSrcCode;
	}
	public void setCardSrcCode(String cardSrcCode) {
		this.cardSrcCode = cardSrcCode;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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
	public String getAcctBankCode() {
		return acctBankCode;
	}
	public void setAcctBankCode(String acctBankCode) {
		this.acctBankCode = acctBankCode;
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
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public String getInterestFreeYn() {
		return interestFreeYn;
	}
	public void setInterestFreeYn(String interestFreeYn) {
		this.interestFreeYn = interestFreeYn;
	}
	public String getPaymOidNo() {
		return paymOidNo;
	}
	public void setPaymOidNo(String paymOidNo) {
		this.paymOidNo = paymOidNo;
	}
	public String getApplNum() {
		return applNum;
	}
	public void setApplNum(String applNum) {
		this.applNum = applNum;
	}
	public String getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(String totPrice) {
		this.totPrice = totPrice;
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
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getCardInterest() {
		return cardInterest;
	}
	public void setCardInterest(String cardInterest) {
		this.cardInterest = cardInterest;
	}
	public String getCardQuota() {
		return cardQuota;
	}
	public void setCardQuota(String cardQuota) {
		this.cardQuota = cardQuota;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardBankcode() {
		return cardBankcode;
	}
	public void setCardBankcode(String cardBankcode) {
		this.cardBankcode = cardBankcode;
	}
	public String getSearchMode() {
		return SearchMode;
	}
	public void setSearchMode(String searchMode) {
		SearchMode = searchMode;
	}
	public int getStuCnt() {
		return stuCnt;
	}
	public void setStuCnt(int stuCnt) {
		this.stuCnt = stuCnt;
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
	public String getRefundYn() {
		return refundYn;
	}
	public void setRefundYn(String refundYn) {
		this.refundYn = refundYn;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public Integer getEduPrice() {
		return eduPrice;
	}
	public void setEduPrice(Integer eduPrice) {
		this.eduPrice = eduPrice;
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
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getPaymMthdCd() {
		return paymMthdCd;
	}
	public void setPaymMthdCd(String paymMthdCd) {
		this.paymMthdCd = paymMthdCd;
	}
	public String getPaymBankNm() {
		return paymBankNm;
	}
	public void setPaymBankNm(String paymBankNm) {
		this.paymBankNm = paymBankNm;
	}
	public String getPaymMthdNm() {
		return paymMthdNm;
	}
	public void setPaymMthdNm(String paymMthdNm) {
		this.paymMthdNm = paymMthdNm;
	}
	public Integer getPaymPrice() {
		return paymPrice;
	}
	public void setPaymPrice(Integer paymPrice) {
		this.paymPrice = paymPrice;
	}
	public String getPaymNo() {
		return paymNo;
	}
	public void setPaymNo(String paymNo) {
		this.paymNo = paymNo;
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
	public String getAreaCd() {
		return areaCd;
	}
	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
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
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public int getContentsCnt() {
		return contentsCnt;
	}
	public void setContentsCnt(int contentsCnt) {
		this.contentsCnt = contentsCnt;
	}
	public List<String> getStdNoList() {
		return stdNoList;
	}
	public void setStdNoList(List<String> stdNoList) {
		this.stdNoList = stdNoList;
	}
	public String getCreTerm() {
		return creTerm;
	}
	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}
	
}
