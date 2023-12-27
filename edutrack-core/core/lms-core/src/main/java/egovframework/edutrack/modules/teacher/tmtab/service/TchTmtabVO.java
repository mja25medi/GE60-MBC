/**
 *
 */
package egovframework.edutrack.modules.teacher.tmtab.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 강사 활동 이력 VO
 */

public class TchTmtabVO extends DefaultVO {
	
	private static final long	serialVersionUID	= -9082240955317510669L;
	private String userNo;               
    private String crsCreCd;
	private String tmtabDay;
	private String crsCreNm;
	private String sbjCd;
	private String sbjNm;
	private String eduTm;
	private String eduCts;
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
	 * @return the crsCreCd
	 */
	public String getCrsCreCd() {
		return crsCreCd;
	}
	/**
	 * @param crsCreCd the crsCreCd to set
	 */
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	/**
	 * @return the tmtabDay
	 */
	public String getTmtabDay() {
		return tmtabDay;
	}
	/**
	 * @param tmtabDay the tmtabDay to set
	 */
	public void setTmtabDay(String tmtabDay) {
		this.tmtabDay = tmtabDay;
	}
	/**
	 * @return the crsCreNm
	 */
	public String getCrsCreNm() {
		return crsCreNm;
	}
	/**
	 * @param crsCreNm the crsCreNm to set
	 */
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
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
	 * @return the sbjNm
	 */
	public String getSbjNm() {
		return sbjNm;
	}
	/**
	 * @param sbjNm the sbjNm to set
	 */
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	/**
	 * @return the eduTm
	 */
	public String getEduTm() {
		return eduTm;
	}
	/**
	 * @param eduTm the eduTm to set
	 */
	public void setEduTm(String eduTm) {
		this.eduTm = eduTm;
	}
	/**
	 * @return the eduCts
	 */
	public String getEduCts() {
		return eduCts;
	}
	/**
	 * @param eduCts the eduCts to set
	 */
	public void setEduCts(String eduCts) {
		this.eduCts = eduCts;
	}
	
}