package egovframework.edutrack.modules.org.creaplc.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgCreAplcInfoVO extends DefaultVO {

	private static final long serialVersionUID = -3985112029546841234L;

	private Integer creAplcSn;
	private String aplcOrgNm;
	private String phoneno;
	private String mobileNo;
	private String emailAddr;
	private String emailCertYn;
	private String emailCertKey;
	private String domainNm;
	private String domain;
	private String purp;
	private String creOrgCd;
	private String openYn;
	
	public Integer getCreAplcSn() {
		return creAplcSn;
	}
	public void setCreAplcSn(Integer creAplcSn) {
		this.creAplcSn = creAplcSn;
	}
	public String getAplcOrgNm() {
		return aplcOrgNm;
	}
	public void setAplcOrgNm(String aplcOrgNm) {
		this.aplcOrgNm = aplcOrgNm;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getEmailCertYn() {
		return emailCertYn;
	}
	public void setEmailCertYn(String emailCertYn) {
		this.emailCertYn = emailCertYn;
	}
	public String getEmailCertKey() {
		return emailCertKey;
	}
	public void setEmailCertKey(String emailCertKey) {
		this.emailCertKey = emailCertKey;
	}
	public String getDomainNm() {
		return domainNm;
	}
	public void setDomainNm(String domainNm) {
		this.domainNm = domainNm;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getPurp() {
		return purp;
	}
	public void setPurp(String purp) {
		this.purp = purp;
	}
	public String getCreOrgCd() {
		return creOrgCd;
	}
	public void setCreOrgCd(String creOrgCd) {
		this.creOrgCd = creOrgCd;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	
}
