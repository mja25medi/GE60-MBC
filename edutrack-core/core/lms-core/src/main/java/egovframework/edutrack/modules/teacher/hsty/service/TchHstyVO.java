/**
 *
 */
package egovframework.edutrack.modules.teacher.hsty.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 강사 활동 이력 VO
 */

public class TchHstyVO
		extends DefaultVO {

	private static final long	serialVersionUID	= 4626221943588380950L;
	private String				userNo;
	private int					actnHstySn;
	private String				actnHstyCts;
	private String				actnDt;
	private String				actnFromDt;
	private String				actnToDt;

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
	 * @return the actnHstySn
	 */
	public int getActnHstySn() {
		return actnHstySn;
	}

	/**
	 * @param actnHstySn the actnHstySn to set
	 */
	public void setActnHstySn(int actnHstySn) {
		this.actnHstySn = actnHstySn;
	}

	/**
	 * @return the actnHstyCts
	 */
	public String getActnHstyCts() {
		return actnHstyCts;
	}

	/**
	 * @param actnHstyCts the actnHstyCts to set
	 */
	public void setActnHstyCts(String actnHstyCts) {
		this.actnHstyCts = actnHstyCts;
	}

	/**
	 * @return the actnDt
	 */
	public String getActnDt() {
		return actnDt;
	}

	/**
	 * @param actnDt the actnDt to set
	 */
	public void setActnDt(String actnDt) {
		this.actnDt = actnDt;
	}

	/**
	 * @return the actnFromDt
	 */
	public String getActnFromDt() {
		return actnFromDt;
	}

	/**
	 * @param actnFromDt the actnFromDt to set
	 */
	public void setActnFromDt(String actnFromDt) {
		this.actnFromDt = actnFromDt;
	}

	/**
	 * @return the actnToDt
	 */
	public String getActnToDt() {
		return actnToDt;
	}

	/**
	 * @param actnToDt the actnToDt to set
	 */
	public void setActnToDt(String actnToDt) {
		this.actnToDt = actnToDt;
	}

}