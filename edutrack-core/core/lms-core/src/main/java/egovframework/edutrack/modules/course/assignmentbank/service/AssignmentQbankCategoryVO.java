package egovframework.edutrack.modules.course.assignmentbank.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 과제 문제은행 분류 DTO
 */
public class AssignmentQbankCategoryVO  extends DefaultVO {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3939999383363808498L;
	private String 	sbjCd;
	private String 	ctgrCd;
	private String	ctgrNm;
	
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
	
}