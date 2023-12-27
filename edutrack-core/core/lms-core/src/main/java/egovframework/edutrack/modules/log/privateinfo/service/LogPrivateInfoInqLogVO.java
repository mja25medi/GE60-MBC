package egovframework.edutrack.modules.log.privateinfo.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogPrivateInfoInqLogVO extends DefaultVO {

	private static final long	serialVersionUID	= -4746591041812414343L;
	private String  orgCd;
	private String  inqDttm;
	private String  menuCd;
	private String  divCd;
	private String  userNo;
	private String  userNm;
	private String  inqCts;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getInqDttm() {
		return inqDttm;
	}
	public void setInqDttm(String inqDttm) {
		this.inqDttm = inqDttm;
	}
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getDivCd() {
		return divCd;
	}
	public void setDivCd(String divCd) {
		this.divCd = divCd;
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
	public String getInqCts() {
		return inqCts;
	}
	public void setInqCts(String inqCts) {
		this.inqCts = inqCts;
	}
}
