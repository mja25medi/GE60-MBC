/**
 *
 */
package egovframework.edutrack.modules.teacher.writings.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 강의저서 DTO
 */

public class TchWritingsVO extends DefaultVO {

	private static final long	serialVersionUID	= -8023696182144257516L;
	private Integer lecWritingsSn;
	private String userNo;
	private String pblsDt;
	private String bookNm;
	private String cts;
	private String pblsCnt;
	private String pblsrPhoneno;
	private String pblsDeptNm;
	private String	isView;

	/**
	 * @return the lecWritingsSn
	 */
	public Integer getLecWritingsSn() {
		return lecWritingsSn;
	}
	/**
	 * @param lecWritingsSn the lecWritingsSn to set
	 */
	public void setLecWritingsSn(Integer lecWritingsSn) {
		this.lecWritingsSn = lecWritingsSn;
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
	 * @return the pblsDt
	 */
	public String getPblsDt() {
		return pblsDt;
	}
	/**
	 * @param pblsDt the pblsDt to set
	 */
	public void setPblsDt(String pblsDt) {
		this.pblsDt = pblsDt;
	}
	/**
	 * @return the bookNm
	 */
	public String getBookNm() {
		return bookNm;
	}
	/**
	 * @param bookNm the bookNm to set
	 */
	public void setBookNm(String bookNm) {
		this.bookNm = bookNm;
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
	 * @return the pblsCnt
	 */
	public String getPblsCnt() {
		return pblsCnt;
	}
	/**
	 * @param pblsCnt the pblsCnt to set
	 */
	public void setPblsCnt(String pblsCnt) {
		this.pblsCnt = pblsCnt;
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
	public String getIsView() {
		return isView;
	}
	public void setIsView(String isView) {
		this.isView = isView;
	}

}