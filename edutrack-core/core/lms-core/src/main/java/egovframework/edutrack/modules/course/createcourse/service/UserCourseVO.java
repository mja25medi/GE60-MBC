/**
 *
 */
package egovframework.edutrack.modules.course.createcourse.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 학습자 과정, 교수자 과정
 */
public class UserCourseVO extends DefaultVO {
	/**
	 *
	 */
	private static final long	serialVersionUID	= -2640382252804745971L;
	private String 	crsCreCd;
	private String 	crsCd;
	private String 	sbjCd;
	private String 	crsYear;
	private String 	crsTerm;
	private String 	creYear;
	private String 	creTerm;
	private String 	crsCreNm;
	private String	startDttm;
	private String	endDttm;
	private String	auditEndDttm;
	private String	stdNo;
	private String	connectYn;
	private Integer	prgrRatio;
	private Integer	stuCnt;
	private String	crsOperMthd;
	private String	crsOperMthdNm;
	private String	crsOperType;
	private String	crsOperTypeNm;
	private String	crsCtgrNm;
	private String	enrlDuration;
	private Integer	eduCredit;
	private Integer	getCredit;
	private String	enrlAplcDttm;
	private String	enrlSts;
	private String	enrlStsNm;
	private String	certIssueYn;
	private String	stayYn;
	private String	stayYnNm;
	private String  cpltNo;
	private String	carAplcSts; //차량 출입증 A:신청가능 E:수정가능 N:신청(수정)불가

	private Integer	reshSn;		//설문번호
	private String 	reshYn;		//설문참여 여부

	private String 	reshStartDttm;		//설문 시작일
	private String 	reshEndDttm;		//설문 종료일

	private String	paymNo;				//결제 번호
	private String	paymItgyDiv;		//무결성 검증 필드
	private String	paymMthdCd;			//거래 종류
	private String	paymOidNo;			//결제 주문 번호
	private String  paymStsCd;
	private Integer paymPrice;
	private String 	oflnStartDttm;
	private String 	oflnEndDttm;

	private String 	eduTeam;			//교육팀

	private String  resh1Cd;
	private String  resh2Cd;
	private String  resh3Cd;

	private Integer resh1Cnt = 0;
	private Integer resh2Cnt = 0;
	private Integer resh3Cnt = 0;

	private String  reshUseYn = "N";

	private String enrlDaycnt;
	private String enrlAplcStartDttm;
	private String enrlAplcEndDttm;

	private String cpltScore;
	private String totScore;
	
	private Integer sbjCnt;
	
	private int thumbFileSn;
	
	private String metaTag;
	private String[] metaTagArr;

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
	 * @return the creYear
	 */
	public String getCreYear() {
		return this.creYear;
	}

	/**
	 * @param creYear the creYear to set
	 */
	public void setCreYear(String creYear) {
		this.creYear = creYear;
	}

	/**
	 * @return the creTerm
	 */
	public String getCreTerm() {
		return this.creTerm;
	}

