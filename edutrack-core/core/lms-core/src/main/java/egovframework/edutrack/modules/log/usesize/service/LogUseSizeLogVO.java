package egovframework.edutrack.modules.log.usesize.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogUseSizeLogVO extends DefaultVO {

	private static final long serialVersionUID = 2426187830606448744L;
	
	private String  orgCd;
	private String  saveDttm;
	private String  divCd;
	private long	useSize;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getSaveDttm() {
		return saveDttm;
	}
	public void setSaveDttm(String saveDttm) {
		this.saveDttm = saveDttm;
	}
	public String getDivCd() {
		return divCd;
	}
	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}
	public long getUseSize() {
		return useSize;
	}
	public void setUseSize(long useSize) {
		this.useSize = useSize;
	}
}
