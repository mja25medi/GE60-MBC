package egovframework.edutrack.modules.course.exambank.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 시험 문제은행 분류 VO
 */
public class ExamQbankCtgrVO  extends DefaultVO {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5170298164137391312L;
	private String 	sbjCd = "sbjCd";
	private String 	ctgrCd;
	private String	ctgrNm;
	
	private String	crsNm;
	private String parCtgrCd;
	private String parCtgrNm;
	private Integer ctgrLvl;
	private Integer parCtgrLvl;
	private String ctgrDesc;
	private String useYn;
	private String	qstnType;
	private String orgCd;
	
	private	int		subCnt;

	/**
	 * @return the sbjCd
	 */
	public String getSbjCd() {
		return sbjCd;
	}
	
	/**
	 * @param sbjCd the sbjCd to set
	 */
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	/**
	 * @return the ctgrCd
	 */
	public String getCtgrCd() {
		return ctgrCd;
	}

	/**
	 * @param ctgrCd the ctgrCd to set
	 */
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}

	/**
	 * @return the ctgrNm
	 */
	public String getCtgrNm() {
		return ctgrNm;
	}

	/**
	 * @param ctgrNm the ctgrNm to set
	 */
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}

	/**
	 * @return the subCnt
	 */
	public int getSubCnt() {
		return subCnt;
	}

	/**
	 * @param subCnt the subCnt to set
	 */
	public void setSubCnt(int subCnt) {
		this.subCnt = subCnt;
	}

	public String getCrsNm() {
		return crsNm;
	}

	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}

	public String getParCtgrCd() {
		return parCtgrCd;
	}

	public void setParCtgrCd(String parCtgrCd) {
		this.parCtgrCd = parCtgrCd;
	}

	public Integer getCtgrLvl() {
		return ctgrLvl;
	}

	public void setCtgrLvl(Integer ctgrLvl) {
		this.ctgrLvl = ctgrLvl;
	}

	public Integer getParCtgrLvl() {
		return parCtgrLvl;
	}

	public void setParCtgrLvl(Integer parCtgrLvl) {
		this.parCtgrLvl = parCtgrLvl;
	}

	public String getCtgrDesc() {
		return ctgrDesc;
	}

	public void setCtgrDesc(String ctgrDesc) {
		this.ctgrDesc = ctgrDesc;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getQstnType() {
		return qstnType;
	}

	public void setQstnType(String qstnType) {
		this.qstnType = qstnType;
	}

	public String getParCtgrNm() {
		return parCtgrNm;
	}

	public void setParCtgrNm(String parCtgrNm) {
		this.parCtgrNm = parCtgrNm;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	
}