	/**
	 * @param creTerm the creTerm to set
	 */
	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}

	/**
	 * @return the crsCreNm
	 */
	public String getCrsCreNm() {
		return this.crsCreNm;
	}

	/**
	 * @param crsCreNm the crsCreNm to set
	 */
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}

	/**
	 * @return the startDttm
	 */
	public String getStartDttm() {
		return this.startDttm;
	}

	/**
	 * @param startDttm the startDttm to set
	 */
	public void setStartDttm(String startDttm) {
		this.startDttm = startDttm;
	}

	/**
	 * @return the endDttm
	 */
	public String getEndDttm() {
		return this.endDttm;
	}

	/**
	 * @param endDttm the endDttm to set
	 */
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
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
	 * @return the connectYn
	 */
	public String getConnectYn() {
		return this.connectYn;
	}

	/**
	 * @param connectYn the connectYn to set
	 */
	public void setConnectYn(String connectYn) {
		this.connectYn = connectYn;
	}

	/**
	 * @return the prgrRatio
	 */
	public Integer getPrgrRatio() {
		return this.prgrRatio;
	}

	/**
	 * @param prgrRatio the prgrRatio to set
	 */
	public void setPrgrRatio(Integer prgrRatio) {
		this.prgrRatio = prgrRatio;
	}

	/**
	 * @return the stuCnt
	 */
	public Integer getStuCnt() {
		return this.stuCnt;
	}

	/**
	 * @param stuCnt the stuCnt to set
	 */
	public void setStuCnt(Integer stuCnt) {
		this.stuCnt = stuCnt;
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
	 * @return the crsOperMthdNm
	 */
	public String getCrsOperMthdNm() {
		return this.crsOperMthdNm;
	}

	/**
	 * @param crsOperMthdNm the crsOperMthdNm to set
	 */
	public void setCrsOperMthdNm(String crsOperMthdNm) {
		this.crsOperMthdNm = crsOperMthdNm;
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
	 * @return the crsOperTypeNm
	 */
	public String getCrsOperTypeNm() {
		return this.crsOperTypeNm;
	}

	/**
	 * @param crsOperTypeNm the crsOperTypeNm to set
	 */
	public void setCrsOperTypeNm(String crsOperTypeNm) {
		this.crsOperTypeNm = crsOperTypeNm;
	}

	/**
	 * @return the crsCtgrNm
	 */
	public String getCrsCtgrNm() {
		return this.crsCtgrNm;
	}

	/**
	 * @param crsCtgrNm the crsCtgrNm to set
	 */
	public void setCrsCtgrNm(String crsCtgrNm) {
		this.crsCtgrNm = crsCtgrNm;
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
	 * @return the eduCredit
	 */
	public Integer getEduCredit() {
		return this.eduCredit;
	}

	/**
	 * @param eduCredit the eduCredit to set
	 */
	public void setEduCredit(Integer eduCredit) {
		this.eduCredit = eduCredit;
	}

	/**
	 * @return the getCredit
	 */
	public Integer getGetCredit() {
		return this.getCredit;
	}

	/**
	 * @param getCredit the getCredit to set
	 */
	public void setGetCredit(Integer getCredit) {
		this.getCredit = getCredit;
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
	 * @return the certIssueYn
	 */
	public String getCertIssueYn() {
		return this.certIssueYn;
	}

	/**
	 * @param certIssueYn the certIssueYn to set
	 */
	public void setCertIssueYn(String certIssueYn) {
		this.certIssueYn = certIssueYn;
	}

	/**
	 * @return the stayYn
	 */
	public String getStayYn() {
		return this.stayYn;
	}

	/**
	 * @param stayYn the stayYn to set
	 */
	public void setStayYn(String stayYn) {
		this.stayYn = stayYn;
	}

	/**
	 * @return the stayYnNm
	 */
	public String getStayYnNm() {
		return this.stayYnNm;
	}

	/**
	 * @param stayYnNm the stayYnNm to set
	 */
	public void setStayYnNm(String stayYnNm) {
		this.stayYnNm = stayYnNm;
	}

	public String getCarAplcSts() {
		return carAplcSts;
	}

	public void setCarAplcSts(String carAplcSts) {
		this.carAplcSts = carAplcSts;
	}

	public Integer getReshSn() {
		return reshSn;
	}

	public void setReshSn(Integer reshSn) {
		this.reshSn = reshSn;
	}

	public String getReshYn() {
		return reshYn;
	}

	public void setReshYn(String reshYn) {
		this.reshYn = reshYn;
	}

	public String getReshStartDttm() {
		return reshStartDttm;
	}

	public void setReshStartDttm(String reshStartDttm) {
		this.reshStartDttm = reshStartDttm;
	}

	public String getReshEndDttm() {
		return reshEndDttm;
	}

	public void setReshEndDttm(String reshEndDttm) {
		this.reshEndDttm = reshEndDttm;
	}

	public String getCpltNo() {
		return cpltNo;
	}

	public void setCpltNo(String cpltNo) {
		this.cpltNo = cpltNo;
	}

	public String getPaymNo() {
		return paymNo;
	}

	public void setPaymNo(String paymNo) {
		this.paymNo = paymNo;
	}

	public String getPaymMthdCd() {
		return paymMthdCd;
	}

	public void setPaymMthdCd(String paymMthdCd) {
		this.paymMthdCd = paymMthdCd;
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

	public String getEduTeam() {
		return eduTeam;
	}

	public void setEduTeam(String eduTeam) {
		this.eduTeam = eduTeam;
	}

	public String getPaymStsCd() {
		return paymStsCd;
	}

	public void setPaymStsCd(String paymStsCd) {
		this.paymStsCd = paymStsCd;
	}

	public Integer getPaymPrice() {
		return paymPrice;
	}

	public void setPaymPrice(Integer paymPrice) {
		this.paymPrice = paymPrice;
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

	public Integer getResh1Cnt() {
		return resh1Cnt;
	}

	public void setResh1Cnt(Integer resh1Cnt) {
		this.resh1Cnt = resh1Cnt;
	}

	public Integer getResh2Cnt() {
		return resh2Cnt;
	}

	public void setResh2Cnt(Integer resh2Cnt) {
		this.resh2Cnt = resh2Cnt;
	}

	public Integer getResh3Cnt() {
		return resh3Cnt;
	}

	public void setResh3Cnt(Integer resh3Cnt) {
		this.resh3Cnt = resh3Cnt;
	}

	public String getReshUseYn() {
		return reshUseYn;
	}

	public void setReshUseYn(String reshUseYn) {
		this.reshUseYn = reshUseYn;
	}

	public String getEnrlDaycnt() {
		return enrlDaycnt;
	}

	public void setEnrlDaycnt(String enrlDaycnt) {
		this.enrlDaycnt = enrlDaycnt;
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

	public String getCpltScore() {
		return cpltScore;
	}

	public void setCpltScore(String cpltScore) {
		this.cpltScore = cpltScore;
	}

	public String getTotScore() {
		return totScore;
	}

	public void setTotScore(String totScore) {
		this.totScore = totScore;
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

	public String getSbjCd() {
		return sbjCd;
	}

	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	public Integer getSbjCnt() {
		return sbjCnt;
	}

	public void setSbjCnt(Integer sbjCnt) {
		this.sbjCnt = sbjCnt;
	}

	public int getThumbFileSn() {
		return thumbFileSn;
	}

	public void setThumbFileSn(int thumbFileSn) {
		this.thumbFileSn = thumbFileSn;
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
	
	
}