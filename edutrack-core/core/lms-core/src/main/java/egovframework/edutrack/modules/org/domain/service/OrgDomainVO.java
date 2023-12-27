package egovframework.edutrack.modules.org.domain.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgDomainVO extends DefaultVO {

	private static final long serialVersionUID = -8244198753818122358L;

	private String  orgCd;
	private String  orgDomain;
	private String  dfltYn;
	private String  rprstYn;
	private String  domainTypeCd;
	private String  siteServiceTypeCd;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOrgDomain() {
		return orgDomain;
	}
	public void setOrgDomain(String orgDomain) {
		this.orgDomain = orgDomain;
	}
	public String getDfltYn() {
		return dfltYn;
	}
	public void setDfltYn(String dfltYn) {
		this.dfltYn = dfltYn;
	}
	public String getRprstYn() {
		return rprstYn;
	}
	public void setRprstYn(String rprstYn) {
		this.rprstYn = rprstYn;
	}
	public String getDomainTypeCd() {
		return domainTypeCd;
	}
	public void setDomainTypeCd(String domainTypeCd) {
		this.domainTypeCd = domainTypeCd;
	}
	public String getSiteServiceTypeCd() {
		return siteServiceTypeCd;
	}
	public void setSiteServiceTypeCd(String siteServiceTypeCd) {
		this.siteServiceTypeCd = siteServiceTypeCd;
	}
	
}
