package egovframework.edutrack.modules.user.info.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class UsrUserAuthGrpVO extends DefaultVO {

	private static final long serialVersionUID = -2897766342230335596L;
	private String menuType;
	private String authGrpCd;
	private String authGrpNm;
	private String userNo;
	
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getAuthGrpCd() {
		return authGrpCd;
	}
	public void setAuthGrpCd(String authGrpCd) {
		this.authGrpCd = authGrpCd;
	}
	public String getAuthGrpNm() {
		return authGrpNm;
	}
	public void setAuthGrpNm(String authGrpNm) {
		this.authGrpNm = authGrpNm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
}
