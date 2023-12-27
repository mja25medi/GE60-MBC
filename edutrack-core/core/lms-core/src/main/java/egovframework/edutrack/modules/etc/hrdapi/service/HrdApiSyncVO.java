package egovframework.edutrack.modules.etc.hrdapi.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class HrdApiSyncVO extends DefaultVO {

	private static final long serialVersionUID = 1L;
	private Integer crsYear;
	private Integer crsTerm;
	private String apiPk;
	private String syncGubunCd;
	private String syncStatus;
	private String syncResultMsg;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	
	private String apiUrl;
	
	public Integer getCrsYear() {
		return crsYear;
	}
	public void setCrsYear(Integer crsYear) {
		this.crsYear = crsYear;
	}
	public Integer getCrsTerm() {
		return crsTerm;
	}
	public void setCrsTerm(Integer crsTerm) {
		this.crsTerm = crsTerm;
	}
	public String getApiPk() {
		return apiPk;
	}
	public void setApiPk(String apiPk) {
		this.apiPk = apiPk;
	}
	public String getSyncGubunCd() {
		return syncGubunCd;
	}
	public void setSyncGubunCd(String syncGubunCd) {
		this.syncGubunCd = syncGubunCd;
	}
	public String getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}
	public String getSyncResultMsg() {
		return syncResultMsg;
	}
	public void setSyncResultMsg(String syncResultMsg) {
		this.syncResultMsg = syncResultMsg;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
}
