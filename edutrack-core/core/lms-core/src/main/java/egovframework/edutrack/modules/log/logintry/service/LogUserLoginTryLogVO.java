package egovframework.edutrack.modules.log.logintry.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogUserLoginTryLogVO extends DefaultVO {

	private static final long serialVersionUID = 7612328799504699385L;

	private int		loginTrySn;
	private String	userNo;
	private	String	userId;
	private String	loginTryDttm;
	private String	loginSuccYn;
	private String	browserInfo;
	private String	connIp;
	
	public int getLoginTrySn() {
		return this.loginTrySn;
	}
	
	public void setLoginTrySn(int loginTrySn) {
		this.loginTrySn = loginTrySn;
	}
	
	public String getUserNo() {
		return this.userNo;
	}
	
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLoginTryDttm() {
		return this.loginTryDttm;
	}
	
	public void setLoginTryDttm(String loginTryDttm) {
		this.loginTryDttm = loginTryDttm;
	}
	
	public String getLoginSuccYn() {
		return this.loginSuccYn;
	}
	
	public void setLoginSuccYn(String loginSuccYn) {
		this.loginSuccYn = loginSuccYn;
	}
	
	public String getBrowserInfo() {
		return this.browserInfo;
	}
	
	public void setBrowserInfo(String browserInfo) {
		this.browserInfo = browserInfo;
	}
	
	public String getConnIp() {
		return this.connIp;
	}
	
	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}
}
