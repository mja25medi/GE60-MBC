package egovframework.edutrack.modules.user.info.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class UsrUserInfoChgHstyVO extends DefaultVO {

	private static final long serialVersionUID = 1298581295332327903L;

	private String  userNo;
	private String  userInfoChgDivCd;
	private String  userInfoCts;
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserInfoChgDivCd() {
		return userInfoChgDivCd;
	}
	public void setUserInfoChgDivCd(String userInfoChgDivCd) {
		this.userInfoChgDivCd = userInfoChgDivCd;
	}
	public String getUserInfoCts() {
		return userInfoCts;
	}
	public void setUserInfoCts(String userInfoCts) {
		this.userInfoCts = userInfoCts;
	}
}
