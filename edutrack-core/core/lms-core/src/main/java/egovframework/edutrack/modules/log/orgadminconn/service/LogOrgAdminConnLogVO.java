package egovframework.edutrack.modules.log.orgadminconn.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogOrgAdminConnLogVO extends DefaultVO {

	private static final long serialVersionUID = -5148496703070661246L;
	
	private int		connLogSn;
	private String	orgCd;
	private String	userNo;
	private String	userNm;
	private String	loginIp;
	private String	loginDttm;
	private String	logoutDttm;
	private String	userId;
	
	public int getConnLogSn() {
		return connLogSn;
	}
	public void setConnLogSn(int connLogSn) {
		this.connLogSn = connLogSn;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
