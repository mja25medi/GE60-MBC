/**
 *
 */
package egovframework.edutrack.modules.teacher.field.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 강사 강의 분야 DTO
 */

public class TchFieldVO extends DefaultVO {

	private static final long	serialVersionUID	= 8954225955335040970L;
	private String userNo;
	private String lecFieldCd;
	private String lecFieldCdNm;
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
	 * @return the lecFieldCd
	 */
	public String getLecFieldCd() {
		return lecFieldCd;
	}
	/**
	 * @param lecFieldCd the lecFieldCd to set
	 */
	public void setLecFieldCd(String lecFieldCd) {
		this.lecFieldCd = lecFieldCd;
	}
	/**
	 * @return the lecFieldCdNm
	 */
	public String getLecFieldCdNm() {
		return lecFieldCdNm;
	}
	/**
	 * @param lecFieldCdNm the lecFieldCdNm to set
	 */
	public void setLecFieldCdNm(String lecFieldCdNm) {
		this.lecFieldCdNm = lecFieldCdNm;
	}
}