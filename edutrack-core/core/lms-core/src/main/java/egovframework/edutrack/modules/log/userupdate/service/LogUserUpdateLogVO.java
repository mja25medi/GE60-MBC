package egovframework.edutrack.modules.log.userupdate.service;

import java.io.Serializable;

public class LogUserUpdateLogVO implements Serializable {

	private static final long serialVersionUID = -4016197315550525528L;

	private Long seq;
	private String accessIp;
	private String accessUserId;
	private String accessUserNo;
	private String targetUserNo;

	private String uriInfo;
	private String methodInfo;
	
	private UserInfoLog.LogType logType;
	
	private String history;
	
	private String accessDttm;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	public String getAccessUserId() {
		return accessUserId;
	}

	public void setAccessUserId(String accessUserId) {
		this.accessUserId = accessUserId;
	}

	public String getAccessUserNo() {
		return accessUserNo;
	}

	public void setAccessUserNo(String accessUserNo) {
		this.accessUserNo = accessUserNo;
	}

	public String getTargetUserNo() {
		return targetUserNo;
	}

	public void setTargetUserNo(String targetUserNo) {
		this.targetUserNo = targetUserNo;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public UserInfoLog.LogType getLogType() {
		return logType;
	}

	public void setLogType(UserInfoLog.LogType logType) {
		this.logType = logType;
	}

	public String getAccessDttm() {
		return accessDttm;
	}

	public void setAccessDttm(String accessDttm) {
		this.accessDttm = accessDttm;
	}

	public String getMethodInfo() {
		return methodInfo;
	}

	public void setMethodInfo(String methodInfo) {
		this.methodInfo = methodInfo;
	}

	public String getUriInfo() {
		return uriInfo;
	}

	public void setUriInfo(String uriInfo) {
		this.uriInfo = uriInfo;
	}
}
