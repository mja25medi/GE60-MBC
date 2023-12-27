package egovframework.edutrack.modules.log.userconn.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogUserConnLogVO extends DefaultVO {

	private static final long serialVersionUID = 5889203912816055968L;

	private String  loginDttm;
	private String  userNo;
	private String  connIp;
	private String  logoutDttm;
	private String  deviceType;
	private String  osType;
	private String  browserType;
	private String  appType;
	private String  mobileType;
	private String  userNm;
	private String 	userId;

	public String getLoginDttm() {
		return loginDttm;
	}
	public void setLoginDttm(String loginDttm) {
		this.loginDttm = loginDttm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getConnIp() {
		return connIp;
	}
	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}
	public String getLogoutDttm() {
		return logoutDttm;
	}
	public void setLogoutDttm(String logoutDttm) {
		this.logoutDttm = logoutDttm;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
