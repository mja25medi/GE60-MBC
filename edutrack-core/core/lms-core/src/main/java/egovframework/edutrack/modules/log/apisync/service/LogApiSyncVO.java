package egovframework.edutrack.modules.log.apisync.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogApiSyncVO  extends DefaultVO {

	private static final long serialVersionUID = 1L;
	
	private Integer  apiSyncLogSn;
	private String   crsCd;
	private String   syncGubunCd;
	private String   siteUrl;
	private Integer  syncTime;
	private String   syncResultMsg;
	private String   syncSuccessYn;
	private Integer  syncSuccessCnt;
	private Integer  syncFailCnt;
	private String   syncFailPk;
	private String   syncDate;
	
	
	public Integer getApiSyncLogSn() {
		return apiSyncLogSn;
	}
	public void setApiSyncLogSn(Integer apiSyncLogSn) {
		this.apiSyncLogSn = apiSyncLogSn;
	}
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getSyncGubunCd() {
		return syncGubunCd;
	}
	public void setSyncGubunCd(String syncGubunCd) {
		this.syncGubunCd = syncGubunCd;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public Integer getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(Integer syncTime) {
		this.syncTime = syncTime;
	}
	public String getSyncResultMsg() {
		return syncResultMsg;
	}
	public void setSyncResultMsg(String syncResultMsg) {
		this.syncResultMsg = syncResultMsg;
	}
	public String getSyncSuccessYn() {
		return syncSuccessYn;
	}
	public void setSyncSuccessYn(String syncSuccessYn) {
		this.syncSuccessYn = syncSuccessYn;
	}
	public Integer getSyncSuccessCnt() {
		return syncSuccessCnt;
	}
	public void setSyncSuccessCnt(Integer syncSuccessCnt) {
		this.syncSuccessCnt = syncSuccessCnt;
	}
	public Integer getSyncFailCnt() {
		return syncFailCnt;
	}
	public void setSyncFailCnt(Integer syncFailCnt) {
		this.syncFailCnt = syncFailCnt;
	}
	public String getSyncFailPk() {
		return syncFailPk;
	}
	public void setSyncFailPk(String syncFailPk) {
		this.syncFailPk = syncFailPk;
	}
	public String getSyncDate() {
		return syncDate;
	}
	public void setSyncDate(String syncDate) {
		this.syncDate = syncDate;
	}
	
}
