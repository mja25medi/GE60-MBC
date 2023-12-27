/**
 *
 */
package egovframework.edutrack.modules.teacher.schs.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 회원 VO
 */

public class TchSchsVO
		extends DefaultVO {

	private static final long	serialVersionUID	= 789462365590073990L;
	private String				userNo;
	private int					schsSn;
	private String				entrgradDt;
	private String				entrDt;
	private String				gradDt;
	private String				schlNm;
	private String				schlSubj;
	private String				gradDiv;
	private String				locatNm;
	private String				gradDivNm;
	private String				isView;
	private String[]			schsList;

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
	 * @return the schsSn
	 */
	public int getSchsSn() {
		return schsSn;
	}

	/**
	 * @param schsSn the schsSn to set
	 */
	public void setSchsSn(int schsSn) {
		this.schsSn = schsSn;
	}

	/**
	 * @return the entrgradDt
	 */
	public String getEntrgradDt() {
		return entrgradDt;
	}

	/**
	 * @param entrgradDt the entrgradDt to set
	 */
	public void setEntrgradDt(String entrgradDt) {
		this.entrgradDt = entrgradDt;
	}

	/**
	 * @return the entrDt
	 */
	public String getEntrDt() {
		return entrDt;
	}

	/**
	 * @param entrDt the entrDt to set
	 */
	public void setEntrDt(String entrDt) {
		this.entrDt = entrDt;
	}

	/**
	 * @return the gradDt
	 */
	public String getGradDt() {
		return gradDt;
	}

	/**
	 * @param gradDt the gradDt to set
	 */
	public void setGradDt(String gradDt) {
		this.gradDt = gradDt;
	}

	/**
	 * @return the schlNm
	 */
	public String getSchlNm() {
		return schlNm;
	}

	/**
	 * @param schlNm the schlNm to set
	 */
	public void setSchlNm(String schlNm) {
		this.schlNm = schlNm;
	}

	/**
	 * @return the schlSubj
	 */
	public String getSchlSubj() {
		return schlSubj;
	}

	/**
	 * @param schlSubj the schlSubj to set
	 */
	public void setSchlSubj(String schlSubj) {
		this.schlSubj = schlSubj;
	}

	/**
	 * @return the locatNm
	 */
	public String getLocatNm() {
		return locatNm;
	}

	/**
	 * @param locatNm the locatNm to set
	 */
	public void setLocatNm(String locatNm) {
		this.locatNm = locatNm;
	}

	/**
	 * @return the schsList
	 */
	public String[] getSchsList() {
		return schsList;
	}

	/**
	 * @param schsList the schsList to set
	 */
	public void setSchsList(String[] schsList) {
		this.schsList = schsList;
	}

	/**
	 * @return the gradDiv
	 */
	public String getGradDiv() {
		return gradDiv;
	}

	/**
	 * @param gradDiv the gradDiv to set
	 */
	public void setGradDiv(String gradDiv) {
		this.gradDiv = gradDiv;
	}

	/**
	 * @return the gradDivNm
	 */
	public String getGradDivNm() {
		return gradDivNm;
	}

	/**
	 * @param gradDivNm the gradDivNm to set
	 */
	public void setGradDivNm(String gradDivNm) {
		this.gradDivNm = gradDivNm;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

}