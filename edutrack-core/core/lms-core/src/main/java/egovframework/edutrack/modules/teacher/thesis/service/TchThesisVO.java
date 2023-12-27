/**
 *
 */
package egovframework.edutrack.modules.teacher.thesis.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 발표논문 VO
 */

public class TchThesisVO
		extends DefaultVO {

	private static final long	serialVersionUID	= -146323181972914717L;
	private Integer				thesisSn;
	private String				userNo;
	private String				receiptDt;
	private String				magazineNm;
	private Integer				pblsTitleNo;
	private String				cts;
	private String				pblsrPhoneno;
	private String				pblsDeptNm;

	/**
	 * @return the thesisSn
	 */
	public Integer getThesisSn() {
		return thesisSn;
	}

	/**
	 * @param thesisSn the thesisSn to set
	 */
	public void setThesisSn(Integer thesisSn) {
		this.thesisSn = thesisSn;
	}

	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return the receiptDt
	 */
	public String getReceiptDt() {
		return receiptDt;
	}

	/**
	 * @param receiptDt the receiptDt to set
	 */
	public void setReceiptDt(String receiptDt) {
		this.receiptDt = receiptDt;
	}

	/**
	 * @return the magazineNm
	 */
	public String getMagazineNm() {
		return magazineNm;
	}

	/**
	 * @param magazineNm the magazineNm to set
	 */
	public void setMagazineNm(String magazineNm) {
		this.magazineNm = magazineNm;
	}

	/**
	 * @return the pblsTitleNo
	 */
	public Integer getPblsTitleNo() {
		return pblsTitleNo;
	}

	/**
	 * @param pblsTitleNo the pblsTitleNo to set
	 */
	public void setPblsTitleNo(Integer pblsTitleNo) {
		this.pblsTitleNo = pblsTitleNo;
	}

	/**
	 * @return the cts
	 */
	public String getCts() {
		return cts;
	}

	/**
	 * @param cts the cts to set
	 */
	public void setCts(String cts) {
		this.cts = cts;
	}

	/**
	 * @return the pblsrPhoneno
	 */
	public String getPblsrPhoneno() {
		return pblsrPhoneno;
	}

	/**
	 * @param pblsrPhoneno the pblsrPhoneno to set
	 */
	public void setPblsrPhoneno(String pblsrPhoneno) {
		this.pblsrPhoneno = pblsrPhoneno;
	}

	/**
	 * @return the pblsDeptNm
	 */
	public String getPblsDeptNm() {
		return pblsDeptNm;
	}

	/**
	 * @param pblsDeptNm the pblsDeptNm to set
	 */
	public void setPblsDeptNm(String pblsDeptNm) {
		this.pblsDeptNm = pblsDeptNm;
	}

}