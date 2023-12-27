/**
 *
 */
package egovframework.edutrack.modules.teacher.aplc.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 회원 DTO
 */

public class TchAplcVO extends DefaultVO {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1407500948963743954L;

	private String searchSts;
	
	private String chkUser; 
	private String userNo;                
	private String aplcDt;       
	private String aprvSts;                 
	private String aprvStsNm;               
	private String userId;
	private String userNm;
	private String sex;
	private String mobileNo;
	private String email;
	private String tchDivCd;
	private String tchDivCdNm;
	private String tchCtgrCd;  //강사분류
    private String[] userList;

    
	
	/**
	 * @return the searchSts
	 */
	public String getSearchSts() {
		return searchSts;
	}
	/**
	 * @param searchSts the searchSts to set
	 */
	public void setSearchSts(String searchSts) {
		this.searchSts = searchSts;
	}
	/**
	 * @return the chkUser
	 */
	public String getChkUser() {
		return chkUser;
	}
	/**
	 * @param chkUser the chkUser to set
	 */
	public void setChkUser(String chkUser) {
		this.chkUser = chkUser;
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
	 * @return the aplcDt
	 */
	public String getAplcDt() {
		return aplcDt;
	}
	/**
	 * @param aplcDt the aplcDt to set
	 */
	public void setAplcDt(String aplcDt) {
		this.aplcDt = aplcDt;
	}
	/**
	 * @return the aprvSts
	 */
	public String getAprvSts() {
		return aprvSts;
	}
	/**
	 * @param aprvSts the aprvSts to set
	 */
	public void setAprvSts(String aprvSts) {
		this.aprvSts = aprvSts;
	}
	/**
	 * @return the aprvStsNm
	 */
	public String getAprvStsNm() {
		return aprvStsNm;
	}
	/**
	 * @param aprvStsNm the aprvStsNm to set
	 */
	public void setAprvStsNm(String aprvStsNm) {
		this.aprvStsNm = aprvStsNm;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the userList
	 */
	public String[] getUserList() {
		return userList;
	}
	/**
	 * @param userList2 the userList to set
	 */
	public void setUserList(String[] userList) {
		this.userList = userList;
	}
	/**
	 * @return the tchDivCd
	 */
	public String getTchDivCd() {
		return tchDivCd;
	}
	/**
	 * @param tchDivCd the tchDivCd to set
	 */
	public void setTchDivCd(String tchDivCd) {
		this.tchDivCd = tchDivCd;
	}
	/**
	 * @return the tchDivCdNm
	 */
	public String getTchDivCdNm() {
		return tchDivCdNm;
	}
	/**
	 * @param tchDivCdNm the tchDivCdNm to set
	 */
	public void setTchDivCdNm(String tchDivCdNm) {
		this.tchDivCdNm = tchDivCdNm;
	}
	
	
	/**
	 * @return the tchCtgrCd
	 */
	public String getTchCtgrCd() {
		return tchCtgrCd;
	}
	/**
	 * @param tchCtgrCd the tchCtgrCd to set
	 */
	public void setTchCtgrCd(String tchCtgrCd) {
		this.tchCtgrCd = tchCtgrCd;
	}
	@Override
	public String toString() {
		return "TeacherAplcDTO [searchSts=" + this.searchSts + ", searchFrom=" + this.searchFrom 
		           + ", searchTo=" + this.searchTo  +"]";
	}
}