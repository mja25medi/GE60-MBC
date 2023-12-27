package egovframework.edutrack.modules.org.connip.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgConnIpVO extends DefaultVO {

	private static final long serialVersionUID = 7398798073781591165L;

	private String  orgCd;
	private String  connIp;
	private String  divCd;
	private String  useYn;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getConnIp() {
		return connIp;
	}
	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}
	public String getDivCd() {
		return divCd;
	}
	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
