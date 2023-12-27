package egovframework.edutrack.modules.log.adminconn.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogAdminConnLogVO extends DefaultVO {

	private static final long serialVersionUID = -5148496703070761246L;
	
	private int		connLogSn;
	private String	userNo;
	private String	userNm;
	private String	loginIp;
	private String	loginDttm;
	private String	logoutDttm;
	
	public int getConnLogSn() {
		return connLogSn;
	}
	public void setConnLogSn(int connLogSn) {
		this.connLogSn = connLogSn;
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
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginDttm() {
		return loginDttm;
	}
	public void setLoginDttm(String loginDttm) {
		this.loginDttm = loginDttm;
	}
	public String getLogoutDttm() {
		return logoutDttm;
	}
	public void setLogoutDttm(String logoutDttm) {
		this.logoutDttm = logoutDttm;
	}
}
