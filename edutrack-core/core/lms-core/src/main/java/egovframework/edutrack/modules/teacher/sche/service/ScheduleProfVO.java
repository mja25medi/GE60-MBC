/**
 *
 */
package egovframework.edutrack.modules.teacher.sche.service;

import java.util.Calendar;

import egovframework.edutrack.comm.service.DefaultVO;




/**
 * 시스템 코드 VO
 */

public class ScheduleProfVO extends DefaultVO{

	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6893506399455880052L;
	private String userNo     ;
	private String userNm     ;
	
	private  Calendar cfirstday;
	private  Calendar clastday;
	private  String NOW; //현재일자;
	
	
	
	
	
	/**
	 * 생성자
	 */
	public ScheduleProfVO() {
		// 
	}



	public String getUserNo() {
		return userNo;
	}



	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}



	public String getUserNm() {
		return userNm;
	}



	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}



	/**
	 * @return the cfirstday
	 */
	public Calendar getCfirstday() {
		return cfirstday;
	}



	/**
	 * @param cfirstday the cfirstday to set
	 */
	public void setCfirstday(Calendar cfirstday) {
		this.cfirstday = cfirstday;
	}



	/**
	 * @return the clastday
	 */
	public Calendar getClastday() {
		return clastday;
	}



	/**
	 * @param clastday the clastday to set
	 */
	public void setClastday(Calendar clastday) {
		this.clastday = clastday;
	}



	/**
	 * @return the nOW
	 */
	public String getNOW() {
		return NOW;
	}



	/**
	 * @param nOW the nOW to set
	 */
	public void setNOW(String nOW) {
		NOW = nOW;
	}
	
	
}