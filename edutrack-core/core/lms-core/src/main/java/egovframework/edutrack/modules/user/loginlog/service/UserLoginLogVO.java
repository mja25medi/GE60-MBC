package egovframework.edutrack.modules.user.loginlog.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class UserLoginLogVO extends DefaultVO {

	private static final long serialVersionUID = 6208087567761998769L;
	private String loginId;
	private String loginData;
	private String connDttm;
	private String endDttm;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginData() {
		return loginData;
	}
	public void setLoginData(String loginData) {
		this.loginData = loginData;
	}
	public String getConnDttm() {
		return connDttm;
	}
	public void setConnDttm(String connDttm) {
		this.connDttm = connDttm;
	}
	public String getEndDttm() {
		return endDttm;
	}
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
}
