package egovframework.edutrack.modules.course.assignmentbank.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 과제 문제은행 문제 DTO
 */
public class AssignmentQbankQuestionFileVO extends DefaultVO {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4574143861823007907L;
	private String	sbjCd;
	private int		qstnSn;
	private String	ctgrCd;
	private int		fileSn;

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
	 * @return the qstnSn
	 */
	public int getQstnSn() {
		return qstnSn;
	}
	/**
	 * @param qstnSn the qstnSn to set
	 */
	public void setQstnSn(int qstnSn) {
		this.qstnSn = qstnSn;
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
	 * @return the fileSn
	 */
	public int getFileSn() {
		return fileSn;
	}
	/**
	 * @param fileSn the fileSn to set
	 */
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
}